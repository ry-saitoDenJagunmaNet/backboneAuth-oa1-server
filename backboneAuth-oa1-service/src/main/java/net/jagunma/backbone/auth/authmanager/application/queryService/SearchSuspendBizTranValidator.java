package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * 一時取引抑止リスト検索サービス Validator
 */
public class SearchSuspendBizTranValidator {

    private final SuspendBizTranSearchRequest request;

    // コンストラクタ
    SearchSuspendBizTranValidator(SuspendBizTranSearchRequest request) {
        this.request = request;
    }

    // ファクトリーメソッド
    public static SearchSuspendBizTranValidator with(SuspendBizTranSearchRequest request) {
        return new SearchSuspendBizTranValidator(request);
    }

    /**
     * リクエストのチェックを行います
     */
    public void validate() {

        // 未入力チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));

        // 範囲指定不正チェック
        // 抑止期間開始
        if (request.getSuspendStartDateCriteria().getMoreOrEqual() != null &&
            request.getSuspendStartDateCriteria().getLessOrEqual() != null) {
            // 条件指定
            Preconditions.checkMax(request.getSuspendStartDateCriteria().getLessOrEqual(),
                request.getSuspendStartDateCriteria().getMoreOrEqual(),
                () -> new GunmaRuntimeException("EOA13008", "抑止期間開始"));
        }
        // 抑止期間終了
        if (request.getSuspendEndDateCriteria().getMoreOrEqual() != null &&
            request.getSuspendEndDateCriteria().getLessOrEqual() != null) {
            // 条件指定
            Preconditions.checkMax(request.getSuspendEndDateCriteria().getLessOrEqual(),
                request.getSuspendEndDateCriteria().getMoreOrEqual(),
                () -> new GunmaRuntimeException("EOA13008", "抑止期間終了"));
        }

    }
}
