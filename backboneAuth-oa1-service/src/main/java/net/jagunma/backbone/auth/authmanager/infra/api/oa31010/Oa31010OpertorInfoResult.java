package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import net.jagunma.common.values.model.operator.SimpleOperator;

/**
 * Oa31010 オペレーター情報 Result
 */
public class Oa31010OpertorInfoResult {

    public Long jaId;
    public String jaCode;
    public String jaName;
    public Long branchId;
    public String branchCode;
    public String branchName;
    public Long operatorId;
    public String operatorCode;
    public String operatorName;

    /**
     * ＪＡIDのＧｅｔ
     * @return ＪＡID
     */
    public Long getJaId() {
        return jaId;
    }
    /**
     * ＪＡIDのＳｅｔ
     * @param jaId ＪＡID
     */
    public void setJaId(Long jaId) {
        this.jaId = jaId;
    }
    /**
     * ＪＡコードのＧｅｔ
     * @return ＪＡコード
     */
    public String getJaCode() {
        return jaCode;
    }
    /**
     * ＪＡコードのＳｅｔ
     * @param jaCode　ＪＡコード
     */
    public void setJaCode(String jaCode) {
        this.jaCode = jaCode;
    }
    /**
     * ＪＡ名のＧｅｔ
     * @return ＪＡ名
     */
    public String getJaName() {
        return jaName;
    }
    /**
     * ＪＡ名のＳｅｔ
     * @param jaName ＪＡ名
     */
    public void setJaName(String jaName) {
        this.jaName = jaName;
    }
    /**
     * 店舗IDのＧｅｔ
     * @return 店舗ID
     */
    public Long getBranchId() {
        return branchId;
    }
    /**
     * 店舗IDのＳｅｔ
     * @param branchId 店舗ID
     */
    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
    /**
     * 店舗コードのＧｅｔ
     * @return 店舗コード
     */
    public String getBranchCode() {
        return branchCode;
    }
    /**
     * 店舗コードのＳｅｔ
     * @param branchCode 店舗コード
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    /**
     * 店舗名のＧｅｔ
     * @return 店舗名
     */
    public String getBranchName() {
        return branchName;
    }
    /**
     * 店舗名のＳｅｔ
     * @param branchName 店舗名
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    /**
     * オペレーターIDのＧｅｔ
     * @return オペレーターID
     */
    public Long getOperatorId() {
        return operatorId;
    }
    /**
     * オペレーターIDのＳｅｔ
     * @param operatorId オペレーターID
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
    /**
     * オペレーターコードのＧｅｔ
     * @return オペレーターコード
     */
    public String getOperatorCode() {
        return operatorCode;
    }
    /**
     * オペレーターコードのＳｅｔ
     * @param operatorCode オペレーターコード
     */
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }
    /**
     * オペレーター名のＧｅｔ
     * @return オペレーター名
     */
    public String getOperatorName() {
        return operatorName;
    }
    /**
     * オペレーター名のＳｅｔ
     * @param operatorName オペレーター名
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
