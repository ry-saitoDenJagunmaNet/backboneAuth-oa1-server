package net.jagunma.backbone.auth.authmanager.infra.datasource.calendar;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarType;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntity;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntityCriteria;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CalendarForStoreDataSourceTest {

    // 実行既定値
    private final Long calendarId = 5L;
    private final CalendarType calendarType = CalendarType.信用カレンダー;
    private final LocalDate ymd = LocalDate.now().withDayOfMonth(2);
    private final Boolean isHoliday = false;
    private final Boolean isManualChange = false;
    private final Boolean isRelease = true;
    private final Integer recordVersion = 1;
    private CalendarEntityDao createCalendarEntityDao() {
        // CalendarEntityDaoオブジェクトの作成
        return new CalendarEntityDao() {
            @Override
            public List<CalendarEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public CalendarEntity findOneBy(CalendarEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<CalendarEntity> findBy(CalendarEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(CalendarEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(CalendarEntity entity) {
                return 0;
            }
            @Override
            public int update(CalendarEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(CalendarEntity entity) {
                // 結果検証
                assertThat(entity).isEqualToComparingFieldByField(createExpectedEntity());
                return 0;
            }
            @Override
            public int delete(CalendarEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(CalendarEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<CalendarEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<CalendarEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<CalendarEntity> entities) {
                return new int[0];
            }
        };
    }

    // 期待値
    private CalendarEntity createExpectedEntity() {
        CalendarEntity entity = new CalendarEntity();
        entity.setCalendarId(calendarId);
        entity.setCalendarType(calendarType.getCode());
        entity.setDate(ymd);
        entity.setIsHoliday(isHoliday);
        entity.setIsManualChange(isManualChange);
        entity.setIsRelease(isRelease);
        entity.setCreatedBy(null);
        entity.setCreatedAt(null);
        entity.setCreatedIpAddress(null);
        entity.setUpdatedBy(null);
        entity.setUpdatedAt(null);
        entity.setUpdatedIpAddress(null);
        entity.setRecordVersion(recordVersion);
        return entity;
    }

    /**
     * {@link CalendarForStoreDataSource#update(Calendar)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・ 正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test0() {

        // 実行値
        Calendar calendar = Calendar.createFrom(
            calendarId,
            calendarType,
            ymd,
            isHoliday,
            isManualChange,
            isRelease,
            recordVersion);
        CalendarForStoreDataSource calendarForStoreDataSource = new CalendarForStoreDataSource(createCalendarEntityDao());

        // 実行
        Calendar resultCalendar = calendarForStoreDataSource.update(calendar);

        // 結果検証
        assertThat(resultCalendar).isEqualToComparingFieldByField(calendar);
    }

    /**
     * {@link CalendarForStoreDataSource#update(Calendar)}のテスト
     *  ●パターン
     *    対象データ無し（null）
     *
     *  ●検証事項
     *  ・ 正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test1() {

        // 実行値
        Calendar calendar = null;
        CalendarForStoreDataSource calendarForStoreDataSource = new CalendarForStoreDataSource(createCalendarEntityDao());

        // 実行
        Calendar resultCalendar = calendarForStoreDataSource.update(calendar);

        // 結果検証
        assertThat(resultCalendar).isNull();
    }
}