package net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoReferenceDto;
import net.jagunma.backbone.auth.authmanager.application.service.oa11010.Oa11010SearchConverterOperatorBizTranRole;
import net.jagunma.backbone.auth.authmanager.application.service.oa11010.Oa11010SearchConverterOperatorSubSystemRole;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;

/**
 * オペレーター参照サービス Request
 */
public interface OperatorSearchRequest {
	/**
	 * ＪＡ（ＪＡコード＆ＪＡ名）のＧｅｔ
	 * @return ＪＡ
	 */
	String getJa();
	/**
	 * ＪＡIDのＧｅｔ
	 * @return ＪＡID
	 */
	long getJaId();
	/**
	 * 店舗コードのＧｅｔ
	 * @return 店舗コード
	 */
	String getTempoCode();
	/**
	 * 店舗コンボボックスリストのＧｅｔ
	 * @return 店舗コンボボックスリスト
	 */
	List<TempoReferenceDto> getTempoList();
	/**
	 * オペレーターコードのＧｅｔ
	 * @return オペレーターコード
	 */
	String getOperatorCode();
	/**
	 * オペレーター名のＧｅｔ
	 * @return オペレーター名
	 */
	String getOperatorName();
	/**
	 * メールアドレスのＧｅｔ
	 * @return メールアドレス
	 */
	String getMailAddress();
	/**
	 * 利用可否状態 利用可能のＧｅｔ
	 * @return 利用可否状態 利用可能
	 */
	Short getAvailableStatus0();
	/**
	 * 利用可否状態 利用不可のＧｅｔ
	 * @return 利用可否状態 利用不可
	 */
	Short getAvailableStatus1();
	/**
	 * 有効期限選択のＧｅｔ
	 * @return 有効期限選択
	 */
	Integer getExpirationSelect();
	/**
	 * 状態指定日 状態指定日のＧｅｔ
	 * @return 状態指定日 状態指定日
	 */
	LocalDate getExpirationStatusDate();
	/**
	 * 条件指定 有効期限開始（開始日）のＧｅｔ
	 * @return 条件指定 有効期限開始（開始日）
	 */
	LocalDate getExpirationStartDateFrom();
	/**
	 * 条件指定 有効期限開始（終了日）のＧｅｔ
	 * @return 条件指定 有効期限開始（終了日）
	 */
	LocalDate getExpirationStartDateTo();
	/**
	 * 条件指定 有効期限終了（開始日）のＧｅｔ
	 * @return 条件指定 有効期限終了（開始日）
	 */
	LocalDate getExpirationEndDateFrom();
	/**
	 * 条件指定 有効期限終了（終了日）のＧｅｔ
	 * @return 条件指定 有効期限終了（終了日）
	 */
	LocalDate getExpirationEndDateTo();

	/**
	 * サブシステムロール条件選択のＧｅｔ
	 * @return サブシステムロール条件選択
	 */
	Integer getSubSystemRoleConditionsSelect();
	/**
	 * サブシステムロール一覧のＧｅｔ
	 * @return サブシステムロール一覧
	 */
	List<Oa11010SearchConverterOperatorSubSystemRole> getSubSystemRoleList();

	/**
	 * 取引ロール条件選択のＧｅｔ
	 * @return 取引ロール条件選択
	 */
	Integer getBizTranRoleConditionsSelect();
	/**
	 * 取引ロール一覧フィルター用サブシステムコードのＧｅｔ
	 * @return 取引ロール一覧フィルター用サブシステムコード
	 */
	String getBizTranRoleSubSyStemCode();
	/**
	 * 取引ロール一覧フィルター用サブシステムコンボボックスリストのＧｅｔ
	 * @return 取引ロール一覧フィルター用サブシステムコンボボックスリスト
	 */
//	List<SubSystem> getBizTranRoleSubSystemList();
	/**
	 * 取引ロール一覧のＧｅｔ
	 * @return 取引ロール一覧
	 */
	List<Oa11010SearchConverterOperatorBizTranRole> getBizTranRoleList();

