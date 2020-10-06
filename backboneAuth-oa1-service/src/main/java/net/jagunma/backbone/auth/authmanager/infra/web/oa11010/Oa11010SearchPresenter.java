package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SearchResponseVo;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLock;
import net.jagunma.backbone.auth.authmanager.model.domain.accountLock.AccountLocks;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchesAtMoment;

/**
 * OA11010 オペレーター＜一覧＞検索サービス Response Presenter
 */
class Oa11010SearchPresenter implements OperatorSearchResponse {

    /**
     * オペレーター一覧の１ページ当たりの行数
     */
    private final int PAGE_SIZE = 10;

    private int pageNo;
    private Operators operators;
    private BranchesAtMoment branchesAtMoment;
    private AccountLocks accountLocks;
    private Operator_SubSystemRoles operator_SubSystemRoles;
    private Operator_BizTranRoles operator_BizTranRoles;

    // コンストラクタ
    Oa11010SearchPresenter() {}

    /**
     * オペレーター一覧表示ページのＳｅｔ
     *
     * @param pageNo オペレーター一覧表示ページ
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    /**
     * オペレーター群のＳｅｔ
     *
     * @param operators オペレーター群
     */
    public void setOperators(Operators operators) {
        this.operators = operators;
    }
    /**
     * ある時点Branch群のＳｅｔ
     *
     * @param branchesAtMoment ある時点Branch群
     */
    public void setBranchesAtMoment(BranchesAtMoment branchesAtMoment) {
        this.branchesAtMoment = branchesAtMoment;
    }
    /**
     * アカウントロック群のＳｅｔ
     *
     * @param accountLocks アカウントロック群
     */
    public void setAccountLocks(AccountLocks accountLocks) {
        this.accountLocks = accountLocks;
    }
    /**
     * オペレーター_サブシステムロール割当群のＳｅｔ
     *
     * @param operator_SubSystemRoles オペレーター_サブシステムロール割当群
     */
    public void setOperator_SubSystemRoles(Operator_SubSystemRoles operator_SubSystemRoles) {
        this.operator_SubSystemRoles = operator_SubSystemRoles;
    }
    /**
     * オペレーター_取引ロール割当群のＳｅｔ
     *
     * @param operator_BizTranRoles オペレーター_取引ロール割当群
     */
    public void setOperator_BizTranRoles(Operator_BizTranRoles operator_BizTranRoles) {
        this.operator_BizTranRoles = operator_BizTranRoles;
    }

    /**
     * responseに変換
     *
     * @param vo ViewObject
     */
    public void bindTo(Oa11010SearchResponseVo vo) {
        vo.setOperatorTable(genOperatorTableHtml());
        vo.setPagination(genPaginationHtml());
    }

    /**
     * オペレーターテーブルHtmlを生成します。
     *
     * @return オペレーターテーブルHtml
     */
    private String genOperatorTableHtml() {
        StringBuilder html = new StringBuilder();
        List<Operator> list = getOperatorPageList();
        String branchCode = "";
        for (Operator operator : list) {
            BranchAtMoment branchAtMoment = branchesAtMoment.getValue().stream().filter(
                b->b.getBranchAttribute().getBranchCode().getValue().equals(operator.getBranchCode())).findFirst().orElse(null);
            if (branchCode.equals(branchAtMoment.getBranchAttribute().getBranchCode().getValue())) {
                branchAtMoment = null;
            } else {
                branchCode = branchAtMoment.getBranchAttribute().getBranchCode().getValue();
            }
            html.append(genOperatorTableRowHtml(operator, branchAtMoment));
        }
        return html.toString();
    }

