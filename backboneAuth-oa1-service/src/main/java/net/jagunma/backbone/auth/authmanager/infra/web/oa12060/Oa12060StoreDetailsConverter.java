package net.jagunma.backbone.auth.authmanager.infra.web.oa12060;

import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarStoreDetailsRequest;
import net.jagunma.backbone.auth.authmanager.model.types.CalendarType;

/**
 * OA12060 カレンダーメンテナンス反映サービス カレンダー詳細 Request Converter
 */
public class Oa12060StoreDetailsConverter implements CalendarStoreDetailsRequest {
    private final Long calendarId;
    private final CalendarType calendarType;
    private final Boolean isWorkingDay;
    private final Integer recordVersion;

    // コンストラクタ
    Oa12060StoreDetailsConverter(
        Long calendarId,
        CalendarType calendarType,
        Boolean isWorkingDay,
        Integer recordVersion) {

        this.calendarId = calendarId;
        this.calendarType = calendarType;
        this.isWorkingDay = isWorkingDay;
        this.recordVersion = recordVersion;
    }

    // ファクトリーメソッド
    public static Oa12060StoreDetailsConverter with(
        Long calendarId,
        CalendarType calendarType,
        Boolean isWorkingDay,
        Integer recordVersion) {

        return new Oa12060StoreDetailsConverter(calendarId, calendarType, isWorkingDay, recordVersion);
    }

    /**
     * カレンダーIDのＧｅｔ
     *
     * @return カレンダーID
     */
    public Long getCalendarId() {
        return calendarId;
    }
    /**
     * カレンダー種類のＧｅｔ
     *
     * @return カレンダー種類
     */
    public CalendarType getCalendarType() {
        return calendarType;
    }
    /**
     * 稼働日チェックのＧｅｔ
     *
     * @return 稼働日チェック
     */
    public Boolean getIsWorkingDay() {
        return isWorkingDay;
    }
    /**
     * レコードバージョンのＧｅｔ
     *
     * @return レコードバージョン
     */
    public Integer getRecordVersion() {
        return recordVersion;
    }
}
