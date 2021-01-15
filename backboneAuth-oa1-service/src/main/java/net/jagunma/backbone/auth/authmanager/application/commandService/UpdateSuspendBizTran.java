package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranUpdateRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 一時取引抑止更新サービス
 */
@Service
@Transactional
public class UpdateSuspendBizTran {

    private final SuspendBizTranRepositoryForStore suspendBizTranRepositoryForStore;

    // コンストラクタ
    public UpdateSuspendBizTran(SuspendBizTranRepositoryForStore suspendBizTranRepositoryForStore) {
        this.suspendBizTranRepositoryForStore = suspendBizTranRepositoryForStore;
    }

    /**
     * 一時取引抑止の更新を行います
     *
     * @param request 一時取引抑止更新サービス Request
     */
    public void execute(SuspendBizTranUpdateRequest request) {

        // パラメーターの検証
        UpdateSuspendBizTranValidator.with(request).validate();

        // SuspendBizTranモデルに変換
        SuspendBizTran suspendBizTran = SuspendBizTran.createFrom(
            request.getSuspendBizTranId(),
            null,
            null,
            null,
            null,
            null,
            request.getSuspendStartDate(),
            request.getSuspendEndDate(),
            null,
            request.getRecordVersion(),
            null,
            null,
            null,
            null,
            null);

        // 一時取引抑止 Update
        suspendBizTranRepositoryForStore.update(suspendBizTran);
    }
}
