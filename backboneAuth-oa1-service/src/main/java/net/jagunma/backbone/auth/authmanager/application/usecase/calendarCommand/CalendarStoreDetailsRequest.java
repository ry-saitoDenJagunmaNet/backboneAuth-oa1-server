package net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand;

import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarType;

/**
 * カレンダー適用サービス カレンダー詳細 Request
 */
public interface CalendarStoreDetailsRequest {
    /**
     * カレンダーIDのＧｅｔ
     * @return カレンダーID
     */
    Long getCalendarId();
    /**
     * カレンダー種類のＧｅｔ
     * @return カレンダー種類
     */
    CalendarType getCalendarType();
    /**
     * 稼働日チェックのＧｅｔ
     * @return 稼働日チェック
     */
    Boolean getIsWorkingDay();
    /**
     * レコードバージョンのＧｅｔ
     * @return レコードバージョン
     */
    Integer getRecordVersion();
}
