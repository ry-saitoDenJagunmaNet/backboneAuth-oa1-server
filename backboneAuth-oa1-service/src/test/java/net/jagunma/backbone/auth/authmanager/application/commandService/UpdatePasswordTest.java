package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordChangeRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordResetRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoriesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UpdatePasswordTest {

    // 実行既定値
    private Long operatorId = 123456L;
    private String password = "PaSsWoRd";
    private String oldPassword = "OldPaSsWoRd";
    private String newPassword = "PaSsWoRd";
    private String confirmPassword = "PaSsWoRd";

    private String passwordLastTime = "PasswordLastTime";
    private String password2TimesBefore = "PasswordTwoTimesBefore";
    private String password3TimesBefore = "PasswordThreeTimesBefore";
    private PasswordChangeType passwordChangeTypeLastTime = PasswordChangeType.ユーザーによる変更;
    private PasswordChangeType passwordChangeType2TimesBefore = PasswordChangeType.管理者によるリセット;
    private PasswordChangeType passwordChangeType3TimesBefore = PasswordChangeType.初期;

    // パスワード履歴生成
    private PasswordHistories createPasswordHistories() {
        List<PasswordHistory> passwordHistoryList = newArrayList(
            PasswordHistory.createFrom(202L, operatorId, LocalDateTime.of(2020,10,1,8,31,12), passwordLastTime, passwordChangeTypeLastTime, 1, null),
            PasswordHistory.createFrom(201L, operatorId, LocalDateTime.of(2020,10,1,8,30,12), password2TimesBefore, passwordChangeType2TimesBefore, 1, null),
            PasswordHistory.createFrom(200L, operatorId, LocalDateTime.of(2020,10,1,8,29,12), password3TimesBefore, passwordChangeType3TimesBefore, 1, null));

        return PasswordHistories.createFrom(passwordHistoryList);
    }

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

            }
        };
        PasswordHistoriesRepository passwordHistoriesRepository = new PasswordHistoriesRepository() {
            @Override
            public PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria, Orders orders) {
                return createPasswordHistories();
            }
        };

        return new UpdatePassword(passwordHistoryRepositoryForStore, passwordHistoriesRepository);
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
        passwordLastTime = oldPassword;

        assertThatCode(() ->
            // 実行
            updatePassword.execute(request))
            .doesNotThrowAnyException();
    }

    /**
     * {@link UpdatePassword#checkNotDeviceAuthPassword(Long operatorId)}テスト
     *  ●パターン
     *    現在の変更種別が「機器認証パスワード」でないかのチェック）機器認証パスワード
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkNotDeviceAuthPassword_test() {
        // テスト対象クラス生成
        UpdatePassword updatePassword = createUpdatePassword();

        // 実行値
        passwordChangeTypeLastTime = PasswordChangeType.機器認証パスワード;

        assertThatThrownBy(() ->
            // 実行
            updatePassword.checkNotDeviceAuthPassword(operatorId))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA12002");
            });
    }

    /**
     * {@link UpdatePassword#checkPastGenerationsPassword(PasswordChangeRequest request)}テスト
     *  ●パターン
     *    機器認証パスワード以外での過去世代のパスワードのチェック
     *    ・リクエストの古いパスワードと現在のパスワードが同じかのチェック）不一致
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkPastGenerationsPassword_test1() {
        // テスト対象クラス生成
        UpdatePassword updatePassword = createUpdatePassword();

        // 実行値
        PasswordChangeRequest request = createPasswordChangeRequest();

        assertThatThrownBy(() ->
            // 実行
            updatePassword.checkPastGenerationsPassword(request))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA12003");
            });
    }

    /**
     * {@link UpdatePassword#checkPastGenerationsPassword(PasswordChangeRequest request)}テスト
     *  ●パターン
     *    機器認証パスワード以外での過去世代のパスワードのチェック
     *    ・現在と過去2世代に同じパスワードを使用していないか）使用している
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkPastGenerationsPassword_test2() {
        // テスト対象クラス生成
        UpdatePassword updatePassword = createUpdatePassword();

        // 実行値
        PasswordChangeRequest request = createPasswordChangeRequest();
        passwordLastTime = oldPassword;
        password2TimesBefore = newPassword;

        assertThatThrownBy(() ->
            // 実行
            updatePassword.checkPastGenerationsPassword(request))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA12004");
            });
    }

    /**
     * {@link UpdatePassword#createPasswordHistory(Long operatorId, String password, PasswordChangeType passwordChangeType)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・PasswordHistoryへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createPasswordHistory_test() {
        // テスト対象クラス生成
        UpdatePassword updatePassword = createUpdatePassword();

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
        PasswordHistory passwordHistory = updatePassword.createPasswordHistory(operatorId, password, PasswordChangeType.管理者によるリセット);

        // 結果検証
        assertThat(passwordHistory.getPasswordHistoryId()).isEqualTo(expectedModel.getPasswordHistoryId());
        assertThat(passwordHistory.getOperatorId()).isEqualTo(expectedModel.getOperatorId());
        assertThat(passwordHistory.getChangeDateTime().toLocalDate().equals(expectedModel.getChangeDateTime().toLocalDate()));
        assertThat(passwordHistory.getPassword()).isEqualTo(expectedModel.getPassword());
        assertThat(passwordHistory.getPasswordChangeType()).isEqualTo(expectedModel.getPasswordChangeType());
        assertThat(passwordHistory.getRecordVersion()).isEqualTo(expectedModel.getRecordVersion());
        assertThat(passwordHistory.getOperator()).isEqualTo(expectedModel.getOperator());
    }
}