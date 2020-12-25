package net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleAllocateReference;

import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * オペレーター_サブシステムロール割当コピーサービス Request
 */
public interface SubSystemRoleAllocateCopyRequest {

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
