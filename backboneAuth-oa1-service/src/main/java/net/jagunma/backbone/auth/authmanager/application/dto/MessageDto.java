package net.jagunma.backbone.auth.authmanager.application.dto;

import java.util.List;
import net.jagunma.common.util.message.MessageFormatter;

public class MessageDto {

    private final String messageCode;
    private final String message;
    private final List<String> messageArgs;

    // コンストラクタ
    MessageDto(
        String messageCode,
        String message,
        List<String> messageArgs) {

        this.messageCode = messageCode;
        this.message = message;
        this.messageArgs = messageArgs;
    }

    // ファクトリーメソッド
    public static MessageDto createFrom(
        String messageCode,
        List<String> messageArgs) {

        Object[] messageArgArray = messageArgs.toArray(new String[messageArgs.size()]);
        return new MessageDto(
            messageCode,
            MessageFormatter.getSimpleMessage(messageCode, messageArgArray),
            messageArgs);
    }
    public static MessageDto createFrom(String messageCode) {

        return new MessageDto(
            messageCode,
            MessageFormatter.getSimpleMessage(messageCode),
            null);
    }
    public static MessageDto createFrom(
        String messageCode,
        String message,
        List<String> messageArgs) {

        return new MessageDto(messageCode, message, messageArgs);
    }

    // Getter
    public String getMessageCode() {
        return messageCode;
    }
    public String getMessage() {
        return message;
    }
    public List<String> getMessageArgs() {
        return messageArgs;
    }
}
