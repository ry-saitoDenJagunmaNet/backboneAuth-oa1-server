package net.jagunma.backbone.auth.authmanager.model.domain.subSystem;

/**
 * サブシステム検索 DataSource
 */
public interface SubSystemsRepository {
	/**
	 * サブシステムの条件検索を行います。
	 *
	 * @param subSystemCriteria サブシステムの検索条件
	 * @return サブシステム群
	 */
//	SubSystems selectBy(SubSystemCriteria subSystemCriteria);
	/**
	 * サブシステムの全件検索を行います。
	 *
	 * @return サブシステム群
	 */
	SubSystems selectAll();
}
