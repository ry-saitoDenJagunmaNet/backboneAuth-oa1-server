package net.jagunma.backbone.auth.authmanager.application.queryService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.net.URI;
import net.jagunma.backbone.auth.authmanager.application.BackboneAuthConfig;
import net.jagunma.backbone.auth.authmanager.application.base.BaseOfService;
import net.jagunma.backbone.auth.authmanager.application.usecase.profileReference.ProfileSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.profileReference.ProfileSearchResponse;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.values.model.operator.SimpleOperator;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Profile検索サービス
 */
@Service
public class SearchProfile extends BaseOfService {

    // ProFile取得api URI（本当はao2へ接続）（oa2への接続方法確認）
    private final String GET_SIMPLEOPERATOR_URI_PATH = "/oa31010/getSimpleOperator";

    // コンストラクタ
    public SearchProfile(BackboneAuthConfig backboneAuthConfig) {
        super(backboneAuthConfig);
    }

    /**
     * オペレーター（簡略版）を検索します
     *
     * @param request  Profile検索サービス Request
     * @param response Profile検索サービス Response
     */
    public void execute(ProfileSearchRequest request, ProfileSearchResponse response) {

        // ProFile取得apiのUriを設定
        URI uri = createBackboneAuthOa3ServeUri(GET_SIMPLEOPERATOR_URI_PATH);

        // ToDo: オペレーター情報取得（oa2への接続方法確認）
        // 認証apiでサインイン
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        String simpleOperatorString = restTemplate.postForObject(uri, request.getOperatorCode(), String.class);

        ObjectMapper mp = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            response.setSimpleOperator(mp.readValue(simpleOperatorString, SimpleOperator.class));
        } catch (HttpClientErrorException e) {
            throw new GunmaRuntimeException("EOX10001");
        } catch (HttpServerErrorException | JsonProcessingException e) {
            throw new GunmaRuntimeException("EOX10002");
        }
    }

}
