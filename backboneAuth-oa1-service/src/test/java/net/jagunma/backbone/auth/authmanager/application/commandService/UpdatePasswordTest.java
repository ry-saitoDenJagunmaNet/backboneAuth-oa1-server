package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordChangeRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordResetRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UpdatePasswordTest {

    // 実行既定値
    private Long operatorId = 123456L;
    private String password = "PaSsWoRd";
    private String confirmPassword = "PaSsWoRd";
    private String oldPassword = "OldPaSsWoRd";
    private String newPassword = "PaSsWoRd";

    // 検証値
    private PasswordHistory actualModel;

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

    // テスト対象クラス生成
    private UpdatePassword createUpdatePassword() {
        PasswordHistoryRepositoryForStore passwordHistoryRepositoryForStore = new PasswordHistoryRepositoryForStore() {
            @Override
            public void store(PasswordHistory passwordHistory) {
                actualModel = passwordHistory;
            }
        };
        return new UpdatePassword(passwordHistoryRepositoryForStore);
    }

    /**
     * {@link UpdatePassword#execute(PasswordResetRequest request)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test_ResetRequest() {
        // テスト対象クラス生成
        UpdatePassword updatePassword = createUpdatePassword();

        // 実行値
        PasswordResetRequest request = createPasswordResetRequest();

        assertThatCode(() ->
            // 実行
            updatePassword.execute(request))
            .doesNotThrowAnyException();
    }

    /**
     * {@link UpdatePassword#execute(PasswordChangeRequest request)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test_ChangeRequest() {
        // テスト対象クラス生成
        UpdatePassword updatePassword = createUpdatePassword();

        // 実行値
        PasswordChangeRequest request = createPasswordChangeRequest();

        assertThatCode(() ->
            // 実行
            updatePassword.execute(request))
            .doesNotThrowAnyException();
    }

    /**
     * {@link UpdatePassword#execute(PasswordResetRequest request)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・PasswordHistoryへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test1_ResetRequest() {
        // テスト対象クラス生成
        UpdatePassword updatePassword = createUpdatePassword();

        // 実行値
        PasswordResetRequest request = createPasswordResetRequest();

        // 期待値
        PasswordHistory expectedModel = PasswordHistory.createFrom(
            null,
            operatorId,
            LocalDateTime.now(),
            password,
            PasswordChangeType.管理者によるリセット,
            null,
            null);

        // 実行
        updatePassword.execute(request);

        // 結果検証
        assertThat(actualModel.getPasswordHistoryId()).isEqualTo(expectedModel.getPasswordHistoryId());
        assertThat(actualModel.getOperatorId()).isEqualTo(expectedModel.getOperatorId());
        assertThat(actualModel.getPassword()).isEqualTo(expectedModel.getPassword());
        assertThat(actualModel.getPasswordChangeType()).isEqualTo(expectedModel.getPasswordChangeType());
        assertThat(actualModel.getRecordVersion()).isEqualTo(expectedModel.getRecordVersion());
        assertThat(actualModel.getOperator()).isEqualTo(expectedModel.getOperator());
    }

    /**
     * {@link UpdatePassword#execute(PasswordChangeRequest request)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・PasswordHistoryへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test1_ChangeRequest() {
        // テスト対象クラス生成
        UpdatePassword updatePassword = createUpdatePassword();

        // 実行値
        PasswordChangeRequest request = createPasswordChangeRequest();

        // 期待値
        PasswordHistory expectedModel = PasswordHistory.createFrom(
            null,
            operatorId,
            LocalDateTime.now(),
            password,
            PasswordChangeType.ユーザーによる変更,
            null,
            null);

        // 実行
        updatePassword.execute(request);

        // 結果検証
        assertThat(actualModel.getPasswordHistoryId()).isEqualTo(expectedModel.getPasswordHistoryId());
        assertThat(actualModel.getOperatorId()).isEqualTo(expectedModel.getOperatorId());
        assertThat(actualModel.getPassword()).isEqualTo(expectedModel.getPassword());
        assertThat(actualModel.getPasswordChangeType()).isEqualTo(expectedModel.getPasswordChangeType());
        assertThat(actualModel.getRecordVersion()).isEqualTo(expectedModel.getRecordVersion());
        assertThat(actualModel.getOperator()).isEqualTo(expectedModel.getOperator());
    }
}