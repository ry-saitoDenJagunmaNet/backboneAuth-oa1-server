package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAllRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;

/**
 * 取引ロール付与検索サービス Response
 */
public interface BizTranRoleGrantedSearchResponse {
    /**
     * オペレーターIDのＳｅｔ
     *
     * @param operatorId オペレーターID
     */
    void setOperatorId(Long operatorId);
    /**
     * アサインロールDtoリストのＳｅｔ
     *
     * @param assignRoleDtoList アサインロールDtoリスト
     */
    void setAssignRoleDtoList(List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList);
    /**
     * 全ロールDtoリストのＳｅｔ
     *
     * @param allRoleDtoList 全ロールDtoリスト
     */
    void setAllRoleDtoList(List<BizTranRoleGrantedAllRoleDto> allRoleDtoList);
    /**
     * オペレーター履歴ヘッダーのＳｅｔ
     *
     * @param operatorHistoryHeader オペレーター履歴ヘッダー
     */
    void setOperatorHistoryHeader(OperatorHistoryHeader operatorHistoryHeader);
}
