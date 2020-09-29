package net.jagunma.backbone.auth.authmanager.infra.web.oa11020;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11020.vo.Oa11020Vo;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.util.strings2.Strings2;

/**
 * OA11020 登録 Validator
 */
class Oa11020EntryValidator {

    private final Oa11020Vo vo;

    /**
     * コンストラクタ
     */
    Oa11020EntryValidator(Oa11020Vo vo) {
        this.vo = vo;
    }

    public static Oa11020EntryValidator with(Oa11020Vo vo) {
        return new Oa11020EntryValidator(vo);
    }

    /**
     * ViewObjectの検証を行います。
     */
    public void validate() {

        // ViewObject不正チェック
        Preconditions.checkNotNull(vo, () -> new GunmaRuntimeException("EOA13001"));

        // 未セットチェック
        Preconditions.checkNotEmpty(vo.getOperatorCode6(), () -> new GunmaRuntimeException("EOA13002", "オペレーターコード（下6桁）"));
        Preconditions.checkNotEmpty(vo.getOperatorName(), () -> new GunmaRuntimeException("EOA13002", "オペレーター名"));
        Preconditions.checkNotNull(vo.getExpirationStartDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（開始日）"));
        Preconditions.checkNotNull(vo.getExpirationEndDate(), () -> new GunmaRuntimeException("EOA13002", "有効期限（終了日）"));
        Preconditions.checkNotNull(vo.getTempoId(), () -> new GunmaRuntimeException("EOA13002", "店舗ID"));
        Preconditions.checkNotEmpty(vo.getChangeCause(), () -> new GunmaRuntimeException("EOA13002", "変更事由"));

        // 桁数チェック
        Preconditions.checkSize(6, vo.getOperatorCode6(), () -> new GunmaRuntimeException("EOA13003", "オペレーターコード（下6桁）", "6"));
        Preconditions.checkSize(0, 255, vo.getOperatorName(), () -> new GunmaRuntimeException("EOA13003", "オペレーター名", "255", "以下"));
        Preconditions.checkSize(0, 255, vo.getMailAddress(), () -> new GunmaRuntimeException("EOA13003", "メールアドレス", "255", "以下"));
        Preconditions.checkSize(0, 255, vo.getChangeCause(), () -> new GunmaRuntimeException("EOA13003", "変更事由", "255", "以下"));

        // ToDo: 全角混入チェック
        //  throw new GunmaRuntimeException("EOA13005", "オペレーターコード（下6桁）");
        //  throw new GunmaRuntimeException("EOA13005", "メールアドレス"); ←どこまでチェックするか？ライブラリ提供あるかも
        //  throw new GunmaRuntimeException("EOA13005", "パスワード");

        // 数値チェック
        if (!Strings2.isDigit(vo.getOperatorCode6())) {
            throw new GunmaRuntimeException("EOA13006", "オペレーターコード（下6桁）");
        }

        // 範囲指定不正チェック 有効期限
        Preconditions.checkMax(vo.getExpirationEndDate(), vo.getExpirationStartDate(), () -> new GunmaRuntimeException("EOA13007", "有効期限"));
    }
}
