package net.jagunma.backbone.auth.authmanager.application.usecase.operatorReferenceXXX;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.Oa11010SearchBizTranRoleConverter;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.Oa11010SearchSubSystemRoleConverter;

/**
 * オペレーターリスト参照サービス Request
 */
public interface OperatorSearchRequest {

    Long getJaId();
    Long getBranchId();
    String getOperatorCode();
    String getOperatorName();
    String getMailAddress();
    Short getAvailableStatus0();
    Short getAvailableStatus1();
    Integer getExpirationSelect();
    LocalDate getExpirationStatusDate();
    LocalDate getExpirationStartDateFrom();
    LocalDate getExpirationStartDateTo();
    LocalDate getExpirationEndDateFrom();
    LocalDate getExpirationEndDateTo();
    Integer getSubSystemRoleConditionsSelect();
    List<Oa11010SearchSubSystemRoleConverter>  getSubSystemRoleList();
    Integer getBizTranRoleConditionsSelect();
    String getBizTranRoleSubSystemCode();
    List<Oa11010SearchBizTranRoleConverter> getBizTranRoleList();
//    Short getDeviceAuthUse();
//    Short getDeviceAuthUnuse();
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
    int getPageNo();

    /**
     * 利用可否状態IncludesListを取得します。
     *
     * @return 利用可否状態IncludesList
     */
    List<Short> getAvailableStatusIncludesList();
    /**
     * OPTION検索条件 その他　機器認証を取得します。
     *
     * @return true:機器認証使用
     */
    Boolean getDeviceAuthUse();
}
