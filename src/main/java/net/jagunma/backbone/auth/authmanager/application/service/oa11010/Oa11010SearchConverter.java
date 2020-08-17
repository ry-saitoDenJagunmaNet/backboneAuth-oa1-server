package net.jagunma.backbone.auth.authmanager.application.service.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.subSystem.SubSystem;
import net.jagunma.backbone.auth.authmanager.application.model.types.ConditionsExpirationSelect;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoReferenceDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010Vo;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;

/**
 * OA11010 オペレーター＜一覧＞ 検索 Converter
 */
class Oa11010SearchConverter implements OperatorSearchRequest {

	/**
	 * 検索条件開始日がnullの場合の設定値
	 */
	private final LocalDate CONDITIONS_DATE_FROM = LocalDate.of(0, 1, 1);
	/**
	 * 検索条件終了日がnullの場合の設定値
	 */
	private final LocalDate CONDITIONS_DATE_TO = LocalDate.of(9999, 12, 31);

	/**
	 * OA11010 View Object
	 */
	private final Oa11010Vo arg;

	// コンストラクタ
	Oa11010SearchConverter(Oa11010Vo oa11010Vo)  {
		arg = oa11010Vo;
	}
	// ファクトリーメソッド
	public static Oa11010SearchConverter with(Oa11010Vo oa11010Vo) {
		return new Oa11010SearchConverter(oa11010Vo);
	}

	/**
	 * ＪＡ（ＪＡコード＆ＪＡ名）のＧｅｔ
	 * @return ＪＡID
	 */
	public String getJa() { return arg.getJa(); }
	/**
	 * ＪＡIDのＧｅｔ
	 * @return ＪＡID
	 */
	public long getJaId() { return arg.getJaId(); }
	/**
	 * 店舗コードのＧｅｔ
	 * @return 店舗コード
	 */
	public String getTempoCode() { return arg.getTempoCode(); }
	/**
	 * 店舗コンボボックスリストのＧｅｔ
	 * @return 店舗コンボボックスリスト
	 */
	public List<TempoReferenceDto> getTempoList() { return arg.getTempoReferenceDtoList(); }
	/**
	 * オペレーターコードのＧｅｔ
	 * @return オペレーターコード
	 */
	public String getOperatorCode() { return arg.getOperatorCode(); }
	/**
	 * オペレーター名のＧｅｔ
	 * @return オペレーター名
	 */
	public String getOperatorName() { return arg.getOperatorName(); }
	/**
	 * メールアドレスのＧｅｔ
	 * @return メールアドレス
	 */
	public String getMailAddress() { return arg.getMailAddress(); }
	/**
	 * 利用可否状態 利用可能のＧｅｔ
	 * @return 利用可否状態 利用可能
	 */
	public Short getAvailableStatus0() { return arg.getAvailableStatus0(); }
	/**
	 * 利用可否状態 利用不可のＧｅｔ
	 * @return 利用可否状態 利用不可
	 */
	public Short getAvailableStatus1() { return arg.getAvailableStatus1(); }
	/**
	 * 有効期限選択のＧｅｔ
	 * @return 有効期限選択
	 */
	public Integer getExpirationSelect() { return arg.getExpirationSelect(); }
	/**
	 * 状態指定日 状態指定日のＧｅｔ
	 * @return 状態指定日 状態指定日
	 */
	public LocalDate getExpirationStatusDate() { return arg.getExpirationStatusDate(); }
	/**
	 * 条件指定 有効期限開始（開始日）のＧｅｔ
	 * @return 条件指定 有効期限開始（開始日）
	 */
	public LocalDate getExpirationStartDateFrom() { return arg.getExpirationStartDateFrom(); }
	/**
	 * 条件指定 有効期限開始（終了日）のＧｅｔ
	 * @return 条件指定 有効期限開始（終了日）
	 */
	public LocalDate getExpirationStartDateTo() { return arg.getExpirationStartDateTo(); }
	/**
	 * 条件指定 有効期限終了（開始日）のＧｅｔ
	 * @return 条件指定 有効期限終了（開始日）
	 */
	public LocalDate getExpirationEndDateFrom() { return arg.getExpirationEndDateFrom(); }
	/**
	 * 条件指定 有効期限終了（終了日）のＧｅｔ
	 * @return 条件指定 有効期限終了（終了日）
	 */
	public LocalDate getExpirationEndDateTo() { return arg.getExpirationEndDateTo(); }

