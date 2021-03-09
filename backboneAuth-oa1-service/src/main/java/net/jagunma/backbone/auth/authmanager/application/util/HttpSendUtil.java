package net.jagunma.backbone.auth.authmanager.application.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.net.URI;
import net.jagunma.backbone.auth.authmanager.application.BackboneAuthConfig;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class HttpSendUtil {

    /**
     * Http送信 ユーティリティ
     */
    @Autowired
    private BackboneAuthConfig backboneAuthConfig;

    // コンストラクタ
    public HttpSendUtil(BackboneAuthConfig backboneAuthConfig) {
        this.backboneAuthConfig = backboneAuthConfig;
    }

    /**
     * backboneAuth-oa2-serverにHttpリクエストを行います
     * @param path    path
     * @param request リクエスト
     * @param clazz   httpのResponseのクラス
     * @return httpのResponse
     */
    public Object postBackboneAuthOa2(String path, Object request, Class<?> clazz) {
        return restTemplatePostForObject(createBackboneAuthOa2ServeUri(path), request, clazz);
    }

    /**
     * backboneAuth-oa3-serverにHttpリクエストを行います
     * @param path    path
     * @param request リクエスト
     * @param clazz   httpのResponseのクラス
     * @return httpのResponse
     */
    public Object postBackboneAuthOa3(String path, Object request, Class<?> clazz) {
        return restTemplatePostForObject(createBackboneAuthOa3ServeUri(path), request, clazz);
    }

    /**
     * backboneAuth-oa2-serve の Uriを作成します
     *
     * @param path path
     * @return backboneAuth-oa2-serve の Uri
     */
    private URI createBackboneAuthOa2ServeUri(String path) {
        return UriComponentsBuilder.newInstance()
            .scheme(backboneAuthConfig.getOa2scheme())
            .host(backboneAuthConfig.getOa2host())
            .port(backboneAuthConfig.getOa2port())
            .path(path).build().toUri();
    }

    /**
     * backboneAuth-oa3-serve の Uriを作成します
     *
     * @param path path
     * @return backboneAuth-oa3-serve の Uri
     */
    private URI createBackboneAuthOa3ServeUri(String path) {
        return UriComponentsBuilder.newInstance()
            .scheme(backboneAuthConfig.getOa3scheme())
            .host(backboneAuthConfig.getOa3host())
            .port(backboneAuthConfig.getOa3port())
            .path(path).build().toUri();
    }

//    /**
//     * Httpリクエストを行います
//     * @param uri     URI
//     * @param request リクエスト
//     * @param clazz   httpのResponseのクラス
//     * @return httpのResponse
//     */
//    private Object restTemplatePostForObject(URI uri, Object request, Class<?> clazz) {
//
//        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        return restTemplate.postForObject(uri, request, clazz);
//    }

    /**
     * Httpリクエストを行います
     * @param uri     URI
     * @param request リクエスト
     * @param clazz   httpのResponseのクラス
     * @return httpのResponse
     */
    private Object restTemplatePostForObject(URI uri, Object request, Class<?> clazz) {

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        String str = restTemplate.postForObject(uri, request, String.class);

        ObjectMapper mp = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            return mp.readValue(str, clazz);
        } catch (HttpClientErrorException e) {
            throw new GunmaRuntimeException("EOA10003");
        } catch (HttpServerErrorException | JsonProcessingException e) {
            throw new GunmaRuntimeException("EOA10003");
        }
    }
}
