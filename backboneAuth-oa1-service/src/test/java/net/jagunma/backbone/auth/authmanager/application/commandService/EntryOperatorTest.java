package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBranchAtMoment;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorUpdatePack;
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
import net.jagunma.common.values.model.branch.BranchNotFoundException;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class EntryOperatorTest {

    // 実行既定値
    private String operatorCode6 = "123456";
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate expirationStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate expirationEndDate = LocalDate.of(2020, 9, 30);
    private Long branchId = 1L;
    private String changeCause = "新職員の入組による登録";
    private String password = "PaSsWoRd";
    private String confirmPassword = "PaSsWoRd";
    private OperatorEntryRequest createRequest() {
        return new OperatorEntryRequest() {
            @Override
            public String getOperatorCode6() {
                return operatorCode6;
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
            public Long getBranchId() {
                return branchId;
            }
            @Override
            public String getChangeCause() {
                return changeCause;
            }
            @Override
            public String getPassword() {
                return password;
            }
            @Override
            public String getConfirmPassword() {
                return confirmPassword;
            }
        };
    }

    // テスト対象クラス生成
    private EntryOperator createEntryOperator() {
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

        return new EntryOperator(operatorRepositoryForStore, searchBranchAtMoment);
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
                .withBranchCode(BranchCode.of(AuditInfoHolder.getBranch().getBranchAttribute().getBranchCode().getValue()))
                .build())
            .build();
    }

    EntryOperatorTest () {
        // 認証情報
        TestAuditInfoHolder.setAuthInf();
    }

    /**
     * {@link EntryOperator#execute(OperatorEntryRequest request)}テスト
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
        EntryOperator entryOperator = createEntryOperator();

        // 実行値
        OperatorEntryRequest request = createRequest();

        assertThatCode(() ->
            // 実行
            entryOperator.execute(request))
            .doesNotThrowAnyException();
    }

    /**
     * {@link EntryOperator#checkBranchBelongJa(BranchAtMoment branchAtMoment)}テスト
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
        EntryOperator entryOperator = createEntryOperator();

        // 実行値
        BranchAtMoment branchAtMoment = createBranchAtMoment();

        assertThatCode(() ->
            // 実行
            entryOperator.checkBranchBelongJa(branchAtMoment))
            .doesNotThrowAnyException();
    }

    /**
     * {@link EntryOperator#checkBranchBelongJa(BranchAtMoment branchAtMoment)}テスト
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
        EntryOperator entryOperator = createEntryOperator();

        // 実行値
        BranchAtMoment branchAtMoment = createBranchAtMoment("999");

        assertThatThrownBy(() ->
            // 実行
            entryOperator.checkBranchBelongJa(branchAtMoment))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA12001");
                assertThat(e.getArgs()).containsSequence(AuditInfoHolder.getJa().getJaAttribute().getJaCode());
                assertThat(e.getArgs()).containsSequence(branchAtMoment.getJaAtMoment().getJaAttribute().getJaCode());
            });
    }

    /**
     * {@link EntryOperator#createOperatorEntryPack(
     *      OperatorEntryRequest request,
     *      String operatorCodePrefix,
     *      Long jaId,
     *      String jaCode,
     *      String branchCode)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・OperatorEntryPackへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createOperatorEntryPack_test() {
        // テスト対象クラス生成
        EntryOperator entryOperator = createEntryOperator();

        // 実行値
        OperatorEntryRequest request = createRequest();
        BranchAtMoment branchAtMoment = createBranchAtMoment();

        // 実行
        OperatorEntryPack operatorEntryPack = entryOperator.createOperatorEntryPack(
            request,
            OperatorCodePrefix.codeOf(AuditInfoHolder.getAuthInf().getJaCode()).getPrefix(),
            AuditInfoHolder.getJa().getIdentifier(),
            AuditInfoHolder.getJa().getJaAttribute().getJaCode().getValue(),
            branchAtMoment.getBranchAttribute().getBranchCode().getValue());

        // 結果検証
        assertTrue(operatorEntryPack instanceof OperatorEntryPack);
        assertThat(operatorEntryPack.getOperatorCode()).isEqualTo(OperatorCodePrefix.codeOf(AuditInfoHolder.getAuthInf().getJaCode()).getPrefix() + request.getOperatorCode6());
        assertThat(operatorEntryPack.getOperatorName()).isEqualTo(request.getOperatorName());
        assertThat(operatorEntryPack.getMailAddress()).isEqualTo(request.getMailAddress());
        assertThat(operatorEntryPack.getExpirationStartDate()).isEqualTo(request.getExpirationStartDate());
        assertThat(operatorEntryPack.getExpirationEndDate()).isEqualTo(request.getExpirationEndDate());
        assertThat(operatorEntryPack.getJaId()).isEqualTo(AuditInfoHolder.getJa().getIdentifier());
        assertThat(operatorEntryPack.getJaCode()).isEqualTo(AuditInfoHolder.getJa().getJaAttribute().getJaCode().getValue());
        assertThat(operatorEntryPack.getBranchId()).isEqualTo(request.getBranchId());
        assertThat(operatorEntryPack.getBranchCode()).isEqualTo(branchAtMoment.getBranchAttribute().getBranchCode().getValue());
        assertThat(operatorEntryPack.getChangeCause()).isEqualTo(request.getChangeCause());
        assertThat(operatorEntryPack.getPassword()).isEqualTo(request.getPassword());
    }
}