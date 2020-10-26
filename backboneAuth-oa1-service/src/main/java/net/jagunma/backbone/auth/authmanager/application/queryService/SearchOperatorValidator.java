package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchBizTranRoleRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchSubSystemRoleRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * オペレーター検索サービス Validator
 */
public class SearchOperatorValidator {

    private final OperatorSearchRequest request;

    // コンストラクタ
    SearchOperatorValidator(OperatorSearchRequest request) {
        this.request = request;
    }

    // ファクトリーメソッド
    public static SearchOperatorValidator with(OperatorSearchRequest request) {
        return new SearchOperatorValidator(request);
    }

    /**
     * リクエストのチェックを行います
     */
    public void validate() {

        // 未入力チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));

        // 範囲指定不正チェック
        // オペレーター 有効期限開始
        if (request.getExpirationStartDateCriteria().getMoreOrEqual() != null &&
            request.getExpirationStartDateCriteria().getLessOrEqual() != null) {
            // 条件指定
            Preconditions.checkMax(request.getExpirationStartDateCriteria().getLessOrEqual(),
                request.getExpirationStartDateCriteria().getMoreOrEqual(),
                () -> new GunmaRuntimeException("EOA13008", "有効期限開始"));
        }
        // オペレーター 有効期限終了
        if (request.getExpirationEndDateCriteria().getMoreOrEqual() != null &&
            request.getExpirationEndDateCriteria().getLessOrEqual() != null) {
            // 条件指定
            Preconditions.checkMax(request.getExpirationEndDateCriteria().getLessOrEqual(),
                request.getExpirationEndDateCriteria().getMoreOrEqual(),
                () -> new GunmaRuntimeException("EOA13008", "有効期限終了"));
        }
        // サブシステムロール 有効期限
        if (request.getSubSystemRoleList() != null) {
            for(OparatorSearchSubSystemRoleRequest subSystemRoleRequest : request.getSubSystemRoleList()) {
                if (subSystemRoleRequest.getSubSystemRoleSelected() != null) {
                    // 有効期限開始
                    if (subSystemRoleRequest.getExpirationStartDateFrom() != null && subSystemRoleRequest.getExpirationStartDateTo() != null) {
                        Preconditions.checkMax(subSystemRoleRequest.getExpirationStartDateTo(),
                            subSystemRoleRequest.getExpirationStartDateFrom(),
                            () -> new GunmaRuntimeException("EOA13008", "サブシステムロール 有効期限開始"));
                    }
                    // 有効期限終了
                    if (subSystemRoleRequest.getExpirationEndDateFrom() != null && subSystemRoleRequest.getExpirationEndDateTo() != null) {
                        Preconditions.checkMax(subSystemRoleRequest.getExpirationEndDateTo(),
                            subSystemRoleRequest.getExpirationEndDateFrom(),
                            () -> new GunmaRuntimeException("EOA13008", "サブシステムロール 有効期限終了"));
                    }
                }
            }
        }
        // 取引ロール 有効期限
        if (request.getBizTranRoleList() != null) {
            for(OparatorSearchBizTranRoleRequest bizTranRoleRequest : request.getBizTranRoleList()) {
                if (bizTranRoleRequest.getBizTranRoleSelected() != null) {
                    // 有効期限開始
                    if (bizTranRoleRequest.getExpirationStartDateFrom() != null && bizTranRoleRequest.getExpirationStartDateTo() != null) {
                        Preconditions.checkMax(bizTranRoleRequest.getExpirationStartDateTo(), bizTranRoleRequest.getExpirationStartDateFrom(),
                            () -> new GunmaRuntimeException("EOA13008", "取引ロール 有効期限開始"));
                    }
                    // 有効期限終了
                    if (bizTranRoleRequest.getExpirationEndDateFrom() != null && bizTranRoleRequest.getExpirationEndDateTo() != null) {
                        Preconditions.checkMax(bizTranRoleRequest.getExpirationEndDateTo(), bizTranRoleRequest.getExpirationEndDateFrom(),
                            () -> new GunmaRuntimeException("EOA13008", "取引ロール 有効期限終了"));
                    }
                }
            }
        }
        // 最終ロック・アンロック発生日
        if (request.getAccountLockOccurredDateFrom() != null && request.getAccountLockOccurredDateTo() != null) {
            Preconditions.checkMax(request.getAccountLockOccurredDateTo(), request.getAccountLockOccurredDateFrom(), () -> new GunmaRuntimeException("EOA13008", "最終ロック・アンロック発生日"));
        }
        // 最終サインオペレーション試行日
        if (request.getSignintraceTrydateFrom() != null && request.getSignintraceTrydateTo() != null) {
            Preconditions.checkMax(request.getSignintraceTrydateTo(), request.getSignintraceTrydateFrom(), () -> new GunmaRuntimeException("EOA13008", "最終サインオペレーション試行日"));
        }
    }
}
