package net.jagunma.backbone.auth.authmanager.infra.web.ed01000;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.jagunma.backbone.auth.authmanager.application.BackboneAuthConfig;
import net.jagunma.backbone.auth.authmanager.application.commandService.SignIn;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInResponse;
import net.jagunma.backbone.auth.authmanager.application.util.HttpSendUtil;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01000.vo.Ed01000Vo;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class Ed01000ControllerTest {

    // 実行既定値
    private final ConcurrentModel model = new ConcurrentModel();
    private final MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
    private final String redirectUri = "http://001.001.001.001:1234/redirectUri/get";
    private SignInCause signInCause = SignInCause.サインイン;
    private SignInResult signInResult = SignInResult.成功;
    private final String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik5HVEZ2ZEstZnl0aEV1";

    // 検証値
    SignInRequest actualSignInRequest;

    // テスト対象クラス生成
    private Ed01000Controller createEd01000Controller() {
        // BackboneAuthConfigのスタブ
        BackboneAuthConfig backboneAuthConfig = new BackboneAuthConfig();
        // Http送信 ユーティリティのスタブ
        HttpSendUtil httpSendUtil = new HttpSendUtil(backboneAuthConfig);
        // サインインサービスのスタブ
        SignIn signIn = new SignIn(httpSendUtil) {
            public void execute(SignInRequest request, SignInResponse response) {
                actualSignInRequest = request;
                if (request.getMode() == SignInCause.UnKnown.getCode()) {
                    throw new RuntimeException();
                }
                response.setSignInResultCode(signInResult.getCode());
                response.setSignInResultMessage(signInResult.getDisplayName());
                response.setAccessToken(accessToken);
            }
        };
        return new Ed01000Controller(signIn);
    }

    /**
     * {@link Ed01000Controller#get(String, HttpServletRequest, Model)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・戻り値
     *  ・Voへのセット
     */
    @Disabled
    @Test
    @Tag(TestSize.SMALL)
    void get_test0() {

        // テスト対象クラス生成
        Ed01000Controller controller = createEd01000Controller();

        // 実行値
        String redirect_uri = "http://001.001.001.001:1234/redirectUri/get";
        controller.setHttpSession(mockHttpServletRequest.getSession());

        // 期待値

        // 実行
        String result = controller.get(redirect_uri, mockHttpServletRequest, model);

        // 結果検証
    }

    /**
     * {@link Ed01000Controller#oAuthReception(String, String, String, Model)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void oAuthReception_test0() {

        // テスト対象クラス生成
        Ed01000Controller controller = createEd01000Controller();

        // 実行値
        controller.setHttpSession(mockHttpServletRequest.getSession());
        String error = "";
        String code = "code12345";
        String state = "state12345";
        Map<String, String> sessionStringMap = new HashMap<>();
        sessionStringMap.put("redirect_uri", redirectUri);
        sessionStringMap.put("state", state);
        controller.setSessionAttribute(controller.SESSIONKEY_STRING_MAP, sessionStringMap);

        // 期待値
        String expected = "ed01000";
        Ed01000Vo expectedVo = new Ed01000Vo();
        expectedVo.setRedirectUri(redirectUri);
        expectedVo.setMode(signInCause.getCode());

        // 実行
        String actual = controller.oAuthReception(error, code, state, model);
        Ed01000Vo actualVo = (Ed01000Vo)model.getAttribute("form");

        // 結果検証
        assertThat(actual).isEqualTo(expected);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Ed01000Controller#signIn(HttpServletRequest, Model, Ed01000Vo)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void signIn_test0() {

        // テスト対象クラス生成
        Ed01000Controller controller = createEd01000Controller();

        // 実行値
        String operatorCode = "yu001009";
        String password = "password12345";
        Ed01000Vo vo = new Ed01000Vo();
        vo.setOperatorCode(operatorCode);
        vo.setPassword(password);
        vo.setRedirectUri(redirectUri);
        vo.setMode(signInCause.getCode());
        String clientIpaddress = "001.001.001.001";
        mockHttpServletRequest.setLocalAddr(clientIpaddress);

        // 期待値
        String expected = String.format("redirect:%1$s?access_token=%2$s", redirectUri, accessToken);

        // 実行
        String actual = controller.signIn(mockHttpServletRequest, model, vo);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
        assertThat(actualSignInRequest.getOperatorCode()).isEqualTo(operatorCode);
        assertThat(actualSignInRequest.getPassword()).isEqualTo(password);
        assertThat(actualSignInRequest.getMode()).isEqualTo(signInCause.getCode());
        assertThat(actualSignInRequest.getClientIpaddress()).isEqualTo(clientIpaddress);
    }

    /**
     * {@link Ed01000Controller#signIn(HttpServletRequest, Model, Ed01000Vo)}のテスト
     *  ●パターン
     *    正常（サインイン失敗）
     *
     *  ●検証事項
     *  ・戻り値
     *  ・例外（GunmaRuntimeException ）発生
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void signIn_test1() {

        // テスト対象クラス生成
        Ed01000Controller controller = createEd01000Controller();

        // 実行値
        signInResult = SignInResult.失敗_パスワード誤り;
        String operatorCode = "yu001009";
        String password = "password12345";
        Ed01000Vo vo = new Ed01000Vo();
        vo.setOperatorCode(operatorCode);
        vo.setPassword(password);
        vo.setRedirectUri(redirectUri);
        vo.setMode(signInCause.getCode());
        String clientIpaddress = "001.001.001.001";
        mockHttpServletRequest.setLocalAddr(clientIpaddress);

        // 期待値
        String expected = "ed01000";
        String expectedMessageCode = "EOA12006";

        // 実行
        String actual = controller.signIn(mockHttpServletRequest, model, vo);
        Ed01000Vo actualVo = (Ed01000Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actual).isEqualTo(expected);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }

    /**
     * {@link Ed01000Controller#signIn(HttpServletRequest, Model, Ed01000Vo)}のテスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void signIn_test2() {

        // テスト対象クラス生成
        Ed01000Controller controller = createEd01000Controller();

        // 実行値
        signInCause = SignInCause.UnKnown;
        String operatorCode = "yu001009";
        String password = "password12345";
        Ed01000Vo vo = new Ed01000Vo();
        vo.setOperatorCode(operatorCode);
        vo.setPassword(password);
        vo.setRedirectUri(redirectUri);
        vo.setMode(signInCause.getCode());
        String clientIpaddress = "001.001.001.001";
        mockHttpServletRequest.setLocalAddr(clientIpaddress);

        // 期待値
        String expected = "oa19999";
        String expectedMessageCode = "EOA10001";

        // 実行
        String actual = controller.signIn(mockHttpServletRequest, model, vo);
        Ed01000Vo actualVo = (Ed01000Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actual).isEqualTo(expected);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }
}