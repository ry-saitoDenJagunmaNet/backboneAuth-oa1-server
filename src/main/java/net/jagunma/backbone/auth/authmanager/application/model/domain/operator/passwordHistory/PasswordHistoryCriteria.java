package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.passwordHistory;

import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityCriteria;

/**
 * パスワード履歴の検索条件
 */
public class PasswordHistoryCriteria extends PasswordHistoryEntityCriteria {
	public boolean test(PasswordHistory aValue) {
		return super.test(aValue.getPasswordHistoryEntityForRepository());
	}
}
