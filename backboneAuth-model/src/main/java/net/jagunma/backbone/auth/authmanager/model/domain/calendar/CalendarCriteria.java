package net.jagunma.backbone.auth.authmanager.model.domain.calendar;

import net.jagunma.backbone.auth.model.dao.calendar.CalendarEntityCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * カレンダーの検索条件
 */
public class CalendarCriteria extends CalendarEntityCriteria {
	LongCriteria item = new LongCriteria();
	public boolean test(Calendar aValue) {
		return item.test(aValue.getCalendarId());
	}
}
