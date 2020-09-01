package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarEntryRequest;

/**
 * カレンダー コマンドサービス Validator
 */
class StoreingCalendarValidator {
	private CalendarEntryRequest request;

	// コンストラクタ
	StoreingCalendarValidator(CalendarEntryRequest request) {
		this.request = request;
	}
	// ファクトリーメソッド
	public static StoreingCalendarValidator with(CalendarEntryRequest request) {
		return new StoreingCalendarValidator(request);
	}

	/**
	 * リクエストのチェックを行います。
	 */
	public void validate() {

	}
}

