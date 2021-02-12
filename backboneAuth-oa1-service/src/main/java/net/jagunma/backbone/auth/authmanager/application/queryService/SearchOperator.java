package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchBizTranRoleRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchSubSystemRoleRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorsSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorsSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
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
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTrace;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraceCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraceRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.signOutTrace.SignOutTraces;
import net.jagunma.backbone.auth.authmanager.model.types.AccountLockStatus;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.common.ddd.model.criterias.BooleanCriteria;
import net.jagunma.common.ddd.model.criterias.LocalDateCriteria;
import net.jagunma.common.ddd.model.criterias.LongCriteria;
import net.jagunma.common.ddd.model.criterias.ShortCriteria;
import net.jagunma.common.ddd.model.criterias.StringCriteria;
import net.jagunma.common.ddd.model.orders.Order;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;

/**
 * オペレーター検索サービス
 */
@Service
public class SearchOperator {

    private final OperatorRepository operatorRepository;
    private final AccountLockRepository accountLockRepository;
    private final PasswordHistoryRepository passwordHistoryRepository;
    private final SignInTraceRepository signInTraceRepository;
    private final SignOutTraceRepository signOutTraceRepository;
    private final Operator_SubSystemRoleRepository operator_SubSystemRoleRepository;
    private final Operator_BizTranRoleRepository operator_BizTranRoleRepository;
    private final OperatorHistoryHeaderRepository operatorHistoryHeaderRepository;

    // コンストラクタ
    public SearchOperator(OperatorRepository operatorRepository,
        AccountLockRepository accountLockRepository,
        PasswordHistoryRepository passwordHistoryRepository,
        SignInTraceRepository signInTraceRepository,
        SignOutTraceRepository signOutTraceRepository,
        Operator_SubSystemRoleRepository operator_SubSystemRoleRepository,
        Operator_BizTranRoleRepository operator_BizTranRoleRepository,
        OperatorHistoryHeaderRepository operatorHistoryHeaderRepository) {

        this.operatorRepository = operatorRepository;
        this.accountLockRepository = accountLockRepository;
        this.passwordHistoryRepository = passwordHistoryRepository;
        this.signInTraceRepository = signInTraceRepository;
        this.signOutTraceRepository = signOutTraceRepository;
        this.operator_SubSystemRoleRepository = operator_SubSystemRoleRepository;
        this.operator_BizTranRoleRepository = operator_BizTranRoleRepository;
        this.operatorHistoryHeaderRepository = operatorHistoryHeaderRepository;
    }

    /**
     * オペレーターを検索します
     *
     * @param request  オペレーター検索 Request
     * @param response オペレーター検索 Response
     */
    public void execute(OperatorSearchRequest request, OperatorSearchResponse response) {

//        // パラメーターの検証
//        SearchOperatorValidator.with(request).validate();

        // オペレーター検索
        Operator operator = operatorRepository.findOneById(request.getOperatorId());

        // オペレーターIDリスト
        List<Long> operatorIdList = new ArrayList<Long>(Arrays.asList(operator.getOperatorId()));
        // オペレーターコードリスト
        List<String> operatorCodeList = new ArrayList<String>(Arrays.asList(operator.getOperatorCode()));

        // オペレーター_サブシステムロール割当群検索
        Operator_SubSystemRoles operator_SubSystemRoles = searchOperator_SubSystemRoles(operatorIdList);
        // オペレーター_取引ロール割当群検索
        Operator_BizTranRoles operator_BizTranRoles = searchOperator_BizTranRoles(operatorIdList);
        // アカウントロック群検索
        AccountLocks AccountLocks = searchAccountLocks(operatorIdList);
        // パスワード履歴群検索
        PasswordHistories passwordHistories = searchPasswordHistories(operatorIdList);
        // サインイン証跡群検索
        SignInTraces signInTraces =  searchSignInTraces(operatorCodeList);
        // サインアウト証跡群検索
        SignOutTraces signOutTraces = searchSignOutTraces(operatorIdList);
        // オペレーター履歴ヘッダー群検索
        OperatorHistoryHeaders operatorHistoryHeaders = searchOperatorHistoryHeaders(operatorIdList);

        response.setOperator(operator);
        response.setOperator_SubSystemRoles(operator_SubSystemRoles);
        response.setOperator_BizTranRoles(operator_BizTranRoles);
        response.setAccountLocks(AccountLocks);
        response.setOperatorHistoryHeaders(operatorHistoryHeaders);
    }

