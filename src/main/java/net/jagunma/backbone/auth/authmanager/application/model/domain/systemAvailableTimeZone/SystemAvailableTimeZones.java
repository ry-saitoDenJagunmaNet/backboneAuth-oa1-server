package net.jagunma.backbone.auth.authmanager.application.model.domain.systemAvailableTimeZone;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.systemAvailableTimeZone.SystemAvailableTimeZoneEntity;

/**
 * システム利用可能時間帯群
 */
public class SystemAvailableTimeZones {

	private final ArrayList<SystemAvailableTimeZone> list = newArrayList();

	// コンストラクタ
	SystemAvailableTimeZones(Collection<SystemAvailableTimeZone> collection) {
		this.list.addAll(collection);
	}

	/**
	 * システム利用可能時間帯リストから作成します。
	 *
	 * @param systemAvailableTimeZoneList システム利用可能時間帯リスト
	 * @return システム利用可能時間帯群
	 */
	public static SystemAvailableTimeZones createFrom(List<SystemAvailableTimeZoneEntity> systemAvailableTimeZoneList) {
		List<SystemAvailableTimeZone> systemAvailableTimeZones = new ArrayList<>();

		systemAvailableTimeZoneList.forEach(d -> {
			SystemAvailableTimeZone systemAvailableTimeZone = SystemAvailableTimeZone.of(d);
			systemAvailableTimeZones.add(systemAvailableTimeZone);
		});
		return new SystemAvailableTimeZones(systemAvailableTimeZones);
	}

	/**
	 * システム利用可能時間帯リストを取得します。
	 *
	 * @return システム利用可能時間帯リスト
	 */
	public List<SystemAvailableTimeZone> getValues() {
		return list;
	}

	/**
	 * オブジェクトの比較を行います。
	 *
	 * @param o 比較するオブジェクト
	 * @return true：比較結果は同じ　false：比較結果は差異がある
	 */
	public boolean sameValueAs(Object o) {
		if (this == o) {
			return true;
		} else if (o != null && this.getClass() == o.getClass()) {
			if (list.isEmpty() && ((SystemAvailableTimeZones) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((SystemAvailableTimeZones) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
