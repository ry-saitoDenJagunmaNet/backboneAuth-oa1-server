package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBranchAtMoment;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorUpdateRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorUpdatePack;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.util.TestAuditInfoHolder;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchNotFoundException;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UpdateOperatorTest {

    // 実行既定値
    private Long operatorId = 123456L;
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate validThruStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate validThruEndDate = LocalDate.of(2020, 9, 30);
    private Boolean isDeviceAuth = true;
    private Long branchId = 1L;
    private AvailableStatus availableStatus = AvailableStatus.利用可能;
    private Integer recordVersion = 1;
    private String changeCause = "認証機器使用開始";
    private OperatorUpdateRequest createRequest() {
        return new OperatorUpdateRequest() {
            @Override
            public Long getOperatorId() {
                return operatorId;
            }
            @Override
            public String getOperatorName() {
                return operatorName;
            }
            @Override
            public String getMailAddress() {
                return mailAddress;
            }
            @Override
            public LocalDate getValidThruStartDate() {
                return validThruStartDate;
            }
            @Override
            public LocalDate getValidThruEndDate() {
                return validThruEndDate;
            }
            @Override
            public Boolean getIsDeviceAuth() {
                return isDeviceAuth;
            }
            @Override
            public Long getBranchId() {
                return branchId;
            }
            @Override
            public AvailableStatus getAvailableStatus() {
                return availableStatus;
            }
            @Override
            public Integer getRecordVersion() {
                return recordVersion;
            }
            @Override
            public String getChangeCause() {
                return changeCause;
            }
        };
    }

    // テスト対象クラス生成
    private UpdateOperator createUpdateOperator() {
        OperatorRepositoryForStore operatorRepositoryForStore = new OperatorRepositoryForStore() {
            @Override
            public void entry(OperatorEntryPack operatorEntryPack) {

            }
            @Override
            public void update(OperatorUpdatePack operatorUpdatePack) {

            }
        };

        BranchAtMomentRepository branchAtMomentRepository = new BranchAtMomentRepository() {
            @Override
            public BranchAtMoment findOneBy(BranchAtMomentCriteria criteria) throws BranchNotFoundException {
                return null;
            }
            @Override
            public BranchesAtMoment selectBy(BranchAtMomentCriteria criteria, Orders orders) {
                return null;
            }
        };
        SearchBranchAtMoment searchBranchAtMoment = new SearchBranchAtMoment(branchAtMomentRepository) {
            public BranchAtMoment findOneBy(long branchId) {
                return createBranchAtMoment();
            }
        };

        return new UpdateOperator(operatorRepositoryForStore, searchBranchAtMoment);
    }

    // 店舗AtMoment
    private BranchAtMoment createBranchAtMoment() {
        return BranchAtMoment.builder()
            .withIdentifier(AuditInfoHolder.getBranch().getIdentifier())
            .withJaAtMoment(JaAtMoment.builder()
                .withJaAttribute(JaAttribute.builder()
                    .withJaCode(JaCode.of(AuditInfoHolder.getJa().getJaAttribute().getJaCode().getValue()))
                    .build())
                .build())
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchCode(
                    BranchCode.of(AuditInfoHolder.getBranch().getBranchAttribute().getBranchCode().getValue()))
                .build())
            .build();
    }

    UpdateOperatorTest () {
        // 認証情報
        TestAuditInfoHolder.setAuthInf();
    }

    /**
     * {@link UpdateOperator#execute(OperatorUpdateRequest request)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test() {
        // テスト対象クラス生成
        UpdateOperator updateOperator = createUpdateOperator();

        // 実行値
        OperatorUpdateRequest request = createRequest();

        assertThatCode(() ->
            // 実行
            updateOperator.execute(request))
            .doesNotThrowAnyException();
    }

    /**
     * {@link UpdateOperator#createOperatorUpdatePack(
     *      OperatorUpdateRequest request,
     *      String branchCode)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・OperatorUpdatePackへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createOperatorUpdatePack_test() {
        // テスト対象クラス生成
        UpdateOperator updateOperator = createUpdateOperator();

        // 実行値
        OperatorUpdateRequest request = createRequest();
        BranchAtMoment branchAtMoment = createBranchAtMoment();

        // 実行
        OperatorUpdatePack operatorUpdatePack = updateOperator.createOperatorUpdatePack(
            request,
            branchAtMoment.getBranchAttribute().getBranchCode().getValue());

        // 結果検証
        assertTrue(operatorUpdatePack instanceof OperatorUpdatePack);
        assertThat(operatorUpdatePack.getOperatorId()).isEqualTo(request.getOperatorId());
        assertThat(operatorUpdatePack.getOperatorName()).isEqualTo(request.getOperatorName());
        assertThat(operatorUpdatePack.getMailAddress()).isEqualTo(request.getMailAddress());
        assertThat(operatorUpdatePack.getValidThruStartDate()).isEqualTo(request.getValidThruStartDate());
        assertThat(operatorUpdatePack.getValidThruEndDate()).isEqualTo(request.getValidThruEndDate());
        assertThat(operatorUpdatePack.getIsDeviceAuth()).isEqualTo(request.getIsDeviceAuth());
        assertThat(operatorUpdatePack.getBranchId()).isEqualTo(request.getBranchId());
        assertThat(operatorUpdatePack.getBranchCode()).isEqualTo(branchAtMoment.getBranchAttribute().getBranchCode().getValue());
        assertThat(operatorUpdatePack.getAvailableStatus()).isEqualTo(request.getAvailableStatus());
        assertThat(operatorUpdatePack.getRecordVersion()).isEqualTo(request.getRecordVersion());
        assertThat(operatorUpdatePack.getChangeCause()).isEqualTo(request.getChangeCause());
    }
}