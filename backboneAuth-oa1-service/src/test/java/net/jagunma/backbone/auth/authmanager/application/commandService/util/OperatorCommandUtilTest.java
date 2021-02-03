package net.jagunma.backbone.auth.authmanager.application.commandService.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import net.jagunma.backbone.auth.authmanager.util.TestAuditInfoHolder;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OperatorCommandUtilTest {

    OperatorCommandUtilTest () {
        // 認証情報
        TestAuditInfoHolder.setAuthInf();
    }

    // 店舗AtMoment
    private BranchAtMoment createBranchAtMoment() {
        return createBranchAtMoment(AuditInfoHolder.getJa().getJaAttribute().getJaCode().getValue());
    }
    private BranchAtMoment createBranchAtMoment(String jaCode) {
        return BranchAtMoment.builder()
            .withIdentifier(AuditInfoHolder.getBranch().getIdentifier())
            .withJaAtMoment(JaAtMoment.builder()
                .withJaAttribute(JaAttribute.builder()
                    .withJaCode(JaCode.of(jaCode))
                    .build())
                .build())
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchCode(
                    BranchCode.of(AuditInfoHolder.getBranch().getBranchAttribute().getBranchCode().getValue()))
                .build())
            .build();
    }

    /**
     * {@link OperatorCommandUtil#checkBranchBelongJa(BranchAtMoment branchAtMoment)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkBranchBelongJa_test() {
        // テスト対象クラス生成
        OperatorCommandUtil operatorCommandUtil = new OperatorCommandUtil();

        // 実行値
        BranchAtMoment branchAtMoment = createBranchAtMoment();

        assertThatCode(() ->
            // 実行
            operatorCommandUtil.checkBranchBelongJa(branchAtMoment))
            .doesNotThrowAnyException();
    }

    /**
     * {@link OperatorCommandUtil#checkBranchBelongJa(BranchAtMoment branchAtMoment)}テスト
     *  ●パターン
     *    店舗が当JAに属するかのチェック）店舗所属JA不一致
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkBranchBelongJa_test1() {
        // テスト対象クラス生成
        OperatorCommandUtil operatorCommandUtil = new OperatorCommandUtil();

        // 実行値
        BranchAtMoment branchAtMoment = createBranchAtMoment("999");

        assertThatThrownBy(() ->
            // 実行
            operatorCommandUtil.checkBranchBelongJa(branchAtMoment))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA12001");
                assertThat(e.getArgs()).containsSequence(AuditInfoHolder.getJa().getJaAttribute().getJaCode());
                assertThat(e.getArgs()).containsSequence(branchAtMoment.getJaAtMoment().getJaAttribute().getJaCode());
            });
    }
}