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
        if (request.getValidThruStartDateCriteria().getMoreOrEqual() != null &&
            request.getValidThruStartDateCriteria().getLessOrEqual() != null) {
            // 条件指定
            Preconditions.checkMax(request.getValidThruStartDateCriteria().getLessOrEqual(),
                request.getValidThruStartDateCriteria().getMoreOrEqual(),
                () -> new GunmaRuntimeException("EOA13008", "有効期限開始"));
        }
        // オペレーター 有効期限終了
        if (request.getValidThruEndDateCriteria().getMoreOrEqual() != null &&
            request.getValidThruEndDateCriteria().getLessOrEqual() != null) {
            // 条件指定
            Preconditions.checkMax(request.getValidThruEndDateCriteria().getLessOrEqual(),
                request.getValidThruEndDateCriteria().getMoreOrEqual(),
                () -> new GunmaRuntimeException("EOA13008", "有効期限終了"));
        }
        // サブシステムロール 有効期限
        if (request.getSubSystemRoleList() != null) {
            for(OparatorSearchSubSystemRoleRequest subSystemRoleRequest : request.getSubSystemRoleList()) {
                if (subSystemRoleRequest.getSubSystemRoleSelected() != null) {
                    // 有効期限開始
                    if (subSystemRoleRequest.getValidThruStartDateFrom() != null && subSystemRoleRequest.getValidThruStartDateTo() != null) {
                        Preconditions.checkMax(subSystemRoleRequest.getValidThruStartDateTo(),
                            subSystemRoleRequest.getValidThruStartDateFrom(),
                            () -> new GunmaRuntimeException("EOA13008", "サブシステムロール 有効期限開始"));
                    }
                    // 有効期限終了
                    if (subSystemRoleRequest.getValidThruEndDateFrom() != null && subSystemRoleRequest.getValidThruEndDateTo() != null) {
                        Preconditions.checkMax(subSystemRoleRequest.getValidThruEndDateTo(),
                            subSystemRoleRequest.getValidThruEndDateFrom(),
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
                    if (bizTranRoleRequest.getValidThruStartDateFrom() != null && bizTranRoleRequest.getValidThruStartDateTo() != null) {
                        Preconditions.checkMax(bizTranRoleRequest.getValidThruStartDateTo(), bizTranRoleRequest.getValidThruStartDateFrom(),
                            () -> new GunmaRuntimeException("EOA13008", "取引ロール 有効期限開始"));
                    }
                    // 有効期限終了
                    if (bizTranRoleRequest.getValidThruEndDateFrom() != null && bizTranRoleRequest.getValidThruEndDateTo() != null) {
                        Preconditions.checkMax(bizTranRoleRequest.getValidThruEndDateTo(), bizTranRoleRequest.getValidThruEndDateFrom(),
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
