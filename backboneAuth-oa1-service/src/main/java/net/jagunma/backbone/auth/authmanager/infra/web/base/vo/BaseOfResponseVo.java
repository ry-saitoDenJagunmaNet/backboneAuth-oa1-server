package net.jagunma.backbone.auth.authmanager.infra.web.base.vo;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.util.message.MessageFormatter;
import org.springframework.dao.OptimisticLockingFailureException;

/**
 * レスポンスの基底クラス
 */
public class BaseOfResponseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * チェックボックスのチェックtrue状態値
     */
    public static final Short CHECKBOX_TRUE  = 1;
    /**
     * チェックボックスのチェックfalse状態値
     */
    public static final Short CHECKBOX_FALSE  = 0;

    /**
     * メッセージコード
     */
    private String messageCode;
    /**
     * メッセージコード
     */
    private List<String> messageArgs;
    /**
     * メッセージ
     */
    private String message;
    /**
     * エラーメッセージ
     */
    private String errorMessage;
    /**
     * エラー詳細メッセージ
     */
    private String errorDetailsMessage;
    /**
     * スタックトレース
     */
    private String stackTrace;

    public String getMessageCode() { return messageCode; }
    public void setMessageCode(String messageCode) { this.messageCode = messageCode; }
    public List<String> getMessageArgs() { return messageArgs; }
    public void setMessageArgs(List<String> messageArgs) { this.messageArgs = messageArgs; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    public String getErrorDetailsMessage() { return errorDetailsMessage; }
    public void setErrorDetailsMessage(String errorDetailsMessage) { this.errorDetailsMessage = errorDetailsMessage; }
    public String getStackTrace() { return stackTrace; }
    public void setStackTrace(String stackTrace) { this.stackTrace = stackTrace; }

    /**
     * 例外メッセージをセットします
     * @param gre GunmaRuntimeException
     */
    public void setExceptionMessage(GunmaRuntimeException gre) {
        messageCode = gre.getMessageCode();
        messageArgs = Arrays.stream(gre.getArgs()).map(o -> (String) o).collect(Collectors.toList());
        message = gre.getMessage();
    }

    /**
     * 楽観的ロックのメッセージをセットします
     * @param ole OptimisticLockingFailureException
     */
    public void setExceptionMessage(OptimisticLockingFailureException ole) {
        messageCode = "EOA10002";
        errorMessage = MessageFormatter.getSimpleMessage("EOA10002");

        if (ole.getMessage() != null) {errorDetailsMessage = ole.getMessage() + "\r\n";}

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ole.printStackTrace(pw);
        pw.flush();
        stackTrace = sw.toString();
    }

    /**
     * 例外メッセージをセットします
     * @param re IOException
     */
    public void setExceptionMessage(IOException re) {
        messageCode = "EOA10001";
        errorMessage = MessageFormatter.getSimpleMessage("EOA10001");

        if (re.getMessage() != null) {errorDetailsMessage = re.getMessage() + "\r\n";}

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        re.printStackTrace(pw);
        pw.flush();
        stackTrace = sw.toString();
    }

    /**
     * 例外メッセージをセットします
     * @param re RuntimeException
     */
    public void setExceptionMessage(RuntimeException re) {
        messageCode = "EOA10001";
        errorMessage = MessageFormatter.getSimpleMessage("EOA10001");

        if (re.getMessage() != null) {errorDetailsMessage = re.getMessage() + "\r\n";}

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        re.printStackTrace(pw);
        pw.flush();
        stackTrace = sw.toString();
    }
}
