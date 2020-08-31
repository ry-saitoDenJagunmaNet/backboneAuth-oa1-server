package net.jagunma.backbone.auth.authmanager.application.service.oa11020;

import net.jagunma.backbone.auth.authmanager.application.queryService.TempoReferenceService;
import net.jagunma.backbone.auth.authmanager.infrastructure.web.oa11020.vo.Oa11020Vo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * OA11020 オペレーター登録 初期表示サービス
 */
@Service
@Transactional
public class Oa11020InitService {
	private final TempoReferenceService tempoReferenceService;

	// コンストラクタ
	public Oa11020InitService(
		TempoReferenceService tempoReferenceService) {

		this.tempoReferenceService = tempoReferenceService;
	}

	/**
	 * Formの初期化処理です。
	 *
	 * @param vo View Object
	 */
	public void init(Oa11020Vo vo) {

		Oa11020InitPresenter presenter = new Oa11020InitPresenter();

		// TODO: サインインオペレーターのJA
		presenter.setJaCode("006");
		presenter.setJaName("JA前橋");
		presenter.setJaId(6);

		// 店舗コンボボックスリスト取得
		presenter.setTempoList(tempoReferenceService.getComboBoxList(6));

		// 識別（オペレーターコードプレフィックス）
		presenter.setOperatorCodePrefix("yu");

		presenter.bindTo(vo);
	}
}
