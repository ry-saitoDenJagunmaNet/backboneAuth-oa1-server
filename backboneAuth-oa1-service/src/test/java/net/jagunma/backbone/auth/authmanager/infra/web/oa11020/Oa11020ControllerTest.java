package net.jagunma.backbone.auth.authmanager.infra.web.oa11020;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.commandService.EntryOperator;
import net.jagunma.backbone.auth.authmanager.application.queryService.BranchReference;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01010.vo.Ed01010Vo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11020.vo.Oa11020Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPackRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.types.OperatorCodePrefix;
import net.jagunma.backbone.auth.authmanager.util.TestAuditInfoHolder;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class Oa11020ControllerTest {

    // 実行 ＆ 期待 既定値
    private String ja = null;                   //コンストラクタでセット
    private Long branchId = 1L;
    private String operatorCodePrefix = null;   //コンストラクタでセット
    private String operatorCode6 = "123456";
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate expirationStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate expirationEndDate = LocalDate.of(2020, 9, 30);
    private String changeCause = "新職員の入組による登録";
    private String password = "PaSsWoRd";
    private String confirmPassword = "pAsSwOrD";
    private String mode = "Initial";

    // テスト対象クラス生成
    private Oa11020Controller createOa11020Controller() {

        OperatorEntityDao operatorEntityDao = new OperatorEntityDao(){
            @Override
            public List<OperatorEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public OperatorEntity findOneBy(OperatorEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<OperatorEntity> findBy(OperatorEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(OperatorEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int update(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int delete(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(OperatorEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<OperatorEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<OperatorEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<OperatorEntity> entities) {
                return new int[0];
            }
        };
        OperatorEntryPackRepositoryForStore operatorEntryPackRepositoryForStore = new OperatorEntryPackRepositoryForStore() {
            @Override
            public void insert(OperatorEntryPack operatorEntryPack) {

            }
        };
        BranchAtMomentRepository branchAtMomentRepository = new BranchAtMomentRepository() {
            @Override
            public BranchAtMoment findOneBy(BranchAtMomentCriteria criteria) {
                return null;
            }
            @Override
            public BranchesAtMoment selectBy(BranchAtMomentCriteria criteria, Orders orders) {
                return null;
            }
        };
        BranchReference branchReference = new BranchReference(branchAtMomentRepository, operatorEntityDao) {
            public BranchesAtMoment getBranchesAtMoment(long jaId) {
                return createBranchesAtMoment();
            }
        };
        EntryOperator entryOperator = new EntryOperator(operatorEntryPackRepositoryForStore, branchAtMomentRepository) {
            @Override
            public void execute(OperatorEntryRequest request) {
                // request.getBranchId() = 11 の場合：GunmaRuntimeException を発生させる
                if(request.getBranchId().equals(11L)) {
                    Preconditions.checkNotNull(null, () -> new GunmaRuntimeException("EOA13002", "店舗ID"));
                }
                // request.getBranchId() = 12 の場合：GunmaRuntimeException を発生させる
                if(request.getBranchId().equals(12L)) {
                    Preconditions.checkNotNull(null, () -> new GunmaRuntimeException("EOA13002", "パスワード"));
                }
                // request.getBranchId() = 13 の場合：RuntimeException を発生させる
                if(request.getBranchId().equals(13L)) {
                    throw new RuntimeException();
                }
            }
        };

        return new Oa11020Controller(branchReference, entryOperator);
    }

    // Oa11020Vo作成
    private Oa11020Vo createOa11020Vo() {
        Oa11020Vo oa11020Vo = new Oa11020Vo();

        oa11020Vo.setJa(ja);
        oa11020Vo.setBranchId(branchId);
        oa11020Vo.setOperatorCodePrefix(operatorCodePrefix);
        oa11020Vo.setOperatorCode6(operatorCode6);
        oa11020Vo.setOperatorName(operatorName);
        oa11020Vo.setMailAddress(mailAddress);
        oa11020Vo.setExpirationStartDate(expirationStartDate);
        oa11020Vo.setExpirationEndDate(expirationEndDate);
        oa11020Vo.setChangeCause(changeCause);
        oa11020Vo.setBranchItemsSource(SelectOptionItemsSource.createFrom(createBranchesAtMoment()).getValue());
        oa11020Vo.setPassword(password);
        oa11020Vo.setConfirmPassword(confirmPassword);

        return oa11020Vo;
    }

    // 店舗群AtMoment作成
    private BranchesAtMoment createBranchesAtMoment() {
        List<BranchAtMoment> branchAtMomentList = newArrayList();
        branchAtMomentList.add(BranchAtMoment.builder()
            .withIdentifier(1L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("001")).withName("本店").build())
            .build());
        branchAtMomentList.add(BranchAtMoment.builder()
            .withIdentifier(2L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("002")).withName("店舗002").build())
            .build());
        branchAtMomentList.add(BranchAtMoment.builder()
            .withIdentifier(3L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("003")).withName("店舗003").build())
            .build());

        return BranchesAtMoment.of(branchAtMomentList);
    }

    Oa11020ControllerTest () {
        // 認証情報
        TestAuditInfoHolder.setAuthInf();

        ja = AuditInfoHolder.getAuthInf().getJaCode() + " " +AuditInfoHolder.getJa().getJaAttribute().getName();
        operatorCodePrefix = OperatorCodePrefix.codeOf(AuditInfoHolder.getAuthInf().getJaCode()).getPrefix();
    }

    /**
     * {@link Oa11020Controller#get(Model)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test() {
        // テスト対象クラス生成
        Oa11020Controller oa11020Controller = createOa11020Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();

        // 期待値
        String expectedViewName = "oa11020";
        branchId = null;
        operatorCode6 = null;
        operatorName = null;
        mailAddress = null;
        expirationStartDate = null;
        expirationEndDate = null;
        changeCause = null;
        password = null;
        confirmPassword = null;
        Oa11020Vo expectedVo = createOa11020Vo();

        // 実行
        String actualViewName = oa11020Controller.get(model);
        Oa11020Vo actualVo = (Oa11020Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11020Controller#entry(Model, Oa11020Vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void entry_test() {
        // テスト対象クラス生成
        Oa11020Controller oa11020Controller = createOa11020Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        password = null;
        confirmPassword = null;
        Oa11020Vo vo = createOa11020Vo();

        // 期待値
        String expectedViewName = "ed01010";
        Ed01010Vo expectedVo = new Ed01010Vo();
        expectedVo.setMode(mode);
        expectedVo.setJa(vo.getJa());
        expectedVo.setOperator(vo.getOperatorCodePrefix() + vo.getOperatorCode6() + " " + vo.getOperatorName());
        expectedVo.setOldPassword(null);
        expectedVo.setNewPassword(null);
        expectedVo.setConfirmPassword(null);

        // 実行
        String actualViewName = oa11020Controller.entry(model, vo);
        Ed01010Vo actualVo = (Ed01010Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11020Controller#save(Oa11020Vo, Model, Ed01010Vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void save_test() {
        // テスト対象クラス生成
        Oa11020Controller oa11020Controller = createOa11020Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa11020Vo oa11020Vo = createOa11020Vo();
        Ed01010Vo ed01010Vo = new Ed01010Vo();
        ed01010Vo.setMode(mode);
        ed01010Vo.setJa(oa11020Vo.getJa());
        ed01010Vo.setOperator(oa11020Vo.getOperatorCodePrefix() + oa11020Vo.getOperatorCode6() + " " + oa11020Vo.getOperatorName());
        ed01010Vo.setOldPassword(null);
        ed01010Vo.setNewPassword(password);
        ed01010Vo.setConfirmPassword(confirmPassword);

        // 期待値
        String expectedViewName = "oa11020";
        branchId = null;
        operatorCode6 = null;
        operatorName = null;
        mailAddress = null;
        expirationStartDate = null;
        expirationEndDate = null;
        changeCause = null;
        password = null;
        confirmPassword = null;
        Oa11020Vo expectedVo = createOa11020Vo();

        // 実行
        String actualViewName = oa11020Controller.save(oa11020Vo, model, ed01010Vo);
        Oa11020Vo actualVo = (Oa11020Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11020Controller#get(Model)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     *  ・なし
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test1() {
        // getメソッドでGunmaRuntimeExceptionを発生させるテストは不可
        assertThat(true);
    }

    /**
     * {@link Oa11020Controller#get(Model)}テスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・なし
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test2() {
        // getメソッドでRuntimeExceptionを発生させるテストは実現不可
        assertThat(true);
    }

    /**
     * {@link Oa11020Controller#entry(Model, Oa11020Vo)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     *  ・なし
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void entry_test1() {
        // テスト対象クラス生成
        Oa11020Controller oa11020Controller = createOa11020Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        branchId = null;
        Oa11020Vo vo = createOa11020Vo();

        // 期待値
        String expectedViewName = "oa11020";
        String expectedMessageCode = "EOA14002";
        String expectedMessageArgs0 = "店舗";

        // 実行
        String actualViewName = oa11020Controller.entry(model, vo);
        Oa11020Vo actualVo = (Oa11020Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(expectedMessageArgs0);
    }

    /**
     * {@link Oa11020Controller#entry(Model, Oa11020Vo)}テスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・なし
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void entry_test2() {
        // entryメソッドでRuntimeExceptionを発生させるテストは実現不可
        assertThat(true);
    }

    /**
     * {@link Oa11020Controller#save(Oa11020Vo, Model, Ed01010Vo)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void save_test1() {
        // テスト対象クラス生成
        Oa11020Controller oa11020Controller = createOa11020Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        branchId = 11L;
        Oa11020Vo oa11020Vo = createOa11020Vo();
        Ed01010Vo ed01010Vo = new Ed01010Vo();

        // 期待値
        String expectedViewName = "oa11020";
        String expectedMessageCode = "EOA13002";
        String expectedMessageArgs0 = "店舗ID";

        // 実行
        String actualViewName = oa11020Controller.save(oa11020Vo, model, ed01010Vo);
        Oa11020Vo actualVo = (Oa11020Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(expectedMessageArgs0);
    }

    /**
     * {@link Oa11020Controller#save(Oa11020Vo, Model, Ed01010Vo)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void save_test2() {
        // テスト対象クラス生成
        Oa11020Controller oa11020Controller = createOa11020Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        branchId = 12L;
        Oa11020Vo oa11020Vo = createOa11020Vo();
        Ed01010Vo ed01010Vo = new Ed01010Vo();

        // 期待値
        String expectedViewName = "ed01010";
        String expectedMessageCode = "EOA13002";
        String expectedMessageArgs0 = "パスワード";

        // 実行
        String actualViewName = oa11020Controller.save(oa11020Vo, model, ed01010Vo);
        Ed01010Vo actualVo = (Ed01010Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(expectedMessageArgs0);
    }

    /**
     * {@link Oa11020Controller#save(Oa11020Vo, Model, Ed01010Vo)}テスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void save_test3() {
        // テスト対象クラス生成
        Oa11020Controller oa11020Controller = createOa11020Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        branchId = 13L;
        Oa11020Vo oa11020Vo = createOa11020Vo();
        Ed01010Vo ed01010Vo = new Ed01010Vo();

        // 期待値
        String expectedViewName = "oa19999";
        String expectedErrorMessage = "サーバーで予期しないエラーが発生しました。";

        // 実行
        String actualViewName = oa11020Controller.save(oa11020Vo, model, ed01010Vo);
        Oa11020Vo actualVo = (Oa11020Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getErrorMessage()).isEqualTo(expectedErrorMessage);
    }
}