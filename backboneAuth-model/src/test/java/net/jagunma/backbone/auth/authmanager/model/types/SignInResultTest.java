package net.jagunma.backbone.auth.authmanager.model.types;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

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
}