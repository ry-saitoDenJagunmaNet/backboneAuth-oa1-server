package net.jagunma.backbone.auth.authmanager.infra.web.oa11030;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030BizTranRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030SubsystemRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.AccountLockStatus;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11030InitPresenterTest {

    // 実行既定値
    // オペレーター系
    private Long operatorId = 123456L;
    private Integer recordVersion = 1;
    private String jaCode = "006";
    private String jaName = "JA前橋市";
    private Long branchId = 1L;
    private String operatorCode = "yu123456";
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate expirationStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate expirationEndDate = LocalDate.of(2020, 9, 30);
    private Boolean isDeviceAuth = true;
    private AvailableStatus availableStatus = AvailableStatus.利用可能;
    private String changeCause = "認証機器使用開始";
    private AccountLockStatus lockStatus = AccountLockStatus.アンロック;

    private Long jaId = 6L;
    private String branchCode = "001";

    // オペレーター_サブシステムロール割当履歴系 ＆ オペレーター_取引ロール割当履歴系
    Operator operator = Operator.createFrom(operatorId, operatorCode, operatorName, mailAddress, expirationStartDate, expirationEndDate, isDeviceAuth, jaId, jaCode, branchId, branchCode, availableStatus.getCode(), recordVersion);
    // オペレーター_サブシステムロール割当履歴系
    List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(301L, operatorId, SubSystemRole.JA管理者.getCode(), expirationStartDate, expirationEndDate, 391, operator, SubSystemRole.JA管理者),
        Operator_SubSystemRole.createFrom(302L, operatorId, SubSystemRole.業務統括者_購買.getCode(), expirationStartDate, expirationEndDate, 392, operator, SubSystemRole.業務統括者_購買),
        Operator_SubSystemRole.createFrom(303L, operatorId, SubSystemRole.業務統括者_販売_青果.getCode(), expirationStartDate, expirationEndDate, 393, operator, SubSystemRole.業務統括者_販売_青果),
        Operator_SubSystemRole.createFrom(304L, operatorId, SubSystemRole.業務統括者_販売_米.getCode(), expirationStartDate, expirationEndDate, 394, operator, SubSystemRole.業務統括者_販売_米),
        Operator_SubSystemRole.createFrom(305L, operatorId, SubSystemRole.業務統括者_販売_畜産.getCode(), expirationStartDate, expirationEndDate, 395, operator, SubSystemRole.業務統括者_販売_畜産));
    private Operator_SubSystemRoles operator_SubSystemRoles = Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);

    // オペレーター_取引ロール割当履歴系
    List<BizTranRole> bizTranRoleList = newArrayList(
        BizTranRole.createFrom(401L, "KB0000", "購買メインメニュー", SubSystem.購買.getCode(), recordVersion, SubSystem.購買),
        BizTranRole.createFrom(402L, "KB0001", "支所検索", SubSystem.購買.getCode(), recordVersion, SubSystem.購買),
        BizTranRole.createFrom(403L, "KB0002", "顧客検索", SubSystem.購買.getCode(), recordVersion, SubSystem.購買),
        BizTranRole.createFrom(404L, "YS0000", "野菜メインメニュー", SubSystem.販売_青果.getCode(), recordVersion, SubSystem.販売_青果));
    List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList(
        Operator_BizTranRole.createFrom(501L, operatorId, bizTranRoleList.get(0).getBizTranRoleId(), expirationStartDate, expirationEndDate, recordVersion, operator, bizTranRoleList.get(0)),
        Operator_BizTranRole.createFrom(502L, operatorId, bizTranRoleList.get(1).getBizTranRoleId(), expirationStartDate, expirationEndDate, recordVersion, operator, bizTranRoleList.get(1)),
        Operator_BizTranRole.createFrom(503L, operatorId, bizTranRoleList.get(2).getBizTranRoleId(), expirationStartDate, expirationEndDate, recordVersion, operator, bizTranRoleList.get(2)),
        Operator_BizTranRole.createFrom(504L, operatorId, bizTranRoleList.get(3).getBizTranRoleId(), expirationStartDate, expirationEndDate, recordVersion, operator, bizTranRoleList.get(3)));
    private Operator_BizTranRoles operator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);;

    // 店舗群AtMoment作成
    private BranchesAtMoment createBranchesAtMoment() {
        List<BranchAtMoment> branchAtMomentList = newArrayList();
        branchAtMomentList.add(BranchAtMoment.builder()
            .withIdentifier(1L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(
                BranchAttribute.builder()
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

    /**
     * {@link Oa11030InitPresenter#bindTo(Oa11030Vo vo)}テスト
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
        Oa11030Vo vo = new Oa11030Vo();
        Oa11030InitPresenter presenter = new Oa11030InitPresenter();
        presenter.setOperatorId(operatorId);
        presenter.setRecordVersion(recordVersion);
        presenter.setJaCode(jaCode);
        presenter.setJaName(jaName);
        presenter.setBranchId(branchId);
        presenter.setOperatorCode(operatorCode);
        presenter.setOperatorName(operatorName);
        presenter.setMailAddress(mailAddress);
        presenter.setExpirationStartDate(expirationStartDate);
        presenter.setExpirationEndDate(expirationEndDate);
        presenter.setIsDeviceAuth(isDeviceAuth);
        presenter.setAvailableStatus(availableStatus);
        presenter.setChangeCause(changeCause);
        presenter.setLockStatus(lockStatus);
        presenter.setOperator_SubSystemRoles(operator_SubSystemRoles);
        presenter.setOperator_BizTranRoles(operator_BizTranRoles);
        presenter.setBranchesAtMoment(createBranchesAtMoment());

        // 期待値
        List<Oa11030SubsystemRoleTableVo> oa11030SubsystemRoleTableVoList = newArrayList();
        for (Operator_SubSystemRole operator_subSystemRole : operator_SubSystemRoles.getValues()) {
            Oa11030SubsystemRoleTableVo oa11030SubsystemRoleTableVo = new Oa11030SubsystemRoleTableVo();
            oa11030SubsystemRoleTableVo.setRoleName(operator_subSystemRole.getSubSystemRole().getName());
            oa11030SubsystemRoleTableVo.setExpirationDate(
                operator_subSystemRole.getExpirationStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " ～ " +
                    operator_subSystemRole.getExpirationEndDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            oa11030SubsystemRoleTableVoList.add(oa11030SubsystemRoleTableVo);
        }

        List<Oa11030BizTranRoleTableVo> oa11030BizTranRoleTableVoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoles.getValues()) {
            Oa11030BizTranRoleTableVo oa11030BizTranRoleTableVo = new Oa11030BizTranRoleTableVo();
            oa11030BizTranRoleTableVo.setRoleCode(operator_BizTranRole.getBizTranRole().getBizTranRoleCode());
            oa11030BizTranRoleTableVo.setRoleName(operator_BizTranRole.getBizTranRole().getBizTranRoleName());
            oa11030BizTranRoleTableVo.setExpirationDate(
                operator_BizTranRole.getExpirationStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " ～ " +
                    operator_BizTranRole.getExpirationEndDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            oa11030BizTranRoleTableVoList.add(oa11030BizTranRoleTableVo);
        }
        Oa11030Vo expectedVo = new Oa11030Vo();
        expectedVo.setOperatorId(operatorId);
        expectedVo.setRecordVersion(recordVersion);
        expectedVo.setJa(jaCode + " " + jaName);
        expectedVo.setBranchId(branchId);
        expectedVo.setOperatorCode(operatorCode);
        expectedVo.setOperatorName(operatorName);
        expectedVo.setMailAddress(mailAddress);
        expectedVo.setExpirationStartDate(expirationStartDate);
        expectedVo.setExpirationEndDate(expirationEndDate);
        expectedVo.setIsDeviceAuth(isDeviceAuth);
        expectedVo.setAvailableStatus(availableStatus.getCode());
        expectedVo.setChangeCause(changeCause);
        expectedVo.setLockStatus(lockStatus.getCode());
        expectedVo.setOa11030SubsystemRoleTableVoList(oa11030SubsystemRoleTableVoList);
        expectedVo.setOa11030BizTranRoleTableVoList(oa11030BizTranRoleTableVoList);
        expectedVo.setBranchItemsSource(SelectOptionItemsSource.createFrom(createBranchesAtMoment()).getValue());

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}