package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTransRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;

/**
 * 一時取引抑止リスト検索サービス
 */
@Service
public class SearchSuspendBizTran {

    private final SuspendBizTransRepository suspendBizTransRepository;

    // コンストラクタ
    public SearchSuspendBizTran(SuspendBizTransRepository suspendBizTransRepository) {
        this.suspendBizTransRepository = suspendBizTransRepository;
    }

    /**
     * 一時取引抑止群を検索します
     *
     * @param request  一時取引抑止<一覧>検索サービス Request
     * @param response 一時取引抑止<一覧>検索サービス Response
     */
    public void execute(SuspendBizTranSearchRequest request, SuspendBizTranSearchResponse response) {

        // パラメーターの検証
        SearchSuspendBizTranValidator.with(request).validate();

        // 一時取引抑止検索
        Orders orders = Orders.empty()
            .addOrder("suspendStartDate")
            .addOrder("suspendEndDate")
            .addOrder("jaCode")
            .addOrder("branchCode")
            .addOrder("subSystemDisplaySortOrder")
            .addOrder("bizTranGrpCode")
            .addOrder("bizTranCode");
        response.setSuspendBizTrans(suspendBizTransRepository.selectBy(createSuspendBizTranCriteria(request), orders));
    }

    /**
     * 一時取引抑止検索条件を作成します
     *
     * @param request 一時取引抑止<一覧>検索サービス Request
     * @return 一時取引抑止検索条件
     */
    SuspendBizTranCriteria createSuspendBizTranCriteria(SuspendBizTranSearchRequest request) {
        SuspendBizTranCriteria criteria = new SuspendBizTranCriteria();

        // ＪＡID
        criteria.getJaIdCriteria().assignFrom(request.getJaIdCriteria());
        // 店舗ID
        criteria.getBranchIdCriteria().assignFrom(request.getBranchIdCriteria());
        // サブシステムID
        criteria.getSubSystemCodeCriteria().assignFrom(request.getSubSystemCodeCriteria());
        // 取引グループID
        criteria.getBizTranGrpIdCriteria().assignFrom(request.getBizTranGrpIdCriteria());
        // 取引ID
        criteria.getBizTranIdCriteria().assignFrom(request.getBizTranIdCriteria());
        // 抑止期間開始日
        criteria.getSuspendStartDateCriteria().assignFrom(request.getSuspendStartDateCriteria());
        // 抑止期間終了日
        criteria.getSuspendEndDateCriteria().assignFrom(request.getSuspendEndDateCriteria());
        // 抑止理由
        criteria.getSuspendReasonCriteria().assignFrom(request.getSuspendReasonCriteria());

        return criteria;
    }
}
