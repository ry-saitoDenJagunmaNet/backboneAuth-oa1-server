package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OperatorsTest {

    /**
     * {@link Operators#createFrom(Collection)}テスト
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
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L, "yu001009", "ｙｕ００１００９","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能.getCode(),1));
        list.add(Operator.createFrom(19L, "yu001010", "ｙｕ００１０１０","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能.getCode(),1));
        list.add(Operator.createFrom(20L, "yu001011", "ｙｕ００１０１１","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能.getCode(),1));

        // 実行
        Operators operators = Operators.createFrom(list);

        // 結果検証
        for(int i = 0; i < operators.getValues().size(); i++) {
            assertThat(operators.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }
}