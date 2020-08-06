package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010;

import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010Vo;

/**
 * OA11010 オペレーター＜一覧＞ 検索 Validator
 */
public class Oa11010SearchValidator {
	private final Oa11010Vo oa11010Vo;

	/**
	 * コンストラクタ
	 */
	Oa11010SearchValidator(Oa11010Vo oa11010Vo) {
		this.oa11010Vo = oa11010Vo;
	}

	public static Oa11010SearchValidator with(Oa11010Vo oa11010Vo) {
		return new Oa11010SearchValidator(oa11010Vo);
	}

	/**
	 * リクエストのチェックを行います。
	 */
	public void validate() {
	}
}
