package net.jagunma.backbone.auth.authmanager.infra.web.common.vo;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchesAtMoment;

/**
 * 共通 コンボボックス選択子群 View Object
 */
public class SelectOptionVos {

    private final ArrayList<SelectOptionVo> list = newArrayList();

    // コンストラクタ
    SelectOptionVos(Collection<SelectOptionVo> collection) {
        this.list.addAll(collection);
    }

    /**
     * 店舗群（BranchesAtMoment）から作成します。
     *
     * @param branchesAtMoment 店舗群
     * @return 店舗コンボボックス選択子群
     */
    public static SelectOptionVos createFrom(BranchesAtMoment branchesAtMoment) {
        List<SelectOptionVo> list = newArrayList();
        list.add(SelectOptionVo.empty());
        for (BranchAtMoment branchAtMoment : branchesAtMoment.getValue()) {
            list.add(new SelectOptionVo(
                branchAtMoment.getIdentifier()
                , branchAtMoment.getBranchAttribute().getBranchCode().getValue()
                , branchAtMoment.getBranchAttribute().getName()
            ));
        }
        return new SelectOptionVos(list);
    }

    /**
     * サブシステム（enum）から作成します。
     *
     * @return サブシステムコンボボックス選択子群
     */
    public static SelectOptionVos createFromSubSystem() {
        List<SelectOptionVo> list = newArrayList();
        list.add(SelectOptionVo.empty());
        for (SubSystem subSystem : SubSystem.values()) {
            if (subSystem.getCode().length() == 0) { continue; }
            list.add(new SelectOptionVo(
                null
                , subSystem.getCode()
                , subSystem.getName()
            ));

        }
        return new SelectOptionVos(list);
    }

    /**
     * 共通 コンボボックス選択子リストを取得します。
     *
     * @return 共通 コンボボックス選択子リスト
     */
    public ArrayList<SelectOptionVo> getValue() {
        return list;
    }
}
