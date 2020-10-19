package net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PasswordHistoriesTest {
    // オペレーター
    private Operator createOperator(long id) {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","ｙｕ００１００９","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能.getCode(),1,null));
        list.add(Operator.createFrom(19L,"yu001010","ｙｕ００１０１０","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能.getCode(),1,null));
        list.add(Operator.createFrom(20L,"yu001011","ｙｕ００１０１１","001.001.001.001",LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),false,6L,"006",33L,"001",AvailableStatus.利用可能.getCode(),1,null));
        return list.stream().filter(o->o.getOperatorId().equals(id)).findFirst().orElse(null);
    }

    /**
     * {@link AccountLocks#createFrom(Collection)}テスト
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
        List<PasswordHistory> list = newArrayList();
        list.add(PasswordHistory.createFrom(1L,18L,LocalDateTime.of(2020,10,1,8,30,12),"password",PasswordChangeType.初期.getCode(),1,createOperator(18L)));
        list.add(PasswordHistory.createFrom(2L,19L,LocalDateTime.of(2020,10,1,8,30,12),"password",PasswordChangeType.初期.getCode(),1,createOperator(19L)));
        list.add(PasswordHistory.createFrom(3L,20L,LocalDateTime.of(2020,10,1,8,30,12),"password",PasswordChangeType.初期.getCode(),1,createOperator(20L)));

        // 実行
        PasswordHistories passwordHistories = PasswordHistories.createFrom(list);

        // 結果検証
        for(int i = 0; i < passwordHistories.getValues().size(); i++) {
            assertThat(passwordHistories.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }
}