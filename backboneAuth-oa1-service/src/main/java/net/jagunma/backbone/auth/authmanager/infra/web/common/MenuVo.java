package net.jagunma.backbone.auth.authmanager.infra.web.common;

import java.util.List;

public class MenuVo {

    private String name;
    private List<MenuItemVo> menuItemList;

    // Getter／Setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<MenuItemVo> getMenuItemList() {
        return menuItemList;
    }
    public void setMenuItemList(List<MenuItemVo> menuItemList) {
        this.menuItemList = menuItemList;
    }
}
