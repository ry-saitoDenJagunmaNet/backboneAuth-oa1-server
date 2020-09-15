package net.jagunma.backbone.auth.authmanager.util;

import static org.junit.jupiter.api.Assertions.*;

import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TestAuditInfoHolderTest {

    @Test
    @Tag("SMALL")
    void testSetAuthInf(){

        TestAuditInfoHolder.setAuthInf();
        System.out.println("デフォル");
        System.out.println("## JA ID="+AuditInfoHolder.getJa().getIdentifier()+
            ", CODE="+AuditInfoHolder.getJa().getJaAttribute().getJaCode().getValue()+
            ", NAME="+AuditInfoHolder.getJa().getJaAttribute().getName());
        System.out.println("## BRANCH ID="+AuditInfoHolder.getBranch().getIdentifier()+
            ", CODE="+AuditInfoHolder.getBranch().getBranchAttribute().getBranchCode().getValue()+
            ", NAME="+AuditInfoHolder.getBranch().getBranchAttribute().getName());
        System.out.println("## OPARTOR ID="+AuditInfoHolder.getOperator().getIdentifier()+
            ", CODE="+AuditInfoHolder.getOperator().getOperatorCode().getValue()+
            ", NAME="+AuditInfoHolder.getOperator().getOperatorName());

        JaAtMoment jaAtMoment = TestAuditInfoHolder.createJaAtMoment(9L, "900", "JA名");
        BranchAtMoment branchAtMoment = TestAuditInfoHolder.createBranchAtMoment(99L, "999", "店舗名", jaAtMoment);
        TestAuditInfoHolder.setAuthInf(123456L, "operid", "オペレータ名", jaAtMoment, branchAtMoment);
        System.out.println("設定");
        System.out.println("## JA ID="+AuditInfoHolder.getJa().getIdentifier()+
            ", CODE="+AuditInfoHolder.getJa().getJaAttribute().getJaCode().getValue()+
            ", NAME="+AuditInfoHolder.getJa().getJaAttribute().getName());
        System.out.println("## BRANCH ID="+AuditInfoHolder.getBranch().getIdentifier()+
            ", CODE="+AuditInfoHolder.getBranch().getBranchAttribute().getBranchCode().getValue()+
            ", NAME="+AuditInfoHolder.getBranch().getBranchAttribute().getName());
        System.out.println("## OPARTOR ID="+AuditInfoHolder.getOperator().getIdentifier()+
            ", CODE="+AuditInfoHolder.getOperator().getOperatorCode().getValue()+
            ", NAME="+AuditInfoHolder.getOperator().getOperatorName());
    }

}