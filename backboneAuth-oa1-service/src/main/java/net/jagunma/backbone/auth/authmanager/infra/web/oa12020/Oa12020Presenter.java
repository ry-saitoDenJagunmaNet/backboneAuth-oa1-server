package net.jagunma.backbone.auth.authmanager.infra.web.oa12020;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.util.List;
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

//    private JasAtMoment jasAtMoment;
//    private BranchesAtMoment branchesAtMoment;
//    private BizTranGrps bizTranGrps;
//    private BizTrans bizTrans;
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
    private SuspendBizTrans suspendBizTrans;
    private Integer pageNo;

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
        this.suspendReason = vo.getSuspendReason();
        this.pageNo = vo.getPageNo();
    }

    // ファクトリーメソッド
    public static Oa12020Presenter with(Oa12020Vo vo) {
        return new Oa12020Presenter(vo);
    }

//    /**
//     * JasAtMomentのＳｅｔ
//     *
//     * @param jasAtMoment JasAtMoment
//     */
//    public void setJasAtMoment(JasAtMoment jasAtMoment) {
//        this.jasAtMoment = jasAtMoment;
//    }
//    /**
//     * BranchesAtMomentのＳｅｔ
//     *
//     * @param branchesAtMoment BranchesAtMoment
//     */
//    public void setBranchesAtMoment(BranchesAtMoment branchesAtMoment) {
//        this.branchesAtMoment = branchesAtMoment;
//    }
//    /**
//     * 取引グループ群のＳｅｔ
//     *
//     * @param bizTranGrps 取引グループ群
//     */
//    public void setBizTranGrps(BizTranGrps bizTranGrps) {
//        this.bizTranGrps = bizTranGrps;
//    }
//    /**
//     * 取引群のＳｅｔ
//     *
//     * @param bizTrans 取引群
//     */
//    public void setBizTrans(BizTrans bizTrans) {
//        this.bizTrans = bizTrans;
//    }
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
     * 一時取引抑止群のＳｅｔ
     *
     * @param suspendBizTrans 一時取引抑止群
     */
    public void setSuspendBizTrans(SuspendBizTrans suspendBizTrans) {
        this.suspendBizTrans = suspendBizTrans;
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
     * voに変換します
     *
     * @param vo 一時取引抑止<一覧> View Object
     */
    public void bindTo(Oa12020Vo vo) {

//        // ＪＡコンボボックスItemsSource
//        if (jasAtMoment != null) {
//            vo.setJaItemsSource(SelectOptionItemsSource.createFrom(jasAtMoment).getValue());
//        }
//        // 店舗コンボボックスItemsSource
//        if (branchesAtMoment != null) {
//            vo.setBranchItemsSource(SelectOptionItemsSource.createFrom(branchesAtMoment).getValue());
//        }
//        // サブシステムコンボボックスItemsSource
//        vo.setSubSystemItemsSource(SelectOptionItemsSource.createFrom(SubSystem.values()).getValue());
//        // 取引グループコンボボックスItemsSource
//        if (bizTranGrps != null) {
//            vo.setBizTranGrpItemsSource(SelectOptionItemsSource.createFrom(bizTranGrps).getValue());
//        }
//        // 取引コンボボックスItemsSource
//        if (bizTrans != null) {
//            vo.setBizTranItemsSource(SelectOptionItemsSource.createFrom(bizTrans).getValue());
//        }

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
        List<Oa12020SearchResultVo> list = newArrayList();
        if (suspendBizTrans != null) {
            for (SuspendBizTran suspendBizTran : suspendBizTrans.getValues()) {
                Oa12020SearchResultVo searchResultVo = new Oa12020SearchResultVo();
                searchResultVo.setSuspendBizTranId(suspendBizTran.getSuspendBizTranId());
                searchResultVo.setJaCode(suspendBizTran.getBranchAtMoment().getJaAtMoment().getJaAttribute().getJaCode().getValue());
                searchResultVo.setJaName(suspendBizTran.getBranchAtMoment().getJaAtMoment().getJaAttribute().getName());
                searchResultVo.setBranchCode(suspendBizTran.getBranchAtMoment().getBranchAttribute().getBranchCode().getValue());
                searchResultVo.setBranchName(suspendBizTran.getBranchAtMoment().getBranchAttribute().getName());
                searchResultVo.setSubSystemName(SubSystem.codeOf(suspendBizTran.getSubSystemCode()).getName());
                searchResultVo.setBizTranGrpCode(suspendBizTran.getBizTranGrp().getBizTranGrpCode());
                searchResultVo.setBizTranGrpName(suspendBizTran.getBizTranGrp() .getBizTranGrpName());
                searchResultVo.setBizTranCode(suspendBizTran.getBizTran().getBizTranCode());
                searchResultVo.setBizTranName(suspendBizTran.getBizTran().getBizTranName());
                searchResultVo.setSuspendStartDate(suspendBizTran.getSuspendStartDate());
                searchResultVo.setSuspendEndDate(suspendBizTran.getSuspendEndDate());
                searchResultVo.setSuspendReason(suspendBizTran.getSuspendReason());
                list.add(searchResultVo);
            }
        }
        vo.setSearchResultList(list);
        vo.setPageNo(pageNo);
    }
}
