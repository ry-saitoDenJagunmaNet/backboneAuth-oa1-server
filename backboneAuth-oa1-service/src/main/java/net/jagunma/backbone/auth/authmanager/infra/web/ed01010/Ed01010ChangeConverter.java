package net.jagunma.backbone.auth.authmanager.infra.web.ed01010;

import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordChangeRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01010.vo.Ed01010Vo;

/**
 * ED01010 パスワード変更サービス Request Converter
 */
class Ed01010ChangeConverter implements PasswordChangeRequest {

    /**
     * ED01010 ViewObject
     */
    private final Ed01010Vo vo;

    // コンストラクタ
    Ed01010ChangeConverter(Ed01010Vo vo) {
        this.vo = vo;
    }

    // ファクトリーメソッド
    public static Ed01010ChangeConverter with(Ed01010Vo vo) {
        return new Ed01010ChangeConverter(vo);
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
     * 古いパスワードのＧｅｔ
     *
     * @return 古いパスワード
     */
    public String getOldPassword() {
        return vo.getOldPassword();
    }
    /**
     * 新しいパスワードのＧｅｔ
     *
     * @return 新しいパスワード
     */
    public String getNewPassword() {
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
