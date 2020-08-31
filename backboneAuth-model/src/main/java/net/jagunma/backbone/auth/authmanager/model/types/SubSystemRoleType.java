package net.jagunma.backbone.auth.authmanager.model.types;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;

/**
 * サブシステムロールの列挙型
 */
public enum SubSystemRoleType {
	JaAdmin("JA管理者","KB,YS,YF,HK,HM,AN"),
	KbManager("業務統括者（購買）","KB"),
	YsManager("業務統括者（販売・野菜）","YS"),
	YfManager("業務統括者（販売・花卉）","YF"),
	HkManager("業務統括者（販売・米）","HK"),
	HmManager("業務統括者（販売・麦）","HM"),
	AnManager("業務統括者（販売・畜産）","AN"),
	;

	private final String subSystemRolename;
	private final String subSystemCode;

	private SubSystemRoleType(String subSystemRolename, String subSystemCode) {
		this.subSystemRolename = subSystemRolename;
		this.subSystemCode = subSystemCode;
	}

	public String getSubSystemRoleName() {
		return subSystemRolename;
	}

	public List<String> getSubSystemCode() {
		List<String> list = newArrayList();
		for (String item : subSystemCode.split(",")) {
			list.add(item);
		}
		return list;
	}
}
