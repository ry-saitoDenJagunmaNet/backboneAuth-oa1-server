package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorUpdateRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorUpdatePack;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * オペレーター更新サービス
 */
@Service
@Transactional
public class UpdateOperator {

    private final OperatorRepositoryForStore operatorRepositoryForStore;
    private final BranchAtMomentRepository branchAtMomentRepository;

    public UpdateOperator(OperatorRepositoryForStore operatorRepositoryForStore,
        BranchAtMomentRepository branchAtMomentRepository) {

        this.operatorRepositoryForStore = operatorRepositoryForStore;
        this.branchAtMomentRepository = branchAtMomentRepository;
    }

    /**
     * オペレーターの更新を行います。
     *
     * @param request オペレーター更新サービス Request
     */
    public void execute(OperatorUpdateRequest request) {

        // パラメーターの検証
        UpdateOperatorValidator.with(request).validate();

        // 店舗の取得を行います
        BranchAtMoment branchAtMoment = getBranchAtMoment(request.getBranchId());

        // 店舗が当JAに属するかのチェックを行います
        EntryOperator.checkBranchBelongJa(branchAtMoment);

        // オペレーターアップデートパックの生成を行います
        OperatorUpdatePack operatorUpdatePack = createOperatorUpdatePack(
            request,
            branchAtMoment.getBranchAttribute().getBranchCode().getValue());

        // オペレーターの更新を行います
        operatorRepositoryForStore.update(operatorUpdatePack);
    }

    /**
     * 店舗の取得を行います。
     *
     * @param branchId 店舗ID
     * @return branchAtMoment 店舗AtMoment
     */
    BranchAtMoment getBranchAtMoment(Long branchId) {
        BranchAtMomentCriteria criteria = new BranchAtMomentCriteria();

        criteria.getIdentifierCriteria().setEqualTo(branchId);

        BranchAtMoment branchAtMoment = branchAtMomentRepository.findOneBy(criteria);
        if (branchAtMoment.isEmpty()) {
            throw new GunmaRuntimeException("EOA12001", branchId);
        }

        return branchAtMoment;
    }

    /**
     * オペレーターアップデートパックの生成を行います。
     *
     * @param request オペレーター更新サービス Request
     * @param branchCode 店舗コード
     * @return オペレーターアップデートパック
     */
    OperatorUpdatePack createOperatorUpdatePack(
        OperatorUpdateRequest request,
        String branchCode) {

        return OperatorUpdatePack.createFrom(
            request.getOperatorId(),
            request.getOperatorName(),
            request.getMailAddress(),
            request.getExpirationStartDate(),
            request.getExpirationEndDate(),
            request.getIsDeviceAuth(),
            request.getBranchId(),
            branchCode,
            request.getAvailableStatus(),
            request.getRecordVersion(),
            request.getChangeCause());
    }
}
