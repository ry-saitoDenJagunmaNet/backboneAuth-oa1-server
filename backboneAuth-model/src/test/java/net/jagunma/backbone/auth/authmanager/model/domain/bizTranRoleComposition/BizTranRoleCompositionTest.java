package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranRoleCompositionTest {

    // 実行既定値
    // 取引群データ作成
    private BizTrans cretaeBizTrans() {
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(100001L,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31), SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTran.createFrom(100002L,"AN1110","前日処理照会",false,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31), SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTran.createFrom(100003L,"AN0002","畜産業務（センター）メニュー",true,LocalDate.of(2020,10,1),LocalDate.of(9999,12,31), SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return BizTrans.createFrom(list);
    }
    // 取引グループ群データ作成
    private BizTranGrps cretaeBizTranGrps() {
        List<BizTranGrp> list = newArrayList();
        list.add(BizTranGrp.createFrom(10001L,"ANTG01","データ入力取引グループ", SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranGrp.createFrom(10002L,"ANTG02","精算取引グループ", SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTranGrp.createFrom(10003L,"ANTG10","センター維持管理グループ", SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        return BizTranGrps.createFrom(list);
    }
    // 取引グループ_取引割当群データ作成
    private BizTranGrp_BizTrans cretaeBizTranGrp_BizTrans() {
        List<BizTranGrp_BizTran> list = newArrayList();
        list.add(BizTranGrp_BizTran.createFrom(101L,10001L,100001L,SubSystem.販売_畜産.getCode(),1,
            cretaeBizTranGrps().getValues().stream().filter(b->b.getBizTranGrpId().equals(10001L)).findFirst().orElse(null),
            cretaeBizTrans().getValues().stream().filter(b->b.getBizTranId().equals(100001L)).findFirst().orElse(null),
            SubSystem.販売_畜産));
        list.add(BizTranGrp_BizTran.createFrom(102L,10002L,100002L,SubSystem.販売_畜産.getCode(),1,
            cretaeBizTranGrps().getValues().stream().filter(b->b.getBizTranGrpId().equals(10002L)).findFirst().orElse(null),
            cretaeBizTrans().getValues().stream().filter(b->b.getBizTranId().equals(100002L)).findFirst().orElse(null),
            SubSystem.販売_畜産));
        list.add(BizTranGrp_BizTran.createFrom(103L,10003L,100003L,SubSystem.販売_畜産.getCode(),1,
            cretaeBizTranGrps().getValues().stream().filter(b->b.getBizTranGrpId().equals(10003L)).findFirst().orElse(null),
            cretaeBizTrans().getValues().stream().filter(b->b.getBizTranId().equals(100003L)).findFirst().orElse(null),
            SubSystem.販売_畜産));
        return BizTranGrp_BizTrans.createFrom(list);
    }
    // 取引ロール群データ作成
    private BizTranRoles cretaeBizTranRoles() {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(1001L,"ANAG01","（畜産）取引全般","KB",1,SubSystem.購買));
        list.add(BizTranRole.createFrom(1002L,"ANAG02","（畜産）維持管理担当者","KB",1,SubSystem.購買));
        list.add(BizTranRole.createFrom(1003L,"ANAG99","（畜産）維持管理責任者","KB",1,SubSystem.購買));
        return BizTranRoles.createFrom(list);
    }
    // 取引ロール_取引グループ割当群データ作成
    private BizTranRole_BizTranGrps cretaeBizTranRole_BizTranGrps() {
        List<BizTranRole_BizTranGrp> list = newArrayList();
        list.add(BizTranRole_BizTranGrp.createFrom(1L,1001L,10001L,SubSystem.販売_畜産.getCode(),1,
            cretaeBizTranRoles().getValues().stream().filter(b->b.getBizTranRoleId().equals(1001L)).findFirst().orElse(null),
            cretaeBizTranGrps().getValues().stream().filter(b->b.getBizTranGrpId().equals(10001L)).findFirst().orElse(null),
            SubSystem.販売_畜産));
        list.add(BizTranRole_BizTranGrp.createFrom(2L,1002L,10002L,SubSystem.販売_畜産.getCode(),1,
            cretaeBizTranRoles().getValues().stream().filter(b->b.getBizTranRoleId().equals(1002L)).findFirst().orElse(null),
            cretaeBizTranGrps().getValues().stream().filter(b->b.getBizTranGrpId().equals(10002L)).findFirst().orElse(null),
            SubSystem.販売_畜産));
        list.add(BizTranRole_BizTranGrp.createFrom(3L,1003L,10003L,SubSystem.販売_畜産.getCode(),1,
            cretaeBizTranRoles().getValues().stream().filter(b->b.getBizTranRoleId().equals(1003L)).findFirst().orElse(null),
            cretaeBizTranGrps().getValues().stream().filter(b->b.getBizTranGrpId().equals(10003L)).findFirst().orElse(null),
            SubSystem.販売_畜産));
        return BizTranRole_BizTranGrps.createFrom(list);
    }

    /**
     * {@link BizTranRoleComposition#createFrom(BizTrans,BizTranGrps,BizTranGrp_BizTrans,BizTranRoles,BizTranRole_BizTranGrps)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_test0() {

        // 実行値 ＆ 期待値
        BizTrans bizTrans = cretaeBizTrans();
        BizTranGrps bizTranGrps = cretaeBizTranGrps();
        BizTranGrp_BizTrans bizTranGrp_BizTrans = cretaeBizTranGrp_BizTrans();
        BizTranRoles bizTranRoles = cretaeBizTranRoles();
        BizTranRole_BizTranGrps bizTranRole_BizTranGrps = cretaeBizTranRole_BizTranGrps();

        // 実行
        BizTranRoleComposition bizTranRoleComposition = BizTranRoleComposition.createFrom(
            bizTrans,
            bizTranGrps,
            bizTranGrp_BizTrans,
            bizTranRoles,
            bizTranRole_BizTranGrps);

        // 結果検証
        assertTrue(bizTranRoleComposition instanceof BizTranRoleComposition);
        assertThat(bizTranRoleComposition.getBizTrans()).usingRecursiveComparison().isEqualTo(bizTrans);
        assertThat(bizTranRoleComposition.getBizTranGrps()).usingRecursiveComparison().isEqualTo(bizTranGrps);
        assertThat(bizTranRoleComposition.getBizTranGrp_BizTrans().getValues()).usingRecursiveComparison().isEqualTo(bizTranGrp_BizTrans.getValues());
        assertThat(bizTranRoleComposition.getBizTranRoles()).usingRecursiveComparison().isEqualTo(bizTranRoles);
        assertThat(bizTranRoleComposition.getBizTranRole_BizTranGrps().getValues()).usingRecursiveComparison().isEqualTo(bizTranRole_BizTranGrps.getValues());
    }
}