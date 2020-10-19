package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchBizTranRoleRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchSubSystemRoleRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfOperatorSearchConverter;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010BizTranRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SubSystemRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010Vo;
import net.jagunma.common.ddd.model.criterias.BooleanCriteria;
import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.ShortCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;
import net.jagunma.common.util.strings2.Strings2;

/**
 * OA11010 オペレーター＜一覧＞検索サービス Request Converter
 */
class Oa11010SearchConverter extends BaseOfOperatorSearchConverter implements OperatorSearchRequest {

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
     * オペレーターコード検索条件のＧｅｔ
     *
     * @return オペレーターコード検索条件
     */
    public StringCriteria getOperatorCodeCriteria() {
        StringCriteria criteria = new StringCriteria();
        if (Strings2.isNotEmpty(vo.getOperatorCode())) {
            criteria.setForwardMatch(vo.getOperatorCode());
        }
        return criteria;
    }
    /**
     * オペレーター名検索条件のＧｅｔ
     *
     * @return オペレーター名検索条件
     */
    public StringCriteria getOperatorNameCriteria() {
        StringCriteria criteria = new StringCriteria();
        if (Strings2.isNotEmpty(vo.getOperatorName())) {
            criteria.setForwardMatch(vo.getOperatorName());
        }
        return criteria;
    }
    /**
     * メールアドレス検索条件のＧｅｔ
     *
     * @return メールアドレス検索条件
     */
    public StringCriteria getMailAddressCriteria() {
        StringCriteria criteria = new StringCriteria();
        if (Strings2.isNotEmpty(vo.getMailAddress())) {
            criteria.setForwardMatch(vo.getMailAddress());
        }
        return criteria;
    }
    /***
     * 有効期限開始日検索条件のＧｅｔ
     *
     * @return 有効期限開始日
     */
    public LocalDateCriteria getExpirationStartDateCriteria() {
        LocalDateCriteria criteria = new LocalDateCriteria();
        if (vo.getExpirationSelect() != null) {
            if (vo.getExpirationSelect() == 1) {
                // 状態指定日
                criteria.setLessOrEqual(vo.getExpirationStatusDate());
            } else if (vo.getExpirationSelect() == 2) {
                // 条件指定
                criteria.setMoreOrEqual(vo.getExpirationStartDateFrom());
                criteria.setLessOrEqual(vo.getExpirationStartDateTo());
            }
        }
        return criteria;
    }
    /***
     * 有効期限終了日検索条件のＧｅｔ
     *
     * @return 有効期限終了日
     */
    public LocalDateCriteria getExpirationEndDateCriteria() {
        LocalDateCriteria criteria = new LocalDateCriteria();
        if (vo.getExpirationSelect() != null) {
            if (vo.getExpirationSelect() == 1) {
                // 状態指定日
                criteria.setMoreOrEqual(vo.getExpirationStatusDate());
            } else if (vo.getExpirationSelect() == 2) {
                // 条件指定
                criteria.setMoreOrEqual(vo.getExpirationEndDateFrom());
                criteria.setLessOrEqual(vo.getExpirationEndDateTo());
            }
        }
        return criteria;
    }
    /**
     * 機器認証検索条件のＧｅｔ
     *
     * @return 機器認証使用
     */
    public BooleanCriteria getIsDeviceAuthCriteria() {
        BooleanCriteria criteria = new BooleanCriteria();
        Short deviceAuthUse = vo.getDeviceAuthUse() == null? Oa11010Vo.CHECKBOX_FALSE : vo.getDeviceAuthUse();
        Short deviceAuthUnuse = vo.getDeviceAuthUnuse() == null? Oa11010Vo.CHECKBOX_FALSE : vo.getDeviceAuthUnuse();
        if (deviceAuthUse.equals(deviceAuthUnuse)) { return criteria; }
        criteria.setEqualTo(Oa11010Vo.CHECKBOX_TRUE.equals(deviceAuthUse));
        return criteria;
    }
    /**
     * ＪＡID検索条件のＧｅｔ
     *
     * @return ＪＡID検索条件
     */
    public LongCriteria getJaIdCriteria() {
        LongCriteria criteria = new LongCriteria();
        criteria.setEqualTo(vo.getJaId());
        return criteria;
    }
    /**
     * 店舗ID検索条件のＧｅｔ
     *
     * @return 店舗コード検索条件
     */
    public LongCriteria getBranchIdCriteria() {
        LongCriteria criteria = new LongCriteria();
        criteria.setEqualTo(vo.getBranchId());
        return criteria;
    }
    /**
     * 利用可否状態検索条件のＧｅｔ
     *
     * @return 利用可否状態検索条件
     */
    public ShortCriteria getAvailableStatusCriteria() {
        ShortCriteria criteria = new ShortCriteria();
        List<Short> result = newArrayList();
        if (Oa11010Vo.CHECKBOX_TRUE.equals(vo.getAvailableStatus0())) {
            result.add((short) 0);
        }
        if (Oa11010Vo.CHECKBOX_TRUE.equals(vo.getAvailableStatus1())) {
            result.add((short) 1);
        }
        criteria.getIncludes().addAll(result);
        return criteria;
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
    public List<OparatorSearchSubSystemRoleRequest> getSubSystemRoleList() {
        List<OparatorSearchSubSystemRoleRequest> list = newArrayList();
        for (Oa11010SubSystemRoleVo subSystemRoleVo : vo.getSubSystemRoleList()) {
            // 選択チェックしたサブシステムロールを検索条件にする
            if (Oa11010Vo.CHECKBOX_TRUE.equals(subSystemRoleVo.getSubSystemRoleSelected())) {
                list.add(Oa11010SearchSubSystemRoleConverter.with(
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
    public List<OparatorSearchBizTranRoleRequest> getBizTranRoleList() {
        List<OparatorSearchBizTranRoleRequest> list = newArrayList();
        for (Oa11010BizTranRoleVo bizTranRoleVo : vo.getBizTranRoleList()) {
            // 選択チェックした取引ロールを検索条件にする
            if (Oa11010Vo.CHECKBOX_TRUE.equals(bizTranRoleVo.getBizTranRoleSelected())) {
                list.add(Oa11010SearchBizTranRoleConverter.with(
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
        }
        return list;
    }
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
     * @return 最終ロック・アンロック発生日（終了日）
     */
    public LocalDate getAccountLockOccurredDateTo() {
        return vo.getAccountLockOccurredDateTo();
    }
    /**
     * 現在ロック状態ロックのＧｅｔ
     *
     * @return 現在ロック状態ロック（0:アンロック、1:ロック）
     */
    public Short getAccountLockStatusLock() {
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
     * @return 最終パスワード変更日チェック（0:チェックfalse、1:チェックtrue）
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
}
