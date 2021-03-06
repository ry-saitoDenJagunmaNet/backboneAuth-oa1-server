package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepository;
import net.jagunma.common.util.primitives.LocalDates;
import org.springframework.stereotype.Service;

/**
 * カレンダー検索サービス
 */
@Service
public class SearchCalendar {

    private final CalendarRepository calendarRepository;

    // コンストラクタ
    public SearchCalendar(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    /**
     * カレンダー群を検索します
     *
     * @param request カレンダーメンテナンス検索サービス Request
     * @param response 検索結果
     */
    public void execute(CalendarSearchRequest request, CalendarSearchResponse response) {

        // パラメーターの検証
        SearchCalendarValidator.with(request).validate();

        // カレンダー検索
        response.setCalendars(calendarRepository.selectBy(createCriteria(request)));
        response.setYearMonth(request.getYearMonth());
    }

    /**
     * カレンダーの検索条件を作成します
     *
     * @param request カレンダーメンテナンス検索サービス Request
     * @return カレンダー検索条件
     */
    CalendarCriteria createCriteria(CalendarSearchRequest request) {

        CalendarCriteria criteria = new CalendarCriteria();
        criteria.getDateCriteria().setFrom(LocalDates.getFirstDate(request.getYearMonth()));
        criteria.getDateCriteria().setTo(LocalDates.getLastDate(request.getYearMonth()));

        return criteria;
    }

}
