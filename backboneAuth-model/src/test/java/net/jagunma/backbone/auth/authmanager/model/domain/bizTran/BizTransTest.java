package net.jagunma.backbone.auth.authmanager.model.domain.bizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTransTest {

    /**
     * {@link BizTrans#createFrom(Collection)}テスト
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
        List<BizTran> list = newArrayList();
        list.add(BizTran.createFrom(1L,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31), SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTran.createFrom(2L,"AN1110","前日処理照会",false,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31), SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));
        list.add(BizTran.createFrom(3L,"AN0002","畜産業務（センター）メニュー",true,LocalDate.of(2020,10,1),LocalDate.of(9999,12,31), SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産));

        // 実行
        BizTrans bizTrans = BizTrans.createFrom(list);

        // 結果検証
        for(int i = 0; i < bizTrans.getValues().size(); i++) {
            assertThat(bizTrans.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }
}