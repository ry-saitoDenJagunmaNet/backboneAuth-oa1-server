package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranGrp_BizTransTest {

    /**
     * {@link BizTranGrp_BizTrans#createFrom(Collection)}テスト
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
        List<BizTranGrp_BizTran> list = newArrayList();
        list.add(BizTranGrp_BizTran.createFrom(1L,10001L,100001L,SubSystem.販売_畜産.getCode(),1,
            BizTranGrp.createFrom(10001L,"ANTG01","データ入力取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            BizTran.createFrom(100001L,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            SubSystem.販売_畜産));
        list.add(BizTranGrp_BizTran.createFrom(2L,10002L,100002L,SubSystem.販売_畜産.getCode(),1,
            BizTranGrp.createFrom(10002L,"ANTG02","精算取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            BizTran.createFrom(100002L,"AN1110","前日処理照会",false,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            SubSystem.販売_畜産));
        list.add(BizTranGrp_BizTran.createFrom(3L,10003L,100003L,SubSystem.販売_畜産.getCode(),1,
            BizTranGrp.createFrom(10003L,"ANTG10","センター維持管理グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            BizTran.createFrom(100003L,"AN0002","畜産業務（センター）メニュー",true,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            SubSystem.販売_畜産));

        // 実行
        BizTranGrp_BizTrans bizTranGrp_BizTrans = BizTranGrp_BizTrans.createFrom(list);

        // 結果検証
        for(int i = 0; i < bizTranGrp_BizTrans.getValues().size(); i++) {
            assertThat(bizTranGrp_BizTrans.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }
}