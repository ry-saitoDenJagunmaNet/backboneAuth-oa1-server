package net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.OperatorReferenceDto;

/**
 * オペレーター参照サービス Response
 */
public interface OperatorSearchResponse {
    /**
     * オペレーター参照ＤｔｏのＳｅｔ
     * @param operatorReferenceDtos オペレーター参照Ｄｔｏ
     */
    void setOperatorReferenceDtos(List<OperatorReferenceDto> operatorReferenceDtos);
    /**
     * オペレーターテーブルHtmlを生成します。
     *
     * @param pageNo 対象ページ
     */
    void genOperatorTableHtml(int pageNo);
    /**
     * Pagination Htmlを生成します。
     *
     * @param pageNo 表示ページ番号
     */
    void genPaginationHtml(int pageNo);
}
