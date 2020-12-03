package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;

/**
 * 取引
 */
public class BizTran {

    private final Long bizTranId;
    private final String bizTranCode;
    private final String bizTranName;
    private final Boolean isCenterBizTran;
    private final LocalDate validThruStartDate;
    private final LocalDate validThruEndDate;
    private final String subSystemCode;
    private final Integer recordVersion;
    private final SubSystem subSystem;

    // コンストラクタ
    BizTran(
        Long bizTranId,
        String bizTranCode,
        String bizTranName,
        Boolean isCenterBizTran,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate,
        String subSystemCode,
        Integer recordVersion,
        SubSystem subSystem) {

        this.bizTranId = bizTranId;
        this.bizTranCode = bizTranCode;
        this.bizTranName = bizTranName;
        this.isCenterBizTran = isCenterBizTran;
        this.validThruStartDate = validThruStartDate;
        this.validThruEndDate = validThruEndDate;
        this.subSystemCode = subSystemCode;
        this.recordVersion = recordVersion;
        this.subSystem = subSystem;
    }

    // ファクトリーメソッド
    public static BizTran createFrom(
        Long bizTranId,
        String bizTranCode,
        String bizTranName,
        Boolean isCenterBizTran,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate,
        String subSystemCode,
        Integer recordVersion,
        SubSystem subSystem) {

        return new BizTran(
            bizTranId,
            bizTranCode,
            bizTranName,
            isCenterBizTran,
            validThruStartDate,
            validThruEndDate,
            subSystemCode,
            recordVersion,
            subSystem);
    }

    // Getter
    public Long getBizTranId() {
        return bizTranId;
    }
    public String getBizTranCode() {
        return bizTranCode;
    }
    public String getBizTranName() {
        return bizTranName;
    }
    public Boolean getIsCenterBizTran() {
        return isCenterBizTran;
    }
    public LocalDate getValidThruStartDate() {
        return validThruStartDate;
    }
    public LocalDate getValidThruEndDate() {
        return validThruEndDate;
    }
    public String getSubSystemCode() {
        return subSystemCode;
    }
    public Integer getRecordVersion() {
        return recordVersion;
    }
    public SubSystem getSubSystem() {
        return subSystem;
    }
}
