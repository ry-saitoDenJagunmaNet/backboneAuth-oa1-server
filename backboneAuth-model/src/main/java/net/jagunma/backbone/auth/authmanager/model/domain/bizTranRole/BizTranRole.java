package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole;

import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;

/**
 * 取引ロール
 */
public class BizTranRole {

    private final Long bizTranRoleId;
    private final String bizTranRoleCode;
    private final String bizTranRoleName;
    private final String subSystemCode;
    private final SubSystem subSystem;

    // コンストラクタ
    BizTranRole(
        Long bizTranRoleId,
        String bizTranRoleCode,
        String bizTranRoleName,
        String subSystemCode,
        SubSystem subSystem) {

        this.bizTranRoleId = bizTranRoleId;
        this.bizTranRoleCode = bizTranRoleCode;
        this.bizTranRoleName = bizTranRoleName;
        this.subSystemCode = subSystemCode;
        this.subSystem = subSystem;
    }

    // ファクトリーメソッド
    public static BizTranRole createFrom(
        Long bizTranRoleId,
        String bizTranRoleCode,
        String bizTranRoleName,
        String subSystemCode,
        SubSystem subSystem) {

        return new BizTranRole(
            bizTranRoleId,
            bizTranRoleCode,
            bizTranRoleName,
            subSystemCode,
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
    public SubSystem getSubSystem() {
        return subSystem;
    }
}
