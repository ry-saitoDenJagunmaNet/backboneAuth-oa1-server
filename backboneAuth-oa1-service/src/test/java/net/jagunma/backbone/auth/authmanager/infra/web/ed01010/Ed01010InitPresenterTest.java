package net.jagunma.backbone.auth.authmanager.infra.web.ed01010;

import static org.assertj.core.api.Assertions.assertThat;

import net.jagunma.backbone.auth.authmanager.infra.web.ed01010.vo.Ed01010Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Ed01010InitPresenterTest {

    // 実行既定値
    // オペレーター項目系
    private Long operatorId = 123456L;
    private String operatorCode = "yu123456";
    private String operatorName = "オペレーター名";
    private Long jaId = 6L;
    private String jaCode = "006";
    private Long branchId = 1L;
    private String branchCode = "001";

    // ＪＡAtMoment
    private String jaName = "JA前橋市";
    private JaAtMoment jaAtMoment = JaAtMoment.builder()
        .withIdentifier(jaId)
        .withJaAttribute(JaAttribute.builder()
            .withJaCode(JaCode.of(jaCode))
            .withName(jaName)
            .build())
        .build();

    // 店舗AtMoment
    private BranchAtMoment branchAtMoment = BranchAtMoment.builder().withIdentifier(branchId).withJaAtMoment(jaAtMoment).withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.一般).withBranchCode(BranchCode.of(branchCode)).withName("本店").build()).build();

    // オペレーター系
    private Operator operator = Operator.createFrom(operatorId, operatorCode, operatorName, null, null, null, null, jaId, jaCode, branchId, branchCode, null, null, branchAtMoment);

    // モード
    private String mode = "";

    /**
     * {@link Ed01010InitPresenter#bindTo(Ed01010Vo vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test() {
        // 実行値
        Ed01010Vo vo = new Ed01010Vo();
        mode = "Change";
        vo.setMode(mode);
        vo.setOperatorId(operatorId);
        Ed01010InitPresenter presenter = new Ed01010InitPresenter();
        presenter.setOperator(operator);

        // 期待値
        Ed01010Vo expectedVo = new Ed01010Vo();
        expectedVo.setMode("Change");
        expectedVo.setOperatorId(operatorId);
        expectedVo.setJa(jaCode + " " + jaName);
        expectedVo.setOperator(operatorCode + " " + operatorName);
        expectedVo.setOldPassword(null);
        expectedVo.setNewPassword(null);
        expectedVo.setConfirmPassword(null);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}