package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorUpdateRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorUpdatePack;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.OperatorCodePrefix;
import net.jagunma.backbone.auth.authmanager.util.TestAuditInfoHolder;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
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
    private LocalDate expirationStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate expirationEndDate = LocalDate.of(2020, 9, 30);
    private Boolean isDeviceAuth = true;
    private Long branchId = 1L;
    private String branchCode = "001";
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
            public LocalDate getExpirationStartDate() {
                return expirationStartDate;
            }
            @Override
            public LocalDate getExpirationEndDate() {
                return expirationEndDate;
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
            public BranchAtMoment findOneBy(BranchAtMomentCriteria criteria) {
                // 店舗未存在 テスト時
                if (!criteria.getIdentifierCriteria().getEqualTo().equals(AuditInfoHolder.getBranch().getIdentifier())) {
                    return BranchAtMoment.empty();
                }

                return createBranchAtMoment();
            }
            @Override
            public BranchesAtMoment selectBy(BranchAtMomentCriteria criteria, Orders orders) {
                return null;
            }
        };

        return new UpdateOperator(operatorRepositoryForStore, branchAtMomentRepository);
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
        OperatorUpdateRequest operatorUpdateRequest = createRequest();

        assertThatCode(() ->
            // 実行
            updateOperator.execute(operatorUpdateRequest))
            .doesNotThrowAnyException();
    }

    /**
     * {@link UpdateOperator#getBranchAtMoment(Long branchId)}テスト
     *  ●パターン
     *    店舗未存在
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void getBranchAtMoment_test1() {
        // テスト対象クラス生成
        UpdateOperator updateOperator = createUpdateOperator();

        // 実行値
        branchId = 999L;

        assertThatThrownBy(() ->
            // 実行
            updateOperator.getBranchAtMoment(branchId))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA12001");
                assertThat(e.getArgs()).containsSequence(branchId);
            });
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
        OperatorUpdateRequest operatorUpdateRequest = createRequest();
        BranchAtMoment branchAtMoment = createBranchAtMoment();

        // 実行
        OperatorUpdatePack operatorUpdatePack = updateOperator.createOperatorUpdatePack(
            operatorUpdateRequest,
            branchAtMoment.getBranchAttribute().getBranchCode().getValue());

        // 結果検証
        assertTrue(operatorUpdatePack instanceof OperatorUpdatePack);
        assertThat(operatorUpdatePack.getOperatorId()).isEqualTo(operatorUpdateRequest.getOperatorId());
        assertThat(operatorUpdatePack.getOperatorName()).isEqualTo(operatorUpdateRequest.getOperatorName());
        assertThat(operatorUpdatePack.getMailAddress()).isEqualTo(operatorUpdateRequest.getMailAddress());
        assertThat(operatorUpdatePack.getExpirationStartDate()).isEqualTo(operatorUpdateRequest.getExpirationStartDate());
        assertThat(operatorUpdatePack.getExpirationEndDate()).isEqualTo(operatorUpdateRequest.getExpirationEndDate());
        assertThat(operatorUpdatePack.getIsDeviceAuth()).isEqualTo(operatorUpdateRequest.getIsDeviceAuth());
        assertThat(operatorUpdatePack.getBranchId()).isEqualTo(operatorUpdateRequest.getBranchId());
        assertThat(operatorUpdatePack.getBranchCode()).isEqualTo(branchAtMoment.getBranchAttribute().getBranchCode().getValue());
        assertThat(operatorUpdatePack.getAvailableStatus()).isEqualTo(operatorUpdateRequest.getAvailableStatus());
        assertThat(operatorUpdatePack.getRecordVersion()).isEqualTo(operatorUpdateRequest.getRecordVersion());
        assertThat(operatorUpdatePack.getChangeCause()).isEqualTo(operatorUpdateRequest.getChangeCause());
    }
}