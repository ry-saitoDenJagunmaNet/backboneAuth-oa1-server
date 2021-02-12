package net.jagunma.backbone.auth.authmanager.application.commandService;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInTraceCommand.SignInTraceEntryRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraces;
import net.jagunma.backbone.auth.authmanager.model.types.AccountLockStatus;
import net.jagunma.common.ddd.model.orders.Order;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * サインイン証跡登録サービス
 */
@Service
@Transactional
public class EntrySignInTrace {

    private final SignInTraceRepositoryForStore signInTraceRepositoryForStore;
    private final SignInTraceRepository signInTraceRepository;
    private final AccountLockRepositoryForStore accountLockRepositoryForStore;

    // コンストラクタ
    public EntrySignInTrace(
        SignInTraceRepositoryForStore signInTraceRepositoryForStore,
        SignInTraceRepository signInTraceRepository,
        AccountLockRepositoryForStore accountLockRepositoryForStore) {

        this.signInTraceRepositoryForStore = signInTraceRepositoryForStore;
        this.signInTraceRepository = signInTraceRepository;
        this.accountLockRepositoryForStore = accountLockRepositoryForStore;
    }

    /**
     * サインイン証跡の登録を行います
     *
     * @param request サインイン証跡登録サービス Request
     */
    public void execute(SignInTraceEntryRequest request) {

        // パラメーターの検証
        EntrySignInTraceValidator.with(request).validate();

        // サインイン証跡の登録
        SignInTrace signInTrace = insertSignInTrace(request);

        // ロック判定
        if (isLockJudgment(request.getOperatorCode())) {
            // ロック状態でアカウントロックの登録
            insertAccountLockForLock(signInTrace.getOperator().getOperatorId(), signInTrace.getTryDateTime());
        }
    }

    /**
     * サインイン証跡の登録を行います
     *
     * @param request サインイン証跡登録サービス Request
     * @return サインイン証跡
     */
    private SignInTrace insertSignInTrace(SignInTraceEntryRequest request) {

        // サインイン証跡モデルに変換
        SignInTrace signInTrace = SignInTrace.createFrom(
            null,
            LocalDateTime.now(),
            request.getTryIpAddress(),
            request.getOperatorCode(),
            request.getSignInCause(),
            request.getSignInResult(),
            null,
            null);

        // サインイン証跡 Insert
        return signInTraceRepositoryForStore.insert(signInTrace);
    }

    /**
     * ロック判定を行います
     *
     * @param operatorCode オペレーターコード
     * @return true:ロック
     */
    private Boolean isLockJudgment(String operatorCode) {

        SignInTraceCriteria criteria = new SignInTraceCriteria();
        criteria.getOperatorCodeCriteria().setEqualTo(operatorCode);
        SignInTraces signInTraces = signInTraceRepository.selectBy(criteria, Orders.empty().addOrder("tryDateTime", Order.DESC));
        int passwordErrorCount = 0;
        for (SignInTrace signInTrace : signInTraces.getValues()) {
            if (!signInTrace.getSignInResult().is失敗_パスワード誤り()) { break; }
            passwordErrorCount++;
            // ToDo: ３回でロック
            if (passwordErrorCount >= 3) { return true; }
        }
        return false;
    }

    /**
     * ロック状態でアカウントロックの登録を行います
     *
     * @param operatorId オペレーターID
     * @param occurredDateTime 発生日時
     * @return アカウントロック
     */
    private AccountLock insertAccountLockForLock(Long operatorId, LocalDateTime occurredDateTime) {

        // アカウントロックモデルに変換
        AccountLock sccountLock = AccountLock.createFrom(
            null,
            operatorId,
            occurredDateTime,
            AccountLockStatus.ロック,
            null,
            null);

        // アカウントロック Insert
        return accountLockRepositoryForStore.insert(sccountLock);
    }
}
