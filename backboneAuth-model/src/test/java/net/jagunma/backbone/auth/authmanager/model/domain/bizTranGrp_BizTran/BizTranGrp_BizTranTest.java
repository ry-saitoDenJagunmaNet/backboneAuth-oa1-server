package net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp_BizTran;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranGrp_BizTranTest {

    /**
     * {@link BizTranGrp_BizTran#createFrom(Long,Long,Long,String,Integer,BizTranGrp,BizTran,SubSystem)}テスト
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
        Long bizTranGrp_BizTranId = 987654321L;
        Long bizTranGrpId = 123456789L;
        Long bizTranId = 1L;
        String subSystemCode = SubSystem.販売_畜産.getCode();
        Integer recordVersion = 1;
        BizTranGrp bizTranGrp = BizTranGrp.createFrom(123456789L,"ANTG01","データ入力取引グループ", SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産);
        BizTran bizTran = BizTran.createFrom(1L,"AN0001","畜産メインメニュー",false, LocalDate.of(2010,1,1),LocalDate.of(9999,12,31), SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産);
        SubSystem subSystem = SubSystem.販売_畜産;

        // 実行
        BizTranGrp_BizTran bizTranGrp_BizTran = BizTranGrp_BizTran.createFrom(
            bizTranGrp_BizTranId,
            bizTranGrpId,
            bizTranId,
            subSystemCode,
            recordVersion,
            bizTranGrp,
            bizTran,
            subSystem);

        // 結果検証
        assertTrue(bizTranGrp_BizTran instanceof BizTranGrp_BizTran);
        assertThat(bizTranGrp_BizTran.getBizTranGrp_BizTranId()).isEqualTo(bizTranGrp_BizTranId);
        assertThat(bizTranGrp_BizTran.getBizTranGrpId()).isEqualTo(bizTranGrpId);
        assertThat(bizTranGrp_BizTran.getBizTranId()).isEqualTo(bizTranId);
        assertThat(bizTranGrp_BizTran.getSubSystemCode()).isEqualTo(subSystemCode);
        assertThat(bizTranGrp_BizTran.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(bizTranGrp_BizTran.getBizTranGrp()).usingRecursiveComparison().isEqualTo(bizTranGrp);
        assertThat(bizTranGrp_BizTran.getBizTran()).usingRecursiveComparison().isEqualTo(bizTran);
        assertThat(bizTranGrp_BizTran.getSubSystem()).isEqualTo(subSystem);
    }
}