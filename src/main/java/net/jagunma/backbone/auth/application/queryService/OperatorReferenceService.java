package net.jagunma.backbone.auth.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.application.queryService.dto.OperatorDto;
import net.jagunma.backbone.auth.application.queryService.dto.OperatorBizTranRoleDto;
import net.jagunma.backbone.auth.application.queryService.dto.OperatorSubSystemRoleDto;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.auth.usecase.operator.OperatorSearchRequest;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OperatorReferenceService {
	/**
	 * オペレータ一覧の１ページ当たりの行数
	 */
	private final int PAGE_SIZE = 10;

	private final OperatorEntityDao operatorEntityDao;
	private final OperatorSubSystemRoleReferenceService operatorSubSystemRoleReferenceService;
	private final OperatorBizTranRoleReferenceService operatorBizTranRoleReferenceService;

	public OperatorReferenceService(OperatorEntityDao operatorEntityDao,
		OperatorSubSystemRoleReferenceService operatorSubSystemRoleReferenceService,
		OperatorBizTranRoleReferenceService operatorBizTranRoleReferenceService) {

		this.operatorEntityDao = operatorEntityDao;
		this.operatorSubSystemRoleReferenceService = operatorSubSystemRoleReferenceService;
		this.operatorBizTranRoleReferenceService = operatorBizTranRoleReferenceService;
	}

	/**
	 * オペレータリストを取得します。
	 * @param request 条件
	 * @return オペレータリストを
	 */
	public List<OperatorDto> getOperatorList(OperatorSearchRequest request) {

		// オペレータ検索
		Orders orders = Orders.empty()
			.addOrder("TempoCode")
			.addOrder("OperatorCode");
		List<OperatorEntity> operatorEntitys = operatorEntityDao.findBy(request.getOperatorEntityCriteria(request), orders);

		// オペレーター_サブシステムロール割当検索
		List<OperatorSubSystemRoleDto> operatorSubSystemRoles = operatorSubSystemRoleReferenceService.getOperatorSubSystemRoleList(request);
		// オペレーター_取引ロール割当リスト
		List<OperatorBizTranRoleDto> operatorBizTranRoles = operatorBizTranRoleReferenceService.getOperatorBizTranRoleList(request);


		List<OperatorDto> operatorList = newArrayList();

		operatorEntitys.forEach(o -> {
			OperatorDto entity = new OperatorDto();
			// オペレーターID
			entity.setOperatorId(o.getOperatorId());
			// オペレーターコード
			entity.setOperatorCode(o.getOperatorCode());
			// オペレーター名
			entity.setOperatorName(o.getOperatorName());
			// 有効期限開始日
			entity.setExpirationStartDate(o.getExpirationStartDate());
			// 有効期限終了日
			entity.setExpirationEndDate(o.getExpirationEndDate());
			// JAID
			entity.setJaId(o.getJaId());
			// JAコード
			entity.setJaCode(o.getJaCode());
			// 店舗ID
			entity.setTempoId(o.getTempoId());
			// 店舗コード
			entity.setTempoCode(o.getTempoCode());
			// 利用可否状態
			entity.setAvailableStatus(o.getAvailableStatus());
			// 店舗名
			//entity.setTempoName("");
			// ロック状態
			//entity.setLockStatus(0);

			// オペレーター_サブシステムロール割当リスト
			entity.setOperatorSubSystemRoleList(
				operatorSubSystemRoles.stream().filter(s->s.getOperatorId() == o.getOperatorId()).collect(Collectors.toList()));
			// オペレーター_取引ロール割当リスト
			entity.setOperatorBizTranRoleList(
				operatorBizTranRoles.stream().filter(s->s.getOperatorId() == o.getOperatorId()).collect(Collectors.toList()));

			operatorList.add(entity);
		});
		return operatorList;
	}

	/**
	 * オペレータ一覧の最終ページを取得します、
	 * @param list オペレーターリスト
	 * @return オペレータ一覧の最終ページ
	 */
	public int getMaxPage(List<OperatorDto> list) {
		return (int) Math.ceil((double)list.size() / PAGE_SIZE);
	}

	/**
	 * 該当ページのオペレータ一覧を取得します。
	 * @param list オペレーターリスト
	 * @param pageNo 対象ページ
	 * @return 該当ページのオペレータ一覧
	 */
	public List<OperatorDto> getPageList(List<OperatorDto> list, int pageNo) {
		int skip = pageNo * PAGE_SIZE - PAGE_SIZE;
		return list.stream().skip(skip).limit(10).collect(Collectors.toList());
	}
}
