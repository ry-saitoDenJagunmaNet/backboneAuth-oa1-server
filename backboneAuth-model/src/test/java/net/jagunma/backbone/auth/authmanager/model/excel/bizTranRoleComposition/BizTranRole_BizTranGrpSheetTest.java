package net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranRole_BizTranGrpSheetTest {

    /**
     * {@link BizTranRole_BizTranGrpSheet#createFrom(int,String,String,String,String,String)}テスト
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
        String bizTranRoleCode = "ANAG01";
        String bizTranRoleName = "（畜産）取引全般";
        String bizTranGrpCode = "ANTG01";
        String bizTranGrpName = "データ入力取引グループ";

        // 実行
        BizTranRole_BizTranGrpSheet bizTranRole_BizTranGrpSheet = BizTranRole_BizTranGrpSheet.createFrom(
            rowno,
            subSystemName,
            bizTranRoleCode,
            bizTranRoleName,
            bizTranGrpCode,
            bizTranGrpName);

        // 結果検証
        assertTrue(bizTranRole_BizTranGrpSheet instanceof BizTranRole_BizTranGrpSheet);
        assertThat(bizTranRole_BizTranGrpSheet.getRowno()).isEqualTo(rowno);
        assertThat(bizTranRole_BizTranGrpSheet.getSubSystemName()).isEqualTo(subSystemName);
        assertThat(bizTranRole_BizTranGrpSheet.getBizTranRoleCode()).isEqualTo(bizTranRoleCode);
        assertThat(bizTranRole_BizTranGrpSheet.getBizTranRoleName()).isEqualTo(bizTranRoleName);
        assertThat(bizTranRole_BizTranGrpSheet.getBizTranGrpCode()).isEqualTo(bizTranGrpCode);
        assertThat(bizTranRole_BizTranGrpSheet.getBizTranGrpName()).isEqualTo(bizTranGrpName);
    }
}