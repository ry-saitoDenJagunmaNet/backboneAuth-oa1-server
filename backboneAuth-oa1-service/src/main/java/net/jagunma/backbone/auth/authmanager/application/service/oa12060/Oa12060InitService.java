package net.jagunma.backbone.auth.authmanager.application.service.oa12060;


import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.queryService.CalendarReferenceService;
import net.jagunma.backbone.auth.authmanager.infrastructure.web.oa12060.vo.Oa12060Vo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * OA12060 カレンダーメンテナンス 初期表示 サービス
 */
@Service
@Transactional
public class Oa12060InitService {

	private final CalendarReferenceService calendarReferenceService;

	// コンストラクタ
	public Oa12060InitService(CalendarReferenceService calendarReferenceService) {

		this.calendarReferenceService = calendarReferenceService;
	}

	/**
	 * Formの初期化処理です。
	 *
	 * @param vo カレンダーメンテナンスView Object
	 */
	public void init(Oa12060Vo vo) {

		Oa12060InitPresenter presenter = new Oa12060InitPresenter();

		// 検索（表示）条件の初期値
		LocalDate ld = LocalDate.now();
		presenter.setYearMonth(ld);
		presenter.setCalendarTypeCheck1((short) 1);
		presenter.setCalendarTypeCheck2((short) 1);
		presenter.setCalendarTypeCheck3((short) 1);
		presenter.setWorkingdayOrHolidaySelect("");

		presenter.bindTo(vo);
	}
}
