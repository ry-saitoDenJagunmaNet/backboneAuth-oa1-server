package net.jagunma.backbone.auth.authmanager.model.domain.ja.jaIpAddressRange;

/**
 * JA割当IPアドレス範囲検索
 */
public interface JaIpAddressRangeRepository {
	/**
	 * JA割当IPアドレス範囲の条件検索を行います。
	 *
	 * @param jaIpAddressRangeCriteria JA割当IPアドレス範囲の検索条件
	 * @return JA割当IPアドレス範囲
	 */
	JaIpAddressRange findOneBy(JaIpAddressRangeCriteria jaIpAddressRangeCriteria);
}
