package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAssignRoleDto;

/**
 * 取引ロール付与コピーサービス Response
 */
public interface BizTranRoleGrantedCopyResponse {
    /**
     * アサインロールDtoリストのＳｅｔ
     *
     * @param assignRoleDtoList アサインロールDtoリスト
     */
    void setAssignRoleDtoList(List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList);
}
