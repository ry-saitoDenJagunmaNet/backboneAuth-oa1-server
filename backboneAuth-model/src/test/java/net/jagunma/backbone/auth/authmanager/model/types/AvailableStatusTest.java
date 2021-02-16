package net.jagunma.backbone.auth.authmanager.model.types;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AvailableStatusTest {

    /**
     * {@link AvailableStatus#getCode()}テスト
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
        assertThat(AvailableStatus.利用可能.getCode()).isEqualTo((short) 0);
        assertThat(AvailableStatus.利用不可.getCode()).isEqualTo((short) 1);
    }

    /**
     * {@link AvailableStatus#getDisplayName()}テスト
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
        assertThat(AvailableStatus.利用可能.getDisplayName()).isEqualTo("利用可能");
        assertThat(AvailableStatus.利用不可.getDisplayName()).isEqualTo("利用不可");
    }

    /**
     * {@link AvailableStatus#getValidList()}テスト
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
        List<AvailableStatus> expected = newArrayList();
        expected.add(AvailableStatus.利用可能);
        expected.add(AvailableStatus.利用不可);

        // 実行
        List<AvailableStatus> actual = AvailableStatus.getValidList();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link AvailableStatus#getValidValues()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・list値
     */
    @Test
    @Tag(TestSize.SMALL)
    void getValidValues_test0() {

        // 期待値
        List<AvailableStatus> list = newArrayList();
        list.add(AvailableStatus.利用可能);
        list.add(AvailableStatus.利用不可);
        AvailableStatus expected[] = list.toArray(new AvailableStatus[list.size()]);

        // 実行
        AvailableStatus actual[] = AvailableStatus.getValidValues();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link AvailableStatus#codeOf(short)}テスト
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
        assertThat(AvailableStatus.codeOf((short) 0)).isEqualTo(AvailableStatus.利用可能);
        assertThat(AvailableStatus.codeOf((short) 1)).isEqualTo(AvailableStatus.利用不可);
        assertThat(AvailableStatus.codeOf((short) -1)).isEqualTo(AvailableStatus.UnKnown);
        assertThat(AvailableStatus.codeOf((short) 9)).isEqualTo(AvailableStatus.UnKnown);
    }
}