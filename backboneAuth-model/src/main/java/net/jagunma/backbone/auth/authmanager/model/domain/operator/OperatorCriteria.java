package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import net.jagunma.common.ddd.model.criterias.BooleanCriteria;
import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.ShortCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * オペレーターの検索条件
 */
public class OperatorCriteria {

    private LongCriteria operatorIdCriteria = new LongCriteria();
    private StringCriteria operatorCodeCriteria = new StringCriteria();
    private StringCriteria operatorNameCriteria = new StringCriteria();
    private StringCriteria mailAddressCriteria = new StringCriteria();
    private LocalDateCriteria expirationStartDateCriteria = new LocalDateCriteria();
    private LocalDateCriteria expirationEndDateCriteria = new LocalDateCriteria();
    private BooleanCriteria isDeviceAuthCriteria = new BooleanCriteria();
    private LongCriteria jaIdCriteria = new LongCriteria();
    private StringCriteria jaCodeCriteria = new StringCriteria();
    private LongCriteria branchIdCriteria = new LongCriteria();
    private StringCriteria branchCodeCriteria = new StringCriteria();
    private ShortCriteria availableStatusCriteria = new ShortCriteria();

    // Getter
    public LongCriteria getOperatorIdCriteria() {
        return operatorIdCriteria;
    }
    public StringCriteria getOperatorCodeCriteria() {
        return operatorCodeCriteria;
    }
    public StringCriteria getOperatorNameCriteria() {
        return operatorNameCriteria;
    }
    public StringCriteria getMailAddressCriteria() {
        return mailAddressCriteria;
    }
    public LocalDateCriteria getExpirationStartDateCriteria() {
        return expirationStartDateCriteria;
    }
    public LocalDateCriteria getExpirationEndDateCriteria() {
        return expirationEndDateCriteria;
    }
    public BooleanCriteria getIsDeviceAuthCriteria() {
        return isDeviceAuthCriteria;
    }
    public LongCriteria getJaIdCriteria() {
        return jaIdCriteria;
    }
    public StringCriteria getJaCodeCriteria() {
        return jaCodeCriteria;
    }
    public LongCriteria getBranchIdCriteria() {
        return branchIdCriteria;
    }
    public StringCriteria getBranchCodeCriteria() {
        return branchCodeCriteria;
    }
    public ShortCriteria getAvailableStatusCriteria() {
        return availableStatusCriteria;
    }
}
