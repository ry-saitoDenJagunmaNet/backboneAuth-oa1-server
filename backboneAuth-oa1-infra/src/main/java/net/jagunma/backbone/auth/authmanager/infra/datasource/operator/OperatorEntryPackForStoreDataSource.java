package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPackRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntityDao;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityDao;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import net.jagunma.common.util.beans.Beans;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.springframework.stereotype.Component;

/**
 * オペレーターエントリーパック登録
 */
@Component
public class OperatorEntryPackForStoreDataSource implements
    OperatorEntryPackRepositoryForStore {

    private final OperatorEntityDao operatorEntityDao;
    private final OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao;
    private final OperatorHistoryEntityDao operatorHistoryEntityDao;
    private final PasswordHistoryEntityDao passwordHistoryEntityDao;

    // コンストラクタ
    public OperatorEntryPackForStoreDataSource(
        OperatorEntityDao operatorEntityDao,
        OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao,
        OperatorHistoryEntityDao operatorHistoryEntityDao,
        PasswordHistoryEntityDao passwordHistoryEntityDao) {

        this.operatorEntityDao = operatorEntityDao;
        this.operatorHistoryHeaderEntityDao = operatorHistoryHeaderEntityDao;
        this.operatorHistoryEntityDao = operatorHistoryEntityDao;
        this.passwordHistoryEntityDao = passwordHistoryEntityDao;
    }

    /**
     * オペレーターエントリーパックのインサートを行います。
     *
     * @param operatorEntryPack オペレーターエントリーパック
     */
    public void insert(OperatorEntryPack operatorEntryPack) {

        // オペレーター（コード）がすでに存在しているかのチェックを行います
        checkAlreadyExists(operatorEntryPack.getOperatorCode());

        // オペレーターのインサートを行います
        OperatorEntity operatorEntity = insertOperator(operatorEntryPack);

        // オペレーター履歴ヘッダーのインサートを行います
        OperatorHistoryHeaderEntity operatorHistoryHeaderEntity = insertOperatorHistoryHeader(operatorEntryPack, operatorEntity);

        // オペレーター履歴のインサートを行います
        insertOperatorHistory(operatorHistoryHeaderEntity, operatorEntity);

        // パスワード履歴のインサートを行います
        insertPasswordHistory(operatorEntryPack, operatorEntity);
    }

    /**
     * オペレーター（コード）がすでに存在しているかのチェックを行います。
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
     * オペレーターのインサートを行います。
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
     * オペレーター履歴ヘッダーのインサートを行います。
     *
     * @param operatorEntryPack オペレーターエントリーパック
     * @param operatorEntity オペレーターエンティティ
     * @return オペレーター履歴ヘッダーエンティティ
     */
    OperatorHistoryHeaderEntity insertOperatorHistoryHeader(OperatorEntryPack operatorEntryPack, OperatorEntity operatorEntity) {
        OperatorHistoryHeaderEntity operatorHistoryHeaderEntity = new OperatorHistoryHeaderEntity();

        operatorHistoryHeaderEntity.setOperatorId(operatorEntity.getOperatorId());
        operatorHistoryHeaderEntity.setChangeDateTime(operatorEntity.getCreatedAt());
        operatorHistoryHeaderEntity.setChangeCause(operatorEntryPack.getChangeCause());

        operatorHistoryHeaderEntityDao.insert(operatorHistoryHeaderEntity);

        return operatorHistoryHeaderEntity;
    }

    /**
     * オペレーター履歴のインサートを行います。
     *
     * @param operatorHistoryHeaderEntity オペレーター履歴ヘッダーエンティティ
     * @param operatorEntity オペレーターエンティティ
     * @return オペレーター履歴エンティティ
     */
    OperatorHistoryEntity insertOperatorHistory(OperatorHistoryHeaderEntity operatorHistoryHeaderEntity, OperatorEntity operatorEntity) {

        OperatorHistoryEntity operatorHistoryEntity = Beans.createAndCopy(OperatorHistoryEntity.class, operatorEntity).execute();

        operatorHistoryEntity.setOperatorHistoryId(operatorHistoryHeaderEntity.getOperatorHistoryId());

        operatorHistoryEntityDao.insert(operatorHistoryEntity);

        return operatorHistoryEntity;
    }

    /**
     * パスワード履歴のインサートを行います。
     *
     * @param operatorEntryPack オペレーターエントリーパック
     * @param operatorEntity オペレーターエンティティ
     * @return パスワード履歴エンティティ
     */
    PasswordHistoryEntity insertPasswordHistory(OperatorEntryPack operatorEntryPack, OperatorEntity operatorEntity) {
        PasswordHistoryEntity passwordHistoryEntity = new PasswordHistoryEntity();

        passwordHistoryEntity.setOperatorId(operatorEntity.getOperatorId());
        passwordHistoryEntity.setChangeDateTime(operatorEntity.getCreatedAt());
        passwordHistoryEntity.setPassword(operatorEntryPack.getPassword());
        passwordHistoryEntity.setChangeType(PasswordChangeType.初期.getCode());

        passwordHistoryEntityDao.insert(passwordHistoryEntity);

        return passwordHistoryEntity;
    }
}