	/**
	 * サブシステムロール条件選択のＧｅｔ
	 * @return サブシステムロール条件選択
	 */
	public Integer getSubSystemRoleConditionsSelect() { return arg.getSubSystemRoleConditionsSelect(); }
	/**
	 * サブシステムロール一覧のＧｅｔ
	 * @return サブシステムロール一覧
	 */
	public List<Oa11010SearchConverterOperatorSubSystemRole> getSubSystemRoleList() {
		List<Oa11010SearchConverterOperatorSubSystemRole> list = newArrayList();
		if (arg.getSubSystemRoleList() != null) {
			arg.getSubSystemRoleList().forEach(s -> {
				Oa11010SearchConverterOperatorSubSystemRole os = new Oa11010SearchConverterOperatorSubSystemRole();
				os.setSubSystemRoleSelected(s.getSubSystemRoleSelected());
				os.setSubSystemRoleId(s.getSubSystemRoleId());
				os.setSubSystemRoleCode(s.getSubSystemRoleCode());
				os.setSubSystemRoleName(s.getSubSystemRoleName());
				os.setExpirationSelect(s.getExpirationSelect());
				os.setExpirationStatusDate(s.getExpirationStatusDate());
				if (s.getExpirationStartDateFrom() == null) {
					os.setExpirationStartDateFrom(CONDITIONS_DATE_FROM);
				} else {
					os.setExpirationStartDateFrom(s.getExpirationStartDateFrom());
				}
				if (s.getExpirationStartDateTo() == null) {
					os.setExpirationStartDateTo(CONDITIONS_DATE_TO);
				} else {
					os.setExpirationStartDateTo(s.getExpirationStartDateTo());
				}
				if (s.getExpirationEndDateFrom() == null) {
					os.setExpirationEndDateFrom(CONDITIONS_DATE_FROM);
				} else {
					os.setExpirationEndDateFrom(s.getExpirationEndDateFrom());
				}
				if (s.getExpirationEndDateTo() == null) {
					os.setExpirationEndDateTo(CONDITIONS_DATE_TO);
				} else {
					os.setExpirationEndDateTo(s.getExpirationEndDateTo());
				}
				list.add(os);
			});
		}
		return list;
	}

	/**
	 * 取引ロール条件選択のＧｅｔ
	 * @return 取引ロール条件選択
	 */
	public Integer getBizTranRoleConditionsSelect() { return arg.getBizTranRoleConditionsSelect(); }
	/**
	 * 取引ロール一覧フィルター用サブシステムコードのＧｅｔ
	 * @return 取引ロールサブシステムコード
	 */
	public String getBizTranRoleSubSyStemCode() { return arg.getBizTranRoleSubSystemCode(); }
	/**
	 * 取引ロール一覧フィルター用サブシステムコンボボックスリストのＧｅｔ
	 * @return 取引ロール一覧フィルター用サブシステムコンボボックスリスト
	 */
	public List<SubSystem> getBizTranRoleSubSystemList() { return arg.getBizTranRoleSubSystemList(); }
	/**
	 * 取引ロール一覧のＧｅｔ
	 * @return 取引ロール一覧
	 */
	public List<Oa11010SearchConverterOperatorBizTranRole> getBizTranRoleList() {
		List<Oa11010SearchConverterOperatorBizTranRole> list = newArrayList();
		if (arg.getBizTranRoleList() != null) {
			arg.getBizTranRoleList().forEach(b -> {
				Oa11010SearchConverterOperatorBizTranRole ob = new Oa11010SearchConverterOperatorBizTranRole();
				ob.setBizTranRoleSelected(b.getBizTranRoleSelected());
				ob.setBizTranRoleId(b.getBizTranRoleId());
				ob.setBizTranRoleCode(b.getBizTranRoleCode());
				ob.setBizTranRoleName(b.getBizTranRoleName());
				ob.setSubSystemCode(b.getSubSystemCode());
				ob.setExpirationSelect(b.getExpirationSelect());
				ob.setExpirationStatusDate(b.getExpirationStatusDate());
				if (b.getExpirationStartDateFrom() == null) {
					ob.setExpirationStartDateFrom(CONDITIONS_DATE_FROM);
				} else {
					ob.setExpirationStartDateFrom(b.getExpirationStartDateFrom());
				}
				if (b.getExpirationStartDateTo() == null) {
					ob.setExpirationStartDateTo(CONDITIONS_DATE_TO);
				} else {
					ob.setExpirationStartDateTo(b.getExpirationStartDateTo());
				}
				if (b.getExpirationEndDateFrom() == null) {
					ob.setExpirationEndDateFrom(CONDITIONS_DATE_FROM);
				} else {
					ob.setExpirationEndDateFrom(b.getExpirationEndDateFrom());
				}
				if (b.getExpirationEndDateTo() == null) {
					ob.setExpirationEndDateTo(CONDITIONS_DATE_TO);
				} else {
					ob.setExpirationEndDateTo(b.getExpirationEndDateTo());
				}
				list.add(ob);
			});
		}
		return list;
	}