	/**
	 * 機器認証使用のＧｅｔ
	 * @return 機器認証使用
	 */
	Short getDeviceAuthUse();
	/**
	 * 機器認証未使用のＧｅｔ
	 * @return 機器認証未使用
	 */
	Short getDeviceAuthUnuse();
	/**
	 * 最終ロック・アンロック発生日（開始日）のＧｅｔ
	 * @return 最終ロック・アンロック発生日（開始日）
	 */
	LocalDate getAccountLockOccurredDateFrom();
	/**
	 * 最終ロック・アンロック発生日（終了日）のＧｅｔ
	 * @return 最終ロック・アンロック発生日（終了日）
	 */
	LocalDate getAccountLockOccurredDateTo();
	/**
	 * 現在ロック状態ロックのＧｅｔ
	 * @return 現在ロック状態ロック
	 */
	Short getAccountLockStatusLock();
	/**
	 * 現在ロック状態アンロックのＧｅｔ
	 * @return 現在ロック状態アンロック
	 */
	Short getAccountLockStatusUnlock();
	/**
	 * 最終パスワード変更日チェックのＧｅｔ
	 * @return 最終パスワード変更日チェック
	 */
	Short getPasswordHistoryCheck();
	/**
	 * 最終パスワード変更日数のＧｅｔ
	 * @return 最終パスワード変更日数
	 */
	Integer getPasswordHistoryLastChangeDate();
	/**
	 * 最終パスワード変更日状態のＧｅｔ
	 * @return 最終パスワード変更日状態
	 */
	String getPasswordHistoryLastChangeDateStatus();
	/**
	 * 最終パスワード変更種別初期のＧｅｔ
	 * @return 最終パスワード変更種別初期
	 */
	Short getPasswordHistoryChangeType0();
	/**
	 * 最終パスワード変更種別ユーザによる変更のＧｅｔ
	 * @return 最終パスワード変更種別ユーザによる変更
	 */
	Short getPasswordHistoryChangeType1();
	/**
	 * 最終パスワード変更種別管理者によるリセットのＧｅｔ
	 * @return 最終パスワード変更種別管理者によるリセット
	 */
	Short getPasswordHistoryChangeType2();
	/**
	 * 最終パスワード変更種別機器認証パスワードのＧｅｔ
	 * @return 最終パスワード変更種別機器認証パスワード
	 */
	Short getPasswordHistoryChangeType3();
	/**
	 * 最終サインオペレーション試行日（開始日）のＧｅｔ
	 * @return 最終サインオペレーション試行日（開始日）
	 */
	LocalDate getSignintraceTrydateFrom();
	/**
	 * 最終サインオペレーション試行日（終了日）のＧｅｔ
	 * @return 最終サインオペレーション試行日（終了日）
	 */
	LocalDate getSignintraceTrydateTo();
	/**
	 * 最終サインオペレーション元IPアドレスのＧｅｔ
	 * @return 最終サインオペレーション元IPアドレス
	 */
	String getSignintraceTryIpAddress();
	/**
	 * 最終サインオペレーションサインインのＧｅｔ
	 * @return 最終サインオペレーションサインイン
	 */
	Short getSignintraceSignIn();
	/**
	 * 最最終サインオペレーションサインアウトのＧｅｔ
	 * @return 最終サインオペレーションサインアウト
	 */
	Short getSignintraceSignOut();
	/**
	 * 最終サインオペレーションサインイン結果のＧｅｔ
	 * @return 最終サインオペレーションサインイン結果
	 */
	Short[] getSignintraceSignInResult();

	/**
	 * 表示ページのＧｅｔ
	 * @return 表示ページ
	 */
	int getPageNo();

	/**
	 * オペレーターの検索条件を生成します。
	 */
	OperatorEntityCriteria genOperatorEntityCriteria();
}
