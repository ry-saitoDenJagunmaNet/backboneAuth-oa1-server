package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBranchAtMoment;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchOperator;
import net.jagunma.backbone.auth.authmanager.application.queryService.SimpleSearchBizTranRole;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorsSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorsSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemSource;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010BizTranRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SearchResponseVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SubSystemRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaders;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraces;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraceRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraces;
import net.jagunma.backbone.auth.authmanager.model.types.AccountLockStatus;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.base.Preconditions;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class Oa11010ControllerTest {

    // ?????? ??? ?????? ?????????
    private String ja = null;
    private final String jaCode = "006";
    private final String jaName = "JA?????????";
    private Long jaId = 6L;
    private final Long branchId = null;
    private final List<SelectOptionItemSource> branchItemsSource = null;
    private final String operatorCode = null;
    private final String operatorName = null;
    private final String mailAddress = null;
    private final Boolean availableStatus0 = null;
    private final Boolean availableStatus1 = null;
    private final Integer validThruSelect = 0;
    private final LocalDate validThruStatusDate = null;
    private final LocalDate validThruStartDateFrom = null;
    private final LocalDate validThruStartDateTo = null;
    private final LocalDate validThruEndDateFrom = null;
    private final LocalDate validThruEndDateTo = null;
    private Integer subSystemRoleConditionsSelect = 1;
    private final Integer bizTranRoleConditionsSelect = 1;
    private final String bizTranRoleSubSystemCode = null;
    private final Boolean deviceAuthUse = null;
    private final Boolean deviceAuthUnuse = null;
    private final LocalDate accountLockOccurredDateFrom = null;
    private final LocalDate accountLockOccurredDateTo = null;
    private final Boolean accountLockStatusLock = null;
    private final Boolean accountLockStatusUnlock = null;
    private final Boolean passwordHistoryCheck = null;
    private final Integer passwordHistoryLastChangeDate = null;
    private final String passwordHistoryLastChangeDateStatus = null;
    private final Boolean passwordHistoryChangeType0 = null;
    private final Boolean passwordHistoryChangeType1 = null;
    private final Boolean passwordHistoryChangeType2 = null;
    private final Boolean passwordHistoryChangeType3 = null;
    private final LocalDate signintraceTrydateFrom = null;
    private final LocalDate signintraceTrydateTo = null;
    private final String signintraceTryIpAddress = null;
    private final Boolean signintraceSignIn = null;
    private final Boolean signintraceSignOut = null;
    private final Short[] signintraceSignInResult = null;
    private int pageNo = 0;
    private String responseMethod = null;
    private final String GunmaRuntimeExceptionMessageCode = "EOA14002";
    private final String GunmaRuntimeExceptionMessageArg1 = "JAID";
    private final String GunmaRuntimeExceptionMessageArg2 = "???????????????";
    private final String statusCode = "200 OK";

    // ??????????????????????????????
    private Oa11010Controller createOa11010Controller() {
        OperatorRepository operatorRepository = new OperatorRepository() {
            @Override
            public Operator findOneById(Long operatorId) {
                return null;
            }
            @Override
            public Operator findOneByCode(String operatorCode) {
                return null;
            }
            @Override
            public boolean existsById(Long operatorId) {
                return false;
            }
            @Override
            public boolean existsByCode(String operatorCode) {
                return false;
            }
            @Override
            public boolean existsBy(OperatorCriteria operatorCriteria) {
                return true;
            }
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                return null;
            }
        };
        AccountLockRepository accountLockRepository = new AccountLockRepository() {
            @Override
            public AccountLock findOneById(Long accountLockId) {
                return null;
            }
            @Override
            public boolean existsByOperatorId(Long operatorId) {
                return false;
            }
            @Override
            public AccountLock latestOneByOperatorId(Long operatorId) {
                return null;
            }
            @Override
            public AccountLocks selectBy(AccountLockCriteria accountLockrCriteria, Orders orders) {
                return null;
            }
        };
        PasswordHistoryRepository passwordHistoryRepository = new PasswordHistoryRepository() {
            @Override
            public PasswordHistory latestOneByOperatorId(Long operatorId) {
                return null;
            }
            @Override
            public PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria, Orders orders) {
                return null;
            }
        };
        SignInTraceRepository signInTraceRepository = new SignInTraceRepository() {
            @Override
            public SignInTrace findOneById(Long signInTraceId) {
                return null;
            }
            @Override
            public SignInTraces selectBy(SignInTraceCriteria signInTraceCriteria, Orders orders) {
                return null;
            }
        };
        SignOutTraceRepository signOutTraceRepository = new SignOutTraceRepository() {
            @Override
            public SignOutTraces selectBy(SignOutTraceCriteria signOutTraceCriteria, Orders orders) {
                return null;
            }
            @Override
            public SignOutTraces latestBy(SignOutTraceCriteria signOutTraceCriteria, Orders orders) {
                return null;
            }
        };
        Operator_SubSystemRoleRepository operator_SubSystemRoleRepository = new Operator_SubSystemRoleRepository() {
            @Override
            public Operator_SubSystemRoles selectBy(Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {
                return null;
            }
        };
        Operator_BizTranRoleRepository operator_BizTranRoleRepository = new Operator_BizTranRoleRepository() {
            @Override
            public Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders) {
                return null;
            }
        };
        OperatorHistoryHeaderRepository operatorHistoryHeaderRepository = new OperatorHistoryHeaderRepository() {
            @Override
            public OperatorHistoryHeader latestOneByOperatorId(Long operatorId) {
                return null;
            }
            @Override
            public OperatorHistoryHeaders selectBy(
                OperatorHistoryHeaderCriteria operatorHistoryHeaderCriteria, Orders orders) {
                return null;
            }
        };
        BranchAtMomentRepository branchAtMomentRepository = new BranchAtMomentRepository() {
            @Override
            public BranchAtMoment findOneBy(BranchAtMomentCriteria criteria) {
                return null;
            }
            @Override
            public BranchesAtMoment selectBy(BranchAtMomentCriteria criteria, Orders orders) {
                return null;
            }
        };
        SearchBranchAtMoment searchBranchAtMoment = new SearchBranchAtMoment(branchAtMomentRepository) {
            public BranchesAtMoment selectBy(long jaId) {
                return createBranchesAtMoment();
            }
        };
        SearchOperator searchOperator = new SearchOperator(
            operatorRepository,
            accountLockRepository,
            passwordHistoryRepository,
            signInTraceRepository,
            signOutTraceRepository,
            operator_SubSystemRoleRepository,
            operator_BizTranRoleRepository,
            operatorHistoryHeaderRepository) {
            public void execute(OperatorsSearchRequest request, OperatorsSearchResponse response) {
                // request.getJaIdCriteria().getEqualTo() == -1 ????????????RuntimeException ??????????????????
                if (request.getJaIdCriteria().getEqualTo() == -1) {
                    throw new RuntimeException();
                }
                // request.getJaIdCriteria().getEqualTo() == -2 ????????????GunmaRuntimeException ??????????????????
                if (request.getJaIdCriteria().getEqualTo() == -2) {
                    Preconditions.checkNotEmpty("", () -> new GunmaRuntimeException(GunmaRuntimeExceptionMessageCode, GunmaRuntimeExceptionMessageArg1, GunmaRuntimeExceptionMessageArg2));
                }

                response.setOperators(createOperators());
                response.setAccountLocks(createAccountLocks());
                response.setOperator_SubSystemRoles(createOperator_SubSystemRoles());
                response.setOperator_BizTranRoles(createOperator_BizTranRoles());
                return;
            }
        };
        BizTranRoleRepository bizTranRoleRepository = new BizTranRoleRepository() {
            @Override
            public BizTranRoles selectBy(BizTranRoleCriteria bizTranRoleCriteria, Orders orders) {
                return null;
            }
            @Override
            public BizTranRoles selectAll(Orders orders) {
                return null;
            }
        };
        SimpleSearchBizTranRole simpleSearchBizTranRole = new SimpleSearchBizTranRole(
            bizTranRoleRepository) {
            public BizTranRoles getBizTranRoles() {
                return createBizTranRoles();
            }
        };

        return new Oa11010Controller(
            searchOperator,
            simpleSearchBizTranRole,
            searchBranchAtMoment);
    }

    // Oa11010Vo??????
    private Oa11010Vo createOa11010Vo() {
        Oa11010Vo oa11010Vo = new Oa11010Vo();
        oa11010Vo.setJa(ja);
        oa11010Vo.setJaId(jaId);
        oa11010Vo.setBranchId(branchId);
        oa11010Vo.setBranchItemsSource(SelectOptionItemsSource.createFrom(createBranchesAtMoment()).getValue());
        oa11010Vo.setOperatorCode(operatorCode);
        oa11010Vo.setOperatorName(operatorName);
        oa11010Vo.setMailAddress(mailAddress);
        oa11010Vo.setAvailableStatus0(availableStatus0);
        oa11010Vo.setAvailableStatus1(availableStatus1);
        oa11010Vo.setValidThruSelect(validThruSelect);
        oa11010Vo.setValidThruStatusDate(validThruStatusDate);
        oa11010Vo.setValidThruStartDateFrom(validThruStartDateFrom);
        oa11010Vo.setValidThruStartDateTo(validThruStartDateTo);
        oa11010Vo.setValidThruEndDateFrom(validThruEndDateFrom);
        oa11010Vo.setValidThruEndDateTo(validThruEndDateTo);
        oa11010Vo.setSubSystemRoleConditionsSelect(subSystemRoleConditionsSelect);
        List<Oa11010SubSystemRoleVo> subSystemRoleVoList = newArrayList();
        for(SubSystemRole subSystemRole : SubSystemRole.values()) {
            if (subSystemRole.getCode().length() == 0) { continue; }
            Oa11010SubSystemRoleVo subSystemRoleVo = new Oa11010SubSystemRoleVo();
            subSystemRoleVo.setSubSystemRoleCode(subSystemRole.getCode());
            subSystemRoleVo.setSubSystemRoleName(subSystemRole.getDisplayName());
            subSystemRoleVo.setValidThruSelect(0);
            subSystemRoleVoList.add(subSystemRoleVo);
        }
        oa11010Vo.setSubSystemRoleList(subSystemRoleVoList);
        oa11010Vo.setBizTranRoleConditionsSelect(bizTranRoleConditionsSelect);
        oa11010Vo.setBizTranRoleSubSystemCode(bizTranRoleSubSystemCode);
        oa11010Vo.setBizTranRoleSubSystemList(SelectOptionItemsSource.createFrom(SubSystem.getValidValues()).getValue());
        List<Oa11010BizTranRoleVo> oa11010BizTranRoleVoList = newArrayList();
        for (BizTranRole bizTranRole :  createBizTranRoles().getValues()) {
            Oa11010BizTranRoleVo bizTranRoleVo = new Oa11010BizTranRoleVo();
            bizTranRoleVo.setBizTranRoleId(bizTranRole.getBizTranRoleId());
            bizTranRoleVo.setBizTranRoleCode(bizTranRole.getBizTranRoleCode());
            bizTranRoleVo.setBizTranRoleName(bizTranRole.getBizTranRoleName());
            bizTranRoleVo.setSubSystemCode(bizTranRole.getSubSystemCode());
            bizTranRoleVo.setValidThruSelect(0);
            oa11010BizTranRoleVoList.add(bizTranRoleVo);
        }
        oa11010Vo.setBizTranRoleList(oa11010BizTranRoleVoList);
        oa11010Vo.setDeviceAuthUse(deviceAuthUse);
        oa11010Vo.setDeviceAuthUnuse(deviceAuthUnuse);
        oa11010Vo.setAccountLockOccurredDateFrom(accountLockOccurredDateFrom);
        oa11010Vo.setAccountLockOccurredDateTo(accountLockOccurredDateTo);
        oa11010Vo.setAccountLockStatusLock(accountLockStatusLock);
        oa11010Vo.setAccountLockStatusUnlock(accountLockStatusUnlock);
        oa11010Vo.setPasswordHistoryCheck(passwordHistoryCheck);
        oa11010Vo.setPasswordHistoryLastChangeDate(passwordHistoryLastChangeDate);
        oa11010Vo.setPasswordHistoryLastChangeDateStatus(passwordHistoryLastChangeDateStatus);
        oa11010Vo.setPasswordHistoryChangeType0(passwordHistoryChangeType0);
        oa11010Vo.setPasswordHistoryChangeType1(passwordHistoryChangeType1);
        oa11010Vo.setPasswordHistoryChangeType2(passwordHistoryChangeType2);
        oa11010Vo.setPasswordHistoryChangeType3(passwordHistoryChangeType3);
        oa11010Vo.setSignintraceTrydateFrom(signintraceTrydateFrom);
        oa11010Vo.setSignintraceTrydateTo(signintraceTrydateTo);
        oa11010Vo.setSignintraceTryIpAddress(signintraceTryIpAddress);
        oa11010Vo.setSignintraceSignIn(signintraceSignIn);
        oa11010Vo.setSignintraceSignOut(signintraceSignOut);
        oa11010Vo.setSignintraceSignInResult(signintraceSignInResult);
        oa11010Vo.setPageNo(pageNo);
        oa11010Vo.setResponseMethod(responseMethod);
        return oa11010Vo;
    }

    // BranchesAtMoment??????
    private BranchesAtMoment createBranchesAtMoment() {
        List<BranchAtMoment> branchAtMomentList = newArrayList();
        branchAtMomentList.add(BranchAtMoment.builder()
            .withIdentifier(33L).withJaAtMoment(createJaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.??????).withBranchCode(BranchCode.of("001")).withName("??????001").build())
            .build());
        branchAtMomentList.add(BranchAtMoment.builder()
            .withIdentifier(35L).withJaAtMoment(createJaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.??????).withBranchCode(BranchCode.of("003")).withName("??????003").build())
            .build());
        branchAtMomentList.add(BranchAtMoment.builder()
            .withIdentifier(36L).withJaAtMoment(createJaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.??????).withBranchCode(BranchCode.of("004")).withName("??????004").build())
            .build());
        return BranchesAtMoment.of(branchAtMomentList);
    }
    private BranchAtMoment createBranchAtMoment(Long branchId) {
        return createBranchesAtMoment().getValue().stream().filter(b->b.getIdentifier().equals(branchId)).findFirst().orElse(null);
    }

    // JaAtMoment??????
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

    // ????????????????????????
    private BizTranRoles createBizTranRoles() {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(1L,"KBAG01","??????????????????????????????","KB",1,SubSystem.??????));
        list.add(BizTranRole.createFrom(2L,"KBAG02","????????????????????????","KB",1,SubSystem.??????));
        return BizTranRoles.createFrom(list);
    }

    // ???????????????????????????
    private Operators createOperators() {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","????????????????????????","yu001009@aaaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),false,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment(33L)));
        list.add(Operator.createFrom(19L,"yu001010","????????????????????????","yu001010@aaaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),false,6L,"006",33L,"001",AvailableStatus.????????????,1,createBranchAtMoment(33L)));
        return Operators.createFrom(list);
    }

    // ?????????????????????????????????
    private AccountLocks createAccountLocks() {
        List<AccountLock> list = newArrayList();
        list.add(AccountLock.createFrom(1L,18L,LocalDateTime.of(2020,10,1,8,30,12),AccountLockStatus.???????????????,0,null));
        list.add(AccountLock.createFrom(2L,19L,LocalDateTime.of(2020,10,1,8,30,12),AccountLockStatus.?????????,0,null));
        return AccountLocks.createFrom(list);
    }

    // ??????????????????_??????????????????????????????????????????
    private Operator_SubSystemRoles createOperator_SubSystemRoles() {
        List<Operator_SubSystemRole> list = newArrayList();
        list.add(Operator_SubSystemRole.createFrom(1L,18L,SubSystemRole.???????????????_??????.getCode(),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,SubSystemRole.???????????????_??????));
        list.add(Operator_SubSystemRole.createFrom(2L,21L,SubSystemRole.???????????????_??????.getCode(),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,SubSystemRole.???????????????_??????));
        list.add(Operator_SubSystemRole.createFrom(3L,21L,SubSystemRole.???????????????_??????_??????.getCode(),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,SubSystemRole.???????????????_??????_??????));
        return Operator_SubSystemRoles.createFrom(list);
    }

    // ??????????????????_??????????????????????????????
    private Operator_BizTranRoles createOperator_BizTranRoles() {
        List<Operator_BizTranRole> list = newArrayList();
        list.add(Operator_BizTranRole.createFrom(1L,18L,1L,LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,createBizTranRole(1L)));
        list.add(Operator_BizTranRole.createFrom(2L,18L,2L,LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,createBizTranRole(4L)));
        list.add(Operator_BizTranRole.createFrom(3L,20L,2L,LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,createBizTranRole(2L)));
        list.add(Operator_BizTranRole.createFrom(4L,20L,3L,LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,createBizTranRole(3L)));
        list.add(Operator_BizTranRole.createFrom(5L,21L,1L,LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,createBizTranRole(3L)));
        list.add(Operator_BizTranRole.createFrom(6L,21L,2L,LocalDate.of(2020,1,1),null,1,null,createBizTranRole(3L)));
        return Operator_BizTranRoles.createFrom(list);
    }

    // ?????????????????????
    private BizTranRole createBizTranRole(Long id) {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(1L,"KB0000","???????????????????????????",SubSystem.??????.getCode(),1,SubSystem.??????));
        list.add(BizTranRole.createFrom(2L,"KB0001","????????????",SubSystem.??????.getCode(),1,SubSystem.??????));
        list.add(BizTranRole.createFrom(3L,"KB0002","????????????",SubSystem.??????.getCode(),1,SubSystem.??????));
        list.add(BizTranRole.createFrom(4L,"YS0000","???????????????????????????",SubSystem.??????_??????.getCode(),1,SubSystem.??????_??????));
        return list.stream().filter(b->b.getBizTranRoleId().equals(id)).findFirst().orElse(null);
    }

    /**
     * {@link Oa11010Controller#get(Model)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???Vo???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test0() {

        // ??????????????????????????????
        Oa11010Controller oa11010Controller = createOa11010Controller();

        // ?????????
        ConcurrentModel model = new ConcurrentModel();

        // ?????????
        String expectedViewName = "oa11010";
        ja = jaCode + " " + jaName;
        Oa11010Vo expectedVo = createOa11010Vo();

        // ??????
        String actualViewName = oa11010Controller.get(model);
        Oa11010Vo actualVo = (Oa11010Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11010Controller#get(Model)}?????????
     *  ???????????????
     *    ?????????GunmaRuntimeException?????????
     *
     *  ???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test1() {
        // get???????????????GunmaRuntimeException????????????????????????????????????
        assertThat(true);
    }

    /**
     * {@link Oa11010Controller#get(Model)}?????????
     *  ???????????????
     *    ?????????RuntimeException?????????
     *
     *  ???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test2() {
        // get???????????????RuntimeException????????????????????????????????????
        assertThat(true);
    }

    /**
     * {@link Oa11010Controller#search(Oa11010Vo)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???Vo???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void search_test0() {

        // ??????????????????????????????
        Oa11010Controller oa11010Controller = createOa11010Controller();

        // ?????????
        pageNo = 1;
        Oa11010Vo oa11010Vo = createOa11010Vo();

        // ?????????
        Oa11010SearchResponseVo expectedVo = new Oa11010SearchResponseVo();
        expectedVo.setOperatorTable("<tr class=\"oaex_operator_table_operator_yu001009 oaex_th_operator_table_row\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\">001</td><td class=\"oaex_operator_branch_name\">??????001</td><td class=\"oaex_operator_operator_code\">yu001009<input type=\"hidden\" value=\"18\"/></td><td class=\"oaex_operator_operator_name\">????????????????????????</td><td class=\"oaex_operator_valid_thru_date\">2010/08/17???9999/12/21</td><td class=\"oaex_operator_subsystem_role\">???????????????????????????</td><td class=\"oaex_operator_subsystem_role_valid_thru_date\">2020/01/01???9999/12/31</td><td class=\"oaex_operator_biztran_role_code\">KB0000</td><td class=\"oaex_operator_biztran_role_name\">???????????????????????????</td><td class=\"oaex_operator_biztran_role_valid_thru_date\">2020/01/01???9999/12/31</tr>"
            + "<tr class=\"oaex_operator_table_operator_yu001010 oaex_th_operator_table_row\" onclick=\"oaex_operatorTable_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_lock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001010<input type=\"hidden\" value=\"19\"/></td><td class=\"oaex_operator_operator_name\">????????????????????????</td><td class=\"oaex_operator_valid_thru_date\">2010/08/17???9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_valid_thru_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_valid_thru_date\"></td></tr>"
        );
        expectedVo.setPagination("<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&lt;</a></li>"
            + "<li class=\"active\" th:remove=\"all\"><a href=\"#!\">1</a></li>"
            + "<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&gt;</a></li>"
        );

        // ??????
        ResponseEntity<Oa11010SearchResponseVo> actualVo = oa11010Controller.search(oa11010Vo);

        // ????????????
        assertThat(actualVo.getStatusCode().toString()).isEqualTo(statusCode);
        assertThat(actualVo.getBody()).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11010Controller#search(Oa11010Vo)}?????????
     *  ???????????????
     *    ?????????GunmaRuntimeException?????????
     *
     *  ???????????????
     *  ????????????
     *  ???????????????????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void search_test1() {

        // ??????????????????????????????
        Oa11010Controller oa11010Controller = createOa11010Controller();

        // ?????????
        jaId = -2L;
        Oa11010Vo oa11010Vo = createOa11010Vo();

        // ??????
        ResponseEntity<Oa11010SearchResponseVo> actualVo = oa11010Controller.search(oa11010Vo);

        // ????????????
        assertThat(actualVo.getStatusCode().toString()).isEqualTo(statusCode);
        assertThat(actualVo.getBody().getMessageCode()).isEqualTo(GunmaRuntimeExceptionMessageCode);
        assertThat(actualVo.getBody().getMessageArgs().get(0)).isEqualTo(GunmaRuntimeExceptionMessageArg1);
        assertThat(actualVo.getBody().getMessageArgs().get(1)).isEqualTo(GunmaRuntimeExceptionMessageArg2);
    }

    /**
     * {@link Oa11010Controller#search(Oa11010Vo)}?????????
     *  ???????????????
     *    ?????????RuntimeException?????????
     *
     *  ???????????????
     *  ????????????
     *  ???????????????????????????????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void search_test2() {

        // ??????????????????????????????
        Oa11010Controller oa11010Controller = createOa11010Controller();

        // ?????????
        jaId = -1L;
        Oa11010Vo oa11010Vo = createOa11010Vo();

        // ?????????
        String expectedMessageCode = "EOA10001";

        // ??????
        ResponseEntity<Oa11010SearchResponseVo> actualVo = oa11010Controller.search(oa11010Vo);

        // ????????????
        assertThat(actualVo.getStatusCode().toString()).isEqualTo(statusCode);
        assertThat(actualVo.getBody().getMessageCode()).isEqualTo(expectedMessageCode);
    }

    /**
     * {@link Oa11010Controller#assistance(String, Model)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???Vo???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void assistance_test0() {

        // ??????????????????????????????
        Oa11010Controller oa11010Controller = createOa11010Controller();

        // ?????????
        responseMethod = "redirect:/oa11010/assistance";
        ConcurrentModel model = new ConcurrentModel();

        // ?????????
        ja = jaCode + " " + jaName;
        Oa11010Vo expectedVo = createOa11010Vo();

        // ??????
        String actualViewName = oa11010Controller.assistance(responseMethod, model);
        Oa11010Vo actualVo = (Oa11010Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11010Controller#assistance(String, Model)}?????????
     *  ???????????????
     *    ?????????GunmaRuntimeException?????????
     *
     *  ???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void assistance_test1() {
        // get???????????????GunmaRuntimeException????????????????????????????????????
        assertThat(true);
    }

    /**
     * {@link Oa11010Controller#assistance(String, Model)}?????????
     *  ???????????????
     *    ?????????RuntimeException?????????
     *
     *  ???????????????
     */
    @Test
    @Tag(TestSize.SMALL)
    void assistance_test2() {
        // get???????????????RuntimeException????????????????????????????????????
        assertThat(true);
    }

}