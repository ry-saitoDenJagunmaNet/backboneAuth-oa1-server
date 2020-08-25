package net.jagunma.backbone.auth.authmanager.application.model.domain.roleAssignment.operator_BizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * オペレーター_取引ロール割当群
 */
public class Operator_BizTranRoles {

	private final ArrayList<Operator_BizTranRole> list = newArrayList();

	// コンストラクタ
	Operator_BizTranRoles(Collection<Operator_BizTranRole> collection) {
		this.list.addAll(collection);
	}

	/**
	 * オペレーター_取引ロール割当リストから作成します。
	 *
	 * @param operator_BizTranRoleList オペレーター_取引ロール割当リスト
	 * @return オペレーター_取引ロール割当群
	 */
	public static Operator_BizTranRoles createFrom(List<Operator_BizTranRole> operator_BizTranRoleList) {
		return new Operator_BizTranRoles(operator_BizTranRoleList);
	}

	/**
	 * オペレーター_取引ロール割当リストを取得します。
	 *
	 * @return オペレーター_取引ロール割当リスト
	 */
	public List<Operator_BizTranRole> getValues() {
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
			if (list.isEmpty() && ((Operator_BizTranRoles) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((Operator_BizTranRoles) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;
	}
}
