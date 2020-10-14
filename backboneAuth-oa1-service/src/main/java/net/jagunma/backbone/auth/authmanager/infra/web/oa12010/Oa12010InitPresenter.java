package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;

/**
 * OA12010 取引ロール編成インポート＆エクスポート 画面初期表示 Presenter
 */
public class Oa12010InitPresenter {

    private String mode;
    private String subSystemCode;

    /**
     * インポート／エクスポート選択モードのＳｅｔ
     *
     * @param mode インポート／エクスポート選択モード
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * サブシステムコードのＳｅｔ
     *
     * @param subSystemCode サブシステムコード
     */
    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode;
    }

    /**
     * voに変換に変換します。
     *
     * @param vo 取引ロール編成インポート＆エクスポートView Object
     */
    public void bindTo(Oa12010Vo vo) {
        vo.setMode(mode);
        vo.setSubSystemCode(subSystemCode);
        vo.setSubSystemList(SelectOptionItemsSource.createFrom(SubSystem.values()).getValue());
    }
}
