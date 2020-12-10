package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.suspendBizTranCommand.SuspendBizTranDeleteRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.suspendBizTran.SuspendBizTranRepositoryForStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 一時取引抑止削除サービス
 */
@Service
@Transactional
public class DeleteSuspendBizTran {

    private final SuspendBizTranRepositoryForStore suspendBizTranRepositoryForStore;

    // コンストラクタ
    public DeleteSuspendBizTran(SuspendBizTranRepositoryForStore suspendBizTranRepositoryForStore) {
        this.suspendBizTranRepositoryForStore = suspendBizTranRepositoryForStore;
    }

    /**
     * 一時取引抑止の削除を行います
     *
     * @param request
     */
    public void execute(SuspendBizTranDeleteRequest request) {

        // SuspendBizTranモデルに変換
        SuspendBizTran suspendBizTran = SuspendBizTran.createFrom(
            request.getSuspendBizTranId(),
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            request.getRecordVersion(),
            null,
            null,
            null,
            null,
            null);

        // 一時取引抑止 Delete
        suspendBizTranRepositoryForStore.delete(suspendBizTran);
    }
}
