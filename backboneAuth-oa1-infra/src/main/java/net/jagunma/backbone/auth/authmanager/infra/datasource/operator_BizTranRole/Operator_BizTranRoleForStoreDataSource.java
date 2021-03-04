package net.jagunma.backbone.auth.authmanager.infra.datasource.operator_BizTranRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.OperatorHistoryPackRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntity;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRole.Operator_BizTranRoleEntityDao;
import org.springframework.stereotype.Component;

/**
 * オペレーター_取引ロール割当群格納
 */
@Component
public class Operator_BizTranRoleForStoreDataSource implements Operator_BizTranRoleRepositoryForStore {

    private final Operator_BizTranRoleEntityDao operator_BizTranRoleEntityDao;
    private final OperatorHistoryPackRepositoryForStore operatorHistoryPackRepositoryForStore;

    // コンストラクタ
    public Operator_BizTranRoleForStoreDataSource(
        Operator_BizTranRoleEntityDao operator_BizTranRoleEntityDao,
        OperatorHistoryPackRepositoryForStore operatorHistoryPackRepositoryForStore) {

        this.operator_BizTranRoleEntityDao = operator_BizTranRoleEntityDao;
        this.operatorHistoryPackRepositoryForStore = operatorHistoryPackRepositoryForStore;
    }

    /**
     * オペレーター_取引ロール割当群の格納を行います
     *
     * @param operatorId オペレーターID
     * @param operator_BizTranRoles オペレーター_取引ロール割当群
     * @param changeCause 変更事由
     */
    public void store(Long operatorId, Operator_BizTranRoles operator_BizTranRoles, String changeCause) {

        // オペレーター_取引ロール割当のデリートを行います
        deleteOperator_BizTranRole(operatorId);

        // オペレーター_取引ロール割当のインサートを行います
        List<Operator_BizTranRoleEntity> operator_BizTranRoleEntityList = insertOperator_BizTranRole(operator_BizTranRoles);

        // オペレーター履歴パックの格納を行います
        operatorHistoryPackRepositoryForStore.store(operatorId, operator_BizTranRoleEntityList.get(0).getCreatedAt(), changeCause);
    }

    /**
     * オペレーター_取引ロール割当のデリートを行います
     *
     * @param operatorId オペレーターID
     */
    void deleteOperator_BizTranRole(Long operatorId) {
        Operator_BizTranRoleEntityCriteria operator_BizTranRoleEntityCriteria = new Operator_BizTranRoleEntityCriteria();
        operator_BizTranRoleEntityCriteria.getOperatorIdCriteria().setEqualTo(operatorId);

        operator_BizTranRoleEntityDao.forceDelete(operator_BizTranRoleEntityCriteria);
    }

    /**
     * オペレーター_取引ロール割当のインサートを行います
     *
     * @param operator_BizTranRoles オペレーター_取引ロール割当群
     * @return オペレーター_取引ロール割当エンティティリスト
     */
    List<Operator_BizTranRoleEntity> insertOperator_BizTranRole(Operator_BizTranRoles operator_BizTranRoles) {

        LocalDateTime createdAt = LocalDateTime.now();

        List<Operator_BizTranRoleEntity> operator_BizTranRoleEntityList = newArrayList();

        for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoles.getValues()) {
            Operator_BizTranRoleEntity operator_BizTranRoleEntity = new Operator_BizTranRoleEntity();

            operator_BizTranRoleEntity.setOperatorId(operator_BizTranRole.getOperatorId());
            operator_BizTranRoleEntity.setBizTranRoleId(operator_BizTranRole.getBizTranRoleId());
            operator_BizTranRoleEntity.setValidThruStartDate(operator_BizTranRole.getValidThruStartDate());
            operator_BizTranRoleEntity.setValidThruEndDate(operator_BizTranRole.getValidThruEndDate());
            operator_BizTranRoleEntity.setCreatedAt(createdAt);

            operator_BizTranRoleEntityDao.insert(operator_BizTranRoleEntity);

            operator_BizTranRoleEntityList.add(operator_BizTranRoleEntity);
        }

        return operator_BizTranRoleEntityList;
    }
}
