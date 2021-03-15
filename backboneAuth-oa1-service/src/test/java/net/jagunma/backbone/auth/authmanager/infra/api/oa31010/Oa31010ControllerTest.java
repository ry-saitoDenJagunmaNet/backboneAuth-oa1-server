package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.jagunma.backbone.auth.authmanager.application.api_commandService.StoreSignInTrace;
import net.jagunma.backbone.auth.authmanager.application.api_queryService.Authentication;
import net.jagunma.backbone.auth.authmanager.application.api_queryService.SearchSimpleOperator;
import net.jagunma.backbone.auth.authmanager.application.api_usecase.authenticationCommand.AuthenticationRequest;
import net.jagunma.backbone.auth.authmanager.application.api_usecase.authenticationCommand.AuthenticationResponse;
import net.jagunma.backbone.auth.authmanager.application.api_usecase.operatorReference.SimpleOperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.api_usecase.operatorReference.SimpleOperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.application.api_usecase.signInTraceCommand.SignInTraceStoreRequest;
import net.jagunma.backbone.auth.authmanager.infra.api.JacksonConfig;
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
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.util.strings2.Strings2;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import net.jagunma.common.values.model.operator.OperatorCode;
import net.jagunma.common.values.model.operator.SimpleOperator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

class Oa31010ControllerTest {

    // 実行既定値
    private final String clientIpaddress = "001.001.001.001";
    private final Long operatorId = 1018L;
    private String operatorCode = "yu001009";
    private final String operatorName = "ＹＵ００１００９";
    private String password = "password1234";
    SignInResult authentication_execute_signInTrace = SignInResult.成功;
    private final String runtimeException = "runtimeException";
    private final Long jaId = 6L;
    private final String jaCode = "006";
    private final String jaName = "ＪＡ００６";
    private final Long branchId = 33L;
    private final String branchCode = "001";
    private final String branchName = "店舗００１";

    // 検証値
    private AuthenticationRequest actualAuthenticationRequest;
    private SignInTraceStoreRequest actualSignInTraceStoreRequest;
    private SimpleOperatorSearchRequest actualSimpleOperatorSearchRequest;

