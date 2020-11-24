package net.jagunma.backbone.auth.authmanager.infra.web.oa12020;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12020.vo.Oa12020SearchResultVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12020.vo.Oa12020Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTrans;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;

/**
 * OA12020 一時取引抑止<一覧> Response Presenter
 */
class Oa12020Presenter implements SuspendBizTranSearchResponse {

    /**
     *一時取引抑止一覧の１ページ当たりの行数
     */
    private final int PAGE_SIZE = 10;

    private Long jaId;
    private Long  branchId;
    private String  subSystemCode;
    private Long  bizTranGrpId;
    private Long  bizTranId;
    private Integer suspendConditionsSelect;
    private LocalDate suspendStatusDate;
    private LocalDate suspendStatusStartDateFrom;
    private LocalDate suspendStatusStartDateTo;
    private LocalDate suspendStatusEndDateFrom;
    private LocalDate suspendStatusEndDateTo;
    private String suspendReason;
    private Integer pageNo;
    private SuspendBizTrans suspendBizTrans;
    private Integer paginationLastPageNo;

    // コンストラクタ
    public Oa12020Presenter() {}
    Oa12020Presenter(Oa12020Vo vo) {
        this.jaId = vo.getJaId();
        this.branchId = vo.getBranchId();
        this.subSystemCode = vo.getSubSystemCode();
        this.bizTranGrpId = vo.getBizTranGrpId();
        this.bizTranId = vo.getBizTranId();
        this.suspendConditionsSelect = vo.getSuspendConditionsSelect();
        this.suspendStatusDate = vo.getSuspendStatusDate();
        this.suspendStatusStartDateFrom = vo.getSuspendStatusStartDateFrom();
        this.suspendStatusStartDateTo = vo.getSuspendStatusStartDateTo();
        this.suspendStatusEndDateFrom = vo.getSuspendStatusEndDateFrom();
        this.suspendStatusEndDateTo = vo.getSuspendStatusEndDateTo();
        this.pageNo = vo.getPageNo();
        this.suspendReason = vo.getSuspendReason();
    }

    // ファクトリーメソッド
    public static Oa12020Presenter with(Oa12020Vo vo) {
        return new Oa12020Presenter(vo);
    }

