package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchResponse;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * カレンダー参照サービス Validator
 */
class CalendarReferenceValidator {
	private final CalendarSearchRequest request;

	// コンストラクタ
	CalendarReferenceValidator(CalendarSearchRequest request) {
		this.request = request;
	}
	// ファクトリーメソッド
	public static CalendarReferenceValidator with(CalendarSearchRequest request) {
		return new CalendarReferenceValidator(request);
	}

	/**
	 * リクエストのチェックを行います。
	 */
	public void responsevalidate() {
		// 未入力チェック
		Preconditions.checkNotNull(request.getYearMonth(), () -> new GunmaRuntimeException("EOA10002", "年月"));
	}
}
