package net.jagunma.backbone.auth.authmanager.application.model.domain.calendar;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.types.CalendarType;
import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntity;

/**
 * カレンダー群
 */
public class Calendars {

	private final ArrayList<Calendar> list = newArrayList();

	// コンストラクタ
	Calendars(Collection<Calendar> collection) {
		this.list.addAll(collection);
	}
	Calendars() {}

	/**
	 * カレンダーリストから作成します。
	 *
	 * @param calendarList カレンダーリスト
	 * @return カレンダー群
	 */
	public static Calendars createFrom(List<CalendarEntity> calendarList) {
		List<Calendar> calendars = new ArrayList<>();
		for (CalendarEntity entity : calendarList) {
			if (entity.getCalendarId() != null) {
				Calendar calendar = Calendar.createFrom(
					entity.getCalendarId(),
					CalendarType.codeOf(entity.getCalendarType()),
					entity.getDate(),
					entity.getIsHoliday(),
					entity.getIsManualChange(),
					entity.getIsRelease(),
					entity.getRecordVersion());
				calendars.add(calendar);
			}
		}
		return new Calendars(calendars);
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

	/**
	 * オブジェクトの比較を行います。
	 *
	 * @param o 比較するオブジェクト
	 * @return true：比較結果は同じ　false：比較結果は差異がある
	 */
	public boolean sameValueAs(Object o) {
		if (this == o) {
			return true;
		} else if (o != null && this.getClass() == o.getClass()) {
			if (list.isEmpty() && ((Calendars) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((Calendars) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
