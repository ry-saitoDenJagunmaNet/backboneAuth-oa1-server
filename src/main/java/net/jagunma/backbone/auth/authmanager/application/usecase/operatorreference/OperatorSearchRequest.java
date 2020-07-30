package net.jagunma.backbone.auth.authmanager.application.usecase.operatorreference;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoDto;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.Oa11010SearchConverterOperatorBizTranRole;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.Oa11010SearchConverterOperatorSubSystemRole;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;

/**
 * OA11010 オペレーター＜一覧＞ SearchConverter interface
 */
public interface OperatorSearchRequest {
	/**
	 * ＪＡ（ＪＡコード＆ＪＡ名）のゲット
	 * @return ＪＡID
	 */
	String getJa();
	/**
	 * ＪＡIDのゲット
	 * @return ＪＡID
	 */
	long getJaId();
	/**
	 * 店舗コードのゲット
	 * @return 店舗コード
	 */
	String getTempoCode();
	/**
	 * 店舗コンボボックスリストのゲット
	 * @return 店舗コンボボックスリスト
	 */
	List<TempoDto> getTempoList();
	/**
	 * オペレーターコードのゲット
	 * @return オペレーターコード
	 */
	String getOperatorCode();
	/**
	 * オペレーター名のゲット
	 * @return オペレーター名
	 */
	String getOperatorName();
	/**
	 * メールアドレスのゲット
	 * @return メールアドレス
	 */
	String getMailAddress();
	/**
	 * 利用可否状態 利用可能のゲット
	 * @return 利用可否状態 利用可能
	 */
	Integer getAvailableStatus0();
	/**
	 * 利用可否状態 利用不可のゲット
	 * @return 利用可否状態 利用不可
	 */
	Integer getAvailableStatus1();
	/**
	 * 有効期限選択のゲット
	 * @return 有効期限選択
	 */
	Integer getExpirationSelect();
	/**
	 * 状態指定日 状態指定日のゲット
	 * @return 状態指定日 状態指定日
	 */
	LocalDate getExpirationStatusDate();
	/**
	 * 条件指定 有効期限開始（開始日）のゲット
	 * @return 条件指定 有効期限開始（開始日）
	 */
	LocalDate getExpirationStartDateFrom();
	/**
	 * 条件指定 有効期限開始（終了日）のゲット
	 * @return 条件指定 有効期限開始（終了日）
	 */
	LocalDate getExpirationStartDateTo();
	/**
	 * 条件指定 有効期限終了（開始日）のゲット
	 * @return 条件指定 有効期限終了（開始日）
	 */
	LocalDate getExpirationEndDateFrom();
	/**
	 * 条件指定 有効期限終了（終了日）のゲット
	 * @return 条件指定 有効期限終了（終了日）
	 */
	LocalDate getExpirationEndDateTo();

	/**
	 * サブシステムロール条件選択のゲット
	 * @return サブシステムロール条件選択
	 */
	Integer getSubSystemRoleConditionsSelect();
	/**
	 * サブシステムロール一覧のゲット
	 * @return サブシステムロール一覧
	 */
	List<Oa11010SearchConverterOperatorSubSystemRole> getSubSystemRoleList();

	/**
	 * 取引ロール条件選択のゲット
	 * @return 取引ロール条件選択
	 */
	Integer getBizTranRoleConditionsSelect();
	/**
	 * 取引ロール一覧フィルター用サブシステムコードのゲット
	 * @return 取引ロール一覧フィルター用サブシステムコード
	 */
	String getBizTranRoleSubSyStemCode();
	/**
	 * 取引ロール一覧フィルター用サブシステムコンボボックスリストのゲット
	 * @return 取引ロール一覧フィルター用サブシステムコンボボックスリスト
	 */
	List<SubSystemDto> getBizTranRoleSubSystemList();
	/**
	 * 取引ロール一覧のゲット
	 * @return 取引ロール一覧
	 */
	List<Oa11010SearchConverterOperatorBizTranRole> getBizTranRoleList();

	/**
	 * 機器認証使用のゲット
	 * @return 機器認証使用
	 */
	Integer getDeviceAuthUse();
	/**
	 * 機器認証未使用のゲット
	 * @return 機器認証未使用
	 */
	Integer getDeviceAuthUnuse();
	/**
	 * 最終ロック・アンロック発生日（開始日）のゲット
	 * @return 最終ロック・アンロック発生日（開始日）
	 */
	LocalDate getAccountLockOccurredDateFrom();
	/**
	 * 最終ロック・アンロック発生日（終了日）のゲット
	 * @return 最終ロック・アンロック発生日（終了日）
	 */
	LocalDate getAccountLockOccurredDateTo();
	/**
	 * 現在ロック状態ロックのゲット
	 * @return 現在ロック状態ロック
	 */
	Integer getAccountLockStatusLock();
	/**
	 * 現在ロック状態アンロックのゲット
	 * @return 現在ロック状態アンロック
	 */
	Integer getAccountLockStatusUnlock();
	/**
	 * 最終パスワード変更日チェックのゲット
	 * @return 最終パスワード変更日チェック
	 */
	Integer getPasswordHistoryCheck();
	/**
	 * 最終パスワード変更日数のゲット
	 * @return 最終パスワード変更日数
	 */
	Integer getPasswordHistoryLastChangeDate();
	/**
	 * 最終パスワード変更日状態のゲット
	 * @return 最終パスワード変更日状態
	 */
	String getPasswordHistoryLastChangeDateStatus();
	/**
	 * 最終パスワード変更種別初期のゲット
	 * @return 最終パスワード変更種別初期
	 */
	Integer getPasswordHistoryChangeType0();
	/**
	 * 最終パスワード変更種別ユーザによる変更のゲット
	 * @return 最終パスワード変更種別ユーザによる変更
	 */
	Integer getPasswordHistoryChangeType1();
	/**
	 * 最終パスワード変更種別管理者によるリセットのゲット
	 * @return 最終パスワード変更種別管理者によるリセット
	 */
	Integer getPasswordHistoryChangeType2();
	/**
	 * 最終パスワード変更種別機器認証パスワードのゲット
	 * @return 最終パスワード変更種別機器認証パスワード
	 */
	Integer getPasswordHistoryChangeType3();
	/**
	 * 最終サインオペレーション試行日（開始日）のゲット
	 * @return 最終サインオペレーション試行日（開始日）
	 */
	LocalDate getSignintraceTrydateFrom();
	/**
	 * 最終サインオペレーション試行日（終了日）のゲット
	 * @return 最終サインオペレーション試行日（終了日）
	 */
	LocalDate getSignintraceTrydateTo();
	/**
	 * 最終サインオペレーション元IPアドレスのゲット
	 * @return 最終サインオペレーション元IPアドレス
	 */
	String getSignintraceTryIpAddress();
	/**
	 * 最終サインオペレーションサインインのゲット
	 * @return 最終サインオペレーションサインイン
	 */
	Integer getSignintraceSignIn();
	/**
	 * 最最終サインオペレーションサインアウトのゲット
	 * @return 最終サインオペレーションサインアウト
	 */
	Integer getSignintraceSignOut();
	/**
	 * 最終サインオペレーションサインイン結果のゲット
	 * @return 最終サインオペレーションサインイン結果
	 */
	Integer[] getSignintraceSignInResult();

	/**
	 * 表示ページのゲット
	 * @return 表示ページ
	 */
	int getPageNo();

	/**
	 * オペレータの検索条件を取得
	 * @return オペレータの検索条件
	 */
	OperatorEntityCriteria getOperatorEntityCriteria(OperatorSearchRequest request);
}
