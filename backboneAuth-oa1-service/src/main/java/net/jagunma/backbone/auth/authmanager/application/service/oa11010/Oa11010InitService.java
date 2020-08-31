package net.jagunma.backbone.auth.authmanager.application.service.oa11010;

import net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.BizTranRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.role.subSystemRole.SubSystemRolesRepository;
import net.jagunma.backbone.auth.authmanager.application.queryService.SubSystemReferenceService;
import net.jagunma.backbone.auth.authmanager.application.queryService.TempoReferenceService;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010Vo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * OA11010 オペレーター＜一覧＞ 初期表示 サービス
 */
@Service
@Transactional
public class Oa11010InitService {
	private final TempoReferenceService tempoReferenceService;
	private final SubSystemReferenceService subSystemReferenceService;
	private final SubSystemRolesRepository subSystemRolesRepository;
	private final BizTranRolesRepository bizTranRolesRepository;

	// コンストラクタ
	public Oa11010InitService(TempoReferenceService tempoReferenceService,
		SubSystemReferenceService subSystemReferenceService,
		SubSystemRolesRepository subSystemRolesRepository,
		BizTranRolesRepository bizTranRolesRepository) {

		this.tempoReferenceService = tempoReferenceService;
		this.subSystemReferenceService = subSystemReferenceService;
		this.subSystemRolesRepository = subSystemRolesRepository;
		this.bizTranRolesRepository = bizTranRolesRepository;
	}

	/**
	 * Formの初期化処理です。
	 *
	 * @param vo オペレーター＜一覧＞View Object
	 */
	public void init(Oa11010Vo vo) {

		Oa11010InitPresenter presenter = new Oa11010InitPresenter();

		// TODO: サインインオペレーターのJA
		presenter.setJaCode("006");
		presenter.setJaName("JA前橋");
		presenter.setJaId(6);

		// 店舗コンボボックスリスト取得
		presenter.setTempoReferenceDtoList(tempoReferenceService.getComboBoxList(6));
		// 有効期限選択
		presenter.setExpirationSelect(0);
		// サブシステムロール初期選択
		presenter.setSubSystemRoleConditionsSelect(1);
		// サブシステムロールリスト取得
		presenter.getSubsystemRoleList(subSystemRolesRepository.selectAll().getValues());
		// 取引ロール初期選択
		presenter.setBiztranRoleConditionsSelect(1);
		// サブシステムコンボボックスリスト取得
		presenter.setBizTranRoleSubSystemList(subSystemReferenceService.getComboBoxList());
		// 取引ロールリスト取得
		presenter.getBizTranRoleList(bizTranRolesRepository.selectAll().getValues());

		presenter.bindTo(vo);

		//throw new GunmaRuntimeException("EOA10002", "最終パスワード変更日の変更有無");
	}
}
