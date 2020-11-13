package net.jagunma.backbone.auth.authmanager.infra.web.ed01010;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfOperatorSearchConverter;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01010.vo.Ed01010Vo;
import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * ED01010 初期表示 Converter
 */
class Ed01010InitConverter extends BaseOfOperatorSearchConverter implements OperatorSearchRequest {

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
     * オペレーターID検索条件のＧｅｔ
     *
     * @return オペレーターID検索条件
     */
    public LongCriteria getOperatorIdCriteria() {
        LongCriteria criteria = new LongCriteria();
        criteria.setEqualTo(vo.getOperatorId());
        return criteria;
    }
}
