package net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo;

import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfVo;

/**
 * OA11010検索レスポンス
 */
public class Oa11010SearchResponseVo extends BaseOfVo {

    private static final long serialVersionUID = 1L;

    /**
     * オペレーターTable
     */
    private String operatorTable;
    /**
     * オペレーター一覧pagination
     */
    private String pagination;

    // Getter／Setter
    public String getOperatorTable() {
        return operatorTable;
    }
    public void setOperatorTable(String operatorTable) {
        this.operatorTable = operatorTable;
    }
    public String getPagination() {
        return pagination;
    }
    public void setPagination(String pagination) {
        this.pagination = pagination;
    }
}
