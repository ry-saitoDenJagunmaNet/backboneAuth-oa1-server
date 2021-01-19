package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * サブシステムロール付与コピーサービス Validator
 */
class CopySubSystemRoleGrantedValidator {

    private final SubSystemRoleGrantedCopyRequest request;

    /**
     * コンストラクタ
     */
    CopySubSystemRoleGrantedValidator(SubSystemRoleGrantedCopyRequest request) {
        this.request = request;
    }

    public static CopySubSystemRoleGrantedValidator with(SubSystemRoleGrantedCopyRequest request) {
        return new CopySubSystemRoleGrantedValidator(request);
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
