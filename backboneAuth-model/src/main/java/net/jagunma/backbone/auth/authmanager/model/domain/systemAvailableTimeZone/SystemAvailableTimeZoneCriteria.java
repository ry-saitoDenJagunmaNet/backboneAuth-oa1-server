package net.jagunma.backbone.auth.authmanager.model.domain.systemAvailableTimeZone;

import net.jagunma.backbone.auth.authmanager.model.base.AbstractCriteria;
import net.jagunma.common.ddd.model.criterias.ShortCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * システム利用可能時間帯の検索条件
 */
public class SystemAvailableTimeZoneCriteria extends AbstractCriteria {

    private final StringCriteria subSystemCodeCriteria = new StringCriteria();
    private final ShortCriteria dayOfWeekCriteria = new ShortCriteria();

    // Getter
    public StringCriteria getSubSystemCodeCriteria() {
        return subSystemCodeCriteria;
    }
    public ShortCriteria getDayOfWeekCriteria() {
        return dayOfWeekCriteria;
    }
}