package net.jagunma.backbone.auth.authmanager.infra.web.oa12060;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarStoreRequest;
import net.jagunma.backbone.auth.authmanager.infra.util.CheckboxUtil;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarType;

/**
 * OA12060 カレンダーメンテナンス反映サービス Request Converter
 */
class Oa12060StoreConverter implements CalendarStoreRequest {

    /**
     * OA12060 View Object
     */
    private final Oa12060Vo vo;

    // コンストラクタ
    Oa12060StoreConverter(Oa12060Vo oa12060Vo) {
        vo = oa12060Vo;
    }

    // ファクトリーメソッド
    public static Oa12060StoreConverter with(Oa12060Vo oa12060Vo) {
        return new Oa12060StoreConverter(oa12060Vo);
    }

    /**
     * カレンダー詳細リストを作成します
     *
     * @return カレンダー詳細リスト
     */
    public List<Oa12060StoreDetailsConverter> createCalendarList() {

        List<Oa12060StoreDetailsConverter> list = newArrayList();
        vo.getCalendarList().forEach(c -> {
            if (c.getCalendarId1() != null) {
                list.add(Oa12060StoreDetailsConverter.with(
                    c.getCalendarId1(),
                    CalendarType.経済システム稼働カレンダー,
                    CheckboxUtil.getSmoother(c.getIsWorkingDay1()),
                    c.getRecordVersion1()));
            }
            if (c.getCalendarId2() != null) {
                list.add(Oa12060StoreDetailsConverter.with(
                    c.getCalendarId2(),
                    CalendarType.信用カレンダー,
                    CheckboxUtil.getSmoother(c.getIsWorkingDay2()),
                    c.getRecordVersion2()));
            }
            if (c.getCalendarId3() != null) {
                list.add(Oa12060StoreDetailsConverter.with(
                    c.getCalendarId3(),
                    CalendarType.広域物流カレンダー,
                    CheckboxUtil.getSmoother(c.getIsWorkingDay3()),
                    c.getRecordVersion3()));
            }
        });
        return list;
    }
}
