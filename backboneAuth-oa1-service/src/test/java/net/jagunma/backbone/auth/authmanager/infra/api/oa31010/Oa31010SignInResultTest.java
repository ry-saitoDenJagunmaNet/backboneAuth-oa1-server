package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import static org.assertj.core.api.Assertions.assertThat;

import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa31010SignInResultTest {

    /**
     * {@link Oa31010SignInResult#createFrom(SignInResult)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Resultへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void createFrom_test1() {

        // 実行値 ＆ 期待値
        SignInResult signInResult = SignInResult.成功;

        // 実行
        Oa31010SignInResult result = Oa31010SignInResult.createFrom(signInResult);

        // 結果検証
        assertThat(result.getSignInResultCode()).isEqualTo(signInResult.getCode());
        assertThat(result.getSignInResultMessage()).isEqualTo(signInResult.getDisplayName());
    }
}