package net.jagunma.backbone.auth.authmanager.model.types;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;

/**
 * サブシステムロールの列挙型
 */
public enum SubSystemRole {
	JA管理者("JaAdmin", "JA管理者", newArrayList(SubSystem.購買, SubSystem.販売_青果, SubSystem.販売_花卉, SubSystem.販売_米, SubSystem.販売_麦, SubSystem.販売_畜産)),
	業務統括者_購買("KbManager", "業務統括者（購買）", newArrayList(SubSystem.購買)),
	業務統括者_販売_野菜("YsManager", "業務統括者（販売・野菜）", newArrayList(SubSystem.販売_青果)),
	業務統括者_販売_花卉("YfManager", "業務統括者（販売・花卉）", newArrayList(SubSystem.販売_花卉)),
	業務統括者_販売_米("HkManager", "業務統括者（販売・米）", newArrayList(SubSystem.販売_米)),
	業務統括者_販売_麦("HmManager", "業務統括者（販売・麦）", newArrayList(SubSystem.販売_麦)),
	業務統括者_販売_畜産("AnManager", "業務統括者（販売・畜産）", newArrayList(SubSystem.販売_畜産)),
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
