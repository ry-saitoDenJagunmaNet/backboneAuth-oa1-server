package net.jagunma.backbone.auth.authmanager.infra.web.ed01010;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01010.vo.Ed01010Vo;

/**
 * ED01010 初期表示 Converter
 */
class Ed01010InitConverter implements OperatorSearchRequest {

    /**
     * ED01010 View Object
     */
    private final Ed01010Vo vo;

    // コンストラクタ
    Ed01010InitConverter(Ed01010Vo vo)  {
        this.vo = vo;
    }

    // ファクトリーメソッド
    public static Ed01010InitConverter with(Ed01010Vo vo) {
        return new Ed01010InitConverter(vo);
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
