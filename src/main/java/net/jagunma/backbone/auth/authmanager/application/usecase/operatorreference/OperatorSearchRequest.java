package net.jagunma.backbone.auth.authmanager.application.usecase.operatorreference;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoDto;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.Oa11010SearchRequestOperatorBiztranRole;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.Oa11010SearchRequestOperatorSubsystemRoleRequest;

/**
 * OA11010 オペレーター＜一覧＞ SearchConverter interface
 */
public interface OperatorSearchRequest {
	String getJa();
	long getJaId();
	String getTempoCode();
	List<TempoDto> getTempoList();
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
	List<SubSystemDto> getSubsystemRoleSubsystemList();
	List<Oa11010SearchRequestOperatorSubsystemRoleRequest> getSubsystemRoleList();

	Integer getBiztranRoleConditionsSelect();
	List<Oa11010SearchRequestOperatorBiztranRole> getBiztranRoleList();

	Integer getDeviceAuthUse();
	Integer getDeviceAuthUnuse();
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
