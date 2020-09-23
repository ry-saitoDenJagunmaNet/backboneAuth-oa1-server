package net.jagunma.backbone.auth.authmanager.model.domain.calendar;

/**
 * カレンダー更新
 */
public interface CalendarRepositoryForStore {
    /**
     * カレンダー更新を行います。
     *
     * @param calendar カレンダー
     */
    Calendar update(Calendar calendar);
}
