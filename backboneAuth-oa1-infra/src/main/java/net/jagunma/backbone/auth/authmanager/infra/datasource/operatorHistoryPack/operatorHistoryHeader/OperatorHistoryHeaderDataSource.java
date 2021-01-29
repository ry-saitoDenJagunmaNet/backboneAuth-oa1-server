package net.jagunma.backbone.auth.authmanager.infra.datasource.operatorHistoryPack.operatorHistoryHeader;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaders;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntity;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operatorHistoryHeader.OperatorHistoryHeaderEntityDao;
import net.jagunma.common.ddd.model.orders.Order;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.springframework.stereotype.Component;

/**
 * オペレーター履歴ヘッダー検索
 */
@Component
public class OperatorHistoryHeaderDataSource implements OperatorHistoryHeaderRepository {

    private final OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao;
    private final OperatorRepository operatorRepository;

    // コンストラクタ
    OperatorHistoryHeaderDataSource(OperatorHistoryHeaderEntityDao operatorHistoryHeaderEntityDao,
        OperatorRepository operatorRepository) {

        this.operatorHistoryHeaderEntityDao = operatorHistoryHeaderEntityDao;
        this.operatorRepository = operatorRepository;
    }

    /**
     * 対象オペレーターで最新のオペレーター履歴ヘッダーの検索を行います
     *
     * @param operatorId オペレーターID
     * @return 対象オペレーターの中で最新のオペレーター履歴ヘッダー
     */
    public OperatorHistoryHeader latestOneByOperatorId(Long operatorId) {

        // オペレーター履歴ヘッダー検索
        OperatorHistoryHeaderEntityCriteria entityCriteria = new OperatorHistoryHeaderEntityCriteria();
        entityCriteria.getOperatorIdCriteria().setEqualTo(operatorId);
        Orders orders = Orders.empty().addOrder("changeDateTime", Order.DESC);
        List<OperatorHistoryHeaderEntity> list = operatorHistoryHeaderEntityDao.findBy(entityCriteria, orders);

        if (list.size() == 0) {
            throw new GunmaRuntimeException("EOA11002", "オペレーター履歴ヘッダー", "OperatorId="+operatorId.toString());
        }

        // オペレーターの検索
        Operator operator = operatorRepository.findOneById(operatorId);

        OperatorHistoryHeaderEntity entity = list.get(0);
        return OperatorHistoryHeader.createFrom(
                entity.getOperatorHistoryId(),
                entity.getOperatorId(),
                entity.getChangeDateTime(),
                entity.getChangeCause(),
                entity.getRecordVersion(),
                operator);
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
        operatorCriteria.getOperatorIdCriteria().assignFrom(operatorHistoryHeaderCriteria.getOperatorIdCriteria());
        Operators operators = operatorRepository.selectBy(operatorCriteria, Orders.empty());

        // オペレーター履歴ヘッダー群検索
        OperatorHistoryHeaderEntityCriteria entityCriteria = new OperatorHistoryHeaderEntityCriteria();
        entityCriteria.getOperatorHistoryIdCriteria().assignFrom(operatorHistoryHeaderCriteria.getOperatorHistoryIdCriteria());
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
