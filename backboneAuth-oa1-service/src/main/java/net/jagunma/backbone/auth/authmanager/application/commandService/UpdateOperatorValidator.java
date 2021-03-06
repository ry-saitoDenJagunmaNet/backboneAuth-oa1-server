package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorUpdateRequest;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

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
     * リクエストの検証を行います
     */
    public void validate() {

        // リクエスト不正チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));

        // 未セットチェック
        Preconditions.checkNotNull(request.getOperatorId(), () -> new GunmaRuntimeException("EOA13002", "オペレーターID"));
        Preconditions.checkNotEmpty(request.getOperatorName(), () -> new GunmaRuntimeException("EOA13002", "オペレーター名"));
        Preconditions.checkNotNull(request.getValidThruStartDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（開始日）"));
        Preconditions.checkNotNull(request.getValidThruEndDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（終了日）"));
        Preconditions.checkNotNull(request.getIsDeviceAuth(), () -> new GunmaRuntimeException("EOA13002", "機器認証"));
        Preconditions.checkNotNull(request.getBranchId(), () -> new GunmaRuntimeException("EOA13002", "店舗ID"));
        Preconditions.checkNotEmpty(request.getChangeCause(), () -> new GunmaRuntimeException("EOA13002", "変更事由"));
        Preconditions.checkNotNull(request.getAvailableStatus(), () -> new GunmaRuntimeException("EOA13002", "利用可否状態"));

        // 桁数チェック
        Preconditions.checkSize(0, 255, request.getOperatorName(), () -> new GunmaRuntimeException("EOA13003", "オペレーター名", "255", "以下"));
        Preconditions.checkSize(0, 255, request.getMailAddress(), () -> new GunmaRuntimeException("EOA13003", "メールアドレス", "255", "以下"));
        Preconditions.checkSize(0, 255, request.getChangeCause(), () -> new GunmaRuntimeException("EOA13003", "変更事由", "255", "以下"));

        // 列挙型未定義チェック
        Preconditions.checkState(!request.getAvailableStatus().equals(AvailableStatus.UnKnown), () -> new GunmaRuntimeException("EOA13007", "利用可否状態"));

        // 範囲指定不正チェック
        Preconditions.checkMax(request.getValidThruEndDate(), request.getValidThruStartDate(), () -> new GunmaRuntimeException("EOA13008", "有効期限"));

        // メールアドレスフォーマット有効チェック
        Preconditions.checkAvailableMailAddressFormat(request.getMailAddress(), () -> new GunmaRuntimeException("EOA13009", "メールアドレス"));
    }
}
