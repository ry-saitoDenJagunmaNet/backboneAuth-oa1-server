package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranRole_BizTranGrpsTest {

    // 実行既定値
    private List<BizTranRole_BizTranGrp> bizTranRole_BizTranGrpList = createBizTranRole_BizTranGrpList();

    // 取引ロール_取引グループ割当リストデータ作成
    private List<BizTranRole_BizTranGrp> createBizTranRole_BizTranGrpList() {
        List<BizTranRole_BizTranGrp> list = newArrayList();
        list.add(BizTranRole_BizTranGrp.createFrom(1L,10000001L,1000001L,SubSystem.販売_畜産.getCode(),1,
            BizTranRole.createFrom(10000001L,"ANAG01","（畜産）取引全般",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            BizTranGrp.createFrom(1000001L,"ANTG01","データ入力取引グループ", SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            SubSystem.販売_畜産));
        list.add(BizTranRole_BizTranGrp.createFrom(2L,10000002L,1000002L,SubSystem.販売_畜産.getCode(),1,
            BizTranRole.createFrom(10000002L,"ANAG98","（畜産）センター維持管理担当者",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            BizTranGrp.createFrom(1000002L,"ANTG10","センター維持管理グループ", SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            SubSystem.販売_畜産));
        list.add(BizTranRole_BizTranGrp.createFrom(3L,10000003L,1000003L,SubSystem.販売_畜産.getCode(),1,
            BizTranRole.createFrom(10000003L,"ANAG02","（畜産）維持管理担当者",SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            BizTranGrp.createFrom(1000003L,"ANTG02","精算取引グループ", SubSystem.販売_畜産.getCode(),1,SubSystem.販売_畜産),
            SubSystem.販売_畜産));
        return list;
    }

    /**
     * {@link BizTranRole_BizTranGrps#createFrom(Collection)}テスト
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
        BizTranRole_BizTranGrps actualBizTranRole_BizTranGrps = BizTranRole_BizTranGrps.createFrom(bizTranRole_BizTranGrpList);

        // 結果検証
        assertThat(actualBizTranRole_BizTranGrps.size()).isEqualTo(bizTranRole_BizTranGrpList.size());
        for(int i = 0; i < actualBizTranRole_BizTranGrps.getValues().size(); i++) {
            assertThat(actualBizTranRole_BizTranGrps.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(bizTranRole_BizTranGrpList.get(i));
        }
    }

    /**
     * {@link BizTranRole_BizTranGrps#select(Predicate)}テスト
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
        Long bizTranRoleId = 10000002L;
        BizTranRole_BizTranGrpCriteria criteria = new  BizTranRole_BizTranGrpCriteria();
        criteria.getBizTranRoleIdCriteria().setEqualTo(bizTranRoleId);

        // テスト対象クラス生成
        BizTranRole_BizTranGrps bizTranRole_BizTranGrps = BizTranRole_BizTranGrps.createFrom(bizTranRole_BizTranGrpList);

        // 期待値
        List<BizTranRole_BizTranGrp> expectedBizTranRole_BizTranGrpsList = bizTranRole_BizTranGrpList.stream()
            .filter(b -> b.getBizTranRoleId().equals(bizTranRoleId)).collect(Collectors.toList());

        // 実行
        BizTranRole_BizTranGrps actualBizTranRole_BizTranGrps =  bizTranRole_BizTranGrps.select(criteria);

        // 結果検証
        assertThat(actualBizTranRole_BizTranGrps.size()).isEqualTo(expectedBizTranRole_BizTranGrpsList.size());
        for(int i = 0; i < actualBizTranRole_BizTranGrps.getValues().size(); i++) {
            assertThat(actualBizTranRole_BizTranGrps.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranRole_BizTranGrpsList.get(i));
        }
    }

    /**
     * {@link BizTranRole_BizTranGrps#sortBy(Orders)}テスト
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
        Orders orders = Orders.empty().addOrder("bizTranRole.bizTranRoleCode");

        // テスト対象クラス生成
        BizTranRole_BizTranGrps bizTranRole_BizTranGrps = BizTranRole_BizTranGrps.createFrom(bizTranRole_BizTranGrpList);

        // 期待値
        List<BizTranRole_BizTranGrp> expectedBizTranRole_BizTranGrpsList = bizTranRole_BizTranGrpList.stream().sorted(orders.toComparator()).collect(Collectors.toList());

        // 実行
        BizTranRole_BizTranGrps actualBizTranRole_BizTranGrps =  bizTranRole_BizTranGrps.sortBy(orders);

        // 結果検証
        assertThat(actualBizTranRole_BizTranGrps.size()).isEqualTo(expectedBizTranRole_BizTranGrpsList.size());
        for(int i = 0; i < actualBizTranRole_BizTranGrps.getValues().size(); i++) {
            assertThat(actualBizTranRole_BizTranGrps.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedBizTranRole_BizTranGrpsList.get(i));
        }
    }
}