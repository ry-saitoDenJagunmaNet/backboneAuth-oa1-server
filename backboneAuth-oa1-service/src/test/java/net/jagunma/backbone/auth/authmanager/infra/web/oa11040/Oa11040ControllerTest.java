package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.commandService.GrantSubSystemRole;
import net.jagunma.backbone.auth.authmanager.application.queryService.CopySubSystemRoleGranted;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchSubSystemRoleGranted;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAllRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantCommand.SubSystemRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyResponse;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040UnAssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

class Oa11040ControllerTest {

    // 実行既定値
    // サインインオペレーター項目系
    private Long signInOperatorId = 987654L;
    private Long selectedOperatorId = 876543L;

    // ターゲットオペレーター項目系
    private Long operatorId = 123456L;
    private String operatorCode = "yu123456";
    private String operatorName = "オペレーター名";

    // オペレーター_サブシステムロール割当履歴項目系
    private SubSystemRole subSystemRole0 = SubSystemRole.JA管理者;
    private SubSystemRole subSystemRole1 = SubSystemRole.業務統括者_購買;
    private SubSystemRole subSystemRole2 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole subSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole subSystemRole6 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate validThruStartDate0 = LocalDate.of(2020, 9, 10);
    private LocalDate validThruStartDate1 = LocalDate.of(2020, 9, 11);
    private LocalDate validThruStartDate2 = LocalDate.of(2020, 9, 12);
    private LocalDate validThruStartDate4 = LocalDate.of(2020, 9, 14);
    private LocalDate validThruStartDate6 = LocalDate.of(2020, 9, 16);
    private LocalDate validThruEndDate0 = LocalDate.of(2020, 9, 20);
    private LocalDate validThruEndDate1 = LocalDate.of(2020, 9, 21);
    private LocalDate validThruEndDate2 = LocalDate.of(2020, 9, 22);
    private LocalDate validThruEndDate4 = LocalDate.of(2020, 9, 24);
    private LocalDate validThruEndDate6 = LocalDate.of(2020, 9, 26);
    private SubSystemRole subSystemRole5 = SubSystemRole.業務統括者_販売_麦;
    private LocalDate validThruStartDate5 = LocalDate.of(2020, 12, 15);
    private LocalDate validThruEndDate5 = LocalDate.of(9999, 12, 25);

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
        Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole0.getCode(), validThruStartDate0, validThruEndDate0, null, operator, subSystemRole0),
        Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole1.getCode(), validThruStartDate1, validThruEndDate1, null, operator, subSystemRole1),
        Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole2.getCode(), validThruStartDate2, validThruEndDate2, null, operator, subSystemRole2),
        Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole4.getCode(), validThruStartDate4, validThruEndDate4, null, operator, subSystemRole4),
        Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole6.getCode(), validThruStartDate6, validThruEndDate6, null, operator, subSystemRole6));
    private Operator_SubSystemRoles operator_SubSystemRoles = Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);

    // オペレーター履歴ヘッダー系
    OperatorHistoryHeader operatorHistoryHeader = OperatorHistoryHeader.createFrom(null, operatorId, LocalDateTime.of(2020,10,1,0,1,2), changeCausePlaceholder, null, operator);

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
                //  request.getOperatorId().equals(21L) の場合：GunmaRuntimeException を発生させる
                if (request.getOperatorId().equals(21L)) {
                    Preconditions.checkNotNull(null, () -> new GunmaRuntimeException("EOA13002", "オペレーターID"));
                }
                //  request.getOperatorId().equals(22L) の場合：RuntimeException を発生させる
                if (request.getOperatorId().equals(22L)) {
                    throw new RuntimeException();
                }
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
        SearchSubSystemRoleGranted searchSubSystemRoleGranted = new SearchSubSystemRoleGranted(operator_SubSystemRoleRepository, operatorHistoryHeaderRepository) {
            @Override
            public void execute(SubSystemRoleGrantedSearchRequest request, SubSystemRoleGrantedSearchResponse response) {
                //  request.getTargetOperatorId().equals(11L) の場合：GunmaRuntimeException を発生させる
                if (request.getTargetOperatorId().equals(11L)) {
                    Preconditions.checkNotNull(null, () -> new GunmaRuntimeException("EOA13008", "有効期限開始"));
                }
                //  request.getTargetOperatorId().equals(12L) の場合：RuntimeException を発生させる
                if (request.getTargetOperatorId().equals(12L)) {
                    throw new RuntimeException();
                }
                response.setSignInOperatorId(signInOperatorId);
                response.setTargetOperatorId(operatorId);
                response.setAssignRoleDtoList(createSubSystemRoleGrantedAssignRoleDtoList());
                response.setAllRoleDtoList(createSubSystemRoleGrantedAllRoleDtoList());
                response.setOperatorHistoryHeader(operatorHistoryHeader);
            }
        };

        CopySubSystemRoleGranted copySubSystemRoleGranted = new CopySubSystemRoleGranted(operator_SubSystemRoleRepository) {
            @Override
            public void execute(SubSystemRoleGrantedCopyRequest request, SubSystemRoleGrantedCopyResponse response) {
                List<Operator_SubSystemRole> actualOperator_SubSystemRoleList = operator_SubSystemRoleList;
                actualOperator_SubSystemRoleList.add(Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole5.getCode(), validThruStartDate5, validThruEndDate5, null, operator, subSystemRole5));
                Operator_SubSystemRoles actualOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(actualOperator_SubSystemRoleList);

                List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = newArrayList();
                for (Operator_SubSystemRole operator_SubSystemRole : actualOperator_SubSystemRoles.getValues()) {
                    SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
                    assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
                    assignRoleDto.setIsModifiable(true);
                    assignRoleDtoList.add(assignRoleDto);
                }
                response.setAssignRoleDtoList(assignRoleDtoList);
            }
        };

        return new Oa11040Controller(grantSubSystemRole, searchSubSystemRoleGranted, copySubSystemRoleGranted);
    }

    // Oa11040Vo作成
    private Oa11040Vo createOa11040Vo() {
        Oa11040Vo vo = new Oa11040Vo();
        vo.setOperatorId(operatorId);
        vo.setJa(jaCode + " " + jaName);
        vo.setOperator(operatorCode + " " + operatorName);
        vo.setAssignRoleTableVoList(createOa11040AssignRoleTableVoList());
        vo.setUnAssignRoleTableVoList(createOa11040UnAssignRoleTableVoList());
        vo.setChangeCause(changeCause);
        vo.setChangeCausePlaceholder(changeCausePlaceholder);
        return vo;
    }

    private List<SubSystemRoleGrantedAssignRoleDto> createSubSystemRoleGrantedAssignRoleDtoList() {
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = newArrayList();
        createOa11040AssignRole(assignRoleDtoList, newArrayList());
        return assignRoleDtoList;
    }
    private List<Oa11040AssignRoleTableVo> createOa11040AssignRoleTableVoList() {
        List<Oa11040AssignRoleTableVo> assignRoleTableVoList = newArrayList();
        createOa11040AssignRole(newArrayList(), assignRoleTableVoList);
        return assignRoleTableVoList;
    }
    private void createOa11040AssignRole(List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList, List<Oa11040AssignRoleTableVo> assignRoleTableVoList) {
        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(true);
            assignRoleDtoList.add(assignRoleDto);

            Oa11040AssignRoleTableVo assignRoleTableVo = new Oa11040AssignRoleTableVo();
            assignRoleTableVo.setRoleCode(operator_SubSystemRole.getSubSystemRoleCode());
            assignRoleTableVo.setRoleName(operator_SubSystemRole.getSubSystemRole().getDisplayName());
            assignRoleTableVo.setValidThruStartDate(operator_SubSystemRole.getValidThruStartDate());
            assignRoleTableVo.setValidThruEndDate(operator_SubSystemRole.getValidThruEndDate());
            assignRoleTableVo.setIsModifiable(true);
            assignRoleTableVoList.add(assignRoleTableVo);
        }
    }

    private List<SubSystemRoleGrantedAllRoleDto> createSubSystemRoleGrantedAllRoleDtoList() {
        List<SubSystemRoleGrantedAllRoleDto> allRoleDtoList = newArrayList();
        createAllRole(allRoleDtoList, newArrayList());
        return allRoleDtoList;
    }
    private List<Oa11040UnAssignRoleTableVo> createOa11040UnAssignRoleTableVoList() {
        List<Oa11040UnAssignRoleTableVo> unAssignRoleTableVoList = newArrayList();
        createAllRole(newArrayList(), unAssignRoleTableVoList);
        return unAssignRoleTableVoList;
    }
    private void createAllRole(List<SubSystemRoleGrantedAllRoleDto> allRoleDtoList, List<Oa11040UnAssignRoleTableVo> unAssignRoleTableVoList) {
        for (SubSystemRole subSystemRole : SubSystemRole.values()) {//ToDo:ソートオーダー回し実装（ユーティリティで実現）
            if (subSystemRole.getCode().length() == 0) { continue; }
            SubSystemRoleGrantedAllRoleDto allRoleDto = new SubSystemRoleGrantedAllRoleDto();
            allRoleDto.setSubSystemRole(subSystemRole);
            allRoleDto.setIsModifiable(true);
            allRoleDtoList.add(allRoleDto);

            Oa11040UnAssignRoleTableVo unAssignRoleTableVo = new Oa11040UnAssignRoleTableVo();
            unAssignRoleTableVo.setRoleCode(subSystemRole.getCode());
            unAssignRoleTableVo.setRoleName(subSystemRole.getDisplayName());
            unAssignRoleTableVo.setIsModifiable(true);
            unAssignRoleTableVoList.add(unAssignRoleTableVo);
        }
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
     * {@link Oa11040Controller#copy(Model model, RedirectAttributes redirectAttribute, Oa11040Vo vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copy_test() {
//todo:★なにをどうやって検証するか？

        // テスト対象クラス生成
        Oa11040Controller oa11040Controller = createOa11040Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa11040Vo vo = createOa11040Vo();

        // 期待値
        String expectedViewName = "oa11040"; // ToDo: 遷移制御
        changeCause = null;
        Oa11040Vo expectedVo = createOa11040Vo();

        // 実行
//        String actualViewName = oa11040Controller.copy(model, vo);
        Oa11040Vo actualVo = (Oa11040Vo) model.getAttribute("form");

        // 結果検証
//        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11040Controller#copyResponse(@ RequestParam (value = "operatorId", required = false) Long selectedOperatorId, Model model)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyResponse_test() {
//todo:★セッションの格納方法が変わった？
//todo:★OA11020も変更の必要性（setSessionVo）？

        // テスト対象クラス生成
        Oa11040Controller oa11040Controller = createOa11040Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();

        // 期待値
        String expectedViewName = "oa11040"; // ToDo: 遷移制御
        changeCause = null;
        Oa11040Vo expectedVo = createOa11040Vo();
        List<Operator_SubSystemRole> expectedOperator_SubSystemRoleList = operator_SubSystemRoleList;
        expectedOperator_SubSystemRoleList.add(Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole5.getCode(), validThruStartDate5, validThruEndDate5, null, operator, subSystemRole5));
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(expectedOperator_SubSystemRoleList);
        List<Oa11040AssignRoleTableVo> assignRoleTableVoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : expectedOperator_SubSystemRoles.getValues()) {
            Oa11040AssignRoleTableVo assignRoleTableVo = new Oa11040AssignRoleTableVo();
            assignRoleTableVo.setRoleCode(operator_SubSystemRole.getSubSystemRoleCode());
            assignRoleTableVo.setRoleName(operator_SubSystemRole.getSubSystemRole().getDisplayName());
            assignRoleTableVo.setValidThruStartDate(operator_SubSystemRole.getValidThruStartDate());
            assignRoleTableVo.setValidThruEndDate(operator_SubSystemRole.getValidThruEndDate());
            assignRoleTableVo.setIsModifiable(true);
            assignRoleTableVoList.add(assignRoleTableVo);
        }
        expectedVo.setAssignRoleTableVoList(assignRoleTableVoList);

        // 実行
        String actualViewName = oa11040Controller.copyResponse(selectedOperatorId, model);
        Oa11040Vo actualVo = (Oa11040Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
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