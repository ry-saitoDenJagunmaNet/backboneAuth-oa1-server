package net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;

/**
 * オペレーター登録サービス Request
 */
public interface OperatorEntryRequest {
	/**
	 * 識別（オペレーターコードプレフィックス）のＧｅｔ
	 * @return 識別（オペレーターコードプレフィックス）
	 */
	String getOperatorCodePrefix();
	/**
	 * オペレーターコード（下6桁）のＧｅｔ
	 * @return オペレーターコード（下6桁）
	 */
	String getOperatorCode6();
	/**
	 * オペレーターコードのＧｅｔ
	 * @return オペレーターコード
	 */
	String getOperatorCode();
	/**
	 * オペレーター名のＧｅｔ
	 * @return オペレーター名
	 */
	String getOperatorName();
	/**
	 * メールアドレスのＧｅｔ
	 * @return メールアドレス
	 */
	String getMailAddress();
	/**
	 * 有効期限開始日のＧｅｔ
	 * @return 有効期限開始日
	 */
	LocalDate getExpirationStartDate();
	/**
	 * 有効期限終了日のＧｅｔ
	 * @return 有効期限終了日
	 */
	LocalDate getExpirationEndDate();
//	/**
//	 * 機器認証のＧｅｔ
//	 * @return 機器認証
//	 */
//	boolean getIsDeviceAuth();
//	/**
//	 * ＪＡIDのＧｅｔ
//	 * @return ＪＡID
//	 */
//	long getJaId();
//	/**
//	 * JAコードのＧｅｔ
//	 * @return ＪＡコード
//	 */
//	String getJaCode();
	/**
	 * 店舗IDのＧｅｔ
	 * @return 店舗ID
	 */
	Long getTempoId();
//	/**
//	 * 店舗コードのＧｅｔ
//	 * @return 店舗コード
//	 */
//	String getTempoCode();
	/**
	 * 変更事由のＧｅｔ
	 * @return 変更事由
	 */
	String getChangeCause();
	/**
	 * パスワードのＧｅｔ
	 * @return パスワード
	 */
	String getPassword();
}
