package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranUpdateRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * 一時取引抑止更新サービス Validator
 */
public class UpdateSuspendBizTranValidator {

    private final SuspendBizTranUpdateRequest updateRequest;

    // コンストラクタ
    UpdateSuspendBizTranValidator(SuspendBizTranUpdateRequest updateRequest) {
        this.updateRequest = updateRequest;
    }

    // ファクトリーメソッド
    public static UpdateSuspendBizTranValidator with(SuspendBizTranUpdateRequest updateRequest) {
        return new UpdateSuspendBizTranValidator(updateRequest);
    }

    /**
     * リクエストの検証を行います
     */
    public void validate() {

        // リクエスト不正チェック
        Preconditions.checkNotNull(updateRequest, () -> new GunmaRuntimeException("EOA13001"));

        // 未セットチェック
        Preconditions.checkNotNull(updateRequest.getSuspendStartDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（開始日）"));
        Preconditions.checkNotNull(updateRequest.getSuspendEndDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（終了日）"));

        // 範囲指定不正チェック
        Preconditions.checkMax(updateRequest.getSuspendEndDate(), updateRequest.getSuspendStartDate(), () -> new GunmaRuntimeException("EOA13008", "有効期限"));
    }
}
