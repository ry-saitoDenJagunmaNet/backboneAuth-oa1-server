package net.jagunma.backbone.auth.oa1.application.service.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.application.queryService.SubSystemReferenceService;
import net.jagunma.backbone.auth.application.queryService.dto.SubSystemRoleDto;
import net.jagunma.backbone.auth.application.queryService.SubSystemRoleReferenceSetvice;
import net.jagunma.backbone.auth.application.queryService.TempoReferenceService;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityDao;
import net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.vo.Oa11010BiztranRoleVo;
import net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.vo.Oa11010SubsystemRoleVo;
import net.jagunma.backbone.auth.oa1.application.model.oa11010.Oa11010InitFormResponse;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Oa11010InitService {
	private final TempoReferenceService tempoReferenceService;
	private final SubSystemReferenceService subSystemReferenceService;
	private final BizTranRoleEntityDao bizTranRoleEntityDao;
	private final SubSystemRoleReferenceSetvice subSystemRoleReferenceSetvice;

	public Oa11010InitService(TempoReferenceService tempoReferenceService,
		SubSystemReferenceService subSystemReferenceService,
		BizTranRoleEntityDao bizTranRoleEntityDao,
		SubSystemRoleReferenceSetvice subSystemRoleReferenceSetvice) {

		this.tempoReferenceService = tempoReferenceService;
		this.subSystemReferenceService = subSystemReferenceService;
		this.bizTranRoleEntityDao = bizTranRoleEntityDao;
		this.subSystemRoleReferenceSetvice = subSystemRoleReferenceSetvice;
	}

	/**
	 * Formの初期化処理です。
	 * @return Formの初期項目
	 */
	public void initForm(Oa11010InitFormResponse response) {

		// TODO: サインインオペレーターのJA
		response.setJaId(6);
		response.setJaCode("006");
		response.setJaName("JA前橋");

		// 有効期限選択
		response.setExpirationSelect(0);
		// サブシステムロール初期選択
		response.setSubsystemRoleConditionsSelect(1);
		// サブシステムロールリスト取得
		response.setSubsystemRoleList(getSubsystemRoleList());
		// 取引ロール初期選択
		response.setBiztranRoleConditionsSelect(1);
		// 取引ロールリスト取得
		response.setBiztranRoleList(getBiztranRoleList());
		// 店舗コンボボックスリスト取得
		response.setTempoList(tempoReferenceService.getComboBoxList());
		// サブシステムコンボボックスリスト取得
		response.setSubsystemRoleSubsystemList(subSystemReferenceService.getComboBoxList());
	}

	/**
	 * サブシステムロールリストを取得します
	 * @return サブシステムロールリスト
	 */
	private List<Oa11010SubsystemRoleVo> getSubsystemRoleList() {
		// サブシステムロール検索
		List<SubSystemRoleDto> subSystemRoles = subSystemRoleReferenceSetvice.getSubSystemRoleList();

		List<Oa11010SubsystemRoleVo> list = newArrayList();
		for(SubSystemRoleDto subSystemRole : subSystemRoles) {
			Oa11010SubsystemRoleVo item = new Oa11010SubsystemRoleVo();
			item.setSubsystemRoleSelected(0);
			item.setSubsystemRoleId(0);
			item.setSubsystemRoleCode(subSystemRole.getSubSystemRoleCode());
			item.setSubsystemRoleName(subSystemRole.getSubSystemRoleName());
			item.setExpirationSelect(0);
			item.setExpirationStatusDate(null);
			item.setExpirationStartDateFrom(null);
			item.setExpirationStartDateTo(null);
			item.setExpirationEndDateFrom(null);
			item.setExpirationEndDateTo(null);

			list.add(item);
		}
		return list;
	}

	/**
	 * 取引ロールリストを取得します
	 * @return 取引ロールリスト
	 */
	private List<Oa11010BiztranRoleVo> getBiztranRoleList() {
		// 取引ロール検索
		Orders orders = Orders.empty()
			.addOrder("BizTranRoleCode")
			.addOrder("SubSystemCode");
		List<BizTranRoleEntity> bizTranRoles = bizTranRoleEntityDao.findAll(orders);

		List<Oa11010BiztranRoleVo> list = newArrayList();
		for (BizTranRoleEntity bizTranRole :  bizTranRoles) {
			Oa11010BiztranRoleVo item = new Oa11010BiztranRoleVo();
			item.setBiztranRoleSelected(0);
			item.setBiztranRoleId(0);
			item.setBiztranRoleCode(bizTranRole.getBizTranRoleCode());
			item.setBiztranRoleName(bizTranRole.getBizTranRoleName());
			item.setSubSystemCode(bizTranRole.getSubSystemCode());
			item.setExpirationSelect(0);
			item.setExpirationStatusDate("");
			item.setExpirationStartDateFrom(null);
			item.setExpirationStartDateTo(null);
			item.setExpirationEndDateFrom(null);
			item.setExpirationEndDateTo(null);
			list.add(item);
		}
		return list;
	}
}
