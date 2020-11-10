package net.jagunma.backbone.auth.authmanager.application.queryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionExportRequest;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchBizTranRoleCompositionValidatorTest {

    // 実行既定値
    private String subSystemCode = SubSystem.販売_畜産.getCode();

    /**
     * {@link SearchBizTranRoleCompositionValidator#validate()}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test0() {

        // 実行値
        BizTranRoleCompositionExportRequest request = new BizTranRoleCompositionExportRequest() {
            @Override
            public String getSubSystemCode() {
                return subSystemCode;
            }
        };

        assertThatCode(()->
            // 実行
            SearchBizTranRoleCompositionValidator.with(request).validate()).doesNotThrowAnyException();
    }

    /**
     * {@link SearchBizTranRoleCompositionValidator#validate()}のテスト
     *  ●パターン
     *    リクエスト不正
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test1() {

        // 実行値
        BizTranRoleCompositionExportRequest request = null;

        assertThatThrownBy(() ->
            // 実行
            SearchBizTranRoleCompositionValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link SearchBizTranRoleCompositionValidator#validate()}のテスト
     *  ●パターン
     *    サブシステム 未セット
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test2() {

        // 実行値
        BizTranRoleCompositionExportRequest request = new BizTranRoleCompositionExportRequest() {
            @Override
            public String getSubSystemCode() {
                return null;
            }
        };

        assertThatThrownBy(() ->
            // 実行
            SearchBizTranRoleCompositionValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("サブシステム");
            });
    }
}