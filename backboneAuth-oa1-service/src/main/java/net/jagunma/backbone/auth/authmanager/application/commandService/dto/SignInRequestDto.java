package net.jagunma.backbone.auth.authmanager.application.commandService.dto;

public class SignInRequestDto {

    private static final long serialVersionUID = 1L;

    // コンストラクタ
    SignInRequestDto(
        String operatorCode,
        String password) {

        this.operatorCode = operatorCode;
        this.password = password;
    }

    // ファクトリーメソッド
    public static SignInRequestDto with(
        String operatorCode,
        String password) {

        return new SignInRequestDto(operatorCode, password);
    }

    /**
     * オペレーターCode
     */
    private String operatorCode;
    /**
     * パスワード
     */
    private String password;

    // Getter／Setter
    public String getOperatorCode() {
        return operatorCode;
    }
    public String getPassword() {
        return password;
    }
}
