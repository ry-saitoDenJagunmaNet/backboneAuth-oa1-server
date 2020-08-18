package net.jagunma.backbone.auth.authmanager.application.model.domain.signTrace.signInTrace;

import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntityCriteria;

/**
 * サインイン証跡の検索条件
 */
public class SignInTraceCriteria extends SignInTraceEntityCriteria {
	public boolean test(SignInTrace aValue) {
		return super.test(aValue.getSignInTraceEntityForRepository());
	}
}
