package net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran;

/**
 * 一時取引抑止格納
 */
public interface SuspendBizTranRepositoryForStore {

    /**
     * 一時取引抑止の追加を行います
     *
     * @param suspendBizTran 一時取引抑止
     * @return 一時取引抑止
     */
    SuspendBizTran insert(SuspendBizTran suspendBizTran);
    /**
     * 一時取引抑止を更新を行います
     *
     * @param suspendBizTran 一時取引抑止
     * @return 一時取引抑止
     */
    SuspendBizTran update(SuspendBizTran suspendBizTran);
    /**
     * 一時取引抑止を削除を行います
     *
     * @param suspendBizTran 一時取引抑止
     */
    void delete(SuspendBizTran suspendBizTran);
}
