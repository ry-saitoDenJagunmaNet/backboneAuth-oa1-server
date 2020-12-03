package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.dto.MessageDto;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010MessageVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12010CompositionImportCheckPresenterTest {

    // 実行既定値
    private String importfileView = null;
    private List<MessageDto> messageDtoList = newArrayList();
    private String status = "";

    private List<MessageDto> createMessageDtoList() {
        List<MessageDto> list = newArrayList();
        list.add(MessageDto.createFrom("EOA13103", new ArrayList<String>(Arrays.asList("[取引ロール－取引グループ編成]シート","1","取引ロールコード"))));
        list.add(MessageDto.createFrom("EOA13103", new ArrayList<String>(Arrays.asList("[取引ロール－取引グループ編成]シート","2","取引グループコード"))));
        return list;
    }


    /**
     * {@link Oa12010CompositionImportCheckPresenter#bindTo(Oa12010Vo)}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test0() {

        // 実行値
        Oa12010Vo actualVo = new Oa12010Vo();
        Oa12010CompositionImportCheckPresenter presenter = new Oa12010CompositionImportCheckPresenter();
        presenter.setImportfileView(importfileView);
        presenter.setMessageDtoList(messageDtoList);
        presenter.setStatus(status);

        // 期待値
        Oa12010Vo expectedVo = new Oa12010Vo();
        expectedVo.setMessageVoList(newArrayList());

        // 実行
        presenter.bindTo(actualVo);

        // 結果検証
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa12010CompositionImportCheckPresenter#bindTo(Oa12010Vo)}のテスト
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
        importfileView = "取引ロール編成-販売・畜産システム.xlsx";
        messageDtoList = createMessageDtoList();
        status = "";

        Oa12010Vo actualVo = new Oa12010Vo();
        Oa12010CompositionImportCheckPresenter presenter = new Oa12010CompositionImportCheckPresenter();
        presenter.setImportfileView(importfileView);
        presenter.setMessageDtoList(messageDtoList);
        presenter.setStatus(status);

        // 期待値
        Oa12010Vo expectedVo = new Oa12010Vo();
        expectedVo.setImportfileView(importfileView);
        List<Oa12010MessageVo> voList = newArrayList();
        for (MessageDto messageDto : messageDtoList) {
            Oa12010MessageVo messageVo = new Oa12010MessageVo();
            messageVo.setMessageCode(messageDto.getMessageCode());
            messageVo.setMessage(messageDto.getMessage());
            messageVo.setMessageArgs(messageDto.getMessageArgs());
            voList.add(messageVo);
        }
        expectedVo.setMessageVoList(voList);
        presenter.setStatus(status);

        // 実行
        presenter.bindTo(actualVo);

        // 結果検証
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}