package net.jagunma.backbone.auth.authmanager.application.queryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchOperatorValidatorTest {

    /**
     * {@link SearchOperatorValidator#validate()}のテスト
     *  ●パターン
     *    リクエストがnullのテスト
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test0() {

        // 実行値
        OperatorSearchRequest request = null;

        assertThatCode(()->
            // 実行
            SearchOperatorValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link SearchOperatorValidator#validate()}のテスト
     *  ●パターン
     *    有効期限開始の範囲指定不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_test1() {

        // 実行値
//        OperatorSearchRequest request = null;
//
//        assertThatThrownBy(()->
//            // 実行
//            SearchOperatorValidator.with(request).validate())
//                .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
//                    // 結果検証
//                    assertThat(e.getMessageCode()).isEqualTo("EOA13007");
//                    assertThat(e.getArgs()).containsSequence("有効期限開始");
//                });
    }
}