package net.jagunma.backbone.auth.authmanager.infra.web.oa12060;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060Vo;

/**
 * OA12060 検索 Converter
 */
class Oa12060SearchConverter implements CalendarSearchRequest {

    /**
     * OA12060 View Object
     */
    private final Oa12060Vo vo;

    // コンストラクタ
    Oa12060SearchConverter(Oa12060Vo oa12060Vo)  {
        vo = oa12060Vo;
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
        return vo.getYearMonth();
    }
}
