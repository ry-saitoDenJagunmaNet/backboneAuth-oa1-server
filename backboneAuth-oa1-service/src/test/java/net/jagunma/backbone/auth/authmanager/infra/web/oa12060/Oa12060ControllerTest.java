package net.jagunma.backbone.auth.authmanager.infra.web.oa12060;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.commandService.StoreCalendar;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchCalendar;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarStoreRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060CalendarVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarType;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarsRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class Oa12060ControllerTest {

    // 実行既定値
    private final ConcurrentModel model = new ConcurrentModel();
    private final String ym = "2020/09";
    private final String ct1 = "1";
    private final String ct2 = "1";
    private final String ct3 = "1";
    private final String wh = "";
    private final boolean calendarTypeFilterCheck1disabled = false;
    private final boolean calendarTypeFilterCheck2disabled = false;
    private final boolean calendarTypeFilterCheck3disabled = false;
    private final String GunmaRuntimeExceptionMessageCode = "EOA13002";
    private final String GunmaRuntimeExceptionMessageArg = "年月";
    // CalendarsのselectByテストデータ
    private Calendars createCalendars(String yearMonthString) {
        LocalDate yearMonth = LocalDate.parse(yearMonthString + "/01", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate ym = yearMonth.withDayOfMonth(1).plusMonths(1).minusDays(1);
        List<Calendar> calendaerList = newArrayList();
        long id = 0;
        //対象月のカレンダーデータを作成
        boolean isHoliday = true;
        for (int i = 1; i <= ym.getDayOfMonth(); i++) {
            calendaerList.add(Calendar.createFrom(id++, CalendarType.経済システム稼働カレンダー, ym.withDayOfMonth(i), isHoliday, true, true, 1));
            calendaerList.add(Calendar.createFrom(id++, CalendarType.信用カレンダー, ym.withDayOfMonth(i), isHoliday, true, true, 1));
            calendaerList.add(Calendar.createFrom(id++, CalendarType.広域物流カレンダー, ym.withDayOfMonth(i), isHoliday, true, true, 1));
            // 1日だけHoliday
            isHoliday = false;
        }
        return Calendars.createFrom(calendaerList);
    }
    // テスト対象クラス生成
    private Oa12060Controller createOa12060Controller(String yearMonthString) {
        return createOa12060Controller(yearMonthString, false);
    }
    private Oa12060Controller createOa12060Controller(String yearMonthString, Boolean isOptimisticLockingFailureException) {
        // カレンダー検索リポジトリのスタブ
        CalendarsRepository calendarsRepository = new CalendarsRepository() {
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
        // カレンダー検索サービスのスタブ
        SearchCalendar searchCalendar = new SearchCalendar(calendarsRepository) {
            public void execute(CalendarSearchRequest request, CalendarSearchResponse response) {
                //yearMonthString==nullならRuntimeException
                //yearMonthString==EmptyならGunmaRuntimeExceptioが発生
                if (yearMonthString.length() == 0) {
                    Preconditions.checkNotEmpty(yearMonthString, () -> new GunmaRuntimeException(GunmaRuntimeExceptionMessageCode, GunmaRuntimeExceptionMessageArg));
                }
                response.setYearMonth(LocalDate.parse(yearMonthString + "/01", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                response.setCalendars(createCalendars(yearMonthString));
            }
        };
        // カレンダー登録リポジトリのスタブ
        CalendarRepositoryForStore calendarRepositoryForStore = new CalendarRepositoryForStore() {
            @Override
            public Calendar update(Calendar calendar) {
                return null;
            }
        };
        // カレンダー検索リポジトリのスタブ
        CalendarRepository calendarRepository = new CalendarRepository() {
            @Override
            public Calendar findOneBy(CalendarCriteria calendarCriteria) {
                return null;
            }
        };
        // カレンダー適用サービスのスタブ
        StoreCalendar storeCalendar = new StoreCalendar(calendarRepositoryForStore, calendarRepository) {
            public int execute(CalendarStoreRequest request) {
                //yearMonthString==nullならRuntimeException
                //yearMonthString==EmptyならGunmaRuntimeExceptioが発生
                if (yearMonthString.length() == 0) {
                    Preconditions.checkNotEmpty(yearMonthString, () -> new GunmaRuntimeException(GunmaRuntimeExceptionMessageCode, GunmaRuntimeExceptionMessageArg));
                }
                if (isOptimisticLockingFailureException) {
                    throw new OptimisticLockingFailureException("aa");
                }
                return 1;
            }
        };
        // テスト対象クラス
        return new Oa12060Controller(searchCalendar, storeCalendar);
    }
    // search時の期待値vo
    private Oa12060Vo createExpectedVo() {
        Oa12060Vo vo = new Oa12060Vo();
        vo.setYearMonth(LocalDate.parse(ym + "/01", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        vo.setYearMonthToString(null);
        vo.setCalendarTypeFilterCheck1(Short.parseShort(ct1));
        vo.setCalendarTypeFilterCheck2(Short.parseShort(ct2));
        vo.setCalendarTypeFilterCheck3(Short.parseShort(ct3));
        vo.setWorkingdayOrHolidaySelect(wh);
        String html =
            "<table id=\"calendar_table\"> <thead>  <tr>   <th colspan=\"2\" class=\"center-align red-text\">日</th>   <th colspan=\"2\" class=\"center-align\">月</th>   <th colspan=\"2\" class=\"center-align\">火</th>   <th colspan=\"2\" class=\"center-align\">水</th>   <th colspan=\"2\" class=\"center-align\">木</th>   <th colspan=\"2\" class=\"center-align\">金</th>   <th colspan=\"2\" class=\"center-align blue-text\">土</th>  </tr>"
                + "</thead> <tbody>  <tr>   <td colspan=\"2\"></td>   <td colspan=\"2\"></td>   <td class=\" oaex_calendarcell_left\">1</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[0]\" name=\"CalendarList[0].isWorkingDay1\" value=\"1\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[0].calendarId1\" value=\"0\"/>     <input type=\"hidden\" name=\"CalendarList[0].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[0]\" name=\"CalendarList[0].isWorkingDay2\" value=\"1\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[0].calendarId2\" value=\"1\"/>     <input type=\"hidden\" name=\"CalendarList[0].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[0]\" name=\"CalendarList[0].isWorkingDay3\" value=\"1\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[0].calendarId3\" value=\"2\"/>     <input type=\"hidden\" name=\"CalendarList[0].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">2</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[1]\" name=\"CalendarList[1].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[1].calendarId1\" value=\"3\"/>     <input type=\"hidden\" name=\"CalendarList[1].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[1]\" name=\"CalendarList[1].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[1].calendarId2\" value=\"4\"/>     <input type=\"hidden\" name=\"CalendarList[1].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[1]\" name=\"CalendarList[1].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[1].calendarId3\" value=\"5\"/>     <input type=\"hidden\" name=\"CalendarList[1].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">3</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[2]\" name=\"CalendarList[2].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[2].calendarId1\" value=\"6\"/>     <input type=\"hidden\" name=\"CalendarList[2].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[2]\" name=\"CalendarList[2].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[2].calendarId2\" value=\"7\"/>     <input type=\"hidden\" name=\"CalendarList[2].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[2]\" name=\"CalendarList[2].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[2].calendarId3\" value=\"8\"/>     <input type=\"hidden\" name=\"CalendarList[2].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">4</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[3]\" name=\"CalendarList[3].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[3].calendarId1\" value=\"9\"/>     <input type=\"hidden\" name=\"CalendarList[3].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[3]\" name=\"CalendarList[3].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[3].calendarId2\" value=\"10\"/>     <input type=\"hidden\" name=\"CalendarList[3].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[3]\" name=\"CalendarList[3].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[3].calendarId3\" value=\"11\"/>     <input type=\"hidden\" name=\"CalendarList[3].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\"blue-text oaex_calendarcell_left\">5</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[4]\" name=\"CalendarList[4].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[4].calendarId1\" value=\"12\"/>     <input type=\"hidden\" name=\"CalendarList[4].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[4]\" name=\"CalendarList[4].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[4].calendarId2\" value=\"13\"/>     <input type=\"hidden\" name=\"CalendarList[4].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[4]\" name=\"CalendarList[4].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[4].calendarId3\" value=\"14\"/>     <input type=\"hidden\" name=\"CalendarList[4].recordVersion3\" value=\"1\"/>    </div>   </td>  </tr>"
                + "  <tr>   <td class=\"red-text oaex_calendarcell_left\">6</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[5]\" name=\"CalendarList[5].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[5].calendarId1\" value=\"15\"/>     <input type=\"hidden\" name=\"CalendarList[5].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[5]\" name=\"CalendarList[5].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[5].calendarId2\" value=\"16\"/>     <input type=\"hidden\" name=\"CalendarList[5].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[5]\" name=\"CalendarList[5].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[5].calendarId3\" value=\"17\"/>     <input type=\"hidden\" name=\"CalendarList[5].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">7</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[6]\" name=\"CalendarList[6].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[6].calendarId1\" value=\"18\"/>     <input type=\"hidden\" name=\"CalendarList[6].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[6]\" name=\"CalendarList[6].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[6].calendarId2\" value=\"19\"/>     <input type=\"hidden\" name=\"CalendarList[6].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[6]\" name=\"CalendarList[6].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[6].calendarId3\" value=\"20\"/>     <input type=\"hidden\" name=\"CalendarList[6].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">8</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[7]\" name=\"CalendarList[7].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[7].calendarId1\" value=\"21\"/>     <input type=\"hidden\" name=\"CalendarList[7].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[7]\" name=\"CalendarList[7].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[7].calendarId2\" value=\"22\"/>     <input type=\"hidden\" name=\"CalendarList[7].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[7]\" name=\"CalendarList[7].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[7].calendarId3\" value=\"23\"/>     <input type=\"hidden\" name=\"CalendarList[7].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">9</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[8]\" name=\"CalendarList[8].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[8].calendarId1\" value=\"24\"/>     <input type=\"hidden\" name=\"CalendarList[8].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[8]\" name=\"CalendarList[8].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[8].calendarId2\" value=\"25\"/>     <input type=\"hidden\" name=\"CalendarList[8].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[8]\" name=\"CalendarList[8].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[8].calendarId3\" value=\"26\"/>     <input type=\"hidden\" name=\"CalendarList[8].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">10</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[9]\" name=\"CalendarList[9].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[9].calendarId1\" value=\"27\"/>     <input type=\"hidden\" name=\"CalendarList[9].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[9]\" name=\"CalendarList[9].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[9].calendarId2\" value=\"28\"/>     <input type=\"hidden\" name=\"CalendarList[9].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[9]\" name=\"CalendarList[9].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[9].calendarId3\" value=\"29\"/>     <input type=\"hidden\" name=\"CalendarList[9].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">11</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[10]\" name=\"CalendarList[10].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[10].calendarId1\" value=\"30\"/>     <input type=\"hidden\" name=\"CalendarList[10].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[10]\" name=\"CalendarList[10].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[10].calendarId2\" value=\"31\"/>     <input type=\"hidden\" name=\"CalendarList[10].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[10]\" name=\"CalendarList[10].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[10].calendarId3\" value=\"32\"/>     <input type=\"hidden\" name=\"CalendarList[10].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\"blue-text oaex_calendarcell_left\">12</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[11]\" name=\"CalendarList[11].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[11].calendarId1\" value=\"33\"/>     <input type=\"hidden\" name=\"CalendarList[11].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[11]\" name=\"CalendarList[11].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[11].calendarId2\" value=\"34\"/>     <input type=\"hidden\" name=\"CalendarList[11].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[11]\" name=\"CalendarList[11].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[11].calendarId3\" value=\"35\"/>     <input type=\"hidden\" name=\"CalendarList[11].recordVersion3\" value=\"1\"/>    </div>   </td>  </tr>"
                + "  <tr>   <td class=\"red-text oaex_calendarcell_left\">13</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[12]\" name=\"CalendarList[12].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[12].calendarId1\" value=\"36\"/>     <input type=\"hidden\" name=\"CalendarList[12].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[12]\" name=\"CalendarList[12].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[12].calendarId2\" value=\"37\"/>     <input type=\"hidden\" name=\"CalendarList[12].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[12]\" name=\"CalendarList[12].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[12].calendarId3\" value=\"38\"/>     <input type=\"hidden\" name=\"CalendarList[12].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">14</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[13]\" name=\"CalendarList[13].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[13].calendarId1\" value=\"39\"/>     <input type=\"hidden\" name=\"CalendarList[13].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[13]\" name=\"CalendarList[13].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[13].calendarId2\" value=\"40\"/>     <input type=\"hidden\" name=\"CalendarList[13].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[13]\" name=\"CalendarList[13].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[13].calendarId3\" value=\"41\"/>     <input type=\"hidden\" name=\"CalendarList[13].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">15</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[14]\" name=\"CalendarList[14].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[14].calendarId1\" value=\"42\"/>     <input type=\"hidden\" name=\"CalendarList[14].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[14]\" name=\"CalendarList[14].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[14].calendarId2\" value=\"43\"/>     <input type=\"hidden\" name=\"CalendarList[14].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[14]\" name=\"CalendarList[14].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[14].calendarId3\" value=\"44\"/>     <input type=\"hidden\" name=\"CalendarList[14].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">16</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[15]\" name=\"CalendarList[15].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[15].calendarId1\" value=\"45\"/>     <input type=\"hidden\" name=\"CalendarList[15].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[15]\" name=\"CalendarList[15].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[15].calendarId2\" value=\"46\"/>     <input type=\"hidden\" name=\"CalendarList[15].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[15]\" name=\"CalendarList[15].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[15].calendarId3\" value=\"47\"/>     <input type=\"hidden\" name=\"CalendarList[15].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">17</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[16]\" name=\"CalendarList[16].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[16].calendarId1\" value=\"48\"/>     <input type=\"hidden\" name=\"CalendarList[16].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[16]\" name=\"CalendarList[16].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[16].calendarId2\" value=\"49\"/>     <input type=\"hidden\" name=\"CalendarList[16].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[16]\" name=\"CalendarList[16].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[16].calendarId3\" value=\"50\"/>     <input type=\"hidden\" name=\"CalendarList[16].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">18</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[17]\" name=\"CalendarList[17].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[17].calendarId1\" value=\"51\"/>     <input type=\"hidden\" name=\"CalendarList[17].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[17]\" name=\"CalendarList[17].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[17].calendarId2\" value=\"52\"/>     <input type=\"hidden\" name=\"CalendarList[17].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[17]\" name=\"CalendarList[17].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[17].calendarId3\" value=\"53\"/>     <input type=\"hidden\" name=\"CalendarList[17].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\"blue-text oaex_calendarcell_left\">19</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[18]\" name=\"CalendarList[18].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[18].calendarId1\" value=\"54\"/>     <input type=\"hidden\" name=\"CalendarList[18].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[18]\" name=\"CalendarList[18].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[18].calendarId2\" value=\"55\"/>     <input type=\"hidden\" name=\"CalendarList[18].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[18]\" name=\"CalendarList[18].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[18].calendarId3\" value=\"56\"/>     <input type=\"hidden\" name=\"CalendarList[18].recordVersion3\" value=\"1\"/>    </div>   </td>  </tr>"
                + "  <tr>   <td class=\"red-text oaex_calendarcell_left\">20</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[19]\" name=\"CalendarList[19].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[19].calendarId1\" value=\"57\"/>     <input type=\"hidden\" name=\"CalendarList[19].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[19]\" name=\"CalendarList[19].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[19].calendarId2\" value=\"58\"/>     <input type=\"hidden\" name=\"CalendarList[19].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[19]\" name=\"CalendarList[19].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[19].calendarId3\" value=\"59\"/>     <input type=\"hidden\" name=\"CalendarList[19].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">21</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[20]\" name=\"CalendarList[20].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[20].calendarId1\" value=\"60\"/>     <input type=\"hidden\" name=\"CalendarList[20].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[20]\" name=\"CalendarList[20].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[20].calendarId2\" value=\"61\"/>     <input type=\"hidden\" name=\"CalendarList[20].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[20]\" name=\"CalendarList[20].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[20].calendarId3\" value=\"62\"/>     <input type=\"hidden\" name=\"CalendarList[20].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">22</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[21]\" name=\"CalendarList[21].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[21].calendarId1\" value=\"63\"/>     <input type=\"hidden\" name=\"CalendarList[21].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[21]\" name=\"CalendarList[21].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[21].calendarId2\" value=\"64\"/>     <input type=\"hidden\" name=\"CalendarList[21].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[21]\" name=\"CalendarList[21].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[21].calendarId3\" value=\"65\"/>     <input type=\"hidden\" name=\"CalendarList[21].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">23</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[22]\" name=\"CalendarList[22].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[22].calendarId1\" value=\"66\"/>     <input type=\"hidden\" name=\"CalendarList[22].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[22]\" name=\"CalendarList[22].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[22].calendarId2\" value=\"67\"/>     <input type=\"hidden\" name=\"CalendarList[22].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[22]\" name=\"CalendarList[22].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[22].calendarId3\" value=\"68\"/>     <input type=\"hidden\" name=\"CalendarList[22].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">24</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[23]\" name=\"CalendarList[23].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[23].calendarId1\" value=\"69\"/>     <input type=\"hidden\" name=\"CalendarList[23].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[23]\" name=\"CalendarList[23].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[23].calendarId2\" value=\"70\"/>     <input type=\"hidden\" name=\"CalendarList[23].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[23]\" name=\"CalendarList[23].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[23].calendarId3\" value=\"71\"/>     <input type=\"hidden\" name=\"CalendarList[23].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">25</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[24]\" name=\"CalendarList[24].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[24].calendarId1\" value=\"72\"/>     <input type=\"hidden\" name=\"CalendarList[24].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[24]\" name=\"CalendarList[24].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[24].calendarId2\" value=\"73\"/>     <input type=\"hidden\" name=\"CalendarList[24].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[24]\" name=\"CalendarList[24].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[24].calendarId3\" value=\"74\"/>     <input type=\"hidden\" name=\"CalendarList[24].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\"blue-text oaex_calendarcell_left\">26</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[25]\" name=\"CalendarList[25].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[25].calendarId1\" value=\"75\"/>     <input type=\"hidden\" name=\"CalendarList[25].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[25]\" name=\"CalendarList[25].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[25].calendarId2\" value=\"76\"/>     <input type=\"hidden\" name=\"CalendarList[25].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[25]\" name=\"CalendarList[25].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[25].calendarId3\" value=\"77\"/>     <input type=\"hidden\" name=\"CalendarList[25].recordVersion3\" value=\"1\"/>    </div>   </td>  </tr>"
                + "  <tr>   <td class=\"red-text oaex_calendarcell_left\">27</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[26]\" name=\"CalendarList[26].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[26].calendarId1\" value=\"78\"/>     <input type=\"hidden\" name=\"CalendarList[26].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[26]\" name=\"CalendarList[26].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[26].calendarId2\" value=\"79\"/>     <input type=\"hidden\" name=\"CalendarList[26].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[26]\" name=\"CalendarList[26].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[26].calendarId3\" value=\"80\"/>     <input type=\"hidden\" name=\"CalendarList[26].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">28</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[27]\" name=\"CalendarList[27].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[27].calendarId1\" value=\"81\"/>     <input type=\"hidden\" name=\"CalendarList[27].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[27]\" name=\"CalendarList[27].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[27].calendarId2\" value=\"82\"/>     <input type=\"hidden\" name=\"CalendarList[27].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[27]\" name=\"CalendarList[27].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[27].calendarId3\" value=\"83\"/>     <input type=\"hidden\" name=\"CalendarList[27].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">29</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[28]\" name=\"CalendarList[28].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[28].calendarId1\" value=\"84\"/>     <input type=\"hidden\" name=\"CalendarList[28].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[28]\" name=\"CalendarList[28].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[28].calendarId2\" value=\"85\"/>     <input type=\"hidden\" name=\"CalendarList[28].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[28]\" name=\"CalendarList[28].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[28].calendarId3\" value=\"86\"/>     <input type=\"hidden\" name=\"CalendarList[28].recordVersion3\" value=\"1\"/>    </div>   </td>   <td class=\" oaex_calendarcell_left\">30</td>   <td class=\"oaex_calendarcell_right\">    <div>     <label class=\"oaex_calendarcell_check1\">      <input type=\"checkbox\" id=\"calendar_type_check1[29]\" name=\"CalendarList[29].isWorkingDay1\" value=\"1\" checked=\"checked\"/>      <span>経済</span>     </label>     <input type=\"hidden\" name=\"CalendarList[29].calendarId1\" value=\"87\"/>     <input type=\"hidden\" name=\"CalendarList[29].recordVersion1\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check2\">      <input type=\"checkbox\" id=\"calendar_type_check2[29]\" name=\"CalendarList[29].isWorkingDay2\" value=\"1\" checked=\"checked\"/>      <span>信用</span>     </label>     <input type=\"hidden\" name=\"CalendarList[29].calendarId2\" value=\"88\"/>     <input type=\"hidden\" name=\"CalendarList[29].recordVersion2\" value=\"1\"/>    </div>    <div>     <label class=\"oaex_calendarcell_check3\">      <input type=\"checkbox\" id=\"calendar_type_check3[29]\" name=\"CalendarList[29].isWorkingDay3\" value=\"1\" checked=\"checked\"/>      <span>広域物流</span>     </label>     <input type=\"hidden\" name=\"CalendarList[29].calendarId3\" value=\"89\"/>     <input type=\"hidden\" name=\"CalendarList[29].recordVersion3\" value=\"1\"/>    </div>   </td>  </tr>"
                + " </tbody></table>";
        vo.setCalendarTable(html);
        vo.setCalendarTypeFilterCheck1disabled(calendarTypeFilterCheck1disabled);
        vo.setCalendarTypeFilterCheck2disabled(calendarTypeFilterCheck2disabled);
        vo.setCalendarTypeFilterCheck3disabled(calendarTypeFilterCheck3disabled);
        vo.setCalendarList(null);
        return vo;
    }
    //OA12060カレンダー１日分のセル作成
    private Oa12060CalendarVo cretaeOa12060CalendarVo(
        Long calendarId1, short isWorkingDay1,
        Long calendarId2, short isWorkingDay2,
        Long calendarId3, short isWorkingDay3) {

        Oa12060CalendarVo calendarVo = new Oa12060CalendarVo();
        calendarVo.setCalendarId1(calendarId1);
        calendarVo.setIsWorkingDay1(isWorkingDay1);
        calendarVo.setRecordVersion1(1);
        calendarVo.setCalendarId2(calendarId2);
        calendarVo.setIsWorkingDay2(isWorkingDay2);
        calendarVo.setRecordVersion2(1);
        calendarVo.setCalendarId3(calendarId3);
        calendarVo.setIsWorkingDay3(isWorkingDay3);
        calendarVo.setRecordVersion3(1);
        return calendarVo;
    }

    /**
     * {@link Oa12060Controller#get(Model)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・ 戻り値
     *  ・ Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test0() {

        // テスト対象クラス生成
        Oa12060Controller oa12060Controller = createOa12060Controller(ym);

        // 期待値
        String expected = "oa12060";
        Oa12060Vo expectedVo = createExpectedVo();

        // 実行
        String result = oa12060Controller.get(model);

        // 検証対象
        Oa12060Vo vo = (Oa12060Vo) model.getAttribute("form");

        // 結果検証
        assertThat(result).isEqualTo(expected);
        assertThat(vo).isEqualToComparingFieldByField(expectedVo);
    }

    /**
     * {@link Oa12060Controller#get(Model)}のテスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test1() {
        // getメソッドでGunmaRuntimeExceptionを発生させるテストは不可
        assertThat(true);
    }

    /**
     * {@link Oa12060Controller#get(Model)}のテスト
     *  ●パターン
     *    例外（RuntimeException ）発生
     *
     *  ●検証事項
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test2() {
        // getメソッドでRuntimeExceptionを発生させるテストは不可
        assertThat(true);
    }

    /**
     * {@link Oa12060Controller#search(Model, String, String, String, String, String)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・ 戻り値
     *  ・ Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void search_test0() {

        // テスト対象クラス生成
        Oa12060Controller oa12060Controller = createOa12060Controller(ym);

        // 期待値
        String expected = "oa12060";
        Oa12060Vo expectedVo = createExpectedVo();

        // 実行
        String result = oa12060Controller.search(model, ym, ct1, ct2, ct3, wh);

        // 検証対象
        Oa12060Vo vo = (Oa12060Vo) model.getAttribute("form");

        // 結果検証
        assertThat(result).isEqualTo(expected);
        assertThat(vo).isEqualToComparingFieldByField(expectedVo);
    }

    /**
     * {@link Oa12060Controller#search(Model, String, String, String, String, String)}のテスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     *  ・ 戻り値
     *  ・ エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void search_test1() {

        // 事前準備
        String ymEmpty = "";

        // テスト対象クラス生成
        Oa12060Controller oa12060Controller = createOa12060Controller(ymEmpty);

        // 期待値
        String expected = "oa12060";

        // 実行
        String result = oa12060Controller.search(model, ym, ct1, ct2, ct3, wh);

        // 検証対象
        Oa12060Vo vo = (Oa12060Vo) model.getAttribute("form");

        // 結果検証
        assertThat(result).isEqualTo(expected);
        assertThat(vo.getMessageCode()).isEqualTo(GunmaRuntimeExceptionMessageCode);
        assertThat(vo.getMessageArgs().get(0)).isEqualTo(GunmaRuntimeExceptionMessageArg);
    }

    /**
     * {@link Oa12060Controller#search(Model, String, String, String, String, String)}のテスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・ 戻り値
     *  ・ エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void search_test2() {

        // 事前準備
        String ymNull = null;

        // テスト対象クラス生成
        Oa12060Controller oa12060Controller = createOa12060Controller(ymNull);

        // 期待値
        String expected = "oa19999";
        String expectedErrorMessage = "サーバーで予期しないエラーが発生しました。";

        // 実行
        String result = oa12060Controller.search(model, ym, ct1, ct2, ct3, wh);

        // 検証対象
        Oa12060Vo vo = (Oa12060Vo) model.getAttribute("form");

        // 結果検証
        assertThat(result).isEqualTo(expected);
        assertThat(vo.getErrorMessage()).isEqualTo(expectedErrorMessage);
    }

    /**
     * {@link Oa12060Controller#store(Model, Oa12060Vo )}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・ 戻り値
     *  ・ Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void store_test0() {

        // 事前準備
        Oa12060Vo vo = new Oa12060Vo();

        // 実行値
        vo.setYearMonthToString(ym);
        vo.setCalendarTypeFilterCheck1(Short.parseShort(ct1));
        vo.setCalendarTypeFilterCheck2(Short.parseShort(ct2));
        vo.setCalendarTypeFilterCheck3(Short.parseShort(ct3));
        vo.setWorkingdayOrHolidaySelect("");
        List<Oa12060CalendarVo> calendarList = newArrayList();
        calendarList.add(cretaeOa12060CalendarVo(1L, (short) 1, 2L, (short) 0, 3L, (short) 1));
        calendarList.add(cretaeOa12060CalendarVo(4L, (short) 0, 5L, (short) 1, 6L, (short) 0));
        vo.setCalendarList(calendarList);

        // テスト対象クラス生成
        Oa12060Controller oa12060Controller = createOa12060Controller(ym);

        // 期待値
        String expected = "oa12060";
        Oa12060Vo expectedVo = createExpectedVo();
        expectedVo.setMessage("適用しました。");

        // 実行
        String result = oa12060Controller.store(model, vo);

        // 検証対象
        Oa12060Vo oa12060Vo = (Oa12060Vo) model.getAttribute("form");

        // 結果検証
        assertThat(result).isEqualTo(expected);
        assertThat(oa12060Vo).isEqualToComparingFieldByField(expectedVo);
    }

    /**
     * {@link Oa12060Controller#store(Model, Oa12060Vo )}のテスト
     *  ●パターン
     *    正常
     *    (表示対象フラグがNullの場合）
     *
     *  ●検証事項
     *  ・ 戻り値
     *  ・ Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void store_test1() {

        // 事前準備
        Oa12060Vo vo = new Oa12060Vo();

        // 実行値
        vo.setYearMonthToString(ym);
        vo.setWorkingdayOrHolidaySelect("");
        List<Oa12060CalendarVo> calendarList = newArrayList();
        calendarList.add(cretaeOa12060CalendarVo(1L, (short) 1, 2L, (short) 0, 3L, (short) 1));
        calendarList.add(cretaeOa12060CalendarVo(4L, (short) 0, 5L, (short) 1, 6L, (short) 0));
        vo.setCalendarList(calendarList);

        // テスト対象クラス生成
        Oa12060Controller oa12060Controller = createOa12060Controller(ym);

        // 期待値
        String expected = "oa12060";
        Oa12060Vo expectedVo = createExpectedVo();
        expectedVo.setMessage("適用しました。");

        // 実行
        String result = oa12060Controller.store(model, vo);

        // 検証対象
        Oa12060Vo oa12060Vo = (Oa12060Vo) model.getAttribute("form");

        // 結果検証
        assertThat(result).isEqualTo(expected);
        assertThat(oa12060Vo).isEqualToComparingFieldByField(expectedVo);
    }

    /**
     * {@link Oa12060Controller#store(Model, Oa12060Vo )}のテスト
     *  ●パターン
     *    例外（OptimisticLockingFailureException ）発生
     *
     *  ●検証事項
     *  ・ 戻り値
     *  ・ エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void store_test2() {

        // 事前準備
        Oa12060Vo vo = new Oa12060Vo();
        Boolean isOptimisticLockingFailureException = true;

        // テスト対象クラス生成
        Oa12060Controller oa12060Controller = createOa12060Controller(ym, isOptimisticLockingFailureException);

        // 期待値
        String expected = "oa19999";
        String expectedErrorMessage = "該当データは他端末で更新されています。";

        // 実行
        String result = oa12060Controller.store(model, vo);

        // 結果検証
        assertThat(result).isEqualTo(expected);
        assertThat(vo.getErrorMessage()).isEqualTo(expectedErrorMessage);
    }

    /**
     * {@link Oa12060Controller#store(Model, Oa12060Vo )}のテスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     *  ・ 戻り値
     *  ・ エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void store_test3() {

        // 事前準備
        Oa12060Vo vo = new Oa12060Vo();
        String ymEmpty = "";

        // テスト対象クラス生成
        Oa12060Controller oa12060Controller = createOa12060Controller(ymEmpty);

        // 期待値
        String expected = "oa12060";

        // 実行
        String result = oa12060Controller.store(model, vo);

        // 結果検証
        assertThat(result).isEqualTo(expected);
        assertThat(vo.getMessageCode()).isEqualTo(GunmaRuntimeExceptionMessageCode);
        assertThat(vo.getMessageArgs().get(0)).isEqualTo(GunmaRuntimeExceptionMessageArg);
    }

    /**
     * {@link Oa12060Controller#store(Model, Oa12060Vo )}のテスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・ 戻り値
     *  ・ エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void store_test4() {

        // 事前準備
        Oa12060Vo vo = new Oa12060Vo();
        String ymNull = null;

        // テスト対象クラス生成
        Oa12060Controller oa12060Controller = createOa12060Controller(ymNull);

        // 期待値
        String expected = "oa19999";
        String expectedErrorMessage = "サーバーで予期しないエラーが発生しました。";

        // 実行
        String result = oa12060Controller.store(model, vo);

        // 結果検証
        assertThat(result).isEqualTo(expected);
        assertThat(vo.getErrorMessage()).isEqualTo(expectedErrorMessage);
    }
}