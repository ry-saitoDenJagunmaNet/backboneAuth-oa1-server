package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.SimpleSearchBizTranRole;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBranchAtMoment;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchOperator;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemSource;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010BizTranRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SearchResponseVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SubSystemRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocksRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorsRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaders;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeadersRepository;
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
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTraces;
import net.jagunma.backbone.auth.authmanager.model.domain.signInTrace.SignInTracesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraces;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTracesRepository;
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

    // 実行 ＆ 期待 既定値
    private String ja = null;
    private final String jaCode = "006";
    private final String jaName = "JA前橋市";
    private Long jaId = 6L;
    private final Long branchId = null;
    private final List<SelectOptionItemSource> branchItemsSource = null;
    private final String operatorCode = null;
    private final String operatorName = null;
    private final String mailAddress = null;
    private final Short availableStatus0 = null;
    private final Short availableStatus1 = null;
    private final Integer expirationSelect = 0;
    private final LocalDate expirationStatusDate = null;
    private final LocalDate expirationStartDateFrom = null;
    private final LocalDate expirationStartDateTo = null;
    private final LocalDate expirationEndDateFrom = null;
    private final LocalDate expirationEndDateTo = null;
    private Integer subSystemRoleConditionsSelect = 1;
    private final Integer bizTranRoleConditionsSelect = 1;
    private final String bizTranRoleSubSystemCode = null;
    private final Short deviceAuthUse = null;
    private final Short deviceAuthUnuse = null;
    private final LocalDate accountLockOccurredDateFrom = null;
    private final LocalDate accountLockOccurredDateTo = null;
    private final Short accountLockStatusLock = null;
    private final Short accountLockStatusUnlock = null;
    private final Short passwordHistoryCheck = null;
    private final Integer passwordHistoryLastChangeDate = null;
    private final String passwordHistoryLastChangeDateStatus = null;
    private final Short passwordHistoryChangeType0 = null;
    private final Short passwordHistoryChangeType1 = null;
    private final Short passwordHistoryChangeType2 = null;
    private final Short passwordHistoryChangeType3 = null;
    private final LocalDate signintraceTrydateFrom = null;
    private final LocalDate signintraceTrydateTo = null;
    private final String signintraceTryIpAddress = null;
    private final Short signintraceSignIn = null;
    private final Short signintraceSignOut = null;
    private final Short[] signintraceSignInResult = null;
    private int pageNo = 0;
    private final String GunmaRuntimeExceptionMessageCode = "EOA14002";
    private final String GunmaRuntimeExceptionMessageArg1 = "JAID";
    private final String GunmaRuntimeExceptionMessageArg2 = "正しく設定";
    private final String statusCode = "200 OK";

    // テスト対象クラス生成
    private Oa11010Controller createOa11010Controller() {
        OperatorsRepository operatorsRepository = new OperatorsRepository() {
            @Override
            public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {
                return null;
            }
        };
        AccountLocksRepository accountLocksRepository = new AccountLocksRepository() {
            @Override
            public AccountLocks selectBy(AccountLockCriteria accountLockrCriteria, Orders orders) {
                return null;
            }
        };
        PasswordHistoriesRepository passwordHistoriesRepository = new PasswordHistoriesRepository() {
            @Override
            public PasswordHistories selectBy(PasswordHistoryCriteria passwordHistoryCriteria, Orders orders) {
                return null;
            }
        };
        SignInTracesRepository signInTracesRepository = new SignInTracesRepository() {
            @Override
            public SignInTraces selectBy(SignInTraceCriteria signInTraceCriteria, Orders orders) {
                return null;
            }
        };
        SignOutTracesRepository signOutTracesRepository = new SignOutTracesRepository() {
            @Override
            public SignOutTraces selectBy(SignOutTraceCriteria signOutTraceCriteria, Orders orders) {
                return null;
            }
        };
        Operator_SubSystemRolesRepository operator_SubSystemRolesRepository = new Operator_SubSystemRolesRepository() {
            @Override
            public Operator_SubSystemRoles selectBy(Operator_SubSystemRoleCriteria operator_SubSystemRoleCriteria, Orders orders) {
                return null;
            }
        };
        Operator_BizTranRolesRepository operator_BizTranRolesRepository = new Operator_BizTranRolesRepository() {
            @Override
            public Operator_BizTranRoles selectBy(Operator_BizTranRoleCriteria operator_BizTranRoleCriteria, Orders orders) {
                return null;
            }
        };
        OperatorHistoryHeadersRepository operatorHistoryHeadersRepository = new OperatorHistoryHeadersRepository() {
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
            operatorsRepository,
            accountLocksRepository,
            passwordHistoriesRepository,
            signInTracesRepository,
            signOutTracesRepository,
            operator_SubSystemRolesRepository,
            operator_BizTranRolesRepository,
            operatorHistoryHeadersRepository) {
            public void execute(OperatorSearchRequest request, OperatorSearchResponse response) {
                // request.getJaIdCriteria().getEqualTo() == -1 の場合：RuntimeException を発生させる
                if (request.getJaIdCriteria().getEqualTo() == -1) {
                    throw new RuntimeException();
                }
                // request.getJaIdCriteria().getEqualTo() == -2 の場合：GunmaRuntimeException を発生させる
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
        BizTranRolesRepository bizTranRolesRepository = new BizTranRolesRepository() {
            @Override
            public BizTranRoles selectBy(BizTranRoleCriteria bizTranRoleCriteria, Orders orders) {
                return null;
            }
            @Override
            public BizTranRoles selectAll(Orders orders) {
                return null;
            }
        };
        SimpleSearchBizTranRole simpleSearchBizTranRole = new SimpleSearchBizTranRole(bizTranRolesRepository) {
            public BizTranRoles getBizTranRoles() {
                return createBizTranRoles();
            }
        };

        return new Oa11010Controller(
            searchOperator,
            simpleSearchBizTranRole,
            searchBranchAtMoment);
    }

    // Oa11010Vo作成
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
        oa11010Vo.setExpirationSelect(expirationSelect);
        oa11010Vo.setExpirationStatusDate(expirationStatusDate);
        oa11010Vo.setExpirationStartDateFrom(expirationStartDateFrom);
        oa11010Vo.setExpirationStartDateTo(expirationStartDateTo);
        oa11010Vo.setExpirationEndDateFrom(expirationEndDateFrom);
        oa11010Vo.setExpirationEndDateTo(expirationEndDateTo);
        oa11010Vo.setSubSystemRoleConditionsSelect(subSystemRoleConditionsSelect);
        List<Oa11010SubSystemRoleVo> subSystemRoleVoList = newArrayList();
        for(SubSystemRole subSystemRole : SubSystemRole.values()) {
            if (subSystemRole.getCode().length() == 0) { continue; }
            Oa11010SubSystemRoleVo subSystemRoleVo = new Oa11010SubSystemRoleVo();
            subSystemRoleVo.setSubSystemRoleCode(subSystemRole.getCode());
            subSystemRoleVo.setSubSystemRoleName(subSystemRole.getName());
            subSystemRoleVo.setExpirationSelect(0);
            subSystemRoleVoList.add(subSystemRoleVo);
        }
        oa11010Vo.setSubSystemRoleList(subSystemRoleVoList);
        oa11010Vo.setBizTranRoleConditionsSelect(bizTranRoleConditionsSelect);
        oa11010Vo.setBizTranRoleSubSystemCode(bizTranRoleSubSystemCode);
        oa11010Vo.setBizTranRoleSubSystemList(SelectOptionItemsSource.createFrom(SubSystem.values()).getValue());
        List<Oa11010BizTranRoleVo> oa11010BizTranRoleVoList = newArrayList();
        for (BizTranRole bizTranRole :  createBizTranRoles().getValues()) {
            Oa11010BizTranRoleVo bizTranRoleVo = new Oa11010BizTranRoleVo();
            bizTranRoleVo.setBizTranRoleId(bizTranRole.getBizTranRoleId());
            bizTranRoleVo.setBizTranRoleCode(bizTranRole.getBizTranRoleCode());
            bizTranRoleVo.setBizTranRoleName(bizTranRole.getBizTranRoleName());
            bizTranRoleVo.setSubSystemCode(bizTranRole.getSubSystemCode());
            bizTranRoleVo.setExpirationSelect(0);
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
        return oa11010Vo;
    }

    // BranchesAtMoment作成
    private BranchesAtMoment createBranchesAtMoment() {
        List<BranchAtMoment> branchAtMomentList = newArrayList();
        branchAtMomentList.add(BranchAtMoment.builder()
            .withIdentifier(33L).withJaAtMoment(createJaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("001")).withName("店舗001").build())
            .build());
        branchAtMomentList.add(BranchAtMoment.builder()
            .withIdentifier(35L).withJaAtMoment(createJaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("003")).withName("店舗003").build())
            .build());
        branchAtMomentList.add(BranchAtMoment.builder()
            .withIdentifier(36L).withJaAtMoment(createJaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("004")).withName("店舗004").build())
            .build());
        return BranchesAtMoment.of(branchAtMomentList);
    }
    private BranchAtMoment createBranchAtMoment(Long branchId) {
        return createBranchesAtMoment().getValue().stream().filter(b->b.getIdentifier().equals(branchId)).findFirst().orElse(null);
    }

    // JaAtMoment作成
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

    // 取引ロール群作成
    private BizTranRoles createBizTranRoles() {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(1L,"KBAG01","（購買）購買業務基本","KB",1,SubSystem.購買));
        list.add(BizTranRole.createFrom(2L,"KBAG02","（購買）本所業務","KB",1,SubSystem.購買));
        return BizTranRoles.createFrom(list);
    }

    // オペレーター群作成
    private Operators createOperators() {
        List<Operator> list = newArrayList();
        list.add(Operator.createFrom(18L,"yu001009","ｙｕ００１００９","yu001009@aaaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,createBranchAtMoment(33L)));
        list.add(Operator.createFrom(19L,"yu001010","ｙｕ００１０１０","yu001010@aaaa.net",LocalDate.of(2010,8,17),LocalDate.of(9999,12,21),false,6L,"006",33L,"001",AvailableStatus.利用可能,1,createBranchAtMoment(33L)));
        return Operators.createFrom(list);
    }

    // アカウントロック群作成
    private AccountLocks createAccountLocks() {
        List<AccountLock> list = newArrayList();
        list.add(AccountLock.createFrom(1L,18L,LocalDateTime.of(2020,10,1,8,30,12),(short) 0,0,null));
        list.add(AccountLock.createFrom(2L,19L,LocalDateTime.of(2020,10,1,8,30,12),(short) 1,0,null));
        return AccountLocks.createFrom(list);
    }

    // オペレーター_サブシステムロール割当群作成
    private Operator_SubSystemRoles createOperator_SubSystemRoles() {
        List<Operator_SubSystemRole> list = newArrayList();
        list.add(Operator_SubSystemRole.createFrom(1L,18L,SubSystemRole.業務統括者_購買.getCode(),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,SubSystemRole.業務統括者_購買));
        list.add(Operator_SubSystemRole.createFrom(2L,21L,SubSystemRole.業務統括者_購買.getCode(),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,SubSystemRole.業務統括者_購買));
        list.add(Operator_SubSystemRole.createFrom(3L,21L,SubSystemRole.業務統括者_販売_青果.getCode(),LocalDate.of(2020,1,1),LocalDate.of(9999,12,31),1,null,SubSystemRole.業務統括者_販売_青果));
        return Operator_SubSystemRoles.createFrom(list);
    }

    // オペレーター_取引ロール割当群作成
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

    // 取引ロール作成
    private BizTranRole createBizTranRole(Long id) {
        List<BizTranRole> list = newArrayList();
        list.add(BizTranRole.createFrom(1L,"KB0000","購買メインメニュー",SubSystem.購買.getCode(),1,SubSystem.購買));
        list.add(BizTranRole.createFrom(2L,"KB0001","支所検索",SubSystem.購買.getCode(),1,SubSystem.購買));
        list.add(BizTranRole.createFrom(3L,"KB0002","顧客検索",SubSystem.購買.getCode(),1,SubSystem.購買));
        list.add(BizTranRole.createFrom(4L,"YS0000","野菜メインメニュー",SubSystem.販売_青果.getCode(),1,SubSystem.販売_青果));
        return list.stream().filter(b->b.getBizTranRoleId().equals(id)).findFirst().orElse(null);
    }

    /**
     * {@link Oa11010Controller#get(Model)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test0() {

        // テスト対象クラス生成
        Oa11010Controller oa11010Controller = createOa11010Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();

        // 期待値
        String expectedViewName = "oa11010";
        ja = jaCode + " " + jaName;
        Oa11010Vo expectedVo = createOa11010Vo();

        // 実行
        String actualViewName = oa11010Controller.get(model);
        Oa11010Vo actualVo = (Oa11010Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11010Controller#get(Model)}のテスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test1() {
        // getメソッドでGunmaRuntimeExceptionを発生させるテストは不可
        assertThat(true);
    }

    /**
     * {@link Oa11010Controller#get(Model)}のテスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test2() {
        // getメソッドでRuntimeExceptionを発生させるテストは不可
        assertThat(true);
    }

    /**
     * {@link Oa11010Controller#search(Oa11010Vo)}のテスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void search_test0() {

        // テスト対象クラス生成
        Oa11010Controller oa11010Controller = createOa11010Controller();

        // 実行値
        pageNo = 1;
        Oa11010Vo oa11010Vo = createOa11010Vo();

        // 期待値
        String expectedViewName = "oa11010";
        Oa11010SearchResponseVo expectedVo = new Oa11010SearchResponseVo();
        expectedVo.setOperatorTable("<tr class=\"oaex_operator_table_operator_yu001009 oaex_th_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td><td class=\"oaex_operator_branch_code\">001</td><td class=\"oaex_operator_branch_name\">店舗001</td><td class=\"oaex_operator_operator_code\">yu001009<input type=\"hidden\" value=\"33\"/></td><td class=\"oaex_operator_operator_name\">ｙｕ００１００９</td><td class=\"oaex_operator_expiration_date\">2010/08/17～9999/12/21</td><td class=\"oaex_operator_subsystem_role\">業務統括者（購買）</td><td class=\"oaex_operator_subsystem_role_expiration_date\">2020/01/01～9999/12/31</td><td class=\"oaex_operator_biztran_role_code\">KB0000</td><td class=\"oaex_operator_biztran_role_name\">購買メインメニュー</td><td class=\"oaex_operator_biztran_role_expiration_date\">2020/01/01～9999/12/31</tr>"
            + "<tr class=\"oaex_operator_table_operator_yu001009\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"></td><td class=\"oaex_operator_account_lock\"></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\"></td><td class=\"oaex_operator_operator_name\"></td><td class=\"oaex_operator_expiration_date\"></td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_expiration_date\"></td><td class=\"oaex_operator_biztran_role_code\">YS0000</td><td class=\"oaex_operator_biztran_role_name\">野菜メインメニュー</td><td class=\"oaex_operator_biztran_role_expiration_date\">2020/01/01～9999/12/31</tr>"
            + "<tr class=\"oaex_operator_table_operator_yu001010 oaex_th_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\"><td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td><td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_lock\"></div></td><td class=\"oaex_operator_branch_code\"></td><td class=\"oaex_operator_branch_name\"></td><td class=\"oaex_operator_operator_code\">yu001010<input type=\"hidden\" value=\"33\"/></td><td class=\"oaex_operator_operator_name\">ｙｕ００１０１０</td><td class=\"oaex_operator_expiration_date\">2010/08/17～9999/12/21</td><td class=\"oaex_operator_subsystem_role\"></td><td class=\"oaex_operator_subsystem_role_expiration_date\"></td><td class=\"oaex_operator_biztran_role_code\"></td><td class=\"oaex_operator_biztran_role_name\"></td><td class=\"oaex_operator_biztran_role_expiration_date\"></td></tr>"
        );
        expectedVo.setPagination("<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&lt;</a></li>"
            + "<li class=\"active\" th:remove=\"all\"><a href=\"#!\">1</a></li>"
            + "<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&gt;</a></li>"
        );

        // 実行
        ResponseEntity<Oa11010SearchResponseVo> actualVo = oa11010Controller.search(oa11010Vo);

        // 結果検証
        assertThat(actualVo.getStatusCode().toString()).isEqualTo(statusCode);
        assertThat(actualVo.getBody()).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11010Controller#search(Oa11010Vo)}のテスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void search_test1() {

        // テスト対象クラス生成
        Oa11010Controller oa11010Controller = createOa11010Controller();

        // 実行値
        jaId = -2L;
        Oa11010Vo oa11010Vo = createOa11010Vo();

        // 期待値
        String expectedViewName = "oa11010";

        // 実行
        ResponseEntity<Oa11010SearchResponseVo> actualVo = oa11010Controller.search(oa11010Vo);

        // 結果検証
        assertThat(actualVo.getStatusCode().toString()).isEqualTo(statusCode);
        assertThat(actualVo.getBody().getMessageCode()).isEqualTo(GunmaRuntimeExceptionMessageCode);
        assertThat(actualVo.getBody().getMessageArgs().get(0)).isEqualTo(GunmaRuntimeExceptionMessageArg1);
        assertThat(actualVo.getBody().getMessageArgs().get(1)).isEqualTo(GunmaRuntimeExceptionMessageArg2);
    }

    /**
     * {@link Oa11010Controller#search(Oa11010Vo)}のテスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void search_test2() {

        // テスト対象クラス生成
        Oa11010Controller oa11010Controller = createOa11010Controller();

        // 実行値
        jaId = -1L;
        Oa11010Vo oa11010Vo = createOa11010Vo();
        String expectedErrorMessage = "サーバーで予期しないエラーが発生しました。";

        // 期待値
        String expectedViewName = "oa11010";

        // 実行
        ResponseEntity<Oa11010SearchResponseVo> actualVo = oa11010Controller.search(oa11010Vo);

        // 結果検証
        assertThat(actualVo.getStatusCode().toString()).isEqualTo(statusCode);
        assertThat(actualVo.getBody().getErrorMessage()).isEqualTo(expectedErrorMessage);
    }
}