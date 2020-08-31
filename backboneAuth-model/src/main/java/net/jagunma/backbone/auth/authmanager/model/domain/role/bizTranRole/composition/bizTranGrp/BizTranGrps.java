package net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranGrp;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.bizTranGrp.BizTranGrpEntity;

/**
 * 取引グループ群
 */
public class BizTranGrps {

	private final ArrayList<BizTranGrp> list = newArrayList();

	// コンストラクタ
	BizTranGrps(Collection<BizTranGrp> collection) {
		this.list.addAll(collection);
	}

	/**
	 * 取引グループリストから作成します。
	 *
	 * @param bizTranGrpList 取引グループリスト
	 * @return 取引グループ群
	 */
	public static BizTranGrps createFrom(List<BizTranGrpEntity> bizTranGrpList) {
		List<BizTranGrp> bizTranGrps = new ArrayList<>();

		bizTranGrpList.forEach(d -> {
			BizTranGrp bizTranGrp = BizTranGrp.of(d);
			bizTranGrps.add(bizTranGrp);
		});
		return new BizTranGrps(bizTranGrps);
	}

	/**
	 * 取引グループリストを取得します。
	 *
	 * @return 取引グループリスト
	 */
	public List<BizTranGrp> getValues() {
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
			if (list.isEmpty() && ((BizTranGrps) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((BizTranGrps) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
