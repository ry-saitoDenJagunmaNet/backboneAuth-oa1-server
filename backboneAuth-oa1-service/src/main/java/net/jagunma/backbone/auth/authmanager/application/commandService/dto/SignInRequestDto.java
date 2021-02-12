package net.jagunma.backbone.auth.authmanager.application.commandService.dto;

public class SignInRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * オペレーターコード
     */
    private String operatorCode;
    /**
     * パスワード
     */
    private String password;
    /**
     * クライアントIPアドレス
     */
    private String clientIpaddress;

    // コンストラクタ
    SignInRequestDto(
        String operatorCode,
        String password,
        String clientIpaddress) {

        this.operatorCode = operatorCode;
        this.password = password;
        this.clientIpaddress = clientIpaddress;
    }

    // ファクトリーメソッド
    public static SignInRequestDto with(
        String operatorCode,
        String password,
        String clientIpaddress) {

        return new SignInRequestDto(operatorCode, password, clientIpaddress);
    }

    // Getter／Setter
    public String getOperatorCode() {
        return operatorCode;
    }
    public String getPassword() {
        return password;
    }
    public String getClientIpaddress() {
        return clientIpaddress;
    }
}
