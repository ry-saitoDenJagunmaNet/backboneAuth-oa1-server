package net.jagunma.backbone.auth.authmanager.infra.datasource.signInTrace;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.datasource.operator.OperatorsDataSource;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraces;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTracesRepository;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntity;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntityCriteria;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * サインイン証跡群検索
 */
@Component
public class SignInTracesDataSource implements SignInTracesRepository {

    private final SignInTraceEntityDao signInTraceEntityDao;
    private final OperatorsDataSource operatorsDataSource;

    // コンストラクタ
    SignInTracesDataSource(SignInTraceEntityDao signInTraceEntityDao,
        OperatorsDataSource operatorsDataSource) {

        this.signInTraceEntityDao = signInTraceEntityDao;
        this.operatorsDataSource = operatorsDataSource;
    }

    /**
     * サインイン証跡群の検索を行います。
     *
     * @param signInTraceCriteria サインイン証跡の検索条件
     * @param orders              オーダー指定
     * @return サインイン証跡群
     */
    public SignInTraces selectBy(SignInTraceCriteria signInTraceCriteria, Orders orders) {

        // オペレーター群の検索
        OperatorCriteria operatorCriteria = new OperatorCriteria();
        operatorCriteria.getOperatorCodeCriteria().getIncludes().addAll(signInTraceCriteria.getOperatorCodeCriteria().getIncludes());
        Operators operators = operatorsDataSource.selectBy(operatorCriteria, Orders.empty());

        // サインイン証跡群検索
        SignInTraceEntityCriteria entityCriteria = new SignInTraceEntityCriteria();
        entityCriteria.getOperatorCodeCriteria().getIncludes().addAll(signInTraceCriteria.getOperatorCodeCriteria().getIncludes());

        List<SignInTrace> list = newArrayList();
        for (SignInTraceEntity entity : signInTraceEntityDao.findBy(entityCriteria, orders)) {
            list.add(SignInTrace.createFrom(
                entity.getSignInTraceId(),
                entity.getTryDateTime(),
                entity.getTryIpAddress(),
                entity.getOperatorCode(),
                entity.getSignInCause(),
                entity.getSignInResult(),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->
                    o.getOperatorCode().equals(entity.getOperatorCode())).findFirst().orElse(null)
            ));
        }

        return SignInTraces.createFrom(list);
    }
}
