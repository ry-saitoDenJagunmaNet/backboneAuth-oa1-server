package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.operatorHistoryPack.operatorHistory;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntity;

/**
 * オペレーター履歴群
 */
public class OperatorHistories {

	private final ArrayList<OperatorHistory> list = newArrayList();

	// コンストラクタ
	OperatorHistories(Collection<OperatorHistory> collection) {
		this.list.addAll(collection);
	}

	/**
	 * オペレーター履歴リストから作成します。
	 *
	 * @param operatorHistoryList オペレーター履歴リスト
	 * @return オペレーター履歴群
	 */
	public static OperatorHistories createFrom(List<OperatorHistoryEntity> operatorHistoryList) {
		List<OperatorHistory> operatorHistories = new ArrayList<>();

		operatorHistoryList.forEach(d -> {
			OperatorHistory operatorHistory = OperatorHistory.of(d);
			operatorHistories.add(operatorHistory);
		});
		return new OperatorHistories(operatorHistories);
	}

	/**
	 * オペレーター履歴リストを取得します。
	 *
	 * @return オペレーター履歴リスト
	 */
	public List<OperatorHistory> getValues() {
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
			if (list.isEmpty() && ((OperatorHistories) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((OperatorHistories) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
