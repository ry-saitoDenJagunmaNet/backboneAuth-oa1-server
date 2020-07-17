package net.jagunma.backbone.auth.application.queryService;

import java.util.ArrayList;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntity;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntityDao;
import net.jagunma.backbone.auth.usecase.operator.OperatorSearchRequest;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OperatorSubSystemRoleReferenceService {
	private Operator_SubSystemRoleEntityDao operator_SubSystemRoleEntityDao;
	private SubSystemRoleReferenceSetvice subSystemRoleReferenceSetvice;

	public OperatorSubSystemRoleReferenceService(Operator_SubSystemRoleEntityDao operator_SubSystemRoleEntityDao,
		SubSystemRoleReferenceSetvice subSystemRoleReferenceSetvice) {
		this.operator_SubSystemRoleEntityDao = operator_SubSystemRoleEntityDao;
		this.subSystemRoleReferenceSetvice = subSystemRoleReferenceSetvice;
	}

	/**
	 * オペレーター_サブシステムロール割当リストを取得します。
	 * @param request 検索条件
	 * @return オペレーター_サブシステムロール割当リスト
	 */
	public List<OperatorSubSystemRole> getOperatorSubSystemRoleList(OperatorSearchRequest request) {

		// オペレーター_サブシステムロール割当検索
		Orders orders = Orders.empty()
			.addOrder("OperatorId")
			.addOrder("SubSystemRoleCode")
			.addOrder("ExpirationStartDate");
		List<Operator_SubSystemRoleEntity> entitys = operator_SubSystemRoleEntityDao.findAll(orders);

		// サブシステムロール検索
		List<SubSystemRole> subSystemRoles = subSystemRoleReferenceSetvice.getSubSystemRoleList();

		List<OperatorSubSystemRole> list = new ArrayList<OperatorSubSystemRole>();
		entitys.forEach(o -> {
			OperatorSubSystemRole item = new OperatorSubSystemRole();

			item.setOperator_SubSystemRoleId(o.getOperator_SubSystemRoleId());
			item.setOperatorId(o.getOperatorId());
			item.setSubSystemRoleCode(o.getSubSystemRoleCode());
			item.setExpirationStartDate(o.getExpirationStartDate());
			item.setExpirationEndDate(o.getExpirationEndDate());
			item.setCreatedBy(o.getCreatedBy());
			item.setCreatedAt(o.getCreatedAt());
			item.setCreatedIpAddress(o.getCreatedIpAddress());
			item.setUpdatedBy(o.getUpdatedBy());
			item.setUpdatedAt(o.getUpdatedAt());
			item.setUpdatedIpAddress(o.getUpdatedIpAddress());
			item.setRecordVersion(o.getRecordVersion());
			subSystemRoles.stream().filter(s->s.getSubSystemRoleCode().equals(o.getSubSystemRoleCode())).forEach(s ->  {
				item.setSubSystemRoleName(s.getSubSystemRoleName());
			});
			list.add(item);
		});

		return list;
	}
}
