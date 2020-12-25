package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleReference.SubSystemSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleReference.SubSystemSearchResponse;
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

class SearchSubSystemRoleTest {

    // 実行既定値
    private Long targetOperatorId = 123456L;
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

    private SubSystemSearchRequest createRequest() {
        return new SubSystemSearchRequest() {
            @Override
            public Long getTargetOperatorId() {
                return targetOperatorId;
            }
            @Override
            public Long getSignInOperatorId() {
                return signInOperatorId;
            }
            @Override
            public LongCriteria getOperatorIdCriteria() {
                LongCriteria criteria = new LongCriteria();
                criteria.getIncludes().addAll(new ArrayList<Long>(Arrays.asList(targetOperatorId, signInOperatorId)));
                return criteria;
            }
        };
    }
    private SubSystemSearchResponse createResponse() {
        return new SubSystemSearchResponse() {
            @Override
            public void setOperator_SubSystemRoles(Operator_SubSystemRoles operator_SubSystemRoles) {
                actualOperator_SubSystemRoles = operator_SubSystemRoles;
            }
        };
    }

    // テスト対象クラス生成
    private SearchSubSystemRole createSearchSubSystemRole() {
        Operator_SubSystemRoleRepository operator_SubSystemRoleRepository = new Operator_SubSystemRoleRepository() {
            @Override
            public Operator_SubSystemRoles selectBy(Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {
                List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList();
                operator_SubSystemRoleList.addAll(targetOperator_SubSystemRoleList);
                operator_SubSystemRoleList.addAll(signInOperator_SubSystemRoleList);
                return Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);
            }
        };

        return new SearchSubSystemRole(operator_SubSystemRoleRepository);
    }

    /**
     * {@link SearchSubSystemRole#execute(SubSystemSearchRequest request, SubSystemSearchResponse response)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *  ・modelへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test() {
        // テスト対象クラス生成
        SearchSubSystemRole searchSubSystemRole = createSearchSubSystemRole();

        // 実行値
        SubSystemSearchRequest request = createRequest();
        SubSystemSearchResponse response = createResponse();

        // 期待値
        List<Operator_SubSystemRole> expectedOperator_SubSystemRoleList = newArrayList();
        expectedOperator_SubSystemRoleList.addAll(targetOperator_SubSystemRoleList);
        expectedOperator_SubSystemRoleList.addAll(signInOperator_SubSystemRoleList);
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(expectedOperator_SubSystemRoleList);

        assertThatCode(() ->
            // 実行
            searchSubSystemRole.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        Orders orders = Orders.empty().addOrder("SubSystemRoleCode");
        assertThat(actualOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList())).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList()));
    }

    /**
     * {@link SearchSubSystemRole#execute(SubSystemSearchRequest request, SubSystemSearchResponse response)}テスト
     *  ●パターン
     *    正常（サインインオペレーターがセットされていない）
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
        SearchSubSystemRole searchSubSystemRole = createSearchSubSystemRole();

        // 実行値
        signInOperatorId = null;
        SubSystemSearchRequest request = createRequest();
        SubSystemSearchResponse response = createResponse();

        // 期待値
        List<Operator_SubSystemRole> expectedOperator_SubSystemRoleList = newArrayList();
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(expectedOperator_SubSystemRoleList);

        assertThatCode(() ->
            // 実行
            searchSubSystemRole.execute(request, response))
            .doesNotThrowAnyException();

        // 結果検証
        Orders orders = Orders.empty().addOrder("SubSystemRoleCode");
        assertThat(actualOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList())).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles.getValues().stream().sorted(orders.toComparator()).collect(Collectors.toList()));
    }

    /**
     * {@link SearchSubSystemRole#searchOperator_SubSystemRoles(SubSystemSearchRequest request)}テスト
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
        SearchSubSystemRole searchSubSystemRole = createSearchSubSystemRole();

        // 実行値
        SubSystemSearchRequest request = createRequest();

        // 期待値
        List<Operator_SubSystemRole> expectedOperator_SubSystemRoleList = newArrayList();
        expectedOperator_SubSystemRoleList.addAll(targetOperator_SubSystemRoleList);
        expectedOperator_SubSystemRoleList.addAll(signInOperator_SubSystemRoleList);
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = Operator_SubSystemRoles.createFrom(expectedOperator_SubSystemRoleList);

        // 実行
        Operator_SubSystemRoles operator_SubSystemRoles = searchSubSystemRole.searchOperator_SubSystemRoles(request);

        // 結果検証
        assertThat(operator_SubSystemRoles).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles);
    }
}