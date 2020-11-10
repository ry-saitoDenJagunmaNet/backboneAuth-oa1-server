package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.dto.MessageDto;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010MessageVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa12010CompositionImportPresenterTest {

    // 実行既定値
    // MessageDtoリストデータの作成
    private List<MessageDto> createMessageDtoList() {
        List<MessageDto> list = newArrayList();
        list.add(MessageDto.createFrom("EOA13013","未セット(取引ロールコード)  CL:取引ロールコードが入力されていません。",Arrays.asList("取引ロールコード")));
        list.add(MessageDto.createFrom("EOA13009","重複レコードあり({0})    CL:{0}の同一キーで重複するレコードがあります。",Arrays.asList("取引ロールコード")));
        return list;
    }
    // MessageVoリストデータの作成
    private List<Oa12010MessageVo> createOa12010MessageVoList() {
        List<Oa12010MessageVo> list = newArrayList();
        for (MessageDto messageDto : createMessageDtoList()) {
            Oa12010MessageVo vo = new Oa12010MessageVo();
            vo.setMessageCode(messageDto.getMessageCode());
            vo.setMessage(messageDto.getMessage());
            vo.setMessageArgs(messageDto.getMessageArgs());
            list.add(vo);
        }
        return list;
    }

    /**
     * {@link Oa12010CompositionImportPresenter#bindTo(Oa12010Vo)}のテスト
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
        Oa12010Vo vo = new Oa12010Vo();
        Oa12010CompositionImportPresenter presenter = new Oa12010CompositionImportPresenter();
        presenter.setMessageDtoList(createMessageDtoList());

        // 期待値
        Oa12010Vo expectedVo = new Oa12010Vo();
        expectedVo.setMessageVoList(createOa12010MessageVoList());

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}