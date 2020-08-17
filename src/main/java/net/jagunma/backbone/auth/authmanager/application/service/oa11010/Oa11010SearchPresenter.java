package net.jagunma.backbone.auth.authmanager.application.service.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.model.domain.operator.roleAssignment.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.application.model.domain.subSystem.SubSystem;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.OperatorReferenceDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorReference.OperatorSearchResponse;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010SearchResponseVo;

/**
 * OA11010 オペレーター＜一覧＞ 検索 Presenter
 */
public class Oa11010SearchPresenter implements OperatorSearchResponse {
	private String operatorTable;
	private String pagination;
	private List<OperatorReferenceDto> OperatorReferenceDtos;

	/**
	 * オペレーター一覧の１ページ当たりの行数
	 */
	private final int PAGE_SIZE = 10;

	/**
	 * コンストラクタ
	 */
	Oa11010SearchPresenter() {}

	/**
	 * オペレーター参照ＤｔｏのＳｅｔ
	 * @param operatorReferenceDtos オペレーター参照Ｄｔｏ
	 */
	public void setOperatorReferenceDtos(List<OperatorReferenceDto> operatorReferenceDtos) { this.OperatorReferenceDtos = operatorReferenceDtos; }

	/**
	 * responseに変換
	 * @param response オペレーター＜一覧＞ 検索結果
	 */
	public void bindTo(Oa11010SearchResponseVo response) {
		response.setOperatorTable(operatorTable);
		response.setPagination(pagination);
	}

