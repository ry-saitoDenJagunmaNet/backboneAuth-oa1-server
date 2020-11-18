package net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;

/**
 * 一時取引抑止<一覧> 検索 Response
 */
public interface SuspendBizTranSearchResponse {

    /**
     * ＪＡIDのＳｅｔ
     *
     * @param jaId ＪＡID
     */
    void setJaId(Long jaId);
//    /**
//     * JasAtMomentのＳｅｔ
//     *
//     * @param jasAtMoment JasAtMoment
//     */
//    void setJasAtMoment(JasAtMoment jasAtMoment);
    /**
     * 店舗IDのＳｅｔ
     *
     * @param branchId 店舗ID
     */
    void setBranchId(Long branchId);
//    /**
//     * BranchesAtMomentのＳｅｔ
//     *
//     * @param branchesAtMoment BranchesAtMoment
//     */
//    void setBranchesAtMoment(BranchesAtMoment branchesAtMoment);
    /**
     * サブシステムコードのＳｅｔ
     *
     * @param subSystemCode サブシステムコード
     */
    void setSubSystemCode(String subSystemCode);
    /**
     * 取引グループIDのＳｅｔ
     *
     * @param bizTranGrpId 取引グループID
     */
    void setBizTranGrpId(Long bizTranGrpId);
//    /**
//     * 取引グループ群のＳｅｔ
//     *
//     * @param bizTranGrps 取引グループ群
//     */
//    void setBizTranGrps(BizTranGrps bizTranGrps);
    /**
     * 取引IDのＳｅｔ
     *
     * @param bizTranId 取引ID
     */
    void setBizTranId(Long bizTranId);
//    /**
//     * 取引群のＳｅｔ
//     *
//     * @param bizTrans 取引群
//     */
//    void setBizTrans(BizTrans bizTrans);
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
     * 一時取引抑止群のＳｅｔ
     *
     * @param suspendBizTrans 一時取引抑止群
     */
    void setSuspendBizTrans(SuspendBizTrans suspendBizTrans);

    /**
     * 一時取引抑止一覧表示ページのＳｅｔ
     *
     * @param pageNo 一時取引抑止一覧表示ページ
     */
    void setPageNo(Integer pageNo);
}
