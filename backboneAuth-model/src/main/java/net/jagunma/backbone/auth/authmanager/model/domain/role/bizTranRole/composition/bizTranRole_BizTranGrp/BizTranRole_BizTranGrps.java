package net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranRole_BizTranGrp;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpEntity;

/**
 * 取引ロール_取引グループ割当群
 */
public class BizTranRole_BizTranGrps {

	private final ArrayList<BizTranRole_BizTranGrp> list = newArrayList();

	// コンストラクタ
	BizTranRole_BizTranGrps(Collection<BizTranRole_BizTranGrp> collection) {
		this.list.addAll(collection);
	}

	/**
	 * 取引ロール_取引グループ割当リストから作成します。
	 *
	 * @param bizTranRole_BizTranGrpList 取引ロール_取引グループ割当リスト
	 * @return 取引ロール_取引グループ割当群
	 */
	public static BizTranRole_BizTranGrps createFrom(List<BizTranRole_BizTranGrpEntity> bizTranRole_BizTranGrpList) {
		List<BizTranRole_BizTranGrp> bizTranRole_BizTranGrps = new ArrayList<>();

		bizTranRole_BizTranGrpList.forEach(d -> {
			BizTranRole_BizTranGrp bizTranRole_BizTranGrp = BizTranRole_BizTranGrp.of(d);
			bizTranRole_BizTranGrps.add(bizTranRole_BizTranGrp);
		});
		return new BizTranRole_BizTranGrps(bizTranRole_BizTranGrps);
	}

	/**
	 * 取引ロール_取引グループ割当リストを取得します。
	 *
	 * @return 取引ロール_取引グループ割当リスト
	 */
	public List<BizTranRole_BizTranGrp> getValues() {
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
			if (list.isEmpty() && ((BizTranRole_BizTranGrps) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((BizTranRole_BizTranGrps) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
