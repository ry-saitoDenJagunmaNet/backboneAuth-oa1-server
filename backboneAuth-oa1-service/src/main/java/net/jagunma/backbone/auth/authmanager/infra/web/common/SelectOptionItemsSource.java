package net.jagunma.backbone.auth.authmanager.infra.web.common;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
 * 共通 コンボボックス選択肢群 View Object
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
     * @param bizTranGrps      取引グループ群
     * @param isFirstRowInsert 最初の空行挿入（true：最初の空行挿入あり、false：最初の空行挿入なし）
     * @return 取引グループコンボボックス選択肢群
     */
    public static SelectOptionItemsSource createFrom(BizTranGrps bizTranGrps, Boolean isFirstRowInsert) {
        List<SelectOptionItemSource> list = newArrayList();
        if (isFirstRowInsert) {list.add(SelectOptionItemSource.empty());}
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
     * 取引グループ群（BizTranGrps）から作成します
     *
     * @param bizTranGrps 取引グループ群
     * @return 取引グループコンボボックス選択肢群
     */
    public static SelectOptionItemsSource createFrom(BizTranGrps bizTranGrps) {
        return createFrom(bizTranGrps, true);
    }

    /**
     * 取引群（BizTrans）から作成します
     *
     * @param bizTrans         取引群
     * @param isFirstRowInsert 最初の空行挿入（true：最初の空行挿入あり、false：最初の空行挿入なし）
     * @return 取引コンボボックス選択肢群
     */
    public static SelectOptionItemsSource createFrom(BizTrans bizTrans, Boolean isFirstRowInsert) {
        List<SelectOptionItemSource> list = newArrayList();
        if (isFirstRowInsert) {list.add(SelectOptionItemSource.empty());}
        for (BizTran bizTran : bizTrans.getValues()) {
            list.add(new SelectOptionItemSource(
                bizTran.getBizTranId(),
                bizTran.getBizTranCode(),
                bizTran.getBizTranName()
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
        return createFrom(bizTrans, true);
    }

    /**
     * サブシステム（enum）から作成します
     * <pre>
     * 記述例
     *   SelectOptionItemsSource.createFrom(SubSystem.getValidValues())
     * </pre>
     *
     * @param subSystems サブシステム（enum）配列
     * @return サブシステムコンボボックス選択肢群
     */
    public static SelectOptionItemsSource createFrom(SubSystem[] subSystems) {
        List<SubSystem> subSystemList = Arrays.asList(subSystems);
        List<SelectOptionItemSource> list = newArrayList();
        list.add(SelectOptionItemSource.empty());
        for (SubSystem subSystem : subSystemList.stream().sorted(Comparator.comparing(SubSystem::getDisplaySortOrder)).collect(Collectors.toList())) {
            if (subSystem.getCode().length() == 0) { continue; }
            list.add(new SelectOptionItemSource(
                null,
                subSystem.getCode(),
                subSystem.getDisplayName()
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
