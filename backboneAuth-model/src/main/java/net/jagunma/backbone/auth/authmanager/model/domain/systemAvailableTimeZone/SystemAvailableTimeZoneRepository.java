package net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone;

/**
 * システム利用可能時間帯検索
 */
public interface SystemAvailableTimeZoneRepository {
	/**
	 * システム利用可能時間帯の条件検索を行います。
	 *
	 * @param systemAvailableTimeZoneCriteria システム利用可能時間帯の検索条件
	 * @return システム利用可能時間帯
	 */
	SystemAvailableTimeZone findOneBy(SystemAvailableTimeZoneCriteria systemAvailableTimeZoneCriteria);
}
