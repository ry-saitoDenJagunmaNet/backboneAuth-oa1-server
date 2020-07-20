package net.jagunma.backbone.auth.usecase.operator;

import net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.Dto.Oa11010SearchResponseDto;

/**
 * OA11010 オペレーター＜一覧＞ SearchPresenter interface
 */
public interface OperatorSearchResponse {
	void setOperatorTable(String operatorTable);
	void setPagination(String pagination);
	void bindTo(Oa11010SearchResponseDto response);
}
