package net.jagunma.backbone.auth.authmanager.infra.web.oa11030;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;

/**
 * OA11030 初期表示 Converter
 */
class Oa11030InitConverter implements OperatorSearchRequest {

    /**
     * オペレーターID
     */
    private final Long operatorId;

    // コンストラクタ
    Oa11030InitConverter(Long operatorId) {
        this.operatorId = operatorId;
    }

    // ファクトリーメソッド
    public static Oa11030InitConverter with(Long operatorId) {
        return new Oa11030InitConverter(operatorId);
    }

    /**
     * オペレーターIDのＧｅｔ
     *
     * @return オペレーターID
     */
    public Long getOperatorId() {
        return operatorId;
    }
}
