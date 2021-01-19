package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantCommand.SubSystemRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantCommand.SubSystemRoleGrantRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GrantSubSystemRoleTest {

    // 実行既定値
    private Long operatorId = 123456L;
    private List<SubSystemRoleGrantRequestAssignRole> assignRoleList = newArrayList();
    private SubSystemRole subSystemRole0 = SubSystemRole.JA管理者;
    private SubSystemRole subSystemRole1 = SubSystemRole.業務統括者_購買;
    private SubSystemRole subSystemRole2 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole subSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole subSystemRole6 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate validThruStartDate0 = LocalDate.of(2020, 9, 10);
    private LocalDate validThruStartDate1 = LocalDate.of(2020, 9, 11);
    private LocalDate validThruStartDate2 = LocalDate.of(2020, 9, 12);
    private LocalDate validThruStartDate4 = LocalDate.of(2020, 9, 14);
    private LocalDate validThruStartDate6 = LocalDate.of(2020, 9, 16);
    private LocalDate validThruEndDate0 = LocalDate.of(2020, 9, 20);
    private LocalDate validThruEndDate1 = LocalDate.of(2020, 9, 21);
    private LocalDate validThruEndDate2 = LocalDate.of(2020, 9, 22);
    private LocalDate validThruEndDate4 = LocalDate.of(2020, 9, 24);
    private LocalDate validThruEndDate6 = LocalDate.of(2020, 9, 26);
    private String changeCause = "業務統括者（販売・花卉）も兼務";
    List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole0.getCode(), validThruStartDate0, validThruEndDate0, null, null, subSystemRole0),
        Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole1.getCode(), validThruStartDate1, validThruEndDate1, null, null, subSystemRole1),
        Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole2.getCode(), validThruStartDate2, validThruEndDate2, null, null, subSystemRole2),
        Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole4.getCode(), validThruStartDate4, validThruEndDate4, null, null, subSystemRole4),
        Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole6.getCode(), validThruStartDate6, validThruEndDate6, null, null, subSystemRole6));
    Operator_SubSystemRoles operator_SubSystemRoles = Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);

    private SubSystemRoleGrantRequest createRequest() {
        return new SubSystemRoleGrantRequest() {
            @Override
            public Long getOperatorId() {
                return operatorId;
            }
            @Override
            public List<SubSystemRoleGrantRequestAssignRole> getAssignRoleList() {
                return assignRoleList;
            }
            @Override
            public String getChangeCause() {
                return changeCause;
            }
        };
    }

    private List<SubSystemRoleGrantRequestAssignRole> createAssignRoleList() {

        SubSystemRoleGrantRequestAssignRole assignRole0 = new SubSystemRoleGrantRequestAssignRole() {
            @Override
            public SubSystemRole getSubSystemRole() {
                return subSystemRole0;
            }
            @Override
            public LocalDate getValidThruStartDate() {
                return validThruStartDate0;
            }
            @Override
            public LocalDate getValidThruEndDate() {
                return validThruEndDate0;
            }
        };
        SubSystemRoleGrantRequestAssignRole assignRole1 = new SubSystemRoleGrantRequestAssignRole() {
            @Override
            public SubSystemRole getSubSystemRole() {
                return subSystemRole1;
            }
            @Override
            public LocalDate getValidThruStartDate() {
                return validThruStartDate1;
            }
            @Override
            public LocalDate getValidThruEndDate() {
                return validThruEndDate1;
            }
        };
        SubSystemRoleGrantRequestAssignRole assignRole2 = new SubSystemRoleGrantRequestAssignRole() {
            @Override
            public SubSystemRole getSubSystemRole() {
                return subSystemRole2;
            }
            @Override
            public LocalDate getValidThruStartDate() {
                return validThruStartDate2;
            }
            @Override
            public LocalDate getValidThruEndDate() {
                return validThruEndDate2;
            }
        };
        SubSystemRoleGrantRequestAssignRole assignRole4 = new SubSystemRoleGrantRequestAssignRole() {
            @Override
            public SubSystemRole getSubSystemRole() {
                return subSystemRole4;
            }
            @Override
            public LocalDate getValidThruStartDate() {
                return validThruStartDate4;
            }
            @Override
            public LocalDate getValidThruEndDate() {
                return validThruEndDate4;
            }
        };
        SubSystemRoleGrantRequestAssignRole assignRole6 = new SubSystemRoleGrantRequestAssignRole() {
            @Override
            public SubSystemRole getSubSystemRole() {
                return subSystemRole6;
            }
            @Override
            public LocalDate getValidThruStartDate() {
                return validThruStartDate6;
            }
            @Override
            public LocalDate getValidThruEndDate() {
                return validThruEndDate6;
            }
        };

        return newArrayList(
            assignRole0,
            assignRole1,
            assignRole2,
            assignRole4,
            assignRole6);
    }

    // テスト対象クラス生成
    private GrantSubSystemRole createGrantSubSystemRole() {

        Operator_SubSystemRoleRepositoryForStore operator_SubSystemRoleRepositoryForStore = new Operator_SubSystemRoleRepositoryForStore() {
            @Override
            public void store(Operator_SubSystemRoles operator_SubSystemRoles, String changeCause) {
            }
        };

        return new GrantSubSystemRole(operator_SubSystemRoleRepositoryForStore);
    }

    /**
     * {@link GrantSubSystemRole#execute(SubSystemRoleGrantRequest request)}テスト
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
        GrantSubSystemRole grantSubSystemRole = createGrantSubSystemRole();

        // 実行値
        assignRoleList = createAssignRoleList();
        SubSystemRoleGrantRequest request = createRequest();

        assertThatCode(() ->
            // 実行
            grantSubSystemRole.execute(request))
            .doesNotThrowAnyException();
    }

    /**
     * {@link GrantSubSystemRole#createOperator_SubSystemRoles(SubSystemRoleGrantRequest request)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Operator_SubSystemRolesへのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void createOperator_SubSystemRoles_test() {
        // テスト対象クラス生成
        GrantSubSystemRole grantSubSystemRole = createGrantSubSystemRole();

        // 実行値
        assignRoleList = createAssignRoleList();
        SubSystemRoleGrantRequest request = createRequest();

        // 期待値
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = operator_SubSystemRoles;

        // 実行
        Operator_SubSystemRoles operator_SubSystemRoles = grantSubSystemRole.createOperator_SubSystemRoles(request);

        // 結果検証
        assertThat(operator_SubSystemRoles).usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles);
    }
}