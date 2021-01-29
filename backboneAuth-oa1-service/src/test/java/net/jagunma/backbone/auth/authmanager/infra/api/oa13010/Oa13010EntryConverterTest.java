package net.jagunma.backbone.auth.authmanager.infra.api.oa13010;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa13010EntryConverterTest {

    /**
     * {@link  Oa13010EntryConverter#of(String, String, Short, Short)}テスト
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
        short signInCauseCode = SignInCause.サインイン.getCode();
        short signInResultCode = SignInResult.成功.getCode();

        // 実行
        Oa13010EntryConverter converter = Oa13010EntryConverter.of(tryIpAddress,
            operatorCode,
            signInCauseCode,
            signInResultCode);


        // 結果検証
        assertTrue(converter instanceof Oa13010EntryConverter);
        assertThat(converter.getTryIpAddress()).isEqualTo(tryIpAddress);
        assertThat(converter.getOperatorCode()).isEqualTo(operatorCode);
        assertThat(converter.getSignInCause()).isEqualTo(SignInCause.codeOf(signInCauseCode));
        assertThat(converter.getSignInResult()).isEqualTo(SignInResult.codeOf(signInResultCode));
    }
}