    /**
     * オペレーター群を検索します
     *
     * @param request  オペレーター検索 Request
     * @param response オペレーター群検索 Response
     */
    public void execute(OperatorsSearchRequest request, OperatorsSearchResponse response) {

        // パラメーターの検証
        SearchOperatorValidator.with(request).validate();

        // オペレーター検索
        Orders orders = Orders.empty().addOrder("branchCode").addOrder("operatorCode");
        Operators operators = operatorRepository.selectBy(createOperatorCriteria(request), orders);

        // オペレーターIDリスト
        List<Long> operatorIdList = operators.getValues().stream().map(Operator::getOperatorId).collect(Collectors.toList());
        // オペレーターコードリスト
        List<String> operatorCodeList = operators.getValues().stream().map(Operator::getOperatorCode).collect(Collectors.toList());

        // オペレーター_サブシステムロール割当群検索
        Operator_SubSystemRoles operator_SubSystemRoles = searchOperator_SubSystemRoles(operatorIdList);
        // オペレーター_取引ロール割当群検索
        Operator_BizTranRoles operator_BizTranRoles = searchOperator_BizTranRoles(operatorIdList);
        // アカウントロック群検索
        AccountLocks AccountLocks = searchAccountLocks(operatorIdList);
        // パスワード履歴群検索
        PasswordHistories passwordHistories = searchPasswordHistories(operatorIdList);
        // サインイン証跡群検索
        SignInTraces signInTraces =  searchSignInTraces(operatorCodeList);
        // サインアウト証跡群検索
        SignOutTraces signOutTraces = searchSignOutTraces(operatorIdList);
        // オペレーター履歴ヘッダー群検索
        OperatorHistoryHeaders operatorHistoryHeaders = searchOperatorHistoryHeaders(operatorIdList);

        List<Operator> removeOperator = newArrayList();
        for (Operator operator : operators.getValues()) {
            // オペレーター_サブシステムロール割当の検索条件判定
            List<Operator_SubSystemRole> operator_SubSystemRoleList = operator_SubSystemRoles.getValues().stream().filter(a->a.getOperatorId().equals(operator.getOperatorId())).collect(Collectors.toList());
            if (!conditionsOperatorSubSystemRole(request, operator_SubSystemRoleList)) {
                // 削除対象を退避
                removeOperator.add(operator);
                continue;
            }
            // オペレーター_取引ロール割当の検索条件判定
            List<Operator_BizTranRole> operator_BizTranRoleList = operator_BizTranRoles.getValues().stream().filter(a->a.getOperatorId().equals(operator.getOperatorId())).collect(Collectors.toList());
            if (!conditionsOperatorBizTranRole(request, operator_BizTranRoleList)) {
                // 削除対象を退避
                removeOperator.add(operator);
                continue;
            }
            // アカウントロックの検索条件の検索条件判定
            AccountLock accountLock = AccountLocks.getValues().stream().filter(a->a.getOperatorId().equals(operator.getOperatorId())).findFirst().orElse(null);
            if (!conditionsAccountLock(request, accountLock)) {
                // 削除対象を退避
                removeOperator.add(operator);
                continue;
            }
            // サインイン証跡の検索条件判定
            SignInTrace signInTrace = signInTraces.getValues().stream().filter(a->a.getOperatorCode().equals(operator.getOperatorCode())).findFirst().orElse(null);
            if (!conditionsSignInTrace(request, signInTrace)) {
                // 削除対象を退避
                removeOperator.add(operator);
                continue;
            }
            // サインアウトの検索条件判定
            SignOutTrace signOutTrace = signOutTraces.getValues().stream().filter(a->a.getOperatorId().equals(operator.getOperatorId())).findFirst().orElse(null);
            if (!conditionsSignOutTrace(request, signOutTrace)) {
                // 削除対象を退避
                removeOperator.add(operator);
            }
            // パスワード履歴の検索条件の検索条件判定
            PasswordHistory passwordHistory = passwordHistories.getValues().stream().filter(a->a.getOperatorId().equals(operator.getOperatorId())).findFirst().orElse(null);
            if (!conditionsPasswordHistory(request, passwordHistory)) {
                // 削除対象を退避
                removeOperator.add(operator);
                continue;
            }
        }
        // 対象から削除
        operators.getValues().removeAll(removeOperator);

        response.setOperators(operators);
        response.setOperator_SubSystemRoles(operator_SubSystemRoles);
        response.setOperator_BizTranRoles(operator_BizTranRoles);
        response.setAccountLocks(AccountLocks);
        response.setOperatorHistoryHeaders(operatorHistoryHeaders);
    }

