package net.jagunma.backbone.auth.authmanager.application.model.domain.ja;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntity;

/**
 * JA割当IPアドレス範囲群
 */
public class JaIpAddressRanges {

	private ArrayList<JaIpAddressRange> list = newArrayList();

	JaIpAddressRanges(Collection<JaIpAddressRange> collection) {
		this.list.addAll(collection);
	}

	/**
	 * JA割当IPアドレス範囲リストから作成
	 *
	 * @param jaIpAddressRangeList JA割当IPアドレス範囲リスト
	 * @return JA割当IPアドレス範囲群
	 */
	public static JaIpAddressRanges createFrom(List<JaIpAddressRangeEntity> jaIpAddressRangeList) {
		List<JaIpAddressRange> JaIpAddressRanges = new ArrayList<>();

		jaIpAddressRangeList.forEach(d -> {
			JaIpAddressRange jaIpAddressRange = JaIpAddressRange.of(d);
			JaIpAddressRanges.add(jaIpAddressRange);
		});
		return new JaIpAddressRanges(JaIpAddressRanges);
	}

	public List<JaIpAddressRange> getValues() {
		return list;
	}

	/**
	 * オブジェクトの比較を行います。
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
