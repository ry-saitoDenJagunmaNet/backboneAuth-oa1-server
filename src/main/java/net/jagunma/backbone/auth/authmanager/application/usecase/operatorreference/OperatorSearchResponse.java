package net.jagunma.backbone.auth.authmanager.application.usecase.operatorreference;

/**
 * OA11010 オペレーター＜一覧＞ SearchPresenter interface
 */
public interface OperatorSearchResponse {
	/**
	 * オペレータTableのセット
	 * @param operatorTable
	 */
	void setOperatorTable(String operatorTable);
	/**
	 * オペレータ一覧paginationのセット
	 * @param pagination
	 */
	void setPagination(String pagination);
}
