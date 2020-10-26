package net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo;

import java.util.List;

/**
 * OA12010 メッセージ
 */
public class Oa12010MessageVo {

    private static final long serialVersionUID = 1L;

    /**
     * メッセージコード
     */
    public String messageCode;
    /**
     * メッセージ
     */
    public String message;
    /**
     * メッセージ引数
     */
    public List<String> messageArgs;

    // Getter／Setter
    public String getMessageCode() {
        return messageCode;
    }
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<String> getMessageArgs() {
        return messageArgs;
    }
    public void setMessageArgs(List<String> messageArgs) {
        this.messageArgs = messageArgs;
    }
}
