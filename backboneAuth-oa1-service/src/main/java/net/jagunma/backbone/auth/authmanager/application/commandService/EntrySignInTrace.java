package net.jagunma.backbone.auth.authmanager.application.commandService;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.application.usecase.signInTraceCommand.SignInTraceEntryRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceRepositoryForStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * サインイン証跡登録サービス
 */
@Service
@Transactional
public class EntrySignInTrace {

    private final SignInTraceRepositoryForStore signInTraceRepositoryForStore;

    // コンストラクタ
    EntrySignInTrace(SignInTraceRepositoryForStore signInTraceRepositoryForStore) {
        this.signInTraceRepositoryForStore = signInTraceRepositoryForStore;
    }

    /**
     * サインイン証跡の登録を行います
     *
     * @param request サインイン証跡登録サービス Request
     */
    public void execute(SignInTraceEntryRequest request) {

        // パラメーターの検証
        EntrySignInTraceValidator.with(request).validate();

        // SignInTraceモデルに変換
        SignInTrace signInTrace = SignInTrace.createFrom(
            null,
            LocalDateTime.now(),
            request.getTryIpAddress(),
            request.getOperatorCode(),
            request.getSignInCause().getCode(),
            request.getSignInResult().getCode(),
            null,
            null);

        // サインイン証跡 Insert
        SignInTrace resultSignInTrace = signInTraceRepositoryForStore.insert(signInTrace);
    }
}
