package net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.common.ddd.model.criterias.BooleanCriteria;
import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.ShortCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * オペレーターリスト参照サービス Request
 */
public interface OperatorSearchRequest {

    LongCriteria getOperatorIdCriteria();
    StringCriteria getOperatorCodeCriteria();
    StringCriteria getOperatorNameCriteria();
    StringCriteria getMailAddressCriteria();
    LocalDateCriteria getExpirationStartDateCriteria();
    LocalDateCriteria getExpirationEndDateCriteria();
    BooleanCriteria getIsDeviceAuthCriteria();
    LongCriteria getJaIdCriteria();
    LongCriteria getBranchIdCriteria();
    ShortCriteria getAvailableStatusCriteria();

    Integer getSubSystemRoleConditionsSelect();
    List<OparatorSearchSubSystemRoleRequest>  getSubSystemRoleList();
    Integer getBizTranRoleConditionsSelect();
    String getBizTranRoleSubSystemCode();
    List<OparatorSearchBizTranRoleRequest> getBizTranRoleList();
    LocalDate getAccountLockOccurredDateFrom();
    LocalDate getAccountLockOccurredDateTo();
    Short getAccountLockStatusLock();
    Short getAccountLockStatusUnlock();
    Short getPasswordHistoryCheck();
    Integer getPasswordHistoryLastChangeDate();
    String getPasswordHistoryLastChangeDateStatus();
    Short getPasswordHistoryChangeType0();
    Short getPasswordHistoryChangeType1();
    Short getPasswordHistoryChangeType2();
    Short getPasswordHistoryChangeType3();
    LocalDate getSignintraceTrydateFrom();
    LocalDate getSignintraceTrydateTo();
    String getSignintraceTryIpAddress();
    Short getSignintraceSignIn();
    Short getSignintraceSignOut();
    Short[] getSignintraceSignInResult();
}
