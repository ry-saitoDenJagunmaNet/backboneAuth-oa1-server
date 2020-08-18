package net.jagunma.backbone.auth.authmanager.application.model.domain.ja.jaIpAddressRange;

/**
 * JA割当IPアドレス範囲検索
 */
public interface JaIpAddressRangesRepository {
	/**
	 * JA割当IPアドレス範囲の条件検索を行います。
	 *
	 * @param jaIpAddressRangeCriteria JA割当IPアドレス範囲の検索条件
	 * @return JA割当IPアドレス範囲群
	 */
	JaIpAddressRanges selectBy(JaIpAddressRangeCriteria jaIpAddressRangeCriteria);
	/**
	 * JA割当IPアドレス範囲の全件検索を行います。
	 *
	 * @return JA割当IPアドレス範囲群
	 */
	JaIpAddressRanges selectAll();
}
