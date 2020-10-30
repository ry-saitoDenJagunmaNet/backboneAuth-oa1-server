package net.jagunma.backbone.auth.authmanager.application.commandService;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordChangeRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordResetRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * パスワード更新サービス
 */
@Service
@Transactional
public class UpdatePassword {

    private final PasswordHistoryRepositoryForStore passwordHistoryRepositoryForStore;

    public UpdatePassword(PasswordHistoryRepositoryForStore passwordHistoryRepositoryForStore) {
        this.passwordHistoryRepositoryForStore = passwordHistoryRepositoryForStore;
    }

    /**
     * パスワードの更新を行います
     * （パスワードのリセット）
     *
     * @param request パスワードリセットサービス Request
     */
    public void execute(PasswordResetRequest request) {

        // パラメーターの検証
//        UpdatePasswordValidator.with(request).validate();

        // パスワード履歴の生成を行います
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            null,
            request.getOperatorId(),
            LocalDateTime.now(), //ToDo:★検討
            request.getPassword(),
            PasswordChangeType.管理者によるリセット,
            null,
            null);

        // パスワードの格納を行います
        passwordHistoryRepositoryForStore.store(passwordHistory);
    }

    /**
     * パスワードの更新を行います
     * （パスワードの変更）
     *
     * @param request パスワード変更サービス Request
     */
    public void execute(PasswordChangeRequest request) {

        // パラメーターの検証
//        UpdatePasswordValidator.with(request).validate();

        // パスワード履歴の生成を行います
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            null,
            request.getOperatorId(),
            LocalDateTime.now(), //ToDo:★検討
            request.getNewPassword(),
            PasswordChangeType.ユーザーによる変更,
            null,
            null);

        // パスワードの格納を行います
        passwordHistoryRepositoryForStore.store(passwordHistory);
    }
}
