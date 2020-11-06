package net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition;

/**
 * 取引ロール－取引グループ編成
 */
public class BizTranRole_BizTranGrpSheet {

    private final Integer rowno;
    private final String subSystemName;
    private final String bizTranRoleCode;
    private final String bizTranRoleName;
    private final String bizTranGrpCode;
    private final String bizTranGrpName;

    // コンストラクタ
    BizTranRole_BizTranGrpSheet(
        Integer rowno,
        String subSystemName,
        String bizTranRoleCode,
        String bizTranRoleName,
        String bizTranGrpCode,
        String bizTranGrpName) {

        this.rowno = rowno;
        this.subSystemName = subSystemName;
        this.bizTranRoleCode = bizTranRoleCode;
        this.bizTranRoleName = bizTranRoleName;
        this.bizTranGrpCode = bizTranGrpCode;
        this.bizTranGrpName = bizTranGrpName;
    }

    // ファクトリーメソッド
    public static BizTranRole_BizTranGrpSheet createFrom(
        Integer rowno,
        String subSystemName,
        String bizTranRoleCode,
        String bizTranRoleName,
        String bizTranGrpCode,
        String bizTranGrpName) {

        return new BizTranRole_BizTranGrpSheet(
            rowno,
            subSystemName,
            bizTranRoleCode,
            bizTranRoleName,
            bizTranGrpCode,
            bizTranGrpName);
    }

    // Getter
    public Integer getRowno() {
        return rowno;
    }
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
