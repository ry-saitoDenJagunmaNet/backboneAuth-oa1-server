package net.jagunma.backbone.auth.authmanager.application.service.oa12060;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import net.jagunma.backbone.auth.authmanager.application.queryService.CalendarReferenceService;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa12060.vo.Oa12060SearchResponseVo;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa12060.vo.Oa12060Vo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * OA12060 カレンダーメンテナンス 検索 サービス
 */
@Service
@Transactional
public class Oa12060SearchService {

	private final CalendarReferenceService calendarReferenceService;

	// コンストラクタ
	public Oa12060SearchService(CalendarReferenceService calendarReferenceService) {
		this.calendarReferenceService = calendarReferenceService;
	}

	/**
	 * カレンダー検索を行います。
	 *
	 * @param vo カレンダーメンテナンスView Object
	 * @param responseVo カレンダー検索結果
	 */
	public void search(Oa12060Vo vo, Oa12060SearchResponseVo responseVo) {

		vo.setYearMonth(LocalDate.parse( vo.getYearMonthToString() + "/01", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
		Oa12060SearchConverter converter = Oa12060SearchConverter.with(vo);
		Oa12060SearchPresenter presenter = new Oa12060SearchPresenter();

		presenter.setYearMonth(converter.getYearMonth());
		calendarReferenceService.getCalendars(converter, presenter);

		presenter.bindTo(responseVo);
	}
}
