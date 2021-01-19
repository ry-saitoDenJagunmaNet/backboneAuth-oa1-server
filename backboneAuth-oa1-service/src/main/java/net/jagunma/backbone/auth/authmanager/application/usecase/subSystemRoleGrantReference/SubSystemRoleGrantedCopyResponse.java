package net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;

/**
 * サブシステムロール付与コピーサービス Response
 */
public interface SubSystemRoleGrantedCopyResponse {
    /**
     * アサインロールDtoリストのＳｅｔ
     *
     * @param assignRoleDtoList アサインロールDtoリスト
     */
    void setAssignRoleDtoList(List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList);
}
