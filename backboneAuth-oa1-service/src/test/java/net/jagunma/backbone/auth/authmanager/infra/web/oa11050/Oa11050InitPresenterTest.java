package net.jagunma.backbone.auth.authmanager.infra.web.oa11050;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAllRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050UnAssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11050InitPresenterTest {

    // 実行既定値
    // サインインオペレーター項目系
    private Long signInOperatorId = 987654L;

    // ターゲットオペレーター項目系
    private Long operatorId = 123456L;
    private String operatorCode = "yu123456";
    private String operatorName = "オペレーター名";

    // オペレーター_取引ロール割当系
    private String targetBizTranRoleCode0 = "KBAG01";
    private String targetBizTranRoleCode1 = "YSAG10";
    private String targetBizTranRoleCode2 = "HKAG10";
    private String targetBizTranRoleCode3 = "ANAG01";
    private String targetBizTranRoleName0 = "（購買）購買業務基本";
    private String targetBizTranRoleName1 = "（青果）管理者";
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

    // オペレーター履歴ヘッダー項目系
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
    private List<BizTranRole> targetBizTranRoleList = newArrayList(
        BizTranRole.createFrom(401L, targetBizTranRoleCode0, targetBizTranRoleName0, targetSubSystem0.getCode(), null, targetSubSystem0),
        BizTranRole.createFrom(402L, targetBizTranRoleCode1, targetBizTranRoleName1, targetSubSystem1.getCode(), null, targetSubSystem1),
        BizTranRole.createFrom(403L, targetBizTranRoleCode2, targetBizTranRoleName2, targetSubSystem2.getCode(), null, targetSubSystem2),
        BizTranRole.createFrom(404L, targetBizTranRoleCode3, targetBizTranRoleName3, targetSubSystem3.getCode(), null, targetSubSystem3));
    private List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList(
        Operator_BizTranRole.createFrom(501L, operatorId, targetBizTranRoleList.get(0).getBizTranRoleId(), targetValidThruStartDate0, targetValidThruEndDate0, null, operator, targetBizTranRoleList.get(0)),
        Operator_BizTranRole.createFrom(502L, operatorId, targetBizTranRoleList.get(1).getBizTranRoleId(), targetValidThruStartDate1, targetValidThruEndDate1, null, operator, targetBizTranRoleList.get(1)),
        Operator_BizTranRole.createFrom(503L, operatorId, targetBizTranRoleList.get(2).getBizTranRoleId(), targetValidThruStartDate2, targetValidThruEndDate2, null, operator, targetBizTranRoleList.get(2)),
        Operator_BizTranRole.createFrom(504L, operatorId, targetBizTranRoleList.get(3).getBizTranRoleId(), targetValidThruStartDate3, targetValidThruEndDate3, null, operator, targetBizTranRoleList.get(3)));
    private Operator_BizTranRoles targetOperator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);

    // オペレーター履歴ヘッダー系
    OperatorHistoryHeader operatorHistoryHeader = OperatorHistoryHeader.createFrom(null, operatorId, LocalDateTime.of(2020,10,1,0,1,2), changeCausePlaceholder, null, operator);

    // 取引ロール系
    private List<BizTranRole> bizTranRoleList = newArrayList(
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
    private BizTranRoles bizTranRoles = BizTranRoles.createFrom(bizTranRoleList);

    private List<BizTranRoleGrantedAssignRoleDto> createBizTranRoleGrantedAssignRoleDtoList() {
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = newArrayList();
        createOa11050AssignRole(assignRoleDtoList, newArrayList());
        return assignRoleDtoList;
    }
    private List<Oa11050AssignRoleTableVo> createOa11050AssignRoleTableVoList() {
        List<Oa11050AssignRoleTableVo> assignRoleTableVoList = newArrayList();
        createOa11050AssignRole(newArrayList(), assignRoleTableVoList);
        return assignRoleTableVoList;
    }
    private void createOa11050AssignRole(List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList, List<Oa11050AssignRoleTableVo> assignRoleTableVoList) {
        for (Operator_BizTranRole operator_BizTranRole : targetOperator_BizTranRoles.getValues()) {
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
        for (BizTranRole bizTranRole : bizTranRoles.getValues()) {
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

    /**
     * {@link Oa11050InitPresenter#bindTo(Oa11050Vo vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test() {
        // 実行値
        Oa11050Vo vo = new Oa11050Vo();
        Oa11050InitPresenter presenter = new Oa11050InitPresenter();
        presenter.setSignInOperatorId(signInOperatorId);
        presenter.setTargetOperatorId(operatorId);
        presenter.setAssignRoleDtoList(createBizTranRoleGrantedAssignRoleDtoList());
        presenter.setAllRoleDtoList(createBizTranRoleGrantedAllRoleDtoList());
        presenter.setOperatorHistoryHeader(operatorHistoryHeader);

        // 期待値
        Oa11050Vo expectedVo = new Oa11050Vo();
        expectedVo.setOperatorId(operatorId);
        expectedVo.setJa(jaCode + " " + jaName);
        expectedVo.setOperator(operatorCode + " " + operatorName);
        expectedVo.setAssignRoleTableVoList(createOa11050AssignRoleTableVoList());
        expectedVo.setUnAssignRoleTableVoList(createOa11050UnAssignRoleTableVoList());
        expectedVo.setChangeCause(null);
        expectedVo.setChangeCausePlaceholder(changeCausePlaceholder);
        expectedVo.setSubSystemItemsSource(SelectOptionItemsSource.createFrom(SubSystem.getValidValues()).getValue());

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11050InitPresenter#bindTo(Oa11050Vo vo)}テスト
     *  ●パターン
     *    正常
     *    （アサインロールDto（オペレーター_取引ロール割当）が ０件）
     *    （全ロールDto（取引ロール）が ０件）
     *
     *  ●検証事項
     *  ・Voへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test1() {
        // 実行値
        Oa11050Vo vo = new Oa11050Vo();
        Oa11050InitPresenter presenter = new Oa11050InitPresenter();
        presenter.setSignInOperatorId(signInOperatorId);
        presenter.setTargetOperatorId(operatorId);
        presenter.setAssignRoleDtoList(newArrayList());
        presenter.setAllRoleDtoList(newArrayList());
        presenter.setOperatorHistoryHeader(operatorHistoryHeader);

        // 期待値
        Oa11050Vo expectedVo = new Oa11050Vo();
        expectedVo.setOperatorId(operatorId);
        expectedVo.setJa(jaCode + " " + jaName);
        expectedVo.setOperator(operatorCode + " " + operatorName);
        expectedVo.setAssignRoleTableVoList(newArrayList());
        expectedVo.setUnAssignRoleTableVoList(newArrayList());
        expectedVo.setChangeCause(null);
        expectedVo.setChangeCausePlaceholder(changeCausePlaceholder);
        expectedVo.setSubSystemItemsSource(SelectOptionItemsSource.createFrom(SubSystem.getValidValues()).getValue());

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}
