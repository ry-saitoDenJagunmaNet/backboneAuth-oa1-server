package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBranchAtMoment;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.OperatorCodePrefix;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * オペレーター登録サービス
 */
@Service
@Transactional
public class EntryOperator {

    private final OperatorRepositoryForStore operatorRepositoryForStore;
    private final SearchBranchAtMoment searchBranchAtMoment;

    public EntryOperator(OperatorRepositoryForStore operatorRepositoryForStore,
        SearchBranchAtMoment searchBranchAtMoment) {

        this.operatorRepositoryForStore = operatorRepositoryForStore;
        this.searchBranchAtMoment = searchBranchAtMoment;
    }

    /**
     * オペレーターの登録を行います
     *
     * @param request オペレーター登録サービス Request
     */
    public void execute(OperatorEntryRequest request) {

        // パラメーターの検証
        EntryOperatorValidator.with(request).validate();

        // 店舗の取得を行います
        BranchAtMoment branchAtMoment = searchBranchAtMoment.findOneBy(request.getBranchId());

        // 店舗が当JAに属するかのチェックを行います
        checkBranchBelongJa(branchAtMoment);

        // オペレーターエントリーパックの生成を行います
        OperatorEntryPack operatorEntryPack = createOperatorEntryPack(
            request,
            OperatorCodePrefix.codeOf(AuditInfoHolder.getAuthInf().getJaCode()).getPrefix(),
            AuditInfoHolder.getJa().getIdentifier(),
            AuditInfoHolder.getJa().getJaAttribute().getJaCode().getValue(),
            branchAtMoment.getBranchAttribute().getBranchCode().getValue());

        // オペレーターの登録を行います
        operatorRepositoryForStore.entry(operatorEntryPack);
    }

    /**
     * 店舗が当JAに属するかのチェックを行います
     *
     * @param branchAtMoment 店舗
     */
    static void checkBranchBelongJa (BranchAtMoment branchAtMoment) {
        if (!branchAtMoment.getJaAtMoment().getJaAttribute().getJaCode().sameValueAs(AuditInfoHolder.getJa().getJaAttribute().getJaCode())) {
            throw new GunmaRuntimeException("EOA12001", AuditInfoHolder.getJa().getJaAttribute().getJaCode(), branchAtMoment.getJaAtMoment().getJaAttribute().getJaCode());
        }
    }

    /**
     * オペレーターエントリーパックの生成を行います
     *
     * @param request オペレーター登録サービス Request
     * @param operatorCodePrefix 識別（オペレーターコードプレフィックス）
     * @param jaId ＪＡID
     * @param jaCode ＪＡコード
     * @param branchCode 店舗コード
     * @return オペレーターエントリーパック
     */
    OperatorEntryPack createOperatorEntryPack(
        OperatorEntryRequest request,
        String operatorCodePrefix,
        Long jaId,
        String jaCode,
        String branchCode) {

        return OperatorEntryPack.createFrom(
            operatorCodePrefix + request.getOperatorCode6(),
            request.getOperatorName(),
            request.getMailAddress(),
            request.getValidThruStartDate(),
            request.getValidThruEndDate(),
            jaId,
            jaCode,
            request.getBranchId(),
            branchCode,
            request.getChangeCause(),
            request.getPassword());
    }
}
