package net.jagunma.backbone.auth.authmanager.infra.api.oa13010;

import static org.assertj.core.api.Assertions.assertThat;

import net.jagunma.backbone.auth.authmanager.application.commandService.EntrySignInTrace;
import net.jagunma.backbone.auth.authmanager.application.queryService.Authentication;
import net.jagunma.backbone.auth.authmanager.application.usecase.authenticationCommand.AuthenticationRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.authenticationCommand.AuthenticationResponse;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInTraceCommand.SignInTraceEntryRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraces;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class Oa13010ControllerTest {

    // 実行既定値
    private String clientIpaddress = "001.001.001.001";
    private String operatorCode = "yu001009";
    private String password = "password1234";
    SignInResult authentication_execute_signInTrace = SignInResult.成功;
    private String runtimeException = "runtimeException";

    // 検証値
    AuthenticationRequest actualAuthenticationRequest;
    SignInTraceEntryRequest actualSignInTraceEntryRequest;

    // Oa13010 サインイン Arg 作成
    private Oa13010SignInArg createOa13010SignInArg() {
        Oa13010SignInArg signInArg = new Oa13010SignInArg();
        signInArg.setClientIpaddress(clientIpaddress);
        signInArg.setOperatorCode(operatorCode);
        signInArg.setPassword(password);
        return signInArg;
    }

    // 業務オペレーター認証クラス作成（テスト対象クラス）
    private Oa13010Controller createOa13010Controller() {
        // オペレーター検索リポジトリのスタブ
        OperatorRepository operatorRepository = new OperatorRepository() {
            @Override
            public Operator findOneById(Long operatorId) {
                return null;
            }
            @Override
            public Operator findOneByCode(String operatorCode) {
                return null;
            }
            @Override
            public boolean existsById(Long operatorId) {
                return false;
            }
            @Override
            public boolean existsByCode(String operatorCode) {
                return false;
            }
            @Override
            public boolean existsBy(OperatorCriteria operatorCriteria) {
                return false;
            }
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                return null;
            }
        };
        // パスワード履歴群検索リポジトリのスタブ
        PasswordHistoryRepository passwordHistoryRepository = new PasswordHistoryRepository() {
            @Override
            public PasswordHistory latestOneByOperatorId(Long operatorId) {
                return null;
            }
            @Override
            public PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria, Orders orders) {
                return null;
            }
        };
        // アカウントロック検索リポジトリのスタブ
        AccountLockRepository accountLockRepository = new AccountLockRepository() {
            @Override
            public AccountLock findOneById(Long accountLockId) {
                return null;
            }
            @Override
            public boolean existsByOperatorId(Long operatorId) {
                return false;
            }
            @Override
            public AccountLock latestOneByOperatorId(Long operatorId) {
                return null;
            }
            @Override
            public AccountLocks selectBy(AccountLockCriteria accountLockrCriteria, Orders orders) {
                return null;
            }
        };
        // 認証サービスのスタブ
        Authentication authentication = new Authentication(operatorRepository,passwordHistoryRepository,accountLockRepository) {
            public void execute(AuthenticationRequest request, AuthenticationResponse response) {
                actualAuthenticationRequest = request;
                // request.getPassword() == runtimeException の場合：RuntimeException を発生させる
                if (request.getPassword().equals(runtimeException)) {
                    throw new RuntimeException();
                }
                response.setSignInResult(authentication_execute_signInTrace);
            };
        };
        // サインイン証跡格納リポジトリのスタブ
        SignInTraceRepositoryForStore signInTraceRepositoryForStore = new SignInTraceRepositoryForStore() {
            @Override
            public SignInTrace insert(SignInTrace signInTrace) {
                return null;
            }
        };
        // サインイン証跡リポジトリのスタブ
        SignInTraceRepository signInTraceRepository = new SignInTraceRepository() {
            @Override
            public SignInTrace findOneById(Long signInTraceId) {
                return null;
            }
            @Override
            public SignInTraces selectBy(SignInTraceCriteria signInTraceCriteria, Orders orders) {
                return null;
            }
        };
        // アカウントロック格納リポジトリのスタブ
        AccountLockRepositoryForStore accountLockRepositoryForStore = new AccountLockRepositoryForStore() {
            @Override
            public AccountLock insert(AccountLock AccountLock) {
                return null;
            }
        };
        // サインイン証跡登録サービスのスタブ
        EntrySignInTrace entrySignInTrace = new EntrySignInTrace(signInTraceRepositoryForStore, signInTraceRepository, accountLockRepositoryForStore) {
            public void execute(SignInTraceEntryRequest request) {
                actualSignInTraceEntryRequest = request;
            }
        };
        return new Oa13010Controller(authentication, entrySignInTrace);
    }

    /**
     * {@link Oa13010Controller#signIn(Oa13010SignInArg)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void signIn_test0() {

        // テスト対象クラス生成
        Oa13010Controller controller = createOa13010Controller();

        // 実行値
        Oa13010SignInArg signInArg = createOa13010SignInArg();

        // 期待値
        SignInCause signInCause = SignInCause.サインイン;
        SignInResult signInTrace = authentication_execute_signInTrace;
        Oa13010SignInResult oa13010SignInResult = Oa13010SignInResult.createFrom(signInTrace);
        ResponseEntity<Oa13010SignInResult> expected = new ResponseEntity<>(oa13010SignInResult, HttpStatus.OK);
        AuthenticationRequest expectedAuthenticationRequest = Oa13010AuthenticationConverter.with(operatorCode, password);
        SignInTraceEntryRequest expectedSignInTraceEntryRequest = Oa13010EntryConverter.with(
            clientIpaddress,
            operatorCode,
            signInCause,
            signInTrace);

        // 実行
        ResponseEntity<Oa13010SignInResult> actual = controller.signIn(signInArg);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceEntryRequest).usingRecursiveComparison().isEqualTo(expectedSignInTraceEntryRequest);
    }

    /**
     * {@link Oa13010Controller#signIn(Oa13010SignInArg)}のテスト
     *  ●パターン
     *    正常（サインイン失敗）
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void signIn_test1() {

        // テスト対象クラス生成
        Oa13010Controller controller = createOa13010Controller();

        // 実行値
        authentication_execute_signInTrace = SignInResult.拒否_アカウントロック中;
        Oa13010SignInArg signInArg = createOa13010SignInArg();

        // 期待値
        SignInCause signInCause = SignInCause.サインイン;
        SignInResult signInTrace = authentication_execute_signInTrace;
        Oa13010SignInResult oa13010SignInResult = Oa13010SignInResult.createFrom(signInTrace);
        ResponseEntity<Oa13010SignInResult> expected = new ResponseEntity<>(oa13010SignInResult, HttpStatus.OK);
        AuthenticationRequest expectedAuthenticationRequest = Oa13010AuthenticationConverter.with(operatorCode, password);
        SignInTraceEntryRequest expectedSignInTraceEntryRequest = Oa13010EntryConverter.with(
            clientIpaddress,
            operatorCode,
            signInCause,
            signInTrace);

        // 実行
        ResponseEntity<Oa13010SignInResult> actual = controller.signIn(signInArg);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceEntryRequest).usingRecursiveComparison().isEqualTo(expectedSignInTraceEntryRequest);
    }

    /**
     * {@link Oa13010Controller#signIn(Oa13010SignInArg)}のテスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void signIn_test2() {

        // テスト対象クラス生成
        Oa13010Controller controller = createOa13010Controller();

        // 実行値
        password = runtimeException;
        Oa13010SignInArg signInArg = createOa13010SignInArg();

        // 期待値
        SignInResult signInTrace = authentication_execute_signInTrace;
        ResponseEntity<Oa13010SignInResult> expected = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        AuthenticationRequest expectedAuthenticationRequest = Oa13010AuthenticationConverter.with(operatorCode, password);

        // 実行
        ResponseEntity<Oa13010SignInResult> actual = controller.signIn(signInArg);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceEntryRequest).isNull();
    }

    /**
     * {@link Oa13010Controller#continuedSignIn(Oa13010SignInArg)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void continuedSignIn_test0() {

        // テスト対象クラス生成
        Oa13010Controller controller = createOa13010Controller();

        // 実行値
        Oa13010SignInArg signInArg = createOa13010SignInArg();

        // 期待値
        SignInCause signInCause = SignInCause.継続サインイン;
        SignInResult signInTrace = authentication_execute_signInTrace;
        Oa13010SignInResult oa13010SignInResult = Oa13010SignInResult.createFrom(signInTrace);
        ResponseEntity<Oa13010SignInResult> expected = new ResponseEntity<>(oa13010SignInResult, HttpStatus.OK);
        AuthenticationRequest expectedAuthenticationRequest = Oa13010AuthenticationConverter.with(operatorCode, password);
        SignInTraceEntryRequest expectedSignInTraceEntryRequest = Oa13010EntryConverter.with(
            clientIpaddress,
            operatorCode,
            signInCause,
            signInTrace);

        // 実行
        ResponseEntity<Oa13010SignInResult> actual = controller.continuedSignIn(signInArg);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceEntryRequest).usingRecursiveComparison().isEqualTo(expectedSignInTraceEntryRequest);
    }

    /**
     * {@link Oa13010Controller#continuedSignIn(Oa13010SignInArg)}のテスト
     *  ●パターン
     *    正常（サインイン失敗）
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void continuedSignIn_test1() {

        // テスト対象クラス生成
        Oa13010Controller controller = createOa13010Controller();

        // 実行値
        authentication_execute_signInTrace = SignInResult.失敗_パスワード誤り;
        Oa13010SignInArg signInArg = createOa13010SignInArg();

        // 期待値
        SignInCause signInCause = SignInCause.継続サインイン;
        SignInResult signInTrace = authentication_execute_signInTrace;
        Oa13010SignInResult oa13010SignInResult = Oa13010SignInResult.createFrom(signInTrace);
        ResponseEntity<Oa13010SignInResult> expected = new ResponseEntity<>(oa13010SignInResult, HttpStatus.OK);
        AuthenticationRequest expectedAuthenticationRequest = Oa13010AuthenticationConverter.with(operatorCode, password);
        SignInTraceEntryRequest expectedSignInTraceEntryRequest = Oa13010EntryConverter.with(
            clientIpaddress,
            operatorCode,
            signInCause,
            signInTrace);

        // 実行
        ResponseEntity<Oa13010SignInResult> actual = controller.continuedSignIn(signInArg);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceEntryRequest).usingRecursiveComparison().isEqualTo(expectedSignInTraceEntryRequest);
    }

    /**
     * {@link Oa13010Controller#continuedSignIn(Oa13010SignInArg)}のテスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void continuedSignIn_test2() {

        // テスト対象クラス生成
        Oa13010Controller controller = createOa13010Controller();

        // 実行値
        password = runtimeException;
        Oa13010SignInArg signInArg = createOa13010SignInArg();

        // 期待値
        SignInResult signInTrace = authentication_execute_signInTrace;
        ResponseEntity<Oa13010SignInResult> expected = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        AuthenticationRequest expectedAuthenticationRequest = Oa13010AuthenticationConverter.with(operatorCode, password);

        // 実行
        ResponseEntity<Oa13010SignInResult> actual = controller.continuedSignIn(signInArg);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceEntryRequest).isNull();
    }
}