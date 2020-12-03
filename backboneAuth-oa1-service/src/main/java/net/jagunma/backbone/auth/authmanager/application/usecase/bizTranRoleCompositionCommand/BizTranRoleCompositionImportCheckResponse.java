package net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.dto.MessageDto;

/**
 * 取引ロール編成インポート＆エクスポート Excel Importチェックサービス Response
 */
public interface BizTranRoleCompositionImportCheckResponse {

    /**
     * インポートファイル（表示）のＳｅｔ
     *
     * @param importfileView インポートファイル（表示）
     */
    void setImportfileView(String importfileView);
    /**
     * メッセージリストのＳｅｔ
     *
     * @param messageDtoList メッセージリスト
     */
    void setMessageDtoList(List<MessageDto> messageDtoList);
    /**
     * 状態のＳｅｔ
     *
     * @param status 状態
     */
    void setStatus(String status);
}
