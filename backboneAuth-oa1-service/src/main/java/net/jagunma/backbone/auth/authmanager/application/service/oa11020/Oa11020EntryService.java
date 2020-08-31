package net.jagunma.backbone.auth.authmanager.application.service.oa11020;

import net.jagunma.backbone.auth.authmanager.application.commandService.OperatorCommandService;
import net.jagunma.backbone.auth.authmanager.infrastructure.web.oa11020.vo.Oa11020Vo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * OA11020 オペレーター登録 登録サービス
 */
@Service
@Transactional
public class Oa11020EntryService {
	private final OperatorCommandService operatorCommandService;

	// コンストラクタ
	public Oa11020EntryService(OperatorCommandService operatorCommandService) {
		this.operatorCommandService = operatorCommandService;
	}

	/**
	 * 登録処理です。
	 *
	 * @param vo View Object
	 */
	public void entry(Oa11020Vo vo) {

		Oa11020EntryConverter converter = new Oa11020EntryConverter(vo);

		// 登録
		operatorCommandService.entry(converter);

	}
}
