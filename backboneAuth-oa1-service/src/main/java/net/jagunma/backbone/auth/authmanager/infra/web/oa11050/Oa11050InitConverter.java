package net.jagunma.backbone.auth.authmanager.infra.web.oa11050;

import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedSearchRequest;

/**
 * OA11050 初期表示 Converter
 */
class Oa11050InitConverter implements BizTranRoleGrantedSearchRequest {

    /**
     * OA11050 Various OperatorId
     */
    private final Long signInOperatorId;
    private final Long targetOperatorId;

    // コンストラクタ
    Oa11050InitConverter(Long signInOperatorId, Long targetOperatorId) {
        this.signInOperatorId = signInOperatorId;
        this.targetOperatorId = targetOperatorId;
    }

    // ファクトリーメソッド
    public static Oa11050InitConverter with(Long signInOperatorId, Long targetOperatorId) {
        return new Oa11050InitConverter(signInOperatorId, targetOperatorId);
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
