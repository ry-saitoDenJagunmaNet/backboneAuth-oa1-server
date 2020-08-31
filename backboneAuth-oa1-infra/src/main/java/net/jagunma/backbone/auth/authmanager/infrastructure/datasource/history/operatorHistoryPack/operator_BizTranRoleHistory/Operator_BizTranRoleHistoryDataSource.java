package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.history.operatorHistoryPack.operator_BizTranRoleHistory;

import net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistoryPack.operator_BizTranRoleHistory.Operator_BizTranRoleHistory;
import net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistoryPack.operator_BizTranRoleHistory.Operator_BizTranRoleHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistoryPack.operator_BizTranRoleHistory.Operator_BizTranRoleHistoryRepository;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRoleHistory.Operator_BizTranRoleHistoryEntity;
import net.jagunma.backbone.auth.model.dao.operator_BizTranRoleHistory.Operator_BizTranRoleHistoryEntityDao;
import org.springframework.stereotype.Component;

/**
 * オペレーター_取引ロール割当履歴検索 DataSource
 */
@Component
public class Operator_BizTranRoleHistoryDataSource implements
    Operator_BizTranRoleHistoryRepository {

    private final Operator_BizTranRoleHistoryEntityDao operator_BizTranRoleHistoryEntityDao;

    // コンストラクタ
    public Operator_BizTranRoleHistoryDataSource(
        Operator_BizTranRoleHistoryEntityDao operator_BizTranRoleHistoryEntityDao) {
        this.operator_BizTranRoleHistoryEntityDao = operator_BizTranRoleHistoryEntityDao;
    }

    /**
     * オペレーター_取引ロール割当履歴の条件検索を行います。
     *
     * @param operator_BizTranRoleHistoryCriteria オペレーター_取引ロール割当履歴の検索条件
     * @return オペレーター_取引ロール割当履歴
     */
    @Override
    public Operator_BizTranRoleHistory findOneBy(
        Operator_BizTranRoleHistoryCriteria operator_BizTranRoleHistoryCriteria) {
        Operator_BizTranRoleHistoryEntity operator_BizTranRoleHistoryEntity = operator_BizTranRoleHistoryEntityDao
            .findOneBy(operator_BizTranRoleHistoryCriteria);
        return Operator_BizTranRoleHistory.of(operator_BizTranRoleHistoryEntity);
    }
}
