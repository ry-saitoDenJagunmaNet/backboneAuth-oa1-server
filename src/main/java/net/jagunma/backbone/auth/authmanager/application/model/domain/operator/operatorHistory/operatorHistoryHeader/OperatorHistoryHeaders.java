package net.jagunma.backbone.auth.authmanager.application.model.domain.operator.operatorHistory.operatorHistoryHeader;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntity;

/**
 * オペレーター履歴パックヘッダー群
 */
public class OperatorHistoryHeaders {

	private final ArrayList<OperatorHistoryHeader> list = newArrayList();

	// コンストラクタ
	OperatorHistoryHeaders(Collection<OperatorHistoryHeader> collection) {
		this.list.addAll(collection);
	}

	/**
	 * オペレーター履歴パックヘッダーリストから作成します。
	 *
	 * @param operatorHistoryHeaderList オペレーター履歴パックヘッダーリスト
	 * @return オペレーター履歴パックヘッダー群
	 */
	public static OperatorHistoryHeaders createFrom(List<OperatorHistoryHeaderEntity> operatorHistoryHeaderList) {
		List<OperatorHistoryHeader> operatorHistoryHeaders = new ArrayList<>();

		operatorHistoryHeaderList.forEach(d -> {
			OperatorHistoryHeader operatorHistoryHeader = OperatorHistoryHeader.of(d);
			operatorHistoryHeaders.add(operatorHistoryHeader);
		});
		return new OperatorHistoryHeaders(operatorHistoryHeaders);
	}

	/**
	 * オペレーター履歴パックヘッダーリストを取得します。
	 *
	 * @return オペレーター履歴パックヘッダーリスト
	 */
	public List<OperatorHistoryHeader> getValues() {
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
			if (list.isEmpty() && ((OperatorHistoryHeaders) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((OperatorHistoryHeaders) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
