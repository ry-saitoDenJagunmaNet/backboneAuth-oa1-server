package net.jagunma.backbone.auth.usecase.operator;

/**
 * OA11010 オペレーター＜一覧＞ SearchPresenter interface
 */
public interface OperatorSearchResponse {
	void setOperatorTable(String operatorTable);
	void setPagination(String pagination);
}
