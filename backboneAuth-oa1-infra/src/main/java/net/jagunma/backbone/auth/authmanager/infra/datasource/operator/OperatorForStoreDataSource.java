package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorUpdatePack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistory.OperatorHistoryEntityDao;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityDao;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRoleHistory.Operator_BizTranRoleHistoryEntity;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRoleHistory.Operator_BizTranRoleHistoryEntityDao;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoryEntity;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRoleHistory.Operator_SubSystemRoleHistoryEntityDao;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntity;
import net.jagunma.backbone.auth.model.dao.passwordHistory.PasswordHistoryEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.util.beans.Beans;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.springframework.stereotype.Component;

/**
 * オペレーター格納
 */
@Component
public class OperatorForStoreDataSource implements
    OperatorRepositoryForStore {

    private final OperatorEntityDao operatorEntityDao;
    private final OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao;
    private final OperatorHistoryEntityDao operatorHistoryEntityDao;
    private final PasswordHistoryEntityDao passwordHistoryEntityDao;
    private final Operator_SubSystemRoleHistoryEntityDao operator_SubSystemRoleHistoryEntityDao;
    private final Operator_BizTranRoleHistoryEntityDao operator_BizTranRoleHistoryEntityDao;
    private final Operator_SubSystemRolesRepository operator_SubSystemRolesRepository;
    private final Operator_BizTranRolesRepository operator_BizTranRolesRepository;

    // コンストラクタ
    public OperatorForStoreDataSource(
        OperatorEntityDao operatorEntityDao,
        OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao,
        OperatorHistoryEntityDao operatorHistoryEntityDao,
        PasswordHistoryEntityDao passwordHistoryEntityDao,
        Operator_SubSystemRoleHistoryEntityDao operator_SubSystemRoleHistoryEntityDao,
        Operator_BizTranRoleHistoryEntityDao operator_BizTranRoleHistoryEntityDao,
        Operator_SubSystemRolesRepository operator_SubSystemRolesRepository,
        Operator_BizTranRolesRepository operator_BizTranRolesRepository) {

        this.operatorEntityDao = operatorEntityDao;
        this.operatorHistoryHeaderEntityDao = operatorHistoryHeaderEntityDao;
        this.operatorHistoryEntityDao = operatorHistoryEntityDao;
        this.passwordHistoryEntityDao = passwordHistoryEntityDao;
        this.operator_SubSystemRoleHistoryEntityDao = operator_SubSystemRoleHistoryEntityDao;
        this.operator_BizTranRoleHistoryEntityDao = operator_BizTranRoleHistoryEntityDao;
        this.operator_SubSystemRolesRepository = operator_SubSystemRolesRepository;
        this.operator_BizTranRolesRepository = operator_BizTranRolesRepository;
    }

    /**
     * オペレーターの登録を行います。
     *
     * @param operatorEntryPack オペレーターエントリーパック
     */
    public void entry(OperatorEntryPack operatorEntryPack) {

        // オペレーター（コード）がすでに存在しているかのチェックを行います
        checkAlreadyExists(operatorEntryPack.getOperatorCode());

        // オペレーターのインサートを行います
        OperatorEntity operatorEntity = insertOperator(operatorEntryPack);

        // オペレーター履歴ヘッダーのインサートを行います
        OperatorHistoryHeaderEntity operatorHistoryHeaderEntity = insertOperatorHistoryHeader(operatorEntity, operatorEntryPack.getChangeCause());

        // オペレーター履歴のインサートを行います
        insertOperatorHistory(operatorHistoryHeaderEntity, operatorEntity);

        // パスワード履歴のインサートを行います
        insertPasswordHistory(operatorEntryPack, operatorEntity);
    }

    /**
     * オペレーターの更新を行います。
     *
     * @param operatorUpdatePack オペレーターアップデートパック
     */
    public void update(OperatorUpdatePack operatorUpdatePack) {

        // オペレーターが存在しているかのチェックを行います
        checkExists(operatorUpdatePack.getOperatorId());

        // オペレーターのアップデートを行います
        OperatorEntity operatorEntity = updateOperator(operatorUpdatePack);

        // オペレーター履歴ヘッダーのインサートを行います
        OperatorHistoryHeaderEntity operatorHistoryHeaderEntity = insertOperatorHistoryHeader(operatorEntity, operatorUpdatePack.getChangeCause());

        // オペレーター履歴のインサートを行います
        insertOperatorHistory(operatorHistoryHeaderEntity, operatorEntity);

        // オペレーター_サブシステムロール割当履歴のインサートを行います
        insertOperator_SubSystemRoleHistory(operatorHistoryHeaderEntity);

        // オペレーター_取引ロール割当履歴のインサートを行います
        insertOperator_BizTranRoleHistory(operatorHistoryHeaderEntity);
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
     * オペレーターが存在しているかのチェックを行います。
     *
     * @param operatorId オペレーターコード
     */
    void checkExists(Long operatorId) {
        OperatorEntityCriteria operatorEntityCriteria = new OperatorEntityCriteria();

        operatorEntityCriteria.getJaIdCriteria().setEqualTo(operatorId);

        if (operatorEntityDao.countBy(operatorEntityCriteria) == 0 ) {
            throw new GunmaRuntimeException("EOA11002", "オペレーターID", operatorId);
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
     * オペレーターのアップデートを行います。
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

        operatorEntityDao.update(operatorEntity);

        return operatorEntity;
    }

    /**
     * オペレーター履歴ヘッダーのインサートを行います。
     *
     * @param operatorEntity オペレーターエンティティ
     * @param changeCause 変更事由
     * @return オペレーター履歴ヘッダーエンティティ
     */
    OperatorHistoryHeaderEntity insertOperatorHistoryHeader(OperatorEntity operatorEntity, String changeCause) {
        OperatorHistoryHeaderEntity operatorHistoryHeaderEntity = new OperatorHistoryHeaderEntity();

        operatorHistoryHeaderEntity.setOperatorId(operatorEntity.getOperatorId());
        operatorHistoryHeaderEntity.setChangeDateTime(operatorEntity.getCreatedAt());
        operatorHistoryHeaderEntity.setChangeCause(changeCause);

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

    /**
     * オペレーター_サブシステムロール割当履歴のインサートを行います。
     *
     * @param operatorHistoryHeaderEntity オペレーター履歴ヘッダーエンティティ
     * @return オペレーター_サブシステムロール割当履歴エンティティリスト
     */
    List<Operator_SubSystemRoleHistoryEntity> insertOperator_SubSystemRoleHistory(OperatorHistoryHeaderEntity operatorHistoryHeaderEntity) {
        List<Operator_SubSystemRoleHistoryEntity> operator_SubSystemRoleHistoryEntityList = newArrayList();

        // 現割当取得
        Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria = new Operator_SubSystemRoleCriteria();
        operator_SubSystemRoleCriteria.getOperatorIdCriteria().setEqualTo(operatorHistoryHeaderEntity.getOperatorId());
        Operator_SubSystemRoles operator_SubSystemRoles = operator_SubSystemRolesRepository.selectBy(operator_SubSystemRoleCriteria,
            Orders.empty().addOrder("Operator_SubSystemRoleId"));

        // インサート
        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()){

            Operator_SubSystemRoleHistoryEntity operator_SubSystemRoleHistoryEntity = Beans.createAndCopy(Operator_SubSystemRoleHistoryEntity.class, operator_SubSystemRole).execute();
            operator_SubSystemRoleHistoryEntity.setOperatorHistoryId(operatorHistoryHeaderEntity.getOperatorHistoryId());

            operator_SubSystemRoleHistoryEntityDao.insert(operator_SubSystemRoleHistoryEntity);

            operator_SubSystemRoleHistoryEntityList.add(operator_SubSystemRoleHistoryEntity);
        }

        return operator_SubSystemRoleHistoryEntityList;
    }

    /**
     * オペレーター_取引ロール割当履歴のインサートを行います。
     *
     * @param operatorHistoryHeaderEntity オペレーター履歴ヘッダーエンティティ
     * @return オペレーター_取引ロール割当履歴エンティティリスト
     */
    List<Operator_BizTranRoleHistoryEntity> insertOperator_BizTranRoleHistory(OperatorHistoryHeaderEntity operatorHistoryHeaderEntity) {
        List<Operator_BizTranRoleHistoryEntity> operator_BizTranRoleHistoryEntityList = newArrayList();

        // 現割当取得
        Operator_BizTranRoleCriteria operator_BizTranRoleCriteria = new Operator_BizTranRoleCriteria();
        operator_BizTranRoleCriteria.getOperatorIdCriteria().setEqualTo(operatorHistoryHeaderEntity.getOperatorId());
        Operator_BizTranRoles operator_BizTranRoles = operator_BizTranRolesRepository.selectBy(operator_BizTranRoleCriteria,
            Orders.empty().addOrder("operator_BizTranRoleId"));

        // インサート
        for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoles.getValues()){

            Operator_BizTranRoleHistoryEntity operator_BizTranRoleHistoryEntity = Beans.createAndCopy(Operator_BizTranRoleHistoryEntity.class, operator_BizTranRole).execute();
            operator_BizTranRoleHistoryEntity.setOperatorHistoryId(operatorHistoryHeaderEntity.getOperatorHistoryId());

            operator_BizTranRoleHistoryEntityDao.insert(operator_BizTranRoleHistoryEntity);

            operator_BizTranRoleHistoryEntityList.add(operator_BizTranRoleHistoryEntity);
        }

        return operator_BizTranRoleHistoryEntityList;
    }
}
