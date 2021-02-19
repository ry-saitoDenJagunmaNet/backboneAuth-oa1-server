package net.jagunma.backbone.auth.authmanager.infra.api.oa31020;

import net.jagunma.backbone.auth.authmanager.application.usecase.accessibleReference.AccessibleSearchRequest;

/**
 * OA31020 権限取得 Converter
 */
public class Oa31020Converter implements AccessibleSearchRequest {

    private Long operatorId;

    // コンストラクタ
    Oa31020Converter(Long operatorId) {
        this.operatorId = operatorId;
    }

    // ファクトリーメソッド
    public static Oa31020Converter with(Long operatorId) {return new Oa31020Converter(operatorId);
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
