package net.jagunma.backbone.auth.authmanager.infra.web.oa11030;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030BizTranRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030SubsystemRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.AccountLockStatus;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.common.values.model.branch.BranchesAtMoment;

/**
 * OA11030 初期表示 Presenter
 */
class Oa11030InitPresenter {

    private Long operatorId;
    private Integer recordVersion;
    private String jaCode;
    private String jaName;
    private Long branchId;
    private String operatorCode;
    private String operatorName;
    private String mailAddress;
    private LocalDate expirationStartDate;
    private LocalDate expirationEndDate;
    private Boolean isDeviceAuth;
    private AvailableStatus availableStatus;
    private String changeCause;
    private AccountLockStatus lockStatus;
    private Operator_SubSystemRoles operator_SubSystemRoles;
    private Operator_BizTranRoles operator_BizTranRoles;

    private BranchesAtMoment branchesAtMoment;

    // コンストラクタ
    Oa11030InitPresenter() {
    }

    /**
     * オペレーターIDのＳｅｔ
     *
     * @param operatorId
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
    /**
     * レコードバージョンのＳｅｔ
     *
     * @param recordVersion
     */
    public void setRecordVersion(Integer recordVersion) {
        this.recordVersion = recordVersion;
    }
    /**
     * ＪＡコードのＳｅｔ
     *
     * @param jaCode
     */
    public void setJaCode(String jaCode) {
        this.jaCode = jaCode;
    }
    /**
     * ＪＡ名のＳｅｔ
     *
     * @param jaName
     */
    public void setJaName(String jaName) {
        this.jaName = jaName;
    }
    /**
     * 店舗IDのＳｅｔ
     *
     * @param branchId
     */
    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
    /**
     * オペレーターコードのＳｅｔ
     *
     * @param operatorCode
     */
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }
    /**
     * オペレーター名のＳｅｔ
     *
     * @param operatorName
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    /**
     * メールアドレスのＳｅｔ
     *
     * @param mailAddress
     */
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    /**
     * 有効期限（開始日）のＳｅｔ
     *
     * @param expirationStartDate
     */
    public void setExpirationStartDate(LocalDate expirationStartDate) {
        this.expirationStartDate = expirationStartDate;
    }
    /**
     * 有効期限（終了日）のＳｅｔ
     *
     * @param expirationEndDate
     */
    public void setExpirationEndDate(LocalDate expirationEndDate) {
        this.expirationEndDate = expirationEndDate;
    }
    /**
     * 機器認証のＳｅｔ
     *
     * @param isDeviceAuth
     */
    public void setIsDeviceAuth(Boolean isDeviceAuth) {
        this.isDeviceAuth = isDeviceAuth;
    }
    /**
     * 利用可否状態のＳｅｔ
     *
     * @param availableStatus
     */
    public void setAvailableStatus(AvailableStatus availableStatus) {
        this.availableStatus = availableStatus;
    }
    /**
     * 変更事由のＳｅｔ
     *
     * @param changeCause
     */
    public void setChangeCause(String changeCause) {
        this.changeCause = changeCause;
    }
    /**
     * ロック状態のＳｅｔ
     *
     * @param lockStatus
     */
    public void setLockStatus(AccountLockStatus lockStatus) {
        this.lockStatus = lockStatus;
    }
    /**
     * サブシステムロールテーブルのＳｅｔ
     *
     * @param operator_SubSystemRoles
     */
    public void setOperator_SubSystemRoles(Operator_SubSystemRoles operator_SubSystemRoles) {
        this.operator_SubSystemRoles = operator_SubSystemRoles;
    }
    /**
     * 取引ロールテーブルのＳｅｔ
     *
     * @param operator_BizTranRoles
     */
    public void setOperator_BizTranRoles(Operator_BizTranRoles operator_BizTranRoles) {
        this.operator_BizTranRoles = operator_BizTranRoles;
    }
    /**
     * 店舗群AtMomentのＳｅｔ
     *
     * @param branchesAtMoment
     */
    public void setBranchesAtMoment(BranchesAtMoment branchesAtMoment) {
        this.branchesAtMoment = branchesAtMoment;
    }

    /**
     * voに変換します。
     *
     * @param vo
     */
    public void bindTo(Oa11030Vo vo) {

        List<Oa11030SubsystemRoleTableVo> oa11030SubsystemRoleTableVoList = newArrayList();
        for (Operator_SubSystemRole operator_subSystemRole : operator_SubSystemRoles.getValues()) {
            Oa11030SubsystemRoleTableVo oa11030SubsystemRoleTableVo = new Oa11030SubsystemRoleTableVo();
            oa11030SubsystemRoleTableVo.setRoleName(operator_subSystemRole.getSubSystemRole().getName());
            oa11030SubsystemRoleTableVo.setExpirationDate(
                operator_subSystemRole.getExpirationStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " ～ " +
                operator_subSystemRole.getExpirationEndDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            oa11030SubsystemRoleTableVoList.add(oa11030SubsystemRoleTableVo);
        }

        List<Oa11030BizTranRoleTableVo> oa11030BizTranRoleTableVoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoles.getValues()) {
            Oa11030BizTranRoleTableVo oa11030BizTranRoleTableVo = new Oa11030BizTranRoleTableVo();
            oa11030BizTranRoleTableVo.setRoleCode(operator_BizTranRole.getBizTranRole().getBizTranRoleCode());
            oa11030BizTranRoleTableVo.setRoleName(operator_BizTranRole.getBizTranRole().getBizTranRoleName());
            oa11030BizTranRoleTableVo.setExpirationDate(
                operator_BizTranRole.getExpirationStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " ～ " +
                operator_BizTranRole.getExpirationEndDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            oa11030BizTranRoleTableVoList.add(oa11030BizTranRoleTableVo);
        }

        vo.setOperatorId(operatorId);
        vo.setRecordVersion(recordVersion);
        vo.setJa(jaCode + " " + jaName);
        vo.setBranchId(branchId);
        vo.setOperatorCode(operatorCode);
        vo.setOperatorName(operatorName);
        vo.setMailAddress(mailAddress);
        vo.setExpirationStartDate(expirationStartDate);
        vo.setExpirationEndDate(expirationEndDate);
        vo.setIsDeviceAuth(isDeviceAuth);
        vo.setAvailableStatus(availableStatus.getCode());
        vo.setChangeCause(changeCause);
        vo.setLockStatus(lockStatus.getCode());
        vo.setOa11030SubsystemRoleTableVoList(oa11030SubsystemRoleTableVoList);
        vo.setOa11030BizTranRoleTableVoList(oa11030BizTranRoleTableVoList);
        vo.setBranchItemsSource(SelectOptionItemsSource.createFrom(branchesAtMoment).getValue());
    }
}
