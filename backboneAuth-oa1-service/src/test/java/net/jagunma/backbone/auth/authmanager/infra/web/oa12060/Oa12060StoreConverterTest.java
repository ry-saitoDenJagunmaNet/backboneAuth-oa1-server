package net.jagunma.backbone.auth.authmanager.infra.web.oa12060;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060CalendarVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060Vo;
import net.jagunma.backbone.auth.authmanager.model.types.CalendarType;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12060StoreConverterTest {

	/**
	 * {@link Oa12060StoreConverter}のテスト
	 *
	 * ・ リクエストのパラメータが正常にセットできることを確認する。
	 */
	@Test
	@Tag(TestSize.SMALL)
	void 実行検証() {
		// 事前準備
		Oa12060Vo vo = new Oa12060Vo();
		vo.setYearMonthToString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM")));
		List<Oa12060CalendarVo> list = newArrayList();
		Oa12060CalendarVo calendarVo = new Oa12060CalendarVo();
		calendarVo.setCalendarId1(1l);
		calendarVo.setIsWorkingDay1((short)1);
		calendarVo.setRecordVersion1(1);
		calendarVo.setCalendarId2(2l);
		calendarVo.setIsWorkingDay2((short)0);
		calendarVo.setRecordVersion2(1);
		calendarVo.setCalendarId3(3l);
		calendarVo.setIsWorkingDay3((short)1);
		calendarVo.setRecordVersion3(1);
		list.add(calendarVo);
		vo.setCalendarList(list);

		// 実行
		Oa12060StoreConverter converter = Oa12060StoreConverter.with(vo);
		LocalDate yearMonth = converter.getYearMonth();
		List<Oa12060StoreDetailsConverter> actual = converter.createCalendarList();

		// 期待値
		List<Oa12060StoreDetailsConverter> expected = newArrayList();
		expected.add(Oa12060StoreDetailsConverter.with(1l, CalendarType.経済システム稼働カレンダー,true,1));
		expected.add(Oa12060StoreDetailsConverter.with(2l, CalendarType.信用カレンダー,false,1));
		expected.add(Oa12060StoreDetailsConverter.with(3l, CalendarType.広域物流カレンダー,true,1));

		//　結果確認
		// 年月
		assertThat(yearMonth).isEqualTo(LocalDate.now().withDayOfMonth(1));
		// カレンダーの稼働日の結果確認
		for (int i = 0; i < expected.size(); i++) {
			assertThat(actual.get(i)).as(i + 1 + "レコード目でエラー")
				.isEqualToComparingFieldByField(expected.get(i));
		}
	}

	/**
	 * {@link Oa12060StoreConverter}のテスト
	 *
	 * ・ リクエストのパラメータのカレンダーの値がない場合、正常にセットできることを確認する。
	 */
	@Test
	@Tag(TestSize.SMALL)
	void リクエストのカレンダーの値がない場合() {
		// 事前準備
		Oa12060Vo vo = new Oa12060Vo();
		vo.setYearMonthToString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM")));
		List<Oa12060CalendarVo> list = newArrayList();
		vo.setCalendarList(list);

		// 実行
		Oa12060StoreConverter converter = Oa12060StoreConverter.with(vo);
		LocalDate yearMonth = converter.getYearMonth();
		List<Oa12060StoreDetailsConverter> actual = converter.createCalendarList();

		// 期待値
		List<Oa12060StoreDetailsConverter> expected = newArrayList();
		expected.add(Oa12060StoreDetailsConverter.with(1l, CalendarType.経済システム稼働カレンダー,true,1));
		expected.add(Oa12060StoreDetailsConverter.with(2l, CalendarType.信用カレンダー,false,1));
		expected.add(Oa12060StoreDetailsConverter.with(3l, CalendarType.広域物流カレンダー,true,1));

		//　結果確認
		// 年月
		assertThat(yearMonth).isEqualTo(LocalDate.now().withDayOfMonth(1));
		// カレンダーの稼働日の結果確認
		assertThat(actual.size()).isEqualTo(0);
	}
}