package net.jagunma.backbone.auth.authmanager.infra.web.oa12060;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060CalendarVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.CalendarType;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12060StoreConverterTest {

    /**
     * {@link Oa12060StoreConverter#with(Oa12060Vo)}テスト
     *  ●パターン
     *    対象のカレンダーデータが無い場合のテスト
     *
     *  ●検証事項
     *  ・Converterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void with_test1() {

        // 実行値
        LocalDate yearMonth = null;
        String yearMonthToString = null;
        Short calendarTypeFilterCheck1 = null;
        Short calendarTypeFilterCheck2 = null;
        Short calendarTypeFilterCheck3 = null;
        String workingdayOrHolidaySelect = null;
        String calendarTable = null;
        boolean calendarTypeFilterCheck1disabled = false;
        boolean calendarTypeFilterCheck2disabled = false;
        boolean calendarTypeFilterCheck3disabled = false;
        List<Oa12060CalendarVo> calendarList = newArrayList();
        Oa12060Vo vo = new Oa12060Vo();
        vo.setYearMonth(yearMonth);
        vo.setYearMonthToString(yearMonthToString);
        vo.setCalendarTypeFilterCheck1(calendarTypeFilterCheck1);
        vo.setCalendarTypeFilterCheck2(calendarTypeFilterCheck2);
        vo.setCalendarTypeFilterCheck3(calendarTypeFilterCheck3);
        vo.setWorkingdayOrHolidaySelect(workingdayOrHolidaySelect);
        vo.setCalendarTable(calendarTable);
        vo.setCalendarTypeFilterCheck1disabled(calendarTypeFilterCheck1disabled);
        vo.setCalendarTypeFilterCheck2disabled(calendarTypeFilterCheck2disabled);
        vo.setCalendarTypeFilterCheck3disabled(calendarTypeFilterCheck3disabled);
        vo.setCalendarList(calendarList);

        // 実行
        Oa12060StoreConverter converter = Oa12060StoreConverter.with(vo);

        // 結果検証
        assertTrue(converter instanceof Oa12060StoreConverter);
        List<Oa12060StoreDetailsConverter> actual = converter.createCalendarList();
        assertThat(actual.size()).isEqualTo(0);
    }

    /**
     * {@link Oa12060StoreConverter#createCalendarList()}テスト
     *  ●パターン
     *    カレンダー詳細リストを作成のテスト
     *
     *  ●検証事項
     *  ・Converterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void createCalendarList_test1() {

        // 実行値
        LocalDate yearMonth = null;
        String yearMonthToString = null;
        Short calendarTypeFilterCheck1 = null;
        Short calendarTypeFilterCheck2 = null;
        Short calendarTypeFilterCheck3 = null;
        String workingdayOrHolidaySelect = null;
        String calendarTable = null;
        boolean calendarTypeFilterCheck1disabled = false;
        boolean calendarTypeFilterCheck2disabled = false;
        boolean calendarTypeFilterCheck3disabled = false;
        List<Oa12060CalendarVo> calendarList = newArrayList();
        Oa12060CalendarVo calendarVo = new Oa12060CalendarVo();
        calendarList.add(cretaeOa12060CalendarVo(1l, (short) 1, 2l, (short) 0, 3l, (short) 1));
        calendarList.add(cretaeOa12060CalendarVo(null, (short) 1, null, (short) 0, null, (short) 1));
        Oa12060Vo vo = new Oa12060Vo();
        vo.setYearMonth(yearMonth);
        vo.setYearMonthToString(yearMonthToString);
        vo.setCalendarTypeFilterCheck1(calendarTypeFilterCheck1);
        vo.setCalendarTypeFilterCheck2(calendarTypeFilterCheck2);
        vo.setCalendarTypeFilterCheck3(calendarTypeFilterCheck3);
        vo.setWorkingdayOrHolidaySelect(workingdayOrHolidaySelect);
        vo.setCalendarTable(calendarTable);
        vo.setCalendarTypeFilterCheck1disabled(calendarTypeFilterCheck1disabled);
        vo.setCalendarTypeFilterCheck2disabled(calendarTypeFilterCheck2disabled);
        vo.setCalendarTypeFilterCheck3disabled(calendarTypeFilterCheck3disabled);
        vo.setCalendarList(calendarList);

        // 期待値
        List<Oa12060StoreDetailsConverter> expected = newArrayList();
        expected.add(Oa12060StoreDetailsConverter.with(1l, CalendarType.経済システム稼働カレンダー,true,1));
        expected.add(Oa12060StoreDetailsConverter.with(2l, CalendarType.信用カレンダー,false,1));
        expected.add(Oa12060StoreDetailsConverter.with(3l, CalendarType.広域物流カレンダー,true,1));

        // 実行
        Oa12060StoreConverter converter = Oa12060StoreConverter.with(vo);
        List<Oa12060StoreDetailsConverter> actual = converter.createCalendarList();

        // 結果検証
        assertTrue(converter instanceof Oa12060StoreConverter);
        for (int i = 0; i < actual.size(); i++) {
            assertThat(actual.get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(expected.get(i));
        }
    }

    /**
     * リクエスト用の１日分カレンダーを作成します。
     *
     * @param calendarId1   経済システム稼働カレンダーのカレンダーID
     * @param isWorkingDay1 経済システム稼働カレンダーの稼働日フラグ
     * @param calendarId2   信用カレンダーのカレンダーID
     * @param isWorkingDay2 信用カレンダーの稼働日フラグ
     * @param calendarId3   広域物流カレンダーのカレンダーID
     * @param isWorkingDay3 広域物流カレンダーの稼働日フラグ
     * @return リクエスト用の１日分カレンダー
     */
    private Oa12060CalendarVo cretaeOa12060CalendarVo(
        Long calendarId1, short isWorkingDay1,
        Long calendarId2, short isWorkingDay2,
        Long calendarId3, short isWorkingDay3) {

        Oa12060CalendarVo calendarVo = new Oa12060CalendarVo();
        calendarVo.setCalendarId1(calendarId1);
        calendarVo.setIsWorkingDay1(isWorkingDay1);
        calendarVo.setRecordVersion1(1);
        calendarVo.setCalendarId2(calendarId2);
        calendarVo.setIsWorkingDay2(isWorkingDay2);
        calendarVo.setRecordVersion2(1);
        calendarVo.setCalendarId3(calendarId3);
        calendarVo.setIsWorkingDay3(isWorkingDay3);
        calendarVo.setRecordVersion3(1);
        return calendarVo;
    }
}