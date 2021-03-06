package net.jagunma.backbone.auth.authmanager.infra.web.oa11030;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.commandService.UpdateOperator;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBranchAtMoment;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchOperator;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorCommand.OperatorUpdateRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.util.CheckboxUtil;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030BizTranRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030SubSystemRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorEntryPack;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorUpdatePack;
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
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchNotFoundException;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class Oa11030ControllerTest {

    // ?????? ??? ?????? ?????????
    // ???????????????????????????
    private Long operatorId = 123456L;
    private String operatorCode = "yu123456";
    private String operatorName = "?????????????????????";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate validThruStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate validThruEndDate = LocalDate.of(2020, 9, 30);
    private Boolean isDeviceAuth = false;
    private Long jaId = 6L;
    private String jaCode = "006";
    private Long branchId = 1L;
    private String branchCode = "001";
    private AvailableStatus availableStatus = AvailableStatus.????????????;
    private Integer recordVersion = 1;

    private String changeCause = "????????????????????????";
    private String changeCausePlaceholder = "?????????????????????????????????";
    private AccountLockStatus accountLockStatus = AccountLockStatus.???????????????;

    private List<Oa11030SubSystemRoleTableVo> subSystemRoleTableVoList;
    private List<Oa11030BizTranRoleTableVo> bizTranRoleTableVoList;

    // ??????AtMoment
    private String jaName = "JA?????????";
    private JaAtMoment jaAtMoment = JaAtMoment.builder()
        .withIdentifier(jaId)
        .withJaAttribute(JaAttribute.builder()
            .withJaCode(JaCode.of(jaCode))
            .withName(jaName)
            .build())
        .build();

    // ??????AtMoment
    private BranchAtMoment branchAtMoment = BranchAtMoment.builder().withIdentifier(branchId).withJaAtMoment(jaAtMoment).withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.??????).withBranchCode(BranchCode.of(branchCode)).withName("??????").build()).build();

    // ?????????????????????
    private Operator operator = Operator.createFrom(operatorId, operatorCode, operatorName, mailAddress, validThruStartDate, validThruEndDate, isDeviceAuth, jaId, jaCode, branchId, branchCode, availableStatus, recordVersion, branchAtMoment);

    // ?????????AtMoment
    private List<BranchAtMoment> branchAtMomentList = newArrayList(
        branchAtMoment,
        BranchAtMoment.builder().withIdentifier(2L).withJaAtMoment(jaAtMoment).withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.??????).withBranchCode(BranchCode.of("002")).withName("??????002").build()).build(),
        BranchAtMoment.builder().withIdentifier(3L).withJaAtMoment(jaAtMoment).withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.??????).withBranchCode(BranchCode.of("003")).withName("??????003").build()).build());
    private BranchesAtMoment branchesAtMoment = BranchesAtMoment.of(branchAtMomentList);

    // ?????????????????????????????????
    private List<AccountLock> accountLockList = newArrayList(
        AccountLock.createFrom(202L,operatorId, LocalDateTime.of(2020,10,2,3,4,5),AccountLockStatus.???????????????, recordVersion, operator),
        AccountLock.createFrom(201L,operatorId, LocalDateTime.of(2020,10,1,2,3,4),AccountLockStatus.?????????, recordVersion, operator));
    private AccountLocks accountLocks = AccountLocks.createFrom(accountLockList);

    // ??????????????????_????????????????????????????????????
    private List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(301L, operatorId, SubSystemRole.JA?????????.getCode(), validThruStartDate, validThruEndDate, 391, operator, SubSystemRole.JA?????????),
        Operator_SubSystemRole.createFrom(302L, operatorId, SubSystemRole.???????????????_??????.getCode(), validThruStartDate, validThruEndDate, 392, operator, SubSystemRole.???????????????_??????),
        Operator_SubSystemRole.createFrom(303L, operatorId, SubSystemRole.???????????????_??????_??????.getCode(), validThruStartDate, validThruEndDate, 393, operator, SubSystemRole.???????????????_??????_??????),
        Operator_SubSystemRole.createFrom(304L, operatorId, SubSystemRole.???????????????_??????_???.getCode(), validThruStartDate, validThruEndDate, 394, operator, SubSystemRole.???????????????_??????_???),
        Operator_SubSystemRole.createFrom(305L, operatorId, SubSystemRole.???????????????_??????_??????.getCode(), validThruStartDate, validThruEndDate, 395, operator, SubSystemRole.???????????????_??????_??????));
    private Operator_SubSystemRoles operator_SubSystemRoles = Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);

    // ??????????????????_????????????????????????
    private List<BizTranRole> bizTranRoleList = newArrayList(
        BizTranRole.createFrom(401L, "KBAG01", "??????????????????????????????", SubSystem.??????.getCode(), recordVersion, SubSystem.??????),
        BizTranRole.createFrom(402L, "YSAG10", "?????????????????????", SubSystem.??????_??????.getCode(), recordVersion, SubSystem.??????_??????),
        BizTranRole.createFrom(403L, "HKAG10", "???????????????????????????", SubSystem.??????_???.getCode(), recordVersion, SubSystem.??????_???),
        BizTranRole.createFrom(404L, "ANAG01", "????????????????????????", SubSystem.??????_??????.getCode(), recordVersion, SubSystem.??????_??????));
    private List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList(
        Operator_BizTranRole.createFrom(501L, operatorId, bizTranRoleList.get(0).getBizTranRoleId(), validThruStartDate, validThruEndDate, recordVersion, operator, bizTranRoleList.get(0)),
        Operator_BizTranRole.createFrom(502L, operatorId, bizTranRoleList.get(1).getBizTranRoleId(), validThruStartDate, validThruEndDate, recordVersion, operator, bizTranRoleList.get(1)),
        Operator_BizTranRole.createFrom(503L, operatorId, bizTranRoleList.get(2).getBizTranRoleId(), validThruStartDate, validThruEndDate, recordVersion, operator, bizTranRoleList.get(2)),
        Operator_BizTranRole.createFrom(504L, operatorId, bizTranRoleList.get(3).getBizTranRoleId(), validThruStartDate, validThruEndDate, recordVersion, operator, bizTranRoleList.get(3)));
    private Operator_BizTranRoles operator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);

    // ???????????????????????????????????????
    private List<OperatorHistoryHeader> operatorHistoryHeaderList = newArrayList(
        OperatorHistoryHeader.createFrom(601L, operatorId,LocalDateTime.of(2020,10,1,0,1,2), changeCausePlaceholder, recordVersion, operator));
    private OperatorHistoryHeaders operatorHistoryHeaders = OperatorHistoryHeaders.createFrom(operatorHistoryHeaderList);


    // ??????????????????????????????
    private Oa11030Controller createOa11030Controller() {

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
                return false;
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
        PasswordHistoryRepository passwordHistoryRepository = new PasswordHistoryRepository(){
            @Override
            public PasswordHistory latestOneByOperatorId(Long operatorId) {
                return null;
            }
            @Override
            public PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria,
                Orders orders) {
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
            public Operator_SubSystemRoles selectBy(
                Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {
                return null;
            }
        };
        Operator_BizTranRoleRepository operator_BizTranRoleRepository = new Operator_BizTranRoleRepository() {
            @Override
            public Operator_BizTranRoles selectBy(
                Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders) {
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
        SearchOperator searchOperator = new SearchOperator(operatorRepository,
            accountLockRepository,
            passwordHistoryRepository, signInTraceRepository, signOutTraceRepository,
            operator_SubSystemRoleRepository, operator_BizTranRoleRepository,
            operatorHistoryHeaderRepository) {
            @Override
            public void execute(OperatorSearchRequest request, OperatorSearchResponse response) {
                //  request.getOperatorId().equals(11L) ????????????GunmaRuntimeException ??????????????????
                if (request.getOperatorId().equals(11L)) {
                    throw new GunmaRuntimeException("EOA13008", "??????????????????");
                }
                //  request.getOperatorId().equals(12L) ????????????RuntimeException ??????????????????
                if (request.getOperatorId().equals(12L)) {
                    throw new RuntimeException();
                }
                response.setOperator(operator);
                response.setAccountLocks(accountLocks);
                response.setOperator_SubSystemRoles(operator_SubSystemRoles);
                response.setOperator_BizTranRoles(operator_BizTranRoles);
                response.setOperatorHistoryHeaders(operatorHistoryHeaders);
            }
        };

        OperatorRepositoryForStore operatorRepositoryForStore = new OperatorRepositoryForStore() {
            @Override
            public void entry(OperatorEntryPack operatorEntryPack) {
            }
            @Override
            public void update(OperatorUpdatePack operatorUpdatePack) {
            }
        };
        BranchAtMomentRepository branchAtMomentRepository = new BranchAtMomentRepository() {
            @Override
            public BranchAtMoment findOneBy(BranchAtMomentCriteria criteria) throws BranchNotFoundException {
                return null;
            }
            @Override
            public BranchesAtMoment selectBy(BranchAtMomentCriteria criteria, Orders orders) {
                return null;
            }
        };
        SearchBranchAtMoment searchBranchAtMoment = new SearchBranchAtMoment(branchAtMomentRepository) {
            public BranchesAtMoment selectBy(long jaId) {
                return branchesAtMoment;
            }
        };

        UpdateOperator updateOperator = new UpdateOperator(operatorRepositoryForStore, searchBranchAtMoment) {
            @Override
            public void execute(OperatorUpdateRequest request) {
                //  request.getOperatorId().equals(21L) ????????????GunmaRuntimeException ??????????????????
                if (request.getOperatorId().equals(21L)) {
                    throw new GunmaRuntimeException("EOA13002", "??????????????????ID");
                }
                //  request.getOperatorId().equals(22L) ????????????RuntimeException ??????????????????
                if (request.getOperatorId().equals(22L)) {
                    throw new RuntimeException();
                }
                //  request.getOperatorId().equals(23L) ????????????OptimisticLockingFailureException ??????????????????
                if (request.getOperatorId().equals(23L)) {
                    throw new OptimisticLockingFailureException("??????????????????");
                }
            }
        };

        return new Oa11030Controller(updateOperator, searchOperator, searchBranchAtMoment);
    }

    // Oa11030Vo??????
    private Oa11030Vo createOa11030Vo() {
        Oa11030Vo vo = new Oa11030Vo();

        vo.setOperatorId(operatorId);
        vo.setRecordVersion(recordVersion);
        vo.setJa(jaCode + " " + jaName);
        vo.setBranchId(branchId);
        vo.setOperatorCode(operatorCode);
        vo.setOperatorName(operatorName);
        vo.setMailAddress(mailAddress);
        vo.setValidThruStartDate(validThruStartDate);
        vo.setValidThruEndDate(validThruEndDate);
        vo.setIsDeviceAuth(CheckboxUtil.setSmoother(isDeviceAuth));
        vo.setAvailableStatus(CheckboxUtil.setSmoother((availableStatus.equals(AvailableStatus.????????????))? true : false));
        vo.setChangeCause(changeCause);
        vo.setChangeCausePlaceholder(changeCausePlaceholder);
        vo.setAccountLockStatus(accountLockStatus.getCode());
        vo.setSubSystemRoleTableVoList(createOa11030SubSystemRoleTableVoList());
        vo.setBizTranRoleTableVoList(createOa11030BizTranRoleTableVoList());
        vo.setBranchItemsSource(SelectOptionItemsSource.createFrom(branchesAtMoment).getValue());

        return vo;
    }

    // Oa11030SubSystemRoleTableVoList??????
    private List<Oa11030SubSystemRoleTableVo> createOa11030SubSystemRoleTableVoList() {
        List<Oa11030SubSystemRoleTableVo> subSystemRoleTableVoList = newArrayList();

        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoleList) {
            Oa11030SubSystemRoleTableVo subSystemRoleTableVo = new Oa11030SubSystemRoleTableVo();
            subSystemRoleTableVo.setRoleName(operator_SubSystemRole.getSubSystemRole().getDisplayName());
            subSystemRoleTableVo.setValidThruDate(
                operator_SubSystemRole.getValidThruStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " ??? " +
                operator_SubSystemRole.getValidThruEndDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            subSystemRoleTableVoList.add(subSystemRoleTableVo);
        }

        return subSystemRoleTableVoList;
    }

    // Oa11030BizTranRoleTableVoList??????
    private List<Oa11030BizTranRoleTableVo> createOa11030BizTranRoleTableVoList() {
        List<Oa11030BizTranRoleTableVo> bizTranRoleTableVoList = newArrayList();

        for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoleList) {
            Oa11030BizTranRoleTableVo bizTranRoleTableVo = new Oa11030BizTranRoleTableVo();
            bizTranRoleTableVo.setRoleCode(operator_BizTranRole.getBizTranRole().getBizTranRoleCode());
            bizTranRoleTableVo.setRoleName(operator_BizTranRole.getBizTranRole().getBizTranRoleName());
            bizTranRoleTableVo.setValidThruDate(
                operator_BizTranRole.getValidThruStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " ??? " +
                    operator_BizTranRole.getValidThruEndDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            bizTranRoleTableVoList.add(bizTranRoleTableVo);
        }

        return bizTranRoleTableVoList;
    }

    /**
     * {@link Oa11030Controller#get(Long operatorId, Model model)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test() {
        // ??????????????????????????????
        Oa11030Controller oa11030Controller = createOa11030Controller();

        // ?????????
        ConcurrentModel model = new ConcurrentModel();

        // ?????????
        String expectedViewName = "oa11030"; // ToDo: ????????????
        changeCause = null;
        Oa11030Vo expectedVo = createOa11030Vo();

        // ??????
        String actualViewName = oa11030Controller.get(operatorId, model);
        Oa11030Vo actualVo = (Oa11030Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11030Controller#get(Long operatorId, Model model)}?????????
     *  ???????????????
     *    ?????????GunmaRuntimeException?????????
     *
     *  ???????????????
     *  ????????????
     *  ???????????????????????????????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test1() {
        // ??????????????????????????????
        Oa11030Controller oa11030Controller = createOa11030Controller();

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        operatorId = 11L;

        // ?????????
        String expectedViewName = "oa11030";
        String expectedMessageCode = "EOA13008";
        String expectedMessageArgs0 = "??????????????????";

        // ??????
        String actualViewName = oa11030Controller.get(operatorId, model);
        Oa11030Vo actualVo = (Oa11030Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(expectedMessageArgs0);
    }

    /**
     * {@link Oa11030Controller#get(Long operatorId, Model model)}?????????
     *  ???????????????
     *    ?????????RuntimeException?????????
     *
     *  ???????????????
     *  ????????????
     *  ???????????????????????????????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test2() {
        // ??????????????????????????????
        Oa11030Controller oa11030Controller = createOa11030Controller();

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        operatorId = 12L;

        // ?????????
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10001";

        // ??????
        String actualViewName = oa11030Controller.get(operatorId, model);
        Oa11030Vo actualVo = (Oa11030Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }

    /**
     * {@link Oa11030Controller#update(Model model, Oa11030Vo vo)}?????????
     *  ???????????????
     *    ??????
     *
     *  ???????????????
     *  ???????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test() {
        // ??????????????????????????????
        Oa11030Controller oa11030Controller = createOa11030Controller();

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        isDeviceAuth = true;
        Oa11030Vo vo = createOa11030Vo();

        // ?????????
        String expectedViewName = "oa11030"; // ToDo: ????????????
        Oa11030Vo expectedVo = createOa11030Vo();

        // ??????
        String actualViewName = oa11030Controller.update(model, vo);
        Oa11030Vo actualVo = (Oa11030Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11030Controller#update(Model model, Oa11030Vo vo)}?????????
     *  ???????????????
     *    ?????????GunmaRuntimeException?????????
     *
     *  ???????????????
     *  ????????????
     *  ???????????????????????????????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test1() {
        // ??????????????????????????????
        Oa11030Controller oa11030Controller = createOa11030Controller();

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        operatorId = 21L;
        Oa11030Vo vo = createOa11030Vo();

        // ?????????
        String expectedViewName = "oa11030";
        String expectedMessageCode = "EOA13002";
        String expectedMessageArgs0 = "??????????????????ID";

        // ??????
        String actualViewName = oa11030Controller.update(model, vo);
        Oa11030Vo actualVo = (Oa11030Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(expectedMessageArgs0);
    }

    /**
     * {@link Oa11030Controller#update(Model model, Oa11030Vo vo)}?????????
     *  ???????????????
     *    ?????????RuntimeException?????????
     *
     *  ???????????????
     *  ????????????
     *  ???????????????????????????????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test2() {
        // ??????????????????????????????
        Oa11030Controller oa11030Controller = createOa11030Controller();

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        operatorId = 22L;
        Oa11030Vo vo = createOa11030Vo();

        // ?????????
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10001";

        // ??????
        String actualViewName = oa11030Controller.update(model, vo);
        Oa11030Vo actualVo = (Oa11030Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }

    /**
     * {@link Oa11030Controller#update(Model model, Oa11030Vo vo)}?????????
     *  ???????????????
     *    ?????????OptimisticLockingFailureException?????????
     *
     *  ???????????????
     *  ????????????
     *  ???????????????????????????????????????
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test3() {
        // ??????????????????????????????
        Oa11030Controller oa11030Controller = createOa11030Controller();

        // ?????????
        ConcurrentModel model = new ConcurrentModel();
        operatorId = 23L;
        Oa11030Vo vo = createOa11030Vo();

        // ?????????
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10002";

        // ??????
        String actualViewName = oa11030Controller.update(model, vo);
        Oa11030Vo actualVo = (Oa11030Vo) model.getAttribute("form");

        // ????????????
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }
}