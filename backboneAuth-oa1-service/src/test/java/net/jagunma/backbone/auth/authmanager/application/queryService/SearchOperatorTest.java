package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchBizTranRoleRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchSubSystemRoleRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.Oa11010SearchBizTranRoleConverter;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.Oa11010SearchSubSystemRoleConverter;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocksRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorsRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryHeader.OperatorHistoryHeaderCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryHeader.OperatorHistoryHeaders;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryHeader.OperatorHistoryHeadersRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistories;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoriesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraces;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTracesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraces;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTracesRepository;
import net.jagunma.backbone.auth.authmanager.model.types.AccountLockStatus;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.backbone.auth.authmanager.model.types.SignInResult;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.criterias.BooleanCriteria;
import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.ShortCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SearchOperatorTest {

    // 実行既定値
    private final LongCriteria operatorIdCriteria = new LongCriteria();
    private final StringCriteria operatorCodeCriteria = new StringCriteria();
    private final StringCriteria operatorNameCriteria = new StringCriteria();
    private final StringCriteria mailAddressCriteria = new StringCriteria();
    private final LocalDateCriteria expirationStartDateCriteria = new LocalDateCriteria();
    private final LocalDateCriteria expirationEndDateCriteria = new LocalDateCriteria();
    private final BooleanCriteria isDeviceAuthCriteria = new BooleanCriteria();
    private final LongCriteria jaIdCriteria = new LongCriteria();
    private final LongCriteria branchIdCriteria = new LongCriteria();
    private final ShortCriteria availableStatusCriteria = new ShortCriteria();

    private Integer subSystemRoleConditionsSelect = null;
    private List<OparatorSearchSubSystemRoleRequest> subSystemRoleList = null;
    private Integer bizTranRoleConditionsSelect = null;
    private String bizTranRoleSubSystemCode = null;
    private List<OparatorSearchBizTranRoleRequest> bizTranRoleList = null;
    private LocalDate accountLockOccurredDateFrom = null;
    private LocalDate accountLockOccurredDateTo = null;
    private Short accountLockStatusLock = null;
    private Short accountLockStatusUnlock = null;
    private Short passwordHistoryCheck = null;
    private Integer passwordHistoryLastChangeDate = null;
    private String passwordHistoryLastChangeDateStatus = null;
    private Short passwordHistoryChangeType0 = null;
    private Short passwordHistoryChangeType1 = null;
    private Short passwordHistoryChangeType2 = null;
    private Short passwordHistoryChangeType3 = null;
    private LocalDate signintraceTrydateFrom = null;
    private LocalDate signintraceTrydateTo = null;
    private String signintraceTryIpAddress = null;
    private Short signintraceSignIn = null;
    private Short signintraceSignOut = null;
    private Short[] signintraceSignInResult = null;
    private int pageNo = 1;
    private List<Short> availableStatusIncludesList = null;

    // テスト対象クラス生成
    private SearchOperator createSearchOperator() {
        OperatorsRepository operatorsRepository = new OperatorsRepository() {
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                return createOperators();
            }
        };
        AccountLocksRepository accountLocksRepository = new AccountLocksRepository() {
            @Override
            public AccountLocks selectBy(AccountLockCriteria accountLockrCriteria, Orders orders) {
                return createAccountLocks();
            }
        };
        PasswordHistoriesRepository passwordHistoriesRepository = new PasswordHistoriesRepository() {
            @Override
            public PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria, Orders orders) {
                return createPasswordHistories();
            }
        };
        SignInTracesRepository signInTracesRepository = new SignInTracesRepository() {
            @Override
            public SignInTraces selectBy(SignInTraceCriteria signInTraceCriteria, Orders orders) {
                return createSignInTraces();
            }
        };
        SignOutTracesRepository signOutTracesRepository = new SignOutTracesRepository() {
            @Override
            public SignOutTraces selectBy(SignOutTraceCriteria signOutTraceCriteria, Orders orders) {
                return createSignOutTraces();
            }
        };
        Operator_SubSystemRolesRepository operator_SubSystemRolesRepository = new Operator_SubSystemRolesRepository() {
            @Override
            public Operator_SubSystemRoles selectBy(Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {
                return createOperator_SubSystemRoles();
            }
        };
        Operator_BizTranRolesRepository operator_BizTranRolesRepository = new Operator_BizTranRolesRepository() {
            @Override
            public Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders) {
                return createOperator_BizTranRoles();
            }
        };
        OperatorHistoryHeadersRepository operatorHistoryHeadersRepository = new OperatorHistoryHeadersRepository() {
            @Override
            public OperatorHistoryHeaders selectBy(OperatorHistoryHeaderCriteria operatorHistoryHeaderCriteria, Orders orders) {
                return createOperatorHistoryHeaders();
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
        OperatorEntityDao operatorEntityDao = new OperatorEntityDao() {
            @Override
            public List<OperatorEntity> findAll(Orders orders) {
                return null;
            }
            @Override
            public OperatorEntity findOneBy(OperatorEntityCriteria criteria) {
                return null;
            }
            @Override
            public List<OperatorEntity> findBy(OperatorEntityCriteria criteria, Orders orders) {
                return null;
            }
            @Override
            public int countBy(OperatorEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int insert(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int update(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int updateExcludeNull(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int delete(OperatorEntity entity) {
                return 0;
            }
            @Override
            public int forceDelete(OperatorEntityCriteria criteria) {
                return 0;
            }
            @Override
            public int[] insertBatch(List<OperatorEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] updateBatch(List<OperatorEntity> entities) {
                return new int[0];
            }
            @Override
            public int[] deleteBatch(List<OperatorEntity> entities) {
                return new int[0];
            }
        };
        return new SearchOperator(operatorsRepository,
            accountLocksRepository,
            passwordHistoriesRepository,
            signInTracesRepository,
            signOutTracesRepository,
            operator_SubSystemRolesRepository,
            operator_BizTranRolesRepository,
            operatorHistoryHeadersRepository);
    }

    // オペレーター群作成
    private Operators createOperators() {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","ｙｕ００１００９","yu001009@aaaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),false,6L,"006",33L,"001",AvailableStatus.利用可能 ,1,null));
        list.add(Operator.createFrom(19L,"yu001010","ｙｕ００１０１０","yu001010@aaaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),false,6L,"006",33L,"001",AvailableStatus.利用可能 ,1,null));
        list.add(Operator.createFrom(20L,"yu001011","ｙｕ００１０１１","yu001011@aaaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),false,6L,"006",33L,"001",AvailableStatus.利用可能 ,1,null));
        return Operators.createFrom(list);
    }
    // アカウントロック群作成
    private AccountLocks createAccountLocks() {
        List<AccountLock> list = newArrayList();
        list.add(AccountLock.createFrom(1L,18L,LocalDateTime.of(2020,10,1,8,31,12),(short) 0,0,null));
        list.add(AccountLock.createFrom(2L,18L,LocalDateTime.of(2020,10,1,8,30,12),(short) 1,0,null));
        list.add(AccountLock.createFrom(3L,19L,LocalDateTime.of(2020,10,1,8,30,23),(short) 1,0,null));
        return AccountLocks.createFrom(list);
    }
    // パスワード履歴群作成
    private PasswordHistories createPasswordHistories() {
        List<PasswordHistory> list = newArrayList();
        list.add(PasswordHistory.createFrom(1L,18L,LocalDateTime.of(2020,10,1,8,31,12),"12345678",(short) 1,0,null));
        list.add(PasswordHistory.createFrom(2L,18L,LocalDateTime.of(2020,10,1,8,30,12),"abcdefgh",(short) 1,1,null));
        return PasswordHistories.createFrom(list);
    }
    // サインイン証跡群作成
    private SignInTraces createSignInTraces() {
        List<SignInTrace> list = newArrayList();
        list.add(SignInTrace.createFrom(1L,LocalDateTime.of(2020,10,2,9,0,12),"001.001.001.001","yu001009",SignInCause.サインイン.getCode(),SignInResult.失敗_存在しないオペレーター.getCode(),1,null));
        list.add(SignInTrace.createFrom(2L,LocalDateTime.of(2020,10,2,9,1,34),"001.001.001.001","yu001010",SignInCause.サインイン.getCode(),SignInResult.成功.getCode(),1,null));
        list.add(SignInTrace.createFrom(3L,LocalDateTime.of(2020,10,2,9,0,34),"001.001.001.001","yu001010",SignInCause.サインイン.getCode(),SignInResult.失敗_パスワード誤り.getCode(),1,null));
        return SignInTraces.createFrom(list);
    }
    // サインアウト証跡群作成
    private SignOutTraces createSignOutTraces() {
        List<SignOutTrace> list = newArrayList();
        list.add(SignOutTrace.createFrom(1L,LocalDateTime.of(2020,10,3,9,0,23),"001.001.001.001",18L,1,null));
        list.add(SignOutTrace.createFrom(2L,LocalDateTime.of(2020,10,3,9,1,45),"001.001.001.001",19L,1,null));
        return SignOutTraces.createFrom(list);
    }
    // オペレーター_サブシステムロール割当群作成
    private Operator_SubSystemRoles createOperator_SubSystemRoles() {
        List<Operator_SubSystemRole> list = newArrayList();
        list.add(Operator_SubSystemRole.createFrom(1L,18L,SubSystemRole.業務統括者_購買.getCode(),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,SubSystemRole.業務統括者_購買));
        list.add(Operator_SubSystemRole.createFrom(2L,19L,SubSystemRole.業務統括者_販売_青果.getCode(),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,SubSystemRole.業務統括者_販売_青果));
        return Operator_SubSystemRoles.createFrom(list);
    }
    // オペレーター_取引ロール割当群作成
    private Operator_BizTranRoles createOperator_BizTranRoles() {
        List<Operator_BizTranRole> list = newArrayList();
        list.add(Operator_BizTranRole.createFrom(1L,18L,1L,LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,createBizTranRole(1L)));
        list.add(Operator_BizTranRole.createFrom(2L,18L,2L,LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,createBizTranRole(2L)));
        list.add(Operator_BizTranRole.createFrom(3L,19L,2L,LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,createBizTranRole(3L)));
        list.add(Operator_BizTranRole.createFrom(4L,19L,3L,LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,createBizTranRole(4L)));
        return Operator_BizTranRoles.createFrom(list);
    }
    // オペレーター履歴ヘッダー群作成
    private OperatorHistoryHeaders createOperatorHistoryHeaders() {
        List<OperatorHistoryHeader> list = newArrayList();
        list.add(OperatorHistoryHeader.createFrom(1L,18L,LocalDateTime.of(2020,10,1,8,30,12),"変更事由１行目",1,null));
        list.add(OperatorHistoryHeader.createFrom(2L,18L,LocalDateTime.of(2020,10,1,8,31,12),"変更事由２行目",1,null));
        return OperatorHistoryHeaders.createFrom(list);
    }
    // 取引ロール作成
    private BizTranRole createBizTranRole(Long id) {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(1L,"KB0000","購買メインメニュー","KB",1,SubSystem.購買));
        list.add(BizTranRole.createFrom(2L,"KB00001","支所検索","KB",1,SubSystem.購買));
        list.add(BizTranRole.createFrom(3L,"KB0002","顧客検索","KB",1,SubSystem.購買));
        list.add(BizTranRole.createFrom(4L,"YS0000","野菜メインメニュー","YS",1,SubSystem.販売_青果));
        return list.stream().filter(b->b.getBizTranRoleId().equals(id)).findFirst().orElse(null);
    }
    // 店舗群AtMoment作成
    private BranchesAtMoment createBranchesAtMoment() {
        List<BranchAtMoment> branchAtMomentList = newArrayList();
        branchAtMomentList.add(BranchAtMoment.builder()
            .withIdentifier(33L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(
                BranchAttribute.builder()
                    .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("001")).withName("店舗001").build())
            .build());
        branchAtMomentList.add(BranchAtMoment.builder()
            .withIdentifier(35L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("003")).withName("店舗003").build())
            .build());
        branchAtMomentList.add(BranchAtMoment.builder()
            .withIdentifier(36L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("004")).withName("店舗004").build())
            .build());
        return BranchesAtMoment.of(branchAtMomentList);
    }

    // オペレーター検索リクエスト作成
    private OperatorSearchRequest createOperatorSearchRequest(){
        OperatorSearchRequest request = new OperatorSearchRequest() {
            @Override
            public LongCriteria getOperatorIdCriteria() {
                return operatorIdCriteria;
            }
            @Override
            public StringCriteria getOperatorCodeCriteria() {
                return operatorCodeCriteria;
            }
            @Override
            public StringCriteria getOperatorNameCriteria() {
                return operatorNameCriteria;
            }
            @Override
            public StringCriteria getMailAddressCriteria() {
                return mailAddressCriteria;
            }
            @Override
            public LocalDateCriteria getExpirationStartDateCriteria() {
                return expirationStartDateCriteria;
            }
            @Override
            public LocalDateCriteria getExpirationEndDateCriteria() {
                return expirationEndDateCriteria;
            }
            @Override
            public BooleanCriteria getIsDeviceAuthCriteria() {
                return isDeviceAuthCriteria;
            }
            @Override
            public LongCriteria getJaIdCriteria() {
                return jaIdCriteria;
            }
            @Override
            public LongCriteria getBranchIdCriteria() {
                return branchIdCriteria;
            }
            @Override
            public ShortCriteria getAvailableStatusCriteria() {
                return availableStatusCriteria;
            }
            @Override
            public Integer getSubSystemRoleConditionsSelect() {
                return subSystemRoleConditionsSelect;
            }
            @Override
            public List<OparatorSearchSubSystemRoleRequest> getSubSystemRoleList() {
                return subSystemRoleList;
            }
            @Override
            public Integer getBizTranRoleConditionsSelect() {
                return bizTranRoleConditionsSelect;
            }
            @Override
            public String getBizTranRoleSubSystemCode() {
                return bizTranRoleSubSystemCode;
            }
            @Override
            public List<OparatorSearchBizTranRoleRequest> getBizTranRoleList() {
                return bizTranRoleList;
            }
            @Override
            public LocalDate getAccountLockOccurredDateFrom() {
                return accountLockOccurredDateFrom;
            }
            @Override
            public LocalDate getAccountLockOccurredDateTo() {
                return accountLockOccurredDateTo;
            }
            @Override
            public Short getAccountLockStatusLock() {
                return accountLockStatusLock;
            }
            @Override
            public Short getAccountLockStatusUnlock() {
                return accountLockStatusUnlock;
            }
            @Override
            public Short getPasswordHistoryCheck() {
                return passwordHistoryCheck;
            }
            @Override
            public Integer getPasswordHistoryLastChangeDate() {
                return passwordHistoryLastChangeDate;
            }
            @Override
            public String getPasswordHistoryLastChangeDateStatus() {
                return passwordHistoryLastChangeDateStatus;
            }
            @Override
            public Short getPasswordHistoryChangeType0() {
                return passwordHistoryChangeType0;
            }
            @Override
            public Short getPasswordHistoryChangeType1() {
                return passwordHistoryChangeType1;
            }
            @Override
            public Short getPasswordHistoryChangeType2() {
                return passwordHistoryChangeType2;
            }
            @Override
            public Short getPasswordHistoryChangeType3() {
                return passwordHistoryChangeType3;
            }
            @Override
            public LocalDate getSignintraceTrydateFrom() {
                return signintraceTrydateFrom;
            }
            @Override
            public LocalDate getSignintraceTrydateTo() {
                return signintraceTrydateTo;
            }
            @Override
            public String getSignintraceTryIpAddress() {
                return signintraceTryIpAddress;
            }
            @Override
            public Short getSignintraceSignIn() {
                return signintraceSignIn;
            }
            @Override
            public Short getSignintraceSignOut() {
                return signintraceSignOut;
            }
            @Override
            public Short[] getSignintraceSignInResult() {
                return signintraceSignInResult;
            }
        };
        return request;
    }

    // サブシステムローロール検索条件作成
    private List<Oa11010SearchSubSystemRoleConverter> createOa11010SearchSubSystemRoleConverterList() {
        List<Oa11010SearchSubSystemRoleConverter> list = newArrayList();
        list.add(Oa11010SearchSubSystemRoleConverter.with((short) 1,SubSystemRole.JA管理者.getCode(),SubSystemRole.JA管理者.getName(),1,LocalDate.of(2020,1,1),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31)));
        list.add(Oa11010SearchSubSystemRoleConverter.with((short) 1,SubSystemRole.業務統括者_購買.getCode(),SubSystemRole.業務統括者_購買.getName(),1,LocalDate.of(2020,1,1),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31)));
        list.add(Oa11010SearchSubSystemRoleConverter.with((short) 1,SubSystemRole.業務統括者_販売_青果.getCode(),SubSystemRole.業務統括者_販売_青果.getName(),1,LocalDate.of(2020,1,1),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31)));
        return list;
    }

    /**
     * {@link SearchOperator#execute(OperatorSearchRequest, OperatorSearchResponse)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test0() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 期待値
        Operators expectedOperators = createOperators();
        BranchesAtMoment expectedBranchesAtMoment = createBranchesAtMoment();
        AccountLocks expectedAccountLocks = createAccountLocks();
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = createOperator_SubSystemRoles();
        Operator_BizTranRoles expectedOperator_BizTranRoles = createOperator_BizTranRoles();
        OperatorHistoryHeaders expectedOperatorHistoryHeaders = createOperatorHistoryHeaders();

        // 実行 & 結果検証
        searchOperator.execute(createOperatorSearchRequest(),
            new OperatorSearchResponse() {
                @Override
                public void setOperators(Operators actualOperators) {
                    // 結果検証
                    for(int i = 0; i < actualOperators.getValues().size(); i++) {
                        assertThat(actualOperators.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperators.getValues().get(i));
                    }
                }
                @Override
                public void setAccountLocks(AccountLocks actualAccountLocks) {
                    // 結果検証
                    for(int i = 0; i < actualAccountLocks.getValues().size(); i++) {
                        assertThat(actualAccountLocks.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedAccountLocks.getValues().get(i));
                    }
                }
                @Override
                public void setOperator_SubSystemRoles(Operator_SubSystemRoles actualOperator_SubSystemRoles) {
                    // 結果検証
                    for(int i = 0; i < actualOperator_SubSystemRoles.getValues().size(); i++) {
                        assertThat(actualOperator_SubSystemRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles.getValues().get(i));
                    }
                }
                @Override
                public void setOperator_BizTranRoles(Operator_BizTranRoles actualOperator_BizTranRoles) {
                    // 結果検証
                    for(int i = 0; i < actualOperator_BizTranRoles.getValues().size(); i++) {
                        assertThat(actualOperator_BizTranRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoles.getValues().get(i));
                    }
                }
                @Override
                public void setOperatorHistoryHeaders(OperatorHistoryHeaders operatorHistoryHeaders) {
                    // 結果検証
                    for(int i = 0; i < operatorHistoryHeaders.getValues().size(); i++) {
                        assertThat(operatorHistoryHeaders.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperatorHistoryHeaders.getValues().get(i));
                    }
                }
            });
    }

    /**
     * {@link SearchOperator#execute(OperatorSearchRequest, OperatorSearchResponse)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・オペレーターコード、オペレーター名、メールアドレス、
     *    ・JAID、店舗ID、
     *    ・利用可否状態、有効期限（利用可能）
     *    ・サインアウト証跡
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test1() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        operatorCodeCriteria.setForwardMatch("y");
        operatorNameCriteria.setForwardMatch("ｙ");
        mailAddressCriteria.setForwardMatch("y");
        jaIdCriteria.setEqualTo(6L);
        branchIdCriteria.setEqualTo(33L);
        expirationStartDateCriteria.setLessOrEqual(LocalDate.of(2020, 10, 1));
        expirationEndDateCriteria.setMoreOrEqual(LocalDate.of(2020, 10, 1));
        availableStatusIncludesList = newArrayList();
        availableStatusIncludesList.add((short) 0); //利用可能
        signintraceSignOut = (short) 1;

        // 期待値
        Operators expectedOperators = createOperators();
        BranchesAtMoment expectedBranchesAtMoment = createBranchesAtMoment();
        AccountLocks expectedAccountLocks = createAccountLocks();
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = createOperator_SubSystemRoles();
        Operator_BizTranRoles expectedOperator_BizTranRoles = createOperator_BizTranRoles();
        OperatorHistoryHeaders expectedOperatorHistoryHeaders = createOperatorHistoryHeaders();

        // 実行 & 結果検証
        searchOperator.execute(createOperatorSearchRequest(),
            new OperatorSearchResponse() {
                @Override
                public void setOperators(Operators actualOperators) {
                    // 結果検証
                    for(int i = 0; i < actualOperators.getValues().size(); i++) {
                        assertThat(actualOperators.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperators.getValues().get(i));
                    }
                }
                @Override
                public void setAccountLocks(AccountLocks actualAccountLocks) {
                    // 結果検証
                    for(int i = 0; i < actualAccountLocks.getValues().size(); i++) {
                        assertThat(actualAccountLocks.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedAccountLocks.getValues().get(i));
                    }
                }
                @Override
                public void setOperator_SubSystemRoles(Operator_SubSystemRoles actualOperator_SubSystemRoles) {
                    // 結果検証
                    for(int i = 0; i < actualOperator_SubSystemRoles.getValues().size(); i++) {
                        assertThat(actualOperator_SubSystemRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles.getValues().get(i));
                    }
                }
                @Override
                public void setOperator_BizTranRoles(Operator_BizTranRoles actualOperator_BizTranRoles) {
                    // 結果検証
                    for(int i = 0; i < actualOperator_BizTranRoles.getValues().size(); i++) {
                        assertThat(actualOperator_BizTranRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoles.getValues().get(i));
                    }
                }
                @Override
                public void setOperatorHistoryHeaders(OperatorHistoryHeaders operatorHistoryHeaders) {
                    // 結果検証
                    for(int i = 0; i < operatorHistoryHeaders.getValues().size(); i++) {
                        assertThat(operatorHistoryHeaders.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperatorHistoryHeaders.getValues().get(i));
                    }
                }
            });
    }

    /**
     * {@link SearchOperator#execute(OperatorSearchRequest, OperatorSearchResponse)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・サインイン証跡　最終サインオペレーション
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test2() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        signintraceSignIn = (short) 1;
        signintraceSignInResult = new Short[(short) 1];

        // 期待値
        Operators expectedOperators = createOperators();
        BranchesAtMoment expectedBranchesAtMoment = createBranchesAtMoment();
        AccountLocks expectedAccountLocks = createAccountLocks();
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = createOperator_SubSystemRoles();
        Operator_BizTranRoles expectedOperator_BizTranRoles = createOperator_BizTranRoles();
        OperatorHistoryHeaders expectedOperatorHistoryHeaders = createOperatorHistoryHeaders();

        // 実行 & 結果検証
        searchOperator.execute(createOperatorSearchRequest(),
            new OperatorSearchResponse() {
                @Override
                public void setOperators(Operators actualOperators) {
                    // 結果検証
                    for(int i = 0; i < actualOperators.getValues().size(); i++) {
                        assertThat(actualOperators.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperators.getValues().get(i));
                    }
                }
                @Override
                public void setAccountLocks(AccountLocks actualAccountLocks) {
                    // 結果検証
                    for(int i = 0; i < actualAccountLocks.getValues().size(); i++) {
                        assertThat(actualAccountLocks.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedAccountLocks.getValues().get(i));
                    }
                }
                @Override
                public void setOperator_SubSystemRoles(Operator_SubSystemRoles actualOperator_SubSystemRoles) {
                    // 結果検証
                    for(int i = 0; i < actualOperator_SubSystemRoles.getValues().size(); i++) {
                        assertThat(actualOperator_SubSystemRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles.getValues().get(i));
                    }
                }
                @Override
                public void setOperator_BizTranRoles(Operator_BizTranRoles actualOperator_BizTranRoles) {
                    // 結果検証
                    for(int i = 0; i < actualOperator_BizTranRoles.getValues().size(); i++) {
                        assertThat(actualOperator_BizTranRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoles.getValues().get(i));
                    }
                }

                @Override
                public void setOperatorHistoryHeaders(OperatorHistoryHeaders operatorHistoryHeaders) {
                    // 結果検証
                    for(int i = 0; i < operatorHistoryHeaders.getValues().size(); i++) {
                        assertThat(operatorHistoryHeaders.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperatorHistoryHeaders.getValues().get(i));
                    }

                }
            });
    }

    /**
     * {@link SearchOperator#execute(OperatorSearchRequest, OperatorSearchResponse)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・パスワード履歴　最終パスワード変更日
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test3() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryLastChangeDateStatus = "1";
        passwordHistoryLastChangeDate = 30;

        // 期待値
        Operators expectedOperators = createOperators();
        BranchesAtMoment expectedBranchesAtMoment = createBranchesAtMoment();
        AccountLocks expectedAccountLocks = createAccountLocks();
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = createOperator_SubSystemRoles();
        Operator_BizTranRoles expectedOperator_BizTranRoles = createOperator_BizTranRoles();
        OperatorHistoryHeaders expectedOperatorHistoryHeaders = createOperatorHistoryHeaders();

        // 実行 & 結果検証
        searchOperator.execute(createOperatorSearchRequest(),
            new OperatorSearchResponse() {
                @Override
                public void setOperators(Operators actualOperators) {
                    // 結果検証
                    for(int i = 0; i < actualOperators.getValues().size(); i++) {
                        assertThat(actualOperators.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperators.getValues().get(i));
                    }
                }
                @Override
                public void setAccountLocks(AccountLocks actualAccountLocks) {
                    // 結果検証
                    for(int i = 0; i < actualAccountLocks.getValues().size(); i++) {
                        assertThat(actualAccountLocks.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedAccountLocks.getValues().get(i));
                    }
                }
                @Override
                public void setOperator_SubSystemRoles(Operator_SubSystemRoles actualOperator_SubSystemRoles) {
                    // 結果検証
                    for(int i = 0; i < actualOperator_SubSystemRoles.getValues().size(); i++) {
                        assertThat(actualOperator_SubSystemRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles.getValues().get(i));
                    }
                }
                @Override
                public void setOperator_BizTranRoles(Operator_BizTranRoles actualOperator_BizTranRoles) {
                    // 結果検証
                    for(int i = 0; i < actualOperator_BizTranRoles.getValues().size(); i++) {
                        assertThat(actualOperator_BizTranRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoles.getValues().get(i));
                    }
                }
                @Override
                public void setOperatorHistoryHeaders(OperatorHistoryHeaders operatorHistoryHeaders) {
                    // 結果検証
                    for(int i = 0; i < operatorHistoryHeaders.getValues().size(); i++) {
                        assertThat(operatorHistoryHeaders.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperatorHistoryHeaders.getValues().get(i));
                    }
                }
            });
    }

    /**
     * {@link SearchOperator#execute(OperatorSearchRequest, OperatorSearchResponse)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・アカウントロック　最終ロック・アンロック発生日
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test4() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        accountLockOccurredDateFrom = LocalDate.of(2020, 10, 1);
        accountLockOccurredDateTo = LocalDate.of(2020, 10, 31);

        // 期待値
        Operators expectedOperators = createOperators();
        BranchesAtMoment expectedBranchesAtMoment = createBranchesAtMoment();
        AccountLocks expectedAccountLocks = createAccountLocks();
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = createOperator_SubSystemRoles();
        Operator_BizTranRoles expectedOperator_BizTranRoles = createOperator_BizTranRoles();
        OperatorHistoryHeaders expectedOperatorHistoryHeaders = createOperatorHistoryHeaders();

        // 実行 & 結果検証
        searchOperator.execute(createOperatorSearchRequest(),
            new OperatorSearchResponse() {
                @Override
                public void setOperators(Operators actualOperators) {
                    // 結果検証
                    for(int i = 0; i < actualOperators.getValues().size(); i++) {
                        assertThat(actualOperators.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperators.getValues().get(i));
                    }
                }
                @Override
                public void setAccountLocks(AccountLocks actualAccountLocks) {
                    // 結果検証
                    for(int i = 0; i < actualAccountLocks.getValues().size(); i++) {
                        assertThat(actualAccountLocks.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedAccountLocks.getValues().get(i));
                    }
                }
                @Override
                public void setOperator_SubSystemRoles(Operator_SubSystemRoles actualOperator_SubSystemRoles) {
                    // 結果検証
                    for(int i = 0; i < actualOperator_SubSystemRoles.getValues().size(); i++) {
                        assertThat(actualOperator_SubSystemRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles.getValues().get(i));
                    }
                }
                @Override
                public void setOperator_BizTranRoles(Operator_BizTranRoles actualOperator_BizTranRoles) {
                    // 結果検証
                    for(int i = 0; i < actualOperator_BizTranRoles.getValues().size(); i++) {
                        assertThat(actualOperator_BizTranRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoles.getValues().get(i));
                    }
                }
                @Override
                public void setOperatorHistoryHeaders(OperatorHistoryHeaders operatorHistoryHeaders) {
                    // 結果検証
                    for(int i = 0; i < operatorHistoryHeaders.getValues().size(); i++) {
                        assertThat(operatorHistoryHeaders.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperatorHistoryHeaders.getValues().get(i));
                    }
                }
            });
    }

    /**
     * {@link SearchOperator#execute(OperatorSearchRequest, OperatorSearchResponse)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・オペレーター_取引ロール割当
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test5() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        bizTranRoleConditionsSelect = 1;    //AND;
        List<OparatorSearchBizTranRoleRequest> list = newArrayList();
        list.add(Oa11010SearchBizTranRoleConverter.with(Oa11010Vo.CHECKBOX_TRUE,1L,"KBAG01","（購買）購買業務基本","KB",1,LocalDate.of(2020,10,1),null,null,null,null));
        list.add(Oa11010SearchBizTranRoleConverter.with(Oa11010Vo.CHECKBOX_TRUE,2L,"KBAG02","（購買）本所業務","KB",2,null,LocalDate.of(2020,10,1),LocalDate.of(2020,10,31),LocalDate.of(2020,10,1),LocalDate.of(2020,10,31)));
        bizTranRoleList = list;

        // 期待値
        Operators expectedOperators = createOperators();
        BranchesAtMoment expectedBranchesAtMoment = createBranchesAtMoment();
        AccountLocks expectedAccountLocks = createAccountLocks();
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = createOperator_SubSystemRoles();
        Operator_BizTranRoles expectedOperator_BizTranRoles = createOperator_BizTranRoles();
        OperatorHistoryHeaders expectedOperatorHistoryHeaders = createOperatorHistoryHeaders();

        // 実行 & 結果検証
        searchOperator.execute(createOperatorSearchRequest(),
            new OperatorSearchResponse() {
                @Override
                public void setOperators(Operators actualOperators) {
                    // 結果検証
                    for(int i = 0; i < actualOperators.getValues().size(); i++) {
                        assertThat(actualOperators.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperators.getValues().get(i));
                    }
                }
                @Override
                public void setAccountLocks(AccountLocks actualAccountLocks) {
                    // 結果検証
                    for(int i = 0; i < actualAccountLocks.getValues().size(); i++) {
                        assertThat(actualAccountLocks.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedAccountLocks.getValues().get(i));
                    }
                }
                @Override
                public void setOperator_SubSystemRoles(Operator_SubSystemRoles actualOperator_SubSystemRoles) {
                    // 結果検証
                    for(int i = 0; i < actualOperator_SubSystemRoles.getValues().size(); i++) {
                        assertThat(actualOperator_SubSystemRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles.getValues().get(i));
                    }
                }
                @Override
                public void setOperator_BizTranRoles(Operator_BizTranRoles actualOperator_BizTranRoles) {
                    // 結果検証
                    for(int i = 0; i < actualOperator_BizTranRoles.getValues().size(); i++) {
                        assertThat(actualOperator_BizTranRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoles.getValues().get(i));
                    }
                }
                @Override
                public void setOperatorHistoryHeaders(OperatorHistoryHeaders operatorHistoryHeaders) {
                    // 結果検証
                    for(int i = 0; i < operatorHistoryHeaders.getValues().size(); i++) {
                        assertThat(operatorHistoryHeaders.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperatorHistoryHeaders.getValues().get(i));
                    }
                }
            });
    }

    /**
     * {@link SearchOperator#execute(OperatorSearchRequest, OperatorSearchResponse)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・オペレーター_サブシステムロール割当
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void execute_test6() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        subSystemRoleConditionsSelect = 1;  //AND
        List<OparatorSearchSubSystemRoleRequest> list = newArrayList();
        list.add(Oa11010SearchSubSystemRoleConverter.with(Oa11010Vo.CHECKBOX_TRUE,SubSystemRole.業務統括者_購買.getCode(),SubSystemRole.業務統括者_購買.getName(),1,LocalDate.of(2020,10,1),null,null,null,null));
        list.add(Oa11010SearchSubSystemRoleConverter.with(Oa11010Vo.CHECKBOX_TRUE,SubSystemRole.業務統括者_販売_青果.getCode(),SubSystemRole.業務統括者_販売_青果.getName(),2,null,LocalDate.of(2020,10,1),LocalDate.of(2020,10,1),LocalDate.of(2020,10,1),LocalDate.of(2020,10,1)));
        subSystemRoleList = list;

        // 期待値
        Operators expectedOperators = createOperators();
        BranchesAtMoment expectedBranchesAtMoment = createBranchesAtMoment();
        AccountLocks expectedAccountLocks = createAccountLocks();
        Operator_SubSystemRoles expectedOperator_SubSystemRoles = createOperator_SubSystemRoles();
        Operator_BizTranRoles expectedOperator_BizTranRoles = createOperator_BizTranRoles();
        OperatorHistoryHeaders expectedOperatorHistoryHeaders = createOperatorHistoryHeaders();

        // 実行 & 結果検証
        searchOperator.execute(createOperatorSearchRequest(),
            new OperatorSearchResponse() {
                @Override
                public void setOperators(Operators actualOperators) {
                    // 結果検証
                    for(int i = 0; i < actualOperators.getValues().size(); i++) {
                        assertThat(actualOperators.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperators.getValues().get(i));
                    }
                }
                @Override
                public void setAccountLocks(AccountLocks actualAccountLocks) {
                    // 結果検証
                    for(int i = 0; i < actualAccountLocks.getValues().size(); i++) {
                        assertThat(actualAccountLocks.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedAccountLocks.getValues().get(i));
                    }
                }
                @Override
                public void setOperator_SubSystemRoles(Operator_SubSystemRoles actualOperator_SubSystemRoles) {
                    // 結果検証
                    for(int i = 0; i < actualOperator_SubSystemRoles.getValues().size(); i++) {
                        assertThat(actualOperator_SubSystemRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperator_SubSystemRoles.getValues().get(i));
                    }
                }
                @Override
                public void setOperator_BizTranRoles(Operator_BizTranRoles actualOperator_BizTranRoles) {
                    // 結果検証
                    for(int i = 0; i < actualOperator_BizTranRoles.getValues().size(); i++) {
                        assertThat(actualOperator_BizTranRoles.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperator_BizTranRoles.getValues().get(i));
                    }
                }
                @Override
                public void setOperatorHistoryHeaders(OperatorHistoryHeaders operatorHistoryHeaders) {
                    // 結果検証
                    for(int i = 0; i < operatorHistoryHeaders.getValues().size(); i++) {
                        assertThat(operatorHistoryHeaders.getValues().get(i)).as(i + 1 + "レコード目でエラー")
                            .usingRecursiveComparison().isEqualTo(expectedOperatorHistoryHeaders.getValues().get(i));
                    }
                }
            });
    }

    /**
     * {@link SearchOperator#conditionsOperatorSubSystemRole(OperatorSearchRequest, List<Operator_SubSystemRole> )}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・オペレーター_サブシステムロール割当の検索条件判定 検索条件選択=OR
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsOperatorSubSystemRole_test0() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        subSystemRoleConditionsSelect = 2;  //OR
        List<OparatorSearchSubSystemRoleRequest> list = newArrayList();
        list.add(Oa11010SearchSubSystemRoleConverter.with(Oa11010Vo.CHECKBOX_TRUE,SubSystemRole.業務統括者_購買.getCode(),SubSystemRole.業務統括者_購買.getName(),2,null,LocalDate.of(2020,10,1),LocalDate.of(2020,10,31),LocalDate.of(2020,10,1),LocalDate.of(2020,10,31)));
        list.add(Oa11010SearchSubSystemRoleConverter.with(Oa11010Vo.CHECKBOX_TRUE,SubSystemRole.業務統括者_販売_青果.getCode(),SubSystemRole.業務統括者_販売_青果.getName(),2,null,LocalDate.of(2020,10,1),LocalDate.of(2020,10,31),LocalDate.of(2020,10,1),LocalDate.of(2020,10,31)));
        list.add(Oa11010SearchSubSystemRoleConverter.with(Oa11010Vo.CHECKBOX_TRUE,SubSystemRole.業務統括者_販売_畜産.getCode(),SubSystemRole.業務統括者_販売_畜産.getName(),0,null,LocalDate.of(2020,10,1),LocalDate.of(2020,10,31),LocalDate.of(2020,10,1),LocalDate.of(2020,10,31)));
        subSystemRoleList = list;
        List<Operator_SubSystemRole> operatorSubSystemRoleList = newArrayList();
        operatorSubSystemRoleList.add(Operator_SubSystemRole.createFrom(1L,18L,SubSystemRole.業務統括者_購買.getCode(),LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),1,null,null));
        operatorSubSystemRoleList.add(Operator_SubSystemRole.createFrom(2L,18L,SubSystemRole.業務統括者_販売_青果.getCode(),LocalDate.of(2020, 11, 1),LocalDate.of(9999, 12, 31),1,null,null));
        operatorSubSystemRoleList.add(Operator_SubSystemRole.createFrom(3L,19L,SubSystemRole.業務統括者_販売_青果.getCode(),LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),1,null,null));
        operatorSubSystemRoleList.add(Operator_SubSystemRole.createFrom(4L,20L,SubSystemRole.業務統括者_販売_畜産.getCode(),LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),1,null,null));

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsOperatorSubSystemRole(createOperatorSearchRequest(), operatorSubSystemRoleList);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsOperatorSubSystemRole(OperatorSearchRequest, List<Operator_SubSystemRole> )}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・オペレーター_サブシステムロール割当の検索条件判定 検索条件選択=OR & 指定なし
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsOperatorSubSystemRole_test1() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        subSystemRoleConditionsSelect = 2;  //OR
        List<OparatorSearchSubSystemRoleRequest> list = newArrayList();
        list.add(Oa11010SearchSubSystemRoleConverter.with(Oa11010Vo.CHECKBOX_TRUE,SubSystemRole.業務統括者_販売_畜産.getCode(),SubSystemRole.業務統括者_販売_畜産.getName(),0,null,LocalDate.of(2020,10,1),LocalDate.of(2020,10,31),LocalDate.of(2020,10,1),LocalDate.of(2020,10,31)));
        subSystemRoleList = list;
        List<Operator_SubSystemRole> operatorSubSystemRoleList = newArrayList();
        operatorSubSystemRoleList.add(Operator_SubSystemRole.createFrom(4L,20L,SubSystemRole.業務統括者_販売_畜産.getCode(),LocalDate.of(2020, 1, 1),LocalDate.of(9999, 12, 31),1,null,null));

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsOperatorSubSystemRole(createOperatorSearchRequest(), operatorSubSystemRoleList);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsOperatorSubSystemRole(OperatorSearchRequest, List<Operator_SubSystemRole> )}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・オペレーター_サブシステムロール割当の検索条件判定 検索条件選択=OR & サブシステムロール条件不一致
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsOperatorSubSystemRole_test2() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        subSystemRoleConditionsSelect = 2;  //OR
        List<OparatorSearchSubSystemRoleRequest> list = newArrayList();
        list.add(Oa11010SearchSubSystemRoleConverter.with(Oa11010Vo.CHECKBOX_TRUE,SubSystemRole.業務統括者_販売_畜産.getCode(),SubSystemRole.業務統括者_販売_畜産.getName(),1,LocalDate.of(2020,10,1),null,null,null,null));
        subSystemRoleList = list;
        List<Operator_SubSystemRole> operatorSubSystemRoleList = newArrayList();
        operatorSubSystemRoleList.add(Operator_SubSystemRole.createFrom(4L,20L,SubSystemRole.業務統括者_販売_畜産.getCode(),LocalDate.of(2020, 1, 1),LocalDate.of(2020, 8, 31),1,null,null));

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator.conditionsOperatorSubSystemRole(createOperatorSearchRequest(), operatorSubSystemRoleList);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsOperatorSubSystemRole(OperatorSearchRequest, List<Operator_SubSystemRole> )}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・オペレーター_サブシステムロール割当の検索条件判定 検索条件選択=OR & 有効期限検索条=UnKnown
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsOperatorSubSystemRole_test3() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        subSystemRoleConditionsSelect = 2;  //OR
        List<OparatorSearchSubSystemRoleRequest> list = newArrayList();
        list.add(Oa11010SearchSubSystemRoleConverter.with(Oa11010Vo.CHECKBOX_TRUE,SubSystemRole.業務統括者_販売_畜産.getCode(),SubSystemRole.業務統括者_販売_畜産.getName(),-1,LocalDate.of(2020,10,1),null,null,null,null));
        subSystemRoleList = list;
        List<Operator_SubSystemRole> operatorSubSystemRoleList = newArrayList();
        operatorSubSystemRoleList.add(Operator_SubSystemRole.createFrom(4L,20L,SubSystemRole.業務統括者_販売_畜産.getCode(),LocalDate.of(2020, 1, 1),LocalDate.of(2020, 8, 31),1,null,null));

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsOperatorSubSystemRole(createOperatorSearchRequest(), operatorSubSystemRoleList);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsOperatorBizTranRole(OperatorSearchRequest, List<Operator_BizTranRole>)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・オペレーター_取引ロール割当の検索条件判定 検索条件選択=OR
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsOperatorBizTranRole_test0() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        bizTranRoleConditionsSelect = 2;  //OR
        List<OparatorSearchBizTranRoleRequest> list = newArrayList();
        list.add(Oa11010SearchBizTranRoleConverter.with(Oa11010Vo.CHECKBOX_TRUE, 1L, "KBAG01", "（購買）購買業務基本", "KB",0, null, null, null, null, null));
        list.add(Oa11010SearchBizTranRoleConverter.with(Oa11010Vo.CHECKBOX_TRUE, 2L, "KBAG02", "（購買）本所業務", "KB",2, null, LocalDate.of(2020, 10, 1),LocalDate.of(2020, 10, 31), LocalDate.of(2020, 10, 1), LocalDate.of(2020, 10, 31)));
        bizTranRoleList = list;
        List<Operator_BizTranRole> operatorBizTranRoleList = newArrayList();
        operatorBizTranRoleList.add(Operator_BizTranRole.createFrom(1L, 18L, 1L, LocalDate.of(2020, 1, 1), LocalDate.of(9999, 12, 31),1,null,null));

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsOperatorBizTranRole(createOperatorSearchRequest(), operatorBizTranRoleList);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsOperatorBizTranRole(OperatorSearchRequest, List<Operator_BizTranRole>)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・オペレーター_取引ロール割当の検索条件判定 検索条件選択=OR & 取引ロール条件不一致
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsOperatorBizTranRole_test1() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        bizTranRoleConditionsSelect = 2;  //OR
        List<OparatorSearchBizTranRoleRequest> list = newArrayList();
        list.add(Oa11010SearchBizTranRoleConverter.with(Oa11010Vo.CHECKBOX_TRUE, 1L, "KBAG01", "（購買）購買業務基本", "KB",1, LocalDate.of(2020,10,1), null, null, null, null));
        bizTranRoleList = list;
        List<Operator_BizTranRole> operatorBizTranRoleList = newArrayList();
        operatorBizTranRoleList.add(Operator_BizTranRole.createFrom(1L, 18L, 1L, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 9, 30),1,null,null));

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator.conditionsOperatorBizTranRole(createOperatorSearchRequest(), operatorBizTranRoleList);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsOperatorBizTranRole(OperatorSearchRequest, List<Operator_BizTranRole>)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・オペレーター_取引ロール割当の検索条件判定 検索条件選択=OR & 有効期限検索条=UnKnown
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsOperatorBizTranRole_test2() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        bizTranRoleConditionsSelect = 2;  //OR
        List<OparatorSearchBizTranRoleRequest> list = newArrayList();
        list.add(Oa11010SearchBizTranRoleConverter.with(Oa11010Vo.CHECKBOX_TRUE, 1L, "KBAG01", "（購買）購買業務基本", "KB",-1, null, null, null, null, null));
        bizTranRoleList = list;
        List<Operator_BizTranRole> operatorBizTranRoleList = newArrayList();
        operatorBizTranRoleList.add(Operator_BizTranRole.createFrom(1L, 18L, 1L, LocalDate.of(2020, 1, 1), LocalDate.of(9999, 12, 31),1,null,null));

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsOperatorBizTranRole(createOperatorSearchRequest(), operatorBizTranRoleList);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsAccountLock(OperatorSearchRequest, AccountLock)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・アカウントロックの検索条件判定 最終ロック・アンロック発生日開始
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsAccountLock_test0() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        accountLockOccurredDateFrom = LocalDate.of(2020, 10, 2);
        AccountLock accountLocks = AccountLock.createFrom(1L, 18L, LocalDateTime.of(2020, 10, 1, 8, 30, 12),AccountLockStatus.アンロック.getCode(), 0, null);

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator
            .conditionsAccountLock(createOperatorSearchRequest(), accountLocks);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsAccountLock(OperatorSearchRequest, AccountLock)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・アカウントロックの検索条件判定 最終ロック・アンロック発生日終了
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsAccountLock_test1() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        accountLockOccurredDateTo = LocalDate.of(2020, 9, 30);
        AccountLock accountLocks = AccountLock.createFrom(1L, 18L, LocalDateTime.of(2020, 10, 1, 8, 30, 12),AccountLockStatus.アンロック.getCode(), 0, null);

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator
            .conditionsAccountLock(createOperatorSearchRequest(), accountLocks);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsAccountLock(OperatorSearchRequest, AccountLock)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・アカウントロックの検索条件判定 アカウントロック ロック状態 ロック=true
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsAccountLock_test2() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        accountLockStatusLock = Oa11010Vo.CHECKBOX_TRUE;
        AccountLock accountLocks = AccountLock.createFrom(1L, 18L, LocalDateTime.of(2020, 10, 1, 8, 30, 12),AccountLockStatus.ロック.getCode(), 0, null);

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator
            .conditionsAccountLock(createOperatorSearchRequest(), accountLocks);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsAccountLock(OperatorSearchRequest, AccountLock)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・アカウントロックの検索条件判定 アカウントロック ロック状態 ロック=false
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsAccountLock_test3() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        accountLockStatusLock = Oa11010Vo.CHECKBOX_TRUE;
        AccountLock accountLocks = AccountLock.createFrom(1L, 18L, LocalDateTime.of(2020, 10, 1, 8, 30, 12),AccountLockStatus. アンロック.getCode(), 0, null);

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator
            .conditionsAccountLock(createOperatorSearchRequest(), accountLocks);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsAccountLock(OperatorSearchRequest, AccountLock)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・アカウントロックの検索条件判定 アカウントロック ロック状態 アンロック=true
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsAccountLock_test4() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        accountLockStatusUnlock = Oa11010Vo.CHECKBOX_TRUE;
        AccountLock accountLocks = AccountLock.createFrom(1L, 18L, LocalDateTime.of(2020, 10, 1, 8, 30, 12),AccountLockStatus.アンロック.getCode(), 0, null);

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator
            .conditionsAccountLock(createOperatorSearchRequest(), accountLocks);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsAccountLock(OperatorSearchRequest, AccountLock)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・アカウントロックの検索条件判定 アカウントロック ロック状態 アンロック=false
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsAccountLock_test5() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        accountLockStatusUnlock = Oa11010Vo.CHECKBOX_TRUE;
        AccountLock accountLocks = AccountLock.createFrom(1L, 18L, LocalDateTime.of(2020, 10, 1, 8, 30, 12),AccountLockStatus.ロック.getCode(), 0, null);

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator
            .conditionsAccountLock(createOperatorSearchRequest(), accountLocks);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsPasswordHistory(OperatorSearchRequest, PasswordHistory)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・パスワード履歴の検索条件判定 最終パスワード変更日状態 5日以内に「変更した」= false
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsPasswordHistory_test0() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryLastChangeDateStatus = "1";
        passwordHistoryLastChangeDate = 5;
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            1L,
            18L,
            LocalDateTime.now().minusDays(5),
            "password",
            PasswordChangeType.初期.getCode(),
            1,
            null);

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator.conditionsPasswordHistory(createOperatorSearchRequest(), passwordHistory);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsPasswordHistory(OperatorSearchRequest, PasswordHistory)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・パスワード履歴の検索条件判定 最終パスワード変更日状態 5日以内に「変更した」= true
     *    ・最終パスワード変更種別=初期
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsPasswordHistory_test01() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryLastChangeDateStatus = "1";
        passwordHistoryLastChangeDate = 5;
        passwordHistoryChangeType0 = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryChangeType1 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType2 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType3 = Oa11010Vo.CHECKBOX_FALSE;
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            1L,
            18L,
            LocalDateTime.now().minusDays(6),
            "password",
            PasswordChangeType.初期.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsPasswordHistory(createOperatorSearchRequest(), passwordHistory);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsPasswordHistory(OperatorSearchRequest, PasswordHistory)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・パスワード履歴の検索条件判定 最終パスワード変更日状態 5日以内に「変更していない」= false
     *    ・最終パスワード変更種別=初期
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsPasswordHistory_test02() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryLastChangeDateStatus = "2";
        passwordHistoryLastChangeDate = 5;
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            1L,
            18L,
            LocalDateTime.now().minusDays(5),
            "password",
            PasswordChangeType.初期.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator.conditionsPasswordHistory(createOperatorSearchRequest(), passwordHistory);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsPasswordHistory(OperatorSearchRequest, PasswordHistory)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・パスワード履歴の検索条件判定 最終パスワード変更日状態 5日以内に「変更していない」= true
     *    ・最終パスワード変更種別=ユーザーによる変更
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsPasswordHistory_test03() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryLastChangeDateStatus = "2";
        passwordHistoryLastChangeDate = 5;
        passwordHistoryChangeType0 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType1 = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryChangeType2 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType3 = Oa11010Vo.CHECKBOX_FALSE;
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            1L,
            18L,
            LocalDateTime.now().minusDays(6),
            "password",
            PasswordChangeType.ユーザーによる変更.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsPasswordHistory(createOperatorSearchRequest(), passwordHistory);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsPasswordHistory(OperatorSearchRequest, PasswordHistory)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終パスワード変更日  「日以内に」null
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsPasswordHistory_test04() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryLastChangeDate = null;
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            1L,
            18L,
            LocalDateTime.now().minusDays(6),
            "password",
            PasswordChangeType.ユーザーによる変更.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsPasswordHistory(createOperatorSearchRequest(), passwordHistory);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsPasswordHistory(OperatorSearchRequest, PasswordHistory)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・パスワード履歴の検索条件判定 最終パスワード変更日状態 5日以内に「変更していない」= true & passwordHistory=null
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsPasswordHistory_test05() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryLastChangeDate = 5;
        passwordHistoryLastChangeDateStatus = "2";
        PasswordHistory passwordHistory = null;

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsPasswordHistory(createOperatorSearchRequest(), passwordHistory);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsPasswordHistory(OperatorSearchRequest, PasswordHistory)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・パスワード履歴の検索条件判定 「変更した／変更していない」= 未設定
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsPasswordHistory_test06() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryLastChangeDate = 5;
        passwordHistoryLastChangeDateStatus = "";
        PasswordHistory passwordHistory = null;

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsPasswordHistory(createOperatorSearchRequest(), passwordHistory);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsPasswordHistory(OperatorSearchRequest, PasswordHistory)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終パスワード変更種別=管理者によるリセット
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsPasswordHistory_test07() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryLastChangeDateStatus = null;
        passwordHistoryLastChangeDate = null;
        passwordHistoryChangeType0 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType1 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType2 = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryChangeType3 = Oa11010Vo.CHECKBOX_FALSE;
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            1L,
            18L,
            LocalDateTime.now().minusDays(6),
            "password",
            PasswordChangeType.管理者によるリセット.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsPasswordHistory(createOperatorSearchRequest(), passwordHistory);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsPasswordHistory(OperatorSearchRequest, PasswordHistory)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終パスワード変更種別=機器認証パスワード
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsPasswordHistory_test08() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryLastChangeDateStatus = null;
        passwordHistoryLastChangeDate = null;
        passwordHistoryChangeType0 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType1 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType2 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType3 = Oa11010Vo.CHECKBOX_TRUE;
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            1L,
            18L,
            LocalDateTime.now().minusDays(6),
            "password",
            PasswordChangeType.機器認証パスワード.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsPasswordHistory(createOperatorSearchRequest(), passwordHistory);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsPasswordHistory(OperatorSearchRequest, PasswordHistory)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終パスワード変更種別=全てチェｋック
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsPasswordHistory_test09() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryLastChangeDateStatus = null;
        passwordHistoryLastChangeDate = null;
        passwordHistoryChangeType0 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType1 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType2 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType3 = Oa11010Vo.CHECKBOX_FALSE;
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            1L,
            18L,
            LocalDateTime.now().minusDays(6),
            "password",
            PasswordChangeType.機器認証パスワード.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsPasswordHistory(createOperatorSearchRequest(), passwordHistory);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsPasswordHistory(OperatorSearchRequest, PasswordHistory)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終パスワード変更種別チェックあり & passwordHistory=null
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsPasswordHistory_test10() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryLastChangeDateStatus = null;
        passwordHistoryLastChangeDate = null;
        passwordHistoryChangeType0 = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryChangeType1 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType2 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType3 = Oa11010Vo.CHECKBOX_FALSE;
        PasswordHistory passwordHistory = null;

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator.conditionsPasswordHistory(createOperatorSearchRequest(), passwordHistory);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsPasswordHistory(OperatorSearchRequest, PasswordHistory)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終パスワード変更種別チェック 初期=false
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsPasswordHistory_test11() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryLastChangeDateStatus = null;
        passwordHistoryLastChangeDate = null;
        passwordHistoryChangeType0 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType1 = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryChangeType2 = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryChangeType3 = Oa11010Vo.CHECKBOX_TRUE;
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            1L,
            18L,
            LocalDateTime.now().minusDays(6),
            "password",
            PasswordChangeType.初期.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsPasswordHistory(createOperatorSearchRequest(), passwordHistory);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsPasswordHistory(OperatorSearchRequest, PasswordHistory)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終パスワード変更種別チェック ユーザーによる変更=false
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsPasswordHistory_test12() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryLastChangeDateStatus = null;
        passwordHistoryLastChangeDate = null;
        passwordHistoryChangeType0 = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryChangeType1 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType2 = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryChangeType3 = Oa11010Vo.CHECKBOX_TRUE;
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            1L,
            18L,
            LocalDateTime.now().minusDays(6),
            "password",
            PasswordChangeType.ユーザーによる変更.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsPasswordHistory(createOperatorSearchRequest(), passwordHistory);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsPasswordHistory(OperatorSearchRequest, PasswordHistory)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終パスワード変更種別チェック 管理者によるリセット=false
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsPasswordHistory_test13() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryLastChangeDateStatus = null;
        passwordHistoryLastChangeDate = null;
        passwordHistoryChangeType0 = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryChangeType1 = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryChangeType2 = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryChangeType3 = Oa11010Vo.CHECKBOX_TRUE;
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            1L,
            18L,
            LocalDateTime.now().minusDays(6),
            "password",
            PasswordChangeType.管理者によるリセット.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsPasswordHistory(createOperatorSearchRequest(), passwordHistory);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsPasswordHistory(OperatorSearchRequest, PasswordHistory)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終パスワード変更種別チェック 管理者によるリセット=false
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsPasswordHistory_test14() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        passwordHistoryCheck = Oa11010Vo.CHECKBOX_FALSE;
        passwordHistoryLastChangeDateStatus = null;
        passwordHistoryLastChangeDate = null;
        passwordHistoryChangeType0 = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryChangeType1 = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryChangeType2 = Oa11010Vo.CHECKBOX_TRUE;
        passwordHistoryChangeType3 = Oa11010Vo.CHECKBOX_FALSE;
        PasswordHistory passwordHistory = PasswordHistory.createFrom(
            1L,
            18L,
            LocalDateTime.now().minusDays(6),
            "password",
            PasswordChangeType.機器認証パスワード.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsPasswordHistory(createOperatorSearchRequest(), passwordHistory);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsSignInTrace(OperatorSearchRequest, SignInTrace)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終サインオペレーション試行日の条件 = true
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsSignInTrace_test0() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        signintraceTrydateFrom = LocalDate.of(2020,10, 2);
        signintraceTrydateTo = LocalDate.of(2020,10, 2);
        SignInTrace signInTrace = SignInTrace.createFrom(
            1L,
            LocalDateTime.of(2020,10,2,8,30,12),
            "001.001.001.001",
            "yu001009",
            SignInCause.サインイン.getCode(),
            SignInResult.成功.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsSignInTrace(createOperatorSearchRequest(), signInTrace);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsSignInTrace(OperatorSearchRequest, SignInTrace)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終サインオペレーション試行日の条件 = false（範囲前）
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsSignInTrace_test1() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        signintraceTrydateFrom = LocalDate.of(2020,10, 2);
        signintraceTrydateTo = LocalDate.of(2020,10, 2);
        SignInTrace signInTrace = SignInTrace.createFrom(
            1L,
            LocalDateTime.of(2020,10,1,8,30,12),
            "001.001.001.001",
            "yu001009",
            SignInCause.サインイン.getCode(),
            SignInResult.成功.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator.conditionsSignInTrace(createOperatorSearchRequest(), signInTrace);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsSignInTrace(OperatorSearchRequest, SignInTrace)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終サインオペレーション試行日の条件 = false（範囲後）
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsSignInTrace_test2() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        signintraceTrydateFrom = LocalDate.of(2020,10, 2);
        signintraceTrydateTo = LocalDate.of(2020,10, 2);
        SignInTrace signInTrace = SignInTrace.createFrom(
            1L,
            LocalDateTime.of(2020,10,3,8,30,12),
            "001.001.001.001",
            "yu001009",
            SignInCause.サインイン.getCode(),
            SignInResult.成功.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator.conditionsSignInTrace(createOperatorSearchRequest(), signInTrace);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsSignInTrace(OperatorSearchRequest, SignInTrace)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終サインオペレーション試行日開始=null & signInTrace=null
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsSignInTrace_test3() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        signintraceTrydateFrom = null;
        signintraceTrydateTo = LocalDate.of(2020,10, 2);
        SignInTrace signInTrace = null;

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator.conditionsSignInTrace(createOperatorSearchRequest(), signInTrace);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsSignInTrace(OperatorSearchRequest, SignInTrace)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終サインオペレーション試行日開始=null
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsSignInTrace_test4() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        signintraceTrydateFrom = null;
        signintraceTrydateTo = LocalDate.of(2020,10, 2);
        SignInTrace signInTrace = SignInTrace.createFrom(
            1L,
            LocalDateTime.of(2020,10,3,8,30,12),
            "001.001.001.001",
            "yu001009",
            SignInCause.サインイン.getCode(),
            SignInResult.成功.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator.conditionsSignInTrace(createOperatorSearchRequest(), signInTrace);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsSignInTrace(OperatorSearchRequest, SignInTrace)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終サインオペレーション試行日終了=null
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsSignInTrace_test5() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        signintraceTrydateFrom = LocalDate.of(2020,10, 2);
        signintraceTrydateTo = null;
        SignInTrace signInTrace = SignInTrace.createFrom(
            1L,
            LocalDateTime.of(2020,10,3,8,30,12),
            "001.001.001.001",
            "yu001009",
            SignInCause.サインイン.getCode(),
            SignInResult.成功.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsSignInTrace(createOperatorSearchRequest(), signInTrace);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsSignInTrace(OperatorSearchRequest, SignInTrace)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終サインイン元IPアドレスの条件 = true
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsSignInTrace_test6() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        signintraceTryIpAddress = "145.254.211";
        SignInTrace signInTrace = SignInTrace.createFrom(
            1L,
            LocalDateTime.of(2020,10,3,8,30,12),
            "145.254.211.51",
            "yu001009",
            SignInCause.サインイン.getCode(),
            SignInResult.成功.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsSignInTrace(createOperatorSearchRequest(), signInTrace);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsSignInTrace(OperatorSearchRequest, SignInTrace)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終サインイン元IPアドレス 未入力
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsSignInTrace_test7() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        signintraceTryIpAddress = "";
        SignInTrace signInTrace = SignInTrace.createFrom(
            1L,
            LocalDateTime.of(2020,10,3,8,30,12),
            "145.254.211.51",
            "yu001009",
            SignInCause.サインイン.getCode(),
            SignInResult.成功.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsSignInTrace(createOperatorSearchRequest(), signInTrace);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsSignInTrace(OperatorSearchRequest, SignInTrace)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・signInTrace = null
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsSignInTrace_test8() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        signintraceTryIpAddress = "145.254.211";
        SignInTrace signInTrace = null;

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator.conditionsSignInTrace(createOperatorSearchRequest(), signInTrace);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsSignInTrace(OperatorSearchRequest, SignInTrace)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終サインイン元IPアドレスの条件 = false
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsSignInTrace_test9() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        signintraceTryIpAddress = "145.254.212";
        SignInTrace signInTrace = SignInTrace.createFrom(
            1L,
            LocalDateTime.of(2020,10,3,8,30,12),
            "145.254.211.51",
            "yu001009",
            SignInCause.サインイン.getCode(),
            SignInResult.成功.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator.conditionsSignInTrace(createOperatorSearchRequest(), signInTrace);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsSignInTrace(OperatorSearchRequest, SignInTrace)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終サインオペレーションの条件 = true
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsSignInTrace_test10() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        signintraceSignIn = Oa11010Vo.CHECKBOX_TRUE;
        List<Short> signintraceSignInResultList = newArrayList((short) 0);
        signintraceSignInResult = signintraceSignInResultList.toArray(new Short[signintraceSignInResultList.size()]);
        SignInTrace signInTrace = SignInTrace.createFrom(
            1L,
            LocalDateTime.of(2020,10,3,8,30,12),
            "145.254.211.51",
            "yu001009",
            SignInCause.サインイン.getCode(),
            SignInResult.成功.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsSignInTrace(createOperatorSearchRequest(), signInTrace);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsSignInTrace(OperatorSearchRequest, SignInTrace)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終サインオペレーション サインイン＝true & SignInTrace = null
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsSignInTrace_test11() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        signintraceSignIn = Oa11010Vo.CHECKBOX_TRUE;
        SignInTrace signInTrace = null;

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator.conditionsSignInTrace(createOperatorSearchRequest(), signInTrace);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsSignInTrace(OperatorSearchRequest, SignInTrace)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・最終サインオペレーションの条件 = false
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsSignInTrace_test12() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        List<Short> signintraceSignInResultList = newArrayList((short) 1);
        signintraceSignInResult = signintraceSignInResultList.toArray(new Short[signintraceSignInResultList.size()]);
        SignInTrace signInTrace = SignInTrace.createFrom(
            1L,
            LocalDateTime.of(2020,10,3,8,30,12),
            "145.254.211.51",
            "yu001009",
            SignInCause.サインイン.getCode(),
            SignInResult.成功.getCode(),
            1,
            null
        );

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator.conditionsSignInTrace(createOperatorSearchRequest(), signInTrace);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsSignInTrace(OperatorSearchRequest, SignInTrace)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・サインイン結果=0件
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsSignInTrace_test13() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        List<Short> signintraceSignInResultList = newArrayList();
        signintraceSignInResult = signintraceSignInResultList.toArray(new Short[signintraceSignInResultList.size()]);
        SignInTrace signInTrace = null;

        // 期待値
        boolean expected = true;

        // 実行
        boolean actual = searchOperator.conditionsSignInTrace(createOperatorSearchRequest(), signInTrace);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * {@link SearchOperator#conditionsSignInTrace(OperatorSearchRequest, SignInTrace)}のテスト
     *  ●パターン
     *    正常
     *    [検索条件]
     *    ・サインイン結果 指定 & signInTrace=null
     *
     *  ●検証事項
     *  ・正常終了（return=false）
     */
    @Test
    @Tag(TestSize.SMALL)
    void conditionsSignInTrace_test14() {

        // テスト対象クラス生成
        SearchOperator searchOperator = createSearchOperator();

        // 実行値
        List<Short> signintraceSignInResultList = newArrayList((short) 1);
        signintraceSignInResult = signintraceSignInResultList.toArray(new Short[signintraceSignInResultList.size()]);
        SignInTrace signInTrace = null;

        // 期待値
        boolean expected = false;

        // 実行
        boolean actual = searchOperator.conditionsSignInTrace(createOperatorSearchRequest(), signInTrace);

        // 結果検証
        assertThat(actual).isEqualTo(expected);
    }
}