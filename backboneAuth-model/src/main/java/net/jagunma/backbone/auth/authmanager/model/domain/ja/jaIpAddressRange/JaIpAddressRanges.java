package net.jagunma.backbone.auth.authmanager.model.domain.ja.jaIpAddressRange;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntity;

/**
 * JA割当IPアドレス範囲群
 */
public class JaIpAddressRanges {

	private final ArrayList<JaIpAddressRange> list = newArrayList();

	// コンストラクタ
	JaIpAddressRanges(Collection<JaIpAddressRange> collection) {
		this.list.addAll(collection);
	}

	/**
	 * JA割当IPアドレス範囲リストから作成します。
	 *
	 * @param jaIpAddressRangeList JA割当IPアドレス範囲リスト
	 * @return JA割当IPアドレス範囲群
	 */
	public static JaIpAddressRanges createFrom(List<JaIpAddressRangeEntity> jaIpAddressRangeList) {
		List<JaIpAddressRange> jaIpAddressRanges = new ArrayList<>();

		jaIpAddressRangeList.forEach(d -> {
			JaIpAddressRange jaIpAddressRange = JaIpAddressRange.of(d);
			jaIpAddressRanges.add(jaIpAddressRange);
		});
		return new JaIpAddressRanges(jaIpAddressRanges);
	}

	/**
	 * JA割当IPアドレス範囲リストを取得します。
	 *
	 * @return JA割当IPアドレス範囲リスト
	 */
	public List<JaIpAddressRange> getValues() {
		return list;
	}

	/**
	 * オブジェクトの比較を行います。
	 *
	 * @param o 比較するオブジェクト
	 * @return true：比較結果は同じ　false：比較結果は差異がある
	 */
	public boolean sameValueAs(Object o) {
		if (this == o) {
			return true;
		} else if (o != null && this.getClass() == o.getClass()) {
			if (list.isEmpty() && ((JaIpAddressRanges) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((JaIpAddressRanges) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
