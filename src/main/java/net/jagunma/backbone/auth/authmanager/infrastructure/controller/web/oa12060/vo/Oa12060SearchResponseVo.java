package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa12060.vo;

import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.base.vo.BaseOfResponseVo;

/**
 * OA12060検索レスポンス
 */
public class Oa12060SearchResponseVo extends BaseOfResponseVo {
	private static final long serialVersionUID = 1L;

	/**
	 * カレンダーテーブルHtml
	 */
	private String calendarTable;

	public String getCalendarTable() { return calendarTable; }
	public void setCalendarTable(String calendarTable) { this.calendarTable = calendarTable; }
}
