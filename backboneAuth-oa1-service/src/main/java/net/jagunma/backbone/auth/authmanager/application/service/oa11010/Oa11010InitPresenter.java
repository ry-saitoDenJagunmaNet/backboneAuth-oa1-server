package net.jagunma.backbone.auth.authmanager.application.service.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoReferenceDto;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010BizTranRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SubSystemRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.role.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.role.subSystemRole.SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.subSystem.SubSystem;

/**
 * OA11010 オペレーター＜一覧＞ 画面初期表示 Presenter
 */
class Oa11010InitPresenter {

    private long jaId;
    private String jaCode;
    private String jaName;
    private List<TempoReferenceDto> tempoReferenceDtoList;
    private Integer expirationSelect;
    private Integer subSystemRoleConditionsSelect;
    private List<Oa11010SubSystemRoleVo> subSystemRoleList;
    private Integer bizTranRoleConditionsSelect;
    private List<SubSystem> bizTranRoleSubSystemList;
    private List<Oa11010BizTranRoleVo> bizTranRoleList;

    // コンストラクタ
    Oa11010InitPresenter() {
    }

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
     * 店舗コンボボックスリストのＳｅｔ
     *
     * @param tempoReferenceDtoList 舗コンボボックスリスト
     */
    public void setTempoReferenceDtoList(List<TempoReferenceDto> tempoReferenceDtoList) {
        this.tempoReferenceDtoList = tempoReferenceDtoList;
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
     * 有効期限選択のＳｅｔ
     *
     * @param expirationSelect 有効期限選択
     */
    public void setExpirationSelect(Integer expirationSelect) {
        this.expirationSelect = expirationSelect;
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
    public void setBiztranRoleConditionsSelect(Integer bizTranRoleConditionsSelect) {
        this.bizTranRoleConditionsSelect = bizTranRoleConditionsSelect;
    }

    /**
     * 取引ロールサブシステムコンボボックスリストのＳｅｔ
     *
     * @param bizTranRoleSubSystemList 取引ロールサブシステムコンボボックスリスト
     */
    public void setBizTranRoleSubSystemList(List<SubSystem> bizTranRoleSubSystemList) {
        this.bizTranRoleSubSystemList = bizTranRoleSubSystemList;
    }

    /**
     * voに変換に変換します。
     *
     * @param vo オペレーター＜一覧＞View Object
     */
    public void bindTo(Oa11010Vo vo) {
        vo.setJa(jaCode + " " + jaName);
        vo.setJaId(jaId);

        vo.setTempoReferenceDtoList(tempoReferenceDtoList);
        vo.setExpirationSelect(expirationSelect);
        vo.setSubSystemRoleConditionsSelect(subSystemRoleConditionsSelect);
        vo.setSubSystemRoleList(subSystemRoleList);
        vo.setBizTranRoleConditionsSelect(bizTranRoleConditionsSelect);
        vo.setBizTranRoleSubSystemList(bizTranRoleSubSystemList);
        vo.setBizTranRoleList(bizTranRoleList);
    }

    /**
     * サブシステムロールリストを取得します。
     *
     * @param subSystemRoles サブシステムロールＤｔｏリスト
     */
    public void getSubsystemRoleList(List<SubSystemRole> subSystemRoles) {

        List<Oa11010SubSystemRoleVo> list = newArrayList();
        for (SubSystemRole subSystemRole : subSystemRoles) {
            Oa11010SubSystemRoleVo item = new Oa11010SubSystemRoleVo();
            item.setSubSystemRoleSelected((short) 0);
            item.setSubSystemRoleId(0);
            item.setSubSystemRoleCode(subSystemRole.getSubSystemRoleCode());
            item.setSubSystemRoleName(subSystemRole.getSubSystemRoleName());
            item.setExpirationSelect(0);
            item.setExpirationStatusDate(null);
            item.setExpirationStartDateFrom(null);
            item.setExpirationStartDateTo(null);
            item.setExpirationEndDateFrom(null);
            item.setExpirationEndDateTo(null);

            list.add(item);
        }
        subSystemRoleList = list;
    }

    /**
     * 取引ロールリストを取得します。
     *
     * @param bizTranRoles 取引ロールＤｔｏリスト
     */
    public void getBizTranRoleList(List<BizTranRole> bizTranRoles) {

        List<Oa11010BizTranRoleVo> list = newArrayList();
        for (BizTranRole bizTranRole : bizTranRoles) {
            Oa11010BizTranRoleVo item = new Oa11010BizTranRoleVo();
            item.setBizTranRoleSelected((short) 0);
            item.setBizTranRoleId(0);
            item.setBizTranRoleCode(bizTranRole.getBizTranRoleCode());
            item.setBizTranRoleName(bizTranRole.getBizTranRoleName());
            item.setSubSystemCode(bizTranRole.getSubSystemCode());
            item.setExpirationSelect(0);
            item.setExpirationStatusDate(null);
            item.setExpirationStartDateFrom(null);
            item.setExpirationStartDateTo(null);
            item.setExpirationEndDateFrom(null);
            item.setExpirationEndDateTo(null);
            list.add(item);
        }
        bizTranRoleList = list;
    }
}
