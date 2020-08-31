package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;

/**
 * オペレーターの検索条件
 */
public class OperatorCriteria extends OperatorEntityCriteria {
	public boolean test(Operator aValue) {
		return super.test(aValue.getOperatorEntityForRepository());
	}
}
