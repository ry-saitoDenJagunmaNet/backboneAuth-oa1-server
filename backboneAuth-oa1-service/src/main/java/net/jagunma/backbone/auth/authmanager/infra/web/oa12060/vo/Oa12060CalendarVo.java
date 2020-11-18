package net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo;

import java.io.Serializable;

/**
 * OA12060カレンダー１日分のセル
 */
public class Oa12060CalendarVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 経済カレンダーID
     */
    private Long calendarId1;
    /**
     * 経済稼働日チェック
     */
    private Boolean isWorkingDay1;
    /**
     * 経済レコードバージョン
     */
    private Integer recordVersion1;
    /**
     * 信用カレンダーID
     */
    private Long calendarId2;
    /**
     * 信用稼働日チェック
     */
    private Boolean isWorkingDay2;
    /**
     * 信用レコードバージョン
     */
    private Integer recordVersion2;
    /**
     * 広域物流カレンダーID
     */
    private Long calendarId3;
    /**
     * 広域物流稼働日チェック
     */
    private Boolean isWorkingDay3;
    /**
     * 広域物流レコードバージョン
     */
    private Integer recordVersion3;

    public Long getCalendarId1() {
        return calendarId1;
    }
    public void setCalendarId1(Long calendarId1) {
        this.calendarId1 = calendarId1;
    }
    public Boolean getIsWorkingDay1() {
        return isWorkingDay1;
    }
    public void setIsWorkingDay1(Boolean isWorkingDay1) {
        this.isWorkingDay1 = isWorkingDay1;
    }
    public Integer getRecordVersion1() {
        return recordVersion1;
    }
    public void setRecordVersion1(Integer recordVersion1) {
        this.recordVersion1 = recordVersion1;
    }
    public Long getCalendarId2() {
        return calendarId2;
    }
    public void setCalendarId2(Long calendarId2) {
        this.calendarId2 = calendarId2;
    }
    public Boolean getIsWorkingDay2() {
        return isWorkingDay2;
    }
    public void setIsWorkingDay2(Boolean isWorkingDay2) {
        this.isWorkingDay2 = isWorkingDay2;
    }
    public Integer getRecordVersion2() {
        return recordVersion2;
    }
    public void setRecordVersion2(Integer recordVersion2) {
        this.recordVersion2 = recordVersion2;
    }
    public Long getCalendarId3() {
        return calendarId3;
    }
    public void setCalendarId3(Long calendarId3) {
        this.calendarId3 = calendarId3;
    }
    public Boolean getIsWorkingDay3() {
        return isWorkingDay3;
    }
    public void setIsWorkingDay3(Boolean isWorkingDay3) {
        this.isWorkingDay3 = isWorkingDay3;
    }
    public Integer getRecordVersion3() {
        return recordVersion3;
    }
    public void setRecordVersion3(Integer recordVersion3) {
        this.recordVersion3 = recordVersion3;
    }
}
