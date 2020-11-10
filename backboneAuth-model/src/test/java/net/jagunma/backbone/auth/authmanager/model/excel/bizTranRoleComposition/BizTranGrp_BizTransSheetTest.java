package net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranGrp_BizTransSheetTest {

    /**
     * {@link BizTranGrp_BizTransSheet#createFrom(Collection)}テスト
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
        List<BizTranGrp_BizTranSheet> list = newArrayList();
        list.add(BizTranGrp_BizTranSheet.createFrom(1,"販売・畜産","ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        list.add(BizTranGrp_BizTranSheet.createFrom(2,"販売・畜産","ANTG01","データ入力取引グループ","AN1110","前日処理照会",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG10","センター維持管理グループ","AN0002","畜産業務（センター）メニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));

        // 実行
        BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet = BizTranGrp_BizTransSheet.createFrom(list);

        // 結果検証
        for(int i = 0; i < bizTranGrp_BizTransSheet.getValues().size(); i++) {
            assertThat(bizTranGrp_BizTransSheet.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }
}