package net.jagunma.backbone.auth.usecase.operator;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.operator.OperatorBiztranRoleRequest;
import net.jagunma.backbone.auth.model.operator.OperatorSubsystemRoleRequest;

/**
 * OA11010 オペレーター＜一覧＞ SearchConverter interface
 */
public interface OperatorSearchRequest {
	String getJa();
	long getJaId();
	String getTempoCode();
	Map<String, String> getTempoList();
	String getOperatorCode();
	String getOperatorName();
	String getMailAddress();
	Integer getAvailableStatus0();
	Integer getAvailableStatus1();

	Integer getExpirationSelect();
	LocalDate getExpirationStatusDate();
	LocalDate getExpirationStartDateFrom();
	LocalDate getExpirationStartDateTo();
	LocalDate getExpirationEndDateFrom();
	LocalDate getExpirationEndDateTo();

	Integer getSubsystemRoleConditionsSelect();
	String getSubsystemRoleSubsystemCode();
	Map<String, String> getSubsystemRoleSubsystemList();
	List<OperatorSubsystemRoleRequest> getSubsystemRoleList();

	Integer getBiztranRoleConditionsSelect();
	List<OperatorBiztranRoleRequest> getBiztranRoleList();

	Integer getDeviceAuthUse();
	Integer getDeviceAuthUnused();
	LocalDate getAccountLockOccurredDateFrom();
	LocalDate getAccountLockOccurredDateTo();
	Integer getAccountLockStatusLock();
	Integer getAccountLockStatusUnlock();
	Integer getPasswordHistoryCheck();
	Integer getPasswordHistoryLastChangeDate();
	String getPasswordHistoryLastChangeDateStatus();
	Integer getPasswordHistoryChangeType0();
	Integer getPasswordHistoryChangeType1();
	Integer getPasswordHistoryChangeType2();
	Integer getPasswordHistoryChangeType3();
	LocalDate getSignintraceTrydateFrom();
	LocalDate getSignintraceTrydateTo();
	String getSignintraceTryIpAddress();
	Integer getSignintraceSignIn();
	Integer getSignintraceSignOut();
	Integer[] getSignintraceSignInResult();

	int getPageNo();

	OperatorEntityCriteria getOperatorEntityCriteria(OperatorSearchRequest request);
}
