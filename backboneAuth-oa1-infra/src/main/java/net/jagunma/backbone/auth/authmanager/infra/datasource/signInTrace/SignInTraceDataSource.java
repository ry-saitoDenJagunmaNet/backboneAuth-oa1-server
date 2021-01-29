package net.jagunma.backbone.auth.authmanager.infra.datasource.signInTrace;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraces;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntity;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntityCriteria;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * サインイン証跡検索
 */
@Component
public class SignInTraceDataSource implements SignInTraceRepository {

    private final SignInTraceEntityDao signInTraceEntityDao;
    private final OperatorRepository operatorRepository;

    // コンストラクタ
    SignInTraceDataSource(SignInTraceEntityDao signInTraceEntityDao,
        OperatorRepository operatorRepository) {

        this.signInTraceEntityDao = signInTraceEntityDao;
        this.operatorRepository = operatorRepository;
    }

    /**
     * サインイン証跡の検索を行います
     *
     * @param signInTraceId サインイン証跡ID
     * @return サインイン証跡
     */
    public SignInTrace findOneById(Long signInTraceId) {

        // サインイン証跡検索
        SignInTraceEntityCriteria entityCriteria = new SignInTraceEntityCriteria();
        entityCriteria.getSignInTraceIdCriteria().setEqualTo(signInTraceId);
        SignInTraceEntity entity = signInTraceEntityDao.findOneBy(entityCriteria);

        // オペレーターの検索
        Operator operator = operatorRepository.findOneByCode(entity.getOperatorCode());

        return SignInTrace.createFrom(
            entity.getSignInTraceId(),
            entity.getTryDateTime(),
            entity.getTryIpAddress(),
            entity.getOperatorCode(),
            entity.getSignInCause(),
            entity.getSignInResult(),
            entity.getRecordVersion(),
            operator);
    }

    /**
     * サインイン証跡群の検索を行います
     *
     * @param signInTraceCriteria サインイン証跡の検索条件
     * @param orders              オーダー指定
     * @return サインイン証跡群
     */
    public SignInTraces selectBy(SignInTraceCriteria signInTraceCriteria, Orders orders) {

        // サインイン証跡群検索
        List<SignInTraceEntity> entityList = signInTraceEntityDao.findBy(convertCriteria(signInTraceCriteria), orders);
        if (entityList.size() == 0) { return SignInTraces.createFrom(newArrayList()); }

        // オペレーター群の検索
        OperatorCriteria operatorCriteria = new OperatorCriteria();
        operatorCriteria.getOperatorCodeCriteria().getIncludes().addAll(
            entityList.stream().map(SignInTraceEntity::getOperatorCode).distinct().collect(Collectors.toList()));
        Operators operators = operatorRepository.selectBy(operatorCriteria, Orders.empty());

        List<SignInTrace> list = newArrayList();
        for (SignInTraceEntity entity : entityList) {
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

    /**
     * 最新オペレーターのサインイン証跡群の検索を行います
     *
     * @param signInTraceCriteria サインイン証跡の検索条件
     * @return サインイン証跡群
     */
    public SignInTraces latestBy(SignInTraceCriteria signInTraceCriteria, Orders orders) {

        // サインイン証跡群検索
        List<SignInTraceEntity> entityList = signInTraceEntityDao.findBy(convertCriteria(signInTraceCriteria), Orders.empty());
        if (entityList.size() == 0) { return SignInTraces.createFrom(newArrayList()); }

        // オペレーター群の検索
        OperatorCriteria operatorCriteria = new OperatorCriteria();
        operatorCriteria.getOperatorCodeCriteria().getIncludes().addAll(
            entityList.stream().map(SignInTraceEntity::getOperatorCode).distinct().collect(Collectors.toList()));
        Operators operators = operatorRepository.selectBy(operatorCriteria, Orders.empty());

        List<SignInTrace> list = newArrayList();
        // オペレーターコードでグルーピングし試行日時が最新（最大）を抽出
        Map<String, Optional<SignInTraceEntity>> entityListMap = entityList.stream()
            .collect(Collectors.groupingBy(SignInTraceEntity::getOperatorCode,
                Collectors.maxBy(Comparator.comparing(SignInTraceEntity::getTryDateTime))));

        for (Map.Entry<String, Optional<SignInTraceEntity>> entityMap : entityListMap.entrySet()) {
            SignInTraceEntity entity =  entityMap.getValue().get();
            list.add(SignInTrace.createFrom(
                entity.getSignInTraceId(),
                entity.getTryDateTime(),
                entity.getTryIpAddress(),
                entity.getOperatorCode(),
                entity.getSignInCause(),
                entity.getSignInResult(),
                entity.getRecordVersion(),
                operators.getValues().stream().filter(o->
                    o.getOperatorCode().equals(entity.getOperatorCode())).findFirst().orElse(null)));
        }
        return SignInTraces.createFrom(list.stream().sorted(orders.toComparator()).collect(Collectors.toList()));
    }

    /**
     * サインイン証跡EntityCriteriaへ変換します
     *
     * @param signInTraceCriteria サインイン証跡の検索条件
     * @return サインイン証跡EntityCriteria
     */
    private SignInTraceEntityCriteria convertCriteria(SignInTraceCriteria signInTraceCriteria) {
        SignInTraceEntityCriteria entityCriteria = new SignInTraceEntityCriteria();
        entityCriteria.getSignInTraceIdCriteria().assignFrom(signInTraceCriteria.getSignInTraceIdCriteria());
        entityCriteria.getOperatorCodeCriteria().assignFrom(signInTraceCriteria.getOperatorCodeCriteria());
        return entityCriteria;
    }
}
