package net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone;

import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;

/**
 * システム利用可能時間帯
 */
public class SystemAvailableTimeZone {

    private final Long systemAvailableTimeZoneId;
    private final String subSystemCode;
    private final Short dayOfWeek;
    private final String startTime;
    private final String endTime;
    private final Integer recordVersion;
    private final SubSystem subSystem;

    // コンストラクタ
    SystemAvailableTimeZone(
        Long systemAvailableTimeZoneId,
        String subSystemCode,
        Short dayOfWeek,
        String startTime,
        String endTime,
        Integer recordVersion,
        SubSystem subSystem) {

        this.systemAvailableTimeZoneId = systemAvailableTimeZoneId;
        this.subSystemCode = subSystemCode;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.recordVersion = recordVersion;
        this.subSystem = subSystem;
    }

    // ファクトリーメソッド
    public static SystemAvailableTimeZone createFrom(
        Long systemAvailableTimeZoneId,
        String subSystemCode,
        Short dayOfWeek,
        String startTime,
        String endTime,
        Integer recordVersion,
        SubSystem subSystem) {

        return new SystemAvailableTimeZone(
            systemAvailableTimeZoneId,
            subSystemCode,
            dayOfWeek,
            startTime,
            endTime,
            recordVersion,
            subSystem);
    }

    // Getter
    public Long getSystemAvailableTimeZoneId() {
        return systemAvailableTimeZoneId;
    }
    public String getSubSystemCode() {
        return subSystemCode;
    }
    public Short getDayOfWeek() {
        return dayOfWeek;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public Integer getRecordVersion() {
        return recordVersion;
    }
    public SubSystem getSubSystem() {
        return subSystem;
    }
}