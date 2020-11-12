package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import java.time.LocalDateTime;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorUpdatePack;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.OperatorHistoryPackRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoriesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.common.ddd.model.orders.Order;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.springframework.stereotype.Component;

/**
 * オペレーター格納
 */
@Component
public class OperatorForStoreDataSource implements OperatorRepositoryForStore {

    private final OperatorEntityDao operatorEntityDao;
    private final OperatorHistoryPackRepositoryForStore operatorHistoryPackRepositoryForStore;
    private final PasswordHistoriesRepository passwordHistoriesRepository;
    private final PasswordHistoryRepositoryForStore passwordHistoryRepositoryForStore;

    // コンストラクタ
    public OperatorForStoreDataSource(
        OperatorEntityDao operatorEntityDao,
        OperatorHistoryPackRepositoryForStore operatorHistoryPackRepositoryForStore,
        PasswordHistoriesRepository passwordHistoriesRepository,
        PasswordHistoryRepositoryForStore passwordHistoryRepositoryForStore) {

        this.operatorEntityDao = operatorEntityDao;
        this.operatorHistoryPackRepositoryForStore = operatorHistoryPackRepositoryForStore;
        this.passwordHistoriesRepository = passwordHistoriesRepository;
        this.passwordHistoryRepositoryForStore = passwordHistoryRepositoryForStore;
    }

    /**
     * オペレーターの登録を行います
     *
     * @param operatorEntryPack オペレーターエントリーパック
     */
    public void entry(OperatorEntryPack operatorEntryPack) {

        // オペレーター（コード）がすでに存在しているかのチェックを行います
        checkAlreadyExists(operatorEntryPack.getOperatorCode());

        // オペレーターのインサートを行います
        OperatorEntity operatorEntity = insertOperator(operatorEntryPack);

        // オペレーター履歴パックの格納を行います
        operatorHistoryPackRepositoryForStore.store(operatorEntity.getOperatorId(), operatorEntryPack.getChangeCause());

        // パスワード履歴の格納を行います
        storePasswordHistory(operatorEntity.getOperatorId(), operatorEntity.getCreatedAt(), operatorEntryPack.getPassword(), PasswordChangeType.初期);
    }

    /**
     * オペレーターの更新を行います
     *
     * @param operatorUpdatePack オペレーターアップデートパック
     */
    public void update(OperatorUpdatePack operatorUpdatePack) {

        // 機器認証の変更有無を判定します
        Boolean isChangeDeviceAuth = isChangeDeviceAuth(operatorUpdatePack);

        // オペレーターのアップデートを行います
        OperatorEntity operatorEntity = updateOperator(operatorUpdatePack);

        // オペレーター履歴パックの格納を行います
        operatorHistoryPackRepositoryForStore.store(operatorUpdatePack.getOperatorId(), operatorUpdatePack.getChangeCause());

        // パスワード履歴の格納を行います（機器認証に変更があった場合）
        if (isChangeDeviceAuth) {
            storePasswordHistory(operatorUpdatePack.getOperatorId(), operatorEntity.getUpdatedAt(), operatorUpdatePack.getIsDeviceAuth());
        }
    }

    /**
     * オペレーター（コード）がすでに存在しているかのチェックを行います
     *
     * @param operatorCode オペレーターコード
     */
    void checkAlreadyExists(String operatorCode) {
        OperatorEntityCriteria operatorEntityCriteria = new OperatorEntityCriteria();

        operatorEntityCriteria.getOperatorCodeCriteria().setEqualTo(operatorCode);

        if (operatorEntityDao.countBy(operatorEntityCriteria) > 0 ) {
            throw new GunmaRuntimeException("EOA11001", "オペレーターコード", operatorCode);
        }
    }
    /**
     * 機器認証の変更有無を判定します
     *
     * @param operatorUpdatePack オペレーターアップデートパック
     * @return 有無
     */
    Boolean isChangeDeviceAuth(OperatorUpdatePack operatorUpdatePack) {
        OperatorEntityCriteria operatorEntityCriteria = new OperatorEntityCriteria();

        operatorEntityCriteria.getOperatorIdCriteria().setEqualTo(operatorUpdatePack.getOperatorId());

        OperatorEntity operatorEntity = operatorEntityDao.findOneBy(operatorEntityCriteria);

        if (operatorEntity.getIsDeviceAuth() != operatorUpdatePack.getIsDeviceAuth()) {
            return true;
        }

        return false;
    }

