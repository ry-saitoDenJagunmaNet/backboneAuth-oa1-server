package net.jagunma.backbone.auth.authmanager.application.api_queryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.application.api_usecase.authenticationCommand.AuthenticationRequest;
import net.jagunma.backbone.auth.authmanager.application.api_usecase.authenticationCommand.AuthenticationResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepository;
import net.jagunma.backbone.auth.authmanager.model.types.AccountLockStatus;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AuthenticationTest {

    // ???????????????
    private final Long operatorId = 18L;
    private final String operatorCode = "yu001009";
    private final String password = "password1234";
    private LocalDate validThruStartDate = LocalDate.now();
    private LocalDate validThruEndDate = LocalDate.now();
    private AvailableStatus availableStatus = AvailableStatus.????????????;
    private final Long jaId = 6L;
    private final String jaCode = "006";
    private final String jaName = "???????????????";
    private final Long branchId = 33L;
    private final String branchCode = "001";
    private final String branchName = "???????????????";
    private boolean operatorExistsByCodeResult = true;
    private Operator operator = createOperator();
    private String passwordHistoryPassword = password;
    private PasswordHistory passwordHistory = createPasswordHistory();
    private AccountLockStatus accountLockStatus = AccountLockStatus.???????????????;
    private AccountLock accountLock = createAccountLock();

    // ?????????
    private SignInResult actualSignInResult;
    private String actualOperatorRepository_existsByCode_operatorCode;
    private Long actualPasswordHistoryRepository_latestOneByOperatorId_operatorId;
    private Long actualAccountLockRepository_latestOneByOperatorId_operatorId;

    private Operator createOperator() {
        return Operator.createFrom(
            18L,
            operatorCode,
            "",
            "",
            validThruStartDate,
            validThruEndDate,
            null,
            jaId,
            jaCode,
            branchId,
            branchCode,
            availableStatus,
            1,
            createBranchAtMoment());
    }
    // BranchAtMoment???????????????
    private BranchAtMoment createBranchAtMoment() {
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
    // JaAtMoment???????????????
    private JaAtMoment createJaAtMoment() {
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
    // ????????????????????????????????????
    private PasswordHistory createPasswordHistory() {
        return PasswordHistory.createFrom(
            123456789L,
            operatorId,
            LocalDateTime.of(2020,1,21,8,31,12),
            passwordHistoryPassword,
            PasswordChangeType.??????,
            1,
            operator);
    }
    // ???????????????????????????????????????
    private AccountLock createAccountLock() {
        return AccountLock.createFrom(
            12345L,
            operatorId,
            LocalDateTime.of(2020,1,21,8,30,1),
            accountLockStatus,
            1,
            operator);
    }

    // ????????????????????????????????????????????????
    private Authentication createAuthentication() {
        // ?????????????????????????????????????????????
        OperatorRepository operatorRepository = new OperatorRepository() {
            @Override
            public boolean existsByCode(String operatorCode) {
                actualOperatorRepository_existsByCode_operatorCode = operatorCode;
                return operatorExistsByCodeResult;
            }
            @Override
            public Operator findOneByCode(String operatorCode) {
                return operator;
            }
            @Override
            public Operator findOneById(Long operatorId) {
                return null;
            }
            @Override
            public boolean existsById(Long operatorId) {
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
        // ????????????????????????????????????????????????
        PasswordHistoryRepository passwordHistoryRepository = new PasswordHistoryRepository() {
            @Override
            public PasswordHistory latestOneByOperatorId(Long operatorId) {
                actualPasswordHistoryRepository_latestOneByOperatorId_operatorId = operatorId;
                return passwordHistory;
            }
            @Override
            public PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria, Orders orders) {
                return null;
            }
        };
        // ???????????????????????????????????????????????????
        AccountLockRepository accountLockRepository = new AccountLockRepository() {
            @Override
            public AccountLock latestOneByOperatorId(Long operatorId) {
                actualAccountLockRepository_latestOneByOperatorId_operatorId = operatorId;
                return accountLock;
            }
            @Override
            public AccountLock findOneById(Long accountLockId) {
                return null;
            }
            @Override
            public boolean existsByOperatorId(Long operatorId) {
                return accountLock != null;
            }
            @Override
            public AccountLocks selectBy(AccountLockCriteria accountLockrCriteria, Orders orders) {
                return null;
            }
        };
        return new Authentication(
            operatorRepository,
            passwordHistoryRepository,
            accountLockRepository);
    }
    // ?????????????????? Request????????????
    private AuthenticationRequest createAuthenticationRequest() {
        return new AuthenticationRequest() {
            @Override
            public String getOperatorCode() {
                return operatorCode;
            }
            @Override
            public String getPassword() {
                return password;
            }
        };
    }
    // ?????????????????? Response????????????
    private AuthenticationResponse createAuthenticationResponse() {
        return new AuthenticationResponse() {
            @Override
            public void setSignInResult(SignInResult signInResult) {
                actualSignInResult = signInResult;
            }
        };
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???????????????
     *  ???????????????????????????????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test0() {

        // ??????????????????????????????
        Authentication authentication = createAuthentication();

        // ?????????
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();

        // ?????????
        SignInResult expectedSignInResult = SignInResult.??????;
        Operator expectedOperator = operator;
        String expectedOperatorCode = operatorCode;
        Long expectedOperatorId = operatorId;

        assertThatCode(() ->
            // ??????
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // ????????????
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
        assertThat(actualOperatorRepository_existsByCode_operatorCode).isEqualTo(expectedOperatorCode);
        assertThat(actualPasswordHistoryRepository_latestOneByOperatorId_operatorId).isEqualTo(expectedOperatorId);
        assertThat(actualAccountLockRepository_latestOneByOperatorId_operatorId).isEqualTo(expectedOperatorId);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}?????????
     *  ???????????????
     *    ??????
     *    ????????????????????????????????????????????????????????????????????????
     *
     *  ???????????????
     *  ???????????????
     *  ????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByOperator_test0() {

        // ??????????????????????????????
        Authentication authentication = createAuthentication();

        // ?????????
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        operatorExistsByCodeResult = false;

        // ?????????
        SignInResult expectedSignInResult = SignInResult.??????_?????????????????????????????????;
        Operator expectedOperator = null;

        assertThatCode(() ->
            // ??????
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // ????????????
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}?????????
     *  ???????????????
     *    ??????
     *    ???????????????????????????????????????????????????????????????:???????????????
     *
     *  ???????????????
     *  ???????????????
     *  ????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByOperator_test1() {

        // ??????????????????????????????
        Authentication authentication = createAuthentication();

        // ?????????
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        availableStatus = AvailableStatus.????????????;
        operator = createOperator();

        // ?????????
        SignInResult expectedSignInResult = SignInResult.??????_?????????????????????????????????;
        Operator expectedOperator = null;

        assertThatCode(() ->
            // ??????
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // ????????????
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}?????????
     *  ???????????????
     *    ??????
     *    ?????????????????????????????????????????????????????????:????????????
     *
     *  ???????????????
     *  ???????????????
     *  ????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByOperator_test2() {

        // ??????????????????????????????
        Authentication authentication = createAuthentication();

        // ?????????
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        validThruStartDate = LocalDate.now().plusDays(1);
        validThruEndDate = LocalDate.now().plusDays(1);
        operator = createOperator();

        // ?????????
        SignInResult expectedSignInResult = SignInResult.??????_?????????????????????;
        Operator expectedOperator = null;

        assertThatCode(() ->
            // ??????
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // ????????????
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}?????????
     *  ???????????????
     *    ??????
     *    ?????????????????????????????????????????????????????????:????????????
     *
     *  ???????????????
     *  ???????????????
     *  ????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByOperator_test3() {

        // ??????????????????????????????
        Authentication authentication = createAuthentication();

        // ?????????
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        validThruStartDate = LocalDate.now().minusDays(1);
        validThruEndDate = LocalDate.now().minusDays(1);
        operator = createOperator();

        // ?????????
        SignInResult expectedSignInResult = SignInResult.??????_?????????????????????;
        Operator expectedOperator = null;

        assertThatCode(() ->
            // ??????
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // ????????????
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}?????????
     *  ???????????????
     *    ??????
     *    ??????????????????????????????????????????????????????????????????
     *
     *  ???????????????
     *  ???????????????
     *  ????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByPasswordHistory_test0() {

        // ??????????????????????????????
        Authentication authentication = createAuthentication();

        // ?????????
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        passwordHistoryPassword = "errorPasswword";
        passwordHistory = createPasswordHistory();

        // ?????????
        SignInResult expectedSignInResult = SignInResult.??????_?????????????????????;
        Operator expectedOperator = null;

        assertThatCode(() ->
            // ??????
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // ????????????
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}?????????
     *  ???????????????
     *    ??????
     *    ????????????????????????????????????????????????????????????????????????
     *
     *  ???????????????
     *  ???????????????
     *  ????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByPasswordHistory_test1() {

        // ??????????????????????????????
        Authentication authentication = createAuthentication();

        // ?????????
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        passwordHistory = null;

        // ?????????
        SignInResult expectedSignInResult = SignInResult.??????_?????????????????????;
        Operator expectedOperator = null;

        assertThatCode(() ->
            // ??????
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // ????????????
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}?????????
     *  ???????????????
     *    ??????
     *    ?????????????????????????????????????????????????????????????????????????????????
     *
     *  ???????????????
     *  ???????????????
     *  ????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByAccountLock_test0() {

        // ??????????????????????????????
        Authentication authentication = createAuthentication();

        // ?????????
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        accountLockStatus = AccountLockStatus.?????????;
        accountLock = createAccountLock();

        // ?????????
        SignInResult expectedSignInResult = SignInResult.??????_???????????????????????????;
        Operator expectedOperator = null;

        assertThatCode(() ->
            // ??????
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // ????????????
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}?????????
     *  ???????????????
     *    ??????
     *    ????????????????????????????????????????????????????????????????????????????????????
     *
     *  ???????????????
     *  ???????????????
     *  ????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByAccountLock_test1() {

        // ??????????????????????????????
        Authentication authentication = createAuthentication();

        // ?????????
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        accountLock = null;

        // ?????????
        SignInResult expectedSignInResult = SignInResult.??????;
        Operator expectedOperator = operator;

        assertThatCode(() ->
            // ??????
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // ????????????
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }
}