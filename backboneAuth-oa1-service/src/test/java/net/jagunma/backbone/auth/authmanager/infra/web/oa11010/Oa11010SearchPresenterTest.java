package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SearchResponseVo;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.AccountLockStatus;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11010SearchPresenterTest {

    // ???????????????
    private final Long jaId = 6L;
    private final String jaCode = "006";
    private final String jaName = "JA?????????";
    private final Long branchId = 33L;
    private final String branchCode = "001";
    private final String branchName = "??????001";

    private Operators getOperators() {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","??????????????????????????????????????????","yu001009@aaaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),false,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(19L,"yu001010","??????????????????????????????????????????????????????????????????????????????","yu001010@aaaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(20L,"yu001011","????????????????????????????????????????????????","yu001011@aaaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(21L,"yu001012","???????????????????????????????????????????????????????????????","yu001012@aaaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(101L,"yu001101","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(102L,"yu001102","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(103L,"yu001103","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(104L,"yu001104","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(105L,"yu001105","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(106L,"yu001106","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(107L,"yu001107","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(108L,"yu001108","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(109L,"yu001109","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(110L,"yu001110","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(111L,"yu001111","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(112L,"yu001112","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(113L,"yu001113","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(114L,"yu001114","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(115L,"yu001115","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(116L,"yu001116","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        list.add(Operator.createFrom(117L,"yu001117","????????????????????????????????????????????????","",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),true,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment()));
        return Operators.createFrom(list);
    }
    private BranchesAtMoment getBranchesAtMoment() {
        List<BranchAtMoment> list = newArrayList();
        list.add(BranchAtMoment.builder()
            .withIdentifier(33L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(
                BranchAttribute.builder()
                    .withBranchType(BranchType.??????).withBranchCode(BranchCode.of("001")).withName("??????001").build())
            .build());
        list.add(BranchAtMoment.builder()
            .withIdentifier(35L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.??????).withBranchCode(BranchCode.of("003")).withName("??????003").build())
            .build());
        return BranchesAtMoment.of(list);
    }
    private AccountLocks getAccountLocks() {
        List<AccountLock> list = newArrayList();
        list.add(AccountLock.createFrom(1L,18L, LocalDateTime.of(2020,10,1,8,30,12), AccountLockStatus.???????????????,0,null));
        list.add(AccountLock.createFrom(2L,19L, LocalDateTime.of(2020,10,1,8,30,12), AccountLockStatus.?????????,0,null));
        return AccountLocks.createFrom(list);
    }
    private Operator_SubSystemRoles getOperator_SubSystemRoles() {
        List<Operator_SubSystemRole> list = newArrayList();
        list.add(Operator_SubSystemRole.createFrom(1L,18L,SubSystemRole.???????????????_??????.getCode(), LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),1,null, SubSystemRole.???????????????_??????));
        list.add(Operator_SubSystemRole.createFrom(2L,21L,SubSystemRole.???????????????_??????.getCode(), LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),1,null, SubSystemRole.???????????????_??????));
        list.add(Operator_SubSystemRole.createFrom(3L,21L,SubSystemRole.???????????????_??????_??????.getCode(), LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),1,null, SubSystemRole.???????????????_??????_??????));
        return Operator_SubSystemRoles.createFrom(list);
    }
    private Operator_BizTranRoles getOperator_BizTranRoles() {
        List<Operator_BizTranRole> list = newArrayList();
        list.add(Operator_BizTranRole.createFrom(1L,18L,1L,LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),1,null, getBizTranRole(1L)));
        list.add(Operator_BizTranRole.createFrom(2L,18L,2L,LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),1,null, getBizTranRole(4L)));
        list.add(Operator_BizTranRole.createFrom(3L,20L,2L,LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),1,null, getBizTranRole(2L)));
        list.add(Operator_BizTranRole.createFrom(4L,20L,3L,LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),1,null, getBizTranRole(3L)));
        list.add(Operator_BizTranRole.createFrom(5L,21L,1L,LocalDate.of(2020,1,1), LocalDate.of(9999,12,31),1,null, getBizTranRole(3L)));
        list.add(Operator_BizTranRole.createFrom(6L,21L,2L,LocalDate.of(2020,1,1), null,1,null, getBizTranRole(3L)));
        return Operator_BizTranRoles.createFrom(list);
    }
    private BizTranRole getBizTranRole(Long id) {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(1L, "KB0000", "???????????????????????????", SubSystem.??????.getCode(),1, SubSystem.??????));
        list.add(BizTranRole.createFrom(2L, "KB0001", "????????????", SubSystem.??????.getCode(),1, SubSystem.??????));
        list.add(BizTranRole.createFrom(3L, "KB0002", "????????????", SubSystem.??????.getCode(),1, SubSystem.??????));
        list.add(BizTranRole.createFrom(4L, "YS0000", "???????????????????????????", SubSystem.??????_??????.getCode(),1, SubSystem.??????_??????));
        return list.stream().filter(b->b.getBizTranRoleId().equals(id)).findFirst().orElse(null);
    }
    private BranchAtMoment createBranchAtMoment() {
        return BranchAtMoment.builder()
            .withIdentifier(branchId).withJaAtMoment(createJaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.??????).withBranchCode(BranchCode.of(branchCode)).withName(branchName).build())
            .build();
    }
    private JaAtMoment createJaAtMoment() {
        return JaAtMoment.builder()
            .withIdentifier(jaId)
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(jaCode))
                .withName(jaName)
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
    }

    /**
     * {@link Oa11010SearchPresenter}????????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???Vo???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test0() {

        // ???????????????
        int pageNo = 1;
        Operators operators = getOperators();
        BranchesAtMoment branchesAtMoment = getBranchesAtMoment();
        AccountLocks accountLocks = getAccountLocks();
        Operator_SubSystemRoles operator_SubSystemRoles = getOperator_SubSystemRoles();
        Operator_BizTranRoles operator_BizTranRoles = getOperator_BizTranRoles();

        // ?????????
        Oa11010SearchResponseVo vo = new Oa11010SearchResponseVo();
        Oa11010SearchPresenter presenter = new Oa11010SearchPresenter();
        presenter.setPageNo(pageNo);
        presenter.setOperators(operators);
        presenter.setAccountLocks(accountLocks);
        presenter.setOperator_SubSystemRoles(operator_SubSystemRoles);
        presenter.setOperator_BizTranRoles(operator_BizTranRoles);

        // ?????????
        Oa11010SearchResponseVo expectedVo = new Oa11010SearchResponseVo();
        expectedVo.setOperatorTable(
            "<tr class=\"oaex_operator_table_operator_yu001009 oaex_th_operator_table_row\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\">001</td><td class=\"oaex_operator_branch_name\">??????001</td><td class=\"oaex_operator_operator_code\">yu001009<input type=\"hidden\" value=\"18\"/></td><td class=\"oaex_operator_operator_name\">??????????????????????????????????????????</td><td class=\"oaex_operator_valid_thru_date\">2010/08/17???9999/12/21</td><td class=\"oaex_operator_subsystem_role\">???????????????????????????</td><td class=\"oaex_operator_subsystem_role_valid_thru_date\">2020/01/01???9999/12/31</td><td class=\"oaex_operator_biztran_role_code\">KB0000</td><td class=\"oaex_operator_biztran_role_name\">???????????????????????????</td><td class=\"oaex_operator_biztran_role_valid_thru_date\">2020/01/01???9999/12/31</tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001010 oaex_th_operator_table_row\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_lock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001010<input type=\"hidden\" value=\"19\"/></td><td class=\"oaex_operator_operator_name\">??????????????????????????????????????????????????????????????????????????????</td><td class=\"oaex_operator_valid_thru_date\">2010/08/17???9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_valid_thru_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_valid_thru_date\"></td></tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001011 oaex_th_operator_table_row\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001011<input type=\"hidden\" value=\"20\"/></td><td class=\"oaex_operator_operator_name\">????????????????????????????????????????????????</td><td class=\"oaex_operator_valid_thru_date\">2010/08/17???9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_valid_thru_date\"></td><td class=\"oaex_operator_biztran_role_code\">KB0001</td><td class=\"oaex_operator_biztran_role_name\">????????????</td><td class=\"oaex_operator_biztran_role_valid_thru_date\">2020/01/01???9999/12/31</tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001011\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"></td><td class=\"oaex_operator_account_lock\"></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\"></td><td class=\"oaex_operator_operator_name\"></td><td class=\"oaex_operator_valid_thru_date\"></td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_valid_thru_date\"></td><td class=\"oaex_operator_biztran_role_code\">KB0002</td><td class=\"oaex_operator_biztran_role_name\">????????????</td><td class=\"oaex_operator_biztran_role_valid_thru_date\">2020/01/01???9999/12/31</tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001012 oaex_th_operator_table_row\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001012<input type=\"hidden\" value=\"21\"/></td><td class=\"oaex_operator_operator_name\">???????????????????????????????????????????????????????????????</td><td class=\"oaex_operator_valid_thru_date\">2010/08/17???9999/12/21</td><td class=\"oaex_operator_subsystem_role\">???????????????????????????</td><td class=\"oaex_operator_subsystem_role_valid_thru_date\">2020/01/01???9999/12/31</td><td class=\"oaex_operator_biztran_role_code\">KB0002</td><td class=\"oaex_operator_biztran_role_name\">????????????</td><td class=\"oaex_operator_biztran_role_valid_thru_date\">2020/01/01???9999/12/31</tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001012\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"></td><td class=\"oaex_operator_account_lock\"></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\"></td><td class=\"oaex_operator_operator_name\"></td><td class=\"oaex_operator_valid_thru_date\"></td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_valid_thru_date\"></td><td class=\"oaex_operator_biztran_role_code\">KB0002</td><td class=\"oaex_operator_biztran_role_name\">????????????</td><td class=\"oaex_operator_biztran_role_valid_thru_date\">2020/01/01???</tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001012\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"></td><td class=\"oaex_operator_account_lock\"></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\"></td><td class=\"oaex_operator_operator_name\"></td><td class=\"oaex_operator_valid_thru_date\"></td><td class=\"oaex_operator_subsystem_role\">????????????????????????????????????</td><td class=\"oaex_operator_subsystem_role_valid_thru_date\">2020/01/01???9999/12/31</td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_valid_thru_date\"></td></tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001101 oaex_th_operator_table_row\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001101<input type=\"hidden\" value=\"101\"/></td><td class=\"oaex_operator_operator_name\">????????????????????????????????????????????????</td><td class=\"oaex_operator_valid_thru_date\">2010/08/17???9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_valid_thru_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_valid_thru_date\"></td></tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001102 oaex_th_operator_table_row\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001102<input type=\"hidden\" value=\"102\"/></td><td class=\"oaex_operator_operator_name\">????????????????????????????????????????????????</td><td class=\"oaex_operator_valid_thru_date\">2010/08/17???9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_valid_thru_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_valid_thru_date\"></td></tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001103 oaex_th_operator_table_row\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001103<input type=\"hidden\" value=\"103\"/></td><td class=\"oaex_operator_operator_name\">????????????????????????????????????????????????</td><td class=\"oaex_operator_valid_thru_date\">2010/08/17???9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_valid_thru_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_valid_thru_date\"></td></tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001104 oaex_th_operator_table_row\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001104<input type=\"hidden\" value=\"104\"/></td><td class=\"oaex_operator_operator_name\">????????????????????????????????????????????????</td><td class=\"oaex_operator_valid_thru_date\">2010/08/17???9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_valid_thru_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_valid_thru_date\"></td></tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001105 oaex_th_operator_table_row\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001105<input type=\"hidden\" value=\"105\"/></td><td class=\"oaex_operator_operator_name\">????????????????????????????????????????????????</td><td class=\"oaex_operator_valid_thru_date\">2010/08/17???9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_valid_thru_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_valid_thru_date\"></td></tr>"
                + "<tr class=\"oaex_operator_table_operator_yu001106 oaex_th_operator_table_row\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001106<input type=\"hidden\" value=\"106\"/></td><td class=\"oaex_operator_operator_name\">????????????????????????????????????????????????</td><td class=\"oaex_operator_valid_thru_date\">2010/08/17???9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_valid_thru_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_valid_thru_date\"></td></tr>"
        );
        expectedVo.setPagination(
            "<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&lt;</a></li>"
                + "<li class=\"active\" th:remove=\"all\"><a href=\"#!\">1</a></li>"
                + "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(2);\">2</a></li>"
                + "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(3);\">3</a></li>"
                + "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(2);\">&gt;</a></li>"
        );

        // ??????
        presenter.bindTo(vo);

        // ????????????
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11010SearchPresenter}????????????
     *  ???????????????
     *    ???????????????????????????
     *
     *  ???????????????
     *  ???Vo???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test1() {

        // ???????????????
        int pageNo = 3;
        Operators operators = getOperators();
        BranchesAtMoment branchesAtMoment = getBranchesAtMoment();
        AccountLocks accountLocks = getAccountLocks();
        Operator_SubSystemRoles operator_SubSystemRoles = getOperator_SubSystemRoles();
        Operator_BizTranRoles operator_BizTranRoles = getOperator_BizTranRoles();

        // ?????????
        Oa11010SearchResponseVo vo = new Oa11010SearchResponseVo();
        Oa11010SearchPresenter presenter = new Oa11010SearchPresenter();
        presenter.setPageNo(pageNo);
        presenter.setOperators(operators);
        presenter.setAccountLocks(accountLocks);
        presenter.setOperator_SubSystemRoles(operator_SubSystemRoles);
        presenter.setOperator_BizTranRoles(operator_BizTranRoles);

        // ?????????
        Oa11010SearchResponseVo expectedVo = new Oa11010SearchResponseVo();
        expectedVo.setOperatorTable(
            "<tr class=\"oaex_operator_table_operator_yu001117 oaex_th_operator_table_row\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_inpossible\"></div></td>"
                + "<td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td>"
                + "<td class=\"oaex_operator_branch_code\">001</td>"
                + "<td class=\"oaex_operator_branch_name\">??????001</td>"
                + "<td class=\"oaex_operator_operator_code\">yu001117<input type=\"hidden\" value=\"117\"/></td>"
                + "<td class=\"oaex_operator_operator_name\">????????????????????????????????????????????????</td>"
                + "<td class=\"oaex_operator_valid_thru_date\">2010/08/17???9999/12/21</td>"
                + "<td class=\"oaex_operator_subsystem_role\"></td>"
                + "<td class=\"oaex_operator_subsystem_role_valid_thru_date\"></td>"
                + "<td class=\"oaex_operator_biztran_role_code\"></td>"
                + "<td class=\"oaex_operator_biztran_role_name\"></td>"
                + "<td class=\"oaex_operator_biztran_role_valid_thru_date\"></td>"
                + "</tr>"
        );
        expectedVo.setPagination(
            "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(2);\">&lt;</a></li>"
                + "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(1);\">1</a></li>"
                + "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(2);\">2</a></li>"
                + "<li class=\"active\" th:remove=\"all\"><a href=\"#!\">3</a></li>"
                + "<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&gt;</a></li>"
        );

        // ??????
        presenter.bindTo(vo);

        // ????????????
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11010SearchPresenter}????????????
     *  ???????????????
     *    ????????????????????????
     *
     *  ???????????????
     *  ???Vo???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test3() {

        // ???????????????
        int pageNo = 3;
        Operators operators = getOperators();
        BranchesAtMoment branchesAtMoment = new BranchesAtMoment();
        AccountLocks accountLocks = getAccountLocks();
        Operator_SubSystemRoles operator_SubSystemRoles = getOperator_SubSystemRoles();
        Operator_BizTranRoles operator_BizTranRoles = getOperator_BizTranRoles();

        // ?????????
        Oa11010SearchResponseVo vo = new Oa11010SearchResponseVo();
        Oa11010SearchPresenter presenter = new Oa11010SearchPresenter();
        presenter.setPageNo(pageNo);
        presenter.setOperators(operators);
        presenter.setAccountLocks(accountLocks);
        presenter.setOperator_SubSystemRoles(operator_SubSystemRoles);
        presenter.setOperator_BizTranRoles(operator_BizTranRoles);

        // ?????????
        Oa11010SearchResponseVo expectedVo = new Oa11010SearchResponseVo();
        expectedVo.setOperatorTable(
            "<tr class=\"oaex_operator_table_operator_yu001117 oaex_th_operator_table_row\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_inpossible\"></div></td>"
                + "<td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td>"
                + "<td class=\"oaex_operator_branch_code\">001</td>"
                + "<td class=\"oaex_operator_branch_name\">??????001</td>"
                + "<td class=\"oaex_operator_operator_code\">yu001117<input type=\"hidden\" value=\"117\"/></td>"
                + "<td class=\"oaex_operator_operator_name\">????????????????????????????????????????????????</td>"
                + "<td class=\"oaex_operator_valid_thru_date\">2010/08/17???9999/12/21</td>"
                + "<td class=\"oaex_operator_subsystem_role\"></td>"
                + "<td class=\"oaex_operator_subsystem_role_valid_thru_date\"></td>"
                + "<td class=\"oaex_operator_biztran_role_code\"></td>"
                + "<td class=\"oaex_operator_biztran_role_name\"></td>"
                + "<td class=\"oaex_operator_biztran_role_valid_thru_date\"></td>"
                + "</tr>"
        );
        expectedVo.setPagination(
            "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(2);\">&lt;</a></li>"
                + "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(1);\">1</a></li>"
                + "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(2);\">2</a></li>"
                + "<li class=\"active\" th:remove=\"all\"><a href=\"#!\">3</a></li>"
                + "<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&gt;</a></li>"
        );

        // ??????
        presenter.bindTo(vo);

        // ????????????
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11010SearchPresenter}????????????
     *  ???????????????
     *    ???????????????????????????????????????
     *
     *  ???????????????
     *  ???Vo???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test4() {

        // ???????????????
        int pageNo = 1;
        Operators operators = Operators.createFrom(new ArrayList<Operator>());
        BranchesAtMoment branchesAtMoment = new BranchesAtMoment();
        AccountLocks accountLocks = AccountLocks.createFrom(new ArrayList<AccountLock>());
        Operator_SubSystemRoles operator_SubSystemRoles = Operator_SubSystemRoles.createFrom(new ArrayList<Operator_SubSystemRole>());
        Operator_BizTranRoles operator_BizTranRoles = Operator_BizTranRoles.createFrom(new ArrayList<Operator_BizTranRole>());

        // ?????????
        Oa11010SearchResponseVo vo = new Oa11010SearchResponseVo();
        Oa11010SearchPresenter presenter = new Oa11010SearchPresenter();
        presenter.setPageNo(pageNo);
        presenter.setOperators(operators);
        presenter.setAccountLocks(accountLocks);
        presenter.setOperator_SubSystemRoles(operator_SubSystemRoles);
        presenter.setOperator_BizTranRoles(operator_BizTranRoles);

        // ?????????
        Oa11010SearchResponseVo expectedVo = new Oa11010SearchResponseVo();
        expectedVo.setOperatorTable("");
        expectedVo.setPagination(
            "<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&lt;</a></li>"
            + "<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(2);\">&gt;</a></li>"
        );

        // ??????
        presenter.bindTo(vo);

        // ????????????
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}