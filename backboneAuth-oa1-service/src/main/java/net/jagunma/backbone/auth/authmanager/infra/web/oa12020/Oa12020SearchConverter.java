package net.jagunma.backbone.auth.authmanager.infra.web.oa12020;

import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTransSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12020.vo.Oa12020Vo;
import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;
import net.jagunma.common.util.strings2.Strings2;

/**
 * OA12020 検索 Converter
 */
class Oa12020SearchConverter implements SuspendBizTransSearchRequest {

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
     * ＪＡコード検索条件のＧｅｔ
     *
     * @return ＪＡコード検索条件
     */
    public StringCriteria getJaCodeCriteria() {
        StringCriteria criteria = new StringCriteria();
        if (Strings2.isNotEmpty(vo.getJaCode())) {
            criteria.setEqualTo(vo.getJaCode());
        }
        return criteria;
    }
    /**
     * 店舗コード検索条件のＧｅｔ
     *
     * @return 店舗コード検索条件
     */
    public StringCriteria getBranchCodeCriteria() {
        StringCriteria criteria = new StringCriteria();
        if (Strings2.isNotEmpty(vo.getBranchCode())) {
            criteria.setEqualTo(vo.getBranchCode());
        }
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
     * 取引グループコード検索条件のＧｅｔ
     *
     * @return 取引グループコード検索条件
     */
    public StringCriteria getBizTranGrpCodeCriteria() {
        StringCriteria criteria = new StringCriteria();
        if (Strings2.isNotEmpty(vo.getBizTranGrpCode())) {
            criteria.setEqualTo(vo.getBizTranGrpCode());
        }
        return criteria;
    }
    /**
     * 取引コード検索条件のＧｅｔ
     *
     * @return 取引コード検索条件
     */
    public StringCriteria getBizTranCodeCriteria() {
        StringCriteria criteria = new StringCriteria();
        if (Strings2.isNotEmpty(vo.getBizTranCode())) {
            criteria.setEqualTo(vo.getBizTranCode());
        }
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
