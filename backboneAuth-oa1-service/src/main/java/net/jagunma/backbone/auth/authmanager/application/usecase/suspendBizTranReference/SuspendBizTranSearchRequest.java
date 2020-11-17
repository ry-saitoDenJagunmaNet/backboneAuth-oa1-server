package net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference;

import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 一時取引抑止<一覧>検索サービス Request
 */
public interface SuspendBizTranSearchRequest {

    /**
     * ＪＡID検索条件のＧｅｔ
     *
     * @return ＪＡID検索条件
     */
    LongCriteria getJaIdCriteria();
    /**
     * 店舗ID検索条件のＧｅｔ
     *
     * @return 店舗ID検索条件
     */
    LongCriteria getBranchIdCriteria();
    /**
     * サブシステムコード検索条件のＧｅｔ
     *
     * @return サブシステムコード検索条件
     */
    StringCriteria getSubSystemCodeCriteria();
    /**
     * 取引グループID検索条件のＧｅｔ
     *
     * @return 取引グループID検索条件
     */
    LongCriteria getBizTranGrpIdCriteria();
    /**
     * 取引ID検索条件のＧｅｔ
     *
     * @return 取引ID検索条件
     */
    LongCriteria getBizTranIdCriteria();
    /**
     * 抑止期間開始日検索条件のＧｅｔ
     *
     * @return 抑止期間開始日検索条件
     */
    LocalDateCriteria getSuspendStartDateCriteria();

    /**
     * 抑止期間終了日検索条件のＧｅｔ
     *
     * @return 抑止期間終了日検索条件
     */
    LocalDateCriteria getSuspendEndDateCriteria();
    /**
     * 抑止理由検索条件のＧｅｔ
     *
     * @return 抑止理由検索条件
     */
    StringCriteria getSuspendReasonCriteria();
}
