package net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition;

import java.time.LocalDate;

/**
 * 取引グループ－取引編成
 */
public class BizTranGrp_BizTranSheet {

    private final Integer rowno;
    private final String subSystemName;
    private final String bizTranGrpCode;
    private final String bizTranGrpName;
    private final String bizTranCode;
    private final String bizTranName;
    private final Boolean isCenterBizTran;
    private final LocalDate validThruStartDate;
    private final LocalDate validThruEndDate;

    // コンストラクタ
    BizTranGrp_BizTranSheet(
        Integer rowno,
        String subSystemName,
        String bizTranGrpCode,
        String bizTranGrpName,
        String bizTranCode,
        String bizTranName,
        Boolean isCenterBizTran,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate) {

        this.rowno = rowno;
        this.subSystemName = subSystemName;
        this.bizTranGrpCode = bizTranGrpCode;
        this.bizTranGrpName = bizTranGrpName;
        this.bizTranCode = bizTranCode;
        this.bizTranName = bizTranName;
        this.isCenterBizTran = isCenterBizTran;
        this.validThruStartDate = validThruStartDate;
        this.validThruEndDate = validThruEndDate;
    }

    // ファクトリーメソッド
    public static BizTranGrp_BizTranSheet createFrom(
        Integer rowno,
        String subSystemName,
        String bizTranGrpCode,
        String bizTranGrpName,
        String bizTranCode,
        String bizTranName,
        Boolean isCenterBizTran,
        LocalDate validThruStartDate,
        LocalDate validThruEndDate) {

        return new BizTranGrp_BizTranSheet(
            rowno,
            subSystemName,
            bizTranGrpCode,
            bizTranGrpName,
            bizTranCode,
            bizTranName,
            isCenterBizTran,
            validThruStartDate,
            validThruEndDate);
    }

    // Getter
    public Integer getRowno() {
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
    public LocalDate getValidThruStartDate() {
        return validThruStartDate;
    }
    public LocalDate getValidThruEndDate() {
        return validThruEndDate;
    }
}
