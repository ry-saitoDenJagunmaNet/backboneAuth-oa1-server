package net.jagunma.backbone.auth.authmanager.infra.api.oa31010;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.operator.OperatorCode;
import net.jagunma.common.values.model.operator.SimpleOperator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa31010SearchSimpleOperatorPresenterTest {

    // 実行既定値
    private Long operatorId = 1018L;
    private String operatorCode = "yu001009";
    private String operatorName = "ｙｕ００１００９";
    private String mailAddress = "yu001009@abcd.com";
    private LocalDate validThruStartDate = LocalDate.of(2010,8,17);
    private LocalDate validThruEndDate = LocalDate.of(9999,12,31);
    private Boolean isDeviceAuth = false;
    private Long jaId = 6L;
    private String jaCode = "006";
    private Long branchId = 33L;
    private String branchCode = "001";
    private AvailableStatus availableStatus = AvailableStatus.利用可能;
    private Integer recordVersion = 1;
    private BranchAtMoment branchAtMoment = null;

    // オペレーターデータ作成
    private Operator createOperator() {
        return Operator.createFrom(
            operatorId,
            operatorCode,
            operatorName,
            mailAddress,
            validThruStartDate,
            validThruEndDate,
            isDeviceAuth,
            jaId,
            jaCode,
            branchId,
            branchCode,
            availableStatus,
            recordVersion,
            branchAtMoment
        );
    }

    /**
     * {@link Oa31010SearchSimpleOperatorPresenter#bindToSimpleOperator()}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・SimpleOperatorへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindToSimpleOperator_test0() {

        // テスト対象クラス生成
        Oa31010SearchSimpleOperatorPresenter presenter = new Oa31010SearchSimpleOperatorPresenter();

        // 実行値
        presenter.setOperator(createOperator());

        // 期待値
        SimpleOperator expected = SimpleOperator.builder()
            .withIdentifier(operatorId)
            .withOperatorCode(OperatorCode.reconstruct(operatorCode))
            .withOperatorName(operatorName)
            .withBranch(branchAtMoment)
            .build();

        // 実行
        SimpleOperator actual = presenter.bindToSimpleOperator();

        // 結果検証
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}