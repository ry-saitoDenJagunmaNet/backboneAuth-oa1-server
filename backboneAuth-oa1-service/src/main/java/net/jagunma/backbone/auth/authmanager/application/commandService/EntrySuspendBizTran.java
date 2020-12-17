package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranEntryRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 一時取引抑止登録サービス
 */
@Service
@Transactional
public class EntrySuspendBizTran {

    private final SuspendBizTranRepositoryForStore suspendBizTranRepositoryForStore;

    // コンストラクタ
    public EntrySuspendBizTran(SuspendBizTranRepositoryForStore suspendBizTranRepositoryForStore) {
        this.suspendBizTranRepositoryForStore = suspendBizTranRepositoryForStore;
    }

    /**
     * 一時取引抑止の登録を行います
     *
     * @param request
     */
    public void execute(SuspendBizTranEntryRequest request) {

        // パラメーターの検証
        EntrySuspendBizTranValidator.with(request).validate();

        // SuspendBizTranモデルに変換
        SuspendBizTran suspendBizTran = SuspendBizTran.createFrom(
            null,
            request.getJaCode(),
            request.getBranchCode(),
            request.getSubSystemCode(),
            request.getBizTranGrpCode(),
            request.getBizTranCode(),
            request.getSuspendStartDate(),
            request.getSuspendEndDate(),
            request.getSuspendReason(),
            null,
            null,
            null,
            null,
            null,
            null);

        // 一時取引抑止 Insert
        suspendBizTranRepositoryForStore.insert(suspendBizTran);
    }
}
