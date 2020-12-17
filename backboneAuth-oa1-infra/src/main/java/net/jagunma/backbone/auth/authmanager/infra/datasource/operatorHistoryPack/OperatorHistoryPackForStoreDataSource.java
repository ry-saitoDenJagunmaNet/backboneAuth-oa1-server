package net.jagunma.backbone.auth.authmanager.infra.datasource.operatorHistoryPack;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.OperatorHistoryPackRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
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
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.util.beans.Beans;
import org.springframework.stereotype.Component;

/**
 * オペレーター履歴格納
 */
@Component
public class OperatorHistoryPackForStoreDataSource implements OperatorHistoryPackRepositoryForStore {

    private final OperatorEntityDao operatorEntityDao;
    private final OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao;
    private final OperatorHistoryEntityDao operatorHistoryEntityDao;
    private final Operator_SubSystemRoleRepository operator_SubSystemRoleRepository;
    private final Operator_SubSystemRoleHistoryEntityDao operator_SubSystemRoleHistoryEntityDao;
    private final Operator_BizTranRoleRepository operator_BizTranRoleRepository;
    private final Operator_BizTranRoleHistoryEntityDao operator_BizTranRoleHistoryEntityDao;

    // コンストラクタ
    public OperatorHistoryPackForStoreDataSource(
        OperatorEntityDao operatorEntityDao,
        OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao,
        OperatorHistoryEntityDao operatorHistoryEntityDao,
        Operator_SubSystemRoleRepository operator_SubSystemRoleRepository,
        Operator_SubSystemRoleHistoryEntityDao operator_SubSystemRoleHistoryEntityDao,
        Operator_BizTranRoleRepository operator_BizTranRoleRepository,
        Operator_BizTranRoleHistoryEntityDao operator_BizTranRoleHistoryEntityDao) {

        this.operatorEntityDao = operatorEntityDao;
        this.operatorHistoryHeaderEntityDao = operatorHistoryHeaderEntityDao;
        this.operatorHistoryEntityDao = operatorHistoryEntityDao;
        this.operator_SubSystemRoleRepository = operator_SubSystemRoleRepository;
        this.operator_SubSystemRoleHistoryEntityDao = operator_SubSystemRoleHistoryEntityDao;
        this.operator_BizTranRoleRepository = operator_BizTranRoleRepository;
        this.operator_BizTranRoleHistoryEntityDao = operator_BizTranRoleHistoryEntityDao;
    }

    /**
     * オペレーター履歴の格納を行います
     *
     * @param operatorId オペレーターID
     * @param changeDateTime 変更日時
     * @param changeCause 変更事由
     */
    public void store(Long operatorId, LocalDateTime changeDateTime, String changeCause) {

        // オペレーターエンティティを取得します
        OperatorEntity operatorEntity = getOperatorEntity(operatorId);

        // オペレーター履歴ヘッダーのインサートを行います
        OperatorHistoryHeaderEntity operatorHistoryHeaderEntity = insertOperatorHistoryHeader(operatorId, changeDateTime, changeCause);

        // オペレーター履歴のインサートを行います
        insertOperatorHistory(operatorHistoryHeaderEntity.getOperatorHistoryId(), operatorEntity);

        // オペレーター_サブシステムロール割当履歴のインサートを行います
        insertOperator_SubSystemRoleHistory(operatorHistoryHeaderEntity.getOperatorHistoryId(), operatorHistoryHeaderEntity.getOperatorId());

        // オペレーター_取引ロール割当履歴のインサートを行います
        insertOperator_BizTranRoleHistory(operatorHistoryHeaderEntity.getOperatorHistoryId(), operatorHistoryHeaderEntity.getOperatorId());

    }

    /**
     * オペレーターエンティティを取得します
     *
     * @param operatorId オペレーターID
     * @return オペレーターエンティティ
     */
    OperatorEntity getOperatorEntity(Long operatorId) {
        OperatorEntityCriteria operatorEntityCriteria = new OperatorEntityCriteria();

        operatorEntityCriteria.getOperatorIdCriteria().setEqualTo(operatorId);

        return operatorEntityDao.findOneBy(operatorEntityCriteria);
    }

    /**
     * オペレーター履歴ヘッダーのインサートを行います
     *
     * @param operatorId オペレーターID
     * @param changeDateTime 変更日時
     * @param changeCause 変更事由
     * @return オペレーター履歴ヘッダーエンティティ
     */
    OperatorHistoryHeaderEntity insertOperatorHistoryHeader(Long operatorId, LocalDateTime changeDateTime, String changeCause) {
        OperatorHistoryHeaderEntity operatorHistoryHeaderEntity = new OperatorHistoryHeaderEntity();

        operatorHistoryHeaderEntity.setOperatorId(operatorId);
        operatorHistoryHeaderEntity.setChangeDateTime(changeDateTime);
        operatorHistoryHeaderEntity.setChangeCause(changeCause);

        operatorHistoryHeaderEntityDao.insert(operatorHistoryHeaderEntity);

        return operatorHistoryHeaderEntity;
    }

