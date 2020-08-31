package net.jagunma.backbone.auth.authmanager.model.domain.signTrace.signInTrace;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntity;

/**
 * サインイン証跡群
 */
public class SignInTraces {

	private final ArrayList<SignInTrace> list = newArrayList();

	// コンストラクタ
	SignInTraces(Collection<SignInTrace> collection) {
		this.list.addAll(collection);
	}

	/**
	 * サインイン証跡リストから作成します。
	 *
	 * @param signInTraceList サインイン証跡リスト
	 * @return サインイン証跡群
	 */
	public static SignInTraces createFrom(List<SignInTraceEntity> signInTraceList) {
		List<SignInTrace> signInTraces = new ArrayList<>();

		signInTraceList.forEach(d -> {
			SignInTrace signInTrace = SignInTrace.of(d);
			signInTraces.add(signInTrace);
		});
		return new SignInTraces(signInTraces);
	}

	/**
	 * サインイン証跡リストを取得します。
	 *
	 * @return サインイン証跡リスト
	 */
	public List<SignInTrace> getValues() {
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
			if (list.isEmpty() && ((SignInTraces) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((SignInTraces) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
