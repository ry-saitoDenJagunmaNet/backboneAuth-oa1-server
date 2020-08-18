package net.jagunma.backbone.auth.authmanager.application.model.domain.suspendBizTran;

/**
 * 一時取引抑止検索
 */
public interface SuspendBizTransRepository {
	/**
	 * 一時取引抑止の条件検索を行います。
	 *
	 * @param suspendBizTranCriteria 一時取引抑止の検索条件
	 * @return 一時取引抑止群
	 */
	SuspendBizTrans selectBy(SuspendBizTranCriteria suspendBizTranCriteria);
	/**
	 * 一時取引抑止の全件検索を行います。
	 *
	 * @return 一時取引抑止群
	 */
	SuspendBizTrans selectAll();
}
