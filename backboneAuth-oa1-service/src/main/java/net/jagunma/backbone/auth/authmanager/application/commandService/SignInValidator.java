package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * サインインサービス Validator
 */
public class SignInValidator {

    private final SignInRequest request;

    // コンストラクタ
    SignInValidator(SignInRequest request) {
        this.request = request;
    }

    // ファクトリーメソッド
    public static SignInValidator with(SignInRequest request) {
        return new SignInValidator(request);
    }

    /**
     * リクエストの検証を行います
     */
    public void validate() {

        // リクエスト不正チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));

        // 未セットチェック
        Preconditions.checkNotEmpty(request.getOperatorCode(), () -> new GunmaRuntimeException("EOA13002", "オペレーターコード"));
        Preconditions.checkNotEmpty(request.getPassword(), () -> new GunmaRuntimeException("EOA13002", "パスワード"));
    }
}
