package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.collect.Lists2;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class EntryOperatorValidatorTest {

    // 実行既定値
    private String operatorCode6 = "123456";
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate expirationStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate expirationEndDate = LocalDate.of(2020, 9, 30);
    private Long tempoId = 101L;
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
            public LocalDate getExpirationStartDate() {
                return expirationStartDate;
            }
            @Override
            public LocalDate getExpirationEndDate() {
                return expirationEndDate;
            }
            @Override
            public Long getTempoId() {
                return tempoId;
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
    void validate_Test0() {
        // 実行値
        OperatorEntryRequest request = createRequest();
        try {
            // 実行
            EntryOperatorValidator.with(request).validate();
        } catch (GunmaRuntimeException e) {
            // 結果検証
            assertTrue(false);
        }
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    リクエスト不正
     *
     *  ●検証事項
     *  ・ エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test1() {
        // 実行値
        OperatorEntryRequest request = null;
        try {
            // 実行
            EntryOperatorValidator.with(request).validate();
        } catch (GunmaRuntimeException e) {
            // 結果検証
            assertThat(e.getMessageCode()).isEqualTo("EOA13001");
        }
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セット   オペレーターコード（下6桁）
     *
     *  ●検証事項
     *  ・ エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test2() {
        // 実行値
        operatorCode6 = null;
        OperatorEntryRequest request = createRequest();
        try {
            // 実行
            EntryOperatorValidator.with(request).validate();
        } catch (GunmaRuntimeException e) {
            // 結果検証
            assertThat(e.getMessageCode()).isEqualTo("EOA13002");
        }
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セット   オペレーター名
     *
     *  ●検証事項
     *  ・ エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test3() {
        // 実行値
        operatorName = null;
        OperatorEntryRequest request = createRequest();
        try {
            // 実行
            EntryOperatorValidator.with(request).validate();
        } catch (GunmaRuntimeException e) {
            // 結果検証
            assertThat(e.getMessageCode()).isEqualTo("EOA13002");
        }
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セット   有効期限（開始日）
     *
     *  ●検証事項
     *  ・ エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test4() {
        // 実行値
        expirationStartDate = null;
        OperatorEntryRequest request = createRequest();
        try {
            // 実行
            EntryOperatorValidator.with(request).validate();
        } catch (GunmaRuntimeException e) {
            // 結果検証
            assertThat(e.getMessageCode()).isEqualTo("EOA13002");
        }
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セット   有効期限（終了日）
     *
     *  ●検証事項
     *  ・ エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test5() {
        // 実行値
        expirationEndDate = null;
        OperatorEntryRequest request = createRequest();
        try {
            // 実行
            EntryOperatorValidator.with(request).validate();
        } catch (GunmaRuntimeException e) {
            // 結果検証
            assertThat(e.getMessageCode()).isEqualTo("EOA13002");
        }
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セット   店舗ID
     *
     *  ●検証事項
     *  ・ エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test6() {
        // 実行値
        tempoId = null;
        OperatorEntryRequest request = createRequest();
        try {
            // 実行
            EntryOperatorValidator.with(request).validate();
        } catch (GunmaRuntimeException e) {
            // 結果検証
            assertThat(e.getMessageCode()).isEqualTo("EOA13002");
        }
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セット   変更事由
     *
     *  ●検証事項
     *  ・ エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test7() {
        // 実行値
        changeCause = null;
        OperatorEntryRequest request = createRequest();
        try {
            // 実行
            EntryOperatorValidator.with(request).validate();
        } catch (GunmaRuntimeException e) {
            // 結果検証
            assertThat(e.getMessageCode()).isEqualTo("EOA13002");
        }
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セット   パスワード
     *
     *  ●検証事項
     *  ・ エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test8() {
        // 実行値
        password = null;
        OperatorEntryRequest request = createRequest();
        try {
            // 実行
            EntryOperatorValidator.with(request).validate();
        } catch (GunmaRuntimeException e) {
            // 結果検証
            assertThat(e.getMessageCode()).isEqualTo("EOA13002");
        }
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    未セット   パスワードの確認入力
     *
     *  ●検証事項
     *  ・ エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test9() {
        // 実行値
        confirmPassword = null;
        OperatorEntryRequest request = createRequest();
        try {
            // 実行
            EntryOperatorValidator.with(request).validate();
        } catch (GunmaRuntimeException e) {
            // 結果検証
            assertThat(e.getMessageCode()).isEqualTo("EOA13002");
        }
    }

    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    桁数チェック   オペレーターコード（下6桁）
     *
     *  ●検証事項
     *  ・ エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test10() {
        // 実行値
        operatorCode6 = "1234567";
        OperatorEntryRequest request = createRequest();
        try {
            // 実行
            EntryOperatorValidator.with(request).validate();
        } catch (GunmaRuntimeException e) {
            // 結果検証
            assertThat(e.getMessageCode()).isEqualTo("EOA13003");
            assertThat(e.getArgs()).containsSequence("オペレーターコード（下6桁）", "6");
        }
//        assertThatThrownBy(()->{
//            // 実行
//            EntryOperatorValidator.with(request).validate();
//
//        }).isInstanceOf(GunmaRuntimeException.class).hasMessage
    }








    /**
     * {@link EntryOperatorValidator#validate()}テスト
     *  ●パターン
     *    パスワード不一致
     *
     *  ●検証事項
     *  ・ エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test________() {
        confirmPassword = "pAsSwOrD";
        OperatorEntryRequest request = createRequest();
        try {
            // 実行
            EntryOperatorValidator.with(request).validate();
        } catch (GunmaRuntimeException e) {
            // 結果検証
            assertThat(e.getMessageCode()).isEqualTo("EOA13101");
        }
    }
}