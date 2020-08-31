package net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.composition.bizTranGrp_BizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.bizTranGrp_BizTran.BizTranGrp_BizTranEntity;

/**
 * 取引グループ_取引割当群
 */
public class BizTranGrp_BizTrans {

	private final ArrayList<BizTranGrp_BizTran> list = newArrayList();

	// コンストラクタ
	BizTranGrp_BizTrans(Collection<BizTranGrp_BizTran> collection) {
		this.list.addAll(collection);
	}

	/**
	 * 取引グループ_取引割当リストから作成します。
	 *
	 * @param bizTranGrp_BizTranList 取引グループ_取引割当リスト
	 * @return 取引グループ_取引割当群
	 */
	public static BizTranGrp_BizTrans createFrom(List<BizTranGrp_BizTranEntity> bizTranGrp_BizTranList) {
		List<BizTranGrp_BizTran> bizTranGrp_BizTrans = new ArrayList<>();

		bizTranGrp_BizTranList.forEach(d -> {
			BizTranGrp_BizTran bizTranGrp_BizTran = BizTranGrp_BizTran.of(d);
			bizTranGrp_BizTrans.add(bizTranGrp_BizTran);
		});
		return new BizTranGrp_BizTrans(bizTranGrp_BizTrans);
	}

	/**
	 * 取引グループ_取引割当リストを取得します。
	 *
	 * @return 取引グループ_取引割当リスト
	 */
	public List<BizTranGrp_BizTran> getValues() {
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
			if (list.isEmpty() && ((BizTranGrp_BizTrans) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((BizTranGrp_BizTrans) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
