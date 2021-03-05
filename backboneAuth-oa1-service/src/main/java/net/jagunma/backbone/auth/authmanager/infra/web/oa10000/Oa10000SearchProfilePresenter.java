package net.jagunma.backbone.auth.authmanager.infra.web.oa10000;

import net.jagunma.backbone.auth.authmanager.application.usecase.profileReference.ProfileSearchResponse;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.server.model.securities.AuthInf;
import net.jagunma.common.server.model.securities.Route;
import net.jagunma.common.util.dateProvider.DateProvider;
import net.jagunma.common.values.model.operator.SimpleOperator;

public class Oa10000SearchProfilePresenter implements ProfileSearchResponse {

    private SimpleOperator simpleOperator;
    private String remoteAddr;

    /**
     * オペレーター（簡略版）のＳｅｔ
     *
     * @param simpleOperator オペレーター（簡略版）
     */
    public void setSimpleOperator(SimpleOperator simpleOperator) {
        this.simpleOperator = simpleOperator;
    }

    /**
     * リモートIPアドレスのＳｅｔ
     *
     * @param remoteAddr リモートIPアドレス
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    /**
     * AuditInfoHolderに変換します
     */
    public void bindToAuditInfoHolder() {

        AuditInfoHolder.set(AuthInf.createFrom(
            simpleOperator.getBranch().getJaAtMoment().getJaAttribute().getJaCode().getValue(),
            simpleOperator.getBranch().getBranchAttribute().getBranchCode().getValue(),
            simpleOperator.getIdentifier().longValue(),
            simpleOperator.getOperatorName(),
            remoteAddr),
            DateProvider.currentLocalDateTime(),
            Route.createFrom("", ""),
            simpleOperator.getBranch().getJaAtMoment(),
            simpleOperator.getBranch(),
            simpleOperator);

    }
}
