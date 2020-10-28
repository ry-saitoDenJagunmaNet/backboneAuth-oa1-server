package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranRole_BizTranGrpTest {

    /**
     * {@link BizTranRole_BizTranGrp#createFrom(Long,Long,Long,String,Integer,BizTranRole,BizTranGrp,SubSystem)}テスト
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
        Long bizTranRole_BizTranGrpId = 12345678L;
        Long bizTranRoleId = 10000001L;
        Long bizTranGrpId = 1000001L;
        String subSystemCode = SubSystem.販売_畜産.getCode();
        Integer recordVersion = 1;
        BizTranGrp bizTranGrp = BizTranGrp.createFrom(1000001L,"ANTG01","データ入力取引グループ", SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産);
        BizTranRole bizTranRole = BizTranRole.createFrom(10000001L,"ANAG01","（畜産）取引全般",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産);
        SubSystem subSystem = SubSystem.販売_畜産;

        // 実行
        BizTranRole_BizTranGrp bizTranRole_BizTranGrp = BizTranRole_BizTranGrp.createFrom(
            bizTranRole_BizTranGrpId,
            bizTranRoleId,
            bizTranGrpId,
            subSystemCode,
            recordVersion,
            bizTranRole,
            bizTranGrp,
            subSystem);

        // 結果検証
        assertTrue(bizTranRole_BizTranGrp instanceof BizTranRole_BizTranGrp);
        assertThat(bizTranRole_BizTranGrp.getBizTranRole_BizTranGrpId()).isEqualTo(bizTranRole_BizTranGrpId);
        assertThat(bizTranRole_BizTranGrp.getBizTranRoleId()).isEqualTo(bizTranRoleId);
        assertThat(bizTranRole_BizTranGrp.getBizTranGrpId()).isEqualTo(bizTranGrpId);
        assertThat(bizTranRole_BizTranGrp.getSubSystemCode()).isEqualTo(subSystemCode);
        assertThat(bizTranRole_BizTranGrp.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(bizTranRole_BizTranGrp.getBizTranRole()).usingRecursiveComparison().isEqualTo(bizTranRole);
        assertThat(bizTranRole_BizTranGrp.getBizTranGrp()).usingRecursiveComparison().isEqualTo(bizTranGrp);
        assertThat(bizTranRole_BizTranGrp.getSubSystem()).isEqualTo(subSystem);
    }
}