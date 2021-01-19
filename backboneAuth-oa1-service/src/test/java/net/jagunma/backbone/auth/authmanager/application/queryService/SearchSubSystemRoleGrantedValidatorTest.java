package net.jagunma.backbone.auth.authmanager.application.queryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedSearchRequest;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchSubSystemRoleGrantedValidatorTest {

    // 実行既定値
    private Long targetOperatorId = 123456L;
    private Long signInOperatorId = 234567L;

    private SubSystemRoleGrantedSearchRequest createRequest() {
        return new SubSystemRoleGrantedSearchRequest() {
            @Override
            public Long getTargetOperatorId() {
                return targetOperatorId;
            }
            @Override
            public Long getSignInOperatorId() {
                return signInOperatorId;
            }
        };
    }

    /**
     * {@link SearchSubSystemRoleGrantedValidator#validate()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test() {
        // 実行値
        SubSystemRoleGrantedSearchRequest request = createRequest();

        assertThatCode(() ->
            // 実行
            SearchSubSystemRoleGrantedValidator.with(request).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link SearchSubSystemRoleGrantedValidator#validate()}テスト
     *  ●パターン
     *    リクエスト不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test01() {
        // 実行値
        SubSystemRoleGrantedSearchRequest request = null;

        assertThatThrownBy(() ->
            // 実行
            SearchSubSystemRoleGrantedValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link SearchSubSystemRoleGrantedValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  ターゲットオペレーターID
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test02() {
        // 実行値
        targetOperatorId = null;
        SubSystemRoleGrantedSearchRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            SearchSubSystemRoleGrantedValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("ターゲットオペレーターID");
            });
    }

    /**
     * {@link SearchSubSystemRoleGrantedValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  サインインオペレーターID
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test03() {
        // 実行値
        signInOperatorId = null;
        SubSystemRoleGrantedSearchRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            SearchSubSystemRoleGrantedValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("サインインオペレーターID");
            });
    }
}