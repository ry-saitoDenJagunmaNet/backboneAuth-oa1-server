package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchResponse;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTransSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTransSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;

/**
 * 一時取引抑止リスト検索サービス
 */
@Service
public class SearchSuspendBizTran {

    private final SuspendBizTranRepository suspendBizTranRepository;

    // コンストラクタ
    public SearchSuspendBizTran(SuspendBizTranRepository suspendBizTranRepository) {
        this.suspendBizTranRepository = suspendBizTranRepository;
    }

    /**
     * 一時取引抑止を検索します
     *
     * @param request  一時取引抑止検索サービス Request
     * @param response 一時取引抑止検索サービス Response
     */
    public void execute(SuspendBizTranSearchRequest request, SuspendBizTranSearchResponse response) {

        // 検索条件作成
        SuspendBizTranCriteria criteria = new SuspendBizTranCriteria();
        criteria.getSuspendBizTranIdCriteria().assignFrom(request.getSuspendBizTranIdCriteria());

        // 一時取引抑止検索
        SuspendBizTran suspendBizTran = suspendBizTranRepository.findOneBy(criteria);
        response.setSuspendBizTranId(suspendBizTran.getSuspendBizTranId());
        response.setJaCode(suspendBizTran.getJaCode());
        response.setBranchCode(suspendBizTran.getBranchCode());
        response.setSubSystemCode(suspendBizTran.getSubSystemCode());
        response.setBizTranGrpCode(suspendBizTran.getBizTranGrpCode());
        response.setBizTranCode(suspendBizTran.getBizTranCode());
        response.setSuspendStartDate(suspendBizTran.getSuspendStartDate());
        response.setSuspendEndDate(suspendBizTran.getSuspendEndDate());
        response.setSuspendReason(suspendBizTran.getSuspendReason());
        response.setRecordVersion(suspendBizTran.getRecordVersion());
    }

    /**
     * 一時取引抑止群を検索します
     *
     * @param request  一時取引抑止群検索サービス Request
     * @param response 一時取引抑止群検索サービス Response
     */
    public void execute(SuspendBizTransSearchRequest request, SuspendBizTransSearchResponse response) {

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
        response.setSuspendBizTrans(suspendBizTranRepository.selectBy(createSuspendBizTranCriteria(request), orders));
    }

    /**
     * 一時取引抑止検索条件を作成します
     *
     * @param request 一時取引抑止<一覧>検索サービス Request
     * @return 一時取引抑止検索条件
     */
    SuspendBizTranCriteria createSuspendBizTranCriteria(SuspendBizTransSearchRequest request) {

        SuspendBizTranCriteria criteria = new SuspendBizTranCriteria();

        // ＪＡコード
        criteria.getJaCodeCriteria().assignFrom(request.getJaCodeCriteria());
        // 店舗コード
        criteria.getBranchCodeCriteria().assignFrom(request.getBranchCodeCriteria());
        // サブシステムコード
        criteria.getSubSystemCodeCriteria().assignFrom(request.getSubSystemCodeCriteria());
        // 取引グループコード
        criteria.getBizTranGrpCodeCriteria().assignFrom(request.getBizTranGrpCodeCriteria());
        // 取引コード
        criteria.getBizTranCodeCriteria().assignFrom(request.getBizTranCodeCriteria());
        // 抑止期間開始日
        criteria.getSuspendStartDateCriteria().assignFrom(request.getSuspendStartDateCriteria());
        // 抑止期間終了日
        criteria.getSuspendEndDateCriteria().assignFrom(request.getSuspendEndDateCriteria());
        // 抑止理由
        criteria.getSuspendReasonCriteria().assignFrom(request.getSuspendReasonCriteria());

        return criteria;
    }
}
