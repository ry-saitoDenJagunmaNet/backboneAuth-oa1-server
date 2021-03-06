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

    // ???????????????
    private final String clientIpaddress = "001.001.001.001";
    private final Long operatorId = 1018L;
    private String operatorCode = "yu001009";
    private final String operatorName = "????????????????????????";
    private String password = "password1234";
    SignInResult authentication_execute_signInTrace = SignInResult.??????;
    private final String runtimeException = "runtimeException";
    private final Long jaId = 6L;
    private final String jaCode = "006";
    private final String jaName = "???????????????";
    private final Long branchId = 33L;
    private final String branchCode = "001";
    private final String branchName = "???????????????";

    // ?????????
    private AuthenticationRequest actualAuthenticationRequest;
    private SignInTraceStoreRequest actualSignInTraceStoreRequest;
    private SimpleOperatorSearchRequest actualSimpleOperatorSearchRequest;

    // Oa31010 ??????????????? Arg ??????
    private Oa31010SignInArg createOa31010SignInArg() {
        Oa31010SignInArg signInArg = new Oa31010SignInArg();
        signInArg.setClientIpaddress(clientIpaddress);
        signInArg.setOperatorCode(operatorCode);
        signInArg.setPassword(password);
        return signInArg;
    }
    // ????????????????????????????????????
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
    // JaAtMoment??????????????????
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
    // BranchAtMoment??????????????????
    public BranchAtMoment createBranchAtMoment() {
        return BranchAtMoment.builder()
            .withIdentifier(branchId)
            .withJaAtMoment(createJaAtMoment())
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.??????)
                .withBranchCode(BranchCode.of(branchCode))
                .withName(branchName)
                .build())
            .build();
    }

    // ???????????????????????????????????????????????????????????????????????????
    private Oa31010Controller createOa31010Controller() {
        // ???????????????????????????????????????????????????
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
        // ?????????????????????????????????????????????????????????
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
        // ?????????????????????????????????????????????????????????
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
        // ??????????????????????????????
        Authentication authentication = new Authentication(operatorRepository,passwordHistoryRepository,accountLockRepository) {
            public void execute(AuthenticationRequest request, AuthenticationResponse response) {
                actualAuthenticationRequest = request;
                // request.getPassword() == runtimeException ????????????RuntimeException ??????????????????
                if (request.getPassword().equals(runtimeException)) {
                    throw new RuntimeException();
                }
                response.setSignInResult(authentication_execute_signInTrace);
            };
        };
        // ??????????????????????????????????????????????????????
        SignInTraceRepositoryForStore signInTraceRepositoryForStore = new SignInTraceRepositoryForStore() {
            @Override
            public SignInTrace insert(SignInTrace signInTrace) {
                return null;
            }
        };
        // ????????????????????????????????????????????????
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
        // ?????????????????????????????????????????????????????????
        AccountLockRepositoryForStore accountLockRepositoryForStore = new AccountLockRepositoryForStore() {
            @Override
            public AccountLock insert(AccountLock AccountLock) {
                return null;
            }
        };
        // ???????????????????????????????????????????????????
        StoreSignInTrace storeSignInTrace = new StoreSignInTrace(signInTraceRepositoryForStore, signInTraceRepository, accountLockRepositoryForStore) {
            public void execute(SignInTraceStoreRequest request) {
                actualSignInTraceStoreRequest = request;
            }
        };
        // ???????????????????????????????????????????????????
        SearchSimpleOperator searchSimpleOperator = new SearchSimpleOperator(operatorRepository) {
            public void execute(SimpleOperatorSearchRequest request, SimpleOperatorSearchResponse response) {
                if (Strings2.isEmpty(request.getOperatorCode())) {
                    throw new  GunmaRuntimeException("EOA13002", "???????????????????????????");
                }
                actualSimpleOperatorSearchRequest = request;
                response.setOperator(createOperator());
            }
        };
        return new Oa31010Controller(authentication, storeSignInTrace, searchSimpleOperator);
    }

    /**
     * {@link Oa31010Controller#authentication(Oa31010SignInArg)}????????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void authentication_test0() {

        // ??????????????????????????????
        Oa31010Controller controller = createOa31010Controller();

        // ?????????
        Oa31010SignInArg signInArg = createOa31010SignInArg();
        controller.setHttpServletRequest(new MockHttpServletRequest());

        // ?????????
        SignInCause signInCause = SignInCause.???????????????;
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

        // ??????
        ResponseEntity<Oa31010SignInResult> actual = controller.authentication(signInArg);

        // ????????????
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceStoreRequest).usingRecursiveComparison().isEqualTo(
            expectedSignInTraceStoreRequest);
    }

    /**
     * {@link Oa31010Controller#authentication(Oa31010SignInArg)}????????????
     *  ???????????????
     *    ?????????????????????????????????
     *
     *  ???????????????
     *  ????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void authentication_test1() {

        // ??????????????????????????????
        Oa31010Controller controller = createOa31010Controller();

        // ?????????
        authentication_execute_signInTrace = SignInResult.??????_???????????????????????????;
        Oa31010SignInArg signInArg = createOa31010SignInArg();
        controller.setHttpServletRequest(new MockHttpServletRequest());

        // ?????????
        SignInCause signInCause = SignInCause.???????????????;
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

        // ??????
        ResponseEntity<Oa31010SignInResult> actual = controller.authentication(signInArg);

        // ????????????
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceStoreRequest).usingRecursiveComparison().isEqualTo(
            expectedSignInTraceStoreRequest);
    }

    /**
     * {@link Oa31010Controller#authentication(Oa31010SignInArg)}????????????
     *  ???????????????
     *    ?????????RuntimeException?????????
     *
     *  ???????????????
     *  ????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void authentication_test2() {

        // ??????????????????????????????
        Oa31010Controller controller = createOa31010Controller();

        // ?????????
        password = runtimeException;
        Oa31010SignInArg signInArg = createOa31010SignInArg();
        controller.setHttpServletRequest(new MockHttpServletRequest());

        // ?????????
        SignInResult signInTrace = authentication_execute_signInTrace;
        ResponseEntity<Oa31010SignInResult> expected = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        AuthenticationRequest expectedAuthenticationRequest = Oa31010AuthenticationConverter
            .with(operatorCode, password);

        // ??????
        ResponseEntity<Oa31010SignInResult> actual = controller.authentication(signInArg);

        // ????????????
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceStoreRequest).isNull();
    }

    /**
     * {@link Oa31010Controller#continuedAuthentication(Oa31010SignInArg)}????????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void continuedAuthentication_test0() {

        // ??????????????????????????????
        Oa31010Controller controller = createOa31010Controller();

        // ?????????
        Oa31010SignInArg signInArg = createOa31010SignInArg();
        controller.setHttpServletRequest(new MockHttpServletRequest());

        // ?????????
        SignInCause signInCause = SignInCause.?????????????????????;
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

        // ??????
        ResponseEntity<Oa31010SignInResult> actual = controller.continuedAuthentication(signInArg);

        // ????????????
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceStoreRequest).usingRecursiveComparison().isEqualTo(
            expectedSignInTraceStoreRequest);
    }

    /**
     * {@link Oa31010Controller#continuedAuthentication(Oa31010SignInArg)}????????????
     *  ???????????????
     *    ?????????????????????????????????
     *
     *  ???????????????
     *  ????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void continuedAuthentication_test1() {

        // ??????????????????????????????
        Oa31010Controller controller = createOa31010Controller();

        // ?????????
        authentication_execute_signInTrace = SignInResult.??????_?????????????????????;
        Oa31010SignInArg signInArg = createOa31010SignInArg();
        controller.setHttpServletRequest(new MockHttpServletRequest());

        // ?????????
        SignInCause signInCause = SignInCause.?????????????????????;
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

        // ??????
        ResponseEntity<Oa31010SignInResult> actual = controller.continuedAuthentication(signInArg);

        // ????????????
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceStoreRequest).usingRecursiveComparison().isEqualTo(
            expectedSignInTraceStoreRequest);
    }

    /**
     * {@link Oa31010Controller#continuedAuthentication(Oa31010SignInArg)}????????????
     *  ???????????????
     *    ?????????RuntimeException?????????
     *
     *  ???????????????
     *  ????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void continuedAuthentication_test2() {

        // ??????????????????????????????
        Oa31010Controller controller = createOa31010Controller();

        // ?????????
        password = runtimeException;
        Oa31010SignInArg signInArg = createOa31010SignInArg();
        controller.setHttpServletRequest(new MockHttpServletRequest());

        // ?????????
        SignInResult signInTrace = authentication_execute_signInTrace;
        ResponseEntity<Oa31010SignInResult> expected = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        AuthenticationRequest expectedAuthenticationRequest = Oa31010AuthenticationConverter
            .with(operatorCode, password);

        // ??????
        ResponseEntity<Oa31010SignInResult> actual = controller.continuedAuthentication(signInArg);

        // ????????????
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualAuthenticationRequest).usingRecursiveComparison().isEqualTo(expectedAuthenticationRequest);
        assertThat(actualSignInTraceStoreRequest).isNull();
    }

    /**
     * {@link Oa31010Controller#getSimpleOperator(String)}????????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ????????????
     *  ????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void getSimpleOperator_test0() throws JsonProcessingException {

        // ??????????????????????????????
        Oa31010Controller controller = createOa31010Controller();

        // ?????????
        Oa31010SearchSimpleOperatorConverter converter = Oa31010SearchSimpleOperatorConverter.with(operatorCode);

        // ?????????
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

        // ??????
        ResponseEntity<String> actual = controller.getSimpleOperator(operatorCode);

        // ????????????
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualSimpleOperatorSearchRequest).usingRecursiveComparison().isEqualTo(expectedSimpleOperatorSearchRequest);
    }

    /**
     * {@link Oa31010Controller#getSimpleOperator(String)}????????????
     *  ???????????????
     *    ?????????RuntimeException?????????
     *
     *  ???????????????
     *  ????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void getSimpleOperator_test1() {

        // ??????????????????????????????
        operatorCode = null;
        Oa31010Controller controller = createOa31010Controller();

        // ?????????
        Oa31010SearchSimpleOperatorConverter converter = Oa31010SearchSimpleOperatorConverter.with(operatorCode);

        // ?????????
        ResponseEntity<String> expected = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        // ??????
        ResponseEntity<String> actual = controller.getSimpleOperator(operatorCode);

        // ????????????
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * {@link Oa31010Controller#getOperatorInfo(String)}????????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ????????????
     *  ????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void getOperatorInfo_test0() {

        // ??????????????????????????????
        Oa31010Controller controller = createOa31010Controller();

        // ?????????
        Oa31010SearchSimpleOperatorConverter converter = Oa31010SearchSimpleOperatorConverter.with(operatorCode);

        // ?????????
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

        // ??????
        ResponseEntity<Oa31010OpertorInfoResult> actual = controller.getOperatorInfo(operatorCode);

        // ????????????
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(actualSimpleOperatorSearchRequest).usingRecursiveComparison().isEqualTo(expectedSimpleOperatorSearchRequest);
    }

    /**
     * {@link Oa31010Controller#getOperatorInfo(String)}????????????
     *  ???????????????
     *    ?????????RuntimeException?????????
     *
     *  ???????????????
     *  ????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void getOperatorInfo_test1() {

        // ??????????????????????????????
        operatorCode = null;
        Oa31010Controller controller = createOa31010Controller();

        // ?????????
        Oa31010SearchSimpleOperatorConverter converter = Oa31010SearchSimpleOperatorConverter.with(operatorCode);

        // ?????????
        ResponseEntity<Oa31010OpertorInfoResult> expected = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        // ??????
        ResponseEntity<Oa31010OpertorInfoResult> actual = controller.getOperatorInfo(operatorCode);

        // ????????????
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}