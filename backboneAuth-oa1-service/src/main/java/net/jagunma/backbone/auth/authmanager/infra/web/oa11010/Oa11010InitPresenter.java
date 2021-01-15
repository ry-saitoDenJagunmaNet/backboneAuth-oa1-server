package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010BizTranRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SubSystemRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.values.model.branch.BranchesAtMoment;

/**
 * OA11010 初期表示 Presenter
 */
class Oa11010InitPresenter {

    private long jaId;
    private String jaCode;
    private String jaName;
    private BranchesAtMoment branchesAtMoment;
    private Integer validThruSelect;
    private Integer subSystemRoleConditionsSelect;
    private Integer bizTranRoleConditionsSelect;
    private BizTranRoles bizTranRoles;
    private String responseMethod;

    // コンストラクタ
    Oa11010InitPresenter() {}

    /**
     * ＪＡIDのＳｅｔ
     *
     * @param jaId ＪＡID
     */
    public void setJaId(long jaId) {
        this.jaId = jaId;
    }
    /**
     * ＪＡコードのＳｅｔ
     *
     * @param jaCode ＪＡコード
     */
    public void setJaCode(String jaCode) {
        this.jaCode = jaCode;
    }
    /**
     * ＪＡ名のＳｅｔ
     *
     * @param jaName ＪＡ名
     */
    public void setJaName(String jaName) {
        this.jaName = jaName;
    }
    /**
     * 店舗群AtMomentのＳｅｔ
     *
     * @param branchesAtMoment 店舗群AtMoment
     */
    public void setBranchesAtMoment(BranchesAtMoment branchesAtMoment) {
        this.branchesAtMoment = branchesAtMoment;
    }
    /**
     * 有効期限選択のＳｅｔ
     *
     * @param validThruSelect 有効期限選択
     */
    public void setValidThruSelect(Integer validThruSelect) {
        this.validThruSelect = validThruSelect;
    }
    /**
     * サブシステムロール条件選択のＳｅｔ
     *
     * @param subSystemRoleConditionsSelect サブシステムロール条件選択
     */
    public void setSubSystemRoleConditionsSelect(Integer subSystemRoleConditionsSelect) {
        this.subSystemRoleConditionsSelect = subSystemRoleConditionsSelect;
    }
    /**
     * 取引ロール条件選択のＳｅｔ
     *
     * @param bizTranRoleConditionsSelect 取引ロール条件選択
     */
    public void setBizTranRoleConditionsSelect(Integer bizTranRoleConditionsSelect) {
        this.bizTranRoleConditionsSelect = bizTranRoleConditionsSelect;
    }
    /**
     * 取引ロール群のＳｅｔ
     *
     * @param bizTranRoles 取引ロール群
     */
    public void setBizTranRoles(BizTranRoles bizTranRoles) {
        this.bizTranRoles = bizTranRoles;
    }
    /**
     * 入力補助として使用する場合の戻り先のＳｅｔ
     *
     * @param responseMethod 入力補助として使用する場合の戻り先
     */
    public void setResponseMethod(String responseMethod) {
        this.responseMethod = responseMethod;
    }

    /**
     * voに変換します
     *
     * @param vo オペレーター＜一覧＞View Object
     */
    public void bindTo(Oa11010Vo vo) {

        // ＪＡ
        vo.setJa(jaCode + " " + jaName);
        vo.setJaId(jaId);
        // 店舗リスト
        vo.setBranchItemsSource(SelectOptionItemsSource.createFrom(branchesAtMoment).getValue());
        // 有効期限選択
        vo.setValidThruSelect(validThruSelect);
        // サブシステムロール条件選択
        vo.setSubSystemRoleConditionsSelect(subSystemRoleConditionsSelect);
        // サブシステムロールリスト
        List<Oa11010SubSystemRoleVo> subSystemRoleVoList = newArrayList();
        for(SubSystemRole subSystemRole : SubSystemRole.values()) {
            if (subSystemRole.getCode().length() == 0) { continue; }
            Oa11010SubSystemRoleVo subSystemRoleVo = new Oa11010SubSystemRoleVo();
            subSystemRoleVo.setSubSystemRoleCode(subSystemRole.getCode());
            subSystemRoleVo.setSubSystemRoleName(subSystemRole.getDisplayName());
            subSystemRoleVo.setValidThruSelect(0);
            subSystemRoleVoList.add(subSystemRoleVo);
        }
        vo.setSubSystemRoleList(subSystemRoleVoList);
        // 取引ロール条件選択
        vo.setBizTranRoleConditionsSelect(bizTranRoleConditionsSelect);
        // 取引ロールサブシステムリスト
        vo.setBizTranRoleSubSystemList(SelectOptionItemsSource.createFrom(SubSystem.values()).getValue());
        // 取引ロールリスト
        List<Oa11010BizTranRoleVo> bizTranRoleVoList = newArrayList();
        for (BizTranRole bizTranRole :  bizTranRoles.getValues()) {
            Oa11010BizTranRoleVo bizTranRoleVo = new Oa11010BizTranRoleVo();
            bizTranRoleVo.setBizTranRoleId(bizTranRole.getBizTranRoleId());
            bizTranRoleVo.setBizTranRoleCode(bizTranRole.getBizTranRoleCode());
            bizTranRoleVo.setBizTranRoleName(bizTranRole.getBizTranRoleName());
            bizTranRoleVo.setSubSystemCode(bizTranRole.getSubSystemCode());
            bizTranRoleVo.setValidThruSelect(0);
            bizTranRoleVoList.add(bizTranRoleVo);
        }
        vo.setBizTranRoleList(bizTranRoleVoList);
        vo.setResponseMethod(responseMethod);
    }
}
