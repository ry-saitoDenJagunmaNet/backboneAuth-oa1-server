package net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranRole_BizTranGrpsSheetTest {

    /**
     * {@link BizTranRole_BizTranGrpsSheet#createFrom(Collection)}テスト
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
        List<BizTranRole_BizTranGrpSheet> list = newArrayList();
        list.add(BizTranRole_BizTranGrpSheet.createFrom(1,"販売・畜産","ANAG01","（畜産）取引全般","ANTG01","データ入力取引グループ"));
        list.add(BizTranRole_BizTranGrpSheet.createFrom(2,"販売・畜産","ANAG01","（畜産）取引全般","ANTG02","精算取引グループ"));
        list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産","ANAG98","（畜産）センター維持管理担当者","ANTG10","センター維持管理グループ"));

        // 実行
        BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet = BizTranRole_BizTranGrpsSheet.createFrom(list);

        // 結果検証
        for(int i = 0; i < bizTranRole_BizTranGrpsSheet.getValues().size(); i++) {
            assertThat(bizTranRole_BizTranGrpsSheet.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }
}