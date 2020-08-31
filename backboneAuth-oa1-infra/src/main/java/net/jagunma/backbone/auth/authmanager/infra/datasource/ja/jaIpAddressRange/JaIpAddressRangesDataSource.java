package net.jagunma.backbone.auth.authmanager.infra.datasource.ja.jaIpAddressRange;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.model.domain.ja.jaIpAddressRange.JaIpAddressRangeCriteria;
import net.jagunma.backbone.auth.authmanager.application.model.domain.ja.jaIpAddressRange.JaIpAddressRanges;
import net.jagunma.backbone.auth.authmanager.application.model.domain.ja.jaIpAddressRange.JaIpAddressRangesRepository;
import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntity;
import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntityDao;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Component;

/**
 * JA割当IPアドレス範囲検索 DataSource
 */
@Component
public class JaIpAddressRangesDataSource implements JaIpAddressRangesRepository {

    private final JaIpAddressRangeEntityDao jaIpAddressRangeEntityDao;
    private final Orders defaultOrders = Orders.empty().addOrder("jaCode")
        .addOrder("ipAddressRange").addOrder("expirationStartDate");

    // コンストラクタ
    JaIpAddressRangesDataSource(JaIpAddressRangeEntityDao jaIpAddressRangeEntityDao) {
        this.jaIpAddressRangeEntityDao = jaIpAddressRangeEntityDao;
    }

    /**
     * JA割当IPアドレス範囲の条件検索を行います。
     *
     * @param jaIpAddressRangeCriteria JA割当IPアドレス範囲の検索条件
     * @param orders                   オーダー指定
     * @return JA割当IPアドレス範囲群
     */
    @Override
    public JaIpAddressRanges selectBy(JaIpAddressRangeCriteria jaIpAddressRangeCriteria,
        Orders orders) {
        List<JaIpAddressRangeEntity> list = jaIpAddressRangeEntityDao
            .findBy(jaIpAddressRangeCriteria, orders);
        return JaIpAddressRanges.createFrom(list);
    }

    /**
     * JA割当IPアドレス範囲の条件検索を行います。
     *
     * @param jaIpAddressRangeCriteria JA割当IPアドレス範囲の検索条件
     * @return JA割当IPアドレス範囲群
     */
    @Override
    public JaIpAddressRanges selectBy(JaIpAddressRangeCriteria jaIpAddressRangeCriteria) {
        return selectBy(jaIpAddressRangeCriteria, defaultOrders);
    }

    /**
     * JA割当IPアドレス範囲の全件検索を行います。
     *
     * @param orders オーダー指定
     * @return JA割当IPアドレス範囲群
     */
    @Override
    public JaIpAddressRanges selectAll(Orders orders) {
        return JaIpAddressRanges.createFrom(jaIpAddressRangeEntityDao.findAll(orders));
    }

    /**
     * JA割当IPアドレス範囲の全件検索を行います。
     *
     * @return JA割当IPアドレス範囲群
     */
    @Override
    public JaIpAddressRanges selectAll() {
        return selectAll(defaultOrders);
    }
}
