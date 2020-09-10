package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarStoreRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.Oa12060StoreDetailsConverter;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.CalendarType;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StoreCalendarTest {

    /**
     * {@link StoreCalendar#execute(CalendarStoreRequest)}のテスト
     *
     * ・ カレンダーリポジトリのupdateを呼び出していること、呼び出しの引数が正常にセットできることも確認する。
     */
    @Test
    @Tag(TestSize.SMALL)
    void 実行検証() {
        StoreCalendar target = new StoreCalendar(new CalendarRepositoryForStore() {
            @Override
            public Calendar update(Calendar calendar) {
                // 更新対象データを確認
                assertThat(calendar).isEqualToComparingFieldByField(Calendar.createFrom(1l, CalendarType.経済システム稼働カレンダー, null, false, true, null, 1));
                return calendar;
            }
        }, new CalendarRepository() {
            @Override
            public Calendar findOneBy(CalendarCriteria calendarCriteria) {
                return Calendar.createFrom(1l, CalendarType.経済システム稼働カレンダー, null, true, false, null, 1);
            }
        });

        // 実行
        int result = target.execute(new CalendarStoreRequest() {
            @Override
            public List<Oa12060StoreDetailsConverter> createCalendarList() {
                List<Oa12060StoreDetailsConverter> list = newArrayList();
                list.add(Oa12060StoreDetailsConverter.with(1l, CalendarType.経済システム稼働カレンダー,true,1));
                return list;
            }
        });

        // 更新結果が1件の場合OK
        assertThat(result).isEqualTo(1);
    }

    /**
     * {@link StoreCalendar#execute(CalendarStoreRequest)}のテスト
     *
     * ・ カレンダーリポジトリのupdateを呼び出していないこと、更新対象が0件であることも確認する。
     */
    @Test
    @Tag(TestSize.SMALL)
    void 更新対象なしの場合() {
        StoreCalendar target = new StoreCalendar(new CalendarRepositoryForStore() {
            @Override
            public Calendar update(Calendar calendar) {
                // こっちが呼び出されたらエラー
                assertThat("updateが呼び出されました").isNull();
                return null;
            }
        }, new CalendarRepository() {
            @Override
            public Calendar findOneBy(CalendarCriteria calendarCriteria) {
                return null;
            }
        });

        // 実行
        int result = target.execute(new CalendarStoreRequest() {
            @Override
            public List<Oa12060StoreDetailsConverter> createCalendarList() {
                return newArrayList();
            }
        });

        // 更新結果が0件の場合OK
        assertThat(result).isEqualTo(0);
    }
}
