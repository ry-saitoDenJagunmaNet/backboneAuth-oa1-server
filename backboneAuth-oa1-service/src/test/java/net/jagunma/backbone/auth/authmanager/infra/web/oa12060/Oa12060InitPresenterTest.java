package net.jagunma.backbone.auth.authmanager.infra.web.oa12060;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060Vo;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12060InitPresenterTest {

	/**
	 * {@link Oa12060InitPresenter}のテスト
	 *
	 * ・ レスポンスの値が正常にセットできることを確認する。
	 */
	@Test
	@Tag(TestSize.SMALL)
	void 実行検証() {
		// 事前準備
		Oa12060Vo vo = new Oa12060Vo();

		// 実行
		Oa12060InitPresenter presenter = new Oa12060InitPresenter();
		presenter.bindTo(vo);

		//　結果確認
		assertThat(vo).isNotNull();
		assertThat(vo.getYearMonth().compareTo(LocalDate.now())).isEqualTo(0);
		assertThat(vo.getYearMonthToString()).isEqualTo(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM")));
		assertThat(vo.getCalendarTypeFilterCheck1()).isEqualTo((short) 1);
		assertThat(vo.getCalendarTypeFilterCheck2()).isEqualTo((short) 1);
		assertThat(vo.getCalendarTypeFilterCheck3()).isEqualTo((short) 1);
		assertThat(vo.getWorkingdayOrHolidaySelect().length()).isEqualTo(0);

	}
}