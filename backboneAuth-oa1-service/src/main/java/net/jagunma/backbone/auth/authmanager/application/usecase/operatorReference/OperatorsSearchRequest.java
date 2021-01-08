package net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.common.ddd.model.criterias.BooleanCriteria;
import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.ShortCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

/**
 * オペレーター検索サービス Request
 */
public interface OperatorsSearchRequest {

    /**
     * オペレーターコード検索条件のＧｅｔ
     *
     * @return オペレーターコード検索条件
     */
    StringCriteria getOperatorCodeCriteria();
    /**
     * オペレーター名検索条件のＧｅｔ
     *
     * @return オペレーター名検索条件
     */
    StringCriteria getOperatorNameCriteria();
    /**
     * メールアドレス検索条件のＧｅｔ
     *
     * @return メールアドレス検索条件
     */
    StringCriteria getMailAddressCriteria();
    /***
     * 有効期限開始日検索条件のＧｅｔ
     *
     * @return 有効期限開始日
     */
    LocalDateCriteria getValidThruStartDateCriteria();
    /***
     * 有効期限終了日検索条件のＧｅｔ
     *
     * @return 有効期限終了日
     */
    LocalDateCriteria getValidThruEndDateCriteria();
    /**
     * 機器認証検索条件のＧｅｔ
     *
     * @return 機器認証使用
     */
    BooleanCriteria getIsDeviceAuthCriteria();
    /**
     * ＪＡID検索条件のＧｅｔ
     *
     * @return ＪＡID検索条件
     */
    LongCriteria getJaIdCriteria();
    /**
     * 店舗ID検索条件のＧｅｔ
     *
     * @return 店舗コード検索条件
     */
    LongCriteria getBranchIdCriteria();
    /**
     * 利用可否状態検索条件のＧｅｔ
     *
     * @return 利用可否状態検索条件
     */
    ShortCriteria getAvailableStatusCriteria();

    /**
     * サブシステムロール条件選択のＧｅｔ
     *
     * @return サブシステムロール条件選択
     */
    Integer getSubSystemRoleConditionsSelect();
    /**
     * サブシステムロール一覧のＧｅｔ
     *
     * @return OA11010 オペレーター＜一覧＞検索サービス サブシステムロール Request Converterリスト
     */
    List<OparatorSearchSubSystemRoleRequest> getSubSystemRoleList();
    /**
     * 取引ロール条件選択のＧｅｔ
     *
     * @return 取引ロール条件選択
     */
    Integer getBizTranRoleConditionsSelect();
    /**
     * 取引ロール一覧フィルター用サブシステムコードのＧｅｔ
     *
     * @return 取引ロール一覧フィルター用サブシステムコード
     */
    String getBizTranRoleSubSystemCode();
    /**
     * 取引ロール一覧のＧｅｔ
     *
     * @return 取引ロール一覧
     */
    List<OparatorSearchBizTranRoleRequest> getBizTranRoleList();
    /**
     * 最終ロック・アンロック発生日（開始日）のＧｅｔ
     *
     * @return 最終ロック・アンロック発生日（開始日）
     */
    LocalDate getAccountLockOccurredDateFrom();
    /**
     * 最終ロック・アンロック発生日（終了日）のＧｅｔ
     *
     * @return      * 最終ロック・アンロック発生日（終了日）
     */
    LocalDate getAccountLockOccurredDateTo();
    /**
     * 現在ロック状態ロックのＧｅｔ
     *
     * @return 現在ロック状態ロック
     */
    Boolean getAccountLockStatusLock();
    /**
     * 現在ロック状態アンロックのＧｅｔ
     *
     * @return 現在ロック状態アンロック
     */
    Boolean getAccountLockStatusUnlock();
    /**
     * 最終パスワード変更日チェックのＧｅｔ
     *
     * @return 最終パスワード変更日チェック
     */
    Boolean getPasswordHistoryCheck();
    /**
     * 最終パスワード変更日数のＧｅｔ
     *
     * @return 最終パスワード変更日数
     */
    Integer getPasswordHistoryLastChangeDate();
    /**
     * 最終パスワード変更日状態のＧｅｔ
     *
     * @return 最終パスワード変更日状態
     */
    String getPasswordHistoryLastChangeDateStatus();
    /**
     * 最終パスワード変更種別初期のＧｅｔ
     *
     * @return 最終パスワード変更種別初期
     */
    Boolean getPasswordHistoryChangeType0();
    /**
     * 最終パスワード変更種別ユーザによる変更のＧｅｔ
     *
     * @return 最終パスワード変更種別ユーザによる変更
     */
    Boolean getPasswordHistoryChangeType1();
    /**
     * 最終パスワード変更種別管理者によるリセットのＧｅｔ
     *
     * @return 最終パスワード変更種別管理者によるリセット
     */
    Boolean getPasswordHistoryChangeType2();
    /**
     * 最終パスワード変更種別機器認証パスワードのＧｅｔ
     *
     * @return 最終パスワード変更種別機器認証パスワード
     */
    Boolean getPasswordHistoryChangeType3();
    /**
     * 最終サインオペレーション試行日（開始日）のＧｅｔ
     *
     * @return 最終サインオペレーション試行日（開始日）
     */
    LocalDate getSignintraceTrydateFrom();
    /**
     * 最終サインオペレーション試行日（終了日）のＧｅｔ
     *
     * @return 最終サインオペレーション試行日（終了日）
     */
    LocalDate getSignintraceTrydateTo();
    /**
     * 最終サインオペレーション元IPアドレスのＧｅｔ
     *
     * @return 最終サインオペレーション元IPアドレス
     */
    String getSignintraceTryIpAddress();
    /**
     * 最終サインオペレーションサインインのＧｅｔ
     *
     * @return 最終サインオペレーションサインイン
     */
    Boolean getSignintraceSignIn();
    /**
     * 最終サインオペレーションサインアウトのＧｅｔ
     *
     * @return 最終サインオペレーションサインアウト
     */
    Boolean getSignintraceSignOut();
    /**
     * 最終サインオペレーションサインイン結果のＧｅｔ
     *
     * @return 最終サインオペレーションサインイン結果
     */
    Short[] getSignintraceSignInResult();
}