    /**
     * ＪＡIDのＳｅｔ
     *
     * @param jaId ＪＡID
     */
    public void setJaId(Long jaId) {
        this.jaId = jaId;
    }
    /**
     * 店舗IDのＳｅｔ
     *
     * @param branchId 店舗ID
     */
    public void setBranchId(Long branchId) {
        this.branchId = branchId;
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
     * 取引グループIDのＳｅｔ
     *
     * @param bizTranGrpId 取引グループID
     */
    public void setBizTranGrpId(Long bizTranGrpId) {
        this.bizTranGrpId = bizTranGrpId;
    }
    /**
     * 取引IDのＳｅｔ
     *
     * @param bizTranId 取引ID
     */
    public void setBizTranId(Long bizTranId) {
        this.bizTranId = bizTranId;
    }
    /**
     * 抑止期間条件選択のＳｅｔ
     *
     * @param suspendConditionsSelect 抑止期間条件選択
     */
    public void setSuspendConditionsSelect(Integer suspendConditionsSelect) {
        this.suspendConditionsSelect = suspendConditionsSelect;
    }
    /**
     * 抑止期間 状態指定日のＳｅｔ
     *
     * @param suspendStatusDate 抑止期間 状態指定日
     */
    public void setSuspendStatusDate(LocalDate suspendStatusDate) {
        this.suspendStatusDate = suspendStatusDate;
    }
    /**
     * 抑止期間 条件指定開始（開始日）のＳｅｔ
     *
     * @param suspendStatusStartDateFrom 抑止期間 条件指定開始（開始日）
     */
    public void setSuspendStatusStartDateFrom(LocalDate suspendStatusStartDateFrom) {
        this.suspendStatusStartDateFrom = suspendStatusStartDateFrom;
    }
    /**
     * 抑止期間 条件指定開始（終了日）のＳｅｔ
     *
     * @param suspendStatusStartDateTo 抑止期間 条件指定開始（終了日）
     */
    public void setSuspendStatusStartDateTo(LocalDate suspendStatusStartDateTo) {
        this.suspendStatusStartDateTo = suspendStatusStartDateTo;
    }
    /**
     * 抑止期間 条件指定終了（開始日）のＳｅｔ
     *
     * @param suspendStatusEndDateFrom 抑止期間 条件指定終了（開始日）
     */
    public void setSuspendStatusEndDateFrom(LocalDate suspendStatusEndDateFrom) {
        this.suspendStatusEndDateFrom = suspendStatusEndDateFrom;
    }
    /**
     * 抑止期間 条件指定終了（終了日）のＳｅｔ
     *
     * @param suspendStatusEndDateTo 抑止期間 条件指定終了（終了日）
     */
    public void setSuspendStatusEndDateTo(LocalDate suspendStatusEndDateTo) {
        this.suspendStatusEndDateTo = suspendStatusEndDateTo;
    }
    /**
     * 抑止理由のＳｅｔ
     *
     * @param suspendReason 抑止理由
     */
    public void setSuspendReason(String suspendReason) {
        this.suspendReason = suspendReason;
    }
    /**
     * 一時取引抑止一覧表示ページのＳｅｔ
     *
     * @param pageNo 一時取引抑止一覧表示ページ
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 一時取引抑止群のＳｅｔ
     *
     * @param suspendBizTrans 一時取引抑止群
     */
    public void setSuspendBizTrans(SuspendBizTrans suspendBizTrans) {
        this.suspendBizTrans = suspendBizTrans;
    }

    /**
     * 一時取引抑止検索結果一覧最終ページのＳｅｔ
     *
     * @param paginationLastPageNo 一時取引抑止検索結果一覧最終ページ
     */
    public void setPaginationLastPageNo(Integer paginationLastPageNo) {
        this.paginationLastPageNo = paginationLastPageNo;
    }

    /**
     * voに変換します
     *
     * @param vo 一時取引抑止<一覧> View Object
     */
    public void bindTo(Oa12020Vo vo) {

        vo.setJaId(jaId);
        vo.setBranchId(branchId);
        vo.setSubSystemCode(subSystemCode);
        vo.setBizTranGrpId(bizTranGrpId);
        vo.setBizTranId(bizTranId);
        vo.setSuspendConditionsSelect(suspendConditionsSelect);
        vo.setSuspendStatusDate(suspendStatusDate);
        vo.setSuspendStatusStartDateFrom(suspendStatusStartDateFrom);
        vo.setSuspendStatusStartDateTo(suspendStatusStartDateTo);
        vo.setSuspendStatusEndDateFrom(suspendStatusEndDateFrom);
        vo.setSuspendStatusEndDateTo(suspendStatusEndDateTo);
        vo.setSuspendReason(suspendReason);
        vo.setPageNo(pageNo);
        vo.setSearchResultList(createSearchResultList());
        vo.setPaginationLastPageNo(calculationPaginationLastPageNo());
    }

    /**
     * 一時取引抑止リストを作成します
     *
     * @return 一時取引抑止リスト
     */
    List<Oa12020SearchResultVo> createSearchResultList() {

        List<Oa12020SearchResultVo> list = newArrayList();
        if (suspendBizTrans == null) {return list;}

        for (SuspendBizTran suspendBizTran : suspendBizTrans.getValues()) {
            Oa12020SearchResultVo searchResultVo = new Oa12020SearchResultVo();
            searchResultVo.setSuspendBizTranId(suspendBizTran.getSuspendBizTranId());
            if (suspendBizTran.getJaId() != null){
                searchResultVo.setJaCode(suspendBizTran.getJaAtMoment().getJaAttribute().getJaCode().getValue());
                searchResultVo.setJaName(suspendBizTran.getJaAtMoment().getJaAttribute().getName());
            }
            if (suspendBizTran.getBranchId() != null) {
                searchResultVo.setBranchCode(suspendBizTran.getBranchAtMoment().getBranchAttribute().getBranchCode().getValue());
                searchResultVo.setBranchName(suspendBizTran.getBranchAtMoment().getBranchAttribute().getName());
            }
            if (suspendBizTran.getSubSystemCode() != null) {
                searchResultVo.setSubSystemName(SubSystem.codeOf(suspendBizTran.getSubSystemCode()).getName());
            }
            if (suspendBizTran.getBizTranGrpId() != null) {
                searchResultVo.setBizTranGrpCode(suspendBizTran.getBizTranGrp().getBizTranGrpCode());
                searchResultVo.setBizTranGrpName(suspendBizTran.getBizTranGrp() .getBizTranGrpName());
            }
            if (suspendBizTran.getBizTranId() != null) {
                searchResultVo.setBizTranCode(suspendBizTran.getBizTran().getBizTranCode());
                searchResultVo.setBizTranName(suspendBizTran.getBizTran().getBizTranName());
            }
            searchResultVo.setSuspendStartDate(suspendBizTran.getSuspendStartDate());
            searchResultVo.setSuspendEndDate(suspendBizTran.getSuspendEndDate());
            searchResultVo.setSuspendReason(suspendBizTran.getSuspendReason());
            list.add(searchResultVo);
        }

        // 対象ページの表示行のみ出力
        int skipcnt = PAGE_SIZE * pageNo - PAGE_SIZE;
        return list.stream().skip(skipcnt).limit(PAGE_SIZE).collect(Collectors.toList());
    }

    /**
     * Paginationの最終ページを計算します
     *
     * @return Paginationの最終ページ
     */
    Integer calculationPaginationLastPageNo() {
        if (suspendBizTrans == null) {return 0;}
        BigDecimal lastPage = BigDecimal.valueOf(suspendBizTrans.getValues().size()).divide(
            BigDecimal.valueOf(PAGE_SIZE), 0, RoundingMode.UP);
        return lastPage.intValue();
    }
}
