package net.jagunma.backbone.auth.authmanager.application.service.oa12060;

import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa12060.vo.Oa12060Vo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * OA12060 カレンダーメンテナンス 登録 サービス
 */
@Service
@Transactional
public class Oa12060EntryService {

//	private final CalendarRepositoryForStore calendarRepositoryForStore;
//
//	// コンストラクタ
//	public Oa12060EntryService(CalendarRepositoryForStore calendarRepositoryForStore) {
//		this.calendarRepositoryForStore = calendarRepositoryForStore;
//	}

	/**
	 * カレンダー登録を行います。
	 *
	 * @param vo カレンダーメンテナンスView Object
	 */
	public void entry(Oa12060Vo vo) {

	}
}
