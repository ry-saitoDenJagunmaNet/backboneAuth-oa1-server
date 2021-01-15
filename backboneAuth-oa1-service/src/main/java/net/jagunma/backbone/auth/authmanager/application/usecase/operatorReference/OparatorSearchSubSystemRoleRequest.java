package net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference;

import java.time.LocalDate;

/**
 * オペレーター検索サービスサブシステムロール Request
 */
public interface OparatorSearchSubSystemRoleRequest {
    /**
     * サブシステムロール選択のＧｅｔ
     *
     * @return サブシステムロール選択
     */
    Boolean getSubSystemRoleSelected();
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
    Integer getValidThruSelect();
    /**
     * 状態指定日のＧｅｔ
     *
     * @return 状態指定日
     */
    LocalDate getValidThruStatusDate();
    /**
     * 条件指定日開始（開始日）のＧｅｔ
     *
     * @return 条件指定日開始（開始日）
     */
    LocalDate getValidThruStartDateFrom();
    /**
     * 条件指定日開始（終了日）のＧｅｔ
     *
     * @return 条件指定日開始（終了日）
     */
    LocalDate getValidThruStartDateTo();
    /**
     * 条件指定日終了（開始日）のＧｅｔ
     *
     * @return 条件指定日終了（開始日）
     */
    LocalDate getValidThruEndDateFrom();
    /**
     * 条件指定日終了（終了日）のＧｅｔ
     *
     * @return 条件指定日終了（終了日）
     */
    LocalDate getValidThruEndDateTo();

}
