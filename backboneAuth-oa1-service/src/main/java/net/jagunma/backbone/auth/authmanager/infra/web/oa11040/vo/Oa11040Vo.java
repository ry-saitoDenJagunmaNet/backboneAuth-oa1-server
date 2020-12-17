package net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfResponseVo;

/**
 * OA11040 ViewObject
 */
public class Oa11040Vo extends BaseOfResponseVo {

    private static final long serialVersionUID = 1L;

    /**
     * オペレーターID
     */
    private Long operatorId;
    /**
     * ＪＡ
     */
    private String ja;
    /**
     * オペレーター
     */
    private String operator;
    /**
     * 付与ロールテーブル
     */
    private List<Oa11040AllocateRoleTableVo> oa11040AllocateRoleTableVoList;
    /**
     * 未付与ロールテーブル
     */
    private List<Oa11040UnallocateRoleTableVo> oa11040UnallocateRoleTableVoList;
    /**
     * 変更事由
     */
    private String changeCause;
    /**
     * 変更事由プレースホルダー
     */
    private String changeCausePlaceholder;

    // Getter
    public String getJa() {
        return ja;
    }
    public Long getOperatorId() {
        return operatorId;
    }
    public String getOperator() {
        return operator;
    }
    public List<Oa11040AllocateRoleTableVo> getOa11040AllocateRoleTableVoList() {
        return oa11040AllocateRoleTableVoList;
    }
    public List<Oa11040UnallocateRoleTableVo> getOa11040UnallocateRoleTableVoList() {
        return oa11040UnallocateRoleTableVoList;
    }
    public String getChangeCause() {
        return changeCause;
    }
    public String getChangeCausePlaceholder() {
        return changeCausePlaceholder;
    }

    // Setter
    public void setJa(String ja) {
        this.ja = ja;
    }
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public void setOa11040AllocateRoleTableVoList(List<Oa11040AllocateRoleTableVo> oa11040AllocateRoleTableVoList) {
        this.oa11040AllocateRoleTableVoList = oa11040AllocateRoleTableVoList;
    }
    public void setOa11040UnallocateRoleTableVoList(List<Oa11040UnallocateRoleTableVo> oa11040UnallocateRoleTableVoList) {
        this.oa11040UnallocateRoleTableVoList = oa11040UnallocateRoleTableVoList;
    }
    public void setChangeCause(String changeCause) {
        this.changeCause = changeCause;
    }
    public void setChangeCausePlaceholder(String changeCausePlaceholder) {
        this.changeCausePlaceholder = changeCausePlaceholder;
    }
}
