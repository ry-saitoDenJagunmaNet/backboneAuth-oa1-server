package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.dto.MessageDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportCheckResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010MessageVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;

/**
 * OA12010 インポートチェック Presenter
 */
public class Oa12010CompositionImportCheckPresenter implements BizTranRoleCompositionImportCheckResponse {

    private String importfileView;
    private List<MessageDto> messageDtoList = newArrayList();
    private String status = "";

    /**
     * インポートファイル（表示）のＳｅｔ
     *
     * @param importfileView インポートファイル（表示）
     */
    public void setImportfileView(String importfileView) {
        this.importfileView = importfileView;
    }

    /**
     * メッセージリストのＳｅｔ
     *
     * @param messageDtoList メッセージリスト
     */
    public void setMessageDtoList(List<MessageDto> messageDtoList) {
        this.messageDtoList = messageDtoList;
    }

    /**
     * 状態のＳｅｔ
     *
     * @param status 状態
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * responseに変換
     *
     * @param vo ViewObject
     */
    public void bindTo(Oa12010Vo vo) {
        vo.setImportfileView(importfileView);
        List<Oa12010MessageVo> messageVoList = newArrayList();
        for (MessageDto messageDto : messageDtoList) {
            Oa12010MessageVo messageVo = new Oa12010MessageVo();
            messageVo.setMessageCode(messageDto.getMessageCode());
            messageVo.setMessage(messageDto.getMessage());
            messageVo.setMessageArgs(messageDto.getMessageArgs());
            messageVoList.add(messageVo);
        }
        vo.setMessageVoList(messageVoList);
        vo.setStatus(status);
    }
}
