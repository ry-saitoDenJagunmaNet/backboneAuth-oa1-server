package net.jagunma.backbone.auth.authmanager.model.domain.operator;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;

/**
 * オペレーターアップデートパック
 */
public class OperatorUpdatePack {

    private final Long operatorId;
    private final String operatorName;
    private final String mailAddress;
    private final LocalDate expirationStartDate;
    private final LocalDate expirationEndDate;
    private final Boolean isDeviceAuth;
    private final Long branchId;
    private final String branchCode;
    private final AvailableStatus availableStatus;
    private final String changeCause;
    private List<Operator_SubSystemRole> subSystemRoleList = newArrayList();
    private List<Operator_BizTranRole> bizTranRoleList = newArrayList();

    // コンストラクタ
    OperatorUpdatePack(
        Long operatorId,
        String operatorName,
        String mailAddress,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Boolean isDeviceAuth,
        Long branchId,
        String branchCode,
        AvailableStatus availableStatus,
        String changeCause,
        List<Operator_SubSystemRole> subSystemRoleList,
        List<Operator_BizTranRole> bizTranRoleList) {

        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.mailAddress = mailAddress;
        this.expirationStartDate = expirationStartDate;
        this.expirationEndDate = expirationEndDate;
        this.isDeviceAuth = isDeviceAuth;
        this.branchId = branchId;
        this.branchCode = branchCode;
        this.availableStatus = availableStatus;
        this.changeCause = changeCause;
        this.subSystemRoleList = subSystemRoleList;
        this.bizTranRoleList = bizTranRoleList;
    }
    // ファクトリーメソッド
    public static OperatorUpdatePack createFrom(
        Long operatorId,
        String operatorName,
        String mailAddress,
        LocalDate expirationStartDate,
        LocalDate expirationEndDate,
        Boolean isDeviceAuth,
        Long branchId,
        String branchCode,
        AvailableStatus availableStatus,
        String changeCause,
        List<Operator_SubSystemRole> subSystemRoleList,
        List<Operator_BizTranRole> bizTranRoleList) {

        return new OperatorUpdatePack(
            operatorId,
            operatorName,
            mailAddress,
            expirationStartDate,
            expirationEndDate,
            isDeviceAuth,
            branchId,
            branchCode,
            availableStatus,
            changeCause,
            subSystemRoleList,
            bizTranRoleList);
    }

    // Getter
    public Long getOperatorId() {
        return operatorId;
    }
    public String getOperatorName() {
        return operatorName;
    }
    public String getMailAddress() {
        return mailAddress;
    }
    public LocalDate getExpirationStartDate() {
        return expirationStartDate;
    }
    public LocalDate getExpirationEndDate() {
        return expirationEndDate;
    }
    public Boolean getIsDeviceAuth() {
        return isDeviceAuth;
    }
    public Long getBranchId() {
        return branchId;
    }
    public String getBranchCode() {
        return branchCode;
    }
    public AvailableStatus getAvailableStatus() {
        return availableStatus;
    }
    public String getChangeCause() {
        return changeCause;
    }
    public List<Operator_SubSystemRole> getSubSystemRoleList() {
        return subSystemRoleList;
    }
    public List<Operator_BizTranRole> getBizTranRoleList() {
        return bizTranRoleList;
    }
}
