package net.jagunma.backbone.auth.authmanager.infra.web.oa12060;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060Vo;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.primitives.LocalDates;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12060SearchConverterTest {

    /**
     * {@link Oa12060SearchConverter#with(Oa12060Vo)}テスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Converterへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test1() {

        // 実行値
        LocalDate yearMonth = LocalDates.getFirstDate(LocalDate.now());
        Short calendarTypeFilterCheck1 = null;
        Short calendarTypeFilterCheck2 = null;
        Short calendarTypeFilterCheck3 = null;
        String workingdayOrHolidaySelect = null;
        Oa12060Vo vo = new Oa12060Vo();
        vo.setYearMonth(yearMonth);
        vo.setCalendarTypeFilterCheck1(calendarTypeFilterCheck1);
        vo.setCalendarTypeFilterCheck2(calendarTypeFilterCheck2);
        vo.setCalendarTypeFilterCheck3(calendarTypeFilterCheck3);
        vo.setWorkingdayOrHolidaySelect(workingdayOrHolidaySelect);

        // 実行
        Oa12060SearchConverter converter = Oa12060SearchConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa12060SearchConverter);
        assertThat(converter.getYearMonth()).isEqualTo(yearMonth);
    }
}