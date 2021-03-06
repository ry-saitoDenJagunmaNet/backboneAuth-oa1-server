package net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference;

import java.time.LocalDate;

/**
 * オペレーター検索サービス取引ロール Request
 */
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
