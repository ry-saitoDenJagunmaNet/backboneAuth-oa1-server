package net.jagunma.backbone.auth.authmanager.infra.web.ed01010;

import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordResetRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01010.vo.Ed01010Vo;

/**
 * ED01010 パスワードリセットサービス Request Converter
 */
class Ed01010ResetConverter implements PasswordResetRequest {

    /**
     * ED01010 ViewObject
     */
    private final Ed01010Vo vo;

    // コンストラクタ
    Ed01010ResetConverter(Ed01010Vo vo) {
        this.vo = vo;
    }

    // ファクトリーメソッド
    public static Ed01010ResetConverter with(Ed01010Vo vo) {
        return new Ed01010ResetConverter(vo);
    }

    /**
     * オペレーターIDのＧｅｔ
     *
     * @return オペレーターID
     */
    public Long getOperatorId() {
        return vo.getOperatorId();
    }
    /**
     * パスワードのＧｅｔ
     *
     * @return パスワード
     */
    public String getPassword() {
        return vo.getNewPassword();
    }
    /**
     * パスワードの確認入力のＧｅｔ
     *
     * @return パスワードの確認入力
     */
    public String getConfirmPassword() {
        return vo.getConfirmPassword();
    }
}
