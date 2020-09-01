package net.jagunma.backbone.auth.authmanager.infra.datasource.operator.operatorHistory.operatorHistoryHeader;

import net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operatorHistoryHeader.OperatorHistoryHeaderCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.operatorHistory.operatorHistoryHeader.OperatorHistoryHeaderRepository;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityDao;
import org.springframework.stereotype.Component;

/**
 * オペレーター履歴ヘッダー検索 DataSource
 */
@Component
public class OperatorHistoryHeaderDataSource implements OperatorHistoryHeaderRepository {

    private final OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao;

    // コンストラクタ
    public OperatorHistoryHeaderDataSource(OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao) {
        this.operatorHistoryHeaderEntityDao = operatorHistoryHeaderEntityDao;
    }

    /**
     * オペレーター履歴ヘッダーの条件検索を行います。
     *
     * @param operatorHistoryHeaderCriteria オペレーター履歴ヘッダーの検索条件
     * @return オペレーター履歴ヘッダー
     */
    @Override
    public OperatorHistoryHeader findOneBy(
        OperatorHistoryHeaderCriteria operatorHistoryHeaderCriteria) {
        OperatorHistoryHeaderEntity operatorHistoryHeaderEntity = operatorHistoryHeaderEntityDao.findOneBy(
            operatorHistoryHeaderCriteria);
        return OperatorHistoryHeader.of(operatorHistoryHeaderEntity);
    }
}
