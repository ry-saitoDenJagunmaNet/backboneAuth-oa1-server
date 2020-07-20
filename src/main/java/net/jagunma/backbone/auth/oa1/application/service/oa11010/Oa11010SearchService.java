package net.jagunma.backbone.auth.oa1.application.service.oa11010;

import java.util.List;
import net.jagunma.backbone.auth.application.queryService.Operator;
import net.jagunma.backbone.auth.application.queryService.OperatorReferenceService;
import net.jagunma.backbone.auth.usecase.operator.OperatorSearchRequest;
import net.jagunma.backbone.auth.usecase.operator.OperatorSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Oa11010SearchService {
	private OperatorReferenceService operatorReferenceService;

	public Oa11010SearchService(OperatorReferenceService operatorReferenceService) {
		this.operatorReferenceService = operatorReferenceService;
	}

	public void search(OperatorSearchRequest request,
					   OperatorSearchResponse response) {
		// オペレーターリスト取得
		List<Operator> list = operatorReferenceService.getOperatorList(request);
		// オペレーターテーブルHtmlを取得
		response.setOperatorTable(getOperatorTableHtml(list, request.getPageNo()));
		// Pagination Htmlを取得
		response.setPagination(getPaginationHtml(list, request.getPageNo()));
	}

	/**
	 * オペレーターテーブルHtmlを取得します。
	 * @param list オペレーターリスト
	 * @param pageNo 表示ページ番号
	 * @return オペレーターテーブルHtml
	 */
	private String getOperatorTableHtml(List<Operator> list, int pageNo) {
		StringBuffer html = new StringBuffer();

		// オペレータページリスト取得
		List<Operator> pagelist = operatorReferenceService.getPageList(list, pageNo);
		pagelist.forEach(o -> {
			if (o.getOperatorSubSystemRoleList().size() <= 1
				&& o.getOperatorBizTranRoleList().size() <= 1) {
				// 下線を引く（サブシシテムロールおよび取引ロールが1件以下）
				html.append("<tr class=\"oaex_operator_table_operator_").append(o.getOperatorCode())
					.append(" oaex_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\">");
			} else {
				html.append("<tr class=\"oaex_operator_table_operator_").append(o.getOperatorCode())
					.append("\" onclick=\"oaex_operator_table_onClick(this);\">");
			}
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
			if (pagelist.indexOf(o) == 0 || !o.getTempoCode().equals(pagelist.get(pagelist.indexOf(o) - 1).getTempoCode())) {
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
				.append("</td>");
			html.append("<td class=\"oaex_operator_operator_name\">").append(o.getOperatorName())
				.append("</td>");
			// オペレーター有効期限
			html.append("<td class=\"oaex_operator_expiration_date\">")
				.append(o.getExpirationStartDate()).append("～").append(o.getExpirationEndDate())
				.append("</td>");

			if (o.getOperatorSubSystemRoleList().size() == 0
				&& o.getOperatorBizTranRoleList().size() == 0) {
				// サブシステムロール未設定
				html.append(getOperatorSubSystemRoleBlankHtml());
				// 取引ロール未設定
				html.append(getOperatorBizTranRoleBlankHtml());
			} else {
				int is = 0;
				int ib = 0;
				for (int i = 0; true; i++) {
					if (i > 0) {
						if (o.getOperatorSubSystemRoleList().size() <= is
							&& o.getOperatorBizTranRoleList().size() <= ib ) {
							break;
						}
						// 店舗、オペレーターの空セル（td）を出力
						if (o.getOperatorSubSystemRoleList().size()-1 <= is
							&& o.getOperatorBizTranRoleList().size()-1 <= ib ) {
							// 下線を引く（サブシシテムロールおよび取引ロールが1件以下）
							html.append("<tr class=\"oaex_operator_table_operator_").append(o.getOperatorCode())
								.append(" oaex_operator_table_row\" onclick=\"oaex_operator_table_onClick(this);\">");
						} else {
							html.append("<tr class=\"oaex_operator_table_operator_").append(o.getOperatorCode())
								.append("\" onclick=\"oaex_operator_table_onClick(this);\">");
						}

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

					if (o.getOperatorSubSystemRoleList().size() > is ) {
						// サブシステムロール
						html.append("<td class=\"oaex_operator_subsystem_role\">")
							.append(o.getOperatorSubSystemRoleList().get(is).getSubSystemRoleName())
							.append("</td>");
						// サブシステムロール有効期限
						html.append("<td class=\"oaex_operator_subsystem_role_expiration_date\">")
							.append(o.getOperatorSubSystemRoleList().get(is).getExpirationStartDate()).append("～")
							.append(o.getOperatorSubSystemRoleList().get(is).getExpirationEndDate())
							.append("</td>");
						is++;
					} else {
						// サブシステムロール未設定
						html.append(getOperatorSubSystemRoleBlankHtml());
					}

					if (o.getOperatorBizTranRoleList().size() > ib ) {
						// 取引ロール
						html.append("<td class=\"oaex_operator_biztran_role_code\">")
							.append(o.getOperatorBizTranRoleList().get(ib).getBizTranRoleCode())
							.append("</td>");
						html.append("<td class=\"oaex_operator_biztran_role_name\">")
							.append(o.getOperatorBizTranRoleList().get(ib).getBizTranRoleName())
							.append("</td>");
						// 取引ロール有効期限
						html.append("<td class=\"oaex_operator_biztran_role_expiration_date\">")
							.append(o.getOperatorBizTranRoleList().get(ib).getExpirationStartDate()).append("～")
							.append(o.getOperatorBizTranRoleList().get(ib).getExpirationEndDate())
							.append("</td>");
						ib++;
					} else {
						// 取引ロール未設定
						html.append(getOperatorBizTranRoleBlankHtml());
					}
					html.append("</tr>");
				}
			}
		});

		return html.toString();
	}

	/**
	 * オペレータサブシステムロールの未設定Htmlを取得します。
	 * @return オペレータサブシステムロールの未設定Html
	 */
	private String getOperatorSubSystemRoleBlankHtml() {
		StringBuffer html = new StringBuffer();
		// サブシステムロール
		html.append("<td class=\"oaex_operator_subsystem_role\"></td>");
		// サブシステムロール有効期限
		html.append("<td class=\"oaex_operator_subsystem_role_expiration_date\"></td>");
		return html.toString();
	}

	/**
	 * 取引ロールの未設定Htmlを取得します。
	 * @return 取引ロールの未設定Html
	 */
	private String getOperatorBizTranRoleBlankHtml() {
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
	 * Pagination Htmlを取得します。
	 * @param list オペレーターリスト
	 * @param pageNo 表示ページ番号
	 * @return Pagination Html
	 */
	private String getPaginationHtml(List<Operator> list, int pageNo) {
		StringBuffer html = new StringBuffer();
		int maxPage = operatorReferenceService.getMaxPage(list);

		if (pageNo == 1) {
			html.append("<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&lt;</a></li>");
		} else {
			html.append("<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(")
				.append(pageNo-1).append(");\">&lt;</a></li>");
		}
		for (int i = 1; i <= maxPage; i++) {
			if (pageNo == i) {
				html.append("<li class=\"active\" th:remove=\"all\"><a href=\"#!\">").append(i).append("</a></li>");
			} else {
				html.append("<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(")
					.append(i).append(");\">").append(i).append("</a></li>");
			}
		}
		if (pageNo == maxPage) {
			html.append("<li class=\"disabled\" th:remove=\"all\"><a href=\"#!\">&gt;</a></li>");
		} else {
			html.append("<li class=\"waves-effect\" th:remove=\"all\"><a href=\"#!\" onclick=\"oaex_th_searchBtn_onClick(")
				.append(pageNo+1).append(");\">&gt;</a></li>");
		}

		return html.toString();
	}
}
