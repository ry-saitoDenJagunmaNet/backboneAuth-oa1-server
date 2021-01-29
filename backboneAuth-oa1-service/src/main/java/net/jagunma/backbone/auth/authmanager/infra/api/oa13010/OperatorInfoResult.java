package net.jagunma.backbone.auth.authmanager.infra.api.oa13010;

/**
 * オペレーター情報 Result
 */
public class OperatorInfoResult {

    private final String centerJaCode = "990";

    OperatorInfoResult(
        Long jaId,
        String jaCode,
        String jaName,
        Long tempoId,
        String tempoCode,
        String tempoName,
        Long operatorId,
        String operatorCode,
        String operatorName) {

        this.jaId = jaId;
        this.jaCode = jaCode;
        this.jaName = jaName;
        this.tempoId = tempoId;
        this.tempoCode = tempoCode;
        this.tempoName = tempoName;
        this.operatorId = operatorId;
        this.operatorCode = operatorCode;
        this.operatorName = operatorName;
        this.centerOperatorFlag = centerJaCode.equals(jaCode)? 0 : 1;
    }
    public static OperatorInfoResult createFrom(
        Long jaId,
        String jaCode,
        String jaName,
        Long tempoId,
        String tempoCode,
        String tempoName,
        Long operatorId,
        String operatorCode,
        String operatorName) {

        return new OperatorInfoResult(
            jaId,
            jaCode,
            jaName,
            tempoId,
            tempoCode,
            tempoName,
            operatorId,
            operatorCode,
            operatorName);
    }


    private Long jaId;
    private String jaCode;
    private String jaName;
    private Long tempoId;
    private String tempoCode;
    private String tempoName;
    private Long operatorId;
    private String operatorCode;
    private String operatorName;
    private Integer centerOperatorFlag;

    /**
     * ＪＡIDのＧｅｔ
     *
     * @return ＪＡID
     */
    public Long getJaId() {
        return jaId;
    }
    /**
     * ＪＡIDのＳｅｔ
     *
     * @param jaId ＪＡID
     */
    public void setJaId(Long jaId) {
        this.jaId = jaId;
    }
    /**
     * ＪＡコードのＧｅｔ
     *
     * @return ＪＡコード
     */
    public String getJaCode() {
        return jaCode;
    }
    /**
     * ＪＡコードのＳｅｔ
     *
     * @param jaCode ＪＡコード
     */
    public void setJaCode(String jaCode) {
        this.jaCode = jaCode;
    }
    /**
     * ＪＡ名称のＧｅｔ
     *
     * @return ＪＡ名称
     */
    public String getJaName() {
        return jaName;
    }
    /**
     * ＪＡ名称のＳｅｔ
     *
     * @param jaName ＪＡ名称
     */
    public void setJaName(String jaName) {
        this.jaName = jaName;
    }
    /**
     * 店舗IDのＧｅｔ
     *
     * @return 店舗ID
     */
    public Long getTempoId() {
        return tempoId;
    }
    /**
     * 店舗IDのＳｅｔ
     *
     * @param tempoId 店舗ID
     */
    public void setTempoId(Long tempoId) {
        this.tempoId = tempoId;
    }
    /**
     * 店舗コードのＧｅｔ
     *
     * @return 店舗コード
     */
    public String getTempoCode() {
        return tempoCode;
    }
    /**
     * 店舗コードのＳｅｔ
     *
     * @param tempoCode 店舗コード
     */
    public void setTempoCode(String tempoCode) {
        this.tempoCode = tempoCode;
    }
    /**
     * 店舗名称のＧｅｔ
     *
     * @return 店舗名称
     */
    public String getTempoName() {
        return tempoName;
    }
    /**
     * 店舗名称のＳｅｔ
     *
     * @param tempoName 店舗名称
     */
    public void setTempoName(String tempoName) {
        this.tempoName = tempoName;
    }
    /**
     * オペレータIDのＧｅｔ
     *
     * @return オペレータID
     */
    public Long getOperatorId() {
        return operatorId;
    }
    /**
     * オペレータIDのＳｅｔ
     *
     * @param operatorId オペレータID
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
    /**
     * オペレータコードのＧｅｔ
     *
     * @return オペレータコード
     */
    public String getOperatorCode() {
        return operatorCode;
    }
    /**
     * オペレータコードのＳｅｔ
     *
     * @param operatorCode オペレータコード
     */
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }
    /**
     * オペレータ名称のＧｅｔ
     *
     * @return オペレータ名称
     */
    public String getOperatorName() {
        return operatorName;
    }
    /**
     * オペレータ名称のＳｅｔ
     *
     * @param operatorName オペレータ名称
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    /**
     * センターオペレータフラグのＧｅｔ
     *
     * @return センターオペレータフラグ
     */
    public Integer getCenterOperatorFlag() {
        return centerOperatorFlag;
    }
    /**
     * センターオペレータフラグのＳｅｔ
     *
     * @param centerOperatorFlag センターオペレータフラグ
     */
    public void setCenterOperatorFlag(Integer centerOperatorFlag) {
        this.centerOperatorFlag = centerOperatorFlag;
    }
}