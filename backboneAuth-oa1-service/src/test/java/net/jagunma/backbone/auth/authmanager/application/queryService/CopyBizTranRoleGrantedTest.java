package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.util.BizTranRoleGrantedQueryUtil;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedCopyRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedCopyRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedCopyResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
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

class CopyBizTranRoleGrantedTest {

    // ???????????????
    private final BizTranRoleGrantedQueryUtil bizTranRoleGrantedQueryUtil = new BizTranRoleGrantedQueryUtil();
    private Long signInOperatorId = 987654L;
    private Long selectedOperatorId = 876543L;
    private Long targetOperatorId = 123456L;

    // ??????????????????_???????????????????????????????????????????????????????????????????????????
    private SubSystemRole signInSubSystemRole0 = SubSystemRole.JA?????????;
    private SubSystemRole signInSubSystemRole1 = SubSystemRole.???????????????_??????;
    private SubSystemRole signInSubSystemRole2 = SubSystemRole.???????????????_??????_??????;
    private SubSystemRole signInSubSystemRole3 = SubSystemRole.???????????????_??????_??????;
    private SubSystemRole signInSubSystemRole4 = SubSystemRole.???????????????_??????_???;
    private SubSystemRole signInSubSystemRole5 = SubSystemRole.???????????????_??????_???;
    private SubSystemRole signInSubSystemRole6 = SubSystemRole.???????????????_??????_??????;
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

    // ??????????????????_??????????????????????????????????????????????????????
    private String selectedBizTranRoleCode0 = "KBAG01";
    private String selectedBizTranRoleCode1 = "YSAG10";
    private String selectedBizTranRoleCode2 = "HKAG10";
    private String selectedBizTranRoleCode3 = "ANAG01";
    private String selectedBizTranRoleCode4 = "KBAG20";
    private String selectedBizTranRoleCode5 = "YSAG20";
    private String selectedBizTranRoleCode6 = "HKAG15";
    private String selectedBizTranRoleCode7 = "ANAG02";
    private String selectedBizTranRoleName0 = "??????????????????????????????";
    private String selectedBizTranRoleName1 = "?????????????????????";
    private String selectedBizTranRoleName2 = "???????????????????????????";
    private String selectedBizTranRoleName3 = "????????????????????????";
    private String selectedBizTranRoleName4 = "?????????????????????????????????";
    private String selectedBizTranRoleName5 = "????????????????????????";
    private String selectedBizTranRoleName6 = "???????????????????????????????????????";
    private String selectedBizTranRoleName7 = "?????????????????????????????????";
    private SubSystem selectedSubSystem0 = SubSystem.??????;
    private SubSystem selectedSubSystem1 = SubSystem.??????_??????;
    private SubSystem selectedSubSystem2 = SubSystem.??????_???;
    private SubSystem selectedSubSystem3 = SubSystem.??????_??????;
    private SubSystem selectedSubSystem4 = SubSystem.??????;
    private SubSystem selectedSubSystem5 = SubSystem.??????_??????;
    private SubSystem selectedSubSystem6 = SubSystem.??????_???;
    private SubSystem selectedSubSystem7 = SubSystem.??????_??????;
    private LocalDate selectedValidThruStartDate0 = LocalDate.of(2020, 1, 1);
    private LocalDate selectedValidThruStartDate1 = LocalDate.of(2020, 1, 2);
    private LocalDate selectedValidThruStartDate2 = LocalDate.of(2020, 1, 3);
    private LocalDate selectedValidThruStartDate3 = LocalDate.of(2020, 1, 4);
    private LocalDate selectedValidThruStartDate4 = LocalDate.of(2020, 1, 5);
    private LocalDate selectedValidThruStartDate5 = LocalDate.of(2020, 1, 6);
    private LocalDate selectedValidThruStartDate6 = LocalDate.of(2020, 1, 7);
    private LocalDate selectedValidThruStartDate7 = LocalDate.of(2020, 1, 8);
    private LocalDate selectedValidThruEndDate0 = LocalDate.of(9999, 1, 21);
    private LocalDate selectedValidThruEndDate1 = LocalDate.of(9999, 1, 22);
    private LocalDate selectedValidThruEndDate2 = LocalDate.of(9999, 1, 23);
    private LocalDate selectedValidThruEndDate3 = LocalDate.of(9999, 1, 24);
    private LocalDate selectedValidThruEndDate4 = LocalDate.of(9999, 1, 25);
    private LocalDate selectedValidThruEndDate5 = LocalDate.of(9999, 1, 26);
    private LocalDate selectedValidThruEndDate6 = LocalDate.of(9999, 1, 27);
    private LocalDate selectedValidThruEndDate7 = LocalDate.of(9999, 1, 28);
    private Operator_BizTranRoles selectedOperator_BizTranRoles;

