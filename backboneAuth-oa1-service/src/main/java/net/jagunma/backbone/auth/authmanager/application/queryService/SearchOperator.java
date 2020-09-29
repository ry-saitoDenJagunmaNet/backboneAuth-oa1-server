package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchBizTranRoleRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OparatorSearchSubSystemRoleRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLockCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocksRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorsRepository;
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
import net.jagunma.backbone.auth.authmanager.model.types.ConditionsExpirationSelect;
import net.jagunma.backbone.auth.authmanager.model.types.ConditionsSelect;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.common.ddd.model.orders.Order;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.util.strings2.Strings2;
import org.springframework.stereotype.Service;

/**
 * オペレータリスト検索サービス
 */
@Service
public class SearchOperator {

    // オペレーターIDリスト
    private List<Long> operatorIdList;
    // オペレーターコードリスト
    private List<String> operatorCodeList;

    private final OperatorsRepository operatorsRepository;
    private final AccountLocksRepository accountLocksRepository;
    private final PasswordHistoriesRepository passwordHistoriesRepository;
    private final SignInTracesRepository signInTracesRepository;
    private final SignOutTracesRepository signOutTracesRepository;
    private final Operator_SubSystemRolesRepository operator_SubSystemRolesRepository;
    private final Operator_BizTranRolesRepository operator_BizTranRolesRepository;

    // コンストラクタ
    public SearchOperator(OperatorsRepository operatorsRepository,
        AccountLocksRepository accountLocksRepository,
        PasswordHistoriesRepository passwordHistoriesRepository,
        SignInTracesRepository signInTracesRepository,
        SignOutTracesRepository signOutTracesRepository,
        Operator_SubSystemRolesRepository operator_SubSystemRolesRepository,
        Operator_BizTranRolesRepository operator_BizTranRolesRepository) {

        this.operatorsRepository = operatorsRepository;
        this.accountLocksRepository = accountLocksRepository;
        this.passwordHistoriesRepository = passwordHistoriesRepository;
        this.signInTracesRepository = signInTracesRepository;
        this.signOutTracesRepository = signOutTracesRepository;
        this.operator_SubSystemRolesRepository = operator_SubSystemRolesRepository;
        this.operator_BizTranRolesRepository = operator_BizTranRolesRepository;
    }

