package net.jagunma.backbone.auth.authmanager.application.model.domain.signTrace.signOutTrace;

/**
 * サインアウト証跡検索
 */
public interface SignOutTracesRepository {
	/**
	 * サインアウト証跡の条件検索を行います。
	 *
	 * @param signOutTraceCriteria サインアウト証跡の検索条件
	 * @return サインアウト証跡群
	 */
	SignOutTraces selectBy(SignOutTraceCriteria signOutTraceCriteria);
	/**
	 * サインアウト証跡の全件検索を行います。
	 *
	 * @return サインアウト証跡群
	 */
	SignOutTraces selectAll();
}
