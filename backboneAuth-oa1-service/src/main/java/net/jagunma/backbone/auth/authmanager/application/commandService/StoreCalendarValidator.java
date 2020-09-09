package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarStoreRequest;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;

/**
 * カレンダー適用サービス Validator
 */
class StoreCalendarValidator {
    private CalendarStoreRequest request;

    // コンストラクタ
    StoreCalendarValidator(CalendarStoreRequest request) {
        this.request = request;
    }
    // ファクトリーメソッド
    public static StoreCalendarValidator with(CalendarStoreRequest request) {
        return new StoreCalendarValidator(request);
    }

    /**
     * リクエストのチェックを行います。
     */
    public void validate() {
        // 未入力チェック
        Preconditions.checkNotNull(request, () -> new GunmaRuntimeException("EOA13004"));
    }
}

