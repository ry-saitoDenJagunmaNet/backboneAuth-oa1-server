package net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAllRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;

/**
 * サブシステムロール付与検索サービス Response
 */
public interface SubSystemRoleGrantedSearchResponse {
    /**
     * サインインオペレーターIDのSｅｔ
     *
     * @param signInOperatorId サインインオペレーターID
     */
    void setSignInOperatorId(Long signInOperatorId);
    /**
     * ターゲットオペレーターIDのＳｅｔ
     *
     * @param targetOperatorId ターゲットオペレーターID
     */
    void setTargetOperatorId(Long targetOperatorId);
    /**
     * アサインロールDtoリストのＳｅｔ
     *
     * @param assignRoleDtoList アサインロールDtoリスト
     */
    void setAssignRoleDtoList(List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList);
    /**
     * 全ロールDtoリストのＳｅｔ
     *
     * @param allRoleDtoList 全ロールDtoリスト
     */
    void setAllRoleDtoList(List<SubSystemRoleGrantedAllRoleDto> allRoleDtoList);
    /**
     * オペレーター履歴ヘッダーのＳｅｔ
     *
     * @param operatorHistoryHeader オペレーター履歴ヘッダー
     */
    void setOperatorHistoryHeader(OperatorHistoryHeader operatorHistoryHeader);
}
