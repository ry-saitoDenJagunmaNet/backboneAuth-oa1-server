package net.jagunma.backbone.auth.authmanager.infra.web.ed01000;

import static org.assertj.core.api.Assertions.assertThat;

import net.jagunma.backbone.auth.authmanager.infra.web.ed01000.dto.Ed01000Dto;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Ed01000SignInPresenterTest {

    // 実行既定値
    private final SignInResult signInResultCode = SignInResult.成功;
    private final String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik5HVEZ2ZEstZnl0aEV1";

    // Ed01000 サインイン Presenter（テスト対象クラス）
    private Ed01000SignInPresenter createEd01000SignInPresenter() {
        Ed01000SignInPresenter presenter = new Ed01000SignInPresenter();
        presenter.setSignInResultCode(signInResultCode.getCode());
        presenter.setSignInResultMessage(signInResultCode.getDisplayName());
        presenter.setAccessToken(accessToken);
        return presenter;
    }

    /**
     * {@link Ed01000SignInPresenter#bindTo(Ed01000Dto)}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・dtoへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test0() {

        // 実行値
        Ed01000Dto dto = new Ed01000Dto();

        // テスト対象クラス生成
        Ed01000SignInPresenter presenter = createEd01000SignInPresenter();

        // 期待値
        Ed01000Dto expectedDto = new Ed01000Dto();
        expectedDto.setSignInResultCode(signInResultCode.getCode());
        expectedDto.setSignInResultMessage(signInResultCode.getDisplayName());
        expectedDto.setAccessToken(accessToken);

        // 実行
        presenter.bindTo(dto);

        // 結果検証
        assertThat(dto).usingRecursiveComparison().isEqualTo(expectedDto);
    }
}