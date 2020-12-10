package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfOperatorSearchConverter;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;
import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * OA11040 初期表示 Converter
 */
class Oa11040InitConverter extends BaseOfOperatorSearchConverter implements OperatorSearchRequest {

    /**
     * OA11040 View Object
     */
    private final Oa11040Vo vo;

    // コンストラクタ
    Oa11040InitConverter(Oa11040Vo vo)  {
        this.vo = vo;
    }

    // ファクトリーメソッド
    public static Oa11040InitConverter with(Oa11040Vo vo) {
        return new Oa11040InitConverter(vo);
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
