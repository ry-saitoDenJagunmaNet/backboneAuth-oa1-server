package net.jagunma.backbone.auth.authmanager.application.service.oa12060;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarCriteria;

/**
 * OA12060 カレンダーメンテナンス 検索 Converter
 */
class Oa12060SearchConverter implements CalendarSearchRequest {

    /**
     * OA12060 View Object
     */
    private final Oa12060Vo arg;

    // コンストラクタ
    Oa12060SearchConverter(Oa12060Vo oa12060Vo) {
        arg = oa12060Vo;
    }

    // ファクトリーメソッド
    public static Oa12060SearchConverter with(Oa12060Vo oa12060Vo) {
        return new Oa12060SearchConverter(oa12060Vo);
    }

    /**
     * 年月のＧｅｔ
     *
     * @return 年月
     */
    public LocalDate getYearMonth() {
        return arg.getYearMonth();
    }

    /**
     * カレンダーの検索条件を生成します。
     *
     * @return カレンダー検索条件
     */
    public CalendarCriteria genCalendarCriteria() {

        CalendarCriteria criteria = new CalendarCriteria();

        criteria.getDateCriteria().setMoreOrEqual(arg.getYearMonth().withDayOfMonth(1));
        criteria.getDateCriteria().setLessThan(arg.getYearMonth().withDayOfMonth(1).plusMonths(1));

        return criteria;
    }
}
