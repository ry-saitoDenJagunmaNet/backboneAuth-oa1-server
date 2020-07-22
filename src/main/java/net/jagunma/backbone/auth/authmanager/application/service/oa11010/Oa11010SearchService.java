package net.jagunma.backbone.auth.authmanager.application.service.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.OperatorDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.OperatorBizTranRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.OperatorReferenceService;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorreference.OperatorSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorreference.OperatorSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Oa11010SearchService {
	private final OperatorReferenceService operatorReferenceService;

	public Oa11010SearchService(OperatorReferenceService operatorReferenceService) {
		this.operatorReferenceService = operatorReferenceService;
	}

	public void search(OperatorSearchRequest request,
		OperatorSearchResponse response) {

		// オペレーターリスト取得
		List<OperatorDto> list = operatorReferenceService.getOperatorList(request);
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
	private String getOperatorTableHtml(List<OperatorDto> list, int pageNo) {

		StringBuffer html = new StringBuffer();

		// オペレータページリスト取得
		List<OperatorDto> pagelist = operatorReferenceService.getPageList(list, pageNo);
		pagelist.forEach(o -> {
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

			// サブシステムロール
			if (o.getOperatorSubSystemRoleList().size() == 0
				&& o.getOperatorBizTranRoleList().size() == 0) {
				// サブシステムロール未設定
				html.append(getOperatorSubSystemRoleBlankHtml());
				// 取引ロール未設定
				html.append(getOperatorBizTranRoleBlankHtml());
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
							.append(o.getOperatorSubSystemRoleList().get(is).getSubSystemRoleName())
							.append("</td>");
						// サブシステムロール有効期限
						html.append("<td class=\"oaex_operator_subsystem_role_expiration_date\">")
							.append(o.getOperatorSubSystemRoleList().get(is).getExpirationStartDate()).append("～")
							.append(o.getOperatorSubSystemRoleList().get(is).getExpirationEndDate())
							.append("</td>");

						if (o.getOperatorBizTranRoleList().size() == 0) {
							// 取引ロール未設定
							html.append(getOperatorBizTranRoleBlankHtml());
						} else {
							boolean ssfirst = true;
							for(SubSystemDto ss : o.getOperatorSubSystemRoleList().get(is).getSubSystemCodeList()) {
								// サブシステムロールに紐付く取引ロール定義Htmlを設定
								String BizTranRoleHtml = getOperatorBizTranRoleHtml(o.getOperatorBizTranRoleList()
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
								html.append(getOperatorBizTranRoleBlankHtml());
							}
						}
						is++;
					} else {
						// サブシステムロール未設定
						html.append(getOperatorSubSystemRoleBlankHtml());

						if (o.getOperatorBizTranRoleList().size() > 0) {
							boolean ssfirst = true;
							// サブシステムロールに紐付かない取引ロール定義Htmlを表示
							html.append(getOperatorBizTranRoleHtml(o.getOperatorBizTranRoleList()
								, o.getOperatorCode()
								, ""
								, ssfirst));
						}
					}
					html.append("</tr>");
				}
			}
		});

		return html.toString();
	}

	/**
	 * 取引ロール定義Htmlを取得します。
	 * @param list オペレーター取引ロールリスト
	 * @param operatorCode オペレーターコード
	 * @param subSystemCode サブシステムコード
	 * @param isFirst 対象オペレーターで最初の定義（falseの時、空のオペレーター情報、サブシステムロール情報を表示）
	 * @return 取引ロール定義Html
	 */
	private String getOperatorBizTranRoleHtml(List<OperatorBizTranRoleDto> list,
		String operatorCode,
		String subSystemCode,
		boolean isFirst) {

		StringBuffer html = new StringBuffer();

		if (list != null && list.size() > 0) {
			boolean isFirstlocal = isFirst;
			List<OperatorBizTranRoleDto> tempList = newArrayList();
			if ("".equals(subSystemCode)) {
				tempList.addAll(list);
			} else {
				list.stream().filter(obtrf -> obtrf.getSubSystemCode().equals(subSystemCode)).forEach(obtr -> {
					tempList.add(obtr);
				});
			}
			for(OperatorBizTranRoleDto obtr : tempList) {
				if (!isFirstlocal) {
					html.append("</tr>");
					// オペレーター情報未設定
					html.append(getOperatorBlankHtml(operatorCode));
					// サブシステムロール未設定
					html.append(getOperatorSubSystemRoleBlankHtml());
				}
				// 取引ロール
				html.append("<td class=\"oaex_operator_biztran_role_code\">")
					.append(obtr.getBizTranRoleCode())
					.append("</td>");
				html.append("<td class=\"oaex_operator_biztran_role_name\">")
					.append(obtr.getBizTranRoleName())
					.append("</td>");
				// 取引ロール有効期限
				html.append("<td class=\"oaex_operator_biztran_role_expiration_date\">")
					.append(obtr.getExpirationStartDate()).append("～")
					.append(obtr.getExpirationEndDate())
					.append("</td>");
				isFirstlocal = false;

				// 表示した取引ロールを削除
				list.remove(obtr);
			};
		}

		return html.toString();
	}

	/**
	 * オペレーターの未設定Htmlを取得します。
	 * @return オペレーターの未設定Html
	 */
	private String getOperatorBlankHtml(String operatorCode) {

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
	 * オペレーターサブシステムロールの未設定Htmlを取得します。
	 * @return オペレーターサブシステムロールの未設定Html
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
	private String getPaginationHtml(List<OperatorDto> list, int pageNo) {

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
