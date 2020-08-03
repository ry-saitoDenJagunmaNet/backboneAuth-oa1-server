package net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference;

/**
 * オペレーター検索Presenter interface
 */
public interface OperatorSearchResponse {
	/**
	 * オペレーターTableのＳｅｔ
	 * @param operatorTable
	 */
	void setOperatorTable(String operatorTable);
	/**
	 * オペレーター一覧paginationのＳｅｔ
	 * @param pagination
	 */
	void setPagination(String pagination);
}