    /**
     * オペレーター_サブシステムロール割当の検索条件判定を行います
     *
     * @param request                   オペレーターリスト参照サービス Request
     * @param operatorSubSystemRoleList オペレーター_サブシステムロール割当
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsOperatorSubSystemRole(OperatorsSearchRequest request, List<Operator_SubSystemRole> operatorSubSystemRoleList) {

        if (request.getSubSystemRoleList() == null) { return true; }
        if (request.getSubSystemRoleConditionsSelect() == null) { return true; }

        if (request.getSubSystemRoleList().size() > 0) {
            //サブシステムロールでの検索条件設定あり（無しの場合は全件対象）

            //対象オペレーターにオペレーター_サブシステムロール割当無し
            if (operatorSubSystemRoleList.size() == 0) { return false; }

            // サブシステムロール条件選択による振り分け
            if (request.getSubSystemRoleConditionsSelect() == 1) {
                // AND
                for (OparatorSearchSubSystemRoleRequest subSystemRoleRequest : request.getSubSystemRoleList()) {
                    if (!conditionsOperatorSubSystemRoleRow(operatorSubSystemRoleList, subSystemRoleRequest)) {
                        return false;
                    }
                }
            } else if (request.getSubSystemRoleConditionsSelect() == 2) {
                // OR
                boolean orReturn = false;
                for (OparatorSearchSubSystemRoleRequest subSystemRoleRequest : request.getSubSystemRoleList()) {
                    if (conditionsOperatorSubSystemRoleRow(operatorSubSystemRoleList, subSystemRoleRequest)) {
                        orReturn = true;
                        break;
                    }
                }
                if (!orReturn) { return false; }
            }
        }

        return true;
    }

    /**
     * オペレーター_サブシステムロール割当１行の検索条件判定を行います
     *
     * @param operatorSubSystemRoleList オペレーター_サブシステムロール割当リスト
     * @param subSystemRoleRequest      サブシステムの検索条件
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsOperatorSubSystemRoleRow(List<Operator_SubSystemRole> operatorSubSystemRoleList,
        OparatorSearchSubSystemRoleRequest subSystemRoleRequest) {

        LocalDate defaultFromDate = LocalDate.of(1,1,1);
        LocalDate defaultToDate = LocalDate.of(9999,12,31);

        if (subSystemRoleRequest.getValidThruSelect() == null) { return true; }

        if (subSystemRoleRequest.getValidThruSelect() == 0) {
            // 指定なし
            return operatorSubSystemRoleList.stream().filter(o ->
                o.getSubSystemRoleCode().equals(subSystemRoleRequest.getSubSystemRoleCode())).count() != 0;
        } else if (subSystemRoleRequest.getValidThruSelect() == 1) {
            // 状態指定日
            if (subSystemRoleRequest.getValidThruStatusDate() == null) { return true; }
            return operatorSubSystemRoleList.stream().filter(o ->
                o.getSubSystemRoleCode().equals(subSystemRoleRequest.getSubSystemRoleCode()) &&
                    o.getValidThruStartDate().compareTo(subSystemRoleRequest.getValidThruStatusDate()) <= 0 &&
                    o.getValidThruEndDate().compareTo(subSystemRoleRequest.getValidThruStatusDate()) >= 0).count() != 0;
        } else if (subSystemRoleRequest.getValidThruSelect() == 2) {
            // 条件指定
            LocalDate validThruStartDateFrom = (subSystemRoleRequest.getValidThruStartDateFrom() == null)? defaultFromDate : subSystemRoleRequest.getValidThruStartDateFrom();
            LocalDate validThruStartDateTo = (subSystemRoleRequest.getValidThruStartDateTo() == null)? defaultToDate : subSystemRoleRequest.getValidThruStartDateTo();
            LocalDate validThruEndDateFrom = (subSystemRoleRequest.getValidThruEndDateFrom() == null)? defaultFromDate : subSystemRoleRequest.getValidThruEndDateFrom();
            LocalDate validThruEndtDateTo = (subSystemRoleRequest.getValidThruEndDateTo() == null)? defaultToDate : subSystemRoleRequest.getValidThruEndDateTo();
            return operatorSubSystemRoleList.stream().filter(o ->
                o.getSubSystemRoleCode().equals(subSystemRoleRequest.getSubSystemRoleCode()) &&
                    (o.getValidThruStartDate().compareTo(validThruStartDateFrom) >= 0 ||
                        o.getValidThruStartDate().compareTo(validThruStartDateTo) <= 0) &&
                    (o.getValidThruEndDate().compareTo(validThruEndDateFrom) >= 0 ||
                        o.getValidThruEndDate().compareTo(validThruEndtDateTo) <= 0)).count() != 0;
        }

        return true;
    }

    /**
     * オペレーター_取引ロール割当の検索条件判定を行います
     *
     * @param request                 オペレーターリスト参照サービス Request
     * @param operatorBizTranRoleList オペレーター_取引割当
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsOperatorBizTranRole(OperatorsSearchRequest request, List<Operator_BizTranRole> operatorBizTranRoleList) {

        if (request.getBizTranRoleList() == null) { return true; }
        if (request.getBizTranRoleConditionsSelect() == null) { return true; }

        if (request.getBizTranRoleList().size() > 0) {
            //取引ロールロールでの検索条件設定あり（無しの場合は全件対象）

            //対象オペレーターにオペレーター_取引ロール割当無し
            if (operatorBizTranRoleList.size() == 0) { return false; }

            // 取引ロール条件選択による振り分け
            if (request.getBizTranRoleConditionsSelect() == 1) {
                // AND
                for (OparatorSearchBizTranRoleRequest bizTranRoleRequest : request.getBizTranRoleList()) {
                    if (!conditionsOperatorBizTranRoleRow(operatorBizTranRoleList, bizTranRoleRequest)) {
                        return false;
                    }
                }
            } else if (request.getBizTranRoleConditionsSelect() == 2) {
                // OR
                boolean orReturn = false;
                for (OparatorSearchBizTranRoleRequest bizTranRoleRequest : request.getBizTranRoleList()) {
                    if (conditionsOperatorBizTranRoleRow(operatorBizTranRoleList, bizTranRoleRequest)) {
                        orReturn = true;
                        break;
                    }
                }
                if (!orReturn) { return false; }
            }
        }

        return true;
    }

    /**
     * オペレーター_取引ロール割当１行の検索条件判定を行います
     *
     * @param operatorBizTranRoleList オペレーター_取引ロール割当リスト
     * @param bizTranRoleRequest      取引の検索条件
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsOperatorBizTranRoleRow(List<Operator_BizTranRole> operatorBizTranRoleList,
        OparatorSearchBizTranRoleRequest bizTranRoleRequest){

        LocalDate defaultFromDate = LocalDate.of(1,1,1);
        LocalDate defaultToDate = LocalDate.of(9999,12,31);

        if (bizTranRoleRequest.getValidThruSelect() == null) { return true; }

        if (bizTranRoleRequest.getValidThruSelect() == 0) {
            // 指定なし
            return operatorBizTranRoleList.stream().filter(o ->
                o.getBizTranRoleId().equals(bizTranRoleRequest.getBizTranRoleId())).count() != 0;
        } else if (bizTranRoleRequest.getValidThruSelect() == 1) {
            // 状態指定日
            if (bizTranRoleRequest.getValidThruStatusDate() == null) { return true; }
            return operatorBizTranRoleList.stream().filter(o ->
                o.getBizTranRoleId().equals(bizTranRoleRequest.getBizTranRoleId()) &&
                    o.getValidThruStartDate().compareTo(bizTranRoleRequest.getValidThruStatusDate()) <= 0 &&
                    o.getValidThruEndDate().compareTo(bizTranRoleRequest.getValidThruStatusDate()) >= 0).count() != 0;
        } else if (bizTranRoleRequest.getValidThruSelect() == 2) {
            // 条件指定
            LocalDate validThruStartDateFrom = (bizTranRoleRequest.getValidThruStartDateFrom() == null)? defaultFromDate : bizTranRoleRequest.getValidThruStartDateFrom();
            LocalDate validThruStartDateTo = (bizTranRoleRequest.getValidThruStartDateTo() == null)? defaultToDate : bizTranRoleRequest.getValidThruStartDateTo();
            LocalDate validThruEndDateFrom = (bizTranRoleRequest.getValidThruEndDateFrom() == null)? defaultFromDate : bizTranRoleRequest.getValidThruEndDateFrom();
            LocalDate validThruEndtDateTo = (bizTranRoleRequest.getValidThruEndDateTo() == null)? defaultToDate : bizTranRoleRequest.getValidThruEndDateTo();
            return operatorBizTranRoleList.stream().filter(o ->
                o.getBizTranRoleId().equals(bizTranRoleRequest.getBizTranRoleId()) &&
                    (o.getValidThruStartDate().compareTo(validThruStartDateFrom) >= 0 ||
                        o.getValidThruStartDate().compareTo(validThruStartDateTo) <= 0) &&
                    (o.getValidThruEndDate().compareTo(validThruEndDateFrom) >= 0 ||
                        o.getValidThruEndDate().compareTo(validThruEndtDateTo) <= 0)).count() != 0;
        }

        return true;
    }

    /**
     * アカウントロックの検索条件判定を行います
     *
     * @param request     オペレーターリスト参照サービス Request
     * @param accountLock アカウントロック
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsAccountLock(OperatorsSearchRequest request, AccountLock accountLock) {

        // アカウントロック　最終ロック・アンロック発生日の条件
        if (request.getAccountLockOccurredDateFrom() != null ||
            request.getAccountLockOccurredDateTo() != null) {

            if (accountLock == null) {
                return false;
            } else {
                if (request.getAccountLockOccurredDateFrom() != null) {
                    if (accountLock.getOccurredDateTime().compareTo(request.getAccountLockOccurredDateFrom().atStartOfDay()) < 0 ) {
                        return false;
                    }
                }
                if (request.getAccountLockOccurredDateTo() != null) {
                    if (accountLock.getOccurredDateTime().compareTo(request.getAccountLockOccurredDateTo().plusDays(1).atStartOfDay()) > 0 ) {
                        return false;
                    }
                }
            }
        }

        // アカウントロック ロック状態の条件
        boolean accountLockStatusLock = false;
        boolean accountLockStatusUnlock = false;
        if (request.getAccountLockStatusLock() != null) {accountLockStatusLock = request.getAccountLockStatusLock();}
        if (request.getAccountLockStatusUnlock() != null) {accountLockStatusUnlock = request.getAccountLockStatusUnlock();}

        if (!accountLockStatusLock == accountLockStatusUnlock) {
            if (accountLockStatusLock) {
                //ロック
                if (accountLock == null || accountLock.getLockStatus().equals(AccountLockStatus.アンロック.getCode())) {
                    return false;
                }
            } else if (accountLockStatusUnlock) {
                //アンロック
                if (accountLock != null && accountLock.getLockStatus().equals(AccountLockStatus.ロック.getCode())) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * パスワード履歴の検索条件判定を行います
     *
     * @param request         オペレーターリスト参照サービス Request
     * @param passwordHistory パスワード履歴
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsPasswordHistory(OperatorsSearchRequest request, PasswordHistory passwordHistory) {

        // パスワード履歴　最終パスワード変更日の条件
        boolean passwordHistoryCheck = false;
        if (request.getPasswordHistoryCheck() != null) {passwordHistoryCheck = request.getPasswordHistoryCheck();}
        if (passwordHistoryCheck &&
            request.getPasswordHistoryLastChangeDate() != null) {
            LocalDate passwodrChanheDate = LocalDate.now().minusDays(request.getPasswordHistoryLastChangeDate());
            if ("1".equals(request.getPasswordHistoryLastChangeDateStatus())) {
                // 変更した
                if (passwordHistory == null) {
                    return false;
                } else {
                    if (passwordHistory.getChangeDateTime().toLocalDate().compareTo(passwodrChanheDate) >= 0) {
                        return false;
                    }
                }
            } else if ("2".equals(request.getPasswordHistoryLastChangeDateStatus())) {
                // 変更していない
                if (passwordHistory != null) {
                    if (passwordHistory.getChangeDateTime().toLocalDate().compareTo(passwodrChanheDate) >= 0) {
                        return false;
                    }
                }
            }
        }

        // パスワード履歴　最終パスワード変更種別の条件
        boolean passwordHistoryChangeType0 = false;
        boolean passwordHistoryChangeType1 = false;
        boolean passwordHistoryChangeType2 = false;
        boolean passwordHistoryChangeType3 = false;
        if (request.getPasswordHistoryChangeType0() != null) {passwordHistoryChangeType0 = request.getPasswordHistoryChangeType0();}
        if (request.getPasswordHistoryChangeType1() != null) {passwordHistoryChangeType1 = request.getPasswordHistoryChangeType1();}
        if (request.getPasswordHistoryChangeType2() != null) {passwordHistoryChangeType2 = request.getPasswordHistoryChangeType2();}
        if (request.getPasswordHistoryChangeType3() != null) {passwordHistoryChangeType3 = request.getPasswordHistoryChangeType3();}
        if (passwordHistoryChangeType0 != passwordHistoryChangeType1 ||
            passwordHistoryChangeType0 != passwordHistoryChangeType2 ||
            passwordHistoryChangeType0 != passwordHistoryChangeType3) {

            if (passwordHistory == null) { return false; }
            // 最終パスワード変更種別が全て同じでない
            if (passwordHistory.getPasswordChangeType().equals(PasswordChangeType.初期) && passwordHistoryChangeType0) {
                // 初期
                return true;
            } else if (passwordHistory.getPasswordChangeType().equals(PasswordChangeType.ユーザーによる変更) && passwordHistoryChangeType1) {
                // ユーザーによる変更
                return true;
            } else if (passwordHistory.getPasswordChangeType().equals(PasswordChangeType.管理者によるリセット) && passwordHistoryChangeType2) {
                // 管理者によるリセット
                return true;
            } else if (passwordHistory.getPasswordChangeType().equals(PasswordChangeType.機器認証パスワード) && passwordHistoryChangeType3) {
                // 機器認証パスワード
                return true;
            }
        }

        return true;
    }

    /**
     * サインイン証跡の検索条件判定を行います
     *
     * @param request     オペレーターリスト参照サービス Request
     * @param signInTrace サインイン証跡
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsSignInTrace(OperatorsSearchRequest request, SignInTrace signInTrace) {

        // サインイン証跡　最終サインイン試行日の条件
        if (request.getSignintraceTrydateFrom() != null ||
            request.getSignintraceTrydateTo() != null) {

            if (signInTrace == null) { return false; }
            if (request.getSignintraceTrydateFrom() != null &&
                signInTrace.getTryDateTime().compareTo(request.getSignintraceTrydateFrom().atStartOfDay()) < 0 ) {
                return false;
            }
            if (request.getSignintraceTrydateTo() != null &&
                signInTrace.getTryDateTime().compareTo(request.getSignintraceTrydateTo().plusDays(1).atStartOfDay()) > 0 ) {
                return false;
            }
        }

        // サインイン証跡　最終サインイン元IPアドレスの条件
        if (request.getSignintraceTryIpAddress() != null && request.getSignintraceTryIpAddress().length() > 0) {

            if (signInTrace == null) { return false; }
            if (!signInTrace.getTryIpAddress().startsWith(request.getSignintraceTryIpAddress())) {
                return false;
            }
        }

        // サインイン証跡　最終サインオペレーションの条件
        if (request.getSignintraceSignIn() != null && request.getSignintraceSignIn()) {
            if (signInTrace == null) { return false; }
        }
        if (request.getSignintraceSignInResult() != null && request.getSignintraceSignInResult().length > 0) {

            if (signInTrace == null) { return false; }
            // 配列をリストに変換
            List<Short> signInResultList = Arrays.asList(request.getSignintraceSignInResult());
            if (!signInResultList.contains(signInTrace.getSignInResult())) {
                return false;
            }
        }

        return true;
    }

    /**
     * サインアウト証跡の検索条件判定を行います
     *
     * @param request      オペレーターリスト参照サービス Request
     * @param signOutTrace サインアウト証跡
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsSignOutTrace(OperatorsSearchRequest request, SignOutTrace signOutTrace) {

        // サインアウト証跡　最終サインオペレーションの条件
        if (request.getSignintraceSignOut() != null && request.getSignintraceSignOut()) {
            return signOutTrace != null;
        }

        return true;
    }

    /**
     * オペレーター検索条件を作成します
     *
     * @param request オペレーターリスト参照サービス Request
     * @return オペレータ検索条件
     */
    private OperatorCriteria createOperatorCriteria(OperatorsSearchRequest request) {
        OperatorCriteria criteria = new OperatorCriteria();
        // オペレーターコード
        criteria.getOperatorCodeCriteria().assignFrom(
            (request.getOperatorCodeCriteria() == null)? new StringCriteria() : request.getOperatorCodeCriteria());
        // オペレーター名
        criteria.getOperatorNameCriteria().assignFrom(
            (request.getOperatorNameCriteria() == null)? new StringCriteria() : request.getOperatorNameCriteria());
        // メールアドレス
        criteria.getMailAddressCriteria().assignFrom(
            (request.getMailAddressCriteria() == null)? new StringCriteria() : request.getMailAddressCriteria());
        // 有効期限開始
        criteria.getValidThruStartDateCriteria().assignFrom(
            (request.getValidThruStartDateCriteria() == null)? new LocalDateCriteria() : request.getValidThruStartDateCriteria());
        // 有効期限開始
        criteria.getValidThruEndDateCriteria().assignFrom(
            (request.getValidThruEndDateCriteria() == null)? new LocalDateCriteria() : request.getValidThruEndDateCriteria());
        // ＪＡID
        if (request.getJaIdCriteria() != null && request.getJaIdCriteria().getEqualTo() != null) {
            criteria.getJaIdentifierCriteria().setEqualTo(request.getJaIdCriteria().getEqualTo());
        }
        // 店舗ID
        criteria.getBranchIdCriteria().assignFrom(
            (request.getBranchIdCriteria() == null)? new LongCriteria() : request.getBranchIdCriteria());
        // 利用可否状態
        criteria.getAvailableStatusCriteria().assignFrom(
            (request.getAvailableStatusCriteria() == null)? new ShortCriteria() : request.getAvailableStatusCriteria());
        // 機器認証
        criteria.getIsDeviceAuthCriteria().assignFrom(
            (request.getIsDeviceAuthCriteria() == null)? new BooleanCriteria(): request.getIsDeviceAuthCriteria());

        return criteria;
    }

