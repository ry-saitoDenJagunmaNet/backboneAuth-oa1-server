package net.jagunma.backbone.auth.oa1.application.service.oa11010;

import java.util.ArrayList;
import java.util.List;
import net.jagunma.backbone.auth.application.queryService.SubSystemReferenceService;
import net.jagunma.backbone.auth.application.queryService.TempoReferenceService;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntityDao;
import net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.Oa11010BiztranRoleDto;
import net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.Oa11010SubsystemRoleDto;
import net.jagunma.backbone.auth.usecase.oa11010.Oa11010InitFormResponse;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Oa11010InitService {
	private TempoReferenceService tempoReferenceService;
	private SubSystemReferenceService subSystemReferenceService;
	private BizTranRoleEntityDao bizTranRoleEntityDao;

	public Oa11010InitService(TempoReferenceService tempoReferenceService,
							  SubSystemReferenceService subSystemReferenceService,
							  BizTranRoleEntityDao bizTranRoleEntityDao) {

		this.tempoReferenceService = tempoReferenceService;
		this.subSystemReferenceService = subSystemReferenceService;
		this.bizTranRoleEntityDao = bizTranRoleEntityDao;
	}

	/**
	 * Formの初期化処理です。
	 * @param response オペレーター
	 */
	public void initForm(Oa11010InitFormResponse response) {
		// todo: サインインオペレータのJA
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
	 * @return
	 */
	private List<Oa11010SubsystemRoleDto> getSubsystemRoleList() {
		// todo: サブシステムロールを取得してリストを作成する
		List<String[]> oplist = new ArrayList<String[]>();
		oplist.add(new String[]{"0000", "JA管理者"});
		oplist.add(new String[]{"1000", "業務統括者（購買）"});
		oplist.add(new String[]{"2110", "業務統括者（販売・野菜）"});
		oplist.add(new String[]{"2120", "業務統括者（販売・花卉）"});
		oplist.add(new String[]{"2210", "業務統括者（販売・米）"});
		oplist.add(new String[]{"2220", "業務統括者（販売・麦）"});
		oplist.add(new String[]{"2300", "業務統括者（販売・畜産）"});

		List<Oa11010SubsystemRoleDto> list = new ArrayList<Oa11010SubsystemRoleDto>();
		oplist.forEach(s -> {
			Oa11010SubsystemRoleDto item = new Oa11010SubsystemRoleDto();
			item.setSubsystemRoleSelected(0);
			item.setSubsystemRoleId(0);
			item.setSubsystemRoleCode(s[0]);
			item.setSubsystemRoleName(s[1]);
			item.setExpirationSelect(0);
			item.setExpirationStatusDate(null);
			item.setExpirationStartDateFrom(null);
			item.setExpirationStartDateTo(null);
			item.setExpirationEndDateFrom(null);
			item.setExpirationEndDateTo(null);

			list.add(item);
		});

		return list;
	}

	/**
	 * 取引ロールリストを取得します
	 * @return
	 */
	private List<Oa11010BiztranRoleDto> getBiztranRoleList() {
		// 取引ロール検索
		Orders orders = Orders.empty()
			.addOrder("BizTranRoleCode")
			.addOrder("SubSystemCode");
		List<BizTranRoleEntity> bizTranRoleList = bizTranRoleEntityDao.findAll(orders);


		// todo: 取引ロールを取得してリストを作成する
		List<String[]> oplist = new ArrayList<String[]>();
		oplist.add(new String[]{"ANAG01", "（畜産）取引全般", "AN"});
		oplist.add(new String[]{"ANAG02", "（畜産）維持管理担当者", "AN"});
		oplist.add(new String[]{"ANAG98", "（畜産）センター維持管理担当者", "AN"});
		oplist.add(new String[]{"ANAG99", "（畜産）維持管理責任者", "AN"});
		oplist.add(new String[]{"HKAG10", "（米）ＪＡ取引全般", "HK"});
		oplist.add(new String[]{"HKAG15", "（米）ＪＡ取引振込以外全般", "HK"});
		oplist.add(new String[]{"HKAG99", "（米）センター維持管理担当者", "HK"});
		oplist.add(new String[]{"HMAG20", "（麦）ＪＡ取引全般", "KM"});
		oplist.add(new String[]{"HMAG25", "（麦）ＪＡ取引振込以外全般", "KM"});
		oplist.add(new String[]{"HMAG99", "（麦）センター維持管理担当者", "KM"});
		oplist.add(new String[]{"KBAG01", "（購買）購買業務基本", "KB"});
		oplist.add(new String[]{"KBAG02", "（購買）本所業務", "KB"});
		oplist.add(new String[]{"KBAG03", "（購買）本所管理業務", "KB"});
		oplist.add(new String[]{"KBAG04", "（購買）バラエサ処理", "KB"});
		oplist.add(new String[]{"KBAG05", "（購買）在庫管理", "KB"});
		oplist.add(new String[]{"KBAG06", "（購買）支払業務", "KB"});
		oplist.add(new String[]{"KBAG07", "（購買）受入業務", "KB"});
		oplist.add(new String[]{"KBAG08", "（購買）受注管理", "KB"});
		oplist.add(new String[]{"KBAG09", "（購買）未収金管理", "KB"});
		oplist.add(new String[]{"KBAG10", "（購買）損害金利息管理", "KB"});
		oplist.add(new String[]{"KBAG11", "（購買）配送管理", "KB"});
		oplist.add(new String[]{"KBAG12", "（購買）LPG担当者", "KB"});
		oplist.add(new String[]{"KBAG13", "（購買）LPG入金", "KB"});
		oplist.add(new String[]{"KBAG14", "（購買）レジ担当者", "KB"});
		oplist.add(new String[]{"KBAG15", "（購買）支所マスタ担当者", "KB"});
		oplist.add(new String[]{"KBAG16", "（購買）食材担当者", "KB"});
		oplist.add(new String[]{"KBAG17", "（購買）購買業務基本２", "KB"});
		oplist.add(new String[]{"KBAG18", "窓口供給（店舗用２）", "KB"});
		oplist.add(new String[]{"KBAG19", "（購買）合併", "KB"});
		oplist.add(new String[]{"KBAG20", "購買業務基本３", "KB"});
		oplist.add(new String[]{"KBAG99", "（購買）電算センター", "KB"});
		oplist.add(new String[]{"YFAG20", "（花卉）精算担当", "YF"});
		oplist.add(new String[]{"YFAG30", "（花卉）集荷場担当", "YF"});
		oplist.add(new String[]{"YFAG40", "（花卉）単価計算担当", "YF"});
		oplist.add(new String[]{"YFAG50", "（花卉）入力担当", "YF"});
		oplist.add(new String[]{"YFAG70", "（花卉）支所担当", "YF"});
		oplist.add(new String[]{"YFAG90", "（花卉）取引全般", "YF"});
		oplist.add(new String[]{"YSAG19", "（青果）管理者（仕切実績修正）", "YS"});
		oplist.add(new String[]{"YSAG20", "（青果）精算担当", "YS"});
		oplist.add(new String[]{"YSAG30", "（青果）集荷場担当", "YS"});
		oplist.add(new String[]{"YSAG31", "（青果）集荷場担当（荷受送り入力）", "YS"});
		oplist.add(new String[]{"YSAG32", "（青果）集荷場担当（データ送信）", "YS"});
		oplist.add(new String[]{"YSAG40", "（青果）単価計算担当", "YS"});
		oplist.add(new String[]{"YSAG50", "（青果）入力担当", "YS"});
		oplist.add(new String[]{"YSAG60", "（青果）安定基金管理者", "YS"});
		oplist.add(new String[]{"YSAG61", "（青果）安定基金担当", "YS"});
		oplist.add(new String[]{"YSAG70", "（青果）支所担当", "YS"});
		oplist.add(new String[]{"YSAG90", "（青果）取引全般", "YS"});

		List<Oa11010BiztranRoleDto> list = new ArrayList<Oa11010BiztranRoleDto>();
		oplist.forEach(s -> {
			Oa11010BiztranRoleDto item = new Oa11010BiztranRoleDto();
			item.setBiztranRoleSelected(0);
			item.setBiztranRoleId(0);
			item.setBiztranRoleCode(s[0]);
			item.setBiztranRoleName(s[1]);
			item.setSubSystemCode(s[2]);
			item.setExpirationSelect(0);
			item.setExpirationStatusDate("");
			item.setExpirationStartDateFrom(null);
			item.setExpirationStartDateTo(null);
			item.setExpirationEndDateFrom(null);
			item.setExpirationEndDateTo(null);
			list.add(item);
		});

		return list;
	}
}
