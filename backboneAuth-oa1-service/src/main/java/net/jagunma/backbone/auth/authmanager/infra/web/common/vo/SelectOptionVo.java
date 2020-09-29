package net.jagunma.backbone.auth.authmanager.infra.web.common.vo;

/**
 * 共通 コンボボックス選択子 View Object
 */
public class SelectOptionVo {

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
    public SelectOptionVo() {}
    public SelectOptionVo(Long id, String code, String name) {}

    // ファクトリーメソッド
    public static SelectOptionVo empty() {
        return new SelectOptionVo(null, "","");
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
