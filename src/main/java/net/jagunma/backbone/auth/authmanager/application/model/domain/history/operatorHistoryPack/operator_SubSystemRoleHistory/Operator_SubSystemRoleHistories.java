package net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistoryPack.operator_SubSystemRoleHistory;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoryEntity;

/**
 * オペレーター_サブシステムロール割当履歴群
 */
public class Operator_SubSystemRoleHistories {

	private final ArrayList<Operator_SubSystemRoleHistory> list = newArrayList();

	// コンストラクタ
	Operator_SubSystemRoleHistories(Collection<Operator_SubSystemRoleHistory> collection) {
		this.list.addAll(collection);
	}

	/**
	 * オペレーター_サブシステムロール割当履歴リストから作成します。
	 *
	 * @param operator_SubSystemRoleHistoryList オペレーター_サブシステムロール割当履歴リスト
	 * @return オペレーター_サブシステムロール割当履歴群
	 */
	public static Operator_SubSystemRoleHistories createFrom(List<Operator_SubSystemRoleHistoryEntity> operator_SubSystemRoleHistoryList) {
		List<Operator_SubSystemRoleHistory> operator_SubSystemRoleHistories = new ArrayList<>();

		operator_SubSystemRoleHistoryList.forEach(d -> {
			Operator_SubSystemRoleHistory operator_SubSystemRoleHistory = Operator_SubSystemRoleHistory.of(d);
			operator_SubSystemRoleHistories.add(operator_SubSystemRoleHistory);
		});
		return new Operator_SubSystemRoleHistories(operator_SubSystemRoleHistories);
	}

	/**
	 * オペレーター_サブシステムロール割当履歴リストを取得します。
	 *
	 * @return オペレーター_サブシステムロール割当履歴リスト
	 */
	public List<Operator_SubSystemRoleHistory> getValues() {
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
			if (list.isEmpty() && ((Operator_SubSystemRoleHistories) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((Operator_SubSystemRoleHistories) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
