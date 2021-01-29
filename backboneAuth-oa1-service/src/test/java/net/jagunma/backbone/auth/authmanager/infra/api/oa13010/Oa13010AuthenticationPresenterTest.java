package net.jagunma.backbone.auth.authmanager.infra.api.oa13010;

import static org.assertj.core.api.Assertions.assertThat;

import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa13010AuthenticationPresenterTest {

    /**
     * {@link Oa13010AuthenticationPresenter} のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・presenterのＧｅｔ、Ｓｅｔ
     */
    @Test
    @Tag(TestSize.SMALL)
    void test0() {

        // テスト対象クラス生成
        Oa13010AuthenticationPresenter actualPresenter = new Oa13010AuthenticationPresenter();

        // 実行値
        SignInResult signInResult = SignInResult.成功;
        actualPresenter.setSignInResult(signInResult);

        // 結果検証
        assertThat(actualPresenter.getSignInResult()).isEqualTo(signInResult);
    }
}