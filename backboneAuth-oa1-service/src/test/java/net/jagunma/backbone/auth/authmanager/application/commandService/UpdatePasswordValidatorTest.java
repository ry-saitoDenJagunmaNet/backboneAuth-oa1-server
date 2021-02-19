package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordChangeRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordResetRequest;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.util.strings2.Strings2;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UpdatePasswordValidatorTest {

    // 実行既定値
    private Long operatorId = 123456L;
    private String password = "PaSsWoRd";
    private String oldPassword = "OldPaSsWoRd";
    private String newPassword = "PaSsWoRd";
    private String confirmPassword = "PaSsWoRd";
    private String messageAddition = "新しい";

    private PasswordResetRequest createPasswordResetRequest() {
        return new PasswordResetRequest() {
            @Override
            public Long getOperatorId() {
                return operatorId;
            }
            @Override
            public String getPassword() {
                return password;
            }
            @Override
            public String getConfirmPassword() {
                return confirmPassword;
            }
        };
    }
    private PasswordChangeRequest createPasswordChangeRequest() {
        return new PasswordChangeRequest() {
            @Override
            public Long getOperatorId() {
                return operatorId;
            }
            @Override
            public String getOldPassword() {
                return oldPassword;
            }
            @Override
            public String getNewPassword() {
                return newPassword;
            }
            @Override
            public String getConfirmPassword() {
                return confirmPassword;
            }
        };
    }

    /**
     * {@link UpdatePasswordValidator#validate()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test_ResetRequest() {
        // 実行値
        PasswordResetRequest request = createPasswordResetRequest();

        assertThatCode(() ->
            // 実行
            UpdatePasswordValidator.with(request).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link UpdatePasswordValidator#validate()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test_ChangeRequest() {
        // 実行値
        PasswordChangeRequest request = createPasswordChangeRequest();

        assertThatCode(() ->
            // 実行
            UpdatePasswordValidator.with(request).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link UpdatePasswordValidator#validate()}テスト
     *  ●パターン
     *    リクエスト不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test1_ResetRequest() {
        // 実行値
        PasswordResetRequest request = null;

        assertThatThrownBy(() ->
            // 実行
            UpdatePasswordValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link UpdatePasswordValidator#validate()}テスト
     *  ●パターン
     *    リクエスト不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test1_ChangeRequest() {
        // 実行値
        PasswordChangeRequest request = null;

        assertThatThrownBy(() ->
            // 実行
            UpdatePasswordValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link UpdatePasswordValidator#resetValidate()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void resetValidate_Test() {
        // 実行値
        PasswordResetRequest request = createPasswordResetRequest();

        assertThatCode(() ->
            // 実行
            UpdatePasswordValidator.with(request).resetValidate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link UpdatePasswordValidator#changeValidate()}テスト
     *  ●パターン
     *    未セットチェック 古いパスワード
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void changeValidate_Test() {
        // 実行値
        oldPassword = null;
        PasswordChangeRequest request = createPasswordChangeRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdatePasswordValidator.with(request).changeValidate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("古いパスワード");
            });
    }

    /**
     * {@link UpdatePasswordValidator#bothValidate(String password, String confirmPassword)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bothValidate_Test() {
        // 実行値
        PasswordResetRequest request = createPasswordResetRequest();

        assertThatCode(() ->
            // 実行
            UpdatePasswordValidator.with(request).bothValidate(password, confirmPassword))
            .doesNotThrowAnyException();
    }

    /**
     * {@link UpdatePasswordValidator#bothValidate(String newPassword, String confirmPassword, String messageAddition)}テスト
     *  ●パターン
     *    未セットチェック  新しいパスワード
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bothValidate_Test1() {
        // 実行値
        newPassword = null;
        PasswordChangeRequest request = createPasswordChangeRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdatePasswordValidator.with(request).bothValidate(newPassword, confirmPassword, messageAddition))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence(messageAddition + "パスワード");
            });
    }

    /**
     * {@link UpdatePasswordValidator#bothValidate(String newPassword, String confirmPassword, String messageAddition)}テスト
     *  ●パターン
     *    未セットチェック  パスワードの確認入力
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bothValidate_Test2() {
        // 実行値
        confirmPassword = null;
        PasswordChangeRequest request = createPasswordChangeRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdatePasswordValidator.with(request).bothValidate(newPassword, confirmPassword, messageAddition))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("パスワードの確認入力");
            });
    }

    /**
     * {@link UpdatePasswordValidator#bothValidate(String newPassword, String confirmPassword, String messageAddition)}テスト
     *  ●パターン
     *    桁数チェック  新しいパスワード
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bothValidate_Test3() {
        // 実行値
        newPassword = Strings2.repeat("*", 7);
        PasswordChangeRequest request = createPasswordChangeRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdatePasswordValidator.with(request).bothValidate(newPassword, confirmPassword, messageAddition))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13004");
                assertThat(e.getArgs()).containsSequence(messageAddition + "パスワード", "8", "以上", "255", "以下");
            });
    }

    /**
     * {@link UpdatePasswordValidator#bothValidate(String newPassword, String confirmPassword, String messageAddition)}テスト
     *  ●パターン
     *    桁数チェック  新しいパスワード
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bothValidate_Test4() {
        // 実行値
        newPassword = Strings2.repeat("*", 256);
        PasswordChangeRequest request = createPasswordChangeRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdatePasswordValidator.with(request).bothValidate(newPassword, confirmPassword, messageAddition))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13004");
                assertThat(e.getArgs()).containsSequence(messageAddition + "パスワード", "8", "以上", "255", "以下");
            });
    }

    /**
     * {@link UpdatePasswordValidator#bothValidate(String newPassword, String confirmPassword, String messageAddition)}テスト
     *  ●パターン
     *    全角混入チェック  パスワード
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bothValidate_Test5() {
        // 実行値
        newPassword = "PaSs全WoRd";
        PasswordChangeRequest request = createPasswordChangeRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdatePasswordValidator.with(request).bothValidate(newPassword, confirmPassword, messageAddition))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13005");
                assertThat(e.getArgs()).containsSequence(messageAddition + "パスワード");
            });
    }

    /**
     * {@link UpdatePasswordValidator#bothValidate(String newPassword, String confirmPassword, String messageAddition)}テスト
     *  ●パターン
     *    パスワード一致チェック
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bothValidate_Test6() {
        confirmPassword = "pAsSwOrD";
        PasswordChangeRequest request = createPasswordChangeRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdatePasswordValidator.with(request).bothValidate(newPassword, confirmPassword, messageAddition))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13101");
            });
    }
}