    /**
     * オペレーター履歴のインサートを行います
     *
     * @param operatorHistoryId オペレーター履歴ID
     * @param operatorEntity オペレーターエンティティ
     * @return オペレーター履歴エンティティ
     */
    OperatorHistoryEntity insertOperatorHistory(Long operatorHistoryId, OperatorEntity operatorEntity) {

        OperatorHistoryEntity operatorHistoryEntity = Beans.createAndCopy(OperatorHistoryEntity.class, operatorEntity).execute();
        operatorHistoryEntity.setOperatorHistoryId(operatorHistoryId);
        operatorHistoryEntity.setCreatedBy(null);
        operatorHistoryEntity.setCreatedAt(null);
        operatorHistoryEntity.setCreatedIpAddress(null);
        operatorHistoryEntity.setUpdatedBy(null);
        operatorHistoryEntity.setUpdatedAt(null);
        operatorHistoryEntity.setUpdatedIpAddress(null);
        operatorHistoryEntity.setRecordVersion(null);

        operatorHistoryEntityDao.insert(operatorHistoryEntity);

        return operatorHistoryEntity;
    }

    /**
     * オペレーター_サブシステムロール割当履歴のインサートを行います
     *
     * @param operatorHistoryId オペレーター履歴ID
     * @param operatorId オペレーターID
     * @return オペレーター_サブシステムロール割当履歴エンティティリスト
     */
    List<Operator_SubSystemRoleHistoryEntity> insertOperator_SubSystemRoleHistory(Long operatorHistoryId, Long operatorId) {
        List<Operator_SubSystemRoleHistoryEntity> operator_SubSystemRoleHistoryEntityList = newArrayList();

        // 現在の割当を取得
        Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria = new Operator_SubSystemRoleCriteria();
        operator_SubSystemRoleCriteria.getOperatorIdCriteria().setEqualTo(operatorId);
        Operator_SubSystemRoles operator_SubSystemRoles = operator_SubSystemRoleRepository.selectBy(operator_SubSystemRoleCriteria,
            Orders.empty().addOrder("Operator_SubSystemRoleId"));

        // インサート
        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()) {

            Operator_SubSystemRoleHistoryEntity operator_SubSystemRoleHistoryEntity = Beans.createAndCopy(Operator_SubSystemRoleHistoryEntity.class, operator_SubSystemRole).execute();
            operator_SubSystemRoleHistoryEntity.setOperatorHistoryId(operatorHistoryId);

            operator_SubSystemRoleHistoryEntityDao.insert(operator_SubSystemRoleHistoryEntity);

            operator_SubSystemRoleHistoryEntityList.add(operator_SubSystemRoleHistoryEntity);
        }

        return operator_SubSystemRoleHistoryEntityList;
    }

    /**
     * オペレーター_取引ロール割当履歴のインサートを行います
     *
     * @param operatorHistoryId オペレーター履歴ID
     * @param operatorId オペレーターID
     * @return オペレーター_取引ロール割当履歴エンティティリスト
     */
    List<Operator_BizTranRoleHistoryEntity> insertOperator_BizTranRoleHistory(Long operatorHistoryId, Long operatorId) {
        List<Operator_BizTranRoleHistoryEntity> operator_BizTranRoleHistoryEntityList = newArrayList();

        // 現在の割当を取得
        Operator_BizTranRoleCriteria operator_BizTranRoleCriteria = new Operator_BizTranRoleCriteria();
        operator_BizTranRoleCriteria.getOperatorIdCriteria().setEqualTo(operatorId);
        Operator_BizTranRoles operator_BizTranRoles = operator_BizTranRoleRepository.selectBy(operator_BizTranRoleCriteria,
            Orders.empty().addOrder("operator_BizTranRoleId"));

        // インサート
        for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoles.getValues()) {

            Operator_BizTranRoleHistoryEntity operator_BizTranRoleHistoryEntity = Beans.createAndCopy(Operator_BizTranRoleHistoryEntity.class, operator_BizTranRole).execute();
            operator_BizTranRoleHistoryEntity.setOperatorHistoryId(operatorHistoryId);

            operator_BizTranRoleHistoryEntityDao.insert(operator_BizTranRoleHistoryEntity);

            operator_BizTranRoleHistoryEntityList.add(operator_BizTranRoleHistoryEntity);
        }

        return operator_BizTranRoleHistoryEntityList;
    }
}
