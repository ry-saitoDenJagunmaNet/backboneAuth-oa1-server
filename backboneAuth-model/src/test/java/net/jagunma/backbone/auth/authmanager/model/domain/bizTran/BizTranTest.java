package net.jagunma.backbone.auth.authmanager.model.domain.bizTran;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranTest {

    /**
     * {@link BizTran#createFrom(Long,String,String,Boolean,LocalDate,LocalDate,String,Integer,SubSystem)}テスト
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
        Long bizTranId = 123L;
        String bizTranCode = "AN0001";
        String bizTranName = "畜産メインメニュー";
        Boolean isCenterBizTran = false;
        LocalDate expirationStartDate = LocalDate.of(2020,10,1);
        LocalDate expirationEndDate = LocalDate.of(9999,12,31);
        String subSystemCode = SubSystem.販売_畜産.getCode();
        Integer recordVersion = 1;
        SubSystem subSystem = SubSystem.販売_畜産;

        // 実行
        BizTran bizTran = BizTran.createFrom(
            bizTranId,
            bizTranCode,
            bizTranName,
            isCenterBizTran,
            expirationStartDate,
            expirationEndDate,
            subSystemCode,
            recordVersion,
            subSystem);

        // 結果検証
        assertTrue(bizTran instanceof BizTran);
        assertThat(bizTran.getBizTranId()).isEqualTo(bizTranId);
        assertThat(bizTran.getBizTranCode()).isEqualTo(bizTranCode);
        assertThat(bizTran.getBizTranName()).isEqualTo(bizTranName);
        assertThat(bizTran.getIsCenterBizTran()).isEqualTo(isCenterBizTran);
        assertThat(bizTran.getExpirationStartDate()).isEqualTo(expirationStartDate);
        assertThat(bizTran.getExpirationEndDate()).isEqualTo(expirationEndDate);
        assertThat(bizTran.getSubSystemCode()).isEqualTo(subSystemCode);
        assertThat(bizTran.getRecordVersion()).isEqualTo(recordVersion);
        assertThat(bizTran.getSubSystem()).isEqualTo(subSystem);
    }
}