package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole;

import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;

/**
 * 取引ロール
 */
public class BizTranRole {

    private final Long bizTranRoleId;
    private final String bizTranRoleCode;
    private final String bizTranRoleName;
    private final String subSystemCode;
    private final Integer recordVersion;
    private final SubSystem subSystem;

    // コンストラクタ
    BizTranRole(
        Long bizTranRoleId,
        String bizTranRoleCode,
        String bizTranRoleName,
        String subSystemCode,
        Integer recordVersion,
        SubSystem subSystem) {

        this.bizTranRoleId = bizTranRoleId;
        this.bizTranRoleCode = bizTranRoleCode;
        this.bizTranRoleName = bizTranRoleName;
        this.subSystemCode = subSystemCode;
        this.recordVersion = recordVersion;
        this.subSystem = subSystem;
    }

    // ファクトリーメソッド
    public static BizTranRole createFrom(
        Long bizTranRoleId,
        String bizTranRoleCode,
        String bizTranRoleName,
        String subSystemCode,
        Integer recordVersion,
        SubSystem subSystem) {

        return new BizTranRole(
            bizTranRoleId,
            bizTranRoleCode,
            bizTranRoleName,
            subSystemCode,
            recordVersion,
            subSystem);
    }

    // Getter
    public Long getBizTranRoleId() {
        return bizTranRoleId;
    }
    public String getBizTranRoleCode() {
        return bizTranRoleCode;
    }
    public String getBizTranRoleName() {
        return bizTranRoleName;
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
