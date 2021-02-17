package net.jagunma.backbone.auth.authmanager.application.queryService.util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;

/**
 * サブシステムロール付与検索サービス・サブシステムロール付与コピーサービス における ユーティリティ
 */
public class SubSystemRoleGrantedQueryUtil {

    /**
     * 変更可否を判定します
     *
     * @param subSystemRoleCode サブシステムロールコード
     * @param signInOperator_SubSystemRoles サインインオペレーターのオペレーター_サブシステムロール割当群
     * @return 変更可否
     */
    public boolean judgeIsModifiable(String subSystemRoleCode, Operator_SubSystemRoles signInOperator_SubSystemRoles) {
        LocalDate today = LocalDate.now();

        // サインインオペレーター の オペレーター_サブシステムロール割当群 を サブシステムロールコード をキーにしてMap化
        Map<String, Operator_SubSystemRole> signInOperator_SubSystemRoleMap = new HashMap<>();
        for (Operator_SubSystemRole operator_SubSystemRole : signInOperator_SubSystemRoles.getValues()) {
            signInOperator_SubSystemRoleMap.put(operator_SubSystemRole.getSubSystemRoleCode(), operator_SubSystemRole);
        }

        // サインインオペレーター が JA管理者ロール を持っているか
        if (signInOperator_SubSystemRoleMap.containsKey(SubSystemRole.JA管理者.getCode())) {
            // 本日時点で有効か
            Operator_SubSystemRole signInOperator_SubSystemRole = signInOperator_SubSystemRoleMap.get(SubSystemRole.JA管理者.getCode());
            if (!(signInOperator_SubSystemRole.getValidThruStartDate().isAfter(today) ||
                signInOperator_SubSystemRole.getValidThruEndDate().isBefore(today))) {
                return true;
            }
        }

        // サインインオペレーター が 持っているロールか
        if (signInOperator_SubSystemRoleMap.containsKey(subSystemRoleCode)) {
            // 本日時点で有効か
            Operator_SubSystemRole signInOperator_SubSystemRole = signInOperator_SubSystemRoleMap.get(subSystemRoleCode);
            if (!(signInOperator_SubSystemRole.getValidThruStartDate().isAfter(today) ||
                signInOperator_SubSystemRole.getValidThruEndDate().isBefore(today))) {
                return true;
            }
        }

        return false;
    }
}
