package net.jagunma.backbone.auth.authmanager.model.domain.history.passwordHistory;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;

/**
 * パスワード履歴群
 */
public class PasswordHistories {

	private final ArrayList<PasswordHistory> list = newArrayList();

	// コンストラクタ
	PasswordHistories(Collection<PasswordHistory> collection) {
		this.list.addAll(collection);
	}

	/**
	 * パスワード履歴リストから作成します。
	 *
	 * @param passwordHistoryList パスワード履歴リスト
	 * @return パスワード履歴群
	 */
	public static PasswordHistories createFrom(List<PasswordHistoryEntity> passwordHistoryList) {
		List<PasswordHistory> passwordHistories = new ArrayList<>();

		passwordHistoryList.forEach(d -> {
			PasswordHistory passwordHistory = PasswordHistory.of(d);
			passwordHistories.add(passwordHistory);
		});
		return new PasswordHistories(passwordHistories);
	}

	/**
	 * パスワード履歴リストを取得します。
	 *
	 * @return パスワード履歴リスト
	 */
	public List<PasswordHistory> getValues() {
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
			if (list.isEmpty() && ((PasswordHistories) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((PasswordHistories) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
