package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010;

import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010SearchResponseVo;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchResponse;

/**
 * OA11010 オペレーター＜一覧＞ 検索 Presenter
 */
public class Oa11010SearchPresenter implements OperatorSearchResponse {
	private String operatorTable;
	private String pagination;

	Oa11010SearchPresenter() {
	}

	/**
	 * オペレーターTableのＳｅｔ
	 * @param operatorTable
	 */
	public void setOperatorTable(String operatorTable) { this.operatorTable = operatorTable; }
	/**
	 * オペレーター一覧paginationのＳｅｔ
	 * @param pagination
	 */
	public void setPagination(String pagination) { this.pagination = pagination; }

	/**
	 * responseに変換
	 * @param response
	 */
	public void bindTo(Oa11010SearchResponseVo response) {
		response.setOperatorTable(operatorTable);
		response.setPagination(pagination);
	}

}
