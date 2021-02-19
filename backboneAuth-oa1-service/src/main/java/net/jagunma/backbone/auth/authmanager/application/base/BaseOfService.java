package net.jagunma.backbone.auth.authmanager.application.base;

import java.net.URI;
import net.jagunma.backbone.auth.authmanager.application.BackboneAuthConfig;
import org.springframework.web.util.UriComponentsBuilder;

public class BaseOfService {

    private BackboneAuthConfig backboneAuthConfig;

    // コンストラクタ
    public BaseOfService() {}
    public BaseOfService(BackboneAuthConfig backboneAuthConfig) {
        this.backboneAuthConfig = backboneAuthConfig;
    }

    /**
     * backboneAuth-oa2-serve の Uriを作成します
     *
     * @param path path
     * @return backboneAuth-oa2-serve の Uri
     */
    protected URI createBackboneAuthOa2ServeUri(String path) {
        return UriComponentsBuilder.newInstance()
            .scheme(backboneAuthConfig.getOa3scheme())
            .host(backboneAuthConfig.getOa3host())
            .port(backboneAuthConfig.getOa3port())
            .path(path).build().toUri();
    }

    /**
     * backboneAuth-oa3-serve の Uriを作成します
     *
     * @param path path
     * @return backboneAuth-oa3-serve の Uri
     */
    protected URI createBackboneAuthOa3ServeUri(String path) {
        return UriComponentsBuilder.newInstance()
            .scheme(backboneAuthConfig.getOa3scheme())
            .host(backboneAuthConfig.getOa3host())
            .port(backboneAuthConfig.getOa3port())
            .path(path).build().toUri();
    }
}
