package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.shared.application.ja.JaAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.ddd.model.values.buisiness.datetime.TargetDate;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAtMomentCriteria;
import net.jagunma.common.values.model.ja.JasAtMoment;
import org.springframework.stereotype.Service;

/**
 * JaAtMoment検索サービス
 */
@Service
public class SearchJaAtMoment {

    private JaAtMomentRepository jaAtMomentRepository;

    // コンストラクタ
    public SearchJaAtMoment(JaAtMomentRepository jaAtMomentRepository) {
        this.jaAtMomentRepository = jaAtMomentRepository;
    }

    /**
     * JaAtMomentを取得します
     *
     * @param jaId ＪＡID
     * @return JaAtMoment
     */
    public JaAtMoment findOneBy(Long jaId) {

        JaAtMomentCriteria criteria = new JaAtMomentCriteria();
        criteria.getIdentifierCriteria().setEqualTo(jaId);
        criteria.setTargetDate(TargetDate.now());
        return jaAtMomentRepository.findOneBy(criteria);
    }

    /**
     * JasAtMomentを取得します
     *
     * @return JasAtMoment
     */
    public JasAtMoment selectBy() {

        JaAtMomentCriteria criteria = new JaAtMomentCriteria();
        criteria.setTargetDate(TargetDate.now());
        criteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
        Orders orders = Orders.empty().addOrder("JaAttribute.JaCode");
        return jaAtMomentRepository.selectBy(criteria, orders);
    }

}
