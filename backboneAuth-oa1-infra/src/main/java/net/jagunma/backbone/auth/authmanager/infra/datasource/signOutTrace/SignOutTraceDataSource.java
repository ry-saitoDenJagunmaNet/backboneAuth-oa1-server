package net.jagunma.backbone.auth.authmanager.infra.datasource.signOutTrace;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
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
 * サインアウト証跡群検索
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

        // オペレーター群の検索
        OperatorCriteria operatorCriteria = new OperatorCriteria();
        operatorCriteria.getOperatorIdCriteria().assignFrom(signOutTraceCriteria.getOperatorIdCriteria());
        Operators operators = operatorRepository.selectBy(operatorCriteria, Orders.empty());

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
