package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.dto.MessageDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportStoreResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010MessageVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;

/**
 * OA12010 取引ロール編成インポート＆エクスポート Excel 登録サービス Response Presenter
 */
public class Oa12010CompositionImportStorePresenter implements BizTranRoleCompositionImportStoreResponse {

    private List<MessageDto> messageDtoList = newArrayList();

    /**
     * メッセージリストのＳｅｔ
     *
     * @param messageDtoList メッセージリスト
     */
    public void setMessageDtoList(List<MessageDto> messageDtoList) {
        this.messageDtoList = messageDtoList;
    }

    /**
     * responseに変換
     *
     * @param vo ViewObject
     */
    public void bindTo(Oa12010Vo vo) {
        List<Oa12010MessageVo> messageVoList = newArrayList();
        for (MessageDto messageDto : messageDtoList) {
            Oa12010MessageVo messageVo = new Oa12010MessageVo();
            messageVo.setMessageCode(messageDto.getMessageCode());
            messageVo.setMessage(messageDto.getMessage());
            messageVo.setMessageArgs(messageDto.getMessageArgs());
            messageVoList.add(messageVo);
        }
        vo.setMessageVoList(messageVoList);
    }
}
