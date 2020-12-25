package net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleReference;

import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * オペレーター_サブシステムロール割当検索＆コピーサービス Request
 */
public interface SubSystemSearchCopyRequest {

    /**
     * ターゲットオペレーターIDのＧｅｔ
     *
     * @return ターゲットオペレーターID
     */
    Long getTargetOperatorId();
    /**
     * 選択オペレーターIDのＧｅｔ
     *
     * @return 選択オペレーターID
     */
    Long getSelectedOperatorId();
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
