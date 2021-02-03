package net.jagunma.backbone.auth.authmanager.application.queryService.util;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchSubSystemRoleGranted;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SubSystemRoleGrantedQueryUtilTest {

    // 実行既定値
    private Long signInOperatorId = 987654L;

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

    /**
     * {@link SubSystemRoleGrantedQueryUtil#judgeIsModifiable(String subSystemRoleCode, Operator_SubSystemRoles signInOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *    （サインインオペレーター が JA管理者ロール を持っていて 本日時点で有効）
     *
     *  ●検証事項
     *  ・判定結果
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void judgeIsModifiable_test1() {
        // テスト対象クラス生成
        SubSystemRoleGrantedQueryUtil subSystemRoleGrantedQueryUtil = new SubSystemRoleGrantedQueryUtil();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(0, 6));

        // 実行
        Boolean isModifiable = subSystemRoleGrantedQueryUtil.judgeIsModifiable(SubSystemRole.業務統括者_購買.getCode(), signInOperator_SubSystemRoles);

        // 結果検証
        assertThat(isModifiable).isEqualTo(true);
    }

    /**
     * {@link SubSystemRoleGrantedQueryUtil#judgeIsModifiable(String subSystemRoleCode, Operator_SubSystemRoles signInOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *    （サインインオペレーター が JA管理者ロール を持っていて 本日時点で無効）
     *
     *  ●検証事項
     *  ・判定結果
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void judgeIsModifiable_test2() {
        // テスト対象クラス生成
        SubSystemRoleGrantedQueryUtil subSystemRoleGrantedQueryUtil = new SubSystemRoleGrantedQueryUtil();

        // 実行値
        signInValidThruEndDate0 = LocalDate.now().minusDays(1);
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(0, 6));

        // 実行
        Boolean isModifiable = subSystemRoleGrantedQueryUtil.judgeIsModifiable(SubSystemRole.業務統括者_購買.getCode(), signInOperator_SubSystemRoles);

        // 結果検証
        assertThat(isModifiable).isEqualTo(false);
    }

    /**
     * {@link SubSystemRoleGrantedQueryUtil#judgeIsModifiable(String subSystemRoleCode, Operator_SubSystemRoles signInOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *    （サインインオペレーター が 該当ロール を持っていて 本日時点で有効）
     *    （JA管理者ロール は持っていない）
     *
     *  ●検証事項
     *  ・判定結果
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void judgeIsModifiable_test3() {
        // テスト対象クラス生成
        SubSystemRoleGrantedQueryUtil subSystemRoleGrantedQueryUtil = new SubSystemRoleGrantedQueryUtil();

        // 実行値
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 3));

        // 実行
        Boolean isModifiable = subSystemRoleGrantedQueryUtil.judgeIsModifiable(signInOperator_SubSystemRoles.getValues().get(0).getSubSystemRoleCode(), signInOperator_SubSystemRoles);

        // 結果検証
        assertThat(isModifiable).isEqualTo(true);
    }

    /**
     * {@link SubSystemRoleGrantedQueryUtil#judgeIsModifiable(String subSystemRoleCode, Operator_SubSystemRoles signInOperator_SubSystemRoles)}テスト
     *  ●パターン
     *    正常
     *    （サインインオペレーター が 該当ロール を持っていて 本日時点で無効）
     *    （JA管理者ロール は持っていない）
     *
     *  ●検証事項
     *  ・判定結果
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void judgeIsModifiable_test4() {
        // テスト対象クラス生成
        SubSystemRoleGrantedQueryUtil subSystemRoleGrantedQueryUtil = new SubSystemRoleGrantedQueryUtil();

        // 実行値
        signInValidThruEndDate1 = LocalDate.now().minusDays(1);
        signInOperator_SubSystemRoles = createSignInOperator_SubSystemRoles(newArrayList(1, 2, 3));

        // 実行
        Boolean isModifiable = subSystemRoleGrantedQueryUtil.judgeIsModifiable(signInOperator_SubSystemRoles.getValues().get(0).getSubSystemRoleCode(), signInOperator_SubSystemRoles);

        // 結果検証
        assertThat(isModifiable).isEqualTo(false);
    }
}