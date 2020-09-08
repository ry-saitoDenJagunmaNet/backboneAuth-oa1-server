package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.calendarReference.CalendarSearchRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * カレンダー検索サービス Validator
 */
class SearchCalendarValidator {
	private final CalendarSearchRequest request;

	// コンストラクタ
	SearchCalendarValidator(CalendarSearchRequest request) {
		this.request = request;
	}
	// ファクトリーメソッド
	public static SearchCalendarValidator with(CalendarSearchRequest request) {
		return new SearchCalendarValidator(request);
	}

	/**
	 * リクエストのチェックを行います。
	 */
	public void validate() {
		// 未入力チェック
		Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13004"));
		Preconditions.checkNotNull(request.getYearMonth(), () -> new GunmaRuntimeException("EOA13001", "年月"));
	}
}

