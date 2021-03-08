package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInRequest;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SignInValidatorTest {

    // 実行既定値
    String operatorCode = "fb000002";
    String password = "password12345";
    String clientIpaddress = "001.002.003.004";
    SignInCause signInCause = SignInCause.サインイン;

    // サインインサービス Request作成
    private SignInRequest createSignInRequest() {
        return new SignInRequest() {
            @Override
            public String getOperatorCode() {
                return operatorCode;
            }
            @Override
            public String getPassword() {
                return password;
            }
            @Override
            public String getClientIpaddress() {
                return clientIpaddress;
            }
            @Override
            public Short getMode() {
                return signInCause.getCode();
            }
        };
    }

    /**
     * {@link SignInValidator#SignInValidator(SignInRequest)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test0() {

        // 実行値
        SignInRequest signInRequest = createSignInRequest();

        assertThatCode(() ->
            SignInValidator.with(signInRequest).validate()).doesNotThrowAnyException();
    }

    /**
     * {@link SignInValidator#SignInValidator(SignInRequest)}テスト
     *  ●パターン
     *    リクエスト不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test1() {

        // 実行値
        SignInRequest signInRequest = null;

        assertThatThrownBy(() ->
            // 実行
            SignInValidator.with(signInRequest).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link SignInValidator#SignInValidator(SignInRequest)}テスト
     *  ●パターン
     *    未セットチェック オペレーターコード
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test2() {

        // 実行値
        operatorCode = null;
        SignInRequest signInRequest = createSignInRequest();

        assertThatThrownBy(() ->
            // 実行
            SignInValidator.with(signInRequest).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("オペレーターコード");
            });
    }

    /**
     * {@link SignInValidator#SignInValidator(SignInRequest)}テスト
     *  ●パターン
     *    未セットチェック パスワード
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test3() {

        // 実行値
        password = null;
        SignInRequest signInRequest = createSignInRequest();

        assertThatThrownBy(() ->
            // 実行
            SignInValidator.with(signInRequest).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("パスワード");
            });
    }
}