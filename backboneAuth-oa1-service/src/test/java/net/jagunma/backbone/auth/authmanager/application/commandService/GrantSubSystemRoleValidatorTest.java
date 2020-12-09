package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleCommand.SubSystemRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleCommand.SubSystemRoleGrantRequestAllocateSubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GrantSubSystemRoleValidatorTest {

    // 実行既定値
    private Long operatorId = 123456L;
    private List<SubSystemRoleGrantRequestAllocateSubSystemRole> allocateSubSystemRoleList = newArrayList();
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
            public List<SubSystemRoleGrantRequestAllocateSubSystemRole> getAllocateSubSystemRoleList() {
                return allocateSubSystemRoleList;
            }
            @Override
            public String getChangeCause() {
                return changeCause;
            }
        };
    }

    private List<SubSystemRoleGrantRequestAllocateSubSystemRole> createAllocateSubSystemRoleList() {

        SubSystemRoleGrantRequestAllocateSubSystemRole allocateSubSystemRole1 = new SubSystemRoleGrantRequestAllocateSubSystemRole() {
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
        SubSystemRoleGrantRequestAllocateSubSystemRole allocateSubSystemRole2 = new SubSystemRoleGrantRequestAllocateSubSystemRole() {
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
        SubSystemRoleGrantRequestAllocateSubSystemRole allocateSubSystemRole3 = new SubSystemRoleGrantRequestAllocateSubSystemRole() {
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
        SubSystemRoleGrantRequestAllocateSubSystemRole allocateSubSystemRole4 = new SubSystemRoleGrantRequestAllocateSubSystemRole() {
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
        SubSystemRoleGrantRequestAllocateSubSystemRole allocateSubSystemRole5 = new SubSystemRoleGrantRequestAllocateSubSystemRole() {
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

        List<SubSystemRoleGrantRequestAllocateSubSystemRole> list = newArrayList();
        list.add(allocateSubSystemRole1);
        list.add(allocateSubSystemRole2);
        list.add(allocateSubSystemRole3);
        list.add(allocateSubSystemRole4);
        list.add(allocateSubSystemRole5);

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
        allocateSubSystemRoleList = createAllocateSubSystemRoleList();
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
        allocateSubSystemRoleList = createAllocateSubSystemRoleList();
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
     *    未セットチェック  割当対象サブシステムロールリスト
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test03() {
        // 実行値
        allocateSubSystemRoleList = null;
        SubSystemRoleGrantRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            GrantSubSystemRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("割当対象サブシステムロールリスト");
            });
    }

    /**
     * {@link GrantSubSystemRoleValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  割当対象サブシステムロールリスト
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test04() {
        // 実行値
        allocateSubSystemRoleList = newArrayList();
        SubSystemRoleGrantRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            GrantSubSystemRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("割当対象サブシステムロールリスト");
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
    void validate_Test05() {
        // 実行値
        changeCause = null;
        allocateSubSystemRoleList = createAllocateSubSystemRoleList();
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
    void validate_Test06() {
        // 実行値
        subSystemRole1 = null;
        allocateSubSystemRoleList = createAllocateSubSystemRoleList();
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
    void validate_Test07() {
        // 実行値
        validThruStartDate2 = null;
        allocateSubSystemRoleList = createAllocateSubSystemRoleList();
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
    void validate_Test08() {
        // 実行値
        validThruEndDate3 = null;
        allocateSubSystemRoleList = createAllocateSubSystemRoleList();
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
    void validate_Test09() {
        // 実行値
        validThruStartDate4 = LocalDate.of(2020, 10, 30);
        validThruEndDate4 = LocalDate.of(2020, 10, 1);
        allocateSubSystemRoleList = createAllocateSubSystemRoleList();
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