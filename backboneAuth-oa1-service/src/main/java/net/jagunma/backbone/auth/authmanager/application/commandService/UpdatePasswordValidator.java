package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordChangeRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordResetRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * パスワード更新サービス Validator
 */
class UpdatePasswordValidator {

    private final PasswordResetRequest resetRequest;
    private final PasswordChangeRequest changeRequest;

    /**
     * コンストラクタ
     */
    UpdatePasswordValidator(PasswordResetRequest request) {
        this.resetRequest = request;
        this.changeRequest = null;
    }
    UpdatePasswordValidator(PasswordChangeRequest request) {
        this.resetRequest = null;
        this.changeRequest = request;
    }

    public static UpdatePasswordValidator with(PasswordResetRequest request) {
        return new UpdatePasswordValidator(request);
    }
    public static UpdatePasswordValidator with(PasswordChangeRequest request) {
        return new UpdatePasswordValidator(request);
    }

    /**
     * リクエストの検証を行います
     */
    public void validate() {

        // リクエスト不正チェック
        if (resetRequest == null && changeRequest == null) {
            throw new GunmaRuntimeException("EOA13001");
        }

        // パスワードのリセット時
        if (resetRequest != null) {
            resetValidate();
        }

        // パスワードの変更時
        if (changeRequest != null) {
            changeValidate();
        }
    }

    /**
     * パスワードのリセット時
     */
    void resetValidate() {
        // 両方（どちらの時でも）
        bothValidate(resetRequest.getPassword(), resetRequest.getConfirmPassword());
    }

    /**
     * パスワードの変更時
     */
    void changeValidate() {
        // 未セットチェック
        Preconditions.checkNotEmpty(changeRequest.getOldPassword(), () -> new GunmaRuntimeException("EOA13002", "古いパスワード"));

        // 両方（どちらの時でも）
        bothValidate(changeRequest.getNewPassword(), changeRequest.getConfirmPassword(), "新しい");
    }

    /**
     * 両方（どちらの時でも）
     *
     * @param password パスワード
     * @param confirmPassword パスワードの確認入力
     */
    void bothValidate(String password, String confirmPassword) {
        bothValidate(password, confirmPassword, null);
    }
    /**
     * 両方（どちらの時でも）
     *
     * @param newPassword 新しいパスワード
     * @param confirmPassword パスワードの確認入力
     * @param messageAddition メッセージ付加
     */
    void bothValidate(String newPassword, String confirmPassword, String messageAddition) {

        // 未セットチェック
        Preconditions.checkNotEmpty(newPassword, () -> new GunmaRuntimeException("EOA13002", messageAddition + "パスワード"));
        Preconditions.checkNotEmpty(confirmPassword, () -> new GunmaRuntimeException("EOA13002", "パスワードの確認入力"));

        // 桁数チェック
        Preconditions.checkSize(8, 255, newPassword, () -> new GunmaRuntimeException("EOA13004", messageAddition + "パスワード", "8", "以上", "255", "以下"));

        // ToDo: 全角混入チェック
//          throw new GunmaRuntimeException("EOA13005", messageAddition + "パスワード");

        // パスワード不一致チェック
        if (!newPassword.equals(confirmPassword)) {
            throw new GunmaRuntimeException("EOA13101");
        }
    }
}