	/**
	 * オペレーターテーブルHtmlを生成します。
	 * @param pageNo 対象ページ
	 */
	public void genOperatorTableHtml(int pageNo) {
		List<OperatorReferenceDto> list = getPageList(pageNo);

		StringBuffer html = new StringBuffer();

		// オペレーターページリスト取得
		list.forEach(o -> {
			html.append("<tr class=\"oaex_operator_table_operator_").append(o.getOperatorCode())
				.append(" oaex_th_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\">");
			// 利用可否
			if (o.getAvailableStatus() == 0) {
				html.append(
					"<td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_possible\"></div></td>");
			} else {
				html.append(
					"<td class=\"oaex_operator_available_status\"><div class=\"oaex_available_status_inpossible\"></div></td>");
			}
			// ロック
			if (o.getLockStatus() == 1) {
				html.append(
					"<td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_lock\"></div></td>");
			} else {
				html.append(
					"<td class=\"oaex_operator_account_lock\"><div class=\"oaex_account_unlock\"></div></td>");
			}
			// 店舗
			if (list.indexOf(o) == 0 || !o.getTempoCode().equals(list.get(list.indexOf(o) - 1).getTempoCode())) {
				// 店舗コードが変わったら表示
				html.append("<td class=\"oaex_operator_tempo_code\">").append(o.getTempoCode())
					.append("</td>");
				html.append("<td class=\"oaex_operator_tempo_name\">").append(o.getTempoName())
					.append("</td>");
			} else {
				html.append("<td class=\"oaex_operator_tempo_code\"></td>");
				html.append("<td class=\"oaex_operator_tempo_name\"></td>");
			}
			// オペレーター
			html.append("<td class=\"oaex_operator_operator_code\">").append(o.getOperatorCode())
				.append("<input type=\"hidden\" value=\"").append(o.getTempoId()).append("\"/>")
				.append("</td>");
			html.append("<td class=\"oaex_operator_operator_name\">").append(o.getOperatorName())
				.append("</td>");
			// オペレーター有効期限
			html.append("<td class=\"oaex_operator_expiration_date\">")
				.append(o.getExpirationStartDateToStringFormat())
				.append("～")
				.append(o.getExpirationEndDateToStringFormat())
				.append("</td>");

			// サブシステムロール、取引ロール
			if (o.getOperatorSubSystemRoleList().size() == 0
				&& o.getOperatorBizTranRoleList().size() == 0) {
				// サブシステムロール未設定
				html.append(genOperatorSubSystemRoleBlankHtml());
				// 取引ロール未設定
				html.append(genOperatorBizTranRoleBlankHtml());
			} else {
				// オペレーターに紐付くサブシステムロール、取引ロールを設定
				int is = 0;
				for (int i = 0; true; i++) {
					if (o.getOperatorSubSystemRoleList().size() <= is
						&& o.getOperatorBizTranRoleList().size() == 0 ) {
						// オペレーターに紐付くサブシステムロール、取引ロールが無くなったら終了
						break;
					}
					if (i > 0) {
						// 店舗、オペレーターに空セル（td）を設定
						html.append("<tr class=\"oaex_operator_table_operator_").append(o.getOperatorCode())
							.append("\" onclick=\"oaex_operator_table_onClick(this);\">");

						// 利用可否
						html.append("<td class=\"oaex_operator_available_status\"></td>");
						// ロック
						html.append("<td class=\"oaex_operator_account_lock\"></td>");
						// 店舗
						html.append("<td class=\"oaex_operator_tempo_code\"></td>");
						html.append("<td class=\"oaex_operator_tempo_name\"></td>");
						// オペレーター
						html.append("<td class=\"oaex_operator_operator_code\"></td>");
						html.append("<td class=\"oaex_operator_operator_name\"></td>");
						// オペレーター有効期限
						html.append("<td class=\"oaex_operator_expiration_date\"></td>");
					}

					if (o.getOperatorSubSystemRoleList().size() > is) {
						// サブシステムロール
						html.append("<td class=\"oaex_operator_subsystem_role\">")
							.append(o.getOperatorSubSystemRoleList().get(is).getSubSystemRole().getSubSystemRoleName())
							.append("</td>");
						// サブシステムロール有効期限
						html.append("<td class=\"oaex_operator_subsystem_role_expiration_date\">")
							.append(formatLocalDate(o.getOperatorSubSystemRoleList().get(is).getExpirationStartDate()))
							.append("～")
							.append(formatLocalDate(o.getOperatorSubSystemRoleList().get(is).getExpirationEndDate()))
							.append("</td>");

						if (o.getOperatorBizTranRoleList().size() == 0) {
							// 取引ロール未設定
							html.append(genOperatorBizTranRoleBlankHtml());
						} else {
							boolean ssfirst = true;
								// サブシステムロールに紐付く取引ロール定義Htmlを生成
							for(SubSystem ss : o.getOperatorSubSystemRoleList().get(is).getSubSystemRole().getSubSystemList()) {
								// サブシステムロールに紐付く取引ロール定義Htmlを生成
								String BizTranRoleHtml = genOperatorBizTranRoleHtml(o.getOperatorBizTranRoleList()
									, o.getOperatorCode()
									, ss.getSubSystemCode()
									, ssfirst);
								if (BizTranRoleHtml.length() > 0) {
									html.append(BizTranRoleHtml);
									ssfirst = false;
								}
							}
							if (ssfirst) {
								// サブシステムロールに紐付く取引ロールが１件も無い場合、
								// 取引ロール未設定
								html.append(genOperatorBizTranRoleBlankHtml());
							}
						}
						is++;
					} else {
						// サブシステムロール未設定
						html.append(genOperatorSubSystemRoleBlankHtml());
						if (o.getOperatorBizTranRoleList().size() > 0) {
							boolean ssfirst = true;
							// サブシステムロールに紐付かない取引ロール定義Htmlを生成
							html.append(genOperatorBizTranRoleHtml(o.getOperatorBizTranRoleList()
								, o.getOperatorCode()
								, ""
								, ssfirst));
						}
					}
					html.append("</tr>");
				}
			}
		});
		operatorTable = html.toString();
	}

	/**
	 * 該当ページのオペレーター一覧を取得します。
	 * @param pageNo 対象ページ
	 * @return 該当ページのオペレーター一覧
	 */
	private List<OperatorReferenceDto> getPageList(int pageNo) {
		int skip = pageNo * PAGE_SIZE - PAGE_SIZE;
		return OperatorReferenceDtos.stream().skip(skip).limit(10).collect(Collectors.toList());
	}

