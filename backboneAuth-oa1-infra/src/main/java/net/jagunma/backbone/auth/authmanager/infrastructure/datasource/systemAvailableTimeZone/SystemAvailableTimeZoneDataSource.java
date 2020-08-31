package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.systemAvailableTimeZone;

import net.jagunma.backbone.auth.authmanager.application.model.domain.systemAvailableTimeZone.SystemAvailableTimeZone;
import net.jagunma.backbone.auth.authmanager.application.model.domain.systemAvailableTimeZone.SystemAvailableTimeZoneCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.systemAvailableTimeZone.SystemAvailableTimeZoneRepository;
import net.jagunma.backbone.auth.model.dao.systemAvailableTimeZone.SystemAvailableTimeZoneEntity;
import net.jagunma.backbone.auth.model.dao.systemAvailableTimeZone.SystemAvailableTimeZoneEntityDao;
import org.springframework.stereotype.Component;

/**
 * アカウントロック検索 DataSource
 */
@Component
public class SystemAvailableTimeZoneDataSource implements SystemAvailableTimeZoneRepository {

    private final SystemAvailableTimeZoneEntityDao systemAvailableTimeZoneEntityDao;

    // コンストラクタ
    public SystemAvailableTimeZoneDataSource(
        SystemAvailableTimeZoneEntityDao systemAvailableTimeZoneEntityDao) {
        this.systemAvailableTimeZoneEntityDao = systemAvailableTimeZoneEntityDao;
    }

    /**
     * アカウントロックの条件検索を行います。
     *
     * @param systemAvailableTimeZoneCriteria アカウントロックの検索条件
     * @return アカウントロック
     */
    @Override
    public SystemAvailableTimeZone findOneBy(
        SystemAvailableTimeZoneCriteria systemAvailableTimeZoneCriteria) {
        SystemAvailableTimeZoneEntity systemAvailableTimeZoneEntity = systemAvailableTimeZoneEntityDao
            .findOneBy(systemAvailableTimeZoneCriteria);
        return SystemAvailableTimeZone.of(systemAvailableTimeZoneEntity);
    }
}
