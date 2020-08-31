package net.jagunma.backbone.auth.authmanager.infrastructure.datasource.history.operatorHistoryPack.operatorHistoryPackHeader;

import net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistoryPack.operatorHistoryPackHeader.OperatorHistoryPackHeader;
import net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistoryPack.operatorHistoryPackHeader.OperatorHistoryPackHeaderCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.history.operatorHistoryPack.operatorHistoryPackHeader.OperatorHistoryPackHeaderRepository;
import net.jagunma.backbone.auth.model.dao.operatorHistoryPackHeader.OperatorHistoryPackHeaderEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistoryPackHeader.OperatorHistoryPackHeaderEntityDao;
import org.springframework.stereotype.Component;

/**
 * オペレーター履歴パックヘッダー検索 DataSource
 */
@Component
public class OperatorHistoryPackHeaderDataSource implements OperatorHistoryPackHeaderRepository {

    private final OperatorHistoryPackHeaderEntityDao operatorHistoryPackHeaderEntityDao;

    // コンストラクタ
    public OperatorHistoryPackHeaderDataSource(
        OperatorHistoryPackHeaderEntityDao operatorHistoryPackHeaderEntityDao) {
        this.operatorHistoryPackHeaderEntityDao = operatorHistoryPackHeaderEntityDao;
    }

    /**
     * オペレーター履歴パックヘッダーの条件検索を行います。
     *
     * @param operatorHistoryPackHeaderCriteria オペレーター履歴パックヘッダーの検索条件
     * @return オペレーター履歴パックヘッダー
     */
    @Override
    public OperatorHistoryPackHeader findOneBy(
        OperatorHistoryPackHeaderCriteria operatorHistoryPackHeaderCriteria) {
        OperatorHistoryPackHeaderEntity operatorHistoryPackHeaderEntity = operatorHistoryPackHeaderEntityDao
            .findOneBy(operatorHistoryPackHeaderCriteria);
        return OperatorHistoryPackHeader.of(operatorHistoryPackHeaderEntity);
    }
}
