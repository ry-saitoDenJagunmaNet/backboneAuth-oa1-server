package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.util.SubSystemRoleGrantedQueryUtil;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CopySubSystemRoleGrantedTest {

    // 実行既定値
    private final SubSystemRoleGrantedQueryUtil subSystemRoleGrantedQueryUtil = new SubSystemRoleGrantedQueryUtil();
    private Long signInOperatorId = 987654L;
    private Long selectedOperatorId = 876543L;

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

    // オペレーター_サブシステムロール割当系（選択オペレーター）
    private SubSystemRole selectedSubSystemRole0 = SubSystemRole.JA管理者;
    private SubSystemRole selectedSubSystemRole1 = SubSystemRole.業務統括者_購買;
    private SubSystemRole selectedSubSystemRole2 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole selectedSubSystemRole3 = SubSystemRole.業務統括者_販売_花卉;
    private SubSystemRole selectedSubSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole selectedSubSystemRole5 = SubSystemRole.業務統括者_販売_麦;
    private SubSystemRole selectedSubSystemRole6 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate selectedValidThruStartDate0 = LocalDate.of(2020, 11, 10);
    private LocalDate selectedValidThruStartDate1 = LocalDate.of(2020, 11, 11);
    private LocalDate selectedValidThruStartDate2 = LocalDate.of(2020, 11, 12);
    private LocalDate selectedValidThruStartDate3 = LocalDate.of(2020, 11, 13);
    private LocalDate selectedValidThruStartDate4 = LocalDate.of(2020, 11, 14);
    private LocalDate selectedValidThruStartDate5 = LocalDate.of(2020, 11, 15);
    private LocalDate selectedValidThruStartDate6 = LocalDate.of(2020, 11, 16);
    private LocalDate selectedValidThruEndDate0 = LocalDate.of(2020, 11, 20);
    private LocalDate selectedValidThruEndDate1 = LocalDate.of(2020, 11, 21);
    private LocalDate selectedValidThruEndDate2 = LocalDate.of(2020, 11, 22);
    private LocalDate selectedValidThruEndDate3 = LocalDate.of(2020, 11, 23);
    private LocalDate selectedValidThruEndDate4 = LocalDate.of(2020, 11, 24);
    private LocalDate selectedValidThruEndDate5 = LocalDate.of(2020, 11, 25);
    private LocalDate selectedValidThruEndDate6 = LocalDate.of(2020, 11, 26);
    private Operator_SubSystemRoles selectedOperator_SubSystemRoles;

    // オペレーター_サブシステムロール割当系（ターゲットオペレーター）
    private SubSystemRole targetSubSystemRole0 = SubSystemRole.JA管理者;
    private SubSystemRole targetSubSystemRole1 = SubSystemRole.業務統括者_購買;
    private SubSystemRole targetSubSystemRole2 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole targetSubSystemRole3 = SubSystemRole.業務統括者_販売_花卉;
    private SubSystemRole targetSubSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole targetSubSystemRole5 = SubSystemRole.業務統括者_販売_麦;
    private SubSystemRole targetSubSystemRole6 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate targetValidThruStartDate0 = LocalDate.of(2020, 10, 10);
    private LocalDate targetValidThruStartDate1 = LocalDate.of(2020, 10, 11);
    private LocalDate targetValidThruStartDate2 = LocalDate.of(2020, 10, 12);
    private LocalDate targetValidThruStartDate3 = LocalDate.of(2020, 10, 13);
    private LocalDate targetValidThruStartDate4 = LocalDate.of(2020, 10, 14);
    private LocalDate targetValidThruStartDate5 = LocalDate.of(2020, 10, 15);
    private LocalDate targetValidThruStartDate6 = LocalDate.of(2020, 10, 16);
    private LocalDate targetValidThruEndDate0 = LocalDate.of(2020, 10, 20);
    private LocalDate targetValidThruEndDate1 = LocalDate.of(2020, 10, 21);
    private LocalDate targetValidThruEndDate2 = LocalDate.of(2020, 10, 22);
    private LocalDate targetValidThruEndDate3 = LocalDate.of(2020, 10, 23);
    private LocalDate targetValidThruEndDate4 = LocalDate.of(2020, 10, 24);
    private LocalDate targetValidThruEndDate5 = LocalDate.of(2020, 10, 25);
    private LocalDate targetValidThruEndDate6 = LocalDate.of(2020, 10, 26);
    private Operator_SubSystemRoles targetOperator_SubSystemRoles;

    // 検証値
    private Operator_SubSystemRoleCriteria actualOperator_SubSystemRoleCriteria;
    private List<SubSystemRoleGrantedAssignRoleDto> actualAssignRoleDtoList;

    // テスト対象クラス生成
    private CopySubSystemRoleGranted createCopySubSystemRoleGranted() {
        Operator_SubSystemRoleRepository operator_SubSystemRoleRepository = new Operator_SubSystemRoleRepository() {
            @Override
            public Operator_SubSystemRoles selectBy(Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {
                actualOperator_SubSystemRoleCriteria = operator_SubSystemRoleCriteria;

                if (operator_SubSystemRoleCriteria.getOperatorIdCriteria().getEqualTo().equals(signInOperatorId)) {
                    return signInOperator_SubSystemRoles;
                }
                if (operator_SubSystemRoleCriteria.getOperatorIdCriteria().getEqualTo().equals(selectedOperatorId)) {
                    return selectedOperator_SubSystemRoles;
                }
                return null;
            }
        };

        return new CopySubSystemRoleGranted(operator_SubSystemRoleRepository);
    }

    // Request作成
    private SubSystemRoleGrantedCopyRequest createRequest() {
        return new SubSystemRoleGrantedCopyRequest() {
            @Override
            public Long getSignInOperatorId() {
                return signInOperatorId;
            }
            @Override
            public Long getSelectedOperatorId() {
                return selectedOperatorId;
            }
            @Override
            public List<SubSystemRoleGrantedCopyRequestAssignRole> getAssignRoleList() {
                List<SubSystemRoleGrantedCopyRequestAssignRole> assignRoleList = newArrayList();
                for (Operator_SubSystemRole operator_SubSystemRole : targetOperator_SubSystemRoles.getValues()) {
                    SubSystemRoleGrantedCopyRequestAssignRole assignRole = new SubSystemRoleGrantedCopyRequestAssignRole() {
                        @Override
                        public SubSystemRole getSubSystemRole() {
                            return operator_SubSystemRole.getSubSystemRole();
                        }
                        @Override
                        public LocalDate getValidThruStartDate() {
                            return operator_SubSystemRole.getValidThruStartDate();
                        }
                        @Override
                        public LocalDate getValidThruEndDate() {
                            return operator_SubSystemRole.getValidThruEndDate();
                        }
                        @Override
                        public Boolean getIsModifiable() {
                            return subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles);
                        }
                    };
                    assignRoleList.add(assignRole);
                }

                return assignRoleList;
            }
        };
    }
    // Response作成
    private SubSystemRoleGrantedCopyResponse createResponse() {
        return new SubSystemRoleGrantedCopyResponse() {
            @Override
            public void setAssignRoleDtoList(List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList) {
                actualAssignRoleDtoList = assignRoleDtoList;
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
    // 選択オペレーター の オペレーター_サブシステムロール割当群作成
    private Operator_SubSystemRoles createSelectedOperator_SubSystemRoles(List<Integer> createNoList) {
        List<Operator_SubSystemRole> preOperator_SubSystemRoleList = newArrayList(
            Operator_SubSystemRole.createFrom(8000L, selectedOperatorId, selectedSubSystemRole0.getCode(), selectedValidThruStartDate0, selectedValidThruEndDate0, 1, null, selectedSubSystemRole0),
            Operator_SubSystemRole.createFrom(8001L, selectedOperatorId, selectedSubSystemRole1.getCode(), selectedValidThruStartDate1, selectedValidThruEndDate1, 1, null, selectedSubSystemRole1),
            Operator_SubSystemRole.createFrom(8002L, selectedOperatorId, selectedSubSystemRole2.getCode(), selectedValidThruStartDate2, selectedValidThruEndDate2, 1, null, selectedSubSystemRole2),
            Operator_SubSystemRole.createFrom(8003L, selectedOperatorId, selectedSubSystemRole3.getCode(), selectedValidThruStartDate3, selectedValidThruEndDate3, 1, null, selectedSubSystemRole3),
            Operator_SubSystemRole.createFrom(8004L, selectedOperatorId, selectedSubSystemRole4.getCode(), selectedValidThruStartDate4, selectedValidThruEndDate4, 1, null, selectedSubSystemRole4),
            Operator_SubSystemRole.createFrom(8005L, selectedOperatorId, selectedSubSystemRole5.getCode(), selectedValidThruStartDate5, selectedValidThruEndDate5, 1, null, selectedSubSystemRole5),
            Operator_SubSystemRole.createFrom(8006L, selectedOperatorId, selectedSubSystemRole6.getCode(), selectedValidThruStartDate6, selectedValidThruEndDate6, 1, null, selectedSubSystemRole6));

        List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList();
        for (Integer createNo : createNoList) {
            operator_SubSystemRoleList.add(preOperator_SubSystemRoleList.get(createNo));
        }

        return Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);
    }
    // ターゲットオペレーター の オペレーター_サブシステムロール割当群作成
    private Operator_SubSystemRoles createTargetOperator_SubSystemRoles(List<Integer> createNoList) {
        List<Operator_SubSystemRole> preOperator_SubSystemRoleList = newArrayList(
            Operator_SubSystemRole.createFrom(null, null, targetSubSystemRole0.getCode(), targetValidThruStartDate0, targetValidThruEndDate0, null, null, targetSubSystemRole0),
            Operator_SubSystemRole.createFrom(null, null, targetSubSystemRole1.getCode(), targetValidThruStartDate1, targetValidThruEndDate1, null, null, targetSubSystemRole1),
            Operator_SubSystemRole.createFrom(null, null, targetSubSystemRole2.getCode(), targetValidThruStartDate2, targetValidThruEndDate2, null, null, targetSubSystemRole2),
            Operator_SubSystemRole.createFrom(null, null, targetSubSystemRole3.getCode(), targetValidThruStartDate3, targetValidThruEndDate3, null, null, targetSubSystemRole3),
            Operator_SubSystemRole.createFrom(null, null, targetSubSystemRole4.getCode(), targetValidThruStartDate4, targetValidThruEndDate4, null, null, targetSubSystemRole4),
            Operator_SubSystemRole.createFrom(null, null, targetSubSystemRole5.getCode(), targetValidThruStartDate5, targetValidThruEndDate5, null, null, targetSubSystemRole5),
            Operator_SubSystemRole.createFrom(null, null, targetSubSystemRole6.getCode(), targetValidThruStartDate6, targetValidThruEndDate6, null, null, targetSubSystemRole6));

        List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList();
        for (Integer createNo : createNoList) {
            operator_SubSystemRoleList.add(preOperator_SubSystemRoleList.get(createNo));
        }

        return Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);
    }

    /**
     * {@link CopySubSystemRoleGranted#execute(SubSystemRoleGrantedCopyRequest request, SubSystemRoleGrantedCopyResponse response)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・dtoリストへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test() {
        // テスト対象クラス生成
        CopySubSystemRoleGranted copySubSystemRoleGranted = createCopySubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 3, 4, 5));
        selectedOperator_SubSystemRoles = createSelectedOperator_SubSystemRoles(newArrayList(4, 5, 6));
        targetOperator_SubSystemRoles = createTargetOperator_SubSystemRoles(newArrayList(1, 4));
        SubSystemRoleGrantedCopyRequest request = createRequest();
        SubSystemRoleGrantedCopyResponse response = createResponse();

        // 期待値
        List<SubSystemRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : targetOperator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }
        for (Operator_SubSystemRole operator_SubSystemRole : selectedOperator_SubSystemRoles.getValues()) {
            if (operator_SubSystemRole.getSubSystemRole().equals(selectedSubSystemRole5)) {
                SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
                assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
                assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
                expectedAssignRoleDtoList.add(assignRoleDto);
            }
        }

        assertThatCode(() ->
            // 実行
            copySubSystemRoleGranted.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        assertThat(actualAssignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopySubSystemRoleGranted#searchOperator_SubSystemRoles(Long operatorId)}テスト
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
        CopySubSystemRoleGranted copySubSystemRoleGranted = createCopySubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(5, 4, 3, 2, 1, 0));

        // 期待値
        Operator_SubSystemRoleCriteria expectedCriteria = new Operator_SubSystemRoleCriteria();
        expectedCriteria.getOperatorIdCriteria().setEqualTo(signInOperatorId);
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(0, 1, 2, 3, 4, 5));

        // 実行
        Operator_SubSystemRoles operator_SubSystemRoles = copySubSystemRoleGranted.searchOperator_SubSystemRoles(signInOperatorId);

        // 結果検証
        assertThat(actualOperator_SubSystemRoleCriteria.toString()).isEqualTo(expectedCriteria.toString());
        assertThat(operator_SubSystemRoles).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles);
    }

    /**
     * {@link CopySubSystemRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles selectedOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test() {
        // テスト対象クラス生成
        CopySubSystemRoleGranted copySubSystemRoleGranted = createCopySubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 3, 4, 5));
        selectedOperator_SubSystemRoles = createSelectedOperator_SubSystemRoles(newArrayList(3, 4, 5, 6));
        targetOperator_SubSystemRoles = createTargetOperator_SubSystemRoles(newArrayList(1, 4));
        SubSystemRoleGrantedCopyRequest request = createRequest();

        // 期待値
        List<SubSystemRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : targetOperator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }
        for (Operator_SubSystemRole operator_SubSystemRole : selectedOperator_SubSystemRoles.getValues()) {
            if (operator_SubSystemRole.getSubSystemRole().equals(selectedSubSystemRole3) ||
                operator_SubSystemRole.getSubSystemRole().equals(selectedSubSystemRole5)) {
                SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
                assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
                assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
                expectedAssignRoleDtoList.add(assignRoleDto);
            }
        }

        // 実行
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = copySubSystemRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_SubSystemRoles);

        // 結果検証
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopySubSystemRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles selectedOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *    （選択オペレーターのサブシステムロール を サインインオペレーターが操作可能 で ターゲットオペレーターは持っていない）
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *    （追加される）
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test1() {
        // テスト対象クラス生成
        CopySubSystemRoleGranted copySubSystemRoleGranted = createCopySubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1));
        selectedOperator_SubSystemRoles = createSelectedOperator_SubSystemRoles(newArrayList(1));
        targetOperator_SubSystemRoles = createTargetOperator_SubSystemRoles(newArrayList(6));
        SubSystemRoleGrantedCopyRequest request = createRequest();

        // 期待値
        List<SubSystemRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : targetOperator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }
        for (Operator_SubSystemRole operator_SubSystemRole : selectedOperator_SubSystemRoles.getValues()) {
            if (operator_SubSystemRole.getSubSystemRole().equals(selectedSubSystemRole1)) {
                SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
                assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
                assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
                expectedAssignRoleDtoList.add(assignRoleDto);
            }
        }

        // 実行
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = copySubSystemRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_SubSystemRoles);

        // 結果検証
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopySubSystemRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles selectedOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *    （選択オペレーターのサブシステムロール を サインインオペレーターが操作可能 で ターゲットオペレーターは持っている）
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *    （元のまま）
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test2() {
        // テスト対象クラス生成
        CopySubSystemRoleGranted copySubSystemRoleGranted = createCopySubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1));
        selectedOperator_SubSystemRoles = createSelectedOperator_SubSystemRoles(newArrayList(1));
        targetOperator_SubSystemRoles = createTargetOperator_SubSystemRoles(newArrayList(1));
        SubSystemRoleGrantedCopyRequest request = createRequest();

        // 期待値
        List<SubSystemRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : targetOperator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }

        // 実行
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = copySubSystemRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_SubSystemRoles);

        // 結果検証
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopySubSystemRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles selectedOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *    （選択オペレーターのサブシステムロール を サインインオペレーターが操作不可 で ターゲットオペレーターは持っていない）
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *    （元のまま）
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test3() {
        // テスト対象クラス生成
        CopySubSystemRoleGranted copySubSystemRoleGranted = createCopySubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1));
        selectedOperator_SubSystemRoles = createSelectedOperator_SubSystemRoles(newArrayList(2));
        targetOperator_SubSystemRoles = createTargetOperator_SubSystemRoles(newArrayList(1));
        SubSystemRoleGrantedCopyRequest request = createRequest();

        // 期待値
        List<SubSystemRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : targetOperator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }

        // 実行
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = copySubSystemRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_SubSystemRoles);

        // 結果検証
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopySubSystemRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles selectedOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *    （選択オペレーターのサブシステムロール を サインインオペレーターが操作不可 で ターゲットオペレーターは持っている）
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *    （元のまま）
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test4() {
        // テスト対象クラス生成
        CopySubSystemRoleGranted copySubSystemRoleGranted = createCopySubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1));
        selectedOperator_SubSystemRoles = createSelectedOperator_SubSystemRoles(newArrayList(2));
        targetOperator_SubSystemRoles = createTargetOperator_SubSystemRoles(newArrayList(2));
        SubSystemRoleGrantedCopyRequest request = createRequest();

        // 期待値
        List<SubSystemRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : targetOperator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }

        // 実行
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = copySubSystemRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_SubSystemRoles);

        // 結果検証
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopySubSystemRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles selectedOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *    （返却が０件）
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *    （元のまま）
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test5() {
        // テスト対象クラス生成
        CopySubSystemRoleGranted copySubSystemRoleGranted = createCopySubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 3));
        selectedOperator_SubSystemRoles = createSelectedOperator_SubSystemRoles(newArrayList(4, 5, 6));
        targetOperator_SubSystemRoles = createTargetOperator_SubSystemRoles(newArrayList());
        SubSystemRoleGrantedCopyRequest request = createRequest();

        // 期待値
        List<SubSystemRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();

        // 実行
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = copySubSystemRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_SubSystemRoles);

        // 結果検証
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopySubSystemRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles selectedOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *    （選択オペレーターのサブシステムロール が ０件）
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *    （元のまま）
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test6() {
        // テスト対象クラス生成
        CopySubSystemRoleGranted copySubSystemRoleGranted = createCopySubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 3));
        selectedOperator_SubSystemRoles = createSelectedOperator_SubSystemRoles(newArrayList());
        targetOperator_SubSystemRoles = createTargetOperator_SubSystemRoles(newArrayList(4, 5, 6));
        SubSystemRoleGrantedCopyRequest request = createRequest();

        // 期待値
        List<SubSystemRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : targetOperator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }

        // 実行
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = copySubSystemRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_SubSystemRoles);

        // 結果検証
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopySubSystemRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles selectedOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *    （サインインオペレーターのサブシステムロール が ０件）
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *    （元のまま）
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test7() {
        // テスト対象クラス生成
        CopySubSystemRoleGranted copySubSystemRoleGranted = createCopySubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList());
        selectedOperator_SubSystemRoles = createSelectedOperator_SubSystemRoles(newArrayList(1, 2, 3));
        targetOperator_SubSystemRoles = createTargetOperator_SubSystemRoles(newArrayList(4, 5, 6));
        SubSystemRoleGrantedCopyRequest request = createRequest();

        // 期待値
        List<SubSystemRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : targetOperator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }

        // 実行
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = copySubSystemRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_SubSystemRoles);

        // 結果検証
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }

    /**
     * {@link CopySubSystemRoleGranted#copyAddAssignRoleDtoList(List currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles selectedOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *    （ターゲットオペレーターのサブシステムロール が ０件）
     *
     *  ●検証事項
     *  ・dtoリストへのセット
     *    （追加される）
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAddAssignRoleDtoList_test8() {
        // テスト対象クラス生成
        CopySubSystemRoleGranted copySubSystemRoleGranted = createCopySubSystemRoleGranted();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(0));
        selectedOperator_SubSystemRoles = createSelectedOperator_SubSystemRoles(newArrayList(1, 2, 3));
        targetOperator_SubSystemRoles = createTargetOperator_SubSystemRoles(newArrayList());
        SubSystemRoleGrantedCopyRequest request = createRequest();

        // 期待値
        List<SubSystemRoleGrantedAssignRoleDto> expectedAssignRoleDtoList = newArrayList();
        for (Operator_SubSystemRole operator_SubSystemRole : selectedOperator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
            expectedAssignRoleDtoList.add(assignRoleDto);
        }

        // 実行
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = copySubSystemRoleGranted.copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_SubSystemRoles);

        // 結果検証
        assertThat(assignRoleDtoList).usingRecursiveComparison().isEqualTo(expectedAssignRoleDtoList);
    }
}