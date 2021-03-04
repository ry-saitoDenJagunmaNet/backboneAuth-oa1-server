package net.jagunma.backbone.auth.authmanager.infra.web.oa11050;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedCopyRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11050CopyConverterTest {

    // 実行既定値
    // 各種オペレーター項目系
    private Long signInOperatorId = 987654L;
    private Long selectedOperatorId = 876543L;
    private Long operatorId = 123456L;

    // オペレーター_取引ロール割当項目系
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

    // オペレーター_取引ロール割当系
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
     * {@link Oa11050CopyConverter#with(Oa11050Vo vo, Long signInOperatorId, Long selectedOperatorId)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Converterへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test() {
        // 実行値
        List<Oa11050AssignRoleTableVo> assignRoleTableVoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoles.getValues()) {
            Oa11050AssignRoleTableVo assignRoleTableVo = new Oa11050AssignRoleTableVo();
            assignRoleTableVo.setRoleId(operator_BizTranRole.getBizTranRoleId());
            assignRoleTableVo.setRoleCode(operator_BizTranRole.getBizTranRole().getBizTranRoleCode());
            assignRoleTableVo.setRoleName(operator_BizTranRole.getBizTranRole().getBizTranRoleName());
            assignRoleTableVo.setValidThruStartDate(operator_BizTranRole.getValidThruStartDate());
            assignRoleTableVo.setValidThruEndDate(operator_BizTranRole.getValidThruEndDate());
            assignRoleTableVo.setIsModifiable(true);
            assignRoleTableVoList.add(assignRoleTableVo);
        }
        Oa11050Vo vo = new Oa11050Vo();
        vo.setAssignRoleTableVoList(assignRoleTableVoList);

        // 期待値
        List<BizTranRoleGrantedCopyRequestAssignRole> assignRoleList = newArrayList();
        for (Oa11050AssignRoleTableVo assignRoleTableVo : assignRoleTableVoList) {
            Oa11050CopyAssignRoleConverter copyAssignRoleConverter = Oa11050CopyAssignRoleConverter.with(assignRoleTableVo);
            assignRoleList.add(copyAssignRoleConverter);
        }

        // 実行
        Oa11050CopyConverter converter = Oa11050CopyConverter.with(vo, signInOperatorId, selectedOperatorId);

        // 結果検証
        assertTrue(converter instanceof Oa11050CopyConverter);
        assertThat(converter.getSignInOperatorId()).isEqualTo(signInOperatorId);
        assertThat(converter.getSelectedOperatorId()).isEqualTo(selectedOperatorId);
        assertThat(converter.getAssignRoleList()).usingRecursiveComparison().isEqualTo(assignRoleList);
    }

    /**
     * {@link Oa11050CopyConverter#with(Oa11050Vo vo, Long signInOperatorId, Long selectedOperatorId)}テスト
     *  ●パターン
     *    正常
     *    （付与ロールテーブル が ０件）
     *
     *  ●検証事項
     *  ・Converterへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test1() {
        // 実行値
        Oa11050Vo vo = new Oa11050Vo();
        vo.setAssignRoleTableVoList(newArrayList());

        // 期待値
        List<SubSystemRoleGrantedCopyRequestAssignRole> assignRoleList = newArrayList();

        // 実行
        Oa11050CopyConverter converter = Oa11050CopyConverter.with(vo, signInOperatorId, selectedOperatorId);

        // 結果検証
        assertTrue(converter instanceof Oa11050CopyConverter);
        assertThat(converter.getSignInOperatorId()).isEqualTo(signInOperatorId);
        assertThat(converter.getSelectedOperatorId()).isEqualTo(selectedOperatorId);
        assertThat(converter.getAssignRoleList()).usingRecursiveComparison().isEqualTo(assignRoleList);
    }

    /**
     * {@link Oa11050CopyConverter#with(Oa11050Vo vo, Long signInOperatorId, Long selectedOperatorId)}テスト
     *  ●パターン
     *    正常
     *    （付与ロールテーブル が null）
     *
     *  ●検証事項
     *  ・Converterへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test2() {
        // 実行値
        Oa11050Vo vo = new Oa11050Vo();
        vo.setAssignRoleTableVoList(null);

        // 期待値
        List<SubSystemRoleGrantedCopyRequestAssignRole> assignRoleList = newArrayList();

        // 実行
        Oa11050CopyConverter converter = Oa11050CopyConverter.with(vo, signInOperatorId, selectedOperatorId);

        // 結果検証
        assertTrue(converter instanceof Oa11050CopyConverter);
        assertThat(converter.getSignInOperatorId()).isEqualTo(signInOperatorId);
        assertThat(converter.getSelectedOperatorId()).isEqualTo(selectedOperatorId);
        assertThat(converter.getAssignRoleList()).usingRecursiveComparison().isEqualTo(assignRoleList);
    }
}