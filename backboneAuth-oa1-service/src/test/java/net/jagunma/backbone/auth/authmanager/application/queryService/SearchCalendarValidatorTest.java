package net.jagunma.backbone.auth.authmanager.application.queryService;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchRequest;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchCalendarValidatorTest {

    /**
     * {@link SearchCalendarValidator}のテスト
     *
     * ・ リクエストの年月が正常にセットできることを確認する。
     */
    @Test
    @Tag(TestSize.SMALL)
    void エラーなしの場合の確認() {
        CalendarSearchRequest request = new CalendarSearchRequest() {
            @Override
            public LocalDate getYearMonth() {
                return LocalDate.now();
            }
        };

        //　実行
        SearchCalendarValidator.with(request).validate();
    }

    /**
     * {@link SearchCalendarValidator}のテスト
     *
     * ・ リクエストが未設定の場合、例外が発生することを確認する。
     */
    @Test
    @Tag(TestSize.SMALL)
    void Requestが未設定の場合の確認() {
        try {
            //　実行
            SearchCalendarValidator.with(null).validate();
        } catch (GunmaRuntimeException e) {
            // 結果確認
            assertThat(e.getMessageCode()).isEqualTo("EOA13004");
            assertThat(e.getSimpleMessage()).isEqualTo("リクエストが不正です。");
            return;
        }
    }

    /**
     * {@link SearchCalendarValidator}のテスト
     *
     * ・ リクエストの年月が未設定の場合、例外が発生することを確認する。
     */
    @Test
    @Tag(TestSize.SMALL)
    void Requestの年月が未設定の場合の確認() {
        CalendarSearchRequest request = new CalendarSearchRequest() {
            @Override
            public LocalDate getYearMonth() {
                return null;
            }
        };

        try {
            //　実行
            SearchCalendarValidator.with(request).validate();
        } catch (GunmaRuntimeException e) {
            // 結果確認
            assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            assertThat(e.getSimpleMessage()).isEqualTo("年月が入力されていません。年月を入力してください。");
            return;
        }
    }
}