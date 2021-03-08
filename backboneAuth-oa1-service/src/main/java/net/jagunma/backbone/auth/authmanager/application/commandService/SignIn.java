package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.commandService.dto.SignInRequestDto;
import net.jagunma.backbone.auth.authmanager.application.commandService.dto.SignInResponseDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInResponse;
import net.jagunma.backbone.auth.authmanager.application.util.RestTemplateUtil;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import org.springframework.stereotype.Service;

/**
 * サインインサービス
 */
@Service
public class SignIn {

    // 認可api 業務オペレーター認証 URI
    private final String AUTHENTICATION_URI_PATH = "/oa31010/authentication";

    private RestTemplateUtil restTemplateUtil;

    // コンストラクタ
    public SignIn(RestTemplateUtil restTemplateUtil) {
        this.restTemplateUtil = restTemplateUtil;
    }

    /**
     * サインインを行います
     *
     * @param request サインインサービス Request
     * @param response サインインサービス Response
     */
    public void execute(SignInRequest request, SignInResponse response) {

        // パラメーターの検証
        SignInValidator.with(request).validate();

        SignInRequestDto signInRequestDto = SignInRequestDto.with(
            request.getOperatorCode(), request.getPassword(), request.getClientIpaddress());

        // 認証apiでサインイン
        SignInResponseDto signInResponseDto = (SignInResponseDto)restTemplateUtil.postBackboneAuthOa3(AUTHENTICATION_URI_PATH, signInRequestDto, SignInResponseDto.class);

        if (SignInResult.codeOf(signInResponseDto.getSignInResultCode()).is成功()) {
            // ToDo: 認証に成功したらAccessTokenを取得（oa2への接続方法確認）

//            response.setAccessToken("AccessToken12345");
            // ToDo: 暫定でオペレーターコードを設定。この値オペレータ―情報の取得で使用する
            response.setAccessToken(request.getOperatorCode());
        }

        response.setSignInResultCode(signInResponseDto.getSignInResultCode());
        response.setSignInResultMessage(signInResponseDto.getSignInResultMessage());
    }
}
