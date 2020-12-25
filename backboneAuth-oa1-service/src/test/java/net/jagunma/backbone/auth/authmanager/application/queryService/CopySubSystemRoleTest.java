package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleReference.SubSystemCopyRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleReference.SubSystemCopyResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CopySubSystemRoleTest {

    // 実行既定値
    private Long targetOperatorId = 123456L;
    private Long selectedOperatorId = 234567L;
    private Long signInOperatorId = 345678L;

    private SubSystemRole targetSubSystemRole2 = SubSystemRole.業務統括者_購買;
    private SubSystemRole targetSubSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private LocalDate targetValidThruStartDate2 = LocalDate.of(2020, 10, 2);
    private LocalDate targetValidThruStartDate4 = LocalDate.of(2020, 10, 4);
    private LocalDate targetValidThruEndDate2 = LocalDate.of(2020, 10, 22);
    private LocalDate targetValidThruEndDate4 = LocalDate.of(2020, 10, 24);
    private List<Operator_SubSystemRole> targetOperator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(1002L, targetOperatorId, targetSubSystemRole2.getCode(), targetValidThruStartDate2, targetValidThruEndDate2, 1092, null, targetSubSystemRole2),
        Operator_SubSystemRole.createFrom(1004L, targetOperatorId, targetSubSystemRole4.getCode(), targetValidThruStartDate4, targetValidThruEndDate4, 1094, null, targetSubSystemRole4));
    private Operator_SubSystemRoles targetOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(targetOperator_SubSystemRoleList);

    private SubSystemRole selectedSubSystemRole1 = SubSystemRole.JA管理者;
    private SubSystemRole selectedSubSystemRole2 = SubSystemRole.業務統括者_購買;
    private SubSystemRole selectedSubSystemRole3 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole selectedSubSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole selectedSubSystemRole5 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate selectedValidThruStartDate1 = LocalDate.of(2020, 11, 1);
    private LocalDate selectedValidThruStartDate2 = LocalDate.of(2020, 11, 2);
    private LocalDate selectedValidThruStartDate3 = LocalDate.of(2020, 11, 3);
    private LocalDate selectedValidThruStartDate4 = LocalDate.of(2020, 11, 4);
    private LocalDate selectedValidThruStartDate5 = LocalDate.of(2020, 11, 5);
    private LocalDate selectedValidThruEndDate1 = LocalDate.of(2020, 11, 21);
    private LocalDate selectedValidThruEndDate2 = LocalDate.of(2020, 11, 22);
    private LocalDate selectedValidThruEndDate3 = LocalDate.of(2020, 11, 23);
    private LocalDate selectedValidThruEndDate4 = LocalDate.of(2020, 11, 24);
    private LocalDate selectedValidThruEndDate5 = LocalDate.of(2020, 11, 25);
    private List<Operator_SubSystemRole> selectedOperator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(1101L, selectedOperatorId, selectedSubSystemRole1.getCode(), selectedValidThruStartDate1, selectedValidThruEndDate1, 1191, null, selectedSubSystemRole1),
        Operator_SubSystemRole.createFrom(1102L, selectedOperatorId, selectedSubSystemRole2.getCode(), selectedValidThruStartDate2, selectedValidThruEndDate2, 1192, null, selectedSubSystemRole2),
        Operator_SubSystemRole.createFrom(1103L, selectedOperatorId, selectedSubSystemRole3.getCode(), selectedValidThruStartDate3, selectedValidThruEndDate3, 1193, null, selectedSubSystemRole3),
        Operator_SubSystemRole.createFrom(1104L, selectedOperatorId, selectedSubSystemRole4.getCode(), selectedValidThruStartDate4, selectedValidThruEndDate4, 1194, null, selectedSubSystemRole4),
        Operator_SubSystemRole.createFrom(1105L, selectedOperatorId, selectedSubSystemRole5.getCode(), selectedValidThruStartDate5, selectedValidThruEndDate5, 1195, null, selectedSubSystemRole5));
    private Operator_SubSystemRoles selectedOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(selectedOperator_SubSystemRoleList);

    private SubSystemRole signInSubSystemRole3 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole signInSubSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole signInSubSystemRole5 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate signInValidThruStartDate3 = LocalDate.of(2020, 12, 3);
    private LocalDate signInValidThruStartDate4 = LocalDate.of(2020, 12, 4);
    private LocalDate signInValidThruStartDate5 = LocalDate.of(2020, 12, 5);
    private LocalDate signInValidThruEndDate3 = LocalDate.of(2020, 12, 23);
    private LocalDate signInValidThruEndDate4 = LocalDate.of(2020, 12, 24);
    private LocalDate signInValidThruEndDate5 = LocalDate.of(2020, 12, 25);
    private List<Operator_SubSystemRole> signInOperator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(1203L, signInOperatorId, signInSubSystemRole3.getCode(), signInValidThruStartDate3, signInValidThruEndDate3, 1293, null, signInSubSystemRole3),
        Operator_SubSystemRole.createFrom(1204L, signInOperatorId, signInSubSystemRole4.getCode(), signInValidThruStartDate4, signInValidThruEndDate4, 1294, null, signInSubSystemRole4),
        Operator_SubSystemRole.createFrom(1205L, signInOperatorId, signInSubSystemRole5.getCode(), signInValidThruStartDate5, signInValidThruEndDate5, 1295, null, signInSubSystemRole5));
    private Operator_SubSystemRoles signInOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(signInOperator_SubSystemRoleList);

    // 検証値
    private Operator_SubSystemRoles actualOperator_SubSystemRoles;

    private SubSystemCopyRequest createRequest() {
        return new SubSystemCopyRequest() {
            @Override
            public Long getTargetOperatorId() {
                return targetOperatorId;
            }
            @Override
            public Long getSelectedOperatorId() {
                return selectedOperatorId;
            }
            @Override
            public Long getSignInOperatorId() {
                return signInOperatorId;
            }
            @Override
            public LongCriteria getOperatorIdCriteria() {
                LongCriteria criteria = new LongCriteria();
                criteria.getIncludes().addAll(new ArrayList<Long>(Arrays.asList(targetOperatorId, selectedOperatorId, signInOperatorId)));
                return criteria;
            }
        };
    }
    private SubSystemCopyResponse createResponse() {
        return new SubSystemCopyResponse() {
            @Override
            public void setOperator_SubSystemRoles(Operator_SubSystemRoles operator_SubSystemRoles) {
                actualOperator_SubSystemRoles = operator_SubSystemRoles;
            }
        };
    }

    // テスト対象クラス生成
    private CopySubSystemRole createCopySubSystemRole() {
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

        return new CopySubSystemRole(operator_SubSystemRoleRepository);
    }

    /**
     * {@link CopySubSystemRole#execute(SubSystemCopyRequest request, SubSystemCopyResponse response)}テスト
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
        CopySubSystemRole copySubSystemRole = createCopySubSystemRole();

        // 実行値
        SubSystemCopyRequest request = createRequest();
        SubSystemCopyResponse response = createResponse();

        // 期待値
        List<Operator_SubSystemRole> expectedOperator_SubSystemRoleList = newArrayList();
        expectedOperator_SubSystemRoleList.addAll(targetOperator_SubSystemRoleList);
        expectedOperator_SubSystemRoleList.add(Operator_SubSystemRole.createFrom(1103L, selectedOperatorId, selectedSubSystemRole3.getCode(), selectedValidThruStartDate3, selectedValidThruEndDate3, 1193, null, selectedSubSystemRole3));
        expectedOperator_SubSystemRoleList.add(Operator_SubSystemRole.createFrom(1105L, selectedOperatorId, selectedSubSystemRole5.getCode(), selectedValidThruStartDate5, selectedValidThruEndDate5, 1195, null, selectedSubSystemRole5));
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(expectedOperator_SubSystemRoleList);

        assertThatCode(() ->
            // 実行
            copySubSystemRole.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        Orders orders = Orders.empty().addOrder("SubSystemRoleCode");
        assertThat(actualOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList())).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList()));
    }

    /**
     * {@link CopySubSystemRole#execute(SubSystemCopyRequest request, SubSystemCopyResponse response)}テスト
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
        CopySubSystemRole copySubSystemRole = createCopySubSystemRole();

        // 実行値
        selectedOperatorId = null;
        SubSystemCopyRequest request = createRequest();
        SubSystemCopyResponse response = createResponse();

        // 期待値
        List<Operator_SubSystemRole> expectedOperator_SubSystemRoleList = newArrayList();
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(expectedOperator_SubSystemRoleList);

        assertThatCode(() ->
            // 実行
            copySubSystemRole.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        Orders orders = Orders.empty().addOrder("SubSystemRoleCode");
        assertThat(actualOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList())).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList()));
    }

    /**
     * {@link CopySubSystemRole#searchOperator_SubSystemRoles(SubSystemCopyRequest request)}テスト
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
        CopySubSystemRole copySubSystemRole = createCopySubSystemRole();

        // 実行値
        SubSystemCopyRequest request = createRequest();

        // 期待値
        List<Operator_SubSystemRole> expectedOperator_SubSystemRoleList = newArrayList();
        expectedOperator_SubSystemRoleList.addAll(targetOperator_SubSystemRoleList);
        expectedOperator_SubSystemRoleList.addAll(selectedOperator_SubSystemRoleList);
        expectedOperator_SubSystemRoleList.addAll(signInOperator_SubSystemRoleList);
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(expectedOperator_SubSystemRoleList);

        // 実行
        Operator_SubSystemRoles operator_SubSystemRoles = copySubSystemRole.searchOperator_SubSystemRoles(request);

        // 結果検証
        assertThat(operator_SubSystemRoles).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles);
    }

    /**
     * {@link CopySubSystemRole#copyAdditionalOperator_SubSystemRoles(SubSystemCopyRequest request, Operator_SubSystemRoles operator_SubSystemRoles)}テスト
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
        CopySubSystemRole copySubSystemRole = createCopySubSystemRole();

        // 実行値
        SubSystemCopyRequest request = createRequest();
        List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList();
        operator_SubSystemRoleList.addAll(targetOperator_SubSystemRoleList);
        operator_SubSystemRoleList.addAll(selectedOperator_SubSystemRoleList);
        operator_SubSystemRoleList.addAll(signInOperator_SubSystemRoleList);
        Operator_SubSystemRoles operator_SubSystemRoles = Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);

        // 期待値
        List<Operator_SubSystemRole> expectedOperator_SubSystemRoleList = newArrayList();
        expectedOperator_SubSystemRoleList.addAll(targetOperator_SubSystemRoleList);
        expectedOperator_SubSystemRoleList.add(Operator_SubSystemRole.createFrom(1103L, selectedOperatorId, selectedSubSystemRole3.getCode(), selectedValidThruStartDate3, selectedValidThruEndDate3, 1193, null, selectedSubSystemRole3));
        expectedOperator_SubSystemRoleList.add(Operator_SubSystemRole.createFrom(1105L, selectedOperatorId, selectedSubSystemRole5.getCode(), selectedValidThruStartDate5, selectedValidThruEndDate5, 1195, null, selectedSubSystemRole5));
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(expectedOperator_SubSystemRoleList);

        // 実行
        Operator_SubSystemRoles additionalOperator_SubSystemRoles = copySubSystemRole.copyAdditionalOperator_SubSystemRoles(request, operator_SubSystemRoles);

        // 結果検証
        Orders orders = Orders.empty().addOrder("SubSystemRoleCode");
        assertThat(additionalOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList())).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList()));
    }
}