package net.jagunma.backbone.auth.authmanager.application.model.domain.suspendBizTran;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.suspendBizTran.SuspendBizTranEntity;

/**
 * 一時取引抑止群
 */
public class SuspendBizTrans {

	private final ArrayList<SuspendBizTran> list = newArrayList();

	// コンストラクタ
	SuspendBizTrans(Collection<SuspendBizTran> collection) {
		this.list.addAll(collection);
	}

	/**
	 * 一時取引抑止リストから作成します。
	 *
	 * @param suspendBizTranList 一時取引抑止リスト
	 * @return 一時取引抑止群
	 */
	public static SuspendBizTrans createFrom(List<SuspendBizTranEntity> suspendBizTranList) {
		List<SuspendBizTran> suspendBizTrans = new ArrayList<>();

		suspendBizTranList.forEach(d -> {
			SuspendBizTran suspendBizTran = SuspendBizTran.of(d);
			suspendBizTrans.add(suspendBizTran);
		});
		return new SuspendBizTrans(suspendBizTrans);
	}

	/**
	 * 一時取引抑止リストを取得します。
	 *
	 * @return 一時取引抑止リスト
	 */
	public List<SuspendBizTran> getValues() {
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
			if (list.isEmpty() && ((SuspendBizTrans) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((SuspendBizTrans) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
