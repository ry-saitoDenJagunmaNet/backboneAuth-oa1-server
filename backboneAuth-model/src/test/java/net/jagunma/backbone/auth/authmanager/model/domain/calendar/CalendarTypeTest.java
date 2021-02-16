package net.jagunma.backbone.auth.authmanager.model.domain.calendar;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CalendarTypeTest {

    /**
     * {@link CalendarType#getCode()}テスト
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
        assertThat(CalendarType.天文台カレンダー.getCode()).isEqualTo((short) 0);
        assertThat(CalendarType.経済システム稼働カレンダー.getCode()).isEqualTo((short) 1);
        assertThat(CalendarType.信用カレンダー.getCode()).isEqualTo((short) 2);
        assertThat(CalendarType.広域物流カレンダー.getCode()).isEqualTo((short) 3);
    }

    /**
     * {@link CalendarType#getDisplayName()}テスト
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
        assertThat(CalendarType.天文台カレンダー.getDisplayName()).isEqualTo("天文台カレンダー");
        assertThat(CalendarType.経済システム稼働カレンダー.getDisplayName()).isEqualTo("経済システム稼働カレンダー");
        assertThat(CalendarType.信用カレンダー.getDisplayName()).isEqualTo("信用カレンダー");
        assertThat(CalendarType.広域物流カレンダー.getDisplayName()).isEqualTo("広域物流カレンダー");
    }

    /**
     * {@link CalendarType#getValidList()}テスト
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
        List<CalendarType> expected = newArrayList();
        expected.add(CalendarType.天文台カレンダー);
        expected.add(CalendarType.経済システム稼働カレンダー);
        expected.add(CalendarType.信用カレンダー);
        expected.add(CalendarType.広域物流カレンダー);

        // 実行
        List<CalendarType> actual = CalendarType.getValidList();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link CalendarType#getValidValues()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・CalendarType値
     */
    @Test
    @Tag(TestSize.SMALL)
    void getValidValues_test0() {

        // 期待値
        List<CalendarType> list = newArrayList();
        list.add(CalendarType.天文台カレンダー);
        list.add(CalendarType.経済システム稼働カレンダー);
        list.add(CalendarType.信用カレンダー);
        list.add(CalendarType.広域物流カレンダー);
        CalendarType expected[] = list.toArray(new CalendarType[list.size()]);

        // 実行
        CalendarType actual[] = CalendarType.getValidValues();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link CalendarType#codeOf(short)}テスト
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
        assertThat(CalendarType.codeOf((short) 0)).isEqualTo(CalendarType.天文台カレンダー);
        assertThat(CalendarType.codeOf((short) 1)).isEqualTo(CalendarType.経済システム稼働カレンダー);
        assertThat(CalendarType.codeOf((short) 2)).isEqualTo(CalendarType.信用カレンダー);
        assertThat(CalendarType.codeOf((short) 3)).isEqualTo(CalendarType.広域物流カレンダー);
        assertThat(CalendarType.codeOf((short) -1)).isEqualTo(CalendarType.UnKnown);
        assertThat(CalendarType.codeOf((short) 9)).isEqualTo(CalendarType.UnKnown);
    }

    /**
     * {@link CalendarType#is天文台カレンダー()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・CalendarTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is天文台カレンダー_test0() {

        // 実行
        CalendarType actual = CalendarType.天文台カレンダー;

        // 実行 & 結果検証
        assertTrue(actual.is天文台カレンダー());
    }

    /**
     * {@link CalendarType#is天文台カレンダー()}テスト
     *  ●パターン
     *    異常
     *
     *  ●検証事項
     *  ・CalendarTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is天文台カレンダー_test1() {

        // 実行
        CalendarType actual = CalendarType.経済システム稼働カレンダー;

        // 実行 & 結果検証
        assertThat(actual.is天文台カレンダー()).isEqualTo(false);
    }

    /**
     * {@link CalendarType#is経済システム稼働カレンダー()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・CalendarTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is経済システム稼働カレンダー_test0() {

        // 実行
        CalendarType actual = CalendarType.経済システム稼働カレンダー;

        // 実行 & 結果検証
        assertTrue(actual.is経済システム稼働カレンダー());
    }

    /**
     * {@link CalendarType#is経済システム稼働カレンダー()}テスト
     *  ●パターン
     *    異常
     *
     *  ●検証事項
     *  ・CalendarTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is経済システム稼働カレンダー_test1() {

        // 実行
        CalendarType actual = CalendarType.天文台カレンダー;

        // 実行 & 結果検証
        assertThat(actual.is経済システム稼働カレンダー()).isEqualTo(false);
    }

    /**
     * {@link CalendarType#is信用カレンダー()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・CalendarTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is信用カレンダー_test0() {

        // 実行
        CalendarType actual = CalendarType.信用カレンダー;

        // 実行 & 結果検証
        assertTrue(actual.is信用カレンダー());
    }

    /**
     * {@link CalendarType#is信用カレンダー()}テスト
     *  ●パターン
     *    異常
     *
     *  ●検証事項
     *  ・CalendarTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is信用カレンダー_test1() {

        // 実行
        CalendarType actual = CalendarType.天文台カレンダー;

        // 実行 & 結果検証
        assertThat(actual.is信用カレンダー()).isEqualTo(false);
    }

    /**
     * {@link CalendarType#is広域物流カレンダー()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・CalendarTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is広域物流カレンダー_test0() {

        // 実行
        CalendarType actual = CalendarType.広域物流カレンダー;

        // 実行 & 結果検証
        assertTrue(actual.is広域物流カレンダー());
    }

    /**
     * {@link CalendarType#is広域物流カレンダー()}テスト
     *  ●パターン
     *    異常
     *
     *  ●検証事項
     *  ・CalendarTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is広域物流カレンダー_test1() {

        // 実行
        CalendarType actual = CalendarType.天文台カレンダー;

        // 実行 & 結果検証
        assertThat(actual.is広域物流カレンダー()).isEqualTo(false);
    }
}