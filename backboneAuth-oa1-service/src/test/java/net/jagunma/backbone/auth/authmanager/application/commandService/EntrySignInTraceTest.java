package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInTraceCommand.SignInTraceEntryRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraces;
import net.jagunma.backbone.auth.authmanager.model.types.AccountLockStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.common.ddd.model.orders.Order;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class EntrySignInTraceTest {

    // 実行既定値
    private final String tryIpAddress = "001.001.001.001";
    private final Long operatorId = 18L;
    private final String operatorCode = "yu001009";
    private final SignInCause signInCause = SignInCause.サインイン;
    private final SignInResult signInResult = SignInResult.成功;
    private SignInTraces signInTraces = SignInTraces.createFrom(newArrayList());
    private final Long signInTraceId = 1L;

    // 検証値
    private SignInTrace actualSignInTrace;
    private SignInTraceCriteria actualSignInTraceCriteria;
    private Orders actualOrders;
    private AccountLock actualAccountLock = null;

    // サインイン証跡リストデータ作成
    private List<SignInTrace> createSignInTraceListForPasswordError(int count) {
        List<SignInTrace> list = newArrayList();
        LocalDateTime occurredDateTime = LocalDateTime.now();
        list.add(SignInTrace.createFrom(
            (long) 1,
            occurredDateTime,
            "001.001.001.001",
            operatorCode,
            SignInCause.サインイン,
            SignInResult.成功,
            1,
            createOperator()));
        for (int i = 0; i < count; i++) {
            list.add(SignInTrace.createFrom(
                (long) i + 2,
                occurredDateTime.plusMinutes(i+1),
                "001.001.001.001",
                operatorCode,
                SignInCause.サインイン,
                SignInResult.失敗_パスワード誤り,
                1,
                createOperator()));
        }
        return list;
    }
    // オペレーターデータ作成
    private Operator createOperator() {
        return Operator.createFrom(
            operatorId,
            operatorCode,
            "",
            "",
            null,
            null,
            null,
            null,
            "",
            null,
            "",
            null,
            null,
            null);
    }

    // サインイン証跡登録サービス作成（テスト対象クラス）
    private EntrySignInTrace createEntrySignInTrace() {
        // サインイン証跡格納リポジトリのスタブ
        SignInTraceRepositoryForStore signInTraceRepositoryForStore = new SignInTraceRepositoryForStore() {
            @Override
            public SignInTrace insert(SignInTrace signInTrace) {
                actualSignInTrace = signInTrace;
                return SignInTrace.createFrom(
                    signInTraceId,
                    signInTrace.getTryDateTime(),
                    signInTrace.getTryIpAddress(),
                    signInTrace.getOperatorCode(),
                    signInTrace.getSignInCause(),
                    signInTrace.getSignInResult(),
                    1,
                    createOperator());
            }
        };
        // サインイン証跡リポジトリのスタブ
        SignInTraceRepository signInTraceRepository = new SignInTraceRepository() {
            @Override
            public SignInTraces selectBy(SignInTraceCriteria signInTraceCriteria, Orders orders) {
                actualSignInTraceCriteria = signInTraceCriteria;
                actualOrders = orders;
                return SignInTraces.createFrom(signInTraces.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList()));
            }
            @Override
            public SignInTrace findOneById(Long signInTraceId) {
                return null;
            }
        };
        // アカウントロック格納リポジトリのスタブ
        AccountLockRepositoryForStore accountLockRepositoryForStore = new AccountLockRepositoryForStore() {
            @Override
            public AccountLock insert(AccountLock accountLock) {
                actualAccountLock = accountLock;
                return null;
            }
        };
        return new EntrySignInTrace(signInTraceRepositoryForStore, signInTraceRepository, accountLockRepositoryForStore);
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
            signInCause,
            signInResult,
            null,
            null);
        SignInTraceCriteria expectedSignInTraceCriteria = new SignInTraceCriteria();
        expectedSignInTraceCriteria.getOperatorCodeCriteria().setEqualTo(operatorCode);
        Orders expectedOrders = Orders.empty().addOrder("tryDateTime", Order.DESC);

        // 結果検証
        assertThat(actualSignInTrace.getSignInTraceId()).isEqualTo(expectedSignInTrace.getSignInTraceId());
        assertThat(actualSignInTrace.getTryDateTime().toLocalDate()).isEqualTo(expectedSignInTrace.getTryDateTime().toLocalDate());
        assertThat(actualSignInTrace.getTryIpAddress()).isEqualTo(expectedSignInTrace.getTryIpAddress());
        assertThat(actualSignInTrace.getOperatorCode()).isEqualTo(expectedSignInTrace.getOperatorCode());
        assertThat(actualSignInTrace.getSignInCause()).isEqualTo(expectedSignInTrace.getSignInCause());
        assertThat(actualSignInTrace.getSignInResult()).isEqualTo(expectedSignInTrace.getSignInResult());
        assertThat(actualSignInTrace.getRecordVersion()).isEqualTo(expectedSignInTrace.getRecordVersion());
        assertThat(actualSignInTrace.getOperator()).usingRecursiveComparison().isEqualTo(expectedSignInTrace.getOperator());
        assertThat(actualSignInTraceCriteria.toString()).isEqualTo(expectedSignInTraceCriteria.toString());
        assertThat(actualOrders).usingRecursiveComparison().isEqualTo(expectedOrders);
        assertThat(actualAccountLock).isNull();
    }

    /**
     * {@link EntrySignInTrace#execute(SignInTraceEntryRequest)}テスト
     *  ●パターン
     *    正常（3回目の失敗 パスワード誤りでアカウントロックにInsert）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・アカウントロックForStoreリポジトリの登録引数
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test1() {
        // テスト対象クラス生成
        EntrySignInTrace entrySignInTrace = createEntrySignInTrace();

        // 実行値
        SignInTraceEntryRequest request = createSignInTraceEntryRequest();
        signInTraces = SignInTraces.createFrom(createSignInTraceListForPasswordError(3));

        assertThatCode(() ->
            // 実行
            entrySignInTrace.execute(request)).doesNotThrowAnyException();

        // 期待値
        SignInTrace expectedSignInTrace = SignInTrace.createFrom(
            null,
            LocalDateTime.now(),
            tryIpAddress,
            operatorCode,
            signInCause,
            signInResult,
            null,
            null);
        AccountLock expectedAccountLock = AccountLock.createFrom(
            null,
            operatorId,
            actualSignInTrace.getTryDateTime(),
            AccountLockStatus.ロック,
            null,
            null);

        // 結果検証
        assertThat(actualAccountLock).usingRecursiveComparison().isEqualTo(expectedAccountLock);
   }

    /**
     * {@link EntrySignInTrace#execute(SignInTraceEntryRequest)}テスト
     *  ●パターン
     *    正常（2回目の失敗 パスワード誤り。アカウントロックにInsert無し）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・アカウントロックForStoreリポジトリの登録引数
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test2() {
        // テスト対象クラス生成
        EntrySignInTrace entrySignInTrace = createEntrySignInTrace();

        // 実行値
        SignInTraceEntryRequest request = createSignInTraceEntryRequest();
        signInTraces = SignInTraces.createFrom(createSignInTraceListForPasswordError(2));

        assertThatCode(() ->
            // 実行
            entrySignInTrace.execute(request)).doesNotThrowAnyException();

        // 期待値
        SignInTrace expectedSignInTrace = SignInTrace.createFrom(
            null,
            LocalDateTime.now(),
            tryIpAddress,
            operatorCode,
            signInCause,
            signInResult,
            null,
            null);

        // 結果検証
        assertThat(actualAccountLock).isNull();
    }
}