package net.jagunma.backbone.auth.authmanager.application.queryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchRequest;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchCalendarValidatorTest {

    /**
     * {@link SearchCalendarValidator#validate()}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test0() {

        // 実行値
        LocalDate yearMonth = LocalDate.now();
        CalendarSearchRequest request = new CalendarSearchRequest() {
            @Override
            public LocalDate getYearMonth() {
                return yearMonth;
            }
        };

        assertThatCode(()->
            // 実行
            SearchCalendarValidator.with(request).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link SearchCalendarValidator#validate()}のテスト
     *  ●パターン
     *    リクエストの年月がnullのテスト
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test1() {

        // 実行値
        LocalDate yearMonth = null;
        CalendarSearchRequest request = new CalendarSearchRequest() {
            @Override
            public LocalDate getYearMonth() {
                return yearMonth;
            }
        };

        assertThatThrownBy(()->
            // 実行
            SearchCalendarValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("年月");
            });
    }

    /**
     * {@link SearchCalendarValidator#validate()}のテスト
     *  ●パターン
     *    リクエストがnullのテスト
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test2() {

        // 実行値
        CalendarSearchRequest request = null;

        assertThatThrownBy(()->
            // 実行
            SearchCalendarValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }
}