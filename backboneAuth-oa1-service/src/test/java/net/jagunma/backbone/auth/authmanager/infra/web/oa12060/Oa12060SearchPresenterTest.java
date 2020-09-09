package net.jagunma.backbone.auth.authmanager.infra.web.oa12060;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendar;
import net.jagunma.backbone.auth.authmanager.model.domain.calendar.Calendars;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12060SearchPresenterTest {

    /**
     * {@link Oa12060SearchPresenter}のテスト
     *
     * ・ レスポンスの値が正常にセットできることを確認する。
     */
    @Test
    @Tag(TestSize.SMALL)
    void Presenterの全メソッドの実行確認() {

        // 事前準備
        LocalDate yearMonth = LocalDate.now();
        Calendars calendars = Calendars.createFrom(new ArrayList<Calendar>());
        Oa12060Vo vo = new Oa12060Vo();

        // 実行
        Oa12060SearchPresenter presenter = new Oa12060SearchPresenter();
        presenter.setYearMonth(yearMonth);
        presenter.bindTo(vo);


        //　結果確認
        assertThat(vo).isNotNull();
        assertThat(vo.getSearchResponse()).isNotNull();
        assertThat(vo.getCalendarTypeFilterCheck1()).isNotNull();
        assertThat(vo.getCalendarTypeFilterCheck2()).isNotNull();
        assertThat(vo.getCalendarTypeFilterCheck3()).isNotNull();
    }
}