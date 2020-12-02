package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import net.jagunma.common.ddd.model.criterias.BooleanCriteria;
import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.ShortCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;
import net.jagunma.common.values.model.branch.JaIdentifierCriteria;

/**
 * オペレーターの検索条件
 */
public class OperatorCriteria {

    private LongCriteria operatorIdCriteria = new LongCriteria();
    private StringCriteria operatorCodeCriteria = new StringCriteria();
    private StringCriteria operatorNameCriteria = new StringCriteria();
    private StringCriteria mailAddressCriteria = new StringCriteria();
    private LocalDateCriteria validThruStartDateCriteria = new LocalDateCriteria();
    private LocalDateCriteria validThruEndDateCriteria = new LocalDateCriteria();
    private BooleanCriteria isDeviceAuthCriteria = new BooleanCriteria();
    private final JaIdentifierCriteria jaIdentifierCriteria = new JaIdentifierCriteria();
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
    public LocalDateCriteria getValidThruStartDateCriteria() {
        return validThruStartDateCriteria;
    }
    public LocalDateCriteria getValidThruEndDateCriteria() {
        return validThruEndDateCriteria;
    }
    public BooleanCriteria getIsDeviceAuthCriteria() {
        return isDeviceAuthCriteria;
    }
    public JaIdentifierCriteria getJaIdentifierCriteria() {
        return jaIdentifierCriteria;
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
