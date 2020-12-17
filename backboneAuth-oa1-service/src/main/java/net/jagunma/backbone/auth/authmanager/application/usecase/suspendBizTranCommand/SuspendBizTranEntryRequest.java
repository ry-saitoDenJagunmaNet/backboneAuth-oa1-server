package net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand;

import java.time.LocalDate;

/**
 * 一時取引抑止登録サービス Request
 */
public interface SuspendBizTranEntryRequest {

    /**
     * JAコードのＧｅｔ
     *
     * @return JAコード
     */
    String getJaCode();
    /**
     * 店舗コードのＧｅｔ
     *
     * @return 店舗コード
     */
    String getBranchCode();
    /**
     * サブシステムコードのＧｅｔ
     *
     * @return サブシステムコード
     */
    String getSubSystemCode();
    /**
     * 取引グループコードのＧｅｔ
     *
     * @return 取引グループコード
     */
    String getBizTranGrpCode();
    /**
     * 取引コードのＧｅｔ
     *
     * @return 取引コード
     */
    String getBizTranCode();
    /**
     * 抑止期間開始日のＧｅｔ
     *
     * @return 抑止期間開始日
     */
    LocalDate getSuspendStartDate();
    /**
     * 抑止期間終了日のＧｅｔ
     *
     * @return 抑止期間終了日
     */
    LocalDate getSuspendEndDate();
    /**
     * 抑止理由のＧｅｔ
     *
     * @return 抑止理由
     */
    String getSuspendReason();
}
