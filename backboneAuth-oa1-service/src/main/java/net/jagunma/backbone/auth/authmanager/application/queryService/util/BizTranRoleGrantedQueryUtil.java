package net.jagunma.backbone.auth.authmanager.application.queryService.util;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;

/**
 * 取引ロール付与検索サービス・取引ロール付与コピーサービス における ユーティリティ
 */
public class BizTranRoleGrantedQueryUtil {

    /**
     * 変更可否を判定します
     *
     * @param bizTranRole 取引ロール
     * @param signInOperator_SubSystemRoles サインインオペレーターのオペレーター_サブシステムロール割当群
     * @return 変更可否
     */
    public boolean judgeIsModifiable(BizTranRole bizTranRole, Operator_SubSystemRoles signInOperator_SubSystemRoles) {
        LocalDate today = LocalDate.now();

        // サインインオペレーター の オペレーター_サブシステムロール割当群 の
        for (Operator_SubSystemRole signInOperator_SubSystemRole : signInOperator_SubSystemRoles.getValues()) {
            // サブシステムロールのサブシステムリスト中 に 取引ロールのサブシステムロール を持っているか
            if (signInOperator_SubSystemRole.getSubSystemRole().getSubSystemList().contains(bizTranRole.getSubSystem())) {
                // 本日時点で有効か
                if (!(signInOperator_SubSystemRole.getValidThruStartDate().isAfter(today) ||
                    signInOperator_SubSystemRole.getValidThruEndDate().isBefore(today))) {
                    return true;
                }
            }
        }

        return false;
    }
}
