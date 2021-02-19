package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import static org.assertj.core.api.Assertions.assertThat;

import net.jagunma.backbone.auth.authmanager.application.commandService.StoreSignInTrace;
import net.jagunma.backbone.auth.authmanager.application.queryService.Authentication;
import net.jagunma.backbone.auth.authmanager.application.usecase.authenticationCommand.AuthenticationRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.authenticationCommand.AuthenticationResponse;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInTraceCommand.SignInTraceStoreRequest;
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

class Oa31010ControllerTest {

    // 実行既定値
    private String clientIpaddress = "001.001.001.001";
    private String operatorCode = "yu001009";
    private String password = "password1234";
    SignInResult authentication_execute_signInTrace = SignInResult.成功;
    private String runtimeException = "runtimeException";

    // 検証値
    AuthenticationRequest actualAuthenticationRequest;
    SignInTraceStoreRequest actualSignInTraceStoreRequest;

    // Oa31010 サインイン Arg 作成
    private Oa31010SignInArg createOa31010SignInArg() {
        Oa31010SignInArg signInArg = new Oa31010SignInArg();
        signInArg.setClientIpaddress(clientIpaddress);
        signInArg.setOperatorCode(operatorCode);
        signInArg.setPassword(password);
        return signInArg;
    }

    // 業務オペレーター認証クラス作成（テスト対象クラス）
    private Oa31010Controller createOa31010Controller() {
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
        StoreSignInTrace storeSignInTrace = new StoreSignInTrace(signInTraceRepositoryForStore, signInTraceRepository, accountLockRepositoryForStore) {
            public void execute(SignInTraceStoreRequest request) {
                actualSignInTraceStoreRequest = request;
            }
        };
        return new Oa31010Controller(authentication, storeSignInTrace);
    }

