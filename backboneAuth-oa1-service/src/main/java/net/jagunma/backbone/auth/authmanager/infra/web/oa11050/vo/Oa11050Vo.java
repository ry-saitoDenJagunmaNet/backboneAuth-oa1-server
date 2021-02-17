package net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfVo;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemSource;

/**
 * OA11050 ViewObject
 */
public class Oa11050Vo extends BaseOfVo {

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
    private List<Oa11050AssignRoleTableVo> assignRoleTableVoList;
    /**
     * 未付与ロールテーブル
     */
    private List<Oa11050UnAssignRoleTableVo> unAssignRoleTableVoList;
    /**
     * 変更事由
     */
    private String changeCause;
    /**
     * 変更事由プレースホルダー
     */
    private String changeCausePlaceholder;
    /**
     * サブシステムコンボボックスItemsSource
     */
    private List<SelectOptionItemSource> subSystemItemsSource;

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
    public List<Oa11050AssignRoleTableVo> getAssignRoleTableVoList() {
        return assignRoleTableVoList;
    }
    public List<Oa11050UnAssignRoleTableVo> getUnAssignRoleTableVoList() {
        return unAssignRoleTableVoList;
    }
    public String getChangeCause() {
        return changeCause;
    }
    public String getChangeCausePlaceholder() {
        return changeCausePlaceholder;
    }
    public List<SelectOptionItemSource> getSubSystemItemsSource() {
        return subSystemItemsSource;
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
    public void setAssignRoleTableVoList(List<Oa11050AssignRoleTableVo> assignRoleTableVoList) {
        this.assignRoleTableVoList = assignRoleTableVoList;
    }
    public void setUnAssignRoleTableVoList(List<Oa11050UnAssignRoleTableVo> unAssignRoleTableVoList) {
        this.unAssignRoleTableVoList = unAssignRoleTableVoList;
    }
    public void setChangeCause(String changeCause) {
        this.changeCause = changeCause;
    }
    public void setChangeCausePlaceholder(String changeCausePlaceholder) {
        this.changeCausePlaceholder = changeCausePlaceholder;
    }
    public void setSubSystemItemsSource(List<SelectOptionItemSource> subSystemItemsSource) {
        this.subSystemItemsSource = subSystemItemsSource;
    }
}
