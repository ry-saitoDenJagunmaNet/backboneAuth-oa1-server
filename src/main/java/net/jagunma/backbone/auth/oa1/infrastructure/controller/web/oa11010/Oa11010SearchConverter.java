package net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.operator.OperatorBiztranRoleRequest;
import net.jagunma.backbone.auth.model.operator.OperatorSubsystemRoleRequest;
import net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010.dto.Oa11010SearchRequestDto;
import net.jagunma.backbone.auth.usecase.operator.OperatorSearchRequest;

/**
 * OA11010 オペレーター＜一覧＞ 検索 Converter
 */
class Oa11010SearchConverter implements OperatorSearchRequest {

	private Oa11010SearchRequestDto arg;

	Oa11010SearchConverter(Oa11010SearchRequestDto anArg)  {
		arg = anArg;
	}

	public static Oa11010SearchConverter with(Oa11010SearchRequestDto anArg) {
		return new Oa11010SearchConverter(anArg);
	}

	public String getJa() { return arg.getJa(); }
	public long getJaId() { return arg.getJaId(); }
	public String getTempoCode() { return arg.getTempoCode(); }
	public Map<String, String> getTempoList() { return arg.getTempoList(); }
	public String getOperatorCode() { return arg.getOperatorCode(); }
	public String getOperatorName() { return arg.getOperatorName(); }
	public String getMailAddress() { return arg.getMailAddress(); }
	public Integer getAvailableStatus0() { return arg.getAvailableStatus0(); }
	public Integer getAvailableStatus1() { return arg.getAvailableStatus1(); }
	public Integer getExpirationSelect() { return arg.getExpirationSelect(); }
	public LocalDate getExpirationStatusDate() { return arg.getExpirationStatusDate(); }
	public LocalDate getExpirationStartDateFrom() { return arg.getExpirationStartDateFrom(); }
	public LocalDate getExpirationStartDateTo() { return arg.getExpirationStartDateTo(); }
	public LocalDate getExpirationEndDateFrom() { return arg.getExpirationEndDateFrom(); }
	public LocalDate getExpirationEndDateTo() { return arg.getExpirationEndDateTo(); }

	public Integer getSubsystemRoleConditionsSelect() { return arg.getSubsystemRoleConditionsSelect(); }
	public String getSubsystemRoleSubsystemCode() { return arg.getSubsystemRoleSubsystemCode(); }
	public Map<String, String> getSubsystemRoleSubsystemList() { return arg.getSubsystemRoleSubsystemList(); }
	public List<OperatorSubsystemRoleRequest> getSubsystemRoleList() {
		List<OperatorSubsystemRoleRequest> list = newArrayList();
		arg.getSubsystemRoleList().forEach(s -> {
			OperatorSubsystemRoleRequest os = new OperatorSubsystemRoleRequest();
			os.setSubsystemRoleSelected(s.getSubsystemRoleSelected());
			os.setSubsystemRoleId(s.getSubsystemRoleId());
			os.setSubsystemRoleCode(s.getSubsystemRoleCode());
			os.setSubsystemRoleName(s.getSubsystemRoleName());
			os.setExpirationSelect(s.getExpirationSelect());
			os.setExpirationStatusDate(s.getExpirationStatusDate());
			os.setExpirationStartDateFrom(s.getExpirationStartDateFrom());
			os.setExpirationStartDateTo(s.getExpirationStartDateTo());
			os.setExpirationEndDateFrom(s.getExpirationEndDateFrom());
			os.setExpirationEndDateTo(s.getExpirationEndDateTo());
			list.add(os);
		});
		return list;
	}

	public Integer getBiztranRoleConditionsSelect() { return arg.getBiztranRoleConditionsSelect(); }
	public List<OperatorBiztranRoleRequest> getBiztranRoleList() {
		List<OperatorBiztranRoleRequest> list = newArrayList();
		arg.getBiztranRoleList().forEach(b -> {
			OperatorBiztranRoleRequest ob = new OperatorBiztranRoleRequest();
			ob.setBiztranRoleSelected(b.getBiztranRoleSelected());
			ob.setBiztranRoleId(b.getBiztranRoleId());
			ob.setBiztranRoleCode(b.getBiztranRoleCode());
			ob.setBiztranRoleName(b.getBiztranRoleName());
			ob.setSubSystemCode(b.getSubSystemCode());
			ob.setExpirationSelect(b.getExpirationSelect());
			ob.setExpirationStatusDate(b.getExpirationStatusDate());
			ob.setExpirationStartDateFrom(b.getExpirationStartDateFrom());
			ob.setExpirationStartDateTo(b.getExpirationStartDateTo());
			ob.setExpirationEndDateFrom(b.getExpirationEndDateFrom());
			ob.setExpirationEndDateTo(b.getExpirationEndDateTo());
			list.add(ob);
		});
		return list;
	}

