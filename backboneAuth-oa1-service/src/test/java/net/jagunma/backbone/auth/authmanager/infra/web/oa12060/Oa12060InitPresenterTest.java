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
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test1() {

        // 実行値
        Oa12060Vo vo = new Oa12060Vo();
        Oa12060InitPresenter presenter = new Oa12060InitPresenter();

        // 期待値
        Oa12060Vo expectedVo = new Oa12060Vo();
        expectedVo.setYearMonth(LocalDate.now());
        expectedVo.setYearMonthToString(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM")));
        expectedVo.setCalendarTypeFilterCheck1((short) 1);
        expectedVo.setCalendarTypeFilterCheck2((short) 1);
        expectedVo.setCalendarTypeFilterCheck3((short) 1);
        expectedVo.setWorkingdayOrHolidaySelect("");
        expectedVo.setCalendarTable(null);
        expectedVo.setCalendarTypeFilterCheck1disabled(false);
        expectedVo.setCalendarTypeFilterCheck2disabled(false);
        expectedVo.setCalendarTypeFilterCheck3disabled(false);
        expectedVo.setCalendarList(null);
        expectedVo.setMessage(null);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}