package net.jagunma.backbone.auth.authmanager.model.types;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AccountLockStatusTest {

    /**
     * {@link AccountLockStatus#getCode()}テスト
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
        assertThat(AccountLockStatus.アンロック.getCode()).isEqualTo((short) 0);
        assertThat(AccountLockStatus.ロック.getCode()).isEqualTo((short) 1);
    }

    /**
     * {@link AccountLockStatus#getDisplayName()}テスト
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
        assertThat(AccountLockStatus.アンロック.getDisplayName()).isEqualTo("アンロック");
        assertThat(AccountLockStatus.ロック.getDisplayName()).isEqualTo("ロック");
    }

    /**
     * {@link AccountLockStatus#getValidList()}テスト
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
        List<AccountLockStatus> expected = newArrayList();
        expected.add(AccountLockStatus.アンロック);
        expected.add(AccountLockStatus.ロック);

        // 実行
        List<AccountLockStatus> actual = AccountLockStatus.getValidList();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link AccountLockStatus#codeOf(short)}テスト
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
        assertThat(AccountLockStatus.codeOf((short) 0)).isEqualTo(AccountLockStatus.アンロック);
        assertThat(AccountLockStatus.codeOf((short) 1)).isEqualTo(AccountLockStatus.ロック);
        assertThat(AccountLockStatus.codeOf((short) -1)).isEqualTo(AccountLockStatus.UnKnown);
        assertThat(AccountLockStatus.codeOf((short) 9)).isEqualTo(AccountLockStatus.UnKnown);
    }
}