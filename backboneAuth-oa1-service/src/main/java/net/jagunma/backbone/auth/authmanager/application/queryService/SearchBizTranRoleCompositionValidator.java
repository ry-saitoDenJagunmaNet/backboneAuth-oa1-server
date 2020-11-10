package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionExportRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * 取引ロール編成エクスポート検索サービス Validator
 */
public class SearchBizTranRoleCompositionValidator {

    private final BizTranRoleCompositionExportRequest request;

    // コンストラクタ
    SearchBizTranRoleCompositionValidator(BizTranRoleCompositionExportRequest request) {
        this.request = request;
    }

    // ファクトリーメソッド
    public static SearchBizTranRoleCompositionValidator with(
        BizTranRoleCompositionExportRequest request) {
        return new SearchBizTranRoleCompositionValidator(request);
    }

    /**
     * リクエストのチェックを行います
     */
    public void validate() {

        // 未入力チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));

        // 未セットチェック
        Preconditions.checkNotEmpty(request.getSubSystemCode(), () -> new GunmaRuntimeException("EOA13002", "サブシステム"));
    }
}
