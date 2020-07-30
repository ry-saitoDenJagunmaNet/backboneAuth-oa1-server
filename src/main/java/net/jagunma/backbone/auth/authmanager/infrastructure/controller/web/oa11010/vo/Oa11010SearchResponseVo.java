package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo;

import java.io.Serializable;

/**
 * OA11010検索レスポンス
 */
public class Oa11010SearchResponseVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * オペレータTable
	 */
	private String operatorTable;
	/**
	 * オペレータ一覧pagination
	 */
	private String pagination;

	public String getOperatorTable() { return operatorTable; }
	public void setOperatorTable(String operatorTable) { this.operatorTable = operatorTable; }
	public String getPagination() { return pagination; }
	public void setPagination(String pagination) { this.pagination = pagination; }
}
