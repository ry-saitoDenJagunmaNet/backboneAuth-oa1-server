package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarStoreRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.Oa12060StoreDetailsConverter;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarType;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StoreCalendarTest {

    // 実行既定値
    private CalendarRepository createCalendarRepository() {
        // 変更前想定ののカレンダー（1休日、2休日、3営業日）
        List<Calendar> list = newArrayList();
        list.add(Calendar.createFrom(1L, CalendarType.経済システム稼働カレンダー, null, true, false, null, 1));
        list.add(Calendar.createFrom(2L, CalendarType.信用カレンダー, null, true, false, null, 1));
        list.add(Calendar.createFrom(3L, CalendarType.広域物流カレンダー, null, false, false, null, 1));

        return new CalendarRepository() {
            @Override
            public Calendar findOneById(Long calendarId) {
                return list.stream().filter(c->c.getCalendarId().equals(calendarId)).findFirst().orElse(null);
            }
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
        };
    }

    /**
     * {@link StoreCalendar#execute(CalendarStoreRequest)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test0() {

        // 実行値
        // 1休日→営業日、2休日→休日（変更なし）、3営業日→休日でupdate
        List<Oa12060StoreDetailsConverter> list = newArrayList();
        list.add(Oa12060StoreDetailsConverter.with(1L, CalendarType.経済システム稼働カレンダー,true,1));
        list.add(Oa12060StoreDetailsConverter.with(2L, CalendarType.信用カレンダー,false,1));
        list.add(Oa12060StoreDetailsConverter.with(3L, CalendarType.広域物流カレンダー,false,1));

        // 期待値
        List<Calendar> expectedList = newArrayList();
        // 1営業日、2休日、3休日（2は変更なし）でupdate
        expectedList.add(Calendar.createFrom(1L, CalendarType.経済システム稼働カレンダー, null, false, true, null, 1));
        expectedList.add(Calendar.createFrom(2L, CalendarType.信用カレンダー, null, true, true, null, 1));
        expectedList.add(Calendar.createFrom(3L, CalendarType.広域物流カレンダー, null, true, true, null, 1));

        // テスト対象クラス生成
        StoreCalendar storeCalendar = new StoreCalendar(new CalendarRepositoryForStore() {
            @Override
            public Calendar update(Calendar calendar) {
                // 結果検証
                assertThat(calendar).usingRecursiveComparison()
                    .isEqualTo(expectedList.stream().filter(c->c.getCalendarId() == calendar.getCalendarId()).findFirst().orElse(null));
                return calendar;
            }
        }, createCalendarRepository());

        // 実行
        int result = storeCalendar.execute(new CalendarStoreRequest() {
            @Override
            public List<Oa12060StoreDetailsConverter> createCalendarList() {
                return list;
            }
        });

        // 結果検証
        assertThat(result).isEqualTo(2);
    }

    /**
     * {@link StoreCalendar#execute(CalendarStoreRequest)}のテスト
     *  ●パターン
     *    更新対象なし（変更なし）のテスト
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test1() {

        // 実行値
        // 1休日、2休日、3営業日（全て変更なし）でupdate
        List<Oa12060StoreDetailsConverter> list = newArrayList();
        list.add(Oa12060StoreDetailsConverter.with(1L, CalendarType.経済システム稼働カレンダー,false,1));
        list.add(Oa12060StoreDetailsConverter.with(2L, CalendarType.信用カレンダー,false,1));
        list.add(Oa12060StoreDetailsConverter.with(3L, CalendarType.広域物流カレンダー,true,1));

        // テスト対象クラス生成
        StoreCalendar storeCalendar = new StoreCalendar(new CalendarRepositoryForStore() {
            @Override
            public Calendar update(Calendar calendar) {
                // 結果検証
                assertThat("updateが呼び出されました").isNull();
                return null;
            }
        }, createCalendarRepository());

        // 実行
        int result = storeCalendar.execute(new CalendarStoreRequest() {
            @Override
            public List<Oa12060StoreDetailsConverter> createCalendarList() {
                return list;
            }
        });

        // 結果検証
        assertThat(result).isEqualTo(0);
    }

    /**
     * {@link StoreCalendar#execute(CalendarStoreRequest)}のテスト
     *  ●パターン
     *    更新対象なし（0件）のテスト
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test2() {

        // 実行値
        List<Oa12060StoreDetailsConverter> list = newArrayList();

        // テスト対象クラス生成
        StoreCalendar storeCalendar = new StoreCalendar(new CalendarRepositoryForStore() {
            @Override
            public Calendar update(Calendar calendar) {
                // 結果検証
                assertThat("updateが呼び出されました").isNull();
                return null;
            }
        }, createCalendarRepository());

        // 実行
        int result = storeCalendar.execute(new CalendarStoreRequest() {
            @Override
            public List<Oa12060StoreDetailsConverter> createCalendarList() {
                return list;
            }
        });

        // 結果検証
        assertThat(result).isEqualTo(0);
    }
}
