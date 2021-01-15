package net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.Oa12060StoreDetailsConverter;

/**
 * カレンダー適用サービス Request
 */
public interface CalendarStoreRequest {
    /**
     * カレンダーリストを作成します
     * @return カレンダーリスト
     */
    List<Oa12060StoreDetailsConverter> createCalendarList();
}
