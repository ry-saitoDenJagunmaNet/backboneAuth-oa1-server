package net.jagunma.backbone.auth.authmanager.infra.web.oa12030;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranDeleteRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranEntryRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranUpdateRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranReference.SuspendBizTranSearchRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12030.vo.Oa12030Vo;
import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * OA12030 一時取引抑止メンテナンス Request Converter
 */
public class Oa12030Converter implements SuspendBizTranSearchRequest,
    SuspendBizTranEntryRequest, SuspendBizTranUpdateRequest, SuspendBizTranDeleteRequest {

    /**
     * OA12030 View Object
     */
    private final Oa12030Vo vo;

    // コンストラクタ
    Oa12030Converter(Oa12030Vo oa12030Vo) {
        vo = oa12030Vo;
    }

    // ファクトリーメソッド
    public static Oa12030Converter with(Oa12030Vo oa12030Vo) {
        return new Oa12030Converter(oa12030Vo);
    }

    /**
     * 一時取引抑止ID検索条件のＧｅｔ
     *
     * @return 一時取引抑止ID検索条件
     */
    public LongCriteria getSuspendBizTranIdCriteria() {
        LongCriteria criteria = new LongCriteria();
        criteria.setEqualTo(vo.getSuspendBizTranId());
        return criteria;
    }

    /**
     * 一時取引抑止IDのＧｅｔ
     *
     * @return 一時取引抑止ID
     */
    public Long getSuspendBizTranId() {
        return vo.getSuspendBizTranId();
    }

    /**
     * JAコードのＧｅｔ
     *
     * @return JAコード
     */
    public String getJaCode() {
        return vo.getJaCode();
    }

    /**
     * 店舗コードのＧｅｔ
     *
     * @return 店舗コード
     */
    public String getBranchCode() {
        return vo.getBranchCode();
    }

    /**
     * サブシステムコードのＧｅｔ
     *
     * @return サブシステムコード
     */
    public String getSubSystemCode() {
        return vo.getSubSystemCode();
    }

    /**
     * 取引グループコードのＧｅｔ
     *
     * @return 取引グループコード
     */
    public String getBizTranGrpCode() {
        return vo.getBizTranGrpCode();
    }

    /**
     * 取引コードのＧｅｔ
     *
     * @return 取引コード
     */
    public String getBizTranCode() {
        return vo.getBizTranCode();
    }

    /**
     * 抑止期間開始日のＧｅｔ
     *
     * @return 抑止期間開始日
     */
    public LocalDate getSuspendStartDate() {
        return vo.getSuspendStartDate();
    }

    /**
     * 抑止期間終了日のＧｅｔ
     *
     * @return 抑止期間終了日
     */
    public LocalDate getSuspendEndDate() {
        return vo.getSuspendEndDate();
    }

    /**
     * 抑止理由のＧｅｔ
     *
     * @return 抑止理由
     */
    public String getSuspendReason() {
        return vo.getSuspendReason();
    }

    /**
     * レコードバージョンのＧｅｔ
     *
     * @return レコードバージョン
     */
    public Integer getRecordVersion() {
        return vo.getRecordVersion();
    }
}