    /**
     * オペレーター群を検索します。
     *
     * @param request オペレーターリスト参照サービス Request
     * @param response オペレーターリスト参照サービス Response
     */
    public void execute(OperatorSearchRequest request, OperatorSearchResponse response) {

        // パラメーターの検証
        SeatchOperatorValidator.with(request).validate();

        // ページ
        response.setPageNo(request.getPageNo());
        System.out.println("### PageNo="+request.getPageNo());

        // オペレーター検索
        Orders orders = Orders.empty().addOrder("tempoCode").addOrder("operatorCode");
        Operators operators = operatorsRepository.selectBy(createOperatorCriteria(request), orders);
        response.setOperators(operators);

        // オペレーターIDおよびオペレーターコードのリストを設定
        setOperatorIdAndCodeList(operators);

        // オペレーター_サブシステムロール割当検索
        Operator_SubSystemRoles operator_SubSystemRoles = operator_SubSystemRolesRepository.selectBy(createOperator_SubSystemRoleCriteria(),
            Orders.empty().addOrder("OperatorId"));
        response.setOperator_SubSystemRoles(operator_SubSystemRoles);
        // オペレーター_取引ロール割当検索
        Operator_BizTranRoles operator_BizTranRoles = operator_BizTranRolesRepository.selectBy(createOperator_BizTranRoleCriteria(),
            Orders.empty().addOrder("OperatorId"));
        response.setOperator_BizTranRoles(operator_BizTranRoles);
        // アカウントロック検索
        AccountLocks AccountLocks = accountLocksRepository.selectBy(createAccountLockCriteria(),
            Orders.empty().addOrder("OperatorId").addOrder("OccurredDateTime", Order.DESC));
        response.setAccountLocks(AccountLocks);
        // パスワード履歴検索
        PasswordHistories passwordHistories = passwordHistoriesRepository.selectBy(createPasswordHistoryCriteria(),
            Orders.empty().addOrder("OperatorId").addOrder("ChangeDateTime", Order.DESC));
        response.setPasswordHistories(passwordHistories);
        // サインイン証跡検索
        SignInTraces signInTraces =  signInTracesRepository.selectBy(createSignInTraceCriteria(),
            Orders.empty().addOrder("OperatorCode").addOrder("TryDateTime", Order.DESC));
        response.setSignInTraces(signInTraces);
        // サインアウト証跡
        SignOutTraces signOutTraces = signOutTracesRepository.selectBy(createSignOutTraceCriteria(),
            Orders.empty().addOrder("OperatorId").addOrder("SignOutDateTime", Order.DESC));
        response.setSignOutTraces(signOutTraces);

        List<Operator> removeOperator = newArrayList();
        for (Operator operator : operators.getValues()) {
            // オペレーター_サブシステムロール割当検索
            List<Operator_SubSystemRole> operator_SubSystemRolesList = operator_SubSystemRoles.getValues().stream().filter(a->a.getOperatorId().equals(operator.getOperatorId())).collect(Collectors.toList());
            if (!conditionsOperatorSubSystemRole(request, operator_SubSystemRolesList)) {
                // 削除対象を退避
                removeOperator.add(operator);
                continue;
            }
            // オペレーター_取引ロール割当検索
            List<Operator_BizTranRole> operator_BizTranRoleList = operator_BizTranRoles.getValues().stream().filter(a->a.getOperatorId().equals(operator.getOperatorId())).collect(Collectors.toList());
            if (!conditionsOperatorBizTranRole(request, operator_BizTranRoleList)) {
                // 削除対象を退避
                removeOperator.add(operator);
                continue;
            }
            // アカウントロックの検索条件判定
            AccountLock accountLock = AccountLocks.getValues().stream().filter(a->a.getOperatorId().equals(operator.getOperatorId())).findFirst().orElse(null);
            if (!conditionsAccountLock(request, accountLock)) {
                // 削除対象を退避
                removeOperator.add(operator);
                continue;
            }
            // パスワード履歴の検索条件判定
            PasswordHistory passwordHistory = passwordHistories.getValues().stream().filter(a->a.getOperatorId().equals(operator.getOperatorId())).findFirst().orElse(null);
            if (!conditionsPasswordHistory(request, passwordHistory)) {
                // 削除対象を退避
                removeOperator.add(operator);
                continue;
            }
            // サインイン証跡検索
            SignInTrace signInTrace = signInTraces.getValues().stream().filter(a->a.getOperatorCode().equals(operator.getOperatorCode())).findFirst().orElse(null);
            if (!conditionsSignInTrace(request, signInTrace)) {
                // 削除対象を退避
                removeOperator.add(operator);
                continue;
            }
            // サインアウト証跡
            SignOutTrace signOutTrace = signOutTraces.getValues().stream().filter(a->a.getOperatorId().equals(operator.getOperatorId())).findFirst().orElse(null);
            if (!conditionsSignOutTrace(request, signOutTrace)) {
                // 削除対象を退避
                removeOperator.add(operator);
            }
        }
        // 対象から削除
        operators.getValues().removeAll(removeOperator);
    }

