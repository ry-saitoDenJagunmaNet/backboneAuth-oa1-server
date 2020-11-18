package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchBizTranRoleRequest;

/**
 * OA11010 オペレーター＜一覧＞検索サービス 取引ロール Request Converter
 */
public class Oa11010SearchBizTranRoleConverter implements OparatorSearchBizTranRoleRequest {

    private final Boolean bizTranRoleSelected;
    private final long bizTranRoleId;
    private final String bizTranRoleCode;
    private final String bizTranRoleName;
    private final String subSystemCode;
    private final Integer expirationSelect;
    private final LocalDate expirationStatusDate;
    private final LocalDate expirationStartDateFrom;
    private final LocalDate expirationStartDateTo;
    private final LocalDate expirationEndDateFrom;
    private final LocalDate expirationEndDateTo;

    // コンストラクタ
    Oa11010SearchBizTranRoleConverter(
        Boolean bizTranRoleSelected,
        long bizTranRoleId,
        String bizTranRoleCode,
        String bizTranRoleName,
        String subSystemCode,
        Integer expirationSelect,
        LocalDate expirationStatusDate,
        LocalDate expirationStartDateFrom,
        LocalDate expirationStartDateTo,
        LocalDate expirationEndDateFrom,
        LocalDate expirationEndDateTo) {

        this.bizTranRoleSelected = bizTranRoleSelected;
        this.bizTranRoleId = bizTranRoleId;
        this.bizTranRoleCode = bizTranRoleCode;
        this.bizTranRoleName = bizTranRoleName;
        this.subSystemCode = subSystemCode;
        this.expirationSelect = expirationSelect;
        this.expirationStatusDate = expirationStatusDate;
        this.expirationStartDateFrom = expirationStartDateFrom;
        this.expirationStartDateTo = expirationStartDateTo;
        this.expirationEndDateFrom = expirationEndDateFrom;
        this.expirationEndDateTo = expirationEndDateTo;
    }

    // ファクトリーメソッド
    public static Oa11010SearchBizTranRoleConverter with(
        Boolean bizTranRoleSelected,
        long bizTranRoleId,
        String bizTranRoleCode,
        String bizTranRoleName,
        String subSystemCode,
        Integer expirationSelect,
        LocalDate expirationStatusDate,
        LocalDate expirationStartDateFrom,
        LocalDate expirationStartDateTo,
        LocalDate expirationEndDateFrom,
        LocalDate expirationEndDateTo) {

        return new Oa11010SearchBizTranRoleConverter(
            bizTranRoleSelected,
            bizTranRoleId,
            bizTranRoleCode,
            bizTranRoleName,
            subSystemCode,
            expirationSelect,
            expirationStatusDate,
            expirationStartDateFrom,
            expirationStartDateTo,
            expirationEndDateFrom,
            expirationEndDateTo);
    }

    /**
     * 取引ロール選択のＧｅｔ
     *
     * @return 取引ロール選択
     */
    public Boolean getBizTranRoleSelected() {
        return bizTranRoleSelected;
    }
    /**
     * 取引ロールIDのＧｅｔ
     *
     * @return 取引ロールID
     */
    public long getBizTranRoleId() {
        return bizTranRoleId;
    }
    /**
     * 取引ロールコードのＧｅｔ
     *
     * @return 取引ロールコード
     */
    public String getBizTranRoleCode() {
        return bizTranRoleCode;
    }
    /**
     * 取引ロール名のＧｅｔ
     *
     * @return 取引ロール名
     */
    public String getBizTranRoleName() {
        return bizTranRoleName;
    }
    /**
     * サブシステムコードのＧｅｔ
     *
     * @return サブシステムコード
     */
    public String getSubSystemCode() {
        return subSystemCode;
    }
    /**
     * 有効期限選択のＧｅｔ
     *
     * @return 有効期限選択
     */
    public Integer getExpirationSelect() {
        return expirationSelect;
    }
    /**
     * 状態指定日のＧｅｔ
     *
     * @return 状態指定日
     */
    public LocalDate getExpirationStatusDate() {
        return expirationStatusDate;
    }
    /**
     * 条件指定日開始（開始日）のＧｅｔ
     *
     * @return 条件指定日開始（開始日）
     */
    public LocalDate getExpirationStartDateFrom() {
        return expirationStartDateFrom;
    }
    /**
     * 条件指定日開始（終了日）のＧｅｔ
     *
     * @return 条件指定日開始（終了日）
     */
    public LocalDate getExpirationStartDateTo() {
        return expirationStartDateTo;
    }
    /**
     * 条件指定日終了（開始日）のＧｅｔ
     *
     * @return 条件指定日終了（開始日）
     */
    public LocalDate getExpirationEndDateFrom() {
        return expirationEndDateFrom;
    }
    /**
     * 条件指定日終了（終了日）のＧｅｔ
     *
     * @return 条件指定日終了（終了日）
     */
    public LocalDate getExpirationEndDateTo() {
        return expirationEndDateTo;
    }
}
