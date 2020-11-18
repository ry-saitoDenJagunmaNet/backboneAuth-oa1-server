package net.jagunma.backbone.auth.authmanager.infra.datasource.signOutTrace;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorsRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraces;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTracesRepository;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntity;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntityCriteria;
import net.jagunma.backbone.auth.model.dao.signOutTrace.SignOutTraceEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * サインアウト証跡群検索
 */
@Component
public class SignOutTracesDataSource implements SignOutTracesRepository {

    private final SignOutTraceEntityDao signOutTraceEntityDao;
    private final OperatorsRepository operatorsRepository;

    // コンストラクタ
    SignOutTracesDataSource(SignOutTraceEntityDao signOutTraceEntityDao,
        OperatorsRepository operatorsRepository) {

        this.signOutTraceEntityDao = signOutTraceEntityDao;
        this.operatorsRepository = operatorsRepository;
    }

    /**
     * サインアウト証跡群の検索を行います
     *
     * @param signOutTraceCriteria サインアウト証跡の検索条件
     * @param orders               オーダー指定
     * @return サインアウト証跡群
     */
    public SignOutTraces selectBy(SignOutTraceCriteria signOutTraceCriteria, Orders orders) {

        // オペレーター群の検索
        OperatorCriteria operatorCriteria = new OperatorCriteria();
        operatorCriteria.getOperatorIdCriteria().assignFrom(signOutTraceCriteria.getOperatorIdCriteria());
        Operators operators = operatorsRepository.selectBy(operatorCriteria, Orders.empty());

        // サインアウト証跡群検索
        SignOutTraceEntityCriteria entityCriteria = new SignOutTraceEntityCriteria();
        entityCriteria.getOperatorIdCriteria().assignFrom(signOutTraceCriteria.getOperatorIdCriteria());

        List<SignOutTrace> list = newArrayList();
        for (SignOutTraceEntity entity : signOutTraceEntityDao.findBy(entityCriteria, orders)) {
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

}