	/**
	 * 取引ロール定義Htmlを生成します。
	 * @param list オペレーター取引ロールリスト
	 * @param operatorCode オペレーターコード
	 * @param subSystemCode サブシステムコード
	 * @param isFirst 対象オペレーターで最初の定義（falseの時、空のオペレーター情報、サブシステムロール情報を表示）
	 * @return 取引ロール定義Html
	 */
	private String genOperatorBizTranRoleHtml(List<Operator_BizTranRole> list,
		String operatorCode,
		String subSystemCode,
		boolean isFirst) {

		StringBuffer html = new StringBuffer();

		if (list != null && list.size() > 0) {
			boolean isFirstlocal = isFirst;
			List<Operator_BizTranRole> tempList = newArrayList();
			if ("".equals(subSystemCode)) {
				tempList.addAll(list);
			} else {
				list.stream().filter(obtr -> obtr.getBizTranRole().getSubSystemCode().equals(subSystemCode)).forEach(
					tempList::add);
			}
			for(Operator_BizTranRole obtr : tempList) {
				if (!isFirstlocal) {
					html.append("</tr>");
					// オペレーター情報未設定
					html.append(genOperatorBlankHtml(operatorCode));
					// サブシステムロール未設定
					html.append(genOperatorSubSystemRoleBlankHtml());
				}
				// 取引ロール
				html.append("<td class=\"oaex_operator_biztran_role_code\">")
					.append(obtr.getBizTranRole().getBizTranRoleCode())
					.append("</td>");
				html.append("<td class=\"oaex_operator_biztran_role_name\">")
					.append(obtr.getBizTranRole().getBizTranRoleName())
					.append("</td>");
				// 取引ロール有効期限
				html.append("<td class=\"oaex_operator_biztran_role_expiration_date\">")
					.append(formatLocalDate(obtr.getExpirationStartDate()))
					.append("～")
					.append(formatLocalDate(obtr.getExpirationEndDate()))
					.append("</td>");
				isFirstlocal = false;

				// 表示した取引ロールを削除
				list.remove(obtr);
			};
		}

		return html.toString();
	}

	/**
	 * オペレーターの未設定Htmlを生成します。
	 * @return オペレーターの未設定Html
	 */
	private String genOperatorBlankHtml(String operatorCode) {

		StringBuffer html = new StringBuffer();

		html.append("<tr class=\"oaex_operator_table_operator_").append(operatorCode)
			.append("\" onclick=\"oaex_operator_table_onClick(this);\">");
		// 利用可否
		html.append("<td class=\"oaex_operator_available_status\"></td>");
		// ロック
		html.append("<td class=\"oaex_operator_account_lock\"></td>");
		// 店舗
		html.append("<td class=\"oaex_operator_tempo_code\"></td>");
		html.append("<td class=\"oaex_operator_tempo_name\"></td>");
		// オペレーター
		html.append("<td class=\"oaex_operator_operator_code\"></td>");
		html.append("<td class=\"oaex_operator_operator_name\"></td>");
		// オペレーター有効期限
		html.append("<td class=\"oaex_operator_expiration_date\"></td>");
		return html.toString();
	}

	/**
	 * オペレーターサブシステムロールの未設定Htmlを生成します。
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
	 * @param pageNo 表示ページ番号
	 */
	public void genPaginationHtml(int pageNo) {

		StringBuffer html = new StringBuffer();
		int maxPageNo = getMaxPage();

		if (pageNo == 1) {
			html.append("<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&lt;</a></li>");
		} else {
			html.append("<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(")
				.append(pageNo-1).append(");\">&lt;</a></li>");
		}
		for (int i = 1; i <= maxPageNo; i++) {
			if (pageNo == i) {
				html.append("<li class=\"active\" th:remove=\"all\"><a href=\"#!\">").append(i).append("</a></li>");
			} else {
				html.append("<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(")
					.append(i).append(");\">").append(i).append("</a></li>");
			}
		}
		if (pageNo == maxPageNo) {
			html.append("<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&gt;</a></li>");
		} else {
			html.append("<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(")
				.append(pageNo+1).append(");\">&gt;</a></li>");
		}
		pagination =  html.toString();
	}

	/**
	 * オペレーター一覧の最終ページを取得します、
	 * @return オペレーター一覧の最終ページ
	 */
	private int getMaxPage() {
		return (int) Math.ceil((double)OperatorReferenceDtos.size() / PAGE_SIZE);
	}

	/**
	 * 日付を”yyyy/MM/dd”の書式でフォ－マットします。
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
