package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.util.strings2.Strings2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class EntryOperatorValidatorTest {

    // 実行既定値
    private String operatorCode6 = "123456";
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate validThruStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate validThruEndDate = LocalDate.of(2020, 9, 30);
    private Long branchId = 101L;
    private String changeCause = "新職員の入組による登録";
    private String password = "PaSsWoRd";
    private String confirmPassword = "PaSsWoRd";
    private OperatorEntryRequest createRequest() {
        return new OperatorEntryRequest() {
            @Override
            public String getOperatorCode6() {
                return operatorCode6;
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
            public Long getBranchId() {
                return branchId;
            }
            @Override
            public String getChangeCause() {
                return changeCause;
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

    /**
     * {@link EntryOperatorValidator#validate()}テスト
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
        OperatorEntryRequest request = createRequest();

        assertThatCode(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
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
        OperatorEntryRequest request = null;

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  オペレーターコード（下6桁）
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test02() {
        // 実行値
        operatorCode6 = null;
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("オペレーターコード（下6桁）");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
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
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("オペレーター名");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
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
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("有効期限（開始日）");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
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
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("有効期限（終了日）");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  店舗ID
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test06() {
        // 実行値
        branchId = null;
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("店舗ID");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  変更事由
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test07() {
        // 実行値
        changeCause = null;
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("変更事由");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  パスワード
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test08() {
        // 実行値
        password = null;
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("パスワード");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  パスワードの確認入力
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test09() {
        // 実行値
        confirmPassword = null;
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("パスワードの確認入力");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    桁数チェック  オペレーターコード（下6桁）
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test10() {
        // 実行値
        operatorCode6 = Strings2.repeat("*", 7);
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13003");
                assertThat(e.getArgs()).containsSequence("オペレーターコード（下6桁）", "6");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    桁数チェック  オペレーター名
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test11() {
        // 実行値
        operatorName = Strings2.repeat("*", 256);
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13003");
                assertThat(e.getArgs()).containsSequence("オペレーター名", "255", "以下");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    桁数チェック  メールアドレス
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test12() {
        // 実行値
        mailAddress = Strings2.repeat("*", 256);
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13003");
                assertThat(e.getArgs()).containsSequence("メールアドレス", "255", "以下");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    桁数チェック  変更事由
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test13() {
        // 実行値
        changeCause = Strings2.repeat("*", 256);
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13003");
                assertThat(e.getArgs()).containsSequence("変更事由", "255", "以下");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    桁数チェック  パスワード
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test14() {
        // 実行値
        password = Strings2.repeat("*", 7);
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13004");
                assertThat(e.getArgs()).containsSequence("パスワード", "8", "以上", "255", "以下");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    桁数チェック  パスワード
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test15() {
        // 実行値
        password = Strings2.repeat("*", 256);
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13004");
                assertThat(e.getArgs()).containsSequence("パスワード", "8", "以上", "255", "以下");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    全角混入チェック  オペレーターコード（下6桁）
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Disabled // ToDo:
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test16() {
        // 実行値
        operatorCode6 = "123全56";
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13005");
                assertThat(e.getArgs()).containsSequence("オペレーターコード（下6桁）");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
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
    void validate_Test17() {
        // 実行値
        mailAddress = "te全st@den.jagunma.net";
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13005");
                assertThat(e.getArgs()).containsSequence("メールアドレス");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    全角混入チェック  パスワード
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Disabled // ToDo:
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test18() {
        // 実行値
        password = "PaSs全WoRd";
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13005");
                assertThat(e.getArgs()).containsSequence("パスワード");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    数値チェック  オペレーターコード（下6桁）
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test19() {
        // 実行値
        operatorCode6 = "1.3-aB";
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13006");
                assertThat(e.getArgs()).containsSequence("オペレーターコード（下6桁）");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    範囲指定不正チェック  有効期限
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test20() {
        // 実行値
        validThruEndDate = LocalDate.of(2020, 8, 31);
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("有効期限");
            });
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    パスワード不一致チェック
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test21() {
        confirmPassword = "pAsSwOrD";
        OperatorEntryRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            EntryOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13101");
            });
    }
}
