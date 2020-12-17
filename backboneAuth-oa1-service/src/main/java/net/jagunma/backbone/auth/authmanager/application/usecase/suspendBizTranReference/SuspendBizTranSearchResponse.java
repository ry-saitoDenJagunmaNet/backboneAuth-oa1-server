package net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference;

import java.time.LocalDate;

/**
 * 一時取引抑止 検索 Response
 */
public interface SuspendBizTranSearchResponse {

    /**
     * 一時取引抑止IDのＳｅｔ
     *
     * @param suspendBizTranId 一時取引抑止ID
     */
    void setSuspendBizTranId(Long suspendBizTranId);
    /**
     * ＪＡコードのＳｅｔ
     *
     * @param jaCode ＪＡコード
     */
    void setJaCode(String jaCode);
    /**
     * 店舗コードのＳｅｔ
     *
     * @param branchCode 店舗コード
     */
    void setBranchCode(String branchCode);
    /**
     * サブシステムコードのＳｅｔ
     *
     * @param subSystemCode サブシステムコード
     */
    void setSubSystemCode(String subSystemCode);
    /**
     * 取引グループコードのＳｅｔ
     *
     * @param bizTranGrpCode 取引グループコ－ド
     */
    void setBizTranGrpCode(String bizTranGrpCode);
    /**
     * 取引コードのＳｅｔ
     *
     * @param bizTranCode 取引コード
     */
    void setBizTranCode(String bizTranCode);
    /**
     * 抑止期間開始日のＳｅｔ
     *
     * @param suspendStartDate 抑止期間開始日
     */
    void setSuspendStartDate(LocalDate suspendStartDate);
    /**
     * 抑止期間終了日のＳｅｔ
     *
     * @param suspendEndDate 抑止期間終了日
     */
    void setSuspendEndDate(LocalDate suspendEndDate);
    /**
     * 抑止理由のＳｅｔ
     *
     * @param suspendReason 抑止理由
     */
    void setSuspendReason(String suspendReason);
    /**
     * レコードバージョンのＳｅｔ
     *
     * @param recordVersion レコードバージョン
     */
    void setRecordVersion(Integer recordVersion);
}
