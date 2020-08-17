package net.jagunma.backbone.auth.authmanager.application.model.domain.ja;

import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntityCriteria;

/**
 * JA割当IPアドレス範囲の検索条件
 */
public class JaIpAddressRangeCriteria extends JaIpAddressRangeEntityCriteria {
	public boolean test(JaIpAddressRange aValue) {
		return super.test(aValue.getJaIpAddressRangeEntityForRepository());
	}
}
