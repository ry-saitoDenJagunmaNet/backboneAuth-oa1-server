package net.jagunma.backbone.auth.authmanager.infra.datasource.systemAvailableTimeZone;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZone;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZoneCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZoneRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone.SystemAvailableTimeZones;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.model.dao.systemAvailableTimeZone.SystemAvailableTimeZoneEntity;
import net.jagunma.backbone.auth.model.dao.systemAvailableTimeZone.SystemAvailableTimeZoneEntityCriteria;
import net.jagunma.backbone.auth.model.dao.systemAvailableTimeZone.SystemAvailableTimeZoneEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * システム利用可能時間帯検索
 */
@Component
public class SystemAvailableTimeZoneDataSource implements SystemAvailableTimeZoneRepository {

    private SystemAvailableTimeZoneEntityDao systemAvailableTimeZoneEntityDao;

    // コンストラクタ
    SystemAvailableTimeZoneDataSource(SystemAvailableTimeZoneEntityDao systemAvailableTimeZoneEntityDao) {
        this.systemAvailableTimeZoneEntityDao = systemAvailableTimeZoneEntityDao;
    }

    /**
     * システム利用可能時間帯群の検索を行います
     *
     * @param systemAvailableTimeZoneCriteria システム利用可能時間帯の検索条件
     * @param orders                          オーダー指定
     * @return システム利用可能時間帯群
     */
    public SystemAvailableTimeZones selectBy(SystemAvailableTimeZoneCriteria systemAvailableTimeZoneCriteria, Orders orders) {

        SystemAvailableTimeZoneEntityCriteria entityCriteria = new SystemAvailableTimeZoneEntityCriteria();
        entityCriteria.getSubSystemCodeCriteria().assignFrom(systemAvailableTimeZoneCriteria.getSubSystemCodeCriteria());
        entityCriteria.getDayOfWeekCriteria().assignFrom(systemAvailableTimeZoneCriteria.getDayOfWeekCriteria());

        List<SystemAvailableTimeZone> list = newArrayList();
        for (SystemAvailableTimeZoneEntity entity : systemAvailableTimeZoneEntityDao.findBy(entityCriteria, orders)) {
            list.add(SystemAvailableTimeZone.createFrom(
                entity.getSystemAvailableTimeZoneId(),
                entity.getSubSystemCode(),
                entity.getDayOfWeek(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getRecordVersion(),
                SubSystem.codeOf(entity.getSubSystemCode())));
        }

        return SystemAvailableTimeZones.createFrom(list);
    }
}
