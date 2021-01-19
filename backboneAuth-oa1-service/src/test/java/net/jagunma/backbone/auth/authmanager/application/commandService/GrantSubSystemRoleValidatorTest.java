package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantCommand.SubSystemRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantCommand.SubSystemRoleGrantRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GrantSubSystemRoleValidatorTest {

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

        List<SubSystemRoleGrantRequestAssignRole> list = newArrayList();
        list.add(assignRole0);
        list.add(assignRole1);
        list.add(assignRole2);
        list.add(assignRole4);
        list.add(assignRole6);

        return list;
    }

    /**
     * {@link GrantSubSystemRoleValidator#validate()}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test() {
        // 実行値
        assignRoleList = createAssignRoleList();
        SubSystemRoleGrantRequest request = createRequest();

        assertThatCode(() ->
            // 実行
            GrantSubSystemRoleValidator.with(request).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link GrantSubSystemRoleValidator#validate()}テスト
     *  ●パターン
     *    リクエスト不正チェック
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test01() {
        // 実行値
        SubSystemRoleGrantRequest request = null;

        assertThatThrownBy(() ->
            // 実行
            GrantSubSystemRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link GrantSubSystemRoleValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  オペレーターID
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test02() {
        // 実行値
        operatorId = null;
        assignRoleList = createAssignRoleList();
        SubSystemRoleGrantRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            GrantSubSystemRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("オペレーターID");
            });
    }

    /**
     * {@link GrantSubSystemRoleValidator#validate()}テスト
     *  ●パターン
     *    未セッチェック  変更事由
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test03() {
        // 実行値
        changeCause = null;
        assignRoleList = createAssignRoleList();
        SubSystemRoleGrantRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            GrantSubSystemRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("変更事由");
            });
    }

    /**
     * {@link GrantSubSystemRoleValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  サブシステムロール
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test04() {
        // 実行値
        subSystemRole0 = null;
        assignRoleList = createAssignRoleList();
        SubSystemRoleGrantRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            GrantSubSystemRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("サブシステムロール");
            });
    }

    /**
     * {@link GrantSubSystemRoleValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  有効期限（開始日）
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test05() {
        // 実行値
        validThruStartDate0 = null;
        assignRoleList = createAssignRoleList();
        SubSystemRoleGrantRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            GrantSubSystemRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("有効期限（開始日）");
            });
    }

    /**
     * {@link GrantSubSystemRoleValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  有効期限（終了日）
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test06() {
        // 実行値
        validThruEndDate0 = null;
        assignRoleList = createAssignRoleList();
        SubSystemRoleGrantRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            GrantSubSystemRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("有効期限（終了日）");
            });
    }

    /**
     * {@link GrantSubSystemRoleValidator#validate()}テスト
     *  ●パターン
     *    範囲指定不正チェック  有効期限
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test07() {
        // 実行値
        validThruStartDate0 = LocalDate.of(2020, 10, 30);
        validThruEndDate0 = LocalDate.of(2020, 10, 1);
        assignRoleList = createAssignRoleList();
        SubSystemRoleGrantRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            GrantSubSystemRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("有効期限");
            });
    }
}