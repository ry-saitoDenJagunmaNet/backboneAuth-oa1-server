package net.jagunma.backbone.auth.authmanager.infra.web.oa11030;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfOperatorSearchConverter;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030Vo;
import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * OA11030 初期表示 Converter
 */
class Oa11030InitConverter extends BaseOfOperatorSearchConverter implements OperatorSearchRequest {

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
