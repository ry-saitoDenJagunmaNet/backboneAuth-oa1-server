package net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010;

import net.jagunma.backbone.auth.usecase.operator.OperatorSearchResponse;

/**
 * OA11010 オペレーター＜一覧＞ 検索 Presenter
 */
public class SearchPresenter implements OperatorSearchResponse {
	private String operatorTable;
	private String pagination;

	SearchPresenter() {
	}

	/**
	 * オペレータTableのセット
	 * @param operatorTable
	 */
	public void setOperatorTable(String operatorTable) { this.operatorTable = operatorTable; }
	/**
	 * オペレータ一覧paginationのセット
	 * @param pagination
	 */
	public void setPagination(String pagination) { this.pagination = pagination; }

	/**
	 * responseに変換
	 * @param response
	 */
	public void bindTo(Oa11010SearchResponseDto response) {
		response.setOperatorTable(operatorTable);
		response.setPagination(pagination);
	}

}
