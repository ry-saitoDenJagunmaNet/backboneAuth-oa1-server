package net.jagunma.backbone.auth.authmanager.infra.datasource.signOutTrace;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraceRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraces;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntity;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntityCriteria;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * サインアウト証跡検索
 */
@Component
public class SignOutTraceDataSource implements SignOutTraceRepository {

    private final SignOutTraceEntityDao signOutTraceEntityDao;
    private final OperatorRepository operatorRepository;

    // コンストラクタ
    SignOutTraceDataSource(SignOutTraceEntityDao signOutTraceEntityDao,
        OperatorRepository operatorRepository) {

        this.signOutTraceEntityDao = signOutTraceEntityDao;
        this.operatorRepository = operatorRepository;
    }

    /**
     * サインアウト証跡群の検索を行います
     *
     * @param signOutTraceCriteria サインアウト証跡の検索条件
     * @param orders               オーダー指定
     * @return サインアウト証跡群
     */
    public SignOutTraces selectBy(SignOutTraceCriteria signOutTraceCriteria, Orders orders) {

        // サインアウト証跡群検索
        List<SignOutTraceEntity> entityList = signOutTraceEntityDao.findBy(convertCriteria(signOutTraceCriteria), orders);
        if (entityList.size() == 0) { return SignOutTraces.createFrom(newArrayList()); }

        // オペレーター群の検索
        OperatorCriteria operatorCriteria = new OperatorCriteria();
        operatorCriteria.getOperatorIdCriteria().getIncludes().addAll(
            entityList.stream().map(SignOutTraceEntity::getOperatorId).distinct().collect(Collectors.toList()));
        Operators operators = operatorRepository.selectBy(operatorCriteria, Orders.empty());

        List<SignOutTrace> list = newArrayList();
        for (SignOutTraceEntity entity : entityList) {
            list.add(SignOutTrace.createFrom(
                entity.getSignOutTraceId(),
                entity.getSignOutDateTime(),
                entity.getSignOutIpAddress(),
                entity.getOperatorId(),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->
                    o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null)
            ));
        }

        return SignOutTraces.createFrom(list);
    }

    /**
     * 最新オペレーターのサインアウト証跡群の検索を行います
     *
     * @param signOutTraceCriteria サインアウト証跡の検索条件
     * @return サインアウト証跡群
     */
    public SignOutTraces latestBy(SignOutTraceCriteria signOutTraceCriteria, Orders orders) {

        // サインアウト証跡群検索
        List<SignOutTraceEntity> entityList = signOutTraceEntityDao.findBy(convertCriteria(signOutTraceCriteria), orders);
        if (entityList.size() == 0) { return SignOutTraces.createFrom(newArrayList()); }

        // オペレーター群の検索
        OperatorCriteria operatorCriteria = new OperatorCriteria();
        operatorCriteria.getOperatorIdCriteria().getIncludes().addAll(
            entityList.stream().map(SignOutTraceEntity::getOperatorId).distinct().collect(Collectors.toList()));
        Operators operators = operatorRepository.selectBy(operatorCriteria, Orders.empty());

        List<SignOutTrace> list = newArrayList();
        // オペレーターコードでグルーピングしサインアウト日時が最新（最大）を抽出
        Map<Long, Optional<SignOutTraceEntity>> entityListMap = entityList.stream()
            .collect(Collectors.groupingBy(SignOutTraceEntity::getOperatorId,
                Collectors.maxBy(Comparator.comparing(SignOutTraceEntity::getSignOutDateTime))));

        for (Map.Entry<Long, Optional<SignOutTraceEntity>> entityMap : entityListMap.entrySet()) {
            SignOutTraceEntity entity =  entityMap.getValue().get();
            list.add(SignOutTrace.createFrom(
                entity.getSignOutTraceId(),
                entity.getSignOutDateTime(),
                entity.getSignOutIpAddress(),
                entity.getOperatorId(),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->
                    o.getOperatorId().equals(entity.getOperatorId())).findFirst().orElse(null)
            ));
        }

        return SignOutTraces.createFrom(list.stream().sorted(orders.toComparator()).collect(Collectors.toList()));
    }

    /**
     * サインアウト証跡EntityCriteriaへ変換します
     *
     * @param signOutTraceCriteria サインアウト証跡の検索条件
     * @return サインアウト証跡EntityCriteria
     */
    private SignOutTraceEntityCriteria convertCriteria(SignOutTraceCriteria signOutTraceCriteria) {
        SignOutTraceEntityCriteria entityCriteria = new SignOutTraceEntityCriteria();
        entityCriteria.getSignOutTraceIdCriteria().assignFrom(signOutTraceCriteria.getSignOutTraceIdCriteria());
        entityCriteria.getOperatorIdCriteria().assignFrom(signOutTraceCriteria.getOperatorIdCriteria());
        return entityCriteria;
    }
}
