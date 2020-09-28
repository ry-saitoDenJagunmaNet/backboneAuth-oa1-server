package net.jagunma.backbone.auth.authmanager.model.domain.calendar;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CalendarsTest {

    /**
     * {@link Calendars#createFrom(Collection)}テスト
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
        List<Calendar> list = newArrayList();
        list.add(Calendar.createFrom(1L, CalendarType.経済システム稼働カレンダー, LocalDate.of(2020, 9, 1), false, true, true, 1));
        list.add(Calendar.createFrom(2L, CalendarType.信用カレンダー, LocalDate.of(2020, 9, 1), false, true, true, 1));
        list.add(Calendar.createFrom(3L, CalendarType.広域物流カレンダー, LocalDate.of(2020, 9, 1), true, false, true, 1));

        // 実行
        Calendars calendars = Calendars.createFrom(list);

        // 結果検証
        for(int i = 0; i < calendars.getValues().size(); i++) {
            assertThat(calendars.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }
}