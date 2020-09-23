package net.jagunma.backbone.auth.authmanager.model.domain.calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CalendarTest {

    /**
     * {@link Calendar#createFrom(Long, CalendarType, LocalDate, Boolean, Boolean, Boolean, Integer)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom() {

        // 実行値 ＆ 期待値
        Long calendarId = 123L;
        CalendarType calendarType = CalendarType.広域物流カレンダー;
        LocalDate date = LocalDate.of(2020, 9, 1);
        Boolean isHoliday = false;
        Boolean isManualChange = false;
        Boolean isRelease = true;
        Integer recordVersion = 45;

        // 実行
        Calendar calendar = Calendar.createFrom(
            calendarId,
            calendarType,
            date,
            isHoliday,
            isManualChange,
            isRelease,
            recordVersion);

        // 結果検証
        assertTrue(calendar instanceof Calendar);
        assertThat(calendar.getCalendarId()).isEqualTo(calendarId);
        assertThat(calendar.getCalendarType()).isEqualTo(calendarType);
        assertThat(calendar.getDate()).isEqualTo(date);
        assertThat(calendar.getIsHoliday()).isEqualTo(isHoliday);
        assertThat(calendar.getIsManualChange()).isEqualTo(isManualChange);
        assertThat(calendar.getIsRelease()).isEqualTo(isRelease);
        assertThat(calendar.getRecordVersion()).isEqualTo(recordVersion);
    }
}