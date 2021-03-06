package net.jagunma.backbone.auth.authmanager.infra.web.oa11020.vo;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfVo;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemSource;

/**
 * OA11020 ViewObject
 */
public class Oa11020Vo extends BaseOfVo {

    private static final long serialVersionUID = 1L;

    /**
     * ＪＡ
     */
    private String ja;
    /**
     * 店舗ID
     */
    private Long branchId;
    /**
     * 識別（オペレーターコードプレフィックス）
     */
    private String operatorCodePrefix;
    /**
     * オペレーターコード（下6桁）
     */
    private String operatorCode6;
    /**
     * オペレーター名
     */
    private String operatorName;
    /**
     * メールアドレス
     */
    private String mailAddress;
    /**
     * 有効期限（開始日）
     */
    private LocalDate validThruStartDate;
    /**
     * 有効期限（終了日）
     */
    private LocalDate validThruEndDate;
    /**
     * 変更事由
     */
    private String changeCause;

    /**
     * 店舗コンボボックスItemsSource
     */
    private List<SelectOptionItemSource> branchItemsSource;

    /**
     * パスワード
     */
    private String password;
    /**
     * パスワードの確認入力
     */
    private String confirmPassword;

    // Getter
    public String getJa() {
        return ja;
    }
    public Long getBranchId() {
        return branchId;
    }
    public String getOperatorCodePrefix() {
        return operatorCodePrefix;
    }
    public String getOperatorCode6() {
        return operatorCode6;
    }
    public String getOperatorName() {
        return operatorName;
    }
    public String getMailAddress() {
        return mailAddress;
    }
    public LocalDate getValidThruStartDate() {
        return validThruStartDate;
    }
    public LocalDate getValidThruEndDate() {
        return validThruEndDate;
    }
    public String getChangeCause() {
        return changeCause;
    }
    public List<SelectOptionItemSource> getBranchItemsSource() {
        return branchItemsSource;
    }
    public String getPassword() {
        return password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }

    // Setter
    public void setJa(String ja) {
        this.ja = ja;
    }
    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
    public void setOperatorCodePrefix(String operatorCodePrefix) {
        this.operatorCodePrefix = operatorCodePrefix;
    }
    public void setOperatorCode6(String operatorCode6) {
        this.operatorCode6 = operatorCode6;
    }
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    public void setValidThruStartDate(LocalDate validThruStartDate) {
        this.validThruStartDate = validThruStartDate;
    }
    public void setValidThruEndDate(LocalDate validThruEndDate) {
        this.validThruEndDate = validThruEndDate;
    }
    public void setChangeCause(String changeCause) {
        this.changeCause = changeCause;
    }
    public void setBranchItemsSource(List<SelectOptionItemSource> branchItemsSource) {
        this.branchItemsSource = branchItemsSource;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
