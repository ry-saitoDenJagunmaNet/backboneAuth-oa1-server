package net.jagunma.backbone.auth.authmanager.model.domain.subSystem;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemType;

/**
 * サブシステム群
 */
public class SubSystems {

	private final ArrayList<SubSystem> list = newArrayList();

	// コンストラクタ
	SubSystems(Collection<SubSystem> collection) {
		this.list.addAll(collection);
	}

	/**
	 * サブシステム列挙型から作成します。
	 *
	 * @return サブシステム群
	 */
	public static SubSystems createFrom() {

		List<SubSystem> SubSystemList = newArrayList();

		for(SubSystemType subSystemType :
			SubSystemType.values()) {

			SubSystemList.add(new SubSystem(subSystemType.name(), subSystemType.getSubSystemName()));
		}
		return new SubSystems(SubSystemList);
	}

	/**
	 * サブシステムリストを取得します。
	 *
	 * @return サブシステムリスト
	 */
	public List<SubSystem> getValues() {
		return list;
	}
}
