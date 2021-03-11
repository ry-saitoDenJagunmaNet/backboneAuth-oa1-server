package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAllRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040UnAssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11040InitPresenterTest {

    // 実行既定値
    // オペレーター項目系
    private Long operatorId = 123456L;
    private String operatorCode = "yu123456";
    private String operatorName = "オペレーター名";

    // オペレーター_サブシステムロール割当項目系
    private SubSystemRole subSystemRole0 = SubSystemRole.JA管理者;
    private SubSystemRole subSystemRole1 = SubSystemRole.業務統括者_購買;
    private SubSystemRole subSystemRole2 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole subSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole subSystemRole6 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate validThruStartDate0 = LocalDate.of(2020, 9, 10);
    private LocalDate validThruStartDate1 = LocalDate.of(2020, 9, 11);
    private LocalDate validThruStartDate2 = LocalDate.of(2020, 9, 12);
    private LocalDate validThruStartDate4 = LocalDate.of(2020, 9, 14);
    private LocalDate validThruStartDate6 = LocalDate.of(2020, 9, 16);
    private LocalDate validThruEndDate0 = LocalDate.of(2020, 9, 20);
    private LocalDate validThruEndDate1 = LocalDate.of(2020, 9, 21);
    private LocalDate validThruEndDate2 = LocalDate.of(2020, 9, 22);
    private LocalDate validThruEndDate4 = LocalDate.of(2020, 9, 24);
    private LocalDate validThruEndDate6 = LocalDate.of(2020, 9, 26);

    // オペレーター履歴ヘッダー項目系
    private String changeCausePlaceholder = "業務統括者（販売・青果）に昇格";

    // ＪＡAtMoment
    private Long jaId = 6L;
    private String jaCode = "006";
    private String jaName = "JA前橋市";
    private JaAtMoment jaAtMoment = JaAtMoment.builder()
        .withIdentifier(jaId)
        .withJaAttribute(JaAttribute.builder()
            .withJaCode(JaCode.of(jaCode))
            .withName(jaName)
            .build())
        .build();

    // 店舗AtMoment
    private Long branchId = 1L;
    private String branchCode = "001";
    private BranchAtMoment branchAtMoment = BranchAtMoment.builder().withIdentifier(branchId).withJaAtMoment(jaAtMoment).withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.一般).withBranchCode(BranchCode.of(branchCode)).withName("本店").build()).build();

    // オペレーター系
    private Operator operator = Operator.createFrom(operatorId, operatorCode, operatorName, null, null, null, null, null, jaCode, branchId, branchCode, null, null, branchAtMoment);

    // オペレーター_サブシステムロール割当系
    private List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(301L, operatorId, subSystemRole0.getCode(), validThruStartDate0, validThruEndDate0, null, operator, subSystemRole0),
        Operator_SubSystemRole.createFrom(302L, operatorId, subSystemRole1.getCode(), validThruStartDate1, validThruEndDate1, null, operator, subSystemRole1),
        Operator_SubSystemRole.createFrom(303L, operatorId, subSystemRole2.getCode(), validThruStartDate2, validThruEndDate2, null, operator, subSystemRole2),
        Operator_SubSystemRole.createFrom(304L, operatorId, subSystemRole4.getCode(), validThruStartDate4, validThruEndDate4, null, operator, subSystemRole4),
        Operator_SubSystemRole.createFrom(305L, operatorId, subSystemRole6.getCode(), validThruStartDate6, validThruEndDate6, null, operator, subSystemRole6));
    private Operator_SubSystemRoles operator_SubSystemRoles = Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);

    // オペレーター履歴ヘッダー系
    private OperatorHistoryHeader operatorHistoryHeader = OperatorHistoryHeader.createFrom(null, operatorId, LocalDateTime.of(2020,10,1,0,1,2), changeCausePlaceholder, null, operator);

    private List<SubSystemRoleGrantedAssignRoleDto> createSubSystemRoleGrantedAssignRoleDtoList() {
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = newArrayList();
        createOa11040AssignRole(assignRoleDtoList, newArrayList());
        return assignRoleDtoList;
    }
    private List<Oa11040AssignRoleTableVo> createOa11040AssignRoleTableVoList() {
        List<Oa11040AssignRoleTableVo> assignRoleTableVoList = newArrayList();
        createOa11040AssignRole(newArrayList(), assignRoleTableVoList);
        return assignRoleTableVoList;
    }
    private void createOa11040AssignRole(List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList, List<Oa11040AssignRoleTableVo> assignRoleTableVoList) {
        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(true);
            assignRoleDtoList.add(assignRoleDto);

            Oa11040AssignRoleTableVo assignRoleTableVo = new Oa11040AssignRoleTableVo();
            assignRoleTableVo.setRoleCode(operator_SubSystemRole.getSubSystemRoleCode());
            assignRoleTableVo.setRoleName(operator_SubSystemRole.getSubSystemRole().getDisplayName());
            assignRoleTableVo.setValidThruStartDate(operator_SubSystemRole.getValidThruStartDate());
            assignRoleTableVo.setValidThruEndDate(operator_SubSystemRole.getValidThruEndDate());
            assignRoleTableVo.setIsModifiable(true);
            assignRoleTableVoList.add(assignRoleTableVo);
        }
    }

    private List<SubSystemRoleGrantedAllRoleDto> createSubSystemRoleGrantedAllRoleDtoList() {
        List<SubSystemRoleGrantedAllRoleDto> allRoleDtoList = newArrayList();
        createAllRole(allRoleDtoList, newArrayList());
        return allRoleDtoList;
    }
    private List<Oa11040UnAssignRoleTableVo> createOa11040UnAssignRoleTableVoList() {
        List<Oa11040UnAssignRoleTableVo> unAssignRoleTableVoList = newArrayList();
        createAllRole(newArrayList(), unAssignRoleTableVoList);
        return unAssignRoleTableVoList;
    }
    private void createAllRole(List<SubSystemRoleGrantedAllRoleDto> allRoleDtoList, List<Oa11040UnAssignRoleTableVo> unAssignRoleTableVoList) {
        for (SubSystemRole subSystemRole : SubSystemRole.getValidList()) {
            SubSystemRoleGrantedAllRoleDto allRoleDto = new SubSystemRoleGrantedAllRoleDto();
            allRoleDto.setSubSystemRole(subSystemRole);
            allRoleDto.setIsModifiable(true);
            allRoleDtoList.add(allRoleDto);

            Oa11040UnAssignRoleTableVo unAssignRoleTableVo = new Oa11040UnAssignRoleTableVo();
            unAssignRoleTableVo.setRoleCode(subSystemRole.getCode());
            unAssignRoleTableVo.setRoleName(subSystemRole.getDisplayName());
            unAssignRoleTableVo.setIsModifiable(true);
            unAssignRoleTableVoList.add(unAssignRoleTableVo);
        }
    }

    /**
     * {@link Oa11040InitPresenter#bindTo(Oa11040Vo vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test() {
        // 実行値
        Oa11040Vo vo = new Oa11040Vo();
        Oa11040InitPresenter presenter = new Oa11040InitPresenter();
        presenter.setOperatorId(operatorId);
        presenter.setAssignRoleDtoList(createSubSystemRoleGrantedAssignRoleDtoList());
        presenter.setAllRoleDtoList(createSubSystemRoleGrantedAllRoleDtoList());
        presenter.setOperatorHistoryHeader(operatorHistoryHeader);

        // 期待値
        Oa11040Vo expectedVo = new Oa11040Vo();
        expectedVo.setOperatorId(operatorId);
        expectedVo.setJa(jaCode + " " + jaName);
        expectedVo.setOperator(operatorCode + " " + operatorName);
        expectedVo.setAssignRoleTableVoList(createOa11040AssignRoleTableVoList());
        expectedVo.setUnAssignRoleTableVoList(createOa11040UnAssignRoleTableVoList());
        expectedVo.setChangeCause(null);
        expectedVo.setChangeCausePlaceholder(changeCausePlaceholder);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11040InitPresenter#bindTo(Oa11040Vo vo)}テスト
     *  ●パターン
     *    正常
     *    （アサインロールDto（オペレーター_サブシステムロール割当）が ０件）
     *    （全ロールDto（サブシステムロール）が ０件）
     *
     *  ●検証事項
     *  ・Voへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test1() {
        // 実行値
        Oa11040Vo vo = new Oa11040Vo();
        Oa11040InitPresenter presenter = new Oa11040InitPresenter();
        presenter.setOperatorId(operatorId);
        presenter.setAssignRoleDtoList(newArrayList());
        presenter.setAllRoleDtoList(newArrayList());
        presenter.setOperatorHistoryHeader(operatorHistoryHeader);

        // 期待値
        Oa11040Vo expectedVo = new Oa11040Vo();
        expectedVo.setOperatorId(operatorId);
        expectedVo.setJa(jaCode + " " + jaName);
        expectedVo.setOperator(operatorCode + " " + operatorName);
        expectedVo.setAssignRoleTableVoList(newArrayList());
        expectedVo.setUnAssignRoleTableVoList(newArrayList());
        expectedVo.setChangeCause(null);
        expectedVo.setChangeCausePlaceholder(changeCausePlaceholder);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}
