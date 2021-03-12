package net.jagunma.backbone.auth.authmanager.infra.web.ed01010;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;

/**
 * ED01010 初期表示 Converter
 */
class Ed01010InitConverter implements OperatorSearchRequest {

    /**
     * オペレーターID
     */
    private final Long operatorId;

    // コンストラクタ
    Ed01010InitConverter(Long operatorId) {
        this.operatorId = operatorId;
    }

    // ファクトリーメソッド
    public static Ed01010InitConverter with(Long operatorId) {
        return new Ed01010InitConverter(operatorId);
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