    /**
     * オペレーターテーブル行Htmlを生成します。
     *
     * @param operator オペレーター
     * @param branchAtMoment ある時点の店舗
     * @return オペレーターテーブル行Html
     */
    private String genOperatorTableRowHtml(Operator operator, BranchAtMoment branchAtMoment) {

        AccountLock accountLock = accountLocks.getValues().stream().filter(a->a.getOperatorId().equals(operator.getOperatorId())).findFirst().orElse(null);
        List<Operator_SubSystemRole> operatorSubSystemRoleList = operator_SubSystemRoles.getValues().stream().filter(o->o.getOperatorId().equals(operator.getOperatorId())).collect(Collectors.toList());
        List<Operator_BizTranRole> operatorBizTranRoleList = operator_BizTranRoles.getValues().stream().filter(o->o.getOperatorId().equals(operator.getOperatorId())).collect(Collectors.toList());


        StringBuilder html = new StringBuilder();

        html.append(String.format("<tr class=\"oaex_operator_table_operator_%s oaex_th_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\">",
            operator.getOperatorCode()));

        // 利用可否
        String available_status = operator.getAvailableStatus()==0? "oaex_available_status_possible":"oaex_available_status_inpossible";
        html.append(String.format("<td class=\"oaex_operator_available_status\"><div class=\"%s\"></div></td>", available_status));
        // ロック
        String lockStatus = (accountLock!=null && accountLock.getLockStatus()==1)? "oaex_account_lock":"oaex_account_unlock";
        html.append(String.format("<td class=\"oaex_operator_account_lock\"><div class=\"%s\"></div></td>", lockStatus));
        // 店舗
        if (branchAtMoment == null) {
            html.append("<td class=\"oaex_operator_branch_code\"></td>");
            html.append("<td class=\"oaex_operator_branch_name\"></td>");
        } else {
            html.append("<td class=\"oaex_operator_branch_code\">").append(branchAtMoment.getBranchAttribute().getBranchCode().getValue())
                .append("</td>");
            html.append("<td class=\"oaex_operator_branch_name\">").append(branchAtMoment.getBranchAttribute().getName())
                .append("</td>");
        }
        // オペレーター
        html.append(String.format("<td class=\"oaex_operator_operator_code\">%s<input type=\"hidden\" value=\"%d\"/></td>",
            operator.getOperatorCode(),operator.getBranchId()));
        html.append(String.format("<td class=\"oaex_operator_operator_name\">%s</td>", operator.getOperatorName()));
        // オペレーター有効期限
        html.append(String.format("<td class=\"oaex_operator_expiration_date\">%s～%s</td>",
            formatLocalDate(operator.getExpirationStartDate()), formatLocalDate(operator.getExpirationEndDate())));

        // サブシステムロール
        if (operatorSubSystemRoleList.size() == 0) {
            // サブシステムロール未設定
            html.append(genOperatorSubSystemRoleBlankHtml());

            if (operatorBizTranRoleList.size() == 0) {
                // 取引ロール未設定
                html.append(genOperatorBizTranRoleBlankHtml());
            } else {
                boolean firstBizTranRoleRow = true;
                for (Operator_BizTranRole operatorBizTranRole : operatorBizTranRoleList) {
                    if (!firstBizTranRoleRow) {
                        // オペレーター未設定
                        html.append(genOperatorBlankHtml(operator.getOperatorCode()));
                        // サブシステムロール未設定
                        html.append(genOperatorSubSystemRoleBlankHtml());
                    }
                    firstBizTranRoleRow = false;

                    // 取引ロール表示Htmlを生成
                    html.append(genOperatorBizTranRoleHtml(operatorBizTranRole));
                }
            }
        } else {
            // オペレーターに紐付くサブシステムロール
            boolean firstRow = true;
            for (Operator_SubSystemRole operatorSubSystemRole : operatorSubSystemRoleList) {
                if (!firstRow) {
                    html.append(genOperatorBlankHtml(operator.getOperatorCode()));
                }
                firstRow = false;
                // サブシステムロール
                html.append(String.format("<td class=\"oaex_operator_subsystem_role\">%s</td>",
                    operatorSubSystemRole.getSubSystemRole().getName()));
                // サブシステムロール有効期限
                html.append(String.format("<td class=\"oaex_operator_subsystem_role_expiration_date\">%s～%s</td>",
                    formatLocalDate(operatorSubSystemRole.getExpirationStartDate()),
                    formatLocalDate(operatorSubSystemRole.getExpirationEndDate())));

                if (operatorBizTranRoleList.size() == 0) {
                    // 取引ロール未設定
                    html.append(genOperatorBizTranRoleBlankHtml());
                } else {
                    // サブシステムロールに紐付く取引ロール定義Htmlを生成
                    for (SubSystem subSystem : operatorSubSystemRole.getSubSystemRole().getSubSystemList()) {
                        boolean firstBizTranRoleRow = true;
                        List<Operator_BizTranRole> removeOperatorBizTranRole = newArrayList();
                        for (Operator_BizTranRole operatorBizTranRole : operatorBizTranRoleList.stream().filter(
                            obtr->obtr.getBizTranRole().getSubSystemCode().equals(subSystem.getCode())).collect(Collectors.toList())) {

                            if (!firstBizTranRoleRow) {
                                // オペレーター未設定
                                html.append(genOperatorBlankHtml(operator.getOperatorCode()));
                                // サブシステムロール未設定
                                html.append(genOperatorSubSystemRoleBlankHtml());
                            }
                            firstBizTranRoleRow = false;
                            // 取引ロール表示Htmlを生成
                            html.append(genOperatorBizTranRoleHtml(operatorBizTranRole));

                            // 削除対象を退避
                            removeOperatorBizTranRole.add(operatorBizTranRole);
                        }
                        // 対象から削除
                        operatorBizTranRoleList.removeAll(removeOperatorBizTranRole);
                    }
                }
            }

            // サブシステムロールに紐付かない取引ロール定義Htmlを生成
            for (Operator_BizTranRole operatorBizTranRole : operatorBizTranRoleList) {
                // オペレーター未設定
                html.append(genOperatorBlankHtml(operator.getOperatorCode()));
                // サブシステムロール未設定
                html.append(genOperatorSubSystemRoleBlankHtml());
                // 取引ロール表示Htmlを生成
                html.append(genOperatorBizTranRoleHtml(operatorBizTranRole));
            }
        }

        return html.toString();
    }

