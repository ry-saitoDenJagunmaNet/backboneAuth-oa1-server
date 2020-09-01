package net.jagunma.backbone.auth.authmanager.model.domain.roleAssignment.operator_SubSystemRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * オペレーター_サブシステムロール割当群
 */
public class Operator_SubSystemRoles {

	private final ArrayList<Operator_SubSystemRole> list = newArrayList();

	// コンストラクタ
	Operator_SubSystemRoles(Collection<Operator_SubSystemRole> collection) {
		this.list.addAll(collection);
	}

	/**
	 * オペレーター_サブシステムロール割当リストから作成します。
	 *
	 * @param operator_SubSystemRoleList オペレーター_サブシステムロール割当リスト
	 * @return オペレーター_サブシステムロール割当群
	 */
	public static Operator_SubSystemRoles createFrom(List<Operator_SubSystemRole> operator_SubSystemRoleList) {
		return new Operator_SubSystemRoles(operator_SubSystemRoleList);
	}

	/**
	 * オペレーター_サブシステムロール割当リストを取得します。
	 *
	 * @return オペレーター_サブシステムロール割当リスト
	 */
	public List<Operator_SubSystemRole> getValues() {
		return list;
	}

	/**
	 * オブジェクトの比較を行います。
	 *
	 * @param o 比較するオブジェクト
	 * @return true：比較結果は同じ　false：比較結果は差異がある
	 */
	public boolean sameValueAs(Object o) {
//		if (this == o) {
//			return true;
//		} else if (o != null && this.getClass() == o.getClass()) {
//			if (list.isEmpty() && ((Operator_SubSystemRoles) o).list.isEmpty()) {
//				return true;
//			}
//			return list.stream().anyMatch(s ->
//				((Operator_SubSystemRoles) o).getValues().stream()
//					.anyMatch(s::sameValueAs));
//		}
		return false;
	}
}