    // ??????????????????_???????????????????????????????????????????????????????????????
    private String targetBizTranRoleCode0 = "KBAG01";
    private String targetBizTranRoleCode1 = "YSAG10";
    private String targetBizTranRoleCode2 = "HKAG10";
    private String targetBizTranRoleCode3 = "ANAG01";
    private String targetBizTranRoleName0 = "??????????????????????????????";
    private String targetBizTranRoleName1 = "?????????????????????";
    private String targetBizTranRoleName2 = "???????????????????????????";
    private String targetBizTranRoleName3 = "????????????????????????";
    private SubSystem targetSubSystem0 = SubSystem.??????;
    private SubSystem targetSubSystem1 = SubSystem.??????_??????;
    private SubSystem targetSubSystem2 = SubSystem.??????_???;
    private SubSystem targetSubSystem3 = SubSystem.??????_??????;
    private LocalDate targetValidThruStartDate0 = LocalDate.of(2020, 1, 1);
    private LocalDate targetValidThruStartDate1 = LocalDate.of(2020, 1, 2);
    private LocalDate targetValidThruStartDate2 = LocalDate.of(2020, 1, 3);
    private LocalDate targetValidThruStartDate3 = LocalDate.of(2020, 1, 4);
    private LocalDate targetValidThruEndDate0 = LocalDate.of(9999, 1, 21);
    private LocalDate targetValidThruEndDate1 = LocalDate.of(9999, 1, 22);
    private LocalDate targetValidThruEndDate2 = LocalDate.of(9999, 1, 23);
    private LocalDate targetValidThruEndDate3 = LocalDate.of(9999, 1, 24);
    private Operator_BizTranRoles targetOperator_BizTranRoles;

    // ?????????
    private Operator_BizTranRoleCriteria actualOperator_BizTranRoleCriteria;
    private Operator_SubSystemRoleCriteria actualOperator_SubSystemRoleCriteria;
    private List<BizTranRoleGrantedAssignRoleDto> actualAssignRoleDtoList;

    // ??????????????????????????????
    private CopyBizTranRoleGranted createCopyBizTranRoleGranted() {
        Operator_BizTranRoleRepository operator_BizTranRoleRepository = new Operator_BizTranRoleRepository() {
            @Override
            public Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders) {
                actualOperator_BizTranRoleCriteria = operator_BizTranRoleCriteria;
                if (operator_BizTranRoleCriteria.getOperatorIdCriteria().getEqualTo().equals(targetOperatorId)) {
                    return targetOperator_BizTranRoles;
                }
                if (operator_BizTranRoleCriteria.getOperatorIdCriteria().getEqualTo().equals(selectedOperatorId)) {
                    return selectedOperator_BizTranRoles;
                }
                return null;
            }
        };
        Operator_SubSystemRoleRepository operator_SubSystemRoleRepository = new Operator_SubSystemRoleRepository() {
            @Override
            public Operator_SubSystemRoles selectBy(Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {
                actualOperator_SubSystemRoleCriteria = operator_SubSystemRoleCriteria;
                return signInOperator_SubSystemRoles;
            }
        };

        return new CopyBizTranRoleGranted(operator_BizTranRoleRepository, operator_SubSystemRoleRepository);
    }

