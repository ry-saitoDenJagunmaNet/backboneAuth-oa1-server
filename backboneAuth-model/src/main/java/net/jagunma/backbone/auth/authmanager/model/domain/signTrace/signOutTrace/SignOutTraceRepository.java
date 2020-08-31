package net.jagunma.backbone.auth.authmanager.model.domain.signTrace.signOutTrace;

/**
 * サインアウト証跡検索
 */
public interface SignOutTraceRepository {
	/**
	 * サインアウト証跡の条件検索を行います。
	 *
	 * @param signOutTraceCriteria サインアウト証跡の検索条件
	 * @return サインアウト証跡
	 */
	SignOutTrace findOneBy(SignOutTraceCriteria signOutTraceCriteria);
}
