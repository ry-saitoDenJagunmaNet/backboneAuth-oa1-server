package net.jagunma.backbone.auth.authmanager.infra.web.oa12030;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranDeleteRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranEntryRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranUpdateRequest;
import net.jagunma.backbone.auth.authmanager.infra.util.CheckboxUtil;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12030.vo.Oa12030Vo;

/**
 * OA12030 一時取引抑止メンテナンス 更新系 Request Converter
 */
public class Oa12030CommandConverter implements SuspendBizTranEntryRequest, SuspendBizTranUpdateRequest, SuspendBizTranDeleteRequest {

    /**
     * OA12030 View Object
     */
    private final Oa12030Vo vo;

    // コンストラクタ
    Oa12030CommandConverter(Oa12030Vo oa12030Vo) {
        vo = oa12030Vo;
    }

    // ファクトリーメソッド
    public static Oa12030CommandConverter with(Oa12030Vo oa12030Vo) {
        return new Oa12030CommandConverter(oa12030Vo);
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
     * ＪＡコードのＧｅｔ
     *
     * @return ＪＡコード
     */
    public String getJaCode() {
        if (CheckboxUtil.getSmoother(vo.getJaCheck())) {
            return vo.getJaCode();
        }
        return null;
    }

    /**
     * 店舗コードのＧｅｔ
     *
     * @return 店舗コード
     */
    public String getBranchCode() {
        if (CheckboxUtil.getSmoother(vo.getBranchCheck())) {
            return vo.getBranchCode();
        }
        return null;
    }

    /**
     * サブシステムコードのＧｅｔ
     *
     * @return サブシステムコード
     */
    public String getSubSystemCode() {
        if (CheckboxUtil.getSmoother(vo.getSubSystemCheck())) {
            return vo.getSubSystemCode();
        }
        return null;
    }

    /**
     * 取引グループコードのＧｅｔ
     *
     * @return 取引グループコード
     */
    public String getBizTranGrpCode() {
        if (CheckboxUtil.getSmoother(vo.getBizTranGrpCheck())) {
            return vo.getBizTranGrpCode();
        }
        return null;
    }

    /**
     * 取引コードのＧｅｔ
     *
     * @return 取引コード
     */
    public String getBizTranCode() {
        if (CheckboxUtil.getSmoother(vo.getBizTranCheck())) {
            return vo.getBizTranCode();
        }
        return null;
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