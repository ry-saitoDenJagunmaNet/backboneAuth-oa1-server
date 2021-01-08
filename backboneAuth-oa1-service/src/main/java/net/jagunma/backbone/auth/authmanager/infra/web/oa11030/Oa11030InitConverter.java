package net.jagunma.backbone.auth.authmanager.infra.web.oa11030;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030Vo;

/**
 * OA11030 初期表示 Converter
 */
class Oa11030InitConverter implements OperatorSearchRequest {

    /**
     * OA11030 View Object
     */
    private final Oa11030Vo vo;

    // コンストラクタ
    Oa11030InitConverter(Oa11030Vo vo)  {
        this.vo = vo;
    }

    // ファクトリーメソッド
    public static Oa11030InitConverter with(Oa11030Vo vo) {
        return new Oa11030InitConverter(vo);
    }

    /**
     * オペレーターIDのＧｅｔ
     *
     * @return オペレーターID
     */
    public Long getOperatorId() {
        return vo.getOperatorId();
    }
}
