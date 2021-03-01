package net.jagunma.backbone.auth.authmanager.infra.api.oa31020;

import java.util.List;

/**
 * Oa31010 サインイン Result
 */
public class Oa31020AccessibleResult {

    private String subSystemCode;
    private List<String> bizTranCodeList;

    /**
     * サブシステムコードのＧｅｔ
     *
     * @return サブシステムコード
     */
    public String getSubSystemCode() {
        return subSystemCode;
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
     * 取引コードリストのＧｅｔ
     *
     * @return 取引コードリスト
     */
    public List<String> getBizTranCodeList() {
        return bizTranCodeList;
    }
    /**
     * 取引コードリストのＳｅｔ
     *
     * @param bizTranCodeList 取引コードリスト
     */
    public void setBizTranCodeList(List<String> bizTranCodeList) {
        this.bizTranCodeList = bizTranCodeList;
    }
}
