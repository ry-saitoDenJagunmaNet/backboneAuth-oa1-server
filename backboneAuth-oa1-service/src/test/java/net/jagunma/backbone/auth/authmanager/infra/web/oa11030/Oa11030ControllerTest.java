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

    // 実行 ＆ 期待 既定値
    // オペレーター項目系
    private Long operatorId = 123456L;
    private String operatorCode = "yu123456";
    private String operatorName = "オペレーター名";
    private String mailAddress = "test@den.jagunma.net";
    private LocalDate validThruStartDate = LocalDate.of(2020, 9, 1);
    private LocalDate validThruEndDate = LocalDate.of(2020, 9, 30);
    private Boolean isDeviceAuth = false;
    private Long jaId = 6L;
    private String jaCode = "006";
    private Long branchId = 1L;
    private String branchCode = "001";
    private AvailableStatus availableStatus = AvailableStatus.利用可能;
    private Integer recordVersion = 1;

    private String changeCause = "認証機器使用開始";
    private String changeCausePlaceholder = "新職員の入組による登録";
    private AccountLockStatus accountLockStatus = AccountLockStatus.アンロック;

    private List<Oa11030SubSystemRoleTableVo> subSystemRoleTableVoList;
    private List<Oa11030BizTranRoleTableVo> bizTranRoleTableVoList;

    // ＪＡAtMoment
    private String jaName = "JA前橋市";
    private JaAtMoment jaAtMoment = JaAtMoment.builder()
        .withIdentifier(jaId)
        .withJaAttribute(JaAttribute.builder()
            .withJaCode(JaCode.of(jaCode))
            .withName(jaName)
            .build())
        .build();

    // 店舗AtMoment
    private BranchAtMoment branchAtMoment = BranchAtMoment.builder().withIdentifier(branchId).withJaAtMoment(jaAtMoment).withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.一般).withBranchCode(BranchCode.of(branchCode)).withName("本店").build()).build();

    // オペレーター系
    private Operator operator = Operator.createFrom(operatorId, operatorCode, operatorName, mailAddress, validThruStartDate, validThruEndDate, isDeviceAuth, jaId, jaCode, branchId, branchCode, availableStatus, recordVersion, branchAtMoment);

    // 店舗群AtMoment
    private List<BranchAtMoment> branchAtMomentList = newArrayList(
        branchAtMoment,
        BranchAtMoment.builder().withIdentifier(2L).withJaAtMoment(jaAtMoment).withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.一般).withBranchCode(BranchCode.of("002")).withName("店舗002").build()).build(),
        BranchAtMoment.builder().withIdentifier(3L).withJaAtMoment(jaAtMoment).withBranchAttribute(BranchAttribute.builder().withBranchType(BranchType.一般).withBranchCode(BranchCode.of("003")).withName("店舗003").build()).build());
    private BranchesAtMoment branchesAtMoment = BranchesAtMoment.of(branchAtMomentList);

    // アカウントロック履歴系
    private List<AccountLock> accountLockList = newArrayList(
        AccountLock.createFrom(202L,operatorId, LocalDateTime.of(2020,10,2,3,4,5),AccountLockStatus.アンロック, recordVersion, operator),
        AccountLock.createFrom(201L,operatorId, LocalDateTime.of(2020,10,1,2,3,4),AccountLockStatus.ロック, recordVersion, operator));
    private AccountLocks accountLocks = AccountLocks.createFrom(accountLockList);

    // オペレーター_サブシステムロール割当系
    private List<Operator_SubSystemRole> operator_SubSystemRoleList = newArrayList(
        Operator_SubSystemRole.createFrom(301L, operatorId, SubSystemRole.JA管理者.getCode(), validThruStartDate, validThruEndDate, 391, operator, SubSystemRole.JA管理者),
        Operator_SubSystemRole.createFrom(302L, operatorId, SubSystemRole.業務統括者_購買.getCode(), validThruStartDate, validThruEndDate, 392, operator, SubSystemRole.業務統括者_購買),
        Operator_SubSystemRole.createFrom(303L, operatorId, SubSystemRole.業務統括者_販売_青果.getCode(), validThruStartDate, validThruEndDate, 393, operator, SubSystemRole.業務統括者_販売_青果),
        Operator_SubSystemRole.createFrom(304L, operatorId, SubSystemRole.業務統括者_販売_米.getCode(), validThruStartDate, validThruEndDate, 394, operator, SubSystemRole.業務統括者_販売_米),
        Operator_SubSystemRole.createFrom(305L, operatorId, SubSystemRole.業務統括者_販売_畜産.getCode(), validThruStartDate, validThruEndDate, 395, operator, SubSystemRole.業務統括者_販売_畜産));
    private Operator_SubSystemRoles operator_SubSystemRoles = Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);

    // オペレーター_取引ロール割当系
    private List<BizTranRole> bizTranRoleList = newArrayList(
        BizTranRole.createFrom(401L, "KBAG01", "（購買）購買業務基本", SubSystem.購買.getCode(), recordVersion, SubSystem.購買),
        BizTranRole.createFrom(402L, "YSAG10", "（青果）管理者", SubSystem.販売_青果.getCode(), recordVersion, SubSystem.販売_青果),
        BizTranRole.createFrom(403L, "HKAG10", "（米）ＪＡ取引全般", SubSystem.販売_米.getCode(), recordVersion, SubSystem.販売_米),
        BizTranRole.createFrom(404L, "ANAG01", "（畜産）取引全般", SubSystem.販売_畜産.getCode(), recordVersion, SubSystem.販売_畜産));
    private List<Operator_BizTranRole> operator_BizTranRoleList = newArrayList(
        Operator_BizTranRole.createFrom(501L, operatorId, bizTranRoleList.get(0).getBizTranRoleId(), validThruStartDate, validThruEndDate, recordVersion, operator, bizTranRoleList.get(0)),
        Operator_BizTranRole.createFrom(502L, operatorId, bizTranRoleList.get(1).getBizTranRoleId(), validThruStartDate, validThruEndDate, recordVersion, operator, bizTranRoleList.get(1)),
        Operator_BizTranRole.createFrom(503L, operatorId, bizTranRoleList.get(2).getBizTranRoleId(), validThruStartDate, validThruEndDate, recordVersion, operator, bizTranRoleList.get(2)),
        Operator_BizTranRole.createFrom(504L, operatorId, bizTranRoleList.get(3).getBizTranRoleId(), validThruStartDate, validThruEndDate, recordVersion, operator, bizTranRoleList.get(3)));
    private Operator_BizTranRoles operator_BizTranRoles = Operator_BizTranRoles.createFrom(operator_BizTranRoleList);

    // オペレーター履歴ヘッダー系
    private List<OperatorHistoryHeader> operatorHistoryHeaderList = newArrayList(
        OperatorHistoryHeader.createFrom(601L, operatorId,LocalDateTime.of(2020,10,1,0,1,2), changeCausePlaceholder, recordVersion, operator));
    private OperatorHistoryHeaders operatorHistoryHeaders = OperatorHistoryHeaders.createFrom(operatorHistoryHeaderList);


    // テスト対象クラス生成
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
                //  request.getOperatorId().equals(11L) の場合：GunmaRuntimeException を発生させる
                if (request.getOperatorId().equals(11L)) {
                    throw new GunmaRuntimeException("EOA13008", "有効期限開始");
                }
                //  request.getOperatorId().equals(12L) の場合：RuntimeException を発生させる
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
                //  request.getOperatorId().equals(21L) の場合：GunmaRuntimeException を発生させる
                if (request.getOperatorId().equals(21L)) {
                    throw new GunmaRuntimeException("EOA13002", "オペレーターID");
                }
                //  request.getOperatorId().equals(22L) の場合：RuntimeException を発生させる
                if (request.getOperatorId().equals(22L)) {
                    throw new RuntimeException();
                }
                //  request.getOperatorId().equals(23L) の場合：OptimisticLockingFailureException を発生させる
                if (request.getOperatorId().equals(23L)) {
                    throw new OptimisticLockingFailureException("楽観的ロック");
                }
            }
        };

        return new Oa11030Controller(updateOperator, searchOperator, searchBranchAtMoment);
    }

    // Oa11030Vo作成
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
        vo.setAvailableStatus(CheckboxUtil.setSmoother((availableStatus.equals(AvailableStatus.利用可能))? true : false));
        vo.setChangeCause(changeCause);
        vo.setChangeCausePlaceholder(changeCausePlaceholder);
        vo.setAccountLockStatus(accountLockStatus.getCode());
        vo.setSubSystemRoleTableVoList(createOa11030SubSystemRoleTableVoList());
        vo.setBizTranRoleTableVoList(createOa11030BizTranRoleTableVoList());
        vo.setBranchItemsSource(SelectOptionItemsSource.createFrom(branchesAtMoment).getValue());

        return vo;
    }

    // Oa11030SubSystemRoleTableVoList作成
    private List<Oa11030SubSystemRoleTableVo> createOa11030SubSystemRoleTableVoList() {
        List<Oa11030SubSystemRoleTableVo> subSystemRoleTableVoList = newArrayList();

        for (Operator_SubSystemRole operator_SubSystemRole : operator_SubSystemRoleList) {
            Oa11030SubSystemRoleTableVo subSystemRoleTableVo = new Oa11030SubSystemRoleTableVo();
            subSystemRoleTableVo.setRoleName(operator_SubSystemRole.getSubSystemRole().getDisplayName());
            subSystemRoleTableVo.setValidThruDate(
                operator_SubSystemRole.getValidThruStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " ～ " +
                operator_SubSystemRole.getValidThruEndDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            subSystemRoleTableVoList.add(subSystemRoleTableVo);
        }

        return subSystemRoleTableVoList;
    }

    // Oa11030BizTranRoleTableVoList作成
    private List<Oa11030BizTranRoleTableVo> createOa11030BizTranRoleTableVoList() {
        List<Oa11030BizTranRoleTableVo> bizTranRoleTableVoList = newArrayList();

        for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoleList) {
            Oa11030BizTranRoleTableVo bizTranRoleTableVo = new Oa11030BizTranRoleTableVo();
            bizTranRoleTableVo.setRoleCode(operator_BizTranRole.getBizTranRole().getBizTranRoleCode());
            bizTranRoleTableVo.setRoleName(operator_BizTranRole.getBizTranRole().getBizTranRoleName());
            bizTranRoleTableVo.setValidThruDate(
                operator_BizTranRole.getValidThruStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " ～ " +
                    operator_BizTranRole.getValidThruEndDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            bizTranRoleTableVoList.add(bizTranRoleTableVo);
        }

        return bizTranRoleTableVoList;
    }

    /**
     * {@link Oa11030Controller#get(Long operatorId, Model model)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test() {
        // テスト対象クラス生成
        Oa11030Controller oa11030Controller = createOa11030Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();

        // 期待値
        String expectedViewName = "oa11030"; // ToDo: 遷移制御
        changeCause = null;
        Oa11030Vo expectedVo = createOa11030Vo();

        // 実行
        String actualViewName = oa11030Controller.get(operatorId, model);
        Oa11030Vo actualVo = (Oa11030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11030Controller#get(Long operatorId, Model model)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test1() {
        // テスト対象クラス生成
        Oa11030Controller oa11030Controller = createOa11030Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        operatorId = 11L;

        // 期待値
        String expectedViewName = "oa11030";
        String expectedMessageCode = "EOA13008";
        String expectedMessageArgs0 = "有効期限開始";

        // 実行
        String actualViewName = oa11030Controller.get(operatorId, model);
        Oa11030Vo actualVo = (Oa11030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(expectedMessageArgs0);
    }

    /**
     * {@link Oa11030Controller#get(Long operatorId, Model model)}テスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void get_test2() {
        // テスト対象クラス生成
        Oa11030Controller oa11030Controller = createOa11030Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        operatorId = 12L;

        // 期待値
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10001";

        // 実行
        String actualViewName = oa11030Controller.get(operatorId, model);
        Oa11030Vo actualVo = (Oa11030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }

    /**
     * {@link Oa11030Controller#update(Model model, Oa11030Vo vo)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test() {
        // テスト対象クラス生成
        Oa11030Controller oa11030Controller = createOa11030Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        isDeviceAuth = true;
        Oa11030Vo vo = createOa11030Vo();

        // 期待値
        String expectedViewName = "oa11030"; // ToDo: 遷移制御
        Oa11030Vo expectedVo = createOa11030Vo();

        // 実行
        String actualViewName = oa11030Controller.update(model, vo);
        Oa11030Vo actualVo = (Oa11030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo).usingRecursiveComparison().isEqualTo(expectedVo);
    }

    /**
     * {@link Oa11030Controller#update(Model model, Oa11030Vo vo)}テスト
     *  ●パターン
     *    例外（GunmaRuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test1() {
        // テスト対象クラス生成
        Oa11030Controller oa11030Controller = createOa11030Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        operatorId = 21L;
        Oa11030Vo vo = createOa11030Vo();

        // 期待値
        String expectedViewName = "oa11030";
        String expectedMessageCode = "EOA13002";
        String expectedMessageArgs0 = "オペレーターID";

        // 実行
        String actualViewName = oa11030Controller.update(model, vo);
        Oa11030Vo actualVo = (Oa11030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
        assertThat(actualVo.getMessageArgs().get(0)).isEqualTo(expectedMessageArgs0);
    }

    /**
     * {@link Oa11030Controller#update(Model model, Oa11030Vo vo)}テスト
     *  ●パターン
     *    例外（RuntimeException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test2() {
        // テスト対象クラス生成
        Oa11030Controller oa11030Controller = createOa11030Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        operatorId = 22L;
        Oa11030Vo vo = createOa11030Vo();

        // 期待値
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10001";

        // 実行
        String actualViewName = oa11030Controller.update(model, vo);
        Oa11030Vo actualVo = (Oa11030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }

    /**
     * {@link Oa11030Controller#update(Model model, Oa11030Vo vo)}テスト
     *  ●パターン
     *    例外（OptimisticLockingFailureException）発生
     *
     *  ●検証事項
     *  ・戻り値
     *  ・エラーメッセージのセット
     *
     */
    @Test
    @Tag(TestSize.SMALL)
    void update_test3() {
        // テスト対象クラス生成
        Oa11030Controller oa11030Controller = createOa11030Controller();

        // 実行値
        ConcurrentModel model = new ConcurrentModel();
        operatorId = 23L;
        Oa11030Vo vo = createOa11030Vo();

        // 期待値
        String expectedViewName = "oa19999";
        String expectedMessageCode = "EOA10002";

        // 実行
        String actualViewName = oa11030Controller.update(model, vo);
        Oa11030Vo actualVo = (Oa11030Vo) model.getAttribute("form");

        // 結果検証
        assertThat(actualViewName).isEqualTo(expectedViewName);
        assertThat(actualVo.getMessageCode()).isEqualTo(expectedMessageCode);
    }
}