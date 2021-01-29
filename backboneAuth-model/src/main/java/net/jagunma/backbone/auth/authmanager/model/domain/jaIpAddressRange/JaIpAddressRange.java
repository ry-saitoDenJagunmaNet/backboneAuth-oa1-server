package net.jagunma.backbone.auth.authmanager.model.domain.jaIpAddressRange;

import java.time.LocalDate;
import net.jagunma.common.values.model.ja.JaAtMoment;

/**
 * JA割当IPアドレス範囲
 */
public class JaIpAddressRange {

    private final Long jaIpAddressRangeId;
    private final String jaCode;
    private final String ipAddressRange;
    private final LocalDate validThruStartDate;
    private final LocalDate validThruEndDate;
    private final JaAtMoment jaAtMoment;

    // コンストラクタ
    JaIpAddressRange(
        Long jaIpAddressRangeId,
        String jaCode,
        String ipAddressRange,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate,
        JaAtMoment jaAtMoment) {

        this.jaIpAddressRangeId = jaIpAddressRangeId;
        this.jaCode = jaCode;
        this.ipAddressRange = ipAddressRange;
        this.validThruStartDate = validThruStartDate;
        this.validThruEndDate = validThruEndDate;
        this.jaAtMoment = jaAtMoment;
    }

    // ファクトリーメソッド
    public static JaIpAddressRange createFrom(
        Long jaIpAddressRangeId,
        String jaCode,
        String ipAddressRange,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate,
        JaAtMoment jaAtMoment) {

        return new JaIpAddressRange(
            jaIpAddressRangeId,
            jaCode,
            ipAddressRange,
            validThruStartDate,
            validThruEndDate,
            jaAtMoment);
    }

    // Getter
    public Long getJaIpAddressRangeId() {
        return jaIpAddressRangeId;
    }
    public String getJaCode() {
        return jaCode;
    }
    public String getIpAddressRange() {
        return ipAddressRange;
    }
    public LocalDate getValidThruStartDate() {
        return validThruStartDate;
    }
    public LocalDate getValidThruEndDate() {
        return validThruEndDate;
    }
    public JaAtMoment getJaAtMoment() {
        return jaAtMoment;
    }
}
