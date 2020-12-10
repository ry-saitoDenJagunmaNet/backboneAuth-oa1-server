package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleCommand.SubSystemRoleGrantRequestAllocateSubSystemRole;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AllocateRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040UnallocateRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaders;
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

    // オペレーター_サブシステムロール割当履歴項目系
    private SubSystemRole subSystemRole1 = SubSystemRole.JA管理者;
    private SubSystemRole subSystemRole2 = SubSystemRole.業務統括者_購買;
    private SubSystemRole subSystemRole3 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole subSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole subSystemRole5 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate validThruStartDate1 = LocalDate.of(2020, 9, 1);
    private LocalDate validThruStartDate2 = LocalDate.of(2020, 9, 2);
    private LocalDate validThruStartDate3 = LocalDate.of(2020, 9, 3);
    private LocalDate validThruStartDate4 = LocalDate.of(2020, 9, 4);
    private LocalDate validThruStartDate5 = LocalDate.of(2020, 9, 5);
    private LocalDate validThruEndDate1 = LocalDate.of(2020, 9, 21);
    private LocalDate validThruEndDate2 = LocalDate.of(2020, 9, 22);
    private LocalDate validThruEndDate3 = LocalDate.of(2020, 9, 23);
    private LocalDate validThruEndDate4 = LocalDate.of(2020, 9, 24);
    private LocalDate validThruEndDate5 = LocalDate.of(2020, 9, 25);

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

    // オペレーター_サブシステムロール割当履歴系
    private List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(301L, operatorId, subSystemRole1.getCode(), validThruStartDate1, validThruEndDate1, 391, operator, subSystemRole1),
        Operator_SubSystemRole.createFrom(302L, operatorId, subSystemRole2.getCode(), validThruStartDate2, validThruEndDate2, 392, operator, subSystemRole2),
        Operator_SubSystemRole.createFrom(303L, operatorId, subSystemRole3.getCode(), validThruStartDate3, validThruEndDate3, 393, operator, subSystemRole3),
        Operator_SubSystemRole.createFrom(304L, operatorId, subSystemRole4.getCode(), validThruStartDate4, validThruEndDate4, 394, operator, subSystemRole4),
        Operator_SubSystemRole.createFrom(305L, operatorId, subSystemRole5.getCode(), validThruStartDate5, validThruEndDate5, 395, operator, subSystemRole5));
    private Operator_SubSystemRoles operator_SubSystemRoles = Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);

    // オペレーター履歴ヘッダー系
    private List<OperatorHistoryHeader> operatorHistoryHeaderList = newArrayList(
        OperatorHistoryHeader.createFrom(601L, operatorId, LocalDateTime.of(2020,10,1,0,1,2), changeCausePlaceholder, null, operator));
    private OperatorHistoryHeaders operatorHistoryHeaders = OperatorHistoryHeaders.createFrom(operatorHistoryHeaderList);

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
        presenter.setOperator(operator);
        presenter.setOperator_SubSystemRoles(operator_SubSystemRoles);
        presenter.setOperatorHistoryHeaders(operatorHistoryHeaders);

        // 期待値
        List<Oa11040AllocateRoleTableVo> oa11040AllocateRoleTableVoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()) {
            Oa11040AllocateRoleTableVo oa11040AllocateRoleTableVo = new Oa11040AllocateRoleTableVo();
            oa11040AllocateRoleTableVo.setRoleCode(operator_SubSystemRole.getSubSystemRoleCode());
            oa11040AllocateRoleTableVo.setRoleName(operator_SubSystemRole.getSubSystemRole().getName());
            oa11040AllocateRoleTableVo.setValidThruStartDate(operator_SubSystemRole.getValidThruStartDate());
            oa11040AllocateRoleTableVo.setValidThruEndDate(operator_SubSystemRole.getValidThruEndDate());
            oa11040AllocateRoleTableVoList.add(oa11040AllocateRoleTableVo);
        }
        List<Oa11040UnallocateRoleTableVo> oa11040UnallocateRoleTableVoList = newArrayList();
        for (SubSystemRole subSystemRole : SubSystemRole.values()) {//ToDo:ソートオーダー回し実装（ユーティリティで実現）
            if (subSystemRole.getCode().length() == 0) { continue; }
            Oa11040UnallocateRoleTableVo oa11040UnallocateRoleTableVo = new Oa11040UnallocateRoleTableVo();
            oa11040UnallocateRoleTableVo.setRoleCode(subSystemRole.getCode());
            oa11040UnallocateRoleTableVo.setRoleName(subSystemRole.getName());
            oa11040UnallocateRoleTableVoList.add(oa11040UnallocateRoleTableVo);
        }

        Oa11040Vo expectedVo = new Oa11040Vo();
        expectedVo.setOperatorId(operatorId);
        expectedVo.setJa(jaCode + " " + jaName);
        expectedVo.setOperator(operatorCode + " " + operatorName);
        expectedVo.setOa11040AllocateRoleTableVoList(oa11040AllocateRoleTableVoList);
        expectedVo.setOa11040UnallocateRoleTableVoList(oa11040UnallocateRoleTableVoList);
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
     *    （オペレーター_サブシステムロール割当履歴 なし）
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
        presenter.setOperator(operator);
        presenter.setOperator_SubSystemRoles(Operator_SubSystemRoles.createFrom(newArrayList()));
        presenter.setOperatorHistoryHeaders(operatorHistoryHeaders);

        // 期待値
        List<Oa11040AllocateRoleTableVo> oa11040AllocateRoleTableVoList = newArrayList();

        List<Oa11040UnallocateRoleTableVo> oa11040UnallocateRoleTableVoList = newArrayList();
        for (SubSystemRole subSystemRole : SubSystemRole.values()) {//ToDo:ソートオーダー回し実装（ユーティリティで実現）
            if (subSystemRole.getCode().length() == 0) { continue; }
            Oa11040UnallocateRoleTableVo oa11040UnallocateRoleTableVo = new Oa11040UnallocateRoleTableVo();
            oa11040UnallocateRoleTableVo.setRoleCode(subSystemRole.getCode());
            oa11040UnallocateRoleTableVo.setRoleName(subSystemRole.getName());
            oa11040UnallocateRoleTableVoList.add(oa11040UnallocateRoleTableVo);
        }

        Oa11040Vo expectedVo = new Oa11040Vo();
        expectedVo.setOperatorId(operatorId);
        expectedVo.setJa(jaCode + " " + jaName);
        expectedVo.setOperator(operatorCode + " " + operatorName);
        expectedVo.setOa11040AllocateRoleTableVoList(oa11040AllocateRoleTableVoList);
        expectedVo.setOa11040UnallocateRoleTableVoList(oa11040UnallocateRoleTableVoList);
        expectedVo.setChangeCause(null);
        expectedVo.setChangeCausePlaceholder(changeCausePlaceholder);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}