    /**
     * オペレーター_サブシステムロール割当の検索条件判定を行います。
     *
     * @param request                   オペレーターリスト参照サービス Request
     * @param operatorSubSystemRoleList オペレーター_サブシステムロール割当
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsOperatorSubSystemRole(OperatorSearchRequest request, List<Operator_SubSystemRole> operatorSubSystemRoleList) {

        // 選択チェックしたサブシステムロールを検索条件にする
        List<OparatorSearchSubSystemRoleRequest> subSystemRoleRequestList =
            request.getSubSystemRoleList().stream().filter(reqossr->
                Oa11010Vo.CHECKBOX_TRUE.equals(reqossr.getSubSystemRoleSelected())).collect(Collectors.toList());
        if (subSystemRoleRequestList.size() > 0) {
            //サブシステムロールでの検索条件設定あり（無しの場合は全件対象）

            //対象オペレーターにオペレーター_サブシステムロール割当無し
            if (operatorSubSystemRoleList.size() == 0) { return false; }

            // サブシステムロール条件選択による振り分け
            if (request.getSubSystemRoleConditionsSelect().equals(ConditionsSelect.AND.getCode())) {
                for (OparatorSearchSubSystemRoleRequest subSystemRoleRequest : subSystemRoleRequestList) {
                    if (!conditionsOperatorSubSystemRoleRow(operatorSubSystemRoleList, subSystemRoleRequest)) {
                        return false;
                    }
                }
            } else if (request.getSubSystemRoleConditionsSelect().equals(ConditionsSelect.OR.getCode())) {
                boolean orReturn = false;
                for (OparatorSearchSubSystemRoleRequest subSystemRoleRequest : subSystemRoleRequestList) {
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
     * オペレーター_サブシステムロール割当１行の検索条件判定を行います。
     *
     * @param operatorSubSystemRoleList オペレーター_サブシステムロール割当リスト
     * @param subSystemRoleRequest      サブシステムの検索条件
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsOperatorSubSystemRoleRow(List<Operator_SubSystemRole> operatorSubSystemRoleList,
        OparatorSearchSubSystemRoleRequest subSystemRoleRequest) {

        if (subSystemRoleRequest.getExpirationSelect().equals(ConditionsExpirationSelect.指定なし.getCode())) {
            return operatorSubSystemRoleList.stream().filter(o ->
                o.getSubSystemRoleCode().equals(subSystemRoleRequest.getSubSystemRoleCode())).count() != 0;
        } else if (subSystemRoleRequest.getExpirationSelect().equals(ConditionsExpirationSelect.状態指定日.getCode())) {
            return operatorSubSystemRoleList.stream().filter(o ->
                o.getSubSystemRoleCode().equals(subSystemRoleRequest.getSubSystemRoleCode()) &&
                    o.getExpirationStartDate()
                        .compareTo(subSystemRoleRequest.getExpirationStatusDate()) <= 0 &&
                    o.getExpirationEndDate()
                        .compareTo(subSystemRoleRequest.getExpirationStatusDate()) >= 0).count() != 0;
        } else if (subSystemRoleRequest.getExpirationSelect().equals(ConditionsExpirationSelect.条件指定.getCode())) {
            return operatorSubSystemRoleList.stream().filter(o ->
                o.getSubSystemRoleCode().equals(subSystemRoleRequest.getSubSystemRoleCode()) &&
                    (o.getExpirationStartDate()
                        .compareTo(subSystemRoleRequest.getExpirationStartDateFrom()) >= 0) &&
                    o.getExpirationStartDate()
                        .compareTo(subSystemRoleRequest.getExpirationStartDateTo()) <= 0 &&
                    o.getExpirationEndDate()
                        .compareTo(subSystemRoleRequest.getExpirationEndDateFrom()) >= 0 &&
                    o.getExpirationEndDate()
                        .compareTo(subSystemRoleRequest.getExpirationEndDateTo()) <= 0).count() != 0;
        }

        return true;
    }

    /**
     * オペレーター_取引ロール割当の検索条件判定を行います。
     *
     * @param request                 オペレーターリスト参照サービス Request
     * @param operatorBizTranRoleList オペレーター_取引割当
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsOperatorBizTranRole(OperatorSearchRequest request, List<Operator_BizTranRole> operatorBizTranRoleList) {

        // 選択チェックした取引ロールを検索条件にする
        List<OparatorSearchBizTranRoleRequest> bizTranRoleRequestList =
            request.getBizTranRoleList().stream().filter(reqossr->
                Oa11010Vo.CHECKBOX_TRUE.equals(reqossr.getBizTranRoleSelected())).collect(Collectors.toList());
        if (bizTranRoleRequestList.size() > 0) {
            //取引ロールロールでの検索条件設定あり（無しの場合は全件対象）

            //対象オペレーターにオペレーター_取引ロール割当無し
            if (bizTranRoleRequestList.size() == 0) { return false; }

            // 取引ロール条件選択による振り分け
            if (request.getBizTranRoleConditionsSelect().equals(ConditionsSelect.AND.getCode())) {
                for (OparatorSearchBizTranRoleRequest bizTranRoleRequest : bizTranRoleRequestList) {
                    if (!conditionsOperatorBizTranRoleRow(operatorBizTranRoleList, bizTranRoleRequest)) {
                        return false;
                    }
                }
            } else if (request.getBizTranRoleConditionsSelect().equals(ConditionsSelect.OR.getCode())) {
                boolean orReturn = false;
                for (OparatorSearchBizTranRoleRequest bizTranRoleRequest : bizTranRoleRequestList) {
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
     * オペレーター_取引ロール割当１行の検索条件判定を行います。
     *
     * @param operatorBizTranRoleList オペレーター_取引ロール割当リスト
     * @param bizTranRoleRequest      取引の検索条件
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsOperatorBizTranRoleRow(List<Operator_BizTranRole> operatorBizTranRoleList,
        OparatorSearchBizTranRoleRequest bizTranRoleRequest){

        if (bizTranRoleRequest.getExpirationSelect().equals(ConditionsExpirationSelect.指定なし.getCode())) {
            return operatorBizTranRoleList.stream().filter(o ->
                o.getBizTranRoleId().equals(bizTranRoleRequest.getBizTranRoleId())).count() != 0;
        } else if (bizTranRoleRequest.getExpirationSelect().equals(ConditionsExpirationSelect.状態指定日.getCode())) {
            return operatorBizTranRoleList.stream().filter(o ->
                o.getBizTranRoleId().equals(bizTranRoleRequest.getBizTranRoleId()) &&
                    o.getExpirationStartDate()
                        .compareTo(bizTranRoleRequest.getExpirationStatusDate()) <= 0 &&
                    o.getExpirationEndDate()
                        .compareTo(bizTranRoleRequest.getExpirationStatusDate()) >= 0).count() != 0;
        } else if (bizTranRoleRequest.getExpirationSelect().equals(ConditionsExpirationSelect.条件指定.getCode())) {
            return operatorBizTranRoleList.stream().filter(o ->
                o.getBizTranRoleId().equals(bizTranRoleRequest.getBizTranRoleId()) &&
                    (o.getExpirationStartDate()
                        .compareTo(bizTranRoleRequest.getExpirationStartDateFrom()) >= 0) &&
                    o.getExpirationStartDate()
                        .compareTo(bizTranRoleRequest.getExpirationStartDateTo()) <= 0 &&
                    o.getExpirationEndDate()
                        .compareTo(bizTranRoleRequest.getExpirationEndDateFrom()) >= 0 &&
                    o.getExpirationEndDate()
                        .compareTo(bizTranRoleRequest.getExpirationEndDateTo()) <= 0).count() != 0;
        }

        return true;
    }

    /**
     * アカウントロックの検索条件判定を行います。
     *
     * @param request     オペレーターリスト参照サービス Request
     * @param accountLock アカウントロック
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsAccountLock(OperatorSearchRequest request, AccountLock accountLock) {

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
        Short accountLockStatusLock = Oa11010Vo.CHECKBOX_FALSE;
        Short accountLockStatusUnlock = Oa11010Vo.CHECKBOX_FALSE;
        if (request.getAccountLockStatusLock() != null) {accountLockStatusLock = request.getAccountLockStatusLock();}
        if (request.getAccountLockStatusUnlock() != null) {accountLockStatusUnlock = request.getAccountLockStatusUnlock();}

        if (!accountLockStatusLock.equals(accountLockStatusUnlock)) {
            if (Oa11010Vo.CHECKBOX_TRUE.equals(accountLockStatusLock)) {
                //ロック
                if (accountLock == null || accountLock.getLockStatus().equals(AccountLockStatus.アンロック.getCode())) {
                    return false;
                }
            } else if (Oa11010Vo.CHECKBOX_TRUE.equals(accountLockStatusUnlock)) {
                //アンロック
                if (accountLock != null && accountLock.getLockStatus().equals(AccountLockStatus.ロック.getCode())) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * パスワード履歴の検索条件判定を行います。
     *
     * @param request         オペレーターリスト参照サービス Request
     * @param passwordHistory パスワード履歴
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsPasswordHistory(OperatorSearchRequest request, PasswordHistory passwordHistory) {

        // パスワード履歴　最終パスワード変更日の条件
        if (Oa11010Vo.CHECKBOX_TRUE.equals(request.getPasswordHistoryCheck()) &&
            request.getPasswordHistoryLastChangeDate() != null) {
            LocalDate passwodrChanheDate = LocalDate.now().minusDays(request.getPasswordHistoryLastChangeDate());
            if ("1".equals(request.getPasswordHistoryLastChangeDateStatus())) {
                // 変更した
                if (passwordHistory == null) {
                    return false;
                } else {
                    if (passwordHistory.getChangeDateTime().toLocalDate().compareTo(passwodrChanheDate) < 0) {
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
        Short passwordHistoryChangeType0 = 0;
        Short passwordHistoryChangeType1 = 0;
        Short passwordHistoryChangeType2 = 0;
        Short passwordHistoryChangeType3 = 0;
        if (request.getPasswordHistoryChangeType0() != null) {passwordHistoryChangeType0 = request.getPasswordHistoryChangeType0();}
        if (request.getPasswordHistoryChangeType1() != null) {passwordHistoryChangeType1 = request.getPasswordHistoryChangeType1();}
        if (request.getPasswordHistoryChangeType2() != null) {passwordHistoryChangeType2 = request.getPasswordHistoryChangeType2();}
        if (request.getPasswordHistoryChangeType3() != null) {passwordHistoryChangeType3 = request.getPasswordHistoryChangeType2();}
        if (!passwordHistoryChangeType0.equals(passwordHistoryChangeType1) ||
            !passwordHistoryChangeType0.equals(passwordHistoryChangeType2) ||
            !passwordHistoryChangeType0.equals(passwordHistoryChangeType3)) {

            if (passwordHistory == null) {
                return false;
            }
            // 最終パスワード変更種別が全て同じでない
            if (passwordHistory.getChangeType().equals(PasswordChangeType.初期.getCode()) && Oa11010Vo.CHECKBOX_TRUE.equals(passwordHistoryChangeType0)) {
                // 初期
                return true;
            } else if (passwordHistory.getChangeType().equals(PasswordChangeType.ユーザーによる変更.getCode()) && Oa11010Vo.CHECKBOX_TRUE.equals(passwordHistoryChangeType1)) {
                // ユーザーによる変更
                return true;
            } else if (passwordHistory.getChangeType().equals(PasswordChangeType.管理者によるリセット.getCode()) && Oa11010Vo.CHECKBOX_TRUE.equals(passwordHistoryChangeType2)) {
                // 管理者によるリセット
                return true;
            } else if (passwordHistory.getChangeType().equals(PasswordChangeType.機器認証パスワード.getCode()) && Oa11010Vo.CHECKBOX_TRUE.equals(passwordHistoryChangeType3)) {
                // 機器認証パスワード
                return true;
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * サインイン証跡の検索条件判定を行います。
     *
     * @param request     オペレーターリスト参照サービス Request
     * @param signInTrace サインイン証跡
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsSignInTrace(OperatorSearchRequest request, SignInTrace signInTrace) {

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
        if (Oa11010Vo.CHECKBOX_TRUE.equals(request.getSignintraceSignIn())) {

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
     * サインアウト証跡の検索条件判定を行います。
     *
     * @param request      オペレーターリスト参照サービス Request
     * @param signOutTrace サインアウト証跡
     * @return true:検索対象、false:検索対象外
     */
    boolean conditionsSignOutTrace(OperatorSearchRequest request, SignOutTrace signOutTrace) {

        // サインアウト証跡　最終サインオペレーションの条件
        if (Oa11010Vo.CHECKBOX_TRUE.equals(request.getSignintraceSignOut())) {
            return signOutTrace != null;
        }

        return true;
    }

