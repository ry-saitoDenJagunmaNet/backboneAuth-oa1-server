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
    private List<Oa11040AssignRoleTableVo> oa11040AssignRoleTableVoList;
    /**
     * 未付与ロールテーブル
     */
    private List<Oa11040UnAssignRoleTableVo> oa11040UnAssignRoleTableVoList;
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
    public List<Oa11040AssignRoleTableVo> getOa11040AssignRoleTableVoList() {
        return oa11040AssignRoleTableVoList;
    }
    public List<Oa11040UnAssignRoleTableVo> getOa11040UnAssignRoleTableVoList() {
        return oa11040UnAssignRoleTableVoList;
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
    public void setOa11040AssignRoleTableVoList(List<Oa11040AssignRoleTableVo> oa11040AssignRoleTableVoList) {
        this.oa11040AssignRoleTableVoList = oa11040AssignRoleTableVoList;
    }
    public void setOa11040UnAssignRoleTableVoList(List<Oa11040UnAssignRoleTableVo> oa11040UnAssignRoleTableVoList) {
        this.oa11040UnAssignRoleTableVoList = oa11040UnAssignRoleTableVoList;
    }
    public void setChangeCause(String changeCause) {
        this.changeCause = changeCause;
    }
    public void setChangeCausePlaceholder(String changeCausePlaceholder) {
        this.changeCausePlaceholder = changeCausePlaceholder;
    }
}
