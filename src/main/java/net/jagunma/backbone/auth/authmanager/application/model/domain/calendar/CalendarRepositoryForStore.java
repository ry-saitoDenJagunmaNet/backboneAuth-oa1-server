package net.jagunma.backbone.auth.authmanager.application.model.domain.calendar;

/**
 * カレンダー登録
 */
public interface CalendarRepositoryForStore {
	/**
	 * カレンダー登録を行います。
	 *
	 * @param calendar カレンダー
	 */
	Calendar store(Calendar calendar);
}
