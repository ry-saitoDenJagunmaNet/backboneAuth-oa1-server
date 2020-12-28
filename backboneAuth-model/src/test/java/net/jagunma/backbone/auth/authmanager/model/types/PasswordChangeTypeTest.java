package net.jagunma.backbone.auth.authmanager.model.types;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

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
}