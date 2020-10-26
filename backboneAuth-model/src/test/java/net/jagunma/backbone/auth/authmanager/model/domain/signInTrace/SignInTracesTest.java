package net.jagunma.backbone.auth.authmanager.model.domain.signInTrace;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SignInTracesTest {

    // オペレーター
    private Operator createOperator(String code) {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","ｙｕ００１００９","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(19L,"yu001010","ｙｕ００１０１０","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        list.add(Operator.createFrom(20L,"yu001011","ｙｕ００１０１１","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,null));
        return list.stream().filter(o->o.getOperatorCode().equals(code)).findFirst().orElse(null);
    }

    /**
     * {@link SignInTraces#createFrom(Collection)}テスト
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
        List<SignInTrace> list = newArrayList();
        list.add(SignInTrace.createFrom(1L, LocalDateTime.of(2020,10,1,8,30,12), "001.001.001.001", "yu001009", SignInCause.サインイン.getCode(), SignInResult.成功.getCode(), 1, createOperator("yu001009")));
        list.add(SignInTrace.createFrom(2L, LocalDateTime.of(2020,10,1,8,31,23), "001.001.001.001", "yu001010", SignInCause.サインイン.getCode(), SignInResult.成功.getCode(), 1, createOperator("yu001010")));
        list.add(SignInTrace.createFrom(3L, LocalDateTime.of(2020,10,1,8,32,34), "001.001.001.001", "yu001011", SignInCause.サインイン.getCode(), SignInResult.成功.getCode(), 1, createOperator("yu001011")));

        // 実行
        SignInTraces signInTraces = SignInTraces.createFrom(list);

        // 結果検証
        for(int i = 0; i < signInTraces.getValues().size(); i++) {
            assertThat(signInTraces.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }
}