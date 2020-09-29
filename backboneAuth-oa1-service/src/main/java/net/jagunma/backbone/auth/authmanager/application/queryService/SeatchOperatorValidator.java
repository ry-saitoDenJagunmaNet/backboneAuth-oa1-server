package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * オペレーター検索サービス Validator
 */
public class SeatchOperatorValidator {

    private final OperatorSearchRequest request;

    // コンストラクタ
    SeatchOperatorValidator(OperatorSearchRequest request) {
        this.request = request;
    }

    // ファクトリーメソッド
    public static SeatchOperatorValidator with(OperatorSearchRequest request) {
        return new SeatchOperatorValidator(request);
    }

    /**
     * リクエストのチェックを行います。
     */
    public void validate() {
        // 未入力チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));
    }
}