    /**
     * オペレーター検索条件を作成します。
     *
     * @param request オペレーターリスト参照サービス Request
     * @return オペレータ検索条件
     */
    OperatorCriteria createOperatorCriteria(OperatorSearchRequest request) {
        OperatorCriteria criteria = new OperatorCriteria();
        // ＪＡID
        criteria.getJaIdCriteria().setEqualTo(request.getJaId());
        // 店舗コード
        if (!Strings2.isEmpty(request.getTempoCode())) {
            criteria.getTempoCodeCriteria().setEqualTo(request.getTempoCode());
        }
        // オペレーターコード
        if (!Strings2.isEmpty(request.getOperatorCode())) {
            criteria.getOperatorCodeCriteria().setForwardMatch(request.getOperatorCode());
        }
        // オペレーター名
        if (!Strings2.isEmpty(request.getOperatorName())) {
            criteria.getOperatorNameCriteria().setForwardMatch(request.getOperatorName());
        }
        // メールアドレス
        if (!Strings2.isEmpty(request.getMailAddress())) {
            criteria.getMailAddressCriteria().setForwardMatch(request.getMailAddress());
        }
        // 利用可否状態
        criteria.getAvailableStatusCriteria().getIncludes()
            .addAll(request.getAvailableStatusIncludesList());
        // OPTION検索条件 その他　機器認証
        criteria.getIsDeviceAuthCriteria().setEqualTo(request.getDeviceAuthUse());

        return criteria;
    }

