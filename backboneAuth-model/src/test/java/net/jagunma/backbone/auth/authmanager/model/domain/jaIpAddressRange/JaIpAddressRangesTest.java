package net.jagunma.backbone.auth.authmanager.model.domain.jaIpAddressRange;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class JaIpAddressRangesTest {

    // 実行既定値

    // JaAtMomentデータの作成
    private JaAtMoment createJaAtMoment(Long jaId, String jaCode, String jaName) {
        return JaAtMoment.builder()
            .withIdentifier(jaId)
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(jaCode))
                .withName(jaName)
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
    }

    /**
     * {@link JaIpAddressRanges#createFrom(Collection)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_test0() {

        // 実行値 ＆ 期待値
        List<JaIpAddressRange> list = newArrayList();
        list.add(JaIpAddressRange.createFrom(1L,"002","145.254.",LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),
            createJaAtMoment(2L,"002",  "ＪＡ００２")));
        list.add(JaIpAddressRange.createFrom(1L,"006","145.254.",LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),
            createJaAtMoment(6L,"006",  "ＪＡ００６")));
        list.add(JaIpAddressRange.createFrom(1L,"070","145.254.",LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),
            createJaAtMoment(23L,"070",  "ＪＡ０７０")));

        // 実行
        JaIpAddressRanges jaIpAddressRanges = JaIpAddressRanges.createFrom(list);

        // 結果検証
        assertThat(jaIpAddressRanges.getValues().size()).isEqualTo(list.size());
        for(int i = 0; i < jaIpAddressRanges.getValues().size(); i++) {
            assertThat(jaIpAddressRanges.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }

    /**
     * {@link JaIpAddressRanges#createFrom(Collection)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_test1() {

        // 実行値 ＆ 期待値
        List<JaIpAddressRange> list = newArrayList();

        // 実行
        JaIpAddressRanges jaIpAddressRanges = JaIpAddressRanges.createFrom(list);

        // 結果検証
        assertThat(jaIpAddressRanges.getValues().size()).isEqualTo(list.size());
        for(int i = 0; i < jaIpAddressRanges.getValues().size(); i++) {
            assertThat(jaIpAddressRanges.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                .usingRecursiveComparison().isEqualTo(list.get(i));
        }
    }
}