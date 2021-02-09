package net.jagunma.backbone.auth.authmanager.application.commandService;

import java.net.URI;
import net.jagunma.backbone.auth.authmanager.application.BackboneAuthConfig;
import net.jagunma.backbone.auth.authmanager.application.base.BaseOfService;
import net.jagunma.backbone.auth.authmanager.application.commandService.dto.SignInRequestDto;
import net.jagunma.backbone.auth.authmanager.application.commandService.dto.SignInResponseDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * サインインサービス
 */
@Service
public class SignIn extends BaseOfService {

    private final Integer MODE_RESIGNIN = 1;

    public SignIn(BackboneAuthConfig backboneAuthConfig) {
        super(backboneAuthConfig);
    }

    /**
     * サインインを行います
     *
     * @param request サインインサービス Request
     * @param request サインインサービス Response
     */
    public void execute(SignInRequest request, SignInResponse response) {

        String operatorCode = request.getOperatorCode();

        // ToDo: 再認証の場合、オペレーターコードが無いのでアクセストークンからProfileを取得
        if (request.getMode().equals(MODE_RESIGNIN)) {
//            OperatorProfile operatorProfile = getOperatorProfile(request.getAccessToken());
//            operatorCode = operatorProfile.getOperatorCode();
        }

        SignInRequestDto signInRequestDto = SignInRequestDto.with(request.getOperatorCode(), request.getPassword());

        // 認証apiのUrlを設定
        final String path = "/oa13010/signIn";
        URI uri = createBackboneAuthOa3ServeUri(path);

        // 認証apiでサインイン
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        SignInResponseDto signInResponseDto = restTemplate.postForObject(uri, signInRequestDto, SignInResponseDto.class);

        signInResponseDto.bindTo(response);
    }

//    private OperatorProfile getOperatorProfile(String accessToken) {
//
//        // 認証apiのUrlを設定
//        final String path = "/oa2XXXXX/getProfile";
//        URI uri = createBackboneAuthOa2ServeUri(path);
//
//        // オペレーターＰｒｏＦｉｌｅを取得
//        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        return restTemplate.postForObject(uri, accessToken, OperatorProfile.class);
//
//    }
}
