package net.jagunma.backbone.auth.authmanager.infra.web.oa12020.vo;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfResponseVo;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemSource;

/**
 * OA12010 View Object
 */
public class Oa12020Vo extends BaseOfResponseVo {

    private static final long serialVersionUID = 1L;

    /**
     * ＪＡID
     */
    private Long jaId;
    /**
     * ＪＡコンボボックスItemsSource
     */
    private List<SelectOptionItemSource> jaItemsSource;
    /**
     * 店舗ID
     */
    private Long branchId;
    /**
     * 店舗コンボボックスItemsSource
     */
    private List<SelectOptionItemSource> branchItemsSource;
    /**
     * サブシステムコード
     */
    private String subSystemCode;
    /**
     * ブシステムコンボボックスItemsSource
     */
    private List<SelectOptionItemSource> subSystemItemsSource;
    /**
     * 取引グループID
     */
    private Long bizTranGrpId;
    /**
     * 取引グループコンボボックスItemsSource
     */
    private List<SelectOptionItemSource> bizTranGrpItemsSource;
    /**
     * 取引ID
     */
    private Long bizTranId;
    /**
     * 取引コンボボックスItemsSource
     */
    private List<SelectOptionItemSource> bizTranItemsSource;
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
     * 一時取引抑止検索結果一覧
     */
    private List<Oa12020SearchResultVo> searchResultList;
    /**
     * 一時取引抑止一覧表示ページ
     */
    private Integer pageNo;

    // Getter／Setter
    public Long getJaId() {
        return jaId;
    }
    public void setJaId(Long jaId) {
        this.jaId = jaId;
    }
    public List<SelectOptionItemSource> getJaItemsSource() {
        return jaItemsSource;
    }
    public void setJaItemsSource(List<SelectOptionItemSource> jaItemsSource) {
        this.jaItemsSource = jaItemsSource;
    }
    public Long getBranchId() {
        return branchId;
    }
    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
    public List<SelectOptionItemSource> getBranchItemsSource() {
        return branchItemsSource;
    }
    public void setBranchItemsSource(List<SelectOptionItemSource> branchItemsSource) {
        this.branchItemsSource = branchItemsSource;
    }
    public String getSubSystemCode() {
        return subSystemCode;
    }
    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode;
    }
    public List<SelectOptionItemSource> getSubSystemItemsSource() {
        return subSystemItemsSource;
    }
    public void setSubSystemItemsSource(List<SelectOptionItemSource> subSystemItemsSource) {
        this.subSystemItemsSource = subSystemItemsSource;
    }
    public Long getBizTranGrpId() {
        return bizTranGrpId;
    }
    public void setBizTranGrpId(Long bizTranGrpId) {
        this.bizTranGrpId = bizTranGrpId;
    }
    public List<SelectOptionItemSource> getBizTranGrpItemsSource() {
        return bizTranGrpItemsSource;
    }
    public void setBizTranGrpItemsSource(List<SelectOptionItemSource> bizTranGrpItemsSource) {
        this.bizTranGrpItemsSource = bizTranGrpItemsSource;
    }
    public Long getBizTranId() {
        return bizTranId;
    }
    public void setBizTranId(Long bizTranId) {
        this.bizTranId = bizTranId;
    }
    public List<SelectOptionItemSource> getBizTranItemsSource() {
        return bizTranItemsSource;
    }
    public void setBizTranItemsSource(List<SelectOptionItemSource> bizTranItemsSource) {
        this.bizTranItemsSource = bizTranItemsSource;
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
    public List<Oa12020SearchResultVo> getSearchResultList() {
        return searchResultList;
    }
    public void setSearchResultList(List<Oa12020SearchResultVo> searchResultList) {
        this.searchResultList = searchResultList;
    }
    public Integer getPageNo() {
        return pageNo;
    }
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
