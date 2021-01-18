package net.jagunma.backbone.auth.authmanager.infra.api.oa13020;

import net.jagunma.backbone.auth.authmanager.application.usecase.accessibleReference.AccessibleSearchRequest;

/**
 * OA13020 権限取得 Converter
 */
public class Oa13020Converter implements AccessibleSearchRequest {

    private Long operatorId;

    // コンストラクタ
    Oa13020Converter(Long operatorId) {
        this.operatorId = operatorId;
    }

    // ファクトリーメソッド
    public static Oa13020Converter of(Long operatorId) {
        return new Oa13020Converter(operatorId);
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
