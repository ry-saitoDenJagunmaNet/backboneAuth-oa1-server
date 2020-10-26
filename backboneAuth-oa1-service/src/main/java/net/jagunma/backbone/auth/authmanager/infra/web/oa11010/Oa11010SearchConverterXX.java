package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfOperatorSearchConverter;
import net.jagunma.common.ddd.model.criterias.LongCriteria;

public class Oa11010SearchConverterXX extends BaseOfOperatorSearchConverter implements OperatorSearchRequest  {

    // コンストラクタ
    public Oa11010SearchConverterXX() {}

    /**
     * オペレーターID検索条件のＧｅｔ
     *Id
     * @return オペレーターID検索条件
     */
    public LongCriteria getOperatorIdCriteria() {
        LongCriteria criteria = new LongCriteria();
        criteria.setEqualTo(21L);
        return criteria;
    }
}
