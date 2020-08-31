package net.jagunma.backbone.auth.authmanager.model.domain.role.subSystemRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.subSystem.SubSystem;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRoleType;

/**
 * サブシステムロール群
 */
public class SubSystemRoles {

	private final ArrayList<SubSystemRole> list = newArrayList();

	// コンストラクタ
	SubSystemRoles(Collection<SubSystemRole> collection) {
		this.list.addAll(collection);
	}

	/**
	 * サブシステムロール列挙型から作成します。
	 *
	 * @param subSystemList サブシステムリスト
	 * @return サブシステムロール群
	 */
	public static SubSystemRoles createFrom(List<SubSystem> subSystemList) {
		List<SubSystemRole> subSystemRoleList = newArrayList();

		for(SubSystemRoleType subSystemRoleType : SubSystemRoleType.values()) {
			List<SubSystem> subSystemJoinList = newArrayList();
			for(String subsystemCode : subSystemRoleType.getSubSystemCode()) {
				subSystemJoinList.add(subSystemList.stream().filter(s->s.getSubSystemCode().equals(subsystemCode)).findFirst().orElse(SubSystem.empty()));
			}
			subSystemRoleList.add(new SubSystemRole(subSystemRoleType.name(), subSystemRoleType.getSubSystemRoleName(), subSystemJoinList));
		}
		return new SubSystemRoles(subSystemRoleList);
	}

	/**
	 * サブシステムロールリストを取得します。
	 *
	 * @return サブシステムロールリスト
	 */
	public List<SubSystemRole> getValues() {
		return list;
	}
}
