package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedSearchRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * 取引ロール付与検索サービス Validator
 */
class SearchBizTranRoleGrantedValidator {

    private final BizTranRoleGrantedSearchRequest request;

    /**
     * コンストラクタ
     */
    SearchBizTranRoleGrantedValidator(BizTranRoleGrantedSearchRequest request) {
        this.request = request;
    }

    public static SearchBizTranRoleGrantedValidator with(BizTranRoleGrantedSearchRequest request) {
        return new SearchBizTranRoleGrantedValidator(request);
    }

    /**
     * リクエストの検証を行います
     */
    public void validate() {

        // リクエスト不正チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));

        // 未セットチェック
        Preconditions.checkNotNull(request.getSignInOperatorId(), () -> new GunmaRuntimeException("EOA13002", "サインインオペレーターID"));
        Preconditions.checkNotNull(request.getTargetOperatorId(), () -> new GunmaRuntimeException("EOA13002", "ターゲットオペレーターID"));
    }
}
