package net.jagunma.backbone.auth.authmanager.application.model.types;

/**
 * サブシステムの列挙型
 */
public enum SubSystemType {
	KB("購買"),
	YS("販売・青果"),
	YF("販売・花卉"),
	HK("販売・米"),
	HM("販売・麦"),
	AN("販売・畜産"),
	;

	private final String name;

	private SubSystemType(String name) {
		this.name = name;
	}

	public String getSubSystemName() {
		return name;
	}
}
