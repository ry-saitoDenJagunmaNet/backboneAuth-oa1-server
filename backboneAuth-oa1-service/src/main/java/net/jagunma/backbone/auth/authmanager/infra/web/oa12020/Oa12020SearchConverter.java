package net.jagunma.backbone.auth.authmanager.infra.web.oa12020;

import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12020.vo.Oa12020Vo;
import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;
import net.jagunma.common.util.strings2.Strings2;

/**
 * OA12010 一時取引抑止<一覧>検索サービス Request Converter
 */
class Oa12020SearchConverter implements SuspendBizTranSearchRequest {

    /**
     * OA12020 View Object
     */
    private final Oa12020Vo vo;

    // コンストラクタ
    Oa12020SearchConverter(Oa12020Vo oa12020Vo)  {
        vo = oa12020Vo;
    }

    // ファクトリーメソッド
    public static Oa12020SearchConverter with(Oa12020Vo oa12020Vo) {
        return new Oa12020SearchConverter(oa12020Vo);
    }

    /**
     * ＪＡID検索条件のＧｅｔ
     *
     * @return ＪＡID検索条件
     */
    public LongCriteria getJaIdCriteria() {
        LongCriteria criteria = new LongCriteria();
        criteria.setEqualTo(vo.getJaId());
        return criteria;
    }
    /**
     * 店舗ID検索条件のＧｅｔ
     *
     * @return 店舗ID検索条件
     */
    public LongCriteria getBranchIdCriteria() {
        LongCriteria criteria = new LongCriteria();
        criteria.setEqualTo(vo.getBranchId());
        return criteria;
    }
    /**
     * サブシステムコード検索条件のＧｅｔ
     *
     * @return サブシステムコード検索条件
     */
    public StringCriteria getSubSystemCodeCriteria() {
        StringCriteria criteria = new StringCriteria();
        if (Strings2.isNotEmpty(vo.getSubSystemCode())) {
            criteria.setEqualTo(vo.getSubSystemCode());
        }
        return criteria;
    }
    /**
     * 取引グループID検索条件のＧｅｔ
     *
     * @return 取引グループID検索条件
     */
    public LongCriteria getBizTranGrpIdCriteria() {
        LongCriteria criteria = new LongCriteria();
        criteria.setEqualTo(vo.getBizTranGrpId());
        return criteria;
    }
    /**
     * 取引ID検索条件のＧｅｔ
     *
     * @return 取引ID検索条件
     */
    public LongCriteria getBizTranIdCriteria() {
        LongCriteria criteria = new LongCriteria();
        criteria.setEqualTo(vo.getBizTranId());
        return criteria;
    }
    /**
     * 抑止期間開始日検索条件のＧｅｔ
     *
     * @return 抑止期間開始日検索条件
     */
    public LocalDateCriteria getSuspendStartDateCriteria() {
        LocalDateCriteria criteria = new LocalDateCriteria();
        if (vo.getSuspendConditionsSelect() != null) {
            if (vo.getSuspendConditionsSelect() == 1) {
                // 状態指定日
                criteria.setLessOrEqual(vo.getSuspendStatusDate());
            } else if (vo.getSuspendConditionsSelect() == 2) {
                // 条件指定
                criteria.setMoreOrEqual(vo.getSuspendStatusStartDateFrom());
                criteria.setLessOrEqual(vo.getSuspendStatusStartDateTo());
            }
        }
        return criteria;
    }
    /**
     * 抑止期間終了日検索条件のＧｅｔ
     *
     * @return 抑止期間終了日検索条件
     */
    public LocalDateCriteria getSuspendEndDateCriteria() {
        LocalDateCriteria criteria = new LocalDateCriteria();
        if (vo.getSuspendConditionsSelect() != null) {
            if (vo.getSuspendConditionsSelect() == 1) {
                // 状態指定日
                criteria.setMoreOrEqual(vo.getSuspendStatusDate());
            } else if (vo.getSuspendConditionsSelect() == 2) {
                // 条件指定
                criteria.setMoreOrEqual(vo.getSuspendStatusEndDateFrom());
                criteria.setLessOrEqual(vo.getSuspendStatusEndDateTo());
            }
        }
        return criteria;
    }
    /**
     * 抑止理由検索条件のＧｅｔ
     *
     * @return 抑止理由検索条件
     */
    public StringCriteria getSuspendReasonCriteria() {
        StringCriteria criteria = new StringCriteria();
        if (Strings2.isNotEmpty(vo.getSuspendReason())) {
            criteria.setForwardMatch(vo.getSuspendReason());
        }
        return criteria;
    }
}
