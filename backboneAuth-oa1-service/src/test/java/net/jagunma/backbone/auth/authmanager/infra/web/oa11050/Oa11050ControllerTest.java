package net.jagunma.backbone.auth.authmanager.infra.web.oa11050;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import net.jagunma.backbone.auth.authmanager.application.commandService.GrantBizTranRole;
import net.jagunma.backbone.auth.authmanager.application.queryService.CopyBizTranRoleGranted;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBizTranRoleGranted;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAllRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantCommand.BizTranRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedCopyRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedCopyResponse;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.Oa11010Controller;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050UnAssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaders;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

class Oa11050ControllerTest {

    // 実行既定値
    // サインインオペレーター項目系
    private Long signInOperatorId = 987654L;
    // 選択オペレーター項目系
    private Long selectedOperatorId = 876543L;

    // ターゲットオペレーター項目系
    private Long operatorId = 123456L;
    private String operatorCode = "yu123456";
    private String operatorName = "オペレーター名";

    // オペレーター_取引ロール割当項目系
    private String bizTranRoleCode0 = "KBAG01";
    private String bizTranRoleCode1 = "YSAG10";
    private String bizTranRoleCode2 = "HKAG10";
    private String bizTranRoleCode3 = "ANAG01";
    private String bizTranRoleName0 = "（購買）購買業務基本";
    private String bizTranRoleName1 = "（青果）管理者";
    private String bizTranRoleName2 = "（米）ＪＡ取引全般";
    private String bizTranRoleName3 = "（畜産）取引全般";
    private SubSystem subSystem0 = SubSystem.購買;
    private SubSystem subSystem1 = SubSystem.販売_青果;
    private SubSystem subSystem2 = SubSystem.販売_米;
    private SubSystem subSystem3 = SubSystem.販売_畜産;
    private LocalDate validThruStartDate0 = LocalDate.of(2020, 1, 1);
    private LocalDate validThruStartDate1 = LocalDate.of(2020, 1, 2);
    private LocalDate validThruStartDate2 = LocalDate.of(2020, 1, 3);
    private LocalDate validThruStartDate3 = LocalDate.of(2020, 1, 4);
    private LocalDate validThruEndDate0 = LocalDate.of(9999, 1, 21);
    private LocalDate validThruEndDate1 = LocalDate.of(9999, 1, 22);
    private LocalDate validThruEndDate2 = LocalDate.of(9999, 1, 23);
    private LocalDate validThruEndDate3 = LocalDate.of(9999, 1, 24);
    private String bizTranRoleCode4 = "KBAG20";
    private String bizTranRoleName4 = "（購買）購買業務基本３";
    private SubSystem subSystem4 = SubSystem.購買;
    private LocalDate validThruStartDate4 = LocalDate.of(2020, 1, 5);
    private LocalDate validThruEndDate4 = LocalDate.of(9999, 1, 25);

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

