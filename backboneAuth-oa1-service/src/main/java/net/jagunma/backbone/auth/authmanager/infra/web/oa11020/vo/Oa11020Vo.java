package net.jagunma.backbone.auth.authmanager.infra.web.oa11020.vo;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoReferenceDto;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfResponseVo;

/**
 * OA11020 View Object
 */
public class Oa11020Vo extends BaseOfResponseVo {

    private static final long serialVersionUID = 1L;

    /**
     * ＪＡ
     */
    private String ja;
    /**
     * ＪＡID
     */
    private long jaId;
    /**
     * 店舗
     */
    private String tempoCode;
    /**
     * 店舗コンボボックスリスト
     */
    private List<TempoReferenceDto> tempoList;
    /**
     * 識別（オペレーターコードプレフィックス）
     */
    private String operatorCodePrefix;
    /**
     * オペレーターコード（下6桁）
     */
    private String operatorCode6;
    /**
     * オペレーター名
     */
    private String operatorName;
    /**
     * メールアドレス
     */
    private String mailAddress;
    /**
     * 有効期限（開始日）
     */
    private LocalDate expirationStartDate;
    /**
     * 有効期限（終了日）
     */
    private LocalDate expirationEndDate;
    /**
     * 変更事由
     */
    private String changeCause;

    public String getJa() {
        return ja;
    }

    public void setJa(String ja) {
        this.ja = ja;
    }

    public long getJaId() {
        return jaId;
    }

    public void setJaId(long jaId) {
        this.jaId = jaId;
    }

    public String getTempoCode() {
        return tempoCode;
    }

    public void setTempoCode(String tempoCode) {
        this.tempoCode = tempoCode;
    }

    public List<TempoReferenceDto> getTempoList() {
        return tempoList;
    }

    public void setTempoList(List<TempoReferenceDto> tempoList) {
        this.tempoList = tempoList;
    }

    public String getOperatorCodePrefix() {
        return operatorCodePrefix;
    }

    public void setOperatorCodePrefix(String operatorCodePrefix) {
        this.operatorCodePrefix = operatorCodePrefix;
    }

    public String getOperatorCode6() {
        return operatorCode6;
    }

    public void setOperatorCode6(String operatorCode6) {
        this.operatorCode6 = operatorCode6;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public LocalDate getExpirationStartDate() {
        return expirationStartDate;
    }

    public void setExpirationStartDate(LocalDate expirationStartDate) {
        this.expirationStartDate = expirationStartDate;
    }

    public LocalDate getExpirationEndDate() {
        return expirationEndDate;
    }

    public void setExpirationEndDate(LocalDate expirationEndDate) {
        this.expirationEndDate = expirationEndDate;
    }

    public String getChangeCause() {
        return changeCause;
    }

    public void setChangeCause(String changeCause) {
        this.changeCause = changeCause;
    }
}
