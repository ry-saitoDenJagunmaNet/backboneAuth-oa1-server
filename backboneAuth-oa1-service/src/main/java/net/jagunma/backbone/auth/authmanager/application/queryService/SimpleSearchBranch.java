package net.jagunma.backbone.auth.authmanager.application.queryService;

import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.ddd.model.values.buisiness.datetime.TargetDate;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import org.springframework.stereotype.Service;

@Service
public class SimpleSearchBranch {

    private final BranchAtMomentRepository branchAtMomentRepository;

    // コンストラクタ
    public SimpleSearchBranch(BranchAtMomentRepository branchAtMomentRepository) {
        this.branchAtMomentRepository = branchAtMomentRepository;
    }

    /**
     * BranchesAtMomentを取得します
     *
     * @param jaId ＪＡID
     * @return BranchesAtMoment
     */
    public BranchesAtMoment getBranchesAtMoment(long jaId) {

        BranchAtMomentCriteria criteria = new BranchAtMomentCriteria();
        criteria.getJaIdentifierCriteria().setEqualTo(jaId);
        criteria.setTargetDate(TargetDate.now());
        criteria.getAvailableDatePeriodCriteria().getIsAvailableCriteria().at(TargetDate.now());
        Orders orders = Orders.empty().addOrder("BranchAttribute.BranchCode");
        return branchAtMomentRepository.selectBy(criteria, orders);
    }
}
