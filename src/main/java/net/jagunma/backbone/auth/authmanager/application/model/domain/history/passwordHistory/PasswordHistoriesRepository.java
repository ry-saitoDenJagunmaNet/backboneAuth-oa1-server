package net.jagunma.backbone.auth.authmanager.application.model.domain.history.passwordHistory;

/**
 * パスワード履歴検索
 */
public interface PasswordHistoriesRepository {
	/**
	 * パスワード履歴の条件検索を行います。
	 *
	 * @param passwordHistoryCriteria パスワード履歴の検索条件
	 * @return パスワード履歴群
	 */
	PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria);
	/**
	 * パスワード履歴の全件検索を行います。
	 *
	 * @return パスワード履歴群
	 */
	PasswordHistories selectAll();
}
