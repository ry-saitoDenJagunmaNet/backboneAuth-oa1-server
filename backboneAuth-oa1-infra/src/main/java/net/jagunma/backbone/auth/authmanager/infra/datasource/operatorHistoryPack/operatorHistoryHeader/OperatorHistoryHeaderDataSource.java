package net.jagunma.backbone.auth.authmanager.infra.datasource.operatorHistoryPack.operatorHistoryHeader;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.datasource.operator.OperatorDataSource;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaders;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * オペレーター履歴ヘッダー群検索
 */
@Component
public class OperatorHistoryHeaderDataSource implements OperatorHistoryHeaderRepository {

    private final OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao;
    private final OperatorDataSource operatorDataSource;

    // コンストラクタ
    OperatorHistoryHeaderDataSource(OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao,
        OperatorDataSource operatorDataSource) {

        this.operatorHistoryHeaderEntityDao = operatorHistoryHeaderEntityDao;
        this.operatorDataSource = operatorDataSource;
    }

    /**
     * オペレーター履歴ヘッダー群の検索を行います
     *
     * @param operatorHistoryHeaderCriteria オペレーター履歴ヘッダーの検索条件
     * @param orders                        オーダー指定
     * @return オペレーター履歴ヘッダー群
     */
    public OperatorHistoryHeaders selectBy(OperatorHistoryHeaderCriteria operatorHistoryHeaderCriteria, Orders orders) {

        // オペレーターの検索
        OperatorCriteria operatorCriteria = new OperatorCriteria();
        operatorCriteria.getOperatorIdCriteria().getIncludes().addAll(operatorHistoryHeaderCriteria.getOperatorIdCriteria().getIncludes());
        Operators operators = operatorDataSource.selectBy(operatorCriteria, Orders.empty());

        // オペレーター履歴ヘッダー群検索
        OperatorHistoryHeaderEntityCriteria entityCriteria = new OperatorHistoryHeaderEntityCriteria();
        entityCriteria.getOperatorIdCriteria().assignFrom(operatorHistoryHeaderCriteria.getOperatorIdCriteria());

        List<OperatorHistoryHeader> list = newArrayList();
        for (OperatorHistoryHeaderEntity entity : operatorHistoryHeaderEntityDao.findBy(entityCriteria, orders)) {
            list.add(OperatorHistoryHeader.createFrom(
                entity.getOperatorHistoryId(),
                entity.getOperatorId(),
                entity.getChangeDateTime(),
                entity.getChangeCause(),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->
                    o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null)
            ));
        }
        return OperatorHistoryHeaders.createFrom(list);
    }
}
