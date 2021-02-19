package net.jagunma.backbone.auth.authmanager.application.commandService;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantCommand.BizTranRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantCommand.BizTranRoleGrantRequestAssignRole;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * 取引ロール付与サービス Validator
 */
class GrantBizTranRoleValidator {

    private final BizTranRoleGrantRequest request;

    /**
     * コンストラクタ
     */
    GrantBizTranRoleValidator(BizTranRoleGrantRequest request) {
        this.request = request;
    }

    public static GrantBizTranRoleValidator with(BizTranRoleGrantRequest request) {
        return new GrantBizTranRoleValidator(request);
    }

    /**
     * リクエストの検証を行います
     */
    public void validate() {

        // リクエスト不正チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13001"));

        // 未セットチェック
        Preconditions.checkNotNull(request.getOperatorId(), () -> new GunmaRuntimeException("EOA13002", "オペレーターID"));
        Preconditions.checkNotEmpty(request.getChangeCause(), () -> new GunmaRuntimeException("EOA13002", "変更事由"));

        List<BizTranRoleGrantRequestAssignRole> assignRoleList = request.getAssignRoleList();
        for (BizTranRoleGrantRequestAssignRole assignRole : assignRoleList) {
            // 未セットチェック
            Preconditions.checkNotNull(assignRole.getBizTranRole(), () -> new GunmaRuntimeException("EOA13002", "取引ロール"));
            Preconditions.checkNotNull(assignRole.getValidThruStartDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（開始日）"));
            Preconditions.checkNotNull(assignRole.getValidThruEndDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（終了日）"));

            // 範囲指定不正チェック
            Preconditions.checkMax(assignRole.getValidThruEndDate(), assignRole.getValidThruStartDate(), () -> new GunmaRuntimeException("EOA13008", "有効期限"));
        }
    }
}
