package net.jagunma.backbone.auth.authmanager.infra.web.oa11020;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.commandService.EntryOperator;
import net.jagunma.backbone.auth.authmanager.application.queryService.TempoReferenceService;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoReferenceDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorEntryRequest;
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
import net.jagunma.common.util.exception.SRuntimeException;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class Oa11020ControllerTest {

    // 実行既定値（entry）
    private Long tempoId = 1L;
    private String operatorCode6 = "123456";
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate expirationStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate expirationEndDate = LocalDate.of(2020, 9, 30);
    private String changeCause = "新職員の入組による登録";
    private String password = "PaSsWoRd";
    private String confirmPassword = "pAsSwOrD";

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

        TempoReferenceService tempoReferenceService = new TempoReferenceService(operatorEntityDao) {
            public List<TempoReferenceDto> getComboBoxList(long jaid) {
                return createTempoList();
            }
        };
        EntryOperator entryOperator = new EntryOperator(operatorEntryPackRepositoryForStore, branchAtMomentRepository) {
            @Override
            public void execute(OperatorEntryRequest request) {
                // request.getTempoId() = 11 の場合：GunmaRuntimeException を発生させる
                // request.getTempoId() = 12 の場合：RuntimeException を発生させる
                if(request.getTempoId().equals(11L)) {
                    Preconditions.checkNotNull(null, () -> new GunmaRuntimeException("EOA13002", "店舗ID"));
                }
                if(request.getTempoId().equals(12L)) {
                    throw new RuntimeException();
                }
            }
        };

        return new Oa11020Controller(tempoReferenceService, entryOperator);
    }

    // 期待値Vo作成
    private Oa11020Vo createExpectedVo() {
        Oa11020Vo expectedVo = new Oa11020Vo();
        expectedVo.setJa(AuditInfoHolder.getAuthInf().getJaCode() + " " +AuditInfoHolder.getJa().getJaAttribute().getName());
        expectedVo.setOperatorCodePrefix(OperatorCodePrefix.codeOf(AuditInfoHolder.getAuthInf().getJaCode()).getPrefix());
        expectedVo.setOperatorCode6(null);
        expectedVo.setOperatorName(null);
        expectedVo.setMailAddress(null);
        expectedVo.setExpirationStartDate(null);
        expectedVo.setExpirationEndDate(null);
        expectedVo.setChangeCause(null);
        expectedVo.setTempoList(createTempoList());
        expectedVo.setPassword(null);
        expectedVo.setConfirmPassword(null);

        return expectedVo;
    }

    // 店舗リスト作成
    private List<TempoReferenceDto> createTempoList() {
        List<TempoReferenceDto> tempoList = newArrayList();
        TempoReferenceDto tempoReferenceDto;
        tempoReferenceDto = new TempoReferenceDto();
        tempoReferenceDto.setTempoCode("001");
        tempoReferenceDto.setTempoName("本店");
        tempoList.add(tempoReferenceDto);
        tempoReferenceDto = new TempoReferenceDto();
        tempoReferenceDto.setTempoCode("002");
        tempoReferenceDto.setTempoName("テスト店舗002");
        tempoList.add(tempoReferenceDto);
        tempoReferenceDto = new TempoReferenceDto();
        tempoReferenceDto.setTempoCode("003");
        tempoReferenceDto.setTempoName("テスト店舗003");
        tempoList.add(tempoReferenceDto);

        return tempoList;
    }

    Oa11020ControllerTest () {
        // 認証情報
        TestAuditInfoHolder.setAuthInf();
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
        Oa11020Vo expectedVo = createExpectedVo();

        // 実行
        String actualViewName = oa11020Controller.get(model);
        Oa11020Vo actualVo = (Oa11020Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        verifyVoAssert(actualVo, expectedVo);
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
        Oa11020Vo vo = new Oa11020Vo();
        vo.setTempoId(tempoId);
        vo.setOperatorCode6(operatorCode6);
        vo.setOperatorName(operatorName);
        vo.setMailAddress(mailAddress);
        vo.setExpirationStartDate(expirationStartDate);
        vo.setExpirationEndDate(expirationEndDate);
        vo.setChangeCause(changeCause);
        vo.setPassword(password);
        vo.setConfirmPassword(confirmPassword);

        // 期待値
        String expectedViewName = "oa11020";
        Oa11020Vo expectedVo = createExpectedVo();

        // 実行
        String actualViewName = oa11020Controller.entry(model, vo);
        Oa11020Vo actualVo = (Oa11020Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        verifyVoAssert(actualVo, expectedVo);
    }

    // 結果検証（Vo比較）
    private void verifyVoAssert(Oa11020Vo vo, Oa11020Vo expectedVo) {
        assertTrue(expectedVo instanceof Oa11020Vo);
        assertThat(vo.getJa()).isEqualTo(expectedVo.getJa());
        assertThat(vo.getOperatorCodePrefix()).isEqualTo(expectedVo.getOperatorCodePrefix());
        assertThat(vo.getOperatorCode6()).isEqualTo(expectedVo.getOperatorCode6());
        assertThat(vo.getOperatorName()).isEqualTo(expectedVo.getOperatorName());
        assertThat(vo.getMailAddress()).isEqualTo(expectedVo.getMailAddress());
        assertThat(vo.getExpirationStartDate()).isEqualTo(expectedVo.getExpirationStartDate());
        assertThat(vo.getExpirationEndDate()).isEqualTo(expectedVo.getExpirationEndDate());
        assertThat(vo.getChangeCause()).isEqualTo(expectedVo.getChangeCause());
        for(int i = 0; i < vo.getTempoList().size(); i++) {
            assertThat(vo.getTempoList().get(i).getTempoCode()).isEqualTo(expectedVo.getTempoList().get(i).getTempoCode());
            assertThat(vo.getTempoList().get(i).getTempoName()).isEqualTo(expectedVo.getTempoList().get(i).getTempoName());
        }
        // ToDo:
        assertThat(vo.getPassword()).isEqualTo(expectedVo.getPassword());
        assertThat(vo.getConfirmPassword()).isEqualTo(expectedVo.getConfirmPassword());
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
     *  ・戻り値
     *  ・エラーメッセージのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void entry_test1() {
        // テスト対象クラス生成
        Oa11020Controller oa11020Controller = createOa11020Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa11020Vo vo = new Oa11020Vo();
        tempoId = 11L;
        vo.setTempoId(tempoId);

        // 期待値
        String expectedViewName = "oa11020";
        String expectedMessageCode = "EOA13002";
        String expectedMessageArgs0 = "店舗ID";

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
     *  ・戻り値
     *  ・エラーメッセージのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void entry_test2() {
        // テスト対象クラス生成
        Oa11020Controller oa11020Controller = createOa11020Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa11020Vo vo = new Oa11020Vo();
        tempoId = 12L;
        vo.setTempoId(tempoId);

        // 期待値
        String expectedViewName = "oa19999";
        String expectedErrorMessage = "サーバーで予期しないエラーが発生しました。";

        // 実行
        String actualViewName = oa11020Controller.entry(model, vo);
        Oa11020Vo actualVo = (Oa11020Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getErrorMessage()).isEqualTo(expectedErrorMessage);
    }
}