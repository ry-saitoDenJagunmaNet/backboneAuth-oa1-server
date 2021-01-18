package net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * システム利用可能時間帯群
 */
public class SystemAvailableTimeZones {

    private final List<SystemAvailableTimeZone> list = newArrayList();

    // コンストラクタ
    SystemAvailableTimeZones(Collection<SystemAvailableTimeZone> collection) {
        this.list.addAll(collection);
    }

    /**
     * システム利用可能時間帯リストから作成します
     *
     * @param systemAvailableTimeZoneList システム利用可能時間帯リスト
     * @return システム利用可能時間帯群
     */
    public static SystemAvailableTimeZones createFrom(Collection<SystemAvailableTimeZone> systemAvailableTimeZoneList) {
        return new SystemAvailableTimeZones(systemAvailableTimeZoneList);
    }

    /**
     * システム利用可能時間帯リストを取得します
     *
     * @return システム利用可能時間帯リスト
     */
    public List<SystemAvailableTimeZone> getValues() {
        return list;
    }
}
