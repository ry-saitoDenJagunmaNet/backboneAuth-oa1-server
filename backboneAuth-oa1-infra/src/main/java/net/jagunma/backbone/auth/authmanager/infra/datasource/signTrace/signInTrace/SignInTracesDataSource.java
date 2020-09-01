package net.jagunma.backbone.auth.authmanager.infra.datasource.signTrace.signInTrace;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.signTrace.signInTrace.SignInTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signTrace.signInTrace.SignInTraces;
import net.jagunma.backbone.auth.authmanager.model.domain.signTrace.signInTrace.SignInTracesRepository;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntity;
import net.jagunma.backbone.auth.model.dao.signInTrace.SignInTraceEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * サインイン証跡検索 DataSource
 */
@Component
public class SignInTracesDataSource implements SignInTracesRepository {

    private final SignInTraceEntityDao signInTraceEntityDao;
    private final Orders defaultOrders = Orders.empty().addOrder("tryDateTime")
        .addOrder("tryIpAddress").addOrder("operatorCode");

    // コンストラクタ
    SignInTracesDataSource(SignInTraceEntityDao signInTraceEntityDao) {
        this.signInTraceEntityDao = signInTraceEntityDao;
    }

    /**
     * サインイン証跡の条件検索を行います。
     *
     * @param signInTraceCriteria サインイン証跡の検索条件
     * @param orders              オーダー指定
     * @return サインイン証跡群
     */
    @Override
    public SignInTraces selectBy(SignInTraceCriteria signInTraceCriteria, Orders orders) {
        List<SignInTraceEntity> list = signInTraceEntityDao.findBy(signInTraceCriteria, orders);
        return SignInTraces.createFrom(list);
    }

    /**
     * サインイン証跡の条件検索を行います。
     *
     * @param signInTraceCriteria サインイン証跡の検索条件
     * @return サインイン証跡群
     */
    @Override
    public SignInTraces selectBy(SignInTraceCriteria signInTraceCriteria) {
        return selectBy(signInTraceCriteria, defaultOrders);
    }

    /**
     * サインイン証跡の全件検索を行います。
     *
     * @param orders オーダー指定
     * @return サインイン証跡群
     */
    @Override
    public SignInTraces selectAll(Orders orders) {
        return SignInTraces.createFrom(signInTraceEntityDao.findAll(orders));
    }

    /**
     * サインイン証跡の全件検索を行います。
     *
     * @return サインイン証跡群
     */
    @Override
    public SignInTraces selectAll() {
        return selectAll(defaultOrders);
    }
}
