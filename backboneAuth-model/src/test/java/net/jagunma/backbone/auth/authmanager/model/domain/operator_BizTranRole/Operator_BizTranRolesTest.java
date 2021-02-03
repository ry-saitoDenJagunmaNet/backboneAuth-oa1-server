package net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Operator_BizTranRolesTest {

    // 実行既定値
    private List<Operator_BizTranRole> operator_BizTranRoleList = createOperator_BizTranRoleList();

    // オペレーター_取引ロール割当リストデータ作成
    private List<Operator_BizTranRole> createOperator_BizTranRoleList() {
        List<Operator_BizTranRole> list = newArrayList();
        list.add(Operator_BizTranRole.createFrom(1L,18L,1L,LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,createOperator(18L),createBizTranRole(1L)));
        list.add(Operator_BizTranRole.createFrom(2L,20L,3L,LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,createOperator(20L),createBizTranRole(3L)));
        list.add(Operator_BizTranRole.createFrom(3L,19L,2L,LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,createOperator(19L),createBizTranRole(2L)));
        return list;
    }
    // オペレーターデータ作成
    private Operator createOperator(long id) {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","ｙｕ００１００９","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(19L,"yu001010","ｙｕ００１０１０","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(20L,"yu001011","ｙｕ００１０１１","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        return list.stream().filter(o->o.getOperatorId().equals(id)).findFirst().orElse(null);
    }
    // 取引ロールデータ作成
    private BizTranRole createBizTranRole(long id) {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(1L,"KBAG01","（購買）購買業務基本","KB",1,SubSystem.購買));
        list.add(BizTranRole.createFrom(2L,"KBAG02","（購買）本所業務","KB",1,SubSystem.購買));
        list.add(BizTranRole.createFrom(3L,"KBAG03","（購買）本所管理業務","KB",1,SubSystem.購買));
        return list.stream().filter(o->o.getBizTranRoleId().equals(id)).findFirst().orElse(null);
    }

    /**
     * {@link Operator_BizTranRoles#createFrom(Collection)}テスト
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
        Operator_BizTranRoles actualOperator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);

        // 結果検証
        assertThat(actualOperator_BizTranRoles.size()).isEqualTo(operator_BizTranRoleList.size());
        for(int i = 0; i < actualOperator_BizTranRoles.getValues().size(); i++) {
            assertThat(actualOperator_BizTranRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(operator_BizTranRoleList.get(i));
        }
    }

    /**
     * {@link Operator_BizTranRoles#select(Predicate)}テスト
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
        Long operatorId = 19L;
        Operator_BizTranRoleCriteria criteria = new  Operator_BizTranRoleCriteria();
        criteria.getOperatorIdCriteria().setEqualTo(operatorId);

        // テスト対象クラス生成
        Operator_BizTranRoles operator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);

        // 期待値
        List<Operator_BizTranRole> expectedOperator_BizTranRoleList = operator_BizTranRoleList.stream()
            .filter(o -> o.getOperatorId().equals(operatorId)).collect(Collectors.toList());

        // 実行
        Operator_BizTranRoles actualOperator_BizTranRoles =  operator_BizTranRoles.select(criteria);

        // 結果検証
        assertThat(actualOperator_BizTranRoles.size()).isEqualTo(expectedOperator_BizTranRoleList.size());
        for(int i = 0; i < actualOperator_BizTranRoles.getValues().size(); i++) {
            assertThat(actualOperator_BizTranRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoleList.get(i));
        }
    }

    /**
     * {@link Operator_BizTranRoles#sortBy(Orders)}テスト
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
        Orders orders = Orders.empty().addOrder("operator.operatorId");

        // テスト対象クラス生成
        Operator_BizTranRoles operator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);

        // 期待値
        List<Operator_BizTranRole> expectedOperator_BizTranRoleList = operator_BizTranRoleList.stream().sorted(orders.toComparator()).collect(Collectors.toList());

        // 実行
        Operator_BizTranRoles actualOperator_BizTranRoles =  operator_BizTranRoles.sortBy(orders);

        // 結果検証
        assertThat(actualOperator_BizTranRoles.size()).isEqualTo(expectedOperator_BizTranRoleList.size());
        for(int i = 0; i < actualOperator_BizTranRoles.getValues().size(); i++) {
            assertThat(actualOperator_BizTranRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoleList.get(i));
        }
    }
}