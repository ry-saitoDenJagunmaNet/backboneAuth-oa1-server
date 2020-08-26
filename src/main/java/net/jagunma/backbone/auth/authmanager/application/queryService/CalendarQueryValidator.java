package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * カレンダー参照サービス Validator
 */
class CalendarQueryValidator {
	private final CalendarSearchRequest request;

	// コンストラクタ
	CalendarQueryValidator(CalendarSearchRequest request) {
		this.request = request;
	}
	// ファクトリーメソッド
	public static CalendarQueryValidator with(CalendarSearchRequest request) {
		return new CalendarQueryValidator(request);
	}

	/**
	 * リクエストのチェックを行います。
	 */
	public void validate() {
		// 未入力チェック
		Preconditions.checkNotNull(request.getYearMonth(), () -> new GunmaRuntimeException("EOA10002", "年月"));
	}
}
