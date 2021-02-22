package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantCommand.BizTranRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantCommand.BizTranRoleGrantRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GrantBizTranRoleTest {

    // 実行既定値
    private Long operatorId = 123456L;
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
    private BizTranRole targetBizTranRole0 = BizTranRole.createFrom(null, targetBizTranRoleCode0, targetBizTranRoleName0, "KB", null, SubSystem.購買);
    private BizTranRole targetBizTranRole1 = BizTranRole.createFrom(null, targetBizTranRoleCode1, targetBizTranRoleName1, "YS", null, SubSystem.販売_青果);
    private BizTranRole targetBizTranRole2 = BizTranRole.createFrom(null, targetBizTranRoleCode2, targetBizTranRoleName2, "HK", null, SubSystem.販売_米);
    private BizTranRole targetBizTranRole3 = BizTranRole.createFrom(null, targetBizTranRoleCode3, targetBizTranRoleName3, "AN", null, SubSystem.販売_畜産);
    private List<BizTranRoleGrantRequestAssignRole> assignRoleList = newArrayList();
    private String changeCause = "（畜産）取引全般を担当";
    private List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList(
        Operator_BizTranRole.createFrom(null, operatorId, null, targetValidThruStartDate3, targetValidThruEndDate3, null, null, targetBizTranRole3),
        Operator_BizTranRole.createFrom(null, operatorId, null, targetValidThruStartDate2, targetValidThruEndDate2, null, null, targetBizTranRole2),
        Operator_BizTranRole.createFrom(null, operatorId, null, targetValidThruStartDate1, targetValidThruEndDate1, null, null, targetBizTranRole1),
        Operator_BizTranRole.createFrom(null, operatorId, null, targetValidThruStartDate0, targetValidThruEndDate0, null, null, targetBizTranRole0));
    private Operator_BizTranRoles operator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);

    // テスト対象クラス生成
    private GrantBizTranRole createGrantBizTranRole() {

        Operator_BizTranRoleRepositoryForStore operator_BizTranRoleRepositoryForStore = new Operator_BizTranRoleRepositoryForStore() {
            @Override
            public void store(Operator_BizTranRoles operator_BizTranRoles, String changeCause) {
            }
        };

        return new GrantBizTranRole(operator_BizTranRoleRepositoryForStore);
    }
    // Request作成
    private BizTranRoleGrantRequest createRequest() {
        return new BizTranRoleGrantRequest() {
            @Override
            public Long getOperatorId() {
                return operatorId;
            }
            @Override
            public List<BizTranRoleGrantRequestAssignRole> getAssignRoleList() {
                return assignRoleList;
            }
            @Override
            public String getChangeCause() {
                return changeCause;
            }
        };
    }
    // アサインロールリスト作成
    private List<BizTranRoleGrantRequestAssignRole> createAssignRoleList() {
        List<BizTranRole> bizTranRoleList = newArrayList(targetBizTranRole3, targetBizTranRole2, targetBizTranRole1, targetBizTranRole0);
        List<LocalDate> validThruStartDateList = newArrayList(targetValidThruStartDate3, targetValidThruStartDate2, targetValidThruStartDate1, targetValidThruStartDate0);
        List<LocalDate> validThruEndDateList = newArrayList(targetValidThruEndDate3, targetValidThruEndDate2, targetValidThruEndDate1, targetValidThruEndDate0);
        List<BizTranRoleGrantRequestAssignRole> bizTranRoleGrantRequestAssignRoleList = newArrayList();
        for (int i = 0; i < bizTranRoleList.size(); i++) {
            int _i = i;
            BizTranRoleGrantRequestAssignRole assignRole = new BizTranRoleGrantRequestAssignRole() {
                @Override
                public BizTranRole getBizTranRole() {
                    return bizTranRoleList.get(_i);
                }
                @Override
                public LocalDate getValidThruStartDate() {
                    return validThruStartDateList.get(_i);
                }
                @Override
                public LocalDate getValidThruEndDate() {
                    return validThruEndDateList.get(_i);
                }
            };
            bizTranRoleGrantRequestAssignRoleList.add(assignRole);
        }
        return bizTranRoleGrantRequestAssignRoleList;
    }

    /**
     * {@link GrantBizTranRole#execute(BizTranRoleGrantRequest request)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test() {
        // テスト対象クラス生成
        GrantBizTranRole grantBizTranRole = createGrantBizTranRole();

        // 実行値
        assignRoleList = createAssignRoleList();
        BizTranRoleGrantRequest request = createRequest();

        assertThatCode(() ->
            // 実行
            grantBizTranRole.execute(request))
            .doesNotThrowAnyException();
    }

    /**
     * {@link GrantBizTranRole#createOperator_BizTranRoles(BizTranRoleGrantRequest request)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Operator_BizTranRolesへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createOperator_BizTranRoles_test() {
        // テスト対象クラス生成
        GrantBizTranRole grantBizTranRole = createGrantBizTranRole();

        // 実行値
        assignRoleList = createAssignRoleList();
        BizTranRoleGrantRequest request = createRequest();

        // 期待値
        Operator_BizTranRoles expectedOperator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoles.getValues().stream().sorted(Orders.empty().addOrder("bizTranRole.subSystem.displaySortOrder").addOrder("bizTranRole.bizTranRoleCode").toComparator()).collect(Collectors.toList()));

        // 実行
        Operator_BizTranRoles operator_BizTranRoles = grantBizTranRole.createOperator_BizTranRoles(request);

        // 結果検証
        assertThat((Iterable<? extends Operator_BizTranRole>) operator_BizTranRoles).usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoles);
    }
}