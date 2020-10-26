package net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition;

import java.time.LocalDate;

/**
 * 取引グループ－取引編成
 */
public class BizTranGrp_BizTranSheet {

    private final int rowno;
    private final String subSystemName;
    private final String bizTranGrpCode;
    private final String bizTranGrpName;
    private final String bizTranCode;
    private final String bizTranName;
    private final Boolean isCenterBizTran;
    private final LocalDate expirationStartDate;
    private final LocalDate expirationEndDate;

    // コンストラクタ
    BizTranGrp_BizTranSheet(
        int rowno,
        String subSystemName,
        String bizTranGrpCode,
        String bizTranGrpName,
        String bizTranCode,
        String bizTranName,
        Boolean isCenterBizTran,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate) {

        this.rowno = rowno;
        this.subSystemName = subSystemName;
        this.bizTranGrpCode = bizTranGrpCode;
        this.bizTranGrpName = bizTranGrpName;
        this.bizTranCode = bizTranCode;
        this.bizTranName = bizTranName;
        this.isCenterBizTran = isCenterBizTran;
        this.expirationStartDate = expirationStartDate;
        this.expirationEndDate = expirationEndDate;
    }

    // ファクトリーメソッド
    public static BizTranGrp_BizTranSheet createFrom(
        int rowno,
        String subSystemName,
        String bizTranGrpCode,
        String bizTranGrpName,
        String bizTranCode,
        String bizTranName,
        Boolean isCenterBizTran,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate) {

        return new BizTranGrp_BizTranSheet(
            rowno,
            subSystemName,
            bizTranGrpCode,
            bizTranGrpName,
            bizTranCode,
            bizTranName,
            isCenterBizTran,
            expirationStartDate,
            expirationEndDate);
    }

    // Getter
    public int getRowno() {
        return rowno;
    }
    public String getSubSystemName() {
        return subSystemName;
    }
    public String getBizTranGrpCode() {
        return bizTranGrpCode;
    }
    public String getBizTranGrpName() {
        return bizTranGrpName;
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
}