	/**
	 * 機器認証使用のＧｅｔ
	 * @return 機器認証使用
	 */
	public Short getDeviceAuthUse() { return arg.getDeviceAuthUse(); }
	/**
	 * 機器認証未使用のＧｅｔ
	 * @return 機器認証未使用
	 */
	public Short getDeviceAuthUnuse() { return arg.getDeviceAuthUnuse(); }
	/**
	 * 最終ロック・アンロック発生日（開始日）のＧｅｔ
	 * @return 最終ロック・アンロック発生日（開始日）
	 */
	public LocalDate getAccountLockOccurredDateFrom() { return arg.getAccountLockOccurredDateFrom(); }
	/**
	 * 最終ロック・アンロック発生日（終了日）のＧｅｔ
	 * @return 最終ロック・アンロック発生日（終了日）
	 */
	public LocalDate getAccountLockOccurredDateTo() { return arg.getAccountLockOccurredDateTo(); }
	/**
	 * 現在ロック状態ロックのＧｅｔ
	 * @return 現在ロック状態ロック
	 */
	public Short getAccountLockStatusLock() { return arg.getAccountLockStatusLock(); }
	/**
	 * 現在ロック状態アンロックのＧｅｔ
	 * @return 現在ロック状態アンロック
	 */
	public Short getAccountLockStatusUnlock() { return arg.getAccountLockStatusUnlock(); }
	/**
	 * 最終パスワード変更日チェックのＧｅｔ
	 * @return 最終パスワード変更日チェック
	 */
	public Short getPasswordHistoryCheck() { return arg.getPasswordHistoryCheck(); }
	/**
	 * 最終パスワード変更日数のＧｅｔ
	 * @return 最終パスワード変更日数
	 */
	public Integer getPasswordHistoryLastChangeDate() { return arg.getPasswordHistoryLastChangeDate(); }
	/**
	 * 最終パスワード変更日状態のＧｅｔ
	 * @return 最終パスワード変更日状態
	 */
	public String getPasswordHistoryLastChangeDateStatus() { return arg.getPasswordHistoryLastChangeDateStatus(); }
	/**
	 * 最終パスワード変更種別初期のＧｅｔ
	 * @return 最終パスワード変更種別初期
	 */
	public Short getPasswordHistoryChangeType0() { return arg.getPasswordHistoryChangeType0(); }
	/**
	 * 最終パスワード変更種別ユーザによる変更のＧｅｔ
	 * @return 最終パスワード変更種別ユーザによる変更
	 */
	public Short getPasswordHistoryChangeType1() { return arg.getPasswordHistoryChangeType1(); }
	/**
	 * 最終パスワード変更種別管理者によるリセットのＧｅｔ
	 * @return 最終パスワード変更種別管理者によるリセット
	 */
	public Short getPasswordHistoryChangeType2() { return arg.getPasswordHistoryChangeType2(); }
	/**
	 * 最終パスワード変更種別機器認証パスワードのＧｅｔ
	 * @return 最終パスワード変更種別機器認証パスワード
	 */
	public Short getPasswordHistoryChangeType3() { return arg.getPasswordHistoryChangeType3(); }
	/**
	 * 最終サインオペレーション試行日（開始日）のＧｅｔ
	 * @return 最終サインオペレーション試行日（開始日）
	 */
	public LocalDate getSignintraceTrydateFrom() { return arg.getSignintraceTrydateFrom(); }
	/**
	 * 最終サインオペレーション試行日（終了日）のＧｅｔ
	 * @return 最終サインオペレーション試行日（終了日）
	 */
	public LocalDate getSignintraceTrydateTo() { return arg.getSignintraceTrydateTo(); }
	/**
	 * 最終サインオペレーション元IPアドレスのＧｅｔ
	 * @return 最終サインオペレーション元IPアドレス
	 */
	public String getSignintraceTryIpAddress() { return arg.getSignintraceTryIpAddress(); }
	/**
	 * 最終サインオペレーションサインインのＧｅｔ
	 * @return 最終サインオペレーションサインイン
	 */
	public Short getSignintraceSignIn() { return arg.getSignintraceSignIn(); }
	/**
	 * 最最終サインオペレーションサインアウトのＧｅｔ
	 * @return 最終サインオペレーションサインアウト
	 */
	public Short getSignintraceSignOut() { return arg.getSignintraceSignOut(); }
	/**
	 * 最終サインオペレーションサインイン結果のＧｅｔ
	 * @return 最終サインオペレーションサインイン結果
	 */
	public Short[] getSignintraceSignInResult() { return arg.getSignintraceSignInResult(); }

