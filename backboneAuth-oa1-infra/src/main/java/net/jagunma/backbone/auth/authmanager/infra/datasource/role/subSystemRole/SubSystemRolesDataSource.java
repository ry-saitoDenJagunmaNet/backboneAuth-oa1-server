package net.jagunma.backbone.auth.authmanager.infra.datasource.role.subSystemRole;

import net.jagunma.backbone.auth.authmanager.model.domain.role.subSystemRole.SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.role.subSystemRole.SubSystemRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.subSystem.SubSystems;
import net.jagunma.backbone.auth.authmanager.model.domain.subSystem.SubSystemsRepository;
import org.springframework.stereotype.Component;

/**
 * サブシステムロール検索 DataSource
 */
@Component
public class SubSystemRolesDataSource implements SubSystemRolesRepository {

    private SubSystemsRepository subSystemsRepository;

    // コンストラクタ
    public SubSystemRolesDataSource(SubSystemsRepository subSystemsRepository) {
        this.subSystemsRepository = subSystemsRepository;
    }

    /**
     * サブシステムロールの全件検索を行います。
     *
     * @return サブシステムロール群
     */
    @Override
    public SubSystemRoles selectAll() {
        // サブシステム取得
        SubSystems subSystems = subSystemsRepository.selectAll();
        // サブシステムロール列挙型からサブシステムロール銀を取得
        return SubSystemRoles.createFrom(subSystems.getValues());
    }
}