    /**
     * 取引ロール表示Htmlを生成します。
     *
     * @param operatorBizTranRole オペレーター_取引ロール割当
     * @return 取引ロール表示Html
     */
    private String genOperatorBizTranRoleHtml(Operator_BizTranRole operatorBizTranRole) {

        StringBuffer html = new StringBuffer();

        // 取引ロール
        html.append(String.format("<td class=\"oaex_operator_biztran_role_code\">%s</td>",
            operatorBizTranRole.getBizTranRole().getBizTranRoleCode()));
        html.append(String.format("<td class=\"oaex_operator_biztran_role_name\">%s</td>",
            operatorBizTranRole.getBizTranRole().getBizTranRoleName()));
        // 取引ロール有効期限
        html.append(String.format("<td class=\"oaex_operator_biztran_role_expiration_date\">%s～%s",
            formatLocalDate(operatorBizTranRole.getExpirationStartDate()),
            formatLocalDate(operatorBizTranRole.getExpirationEndDate())));
        html.append("</tr>");

        return html.toString();
    }

    /**
     * オペレーターの未設定Htmlを生成します。
     *
     * @param operatorCode オペレーターコード
     * @return オペレーターの未設定Html
     */
    private String genOperatorBlankHtml(String operatorCode) {

        StringBuffer html = new StringBuffer();

        // 店舗、オペレーターに空セル（td）を設定
        html.append(String.format("<tr class=\"oaex_operator_table_operator_%s\" onclick=\"oaex_operator_table_onClick(this);\">",
            operatorCode));
        // 利用可否
        html.append("<td class=\"oaex_operator_available_status\"></td>");
        // ロック
        html.append("<td class=\"oaex_operator_account_lock\"></td>");
        // 店舗
        html.append("<td class=\"oaex_operator_branch_code\"></td>");
        html.append("<td class=\"oaex_operator_branch_name\"></td>");
        // オペレーター
        html.append("<td class=\"oaex_operator_operator_code\"></td>");
        html.append("<td class=\"oaex_operator_operator_name\"></td>");
        // オペレーター有効期限
        html.append("<td class=\"oaex_operator_expiration_date\"></td>");

        return html.toString();
    }

    /**
     * オペレーターサブシステムロールの未設定Htmlを生成します。
     *
     * @return オペレーターサブシステムロールの未設定Html
     */
    private String genOperatorSubSystemRoleBlankHtml() {

        StringBuffer html = new StringBuffer();

        // サブシステムロール
        html.append("<td class=\"oaex_operator_subsystem_role\"></td>");
        // サブシステムロール有効期限
        html.append("<td class=\"oaex_operator_subsystem_role_expiration_date\"></td>");

        return html.toString();
    }

    /**
     * 取引ロールの未設定Htmlを生成します。
     *
     * @return 取引ロールの未設定Html
     */
    private String genOperatorBizTranRoleBlankHtml() {

        StringBuffer html = new StringBuffer();

        // 取引ロール
        html.append("<td class=\"oaex_operator_biztran_role_code\"></td>");
        html.append("<td class=\"oaex_operator_biztran_role_name\"></td>");
        // 取引ロール有効期限
        html.append("<td class=\"oaex_operator_biztran_role_expiration_date\"></td>");
        html.append("</tr>");

        return html.toString();
    }

    /**
     * Pagination Htmlを生成します。
     *
     * @return Pagination Html
     */
    public String genPaginationHtml() {

        StringBuffer html = new StringBuffer();
        int maxPageNo = getMaxPage();

        if (pageNo == 1) {
            html.append("<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&lt;</a></li>");
        } else {
            html.append(String.format("<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(%d);\">&lt;</a></li>", pageNo-1));
        }
        for (int i = 1; i <= maxPageNo; i++) {
            if (pageNo == i) {
                html.append(String.format("<li class=\"active\" th:remove=\"all\"><a href=\"#!\">%d</a></li>", i));
            } else {
                html.append(String.format("<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(%d);\">%d</a></li>", i, i));
            }
        }
        if (pageNo == maxPageNo) {
            html.append("<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&gt;</a></li>");
        } else {
            html.append(String.format("<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(%d);\">&gt;</a></li>", pageNo + 1));
        }
        return html.toString();
    }

    /**
     * 該当ページのオペレーター一覧を取得します。
     *
     * @return 該当ページのオペレーター一覧
     */
    private List<Operator> getOperatorPageList() {
        if (operators.getValues().size() == 0) { return newArrayList(); }
        int skip = pageNo * PAGE_SIZE - PAGE_SIZE;
        return operators.getValues().stream().skip(skip).limit(PAGE_SIZE).collect(Collectors.toList());
    }

    /**
     * オペレーター一覧の最終ページを取得します。
     *
     * @return オペレーター一覧の最終ページ
     */
    private int getMaxPage() {
        return (int) Math.ceil((double) operators.getValues().size() / PAGE_SIZE);
    }

    /**
     * 日付を”yyyy/MM/dd”の書式でフォ－マットします。
     *
     * @param localDt フォーマット対象の日付
     * @return フォ－マットした日付
     */
    private String formatLocalDate(LocalDate localDt) {
        if (localDt == null) {
            return "";
        }
        return localDt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
