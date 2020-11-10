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
    private final LocalDate expirationStartDate;
    private final LocalDate expirationEndDate;
    private final String subSystemCode;
    private final Integer recordVersion;
    private final SubSystem subSystem;

    // コンストラクタ
    BizTran(
        Long bizTranId,
        String bizTranCode,
        String bizTranName,
        Boolean isCenterBizTran,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        String subSystemCode,
        Integer recordVersion,
        SubSystem subSystem) {

        this.bizTranId = bizTranId;
        this.bizTranCode = bizTranCode;
        this.bizTranName = bizTranName;
        this.isCenterBizTran = isCenterBizTran;
        this.expirationStartDate = expirationStartDate;
        this.expirationEndDate = expirationEndDate;
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
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        String subSystemCode,
        Integer recordVersion,
        SubSystem subSystem) {

        return new BizTran(
            bizTranId,
            bizTranCode,
            bizTranName,
            isCenterBizTran,
            expirationStartDate,
            expirationEndDate,
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
    public LocalDate getExpirationStartDate() {
        return expirationStartDate;
    }
    public LocalDate getExpirationEndDate() {
        return expirationEndDate;
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
