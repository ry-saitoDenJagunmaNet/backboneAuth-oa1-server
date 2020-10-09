package net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Operator_SubSystemRolesTest {

    // オペレーター
    private Operator createOperator(long id) {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","ｙｕ００１００９","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能.getCode(),1));
        list.add(Operator.createFrom(19L,"yu001010","ｙｕ００１０１０","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能.getCode(),1));
        list.add(Operator.createFrom(20L,"yu001011","ｙｕ００１０１１","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能.getCode(),1));
        return list.stream().filter(o->o.getOperatorId().equals(id)).findFirst().orElse(null);
    }

    /**
     * {@link Operator_SubSystemRoles#createFrom(Collection)}テスト
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
        List<Operator_SubSystemRole> list = newArrayList();
        list.add(Operator_SubSystemRole.createFrom(1L,18L,"JaAdmin",LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,createOperator(18L),SubSystemRole.業務統括者_購買));
        list.add(Operator_SubSystemRole.createFrom(2L,19L,"JaAdmin",LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,createOperator(19L),SubSystemRole.業務統括者_購買));
        list.add(Operator_SubSystemRole.createFrom(3L,20L,"JaAdmin",LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,createOperator(20L),SubSystemRole.業務統括者_購買));

        // 実行
        Operator_SubSystemRoles operator_SubSystemRoles = Operator_SubSystemRoles.createFrom(list);

        // 結果検証
        for(int i = 0; i < operator_SubSystemRoles.getValues().size(); i++) {
            assertThat(operator_SubSystemRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }

}