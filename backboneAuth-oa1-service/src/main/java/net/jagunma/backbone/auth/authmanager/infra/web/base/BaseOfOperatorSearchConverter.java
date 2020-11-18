package net.jagunma.backbone.auth.authmanager.infra.web.base;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchBizTranRoleRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchSubSystemRoleRequest;
import net.jagunma.common.ddd.model.criterias.BooleanCriteria;
import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.ShortCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;

public class BaseOfOperatorSearchConverter {

    /**
     * オペレーターID検索条件のＧｅｔ
     *
     * @return オペレーターID検索条件
     */
    public LongCriteria getOperatorIdCriteria() {
        return new LongCriteria();
    }
    /**
     * オペレーターコード検索条件のＧｅｔ
     *
     * @return オペレーターコード検索条件
     */
    public StringCriteria getOperatorCodeCriteria() { return new StringCriteria(); }
    /**
     * オペレーター名検索条件のＧｅｔ
     *
     * @return オペレーター名検索条件
     */
    public StringCriteria getOperatorNameCriteria() { return new StringCriteria(); }
    /**
     * メールアドレス検索条件のＧｅｔ
     *
     * @return メールアドレス検索条件
     */
    public StringCriteria getMailAddressCriteria() { return new StringCriteria(); }
    /***
     * 有効期限開始日検索条件のＧｅｔ
     *
     * @return 有効期限開始日
     */
    public LocalDateCriteria getExpirationStartDateCriteria() { return new LocalDateCriteria(); }
    /***
     * 有効期限終了日検索条件のＧｅｔ
     *
     * @return 有効期限終了日
     */
    public LocalDateCriteria getExpirationEndDateCriteria() { return new LocalDateCriteria(); }
    /**
     * 機器認証検索条件のＧｅｔ
     *
     * @return 機器認証使用
     */
    public BooleanCriteria getIsDeviceAuthCriteria() { return new BooleanCriteria(); }
    /**
     * ＪＡID検索条件のＧｅｔ
     *
     * @return ＪＡID検索条件
     */
    public LongCriteria getJaIdCriteria() { return new LongCriteria(); }
    /**
     * 店舗ID検索条件のＧｅｔ
     *
     * @return 店舗コード検索条件
     */
    public LongCriteria getBranchIdCriteria() { return new LongCriteria(); }
    /**
     * 利用可否状態検索条件のＧｅｔ
     *
     * @return 利用可否状態検索条件
     */
    public ShortCriteria getAvailableStatusCriteria() { return new ShortCriteria(); }


    /**
     * サブシステムロール条件選択のＧｅｔ
     *
     * @return サブシステムロール条件選択
     */
    public Integer getSubSystemRoleConditionsSelect() { return null; }
    /**
     * サブシステムロール一覧のＧｅｔ
     *
     * @return OA11010 オペレーター＜一覧＞検索サービス サブシステムロール Request Converterリスト
     */
    public List<OparatorSearchSubSystemRoleRequest> getSubSystemRoleList() { return null; }
    /**
     * 取引ロール条件選択のＧｅｔ
     *
     * @return 取引ロール条件選択
     */
    public Integer getBizTranRoleConditionsSelect() { return null; }
    /**
     * 取引ロール一覧フィルター用サブシステムコードのＧｅｔ
     *
     * @return 取引ロール一覧フィルター用サブシステムコード
     */
    public String getBizTranRoleSubSystemCode() { return null; }
    /**
     * 取引ロール一覧のＧｅｔ
     *
     * @return 取引ロール一覧
     */
    public List<OparatorSearchBizTranRoleRequest> getBizTranRoleList() { return null; }
    /**
     * 最終ロック・アンロック発生日（開始日）のＧｅｔ
     *
     * @return 最終ロック・アンロック発生日（開始日）
     */
    public LocalDate getAccountLockOccurredDateFrom() { return null; }
    /**
     * 最終ロック・アンロック発生日（終了日）のＧｅｔ
     *
     * @return      * 最終ロック・アンロック発生日（終了日）
     */
    public LocalDate getAccountLockOccurredDateTo() { return null; }
    /**
     * 現在ロック状態ロックのＧｅｔ
     *
     * @return 現在ロック状態ロック
     */
    public Boolean getAccountLockStatusLock() { return null; }
    /**
     * 現在ロック状態アンロックのＧｅｔ
     *
     * @return 現在ロック状態アンロック
     */
    public Boolean getAccountLockStatusUnlock() { return null; }
    /**
     * 最終パスワード変更日チェックのＧｅｔ
     *
     * @return 最終パスワード変更日チェック
     */
    public Boolean getPasswordHistoryCheck() { return null; }
    /**
     * 最終パスワード変更日数のＧｅｔ
     *
     * @return 最終パスワード変更日数
     */
    public Integer getPasswordHistoryLastChangeDate() { return null; }
    /**
     * 最終パスワード変更日状態のＧｅｔ
     *
     * @return 最終パスワード変更日状態
     */
    public String getPasswordHistoryLastChangeDateStatus() { return null; }
    /**
     * 最終パスワード変更種別初期のＧｅｔ
     *
     * @return 最終パスワード変更種別初期
     */
    public Boolean getPasswordHistoryChangeType0() { return null; }
    /**
     * 最終パスワード変更種別ユーザによる変更のＧｅｔ
     *
     * @return 最終パスワード変更種別ユーザによる変更
     */
    public Boolean getPasswordHistoryChangeType1() { return null; }
    /**
     * 最終パスワード変更種別管理者によるリセットのＧｅｔ
     *
     * @return 最終パスワード変更種別管理者によるリセット
     */
    public Boolean getPasswordHistoryChangeType2() { return null; }
    /**
     * 最終パスワード変更種別機器認証パスワードのＧｅｔ
     *
     * @return 最終パスワード変更種別機器認証パスワード
     */
    public Boolean getPasswordHistoryChangeType3() { return null; }
    /**
     * 最終サインオペレーション試行日（開始日）のＧｅｔ
     *
     * @return 最終サインオペレーション試行日（開始日）
     */
    public LocalDate getSignintraceTrydateFrom() { return null; }
    /**
     * 最終サインオペレーション試行日（終了日）のＧｅｔ
     *
     * @return 最終サインオペレーション試行日（終了日）
     */
    public LocalDate getSignintraceTrydateTo() { return null; }
    /**
     * 最終サインオペレーション元IPアドレスのＧｅｔ
     *
     * @return 最終サインオペレーション元IPアドレス
     */
    public String getSignintraceTryIpAddress() { return null; }
    /**
     * 最終サインオペレーションサインインのＧｅｔ
     *
     * @return 最終サインオペレーションサインイン
     */
    public Boolean getSignintraceSignIn() { return null; }
    /**
     * 最終サインオペレーションサインアウトのＧｅｔ
     *
     * @return 最終サインオペレーションサインアウト
     */
    public Boolean getSignintraceSignOut() { return null; }
    /**
     * 最終サインオペレーションサインイン結果のＧｅｔ
     *
     * @return 最終サインオペレーションサインイン結果
     */
    public Short[] getSignintraceSignInResult() { return null; }
}