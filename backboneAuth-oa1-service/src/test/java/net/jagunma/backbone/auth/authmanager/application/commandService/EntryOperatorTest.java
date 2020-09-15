package net.jagunma.backbone.auth.authmanager.application.commandService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.lang.reflect.Method;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPackRepositoryForStore;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.server.model.securities.AuthInf;
import net.jagunma.common.server.model.securities.Route;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.dateProvider.DateProvider;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import net.jagunma.common.values.model.operator.OperatorCode;
import net.jagunma.common.values.model.operator.SimpleOperator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class EntryOperatorTest {

    // 実行既定値
    private String branchAtMomentJaCode = "001";     // ＪＡコード
    private Long branchAtMomentTempoId = 105L;       // 店舗ID
    private String branchAtMomentBranchCode = "105"; // 店舗コード
    private String authInfJaCode = "001";            // ＪＡコード
    private void setAuthInf() {
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
            .build();;
        AuditInfoHolder.set(AuthInf.createFrom("006", "001", 18L, "yu001009", "001.001.001.001"),
            DateProvider.currentLocalDateTime(),
            Route.createFrom("", ""),
            jaAtMoment,
            branchAtMoment,
            new SimpleOperator(18l, new OperatorCode("yu001009"), "ｙｕ００１００９", 33l, branchAtMoment ));
//        JaAtMoment jaAtMoment = JaAtMoment.builder()
//            .withIdentifier(1L)
//            .withJaAttribute(JaAttribute
//                .builder()
//                .withJaCode(JaCode.of(authInfJaCode))
//                .build())
//            .build();
//        BranchAtMoment branchAtMoment = BranchAtMoment.builder()
//            .withIdentifier(branchAtMomentTempoId)
//            .withJaAtMoment(jaAtMoment)
//            .withBranchAttribute(BranchAttribute.builder()
//                .withBranchType(BranchType.一般)
//                .withName("")
//                .build())
//            .build();
//
//        AuditInfoHolder.set(AuthInf.createFrom(null, null, 1L, null, null),
//            DateProvider.currentLocalDateTime(),
//            Route.createFrom("", ""),
//            jaAtMoment,
//            branchAtMoment,
//            new SimpleOperator(1L, null, null, branchAtMomentTempoId, branchAtMoment ));
    }

    // テスト対象クラス生成
    private EntryOperator createEntryOperator() {
        OperatorEntryPackRepositoryForStore operatorEntryPackRepositoryForStore = new OperatorEntryPackRepositoryForStore() {
            @Override
            public void insert(OperatorEntryPack operatorEntryPack) {
            }
        };
        BranchAtMomentRepository branchAtMomentRepository = new BranchAtMomentRepository() {
            @Override
            public BranchAtMoment findOneBy(BranchAtMomentCriteria criteria) {

                BranchAtMoment branchAtMoment = createBranchAtMoment(branchAtMomentJaCode, branchAtMomentTempoId, branchAtMomentBranchCode);

                return branchAtMoment;
            }
            @Override
            public BranchesAtMoment selectBy(BranchAtMomentCriteria criteria, Orders orders) {
                return null;
            }
        };

        return new EntryOperator(operatorEntryPackRepositoryForStore, branchAtMomentRepository);
    }

    // 店舗AtMoment
    private BranchAtMoment createBranchAtMoment(String jaCode, Long tempoId, String tempoCode) {

        if (jaCode == null || tempoId == null || tempoCode == null) {
            return BranchAtMoment.empty();
        }

        BranchAtMoment branchAtMoment = BranchAtMoment.builder()
            .withIdentifier(tempoId)
            .withJaAtMoment(JaAtMoment.builder()
                .withJaAttribute(JaAttribute.builder()
                    .withJaCode(JaCode.of(jaCode))
                    .build())
                .build())
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchCode(BranchCode.of(tempoCode))
                .build())
            .build();

        return branchAtMoment;
    }


    /**
     * {@link EntryOperator#execute(OperatorEntryRequest)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test0() {



    }

    /**
     * {@link EntryOperator getBranchAtMoment(tempoId)}テスト
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

        // 実行値
        branchAtMomentJaCode = null;
        branchAtMomentTempoId = null;
        branchAtMomentBranchCode = null;
        Long testBranchAtMomentTempoId = 105L;

        // テスト対象クラス生成
        EntryOperator entryOperator = createEntryOperator();

        assertThatThrownBy(() ->
            // 実行
            entryOperator.getBranchAtMoment(testBranchAtMomentTempoId))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA12001");
                assertThat(e.getArgs()).containsSequence(testBranchAtMomentTempoId);
            });
    }

    /**
     * {@link EntryOperator checkBranchBelongJa(branchAtMoment)}テスト
     *  ●パターン
     *    店舗所属JA不一致
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void checkBranchBelongJa_test1() {

        // 認証情報
        setAuthInf();

        // 実行値
        BranchAtMoment branchAtMoment = createBranchAtMoment("009", branchAtMomentTempoId, branchAtMomentBranchCode);

        // テスト対象クラス生成
        EntryOperator entryOperator = createEntryOperator();

        assertThatThrownBy(() ->
            // 実行
            entryOperator.checkBranchBelongJa(branchAtMoment))
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA12002");
                assertThat(e.getArgs()).containsSequence(AuditInfoHolder.getJa().getJaAttribute().getJaCode());
                assertThat(e.getArgs()).containsSequence(branchAtMoment.getJaAtMoment().getJaAttribute().getJaCode());
            });
    }
}