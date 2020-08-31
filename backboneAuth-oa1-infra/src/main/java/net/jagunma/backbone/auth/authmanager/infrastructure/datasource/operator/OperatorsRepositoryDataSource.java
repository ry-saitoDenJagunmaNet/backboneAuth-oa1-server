package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.operator;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.OperatorsRepository;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * オペレーター検索 DataSource
 */
@Component
public class OperatorsRepositoryDataSource implements OperatorsRepository {

	private final OperatorEntityDao operatorEntityDao;
	private final Orders defaultOrders = Orders.empty().addOrder("operatorCode");

	// コンストラクタ
	OperatorsRepositoryDataSource(OperatorEntityDao operatorEntityDao) {
		this.operatorEntityDao = operatorEntityDao;
	}

	/**
	 * オペレーターの条件検索を行います。
	 *
	 * @param operatorCriteria オペレーターの検索条件
	 * @param orders オーダー指定
	 * @return オペレーター群
	 */
	@Override
	public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
		List<OperatorEntity> list = operatorEntityDao.findBy(operatorCriteria, orders);
		return Operators.createFrom(list);
	}
	/**
	 * オペレーターの条件検索を行います。
	 *
	 * @param operatorCriteria オペレーターの検索条件
	 * @return オペレーター群
	 */
	@Override
	public Operators selectBy(OperatorCriteria operatorCriteria) {
		return selectBy(operatorCriteria, defaultOrders);
	}

	/**
	 * オペレーターの全件検索を行います。
	 *
	 * @param orders オーダー指定
	 * @return オペレーター群
	 */
	@Override
	public Operators selectAll(Orders orders) {
		return Operators.createFrom(operatorEntityDao.findAll(orders));
	}
	/**
	 * オペレーターの全件検索を行います。
	 *
	 * @return オペレーター群
	 */
	@Override
	public Operators selectAll() {
		return selectAll(defaultOrders);
	}
}
