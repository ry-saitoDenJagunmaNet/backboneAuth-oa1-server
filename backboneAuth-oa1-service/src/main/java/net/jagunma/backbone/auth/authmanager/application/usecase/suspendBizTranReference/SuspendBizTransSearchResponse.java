package net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;

/**
 * 一時取引抑止群 検索 Response
 */
public interface SuspendBizTransSearchResponse {

    /**
     * ＪＡコ－ドのＳｅｔ
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
     * 抑止期間条件選択のＳｅｔ
     *
     * @param suspendConditionsSelect 抑止期間条件選択
     */
    void setSuspendConditionsSelect(Integer suspendConditionsSelect);
    /**
     * 抑止期間 状態指定日のＳｅｔ
     *
     * @param suspendStatusDate 抑止期間 状態指定日
     */
    void setSuspendStatusDate(LocalDate suspendStatusDate);
    /**
     * 抑止期間 条件指定開始（開始日）のＳｅｔ
     *
     * @param suspendStatusStartDateFrom 抑止期間 条件指定開始（開始日）
     */
    void setSuspendStatusStartDateFrom(LocalDate suspendStatusStartDateFrom);
    /**
     * 抑止期間 条件指定開始（終了日）のＳｅｔ
     *
     * @param suspendStatusStartDateTo 抑止期間 条件指定開始（終了日）
     */
    void setSuspendStatusStartDateTo(LocalDate suspendStatusStartDateTo);
    /**
     * 抑止期間 条件指定終了（開始日）のＳｅｔ
     *
     * @param suspendStatusEndDateFrom 抑止期間 条件指定終了（開始日）
     */
    void setSuspendStatusEndDateFrom(LocalDate suspendStatusEndDateFrom);
    /**
     * 抑止期間 条件指定終了（終了日）のＳｅｔ
     *
     * @param suspendStatusEndDateTo 抑止期間 条件指定終了（終了日）
     */
    void setSuspendStatusEndDateTo(LocalDate suspendStatusEndDateTo);
    /**
     * 抑止理由のＳｅｔ
     *
     * @param suspendReason 抑止理由
     */
    void setSuspendReason(String suspendReason);
    /**
     * 一時取引抑止一覧表示ページのＳｅｔ
     *
     * @param pageNo 一時取引抑止一覧表示ページ
     */
    void setPageNo(Integer pageNo);
    /**
     * 一時取引抑止群のＳｅｔ
     *
     * @param suspendBizTrans 一時取引抑止群
     */
    void setSuspendBizTrans(SuspendBizTrans suspendBizTrans);
}
