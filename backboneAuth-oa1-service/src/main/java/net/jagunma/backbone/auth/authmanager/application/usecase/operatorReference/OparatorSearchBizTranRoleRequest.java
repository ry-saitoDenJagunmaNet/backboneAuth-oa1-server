package net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference;

import java.time.LocalDate;

public interface OparatorSearchBizTranRoleRequest {

    /**
     * 取引ロール選択のＧｅｔ
     *
     * @return 取引ロール選択
     */
    Boolean getBizTranRoleSelected();
    /**
     * 取引ロールIDのＧｅｔ
     *
     * @return 取引ロールID
     */
    long getBizTranRoleId();
    /**
     * 取引ロールコードのＧｅｔ
     *
     * @return 取引ロールコード
     */
    String getBizTranRoleCode();
    /**
     * 取引ロール名のＧｅｔ
     *
     * @return 取引ロール名
     */
    String getBizTranRoleName();
    /**
     * サブシステムコードのＧｅｔ
     *
     * @return サブシステムコード
     */
    String getSubSystemCode();
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
