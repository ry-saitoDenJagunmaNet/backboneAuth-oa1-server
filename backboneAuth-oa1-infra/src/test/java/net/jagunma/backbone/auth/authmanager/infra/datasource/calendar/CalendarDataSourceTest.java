package net.jagunma.backbone.auth.authmanager.infra.datasource.calendar;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarType;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntity;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntityCriteria;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CalendarDataSourceTest {

    // 実行既定値
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final LocalDate ymd = LocalDate.now();
    private List<CalendarEntity> createCalendarEntityList() {
        // カレンダーデータを作成
        List<CalendarEntity> list = newArrayList();
        long id = 1;
        for (int i = 1; i <= ymd.withDayOfMonth(1).plusMonths(1).minusDays(1).getDayOfMonth(); i++) {
            list.add(createCalendarEntity(id++, CalendarType.経済システム稼働カレンダー.getCode(), ymd.withDayOfMonth(i), false, false, true));
            list.add(createCalendarEntity(id++, CalendarType.信用カレンダー.getCode(), ymd.withDayOfMonth(i), false, false, true));
            list.add(createCalendarEntity(id++, CalendarType.広域物流カレンダー.getCode(), ymd.withDayOfMonth(i), false, false, true));
        }
        return list;
    }
    private CalendarEntity createCalendarEntity(
        long calendarId,
        short calendarType,
        LocalDate date,
        boolean isHoliday,
        boolean isManualChange,
        boolean isRelease) {

        CalendarEntity entity = new CalendarEntity();
        entity.setCalendarId(calendarId);
        entity.setCalendarType(calendarType);
        entity.setDate(date);
        entity.setIsHoliday(isHoliday);
        entity.setIsManualChange(isManualChange);
        entity.setIsRelease(isRelease);
        entity.setCreatedBy(9999L);
        entity.setCreatedAt(createdAt);
        entity.setCreatedIpAddress("001.001.001.001");
        entity.setUpdatedBy(null);
        entity.setUpdatedAt(null);
        entity.setUpdatedIpAddress(null);
        entity.setRecordVersion(1);
        return entity;
    }
    private CalendarEntityDao createCalendarEntityDao() {
        // CalendarEntityDaoオブジェクトの作成
        return new CalendarEntityDao() {
            @Override
            public List<CalendarEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public CalendarEntity findOneBy(CalendarEntityCriteria criteria) {
                return createCalendarEntityList().stream().filter(criteria).findFirst().orElse(null);
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

    /**
     * {@link CalendarDataSource#findOneBy(CalendarCriteria)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・ 正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void findOneBy_test0() {

        // 事前準備
        Long calendarId = 4L;
        CalendarType calendarType = CalendarType.経済システム稼働カレンダー;
        LocalDate date = ymd.withDayOfMonth(2);
        Boolean isHoliday = false;
        Boolean isManualChange = false;
        Boolean isRelease = true;
        Integer recordVersion = 1;

        // 実行値
        CalendarCriteria calendarCriteria = new CalendarCriteria();
        calendarCriteria.getCalendarIdCriteria().setEqualTo(calendarId);
        CalendarDataSource calendarDataSource = new CalendarDataSource(createCalendarEntityDao());

        // 期待値
        Calendar expectedCalendar = Calendar.createFrom(
            calendarId,
            calendarType,
            date,
            isHoliday,
            isManualChange,
            isRelease,
            recordVersion);

        // 実行
        Calendar calendar = calendarDataSource.findOneBy(calendarCriteria);

        // 結果検証
        assertThat(calendar).isEqualToComparingFieldByField(expectedCalendar);
    }
    /**
     * {@link CalendarDataSource#findOneBy(CalendarCriteria)}のテスト
     *  ●パターン
     *    対象データ無し
     *
     *  ●検証事項
     *  ・ 正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void findOneBy_test1() {

        // 事前準備
        Long calendarId = 99999999L;

        // 実行値
        CalendarCriteria calendarCriteria = new CalendarCriteria();
        calendarCriteria.getCalendarIdCriteria().setEqualTo(calendarId);
        CalendarDataSource calendarDataSource = new CalendarDataSource(createCalendarEntityDao());

        // 期待値
        Calendar expectedCalendar = null;

        // 実行
        Calendar calendar = calendarDataSource.findOneBy(calendarCriteria);

        // 結果検証
        assertThat(calendar).isEqualTo(expectedCalendar);
    }
}