package net.jagunma.backbone.auth.authmanager.application.model.domain.subSystem;

import net.jagunma.backbone.auth.authmanager.application.model.domain.operatorSubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.application.model.domain.subSystemRole.SubSystemRole;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntity;

/**
 * サブシステム
 */
public class SubSystem {

	private final String subSystemCode;
	private final String subSystemName;

	/*
	 * コンストラクタ
	 */
	SubSystem(String subSystemCode, String subSystemName) {
		this.subSystemCode = subSystemCode;
		this.subSystemName = subSystemName;
	}

	/*
	 * ファクトリメソッド
	 */
	public static SubSystem createOf(String subSystemCode, String subSystemName) {
		return new SubSystem(subSystemCode, subSystemName);
	}

	public static SubSystem empty() { return new SubSystem("", ""); }

	/**
	 * サブシステムコードのＧｅｔ
	 * @return サブシステムコード
	 */
	public String getSubSystemCode() { return this.subSystemCode; }
	/**
	 * サブシステム名のＧｅｔ
	 * @return サブシステム名
	 */
	public String getSubSystemName() { return this.subSystemName; }
}
