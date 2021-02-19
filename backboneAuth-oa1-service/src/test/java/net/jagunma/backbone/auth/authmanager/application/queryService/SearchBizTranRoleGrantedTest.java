package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAllRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.util.BizTranRoleGrantedQueryUtil;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaders;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchBizTranRoleGrantedTest {

    // 実行既定値
    private final BizTranRoleGrantedQueryUtil bizTranRoleGrantedQueryUtil = new BizTranRoleGrantedQueryUtil();
    private Long signInOperatorId = 987654L;
    private Long targetOperatorId = 123456L;

    // オペレーター_サブシステムロール割当系（サインインオペレーター）
    private SubSystemRole signInSubSystemRole0 = SubSystemRole.JA管理者;
    private SubSystemRole signInSubSystemRole1 = SubSystemRole.業務統括者_購買;
    private SubSystemRole signInSubSystemRole2 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole signInSubSystemRole3 = SubSystemRole.業務統括者_販売_花卉;
    private SubSystemRole signInSubSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole signInSubSystemRole5 = SubSystemRole.業務統括者_販売_麦;
    private SubSystemRole signInSubSystemRole6 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate signInValidThruStartDate0 = LocalDate.of(2020, 12, 10);
    private LocalDate signInValidThruStartDate1 = LocalDate.of(2020, 12, 11);
    private LocalDate signInValidThruStartDate2 = LocalDate.of(2020, 12, 12);
    private LocalDate signInValidThruStartDate3 = LocalDate.of(2020, 12, 13);
    private LocalDate signInValidThruStartDate4 = LocalDate.of(2020, 12, 14);
    private LocalDate signInValidThruStartDate5 = LocalDate.of(2020, 12, 15);
    private LocalDate signInValidThruStartDate6 = LocalDate.of(2020, 12, 16);
    private LocalDate signInValidThruEndDate0 = LocalDate.of(9999, 12, 20);
    private LocalDate signInValidThruEndDate1 = LocalDate.of(9999, 12, 21);
    private LocalDate signInValidThruEndDate2 = LocalDate.of(9999, 12, 22);
    private LocalDate signInValidThruEndDate3 = LocalDate.of(9999, 12, 23);
    private LocalDate signInValidThruEndDate4 = LocalDate.of(9999, 12, 24);
    private LocalDate signInValidThruEndDate5 = LocalDate.of(9999, 12, 25);
    private LocalDate signInValidThruEndDate6 = LocalDate.of(9999, 12, 26);
    private Operator_SubSystemRoles signInOperator_SubSystemRoles;

    // オペレーター_取引ロール割当系
    private String targetBizTranRoleCode0 = "KBAG01";
    private String targetBizTranRoleCode1 = "YSAG19";
    private String targetBizTranRoleCode2 = "HKAG10";
    private String targetBizTranRoleCode3 = "ANAG01";
    private String targetBizTranRoleName0 = "（購買）購買業務基本";
    private String targetBizTranRoleName1 = "（青果）管理者（仕切実績修正）";
    private String targetBizTranRoleName2 = "（米）ＪＡ取引全般";
    private String targetBizTranRoleName3 = "（畜産）取引全般";
    private SubSystem targetSubSystem0 = SubSystem.購買;
    private SubSystem targetSubSystem1 = SubSystem.販売_青果;
    private SubSystem targetSubSystem2 = SubSystem.販売_米;
    private SubSystem targetSubSystem3 = SubSystem.販売_畜産;
    private LocalDate targetValidThruStartDate0 = LocalDate.of(2020, 1, 1);
    private LocalDate targetValidThruStartDate1 = LocalDate.of(2020, 1, 2);
    private LocalDate targetValidThruStartDate2 = LocalDate.of(2020, 1, 3);
    private LocalDate targetValidThruStartDate3 = LocalDate.of(2020, 1, 4);
    private LocalDate targetValidThruEndDate0 = LocalDate.of(9999, 1, 21);
    private LocalDate targetValidThruEndDate1 = LocalDate.of(9999, 1, 22);
    private LocalDate targetValidThruEndDate2 = LocalDate.of(9999, 1, 23);
    private LocalDate targetValidThruEndDate3 = LocalDate.of(9999, 1, 24);
    private Operator_BizTranRoles targetOperator_BizTranRoles;

    // オペレーター履歴ヘッダー系
    private String changeCause = "（青果）集荷場担当を担当";
    private OperatorHistoryHeader operatorHistoryHeader = OperatorHistoryHeader.createFrom(null, targetOperatorId, LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0), changeCause, null, null);

    // 取引ロール系
    List<BizTranRole> bizTranRoleList = newArrayList(
        BizTranRole.createFrom(39L, "HKAG10", "（米）ＪＡ取引全般", SubSystem.販売_米.getCode(), null, SubSystem.販売_米),
        BizTranRole.createFrom(40L, "HKAG10", "（米）ＪＡ取引全般", SubSystem.販売_米.getCode(), null, SubSystem.販売_米),
        BizTranRole.createFrom(41L, "HKAG15", "（米）ＪＡ取引振込以外全般", SubSystem.販売_米.getCode(), null, SubSystem.販売_米),
        BizTranRole.createFrom(42L, "HKAG99", "（米）センター維持管理担当者", SubSystem.販売_米.getCode(), null, SubSystem.販売_米),
        BizTranRole.createFrom(43L, "HMAG20", "（麦）ＪＡ取引全般", SubSystem.販売_麦.getCode(), null, SubSystem.販売_麦),
        BizTranRole.createFrom(44L, "HMAG20", "（麦）ＪＡ取引全般", SubSystem.販売_麦.getCode(), null, SubSystem.販売_麦),
        BizTranRole.createFrom(45L, "HMAG25", "（麦）ＪＡ取引振込以外全般", SubSystem.販売_麦.getCode(), null, SubSystem.販売_麦),
        BizTranRole.createFrom(46L, "HMAG99", "（麦）センター維持管理担当者", SubSystem.販売_麦.getCode(), null, SubSystem.販売_麦),
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
        BizTranRole.createFrom(1L, "KBAG01", "（購買）購買業務基本", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(2L, "KBAG02", "（購買）本所業務", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(3L, "KBAG03", "（購買）本所管理業務", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(4L, "KBAG04", "（購買）バラエサ処理", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(5L, "KBAG05", "（購買）在庫管理", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(6L, "KBAG06", "（購買）支払業務", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(7L, "KBAG07", "（購買）受入業務", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(8L, "KBAG08", "（購買）受注管理", SubSystem.購買.getCode(), null, SubSystem.購買),
        BizTranRole.createFrom(9L, "KBAG09", "（購買）未収金管理", SubSystem.購買.getCode(), null, SubSystem.購買),
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
        BizTranRole.createFrom(47L, "ANAG01", "（畜産）取引全般", SubSystem.販売_畜産.getCode(), null, SubSystem.販売_畜産),
        BizTranRole.createFrom(48L, "ANAG01", "（畜産）取引全般", SubSystem.販売_畜産.getCode(), null, SubSystem.販売_畜産),
        BizTranRole.createFrom(49L, "ANAG02", "（畜産）維持管理担当者", SubSystem.販売_畜産.getCode(), null, SubSystem.販売_畜産),
        BizTranRole.createFrom(50L, "ANAG98", "（畜産）センター維持管理担当者", SubSystem.販売_畜産.getCode(), null, SubSystem.販売_畜産),
        BizTranRole.createFrom(51L, "ANAG99", "（畜産）維持管理責任者", SubSystem.販売_畜産.getCode(), null, SubSystem.販売_畜産));
    BizTranRoles bizTranRoles = BizTranRoles.createFrom(bizTranRoleList);

    // 検証値
    private Operator_BizTranRoleCriteria actualOperator_BizTranRoleCriteria;
    private Operator_SubSystemRoleCriteria actualOperator_SubSystemRoleCriteria;
    private Long actualSignInOperatorId;
    private Long actualTargetOperatorId;
    private List<BizTranRoleGrantedAssignRoleDto> actualAssignRoleDtoList;
    private List<BizTranRoleGrantedAllRoleDto> actualAllRoleDtoList;
    private OperatorHistoryHeader actualOperatorHistoryHeader;

    // テスト対象クラス生成
    private SearchBizTranRoleGranted createSearchBizTranRoleGranted() {
        Operator_BizTranRoleRepository operator_BizTranRoleRepository = new Operator_BizTranRoleRepository() {
            @Override
            public Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders) {
                actualOperator_BizTranRoleCriteria = operator_BizTranRoleCriteria;
                return targetOperator_BizTranRoles;
            }
        };
        Operator_SubSystemRoleRepository operator_SubSystemRoleRepository = new Operator_SubSystemRoleRepository() {
            @Override
            public Operator_SubSystemRoles selectBy(Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {
                actualOperator_SubSystemRoleCriteria = operator_SubSystemRoleCriteria;
                return signInOperator_SubSystemRoles;
            }
        };
        OperatorHistoryHeaderRepository operatorHistoryHeaderRepository = new OperatorHistoryHeaderRepository() {
            @Override
            public OperatorHistoryHeader latestOneByOperatorId(Long operatorId) {
                return operatorHistoryHeader;
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
                return bizTranRoles;
            }
        };

        return new SearchBizTranRoleGranted(operator_BizTranRoleRepository, operator_SubSystemRoleRepository, operatorHistoryHeaderRepository, bizTranRoleRepository);
    }

    // Request作成
    private BizTranRoleGrantedSearchRequest createRequest() {
        return new BizTranRoleGrantedSearchRequest() {
            @Override
            public Long getSignInOperatorId() {
                return signInOperatorId;
            }
            @Override
            public Long getTargetOperatorId() {
                return targetOperatorId;
            }
        };
    }
    // Response作成
    private BizTranRoleGrantedSearchResponse createResponse() {
        return new BizTranRoleGrantedSearchResponse() {
            @Override
            public void setSignInOperatorId(Long signInOperatorId) {
               actualSignInOperatorId = signInOperatorId;
            }
            @Override
            public void setTargetOperatorId(Long targetOperatorId) {
                actualTargetOperatorId = targetOperatorId;
            }
            @Override
            public void setAssignRoleDtoList(List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList) {
                actualAssignRoleDtoList = assignRoleDtoList;
            }
            @Override
            public void setAllRoleDtoList(List<BizTranRoleGrantedAllRoleDto> allRoleDtoList) {
                actualAllRoleDtoList = allRoleDtoList;
            }
            @Override
            public void setOperatorHistoryHeader(OperatorHistoryHeader operatorHistoryHeader) {
                actualOperatorHistoryHeader = operatorHistoryHeader;
            }
        };
    }

    // サインインオペレーター の オペレーター_サブシステムロール割当群作成
    private Operator_SubSystemRoles createSignInOperator_SubSystemRoles(List<Integer> createNoList) {
        List<Operator_SubSystemRole> preOperator_SubSystemRoleList = newArrayList(
            Operator_SubSystemRole.createFrom(9000L, signInOperatorId, signInSubSystemRole0.getCode(), signInValidThruStartDate0, signInValidThruEndDate0, 1, null, signInSubSystemRole0),
            Operator_SubSystemRole.createFrom(9001L, signInOperatorId, signInSubSystemRole1.getCode(), signInValidThruStartDate1, signInValidThruEndDate1, 1, null, signInSubSystemRole1),
            Operator_SubSystemRole.createFrom(9002L, signInOperatorId, signInSubSystemRole2.getCode(), signInValidThruStartDate2, signInValidThruEndDate2, 1, null, signInSubSystemRole2),
            Operator_SubSystemRole.createFrom(9003L, signInOperatorId, signInSubSystemRole3.getCode(), signInValidThruStartDate3, signInValidThruEndDate3, 1, null, signInSubSystemRole3),
            Operator_SubSystemRole.createFrom(9004L, signInOperatorId, signInSubSystemRole4.getCode(), signInValidThruStartDate4, signInValidThruEndDate4, 1, null, signInSubSystemRole4),
            Operator_SubSystemRole.createFrom(9005L, signInOperatorId, signInSubSystemRole5.getCode(), signInValidThruStartDate5, signInValidThruEndDate5, 1, null, signInSubSystemRole5),
            Operator_SubSystemRole.createFrom(9006L, signInOperatorId, signInSubSystemRole6.getCode(), signInValidThruStartDate6, signInValidThruEndDate6, 1, null, signInSubSystemRole6));

        List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList();
        for (Integer createNo : createNoList) {
            operator_SubSystemRoleList.add(preOperator_SubSystemRoleList.get(createNo));
        }

        return Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);
    }
    // オペレーター_取引ロール割当群作成
    private Operator_BizTranRoles createTargetOperator_BizTranRoles(List<Integer> createNoList) {
        BizTranRole bizTranRole1 = BizTranRole.createFrom(401L, targetBizTranRoleCode0, targetBizTranRoleName0, targetSubSystem0.getCode(), null, targetSubSystem0);
        BizTranRole bizTranRole2 = BizTranRole.createFrom(402L, targetBizTranRoleCode1, targetBizTranRoleName1, targetSubSystem1.getCode(), null, targetSubSystem1);
        BizTranRole bizTranRole3 = BizTranRole.createFrom(403L, targetBizTranRoleCode2, targetBizTranRoleName2, targetSubSystem2.getCode(), null, targetSubSystem2);
        BizTranRole bizTranRole4 = BizTranRole.createFrom(404L, targetBizTranRoleCode3, targetBizTranRoleName3, targetSubSystem3.getCode(), null, targetSubSystem3);
        List<Operator_BizTranRole> preOperator_BizTranRoleList = newArrayList(
            Operator_BizTranRole.createFrom(501L, targetOperatorId, bizTranRole1.getBizTranRoleId(), targetValidThruStartDate0, targetValidThruEndDate0, null, null, bizTranRole1),
            Operator_BizTranRole.createFrom(502L, targetOperatorId, bizTranRole2.getBizTranRoleId(), targetValidThruStartDate1, targetValidThruEndDate1, null, null, bizTranRole2),
            Operator_BizTranRole.createFrom(503L, targetOperatorId, bizTranRole3.getBizTranRoleId(), targetValidThruStartDate2, targetValidThruEndDate2, null, null, bizTranRole3),
            Operator_BizTranRole.createFrom(504L, targetOperatorId, bizTranRole4.getBizTranRoleId(), targetValidThruStartDate3, targetValidThruEndDate3, null, null, bizTranRole4));

        List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList();
        for (Integer createNo : createNoList) {
            operator_BizTranRoleList.add(preOperator_BizTranRoleList.get(createNo));
        }

        return Operator_BizTranRoles.createFrom(operator_BizTranRoleList);
    }

    /**
     * {@link SearchBizTranRoleGranted#execute(BizTranRoleGrantedSearchRequest request, BizTranRoleGrantedSearchResponse response)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・responseへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test() {
        // テスト対象クラス生成
        SearchBizTranRoleGranted searchBizTranRoleGranted = createSearchBizTranRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 4));
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(0, 1, 2, 3));
        BizTranRoleGrantedSearchRequest request = createRequest();
        BizTranRoleGrantedSearchResponse response = createResponse();

        // 期待値
        List<BizTranRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : targetOperator_BizTranRoles.getValues()) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
            assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }

        List<BizTranRoleGrantedAllRoleDto> expectedAllRoleDtoList = newArrayList();
        for (BizTranRole bizTranRole : bizTranRoles.getValues().stream().sorted(Orders.empty().addOrder("subSystem.displaySortOrder").addOrder("bizTranRoleCode").toComparator()).collect(Collectors.toList())) {
            BizTranRoleGrantedAllRoleDto allRoleDto = new BizTranRoleGrantedAllRoleDto();
            allRoleDto.setBizTranRole(bizTranRole);
            allRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(bizTranRole, signInOperator_SubSystemRoles));
            expectedAllRoleDtoList.add(allRoleDto);
        }

        assertThatCode(() ->
            // 実行
            searchBizTranRoleGranted.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualSignInOperatorId).isEqualTo(signInOperatorId);
        assertThat(actualTargetOperatorId).isEqualTo(targetOperatorId);
        assertThat(actualAssignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
        assertThat(actualAllRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAllRoleDtoList);
        assertThat(actualOperatorHistoryHeader).usingRecursiveComparison().isEqualTo(operatorHistoryHeader);
    }

    /**
     * {@link SearchBizTranRoleGranted#searchOperator_SubSystemRoles(Long operatorId)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Criteriaへのセット
     *  ・modelへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void searchOperator_SubSystemRoles_test() {
        // テスト対象クラス生成
        SearchBizTranRoleGranted searchBizTranRoleGranted = createSearchBizTranRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(5, 4, 3, 2, 1, 0));

        // 期待値
        Operator_SubSystemRoleCriteria expectedCriteria = new Operator_SubSystemRoleCriteria();
        expectedCriteria.getOperatorIdCriteria().setEqualTo(signInOperatorId);
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(0, 1, 2, 3, 4, 5));

        // 実行
        Operator_SubSystemRoles operator_SubSystemRoles = searchBizTranRoleGranted.searchOperator_SubSystemRoles(signInOperatorId);

        // 結果検証
        assertThat(actualOperator_SubSystemRoleCriteria.toString()).isEqualTo(expectedCriteria.toString());
        assertThat(operator_SubSystemRoles).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles);
    }

    /**
     * {@link SearchBizTranRoleGranted#searchOperator_BizTranRoles(Long operatorId)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Criteriaへのセット
     *  ・modelへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void searchOperator_BizTranRoles_test() {
        // テスト対象クラス生成
        SearchBizTranRoleGranted searchBizTranRoleGranted = createSearchBizTranRoleGranted();

        // 実行値
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(3, 2, 1, 0));

        // 期待値
        Operator_BizTranRoleCriteria expectedCriteria = new Operator_BizTranRoleCriteria();
        expectedCriteria.getOperatorIdCriteria().setEqualTo(targetOperatorId);
        Operator_BizTranRoles expectedOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(0, 1, 2, 3));;

        // 実行
        Operator_BizTranRoles operator_BizTranRoles = searchBizTranRoleGranted.searchOperator_BizTranRoles(targetOperatorId);

        // 結果検証
        assertThat(actualOperator_BizTranRoleCriteria.toString()).isEqualTo(expectedCriteria.toString());
        assertThat((Iterable<? extends Operator_BizTranRole>) operator_BizTranRoles).usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoles);
    }

    /**
     * {@link SearchBizTranRoleGranted#searchOperatorHistoryHeader(Long operatorId)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void searchOperatorHistoryHeader_test() {
        // テスト対象クラス生成
        SearchBizTranRoleGranted searchBizTranRoleGranted = createSearchBizTranRoleGranted();

        // 期待値
        OperatorHistoryHeader expectedOperatorHistoryHeader = operatorHistoryHeader;

        // 実行
        OperatorHistoryHeader operatorHistoryHeader = searchBizTranRoleGranted.searchOperatorHistoryHeader(targetOperatorId);

        // 結果検証
        assertThat(operatorHistoryHeader).usingRecursiveComparison().isEqualTo(expectedOperatorHistoryHeader);
    }

    /**
     * {@link SearchBizTranRoleGranted#searchBizTranRoles()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void searchBizTranRoles_test() {
        // テスト対象クラス生成
        SearchBizTranRoleGranted searchBizTranRoleGranted = createSearchBizTranRoleGranted();

        // 期待値
        BizTranRoles expectedBizTranRoles = BizTranRoles.createFrom(bizTranRoles.getValues().stream().sorted(Orders.empty().addOrder("subSystem.displaySortOrder").addOrder("bizTranRoleCode").toComparator()).collect(Collectors.toList()));

        // 実行
        BizTranRoles bizTranRoles = searchBizTranRoleGranted.searchBizTranRoles();

        // 結果検証
        assertThat(bizTranRoles).usingRecursiveComparison().isEqualTo(expectedBizTranRoles);
    }

    /**
     * {@link SearchBizTranRoleGranted#createAssignRoleDtoList(Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_BizTranRoles targetOperator_BizTranRoles)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createAssignRoleDtoList_test() {
        // テスト対象クラス生成
        SearchBizTranRoleGranted searchBizTranRoleGranted = createSearchBizTranRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 3));
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(0, 1, 2, 3));

        // 期待値
        List<BizTranRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
            for (Operator_BizTranRole operator_BizTranRole : targetOperator_BizTranRoles.getValues()) {
                BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
                assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
                assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
                expectedAssignRoleDtoList.add(assignRoleDto);
        }

        // 実行
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = searchBizTranRoleGranted.createAssignRoleDtoList(signInOperator_SubSystemRoles, targetOperator_BizTranRoles);

        // 結果検証
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link SearchBizTranRoleGranted#createAssignRoleDtoList(Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_BizTranRoles targetOperator_BizTranRoles)}テスト
     *  ●パターン
     *    正常
     *    （サインインオペレーター の オペレーター_サブシステムロール割当 なし）
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createAssignRoleDtoList_test1() {
        // テスト対象クラス生成
        SearchBizTranRoleGranted searchBizTranRoleGranted = createSearchBizTranRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList());
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(0, 1, 2, 3));

        // 期待値
        List<BizTranRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : targetOperator_BizTranRoles.getValues()) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
            assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }

        // 実行
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = searchBizTranRoleGranted.createAssignRoleDtoList(signInOperator_SubSystemRoles, targetOperator_BizTranRoles);

        // 結果検証
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link SearchBizTranRoleGranted#createAssignRoleDtoList(Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_BizTranRoles targetOperator_BizTranRoles)}テスト
     *  ●パターン
     *    正常
     *    （ターゲットオペレーター の オペレーター_取引ロール割当 なし）
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createAssignRoleDtoList_test2() {
        // テスト対象クラス生成
        SearchBizTranRoleGranted searchBizTranRoleGranted = createSearchBizTranRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 3));
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList());

        // 期待値
        List<BizTranRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();

        // 実行
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = searchBizTranRoleGranted.createAssignRoleDtoList(signInOperator_SubSystemRoles, targetOperator_BizTranRoles);

        // 結果検証
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link SearchBizTranRoleGranted#createAllRoleDtoList(Operator_SubSystemRoles signInOperator_SubSystemRoles, BizTranRoles bizTranRoles)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createAllRoleDtoList_test() {
        // テスト対象クラス生成
        SearchBizTranRoleGranted searchBizTranRoleGranted = createSearchBizTranRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 3));
        bizTranRoles = BizTranRoles.createFrom(bizTranRoles.getValues().stream().sorted(Orders.empty().addOrder("subSystem.displaySortOrder").addOrder("bizTranRoleCode").toComparator()).collect(Collectors.toList()));

        // 期待値
        List<BizTranRoleGrantedAllRoleDto> expectedAllRoleDtoList = newArrayList();
        for (BizTranRole bizTranRole : bizTranRoles.getValues().stream().sorted(Orders.empty().addOrder("subSystem.displaySortOrder").addOrder("bizTranRoleCode").toComparator()).collect(Collectors.toList())) {
            BizTranRoleGrantedAllRoleDto allRoleDto = new BizTranRoleGrantedAllRoleDto();
            allRoleDto.setBizTranRole(bizTranRole);
            allRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(bizTranRole, signInOperator_SubSystemRoles));
            expectedAllRoleDtoList.add(allRoleDto);
        }

        // 実行
        List<BizTranRoleGrantedAllRoleDto> allRoleDtoList = searchBizTranRoleGranted.createAllRoleDtoList(signInOperator_SubSystemRoles, bizTranRoles);

        // 結果検証
        assertThat(allRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAllRoleDtoList);
    }

    /**
     * {@link SearchBizTranRoleGranted#createAllRoleDtoList(Operator_SubSystemRoles signInOperator_SubSystemRoles, BizTranRoles bizTranRoles)}テスト
     *  ●パターン
     *    正常
     *    （サインインオペレーター の オペレーター_サブシステムロール割当 なし）
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createAllRoleDtoList_test1() {
        // テスト対象クラス生成
        SearchBizTranRoleGranted searchBizTranRoleGranted = createSearchBizTranRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList());
        bizTranRoles = BizTranRoles.createFrom(bizTranRoles.getValues().stream().sorted(Orders.empty().addOrder("subSystem.displaySortOrder").addOrder("bizTranRoleCode").toComparator()).collect(Collectors.toList()));

        // 期待値
        List<BizTranRoleGrantedAllRoleDto> expectedAllRoleDtoList = newArrayList();
        for (BizTranRole bizTranRole : bizTranRoles.getValues().stream().sorted(Orders.empty().addOrder("subSystem.displaySortOrder").addOrder("bizTranRoleCode").toComparator()).collect(Collectors.toList())) {
            BizTranRoleGrantedAllRoleDto allRoleDto = new BizTranRoleGrantedAllRoleDto();
            allRoleDto.setBizTranRole(bizTranRole);
            allRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(bizTranRole, signInOperator_SubSystemRoles));
            expectedAllRoleDtoList.add(allRoleDto);
        }

        // 実行
        List<BizTranRoleGrantedAllRoleDto> allRoleDtoList = searchBizTranRoleGranted.createAllRoleDtoList(signInOperator_SubSystemRoles, bizTranRoles);

        // 結果検証
        assertThat(allRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAllRoleDtoList);
    }
}