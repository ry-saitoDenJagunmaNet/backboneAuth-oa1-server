package net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Operator_BizTranRolesTest {

    // オペレーター
    private Operator createOperator(long id) {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","ｙｕ００１００９","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能.getCode(),1,null));
        list.add(Operator.createFrom(19L,"yu001010","ｙｕ００１０１０","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能.getCode(),1,null));
        list.add(Operator.createFrom(20L,"yu001011","ｙｕ００１０１１","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能.getCode(),1,null));
        return list.stream().filter(o->o.getOperatorId().equals(id)).findFirst().orElse(null);
    }

    // 取引ロール
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

        // 実行値 ＆ 期待値
        List<Operator_BizTranRole> list = newArrayList();
        list.add(Operator_BizTranRole.createFrom(1L,18L,1L,LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,createOperator(18L),createBizTranRole(1L)));
        list.add(Operator_BizTranRole.createFrom(2L,19L,2L,LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,createOperator(19L),createBizTranRole(2L)));
        list.add(Operator_BizTranRole.createFrom(3L,20L,3L,LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,createOperator(20L),createBizTranRole(3L)));

        // 実行
        Operator_BizTranRoles operator_BizTranRoles = Operator_BizTranRoles.createFrom(list);

        // 結果検証
        for(int i = 0; i < operator_BizTranRoles.getValues().size(); i++) {
            assertThat(operator_BizTranRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }
}