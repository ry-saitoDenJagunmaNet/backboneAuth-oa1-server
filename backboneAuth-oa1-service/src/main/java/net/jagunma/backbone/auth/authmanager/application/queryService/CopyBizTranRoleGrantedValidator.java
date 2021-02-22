package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedCopyRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * 取引ロール付与コピーサービス Validator
 */
class CopyBizTranRoleGrantedValidator {

    private final BizTranRoleGrantedCopyRequest request;

    /**
     * コンストラクタ
     */
    CopyBizTranRoleGrantedValidator(BizTranRoleGrantedCopyRequest request) {
        this.request = request;
    }

    public static CopyBizTranRoleGrantedValidator with(BizTranRoleGrantedCopyRequest request) {
        return new CopyBizTranRoleGrantedValidator(request);
    }

    /**
     * リクエストの検証を行います
     */
    public void validate() {

        // リクエスト不正チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));

        // 未セットチェック
        Preconditions.checkNotNull(request.getSignInOperatorId(), () -> new GunmaRuntimeException("EOA13002", "サインインオペレーターID"));
        Preconditions.checkNotNull(request.getSelectedOperatorId(), () -> new GunmaRuntimeException("EOA13002", "選択オペレーターID"));
    }
}
