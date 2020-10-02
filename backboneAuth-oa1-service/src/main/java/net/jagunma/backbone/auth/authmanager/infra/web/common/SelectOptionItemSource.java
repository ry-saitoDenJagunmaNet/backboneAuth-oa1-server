package net.jagunma.backbone.auth.authmanager.infra.web.common;

/**
 * 共通 コンボボックス選択子 View Object
 */
public class SelectOptionItemSource {

    /**
     * ID
     */
    private Long id;
    /**
     * コード
     */
    private String code;
    /**
     * 名
     */
    private String name;

    // コンストラクタ
    public SelectOptionItemSource() {}
    public SelectOptionItemSource(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    // 空生成メソッド
    public static SelectOptionItemSource empty() {
        return new SelectOptionItemSource(null, "","");
    }

    // Getter／Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
