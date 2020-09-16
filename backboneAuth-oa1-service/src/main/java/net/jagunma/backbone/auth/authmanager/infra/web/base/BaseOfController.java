package net.jagunma.backbone.auth.authmanager.infra.web.base;

import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.server.model.securities.AuthInf;
import net.jagunma.common.server.model.securities.Route;
import net.jagunma.common.util.dateProvider.DateProvider;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import net.jagunma.common.values.model.operator.OperatorCode;
import net.jagunma.common.values.model.operator.SimpleOperator;
import net.jagunma.common.values.model.operator.SimpleOperator.SimpleOperatorBuilder;

/**
 * Controllerの基底クラス
 */
public class BaseOfController {

    /**
     * 認証情報を設定します。（ＰＧ用暫定）
     */
    public void setAuthInf() {
//        System.out.println("### BaseOfController");

        //TODO: パラメータでサインインオペレーターの情報を取得する
//        AuthInf authInf = new AuthInf().createFrom("006", "001", 18l, "yu001009", "145.254.211.73");
//        AuditInfoHolder.set(authInf, DateProvider.currentLocalDateTime(),
//            Route.createFrom("", ""), DefaultAuditTempo.empty());

        JaAtMoment jaAtMoment = JaAtMoment.builder()
            .withIdentifier(6l)
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of("006"))
                .withName("JA前橋市")
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
        
        BranchAtMoment branchAtMoment = BranchAtMoment.builder()
            .withIdentifier(1l)
            .withJaAtMoment(jaAtMoment)
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般)
                .withName("")
                .build())
            .build();

        SimpleOperator operator = new SimpleOperatorBuilder()
            .withIdentifier(18l)
            .withOperatorCode(OperatorCode.of("yu001009"))
            .withOperatorName("ｙｕ００１００９")
            .withBranchIdentifier(branchAtMoment.getIdentifier())
            .withBranch(branchAtMoment)
            .build();

        AuditInfoHolder.set(AuthInf.createFrom("006", "001", 18L, "yu001009", "001.001.001.001"),
            DateProvider.currentLocalDateTime(),
            Route.createFrom("", ""),
            branchAtMoment.getJaAtMoment(),
            branchAtMoment,
            operator);
    }
}
