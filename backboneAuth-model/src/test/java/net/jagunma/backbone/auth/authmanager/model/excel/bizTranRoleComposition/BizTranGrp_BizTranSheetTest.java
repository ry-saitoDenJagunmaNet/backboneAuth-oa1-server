package net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranGrp_BizTranSheetTest {

    /**
     * {@link BizTranGrp_BizTranSheet#createFrom(int,String,String,String,String,String,Boolean,LocalDate,LocalDate)}テスト
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
        int rowno = 1;
        String subSystemName = "販売・畜産";
        String bizTranGrpCode = "ANTG01";
        String bizTranGrpName = "データ入力取引グループ";
        String bizTranCode = "AN0001";
        String bizTranName = "畜産メインメニュー";
        Boolean isCenterBizTran = false;
        LocalDate expirationStartDate = LocalDate.of(2010,6,21);
        LocalDate expirationEndDate = LocalDate.of(9999,12,31);

        // 実行
        BizTranGrp_BizTranSheet bizTranGrp_BizTranSheet = BizTranGrp_BizTranSheet.createFrom(
            rowno,
            subSystemName,
            bizTranGrpCode,
            bizTranGrpName,
            bizTranCode,
            bizTranName,
            isCenterBizTran,
            expirationStartDate,
            expirationEndDate);

        // 結果検証
        assertTrue(bizTranGrp_BizTranSheet instanceof BizTranGrp_BizTranSheet);
        assertThat(bizTranGrp_BizTranSheet.getRowno()).isEqualTo(rowno);
        assertThat(bizTranGrp_BizTranSheet.getSubSystemName()).isEqualTo(subSystemName);
        assertThat(bizTranGrp_BizTranSheet.getBizTranGrpCode()).isEqualTo(bizTranGrpCode);
        assertThat(bizTranGrp_BizTranSheet.getBizTranGrpName()).isEqualTo(bizTranGrpName);
        assertThat(bizTranGrp_BizTranSheet.getBizTranCode()).isEqualTo(bizTranCode);
        assertThat(bizTranGrp_BizTranSheet.getBizTranName()).isEqualTo(bizTranName);
        assertThat(bizTranGrp_BizTranSheet.getIsCenterBizTran()).isEqualTo(isCenterBizTran);
        assertThat(bizTranGrp_BizTranSheet.getExpirationStartDate()).isEqualTo(expirationStartDate);
        assertThat(bizTranGrp_BizTranSheet.getExpirationEndDate()).isEqualTo(expirationEndDate);
    }
}