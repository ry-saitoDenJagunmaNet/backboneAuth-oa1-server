package net.jagunma.backbone.auth.authmanager.infra.datasource.jaIpAddressRange;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.model.domain.jaIpAddressRange.JaIpAddressRange;
import net.jagunma.backbone.auth.authmanager.model.domain.jaIpAddressRange.JaIpAddressRangeCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.jaIpAddressRange.JaIpAddressRangeRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.jaIpAddressRange.JaIpAddressRanges;
import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntity;
import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntityCriteria;
import net.jagunma.backbone.auth.model.dao.jaIpAddressRange.JaIpAddressRangeEntityDao;
import net.jagunma.backbone.shared.application.ja.JaAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.ddd.model.values.buisiness.datetime.TargetDate;
import net.jagunma.common.values.model.ja.JaAtMomentCriteria;
import net.jagunma.common.values.model.ja.JaCode;
import net.jagunma.common.values.model.ja.JasAtMoment;
import org.springframework.stereotype.Component;

/**
 * JA割当IPアドレス範囲検索
 */
@Component
public class JaIpAddressRangeDataSource implements JaIpAddressRangeRepository {

    private final JaIpAddressRangeEntityDao jaIpAddressRangeEntityDao;
    private final JaAtMomentRepository jaAtMomentRepository;

    // コンストラクタ
    JaIpAddressRangeDataSource(JaIpAddressRangeEntityDao jaIpAddressRangeEntityDao,
        JaAtMomentRepository jaAtMomentRepository) {

        this.jaIpAddressRangeEntityDao = jaIpAddressRangeEntityDao;
        this.jaAtMomentRepository = jaAtMomentRepository;
    }

    /**
     * JA割当IPアドレス範囲群の検索を行います
     *
     * @param jaIpAddressRangeCriteria JA割当IPアドレス範囲の検索条件
     * @param orders                   オーダー指定
     * @return JA割当IPアドレス範囲群
     */
    public JaIpAddressRanges selectBy(JaIpAddressRangeCriteria jaIpAddressRangeCriteria, Orders orders) {

        // 一時取引抑止群の検索
        List<JaIpAddressRangeEntity> jaIpAddressRangeEntityList = jaIpAddressRangeEntityDao.findBy(createJaIpAddressRangeEntityCriteria(jaIpAddressRangeCriteria), Orders.empty());
        if (jaIpAddressRangeEntityList.size() == 0) { return JaIpAddressRanges.createFrom(newArrayList()); }

        JaAtMomentCriteria jaAtMomentCriteria = new JaAtMomentCriteria();
        jaAtMomentCriteria.getJaAttributeCriteria().getJaCodeCriteria().getIncludes().addAll(
            jaIpAddressRangeEntityList.stream().map(j -> JaCode.of(j.getJaCode())).collect(Collectors.toList()));
        jaAtMomentCriteria.setTargetDate(TargetDate.now());
        jaAtMomentCriteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
        JasAtMoment jasAtMoment = jaAtMomentRepository.selectBy(jaAtMomentCriteria, Orders.empty());

        List<JaIpAddressRange> list = newArrayList();
        for (JaIpAddressRangeEntity entity : jaIpAddressRangeEntityList) {
            list.add(JaIpAddressRange.createFrom(
                entity.getJaIpAddressRangeId(),
                entity.getJaCode(),
                entity.getIpAddressRange(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
                jasAtMoment.getValue().stream().filter(j -> j.getJaAttribute().getJaCode().getValue().equals( entity.getJaCode())).findFirst().orElse(null)));
        }
        return JaIpAddressRanges.createFrom(list.stream().sorted(orders.toComparator()).collect(Collectors.toList()));
    }

    /**
     * JA割当IPアドレス範囲EntityCriteriaへ変換します
     *
     * @param jaIpAddressRangeCriteria JA割当IPアドレス範囲の検索条件
     * @return JA割当IPアドレス範囲EntityCriteria
     */
    private JaIpAddressRangeEntityCriteria createJaIpAddressRangeEntityCriteria(JaIpAddressRangeCriteria jaIpAddressRangeCriteria) {
        JaIpAddressRangeEntityCriteria jaIpAddressRangeEntityCriteria = new JaIpAddressRangeEntityCriteria();
        jaIpAddressRangeEntityCriteria.getJaIpAddressRangeIdCriteria().assignFrom(jaIpAddressRangeCriteria.getJaIpAddressRangeIdCriteria());
        jaIpAddressRangeEntityCriteria.getJaCodeCriteria().assignFrom(jaIpAddressRangeCriteria.getJaCodeCriteria());
        jaIpAddressRangeEntityCriteria.getValidThruStartDateCriteria().assignFrom(jaIpAddressRangeCriteria.getValidThruStartDateCriteria());
        jaIpAddressRangeEntityCriteria.getValidThruEndDateCriteria().assignFrom(jaIpAddressRangeCriteria.getValidThruEndDateCriteria());
        return jaIpAddressRangeEntityCriteria;
    }
}