package net.jagunma.backbone.auth.authmanager.application.queryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedCopyRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedCopyRequestAssignRole;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CopyBizTranRoleGrantedValidatorTest {

    // 実行既定値
    private Long signInOperatorId = 987654L;
    private Long selectedOperatorId = 876543L;

    private BizTranRoleGrantedCopyRequest createRequest() {
        return new BizTranRoleGrantedCopyRequest() {
            @Override
            public Long getSignInOperatorId() {
                return signInOperatorId;
            }
            @Override
            public Long getSelectedOperatorId() {
                return selectedOperatorId;
            }
            @Override
            public List<BizTranRoleGrantedCopyRequestAssignRole> getAssignRoleList() {
                return null;
            }
        };
    }

    /**
     * {@link CopyBizTranRoleGrantedValidator#validate()}テスト
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
        BizTranRoleGrantedCopyRequest request = createRequest();

        assertThatCode(() ->
            // 実行
            CopyBizTranRoleGrantedValidator.with(request).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link CopyBizTranRoleGrantedValidator#validate()}テスト
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
        BizTranRoleGrantedCopyRequest request = null;

        assertThatThrownBy(() ->
            // 実行
            CopyBizTranRoleGrantedValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link CopyBizTranRoleGrantedValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  サインインオペレーターID
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test02() {
        // 実行値
        signInOperatorId = null;
        BizTranRoleGrantedCopyRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            CopyBizTranRoleGrantedValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("サインインオペレーターID");
            });
    }

    /**
     * {@link CopyBizTranRoleGrantedValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  選択オペレーターID
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test03() {
        // 実行値
        selectedOperatorId = null;
        BizTranRoleGrantedCopyRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            CopyBizTranRoleGrantedValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("選択オペレーターID");
            });
    }
}