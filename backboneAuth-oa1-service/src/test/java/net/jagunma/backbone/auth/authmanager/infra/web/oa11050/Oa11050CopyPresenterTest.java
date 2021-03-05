package net.jagunma.backbone.auth.authmanager.infra.web.oa11050;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11050CopyPresenterTest {

    // 実行既定値
    private Long operatorId = 123456L;
    private String bizTranRoleCode0 = "KBAG01";
    private String bizTranRoleCode1 = "YSAG10";
    private String bizTranRoleCode2 = "HKAG10";
    private String bizTranRoleCode3 = "ANAG01";
    private String bizTranRoleName0 = "（購買）購買業務基本";
    private String bizTranRoleName1 = "（青果）管理者";
    private String bizTranRoleName2 = "（米）ＪＡ取引全般";
    private String bizTranRoleName3 = "（畜産）取引全般";
    private SubSystem subSystem0 = SubSystem.購買;
    private SubSystem subSystem1 = SubSystem.販売_青果;
    private SubSystem subSystem2 = SubSystem.販売_米;
    private SubSystem subSystem3 = SubSystem.販売_畜産;
    private LocalDate validThruStartDate0 = LocalDate.of(2020, 1, 1);
    private LocalDate validThruStartDate1 = LocalDate.of(2020, 1, 2);
    private LocalDate validThruStartDate2 = LocalDate.of(2020, 1, 3);
    private LocalDate validThruStartDate3 = LocalDate.of(2020, 1, 4);
    private LocalDate validThruEndDate0 = LocalDate.of(9999, 1, 21);
    private LocalDate validThruEndDate1 = LocalDate.of(9999, 1, 22);
    private LocalDate validThruEndDate2 = LocalDate.of(9999, 1, 23);
    private LocalDate validThruEndDate3 = LocalDate.of(9999, 1, 24);
    private List<BizTranRole> bizTranRoleList = newArrayList(
        BizTranRole.createFrom(401L, bizTranRoleCode0, bizTranRoleName0, subSystem0.getCode(), null, subSystem0),
        BizTranRole.createFrom(402L, bizTranRoleCode1, bizTranRoleName1, subSystem1.getCode(), null, subSystem1),
        BizTranRole.createFrom(403L, bizTranRoleCode2, bizTranRoleName2, subSystem2.getCode(), null, subSystem2),
        BizTranRole.createFrom(404L, bizTranRoleCode3, bizTranRoleName3, subSystem3.getCode(), null, subSystem3));
    private List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList(
        Operator_BizTranRole.createFrom(501L, operatorId, bizTranRoleList.get(0).getBizTranRoleId(), validThruStartDate0, validThruEndDate0, null, null, bizTranRoleList.get(0)),
        Operator_BizTranRole.createFrom(502L, operatorId, bizTranRoleList.get(1).getBizTranRoleId(), validThruStartDate1, validThruEndDate1, null, null, bizTranRoleList.get(1)),
        Operator_BizTranRole.createFrom(503L, operatorId, bizTranRoleList.get(2).getBizTranRoleId(), validThruStartDate2, validThruEndDate2, null, null, bizTranRoleList.get(2)),
        Operator_BizTranRole.createFrom(504L, operatorId, bizTranRoleList.get(3).getBizTranRoleId(), validThruStartDate3, validThruEndDate3, null, null, bizTranRoleList.get(3)));
    private Operator_BizTranRoles operator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);

    /**
     * {@link Oa11050CopyPresenter#bindTo(Oa11050Vo vo)}テスト
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
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = newArrayList();
        for(Operator_BizTranRole operator_BizTranRole : operator_BizTranRoles.getValues()) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
            assignRoleDto.setIsModifiable(true);
            assignRoleDtoList.add(assignRoleDto);
        }
        Oa11050CopyPresenter presenter = new Oa11050CopyPresenter();
        presenter.setAssignRoleDtoList(assignRoleDtoList);
        Oa11050Vo vo = new Oa11050Vo();

        // 期待値
        List<Oa11050AssignRoleTableVo> assignRoleTableVoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoles.getValues()) {
            Oa11050AssignRoleTableVo assignRoleTableVo = new Oa11050AssignRoleTableVo();
            assignRoleTableVo.setRoleId(operator_BizTranRole.getBizTranRoleId());
            assignRoleTableVo.setRoleCode(operator_BizTranRole.getBizTranRole().getBizTranRoleCode());
            assignRoleTableVo.setRoleName(operator_BizTranRole.getBizTranRole().getBizTranRoleName());
            assignRoleTableVo.setSubSystemCode(operator_BizTranRole.getBizTranRole().getSubSystemCode());
            assignRoleTableVo.setValidThruStartDate(operator_BizTranRole.getValidThruStartDate());
            assignRoleTableVo.setValidThruEndDate(operator_BizTranRole.getValidThruEndDate());
            assignRoleTableVo.setIsModifiable(true);
            assignRoleTableVoList.add(assignRoleTableVo);
        }
        Oa11050Vo expectedVo = new Oa11050Vo();
        expectedVo.setAssignRoleTableVoList(assignRoleTableVoList);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11050CopyPresenter#bindTo(Oa11050Vo vo)}テスト
     *  ●パターン
     *    正常
     *    （アサインロールDto（オペレーター_取引ロール割当）が ０件）
     *
     *  ●検証事項
     *  ・Voへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test1() {
        // 実行値
        Oa11050CopyPresenter presenter = new Oa11050CopyPresenter();
        presenter.setAssignRoleDtoList(newArrayList());
        Oa11050Vo vo = new Oa11050Vo();

        // 期待値
        List<Oa11050AssignRoleTableVo> assignRoleTableVoList = newArrayList();
        Oa11050Vo expectedVo = new Oa11050Vo();
        expectedVo.setAssignRoleTableVoList(assignRoleTableVoList);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}