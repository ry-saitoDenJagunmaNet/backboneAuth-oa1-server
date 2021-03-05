package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.BackboneAuthConfig;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInResponse;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SignInTest {

    // 実行既定値
    private String operatorCode = "yu001009";
    private String password = "password12345";
    private String clientIpaddress = "001.001.001.001";
    private Integer mode = 1;

    // 検証値
    private Short actualSignInResultCode;
    private String actualSignInResultMessage;
    private String actualAccessToken;

    // サインインサービス作成（テスト対象クラス）
    private SignIn createSignIn() {
        // 基幹系認証 Configのスタブ
        BackboneAuthConfig backboneAuthConfig = new BackboneAuthConfig();
        return new SignIn(backboneAuthConfig);
    }
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
            public Integer getMode() {
                return mode;
            }
        };
    }
    // サインインサービス Response作成
    private SignInResponse createSignInResponse() {
        return new SignInResponse() {
            @Override
            public void setSignInResultCode(Short signInResultCode) {
                actualSignInResultCode = signInResultCode;
            }
            @Override
            public void setSignInResultMessage(String signInResultMessage) {
                actualSignInResultMessage = signInResultMessage;
            }
            @Override
            public void setAccessToken(String accessToken) {
                actualAccessToken = accessToken;
            }
        };
    };

    /**
     * {@link SignIn#execute(SignInRequest, SignInResponse)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test0() {

        // テスト対象クラス生成
        SignIn signIn = createSignIn();

        // 実行値
        SignInRequest request = createSignInRequest();
        SignInResponse response = createSignInResponse();

        // 期待値

        // ToDo: 認証Apiを呼ぶテスト方式が不明
//        assertThatCode(() ->
//            // 実行
//            signIn.execute(request, response)).doesNotThrowAnyException();

        // 結果検証
    }
}