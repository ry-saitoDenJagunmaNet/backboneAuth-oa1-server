package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfResponseVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010BizTranRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SubSystemRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010Vo;

/**
 * OA11010 オペレーター＜一覧＞検索サービス Request Converter
 */
class Oa11010SearchConverter implements OperatorSearchRequest {

    /**
     * OA11010 View Object
     */
    private final Oa11010Vo vo;

    // コンストラクタ
    Oa11010SearchConverter(Oa11010Vo oa11010Vo)  {
        vo = oa11010Vo;
    }

    // ファクトリーメソッド
    public static Oa11010SearchConverter with(Oa11010Vo oa11010Vo) {
        return new Oa11010SearchConverter(oa11010Vo);
    }

    /**
     * ＪＡIDのＧｅｔ
     *
     * @return ＪＡID
     */
    public Long getJaId() {
        return vo.getJaId();
    }
    /**
     * 店舗IDのＧｅｔ
     *
     * @return 店舗コード
     */
    public Long getTempoId() {
        return vo.getTempoId();
    }
    /**
     * オペレーターコードのＧｅｔ
     *Id
     * @return オペレーターコード
     */
    public String getOperatorCode() {
        return vo.getOperatorCode();
    }
    /**
     * オペレーター名のＧｅｔ
     *
     * @return オペレーター名
     */
    public String getOperatorName() {
        return vo.getOperatorName();
    }
    /**
     * メールアドレスのＧｅｔ
     *
     * @return メールアドレス
     */
    public String getMailAddress() {
        return vo.getMailAddress();
    }
    /**
     * 利用可否状態 利用可能のＧｅｔ
     *
     * @return 利用可否状態 利用可能
     */
    public Short getAvailableStatus0() {
        return vo.getAvailableStatus0();
    }
    /**
     * 利用可否状態 利用不可のＧｅｔ
     *
     * @return 利用可否状態 利用不可
     */
    public Short getAvailableStatus1() {
        return vo.getAvailableStatus1();
    }
    /**
     * 有効期限選択のＧｅｔ
     *
     * @return 有効期限選択
     */
    public Integer getExpirationSelect() {
        return vo.getExpirationSelect();
    }
    /**
     * 状態指定日のＧｅｔ
     *
     * @return 状態指定日
     */
    public LocalDate getExpirationStatusDate() {
        return vo.getExpirationStatusDate();
    }
    /**
     * 条件指定 有効期限開始（開始日）のＧｅｔ
     *
     * @return 条件指定 有効期限開始（開始日）
     */
    public LocalDate getExpirationStartDateFrom() {
        return vo.getExpirationStartDateFrom();
    }
    /**
     * 条件指定 有効期限開始（終了日）のＧｅｔ
     *
     * @return 条件指定 有効期限開始（終了日）
     */
    public LocalDate getExpirationStartDateTo() {
        return vo.getExpirationStartDateTo();
    }
    /**
     * 条件指定 有効期限終了（開始日）のＧｅｔ
     *
     * @return 条件指定 有効期限終了（開始日）
     */
    public LocalDate getExpirationEndDateFrom() {
        return vo.getExpirationEndDateFrom();
    }
    /**
     * 条件指定 有効期限終了（終了日）のＧｅｔ
     *
     * @return 条件指定 有効期限終了（終了日）
     */
    public LocalDate getExpirationEndDateTo() {
        return vo.getExpirationEndDateTo();
    }
    /**
     * サブシステムロール条件選択のＧｅｔ
     *
     * @return サブシステムロール条件選択
     */
    public Integer getSubSystemRoleConditionsSelect() {
        return vo.getSubSystemRoleConditionsSelect();
    }
    /**
     * サブシステムロール一覧のＧｅｔ
     *
     * @return OA11010 オペレーター＜一覧＞検索サービス サブシステムロール Request Converterリスト
     */
    public List<Oa11010SearchSubSystemRoleConverter> getSubSystemRoleList() {
        List<Oa11010SearchSubSystemRoleConverter> list = newArrayList();
        for (Oa11010SubSystemRoleVo subSystemRoleVo : vo.getSubSystemRoleList()) {
            list.add(new Oa11010SearchSubSystemRoleConverter(
                subSystemRoleVo.getSubSystemRoleSelected(),
                subSystemRoleVo.getSubSystemRoleCode(),
                subSystemRoleVo.getSubSystemRoleName(),
                subSystemRoleVo.getExpirationSelect(),
                subSystemRoleVo.getExpirationStatusDate(),
                subSystemRoleVo.getExpirationStartDateFrom(),
                subSystemRoleVo.getExpirationStartDateTo(),
                subSystemRoleVo.getExpirationEndDateFrom(),
                subSystemRoleVo.getExpirationEndDateTo()));
        }
        return list;
    }
    /**
     * 取引ロール条件選択のＧｅｔ
     *
     * @return 取引ロール条件選択
     */
    public Integer getBizTranRoleConditionsSelect() {
        return vo.getBizTranRoleConditionsSelect();
    }
    /**
     * 取引ロール一覧フィルター用サブシステムコードのＧｅｔ
     *
     * @return 取引ロール一覧フィルター用サブシステムコード
     */
    public String getBizTranRoleSubSystemCode() {
        return vo.getBizTranRoleSubSystemCode();
    }
    /**
     * 取引ロール一覧のＧｅｔ
     *
     * @return 取引ロール一覧
     */
    public List<Oa11010SearchBizTranRoleConverter> getBizTranRoleList() {
        List<Oa11010SearchBizTranRoleConverter> list = newArrayList();
        for (Oa11010BizTranRoleVo bizTranRoleVo : vo.getBizTranRoleList()) {
            list.add(new Oa11010SearchBizTranRoleConverter(
                bizTranRoleVo.getBizTranRoleSelected(),
                bizTranRoleVo.getBizTranRoleId(),
                bizTranRoleVo.getBizTranRoleCode(),
                bizTranRoleVo.getBizTranRoleName(),
                bizTranRoleVo.getSubSystemCode(),
                bizTranRoleVo.getExpirationSelect(),
                bizTranRoleVo.getExpirationStatusDate(),
                bizTranRoleVo.getExpirationStartDateFrom(),
                bizTranRoleVo.getExpirationStartDateTo(),
                bizTranRoleVo.getExpirationEndDateFrom(),
                bizTranRoleVo.getExpirationEndDateTo()));
        }
        return list;
    }
//    public Short getDeviceAuthUse() { return vo.getDeviceAuthUse(); }
//    public Short getDeviceAuthUnuse() { return vo.getDeviceAuthUnuse(); }
    /**
     * 最終ロック・アンロック発生日（開始日）のＧｅｔ
     *
     * @return 最終ロック・アンロック発生日（開始日）
     */
    public LocalDate getAccountLockOccurredDateFrom() {
        return vo.getAccountLockOccurredDateFrom();
    }
    /**
     * 最終ロック・アンロック発生日（終了日）のＧｅｔ
     *
     * @return      * 最終ロック・アンロック発生日（終了日）
     */
    public LocalDate getAccountLockOccurredDateTo() {
        return vo.getAccountLockOccurredDateTo();
    }
    /**
     * 現在ロック状態ロックのＧｅｔ
     *
     * @return 現在ロック状態ロック
     */
    public Short getAccountLockStatusLock()
    {
        return vo.getAccountLockStatusLock();
    }
    /**
     * 現在ロック状態アンロックのＧｅｔ
     *
     * @return 現在ロック状態アンロック
     */
    public Short getAccountLockStatusUnlock() {
        return vo.getAccountLockStatusUnlock();
    }
    /**
     * 最終パスワード変更日チェックのＧｅｔ
     *
     * @return 最終パスワード変更日チェック
     */
    public Short getPasswordHistoryCheck() {
        return vo.getPasswordHistoryCheck();
    }
    /**
     * 最終パスワード変更日数のＧｅｔ
     *
     * @return 最終パスワード変更日数
     */
    public Integer getPasswordHistoryLastChangeDate() {
        return vo.getPasswordHistoryLastChangeDate();
    }
    /**
     * 最終パスワード変更日状態のＧｅｔ
     *
     * @return 最終パスワード変更日状態
     */
    public String getPasswordHistoryLastChangeDateStatus() {
        return vo.getPasswordHistoryLastChangeDateStatus();
    }
    /**
     * 最終パスワード変更種別初期のＧｅｔ
     *
     * @return 最終パスワード変更種別初期
     */
    public Short getPasswordHistoryChangeType0() {
        return vo.getPasswordHistoryChangeType0();
    }
    /**
     * 最終パスワード変更種別ユーザによる変更のＧｅｔ
     *
     * @return 最終パスワード変更種別ユーザによる変更
     */
    public Short getPasswordHistoryChangeType1() {
        return vo.getPasswordHistoryChangeType1();
    }
    /**
     * 最終パスワード変更種別管理者によるリセットのＧｅｔ
     *
     * @return 最終パスワード変更種別管理者によるリセット
     */
    public Short getPasswordHistoryChangeType2() {
        return vo.getPasswordHistoryChangeType2();
    }
    /**
     * 最終パスワード変更種別機器認証パスワードのＧｅｔ
     *
     * @return 最終パスワード変更種別機器認証パスワード
     */
    public Short getPasswordHistoryChangeType3() {
        return vo.getPasswordHistoryChangeType3();
    }
    /**
     * 最終サインオペレーション試行日（開始日）のＧｅｔ
     *
     * @return 最終サインオペレーション試行日（開始日）
     */
    public LocalDate getSignintraceTrydateFrom() {
        return vo.getSignintraceTrydateFrom();
    }
    /**
     * 最終サインオペレーション試行日（終了日）のＧｅｔ
     *
     * @return 最終サインオペレーション試行日（終了日）
     */
    public LocalDate getSignintraceTrydateTo() {
        return vo.getSignintraceTrydateTo();
    }
    /**
     * 最終サインオペレーション元IPアドレスのＧｅｔ
     *
     * @return 最終サインオペレーション元IPアドレス
     */
    public String getSignintraceTryIpAddress() {
        return vo.getSignintraceTryIpAddress();
    }
    /**
     * 最終サインオペレーションサインインのＧｅｔ
     *
     * @return 最終サインオペレーションサインイン
     */
    public Short getSignintraceSignIn() {
        return vo.getSignintraceSignIn();
    }
    /**
     * 最終サインオペレーションサインアウトのＧｅｔ
     *
     * @return 最終サインオペレーションサインアウト
     */
    public Short getSignintraceSignOut() {
        return vo.getSignintraceSignOut();
    }
    /**
     * 最終サインオペレーションサインイン結果のＧｅｔ
     *
     * @return 最終サインオペレーションサインイン結果
     */
    public Short[] getSignintraceSignInResult() {
        return vo.getSignintraceSignInResult();
    }
    /**
     * オペレーター一覧表示ページのＧｅｔ
     * @return オペレーター一覧表示ページ
     */
    public int getPageNo() {
        return vo.getPageNo();
    }

    /**
     * 利用可否状態IncludesListを取得します。
     *
     * @return 利用可否状態IncludesList
     */
    public List<Short> getAvailableStatusIncludesList() {
        List<Short> result = newArrayList();
        if (BaseOfResponseVo.CHECKBOX_TRUE.equals(vo.getAvailableStatus0())) {
            result.add((short) 0);
        }
        if (BaseOfResponseVo.CHECKBOX_TRUE.equals(vo.getAvailableStatus1())) {
            result.add((short) 1);
        }
        return result;
    }

    /**
     * OPTION検索条件 その他　機器認証を取得します。
     *
     * @return true:機器認証使用
     */
    public Boolean getDeviceAuthUse() {
        if (BaseOfResponseVo.CHECKBOX_TRUE.equals(vo.getDeviceAuthUse())) {
            return true;
        }
        return false;
    }
}
