package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import net.jagunma.backbone.auth.authmanager.application.usecase.signInTraceCommand.SignInTraceStoreRequest;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StoreSignInTraceValidatorTest {

    // 実行既定値
    private String tryIpAddress = "001.001.001.001";
    private String operatorCode = "yu001009";
    private SignInCause signInCause = SignInCause.サインイン;
    private SignInResult signInResult = SignInResult.成功;

    // サインイン証跡登録サービス Requestのスタブ
    private SignInTraceStoreRequest createRequest() {
        return new SignInTraceStoreRequest() {
            @Override
            public String getTryIpAddress() {
                return tryIpAddress;
            }
            @Override
            public String getOperatorCode() {
                return operatorCode;
            }
            @Override
            public SignInCause getSignInCause() {
                return signInCause;
            }
            @Override
            public SignInResult getSignInResult() {
                return signInResult;
            }
        };
    }

    /**
     * {@link StoreSignInTraceValidator#validate()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test0() {

        // 実行値
        SignInTraceStoreRequest request = createRequest();

        assertThatCode(() ->
            // 実行
            StoreSignInTraceValidator.with(request).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link StoreSignInTraceValidator#validate()}テスト
     *  ●パターン
     *    リクエスト不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test1() {

        // 実行値
        SignInTraceStoreRequest request = null;

        assertThatThrownBy(() ->
            // 実行
            StoreSignInTraceValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link StoreSignInTraceValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  試行IPアドレス
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test2() {

        // 実行値
        tryIpAddress = null;
        SignInTraceStoreRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            StoreSignInTraceValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("試行IPアドレス");
            });
    }

    /**
     * {@link StoreSignInTraceValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  オペレーターコード
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test3() {

        // 実行値
        operatorCode = null;
        SignInTraceStoreRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            StoreSignInTraceValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("オペレーターコード");
            });
    }

    /**
     * {@link StoreSignInTraceValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  サインイン起因
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test4() {

        // 実行値
        signInCause = null;
        SignInTraceStoreRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            StoreSignInTraceValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("サインイン起因");
            });
    }

    /**
     * {@link StoreSignInTraceValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  サインイン結果
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test5() {

        // 実行値
        signInResult = null;
        SignInTraceStoreRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            StoreSignInTraceValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("サインイン結果");
            });
    }
}