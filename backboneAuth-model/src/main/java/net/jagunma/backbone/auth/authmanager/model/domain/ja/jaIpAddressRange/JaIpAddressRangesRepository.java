package net.jagunma.backbone.auth.authmanager.model.domain.ja.jaIpAddressRange;

import net.jagunma.common.ddd.model.orders.Orders;

/**
 * JA割当IPアドレス範囲検索
 */
public interface JaIpAddressRangesRepository {
	/**
	 * JA割当IPアドレス範囲の条件検索を行います。
	 *
	 * @param jaIpAddressRangeCriteria JA割当IPアドレス範囲の検索条件
	 * @param orders オーダー指定
	 * @return JA割当IPアドレス範囲群
	 */
	JaIpAddressRanges selectBy(JaIpAddressRangeCriteria jaIpAddressRangeCriteria, Orders orders);
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
	 * @param orders オーダー指定
	 * @return JA割当IPアドレス範囲群
	 */
	JaIpAddressRanges selectAll(Orders orders);
	/**
	 * JA割当IPアドレス範囲の全件検索を行います。
	 *
	 * @return JA割当IPアドレス範囲群
	 */
	JaIpAddressRanges selectAll();
}
