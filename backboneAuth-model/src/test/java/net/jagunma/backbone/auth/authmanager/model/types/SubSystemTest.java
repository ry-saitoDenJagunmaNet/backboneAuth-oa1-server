package net.jagunma.backbone.auth.authmanager.model.types;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SubSystemTest {

    /**
     * {@link SubSystem#getCode()}テスト
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
        assertThat(SubSystem.購買.getCode()).isEqualTo("KB");
        assertThat(SubSystem.販売_青果.getCode()).isEqualTo("YS");
        assertThat(SubSystem.販売_花卉.getCode()).isEqualTo("YF");
        assertThat(SubSystem.販売_米.getCode()).isEqualTo("HK");
        assertThat(SubSystem.販売_麦.getCode()).isEqualTo("HM");
        assertThat(SubSystem.販売_畜産.getCode()).isEqualTo("AN");
    }

    /**
     * {@link SubSystem#getDisplayName()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・displayName
     */
    @Test
    @Tag(TestSize.SMALL)
    void getDisplayName_test0() {

        // 実行 & 結果検証
        assertThat(SubSystem.購買.getDisplayName()).isEqualTo("購買");
        assertThat(SubSystem.販売_青果.getDisplayName()).isEqualTo("販売・青果");
        assertThat(SubSystem.販売_花卉.getDisplayName()).isEqualTo("販売・花卉");
        assertThat(SubSystem.販売_米.getDisplayName()).isEqualTo("販売・米");
        assertThat(SubSystem.販売_麦.getDisplayName()).isEqualTo("販売・麦");
        assertThat(SubSystem.販売_畜産.getDisplayName()).isEqualTo("販売・畜産");
    }

    /**
     * {@link SubSystem#getValidList()}テスト
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
        List<SubSystem> expected = newArrayList();
        expected.add(SubSystem.購買);
        expected.add(SubSystem.販売_青果);
        expected.add(SubSystem.販売_花卉);
        expected.add(SubSystem.販売_米);
        expected.add(SubSystem.販売_麦);
        expected.add(SubSystem.販売_畜産);

        // 実行
        List<SubSystem> actual = SubSystem.getValidList();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link SubSystem#getValidValues()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・SubSystemの値
     */
    @Test
    @Tag(TestSize.SMALL)
    void getValidValues_test0() {

        // 期待値
        List<SubSystem> list = newArrayList();
        list.add(SubSystem.購買);
        list.add(SubSystem.販売_青果);
        list.add(SubSystem.販売_花卉);
        list.add(SubSystem.販売_米);
        list.add(SubSystem.販売_麦);
        list.add(SubSystem.販売_畜産);
        SubSystem expected[] = list.toArray(new SubSystem[list.size()]);

        // 実行
        SubSystem actual[] = SubSystem.getValidValues();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link SubSystem#codeOf(String)}テスト
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
        assertThat(SubSystem.codeOf("KB")).isEqualTo(SubSystem.購買);
        assertThat(SubSystem.codeOf("YS")).isEqualTo(SubSystem.販売_青果);
        assertThat(SubSystem.codeOf("YF")).isEqualTo(SubSystem.販売_花卉);
        assertThat(SubSystem.codeOf("HK")).isEqualTo(SubSystem.販売_米);
        assertThat(SubSystem.codeOf("HM")).isEqualTo(SubSystem.販売_麦);
        assertThat(SubSystem.codeOf("AN")).isEqualTo(SubSystem.販売_畜産);
        assertThat(SubSystem.codeOf("")).isEqualTo(SubSystem.UnKnown);
        assertThat(SubSystem.codeOf("XX")).isEqualTo(SubSystem.UnKnown);
    }

    /**
     * {@link SubSystem#nameOf(String)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・nameでのenum取得
     */
    @Test
    @Tag(TestSize.SMALL)
    void nameOf_test0() {

        // 実行 & 結果検証
        assertThat(SubSystem.nameOf("購買")).isEqualTo(SubSystem.購買);
        assertThat(SubSystem.nameOf("販売・青果")).isEqualTo(SubSystem.販売_青果);
        assertThat(SubSystem.nameOf("販売・花卉")).isEqualTo(SubSystem.販売_花卉);
        assertThat(SubSystem.nameOf("販売・米")).isEqualTo(SubSystem.販売_米);
        assertThat(SubSystem.nameOf("販売・麦")).isEqualTo(SubSystem.販売_麦);
        assertThat(SubSystem.nameOf("販売・畜産")).isEqualTo(SubSystem.販売_畜産);
        assertThat(SubSystem.nameOf("未定義")).isEqualTo(SubSystem.UnKnown);
        assertThat(SubSystem.nameOf("XX")).isEqualTo(SubSystem.UnKnown);
    }
}