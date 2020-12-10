package net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference;

import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * 一時取引抑止群検索サービス Request
 */
public interface SuspendBizTranSearchRequest {

    /**
     * 一時取引抑止ID検索条件のＧｅｔ
     *
     * @return 一時取引抑止ID検索条件
     */
    LongCriteria getSuspendBizTranIdCriteria();
}
