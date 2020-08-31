package net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran;

/**
 * 一時取引抑止検索
 */
public interface SuspendBizTranRepository {
	/**
	 * 一時取引抑止の条件検索を行います。
	 *
	 * @param suspendBizTranCriteria 一時取引抑止の検索条件
	 * @return 一時取引抑止
	 */
	SuspendBizTran findOneBy(SuspendBizTranCriteria suspendBizTranCriteria);
}
