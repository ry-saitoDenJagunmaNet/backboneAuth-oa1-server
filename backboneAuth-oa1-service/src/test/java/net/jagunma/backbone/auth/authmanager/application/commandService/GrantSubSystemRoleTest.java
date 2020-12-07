package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleCommand.AllocateSubSystemRole;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleCommand.SubSystemRoleGrantRequest;
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
    private List<AllocateSubSystemRole> allocateSubSystemRoleList = newArrayList();
    private SubSystemRole subSystemRole1 = SubSystemRole.JA管理者;
    private SubSystemRole subSystemRole2 = SubSystemRole.業務統括者_購買;
    private SubSystemRole subSystemRole3 = SubSystemRole.業務統括者_販売_青果;
    private SubSystemRole subSystemRole4 = SubSystemRole.業務統括者_販売_米;
    private SubSystemRole subSystemRole5 = SubSystemRole.業務統括者_販売_畜産;
    private LocalDate validThruStartDate1 = LocalDate.of(2020, 9, 1);
    private LocalDate validThruStartDate2 = LocalDate.of(2020, 9, 2);
    private LocalDate validThruStartDate3 = LocalDate.of(2020, 9, 3);
    private LocalDate validThruStartDate4 = LocalDate.of(2020, 9, 4);
    private LocalDate validThruStartDate5 = LocalDate.of(2020, 9, 5);
    private LocalDate validThruEndDate1 = LocalDate.of(2020, 9, 21);
    private LocalDate validThruEndDate2 = LocalDate.of(2020, 9, 22);
    private LocalDate validThruEndDate3 = LocalDate.of(2020, 9, 23);
    private LocalDate validThruEndDate4 = LocalDate.of(2020, 9, 24);
    private LocalDate validThruEndDate5 = LocalDate.of(2020, 9, 25);
    private String changeCause = "業務統括者（販売・青果）に昇格";

    private SubSystemRoleGrantRequest createRequest() {
        return new SubSystemRoleGrantRequest() {
            @Override
            public Long getOperatorId() {
                return operatorId;
            }
            @Override
            public List<AllocateSubSystemRole> getAllocateSubSystemRoleList() {
                return allocateSubSystemRoleList;
            }
            @Override
            public String getChangeCause() {
                return changeCause;
            }
        };
    }

    private List<AllocateSubSystemRole> createAllocateSubSystemRoleList() {

        AllocateSubSystemRole allocateSubSystemRole1 = new AllocateSubSystemRole() {
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
        AllocateSubSystemRole allocateSubSystemRole2 = new AllocateSubSystemRole() {
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
        AllocateSubSystemRole allocateSubSystemRole3 = new AllocateSubSystemRole() {
            @Override
            public SubSystemRole getSubSystemRole() {
                return subSystemRole3;
            }
            @Override
            public LocalDate getValidThruStartDate() {
                return validThruStartDate3;
            }
            @Override
            public LocalDate getValidThruEndDate() {
                return validThruEndDate3;
            }
        };
        AllocateSubSystemRole allocateSubSystemRole4 = new AllocateSubSystemRole() {
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
        AllocateSubSystemRole allocateSubSystemRole5 = new AllocateSubSystemRole() {
            @Override
            public SubSystemRole getSubSystemRole() {
                return subSystemRole5;
            }
            @Override
            public LocalDate getValidThruStartDate() {
                return validThruStartDate5;
            }
            @Override
            public LocalDate getValidThruEndDate() {
                return validThruEndDate5;
            }
        };

        return newArrayList(
            allocateSubSystemRole1,
            allocateSubSystemRole2,
            allocateSubSystemRole3,
            allocateSubSystemRole4,
            allocateSubSystemRole5);
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
        allocateSubSystemRoleList = createAllocateSubSystemRoleList();
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
        allocateSubSystemRoleList = createAllocateSubSystemRoleList();
        SubSystemRoleGrantRequest request = createRequest();

        // 期待値
        List<Operator_SubSystemRole> expectedList = newArrayList(
            Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole1.getCode(), validThruStartDate1, validThruEndDate1, null, null, subSystemRole1),
            Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole2.getCode(), validThruStartDate2, validThruEndDate2, null, null, subSystemRole2),
            Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole3.getCode(), validThruStartDate3, validThruEndDate3, null, null, subSystemRole3),
            Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole4.getCode(), validThruStartDate4, validThruEndDate4, null, null, subSystemRole4),
            Operator_SubSystemRole.createFrom(null, operatorId, subSystemRole5.getCode(), validThruStartDate5, validThruEndDate5, null, null, subSystemRole5));

        // 実行
        Operator_SubSystemRoles operator_SubSystemRoles = grantSubSystemRole.createOperator_SubSystemRoles(request);

        // 結果検証
        assertTrue(operator_SubSystemRoles instanceof Operator_SubSystemRoles);
        int i = 0;
        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoles.getValues()) {
            assertThat(operator_SubSystemRole.getOperator_SubSystemRoleId()).isEqualTo(expectedList.get(i).getOperator_SubSystemRoleId());
            assertThat(operator_SubSystemRole.getOperatorId()).isEqualTo(expectedList.get(i).getOperatorId());
            assertThat(operator_SubSystemRole.getSubSystemRoleCode()).isEqualTo(expectedList.get(i).getSubSystemRoleCode());
            assertThat(operator_SubSystemRole.getValidThruStartDate()).isEqualTo(expectedList.get(i).getValidThruStartDate());
            assertThat(operator_SubSystemRole.getValidThruEndDate()).isEqualTo(expectedList.get(i).getValidThruEndDate());
            assertThat(operator_SubSystemRole.getRecordVersion()).isEqualTo(expectedList.get(i).getRecordVersion());
            assertThat(operator_SubSystemRole.getOperator()).isEqualTo(expectedList.get(i).getOperator());
            assertThat(operator_SubSystemRole.getSubSystemRole()).isEqualTo(expectedList.get(i).getSubSystemRole());

            i++;
        }
    }
}