package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarsRepository;
import net.jagunma.backbone.auth.authmanager.model.types.CalendarType;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchCalendarTest {

    // 実行既定値
    private final LocalDate yearMonth = LocalDate.now();
    private final LocalDate ym = yearMonth.withDayOfMonth(1).plusMonths(1).minusDays(1);
    private Calendars createCalendars() {
        // 対象月のカレンダーデータを作成
        List<Calendar> calendaerList = newArrayList();
        long id = 0;
        for (int i = 1; i <= ym.getDayOfMonth(); i++) {
            calendaerList.add(Calendar.createFrom(id++, CalendarType.経済システム稼働カレンダー, ym.withDayOfMonth(i), false, true, true, 1));
            calendaerList.add(Calendar.createFrom(id++, CalendarType.信用カレンダー, ym.withDayOfMonth(i), false, true, true, 1));
            calendaerList.add(Calendar.createFrom(id++, CalendarType.広域物流カレンダー, ym.withDayOfMonth(i), false, true, true, 1));
        }
        return Calendars.createFrom(calendaerList);
    }
    private CalendarSearchRequest createCalendarSearchRequest(LocalDate yearMonth) {
        // CalendarSearchRequestオブジェクトの作成
        CalendarSearchRequest request = new CalendarSearchRequest() {
            @Override
            public LocalDate getYearMonth() {
                return yearMonth;
            }
        };
        return request;
    }

    /**
      * {@link SearchCalendar#execute(CalendarSearchRequest, CalendarSearchResponse)}のテスト
      *  ●パターン
      *    正常
      *
      *  ●検証事項
      *  ・ CalendarsRepository.selectByの引数（検索条件：日付範囲）がセットされていること
      */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test0() {

        // 期待値
        CalendarCriteria expectedCriteria = new CalendarCriteria();
        expectedCriteria.getDateCriteria().setFrom(yearMonth.withDayOfMonth(1));
        expectedCriteria.getDateCriteria().setTo(yearMonth.withDayOfMonth(1).plusMonths(1).minusDays(1));

        // 実行スタブ
        SearchCalendar target = new SearchCalendar(new CalendarsRepository() {
            @Override
            public Calendars selectBy(CalendarCriteria calendarCriteria, Orders orders) {
                return null;
            }
            @Override
            public Calendars selectBy(CalendarCriteria calendarCriteria) {
                // 結果検証
//                assertThat(calendarCriteria).isEqualToComparingFieldByField(expectedCriteria);
                assertThat(calendarCriteria.getDateCriteria().getFrom()).isEqualTo(yearMonth.withDayOfMonth(1));
                assertThat(calendarCriteria.getDateCriteria().getTo()).isEqualTo(yearMonth.withDayOfMonth(1).plusMonths(1).minusDays(1));
                return null;
            }
            @Override
            public Calendars selectAll(Orders orders) {
                return null;
            }
            @Override
            public Calendars selectAll() {
                return null;
            }
        });

        // 実行
        target.execute(createCalendarSearchRequest(yearMonth)
            , new CalendarSearchResponse() {
                @Override
                public void setYearMonth(LocalDate thisYearMonth) {}
                @Override
                public void setCalendars(Calendars thisCalendars) {}
            }
        );
    }

    /**
     * {@link SearchCalendar#execute(CalendarSearchRequest, CalendarSearchResponse)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・ 正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test1() {

        // 期待値
        Calendars expectedCalendars = createCalendars();

        // 実行スタブ
        SearchCalendar target = new SearchCalendar(new CalendarsRepository() {
            @Override
            public Calendars selectBy(CalendarCriteria calendarCriteria, Orders orders) {
                return null;
            }
            @Override
            public Calendars selectBy(CalendarCriteria calendarCriteria) {
                return createCalendars();
            }
            @Override
            public Calendars selectAll(Orders orders) {
                return null;
            }
            @Override
            public Calendars selectAll() {
                return null;
            }
        });

        // 実行
        target.execute(createCalendarSearchRequest(yearMonth)
            , new CalendarSearchResponse() {
                @Override
                public void setYearMonth(LocalDate thisYearMonth) {
                    // 結果検証
                    assertThat(thisYearMonth).isEqualTo(yearMonth);
                }
                @Override
                public void setCalendars(Calendars thisCalendars) {
                    // 結果検証
                    //assertThat(thisCalendars).isEqualToComparingFieldByField(expectedCalendars);
                    for(int i = 0; i < thisCalendars.getValues().size(); i++) {
                        assertThat(thisCalendars.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .isEqualToComparingFieldByField(expectedCalendars.getValues().get(i));
                    }
                }
            }
        );
    }

    /**
     * {@link SearchCalendar#execute(CalendarSearchRequest, CalendarSearchResponse)}のテスト
     *  ●パターン
     *    カレンダーの検索結果が0件のテスト
     *
     *  ●検証事項
     *  ・ 正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test2() {

        // 期待値
        Calendars expectedCalendars = Calendars.createFrom(newArrayList());

        // 実行スタブ
        SearchCalendar target = new SearchCalendar(new CalendarsRepository() {
            @Override
            public Calendars selectBy(CalendarCriteria calendarCriteria, Orders orders) {
                return null;
            }
            @Override
            public Calendars selectBy(CalendarCriteria calendarCriteria) {
                return Calendars.createFrom(newArrayList());
            }
            @Override
            public Calendars selectAll(Orders orders) {
                return null;
            }
            @Override
            public Calendars selectAll() {
                return null;
            }
        });

        // 実行
        target.execute(createCalendarSearchRequest(yearMonth)
            , new CalendarSearchResponse() {
                @Override
                public void setYearMonth(LocalDate thisYearMonth) {
                    // 結果検証
                    assertThat(thisYearMonth).isEqualTo(yearMonth);
                }
                @Override
                public void setCalendars(Calendars thisCalendars) {
                    // 結果検証
                    assertThat(thisCalendars).isEqualToComparingFieldByField(expectedCalendars);
                }
            }
        );
    }
}