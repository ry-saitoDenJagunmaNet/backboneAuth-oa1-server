package net.jagunma.backbone.auth.authmanager.application.commandService;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordChangeRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.passwordCommand.PasswordResetRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoriesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.common.ddd.model.orders.Order;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * パスワード更新サービス
 */
@Service
@Transactional
public class UpdatePassword {

    private final PasswordHistoryRepositoryForStore passwordHistoryRepositoryForStore;
    private final PasswordHistoriesRepository passwordHistoriesRepository;

    public UpdatePassword(
        PasswordHistoryRepositoryForStore passwordHistoryRepositoryForStore,
        PasswordHistoriesRepository passwordHistoriesRepository) {
        this.passwordHistoryRepositoryForStore = passwordHistoryRepositoryForStore;
        this.passwordHistoriesRepository = passwordHistoriesRepository;
    }

    /**
     * パスワードの更新を行います
     * （パスワードのリセット）
     *
     * @param request パスワードリセットサービス Request
     */
    public void execute(PasswordResetRequest request) {

        // パラメーターの検証
        UpdatePasswordValidator.with(request).validate();

        // 現在の変更種別が「機器認証パスワード」でないかのチェックを行います
        checkNotDeviceAuthPassword(request.getOperatorId());

        // パスワード履歴の生成を行います
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            null,
            request.getOperatorId(),
            LocalDateTime.now(),
            request.getPassword(),
            PasswordChangeType.管理者によるリセット,
            null,
            null);

        // パスワードの格納を行います
        passwordHistoryRepositoryForStore.store(passwordHistory);
    }

    /**
     * パスワードの更新を行います
     * （変更）
     *
     * @param request パスワード変更サービス Request
     */
    public void execute(PasswordChangeRequest request) {

        // パラメーターの検証
        UpdatePasswordValidator.with(request).validate();

        // 現在の変更種別が「機器認証パスワード」でないかのチェックを行います
        checkNotDeviceAuthPassword(request.getOperatorId());

        // 機器認証パスワード以外での過去世代のパスワードのチェックを行います
        checkPastGenerationsPassword(request);

        // パスワード履歴の生成を行います
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            null,
            request.getOperatorId(),
            LocalDateTime.now(),
            request.getNewPassword(),
            PasswordChangeType.ユーザーによる変更,
            null,
            null);

        // パスワードの格納を行います
        passwordHistoryRepositoryForStore.store(passwordHistory);
    }

    /**
     * 現在の変更種別が「機器認証パスワード」でないかのチェックを行います
     *
     * @param operatorId オペレーターID
     */
    void checkNotDeviceAuthPassword(Long operatorId) {
        PasswordHistoryCriteria passwordHistoryCriteria = new PasswordHistoryCriteria();

        passwordHistoryCriteria.getOperatorIdCriteria().setEqualTo(operatorId);

        PasswordHistories passwordHistories = passwordHistoriesRepository.selectBy(passwordHistoryCriteria, Orders.empty().addOrder("ChangeDateTime", Order.DESC));

        if (passwordHistories.getValues().get(0).getPasswordChangeType().equals(PasswordChangeType.機器認証パスワード)) {
            throw new GunmaRuntimeException("EOA12002");
        }
    }

    /**
     * 機器認証パスワード以外での過去世代のパスワードのチェックを行います
     * ・リクエストの古いパスワードと現在のパスワードが同じか
     * ・現在と過去2世代に同じパスワードを使用していないか
     *
     * @param request パスワード変更サービス Request
     */
    void checkPastGenerationsPassword(PasswordChangeRequest request) {
        PasswordHistoryCriteria passwordHistoryCriteria = new PasswordHistoryCriteria();

        passwordHistoryCriteria.getOperatorIdCriteria().setEqualTo(request.getOperatorId());
        passwordHistoryCriteria.getChangeTypeCriteria().setNotEqualTo(PasswordChangeType.機器認証パスワード.getCode());

        PasswordHistories passwordHistories = passwordHistoriesRepository.selectBy(passwordHistoryCriteria, Orders.empty().addOrder("ChangeDateTime", Order.DESC));

        int counter = 0;
        for (PasswordHistory passwordHistory : passwordHistories.getValues()) {

            // リクエストの古いパスワードと現在のパスワードが同じか
            if (counter == 0) {
                if (!request.getOldPassword().equals(passwordHistory.getPassword())) {
                    throw new GunmaRuntimeException("EOA12003");
                }
            }

            // 現在と過去2世代に同じパスワードを使用していないか
            if (counter <= 2) {
                if (request.getNewPassword().equals(passwordHistory.getPassword())) {
                    throw new GunmaRuntimeException("EOA12004");
                }
            } else {
                break;
            }

            counter ++;
        }
    }
}
