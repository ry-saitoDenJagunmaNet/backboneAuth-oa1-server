package net.jagunma.backbone.auth.authmanager.application.model.domain.role.subSystemRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.subSystem.SubSystem;
import net.jagunma.backbone.auth.authmanager.application.model.domain.subSystem.SubSystemsRepository;
import net.jagunma.backbone.auth.authmanager.application.model.types.SubSystemRoleType;
import net.jagunma.backbone.auth.authmanager.application.model.types.SubSystemType;

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
	 * @return サブシステムロール群
	 */
	public static SubSystemRoles createFrom() {
		List<SubSystemRole> subSystemRoleList = newArrayList();

		List<SubSystem> subSystemAllList = getSubSystemList();

		for(SubSystemRoleType subSystemRoleType : SubSystemRoleType.values()) {
			List<SubSystem> subSystemList = newArrayList();
			for(String subsystemCode : subSystemRoleType.getSubSystemCode()) {
				subSystemList.add(subSystemAllList.stream().filter(s->s.getSubSystemCode().equals(subsystemCode)).findAny().get());
			}
			subSystemRoleList.add(new SubSystemRole(subSystemRoleType.name(), subSystemRoleType.getSubSystemRoleName(), subSystemList));
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

	/**
	 * サブシステムリストを取得します。
	 *
	 * @return サブシステムリスト
	 */
	private static List<SubSystem> getSubSystemList() {
		List<SubSystem> list = newArrayList();

		for(SubSystemType subSystemType : SubSystemType.values()) {
			list.add(SubSystem.createOf(subSystemType.name(), subSystemType.getSubSystemName()));
		}
		return list;
	}
}
