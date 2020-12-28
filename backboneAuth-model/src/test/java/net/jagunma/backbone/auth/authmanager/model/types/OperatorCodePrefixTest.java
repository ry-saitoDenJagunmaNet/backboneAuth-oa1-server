package net.jagunma.backbone.auth.authmanager.model.types;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OperatorCodePrefixTest {

    /**
     * {@link OperatorCodePrefix#getCode()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・code値
     */
    @Test
    @Tag(TestSize.SMALL)
    void getCode_test0() {

        // 実行 & 結果検証
        assertThat(OperatorCodePrefix.赤城たちばな.getCode()).isEqualTo("002");
        assertThat(OperatorCodePrefix.前橋市.getCode()).isEqualTo("006");
        assertThat(OperatorCodePrefix.たかさき.getCode()).isEqualTo("070");
        assertThat(OperatorCodePrefix.はぐくみ.getCode()).isEqualTo("074");
        assertThat(OperatorCodePrefix.北群渋川.getCode()).isEqualTo("160");
        assertThat(OperatorCodePrefix.たのふじ.getCode()).isEqualTo("210");
        assertThat(OperatorCodePrefix.甘楽富岡.getCode()).isEqualTo("280");
        assertThat(OperatorCodePrefix.碓氷安中.getCode()).isEqualTo("350");
        assertThat(OperatorCodePrefix.あがつま.getCode()).isEqualTo("415");
        assertThat(OperatorCodePrefix.利根沼田.getCode()).isEqualTo("470");
        assertThat(OperatorCodePrefix.佐波伊勢崎.getCode()).isEqualTo("550");
        assertThat(OperatorCodePrefix.にったみどり.getCode()).isEqualTo("617");
        assertThat(OperatorCodePrefix.太田市.getCode()).isEqualTo("618");
        assertThat(OperatorCodePrefix.邑楽館林.getCode()).isEqualTo("744");
        assertThat(OperatorCodePrefix.電算センター.getCode()).isEqualTo("990");
    }

    /**
     * {@link OperatorCodePrefix#getPrefix()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・displayName
     */
    @Test
    @Tag(TestSize.SMALL)
    void getPrefix_test0() {

        // 実行 & 結果検証
        assertThat(OperatorCodePrefix.赤城たちばな.getPrefix()).isEqualTo("zv");
        assertThat(OperatorCodePrefix.前橋市.getPrefix()).isEqualTo("yu");
        assertThat(OperatorCodePrefix.たかさき.getPrefix()).isEqualTo("vr");
        assertThat(OperatorCodePrefix.はぐくみ.getPrefix()).isEqualTo("uq");
        assertThat(OperatorCodePrefix.北群渋川.getPrefix()).isEqualTo("qm");
        assertThat(OperatorCodePrefix.たのふじ.getPrefix()).isEqualTo("tp");
        assertThat(OperatorCodePrefix.甘楽富岡.getPrefix()).isEqualTo("so");
        assertThat(OperatorCodePrefix.碓氷安中.getPrefix()).isEqualTo("rn");
        assertThat(OperatorCodePrefix.あがつま.getPrefix()).isEqualTo("ok");
        assertThat(OperatorCodePrefix.利根沼田.getPrefix()).isEqualTo("mi");
        assertThat(OperatorCodePrefix.佐波伊勢崎.getPrefix()).isEqualTo("ws");
        assertThat(OperatorCodePrefix.にったみどり.getPrefix()).isEqualTo("ie");
        assertThat(OperatorCodePrefix.太田市.getPrefix()).isEqualTo("hd");
        assertThat(OperatorCodePrefix.邑楽館林.getPrefix()).isEqualTo("gc");
        assertThat(OperatorCodePrefix.電算センター.getPrefix()).isEqualTo("fb");
    }

    /**
     * {@link OperatorCodePrefix#getValidList()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・list値
     */
    @Test
    @Tag(TestSize.SMALL)
    void getValidList_test0() {

        // 期待値
        List<OperatorCodePrefix> expected = newArrayList();
        expected.add(OperatorCodePrefix.赤城たちばな);
        expected.add(OperatorCodePrefix.前橋市);
        expected.add(OperatorCodePrefix.たかさき);
        expected.add(OperatorCodePrefix.はぐくみ);
        expected.add(OperatorCodePrefix.北群渋川);
        expected.add(OperatorCodePrefix.たのふじ);
        expected.add(OperatorCodePrefix.甘楽富岡);
        expected.add(OperatorCodePrefix.碓氷安中);
        expected.add(OperatorCodePrefix.あがつま);
        expected.add(OperatorCodePrefix.利根沼田);
        expected.add(OperatorCodePrefix.佐波伊勢崎);
        expected.add(OperatorCodePrefix.にったみどり);
        expected.add(OperatorCodePrefix.太田市);
        expected.add(OperatorCodePrefix.邑楽館林);
        expected.add(OperatorCodePrefix.電算センター);

        // 実行
        List<OperatorCodePrefix> actual = OperatorCodePrefix.getValidList();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link OperatorCodePrefix#codeOf(String)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・codeでのenum取得
     */
    @Test
    @Tag(TestSize.SMALL)
    void codeOf_test0() {

        // 実行 & 結果検証
        assertThat(OperatorCodePrefix.codeOf("002")).isEqualTo(OperatorCodePrefix.赤城たちばな);
        assertThat(OperatorCodePrefix.codeOf("006")).isEqualTo(OperatorCodePrefix.前橋市);
        assertThat(OperatorCodePrefix.codeOf("070")).isEqualTo(OperatorCodePrefix.たかさき);
        assertThat(OperatorCodePrefix.codeOf("074")).isEqualTo(OperatorCodePrefix.はぐくみ);
        assertThat(OperatorCodePrefix.codeOf("160")).isEqualTo(OperatorCodePrefix.北群渋川);
        assertThat(OperatorCodePrefix.codeOf("210")).isEqualTo(OperatorCodePrefix.たのふじ);
        assertThat(OperatorCodePrefix.codeOf("280")).isEqualTo(OperatorCodePrefix.甘楽富岡);
        assertThat(OperatorCodePrefix.codeOf("350")).isEqualTo(OperatorCodePrefix.碓氷安中);
        assertThat(OperatorCodePrefix.codeOf("415")).isEqualTo(OperatorCodePrefix.あがつま);
        assertThat(OperatorCodePrefix.codeOf("470")).isEqualTo(OperatorCodePrefix.利根沼田);
        assertThat(OperatorCodePrefix.codeOf("550")).isEqualTo(OperatorCodePrefix.佐波伊勢崎);
        assertThat(OperatorCodePrefix.codeOf("617")).isEqualTo(OperatorCodePrefix.にったみどり);
        assertThat(OperatorCodePrefix.codeOf("618")).isEqualTo(OperatorCodePrefix.太田市);
        assertThat(OperatorCodePrefix.codeOf("744")).isEqualTo(OperatorCodePrefix.邑楽館林);
        assertThat(OperatorCodePrefix.codeOf("990")).isEqualTo(OperatorCodePrefix.電算センター);
        assertThat(OperatorCodePrefix.codeOf("")).isEqualTo(OperatorCodePrefix.UnKnown);
        assertThat(OperatorCodePrefix.codeOf("XXX")).isEqualTo(OperatorCodePrefix.UnKnown);
    }
}