    /**
     * オペレーターIDおよびオペレーターコードのリストを設定します。
     *
     * @param operators オペレーター群
     */
    void setOperatorIdAndCodeList(Operators operators) {
        operatorIdList = operators.getValues().stream().map(Operator::getOperatorId).collect(Collectors.toList());
        operatorCodeList = operators.getValues().stream().map(Operator::getOperatorCode).collect(Collectors.toList());
    }

    /**
     * アカウントロック検索条件を作成します。
     *
     * @return アカウントロック検索条件
     */
    AccountLockCriteria createAccountLockCriteria() {
        AccountLockCriteria criteria = new AccountLockCriteria();
        criteria.getOperatorIdCriteria().getIncludes().addAll(operatorIdList);
        return criteria;
    }

    /**
     * パスワード履歴検索条件を作成します。
     *
     * @return パスワード履歴検索条件
     */
    PasswordHistoryCriteria createPasswordHistoryCriteria() {
        PasswordHistoryCriteria criteria = new PasswordHistoryCriteria();
        criteria.getOperatorIdCriteria().getIncludes().addAll(operatorIdList);
        return criteria;
    }

    /**
     * サインイン証跡検索条件を作成します。
     *
     * @return サインイン証跡検索条件
     */
    SignInTraceCriteria createSignInTraceCriteria() {
        SignInTraceCriteria criteria = new SignInTraceCriteria();
        criteria.getOperatorCodeCriteria().getIncludes().addAll(operatorCodeList);
        return criteria;
    }

    /**
     * サインアウト証跡検索条件を作成します。
     *
     * @return サインアウト証跡検索条件
     */
    SignOutTraceCriteria createSignOutTraceCriteria() {
        SignOutTraceCriteria criteria = new SignOutTraceCriteria();
        criteria.getOperatorIdCriteria().getIncludes().addAll(operatorIdList);
        return criteria;
    }

    /**
     * オペレーター_サブシステムロール割当検索条件を作成します。
     *
     * @return オペレーター_サブシステムロール割当検索条件
     */
    Operator_SubSystemRoleCriteria createOperator_SubSystemRoleCriteria() {
        Operator_SubSystemRoleCriteria criteria = new Operator_SubSystemRoleCriteria();
        criteria.getOperatorIdCriteria().getIncludes().addAll(operatorIdList);
        return criteria;
    }

    /**
     * オペレーター_取引ロール割当検索条件を作成します。
     *
     * @return オペレーター_取引ロール割当検索条件
     */
    Operator_BizTranRoleCriteria createOperator_BizTranRoleCriteria() {
        Operator_BizTranRoleCriteria criteria = new Operator_BizTranRoleCriteria();
        criteria.getOperatorIdCriteria().getIncludes().addAll(operatorIdList);
        return criteria;
    }
}
