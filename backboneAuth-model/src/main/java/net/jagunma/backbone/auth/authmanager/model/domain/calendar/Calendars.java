package net.jagunma.backbone.auth.authmanager.model.domain.calendar;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * カレンダー群
 */
public class Calendars {

    private final List<Calendar> list = newArrayList();

    // コンストラクタ
    Calendars(Collection<Calendar> collection) {
        this.list.addAll(collection);
    }

    /**
     * カレンダーリストから作成します。
     *
     * @param calendarList カレンダーリスト
     * @return カレンダー群
     */
    public static Calendars createFrom(Collection<Calendar> calendarList) {
        return new Calendars(calendarList);
    }

    /**
     * カレンダーリストを取得します。
     *
     * @return カレンダーリスト
     */
    public List<Calendar> getValues() {
        return list;
    }
}
