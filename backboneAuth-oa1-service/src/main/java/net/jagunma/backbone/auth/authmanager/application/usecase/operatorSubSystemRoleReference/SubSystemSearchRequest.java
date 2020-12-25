package net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleReference;

import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * オペレーター_サブシステムロール割当検索サービス Request
 */
public interface SubSystemSearchRequest {

    /**
     * ターゲットオペレーターIDのＧｅｔ
     *
     * @return ターゲットオペレーターID
     */
    Long getTargetOperatorId();
    /**
     * サインインオペレーターIDのＧｅｔ
     *
     * @return サインインオペレーターID
     */
    Long getSignInOperatorId();
    /**
     * オペレーターID検索条件のＧｅｔ
     *
     * @return オペレーターID検索条件
     */
    LongCriteria getOperatorIdCriteria();
}
