package net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand;

import java.time.LocalDate;

/**
 * 一時取引抑止削除サービス Request
 */
public interface SuspendBizTranDeleteRequest {

    /**
     * 一時取引抑止IDのＧｅｔ
     *
     * @return 一時取引抑止ID
     */
    Long getSuspendBizTranId();
    /**
     * レコードバージョンのＧｅｔ
     *
     * @return レコードバージョン
     */
    Integer getRecordVersion();
}
