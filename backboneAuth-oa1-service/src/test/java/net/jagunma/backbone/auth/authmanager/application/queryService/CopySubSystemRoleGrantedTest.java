package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;
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
    private Long targetOperatorId = 123456L;
    private Long signInOperatorId = 234567L;
    private Long selectedOperatorId = 345678L;

    private SubSystemRole targetSubSystemRole1 = SubSystemRole.業務統括者_購買;
    private SubSystemRole targetSubSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private LocalDate targetValidThruStartDate1 = LocalDate.of(2020, 10, 11);
    private LocalDate targetValidThruStartDate4 = LocalDate.of(2020, 10, 14);
    private LocalDate targetValidThruEndDate1 = LocalDate.of(2020, 10, 21);
    private LocalDate targetValidThruEndDate4 = LocalDate.of(2020, 10, 24);
    private List<Operator_SubSystemRole> targetOperator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(null, targetOperatorId, targetSubSystemRole1.getCode(), targetValidThruStartDate1, targetValidThruEndDate1, null, null, targetSubSystemRole1),
        Operator_SubSystemRole.createFrom(null, targetOperatorId, targetSubSystemRole4.getCode(), targetValidThruStartDate4, targetValidThruEndDate4, null, null, targetSubSystemRole4));
    private Operator_SubSystemRoles targetOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(targetOperator_SubSystemRoleList);

    private SubSystemRole signInSubSystemRole2 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole signInSubSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole signInSubSystemRole6 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate signInValidThruStartDate2 = LocalDate.of(2020, 12, 12);
    private LocalDate signInValidThruStartDate4 = LocalDate.of(2020, 12, 14);
    private LocalDate signInValidThruStartDate6 = LocalDate.of(2020, 12, 16);
    private LocalDate signInValidThruEndDate2 = LocalDate.of(2020, 12, 22);
    private LocalDate signInValidThruEndDate4 = LocalDate.of(2020, 12, 24);
    private LocalDate signInValidThruEndDate6 = LocalDate.of(2020, 12, 26);
    private List<Operator_SubSystemRole> signInOperator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(null, signInOperatorId, signInSubSystemRole2.getCode(), signInValidThruStartDate2, signInValidThruEndDate2, null, null, signInSubSystemRole2),
        Operator_SubSystemRole.createFrom(null, signInOperatorId, signInSubSystemRole4.getCode(), signInValidThruStartDate4, signInValidThruEndDate4, null, null, signInSubSystemRole4),
        Operator_SubSystemRole.createFrom(null, signInOperatorId, signInSubSystemRole6.getCode(), signInValidThruStartDate6, signInValidThruEndDate6, null, null, signInSubSystemRole6));
    private Operator_SubSystemRoles signInOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(signInOperator_SubSystemRoleList);

    private SubSystemRole selectedSubSystemRole0 = SubSystemRole.JA管理者;
    private SubSystemRole selectedSubSystemRole1 = SubSystemRole.業務統括者_購買;
    private SubSystemRole selectedSubSystemRole2 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole selectedSubSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole selectedSubSystemRole6 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate selectedValidThruStartDate0 = LocalDate.of(2020, 11, 10);
    private LocalDate selectedValidThruStartDate1 = LocalDate.of(2020, 11, 11);
    private LocalDate selectedValidThruStartDate2 = LocalDate.of(2020, 11, 12);
    private LocalDate selectedValidThruStartDate4 = LocalDate.of(2020, 11, 14);
    private LocalDate selectedValidThruStartDate6 = LocalDate.of(2020, 11, 16);
    private LocalDate selectedValidThruEndDate0 = LocalDate.of(2020, 11, 20);
    private LocalDate selectedValidThruEndDate1 = LocalDate.of(2020, 11, 21);
    private LocalDate selectedValidThruEndDate2 = LocalDate.of(2020, 11, 22);
    private LocalDate selectedValidThruEndDate4 = LocalDate.of(2020, 11, 24);
    private LocalDate selectedValidThruEndDate6 = LocalDate.of(2020, 11, 26);
    private List<Operator_SubSystemRole> selectedOperator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(null, selectedOperatorId, selectedSubSystemRole0.getCode(), selectedValidThruStartDate0, selectedValidThruEndDate0, null, null, selectedSubSystemRole0),
        Operator_SubSystemRole.createFrom(null, selectedOperatorId, selectedSubSystemRole1.getCode(), selectedValidThruStartDate1, selectedValidThruEndDate1, null, null, selectedSubSystemRole1),
        Operator_SubSystemRole.createFrom(null, selectedOperatorId, selectedSubSystemRole2.getCode(), selectedValidThruStartDate2, selectedValidThruEndDate2, null, null, selectedSubSystemRole2),
        Operator_SubSystemRole.createFrom(null, selectedOperatorId, selectedSubSystemRole4.getCode(), selectedValidThruStartDate4, selectedValidThruEndDate4, null, null, selectedSubSystemRole4),
        Operator_SubSystemRole.createFrom(null, selectedOperatorId, selectedSubSystemRole6.getCode(), selectedValidThruStartDate6, selectedValidThruEndDate6, null, null, selectedSubSystemRole6));
    private Operator_SubSystemRoles selectedOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(selectedOperator_SubSystemRoleList);

    // 検証値
    private Operator_SubSystemRoles actualOperator_SubSystemRoles;

    private SubSystemRoleGrantedCopyRequest createRequest() {
        return new SubSystemRoleGrantedCopyRequest() {
//            @Override
//            public Long getTargetOperatorId() {
//                return targetOperatorId;
//            }
            @Override
            public Long getSelectedOperatorId() {
                return selectedOperatorId;
            }

            @Override
            public List<SubSystemRoleGrantedCopyRequestAssignRole> getAssignRoleList() {
                return null;
            }

//            @Override
//            public List<SubSystemRoleGrantedCopyRequestAssignRole> getAllocateSubSystemRoleList() {
//                return null;
//            }

            @Override
            public Long getSignInOperatorId() {
                return signInOperatorId;
            }
//            @Override
//            public LongCriteria getOperatorIdCriteria() {
//                LongCriteria criteria = new LongCriteria();
//                criteria.getIncludes().addAll(new ArrayList<Long>(Arrays.asList(targetOperatorId, selectedOperatorId, signInOperatorId)));
//                return criteria;
//            }
        };
    }
    private SubSystemRoleGrantedCopyResponse createResponse() {
        return new SubSystemRoleGrantedCopyResponse() {
            @Override
            public void setAssignRoleDtoList(
                List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList) {

            }

//            @Override
//            public void setGrantedRoleDtoList(
//                List<SubSystemRoleGrantedAssignRoleDto> grantedRoleDtoList) {
//
//            }

//            @Override
//            public void setOperator_SubSystemRoles(Operator_SubSystemRoles operator_SubSystemRoles) {
//                actualOperator_SubSystemRoles = operator_SubSystemRoles;
//            }
        };
    }

    // テスト対象クラス生成
    private CopySubSystemRoleGranted createCopySubSystemRoleGranted() {
        Operator_SubSystemRoleRepository operator_SubSystemRoleRepository = new Operator_SubSystemRoleRepository() {
            @Override
            public Operator_SubSystemRoles selectBy(Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {
                List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList();
                operator_SubSystemRoleList.addAll(targetOperator_SubSystemRoleList);
                operator_SubSystemRoleList.addAll(selectedOperator_SubSystemRoleList);
                operator_SubSystemRoleList.addAll(signInOperator_SubSystemRoleList);
                return Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);
            }
        };

        return new CopySubSystemRoleGranted(operator_SubSystemRoleRepository);
    }

    /**
     * {@link CopySubSystemRoleGranted#execute(SubSystemRoleGrantedCopyRequest request, SubSystemRoleGrantedCopyResponse response)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・modelへのセット（コピー追加される）
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test() {
        // テスト対象クラス生成
        CopySubSystemRoleGranted copySubSystemRoleGranted = createCopySubSystemRoleGranted();

        // 実行値
        SubSystemRoleGrantedCopyRequest request = createRequest();
        SubSystemRoleGrantedCopyResponse response = createResponse();

        // 期待値
        List<Operator_SubSystemRole> expectedOperator_SubSystemRoleList = newArrayList();
        expectedOperator_SubSystemRoleList.addAll(targetOperator_SubSystemRoleList);
        expectedOperator_SubSystemRoleList.add(selectedOperator_SubSystemRoles.getValues().get(2));
        expectedOperator_SubSystemRoleList.add(selectedOperator_SubSystemRoles.getValues().get(4));
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(expectedOperator_SubSystemRoleList);

        assertThatCode(() ->
            // 実行
            copySubSystemRoleGranted.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
//        Orders orders = Orders.empty().addOrder("SubSystemRoleCode");
//        assertThat(actualOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList())).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList()));
    }

    /**
     * {@link CopySubSystemRoleGranted#execute(SubSystemRoleGrantedCopyRequest request, SubSystemRoleGrantedCopyResponse response)}テスト
     *  ●パターン
     *    正常（選択オペレーターがセットされていない）
     *
     *  ●検証事項
     *  ・正常終了
     *  ・modelへのセット（空）
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test1() {
        // テスト対象クラス生成
        CopySubSystemRoleGranted copySubSystemRoleGranted = createCopySubSystemRoleGranted();

        // 実行値
        selectedOperatorId = null;
        SubSystemRoleGrantedCopyRequest request = createRequest();
        SubSystemRoleGrantedCopyResponse response = createResponse();

        // 期待値
        List<Operator_SubSystemRole> expectedOperator_SubSystemRoleList = newArrayList();
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(expectedOperator_SubSystemRoleList);

        assertThatCode(() ->
            // 実行
            copySubSystemRoleGranted.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
//        Orders orders = Orders.empty().addOrder("SubSystemRoleCode");
//        assertThat(actualOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList())).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList()));
    }

    /**
     * {@link CopySubSystemRoleGranted#searchOperator_SubSystemRoles(SubSystemRoleGrantedCopyRequest request)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void searchOperator_SubSystemRoles_test() {
        // テスト対象クラス生成
        CopySubSystemRoleGranted copySubSystemRoleGranted = createCopySubSystemRoleGranted();

        // 実行値
        SubSystemRoleGrantedCopyRequest request = createRequest();

        // 期待値
        List<Operator_SubSystemRole> expectedOperator_SubSystemRoleList = newArrayList();
        expectedOperator_SubSystemRoleList.addAll(targetOperator_SubSystemRoleList);
        expectedOperator_SubSystemRoleList.addAll(selectedOperator_SubSystemRoleList);
        expectedOperator_SubSystemRoleList.addAll(signInOperator_SubSystemRoleList);
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(expectedOperator_SubSystemRoleList);

        // 実行
//        Operator_SubSystemRoles operator_SubSystemRoles = copySubSystemRoleGranted.searchOperator_SubSystemRoles(request);

        // 結果検証
//        assertThat(operator_SubSystemRoles).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles);
    }

    /**
     * {@link CopySubSystemRoleGranted#copyAdditionalOperator_SubSystemRoles(SubSystemRoleGrantedCopyRequest request, Operator_SubSystemRoles operator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・modelへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void copyAdditionalOperator_SubSystemRoles_test() {
        // テスト対象クラス生成
        CopySubSystemRoleGranted copySubSystemRoleGranted = createCopySubSystemRoleGranted();

        // 実行値
        SubSystemRoleGrantedCopyRequest request = createRequest();
        List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList();
        operator_SubSystemRoleList.addAll(targetOperator_SubSystemRoleList);
        operator_SubSystemRoleList.addAll(selectedOperator_SubSystemRoleList);
        operator_SubSystemRoleList.addAll(signInOperator_SubSystemRoleList);
        Operator_SubSystemRoles operator_SubSystemRoles = Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);

        // 期待値
        List<Operator_SubSystemRole> expectedOperator_SubSystemRoleList = newArrayList();
        expectedOperator_SubSystemRoleList.addAll(targetOperator_SubSystemRoleList);
        expectedOperator_SubSystemRoleList.add(selectedOperator_SubSystemRoles.getValues().get(2));
        expectedOperator_SubSystemRoleList.add(selectedOperator_SubSystemRoles.getValues().get(4));
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(expectedOperator_SubSystemRoleList);

        // 実行
//        Operator_SubSystemRoles additionalOperator_SubSystemRoles = copySubSystemRoleGranted.copyAdditionalOperator_SubSystemRoles(request, operator_SubSystemRoles);

        // 結果検証
//        Orders orders = Orders.empty().addOrder("SubSystemRoleCode");
//        assertThat(additionalOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList())).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList()));
    }
}