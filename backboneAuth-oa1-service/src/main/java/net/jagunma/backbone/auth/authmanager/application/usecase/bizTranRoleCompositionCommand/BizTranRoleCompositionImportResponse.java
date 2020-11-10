package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.dto.MessageDto;

/**
 * 取引ロール編成インポート＆エクスポート Excel 登録サービス Response
 */
public interface BizTranRoleCompositionImportResponse {

    /**
     * メッセージリストのＳｅｔ
     *
     * @param messageDtoList メッセージリスト
     */
    void setMessageDtoList(List<MessageDto> messageDtoList);
}
