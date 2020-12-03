package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchSubSystemRoleRequest;

/**
 * OA11010 オペレーター＜一覧＞検索サービス サブシステムロール Request Converter
 */
public class Oa11010SearchSubSystemRoleConverter implements OparatorSearchSubSystemRoleRequest {

    private final Boolean subSystemRoleSelected;
    private final String subSystemRoleCode;
    private final String subSystemRoleName;
    private final Integer validThruSelect;
    private final LocalDate validThruStatusDate;
    private final LocalDate validThruStartDateFrom;
    private final LocalDate validThruStartDateTo;
    private final LocalDate validThruEndDateFrom;
    private final LocalDate validThruEndDateTo;

    // コンストラクタ
    Oa11010SearchSubSystemRoleConverter(
        Boolean subSystemRoleSelected,
        String subSystemRoleCode,
        String subSystemRoleName,
        Integer validThruSelect,
        LocalDate validThruStatusDate,
        LocalDate validThruStartDateFrom,
        LocalDate validThruStartDateTo,
        LocalDate validThruEndDateFrom,
        LocalDate validThruEndDateTo) {

        this.subSystemRoleSelected = subSystemRoleSelected;
        this.subSystemRoleCode = subSystemRoleCode;
        this.subSystemRoleName = subSystemRoleName;
        this.validThruSelect = validThruSelect;
        this.validThruStatusDate = validThruStatusDate;
        this.validThruStartDateFrom = validThruStartDateFrom;
        this.validThruStartDateTo = validThruStartDateTo;
        this.validThruEndDateFrom = validThruEndDateFrom;
        this.validThruEndDateTo = validThruEndDateTo;
    }

    // ファクトリーメソッド
    public static Oa11010SearchSubSystemRoleConverter with(
        Boolean subSystemRoleSelected,
        String subSystemRoleCode,
        String subSystemRoleName,
        Integer validThruSelect,
        LocalDate validThruStatusDate,
        LocalDate validThruStartDateFrom,
        LocalDate validThruStartDateTo,
        LocalDate validThruEndDateFrom,
        LocalDate validThruEndDateTo) {

        return new Oa11010SearchSubSystemRoleConverter(
            subSystemRoleSelected,
            subSystemRoleCode,
            subSystemRoleName,
            validThruSelect,
            validThruStatusDate,
            validThruStartDateFrom,
            validThruStartDateTo,
            validThruEndDateFrom,
            validThruEndDateTo);
    }

    /**
     * サブシステムロール選択のＧｅｔ
     *
     * @return サブシステムロール選択
     */
    public Boolean getSubSystemRoleSelected() {
        return subSystemRoleSelected;
    }
    /**
     * サブシステムロールコードのＧｅｔ
     *
     * @return サブシステムロールコード
     */
    public String getSubSystemRoleCode() {
        return subSystemRoleCode;
    }
    /**
     * サブシステムロール名のＧｅｔ
     *
     * @return サブシステムロール名
     */
    public String getSubSystemRoleName() {
        return subSystemRoleName;
    }
    /**
     * 有効期限選択のＧｅｔ
     *
     * @return 有効期限選択
     */
    public Integer getValidThruSelect() {
        return validThruSelect;
    }
    /**
     * 状態指定日のＧｅｔ
     *
     * @return 状態指定日
     */
    public LocalDate getValidThruStatusDate() {
        return validThruStatusDate;
    }
    /**
     * 条件指定日開始（開始日）のＧｅｔ
     *
     * @return 条件指定日開始（開始日）
     */
    public LocalDate getValidThruStartDateFrom() {
        return validThruStartDateFrom;
    }
    /**
     * 条件指定日開始（終了日）のＧｅｔ
     *
     * @return 条件指定日開始（終了日）
     */
    public LocalDate getValidThruStartDateTo() {
        return validThruStartDateTo;
    }
    /**
     * 条件指定日終了（開始日）のＧｅｔ
     *
     * @return 条件指定日終了（開始日）
     */
    public LocalDate getValidThruEndDateFrom() {
        return validThruEndDateFrom;
    }
    /**
     * 条件指定日終了（終了日）のＧｅｔ
     *
     * @return 条件指定日終了（終了日）
     */
    public LocalDate getValidThruEndDateTo() {
        return validThruEndDateTo;
    }
}
