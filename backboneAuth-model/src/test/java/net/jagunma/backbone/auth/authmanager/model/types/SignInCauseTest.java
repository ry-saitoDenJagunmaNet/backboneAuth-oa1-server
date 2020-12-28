package net.jagunma.backbone.auth.authmanager.model.types;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SignInCauseTest {

    /**
     * {@link SignInCause#getCode()}テスト
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
        assertThat(SignInCause.サインイン.getCode()).isEqualTo((short) 1);
        assertThat(SignInCause.継続サインイン.getCode()).isEqualTo((short) 2);
    }

    /**
     * {@link SignInCause#getDisplayName()}テスト
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
        assertThat(SignInCause.サインイン.getDisplayName()).isEqualTo("サインイン");
        assertThat(SignInCause.継続サインイン.getDisplayName()).isEqualTo("継続サインイン");
    }

    /**
     * {@link SignInCause#getValidList()}テスト
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
        List<SignInCause> expected = newArrayList();
        expected.add(SignInCause.サインイン);
        expected.add(SignInCause.継続サインイン);

        // 実行
        List<SignInCause> actual = SignInCause.getValidList();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link SignInCause#codeOf(short)}テスト
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
        assertThat(SignInCause.codeOf((short) 1)).isEqualTo(SignInCause.サインイン);
        assertThat(SignInCause.codeOf((short) 2)).isEqualTo(SignInCause.継続サインイン);
        assertThat(SignInCause.codeOf((short) -1)).isEqualTo(SignInCause.UnKnown);
        assertThat(SignInCause.codeOf((short) 9)).isEqualTo(SignInCause.UnKnown);
    }
}