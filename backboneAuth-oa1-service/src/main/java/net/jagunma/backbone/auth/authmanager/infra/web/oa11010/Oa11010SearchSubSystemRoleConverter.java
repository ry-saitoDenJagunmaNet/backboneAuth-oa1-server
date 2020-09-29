package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchSubSystemRoleRequest;

/**
 * OA11010 オペレーター＜一覧＞検索サービス サブシステムロール Request Converter
 */
public class Oa11010SearchSubSystemRoleConverter implements OparatorSearchSubSystemRoleRequest {

    private final Short subSystemRoleSelected;
    private final String subSystemRoleCode;
    private final String subSystemRoleName;
    private final Integer expirationSelect;
    private final LocalDate expirationStatusDate;
    private final LocalDate expirationStartDateFrom;
    private final LocalDate expirationStartDateTo;
    private final LocalDate expirationEndDateFrom;
    private final LocalDate expirationEndDateTo;

    // コンストラクタ
    Oa11010SearchSubSystemRoleConverter(
        Short subSystemRoleSelected,
        String subSystemRoleCode,
        String subSystemRoleName,
        Integer expirationSelect,
        LocalDate expirationStatusDate,
        LocalDate expirationStartDateFrom,
        LocalDate expirationStartDateTo,
        LocalDate expirationEndDateFrom,
        LocalDate expirationEndDateTo) {

        this.subSystemRoleSelected = subSystemRoleSelected;
        this.subSystemRoleCode = subSystemRoleCode;
        this.subSystemRoleName = subSystemRoleName;
        this.expirationSelect = expirationSelect;
        this.expirationStatusDate = expirationStatusDate;
        this.expirationStartDateFrom = expirationStartDateFrom;
        this.expirationStartDateTo = expirationStartDateTo;
        this.expirationEndDateFrom = expirationEndDateFrom;
        this.expirationEndDateTo = expirationEndDateTo;
    }

    // ファクトリーメソッド
    public static Oa11010SearchSubSystemRoleConverter with(
        Short subSystemRoleSelected,
        String subSystemRoleCode,
        String subSystemRoleName,
        Integer expirationSelect,
        LocalDate expirationStatusDate,
        LocalDate expirationStartDateFrom,
        LocalDate expirationStartDateTo,
        LocalDate expirationEndDateFrom,
        LocalDate expirationEndDateTo) {

        return new Oa11010SearchSubSystemRoleConverter(
            subSystemRoleSelected,
            subSystemRoleCode,
            subSystemRoleName,
            expirationSelect,
            expirationStatusDate,
            expirationStartDateFrom,
            expirationStartDateTo,
            expirationEndDateFrom,
            expirationEndDateTo);
    }

    /**
     * サブシステムロール選択のＧｅｔ
     *
     * @return サブシステムロール選択
     */
    public Short getSubSystemRoleSelected() {
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
