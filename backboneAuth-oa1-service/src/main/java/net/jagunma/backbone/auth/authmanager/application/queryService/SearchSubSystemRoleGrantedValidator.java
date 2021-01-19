package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedSearchRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * サブシステムロール付与検索サービス Validator
 */
class SearchSubSystemRoleGrantedValidator {

    private final SubSystemRoleGrantedSearchRequest request;

    /**
     * コンストラクタ
     */
    SearchSubSystemRoleGrantedValidator(SubSystemRoleGrantedSearchRequest request) {
        this.request = request;
    }

    public static SearchSubSystemRoleGrantedValidator with(SubSystemRoleGrantedSearchRequest request) {
        return new SearchSubSystemRoleGrantedValidator(request);
    }

    /**
     * リクエストの検証を行います
     */
    public void validate() {

        // リクエスト不正チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));

        // 未セットチェック
        Preconditions.checkNotNull(request.getTargetOperatorId(), () -> new GunmaRuntimeException("EOA13002", "ターゲットオペレーターID"));
        Preconditions.checkNotNull(request.getSignInOperatorId(), () -> new GunmaRuntimeException("EOA13002", "サインインオペレーターID"));
    }
}
