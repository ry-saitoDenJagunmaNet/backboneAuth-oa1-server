package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa31010StoreConverterTest {

    /**
     * {@link  Oa31010StoreConverter#with(String, String, SignInCause, SignInResult)}テスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Converterへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void of_test0() {

        // 実行既定値
        String tryIpAddress = "001.001.001.001";
        String operatorCode = "yu001009";
        SignInCause signInCauseCode = SignInCause.サインイン;
        SignInResult signInResultCode = SignInResult.成功;

        // 実行
        Oa31010StoreConverter converter = Oa31010StoreConverter.with(tryIpAddress,
            operatorCode,
            signInCauseCode,
            signInResultCode);

        // 結果検証
        assertTrue(converter instanceof Oa31010StoreConverter);
        assertThat(converter.getTryIpAddress()).isEqualTo(tryIpAddress);
        assertThat(converter.getOperatorCode()).isEqualTo(operatorCode);
        assertThat(converter.getSignInCause()).isEqualTo(signInCauseCode);
        assertThat(converter.getSignInResult()).isEqualTo(signInResultCode);
    }
}