package net.jagunma.backbone.auth.application.queryService;

import java.util.ArrayList;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityDao;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntityDao;
import net.jagunma.backbone.auth.usecase.operator.OperatorSearchRequest;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OperatorBizTranRoleReferenceService {
	private Operator_BizTranRoleEntityDao operator_BizTranRoleEntityDao;
	private BizTranRoleEntityDao bizTranRoleEntityDao;

	public OperatorBizTranRoleReferenceService(Operator_BizTranRoleEntityDao operator_BizTranRoleEntityDao,
		BizTranRoleEntityDao bizTranRoleEntityDao) {

		this.operator_BizTranRoleEntityDao = operator_BizTranRoleEntityDao;
		this.bizTranRoleEntityDao = bizTranRoleEntityDao;
	}

	/**
	 * オペレーター_取引ロール割当リストを取得します。
	 * @param request 検索条件
	 * @return オペレーター_取引ロール割当リスト
	 */
	public List<OperatorBizTranRole> getOperatorBizTranRoleList(OperatorSearchRequest request) {

		// オペレーター_取引ロール割当検索
		Orders orders = Orders.empty()
			.addOrder("OperatorId")
			.addOrder("BizTranRoleId")
			.addOrder("ExpirationStartDate");
		List<Operator_BizTranRoleEntity> entitys = operator_BizTranRoleEntityDao.findAll(orders);

		// 取引ロール検索
		orders = Orders.empty()
			.addOrder("BizTranRoleCode");
		List<BizTranRoleEntity> bTREntitys = bizTranRoleEntityDao.findAll(orders);

		List<OperatorBizTranRole> list = new ArrayList<OperatorBizTranRole>();
		entitys.forEach(o -> {
			OperatorBizTranRole item = new OperatorBizTranRole();

			item.setOperator_BizTranRoleId(o.getOperator_BizTranRoleId());
			item.setOperatorId(o.getOperatorId());
			item.setBizTranRoleId(o.getBizTranRoleId());
			item.setExpirationStartDate(o.getExpirationStartDate());
			item.setExpirationEndDate(o.getExpirationEndDate());
			item.setCreatedBy(o.getCreatedBy());
			item.setCreatedAt(o.getCreatedAt());
			item.setCreatedIpAddress(o.getCreatedIpAddress());
			item.setUpdatedBy(o.getUpdatedBy());
			item.setUpdatedAt(o.getUpdatedAt());
			item.setUpdatedIpAddress(o.getUpdatedIpAddress());
			item.setRecordVersion(o.getRecordVersion());
			bTREntitys.stream().filter(s->s.getBizTranRoleId() == o.getBizTranRoleId()).forEach(s ->  {
				item.setBizTranRoleCode(s.getBizTranRoleCode());
				item.setBizTranRoleName(s.getBizTranRoleName());
				item.setSubSystemCode(s.getSubSystemCode());
			});
			list.add(item);
		});

		return list;
	}
}
