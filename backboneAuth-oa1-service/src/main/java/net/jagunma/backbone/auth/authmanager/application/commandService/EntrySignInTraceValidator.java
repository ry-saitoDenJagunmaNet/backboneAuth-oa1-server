package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.signInTraceCommand.SignInTraceEntryRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

public class EntrySignInTraceValidator {

    private SignInTraceEntryRequest request;

    // コンストラクタ
    EntrySignInTraceValidator(SignInTraceEntryRequest request) {
        this.request = request;
    }

    // ファクトリーメソッド
    public static EntrySignInTraceValidator with(SignInTraceEntryRequest request) {
        return new EntrySignInTraceValidator(request);
    }

    /**
     * リクエストのチェックを行います
     */
    public void validate() {
        //ToDo：api validateのMessageはどうする？（Exclusive use）

        // 未入力チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));

        // 未セットチェック
        Preconditions.checkNotEmpty(request.getTryIpAddress(), () -> new GunmaRuntimeException("EOA13002", "試行IPアドレス"));
        Preconditions.checkNotEmpty(request.getOperatorCode(), () -> new GunmaRuntimeException("EOA13002", "オペレーターコード"));
        Preconditions.checkNotNull(request.getSignInCause(), () -> new GunmaRuntimeException("EOA13002", "サインイン起因"));
        Preconditions.checkNotNull(request.getSignInResult(), () -> new GunmaRuntimeException("EOA13002", "サインイン結果"));
    }
}
