package net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;

/**
 * オペレーター更新サービス Request
 */
public interface OperatorUpdateRequest {
    /**
     * オペレーターIDのＧｅｔ
     *
     * @return オペレーターID
     */
    Long getOperatorId();
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
     * 機器認証のＧｅｔ
     *
     * @return 機器認証
     */
    Boolean getIsDeviceAuth();
    /**
     * 店舗IDのＧｅｔ
     *
     * @return 店舗ID
     */
    Long getBranchId();
    /**
     * 利用可否状態のＧｅｔ
     *
     * @return 利用可否状態
     */
    AvailableStatus getAvailableStatus();
    /**
     * レコードバージョンのＧｅｔ
     *
     * @return レコードバージョン
     */
    Integer getRecordVersion();
    /**
     * 変更事由のＧｅｔ
     *
     * @return 変更事由
     */
    String getChangeCause();
}
