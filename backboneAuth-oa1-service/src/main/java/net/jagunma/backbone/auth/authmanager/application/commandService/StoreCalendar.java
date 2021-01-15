package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarStoreDetailsRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.calendarCommand.CalendarStoreRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarRepositoryForStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * カレンダー適用サービス
 */
@Service
@Transactional
public class StoreCalendar {

    private final CalendarRepositoryForStore calendarRepositoryForStore;
    private final CalendarRepository calendarRepository;

    // コンストラクタ
    public StoreCalendar(
        CalendarRepositoryForStore calendarRepositoryForStore,
        CalendarRepository calendarRepository) {

        this.calendarRepositoryForStore = calendarRepositoryForStore;
        this.calendarRepository = calendarRepository;
    }

    /**
     * 稼働日または休日を適用します
     *
     * @param request レンダーメンテナンス反映サービス Request
     * @return 適用件数
     */
    public int execute(CalendarStoreRequest request) {

        // パラメーターの検証
        StoreCalendarValidator.with(request).validate();

        // 更新件数
        int reflected = 0;

        // 更新
        for (CalendarStoreDetailsRequest CalendarStoreDetails : request.createCalendarList()) {
            // calendarモデルに変換
            Calendar calendar = Calendar.createFrom(
                CalendarStoreDetails.getCalendarId(),
                CalendarStoreDetails.getCalendarType(),
                null,
                !CalendarStoreDetails.getIsWorkingDay(),
                true,
                null,
                CalendarStoreDetails.getRecordVersion());

            // 稼働/休日が変更になったデータを更新
            Calendar oldClendar = calendarRepository.findOneById(calendar.getCalendarId());
            if (!calendar.getIsHoliday().equals(oldClendar.getIsHoliday())) {
                calendarRepositoryForStore.update(calendar);
                reflected++;
            }
        }
        return reflected;
    }
}