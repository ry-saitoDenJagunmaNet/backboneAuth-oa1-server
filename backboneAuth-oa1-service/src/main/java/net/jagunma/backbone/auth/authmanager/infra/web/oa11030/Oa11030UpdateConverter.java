package net.jagunma.backbone.auth.authmanager.infra.web.oa11030;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorUpdateRequest;
import net.jagunma.backbone.auth.authmanager.infra.util.CheckboxUtil;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030Vo;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;

/**
 * OA11030 オペレーター更新サービス Request Converter
 */
class Oa11030UpdateConverter implements OperatorUpdateRequest {

    /**
     * OA11030 ViewObject
     */
    private final Oa11030Vo vo;

    // コンストラクタ
    Oa11030UpdateConverter(Oa11030Vo vo) {
        this.vo = vo;
    }

    // ファクトリーメソッド
    public static Oa11030UpdateConverter with(Oa11030Vo vo) {
        return new Oa11030UpdateConverter(vo);
    }

    /**
     * オペレーターIDのＧｅｔ
     *
     * @return オペレーターID
     */
    public Long getOperatorId() {
        return vo.getOperatorId();
    }
    /**
     * オペレーター名のＧｅｔ
     *
     * @return オペレーター名
     */
    public String getOperatorName() {
        return vo.getOperatorName();
    }
    /**
     * メールアドレスのＧｅｔ
     *
     * @return メールアドレス
     */
    public String getMailAddress() {
        return vo.getMailAddress();
    }
    /**
     * 有効期限開始日のＧｅｔ
     *
     * @return 有効期限開始日
     */
    public LocalDate getValidThruStartDate() {
        return vo.getValidThruStartDate();
    }
    /**
     * 有効期限終了日のＧｅｔ
     *
     * @return 有効期限終了日
     */
    public LocalDate getValidThruEndDate() {
        return vo.getValidThruEndDate();
    }
    /**
     * 機器認証のＧｅｔ
     *
     * @return 機器認証
     */
    public Boolean getIsDeviceAuth() {
        return CheckboxUtil.getSmoother(vo.getIsDeviceAuth());
    }
    /**
     * 店舗IDのＧｅｔ
     *
     * @return 店舗ID
     */
    public Long getBranchId() {
        return vo.getBranchId();
    }
    /**
     * 利用可否状態のＧｅｔ
     *
     * @return 利用可否状態
     */
    public AvailableStatus getAvailableStatus() {
        return (CheckboxUtil.getSmoother(vo.getAvailableStatus()) == true)? AvailableStatus.利用可能 : AvailableStatus.利用不可;
    }
    /**
     * レコードバージョンのＧｅｔ
     *
     * @return レコードバージョン
     */
    public Integer getRecordVersion() {
        return vo.getRecordVersion();
    }
    /**
     * 変更事由のＧｅｔ
     *
     * @return 変更事由
     */
    public String getChangeCause() {
        return vo.getChangeCause();
    }
}
