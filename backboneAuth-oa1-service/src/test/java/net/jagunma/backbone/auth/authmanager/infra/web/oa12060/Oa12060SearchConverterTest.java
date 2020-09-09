package net.jagunma.backbone.auth.authmanager.infra.web.oa12060;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060Vo;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12060SearchConverterTest {

    /**
     * {@link Oa12060SearchConverter}のテスト
     *
     * ・ リクエストの年月が正常にセットできることを確認する。
     */
    @Test
    @Tag(TestSize.SMALL)
    void 実行検証() {
        // 事前準備
        Oa12060Vo vo = new Oa12060Vo();
        vo.createFrom(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM")),
            null, null, null, null);

        // 実行
        LocalDate yearMonth = Oa12060SearchConverter.with(vo).getYearMonth();

        //　結果確認
        assertThat(yearMonth).isEqualTo(LocalDate.now().withDayOfMonth(1));
    }
}