package net.jagunma.backbone.auth.authmanager.application.model.domain.signTrace.signOutTrace;

import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntityCriteria;

/**
 * サインアウト証跡の検索条件
 */
public class SignOutTraceCriteria extends SignOutTraceEntityCriteria {
	public boolean test(SignOutTrace aValue) {
		return super.test(aValue.getSignOutTraceEntityForRepository());
	}
}
