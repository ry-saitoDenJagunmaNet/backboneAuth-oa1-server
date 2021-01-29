package net.jagunma.backbone.auth.authmanager.application.queryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.application.usecase.authenticationCommand.AuthenticationRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.authenticationCommand.AuthenticationResponse;
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

    // 実行既定値
    private final Long operatorId = 18L;
    private final String operatorCode = "yu001009";
    private final String password = "password1234";
    private LocalDate validThruStartDate = LocalDate.now();
    private LocalDate validThruEndDate = LocalDate.now();
    private AvailableStatus availableStatus = AvailableStatus.利用可能;
    private final Long jaId = 6L;
    private final String jaCode = "006";
    private final String jaName = "ＪＡ００６";
    private final Long branchId = 33L;
    private final String branchCode = "001";
    private final String branchName = "店舗００１";
    private boolean operatorExistsByCodeResult = true;
    private Operator operator = createOperator();
    private String passwordHistoryPassword = password;
    private PasswordHistory passwordHistory = createPasswordHistory();
    private AccountLockStatus accountLockStatus = AccountLockStatus.アンロック;
    private AccountLock accountLock = createAccountLock();

    // 検証値
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
    // BranchAtMomentデータ作成
    private BranchAtMoment createBranchAtMoment() {
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
    // JaAtMomentデータ作成
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
    // パスワード履歴データ作成
    private PasswordHistory createPasswordHistory() {
        return PasswordHistory.createFrom(
            123456789L,
            operatorId,
            LocalDateTime.of(2020,1,21,8,31,12),
            passwordHistoryPassword,
            PasswordChangeType.初期,
            1,
            operator);
    }
    // アカウントロックデータ作成
    private AccountLock createAccountLock() {
        return AccountLock.createFrom(
            12345L,
            operatorId,
            LocalDateTime.of(2020,1,21,8,30,1),
            accountLockStatus.getCode(),
            1,
            operator);
    }

    // 認証サービス（テスト対象クラス）
    private Authentication createAuthentication() {
        // オペレーターリポジトリのスタブ
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
        // パスワード履歴リポジトリのスタブ
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
        // アカウントロックリポジトリのスタブ
        AccountLockRepository accountLockRepository = new AccountLockRepository() {
            @Override
            public AccountLock latestOneByOperatorId(Long operatorId) {
                actualAccountLockRepository_latestOneByOperatorId_operatorId = operatorId;
                return accountLock;
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
    // 認証サービス Requestのスタブ
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
    // 認証サービス Responseのスタブ
    private AuthenticationResponse createAuthenticationResponse() {
        return new AuthenticationResponse() {
            @Override
            public void setSignInResult(SignInResult signInResult) {
                actualSignInResult = signInResult;
            }
        };
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・サインイン結果、リポジトリの引数
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test0() {

        // テスト対象クラス生成
        Authentication authentication = createAuthentication();

        // 実行値
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();

        // 期待値
        SignInResult expectedSignInResult = SignInResult.成功;
        Operator expectedOperator = operator;
        String expectedOperatorCode = operatorCode;
        Long expectedOperatorId = operatorId;

        assertThatCode(() ->
            // 実行
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
        assertThat(actualOperatorRepository_existsByCode_operatorCode).isEqualTo(expectedOperatorCode);
        assertThat(actualPasswordHistoryRepository_latestOneByOperatorId_operatorId).isEqualTo(expectedOperatorId);
        assertThat(actualAccountLockRepository_latestOneByOperatorId_operatorId).isEqualTo(expectedOperatorId);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}テスト
     *  ●パターン
     *    正常
     *    ・オペレーターによる認証確認（オペレーター無し）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・サインイン結果
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByOperator_test0() {

        // テスト対象クラス生成
        Authentication authentication = createAuthentication();

        // 実行値
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        operatorExistsByCodeResult = false;

        // 期待値
        SignInResult expectedSignInResult = SignInResult.失敗_存在しないオペレーター;
        Operator expectedOperator = null;

        assertThatCode(() ->
            // 実行
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}テスト
     *  ●パターン
     *    正常
     *    ・オペレーターによる認証確認（利用可否状態:利用不可）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・サインイン結果
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByOperator_test1() {

        // テスト対象クラス生成
        Authentication authentication = createAuthentication();

        // 実行値
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        availableStatus = AvailableStatus.利用不可;
        operator = createOperator();

        // 期待値
        SignInResult expectedSignInResult = SignInResult.失敗_存在しないオペレーター;
        Operator expectedOperator = null;

        assertThatCode(() ->
            // 実行
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}テスト
     *  ●パターン
     *    正常
     *    ・オペレーターによる認証確認（有効期限:開始前）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・サインイン結果
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByOperator_test2() {

        // テスト対象クラス生成
        Authentication authentication = createAuthentication();

        // 実行値
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        validThruStartDate = LocalDate.now().plusDays(1);
        validThruEndDate = LocalDate.now().plusDays(1);
        operator = createOperator();

        // 期待値
        SignInResult expectedSignInResult = SignInResult.拒否_有効期限範囲外;
        Operator expectedOperator = null;

        assertThatCode(() ->
            // 実行
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}テスト
     *  ●パターン
     *    正常
     *    ・オペレーターによる認証確認（有効期限:終了後）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・サインイン結果
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByOperator_test3() {

        // テスト対象クラス生成
        Authentication authentication = createAuthentication();

        // 実行値
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        validThruStartDate = LocalDate.now().minusDays(1);
        validThruEndDate = LocalDate.now().minusDays(1);
        operator = createOperator();

        // 期待値
        SignInResult expectedSignInResult = SignInResult.拒否_有効期限範囲外;
        Operator expectedOperator = null;

        assertThatCode(() ->
            // 実行
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}テスト
     *  ●パターン
     *    正常
     *    ・パスワードによる認証確認（パスワード誤り）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・サインイン結果
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByPasswordHistory_test0() {

        // テスト対象クラス生成
        Authentication authentication = createAuthentication();

        // 実行値
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        passwordHistoryPassword = "errorPasswword";
        passwordHistory = createPasswordHistory();

        // 期待値
        SignInResult expectedSignInResult = SignInResult.失敗_パスワード誤り;
        Operator expectedOperator = null;

        assertThatCode(() ->
            // 実行
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}テスト
     *  ●パターン
     *    正常
     *    ・パスワードによる認証確認（パスワード履歴なし）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・サインイン結果
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByPasswordHistory_test1() {

        // テスト対象クラス生成
        Authentication authentication = createAuthentication();

        // 実行値
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        passwordHistory = null;

        // 期待値
        SignInResult expectedSignInResult = SignInResult.失敗_パスワード誤り;
        Operator expectedOperator = null;

        assertThatCode(() ->
            // 実行
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}テスト
     *  ●パターン
     *    正常
     *    ・アカウントロックによる認証確認（アカウントロック中）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・サインイン結果
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByAccountLock_test0() {

        // テスト対象クラス生成
        Authentication authentication = createAuthentication();

        // 実行値
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        accountLockStatus = AccountLockStatus.ロック;
        accountLock = createAccountLock();

        // 期待値
        SignInResult expectedSignInResult = SignInResult.拒否_アカウントロック中;
        Operator expectedOperator = null;

        assertThatCode(() ->
            // 実行
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }

    /**
     * {@link Authentication#execute(AuthenticationRequest, AuthenticationResponse)}テスト
     *  ●パターン
     *    正常
     *    ・アカウントロックによる認証確認（アカウントロックなし）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・サインイン結果
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_isAuthenticationByAccountLock_test1() {

        // テスト対象クラス生成
        Authentication authentication = createAuthentication();

        // 実行値
        AuthenticationRequest request = createAuthenticationRequest();
        AuthenticationResponse response = createAuthenticationResponse();
        accountLock = null;

        // 期待値
        SignInResult expectedSignInResult = SignInResult.成功;
        Operator expectedOperator = operator;

        assertThatCode(() ->
            // 実行
            authentication.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSignInResult).isEqualTo(expectedSignInResult);
    }
}