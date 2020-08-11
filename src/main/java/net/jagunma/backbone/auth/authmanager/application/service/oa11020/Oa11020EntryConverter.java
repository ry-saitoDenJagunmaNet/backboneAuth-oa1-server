package net.jagunma.backbone.auth.authmanager.application.service.oa11020;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11020.vo.Oa11020Vo;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;

/**
 * OA11020 オペレーター登録 登録サービス Converter
 */
class Oa11020EntryConverter implements OperatorEntryRequest {

	/**
	 * OA11020 View Object
	 */
	private final Oa11020Vo vo;

	/**
	 * コンストラクタ
	 */
	Oa11020EntryConverter(Oa11020Vo oa11020Vo)  {
		vo = oa11020Vo;
	}

	public static Oa11020EntryConverter with(Oa11020Vo oa11020Vo) {
		return new Oa11020EntryConverter(oa11020Vo);
	}

	/**
	 * 識別（オペレーターコードプレフィックス）のＧｅｔ
	 * @return 識別（オペレーターコードプレフィックス）
	 */
	public String getOperatorCodePrefix() { return vo.getOperatorCodePrefix(); }
	/**
	 * オペレーターコード（下6桁）のＧｅｔ
	 * @return オペレーターコード（下6桁）
	 */
	public String getOperatorCode6() { return vo.getOperatorCode6(); }
	/**
	 * オペレーターコードのＧｅｔ
	 * @return オペレーターコード
	 */
	public String getOperatorCode() { return vo.getOperatorCodePrefix() + vo.getOperatorCode6(); }
	/**
	 * オペレーター名のＧｅｔ
	 * @return オペレーター名
	 */
	public String getOperatorName() { return vo.getOperatorName(); }
	/**
	 * メールアドレスのＧｅｔ
	 * @return メールアドレス
	 */
	public String getMailAddress() { return vo.getMailAddress(); }
	/**
	 * 有効期限開始日のＧｅｔ
	 * @return 有効期限開始日
	 */
	public LocalDate getExpirationStartDate() { return vo.getExpirationStartDate(); }
	/**
	 * 有効期限終了日のＧｅｔ
	 * @return 有効期限終了日
	 */
	public LocalDate getExpirationEndDate() { return vo.getExpirationEndDate(); }
//	/**
//	 * 機器認証のＧｅｔ
//	 * @return 機器認証
//	 */
//	public boolean getIsDeviceAuth() { return arg.get.getIsDeviceAuth(); }
//	/**
//	 * ＪＡIDのＧｅｔ
//	 * @return ＪＡID
//	 */
//	public long getJaId() {return arg.getJaId(); }
//	/**
//	 * JAコードのＧｅｔ
//	 * @return ＪＡコード
//	 */
//	public String getJaCode() {return arg.getjaCode(); }
//	/**
//	 * 店舗IDのＧｅｔ
//	 * @return 店舗ID
//	 */
//	public long getTempoId() {return arg.getTempoId(); }
	/**
	 * 店舗コードのＧｅｔ
	 * @return 店舗コード
	 */
	public String getTempoCode() {return  vo.getTempoCode(); }
//	/**
//	 * パスワードのＧｅｔ
//	 * @return パスワード
//	 */
//	public String getPassword() {return arg.getPassword(); }

	/**
	 * オペレータの検索条件生成
	 * @return オペレータの検索条件
	 */
	public OperatorEntityCriteria genOperatorEntityCriteria(OperatorEntryRequest request) {
		OperatorEntityCriteria criteria = new OperatorEntityCriteria();

		// オペレーターコード
		if (!isNullOrEmpty(vo.getOperatorCode6())) {
			criteria.getOperatorCodeCriteria().setEqualTo(vo.getOperatorCode6());
		}

		return criteria;
	}

	private boolean isNullOrEmpty(String str) {
		// strがnullもしくは空文字であればtrueを返す
		return (str == null || str.length() == 0);
	}
	private Integer nvl(Integer val, Integer changeVal) {
		if (val == null) {return changeVal;}
		return val;
	}
}
