package net.jagunma.backbone.auth.authmanager.infra.datasource.operator_SubSystemRole;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.OperatorHistoryPackRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntity;
import net.jagunma.backbone.auth.model.dao.operator_SubSystemRole.Operator_SubSystemRoleEntityDao;
import org.springframework.stereotype.Component;

/**
 * オペレーター_サブシステムロール割当群格納
 */
@Component
public class Operator_SubSystemRoleForStoreDataSource implements Operator_SubSystemRoleRepositoryForStore {

    private final Operator_SubSystemRoleEntityDao operator_SubSystemRoleEntityDao;
    private final OperatorHistoryPackRepositoryForStore operatorHistoryPackRepositoryForStore;

    // コンストラクタ
    public Operator_SubSystemRoleForStoreDataSource(
        Operator_SubSystemRoleEntityDao operator_SubSystemRoleEntityDao,
        OperatorHistoryPackRepositoryForStore operatorHistoryPackRepositoryForStore) {

        this.operator_SubSystemRoleEntityDao = operator_SubSystemRoleEntityDao;
        this.operatorHistoryPackRepositoryForStore = operatorHistoryPackRepositoryForStore;
    }

    /**
     * オペレーター_サブシステムロール割当群の格納を行います
     *
     * @param operator_SubSystemRoles オペレーター_サブシステムロール割当群
     * @param changeCause 変更事由
     */
    public void store(Operator_SubSystemRoles operator_SubSystemRoles, String changeCause) {

        // オペレーター_サブシステムロール割当のインサートを行います
        List<Operator_SubSystemRoleEntity> operator_SubSystemRoleEntityList = insertOperator_SubSystemRole(operator_SubSystemRoles);

        // オペレーター履歴パックの格納を行います
        operatorHistoryPackRepositoryForStore.store(operator_SubSystemRoles.getValues().get(0).getOperatorId(), operator_SubSystemRoleEntityList.get(0).getCreatedAt(), changeCause);
    }

    /**
     * オペレーター_サブシステムロール割当のインサートを行います
     *
     * @param operator_SubSystemRoles オペレーター_サブシステムロール割当群
     * @return オペレーター_サブシステムロール割当エンティティリスト
     */
    List<Operator_SubSystemRoleEntity> insertOperator_SubSystemRole(Operator_SubSystemRoles operator_SubSystemRoles) {

        LocalDateTime createdAt = LocalDateTime.now();

        List<Operator_SubSystemRoleEntity> operator_SubSystemRoleEntityList = newArrayList();

        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()) {
            Operator_SubSystemRoleEntity operator_SubSystemRoleEntity = new Operator_SubSystemRoleEntity();

            operator_SubSystemRoleEntity.setOperatorId(operator_SubSystemRole.getOperatorId());
            operator_SubSystemRoleEntity.setSubSystemRoleCode(operator_SubSystemRole.getSubSystemRoleCode());
            operator_SubSystemRoleEntity.setValidThruStartDate(operator_SubSystemRole.getValidThruStartDate());
            operator_SubSystemRoleEntity.setValidThruEndDate(operator_SubSystemRole.getValidThruEndDate());
            operator_SubSystemRoleEntity.setCreatedAt(createdAt);

            operator_SubSystemRoleEntityDao.insert(operator_SubSystemRoleEntity);

            operator_SubSystemRoleEntityList.add(operator_SubSystemRoleEntity);
        }

        return operator_SubSystemRoleEntityList;
    }
}
