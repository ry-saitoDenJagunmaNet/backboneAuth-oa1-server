package net.jagunma.backbone.auth.authmanager.application.model.domain.role.bizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.bizTranRole.BizTranRoleEntity;

/**
 * 取引ロール群
 */
public class BizTranRoles {

	private final ArrayList<BizTranRole> list = newArrayList();

	/**
	 * コンストラクタ
	 */
	BizTranRoles(Collection<BizTranRole> collection) {
		this.list.addAll(collection);
	}

	/**
	 * 取引ロールリストから作成
	 *
	 * @param bizTranRoleList 取引ロールリスト
	 * @return 取引ロール群
	 */
	public static BizTranRoles createFrom(List<BizTranRoleEntity> bizTranRoleList) {
		List<BizTranRole> bizTranRoles = new ArrayList<>();

		bizTranRoleList.forEach(d -> {
			BizTranRole bizTranRole = BizTranRole.of(d);
			bizTranRoles.add(bizTranRole);
		});
		return new BizTranRoles(bizTranRoles);
	}

	public List<BizTranRole> getValues() {
		return list;
	}

	/**
	 * オブジェクトの比較を行います。
	 * @param o 比較するオブジェクト
	 * @return true：比較結果は同じ　false：比較結果は差異がある
	 */
	public boolean sameValueAs(Object o) {
		if (this == o) {
			return true;
		} else if (o != null && this.getClass() == o.getClass()) {
			if (list.isEmpty() && ((BizTranRoles) o).list.isEmpty()) {
				return true;
			}
			return list.stream().anyMatch(s ->
				((BizTranRoles) o).getValues().stream()
					.anyMatch(s::sameValueAs));
		}
		return false;

	}
}
