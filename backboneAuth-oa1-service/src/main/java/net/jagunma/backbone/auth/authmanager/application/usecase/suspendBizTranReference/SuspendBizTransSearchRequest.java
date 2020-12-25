package net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference;

import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * 一時取引抑止群検索サービス Request
 */
public interface SuspendBizTransSearchRequest {

    /**
     * ＪＡコード検索条件のＧｅｔ
     *
     * @return ＪＡコード検索条件
     */
    StringCriteria getJaCodeCriteria();
    /**
     * 店舗コード検索条件のＧｅｔ
     *
     * @return 店舗コード検索条件
     */
    StringCriteria getBranchCodeCriteria();
    /**
     * サブシステムコード検索条件のＧｅｔ
     *
     * @return サブシステムコード検索条件
     */
    StringCriteria getSubSystemCodeCriteria();
    /**
     * 取引グループコード検索条件のＧｅｔ
     *
     * @return 取引グループコード検索条件
     */
    StringCriteria getBizTranGrpCodeCriteria();
    /**
     * 取引コード検索条件のＧｅｔ
     *
     * @return 取引コード検索条件
     */
    StringCriteria getBizTranCodeCriteria();
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
