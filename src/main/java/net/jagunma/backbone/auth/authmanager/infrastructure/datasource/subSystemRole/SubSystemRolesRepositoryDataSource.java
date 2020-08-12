package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.subSystemRole;

import net.jagunma.backbone.auth.authmanager.application.model.domain.subSystemRole.SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.application.model.domain.subSystemRole.SubSystemRolesRepository;
import org.springframework.stereotype.Component;

/**
 * サブシステムロール検索 DataSource
 */
@Component
public class SubSystemRolesRepositoryDataSource implements SubSystemRolesRepository {
	/**
	 * ササブシステムロールの全件検索を行います。
	 * @return サブシステムロール群
	 */
	@Override
	public SubSystemRoles selectAll() { return SubSystemRoles.createFrom(); }
}
