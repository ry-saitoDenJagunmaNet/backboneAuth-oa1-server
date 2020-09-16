package net.jagunma.backbone.auth.authmanager.infra.web.base;

import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.server.model.securities.AuthInf;
import net.jagunma.common.server.model.securities.Route;
import net.jagunma.common.util.dateProvider.DateProvider;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
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

    private static Long defaultJaId = 6L;
    private static String defaultJaCode = "006";
    private static String defaultJaName = "JA前橋市";

    private static Long defaultBranchId = 1L;
    private static String defaultBranchCode = "001";
    private static String defaultBranchName = "本店";

    private static Long defaultOperatorId = 18L;
    private static String defaultOperatorCode = "yu001009";
    private static String defaultOperatorName = "ｙｕ００１００９";
    private static String defaultOperatorIp = "001.001.001.001";

    /**
     * 認証情報を設定します。（ＰＧ用暫定）
     */
    public static void setAuthInf() {
        setAuthInf(null, null, null, null, null);
    }

    public static void setAuthInf(
        Long operatorId,
        String operatorCode,
        String operatorName,
        JaAtMoment jaAtMoment,
        BranchAtMoment branchAtMoment) {

        JaAtMoment ja = createJaAtMoment();
        BranchAtMoment branch = createBranchAtMoment();

        if (operatorId != null) {
            defaultOperatorId = operatorId;
        }
        if (operatorCode != null) {
            defaultOperatorCode = operatorCode;
        }
        if (operatorName != null) {
            defaultOperatorName = operatorName;
        }
        if (jaAtMoment != null) {
            ja = jaAtMoment;
        }
        if (branchAtMoment != null) {
            branch = branchAtMoment;
        }

        SimpleOperator operator = new SimpleOperatorBuilder()
            .withIdentifier(defaultOperatorId)
            .withOperatorCode(OperatorCode.of(defaultOperatorCode))
            .withOperatorName(defaultOperatorName)
            .withBranchIdentifier(defaultBranchId)
            .withBranch(branch)
            .build();

        AuditInfoHolder.set(AuthInf.createFrom(ja.getJaAttribute().getJaCode().getValue(),
            branch.getBranchAttribute().getBranchCode().getValue(),
            defaultOperatorId, defaultOperatorName, defaultOperatorIp),
            DateProvider.currentLocalDateTime(),
            Route.createFrom("", ""),
            ja,
            branch, operator);
    }

    public static JaAtMoment createJaAtMoment() {
        return createJaAtMoment(null, null, null);
    }

    public static JaAtMoment createJaAtMoment(Long jaId, String jaCode, String jaName) {

        if (jaId != null) {
            defaultJaId = jaId;
        }
        if (jaCode != null) {
            defaultJaCode = jaCode;
        }
        if (jaName != null) {
            defaultJaName = jaName;
        }

        return JaAtMoment.builder()
            .withIdentifier(defaultJaId)
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(defaultJaCode))
                .withName(defaultJaName)
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
    }

    public static BranchAtMoment createBranchAtMoment() {
        return createBranchAtMoment(null, null, null, null);
    }

    public static BranchAtMoment createBranchAtMoment(Long branchId, String branchCode,
        String branchName, JaAtMoment jaAtMoment) {
        JaAtMoment ajAtMoment = createJaAtMoment(null, null, null);
        if (branchId != null) {
            defaultBranchId = branchId;
        }
        if (branchCode != null) {
            defaultBranchCode = branchCode;
        }
        if (branchName != null) {
            defaultBranchName = branchName;
        }
        if (jaAtMoment != null) {
            ajAtMoment = jaAtMoment;
        }

        return BranchAtMoment.builder()
            .withIdentifier(defaultBranchId)
            .withJaAtMoment(ajAtMoment)
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般)
                .withBranchCode(BranchCode.of(defaultBranchCode))
                .withName(defaultBranchName)
                .build())
            .build();
    }

//    public void setAuthInf() {
////        System.out.println("### BaseOfController");
//
//        //TODO: パラメータでサインインオペレーターの情報を取得する
////        AuthInf authInf = new AuthInf().createFrom("006", "001", 18l, "yu001009", "145.254.211.73");
////        AuditInfoHolder.set(authInf, DateProvider.currentLocalDateTime(),
////            Route.createFrom("", ""), DefaultAuditTempo.empty());
//
//
////        JaAtMoment jaAtMoment = JaAtMoment.builder()
////            .withIdentifier(6l)
////            .withJaAttribute(JaAttribute
////                .builder()
////                .withJaCode(JaCode.of("006"))
////                .withName("JA前橋市")
////                .withFormalName("")
////                .withAbbreviatedName("")
////                .build())
////            .build();
////        BranchAtMoment branchAtMoment = BranchAtMoment.builder()
////            .withIdentifier(1l)
////            .withJaAtMoment(jaAtMoment)
////            .withBranchAttribute(BranchAttribute.builder()
////                .withBranchType(BranchType.一般)
////                .withName("")
////                .build())
////            .build();
////        SimpleOperator operator = new SimpleOperatorBuilder()
////            .withIdentifier(18l)
////            .withOperatorCode(OperatorCode.of("yu001009"))
////            .withOperatorName("ｙｕ００１００９")
////            .withBranchIdentifier(33l)
////            .build();
////
////        AuditInfoHolder.set(AuthInf.createFrom("006", "001", 18L, "yu001009", "001.001.001.001"),
////            DateProvider.currentLocalDateTime(),
////            Route.createFrom("", ""),
////            jaAtMoment,
////            branchAtMoment,
////            operator);
//////            new SimpleOperator(18l, new OperatorCode("yu001009"), "ｙｕ００１００９", 33l, branchAtMoment ));
//    }
}
