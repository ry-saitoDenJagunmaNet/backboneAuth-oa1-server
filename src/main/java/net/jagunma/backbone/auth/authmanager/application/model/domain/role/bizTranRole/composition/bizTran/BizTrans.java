package net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole.composition.bizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.bizTran.BizTranEntity;

/**
 * 取引群
 */
public class BizTrans {

	private final ArrayList<BizTran> list = newArrayList();

	// コンストラクタ
	BizTrans(Collection<BizTran> collection) {
		this.list.addAll(collection);
	}

	/**
	 * 取引リストから作成します。
	 *
	 * @param bizTranList 取引リスト
	 * @return 取引群
	 */
	public static BizTrans createFrom(List<BizTranEntity> bizTranList) {
		List<BizTran> bizTrans = new ArrayList<>();

		bizTranList.forEach(d -> {
			BizTran bizTran = BizTran.of(d);
			bizTrans.add(bizTran);
		});
		return new BizTrans(bizTrans);
	}

	/**
	 * 取引リストを取得します。
	 *
	 * @return 取引リスト
	 */
	public List<BizTran> getValues() {
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
			if (list.isEmpty() && ((BizTrans) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((BizTrans) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