    /**
     * オペレーター_サブシステムロール割当群を検索します
     *
     * @param operatorIdList オペレーターIDリスト
     * @return オペレーター_サブシステムロール割当群
     */
    private  Operator_SubSystemRoles searchOperator_SubSystemRoles(List<Long> operatorIdList) {
        Operator_SubSystemRoleCriteria criteria = new Operator_SubSystemRoleCriteria();
        criteria.getOperatorIdCriteria().getIncludes().addAll(operatorIdList);
        return operator_SubSystemRoleRepository.selectBy(criteria, Orders.empty().addOrder("operatorId"));
    }

    /**
     * オペレーター_取引ロール割当群を検索します
     *
     * @param operatorIdList オペレーターIDリスト
     * @return オペレーター_取引ロール割当群
     */
    private Operator_BizTranRoles searchOperator_BizTranRoles(List<Long> operatorIdList) {
        Operator_BizTranRoleCriteria criteria = new Operator_BizTranRoleCriteria();
        criteria.getOperatorIdCriteria().getIncludes().addAll(operatorIdList);
        return operator_BizTranRoleRepository.selectBy(criteria, Orders.empty().addOrder("operatorId"));
    }

    /**
     * アカウントロック群を検索します
     *
     * @param operatorIdList オペレーターIDリスト
     * @return アカウントロック群
     */
    private AccountLocks searchAccountLocks(List<Long> operatorIdList) {
        AccountLockCriteria criteria = new AccountLockCriteria();
        criteria.getOperatorIdCriteria().getIncludes().addAll(operatorIdList);
        return accountLockRepository.selectBy(criteria, Orders.empty().addOrder("operatorId").addOrder("occurredDateTime", Order.DESC));
    }

