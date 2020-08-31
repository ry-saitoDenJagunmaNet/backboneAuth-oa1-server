package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.base.vo;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.util.message.MessageFormatter;

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
	 * メッセージ
	 */
	private String message;

	public String getMessage() { return message; }
	public void setMessage(String message) { this.message = message; }

	/**
	 * 例外メッセージをセットします。
	 * @param gre GunmaRuntimeException
	 */
	public void setExceptionMessage(GunmaRuntimeException gre) {
		message = gre.getMessage();
	}

	/**
	 * 例外メッセージをセットします。
	 * @param re RuntimeException
	 */
	public void setExceptionMessage(RuntimeException re) {
		message = MessageFormatter.getSimpleMessage("EOA10001");
		message += "\r\n";
		if (re.getMessage() != null) {message += re.getMessage() + "\r\n";}

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		re.printStackTrace(pw);
		pw.flush();
		message += sw.toString();
	}
}
