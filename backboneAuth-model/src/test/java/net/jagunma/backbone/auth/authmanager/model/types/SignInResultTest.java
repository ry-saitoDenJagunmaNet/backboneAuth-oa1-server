package net.jagunma.backbone.auth.authmanager.model.types;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SignInResultTest {

    /**
     * {@link SignInResult#getCode()}テスト
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
        assertThat(SignInResult.成功.getCode()).isEqualTo((short) 0);
        assertThat(SignInResult.失敗_存在しないオペレーター.getCode()).isEqualTo((short) 1);
        assertThat(SignInResult.失敗_パスワード誤り.getCode()).isEqualTo((short) 2);
        assertThat(SignInResult.遮断_IPアドレス範囲外.getCode()).isEqualTo((short) 3);
        assertThat(SignInResult.拒否_アカウントロック中.getCode()).isEqualTo((short) 4);
        assertThat(SignInResult.拒否_有効期限範囲外.getCode()).isEqualTo((short) 5);
    }

    /**
     * {@link SignInResult#getDisplayName()}テスト
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
        assertThat(SignInResult.成功.getDisplayName()).isEqualTo("成功");
        assertThat(SignInResult.失敗_存在しないオペレーター.getDisplayName()).isEqualTo("失敗（存在しないオペレーター）");
        assertThat(SignInResult.失敗_パスワード誤り.getDisplayName()).isEqualTo("失敗（パスワード誤り）");
        assertThat(SignInResult.遮断_IPアドレス範囲外.getDisplayName()).isEqualTo("遮断（IPアドレス範囲外）");
        assertThat(SignInResult.拒否_アカウントロック中.getDisplayName()).isEqualTo("拒否（アカウントロック中）");
        assertThat(SignInResult.拒否_有効期限範囲外.getDisplayName()).isEqualTo("拒否（有効期限範囲外）");
    }

    /**
     * {@link SignInResult#getValidList()}テスト
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
        List<SignInResult> expected = newArrayList();
        expected.add(SignInResult.成功);
        expected.add(SignInResult.失敗_存在しないオペレーター);
        expected.add(SignInResult.失敗_パスワード誤り);
        expected.add(SignInResult.遮断_IPアドレス範囲外);
        expected.add(SignInResult.拒否_アカウントロック中);
        expected.add(SignInResult.拒否_有効期限範囲外);

        // 実行
        List<SignInResult> actual = SignInResult.getValidList();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link SignInResult#getValidValues()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・SignInResult値
     */
    @Test
    @Tag(TestSize.SMALL)
    void getValidValues_test0() {

        // 期待値
        List<SignInResult> list = newArrayList();
        list.add(SignInResult.成功);
        list.add(SignInResult.失敗_存在しないオペレーター);
        list.add(SignInResult.失敗_パスワード誤り);
        list.add(SignInResult.遮断_IPアドレス範囲外);
        list.add(SignInResult.拒否_アカウントロック中);
        list.add(SignInResult.拒否_有効期限範囲外);
        SignInResult expected[] = list.toArray(new SignInResult[list.size()]);

        // 実行
        SignInResult actual[] = SignInResult.getValidValues();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link SignInResult#codeOf(short)}テスト
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
        assertThat(SignInResult.codeOf((short) 0)).isEqualTo(SignInResult.成功);
        assertThat(SignInResult.codeOf((short) 1)).isEqualTo(SignInResult.失敗_存在しないオペレーター);
        assertThat(SignInResult.codeOf((short) 2)).isEqualTo(SignInResult.失敗_パスワード誤り);
        assertThat(SignInResult.codeOf((short) 3)).isEqualTo(SignInResult.遮断_IPアドレス範囲外);
        assertThat(SignInResult.codeOf((short) 4)).isEqualTo(SignInResult.拒否_アカウントロック中);
        assertThat(SignInResult.codeOf((short) 5)).isEqualTo(SignInResult.拒否_有効期限範囲外);
        assertThat(SignInResult.codeOf((short) -1)).isEqualTo(SignInResult.UnKnown);
        assertThat(SignInResult.codeOf((short) 9)).isEqualTo(SignInResult.UnKnown);
    }

    /**
     * {@link SignInResult#is成功()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・SignInResultの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is成功_test0() {

        // 実行
        SignInResult actual = SignInResult.成功;

        // 実行 & 結果検証
        assertTrue(actual.is成功());
    }

    /**
     * {@link SignInResult#is成功()}テスト
     *  ●パターン
     *    正常（成功以外）
     *
     *  ●検証事項
     *  ・SignInResultの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is成功_test1() {

        // 実行
        SignInResult actual = SignInResult.失敗_存在しないオペレーター;

        // 実行 & 結果検証
        assertThat(actual.is成功()).isEqualTo(false);;
    }

    /**
     * {@link SignInResult#is失敗_パスワード誤り()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・SignInResultの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is失敗_パスワード誤り_test0() {

        // 実行
        SignInResult actual = SignInResult.失敗_パスワード誤り;

        // 実行 & 結果検証
        assertTrue(actual.is失敗_パスワード誤り());
    }

    /**
     * {@link SignInResult#is失敗_パスワード誤り()}テスト
     *  ●パターン
     *    正常（失敗_パスワード誤り以外）
     *
     *  ●検証事項
     *  ・SignInResultの判定
     */
    @Test
    @Tag(TestSize.SMALL)
    void is失敗_パスワード誤り_test1() {

        // 実行
        SignInResult actual = SignInResult.遮断_IPアドレス範囲外;

        // 実行 & 結果検証
        assertThat(actual.is失敗_パスワード誤り()).isEqualTo(false);
    }
}