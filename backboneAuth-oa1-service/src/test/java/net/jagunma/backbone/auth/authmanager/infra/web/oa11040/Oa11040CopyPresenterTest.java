package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AllocateRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11040CopyPresenterTest {

    // 実行既定値
    private Long operatorId = 123456L;
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
    private List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(301L, operatorId, subSystemRole1.getCode(), validThruStartDate1, validThruEndDate1, 391, null, subSystemRole1),
        Operator_SubSystemRole.createFrom(302L, operatorId, subSystemRole2.getCode(), validThruStartDate2, validThruEndDate2, 392, null, subSystemRole2),
        Operator_SubSystemRole.createFrom(303L, operatorId, subSystemRole3.getCode(), validThruStartDate3, validThruEndDate3, 393, null, subSystemRole3),
        Operator_SubSystemRole.createFrom(304L, operatorId, subSystemRole4.getCode(), validThruStartDate4, validThruEndDate4, 394, null, subSystemRole4),
        Operator_SubSystemRole.createFrom(305L, operatorId, subSystemRole5.getCode(), validThruStartDate5, validThruEndDate5, 395, null, subSystemRole5));
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
        Oa11040Vo vo = new Oa11040Vo();
        Oa11040CopyPresenter presenter = new Oa11040CopyPresenter();
        presenter.setOperator_SubSystemRoles(operator_SubSystemRoles);

        // 期待値
        List<Oa11040AllocateRoleTableVo> oa11040AllocateRoleTableVoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()) {
            Oa11040AllocateRoleTableVo oa11040AllocateRoleTableVo = new Oa11040AllocateRoleTableVo();
            oa11040AllocateRoleTableVo.setRoleCode(operator_SubSystemRole.getSubSystemRoleCode());
            oa11040AllocateRoleTableVo.setRoleName(operator_SubSystemRole.getSubSystemRole().getDisplayName());
            oa11040AllocateRoleTableVo.setValidThruStartDate(operator_SubSystemRole.getValidThruStartDate());
            oa11040AllocateRoleTableVo.setValidThruEndDate(operator_SubSystemRole.getValidThruEndDate());
            oa11040AllocateRoleTableVoList.add(oa11040AllocateRoleTableVo);
        }

        Oa11040Vo expectedVo = new Oa11040Vo();
        expectedVo.setOa11040AllocateRoleTableVoList(oa11040AllocateRoleTableVoList);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}