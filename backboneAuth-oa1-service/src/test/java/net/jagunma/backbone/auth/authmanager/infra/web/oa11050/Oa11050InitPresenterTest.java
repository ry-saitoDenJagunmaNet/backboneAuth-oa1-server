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

    // ???????????????
    // ???????????????????????????
    private Long operatorId = 123456L;
    private String operatorCode = "yu123456";
    private String operatorName = "?????????????????????";

    // ??????????????????_??????????????????????????????
    private String bizTranRoleCode0 = "KBAG01";
    private String bizTranRoleCode1 = "YSAG10";
    private String bizTranRoleCode2 = "HKAG10";
    private String bizTranRoleCode3 = "ANAG01";
    private String bizTranRoleName0 = "??????????????????????????????";
    private String bizTranRoleName1 = "?????????????????????";
    private String bizTranRoleName2 = "???????????????????????????";
    private String bizTranRoleName3 = "????????????????????????";
    private SubSystem subSystem0 = SubSystem.??????;
    private SubSystem subSystem1 = SubSystem.??????_??????;
    private SubSystem subSystem2 = SubSystem.??????_???;
    private SubSystem subSystem3 = SubSystem.??????_??????;
    private LocalDate validThruStartDate0 = LocalDate.of(2020, 1, 1);
    private LocalDate validThruStartDate1 = LocalDate.of(2020, 1, 2);
    private LocalDate validThruStartDate2 = LocalDate.of(2020, 1, 3);
    private LocalDate validThruStartDate3 = LocalDate.of(2020, 1, 4);
    private LocalDate validThruEndDate0 = LocalDate.of(9999, 1, 21);
    private LocalDate validThruEndDate1 = LocalDate.of(9999, 1, 22);
    private LocalDate validThruEndDate2 = LocalDate.of(9999, 1, 23);
    private LocalDate validThruEndDate3 = LocalDate.of(9999, 1, 24);

    // ?????????????????????????????????????????????
    private String changeCausePlaceholder = "?????????????????????????????????????????????";

    // ??????AtMoment
    private Long jaId = 6L;
    private String jaCode = "006";
    private String jaName = "JA?????????";
    private JaAtMoment jaAtMoment = JaAtMoment.builder()
        .withIdentifier(jaId)
        .withJaAttribute(JaAttribute.builder()
            .withJaCode(JaCode.of(jaCode))
            .withName(jaName)
            .build())
        .build();

    // ??????AtMoment
    private Long branchId = 1L;
    private String branchCode = "001";
    private BranchAtMoment branchAtMoment = BranchAtMoment.builder().withIdentifier(branchId).withJaAtMoment(jaAtMoment).withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.??????).withBranchCode(BranchCode.of(branchCode)).withName("??????").build()).build();

    // ?????????????????????
    private Operator operator = Operator.createFrom(operatorId, operatorCode, operatorName, null, null, null, null, null, jaCode, branchId, branchCode, null, null, branchAtMoment);

    // ??????????????????_????????????????????????
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

    // ???????????????????????????????????????
    private OperatorHistoryHeader operatorHistoryHeader = OperatorHistoryHeader.createFrom(null, operatorId, LocalDateTime.of(2020,10,1,0,1,2), changeCausePlaceholder, null, operator);

    // ??????????????????
    private List<BizTranRole> allBizTranRoleList = newArrayList(
        BizTranRole.createFrom(1L, "KBAG01", "??????????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(2L, "KBAG02", "????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(3L, "KBAG03", "??????????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(4L, "KBAG04", "??????????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(5L, "KBAG05", "????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(6L, "KBAG06", "????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(7L, "KBAG07", "????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(8L, "KBAG08", "????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(9L, "KBAG09", "???????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(10L, "KBAG10", "?????????????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(11L, "KBAG11", "????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(12L, "KBAG12", "????????????LPG?????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(13L, "KBAG13", "????????????LPG??????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(14L, "KBAG14", "???????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(15L, "KBAG15", "????????????????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(16L, "KBAG16", "???????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(17L, "KBAG17", "?????????????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(18L, "KBAG18", "??????????????????????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(19L, "KBAG19", "??????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(20L, "KBAG20", "?????????????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(21L, "KBAG50", "???????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(22L, "KBAG99", "??????????????????????????????", SubSystem.??????.getCode(), null, SubSystem.??????),
        BizTranRole.createFrom(23L, "YSAG10", "?????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(24L, "YSAG20", "????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(25L, "YSAG30", "???????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(26L, "YSAG40", "??????????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(27L, "YSAG50", "????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(28L, "YSAG60", "?????????????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(29L, "YSAG70", "????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(30L, "YSAG90", "???????????????????????????????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(31L, "YFAG10", "?????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(32L, "YFAG10", "?????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(33L, "YFAG20", "????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(34L, "YFAG30", "???????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(35L, "YFAG40", "??????????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(36L, "YFAG50", "????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(37L, "YFAG70", "????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(38L, "YFAG90", "???????????????????????????????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(39L, "HKAG10", "???????????????????????????", SubSystem.??????_???.getCode(), null, SubSystem.??????_???),
        BizTranRole.createFrom(40L, "HKAG10", "???????????????????????????", SubSystem.??????_???.getCode(), null, SubSystem.??????_???),
        BizTranRole.createFrom(41L, "HKAG15", "???????????????????????????????????????", SubSystem.??????_???.getCode(), null, SubSystem.??????_???),
        BizTranRole.createFrom(42L, "HKAG99", "??????????????????????????????????????????", SubSystem.??????_???.getCode(), null, SubSystem.??????_???),
        BizTranRole.createFrom(43L, "HMAG20", "???????????????????????????", SubSystem.??????_???.getCode(), null, SubSystem.??????_???),
        BizTranRole.createFrom(44L, "HMAG20", "???????????????????????????", SubSystem.??????_???.getCode(), null, SubSystem.??????_???),
        BizTranRole.createFrom(45L, "HMAG25", "???????????????????????????????????????", SubSystem.??????_???.getCode(), null, SubSystem.??????_???),
        BizTranRole.createFrom(46L, "HMAG99", "??????????????????????????????????????????", SubSystem.??????_???.getCode(), null, SubSystem.??????_???),
        BizTranRole.createFrom(47L, "ANAG01", "????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(48L, "ANAG01", "????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(49L, "ANAG02", "?????????????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(50L, "ANAG98", "?????????????????????????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????),
        BizTranRole.createFrom(51L, "ANAG99", "?????????????????????????????????", SubSystem.??????_??????.getCode(), null, SubSystem.??????_??????));
    private BizTranRoles allBizTranRoles = BizTranRoles.createFrom(allBizTranRoleList);

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
        for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoles.getValues()) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
            assignRoleDto.setIsModifiable(true);
            assignRoleDtoList.add(assignRoleDto);

            Oa11050AssignRoleTableVo assignRoleTableVo = new Oa11050AssignRoleTableVo();
            assignRoleTableVo.setRoleId(operator_BizTranRole.getBizTranRoleId());
            assignRoleTableVo.setRoleCode(operator_BizTranRole.getBizTranRole().getBizTranRoleCode());
            assignRoleTableVo.setRoleName(operator_BizTranRole.getBizTranRole().getBizTranRoleName());
            assignRoleTableVo.setSubSystemCode(operator_BizTranRole.getBizTranRole().getSubSystemCode());
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

    /**
     * {@link Oa11050InitPresenter#bindTo(Oa11050Vo vo)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???Vo???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test() {
        // ?????????
        Oa11050Vo vo = new Oa11050Vo();
        Oa11050InitPresenter presenter = new Oa11050InitPresenter();
        presenter.setOperatorId(operatorId);
        presenter.setAssignRoleDtoList(createBizTranRoleGrantedAssignRoleDtoList());
        presenter.setAllRoleDtoList(createBizTranRoleGrantedAllRoleDtoList());
        presenter.setOperatorHistoryHeader(operatorHistoryHeader);

        // ?????????
        Oa11050Vo expectedVo = new Oa11050Vo();
        expectedVo.setOperatorId(operatorId);
        expectedVo.setJa(jaCode + " " + jaName);
        expectedVo.setOperator(operatorCode + " " + operatorName);
        expectedVo.setAssignRoleTableVoList(createOa11050AssignRoleTableVoList());
        expectedVo.setUnAssignRoleTableVoList(createOa11050UnAssignRoleTableVoList());
        expectedVo.setChangeCause(null);
        expectedVo.setChangeCausePlaceholder(changeCausePlaceholder);
        expectedVo.setSubSystemItemsSource(SelectOptionItemsSource.createFrom(SubSystem.getValidValues()).getValue());

        // ??????
        presenter.bindTo(vo);

        // ????????????
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11050InitPresenter#bindTo(Oa11050Vo vo)}?????????
     *  ???????????????
     *    ??????
     *    ????????????????????????Dto?????????????????????_??????????????????????????? ?????????
     *    ???????????????Dto???????????????????????? ?????????
     *
     *  ???????????????
     *  ???Vo???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test1() {
        // ?????????
        Oa11050Vo vo = new Oa11050Vo();
        Oa11050InitPresenter presenter = new Oa11050InitPresenter();
        presenter.setOperatorId(operatorId);
        presenter.setAssignRoleDtoList(newArrayList());
        presenter.setAllRoleDtoList(newArrayList());
        presenter.setOperatorHistoryHeader(operatorHistoryHeader);

        // ?????????
        Oa11050Vo expectedVo = new Oa11050Vo();
        expectedVo.setOperatorId(operatorId);
        expectedVo.setJa(jaCode + " " + jaName);
        expectedVo.setOperator(operatorCode + " " + operatorName);
        expectedVo.setAssignRoleTableVoList(newArrayList());
        expectedVo.setUnAssignRoleTableVoList(newArrayList());
        expectedVo.setChangeCause(null);
        expectedVo.setChangeCausePlaceholder(changeCausePlaceholder);
        expectedVo.setSubSystemItemsSource(SelectOptionItemsSource.createFrom(SubSystem.getValidValues()).getValue());

        // ??????
        presenter.bindTo(vo);

        // ????????????
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}
