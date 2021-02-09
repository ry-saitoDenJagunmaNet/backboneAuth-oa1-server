package net.jagunma.backbone.auth.authmanager.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 基幹系認証 Config
 */
@Component
public class BackboneAuthConfig {

    @Value("${backbone.authOa3.scheme}") // yamlのキーを設定します。
    private String oa3scheme;
    @Value("${backbone.authOa3.host}") // yamlのキーを設定します。
    private String oa3host;
    @Value("${backbone.authOa3.port}") // yamlのキーを設定します。
    private int oa3port;

    // Getter／Setter

    public String getOa3scheme() {
        return oa3scheme;
    }
    public void setOa3Scheme(String oa3scheme) {
        this.oa3scheme = oa3scheme;
    }
    public String getOa3host() {
        return oa3host;
    }
    public void setOa3host(String oa3host) {
        this.oa3host = oa3host;
    }
    public int getOa3port() {
        return oa3port;
    }
    public void setOa3port(int oa3port) {
        this.oa3port = oa3port;
    }
}
