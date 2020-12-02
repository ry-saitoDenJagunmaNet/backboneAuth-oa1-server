package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorUpdateRequest;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.util.strings2.Strings2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UpdateOperatorValidatorTest {

    // 実行既定値
    private Long operatorId = 123456L;
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate validThruStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate validThruEndDate = LocalDate.of(2020, 9, 30);
    private Boolean isDeviceAuth = true;
    private Long branchId = 1L;
    private AvailableStatus availableStatus = AvailableStatus.利用可能;
    private Integer recordVersion = 1;
    private String changeCause = "認証機器使用開始";
    private OperatorUpdateRequest createRequest() {
        return new OperatorUpdateRequest() {
            @Override
            public Long getOperatorId() {
                return operatorId;
            }
            @Override
            public String getOperatorName() {
                return operatorName;
            }
            @Override
            public String getMailAddress() {
                return mailAddress;
            }
            @Override
            public LocalDate getValidThruStartDate() {
                return validThruStartDate;
            }
            @Override
            public LocalDate getValidThruEndDate() {
                return validThruEndDate;
            }
            @Override
            public Boolean getIsDeviceAuth() {
                return isDeviceAuth;
            }
            @Override
            public Long getBranchId() {
                return branchId;
            }
            @Override
            public AvailableStatus getAvailableStatus() {
                return availableStatus;
            }
            @Override
            public Integer getRecordVersion() {
                return recordVersion;
            }
            @Override
            public String getChangeCause() {
                return changeCause;
            }
        };
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test() {
        // 実行値
        OperatorUpdateRequest request = createRequest();

        assertThatCode(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    リクエスト不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test01() {
        // 実行値
        OperatorUpdateRequest request = null;

        assertThatThrownBy(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  オペレーターID
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test02() {
        // 実行値
        operatorId = null;
        OperatorUpdateRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("オペレーターID");
            });
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  オペレーター名
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test03() {
        // 実行値
        operatorName = null;
        OperatorUpdateRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("オペレーター名");
            });
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  有効期限（開始日）
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test04() {
        // 実行値
        validThruStartDate = null;
        OperatorUpdateRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("有効期限（開始日）");
            });
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  有効期限（終了日）
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test05() {
        // 実行値
        validThruEndDate = null;
        OperatorUpdateRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("有効期限（終了日）");
            });
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  機器認証
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test06() {
        // 実行値
        isDeviceAuth = null;
        OperatorUpdateRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("機器認証");
            });
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  店舗ID
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test07() {
        // 実行値
        branchId = null;
        OperatorUpdateRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("店舗ID");
            });
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セッチェック  変更事由
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test08() {
        // 実行値
        changeCause = null;
        OperatorUpdateRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("変更事由");
            });
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  利用可否状態
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test09() {
        // 実行値
        availableStatus = null;
        OperatorUpdateRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("利用可否状態");
            });
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    桁数チェック  オペレーター名
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test10() {
        // 実行値
        operatorName = Strings2.repeat("*", 256);
        OperatorUpdateRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13003");
                assertThat(e.getArgs()).containsSequence("オペレーター名", "255", "以下");
            });
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    桁数チェック  メールアドレス
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test11() {
        // 実行値
        mailAddress = Strings2.repeat("*", 256);
        OperatorUpdateRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13003");
                assertThat(e.getArgs()).containsSequence("メールアドレス", "255", "以下");
            });
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    桁数チェック  変更事由
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test12() {
        // 実行値
        changeCause = Strings2.repeat("*", 256);
        OperatorUpdateRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13003");
                assertThat(e.getArgs()).containsSequence("変更事由", "255", "以下");
            });
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    全角混入チェック  メールアドレス
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Disabled // ToDo:
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test13() {
        // 実行値
        mailAddress = "te全st@den.jagunma.net";
        OperatorUpdateRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13005");
                assertThat(e.getArgs()).containsSequence("メールアドレス");
            });
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    列挙型未定義チェック  利用可否状態
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test14() {
        // 実行値
        availableStatus = AvailableStatus.UnKnown;
        OperatorUpdateRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13007");
                assertThat(e.getArgs()).containsSequence("利用可否状態");
            });
    }

    /**
     * {@link UpdateOperatorValidator#validate()}テスト
     *  ●パターン
     *    範囲指定不正チェック  有効期限
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test15() {
        // 実行値
        validThruEndDate = LocalDate.of(2020, 8, 31);
        OperatorUpdateRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            UpdateOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("有効期限");
            });
    }
}