package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarEntryRequest;

/**
 * カレンダー コマンドサービス Validator
 */
class CalendarCommandValidator {
	private CalendarEntryRequest request;

	// コンストラクタ
	CalendarCommandValidator(CalendarEntryRequest request) {
		this.request = request;
	}
	// ファクトリーメソッド
	public static CalendarCommandValidator with(CalendarEntryRequest request) {
		return new CalendarCommandValidator(request);
	}

	/**
	 * リクエストのチェックを行います。
	 */
	public void validate() {

	}
}
