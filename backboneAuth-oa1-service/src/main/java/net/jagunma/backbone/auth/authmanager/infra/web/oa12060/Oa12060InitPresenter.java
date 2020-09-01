package net.jagunma.backbone.auth.authmanager.infra.web.oa12060;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060Vo;

/**
 * OA12060 カレンダーメンテナンス 画面初期表示 Presenter
 */
class Oa12060InitPresenter {

	/**
	 * responseに変換
	 *
	 * @param vo カレンダーメンテナンスView Object
	 */
	public void bindTo(Oa12060Vo vo) {
		LocalDate ld = LocalDate.now();
		vo.setYearMonth(ld);
		vo.setYearMonthToString(ld.format(DateTimeFormatter.ofPattern("yyyy/MM")));
		vo.setCalendarTypeFilterCheck1((short)1);
		vo.setCalendarTypeFilterCheck2((short)1);
		vo.setCalendarTypeFilterCheck3((short)1);
		vo.setWorkingdayOrHolidaySelect("");
	}
}
