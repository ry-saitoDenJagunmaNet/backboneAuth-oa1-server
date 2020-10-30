package net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition;

import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;

/**
 * 取引ロール編成
 */
public class BizTranRoleComposition {

    private final BizTrans bizTrans;
    private final BizTranGrps bizTranGrps;
    private final BizTranGrp_BizTrans bizTranGrp_BizTrans;
    private final BizTranRoles bizTranRoles;
    private final BizTranRole_BizTranGrps bizTranRole_BizTranGrps;

    // コンストラクタ
    BizTranRoleComposition(
        BizTrans bizTrans,
        BizTranGrps bizTranGrps,
        BizTranGrp_BizTrans bizTranGrp_BizTrans,
        BizTranRoles bizTranRoles,
        BizTranRole_BizTranGrps bizTranRole_BizTranGrps) {

        this.bizTrans = bizTrans;
        this.bizTranGrps = bizTranGrps;
        this.bizTranGrp_BizTrans = bizTranGrp_BizTrans;
        this.bizTranRoles = bizTranRoles;
        this.bizTranRole_BizTranGrps = bizTranRole_BizTranGrps;
    }

    // ファクトリーメソッド
    public static BizTranRoleComposition createFrom(
        BizTrans bizTrans,
        BizTranGrps bizTranGrps,
        BizTranGrp_BizTrans bizTranGrp_BizTrans,
        BizTranRoles bizTranRoles,
        BizTranRole_BizTranGrps bizTranRole_BizTranGrps) {

        return new BizTranRoleComposition(
            bizTrans,
            bizTranGrps,
            bizTranGrp_BizTrans,
            bizTranRoles,
            bizTranRole_BizTranGrps);
    }

    // Getter
    public BizTrans getBizTrans() {
        return bizTrans;
    }
    public BizTranGrps getBizTranGrps() {
        return bizTranGrps;
    }
    public BizTranGrp_BizTrans getBizTranGrp_BizTrans() {
        return bizTranGrp_BizTrans;
    }
    public BizTranRoles getBizTranRoles() {
        return bizTranRoles;
    }
    public BizTranRole_BizTranGrps getBizTranRole_BizTranGrps() {
        return bizTranRole_BizTranGrps;
    }
}
