package net.jagunma.backbone.auth.authmanager.infra.web.oa12030;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12030.vo.Oa12030Vo;

/**
 * OA12030 一時取引抑止メンテナンス Response Presenter
 */
class Oa12030Presenter implements SuspendBizTranSearchResponse {

    private Long suspendBizTranId;
    private String jaCode;
    private String branchCode;
    private String subSystemCode;
    private String bizTranGrpCode;
    private String bizTranCode;
    private LocalDate suspendStartDate;
    private LocalDate suspendEndDate;
    private String suspendReason;
    private Integer recordVersion;

    // コンストラクタ
    Oa12030Presenter() {}

    /**
     * 一時取引抑止IDのＳｅｔ
     *
     * @param suspendBizTranId 一時取引抑止ID
     */
    public void setSuspendBizTranId(Long suspendBizTranId) {
        this.suspendBizTranId = suspendBizTranId;
    }
    /**
     * ＪＡコードのＳｅｔ
     *
     * @param jaCode ＪＡコード
     */
    public void setJaCode(String jaCode) {
        this.jaCode = jaCode;
    }
    /**
     * 店舗コードのＳｅｔ
     *
     * @param branchCode 店舗コード
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    /**
     * サブシステムコードのＳｅｔ
     *
     * @param subSystemCode サブシステムコード
     */
    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode;
    }
    /**
     * 取引グループコードのＳｅｔ
     *
     * @param bizTranGrpCode 取引グループコード
     */
    public void setBizTranGrpCode(String bizTranGrpCode) {
        this.bizTranGrpCode = bizTranGrpCode;
    }
    /**
     * 取引コードのＳｅｔ
     *
     * @param bizTranCode 取引コード
     */
    public void setBizTranCode(String bizTranCode) {
        this.bizTranCode = bizTranCode;
    }
    /**
     * 抑止期間開始日のＳｅｔ
     *
     * @param suspendStartDate 抑止期間開始日
     */
    public void setSuspendStartDate(LocalDate suspendStartDate) {
        this.suspendStartDate = suspendStartDate;
    }
    /**
     * 抑止期間終了日のＳｅｔ
     *
     * @param suspendEndDate 抑止期間終了日
     */
    public void setSuspendEndDate(LocalDate suspendEndDate) {
        this.suspendEndDate = suspendEndDate;
    }
    /**
     * 抑止理由のＳｅｔ
     *
     * @param suspendReason 抑止理由
     */
    public void setSuspendReason(String suspendReason) {
        this.suspendReason = suspendReason;
    }
    /**
     * レコードバージョンのＳｅｔ
     *
     * @param recordVersion レコードバージョン
     */
    public void setRecordVersion(Integer recordVersion) {
        this.recordVersion = recordVersion;
    }

    /**
     * voに変換します
     *
     * @param vo 一時取引抑止メンテナンスView Object
     */
    public void bindTo(Oa12030Vo vo) {

        // 一時取引抑止ID
        vo.setSuspendBizTranId(suspendBizTranId);
        // JAコード
        vo.setJaCode(jaCode);
        // 店舗コード
        vo.setBranchCode(branchCode);
        // サブシステムコード
        vo.setSubSystemCode(subSystemCode);
        // 取引グループコード
        vo.setBizTranGrpCode(bizTranGrpCode);
        // 取引コード
        vo.setBizTranCode(bizTranCode);
        // 抑止期間開始日
        vo.setSuspendStartDate(suspendStartDate);
        // 抑止期間終了日
        vo.setSuspendEndDate(suspendEndDate);
        // 抑止理由
        vo.setSuspendReason(suspendReason);
        // レコードバージョン
        vo.setRecordVersion(recordVersion);
    }
}
