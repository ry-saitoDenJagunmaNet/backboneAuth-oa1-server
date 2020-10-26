package net.jagunma.backbone.auth.authmanager.infra.web.oa11020;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11020.vo.Oa11020Vo;

/**
 * OA11020 オペレーター登録サービス Request Converter
 */
class Oa11020EntryConverter implements OperatorEntryRequest {

    /**
     * OA11020 ViewObject
     */
    private final Oa11020Vo vo;

    // コンストラクタ
    Oa11020EntryConverter(Oa11020Vo vo) {
        this.vo = vo;
    }

    // ファクトリーメソッド
    public static Oa11020EntryConverter with(Oa11020Vo vo) {
        return new Oa11020EntryConverter(vo);
    }

    /**
     * オペレーターコード（下6桁）のＧｅｔ
     *
     * @return オペレーターコード（下6桁）
     */
    public String getOperatorCode6() {
        return vo.getOperatorCode6();
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
    public LocalDate getExpirationStartDate() {
        return vo.getExpirationStartDate();
    }
    /**
     * 有効期限終了日のＧｅｔ
     *
     * @return 有効期限終了日
     */
    public LocalDate getExpirationEndDate() {
        return vo.getExpirationEndDate();
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
     * 変更事由のＧｅｔ
     *
     * @return 変更事由
     */
    public String getChangeCause() {
        return vo.getChangeCause();
    }
    /**
     * パスワードのＧｅｔ
     *
     * @return パスワード
     */
    public String getPassword() {
        return vo.getPassword();
    }
    /**
     * パスワードの確認入力のＧｅｔ
     *
     * @return パスワードの確認入力
     */
    public String getConfirmPassword() {
        return vo.getConfirmPassword();
    }
}