    // Oa31010 サインイン Arg 作成
    private Oa31010SignInArg createOa31010SignInArg() {
        Oa31010SignInArg signInArg = new Oa31010SignInArg();
        signInArg.setClientIpaddress(clientIpaddress);
        signInArg.setOperatorCode(operatorCode);
        signInArg.setPassword(password);
        return signInArg;
    }
    // オペレーターデータの作成
    private Operator createOperator() {
        return Operator.createFrom(
            operatorId,
            operatorCode,
            operatorName,
            null,
            null,
            null,
            null,
            jaId,
            jaCode,
            branchId,
            branchCode,
            null,
            1,
            createBranchAtMoment()
        );
    }
    // JaAtMomentデータの作成
    public JaAtMoment createJaAtMoment() {
        return JaAtMoment.builder()
            .withIdentifier(jaId)
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(jaCode))
                .withName(jaName)
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
    }
    // BranchAtMomentデータの作成
    public BranchAtMoment createBranchAtMoment() {
        return BranchAtMoment.builder()
            .withIdentifier(branchId)
            .withJaAtMoment(createJaAtMoment())
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般)
                .withBranchCode(BranchCode.of(branchCode))
                .withName(branchName)
                .build())
            .build();
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
        // サインイン証跡登録サービスのスタブ
        SearchSimpleOperator searchSimpleOperator = new SearchSimpleOperator(operatorRepository) {
            public void execute(SimpleOperatorSearchRequest request, SimpleOperatorSearchResponse response) {
                if (Strings2.isEmpty(request.getOperatorCode())) {
                    throw new  GunmaRuntimeException("EOA13002", "オペレーターコード");
                }
                actualSimpleOperatorSearchRequest = request;
                response.setOperator(createOperator());
            }
        };
        return new Oa31010Controller(authentication, storeSignInTrace, searchSimpleOperator);
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
        controller.setHttpServletRequest(new MockHttpServletRequest());

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
        controller.setHttpServletRequest(new MockHttpServletRequest());

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
        controller.setHttpServletRequest(new MockHttpServletRequest());

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
        controller.setHttpServletRequest(new MockHttpServletRequest());

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
        controller.setHttpServletRequest(new MockHttpServletRequest());

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
        controller.setHttpServletRequest(new MockHttpServletRequest());

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

    /**
     * {@link Oa31010Controller#getSimpleOperator(String)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・戻り値
     *  ・サービスの引数
     */
    @Test
    @Tag(TestSize.SMALL)
    void getSimpleOperator_test0() throws JsonProcessingException {

        // テスト対象クラス生成
        Oa31010Controller controller = createOa31010Controller();

        // 実行値
        Oa31010SearchSimpleOperatorConverter converter = Oa31010SearchSimpleOperatorConverter.with(operatorCode);

        // 期待値
        SimpleOperator simpleOperator = SimpleOperator.builder()
            .withIdentifier(operatorId)
            .withOperatorCode(OperatorCode.of(operatorCode))
            .withOperatorName(operatorName)
            .withBranch(createBranchAtMoment())
            .build();
        JacksonConfig jacksonConfig = new JacksonConfig();
        String simpleOperatorJsonString;
        try {
            simpleOperatorJsonString = jacksonConfig.objectMapperBuilder().build()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(simpleOperator);
        } catch (RuntimeException | JsonProcessingException re) {
            throw re;
        }
        ResponseEntity<String> expected = new ResponseEntity<>(simpleOperatorJsonString, HttpStatus.OK);
        Oa31010SearchSimpleOperatorConverter expectedSimpleOperatorSearchRequest = Oa31010SearchSimpleOperatorConverter.with(operatorCode);

        // 実行
        ResponseEntity<String> actual = controller.getSimpleOperator(operatorCode);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualSimpleOperatorSearchRequest).usingRecursiveComparison().isEqualTo(expectedSimpleOperatorSearchRequest);
    }

    /**
     * {@link Oa31010Controller#getSimpleOperator(String)}のテスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void getSimpleOperator_test1() {

        // テスト対象クラス生成
        operatorCode = null;
        Oa31010Controller controller = createOa31010Controller();

        // 実行値
        Oa31010SearchSimpleOperatorConverter converter = Oa31010SearchSimpleOperatorConverter.with(operatorCode);

        // 期待値
        ResponseEntity<String> expected = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        // 実行
        ResponseEntity<String> actual = controller.getSimpleOperator(operatorCode);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link Oa31010Controller#getOperatorInfo(String)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・戻り値
     *  ・サービスの引数
     */
    @Test
    @Tag(TestSize.SMALL)
    void getOperatorInfo_test0() {

        // テスト対象クラス生成
        Oa31010Controller controller = createOa31010Controller();

        // 実行値
        Oa31010SearchSimpleOperatorConverter converter = Oa31010SearchSimpleOperatorConverter.with(operatorCode);

        // 期待値
        SimpleOperator simpleOperator = SimpleOperator.builder()
            .withIdentifier(operatorId)
            .withOperatorCode(OperatorCode.of(operatorCode))
            .withOperatorName(operatorName)
            .withBranch(createBranchAtMoment())
            .build();
        Oa31010OpertorInfoResult result = new Oa31010OpertorInfoResult();
        result.setJaId(simpleOperator.getBranch().getJaAtMoment().getIdentifier());
        result.setJaCode(simpleOperator.getBranch().getJaAtMoment().getJaAttribute().getJaCode().getValue());
        result.setJaName(simpleOperator.getBranch().getJaAtMoment().getJaAttribute().getName());
        result.setBranchId(simpleOperator.getBranch().getIdentifier());
        result.setBranchCode(simpleOperator.getBranch().getBranchAttribute().getBranchCode().getValue());
        result.setBranchName(simpleOperator.getBranch().getBranchAttribute().getName());
        result.setOperatorId(simpleOperator.getIdentifier());
        result.setOperatorCode(simpleOperator.getOperatorCode().getValue());
        result.setOperatorName(simpleOperator.getOperatorName());
        ResponseEntity<Oa31010OpertorInfoResult> expected = new ResponseEntity<>(result, HttpStatus.OK);
        Oa31010SearchSimpleOperatorConverter expectedSimpleOperatorSearchRequest = Oa31010SearchSimpleOperatorConverter.with(operatorCode);

        // 実行
        ResponseEntity<Oa31010OpertorInfoResult> actual = controller.getOperatorInfo(operatorCode);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualSimpleOperatorSearchRequest).usingRecursiveComparison().isEqualTo(expectedSimpleOperatorSearchRequest);
    }

    /**
     * {@link Oa31010Controller#getOperatorInfo(String)}のテスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     */
    @Test
    @Tag(TestSize.SMALL)
    void getOperatorInfo_test1() {

        // テスト対象クラス生成
        operatorCode = null;
        Oa31010Controller controller = createOa31010Controller();

        // 実行値
        Oa31010SearchSimpleOperatorConverter converter = Oa31010SearchSimpleOperatorConverter.with(operatorCode);

        // 期待値
        ResponseEntity<Oa31010OpertorInfoResult> expected = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        // 実行
        ResponseEntity<Oa31010OpertorInfoResult> actual = controller.getOperatorInfo(operatorCode);

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}