    /**
     * パスワード履歴群を検索します
     *
     * @param operatorIdList オペレーターIDリスト
     * @return パスワード履歴群
     */
    private PasswordHistories searchPasswordHistories(List<Long> operatorIdList) {
        PasswordHistoryCriteria criteria = new PasswordHistoryCriteria();
        criteria.getOperatorIdCriteria().getIncludes().addAll(operatorIdList);
        return passwordHistoryRepository.selectBy(criteria, Orders.empty().addOrder("operatorId").addOrder("changeDateTime", Order.DESC));
    }

    /**
     * サインイン証跡群を検索します
     *
     * @param operatorCodeList オペレーターコードリスト
     * @return サインイン証跡群
     */
    private SignInTraces searchSignInTraces(List<String> operatorCodeList) {
        SignInTraceCriteria criteria = new SignInTraceCriteria();
        criteria.getOperatorCodeCriteria().getIncludes().addAll(operatorCodeList);
        return signInTraceRepository.selectBy(criteria, Orders.empty().addOrder("operatorCode").addOrder("tryDateTime", Order.DESC));
    }

    /**
     * サインアウト証跡群を検索します
     *
     * @param operatorIdList オペレーターIDリスト
     * @return サインアウト証跡群
     */
    private SignOutTraces searchSignOutTraces(List<Long> operatorIdList) {
        SignOutTraceCriteria criteria = new SignOutTraceCriteria();
        criteria.getOperatorIdCriteria().getIncludes().addAll(operatorIdList);
        return signOutTraceRepository.selectBy(criteria, Orders.empty().addOrder("operatorId").addOrder("signOutDateTime", Order.DESC));
    }

    /**
     * オペレーター履歴ヘッダー検索群を検索します
     *
     * @param operatorIdList オペレーターIDリスト
     * @return オペレーター履歴ヘッダー群
     */
    private OperatorHistoryHeaders searchOperatorHistoryHeaders(List<Long> operatorIdList) {
        OperatorHistoryHeaderCriteria criteria = new OperatorHistoryHeaderCriteria();
        criteria.getOperatorIdCriteria().getIncludes().addAll(operatorIdList);
        return operatorHistoryHeaderRepository.selectBy(criteria,
            Orders.empty().addOrder("operatorId").addOrder("changeDateTime", Order.DESC));
    }
}
