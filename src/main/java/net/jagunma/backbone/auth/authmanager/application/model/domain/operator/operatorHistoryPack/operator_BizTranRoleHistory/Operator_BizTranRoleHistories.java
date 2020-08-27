package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.operatorHistoryPack.operator_BizTranRoleHistory;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRoleHistory.Operator_BizTranRoleHistoryEntity;

/**
 * オペレーター_取引ロール割当履歴群
 */
public class Operator_BizTranRoleHistories {

	private final ArrayList<Operator_BizTranRoleHistory> list = newArrayList();

	// コンストラクタ
	Operator_BizTranRoleHistories(Collection<Operator_BizTranRoleHistory> collection) {
		this.list.addAll(collection);
	}

	/**
	 * オペレーター_取引ロール割当履歴リストから作成します。
	 *
	 * @param operator_BizTranRoleHistoryList オペレーター_取引ロール割当履歴リスト
	 * @return オペレーター_取引ロール割当履歴群
	 */
	public static Operator_BizTranRoleHistories createFrom(List<Operator_BizTranRoleHistoryEntity> operator_BizTranRoleHistoryList) {
		List<Operator_BizTranRoleHistory> operator_BizTranRoleHistories = new ArrayList<>();

		operator_BizTranRoleHistoryList.forEach(d -> {
			Operator_BizTranRoleHistory operator_BizTranRoleHistory = Operator_BizTranRoleHistory.of(d);
			operator_BizTranRoleHistories.add(operator_BizTranRoleHistory);
		});
		return new Operator_BizTranRoleHistories(operator_BizTranRoleHistories);
	}

	/**
	 * オペレーター_取引ロール割当履歴リストを取得します。
	 *
	 * @return オペレーター_取引ロール割当履歴リスト
	 */
	public List<Operator_BizTranRoleHistory> getValues() {
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
			if (list.isEmpty() && ((Operator_BizTranRoleHistories) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((Operator_BizTranRoleHistories) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
