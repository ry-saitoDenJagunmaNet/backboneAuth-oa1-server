package net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone;

import net.jagunma.backbone.auth.model.dao.systemAvailableTimeZone.SystemAvailableTimeZoneEntityCriteria;

/**
 * システム利用可能時間帯の検索条件
 */
public class SystemAvailableTimeZoneCriteria extends SystemAvailableTimeZoneEntityCriteria {
	public boolean test(SystemAvailableTimeZone aValue) {
		return super.test(aValue.getSystemAvailableTimeZoneEntityForRepository());
	}
}
