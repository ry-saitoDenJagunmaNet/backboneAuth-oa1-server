package net.jagunma.backbone.auth.authmanager.model.domain.jaIpAddressRange;

import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * JA割当IPアドレス範囲の検索条件
 */
public class JaIpAddressRangeCriteria {

    private final LongCriteria jaIpAddressRangeIdCriteria = new LongCriteria();
    private final StringCriteria jaCodeCriteria = new StringCriteria();
    private final LocalDateCriteria validThruStartDateCriteria = new LocalDateCriteria();
    private final LocalDateCriteria validThruEndDateCriteria = new LocalDateCriteria();

    // Getter
    public LongCriteria getJaIpAddressRangeIdCriteria() {
        return jaIpAddressRangeIdCriteria;
    }
    public StringCriteria getJaCodeCriteria() {
        return jaCodeCriteria;
    }
    public LocalDateCriteria getValidThruStartDateCriteria() {
        return validThruStartDateCriteria;
    }
    public LocalDateCriteria getValidThruEndDateCriteria() {
        return validThruEndDateCriteria;
    }
}
