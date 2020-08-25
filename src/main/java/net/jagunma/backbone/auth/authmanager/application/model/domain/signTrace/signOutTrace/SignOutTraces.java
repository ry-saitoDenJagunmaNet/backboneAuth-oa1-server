package net.jagunma.backbone.auth.authmanager.application.model.domain.signTrace.signOutTrace;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntity;

/**
 * サインアウト証跡群
 */
public class SignOutTraces {

	private final ArrayList<SignOutTrace> list = newArrayList();

	// コンストラクタ
	SignOutTraces(Collection<SignOutTrace> collection) {
		this.list.addAll(collection);
	}

	/**
	 * サインアウト証跡リストから作成します。
	 *
	 * @param signOutTraceList サインアウト証跡リスト
	 * @return サインアウト証跡群
	 */
	public static SignOutTraces createFrom(List<SignOutTraceEntity> signOutTraceList) {
		List<SignOutTrace> signOutTraces = new ArrayList<>();

		signOutTraceList.forEach(d -> {
			SignOutTrace signOutTrace = SignOutTrace.of(d);
			signOutTraces.add(signOutTrace);
		});
		return new SignOutTraces(signOutTraces);
	}

	/**
	 * サインアウト証跡リストを取得します。
	 *
	 * @return サインアウト証跡リスト
	 */
	public List<SignOutTrace> getValues() {
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
			if (list.isEmpty() && ((SignOutTraces) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((SignOutTraces) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
