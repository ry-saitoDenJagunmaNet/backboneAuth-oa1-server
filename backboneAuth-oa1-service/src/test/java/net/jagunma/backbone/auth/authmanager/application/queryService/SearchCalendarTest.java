package net.jagunma.backbone.auth.authmanager.application.queryService;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarsRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchCalendarTest {

    /**
     * {@link SearchCalendar#execute(CalendarSearchRequest, CalendarSearchResponse)}のテスト
     *
     * ・ カレンダーリポジトリのselectByを呼び出していること、呼び出しの引数が正常にセットできることも確認する。
     */
    @Test
    @Tag(TestSize.SMALL)
    void 実行検証() {
        SearchCalendar target = new SearchCalendar(new CalendarsRepository() {
            @Override
            public Calendars selectBy(CalendarCriteria calendarCriteria, Orders orders) {
                return null;
            }

            @Override
            public Calendars selectBy(CalendarCriteria calendarCriteria) {
                // 引数の設定ができているか確認
                assertThat(calendarCriteria.getDateCriteria().getFrom()).isEqualTo(LocalDate.now().withDayOfMonth(1));
                assertThat(calendarCriteria.getDateCriteria().getTo()).isEqualTo(LocalDate.now().withDayOfMonth(1).plusMonths(1).minusDays(1));
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
        target.execute(new CalendarSearchRequest() {
            @Override
            public LocalDate getYearMonth() {
                return LocalDate.now();
            }
        }, new CalendarSearchResponse() {
            @Override
            public void setYearMonth(LocalDate yearMonth) {
            }

            @Override
            public void setCalendars(Calendars calendars) {
            }
        });

        // 結果確認なし　処理が通ればOK
    }


    /**
     * {@link SearchCalendar#execute(CalendarSearchRequest, CalendarSearchResponse)}のテスト
     *
     * ・ カレンダーリポジトリのselectByを呼び出していること、引数の年月が未入力で例外が発生するを確認する。
     */
    @Test
    @Tag(TestSize.SMALL)
    void 引数の年月が未入力エラーの場合() {
        SearchCalendar target = new SearchCalendar(new CalendarsRepository() {
            @Override
            public Calendars selectBy(CalendarCriteria calendarCriteria, Orders orders) {
                return null;
            }

            @Override
            public Calendars selectBy(CalendarCriteria calendarCriteria) {
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
        try {
            target.execute(new CalendarSearchRequest() {
                @Override
                public LocalDate getYearMonth() {
                    return null;
                }
            }, new CalendarSearchResponse() {
                @Override
                public void setYearMonth(LocalDate yearMonth) {
                }

                @Override
                public void setCalendars(Calendars calendars) {
                }
            });

        } catch (GunmaRuntimeException e) {
            // 結果確認
            assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            assertThat(e.getSimpleMessage()).isEqualTo("年月が入力されていません。年月を入力してください。");
            return;
        }

        // ここまで来たらエラーとする
        assertThat("エラー未発生").isNull();
    }
}