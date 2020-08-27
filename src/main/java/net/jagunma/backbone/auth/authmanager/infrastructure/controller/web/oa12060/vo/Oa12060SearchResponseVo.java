package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa12060.vo;

import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.base.vo.BaseOfResponseVo;

/**
 * OA12060検索レスポンス
 */
public class Oa12060SearchResponseVo {
	private static final long serialVersionUID = 1L;

	/**
	 * カレンダーテーブルHtml
	 */
	private String calendarTable;
	/**
	 * 表示対象経済選択無効
	 */
	private boolean calendarTypeFilterCheck1disabled;
	/**
	 * 表示対象信用選択無効
	 */
	private boolean calendarTypeFilterCheck2disabled;
	/**
	 * 表示対象広域物流選択無効
	 */
	private boolean calendarTypeFilterCheck3disabled;

	public String getCalendarTable() { return calendarTable; }
	public void setCalendarTable(String calendarTable) { this.calendarTable = calendarTable; }
	public boolean getCalendarTypeFilterCheck1disabled() { return calendarTypeFilterCheck1disabled; }
	public void setCalendarTypeFilterCheck1disabled(boolean calendarTypeFilterCheck1disabled) { this.calendarTypeFilterCheck1disabled = calendarTypeFilterCheck1disabled; }
	public boolean getCalendarTypeFilterCheck2disabled() { return calendarTypeFilterCheck2disabled; }
	public void setCalendarTypeFilterCheck2disabled(boolean calendarTypeFilterCheck2disabled) { this.calendarTypeFilterCheck2disabled = calendarTypeFilterCheck2disabled; }
	public boolean getCalendarTypeFilterCheck3disabled() { return calendarTypeFilterCheck3disabled; }
	public void setCalendarTypeFilterCheck3disabled(boolean calendarTypeFilterCheck3disabled) { this.calendarTypeFilterCheck3disabled = calendarTypeFilterCheck3disabled; }
}
