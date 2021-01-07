package net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference;

/**
 * 一時取引抑止検索サービス Request
 */
public interface SuspendBizTranSearchRequest {

    /**
     * 一時取引抑止IDのＧｅｔ
     *
     * @return 一時取引抑止ID
     */
    Long getSuspendBizTranId();
}
