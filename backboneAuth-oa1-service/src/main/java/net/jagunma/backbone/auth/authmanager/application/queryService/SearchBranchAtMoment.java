package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.ddd.model.values.buisiness.datetime.TargetDate;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaCode;
import org.springframework.stereotype.Service;

/**
 * BranchAtMoment検索サービス
 */
@Service
public class SearchBranchAtMoment {

    private final BranchAtMomentRepository branchAtMomentRepository;

    // コンストラクタ
    public SearchBranchAtMoment(BranchAtMomentRepository branchAtMomentRepository) {
        this.branchAtMomentRepository = branchAtMomentRepository;
    }

    /**
     * BranchAtMomentを取得します
     *
     * @param branchId 店舗ID
     * @return BranchAtMoment
     */
    public BranchAtMoment findOneBy(long branchId) {

        BranchAtMomentCriteria criteria = new BranchAtMomentCriteria();
        criteria.getIdentifierCriteria().setEqualTo(branchId);
        criteria.setTargetDate(TargetDate.now());
        return branchAtMomentRepository.findOneBy(criteria);
    }

    /**
     * BranchesAtMomentを取得します
     *
     * @param jaId ＪＡID
     * @return BranchesAtMoment
     */
    public BranchesAtMoment selectBy(long jaId) {

        BranchAtMomentCriteria criteria = new BranchAtMomentCriteria();
        criteria.getJaIdentifierCriteria().setEqualTo(jaId);
        criteria.setTargetDate(TargetDate.now());
        criteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
        Orders orders = Orders.empty().addOrder("branchAttribute.branchCode");
        return branchAtMomentRepository.selectBy(criteria, orders);
    }

    /**
     * BranchesAtMomentを取得します
     *
     * @param jaCode ＪＡコード
     * @return BranchesAtMoment
     */
    public BranchesAtMoment selectBy(String jaCode) {

        BranchAtMomentCriteria criteria = new BranchAtMomentCriteria();
        criteria.getNarrowedJaCodeCriteria().setEqualTo(JaCode.of(jaCode));
        criteria.setTargetDate(TargetDate.now());
        criteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
        Orders orders = Orders.empty().addOrder("branchAttribute.branchCode");
        return branchAtMomentRepository.selectBy(criteria, orders);
    }
}
