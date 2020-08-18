package net.jagunma.backbone.auth.authmanager.application.model.domain.signTrace.signInTrace;

/**
 * サインイン証跡検索
 */
public interface SignInTracesRepository {
	/**
	 * サインイン証跡の条件検索を行います。
	 *
	 * @param signInTraceCriteria サインイン証跡の検索条件
	 * @return サインイン証跡群
	 */
	SignInTraces selectBy(SignInTraceCriteria signInTraceCriteria);
	/**
	 * サインイン証跡の全件検索を行います。
	 *
	 * @return サインイン証跡群
	 */
	SignInTraces selectAll();
}
