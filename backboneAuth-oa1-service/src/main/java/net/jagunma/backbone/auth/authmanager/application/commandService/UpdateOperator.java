package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBranchAtMoment;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorUpdateRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorUpdatePack;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * オペレーター更新サービス
 */
@Service
@Transactional
public class UpdateOperator {

    private final OperatorRepositoryForStore operatorRepositoryForStore;
    private final SearchBranchAtMoment searchBranchAtMoment;

    public UpdateOperator(OperatorRepositoryForStore operatorRepositoryForStore,
        SearchBranchAtMoment searchBranchAtMoment) {

        this.operatorRepositoryForStore = operatorRepositoryForStore;
        this.searchBranchAtMoment = searchBranchAtMoment;
    }

    /**
     * オペレーターの更新を行います
     *
     * @param request オペレーター更新サービス Request
     */
    public void execute(OperatorUpdateRequest request) {

        // パラメーターの検証
        UpdateOperatorValidator.with(request).validate();

        // 店舗の取得を行います
        BranchAtMoment branchAtMoment = searchBranchAtMoment.findOneBy(request.getBranchId());

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
     * オペレーターアップデートパックの生成を行います
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
