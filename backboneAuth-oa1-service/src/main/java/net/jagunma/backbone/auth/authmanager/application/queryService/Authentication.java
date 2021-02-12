package net.jagunma.backbone.auth.authmanager.application.queryService;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.authenticationCommand.AuthenticationRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.authenticationCommand.AuthenticationResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepository;
import net.jagunma.backbone.auth.authmanager.model.types.AccountLockStatus;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import org.springframework.stereotype.Service;

/**
 * 認証サービス
 */
@Service
public class Authentication {

    private final OperatorRepository operatorRepository;
    private final PasswordHistoryRepository passwordHistoryRepository;
    private final AccountLockRepository accountLockRepository;

    // コンストラクタ
    public Authentication(OperatorRepository operatorRepository,
        PasswordHistoryRepository passwordHistoryRepository,
        AccountLockRepository accountLockRepository) {

        this.operatorRepository = operatorRepository;
        this.passwordHistoryRepository = passwordHistoryRepository;
        this.accountLockRepository = accountLockRepository;
    }

    /**
     * 認証を行います
     *
     * @param request  認証サービス Request
     * @param response 認証サービス Response
     */
    public void execute(AuthenticationRequest request, AuthenticationResponse response) {

        LocalDate today = LocalDate.now();

        // オペレーターよる認証
        if (!operatorRepository.existsByCode(request.getOperatorCode())) {
            response.setSignInResult(SignInResult.失敗_存在しないオペレーター);
            return;
        }
        Operator operator = operatorRepository.findOneByCode(request.getOperatorCode());
        SignInResult signInResult = isAuthenticationByOperator(operator);
        if (!SignInResult.成功.equals(signInResult)) {
            response.setSignInResult(signInResult);
            return;
        }

        // アカウントロックよる認証
        if (accountLockRepository.existsByOperatorId(operator.getOperatorId())) {
            AccountLock accountLock = accountLockRepository.latestOneByOperatorId(operator.getOperatorId());
            signInResult =  isAuthenticationByAccountLock(accountLock);
            if (!SignInResult.成功.equals(signInResult)) {
                response.setSignInResult(signInResult);
                return;
            }
        }

        // JA割当IPアドレス範囲よる認証

        // パスワード履歴よる認証
        PasswordHistory passwordHistory = passwordHistoryRepository.latestOneByOperatorId(operator.getOperatorId());
        signInResult = isAuthenticationByPasswordHistory(passwordHistory, request.getPassword());
        if (!SignInResult.成功.equals(signInResult)) {
            response.setSignInResult(signInResult);
            return;
        }


        response.setSignInResult(signInResult);
    }

    /**
     * オペレーターによる認証
     *
     * @param operator オペレーター
     * @return サインイン結果
     */
    private SignInResult isAuthenticationByOperator(Operator operator) {

        LocalDate today = LocalDate.now();

        // 利用可否状態
        if (AvailableStatus.利用不可.equals(operator.getAvailableStatus())) {
            return SignInResult.失敗_存在しないオペレーター;
        }

        // 有効期限
        if (today.compareTo(operator.getValidThruStartDate()) < 0) {
            return SignInResult.拒否_有効期限範囲外;
        }
        if (today.compareTo(operator.getValidThruEndDate()) > 0) {
            return SignInResult.拒否_有効期限範囲外;
        }

        return SignInResult.成功;
    }

    /**
     * パスワードによる認証
     *
     * @param passwordHistory パスワード履歴
     * @return サインイン結果
     */
    private SignInResult isAuthenticationByPasswordHistory(PasswordHistory passwordHistory, String password) {

        // パスワード誤り
        if (passwordHistory == null) {
            return SignInResult.失敗_パスワード誤り;
        }
        if (!password.equals(passwordHistory.getPassword())) {
            return SignInResult.失敗_パスワード誤り;
        }

        return SignInResult.成功;
    }

    /**
     * アカウントロックによる認証
     *
     * @param accountLock アカウントロック
     * @return サインイン結果
     */
    private SignInResult isAuthenticationByAccountLock(AccountLock accountLock) {

        // アカウントロック
        if (accountLock.getLockStatus().isロック()) {
            return SignInResult.拒否_アカウントロック中;
        }

        return SignInResult.成功;
    }
}