	public Integer getDeviceAuthUse() { return arg.getDeviceAuthUse(); }
	public Integer getDeviceAuthUnused() { return arg.getDeviceAuthUnused(); }
	public LocalDate getAccountLockOccurredDateFrom() { return arg.getAccountLockOccurredDateFrom(); }
	public LocalDate getAccountLockOccurredDateTo() { return arg.getAccountLockOccurredDateTo(); }
	public Integer getAccountLockStatusLock() { return arg.getAccountLockStatusLock(); }
	public Integer getAccountLockStatusUnlock() { return arg.getAccountLockStatusUnlock(); }
	public Integer getPasswordHistoryCheck() { return arg.getPasswordHistoryCheck(); }
	public Integer getPasswordHistoryLastChangeDate() { return arg.getPasswordHistoryLastChangeDate(); }
	public String getPasswordHistoryLastChangeDateStatus() { return arg.getPasswordHistoryLastChangeDateStatus(); }
	public Integer getPasswordHistoryChangeType0() { return arg.getPasswordHistoryChangeType0(); }
	public Integer getPasswordHistoryChangeType1() { return arg.getPasswordHistoryChangeType1(); }
	public Integer getPasswordHistoryChangeType2() { return arg.getPasswordHistoryChangeType2(); }
	public Integer getPasswordHistoryChangeType3() { return arg.getPasswordHistoryChangeType3(); }
	public LocalDate getSignintraceTrydateFrom() { return arg.getSignintraceTrydateFrom(); }
	public LocalDate getSignintraceTrydateTo() { return arg.getSignintraceTrydateTo(); }
	public String getSignintraceTryIpAddress() { return arg.getSignintraceTryIpAddress(); }
	public Integer getSignintraceSignIn() { return arg.getSignintraceSignIn(); }
	public Integer getSignintraceSignOut() { return arg.getSignintraceSignOut(); }
	public Integer[] getSignintraceSignInResult() { return arg.getSignintraceSignInResult(); }

	public int getPageNo() { return arg.getPageNo(); }

	/**
	 * オペレータの検索条件を取得
	 * @return
	 */
	public OperatorEntityCriteria getOperatorEntityCriteria(OperatorSearchRequest request) {
		OperatorEntityCriteria criteria = new OperatorEntityCriteria();

		criteria.getJaIdCriteria().setEqualTo(arg.getJaId());
		if (!isNullOrEmpty(arg.getTempoCode())) {
			criteria.getTempoCodeCriteria().setEqualTo(arg.getTempoCode());
		}
		if (!isNullOrEmpty(arg.getOperatorCode())) {
			criteria.getOperatorCodeCriteria().setForwardMatch(arg.getOperatorCode());
		}
		if (!isNullOrEmpty(arg.getOperatorName())) {
			criteria.getOperatorNameCriteria().setForwardMatch(arg.getOperatorName());
		}
		if (!isNullOrEmpty(arg.getMailAddress())) {
			criteria.getMailAddressCriteria().setForwardMatch(arg.getMailAddress());
		}
		criteria.getAvailableStatusCriteria().getIncludes().addAll(arg.getAvailableStatusIncludesList());

		// OPTION検索条件 有効期限
		if (arg.getExpirationSelect() == 1) {
			criteria.getExpirationStartDateCriteria().setLessOrEqual(arg.getExpirationStatusDate());
			criteria.getExpirationEndDateCriteria().setMoreOrEqual(arg.getExpirationStatusDate());
		} else if (arg.getExpirationSelect() == 2) {
			criteria.getExpirationStartDateCriteria().setMoreOrEqual(arg.getExpirationStartDateFrom());
			criteria.getExpirationStartDateCriteria().setLessOrEqual(arg.getExpirationStartDateTo());
			criteria.getExpirationEndDateCriteria().setMoreOrEqual(arg.getExpirationEndDateFrom());
			criteria.getExpirationEndDateCriteria().setLessOrEqual(arg.getExpirationEndDateTo());
		}

		// TODO: OPTION検索条件 サブシステムロール

		// TODO: OPTION検索条件 取引ロール

		// OPTION検索条件 その他
		if (arg.getDeviceAuthUse() != arg.getDeviceAuthUnused()) {
			if (arg.getDeviceAuthUse() == 1) {
				criteria.getIsDeviceAuthCriteria().setEqualTo(true);
			} else if (arg.getDeviceAuthUnused() == 1) {
				criteria.getIsDeviceAuthCriteria().setEqualTo(false);
			}
		}

		return criteria;
	}

	public boolean isNullOrEmpty(String str) {
		// strがnullもしくは空文字であればtrueを返す
		return (str == null || str.length() == 0);
	}
}
