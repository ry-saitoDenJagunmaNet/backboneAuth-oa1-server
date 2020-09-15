package net.jagunma.backbone.auth.authmanager.infra.web.oa11020;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoReferenceDto;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11020.vo.Oa11020Vo;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11020InitPresenterTest {

    /**
     * {@link Oa11020InitPresenter#bindTo(Oa11020Vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test0() {
        // 事前準備
        String jaCode = "006";
        String jaName = "JA前橋市";
        String prefix = "yu";
        List<TempoReferenceDto> tempoList = newArrayList();
        TempoReferenceDto tempoReferenceDto = new TempoReferenceDto();
        tempoReferenceDto.setTempoCode("001");
        tempoReferenceDto.setTempoName("本店");
        tempoList.add(tempoReferenceDto);
        tempoReferenceDto.setTempoCode("002");
        tempoReferenceDto.setTempoName("テスト店舗002");
        tempoList.add(tempoReferenceDto);
        tempoReferenceDto.setTempoCode("003");
        tempoReferenceDto.setTempoName("テスト店舗003");
        tempoList.add(tempoReferenceDto);

        // 実行値
        Oa11020InitPresenter presenter = new Oa11020InitPresenter();
        presenter.setJaCode(jaCode);
        presenter.setJaName(jaName);
        presenter.setOperatorCodePrefix(prefix);
        presenter.setTempoList(tempoList);

        // 期待値
        Oa11020Vo expectedVo = new Oa11020Vo();
        expectedVo.setJa(jaCode + " " + jaName);
        expectedVo.setOperatorCodePrefix(prefix);
        expectedVo.setTempoList(tempoList);

        // 検証対象
        Oa11020Vo vo = new Oa11020Vo();

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).isEqualToComparingFieldByField(expectedVo);
    }
}