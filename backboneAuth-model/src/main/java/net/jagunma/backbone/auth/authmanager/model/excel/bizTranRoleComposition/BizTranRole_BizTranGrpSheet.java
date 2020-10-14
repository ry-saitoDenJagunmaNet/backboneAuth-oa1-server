package net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition;

/**
 * 取引ロール＋取引グループ
 */
public class BizTranRole_BizTranGrpSheet {

    private final String subSystemName;
    private final String bizTranRoleCode;
    private final String bizTranRoleName;
    private final String bizTranGrpCode;
    private final String bizTranGrpName;

    // コンストラクタ
    BizTranRole_BizTranGrpSheet(
        String subSystemName,
        String bizTranRoleCode,
        String bizTranRoleName,
        String bizTranGrpCode,
        String bizTranGrpName) {

        this.subSystemName = subSystemName;
        this.bizTranRoleCode = bizTranRoleCode;
        this.bizTranRoleName = bizTranRoleName;
        this.bizTranGrpCode = bizTranGrpCode;
        this.bizTranGrpName = bizTranGrpName;
    }

    // ファクトリーメソッド
    public static BizTranRole_BizTranGrpSheet createFrom(
        String subSystemName,
        String bizTranRoleCode,
        String bizTranRoleName,
        String bizTranGrpCode,
        String bizTranGrpName) {

        return new BizTranRole_BizTranGrpSheet(
            subSystemName,
            bizTranRoleCode,
            bizTranRoleName,
            bizTranGrpCode,
            bizTranGrpName);
    }

    // Getter
    public String getSubSystemName() {
        return subSystemName;
    }
    public String getBizTranRoleCode() {
        return bizTranRoleCode;
    }
    public String getBizTranRoleName() {
        return bizTranRoleName;
    }
    public String getBizTranGrpCode() {
        return bizTranGrpCode;
    }
    public String getBizTranGrpName() {
        return bizTranGrpName;
    }
}
