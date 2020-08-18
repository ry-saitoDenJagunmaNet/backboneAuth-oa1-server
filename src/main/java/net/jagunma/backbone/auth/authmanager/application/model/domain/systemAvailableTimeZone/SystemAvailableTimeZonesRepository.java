package net.jagunma.backbone.auth.authmanager.application.model.domain.systemAvailableTimeZone;

/**
 * システム利用可能時間帯検索
 */
public interface SystemAvailableTimeZonesRepository {
	/**
	 * システム利用可能時間帯の条件検索を行います。
	 *
	 * @param systemAvailableTimeZoneCriteria システム利用可能時間帯の検索条件
	 * @return システム利用可能時間帯群
	 */
	SystemAvailableTimeZones selectBy(SystemAvailableTimeZoneCriteria systemAvailableTimeZoneCriteria);
	/**
	 * システム利用可能時間帯の全件検索を行います。
	 *
	 * @return システム利用可能時間帯群
	 */
	SystemAvailableTimeZones selectAll();
}
