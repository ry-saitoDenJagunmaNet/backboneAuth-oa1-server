package net.jagunma.backbone.auth.authmanager.infra.web.oa12020.vo;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfResponseVo;

/**
 * OA12010 View Object
 */
public class Oa12020Vo extends BaseOfResponseVo {

    private static final long serialVersionUID = 1L;

    /**
     * ＪＡコード
     */
    private String jaCode;
    /**
     * 店舗コード
     */
    private String branchCode;
    /**
     * サブシステムコード
     */
    private String subSystemCode;
    /**
     * 取引グループコード
     */
    private String bizTranGrpCode;
    /**
     * 取引コード
     */
    private String bizTranCode;
    /**
     * 抑止期間条件選択
     */
    private Integer suspendConditionsSelect;
    /**
     * 抑止期間 状態指定日
     */
    private LocalDate suspendStatusDate;
    /**
     * 抑止期間 条件指定開始（開始日）
     */
    private LocalDate suspendStatusStartDateFrom;
    /**
     * 抑止期間 条件指定開始（終了日）
     */
    private LocalDate suspendStatusStartDateTo;
    /**
     * 抑止期間 条件指定終了（開始日）
     */
    private LocalDate suspendStatusEndDateFrom;
    /**
     * 抑止期間 条件指定終了（終了日）
     */
    private LocalDate suspendStatusEndDateTo;
    /**
     * 抑止理由
     */
    private String suspendReason;
    /**
     * 一時取引抑止一覧表示ページ
     */
    private Integer pageNo;
    /**
     * 一時取引抑止検索結果一覧
     */
    private List<Oa12020SearchResultVo> searchResultList;
    /**
     * 一時取引抑止検索結果一覧最終ページ
     */
    private Integer paginationLastPageNo;

    // Getter／Setter
    public String getJaCode() {
        return jaCode;
    }
    public void setJaCode(String jaCode) {
        this.jaCode = jaCode;
    }
    public String getBranchCode() {
        return branchCode;
    }
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
    public String getSubSystemCode() {
        return subSystemCode;
    }
    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode;
    }
    public String getBizTranGrpCode() {
        return bizTranGrpCode;
    }
    public void setBizTranGrpCode(String bizTranGrpCode) {
        this.bizTranGrpCode = bizTranGrpCode;
    }
    public String getBizTranCode() {
        return bizTranCode;
    }
    public void setBizTranCode(String bizTranCode) {
        this.bizTranCode = bizTranCode;
    }
    public Integer getSuspendConditionsSelect() {
        return suspendConditionsSelect;
    }
    public void setSuspendConditionsSelect(Integer suspendConditionsSelect) {
        this.suspendConditionsSelect = suspendConditionsSelect;
    }
    public LocalDate getSuspendStatusDate() {
        return suspendStatusDate;
    }
    public void setSuspendStatusDate(LocalDate suspendStatusDate) {
        this.suspendStatusDate = suspendStatusDate;
    }
    public LocalDate getSuspendStatusStartDateFrom() {
        return suspendStatusStartDateFrom;
    }
    public void setSuspendStatusStartDateFrom(LocalDate suspendStatusStartDateFrom) {
        this.suspendStatusStartDateFrom = suspendStatusStartDateFrom;
    }
    public LocalDate getSuspendStatusStartDateTo() {
        return suspendStatusStartDateTo;
    }
    public void setSuspendStatusStartDateTo(LocalDate suspendStatusStartDateTo) {
        this.suspendStatusStartDateTo = suspendStatusStartDateTo;
    }
    public LocalDate getSuspendStatusEndDateFrom() {
        return suspendStatusEndDateFrom;
    }
    public void setSuspendStatusEndDateFrom(LocalDate suspendStatusEndDateFrom) {
        this.suspendStatusEndDateFrom = suspendStatusEndDateFrom;
    }
    public LocalDate getSuspendStatusEndDateTo() {
        return suspendStatusEndDateTo;
    }
    public void setSuspendStatusEndDateTo(LocalDate suspendStatusEndDateTo) {
        this.suspendStatusEndDateTo = suspendStatusEndDateTo;
    }
    public String getSuspendReason() {
        return suspendReason;
    }
    public void setSuspendReason(String suspendReason) {
        this.suspendReason = suspendReason;
    }
    public Integer getPageNo() {
        return pageNo;
    }
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
    public List<Oa12020SearchResultVo> getSearchResultList() {
        return searchResultList;
    }
    public void setSearchResultList(List<Oa12020SearchResultVo> searchResultList) {
        this.searchResultList = searchResultList;
    }
    public Integer getPaginationLastPageNo() {
        return paginationLastPageNo;
    }
    public void setPaginationLastPageNo(Integer paginationLastPageNo) {
        this.paginationLastPageNo = paginationLastPageNo;
    }
}
