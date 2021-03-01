package net.jagunma.backbone.auth.authmanager.application.commandService;

import java.net.URI;
import net.jagunma.backbone.auth.authmanager.application.BackboneAuthConfig;
import net.jagunma.backbone.auth.authmanager.application.base.BaseOfService;
import net.jagunma.backbone.auth.authmanager.application.commandService.dto.SignInRequestDto;
import net.jagunma.backbone.auth.authmanager.application.commandService.dto.SignInResponseDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInResponse;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * サインインサービス
 */
@Service
public class SignIn extends BaseOfService {

    // 認可API 業務オペレーター認証 URI
    private final String authenticationUriPath = "/oa31010/authentication";

    // コンストラクタ
    public SignIn(BackboneAuthConfig backboneAuthConfig) {
        super(backboneAuthConfig);
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

        // 認証apiのUriを設定
        URI uri = createBackboneAuthOa3ServeUri(authenticationUriPath);

        // 認証apiでサインイン
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        SignInResponseDto signInResponseDto = restTemplate.postForObject(uri, signInRequestDto, SignInResponseDto.class);

        if (SignInResult.codeOf(signInResponseDto.getSignInResultCode()).is成功()) {
            // ToDo: 認証に成功したらAccessTokenを取得
            response.setAccessToken("AccessToken12345");
        }

        response.setSignInResultCode(signInResponseDto.getSignInResultCode());
        response.setSignInResultMessage(signInResponseDto.getSignInResultMessage());
    }
}
