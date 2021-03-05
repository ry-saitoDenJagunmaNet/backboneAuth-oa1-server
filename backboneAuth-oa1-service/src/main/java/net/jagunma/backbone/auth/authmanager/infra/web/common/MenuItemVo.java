package net.jagunma.backbone.auth.authmanager.infra.web.common;

import java.util.List;

public class MenuItemVo {

    private String name;
    private String linkUrl;
    private String imgUrl;
    private String description;
    private List<String> descriptions;

    // コンストラクタ
    MenuItemVo(
        String linkUrl,
        String imgUrl,
        String name,
        String description,
        List<String> descriptions) {

        this.linkUrl = linkUrl;
        this.imgUrl = imgUrl;
        this.name = name;
        this.description = description;
        this.descriptions = descriptions;
    }

    // ファクトリーメソッド
    public static MenuItemVo createFrom(
        String linkUrl,
        String imgUrl,
        String name,
        String guide,
        List<String> descriptions) {

        return new MenuItemVo(linkUrl, imgUrl, name, guide, descriptions);
    }

    // Getter
    public String getLinkUrl() {
        return linkUrl;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public List<String> getDescriptions() {
        return descriptions;
    }
}
