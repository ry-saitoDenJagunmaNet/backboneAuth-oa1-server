package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import net.jagunma.backbone.auth.authmanager.application.BackboneAuthConfig;
import net.jagunma.backbone.auth.authmanager.application.commandService.dto.SignInRequestDto;
import net.jagunma.backbone.auth.authmanager.application.commandService.dto.SignInResponseDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInResponse;
import net.jagunma.backbone.auth.authmanager.application.util.HttpSendUtil;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SignInTest {

    // 実行既定値
    private String operatorCode = "yu001009";
    private String password = "password12345";
    private String clientIpaddress = "001.001.001.001";
    private Short mode = 1;
    private SignInResult signInResult = SignInResult.成功;

    // 検証値
    private Short actualSignInResultCode;
    private String actualSignInResultMessage;
    private String actualAccessToken;

    private String actualRestTemplateUtilPath;
    private Object actualRequest;
    private Class<?> actualClazz;

    // サインイン結果Dtoデータ作成
    private SignInResponseDto createSignInResponseDto() {
        SignInResponseDto dto = new SignInResponseDto();
        dto.setSignInResultCode(signInResult.getCode());
        dto.setSignInResultMessage(signInResult.getDisplayName());
        return dto;
    }
    // サインインサービス作成（テスト対象クラス）
    private SignIn createSignIn() {
        // 基幹系認証 Configのスタブ
        BackboneAuthConfig backboneAuthConfig = new BackboneAuthConfig();
        // RestTemplateUtilのスタブ
        HttpSendUtil httpSendUtil = new HttpSendUtil(backboneAuthConfig) {
            public Object postBackboneAuthOa3(String path, Object request, Class<?> clazz) {
                actualRestTemplateUtilPath = path;
                actualRequest = request;
                actualClazz = clazz;
                return createSignInResponseDto();
            }
        };
        return new SignIn(httpSendUtil);
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
            public Short getMode() {
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
     *  ・処理結果
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
        Short expectedSignInResultCode = signInResult.getCode();
        String expectedSignInResultMessage = signInResult.getDisplayName();
        String expectedAccessToken = "";
        String expectedRestTemplateUtilPath = "/oa31010/authentication";
        SignInRequestDto expectedRequest = SignInRequestDto.with(operatorCode, password, clientIpaddress);
        Class<?> expectedClazz = SignInResponseDto.class;

        // ToDo: 認証Apiを呼ぶテスト方式が不明Code
        assertThatCode(() ->
            // 実行
            signIn.execute(request, response)).doesNotThrowAnyException();

        // 結果検証
        assertThat(actualRestTemplateUtilPath).isEqualTo(expectedRestTemplateUtilPath);
        assertThat(actualRequest).usingRecursiveComparison().isEqualTo(expectedRequest);
        assertThat(actualClazz).isEqualTo(expectedClazz);
        assertThat(actualSignInResultCode).isEqualTo(expectedSignInResultCode);
        assertThat(actualSignInResultMessage).isEqualTo(expectedSignInResultMessage);
//        assertThat(actualAccessToken).isEqualTo(expectedAccessToken);
    }

    /**
     * {@link SignIn#execute(SignInRequest, SignInResponse)}テスト
     *  ●パターン
     *    サインイン失敗
     *
     *  ●検証事項
     *  ・失敗
     *  ・処理結果
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test1() {

        // テスト対象クラス生成
        SignIn signIn = createSignIn();

        // 実行値
        signInResult = SignInResult.失敗_パスワード誤り;
        SignInRequest request = createSignInRequest();
        SignInResponse response = createSignInResponse();

        // 期待値
        Short expectedSignInResultCode = signInResult.getCode();
        String expectedSignInResultMessage = signInResult.getDisplayName();
        String expectedAccessToken = null;
        String expectedRestTemplateUtilPath = "/oa31010/authentication";
        SignInRequestDto expectedRequest = SignInRequestDto.with(operatorCode, password, clientIpaddress);
        Class<?> expectedClazz = SignInResponseDto.class;

        // ToDo: 認証Apiを呼ぶテスト方式が不明Code
        assertThatCode(() ->
            // 実行
            signIn.execute(request, response)).doesNotThrowAnyException();

        // 結果検証
        assertThat(actualRestTemplateUtilPath).isEqualTo(expectedRestTemplateUtilPath);
        assertThat(actualRequest).usingRecursiveComparison().isEqualTo(expectedRequest);
        assertThat(actualClazz).isEqualTo(expectedClazz);
        assertThat(actualSignInResultCode).isEqualTo(expectedSignInResultCode);
        assertThat(actualSignInResultMessage).isEqualTo(expectedSignInResultMessage);
        assertThat(actualAccessToken).isEqualTo(expectedAccessToken);
    }
}