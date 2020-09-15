package net.jagunma.backbone.auth.authmanager.util;

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

public class TestAuditInfoHolder {

    public static void setAuthInf() {
        setAuthInf(null,null,null,null,null);
    }
    public static void setAuthInf(
        Long operatorId,
        String operatorCode,
        String operatorName,
        JaAtMoment jaAtMoment,
        BranchAtMoment branchAtMoment) {

        Long id = 18L;
        String code = "yu001009";
        String name = "ｙｕ００１００９";
        String ip = "001.001.001.001";
        JaAtMoment ja = createJaAtMoment();
        BranchAtMoment branch = createBranchAtMoment();

        if (operatorId != null) {id = operatorId;}
        if (operatorCode != null) {code = operatorCode;}
        if (operatorName != null) {name = operatorName;}
        if (jaAtMoment != null) {ja = jaAtMoment;}
        if (branchAtMoment != null) {branch = branchAtMoment;}

//        JaAtMoment jaAtMoment = JaAtMoment.builder()
//            .withIdentifier(6l)
//            .withJaAttribute(JaAttribute
//                .builder()
//                .withJaCode(JaCode.of("006"))
//                .withName("JA前橋市")
//                .withFormalName("")
//                .withAbbreviatedName("")
//                .build())
//            .build();
//        BranchAtMoment branchAtMoment = BranchAtMoment.builder()
//            .withIdentifier(1l)
//            .withJaAtMoment(jaAtMoment)
//            .withBranchAttribute(BranchAttribute.builder()
//                .withBranchType(BranchType.一般)
//                .withName("")
//                .build())
//            .build();
        AuditInfoHolder.set(AuthInf.createFrom(ja.getJaAttribute().getJaCode().getValue(), branch.getBranchAttribute().getBranchCode().getValue(), id, name, ip),
            DateProvider.currentLocalDateTime(),
            Route.createFrom("", ""),
            ja,
            branch,
            new SimpleOperator(id, new OperatorCode(code), name, branch.getIdentifier(), branch ));
    }

    public static JaAtMoment createJaAtMoment() {
        return createJaAtMoment(null, null, null);
    }
    public static JaAtMoment createJaAtMoment(Long jaId, String jaCode, String jaName) {

        Long id = 6L;
        String code = "006";
        String name = "JA前橋市";
        if (jaId != null) { id = jaId; }
        if (jaCode != null) { code = jaCode; }
        if (jaName != null) { name = jaName; }

        return JaAtMoment.builder()
            .withIdentifier(id)
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(code))
                .withName(name)
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
    }
    public static BranchAtMoment createBranchAtMoment() {
        return createBranchAtMoment(null,null,null,null);
    }
    public static BranchAtMoment createBranchAtMoment(Long branchId, String branchCode, String branchName, JaAtMoment jaAtMoment) {
        Long id = 1L;
        String code = "001";
        String name = "本店";
        JaAtMoment ajAtMoment = createJaAtMoment(null, null, null);
        if (branchId != null) { id = branchId; }
        if (branchCode != null) { code = branchCode; }
        if (branchName != null) { name = branchName; }
        if (jaAtMoment != null) { ajAtMoment = jaAtMoment; }

        return BranchAtMoment.builder()
            .withIdentifier(id)
            .withJaAtMoment(ajAtMoment)
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般)
                .withBranchCode(BranchCode.of(code))
                .withName(name)
                .build())
            .build();
    }


//    public static void setAuthInf() {
//        JaAtMoment jaAtMoment = JaAtMoment.builder()
//            .withIdentifier(6l)
//            .withJaAttribute(JaAttribute
//                .builder()
//                .withJaCode(JaCode.of("006"))
//                .withName("JA前橋市")
//                .withFormalName("")
//                .withAbbreviatedName("")
//                .build())
//            .build();
//        BranchAtMoment branchAtMoment = BranchAtMoment.builder()
//            .withIdentifier(1l)
//            .withJaAtMoment(jaAtMoment)
//            .withBranchAttribute(BranchAttribute.builder()
//                .withBranchType(BranchType.一般)
//                .withName("")
//                .build())
//            .build();
//        final OperatorCode yu001009 = new OperatorCode("yu001009");
//        String anOperatorName = "ｙｕ００１００９";
//        SimpleOperator anOperator = new SimpleOperator(18l, yu001009,
//            anOperatorName, 33l, branchAtMoment);
//
//        AuditInfoHolder.set(AuthInf.createFrom("006", "001", 18L, "yu001009", "001.001.001.001"),
//            DateProvider.currentLocalDateTime(),
//            Route.createFrom("", ""),
//            jaAtMoment,
//            branchAtMoment,
//            anOperator);
//    }

}
