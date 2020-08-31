package net.jagunma.backbone.auth.authmanager.application.service.oa12060;

import net.jagunma.backbone.auth.authmanager.application.commandService.CalendarCommandService;
import net.jagunma.backbone.auth.authmanager.infrastructure.web.oa12060.vo.Oa12060EntryResponseVo;
import net.jagunma.backbone.auth.authmanager.infrastructure.web.oa12060.vo.Oa12060Vo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * OA12060 カレンダーメンテナンス 登録 サービス
 */
@Service
@Transactional
public class Oa12060EntryService {

    private final CalendarCommandService calendarCommandService;

    // コンストラクタ
    public Oa12060EntryService(CalendarCommandService calendarCommandService) {
        this.calendarCommandService = calendarCommandService;
    }

    /**
     * カレンダー登録を行います。
     *
     * @param vo         カレンダーメンテナンスView Object
     * @param responseVo カレンダー登録結果
     */
    public void entry(Oa12060Vo vo, Oa12060EntryResponseVo responseVo) {

        Oa12060EntryConverter converter = Oa12060EntryConverter.with(vo);

        // カレンダー登録
        calendarCommandService.entry(converter);
    }
}
