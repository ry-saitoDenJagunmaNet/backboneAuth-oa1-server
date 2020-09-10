package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.util.strings2.Strings2;

/**
 * オペレーター登録サービス Validator
 */
class EntryOperatorValidator {

    private final OperatorEntryRequest request;

    /**
     * コンストラクタ
     */
    EntryOperatorValidator(OperatorEntryRequest request) {
        this.request = request;
    }

    public static EntryOperatorValidator with(OperatorEntryRequest request) {
        return new EntryOperatorValidator(request);
    }

    /**
     * リクエストの検証を行います。
     */
    public void validate() {

        // リクエスト不正チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));

        // 未セットチェック
        Preconditions.checkNotEmpty(request.getOperatorCode6(), () -> new GunmaRuntimeException("EOA13002", "オペレーターコード（下6桁）"));
        Preconditions.checkNotEmpty(request.getOperatorName(), () -> new GunmaRuntimeException("EOA13002", "オペレーター名"));
        Preconditions.checkNotNull(request.getExpirationStartDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（開始日）"));
        Preconditions.checkNotNull(request.getExpirationEndDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（終了日）"));
        Preconditions.checkNotNull(request.getTempoId(), () -> new GunmaRuntimeException("EOA13002", "店舗ID"));
        Preconditions.checkNotEmpty(request.getChangeCause(), () -> new GunmaRuntimeException("EOA13002", "変更事由"));
        Preconditions.checkNotEmpty(request.getPassword(), () -> new GunmaRuntimeException("EOA13002", "パスワード"));
        Preconditions.checkNotEmpty(request.getConfirmPassword(), () -> new GunmaRuntimeException("EOA13002", "パスワードの確認入力"));

        // 桁数チェック
        Preconditions.checkClassification(request.getOperatorCode6().length() == 6, () -> new GunmaRuntimeException("EOA13003", "オペレーターコード（下6桁）", "6"));
        Preconditions.checkClassification(request.getOperatorName().length() <= 255, () -> new GunmaRuntimeException("EOA13003", "オペレーター名", "255", "以下"));
        Preconditions.checkClassification(request.getMailAddress().length() <= 255, () -> new GunmaRuntimeException("EOA13003", "メールアドレス", "255", "以下"));
        Preconditions.checkClassification(request.getChangeCause().length() <= 255, () -> new GunmaRuntimeException("EOA13003", "変更事由", "255", "以下"));
        Preconditions.checkClassification(request.getPassword().length() <= 10, () -> new GunmaRuntimeException("EOA13003", "パスワード", "10", "以下"));

        // ToDo: 全角混入チェック
        // オペレーターコード（下6桁）
        // メールアドレス
        // パスワード


        // 範囲指定不正チェック 有効期限
        if (request.getExpirationStartDate().compareTo(request.getExpirationEndDate()) > 0) {
            throw new GunmaRuntimeException("EOA13004", "有効期限");
        }

        // パスワード不一致チェック
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new GunmaRuntimeException("EOA13101");
        }
    }
}
