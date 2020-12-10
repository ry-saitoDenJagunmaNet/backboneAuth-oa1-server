package net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand;

import java.time.LocalDate;

/**
 * 一時取引抑止更新サービス Request
 */
public interface SuspendBizTranUpdateRequest {

    /**
     * 一時取引抑止IDのＧｅｔ
     *
     * @return 一時取引抑止ID
     */
    Long getSuspendBizTranId();
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
     * レコードバージョンのＧｅｔ
     *
     * @return レコードバージョン
     */
    Integer getRecordVersion();
}
