package net.jagunma.backbone.auth.authmanager.application.service.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.SubSystemReferenceService;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleReferenceDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.SubSystemRoleReferenceSetvice;
import net.jagunma.backbone.auth.authmanager.application.queryService.TempoReferenceService;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityDao;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010BizTranRoleVo;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010SubSystemRoleVo;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.Oa11010InitResponse;
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
	 */
	public void init(Oa11010InitResponse response) {

		// TODO: サインインオペレーターのJA
		response.setJaCode("006");
		response.setJaName("JA前橋");
		response.setJaId(6);

		// 店舗コンボボックスリスト取得
		response.setTempoReferenceDtoList(tempoReferenceService.getComboBoxList(6));
		// 有効期限選択
		response.setExpirationSelect(0);
		// サブシステムロール初期選択
		response.setSubSystemRoleConditionsSelect(1);
		// サブシステムロールリスト取得
		response.setSubsystemRoleList(getSubsystemRoleList());
		// 取引ロール初期選択
		response.setBiztranRoleConditionsSelect(1);
		// 取引ロールリスト取得
		response.setBizTranRoleList(getBizTranRoleList());
		// サブシステムコンボボックスリスト取得
		response.setBizTranRoleSubSystemList(subSystemReferenceService.getComboBoxList());
	}

	/**
	 * サブシステムロールリストを取得します
	 * @return サブシステムロールリスト
	 */
	private List<Oa11010SubSystemRoleVo> getSubsystemRoleList() {
		// サブシステムロール検索
		List<SubSystemRoleReferenceDto> subSystemRoles = subSystemRoleReferenceSetvice.getSubSystemRoleList();

		List<Oa11010SubSystemRoleVo> list = newArrayList();
		for(SubSystemRoleReferenceDto subSystemRole : subSystemRoles) {
			Oa11010SubSystemRoleVo item = new Oa11010SubSystemRoleVo();
			item.setSubSystemRoleSelected((short) 0);
			item.setSubSystemRoleId(0);
			item.setSubSystemRoleCode(subSystemRole.getSubSystemRoleCode());
			item.setSubSystemRoleName(subSystemRole.getSubSystemRoleName());
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
	private List<Oa11010BizTranRoleVo> getBizTranRoleList() {
		// 取引ロール検索
		Orders orders = Orders.empty()
			.addOrder("BizTranRoleCode")
			.addOrder("SubSystemCode");
		List<BizTranRoleEntity> bizTranRoles = bizTranRoleEntityDao.findAll(orders);

		List<Oa11010BizTranRoleVo> list = newArrayList();
		for (BizTranRoleEntity bizTranRole :  bizTranRoles) {
			Oa11010BizTranRoleVo item = new Oa11010BizTranRoleVo();
			item.setBizTranRoleSelected((short) 0);
			item.setBizTranRoleId(0);
			item.setBizTranRoleCode(bizTranRole.getBizTranRoleCode());
			item.setBizTranRoleName(bizTranRole.getBizTranRoleName());
			item.setSubSystemCode(bizTranRole.getSubSystemCode());
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
}
