package net.jagunma.backbone.auth.authmanager.application.queryService;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchRequest;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SeatchOperatorValidatorTest {

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
}