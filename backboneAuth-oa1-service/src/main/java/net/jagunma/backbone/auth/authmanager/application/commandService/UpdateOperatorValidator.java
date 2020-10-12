package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorUpdateRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.util.strings2.Strings2;

/**
 * オペレーター更新サービス Validator
 */
class UpdateOperatorValidator {

    private final OperatorUpdateRequest request;

    /**
     * コンストラクタ
     */
    UpdateOperatorValidator(OperatorUpdateRequest request) {
        this.request = request;
    }

    public static UpdateOperatorValidator with(OperatorUpdateRequest request) {
        return new UpdateOperatorValidator(request);
    }

    /**
     * リクエストの検証を行います。
     */
    public void validate() {

        // リクエスト不正チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));

        // 未セットチェック
        Preconditions.checkNotNull(request.getOperatorId(), () -> new GunmaRuntimeException("EOA13002", "オペレーターID"));
        Preconditions.checkNotEmpty(request.getOperatorName(), () -> new GunmaRuntimeException("EOA13002", "オペレーター名"));
        Preconditions.checkNotNull(request.getExpirationStartDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（開始日）"));
        Preconditions.checkNotNull(request.getExpirationEndDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（終了日）"));
        Preconditions.checkNotNull(request.getBranchId(), () -> new GunmaRuntimeException("EOA13002", "店舗ID"));
        Preconditions.checkNotEmpty(request.getChangeCause(), () -> new GunmaRuntimeException("EOA13002", "変更事由"));
        Preconditions.checkNotNull(request.getAvailableStatus(), () -> new GunmaRuntimeException("EOA13002", "利用可否状態"));

        // 桁数チェック
        Preconditions.checkSize(0, 255, request.getOperatorName(), () -> new GunmaRuntimeException("EOA13003", "オペレーター名", "255", "以下"));
        Preconditions.checkSize(0, 255, request.getMailAddress(), () -> new GunmaRuntimeException("EOA13003", "メールアドレス", "255", "以下"));
        Preconditions.checkSize(0, 255, request.getChangeCause(), () -> new GunmaRuntimeException("EOA13003", "変更事由", "255", "以下"));

        // ToDo: 全角混入チェック
        //  throw new GunmaRuntimeException("EOA13005", "メールアドレス"); ←どこまでチェックするか？ライブラリ提供あるかも

        // 範囲指定不正チェック 有効期限
        Preconditions.checkMax(request.getExpirationEndDate(), request.getExpirationStartDate(), () -> new GunmaRuntimeException("EOA13007", "有効期限"));
    }
}
