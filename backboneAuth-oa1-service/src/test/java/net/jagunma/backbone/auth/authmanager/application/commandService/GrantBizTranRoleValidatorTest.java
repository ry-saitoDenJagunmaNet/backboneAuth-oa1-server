package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantCommand.BizTranRoleGrantRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantCommand.BizTranRoleGrantRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GrantBizTranRoleValidatorTest {

    // 実行既定値
    private Long operatorId = 123456L;
    private String targetBizTranRoleCode0 = "KBAG01";
    private String targetBizTranRoleCode1 = "YSAG10";
    private String targetBizTranRoleCode2 = "HKAG10";
    private String targetBizTranRoleCode3 = "ANAG01";
    private String targetBizTranRoleName0 = "（購買）購買業務基本";
    private String targetBizTranRoleName1 = "（青果）管理者";
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

    private List<BizTranRoleGrantRequestAssignRole> createAssignRoleList() {
        List<BizTranRole> bizTranRoleList = newArrayList(targetBizTranRole0, targetBizTranRole1, targetBizTranRole2, targetBizTranRole3);
        List<LocalDate> validThruStartDateList = newArrayList(targetValidThruStartDate0, targetValidThruStartDate1, targetValidThruStartDate2, targetValidThruStartDate3);
        List<LocalDate> validThruEndDateList = newArrayList(targetValidThruEndDate0, targetValidThruEndDate1, targetValidThruEndDate2, targetValidThruEndDate3);
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
     * {@link GrantBizTranRoleValidator#validate()}テスト
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
        BizTranRoleGrantRequest request = createRequest();

        assertThatCode(() ->
            // 実行
            GrantBizTranRoleValidator.with(request).validate())
            .doesNotThrowAnyException();
    }

    /**
     * {@link GrantBizTranRoleValidator#validate()}テスト
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
        BizTranRoleGrantRequest request = null;

        assertThatThrownBy(() ->
            // 実行
            GrantBizTranRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13001");
            });
    }

    /**
     * {@link GrantBizTranRoleValidator#validate()}テスト
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
        BizTranRoleGrantRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            GrantBizTranRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("オペレーターID");
            });
    }

    /**
     * {@link GrantBizTranRoleValidator#validate()}テスト
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
        BizTranRoleGrantRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            GrantBizTranRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("変更事由");
            });
    }

    /**
     * {@link GrantBizTranRoleValidator#validate()}テスト
     *  ●パターン
     *    未セットチェック  取引ロール
     *
     *  ●検証事項
     *  ・エラー発生
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void validate_Test04() {
        // 実行値
        targetBizTranRole0 = null;
        assignRoleList = createAssignRoleList();
        BizTranRoleGrantRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            GrantBizTranRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("取引ロール");
            });
    }

    /**
     * {@link GrantBizTranRoleValidator#validate()}テスト
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
        targetValidThruStartDate0 = null;
        assignRoleList = createAssignRoleList();
        BizTranRoleGrantRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            GrantBizTranRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("有効期限（開始日）");
            });
    }

    /**
     * {@link GrantBizTranRoleValidator#validate()}テスト
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
        targetValidThruEndDate0 = null;
        assignRoleList = createAssignRoleList();
        BizTranRoleGrantRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            GrantBizTranRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13002");
                assertThat(e.getArgs()).containsSequence("有効期限（終了日）");
            });
    }

    /**
     * {@link GrantBizTranRoleValidator#validate()}テスト
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
        targetValidThruStartDate0 = LocalDate.of(2020, 10, 30);
        targetValidThruEndDate0 = LocalDate.of(2020, 10, 1);
        assignRoleList = createAssignRoleList();
        BizTranRoleGrantRequest request = createRequest();

        assertThatThrownBy(() ->
            // 実行
            GrantBizTranRoleValidator.with(request).validate())
            .isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
                // 結果検証
                assertThat(e.getMessageCode()).isEqualTo("EOA13008");
                assertThat(e.getArgs()).containsSequence("有効期限");
            });
    }
}