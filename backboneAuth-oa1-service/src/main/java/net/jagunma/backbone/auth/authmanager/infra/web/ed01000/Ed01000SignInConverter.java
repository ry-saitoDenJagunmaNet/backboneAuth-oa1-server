package net.jagunma.backbone.auth.authmanager.infra.web.ed01000;

import net.jagunma.backbone.auth.authmanager.application.usecase.signInCommand.SignInRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01000.vo.Ed01000Vo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ed01000 サインイン Converter
 */
public class Ed01000SignInConverter implements SignInRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ed01000SignInConverter.class);

    private final Ed01000Vo vo;
    private final String clientIpaddress;

    // コンストラクタ
    Ed01000SignInConverter(
        Ed01000Vo vo,
        String clientIpaddress) {

        this.vo = vo;
        this.clientIpaddress = clientIpaddress;
    }

    // ファクトリーメソッド
    public static Ed01000SignInConverter with(
        Ed01000Vo vo,
        String clientIpaddress) {

        return new Ed01000SignInConverter(vo, clientIpaddress);
    }

    // Getter
    /**
     * オペレーターコードのＧｅｔ
     *
     * @return オペレーターコード
     */
    public String getOperatorCode() {
        return vo.getOperatorCode();
    }
    /**
     * パスワードのＧｅｔ
     *
     * @return パスワード
     */
    public String getPassword() {
        return vo.getPassword();
    }
    /**
     * 画面表示モードのＧｅｔ
     * （1:初期認証、2:継続再認証）
     *
     * @return 画面表示モード
     */
    public Integer getMode() {
        return vo.getMode();
    }
    /**
     * クライアントIPアドレスのＧｅｔ
     *
     * @return クライアントIPアドレス
     */
    public String getClientIpaddress() {
        return clientIpaddress;
    }
}
