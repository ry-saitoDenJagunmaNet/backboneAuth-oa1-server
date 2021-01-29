package net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfVo;

/**
 * OA11040 ViewObject
 */
public class Oa11040Vo extends BaseOfVo {

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
    private List<Oa11040AssignRoleTableVo> assignRoleTableVoList;
    /**
     * 未付与ロールテーブル
     */
    private List<Oa11040UnAssignRoleTableVo> unAssignRoleTableVoList;
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
    public List<Oa11040AssignRoleTableVo> getAssignRoleTableVoList() {
        return assignRoleTableVoList;
    }
    public List<Oa11040UnAssignRoleTableVo> getUnAssignRoleTableVoList() {
        return unAssignRoleTableVoList;
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
    public void setAssignRoleTableVoList(List<Oa11040AssignRoleTableVo> assignRoleTableVoList) {
        this.assignRoleTableVoList = assignRoleTableVoList;
    }
    public void setUnAssignRoleTableVoList(List<Oa11040UnAssignRoleTableVo> unAssignRoleTableVoList) {
        this.unAssignRoleTableVoList = unAssignRoleTableVoList;
    }
    public void setChangeCause(String changeCause) {
        this.changeCause = changeCause;
    }
    public void setChangeCausePlaceholder(String changeCausePlaceholder) {
        this.changeCausePlaceholder = changeCausePlaceholder;
    }
}
