package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp;

import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;

/**
 * 取引グループ
 */
public class BizTranGrp {

    private final Long bizTranGrpId;
    private final String bizTranGrpCode;
    private final String bizTranGrpName;
    private final String subSystemCode;
    private final Integer recordVersion;
    private final SubSystem subSystem;

    // コンストラクタ
    BizTranGrp(
        Long bizTranGrpId,
        String bizTranGrpCode,
        String bizTranGrpName,
        String subSystemCode,
        Integer recordVersion,
        SubSystem subSystem) {

        this.bizTranGrpId = bizTranGrpId;
        this.bizTranGrpCode = bizTranGrpCode;
        this.bizTranGrpName = bizTranGrpName;
        this.subSystemCode = subSystemCode;
        this.recordVersion = recordVersion;
        this.subSystem = subSystem;
    }

    // ファクトリーメソッド
    public static BizTranGrp createFrom(
        Long bizTranGrpId,
        String bizTranGrpCode,
        String bizTranGrpName,
        String subSystemCode,
        Integer recordVersion,
        SubSystem subSystem) {

        return new BizTranGrp(
            bizTranGrpId,
            bizTranGrpCode,
            bizTranGrpName,
            subSystemCode,
            recordVersion,
            subSystem);
    }

    // Getter
    public Long getBizTranGrpId() {
        return bizTranGrpId;
    }
    public String getBizTranGrpCode() {
        return bizTranGrpCode;
    }
    public String getBizTranGrpName() {
        return bizTranGrpName;
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
