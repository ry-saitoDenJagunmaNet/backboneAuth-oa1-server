package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranGrp_BizTransTest {

    // 実行既定値
    private List<BizTranGrp_BizTran> bizTranGrp_BizTranList = createBizTranGrp_BizTranList();

    // 取引グループ_取引割当リストデータ作成
    private List<BizTranGrp_BizTran> createBizTranGrp_BizTranList() {
        List<BizTranGrp_BizTran> list = newArrayList();
        list.add(BizTranGrp_BizTran.createFrom(1L,10001L,100001L,SubSystem.販売_畜産.getCode(),1,
            BizTranGrp.createFrom(10001L,"ANTG01","データ入力取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            BizTran.createFrom(100001L,"AN0001","畜産メインメニュー",false,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            SubSystem.販売_畜産));
        list.add(BizTranGrp_BizTran.createFrom(2L,10002L,100002L,SubSystem.販売_畜産.getCode(),1,
            BizTranGrp.createFrom(10002L,"ANTG10","センター維持管理グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            BizTran.createFrom(100002L,"AN0002","畜産業務（センター）メニュー",true,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            SubSystem.販売_畜産));
        list.add(BizTranGrp_BizTran.createFrom(3L,10003L,100003L,SubSystem.販売_畜産.getCode(),1,
            BizTranGrp.createFrom(10003L,"ANTG02","精算取引グループ",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            BizTran.createFrom(100003L,"AN1110","前日処理照会",false,LocalDate.of(2010,1,1),LocalDate.of(9999,12,31),SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            SubSystem.販売_畜産));
        return list;
    }

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

        // 実行
        BizTranGrp_BizTrans actualBizTranGrp_BizTrans = BizTranGrp_BizTrans.createFrom(bizTranGrp_BizTranList);

        // 結果検証
        assertThat(actualBizTranGrp_BizTrans.size()).isEqualTo(bizTranGrp_BizTranList.size());
        for(int i = 0; i < actualBizTranGrp_BizTrans.getValues().size(); i++) {
            assertThat(actualBizTranGrp_BizTrans.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(bizTranGrp_BizTranList.get(i));
        }
    }

    /**
     * {@link BizTranGrp_BizTrans#select(Predicate)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void select_test0() {

        // 実行値
        Long bizTranGrpId = 10002L;
        BizTranGrp_BizTranCriteria criteria = new  BizTranGrp_BizTranCriteria();
        criteria.getBizTranGrpIdCriteria().setEqualTo(bizTranGrpId);

        // テスト対象クラス生成
        BizTranGrp_BizTrans bizTranGrp_BizTrans = BizTranGrp_BizTrans.createFrom(bizTranGrp_BizTranList);

        // 期待値
        List<BizTranGrp_BizTran> expectedBizTranGrp_BizTranList = bizTranGrp_BizTranList.stream()
            .filter(b -> b.getBizTranGrpId().equals(bizTranGrpId)).collect(Collectors.toList());

        // 実行
        BizTranGrp_BizTrans actualBizTranGrp_BizTrans =  bizTranGrp_BizTrans.select(criteria);

        // 結果検証
        assertThat(actualBizTranGrp_BizTrans.size()).isEqualTo(expectedBizTranGrp_BizTranList.size());
        for(int i = 0; i < actualBizTranGrp_BizTrans.getValues().size(); i++) {
            assertThat(actualBizTranGrp_BizTrans.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranGrp_BizTranList.get(i));
        }
    }

    /**
     * {@link BizTranGrp_BizTrans#sortBy(Orders)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void sortBy_test0() {

        // 実行値
        Orders orders = Orders.empty().addOrder("bizTranGrp.bizTranGrpCode");

        // テスト対象クラス生成
        BizTranGrp_BizTrans bizTranGrp_BizTrans = BizTranGrp_BizTrans.createFrom(bizTranGrp_BizTranList);

        // 期待値
       List<BizTranGrp_BizTran> expectedBizTranGrp_BizTranList = bizTranGrp_BizTranList.stream().sorted(orders.toComparator()).collect(Collectors.toList());

        // 実行
        BizTranGrp_BizTrans actualBizTranGrp_BizTrans =  bizTranGrp_BizTrans.sortBy(orders);

        // 結果検証
        assertThat(actualBizTranGrp_BizTrans.size()).isEqualTo(expectedBizTranGrp_BizTranList.size());
        for(int i = 0; i < actualBizTranGrp_BizTrans.getValues().size(); i++) {
            assertThat(actualBizTranGrp_BizTrans.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranGrp_BizTranList.get(i));
        }
    }

}