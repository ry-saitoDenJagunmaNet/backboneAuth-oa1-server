package net.jagunma.backbone.auth.authmanager.model.domain.calendar;

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
