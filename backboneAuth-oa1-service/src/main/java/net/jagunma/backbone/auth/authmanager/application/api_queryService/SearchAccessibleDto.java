package net.jagunma.backbone.auth.authmanager.application.api_queryService;

import java.util.List;

/**
 * 可能取引 Dto
 */
public class SearchAccessibleDto {

    /**
     * サブシステムコード
     */
    String subSystemCode;
    /**
     * 取引コードリスト
     */
    List<String> bizTranCodeList;

    // Getter／Setter
    public String getSubSystemCode() {
        return subSystemCode;
    }
    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode;
    }
    public List<String> getBizTranCodeList() {
        return bizTranCodeList;
    }
    public void setBizTranCodeList(List<String> bizTranCodeList) {
        this.bizTranCodeList = bizTranCodeList;
    }
}
