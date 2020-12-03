package net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand;

import java.time.LocalDate;

/**
 * オペレーター登録サービス Request
 */
public interface OperatorEntryRequest {
    /**
     * オペレーターコード（下6桁）のＧｅｔ
     *
     * @return オペレーターコード（下6桁）
     */
    String getOperatorCode6();
    /**
     * オペレーター名のＧｅｔ
     *
     * @return オペレーター名
     */
    String getOperatorName();
    /**
     * メールアドレスのＧｅｔ
     *
     * @return メールアドレス
     */
    String getMailAddress();
    /**
     * 有効期限開始日のＧｅｔ
     *
     * @return 有効期限開始日
     */
    LocalDate getValidThruStartDate();
    /**
     * 有効期限終了日のＧｅｔ
     *
     * @return 有効期限終了日
     */
    LocalDate getValidThruEndDate();
    /**
     * 店舗IDのＧｅｔ
     *
     * @return 店舗ID
     */
    Long getBranchId();
    /**
     * 変更事由のＧｅｔ
     *
     * @return 変更事由
     */
    String getChangeCause();
    /**
     * パスワードのＧｅｔ
     *
     * @return パスワード
     */
    String getPassword();
    /**
     * パスワードの確認入力のＧｅｔ
     *
     * @return パスワードの確認入力
     */
    String getConfirmPassword();
}