    // Request??????
    private BizTranRoleGrantedCopyRequest createRequest() {
        return new BizTranRoleGrantedCopyRequest() {
            @Override
            public Long getSignInOperatorId() {
                return signInOperatorId;
            }
            @Override
            public Long getSelectedOperatorId() {
                return selectedOperatorId;
            }
            @Override
            public List<BizTranRoleGrantedCopyRequestAssignRole> getAssignRoleList() {
                List<BizTranRoleGrantedCopyRequestAssignRole> assignRoleList = newArrayList();
                for (Operator_BizTranRole operator_BizTranRole : targetOperator_BizTranRoles.getValues()) {
                    BizTranRoleGrantedCopyRequestAssignRole assignRole = new BizTranRoleGrantedCopyRequestAssignRole() {
                        @Override
                        public BizTranRole getBizTranRole() {
                            return operator_BizTranRole.getBizTranRole();
                        }
                        @Override
                        public LocalDate getValidThruStartDate() {
                            return operator_BizTranRole.getValidThruStartDate();
                        }
                        @Override
                        public LocalDate getValidThruEndDate() {
                            return operator_BizTranRole.getValidThruEndDate();
                        }
                        @Override
                        public Boolean getIsModifiable() {
                            return bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles);
                        }
                    };
                    assignRoleList.add(assignRole);
                }

                return assignRoleList;
            }
        };
    }
    // Response??????
    private BizTranRoleGrantedCopyResponse createResponse() {
        return new BizTranRoleGrantedCopyResponse() {
            @Override
            public void setAssignRoleDtoList(List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList) {
                actualAssignRoleDtoList = assignRoleDtoList;
            }
        };
    }

    // ????????????????????????????????? ??? ??????????????????_??????????????????????????????????????????
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
    // ???????????????????????? ??? ??????????????????_??????????????????????????????
    private Operator_BizTranRoles createSelectedOperator_BizTranRoles(List<Integer> createNoList) {
        BizTranRole bizTranRole0 = BizTranRole.createFrom(401L, selectedBizTranRoleCode0, selectedBizTranRoleName0, selectedSubSystem0.getCode(), null, selectedSubSystem0);
        BizTranRole bizTranRole1 = BizTranRole.createFrom(402L, selectedBizTranRoleCode1, selectedBizTranRoleName1, selectedSubSystem1.getCode(), null, selectedSubSystem1);
        BizTranRole bizTranRole2 = BizTranRole.createFrom(403L, selectedBizTranRoleCode2, selectedBizTranRoleName2, selectedSubSystem2.getCode(), null, selectedSubSystem2);
        BizTranRole bizTranRole3 = BizTranRole.createFrom(404L, selectedBizTranRoleCode3, selectedBizTranRoleName3, selectedSubSystem3.getCode(), null, selectedSubSystem3);
        BizTranRole bizTranRole4 = BizTranRole.createFrom(405L, selectedBizTranRoleCode4, selectedBizTranRoleName4, selectedSubSystem4.getCode(), null, selectedSubSystem4);
        BizTranRole bizTranRole5 = BizTranRole.createFrom(406L, selectedBizTranRoleCode5, selectedBizTranRoleName5, selectedSubSystem5.getCode(), null, selectedSubSystem5);
        BizTranRole bizTranRole6 = BizTranRole.createFrom(407L, selectedBizTranRoleCode6, selectedBizTranRoleName6, selectedSubSystem6.getCode(), null, selectedSubSystem6);
        BizTranRole bizTranRole7 = BizTranRole.createFrom(408L, selectedBizTranRoleCode7, selectedBizTranRoleName7, selectedSubSystem7.getCode(), null, selectedSubSystem7);
        List<Operator_BizTranRole> preOperator_BizTranRoleList = newArrayList(
            Operator_BizTranRole.createFrom(501L, selectedOperatorId, bizTranRole0.getBizTranRoleId(), selectedValidThruStartDate0, selectedValidThruEndDate0, null, null, bizTranRole0),
            Operator_BizTranRole.createFrom(502L, selectedOperatorId, bizTranRole1.getBizTranRoleId(), selectedValidThruStartDate1, selectedValidThruEndDate1, null, null, bizTranRole1),
            Operator_BizTranRole.createFrom(503L, selectedOperatorId, bizTranRole2.getBizTranRoleId(), selectedValidThruStartDate2, selectedValidThruEndDate2, null, null, bizTranRole2),
            Operator_BizTranRole.createFrom(504L, selectedOperatorId, bizTranRole3.getBizTranRoleId(), selectedValidThruStartDate3, selectedValidThruEndDate3, null, null, bizTranRole3),
            Operator_BizTranRole.createFrom(505L, selectedOperatorId, bizTranRole4.getBizTranRoleId(), selectedValidThruStartDate4, selectedValidThruEndDate4, null, null, bizTranRole4),
            Operator_BizTranRole.createFrom(506L, selectedOperatorId, bizTranRole5.getBizTranRoleId(), selectedValidThruStartDate5, selectedValidThruEndDate5, null, null, bizTranRole5),
            Operator_BizTranRole.createFrom(507L, selectedOperatorId, bizTranRole6.getBizTranRoleId(), selectedValidThruStartDate6, selectedValidThruEndDate6, null, null, bizTranRole6),
            Operator_BizTranRole.createFrom(508L, selectedOperatorId, bizTranRole7.getBizTranRoleId(), selectedValidThruStartDate7, selectedValidThruEndDate7, null, null, bizTranRole7));

        List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList();
        for (Integer createNo : createNoList) {
            operator_BizTranRoleList.add(preOperator_BizTranRoleList.get(createNo));
        }

        return Operator_BizTranRoles.createFrom(operator_BizTranRoleList);
    }
    // ????????????????????????????????? ??? ??????????????????_??????????????????????????????
    private Operator_BizTranRoles createTargetOperator_BizTranRoles(List<Integer> createNoList) {
        BizTranRole bizTranRole1 = BizTranRole.createFrom(null, targetBizTranRoleCode0, targetBizTranRoleName0, targetSubSystem0.getCode(), null, targetSubSystem0);
        BizTranRole bizTranRole2 = BizTranRole.createFrom(null, targetBizTranRoleCode1, targetBizTranRoleName1, targetSubSystem1.getCode(), null, targetSubSystem1);
        BizTranRole bizTranRole3 = BizTranRole.createFrom(null, targetBizTranRoleCode2, targetBizTranRoleName2, targetSubSystem2.getCode(), null, targetSubSystem2);
        BizTranRole bizTranRole4 = BizTranRole.createFrom(null, targetBizTranRoleCode3, targetBizTranRoleName3, targetSubSystem3.getCode(), null, targetSubSystem3);
        List<Operator_BizTranRole> preOperator_BizTranRoleList = newArrayList(
            Operator_BizTranRole.createFrom(null, null, bizTranRole1.getBizTranRoleId(), targetValidThruStartDate0, targetValidThruEndDate0, null, null, bizTranRole1),
            Operator_BizTranRole.createFrom(null, null, bizTranRole2.getBizTranRoleId(), targetValidThruStartDate1, targetValidThruEndDate1, null, null, bizTranRole2),
            Operator_BizTranRole.createFrom(null, null, bizTranRole3.getBizTranRoleId(), targetValidThruStartDate2, targetValidThruEndDate2, null, null, bizTranRole3),
            Operator_BizTranRole.createFrom(null, null, bizTranRole4.getBizTranRoleId(), targetValidThruStartDate3, targetValidThruEndDate3, null, null, bizTranRole4));

        List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList();
        for (Integer createNo : createNoList) {
            operator_BizTranRoleList.add(preOperator_BizTranRoleList.get(createNo));
        }

        return Operator_BizTranRoles.createFrom(operator_BizTranRoleList);
    }

    /**
     * {@link CopyBizTranRoleGranted#execute(BizTranRoleGrantedCopyRequest request, BizTranRoleGrantedCopyResponse response)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???????????????
     *  ???dto????????????????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test() {
        // ??????????????????????????????
        CopyBizTranRoleGranted copyBizTranRoleGranted = createCopyBizTranRoleGranted();

        // ?????????
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 3, 4, 5));
        selectedOperator_BizTranRoles = createSelectedOperator_BizTranRoles(newArrayList(0, 1, 7));
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(0, 2));
        BizTranRoleGrantedCopyRequest request = createRequest();
        BizTranRoleGrantedCopyResponse response = createResponse();

        // ?????????
        List<BizTranRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : targetOperator_BizTranRoles.getValues()) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
            assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }
        for (Operator_BizTranRole operator_BizTranRole : selectedOperator_BizTranRoles.getValues()) {
            if (operator_BizTranRole.getBizTranRole().getBizTranRoleCode().equals(selectedBizTranRoleCode1)) {
                BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
                assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
                assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
                expectedAssignRoleDtoList.add(assignRoleDto);
            }
        }

        assertThatCode(() ->
            // ??????
            copyBizTranRoleGranted.execute(request, response))
            .doesNotThrowAnyException();

        // ????????????
        assertThat(actualAssignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopyBizTranRoleGranted#searchOperator_SubSystemRoles(Long operatorId)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???Criteria???????????????
     *  ???model???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void searchOperator_SubSystemRoles_test() {
        // ??????????????????????????????
        CopyBizTranRoleGranted copyBizTranRoleGranted = createCopyBizTranRoleGranted();

        // ?????????
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(5, 4, 3, 2, 1, 0));

        // ?????????
        Operator_SubSystemRoleCriteria expectedCriteria = new Operator_SubSystemRoleCriteria();
        expectedCriteria.getOperatorIdCriteria().setEqualTo(signInOperatorId);
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(0, 1, 2, 3, 4, 5));

        // ??????
        Operator_SubSystemRoles operator_SubSystemRoles = copyBizTranRoleGranted.searchOperator_SubSystemRoles(signInOperatorId);

        // ????????????
        assertThat(actualOperator_SubSystemRoleCriteria.toString()).isEqualTo(expectedCriteria.toString());
        assertThat(operator_SubSystemRoles).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles);
    }

    /**
     * {@link CopyBizTranRoleGranted#searchOperator_BizTranRoles(Long operatorId)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???Criteria???????????????
     *  ???model???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void searchOperator_BizTranRoles_test() {
        // ??????????????????????????????
        CopyBizTranRoleGranted copyBizTranRoleGranted = createCopyBizTranRoleGranted();

        // ?????????
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(3, 2, 1, 0));

        // ?????????
        Operator_BizTranRoleCriteria expectedCriteria = new Operator_BizTranRoleCriteria();
        expectedCriteria.getOperatorIdCriteria().setEqualTo(targetOperatorId);
        Operator_BizTranRoles expectedOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(0, 1, 2, 3));

        // ??????
        Operator_BizTranRoles operator_bizTranRoles = copyBizTranRoleGranted.searchOperator_BizTranRoles(targetOperatorId);

        // ????????????
        assertThat(actualOperator_BizTranRoleCriteria.toString()).isEqualTo(expectedCriteria.toString());
        assertThat((Iterable<? extends Operator_BizTranRole>) operator_bizTranRoles).usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoles);
    }

    /**
     * {@link CopyBizTranRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_BizTranRoles selectedOperator_BizTranRoles)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???dto????????????????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test() {
        // ??????????????????????????????
        CopyBizTranRoleGranted copyBizTranRoleGranted = createCopyBizTranRoleGranted();

        // ?????????
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 3, 4, 5));
        selectedOperator_BizTranRoles = createSelectedOperator_BizTranRoles(newArrayList(0, 1, 5, 7));
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(0, 2));
        BizTranRoleGrantedCopyRequest request = createRequest();

        // ?????????
        List<BizTranRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : targetOperator_BizTranRoles.getValues()) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
            assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }
        for (Operator_BizTranRole operator_BizTranRole : selectedOperator_BizTranRoles.getValues()) {
            if (operator_BizTranRole.getBizTranRole().getBizTranRoleCode().equals(selectedBizTranRoleCode1) ||
                operator_BizTranRole.getBizTranRole().getBizTranRoleCode().equals(selectedBizTranRoleCode5)) {
                BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
                assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
                assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
                expectedAssignRoleDtoList.add(assignRoleDto);
            }
        }

        // ??????
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = copyBizTranRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_BizTranRoles);

        // ????????????
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopyBizTranRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_BizTranRoles selectedOperator_BizTranRoles)}?????????
     *  ???????????????
     *    ??????
     *    ????????????????????????????????????????????? ??? ???????????????????????????????????????????????? ??? ?????????????????????????????????????????????????????????
     *
     *  ???????????????
     *  ???dto????????????????????????
     *    ?????????????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test1() {
        // ??????????????????????????????
        CopyBizTranRoleGranted copyBizTranRoleGranted = createCopyBizTranRoleGranted();

        // ?????????
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1));
        selectedOperator_BizTranRoles = createSelectedOperator_BizTranRoles(newArrayList(0));
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(3));
        BizTranRoleGrantedCopyRequest request = createRequest();

        // ?????????
        List<BizTranRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : targetOperator_BizTranRoles.getValues()) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
            assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }
        for (Operator_BizTranRole operator_BizTranRole : selectedOperator_BizTranRoles.getValues()) {
            if (operator_BizTranRole.getBizTranRole().getBizTranRoleCode().equals(selectedBizTranRoleCode0)) {
                BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
                assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
                assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
                expectedAssignRoleDtoList.add(assignRoleDto);
            }
        }

        // ??????
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = copyBizTranRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_BizTranRoles);

        // ????????????
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopyBizTranRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_BizTranRoles selectedOperator_BizTranRoles)}?????????
     *  ???????????????
     *    ??????
     *    ????????????????????????????????????????????????????????? ??? ???????????????????????????????????????????????? ??? ??????????????????????????????????????????????????????
     *
     *  ???????????????
     *  ???dto????????????????????????
     *    ??????????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test2() {
        // ??????????????????????????????
        CopyBizTranRoleGranted copyBizTranRoleGranted = createCopyBizTranRoleGranted();

        // ?????????
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1));
        selectedOperator_BizTranRoles = createSelectedOperator_BizTranRoles(newArrayList(0));
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(0));
        BizTranRoleGrantedCopyRequest request = createRequest();

        // ?????????
        List<BizTranRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : targetOperator_BizTranRoles.getValues()) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
            assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }

        // ??????
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = copyBizTranRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_BizTranRoles);

        // ????????????
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopyBizTranRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_BizTranRoles selectedOperator_BizTranRoles)}?????????
     *  ???????????????
     *    ??????
     *    ????????????????????????????????????????????? ??? ???????????????????????????????????????????????? ??? ?????????????????????????????????????????????????????????
     *
     *  ???????????????
     *  ???dto????????????????????????
     *    ??????????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test3() {
        // ??????????????????????????????
        CopyBizTranRoleGranted copyBizTranRoleGranted = createCopyBizTranRoleGranted();

        // ?????????
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1));
        selectedOperator_BizTranRoles = createSelectedOperator_BizTranRoles(newArrayList(7));
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(0));
        BizTranRoleGrantedCopyRequest request = createRequest();

        // ?????????
        List<BizTranRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : targetOperator_BizTranRoles.getValues()) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
            assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }

        // ??????
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = copyBizTranRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_BizTranRoles);

        // ????????????
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopyBizTranRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_BizTranRoles selectedOperator_BizTranRoles)}?????????
     *  ???????????????
     *    ??????
     *    ????????????????????????????????????????????? ??? ???????????????????????????????????????????????? ??? ??????????????????????????????????????????????????????
     *
     *  ???????????????
     *  ???dto????????????????????????
     *    ??????????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test4() {
        // ??????????????????????????????
        CopyBizTranRoleGranted copyBizTranRoleGranted = createCopyBizTranRoleGranted();

        // ?????????
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1));
        selectedOperator_BizTranRoles = createSelectedOperator_BizTranRoles(newArrayList(3));
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(3));
        BizTranRoleGrantedCopyRequest request = createRequest();

        // ?????????
        List<BizTranRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : targetOperator_BizTranRoles.getValues()) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
            assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }

        // ??????
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = copyBizTranRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_BizTranRoles);

        // ????????????
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopyBizTranRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_BizTranRoles selectedOperator_BizTranRoles)}?????????
     *  ???????????????
     *    ??????
     *    ?????????????????????
     *
     *  ???????????????
     *  ???dto????????????????????????
     *    ??????????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test5() {
        // ??????????????????????????????
        CopyBizTranRoleGranted copyBizTranRoleGranted = createCopyBizTranRoleGranted();

        // ?????????
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1));
        selectedOperator_BizTranRoles = createSelectedOperator_BizTranRoles(newArrayList(7));
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList());
        BizTranRoleGrantedCopyRequest request = createRequest();

        // ?????????
        List<SubSystemRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();

        // ??????
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = copyBizTranRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_BizTranRoles);

        // ????????????
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopyBizTranRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_BizTranRoles selectedOperator_BizTranRoles)}?????????
     *  ???????????????
     *    ??????
     *    ????????????????????????????????????????????????????????? ??? ?????????
     *
     *  ???????????????
     *  ???dto????????????????????????
     *    ??????????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test6() {
        // ??????????????????????????????
        CopyBizTranRoleGranted copyBizTranRoleGranted = createCopyBizTranRoleGranted();

        // ?????????
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1));
        selectedOperator_BizTranRoles = createSelectedOperator_BizTranRoles(newArrayList());
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(0, 1, 2, 3));
        BizTranRoleGrantedCopyRequest request = createRequest();

        // ?????????
        List<BizTranRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : targetOperator_BizTranRoles.getValues()) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
            assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }

        // ??????
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = copyBizTranRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_BizTranRoles);

        // ????????????
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopyBizTranRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_BizTranRoles selectedOperator_BizTranRoles)}?????????
     *  ???????????????
     *    ??????
     *    ?????????????????????????????????????????????????????????????????? ??? ?????????
     *
     *  ???????????????
     *  ???dto????????????????????????
     *    ??????????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test7() {
        // ??????????????????????????????
        CopyBizTranRoleGranted copyBizTranRoleGranted = createCopyBizTranRoleGranted();

        // ?????????
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList());
        selectedOperator_BizTranRoles = createSelectedOperator_BizTranRoles(newArrayList(0, 1, 2, 3, 4, 5, 6, 7));
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(0, 1, 2, 3));
        BizTranRoleGrantedCopyRequest request = createRequest();

        // ?????????
        List<BizTranRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : targetOperator_BizTranRoles.getValues()) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
            assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }

        // ??????
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = copyBizTranRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_BizTranRoles);

        // ????????????
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopyBizTranRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_BizTranRoles selectedOperator_BizTranRoles)}?????????
     *  ???????????????
     *    ??????
     *    ?????????????????????????????????????????????????????????????????? ??? ?????????
     *
     *  ???????????????
     *  ???dto????????????????????????
     *    ?????????????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test8() {
        // ??????????????????????????????
        CopyBizTranRoleGranted copyBizTranRoleGranted = createCopyBizTranRoleGranted();

        // ?????????
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(0));
        selectedOperator_BizTranRoles = createSelectedOperator_BizTranRoles(newArrayList(0, 1, 2, 3, 4, 5, 6, 7));
        targetOperator_BizTranRoles = createTargetOperator_BizTranRoles(newArrayList(0, 1, 2, 3));
        BizTranRoleGrantedCopyRequest request = createRequest();

        // ?????????
        List<BizTranRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_BizTranRole operator_BizTranRole : targetOperator_BizTranRoles.getValues()) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
            assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }
        for (Operator_BizTranRole operator_BizTranRole : selectedOperator_BizTranRoles.getValues()) {
            if (operator_BizTranRole.getBizTranRole().getBizTranRoleCode().equals(selectedBizTranRoleCode4) ||
                operator_BizTranRole.getBizTranRole().getBizTranRoleCode().equals(selectedBizTranRoleCode5) ||
                operator_BizTranRole.getBizTranRole().getBizTranRoleCode().equals(selectedBizTranRoleCode6) ||
                operator_BizTranRole.getBizTranRole().getBizTranRoleCode().equals(selectedBizTranRoleCode7)) {
                BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
                assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
                assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
                expectedAssignRoleDtoList.add(assignRoleDto);
            }
        }

        // ??????
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = copyBizTranRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_BizTranRoles);

        // ????????????
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }
}