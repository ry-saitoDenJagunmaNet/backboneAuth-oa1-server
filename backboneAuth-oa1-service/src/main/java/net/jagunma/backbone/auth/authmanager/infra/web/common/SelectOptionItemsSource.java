package net.jagunma.backbone.auth.authmanager.infra.web.common;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JasAtMoment;

/**
 * 共通 コンボボックス選択子群 View Object
 */
public class SelectOptionItemsSource {

    private final List<SelectOptionItemSource> list = newArrayList();

    // コンストラクタ
    SelectOptionItemsSource(Collection<SelectOptionItemSource> collection) {
        this.list.addAll(collection);
    }

    /**
     * ＪＡ群（JasAtMoment）から作成します
     *
     * @param jasAtMoment ＪＡ群
     * @return ＪＡコンボボックス選択肢群
     */
    public static SelectOptionItemsSource createFrom(JasAtMoment jasAtMoment) {
        List<SelectOptionItemSource> list = newArrayList();
        list.add(SelectOptionItemSource.empty());
        for (JaAtMoment jaAtMoment : jasAtMoment.getValue()) {
            list.add(new SelectOptionItemSource(
                jaAtMoment.getIdentifier(),
                jaAtMoment.getJaAttribute().getJaCode().getValue(),
                jaAtMoment.getJaAttribute().getName()
            ));
        }
        return new SelectOptionItemsSource(list);
    }

    /**
     * 店舗群（BranchesAtMoment）から作成します
     *
     * @param branchesAtMoment 店舗群
     * @return 店舗コンボボックス選択肢群
     */
    public static SelectOptionItemsSource createFrom(BranchesAtMoment branchesAtMoment) {
        List<SelectOptionItemSource> list = newArrayList();
        list.add(SelectOptionItemSource.empty());
        for (BranchAtMoment branchAtMoment : branchesAtMoment.getValue()) {
            list.add(new SelectOptionItemSource(
                branchAtMoment.getIdentifier(),
                branchAtMoment.getBranchAttribute().getBranchCode().getValue(),
                branchAtMoment.getBranchAttribute().getName()
            ));
        }
        return new SelectOptionItemsSource(list);
    }

    /**
     * 取引グループ群（BizTranGrps）から作成します
     *
     * @param bizTranGrps 取引グループ群
     * @return 取引グループコンボボックス選択肢群
     */
    public static SelectOptionItemsSource createFrom(BizTranGrps bizTranGrps) {
        List<SelectOptionItemSource> list = newArrayList();
        list.add(SelectOptionItemSource.empty());
        for (BizTranGrp bizTranGrp : bizTranGrps.getValues()) {
            list.add(new SelectOptionItemSource(
                bizTranGrp.getBizTranGrpId(),
                bizTranGrp.getBizTranGrpCode(),
                bizTranGrp.getBizTranGrpName()
            ));
        }
        return new SelectOptionItemsSource(list);
    }

    /**
     * 取引群（BizTrans）から作成します
     *
     * @param bizTrans 取引群
     * @return 取引コンボボックス選択肢群
     */
    public static SelectOptionItemsSource createFrom(BizTrans bizTrans) {
//        List<SelectOptionItemSource> list = newArrayList();
//        list.add(SelectOptionItemSource.empty());
//        for (BizTran bizTran : bizTrans.getValues()) {
//            list.add(new SelectOptionItemSource(
//                bizTran.getBizTranId(),
//                bizTran.getBizTranCode(),
//                bizTran.getBizTranName()
//            ));
//        }
//        return new SelectOptionItemsSource(list);
        return createFrom(bizTrans.getValues());
    }

    /**
     * 取引リスト（List<BizTran>）から作成します
     *
     * @param bizTranList 取引リスト
     * @return 取引コンボボックス選択肢群
     */
    public static SelectOptionItemsSource createFrom(List<BizTran> bizTranList) {
        List<SelectOptionItemSource> list = newArrayList();
        list.add(SelectOptionItemSource.empty());
        for (BizTran bizTran : bizTranList) {
            list.add(new SelectOptionItemSource(
                bizTran.getBizTranId(),
                bizTran.getBizTranCode(),
                bizTran.getBizTranName()
            ));
        }
        return new SelectOptionItemsSource(list);
    }

    /**
     * サブシステム（enum）から作成します
     * <pre>
     * 記述例
     *   SelectOptionItemsSource.createFrom(SubSystem.values())
     * </pre>
     *
     * @param subSystems サブシステム（enum）配列
     * @return サブシステムコンボボックス選択肢群
     */
    public static SelectOptionItemsSource createFrom(SubSystem[] subSystems) {
        List<SelectOptionItemSource> list = newArrayList();
        list.add(SelectOptionItemSource.empty());
        for (SubSystem subSystem : subSystems) {
            if (subSystem.getCode().length() == 0) { continue; }
            list.add(new SelectOptionItemSource(
                null,
                subSystem.getCode(),
                subSystem.getName()
            ));
        }
        return new SelectOptionItemsSource(list);
    }

    /**
     * 共通 コンボボックス選択子リストを取得します
     *
     * @return 共通 コンボボックス選択子リスト
     */
    public List<SelectOptionItemSource> getValue() {
        return list;
    }
}