    // オペレーター_取引ロール割当系
    private List<BizTranRole> bizTranRoleList = newArrayList(
        BizTranRole.createFrom(401L, bizTranRoleCode0, bizTranRoleName0, subSystem0.getCode(), null, subSystem0),
        BizTranRole.createFrom(402L, bizTranRoleCode1, bizTranRoleName1, subSystem1.getCode(), null, subSystem1),
        BizTranRole.createFrom(403L, bizTranRoleCode2, bizTranRoleName2, subSystem2.getCode(), null, subSystem2),
        BizTranRole.createFrom(404L, bizTranRoleCode3, bizTranRoleName3, subSystem3.getCode(), null, subSystem3));
    private List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList(
        Operator_BizTranRole.createFrom(501L, operatorId, bizTranRoleList.get(0).getBizTranRoleId(), validThruStartDate0, validThruEndDate0, null, operator, bizTranRoleList.get(0)),
        Operator_BizTranRole.createFrom(502L, operatorId, bizTranRoleList.get(1).getBizTranRoleId(), validThruStartDate1, validThruEndDate1, null, operator, bizTranRoleList.get(1)),
        Operator_BizTranRole.createFrom(503L, operatorId, bizTranRoleList.get(2).getBizTranRoleId(), validThruStartDate2, validThruEndDate2, null, operator, bizTranRoleList.get(2)),
        Operator_BizTranRole.createFrom(504L, operatorId, bizTranRoleList.get(3).getBizTranRoleId(), validThruStartDate3, validThruEndDate3, null, operator, bizTranRoleList.get(3)));
    private Operator_BizTranRoles operator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);

    // オペレーター履歴ヘッダー系
    private OperatorHistoryHeader operatorHistoryHeader = OperatorHistoryHeader.createFrom(null, operatorId, LocalDateTime.of(2020,10,1,0,1,2), changeCausePlaceholder, null, operator);

    // 取引ロール系
    private List<BizTranRole> allBizTranRoleList = newArrayList(
        BizTranRole.createFrom(1L, "KBAG01", "（購買）購買業務基本", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(2L, "KBAG02", "（購買）本所業務", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(3L, "KBAG03", "（購買）本所管理業務", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(4L, "KBAG04", "（購買）バラエサ処理", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(5L, "KBAG05", "（購買）在庫管理", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(6L, "KBAG06", "（購買）支払業務", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(7L, "KBAG07", "（購買）受入業務", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(8L, "KBAG08", "（購買）受注管理", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(9L, "KBAG09", "（購買）未収金管理", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(10L, "KBAG10", "（購買）損害金利息管理", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(11L, "KBAG11", "（購買）配送管理", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(12L, "KBAG12", "（購買）LPG担当者", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(13L, "KBAG13", "（購買）LPG入金", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(14L, "KBAG14", "（購買）レジ担当者", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(15L, "KBAG15", "（購買）支所マスタ担当者", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(16L, "KBAG16", "（購買）食材担当者", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(17L, "KBAG17", "（購買）購買業務基本２", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(18L, "KBAG18", "（購買）窓口供給（店舗用２）", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(19L, "KBAG19", "（購買）合併", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(20L, "KBAG20", "（購買）購買業務基本３", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(21L, "KBAG50", "（購買）マスタ移行", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(22L, "KBAG99", "（購買）電算センター", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(23L, "YSAG10", "（青果）管理者", SubSystem.販売_青果.getCode(), null, SubSystem.販売_青果),
        BizTranRole.createFrom(24L, "YSAG20", "（青果）精算担当", SubSystem.販売_青果.getCode(), null, SubSystem.販売_青果),
        BizTranRole.createFrom(25L, "YSAG30", "（青果）集荷場担当", SubSystem.販売_青果.getCode(), null, SubSystem.販売_青果),
        BizTranRole.createFrom(26L, "YSAG40", "（青果）単価計算担当", SubSystem.販売_青果.getCode(), null, SubSystem.販売_青果),
        BizTranRole.createFrom(27L, "YSAG50", "（青果）入力担当", SubSystem.販売_青果.getCode(), null, SubSystem.販売_青果),
        BizTranRole.createFrom(28L, "YSAG60", "（青果）安定基金管理者", SubSystem.販売_青果.getCode(), null, SubSystem.販売_青果),
        BizTranRole.createFrom(29L, "YSAG70", "（青果）支所担当", SubSystem.販売_青果.getCode(), null, SubSystem.販売_青果),
        BizTranRole.createFrom(30L, "YSAG90", "（青果）電算センター維持管理担当者", SubSystem.販売_青果.getCode(), null, SubSystem.販売_青果),
        BizTranRole.createFrom(31L, "YFAG10", "（花卉）管理者", SubSystem.販売_花卉.getCode(), null, SubSystem.販売_花卉),
        BizTranRole.createFrom(32L, "YFAG10", "（花卉）管理者", SubSystem.販売_花卉.getCode(), null, SubSystem.販売_花卉),
        BizTranRole.createFrom(33L, "YFAG20", "（花卉）精算担当", SubSystem.販売_花卉.getCode(), null, SubSystem.販売_花卉),
        BizTranRole.createFrom(34L, "YFAG30", "（花卉）集荷場担当", SubSystem.販売_花卉.getCode(), null, SubSystem.販売_花卉),
        BizTranRole.createFrom(35L, "YFAG40", "（花卉）単価計算担当", SubSystem.販売_花卉.getCode(), null, SubSystem.販売_花卉),
        BizTranRole.createFrom(36L, "YFAG50", "（花卉）入力担当", SubSystem.販売_花卉.getCode(), null, SubSystem.販売_花卉),
        BizTranRole.createFrom(37L, "YFAG70", "（花卉）支所担当", SubSystem.販売_花卉.getCode(), null, SubSystem.販売_花卉),
        BizTranRole.createFrom(38L, "YFAG90", "（花卉）電算センター維持管理担当者", SubSystem.販売_花卉.getCode(), null, SubSystem.販売_花卉),
        BizTranRole.createFrom(39L, "HKAG10", "（米）ＪＡ取引全般", SubSystem.販売_米.getCode(), null, SubSystem.販売_米),
        BizTranRole.createFrom(40L, "HKAG10", "（米）ＪＡ取引全般", SubSystem.販売_米.getCode(), null, SubSystem.販売_米),
        BizTranRole.createFrom(41L, "HKAG15", "（米）ＪＡ取引振込以外全般", SubSystem.販売_米.getCode(), null, SubSystem.販売_米),
        BizTranRole.createFrom(42L, "HKAG99", "（米）センター維持管理担当者", SubSystem.販売_米.getCode(), null, SubSystem.販売_米),
        BizTranRole.createFrom(43L, "HMAG20", "（麦）ＪＡ取引全般", SubSystem.販売_麦.getCode(), null, SubSystem.販売_麦),
        BizTranRole.createFrom(44L, "HMAG20", "（麦）ＪＡ取引全般", SubSystem.販売_麦.getCode(), null, SubSystem.販売_麦),
        BizTranRole.createFrom(45L, "HMAG25", "（麦）ＪＡ取引振込以外全般", SubSystem.販売_麦.getCode(), null, SubSystem.販売_麦),
        BizTranRole.createFrom(46L, "HMAG99", "（麦）センター維持管理担当者", SubSystem.販売_麦.getCode(), null, SubSystem.販売_麦),
        BizTranRole.createFrom(47L, "ANAG01", "（畜産）取引全般", SubSystem.販売_畜産.getCode(), null, SubSystem.販売_畜産),
        BizTranRole.createFrom(48L, "ANAG01", "（畜産）取引全般", SubSystem.販売_畜産.getCode(), null, SubSystem.販売_畜産),
        BizTranRole.createFrom(49L, "ANAG02", "（畜産）維持管理担当者", SubSystem.販売_畜産.getCode(), null, SubSystem.販売_畜産),
        BizTranRole.createFrom(50L, "ANAG98", "（畜産）センター維持管理担当者", SubSystem.販売_畜産.getCode(), null, SubSystem.販売_畜産),
        BizTranRole.createFrom(51L, "ANAG99", "（畜産）維持管理責任者", SubSystem.販売_畜産.getCode(), null, SubSystem.販売_畜産));
    private BizTranRoles allBizTranRoles = BizTranRoles.createFrom(allBizTranRoleList);

    // 検証値
    String actualAttributeName;
    String actualAttributeValue;

    // テスト対象クラス生成
    private Oa11050Controller createOa11050Controller() {
        Operator_BizTranRoleRepositoryForStore operator_BizTranRoleRepositoryForStore = new Operator_BizTranRoleRepositoryForStore() {
            @Override
            public void store(Operator_BizTranRoles operator_BizTranRoles, String changeCause) {
            }
        };
        GrantBizTranRole grantBizTranRole = new GrantBizTranRole(operator_BizTranRoleRepositoryForStore) {
            @Override
            public void execute(BizTranRoleGrantRequest request) {
                //  request.getOperatorId().equals(11L) の場合：GunmaRuntimeException を発生させる
                if (request.getOperatorId().equals(11L)) {
                    throw new GunmaRuntimeException("EOA13002", "オペレーターID");
                }
                //  request.getOperatorId().equals(12L) の場合：RuntimeException を発生させる
                if (request.getOperatorId().equals(12L)) {
                    throw new RuntimeException();
                }
            }
        };

        Operator_BizTranRoleRepository operator_BizTranRoleRepository = new Operator_BizTranRoleRepository() {
            @Override
            public Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {
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
        BizTranRoleRepository bizTranRoleRepository = new BizTranRoleRepository() {
            @Override
            public BizTranRoles selectBy(BizTranRoleCriteria bizTranRoleCriteria, Orders orders) {
                return null;
            }
            @Override
            public BizTranRoles selectAll(Orders orders) {
                return null;
            }
        };
        SearchBizTranRoleGranted searchBizTranRoleGranted = new SearchBizTranRoleGranted(operator_BizTranRoleRepository, operator_SubSystemRoleRepository, operatorHistoryHeaderRepository, bizTranRoleRepository) {
            @Override
            public void execute(BizTranRoleGrantedSearchRequest request, BizTranRoleGrantedSearchResponse response) {
                //  request.getTargetOperatorId().equals(11L) の場合：GunmaRuntimeException を発生させる
                if (request.getTargetOperatorId().equals(11L)) {
                    throw new GunmaRuntimeException("EOA13002", "ターゲットオペレーターID");
                }
                //  request.getTargetOperatorId().equals(12L) の場合：RuntimeException を発生させる
                if (request.getTargetOperatorId().equals(12L)) {
                    throw new RuntimeException();
                }
                response.setSignInOperatorId(signInOperatorId);
                response.setTargetOperatorId(operatorId);
                response.setAssignRoleDtoList(createBizTranRoleGrantedAssignRoleDtoList());
                response.setAllRoleDtoList(createBizTranRoleGrantedAllRoleDtoList());
                response.setOperatorHistoryHeader(operatorHistoryHeader);
            }
        };

        CopyBizTranRoleGranted copyBizTranRoleGranted = new CopyBizTranRoleGranted(operator_BizTranRoleRepository, operator_SubSystemRoleRepository) {
            @Override
            public void execute(BizTranRoleGrantedCopyRequest request, BizTranRoleGrantedCopyResponse response) {
                //  request.getSelectedOperatorId().equals(11L) の場合：GunmaRuntimeException を発生させる
                if (request.getSelectedOperatorId().equals(11L)) {
                    throw new GunmaRuntimeException("EOA13002", "選択オペレーターID");
                }
                //  request.getSelectedOperatorId().equals(12L) の場合：RuntimeException を発生させる
                if (request.getSelectedOperatorId().equals(12L)) {
                    throw new RuntimeException();
                }
                response.setAssignRoleDtoList(createBizTranRoleGrantedAssignRoleDtoCopiedExpectedList());
            }
        };

        return new Oa11050Controller(grantBizTranRole, searchBizTranRoleGranted, copyBizTranRoleGranted);
    }

    // Oa11050Vo作成
    private Oa11050Vo createOa11050Vo() {
        Oa11050Vo vo = new Oa11050Vo();
        vo.setOperatorId(operatorId);
        vo.setJa(jaCode + " " + jaName);
        vo.setOperator(operatorCode + " " + operatorName);
        vo.setAssignRoleTableVoList(createOa11050AssignRoleTableVoList());
        vo.setUnAssignRoleTableVoList(createOa11050UnAssignRoleTableVoList());
        vo.setChangeCause(changeCause);
        vo.setChangeCausePlaceholder(changeCausePlaceholder);
        vo.setSubSystemItemsSource(SelectOptionItemsSource.createFrom(SubSystem.getValidValues()).getValue());
        return vo;
    }

    private List<BizTranRoleGrantedAssignRoleDto> createBizTranRoleGrantedAssignRoleDtoList() {
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = newArrayList();
        createOa11050AssignRole(assignRoleDtoList, newArrayList(), operator_BizTranRoles);
        return assignRoleDtoList;
    }
    private List<Oa11050AssignRoleTableVo> createOa11050AssignRoleTableVoList() {
        List<Oa11050AssignRoleTableVo> assignRoleTableVoList = newArrayList();
        createOa11050AssignRole(newArrayList(), assignRoleTableVoList, operator_BizTranRoles);
        return assignRoleTableVoList;
    }
    private List<BizTranRoleGrantedAssignRoleDto> createBizTranRoleGrantedAssignRoleDtoCopiedExpectedList() {
        List<BizTranRole> expectedBizTranRoleList = newArrayList(bizTranRoleList,
            BizTranRole.createFrom(405L, bizTranRoleCode4, bizTranRoleName4, subSystem4.getCode(), null, subSystem4));
        List<Operator_BizTranRole> expectedOperator_BizTranRoleList = newArrayList(operator_BizTranRoleList,
            Operator_BizTranRole.createFrom(505L, operatorId, expectedBizTranRoleList.get(4).getBizTranRoleId(), validThruStartDate4, validThruEndDate4, null, operator, expectedBizTranRoleList.get(4)));
        Operator_BizTranRoles expectedOperator_BizTranRoles = Operator_BizTranRoles.createFrom(expectedOperator_BizTranRoleList);

        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = newArrayList();
        createOa11050AssignRole(assignRoleDtoList, newArrayList(), expectedOperator_BizTranRoles);
        return assignRoleDtoList;
    }
    private List<Oa11050AssignRoleTableVo> createOa11050AssignRoleTableVoCopiedExpectedList() {
        List<BizTranRole> expectedBizTranRoleList = newArrayList(bizTranRoleList,
            BizTranRole.createFrom(405L, bizTranRoleCode4, bizTranRoleName4, subSystem4.getCode(), null, subSystem4));
        List<Operator_BizTranRole> expectedOperator_BizTranRoleList = newArrayList(operator_BizTranRoleList,
            Operator_BizTranRole.createFrom(505L, operatorId, expectedBizTranRoleList.get(4).getBizTranRoleId(), validThruStartDate4, validThruEndDate4, null, operator, expectedBizTranRoleList.get(4)));
        Operator_BizTranRoles expectedOperator_BizTranRoles = Operator_BizTranRoles.createFrom(expectedOperator_BizTranRoleList);

        List<Oa11050AssignRoleTableVo> assignRoleTableVoList = newArrayList();
        createOa11050AssignRole(newArrayList(), assignRoleTableVoList, expectedOperator_BizTranRoles);
        return assignRoleTableVoList;
    }
    private void createOa11050AssignRole(List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList, List<Oa11050AssignRoleTableVo> assignRoleTableVoList, Operator_BizTranRoles operator_BizTranRoles) {
        for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoles.getValues()) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
            assignRoleDto.setIsModifiable(true);
            assignRoleDtoList.add(assignRoleDto);

            Oa11050AssignRoleTableVo assignRoleTableVo = new Oa11050AssignRoleTableVo();
            assignRoleTableVo.setRoleId(operator_BizTranRole.getBizTranRoleId());
            assignRoleTableVo.setRoleCode(operator_BizTranRole.getBizTranRole().getBizTranRoleCode());
            assignRoleTableVo.setRoleName(operator_BizTranRole.getBizTranRole().getBizTranRoleName());
            assignRoleTableVo.setValidThruStartDate(operator_BizTranRole.getValidThruStartDate());
            assignRoleTableVo.setValidThruEndDate(operator_BizTranRole.getValidThruEndDate());
            assignRoleTableVo.setIsModifiable(true);
            assignRoleTableVoList.add(assignRoleTableVo);
        }
    }

    private List<BizTranRoleGrantedAllRoleDto> createBizTranRoleGrantedAllRoleDtoList() {
        List<BizTranRoleGrantedAllRoleDto> allRoleDtoList = newArrayList();
        createAllRole(allRoleDtoList, newArrayList());
        return allRoleDtoList;
    }
    private List<Oa11050UnAssignRoleTableVo> createOa11050UnAssignRoleTableVoList() {
        List<Oa11050UnAssignRoleTableVo> unAssignRoleTableVoList = newArrayList();
        createAllRole(newArrayList(), unAssignRoleTableVoList);
        return unAssignRoleTableVoList;
    }
    private void createAllRole(List<BizTranRoleGrantedAllRoleDto> allRoleDtoList, List<Oa11050UnAssignRoleTableVo> unAssignRoleTableVoList) {
        for (BizTranRole bizTranRole : allBizTranRoles.getValues()) {
            BizTranRoleGrantedAllRoleDto allRoleDto = new BizTranRoleGrantedAllRoleDto();
            allRoleDto.setBizTranRole(bizTranRole);
            allRoleDto.setIsModifiable(true);
            allRoleDtoList.add(allRoleDto);

            Oa11050UnAssignRoleTableVo unAssignRoleTableVo = new Oa11050UnAssignRoleTableVo();
            unAssignRoleTableVo.setRoleId(bizTranRole.getBizTranRoleId());
            unAssignRoleTableVo.setRoleCode(bizTranRole.getBizTranRoleCode());
            unAssignRoleTableVo.setRoleName(bizTranRole.getBizTranRoleName());
            unAssignRoleTableVo.setSubSystemCode(bizTranRole.getSubSystem().getCode());
            unAssignRoleTableVo.setIsModifiable(true);
            unAssignRoleTableVoList.add(unAssignRoleTableVo);
        }
    }

    // RedirectAttributes生成
    private RedirectAttributes createRedirectAttributes() {
        return new RedirectAttributes() {
            @Override
            public RedirectAttributes addAttribute(String attributeName, Object attributeValue) {
                //  signInOperatorId.equals(11L) の場合：GunmaRuntimeException を発生させる
                if (signInOperatorId.equals(11L)) {
                    throw new GunmaRuntimeException("EOA13002", "サインインオペレーターID");
                }
                //  signInOperatorId.equals(12L) の場合：RuntimeException を発生させる
                if (signInOperatorId.equals(12L)) {
                    throw new RuntimeException();
                }
                actualAttributeName = attributeName;
                actualAttributeValue = (String) attributeValue;
                return null;
            }
            @Override
            public RedirectAttributes addAttribute(Object attributeValue) {
                return null;
            }
            @Override
            public RedirectAttributes addAllAttributes(Collection<?> attributeValues) {
                return null;
            }
            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }
            @Override
            public RedirectAttributes mergeAttributes(Map<String, ?> attributes) {
                return null;
            }
            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }
            @Override
            public Object getAttribute(String attributeName) {
                return null;
            }
            @Override
            public Map<String, Object> asMap() {
                return null;
            }
            @Override
            public RedirectAttributes addFlashAttribute(String attributeName, Object attributeValue) {
                return null;
            }
            @Override
            public RedirectAttributes addFlashAttribute(Object attributeValue) {
                return null;
            }
            @Override
            public Map<String, ?> getFlashAttributes() {
                return null;
            }
        };
    }

    /**
     * {@link Oa11050Controller#get(Long operatorId, Model model)}テスト
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
        Oa11050Controller oa11050Controller = createOa11050Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();

        // 期待値
        String expectedViewName = "oa11050"; // ToDo: 遷移制御
        changeCause = null;
        Oa11050Vo expectedVo = createOa11050Vo();

        // 実行
        String actualViewName = oa11050Controller.get(operatorId, model);
        Oa11050Vo actualVo = (Oa11050Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11050Controller#get(Long operatorId, Model model)}テスト
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
        Oa11050Controller oa11050Controller = createOa11050Controller();

        // 実行値
        operatorId = 11L;
        ConcurrentModel model = new ConcurrentModel();

        // 期待値
        String expectedViewName = "oa11050";
        String expectedMessageCode = "EOA13002";
        String expectedMessageArgs0 = "ターゲットオペレーターID";

        // 実行
        String actualViewName = oa11050Controller.get(operatorId, model);
        Oa11050Vo actualVo = (Oa11050Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(expectedMessageArgs0);
    }

    /**
     * {@link Oa11050Controller#get(Long operatorId, Model model)}テスト
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
        Oa11050Controller oa11050Controller = createOa11050Controller();

        // 実行値
        operatorId = 12L;
        ConcurrentModel model = new ConcurrentModel();

        // 期待値
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10001";

        // 実行
        String actualViewName = oa11050Controller.get(operatorId, model);
        Oa11050Vo actualVo = (Oa11050Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }

    /**
     * {@link Oa11050Controller#copy(Model model, RedirectAttributes redirectAttribute, Oa11050Vo vo)}テスト
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
        // テスト対象クラス生成
        Oa11050Controller oa11050Controller = createOa11050Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa11050Vo vo = createOa11050Vo();
        // Sessionに格納
        oa11050Controller.setHttpSession(new MockHttpSession());
        oa11050Controller.setSessionAttribute("session_Oa11050Vo", vo);
        RedirectAttributes redirectAttribute = createRedirectAttributes();

        // 期待値
        String expectedAttributeName = Oa11010Controller.RedirectAttribute;
        String expectedAAttributeValue = "oa11050/copyResponse";
        Oa11050Vo expectedVo = createOa11050Vo();

        // 実行
        String actualViewName = oa11050Controller.copy(model, redirectAttribute, vo);
        Oa11050Vo actualSessionVo = (Oa11050Vo) oa11050Controller.getSessionAttribute("session_Oa11050Vo");

        // 結果検証
        assertThat(actualAttributeName).isEqualTo(expectedAttributeName);
        assertThat(actualAttributeValue).isEqualTo(expectedAAttributeValue);
        assertThat(actualSessionVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11050Controller#copy(Model model, RedirectAttributes redirectAttribute, Oa11050Vo vo)}テスト
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
    void copy_test1() {
        // テスト対象クラス生成
        Oa11050Controller oa11050Controller = createOa11050Controller();

        // 実行値
        signInOperatorId = 11L;
        ConcurrentModel model = new ConcurrentModel();
        Oa11050Vo vo = createOa11050Vo();
        // Sessionに格納
        oa11050Controller.setHttpSession(new MockHttpSession());
        oa11050Controller.setSessionAttribute("session_Oa11050Vo", vo);
        RedirectAttributes redirectAttribute = createRedirectAttributes();

        // 期待値
        String expectedViewName = "oa11050";
        String expectedMessageCode = "EOA13002";
        String expectedMessageArgs0 = "サインインオペレーターID";

        // 実行
        String actualViewName = oa11050Controller.copy(model, redirectAttribute, vo);

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(vo.getMessageCode()).isEqualTo(expectedMessageCode);
        assertThat(vo.getMessageArgs().get(0)).isEqualTo(expectedMessageArgs0);
    }

    /**
     * {@link Oa11050Controller#copy(Model model, RedirectAttributes redirectAttribute, Oa11050Vo vo)}テスト
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
    void copy_test2() {
        // テスト対象クラス生成
        Oa11050Controller oa11050Controller = createOa11050Controller();

        // 実行値
        signInOperatorId = 12L;
        ConcurrentModel model = new ConcurrentModel();
        Oa11050Vo vo = createOa11050Vo();
        // Sessionに格納
        oa11050Controller.setHttpSession(new MockHttpSession());
        oa11050Controller.setSessionAttribute("session_Oa11050Vo", vo);
        RedirectAttributes redirectAttribute = createRedirectAttributes();

        // 期待値
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10001";

        // 実行
        String actualViewName = oa11050Controller.copy(model, redirectAttribute, vo);

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(vo.getMessageCode()).isEqualTo(expectedMessageCode);
    }

    /**
     * {@link Oa11050Controller#copyResponse(Long selectedOperatorId, Model model)}テスト
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
        // テスト対象クラス生成
        Oa11050Controller oa11050Controller = createOa11050Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa11050Vo vo = createOa11050Vo();
        // Sessionに格納
        oa11050Controller.setHttpSession(new MockHttpSession());
        oa11050Controller.setSessionAttribute("session_Oa11050Vo", vo);

        // 期待値
        String expectedViewName = "oa11050"; // ToDo: 遷移制御
        Oa11050Vo expectedVo = createOa11050Vo();
        expectedVo.setAssignRoleTableVoList(createOa11050AssignRoleTableVoCopiedExpectedList());

        // 実行
        String actualViewName = oa11050Controller.copyResponse(selectedOperatorId, model);
        Oa11050Vo actualVo = (Oa11050Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11050Controller#copyResponse(Long selectedOperatorId, Model model)}テスト
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
    void copyResponse_test1() {
        // テスト対象クラス生成
        Oa11050Controller oa11050Controller = createOa11050Controller();

        // 実行値
        selectedOperatorId = 11L;
        ConcurrentModel model = new ConcurrentModel();
        Oa11050Vo vo = createOa11050Vo();
        // Sessionに格納
        oa11050Controller.setHttpSession(new MockHttpSession());
        oa11050Controller.setSessionAttribute("session_Oa11050Vo", vo);

        // 期待値
        String expectedViewName = "oa11050";
        String expectedMessageCode = "EOA13002";
        String expectedMessageArgs0 = "選択オペレーターID";

        // 実行
        String actualViewName = oa11050Controller.copyResponse(selectedOperatorId, model);

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(vo.getMessageCode()).isEqualTo(expectedMessageCode);
        assertThat(vo.getMessageArgs().get(0)).isEqualTo(expectedMessageArgs0);
    }

    /**
     * {@link Oa11050Controller#copyResponse(Long selectedOperatorId, Model model)}テスト
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
    void copyResponse_test2() {
        // テスト対象クラス生成
        Oa11050Controller oa11050Controller = createOa11050Controller();

        // 実行値
        selectedOperatorId = 12L;
        ConcurrentModel model = new ConcurrentModel();
        Oa11050Vo vo = createOa11050Vo();
        // Sessionに格納
        oa11050Controller.setHttpSession(new MockHttpSession());
        oa11050Controller.setSessionAttribute("session_Oa11050Vo", vo);

        // 期待値
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10001";

        // 実行
        String actualViewName = oa11050Controller.copyResponse(selectedOperatorId, model);

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(vo.getMessageCode()).isEqualTo(expectedMessageCode);
    }

    /**
     * {@link Oa11050Controller#apply(Model model, Oa11050Vo vo)}テスト
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
        Oa11050Controller oa11050Controller = createOa11050Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        Oa11050Vo vo = createOa11050Vo();

        // 期待値
        String expectedViewName = "oa11050"; // ToDo: 遷移制御
        Oa11050Vo expectedVo = createOa11050Vo();

        // 実行
        String actualViewName = oa11050Controller.apply(model, vo);
        Oa11050Vo actualVo = (Oa11050Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11050Controller#apply(Model model, Oa11050Vo vo)}テスト
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
        Oa11050Controller oa11050Controller = createOa11050Controller();

        // 実行値
        operatorId = 11L;
        ConcurrentModel model = new ConcurrentModel();
        Oa11050Vo vo = createOa11050Vo();

        // 期待値
        String expectedViewName = "oa11050";
        String expectedMessageCode = "EOA13002";
        String expectedMessageArgs0 = "オペレーターID";

        // 実行
        String actualViewName = oa11050Controller.apply(model, vo);
        Oa11050Vo actualVo = (Oa11050Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(expectedMessageArgs0);
    }

    /**
     * {@link Oa11050Controller#apply(Model model, Oa11050Vo vo)}テスト
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
        Oa11050Controller oa11050Controller = createOa11050Controller();

        // 実行値
        operatorId = 12L;
        ConcurrentModel model = new ConcurrentModel();
        Oa11050Vo vo = createOa11050Vo();

        // 期待値
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10001";

        // 実行
        String actualViewName = oa11050Controller.apply(model, vo);
        Oa11050Vo actualVo = (Oa11050Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }
}