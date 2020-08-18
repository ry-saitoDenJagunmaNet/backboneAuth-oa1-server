package net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistory.operatorHistoryPackHeader;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.operatorHistoryPackHeader.OperatorHistoryPackHeaderEntity;

/**
 * オペレーター履歴パックヘッダー群
 */
public class OperatorHistoryPackHeaders {

	private final ArrayList<OperatorHistoryPackHeader> list = newArrayList();

	// コンストラクタ
	OperatorHistoryPackHeaders(Collection<OperatorHistoryPackHeader> collection) {
		this.list.addAll(collection);
	}

	/**
	 * オペレーター履歴パックヘッダーリストから作成します。
	 *
	 * @param operatorHistoryPackHeaderList オペレーター履歴パックヘッダーリスト
	 * @return オペレーター履歴パックヘッダー群
	 */
	public static OperatorHistoryPackHeaders createFrom(List<OperatorHistoryPackHeaderEntity> operatorHistoryPackHeaderList) {
		List<OperatorHistoryPackHeader> operatorHistoryPackHeaders = new ArrayList<>();

		operatorHistoryPackHeaderList.forEach(d -> {
			OperatorHistoryPackHeader operatorHistoryPackHeader = OperatorHistoryPackHeader.of(d);
			operatorHistoryPackHeaders.add(operatorHistoryPackHeader);
		});
		return new OperatorHistoryPackHeaders(operatorHistoryPackHeaders);
	}

	/**
	 * オペレーター履歴パックヘッダーリストを取得します。
	 *
	 * @return オペレーター履歴パックヘッダーリスト
	 */
	public List<OperatorHistoryPackHeader> getValues() {
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
			if (list.isEmpty() && ((OperatorHistoryPackHeaders) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((OperatorHistoryPackHeaders) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
