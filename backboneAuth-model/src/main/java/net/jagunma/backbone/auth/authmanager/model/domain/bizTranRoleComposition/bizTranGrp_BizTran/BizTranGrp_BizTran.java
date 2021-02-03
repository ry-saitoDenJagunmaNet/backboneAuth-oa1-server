package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran;

import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.entity2.AbstractEntity2;

/**
 * 取引グループ_取引割当
 */
public class BizTranGrp_BizTran extends AbstractEntity2<BizTranGrp_BizTran> {

    private static final long serialVersionUID = 1L;

    private final Long bizTranGrp_BizTranId;
    private final Long bizTranGrpId;
    private final Long bizTranId;
    private final String subSystemCode;
    private final Integer recordVersion;
    private final BizTranGrp bizTranGrp;
    private final BizTran bizTran;
    private final SubSystem subSystem;

    // コンストラクタ
    BizTranGrp_BizTran(
        Long bizTranGrp_BizTranId,
        Long bizTranGrpId,
        Long bizTranId,
        String subSystemCode,
        Integer recordVersion,
        BizTranGrp bizTranGrp,
        BizTran bizTran,
        SubSystem subSystem) {

        this.bizTranGrp_BizTranId = bizTranGrp_BizTranId;
        this.bizTranGrpId = bizTranGrpId;
        this.bizTranId = bizTranId;
        this.subSystemCode = subSystemCode;
        this.recordVersion = recordVersion;
        this.bizTranGrp = bizTranGrp;
        this.bizTran = bizTran;
        this.subSystem = subSystem;
    }

    // ファクトリーメソッド
    public static BizTranGrp_BizTran createFrom(
        Long bizTranGrp_BizTranId,
        Long bizTranGrpId,
        Long bizTranId,
        String subSystemCode,
        Integer recordVersion,
        BizTranGrp bizTranGrp,
        BizTran bizTran,
        SubSystem subSystem) {

        return new BizTranGrp_BizTran(
            bizTranGrp_BizTranId,
            bizTranGrpId,
            bizTranId,
            subSystemCode,
            recordVersion,
            bizTranGrp,
            bizTran,
            subSystem);
    }

    // Getter
    public Long getBizTranGrp_BizTranId() {
        return bizTranGrp_BizTranId;
    }
    public Long getBizTranGrpId() {
        return bizTranGrpId;
    }
    public Long getBizTranId() {
        return bizTranId;
    }
    public String getSubSystemCode() {
        return subSystemCode;
    }
    public Integer getRecordVersion() {
        return recordVersion;
    }
    public BizTranGrp getBizTranGrp() {
        return bizTranGrp;
    }
    public BizTran getBizTran() {
        return bizTran;
    }
    public SubSystem getSubSystem() {
        return subSystem;
    }
}
