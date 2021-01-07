package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.commandService.GrantSubSystemRole;
import net.jagunma.backbone.auth.authmanager.application.queryService.CopySubSystemRoleAllocate;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchOperator;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleAllocateCommand.SubSystemRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleAllocateReference.SubSystemRoleAllocateCopyRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleAllocateReference.SubSystemRoleAllocateCopyResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AllocateRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040UnallocateRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaders;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class Oa11040ControllerTest {

    // 実行既定値
    // オペレーター項目系
    private Long operatorId = 123456L;
    private String operatorCode = "yu123456";
    private String operatorName = "オペレーター名";

    // オペレーター_サブシステムロール割当履歴項目系
    private SubSystemRole subSystemRole1 = SubSystemRole.JA管理者;
    private SubSystemRole subSystemRole2 = SubSystemRole.業務統括者_購買;
    private SubSystemRole subSystemRole3 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole subSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole subSystemRole5 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate validThruStartDate1 = LocalDate.of(2020, 9, 1);
    private LocalDate validThruStartDate2 = LocalDate.of(2020, 9, 2);
    private LocalDate validThruStartDate3 = LocalDate.of(2020, 9, 3);
    private LocalDate validThruStartDate4 = LocalDate.of(2020, 9, 4);
    private LocalDate validThruStartDate5 = LocalDate.of(2020, 9, 5);
    private LocalDate validThruEndDate1 = LocalDate.of(2020, 9, 21);
    private LocalDate validThruEndDate2 = LocalDate.of(2020, 9, 22);
    private LocalDate validThruEndDate3 = LocalDate.of(2020, 9, 23);
    private LocalDate validThruEndDate4 = LocalDate.of(2020, 9, 24);
    private LocalDate validThruEndDate5 = LocalDate.of(2020, 9, 25);

    // オペレーター履歴ヘッダー項目系
    private String changeCause = "業務統括者（販売・花卉）も兼務";
    private String changeCausePlaceholder = "業務統括者（販売・青果）に昇格";

    // ＪＡAtMoment
    private Long jaId = 6L;
    private String jaCode = "006";
    private String jaName = "JA前橋市";
    private JaAtMoment jaAtMoment = JaAtMoment.builder()
        .withIdentifier(jaId)
        .withJaAttribute(JaAttribute.builder()
            .withJaCode(JaCode.of(jaCode))
            .withName(jaName)
            .build())
        .build();

    // 店舗AtMoment
    private Long branchId = 1L;
    private String branchCode = "001";
    private BranchAtMoment branchAtMoment = BranchAtMoment.builder().withIdentifier(branchId).withJaAtMoment(jaAtMoment).withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.一般).withBranchCode(BranchCode.of(branchCode)).withName("本店").build()).build();

    // オペレーター系
    private Operator operator = Operator.createFrom(operatorId, operatorCode, operatorName, null, null, null, null, null, jaCode, branchId, branchCode, null, null, branchAtMoment);

    // オペレーター_サブシステムロール割当履歴系
    private List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(301L, operatorId, subSystemRole1.getCode(), validThruStartDate1, validThruEndDate1, 391, operator, subSystemRole1),
        Operator_SubSystemRole.createFrom(302L, operatorId, subSystemRole2.getCode(), validThruStartDate2, validThruEndDate2, 392, operator, subSystemRole2),
        Operator_SubSystemRole.createFrom(303L, operatorId, subSystemRole3.getCode(), validThruStartDate3, validThruEndDate3, 393, operator, subSystemRole3),
        Operator_SubSystemRole.createFrom(304L, operatorId, subSystemRole4.getCode(), validThruStartDate4, validThruEndDate4, 394, operator, subSystemRole4),
        Operator_SubSystemRole.createFrom(305L, operatorId, subSystemRole5.getCode(), validThruStartDate5, validThruEndDate5, 395, operator, subSystemRole5));
    private Operator_SubSystemRoles operator_SubSystemRoles = Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);

    // オペレーター履歴ヘッダー系
    private List<OperatorHistoryHeader> operatorHistoryHeaderList = newArrayList(
        OperatorHistoryHeader.createFrom(601L, operatorId, LocalDateTime.of(2020,10,1,0,1,2), changeCausePlaceholder, null, operator));
    private OperatorHistoryHeaders operatorHistoryHeaders = OperatorHistoryHeaders.createFrom(operatorHistoryHeaderList);


    // テスト対象クラス生成
    private Oa11040Controller createOa11040Controller() {
        Operator_SubSystemRoleRepositoryForStore operator_SubSystemRoleRepositoryForStore = new Operator_SubSystemRoleRepositoryForStore() {
            @Override
            public void store(Operator_SubSystemRoles operator_SubSystemRoles, String changeCause) {
            }
        };
        GrantSubSystemRole grantSubSystemRole = new GrantSubSystemRole(operator_SubSystemRoleRepositoryForStore) {
            @Override
            public void execute(SubSystemRoleGrantRequest request) {
                // request.getOperatorId().equals(21L) の場合：GunmaRuntimeException を発生させる
                if(request.getOperatorId().equals(21L)) {
                    Preconditions.checkNotNull(null, () -> new GunmaRuntimeException("EOA13002", "オペレーターID"));
                }
                // request.getOperatorId().equals(22L) の場合：RuntimeException を発生させる
                if(request.getOperatorId().equals(22L)) {
                    throw new RuntimeException();
                }
            }
        };

        OperatorRepository operatorRepository = new OperatorRepository() {
            @Override
            public Operator findOneBy(OperatorCriteria operatorCriteria) {
                return null;
            }
            @Override
            public boolean existsBy(OperatorCriteria operatorCriteria) {
                return false;
            }
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                return null;
            }
        };
        Operator_SubSystemRoleRepository operator_SubSystemRoleRepository = new Operator_SubSystemRoleRepository() {
            @Override
            public Operator_SubSystemRoles selectBy(Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {
                return null;
            }
        };
        OperatorHistoryHeaderRepository operatorHistoryHeaderRepository = new OperatorHistoryHeaderRepository() {
            @Override
            public OperatorHistoryHeader latestOneByOperatorId(Long operatorId) {
                return null;
            }
            @Override
            public OperatorHistoryHeaders selectBy(OperatorHistoryHeaderCriteria operatorHistoryHeaderCriteria, Orders orders) {
                return null;
            }
        };
        SearchOperator searchOperator = new SearchOperator(operatorRepository, null, null, null, null, operator_SubSystemRoleRepository, null, operatorHistoryHeaderRepository) {
            @Override
            public void execute(OperatorSearchRequest request, OperatorSearchResponse response) {
                // request.getOperatorIdCriteria().getEqualTo().equals(11L) の場合：GunmaRuntimeException を発生させる
                if(request.getOperatorIdCriteria().getEqualTo().equals(11L)) {
                    Preconditions.checkNotNull(null, () -> new GunmaRuntimeException("EOA13008", "有効期限開始"));
                }
                // request.getOperatorIdCriteria().getEqualTo().equals(12L) の場合：RuntimeException を発生させる
                if(request.getOperatorIdCriteria().getEqualTo().equals(12L)) {
                    throw new RuntimeException();
                }
                response.setOperator(operator);
                response.setOperator_SubSystemRoles(operator_SubSystemRoles);
                response.setOperatorHistoryHeaders(operatorHistoryHeaders);
            }
        };

        CopySubSystemRoleAllocate copySubSystemRoleAllocate = new CopySubSystemRoleAllocate(operator_SubSystemRoleRepository) {
            @Override
            public void execute(SubSystemRoleAllocateCopyRequest request, SubSystemRoleAllocateCopyResponse response) {

            }
        };

        return new Oa11040Controller(grantSubSystemRole, searchOperator, copySubSystemRoleAllocate);
    }

    // Oa11040Vo作成
    private Oa11040Vo createOa11040Vo() {
        List<Oa11040AllocateRoleTableVo> oa11040AllocateRoleTableVoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()) {
            Oa11040AllocateRoleTableVo oa11040AllocateRoleTableVo = new Oa11040AllocateRoleTableVo();
            oa11040AllocateRoleTableVo.setRoleCode(operator_SubSystemRole.getSubSystemRoleCode());
            oa11040AllocateRoleTableVo.setRoleName(operator_SubSystemRole.getSubSystemRole().getDisplayName());
            oa11040AllocateRoleTableVo.setValidThruStartDate(operator_SubSystemRole.getValidThruStartDate());
            oa11040AllocateRoleTableVo.setValidThruEndDate(operator_SubSystemRole.getValidThruEndDate());
            oa11040AllocateRoleTableVoList.add(oa11040AllocateRoleTableVo);
        }
        List<Oa11040UnallocateRoleTableVo> oa11040UnallocateRoleTableVoList = newArrayList();
        for (SubSystemRole subSystemRole : SubSystemRole.values()) {//ToDo:ソートオーダー回し実装（ユーティリティで実現）
            if (subSystemRole.getCode().length() == 0) { continue; }

            Oa11040UnallocateRoleTableVo oa11040UnallocateRoleTableVo = new Oa11040UnallocateRoleTableVo();
            oa11040UnallocateRoleTableVo.setRoleCode(subSystemRole.getCode());
            oa11040UnallocateRoleTableVo.setRoleName(subSystemRole.getDisplayName());
            oa11040UnallocateRoleTableVoList.add(oa11040UnallocateRoleTableVo);
        }

        Oa11040Vo vo = new Oa11040Vo();

        vo.setOperatorId(operatorId);
        vo.setJa(jaCode + " " + jaName);
        vo.setOperator(operatorCode + " " + operatorName);
        vo.setOa11040AllocateRoleTableVoList(oa11040AllocateRoleTableVoList);
        vo.setOa11040UnallocateRoleTableVoList(oa11040UnallocateRoleTableVoList);
        vo.setChangeCause(changeCause);
        vo.setChangeCausePlaceholder(changeCausePlaceholder);

        return vo;
    }

    /**
     * {@link Oa11040Controller#get(Long operatorId, Model model)}テスト
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
        Oa11040Controller oa11040Controller = createOa11040Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();

        // 期待値
        String expectedViewName = "oa11040"; // ToDo: 遷移制御
        changeCause = null;
        Oa11040Vo expectedVo = createOa11040Vo();

        // 実行
        String actualViewName = oa11040Controller.get(operatorId, model);
        Oa11040Vo actualVo = (Oa11040Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11040Controller#get(Long operatorId, Model model)}テスト
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
    void get_test1() {
        // テスト対象クラス生成
        Oa11040Controller oa11040Controller = createOa11040Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        operatorId = 11L;

        // 期待値
        String expectedViewName = "oa11040";
        String expectedMessageCode = "EOA13008";
        String expectedMessageArgs0 = "有効期限開始";

        // 実行
        String actualViewName = oa11040Controller.get(operatorId, model);
        Oa11040Vo actualVo = (Oa11040Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(expectedMessageArgs0);
    }

    /**
     * {@link Oa11040Controller#get(Long operatorId, Model model)}テスト
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
    void get_test2() {
        // テスト対象クラス生成
        Oa11040Controller oa11040Controller = createOa11040Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        operatorId = 12L;

        // 期待値
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10001";

        // 実行
        String actualViewName = oa11040Controller.get(operatorId, model);
        Oa11040Vo actualVo = (Oa11040Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }

    /**
     * {@link Oa11040Controller#apply(Model model, Oa11040Vo vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void apply_test() {
        // テスト対象クラス生成
        Oa11040Controller oa11040Controller = createOa11040Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa11040Vo vo = createOa11040Vo();

        // 期待値
        String expectedViewName = "oa11040"; // ToDo: 遷移制御
        Oa11040Vo expectedVo = createOa11040Vo();

        // 実行
        String actualViewName = oa11040Controller.apply(model, vo);
        Oa11040Vo actualVo = (Oa11040Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11040Controller#apply(Model model, Oa11040Vo vo)}テスト
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
    void apply_test1() {
        // テスト対象クラス生成
        Oa11040Controller oa11040Controller = createOa11040Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        operatorId = 21L;
        Oa11040Vo vo = createOa11040Vo();

        // 期待値
        String expectedViewName = "oa11040";
        String expectedMessageCode = "EOA13002";
        String expectedMessageArgs0 = "オペレーターID";

        // 実行
        String actualViewName = oa11040Controller.apply(model, vo);
        Oa11040Vo actualVo = (Oa11040Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(expectedMessageArgs0);
    }

    /**
     * {@link Oa11040Controller#apply(Model model, Oa11040Vo vo)}テスト
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
    void apply_test2() {
        // テスト対象クラス生成
        Oa11040Controller oa11040Controller = createOa11040Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        operatorId = 22L;
        Oa11040Vo vo = createOa11040Vo();

        // 期待値
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10001";

        // 実行
        String actualViewName = oa11040Controller.apply(model, vo);
        Oa11040Vo actualVo = (Oa11040Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }
}