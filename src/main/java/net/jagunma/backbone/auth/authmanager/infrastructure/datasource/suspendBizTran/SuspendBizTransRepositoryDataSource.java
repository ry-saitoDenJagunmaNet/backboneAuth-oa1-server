package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.suspendBizTran;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.suspendBizTran.SuspendBizTrans;
import net.jagunma.backbone.auth.authmanager.application.model.domain.suspendBizTran.SuspendBizTransRepository;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntity;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * 一時取引抑止検索 DataSource
 */
@Component
public class SuspendBizTransRepositoryDataSource implements SuspendBizTransRepository {

	private final SuspendBizTranEntityDao suspendBizTranEntityDao;
	private final Orders defaultOrders = Orders.empty().addOrder("suspendBizTranId");

	// コンストラクタ
	SuspendBizTransRepositoryDataSource(SuspendBizTranEntityDao suspendBizTranEntityDao) {
		this.suspendBizTranEntityDao = suspendBizTranEntityDao;
	}

	/**
	 * 一時取引抑止の条件検索を行います。
	 *
	 * @param suspendBizTranCriteria 一時取引抑止の検索条件
	 * @param orders オーダー指定
	 * @return 一時取引抑止群
	 */
	@Override
	public SuspendBizTrans selectBy(SuspendBizTranCriteria suspendBizTranCriteria, Orders orders) {
		List<SuspendBizTranEntity> list = suspendBizTranEntityDao.findBy(suspendBizTranCriteria, orders);
		return SuspendBizTrans.createFrom(list);
	}
	/**
	 * 一時取引抑止の条件検索を行います。
	 *
	 * @param suspendBizTranCriteria 一時取引抑止の検索条件
	 * @return 一時取引抑止群
	 */
	@Override
	public SuspendBizTrans selectBy(SuspendBizTranCriteria suspendBizTranCriteria) {
		return selectBy(suspendBizTranCriteria, defaultOrders);
	}

	/**
	 * 一時取引抑止の全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return 一時取引抑止群
	 */
	@Override
	public SuspendBizTrans selectAll(Orders orders) {
		return SuspendBizTrans.createFrom(suspendBizTranEntityDao.findAll(orders));
	}
	/**
	 * 一時取引抑止の全件検索を行います。
	 *
	 * @return 一時取引抑止群
	 */
	@Override
	public SuspendBizTrans selectAll() {
		return selectAll(defaultOrders);
	}
}
