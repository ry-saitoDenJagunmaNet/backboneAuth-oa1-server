package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11040CopyPresenterTest {

    // 実行既定値
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
    private List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(null, null, subSystemRole0.getCode(), validThruStartDate0, validThruEndDate0, null, null, subSystemRole0),
        Operator_SubSystemRole.createFrom(null, null, subSystemRole1.getCode(), validThruStartDate1, validThruEndDate1, null, null, subSystemRole1),
        Operator_SubSystemRole.createFrom(null, null, subSystemRole2.getCode(), validThruStartDate2, validThruEndDate2, null, null, subSystemRole2),
        Operator_SubSystemRole.createFrom(null, null, subSystemRole4.getCode(), validThruStartDate4, validThruEndDate4, null, null, subSystemRole4),
        Operator_SubSystemRole.createFrom(null, null, subSystemRole6.getCode(), validThruStartDate6, validThruEndDate6, null, null, subSystemRole6));
    private Operator_SubSystemRoles operator_SubSystemRoles = Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);

    /**
     * {@link Oa11040CopyPresenter#bindTo(Oa11040Vo vo)}テスト
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
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = newArrayList();
        for(Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(true);
            assignRoleDtoList.add(assignRoleDto);
        }
        Oa11040CopyPresenter presenter = new Oa11040CopyPresenter();
        presenter.setAssignRoleDtoList(assignRoleDtoList);
        Oa11040Vo vo = new Oa11040Vo();

        // 期待値
        List<Oa11040AssignRoleTableVo> assignRoleTableVoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()) {
            Oa11040AssignRoleTableVo assignRoleTableVo = new Oa11040AssignRoleTableVo();
            assignRoleTableVo.setRoleCode(operator_SubSystemRole.getSubSystemRoleCode());
            assignRoleTableVo.setRoleName(operator_SubSystemRole.getSubSystemRole().getDisplayName());
            assignRoleTableVo.setValidThruStartDate(operator_SubSystemRole.getValidThruStartDate());
            assignRoleTableVo.setValidThruEndDate(operator_SubSystemRole.getValidThruEndDate());
            assignRoleTableVo.setIsModifiable(true);
            assignRoleTableVoList.add(assignRoleTableVo);
        }
        Oa11040Vo expectedVo = new Oa11040Vo();
        expectedVo.setAssignRoleTableVoList(assignRoleTableVoList);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11040CopyPresenter#bindTo(Oa11040Vo vo)}テスト
     *  ●パターン
     *    正常
     *    （アサインロールDto（オペレーター_サブシステムロール割当履歴）が ０件）
     *
     *  ●検証事項
     *  ・Voへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test1() {
        // 実行値
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = newArrayList();
        Oa11040CopyPresenter presenter = new Oa11040CopyPresenter();
        presenter.setAssignRoleDtoList(assignRoleDtoList);
        Oa11040Vo vo = new Oa11040Vo();

        // 期待値
        List<Oa11040AssignRoleTableVo> assignRoleTableVoList = newArrayList();
        Oa11040Vo expectedVo = new Oa11040Vo();
        expectedVo.setAssignRoleTableVoList(assignRoleTableVoList);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}