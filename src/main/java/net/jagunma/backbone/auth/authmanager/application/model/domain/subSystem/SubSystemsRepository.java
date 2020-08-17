package net.jagunma.backbone.auth.authmanager.application.model.domain.subSystem;

/**
 * サブシステム検索 DataSource
 */
public interface SubSystemsRepository {
	/**
	 * サブシステムの全件検索を行います。
	 * @return サブシステム群
	 */
	SubSystems selectAll();
}