    /**
     * オペレーターのインサートを行います
     *
     * @param operatorEntryPack オペレーターエントリーパック
     * @return オペレーターエンティティ
     */
    OperatorEntity insertOperator(OperatorEntryPack operatorEntryPack) {
        OperatorEntity operatorEntity = new OperatorEntity();

        operatorEntity.setOperatorCode(operatorEntryPack.getOperatorCode());
        operatorEntity.setOperatorName(operatorEntryPack.getOperatorName());
        operatorEntity.setMailAddress(operatorEntryPack.getMailAddress());
        operatorEntity.setExpirationStartDate(operatorEntryPack.getExpirationStartDate());
        operatorEntity.setExpirationEndDate(operatorEntryPack.getExpirationEndDate());
        operatorEntity.setIsDeviceAuth(false);
        operatorEntity.setJaId(operatorEntryPack.getJaId());
        operatorEntity.setJaCode(operatorEntryPack.getJaCode());
        operatorEntity.setBranchId(operatorEntryPack.getBranchId());
        operatorEntity.setBranchCode(operatorEntryPack.getBranchCode());
        operatorEntity.setAvailableStatus(AvailableStatus.利用可能.getCode());

        operatorEntityDao.insert(operatorEntity);

        return operatorEntity;
    }
    /**
     * オペレーターのアップデートを行います
     *
     * @param operatorUpdatePack オペレーターアップデートパック
     * @return オペレーターエンティティ
     */
    OperatorEntity updateOperator(OperatorUpdatePack operatorUpdatePack) {
        OperatorEntity operatorEntity = new OperatorEntity();

        operatorEntity.setOperatorId(operatorUpdatePack.getOperatorId());
        operatorEntity.setOperatorName(operatorUpdatePack.getOperatorName());
        operatorEntity.setMailAddress(operatorUpdatePack.getMailAddress());
        operatorEntity.setExpirationStartDate(operatorUpdatePack.getExpirationStartDate());
        operatorEntity.setExpirationEndDate(operatorUpdatePack.getExpirationEndDate());
        operatorEntity.setIsDeviceAuth(operatorUpdatePack.getIsDeviceAuth());
        operatorEntity.setBranchId(operatorUpdatePack.getBranchId());
        operatorEntity.setBranchCode(operatorUpdatePack.getBranchCode());
        operatorEntity.setAvailableStatus(operatorUpdatePack.getAvailableStatus().getCode());
        operatorEntity.setRecordVersion(operatorUpdatePack.getRecordVersion());

        operatorEntityDao.update(operatorEntity);

        OperatorEntityCriteria operatorEntityCriteria = new OperatorEntityCriteria();
        operatorEntityCriteria.getOperatorIdCriteria().setEqualTo(operatorUpdatePack.getOperatorId());

        return operatorEntityDao.findOneBy(operatorEntityCriteria);
    }

    /**
     * パスワード履歴の格納を行います
     *
     * @param operatorId オペレーターID
     * @param changeDateTime 変更日時
     * @param password パスワード
     * @param passwordChangeType 変更種別
     * @return パスワード履歴
     */
    PasswordHistory storePasswordHistory(Long operatorId, LocalDateTime changeDateTime, String password, PasswordChangeType passwordChangeType) {

       PasswordHistory passwordHistory = PasswordHistory.createFrom(
           null,
           operatorId,
           changeDateTime,
           password,
           passwordChangeType,
           null,
           null);

       passwordHistoryRepositoryForStore.store(passwordHistory);

       return passwordHistory;
   }
    /**
     * パスワード履歴の格納を行います
     *
     * @param operatorId オペレーターID
     * @param changeDateTime 変更日時
     * @param isDeviceAuth 機器認証
     * @return パスワード履歴
     */
    PasswordHistory storePasswordHistory(Long operatorId, LocalDateTime changeDateTime, boolean isDeviceAuth) {
        PasswordHistoryCriteria passwordHistoryCriteria = new PasswordHistoryCriteria();

        passwordHistoryCriteria.getOperatorIdCriteria().setEqualTo(operatorId);

        PasswordHistories passwordHistories = passwordHistoriesRepository.selectBy(passwordHistoryCriteria, Orders.empty().addOrder("ChangeDateTime", Order.DESC));

        if (isDeviceAuth) {
            // 前回の「パスワード」と 「変更種別」機器認証パスワード を補完
            return storePasswordHistory(operatorId, changeDateTime, passwordHistories.getValues().get(0).getPassword(), PasswordChangeType.機器認証パスワード);
        } else {
            // 前回の「パスワード」と 前々回の「変更種別」を補完
            return storePasswordHistory(operatorId, changeDateTime, passwordHistories.getValues().get(0).getPassword(), passwordHistories.getValues().get(1).getPasswordChangeType());
        }
    }
}