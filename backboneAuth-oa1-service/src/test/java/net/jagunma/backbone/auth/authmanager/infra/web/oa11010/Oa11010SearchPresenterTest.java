package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SearchResponseVo;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11010SearchPresenterTest {

    // 実行既定値
    private Operators getOperators() {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L, "yu001009", "購買業務統括者のオペレーター", "yu001009@aaaa.net", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),false,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(19L, "yu001010", "サブシステムロール、取引ロールが未設定のオペレーター", "yu001010@aaaa.net", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(20L, "yu001011", "サブシステムロールのオペレーター", "yu001011@aaaa.net", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(21L, "yu001012", "サブシステムロールが複数設定のオペレーター", "yu001012@aaaa.net", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(101L, "yu001101", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(102L, "yu001102", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(103L, "yu001103", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(104L, "yu001104", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(105L, "yu001105", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(106L, "yu001106", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(107L, "yu001107", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(108L, "yu001108", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(109L, "yu001109", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(110L, "yu001110", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(111L, "yu001111", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(112L, "yu001112", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(113L, "yu001113", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(114L, "yu001114", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(115L, "yu001115", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(116L, "yu001116", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 0 ,1));
        list.add(Operator.createFrom(117L, "yu001117", "ページネーション確認オペレーター", "", LocalDate.of(2010,8,17), LocalDate.of(9999,12,21),true,6L, "006", 33L, "001", (short) 1 ,1));
        return Operators.createFrom(list);
    }
    private BranchesAtMoment getBranchesAtMoment() {
        List<BranchAtMoment> list = newArrayList();
        list.add(BranchAtMoment.builder()
            .withIdentifier(33L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(
                BranchAttribute.builder()
                    .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("001")).withName("店舗001").build())
            .build());
        list.add(BranchAtMoment.builder()
            .withIdentifier(35L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("003")).withName("店舗003").build())
            .build());
        return BranchesAtMoment.of(list);
    }
    private AccountLocks getAccountLocks() {
        List<AccountLock> list = newArrayList();
        list.add(AccountLock.createFrom(1L,18L, LocalDateTime.of(2020,10,1,8,30,12), (short) 0,0,null));
        list.add(AccountLock.createFrom(2L,19L, LocalDateTime.of(2020,10,1,8,30,12), (short) 1,0,null));
        return AccountLocks.createFrom(list);
    }
    private Operator_SubSystemRoles getOperator_SubSystemRoles() {
        List<Operator_SubSystemRole> list = newArrayList();
        list.add(Operator_SubSystemRole.createFrom(1L,18L,"KbManager", LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),1,null, SubSystemRole.業務統括者_購買));
        list.add(Operator_SubSystemRole.createFrom(2L,21L,"KbManager", LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),1,null, SubSystemRole.業務統括者_購買));
        list.add(Operator_SubSystemRole.createFrom(3L,21L,"KbManager", LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),1,null, SubSystemRole.業務統括者_販売_野菜));
        return Operator_SubSystemRoles.createFrom(list);
    }
    private Operator_BizTranRoles getOperator_BizTranRoles() {
        List<Operator_BizTranRole> list = newArrayList();
        list.add(Operator_BizTranRole.createFrom(1L,18L,1L,LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),null, getBizTranRole(1L)));
        list.add(Operator_BizTranRole.createFrom(2L,18L,2L,LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),null, getBizTranRole(4L)));
        list.add(Operator_BizTranRole.createFrom(3L,20L,2L,LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),null, getBizTranRole(2L)));
        list.add(Operator_BizTranRole.createFrom(4L,20L,3L,LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),null, getBizTranRole(3L)));
        list.add(Operator_BizTranRole.createFrom(5L,21L,1L,LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),null, getBizTranRole(3L)));
        list.add(Operator_BizTranRole.createFrom(6L,21L,2L,LocalDate.of(2020,1,1), null,null, getBizTranRole(3L)));
        return Operator_BizTranRoles.createFrom(list);
    }
    private BizTranRole getBizTranRole(Long id) {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(1L, "KB0000", "購買メインメニュー", "KB", SubSystem.購買));
        list.add(BizTranRole.createFrom(2L, "KB00001", "支所検索", "KB", SubSystem.購買));
        list.add(BizTranRole.createFrom(3L, "KB0002", "顧客検索", "KB", SubSystem.購買));
        list.add(BizTranRole.createFrom(4L, "YS0000", "野菜メインメニュー", "YS", SubSystem.販売_青果));
        return list.stream().filter(b->b.getBizTranRoleId().equals(id)).findFirst().orElse(null);
    }

    /**
     * {@link Oa11010SearchPresenter}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test0() {

        // 実行既定値
        int pageNo = 1;
        Operators operators = getOperators();
        BranchesAtMoment branchesAtMoment = getBranchesAtMoment();
        AccountLocks accountLocks = getAccountLocks();
        Operator_SubSystemRoles operator_SubSystemRoles = getOperator_SubSystemRoles();
        Operator_BizTranRoles operator_BizTranRoles = getOperator_BizTranRoles();

        // 実行値
        Oa11010SearchResponseVo vo = new Oa11010SearchResponseVo();
        Oa11010SearchPresenter presenter = new Oa11010SearchPresenter();
        presenter.setPageNo(pageNo);
        presenter.setOperators(operators);
        presenter.setBranchesAtMoment(branchesAtMoment);
        presenter.setAccountLocks(accountLocks);
        presenter.setOperator_SubSystemRoles(operator_SubSystemRoles);
        presenter.setOperator_BizTranRoles(operator_BizTranRoles);

        // 期待値
        Oa11010SearchResponseVo expectedVo = new Oa11010SearchResponseVo();
        expectedVo.setOperatorTable(
            "<tr class=\"oaex_operator_table_operator_yu001009 oaex_th_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\">001</td><td class=\"oaex_operator_branch_name\">店舗001</td><td class=\"oaex_operator_operator_code\">yu001009<input type=\"hidden\" value=\"33\"/></td><td class=\"oaex_operator_operator_name\">購買業務統括者のオペレーター</td><td class=\"oaex_operator_expiration_date\">2010/08/17～9999/12/21</td><td class=\"oaex_operator_subsystem_role\">業務統括者（購買）</td><td class=\"oaex_operator_subsystem_role_expiration_date\">2020/01/01～9999/12/31</td><td class=\"oaex_operator_biztran_role_code\">KB0000</td><td class=\"oaex_operator_biztran_role_name\">購買メインメニュー</td><td class=\"oaex_operator_biztran_role_expiration_date\">2020/01/01～9999/12/31</tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001009\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"></td><td class=\"oaex_operator_account_lock\"></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\"></td><td class=\"oaex_operator_operator_name\"></td><td class=\"oaex_operator_expiration_date\"></td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_expiration_date\"></td><td class=\"oaex_operator_biztran_role_code\">YS0000</td><td class=\"oaex_operator_biztran_role_name\">野菜メインメニュー</td><td class=\"oaex_operator_biztran_role_expiration_date\">2020/01/01～9999/12/31</tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001010 oaex_th_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_lock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001010<input type=\"hidden\" value=\"33\"/></td><td class=\"oaex_operator_operator_name\">サブシステムロール、取引ロールが未設定のオペレーター</td><td class=\"oaex_operator_expiration_date\">2010/08/17～9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_expiration_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_expiration_date\"></td></tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001011 oaex_th_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001011<input type=\"hidden\" value=\"33\"/></td><td class=\"oaex_operator_operator_name\">サブシステムロールのオペレーター</td><td class=\"oaex_operator_expiration_date\">2010/08/17～9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_expiration_date\"></td><td class=\"oaex_operator_biztran_role_code\">KB00001</td><td class=\"oaex_operator_biztran_role_name\">支所検索</td><td class=\"oaex_operator_biztran_role_expiration_date\">2020/01/01～9999/12/31</tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001011\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"></td><td class=\"oaex_operator_account_lock\"></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\"></td><td class=\"oaex_operator_operator_name\"></td><td class=\"oaex_operator_expiration_date\"></td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_expiration_date\"></td><td class=\"oaex_operator_biztran_role_code\">KB0002</td><td class=\"oaex_operator_biztran_role_name\">顧客検索</td><td class=\"oaex_operator_biztran_role_expiration_date\">2020/01/01～9999/12/31</tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001012 oaex_th_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001012<input type=\"hidden\" value=\"33\"/></td><td class=\"oaex_operator_operator_name\">サブシステムロールが複数設定のオペレーター</td><td class=\"oaex_operator_expiration_date\">2010/08/17～9999/12/21</td><td class=\"oaex_operator_subsystem_role\">業務統括者（購買）</td><td class=\"oaex_operator_subsystem_role_expiration_date\">2020/01/01～9999/12/31</td><td class=\"oaex_operator_biztran_role_code\">KB0002</td><td class=\"oaex_operator_biztran_role_name\">顧客検索</td><td class=\"oaex_operator_biztran_role_expiration_date\">2020/01/01～9999/12/31</tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001012\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"></td><td class=\"oaex_operator_account_lock\"></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\"></td><td class=\"oaex_operator_operator_name\"></td><td class=\"oaex_operator_expiration_date\"></td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_expiration_date\"></td><td class=\"oaex_operator_biztran_role_code\">KB0002</td><td class=\"oaex_operator_biztran_role_name\">顧客検索</td><td class=\"oaex_operator_biztran_role_expiration_date\">2020/01/01～</tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001012\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"></td><td class=\"oaex_operator_account_lock\"></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\"></td><td class=\"oaex_operator_operator_name\"></td><td class=\"oaex_operator_expiration_date\"></td><td class=\"oaex_operator_subsystem_role\">業務統括者（販売・野菜）</td><td class=\"oaex_operator_subsystem_role_expiration_date\">2020/01/01～9999/12/31</td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_expiration_date\"></td></tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001101 oaex_th_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001101<input type=\"hidden\" value=\"33\"/></td><td class=\"oaex_operator_operator_name\">ページネーション確認オペレーター</td><td class=\"oaex_operator_expiration_date\">2010/08/17～9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_expiration_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_expiration_date\"></td></tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001102 oaex_th_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001102<input type=\"hidden\" value=\"33\"/></td><td class=\"oaex_operator_operator_name\">ページネーション確認オペレーター</td><td class=\"oaex_operator_expiration_date\">2010/08/17～9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_expiration_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_expiration_date\"></td></tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001103 oaex_th_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001103<input type=\"hidden\" value=\"33\"/></td><td class=\"oaex_operator_operator_name\">ページネーション確認オペレーター</td><td class=\"oaex_operator_expiration_date\">2010/08/17～9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_expiration_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_expiration_date\"></td></tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001104 oaex_th_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001104<input type=\"hidden\" value=\"33\"/></td><td class=\"oaex_operator_operator_name\">ページネーション確認オペレーター</td><td class=\"oaex_operator_expiration_date\">2010/08/17～9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_expiration_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_expiration_date\"></td></tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001105 oaex_th_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001105<input type=\"hidden\" value=\"33\"/></td><td class=\"oaex_operator_operator_name\">ページネーション確認オペレーター</td><td class=\"oaex_operator_expiration_date\">2010/08/17～9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_expiration_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_expiration_date\"></td></tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001106 oaex_th_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001106<input type=\"hidden\" value=\"33\"/></td><td class=\"oaex_operator_operator_name\">ページネーション確認オペレーター</td><td class=\"oaex_operator_expiration_date\">2010/08/17～9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_expiration_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_expiration_date\"></td></tr>"
        );
        expectedVo.setPagination(
            "<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&lt;</a></li>"
                + "<li class=\"active\" th:remove=\"all\"><a href=\"#!\">1</a></li>"
                + "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(2);\">2</a></li>"
                + "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(3);\">3</a></li>"
                + "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(2);\">&gt;</a></li>"
        );

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11010SearchPresenter}のテスト
     *  ●パターン
     *    通常（最終ページ）
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test1() {

        // 実行既定値
        int pageNo = 3;
        Operators operators = getOperators();
        BranchesAtMoment branchesAtMoment = getBranchesAtMoment();
        AccountLocks accountLocks = getAccountLocks();
        Operator_SubSystemRoles operator_SubSystemRoles = getOperator_SubSystemRoles();
        Operator_BizTranRoles operator_BizTranRoles = getOperator_BizTranRoles();

        // 実行値
        Oa11010SearchResponseVo vo = new Oa11010SearchResponseVo();
        Oa11010SearchPresenter presenter = new Oa11010SearchPresenter();
        presenter.setPageNo(pageNo);
        presenter.setOperators(operators);
        presenter.setBranchesAtMoment(branchesAtMoment);
        presenter.setAccountLocks(accountLocks);
        presenter.setOperator_SubSystemRoles(operator_SubSystemRoles);
        presenter.setOperator_BizTranRoles(operator_BizTranRoles);

        // 期待値
        Oa11010SearchResponseVo expectedVo = new Oa11010SearchResponseVo();
        expectedVo.setOperatorTable(
            "<tr class=\"oaex_operator_table_operator_yu001117 oaex_th_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_inpossible\"></div></td>"
                + "<td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td>"
                + "<td class=\"oaex_operator_branch_code\">001</td>"
                + "<td class=\"oaex_operator_branch_name\">店舗001</td>"
                + "<td class=\"oaex_operator_operator_code\">yu001117<input type=\"hidden\" value=\"33\"/></td>"
                + "<td class=\"oaex_operator_operator_name\">ページネーション確認オペレーター</td>"
                + "<td class=\"oaex_operator_expiration_date\">2010/08/17～9999/12/21</td>"
                + "<td class=\"oaex_operator_subsystem_role\"></td>"
                + "<td class=\"oaex_operator_subsystem_role_expiration_date\"></td>"
                + "<td class=\"oaex_operator_biztran_role_code\"></td>"
                + "<td class=\"oaex_operator_biztran_role_name\"></td>"
                + "<td class=\"oaex_operator_biztran_role_expiration_date\"></td>"
                + "</tr>"
        );
        expectedVo.setPagination(
            "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(2);\">&lt;</a></li>"
                + "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(1);\">1</a></li>"
                + "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(2);\">2</a></li>"
                + "<li class=\"active\" th:remove=\"all\"><a href=\"#!\">3</a></li>"
                + "<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&gt;</a></li>"
        );

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}