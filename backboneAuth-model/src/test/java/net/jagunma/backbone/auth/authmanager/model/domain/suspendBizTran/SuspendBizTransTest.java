package net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SuspendBizTransTest {

    /**
     * {@link SuspendBizTrans#createFrom(Collection)}テスト
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
        List<SuspendBizTran> list = newArrayList();
        list.add(SuspendBizTran.createFrom(1L,"006","001",SubSystem.販売_畜産.getCode(),"ANTG01","AN0001",LocalDate.of(2020,4,1),LocalDate.of(2020,4,2),"抑止理由",1,null,null,SubSystem.販売_畜産,null,null));
        list.add(SuspendBizTran.createFrom(2L,"006","001",SubSystem.販売_畜産.getCode(),"ANTG10",null,LocalDate.of(2020,4,1),LocalDate.of(2020,4,2),"抑止理由",1,null,null,SubSystem.販売_畜産,null,null));

        // 実行
        SuspendBizTrans suspendBizTrans = SuspendBizTrans.createFrom(list);

        // 結果検証
        for(int i = 0; i < suspendBizTrans.getValues().size(); i++) {
            assertThat(suspendBizTrans.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }
}