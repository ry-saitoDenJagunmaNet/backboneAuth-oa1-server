package net.jagunma.backbone.auth.authmanager.infra.datasource.calendar;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.model.types.CalendarType;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntity;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntityCriteria;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CalendarsDataSourceTest {

    // 実行既定値
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final LocalDate ymd = LocalDate.now();
    private List<CalendarEntity> createCalendarEntityList() {
        // カレンダーデータを作成
        List<CalendarEntity> list = newArrayList();
        long id = 1;
        // ３か月分のデータ作成
        LocalDate startDay = ymd.withDayOfMonth(1).minusMonths(1);
        LocalDate lastDay = ymd.withDayOfMonth(1).plusMonths(2).minusDays(1);
        for (LocalDate date = startDay; date.isBefore(lastDay.plusDays(1)); date = date.plusDays(1)) {
            list.add(createCalendarEntity(id++, CalendarType.信用カレンダー.getCode(), date, false, false, true));
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
                return createCalendarEntityList();
            }
            @Override
            public CalendarEntity findOneBy(CalendarEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<CalendarEntity> findBy(CalendarEntityCriteria criteria, Orders orders) {
                return createCalendarEntityList().stream().filter(criteria).collect(Collectors.toList());
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
     * {@link CalendarsDataSource#selectBy(CalendarCriteria)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・ 正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void selectBy_test0() {

        // 事前準備
        LocalDate criteriaDate = ymd;

        // 実行値
        CalendarCriteria calendarCriteria = new CalendarCriteria();
        calendarCriteria.getDateCriteria().setFrom(criteriaDate.withDayOfMonth(1));
        calendarCriteria.getDateCriteria().setTo(criteriaDate.withDayOfMonth(1).plusMonths(1).minusDays(1));
        CalendarsDataSource calendarsDataSource = new CalendarsDataSource(createCalendarEntityDao());

        // 期待値
        List<CalendarEntity> expectedlist = createCalendarEntityList().stream().filter(c->
            c.getDate().compareTo(criteriaDate.withDayOfMonth(1)) >= 0 &&
            c.getDate().compareTo(criteriaDate.withDayOfMonth(1).plusMonths(1).minusDays(1)) <= 0).collect(Collectors.toList());
        List<Calendar> expectedCalendarlist = newArrayList();
        for(CalendarEntity entity : expectedlist) {
            expectedCalendarlist.add(Calendar.createFrom(
                entity.getCalendarId(),
                CalendarType.codeOf(entity.getCalendarType()),
                entity.getDate(),
                entity.getIsHoliday(),
                entity.getIsManualChange(),
                entity.getIsRelease(),
                entity.getRecordVersion()));
        }
        Calendars expectedCalendars = Calendars.createFrom(expectedCalendarlist);

        // 実行
        Calendars calendars = calendarsDataSource.selectBy(calendarCriteria);

        // 結果検証
        for(int i = 0; i < calendars.getValues().size(); i++) {
            assertThat(calendars.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .isEqualToComparingFieldByField(expectedCalendars.getValues().get(i));
        }
    }

    /**
     * {@link CalendarsDataSource#selectAll()}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・ 正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void selectBy_test1() {

        // 事前準備
        LocalDate criteriaDate = ymd;

        // 実行値
        CalendarsDataSource calendarsDataSource = new CalendarsDataSource(createCalendarEntityDao());

        // 期待値
        List<CalendarEntity> expectedlist = createCalendarEntityList();
        List<Calendar> expectedCalendarlist = newArrayList();
        for(CalendarEntity entity : expectedlist) {
            expectedCalendarlist.add(Calendar.createFrom(
                entity.getCalendarId(),
                CalendarType.codeOf(entity.getCalendarType()),
                entity.getDate(),
                entity.getIsHoliday(),
                entity.getIsManualChange(),
                entity.getIsRelease(),
                entity.getRecordVersion()));
        }
        Calendars expectedCalendars = Calendars.createFrom(expectedCalendarlist);

        // 実行
        Calendars calendars = calendarsDataSource.selectAll();

        // 結果検証
        for(int i = 0; i < calendars.getValues().size(); i++) {
            assertThat(calendars.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .isEqualToComparingFieldByField(expectedCalendars.getValues().get(i));
        }
    }
}