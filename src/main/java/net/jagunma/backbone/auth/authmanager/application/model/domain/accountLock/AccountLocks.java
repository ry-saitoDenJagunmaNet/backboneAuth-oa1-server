package net.jagunma.backbone.auth.authmanager.application.model.domain.accountLock;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.accountLock.AccountLockEntity;

/**
 * アカウントロック群
 */
public class AccountLocks {

	private final ArrayList<AccountLock> list = newArrayList();

	// コンストラクタ
	AccountLocks(Collection<AccountLock> collection) {
		this.list.addAll(collection);
	}

	/**
	 * アカウントロックリストから作成します。
	 *
	 * @param accountLockList アカウントロックリスト
	 * @return アカウントロック群
	 */
	public static AccountLocks createFrom(List<AccountLockEntity> accountLockList) {
		List<AccountLock> accountLocks = new ArrayList<>();

		accountLockList.forEach(d -> {
			AccountLock accountLock = AccountLock.of(d);
			accountLocks.add(accountLock);
		});
		return new AccountLocks(accountLocks);
	}

	/**
	 * アカウントロックリストを取得します。
	 *
	 * @return アカウントロックリスト
	 */
	public List<AccountLock> getValues() {
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
			if (list.isEmpty() && ((AccountLocks) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((AccountLocks) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
