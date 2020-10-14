package net.jagunma.backbone.auth.authmanager.application.usecase.operatorReferenceXXX;

import java.time.LocalDate;

public interface OparatorSearchSubSystemRoleRequest {
    /**
     * サブシステムロール選択のＧｅｔ
     *
     * @return サブシステムロール選択
     */
    Short getSubSystemRoleSelected();
    /**
     * サブシステムロールコードのＧｅｔ
     *
     * @return サブシステムロールコード
     */
    String getSubSystemRoleCode();
    /**
     * サブシステムロール名のＧｅｔ
     *
     * @return サブシステムロール名
     */
    String getSubSystemRoleName();
    /**
     * 有効期限選択のＧｅｔ
     *
     * @return 有効期限選択
     */
    Integer getExpirationSelect();
    /**
     * 状態指定日のＧｅｔ
     *
     * @return 状態指定日
     */
    LocalDate getExpirationStatusDate();
    /**
     * 条件指定日開始（開始日）のＧｅｔ
     *
     * @return 条件指定日開始（開始日）
     */
    LocalDate getExpirationStartDateFrom();
    /**
     * 条件指定日開始（終了日）のＧｅｔ
     *
     * @return 条件指定日開始（終了日）
     */
    LocalDate getExpirationStartDateTo();
    /**
     * 条件指定日終了（開始日）のＧｅｔ
     *
     * @return 条件指定日終了（開始日）
     */
    LocalDate getExpirationEndDateFrom();
    /**
     * 条件指定日終了（終了日）のＧｅｔ
     *
     * @return 条件指定日終了（終了日）
     */
    LocalDate getExpirationEndDateTo();

}
