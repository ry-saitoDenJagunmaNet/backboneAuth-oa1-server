package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInTraceCommand.SignInTraceEntryRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class EntrySignInTraceTest {

    // 実行既定値
    private String tryIpAddress = "001.001.001.001";
    private  String operatorCode = "yu001009";
    private SignInCause signInCause = SignInCause.サインイン;
    private SignInResult signInResult = SignInResult.成功;

    // 検証値
    private SignInTrace actualSignInTrace;

    // サインイン証跡登録サービス作成（テスト対象クラス）
    private EntrySignInTrace createEntrySignInTrace() {
        // サインイン証跡格納リポジトリのスタブ
        SignInTraceRepositoryForStore signInTraceRepositoryForStore = new SignInTraceRepositoryForStore() {
            @Override
            public SignInTrace insert(SignInTrace signInTrace) {
                actualSignInTrace = signInTrace;
                return null;
            }
        };
        return new EntrySignInTrace(signInTraceRepositoryForStore);
    }
    // サインイン証跡登録サービス Request作成
    private SignInTraceEntryRequest createSignInTraceEntryRequest() {
        return new SignInTraceEntryRequest() {
            @Override
            public String getTryIpAddress() {
                return tryIpAddress;
            }
            @Override
            public String getOperatorCode() {
                return operatorCode;
            }
            @Override
            public SignInCause getSignInCause() {
                return signInCause;
            }
            @Override
            public SignInResult getSignInResult() {
                return signInResult;
            }
        };
    }

    /**
     * {@link EntrySignInTrace#execute(SignInTraceEntryRequest)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・ForStoreリポジトリの登録引数
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test0() {

        // テスト対象クラス生成
        EntrySignInTrace entrySignInTrace = createEntrySignInTrace();

        // 実行値
        SignInTraceEntryRequest request = createSignInTraceEntryRequest();

        assertThatCode(() ->
            // 実行
            entrySignInTrace.execute(request)).doesNotThrowAnyException();

        // 期待値
        SignInTrace expectedSignInTrace = SignInTrace.createFrom(
            null,
            LocalDateTime.now(),
            tryIpAddress,
            operatorCode,
            signInCause.getCode(),
            signInResult.getCode(),
            null,
            null);

        // 結果検証
        assertThat(actualSignInTrace.getSignInTraceId()).isEqualTo(expectedSignInTrace.getSignInTraceId());
        assertThat(actualSignInTrace.getTryDateTime().toLocalDate()).isEqualTo(expectedSignInTrace.getTryDateTime().toLocalDate());
        assertThat(actualSignInTrace.getTryIpAddress()).isEqualTo(expectedSignInTrace.getTryIpAddress());
        assertThat(actualSignInTrace.getOperatorCode()).isEqualTo(expectedSignInTrace.getOperatorCode());
        assertThat(actualSignInTrace.getSignInCause()).isEqualTo(expectedSignInTrace.getSignInCause());
        assertThat(actualSignInTrace.getSignInResult()).isEqualTo(expectedSignInTrace.getSignInResult());
        assertThat(actualSignInTrace.getRecordVersion()).isEqualTo(expectedSignInTrace.getRecordVersion());
        assertThat(actualSignInTrace.getOperator()).usingRecursiveComparison().isEqualTo(expectedSignInTrace.getOperator());
    }
}