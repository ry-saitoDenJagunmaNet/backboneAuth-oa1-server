package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedSearchRequest;

/**
 * OA11040 初期表示 Converter
 */
class Oa11040InitConverter implements SubSystemRoleGrantedSearchRequest {

    /**
     * OA11040 VariousItems
     */
    private final Long signInOperatorId;
    private final Long targetOperatorId;

    // コンストラクタ
    Oa11040InitConverter(Long signInOperatorId, Long targetOperatorId) {
        this.signInOperatorId = signInOperatorId;
        this.targetOperatorId = targetOperatorId;
    }

    // ファクトリーメソッド
    public static Oa11040InitConverter with(Long signInOperatorId, Long targetOperatorId) {
        return new Oa11040InitConverter(signInOperatorId, targetOperatorId);
    }

    /**
     * サインインオペレーターIDのＧｅｔ
     *
     * @return サインインオペレーターID
     */
    public Long getSignInOperatorId() {
        return signInOperatorId;
    }
    /**
     * ターゲットオペレーターIDのＧｅｔ
     *
     * @return ターゲットオペレーターID
     */
    public Long getTargetOperatorId() {
        return targetOperatorId;
    }
}
