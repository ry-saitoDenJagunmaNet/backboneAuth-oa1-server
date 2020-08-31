package net.jagunma.backbone.auth.authmanager.application.service.oa11020;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoReferenceDto;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11020.vo.Oa11020Vo;

/**
 * OA11020 オペレーター登録 初期表示サービス Presenter
 */
class Oa11020InitPresenter {

    private String jaCode;
    private String jaName;
    private long jaId;
    private List<TempoReferenceDto> tempoList;
    private String operatorCodePrefix;

//	private String tempoCode;
//	private String operatorCode;
//	private String operatorName;
//	private String mailAddress;
//	private LocalDate expirationStartDate;
//	private LocalDate expirationEndDate;
//	private String changeCause;

    // コンストラクタ
    Oa11020InitPresenter() {
    }

    /**
     * ＪＡコードのＳｅｔ
     *
     * @param jaCode
     */
    public void setJaCode(String jaCode) {
        this.jaCode = jaCode;
    }

    /**
     * ＪＡ名のＳｅｔ
     *
     * @param jaName
     */
    public void setJaName(String jaName) {
        this.jaName = jaName;
    }

    /**
     * ＪＡIDのＳｅｔ
     *
     * @param jaId
     */
    public void setJaId(long jaId) {
        this.jaId = jaId;
    }

    /**
     * 店舗コンボボックスリストのＳｅｔ
     *
     * @param tempoList
     */
    public void setTempoList(List<TempoReferenceDto> tempoList) {
        this.tempoList = tempoList;
    }

    /**
     * 識別（オペレーターコードプレフィックス）のＳｅｔ
     *
     * @param operatorCodePrefix
     */
    public void setOperatorCodePrefix(String operatorCodePrefix) {
        this.operatorCodePrefix = operatorCodePrefix;
    }

//	/**
//	 * 店舗コードのＳｅｔ
//	 * @param tempoCode
//	 */
//	public void setTempoCode(String tempoCode) { this.tempoCode = tempoCode; }
//	/**
//	 * オペレーターコード（下6桁）のＳｅｔ
//	 * @param operatorCode
//	 */
//	public void setOperatorCode(String operatorCode) { this.operatorCode = operatorCode; }
//	/**
//	 * オペレーター名のＳｅｔ
//	 * @param operatorName
//	 */
//	public void setOperatorName(String operatorName) { this.operatorName = operatorName; }
//	/**
//	 * メールアドレスのＳｅｔ
//	 * @param mailAddress
//	 */
//	public void setMailAddress(String mailAddress) { this.mailAddress = mailAddress; }
//	/**
//	 * 有効期限（開始日）のＳｅｔ
//	 * @param expirationStartDate
//	 */
//	public void setExpirationStartDate(LocalDate expirationStartDate) { this.expirationStartDate = expirationStartDate; }
//	/**
//	 * 有効期限（終了日）のＳｅｔ
//	 * @param expirationEndDate
//	 */
//	public void setExpirationEndDate(LocalDate expirationEndDate) { this.expirationEndDate = expirationEndDate; }
//	/**
//	 * 変更事由のＳｅｔ
//	 * @param changeCause
//	 */
//	public void setChangeCause(String changeCause) { this.changeCause = changeCause; }

    /**
     * voに変換に変換します。
     *
     * @param vo
     */
    public void bindTo(Oa11020Vo vo) {
        vo.setJa(jaCode + " " + jaName);
        vo.setJaId(jaId);

        vo.setTempoList(tempoList);
        vo.setOperatorCodePrefix(operatorCodePrefix);

//		vo.setTempoCode(tempoCode);
//		vo.setOperatorCode(operatorCode);
//		vo.setOperatorName(operatorName);
//		vo.setMailAddress(mailAddress);
//		vo.setExpirationStartDate(expirationStartDate);
//		vo.setExpirationEndDate(expirationEndDate);
//		vo.setChangeCause(changeCause);
    }
}
