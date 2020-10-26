package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp;

import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;

/**
 * 取引ロール_取引グループ割当
 */
public class BizTranRole_BizTranGrp {

    private final Long bizTranRole_BizTranGrpId;
    private final Long bizTranRoleId;
    private final Long bizTranGrpId;
    private final String subSystemCode;
    private final Integer recordVersion;
    private final BizTranRole bizTranRole;
    private final BizTranGrp bizTranGrp;
    private final SubSystem subSystem;

    // コンストラクタ
    BizTranRole_BizTranGrp(
        Long bizTranRole_BizTranGrpId,
        Long bizTranRoleId,
        Long bizTranGrpId,
        String subSystemCode,
        Integer recordVersion,
        BizTranRole bizTranRole,
        BizTranGrp bizTranGrp,
        SubSystem subSystem) {

        this.bizTranRole_BizTranGrpId = bizTranRole_BizTranGrpId;
        this.bizTranRoleId = bizTranRoleId;
        this.bizTranGrpId = bizTranGrpId;
        this.subSystemCode = subSystemCode;
        this.recordVersion = recordVersion;
        this.bizTranRole = bizTranRole;
        this.bizTranGrp = bizTranGrp;
        this.subSystem = subSystem;
    }

    // ファクトリーメソッド
    public static BizTranRole_BizTranGrp createFrom(
        Long bizTranRole_BizTranGrpId,
        Long bizTranRoleId,
        Long bizTranGrpId,
        String subSystemCode,
        Integer recordVersion,
        BizTranRole bizTranRole,
        BizTranGrp bizTranGrp,
        SubSystem subSystem) {

        return new BizTranRole_BizTranGrp(
            bizTranRole_BizTranGrpId,
            bizTranRoleId,
            bizTranGrpId,
            subSystemCode,
            recordVersion,
            bizTranRole,
            bizTranGrp,
            subSystem);
    }

    // Getter
    public Long getBizTranRole_BizTranGrpId() {
        return bizTranRole_BizTranGrpId;
    }
    public Long getBizTranRoleId() {
        return bizTranRoleId;
    }
    public Long getBizTranGrpId() {
        return bizTranGrpId;
    }
    public String getSubSystemCode() {
        return subSystemCode;
    }
    public Integer getRecordVersion() {
        return recordVersion;
    }
    public BizTranRole getBizTranRole() {
        return bizTranRole;
    }
    public BizTranGrp getBizTranGrp() {
        return bizTranGrp;
    }
    public SubSystem getSubSystem() {
        return subSystem;
    }
}