	/**
	 * 表示ページのＧｅｔ
	 * @return 表示ページ
	 */
	public int getPageNo() { return arg.getPageNo(); }

	/**
	 * オペレーターの検索条件を生成します。
	 *
	 * @return オペレーターの検索条件
	 */
	public OperatorEntityCriteria genOperatorEntityCriteria(OperatorSearchRequest request) {
		OperatorEntityCriteria criteria = new OperatorEntityCriteria();

		criteria.getJaIdCriteria().setEqualTo(arg.getJaId());
		// 店舗コード
		if (!isNullOrEmpty(arg.getTempoCode())) {
			criteria.getTempoCodeCriteria().setEqualTo(arg.getTempoCode());
		}
		// オペレーターコード
		if (!isNullOrEmpty(arg.getOperatorCode())) {
			criteria.getOperatorCodeCriteria().setForwardMatch(arg.getOperatorCode());
		}
		// オペレーター名
		if (!isNullOrEmpty(arg.getOperatorName())) {
			criteria.getOperatorNameCriteria().setForwardMatch(arg.getOperatorName());
		}
		// ﾒメールアドレス
		if (!isNullOrEmpty(arg.getMailAddress())) {
			criteria.getMailAddressCriteria().setForwardMatch(arg.getMailAddress());
		}
		// 利用可否状態
		criteria.getAvailableStatusCriteria().getIncludes().addAll(arg.getAvailableStatusIncludesList());

		// OPTION検索条件 有効期限
		if (ConditionsExpirationSelect.状態指定日.equals(arg.getExpirationSelect())) {
			criteria.getExpirationStartDateCriteria().setLessOrEqual(arg.getExpirationStatusDate());
			criteria.getExpirationEndDateCriteria().setMoreOrEqual(arg.getExpirationStatusDate());
		} else if (ConditionsExpirationSelect.条件指定.equals(arg.getExpirationSelect())) {
			criteria.getExpirationStartDateCriteria().setMoreOrEqual(arg.getExpirationStartDateFrom());
			criteria.getExpirationStartDateCriteria().setLessOrEqual(arg.getExpirationStartDateTo());
			criteria.getExpirationEndDateCriteria().setMoreOrEqual(arg.getExpirationEndDateFrom());
			criteria.getExpirationEndDateCriteria().setLessOrEqual(arg.getExpirationEndDateTo());
		}

		// OPTION検索条件 その他　機器認証
		if (!nvl(arg.getDeviceAuthUse(), (short) 0).equals(nvl(arg.getDeviceAuthUnuse(), (short) 0))) {
			if (Oa11010Vo.CHECKBOX_TRUE.equals(arg.getDeviceAuthUse())) {
				criteria.getIsDeviceAuthCriteria().setEqualTo(true);
			} else if (Oa11010Vo.CHECKBOX_TRUE.equals(arg.getDeviceAuthUnuse())) {
				criteria.getIsDeviceAuthCriteria().setEqualTo(false);
			}
		}

		return criteria;
	}

	/**
	 * Nullまたは空文字を判定します。
	 *
	 * @param str 対象文字列
	 * @return true:Nullまたは空文字
	 */
	private boolean isNullOrEmpty(String str) {
		// strがnullもしくは空文字であればtrueを返す
		return (str == null || str.length() == 0);
	}

	/**
	 * Nullの場合に引数の文字列を返します。
	 *
	 * @param val 対象文字列
	 * @param changeVal Nullの場合に返却する文字列
	 * @return 文字列
	 */
	private Short nvl(Short val, Short changeVal) {
		if (val == null) {return  changeVal;}
		return val;
	}
}
