package net.jagunma.backbone.auth.authmanager.infra.web.oa12030;

import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12030.vo.Oa12030Vo;

/**
 * OA12030 一時取引抑止メンテナンス 初期表示 Request Converter
 */
public class Oa12030InitConverter implements SuspendBizTranSearchRequest {

    /**
     * OA12030 View Object
     */
    private final Oa12030Vo vo;

    // コンストラクタ
    Oa12030InitConverter(Oa12030Vo oa12030Vo) {
        vo = oa12030Vo;
    }

    // ファクトリーメソッド
    public static Oa12030InitConverter with(Oa12030Vo oa12030Vo) {
        return new Oa12030InitConverter(oa12030Vo);
    }

    /**
     * 一時取引抑止IDのＧｅｔ
     *
     * @return 一時取引抑止ID
     */
    public Long getSuspendBizTranId() {
        return vo.getSuspendBizTranId();
    }
}
