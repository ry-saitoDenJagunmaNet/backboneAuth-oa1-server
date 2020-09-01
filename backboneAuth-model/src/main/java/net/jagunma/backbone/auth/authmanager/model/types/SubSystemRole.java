package net.jagunma.backbone.auth.authmanager.model.types;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;

/**
 * サブシステムロールの列挙型
 */
public enum SubSystemRole {
	JaAdmin("JaAdmin", "JA管理者", newArrayList(SubSystem.KB, SubSystem.YS, SubSystem.YF, SubSystem.HK, SubSystem.HM, SubSystem.AN)),
	KbManager("KbManager", "業務統括者（購買）", newArrayList(SubSystem.KB)),
	YsManager("YsManager", "業務統括者（販売・野菜）", newArrayList(SubSystem.YS)),
	YfManager("YfManager", "業務統括者（販売・花卉）", newArrayList(SubSystem.YF)),
	HkManager("HkManager", "業務統括者（販売・米）", newArrayList(SubSystem.HK)),
	HmManager("HmManager", "業務統括者（販売・麦）", newArrayList(SubSystem.HM)),
	AnManager("AnManager", "業務統括者（販売・畜産）", newArrayList(SubSystem.AN)),
	UnKnown("", "未定義", null);

	private final String code;
	private final String name;
	private final List<SubSystem> subSystemList;

	// コンストラクタ
	private SubSystemRole(String code, String name, List<SubSystem> subSystemList) {
		this.code = code;
		this.name = name;
		this.subSystemList = subSystemList;
	}

	/**
	 * コードのＧｅｔ
	 * @return コード
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 名称のＧｅｔ
	 * @return 名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * サブシステムリストのＧｅｔ
	 * @return サブシステムリスト
	 */
	public List<SubSystem> getSubSystemList() {
		return subSystemList;
	}

	/**
	 * コードで検索を行います。
	 *
	 * @param code コード
	 * @return サブシステムロール
	 */
	public static SubSystemRole codeOf(String code) {
		for (SubSystemRole enumItem : values()) {
			if (enumItem.code.equals(code)) {
				return enumItem;
			}
		}
		return SubSystemRole.UnKnown;
	}
}
