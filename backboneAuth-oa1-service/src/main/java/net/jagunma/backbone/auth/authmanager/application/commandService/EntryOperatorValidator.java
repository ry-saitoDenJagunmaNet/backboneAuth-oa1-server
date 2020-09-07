package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

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

		// 未入力チェック
		Preconditions.checkNotNull(request.getTempoId(), () -> new GunmaRuntimeException("EOA13002", "店舗"));
		Preconditions.checkNotEmpty(request.getOperatorCode6(), () -> new GunmaRuntimeException("EOA13001", "オペレーターコード（下6桁）"));
		Preconditions.checkNotEmpty(request.getOperatorName(), () -> new GunmaRuntimeException("EOA13001", "オペレーター名"));
		Preconditions.checkNotNull(request.getExpirationStartDate(), () -> new GunmaRuntimeException("EOA13001", "有効期限（開始日）"));
		Preconditions.checkNotNull(request.getExpirationEndDate(), () -> new GunmaRuntimeException("EOA13001", "有効期限（終了日）"));
		Preconditions.checkNotEmpty(request.getChangeCause(), () -> new GunmaRuntimeException("EOA13001", "変更事由"));
		Preconditions.checkNotEmpty(request.getPassword(), () -> new GunmaRuntimeException("EOA13001", "パスワード"));
		Preconditions.checkNotEmpty(request.getConfirmPassword(), () -> new GunmaRuntimeException("EOA13001", "パスワードの確認入力"));

		// 有効期限 大小チェック
		if (request.getExpirationStartDate().compareTo(request.getExpirationEndDate()) > 0) {
			throw new GunmaRuntimeException("EOA13003", "有効期限");
		}

		// パスワードの確認入力の一致チェック
		if (!request.getPassword().equals(request.getConfirmPassword())) {
			throw new GunmaRuntimeException("EOA13101");
		}
	}
}
