package net.jagunma.backbone.auth.authmanager.model.domain.operator.passwordHistory;

/**
 * パスワード履歴検索
 */
public interface PasswordHistoryRepository {
	/**
	 * パスワード履歴の条件検索を行います。
	 *
	 * @param passwordHistoryCriteria パスワード履歴の検索条件
	 * @return パスワード履歴
	 */
	PasswordHistory findOneBy(PasswordHistoryCriteria passwordHistoryCriteria);
}
