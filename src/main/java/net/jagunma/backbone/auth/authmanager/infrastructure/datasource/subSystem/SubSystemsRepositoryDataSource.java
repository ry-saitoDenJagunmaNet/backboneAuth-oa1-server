package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.subSystem;

import net.jagunma.backbone.auth.authmanager.application.model.domain.subSystem.SubSystems;
import net.jagunma.backbone.auth.authmanager.application.model.domain.subSystem.SubSystemsRepository;
import org.springframework.stereotype.Component;

/**
 * サブシステム検索 DataSource
 */
@Component
public class SubSystemsRepositoryDataSource implements SubSystemsRepository {
	/**
	 * サブシステムの全件検索を行います。
	 *
	 * @return サブシステム群
	 */
	@Override
	public SubSystems selectAll() {
		return SubSystems.createFrom();
	}
}
