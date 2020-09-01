package net.jagunma.backbone.auth.authmanager.infra.datasource.subSystem;

import net.jagunma.backbone.auth.authmanager.model.domain.subSystem.SubSystems;
import net.jagunma.backbone.auth.authmanager.model.domain.subSystem.SubSystemsRepository;
import org.springframework.stereotype.Component;

/**
 * サブシステム検索 DataSource
 */
@Component
public class SubSystemsDataSource implements SubSystemsRepository {

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
