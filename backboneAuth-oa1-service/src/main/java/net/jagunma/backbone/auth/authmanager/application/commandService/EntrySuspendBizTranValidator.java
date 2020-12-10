package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranEntryRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * 一時取引抑止登録サービス Validator
 */
public class EntrySuspendBizTranValidator {

    private final SuspendBizTranEntryRequest entryRequest;

    // コンストラクタ
    EntrySuspendBizTranValidator(SuspendBizTranEntryRequest entryRequest) {
        this.entryRequest = entryRequest;
    }

    // ファクトリーメソッド
    public static EntrySuspendBizTranValidator with(SuspendBizTranEntryRequest entryRequest) {
        return new EntrySuspendBizTranValidator(entryRequest);
    }

    /**
     * リクエストの検証を行います
     */
    public void validate() {

        // リクエスト不正チェック
        Preconditions.checkNotNull(entryRequest, () -> new GunmaRuntimeException("EOA13001"));

        // 未セットチェック
        Preconditions.checkNotNull(entryRequest.getSuspendStartDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（開始日）"));
        Preconditions.checkNotNull(entryRequest.getSuspendEndDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（終了日）"));
        Preconditions.checkNotNull(entryRequest.getSuspendReason(), () -> new GunmaRuntimeException("EOA13002", "抑止理由"));

        // 範囲指定不正チェック
        Preconditions.checkMax(entryRequest.getSuspendEndDate(), entryRequest.getSuspendStartDate(), () -> new GunmaRuntimeException("EOA13008", "有効期限"));
    }

}
