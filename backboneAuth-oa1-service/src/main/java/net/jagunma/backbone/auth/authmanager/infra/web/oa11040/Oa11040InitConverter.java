package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedSearchRequest;

/**
 * OA11040 初期表示 Converter
 */
class Oa11040InitConverter implements SubSystemRoleGrantedSearchRequest {

    /**
     * OA11040 VariousItems
     */
    private final Long targetOperatorId;
    private final Long signInOperatorId;

    // コンストラクタ
    Oa11040InitConverter(Long targetOperatorId, Long signInOperatorId) {
        this.targetOperatorId = targetOperatorId;
        this.signInOperatorId = signInOperatorId;
    }

    // ファクトリーメソッド
    public static Oa11040InitConverter with(Long targetOperatorId, Long signInOperatorId) {
        return new Oa11040InitConverter(targetOperatorId, signInOperatorId);
    }

    /**
     * ターゲットオペレーターIDのＧｅｔ
     *
     * @return ターゲットオペレーターID
     */
    public Long getTargetOperatorId() {
        return targetOperatorId;
    }
    /**
     * サインインオペレーターIDのＧｅｔ
     *
     * @return サインインオペレーターID
     */
    public Long getSignInOperatorId() {
        return signInOperatorId;
    }
}