    /**
     * {@link Oa31010Controller#authentication(Oa31010SignInArg)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void authentication_test0() {

        // テスト対象クラス生成
        Oa31010Controller controller = createOa31010Controller();

        // 実行値
        Oa31010SignInArg signInArg = createOa31010SignInArg();

        // 期待値
        SignInCause signInCause = SignInCause.サインイン;
        SignInResult signInTrace = authentication_execute_signInTrace;
        Oa31010SignInResult oa31010SignInResult = Oa31010SignInResult.createFrom(signInTrace);
        ResponseEntity<Oa31010SignInResult> expected = new ResponseEntity<>(oa31010SignInResult, HttpStatus.OK);
        AuthenticationRequest expectedAuthenticationRequest = Oa31010AuthenticationConverter
            .with(operatorCode, password);
        SignInTraceStoreRequest expectedSignInTraceStoreRequest = Oa31010StoreConverter.with(
            clientIpaddress,
            operatorCode,
            signInCause,
            signInTrace);

        // 実行
        ResponseEntity<Oa31010SignInResult> actual = controller.authentication(signInArg);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceStoreRequest).usingRecursiveComparison().isEqualTo(
            expectedSignInTraceStoreRequest);
    }

    /**
     * {@link Oa31010Controller#authentication(Oa31010SignInArg)}のテスト
     *  ●パターン
     *    正常（サインイン失敗）
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void authentication_test1() {

        // テスト対象クラス生成
        Oa31010Controller controller = createOa31010Controller();

        // 実行値
        authentication_execute_signInTrace = SignInResult.拒否_アカウントロック中;
        Oa31010SignInArg signInArg = createOa31010SignInArg();

        // 期待値
        SignInCause signInCause = SignInCause.サインイン;
        SignInResult signInTrace = authentication_execute_signInTrace;
        Oa31010SignInResult oa31010SignInResult = Oa31010SignInResult.createFrom(signInTrace);
        ResponseEntity<Oa31010SignInResult> expected = new ResponseEntity<>(oa31010SignInResult, HttpStatus.OK);
        AuthenticationRequest expectedAuthenticationRequest = Oa31010AuthenticationConverter
            .with(operatorCode, password);
        SignInTraceStoreRequest expectedSignInTraceStoreRequest = Oa31010StoreConverter.with(
            clientIpaddress,
            operatorCode,
            signInCause,
            signInTrace);

        // 実行
        ResponseEntity<Oa31010SignInResult> actual = controller.authentication(signInArg);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceStoreRequest).usingRecursiveComparison().isEqualTo(
            expectedSignInTraceStoreRequest);
    }

    /**
     * {@link Oa31010Controller#authentication(Oa31010SignInArg)}のテスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void authentication_test2() {

        // テスト対象クラス生成
        Oa31010Controller controller = createOa31010Controller();

        // 実行値
        password = runtimeException;
        Oa31010SignInArg signInArg = createOa31010SignInArg();

        // 期待値
        SignInResult signInTrace = authentication_execute_signInTrace;
        ResponseEntity<Oa31010SignInResult> expected = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        AuthenticationRequest expectedAuthenticationRequest = Oa31010AuthenticationConverter
            .with(operatorCode, password);

        // 実行
        ResponseEntity<Oa31010SignInResult> actual = controller.authentication(signInArg);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceStoreRequest).isNull();
    }

    /**
     * {@link Oa31010Controller#continuedAuthentication(Oa31010SignInArg)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void continuedAuthentication_test0() {

        // テスト対象クラス生成
        Oa31010Controller controller = createOa31010Controller();

        // 実行値
        Oa31010SignInArg signInArg = createOa31010SignInArg();

        // 期待値
        SignInCause signInCause = SignInCause.継続サインイン;
        SignInResult signInTrace = authentication_execute_signInTrace;
        Oa31010SignInResult oa31010SignInResult = Oa31010SignInResult.createFrom(signInTrace);
        ResponseEntity<Oa31010SignInResult> expected = new ResponseEntity<>(oa31010SignInResult, HttpStatus.OK);
        AuthenticationRequest expectedAuthenticationRequest = Oa31010AuthenticationConverter
            .with(operatorCode, password);
        SignInTraceStoreRequest expectedSignInTraceStoreRequest = Oa31010StoreConverter.with(
            clientIpaddress,
            operatorCode,
            signInCause,
            signInTrace);

        // 実行
        ResponseEntity<Oa31010SignInResult> actual = controller.continuedAuthentication(signInArg);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceStoreRequest).usingRecursiveComparison().isEqualTo(
            expectedSignInTraceStoreRequest);
    }

    /**
     * {@link Oa31010Controller#continuedAuthentication(Oa31010SignInArg)}のテスト
     *  ●パターン
     *    正常（サインイン失敗）
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void continuedAuthentication_test1() {

        // テスト対象クラス生成
        Oa31010Controller controller = createOa31010Controller();

        // 実行値
        authentication_execute_signInTrace = SignInResult.失敗_パスワード誤り;
        Oa31010SignInArg signInArg = createOa31010SignInArg();

        // 期待値
        SignInCause signInCause = SignInCause.継続サインイン;
        SignInResult signInTrace = authentication_execute_signInTrace;
        Oa31010SignInResult oa31010SignInResult = Oa31010SignInResult.createFrom(signInTrace);
        ResponseEntity<Oa31010SignInResult> expected = new ResponseEntity<>(oa31010SignInResult, HttpStatus.OK);
        AuthenticationRequest expectedAuthenticationRequest = Oa31010AuthenticationConverter
            .with(operatorCode, password);
        SignInTraceStoreRequest expectedSignInTraceStoreRequest = Oa31010StoreConverter.with(
            clientIpaddress,
            operatorCode,
            signInCause,
            signInTrace);

        // 実行
        ResponseEntity<Oa31010SignInResult> actual = controller.continuedAuthentication(signInArg);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceStoreRequest).usingRecursiveComparison().isEqualTo(
            expectedSignInTraceStoreRequest);
    }

    /**
     * {@link Oa31010Controller#continuedAuthentication(Oa31010SignInArg)}のテスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void continuedAuthentication_test2() {

        // テスト対象クラス生成
        Oa31010Controller controller = createOa31010Controller();

        // 実行値
        password = runtimeException;
        Oa31010SignInArg signInArg = createOa31010SignInArg();

        // 期待値
        SignInResult signInTrace = authentication_execute_signInTrace;
        ResponseEntity<Oa31010SignInResult> expected = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        AuthenticationRequest expectedAuthenticationRequest = Oa31010AuthenticationConverter
            .with(operatorCode, password);

        // 実行
        ResponseEntity<Oa31010SignInResult> actual = controller.continuedAuthentication(signInArg);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceStoreRequest).isNull();
    }
}