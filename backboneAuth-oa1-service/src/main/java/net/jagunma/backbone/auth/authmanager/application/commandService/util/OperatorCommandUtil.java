package net.jagunma.backbone.auth.authmanager.application.commandService.util;

import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.values.model.branch.BranchAtMoment;

/**
 * オペレーター登録サービス・オペレーター更新サービス における ユーティリティ
 */
public class OperatorCommandUtil {

    /**
     * 店舗が当JAに属するかのチェックを行います
     *
     * @param branchAtMoment 店舗
     */
   public void checkBranchBelongJa (BranchAtMoment branchAtMoment) {
        if (!branchAtMoment.getJaAtMoment().getJaAttribute().getJaCode().sameValueAs(
            AuditInfoHolder.getJa().getJaAttribute().getJaCode())) {
            throw new GunmaRuntimeException("EOA12001", AuditInfoHolder.getJa().getJaAttribute().getJaCode(), branchAtMoment.getJaAtMoment().getJaAttribute().getJaCode());
        }
    }
}
