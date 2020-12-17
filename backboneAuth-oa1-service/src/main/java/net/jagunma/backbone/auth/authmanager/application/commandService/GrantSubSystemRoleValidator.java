package net.jagunma.backbone.auth.authmanager.application.commandService;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleCommand.SubSystemRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleCommand.SubSystemRoleGrantRequestAllocateSubSystemRole;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * サブシステムロール付与サービス Validator
 */
class GrantSubSystemRoleValidator {

    private final SubSystemRoleGrantRequest request;

    /**
     * コンストラクタ
     */
    GrantSubSystemRoleValidator(SubSystemRoleGrantRequest request) {
        this.request = request;
    }

    public static GrantSubSystemRoleValidator with(SubSystemRoleGrantRequest request) {
        return new GrantSubSystemRoleValidator(request);
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

        List<SubSystemRoleGrantRequestAllocateSubSystemRole> allocateSubSystemRoleList = request.getAllocateSubSystemRoleList();
        for (SubSystemRoleGrantRequestAllocateSubSystemRole allocateSubSystemRole : allocateSubSystemRoleList) {
            // 未セットチェック
            Preconditions.checkNotNull(allocateSubSystemRole.getSubSystemRole(), () -> new GunmaRuntimeException("EOA13002", "サブシステムロール"));
            Preconditions.checkNotNull(allocateSubSystemRole.getValidThruStartDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（開始日）"));
            Preconditions.checkNotNull(allocateSubSystemRole.getValidThruEndDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（終了日）"));

            // 範囲指定不正チェック
            Preconditions.checkMax(allocateSubSystemRole.getValidThruEndDate(), allocateSubSystemRole.getValidThruStartDate(), () -> new GunmaRuntimeException("EOA13008", "有効期限"));
        }
    }
}
