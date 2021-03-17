package net.jagunma.backbone.auth.authmanager.model.types;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PasswordChangeTypeTest {

    /**
     * {@link PasswordChangeType#getCode()}テスト
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
        assertThat(PasswordChangeType.初期.getCode()).isEqualTo((short) 0);
        assertThat(PasswordChangeType.ユーザーによる変更.getCode()).isEqualTo((short) 1);
        assertThat(PasswordChangeType.管理者によるリセット.getCode()).isEqualTo((short) 2);
        assertThat(PasswordChangeType.機器認証パスワード.getCode()).isEqualTo((short) 3);
    }

    /**
     * {@link PasswordChangeType#getDisplayName()}テスト
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
        assertThat(PasswordChangeType.初期.getDisplayName()).isEqualTo("初期");
        assertThat(PasswordChangeType.ユーザーによる変更.getDisplayName()).isEqualTo("ユーザーによる変更");
        assertThat(PasswordChangeType.管理者によるリセット.getDisplayName()).isEqualTo("管理者によるリセット");
        assertThat(PasswordChangeType.機器認証パスワード.getDisplayName()).isEqualTo("機器認証パスワード");
    }

    /**
     * {@link PasswordChangeType#getValidList()}テスト
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
        List<PasswordChangeType> expected = newArrayList();
        expected.add(PasswordChangeType.初期);
        expected.add(PasswordChangeType.ユーザーによる変更);
        expected.add(PasswordChangeType.管理者によるリセット);
        expected.add(PasswordChangeType.機器認証パスワード);

        // 実行
        List<PasswordChangeType> actual = PasswordChangeType.getValidList();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link PasswordChangeType#getValidValues()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・PasswordChangeType値
     */
    @Test
    @Tag(TestSize.SMALL)
    void getValidValues_test0() {

        // 期待値
        List<PasswordChangeType> list = newArrayList();
        list.add(PasswordChangeType.初期);
        list.add(PasswordChangeType.ユーザーによる変更);
        list.add(PasswordChangeType.管理者によるリセット);
        list.add(PasswordChangeType.機器認証パスワード);
        PasswordChangeType expected[] = list.toArray(new PasswordChangeType[list.size()]);

        // 実行
        PasswordChangeType actual[] = PasswordChangeType.getValidValues();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link PasswordChangeType#codeOf(short)}テスト
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
        assertThat(PasswordChangeType.codeOf((short) 0)).isEqualTo(PasswordChangeType.初期);
        assertThat(PasswordChangeType.codeOf((short) 1)).isEqualTo(PasswordChangeType.ユーザーによる変更);
        assertThat(PasswordChangeType.codeOf((short) 2)).isEqualTo(PasswordChangeType.管理者によるリセット);
        assertThat(PasswordChangeType.codeOf((short) 3)).isEqualTo(PasswordChangeType.機器認証パスワード);
        assertThat(PasswordChangeType.codeOf((short) -1)).isEqualTo(PasswordChangeType.UnKnown);
        assertThat(PasswordChangeType.codeOf((short) 9)).isEqualTo(PasswordChangeType.UnKnown);
    }

    /**
     * {@link PasswordChangeType#is初期()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・PasswordChangeTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is初期_test0() {

        // 実行
        PasswordChangeType actual = PasswordChangeType.初期;

        // 実行 & 結果検証
        assertTrue(actual.is初期());
    }

    /**
     * {@link PasswordChangeType#is初期()}テスト
     *  ●パターン
     *    正常（初期以外）
     *
     *  ●検証事項
     *  ・PasswordChangeTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is初期_test1() {

        // 実行
        PasswordChangeType actual = PasswordChangeType.ユーザーによる変更;

        // 実行 & 結果検証
        assertThat(actual.is初期()).isEqualTo(false);
    }

    /**
     * {@link PasswordChangeType#isユーザーによる変更()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・PasswordChangeTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void isユーザーによる変更_test0() {

        // 実行
        PasswordChangeType actual = PasswordChangeType.ユーザーによる変更;

        // 実行 & 結果検証
        assertTrue(actual.isユーザーによる変更());
    }

    /**
     * {@link PasswordChangeType#isユーザーによる変更()}テスト
     *  ●パターン
     *    正常（ユーザーによる変更以外）
     *
     *  ●検証事項
     *  ・PasswordChangeTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void isユーザーによる変更_test1() {

        // 実行
        PasswordChangeType actual = PasswordChangeType.管理者によるリセット;

        // 実行 & 結果検証
        assertThat(actual.isユーザーによる変更()).isEqualTo(false);
    }

    /**
     * {@link PasswordChangeType#is管理者によるリセット()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・PasswordChangeTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is管理者によるリセット_test0() {

        // 実行
        PasswordChangeType actual = PasswordChangeType.管理者によるリセット;

        // 実行 & 結果検証
        assertTrue(actual.is管理者によるリセット());
    }

    /**
     * {@link PasswordChangeType#is管理者によるリセット()}テスト
     *  ●パターン
     *    正常（管理者によるリセット以外）
     *
     *  ●検証事項
     *  ・PasswordChangeTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is管理者によるリセット_test1() {

        // 実行
        PasswordChangeType actual = PasswordChangeType.機器認証パスワード;

        // 実行 & 結果検証
        assertThat(actual.is管理者によるリセット()).isEqualTo(false);
    }

    /**
     * {@link PasswordChangeType#is機器認証パスワード()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・PasswordChangeTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is機器認証パスワード_test0() {

        // 実行
        PasswordChangeType actual = PasswordChangeType.機器認証パスワード;

        // 実行 & 結果検証
        assertTrue(actual.is機器認証パスワード());
    }

    /**
     * {@link PasswordChangeType#is機器認証パスワード()}テスト
     *  ●パターン
     *    正常（機器認証パスワード以外）
     *    正常
     *
     *  ●検証事項
     *  ・PasswordChangeTypeの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is機器認証パスワード_test1() {

        // 実行
        PasswordChangeType actual = PasswordChangeType.初期;

        // 実行 & 結果検証
        assertThat(actual.is機器認証パスワード()).isEqualTo(false);
    }
}