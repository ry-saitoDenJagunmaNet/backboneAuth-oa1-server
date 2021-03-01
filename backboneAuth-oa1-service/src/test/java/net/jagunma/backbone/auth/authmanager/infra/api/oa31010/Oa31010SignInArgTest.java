package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import static org.assertj.core.api.Assertions.assertThat;

import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa31010SignInArgTest {

    // 実行既定値
    private String operatorCode = "yu001009";
    private String password = "password12345";
    private String clientIpaddress = "001.001.001.001";


    /**
     * {@link Oa31010Controller}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Setしたい値のＧｅｔ
     */
    @Test
    @Tag(TestSize.SMALL)
    void test0() {

        // テスト対象クラス生成
        Oa31010SignInArg oa31010SignInArg = new Oa31010SignInArg();

        // 実行値
        oa31010SignInArg.setOperatorCode(operatorCode);
        oa31010SignInArg.setPassword(password);
        oa31010SignInArg.setClientIpaddress(clientIpaddress);

        // 結果検証
        assertThat(oa31010SignInArg.getOperatorCode()).isEqualTo(operatorCode);
        assertThat(oa31010SignInArg.getPassword()).isEqualTo(password);
        assertThat(oa31010SignInArg.getClientIpaddress()).isEqualTo(clientIpaddress);
    }
}