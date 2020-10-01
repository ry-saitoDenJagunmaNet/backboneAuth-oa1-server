package net.jagunma.backbone.auth.authmanager.infra.web.oa11020;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoReferenceDto;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11020.vo.Oa11020Vo;

/**
 * OA11020 初期表示 Presenter
 */
class Oa11020InitPresenter {

    private String jaCode;
    private String jaName;
    private String operatorCodePrefix;
    private List<TempoReferenceDto> tempoList;

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
     * 識別（オペレーターコードプレフィックス）のＳｅｔ
     *
     * @param operatorCodePrefix
     */
    public void setOperatorCodePrefix(String operatorCodePrefix) {
        this.operatorCodePrefix = operatorCodePrefix;
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
     * voに変換に変換します。
     *
     * @param vo
     */
    public void bindTo(Oa11020Vo vo) {
        vo.setJa(jaCode + " " + jaName);
        vo.setTempoId(null);
        vo.setOperatorCodePrefix(operatorCodePrefix);
        vo.setOperatorCode6(null);
        vo.setOperatorName(null);
        vo.setMailAddress(null);
        vo.setExpirationStartDate(null);
        vo.setExpirationEndDate(null);
        vo.setChangeCause(null);
        vo.setTempoList(tempoList);
        vo.setPassword(null);
        vo.setConfirmPassword(null);
    }
}
