package net.jagunma.backbone.auth.authmanager.infra.web.oa11020;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11020.vo.Oa11020Vo;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
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
    void bindTo_test() {
        // 実行既定値
        String jaCode = "006";
        String jaName = "JA前橋市";
        String prefix = "yu";
        List<BranchAtMoment> tempoList = newArrayList();
        tempoList.add(BranchAtMoment.builder()
            .withIdentifier(1L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("001")).withName("本店").build())
            .build());
        tempoList.add(BranchAtMoment.builder()
            .withIdentifier(2L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("002")).withName("店舗002").build())
            .build());
        tempoList.add(BranchAtMoment.builder()
            .withIdentifier(3L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("003")).withName("店舗003").build())
            .build());

        // 実行値
        Oa11020Vo vo = new Oa11020Vo();
        Oa11020InitPresenter presenter = new Oa11020InitPresenter();
        presenter.setJaCode(jaCode);
        presenter.setJaName(jaName);
        presenter.setOperatorCodePrefix(prefix);
        presenter.setTempoList(BranchesAtMoment.of(tempoList));

        // 期待値
        Oa11020Vo expectedVo = new Oa11020Vo();
        expectedVo.setJa(jaCode + " " + jaName);
        expectedVo.setTempoId(null);
        expectedVo.setOperatorCodePrefix(prefix);
        expectedVo.setOperatorCode6(null);
        expectedVo.setOperatorName(null);
        expectedVo.setMailAddress(null);
        expectedVo.setExpirationStartDate(null);
        expectedVo.setExpirationEndDate(null);
        expectedVo.setChangeCause(null);
        expectedVo.setTempoList(BranchesAtMoment.of(tempoList));
        expectedVo.setPassword(null);
        expectedVo.setConfirmPassword(null);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}
