package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarStoreRequest;

/**
 * カレンダー コマンドサービス Validator
 */
class StoreCalendarValidator {
	private CalendarStoreRequest request;

	// コンストラクタ
	StoreCalendarValidator(CalendarStoreRequest request) {
		this.request = request;
	}
	// ファクトリーメソッド
	public static StoreCalendarValidator with(CalendarStoreRequest request) {
		return new StoreCalendarValidator(request);
	}

	/**
	 * リクエストのチェックを行います。
	 */
	public void validate() {

	}
}

