package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchBizTranRoleRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchSubSystemRoleRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * オペレーター検索サービス Validator
 */
public class SeatchOperatorValidator {

    private final OperatorSearchRequest request;

    // コンストラクタ
    SeatchOperatorValidator(OperatorSearchRequest request) {
        this.request = request;
    }

    // ファクトリーメソッド
    public static SeatchOperatorValidator with(OperatorSearchRequest request) {
        return new SeatchOperatorValidator(request);
    }

    /**
     * リクエストのチェックを行います。
     */
    public void validate() {

        // 未入力チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));

        // 範囲指定不正チェック
        // 有効期限開始
        if (request.getExpirationStartDateFrom() != null && request.getExpirationStartDateTo() != null) {
            Preconditions.checkMax(request.getExpirationStartDateTo(), request.getExpirationStartDateFrom(), () -> new GunmaRuntimeException("EOA13007", "有効期限開始"));
        }
        // サブシステムロール 有効期限
        for(OparatorSearchSubSystemRoleRequest subSystemRoleRequest : request.getSubSystemRoleList()) {
            if (subSystemRoleRequest.getSubSystemRoleSelected() != null) {
                // 有効期限開始
                if (subSystemRoleRequest.getExpirationStartDateFrom() != null && subSystemRoleRequest.getExpirationStartDateTo() != null) {
                    Preconditions.checkMax(subSystemRoleRequest.getExpirationStartDateTo(), subSystemRoleRequest.getExpirationStartDateFrom(),
                        () -> new GunmaRuntimeException("EOA13007", "サブシステムロール 有効期限開始"));
                }
                // 有効期限終了
                if (subSystemRoleRequest.getExpirationEndDateFrom() != null && subSystemRoleRequest.getExpirationEndDateTo() != null) {
                    Preconditions.checkMax(subSystemRoleRequest.getExpirationEndDateTo(), subSystemRoleRequest.getExpirationEndDateFrom(),
                        () -> new GunmaRuntimeException("EOA13007", "サブシステムロール 有効期限終了"));
                }
            }
        }
        // 取引ロール 有効期限
        for(OparatorSearchBizTranRoleRequest bizTranRoleRequest : request.getBizTranRoleList()) {
            if (bizTranRoleRequest.getBizTranRoleSelected() != null) {
                // 有効期限開始
                if (bizTranRoleRequest.getExpirationStartDateFrom() != null && bizTranRoleRequest.getExpirationStartDateTo() != null) {
                    Preconditions.checkMax(bizTranRoleRequest.getExpirationStartDateTo(), bizTranRoleRequest.getExpirationStartDateFrom(),
                        () -> new GunmaRuntimeException("EOA13007", "取引ロール 有効期限開始"));
                }
                // 有効期限終了
                if (bizTranRoleRequest.getExpirationEndDateFrom() != null && bizTranRoleRequest.getExpirationEndDateTo() != null) {
                    Preconditions.checkMax(bizTranRoleRequest.getExpirationEndDateTo(), bizTranRoleRequest.getExpirationEndDateFrom(),
                        () -> new GunmaRuntimeException("EOA13007", "取引ロール 有効期限終了"));
                }
            }
        }
        // 有効期限終了
        if (request.getExpirationEndDateFrom() != null && request.getExpirationEndDateTo() != null) {
            Preconditions.checkMax(request.getExpirationEndDateTo(), request.getExpirationEndDateFrom(), () -> new GunmaRuntimeException("EOA13007", "有効期限終了"));
        }
        // 最終ロック・アンロック発生日
        if (request.getAccountLockOccurredDateFrom() != null && request.getAccountLockOccurredDateTo() != null) {
            Preconditions.checkMax(request.getAccountLockOccurredDateTo(), request.getAccountLockOccurredDateFrom(), () -> new GunmaRuntimeException("EOA13007", "最終ロック・アンロック発生日"));
        }
        // 最終サインオペレーション試行日
        if (request.getSignintraceTrydateFrom() != null && request.getSignintraceTrydateTo() != null) {
            Preconditions.checkMax(request.getSignintraceTrydateTo(), request.getSignintraceTrydateFrom(), () -> new GunmaRuntimeException("EOA13007", "最終サインオペレーション試行日"));
        }
    }
}
