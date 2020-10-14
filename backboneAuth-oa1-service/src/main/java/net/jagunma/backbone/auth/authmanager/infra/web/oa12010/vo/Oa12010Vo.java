package net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfResponseVo;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemSource;

/**
 * OA12010 View Object
 */
public class Oa12010Vo extends BaseOfResponseVo {

    private static final long serialVersionUID = 1L;

    /**
     * インポート／エクスポート選択モード
     */
    private String mode;
    /**
     * サブシステムコード
     */
    private String subSystemCode;
    /**
     * サブシステムコンボボックスリスト
     */
    private List<SelectOptionItemSource> subSystemList;
    /**
     * エクスポートExcelBook
     */
    private byte[] exportExcelBook;

    // Getter／Setter
    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    public String getSubSystemCode() {
        return subSystemCode;
    }
    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode;
    }
    public List<SelectOptionItemSource> getSubSystemList() {
        return subSystemList;
    }
    public void setSubSystemList(List<SelectOptionItemSource> subSystemList) {
        this.subSystemList = subSystemList;
    }
    public byte[] getExportExcelBook() {
        return exportExcelBook;
    }
    public void setExportExcelBook(byte[] exportExcelBook) {
        this.exportExcelBook = exportExcelBook;
    }
}
