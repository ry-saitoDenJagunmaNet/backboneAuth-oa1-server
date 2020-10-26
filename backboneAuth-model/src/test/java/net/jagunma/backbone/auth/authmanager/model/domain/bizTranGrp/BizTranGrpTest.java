package net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranGrpTest {

    /**
     * {@link BizTranGrp#createFrom(Long,String,String,String,Integer,SubSystem)}テスト
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
        Long bizTranGrpId = 123456L;
        String bizTranGrpCode = "ANTG01";
        String bizTranGrpName = "データ入力取引グループ";
        String subSystemCode = SubSystem.販売_畜産.getCode();
        Integer recordVersion = 1;
        SubSystem subSystem = SubSystem.販売_畜産;

        // 実行
        BizTranGrp bizTranGrp = BizTranGrp.createFrom(
            bizTranGrpId,
            bizTranGrpCode,
            bizTranGrpName,
            subSystemCode,
            recordVersion,
            subSystem);

        // 結果検証
        assertTrue(bizTranGrp instanceof BizTranGrp);
        assertThat(bizTranGrp.getBizTranGrpId()).isEqualTo(bizTranGrpId);
        assertThat(bizTranGrp.getBizTranGrpCode()).isEqualTo(bizTranGrpCode);
        assertThat(bizTranGrp.getBizTranGrpName()).isEqualTo(bizTranGrpName);
        assertThat(bizTranGrp.getSubSystemCode()).isEqualTo(subSystemCode);
        assertThat(bizTranGrp.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(bizTranGrp.getSubSystem()).isEqualTo(subSystem);
    }
}