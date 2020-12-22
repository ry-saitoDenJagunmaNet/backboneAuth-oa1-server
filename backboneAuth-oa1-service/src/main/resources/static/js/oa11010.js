/** Thymeleaf で起動時のみ実行 **/

/**
 * 画面Loadイベントです。
 */
function oaex_th_onload() {
	_isThymeleaf = true;

	// Collapsible の初期化（画面独自に再初期化）
	let elems = document.querySelectorAll(".oa_collapsible");
	// 折りたたみのopen/closeのインベトを追加
	let instances = M.Collapsible.init(elems,
		{accordion: false,
			onOpenEnd: oaex_collapsible_onOpenEnd,
			onCloseEnd: oaex_collapsible_onCloseEnd,
		});

	// 有効期限のラジオボタン初期化
	oaex_validThruSel_onChange();
	// サブシステムロール/取引ロールの指定方法（指定なし/OR/AND）を非表示
	oaex_collapsible_onCloseEnd();
	// サブシステムロール有効期限のラジオボタン初期化
	for (let i = 0; i < document.getElementById("subsystem_role").rows.length; i++) {
		oaex_subsystemRoleValidThruSel_onChange(i);
	}
	// 取引ロール有効期限のラジオボタン初期化
	for (let i = 0; i < document.getElementById("biztran_role").rows.length; i++) {
		oaex_biztranRoleValidThruSel_onChange(i);
	}

	// オペレーター一覧領域を表示
	oaex_viewOperatorTable();
}

/**
 * 検索ボタンクリックイベントです。
 * @param {int}} pageno 表示するページ番号
 */
function oaex_th_searchBtn_onClick(pageNo) {
	// 表示するページ番号を設定
	document.getElementById("pageNo").value = pageNo;
	let xhr = oa_th_sendFormData("search", document.forms[0]);

	if (xhr == null) {return;}

	let result = JSON.parse(xhr.responseText);
	document.getElementById("operator_table").innerHTML = result.operatorTable
	document.getElementById("operator_pagination").innerHTML = result.pagination
}

const _TABLE_ROW_FILTER = "oaex_table_row_filter";
/**
 * サブシステムフィルター選択変更イベントです。
 * @param {Object}} obj サブシステムフィルターオジェクト
 */
function oaex_th_subsystemFilter_onChange(obj) {
	let index = obj.selectedIndex;
	let biztranRole = document.getElementById("biztran_role");

	// 全行フィルター解除
	for (let row of biztranRole.rows) {
		row.classList.remove(_TABLE_ROW_FILTER);
	}

	if (index > 0){
		let subsystemcode = obj.options[index].value;
		// 選択したサブシステムでフィルター
		for (let row of biztranRole.rows) {
			let subSystemCode = document.getElementById("bizTranRoleList"+row.rowIndex+".subSystemCode");
			if (subSystemCode != null) {
				if (subsystemcode != subSystemCode.value) {
					row.classList.add(_TABLE_ROW_FILTER);
					// 検索条件対象から外す
					let biztranRoleSelected = document.getElementById("bizTranRoleList["+row.rowIndex+"].bizTranRoleSelected");
					biztranRoleSelected.checked = false;
				}
			}
		}
    }
}

/**
 * オペレーター履歴確認ボタンクリックイベントｆです。
 */
function oaex_th_oa11100Btn_onClick() {
	// 選択したオペレータコードを取得
	let operatorTable = document.getElementById("operator_table");
	let selectRow = oa_getTableSelectedRowIndex(operatorTable);
	if (selectRow == -1) {return;}

	let operatorId = "";
	for (let cellChildNode of operatorTable.rows[selectRow].cells[4].childNodes) {
		if (cellChildNode.nodeName.toLowerCase() == "input") {
			operatorId = cellChildNode.value;
			break;
		}
	}
	if (operatorId.length == 0)  {return;}
	//alert(operatorId);

	// オペレーター履歴確認に遷移
	location.href = "../oa11100/get?oi=" + operatorId;
	return;
}

/**
 * 選択ボタンクリックイベントです。
 */
function oaex_th_selectBtn_onClick() {
	let operatorId = oaex_th_getSelectedOperatorId();
	if (operatorId.length == 0)  {
		oa_showAlert("オペレーターを選択して下さい。");
		return;
	}

	let responseMethod = document.getElementById("response_method").value;

	// オペレーター履歴確認に遷移
	location.href = "../"+responseMethod+"?operatorId=" + operatorId;
	return;
}

/**
 * 選択したオペレータコードを取得します。
 */
function oaex_th_getSelectedOperatorId() {
	// 選択したオペレータコードを取得
	let operatorTable = document.getElementById("operator_table");
	let selectRow = oa_getTableSelectedRowIndex(operatorTable);
	if (selectRow == -1) {return "";}

	let operatorId = "";
	for (let cellChildNode of operatorTable.rows[selectRow].cells[4].childNodes) {
		if (cellChildNode.nodeName.toLowerCase() == "input") {
			operatorId = cellChildNode.value;
			break;
		}
	}
	return operatorId;
}

/** Thymeleafとモックで共用 **/
/**
 * 画面Loadイベントです。
 */
window.onload = function() {
	// Collapsible の初期化（画面独自に再初期化）
	let elems = document.querySelectorAll(".oa_collapsible");
	// 折りたたみのopen/closeのインベトを追加
	let instances = M.Collapsible.init(elems,
		{accordion: false,
			onOpenEnd: oaex_collapsible_onOpenEnd,
			onCloseEnd: oaex_collapsible_onCloseEnd,
		});

	// サブシステムロール/取引ロールの指定方法（指定なし/OR/AND）を非表示
	oaex_collapsible_onCloseEnd();

	// 有効期限のラジオボタン初期化
	document.getElementById("valid_thru_sel_non").checked = true;
	oaex_validThruSel_onChange();

	//  サブシステムロール条件のラジオボタン初期化
	document.getElementById("subsystem_role_sel_and").checked = true;
	oaex_subsystemRoleSel_onChange();

	// サブシステムロール有効期限のラジオボタン初期化
	for (let i = 0; i < document.getElementById("subsystem_role").rows.length; i++) {
		document.getElementById("subsystem_role_valid_thru_sel_non[" + i + "]").checked = true;
		oaex_subsystemRoleValidThruSel_onChange(i);
	}

	// 取引ロール条件のラジオボタン初期化
	document.getElementById("biztran_role_sel_and").checked = true;
	oaex_biztranRoleSel_onChange();

	// 取引ロール有効期限のラジオボタン初期化
	for (let i = 0; i < document.getElementById("biztran_role").rows.length; i++) {
		document.getElementById("biztran_role_valid_thru_sel_non[" + i + "]").checked = true;
		oaex_biztranRoleValidThruSel_onChange(i);
	}
}

/**
 * 折りたたみのopenインベトです。
 */
function oaex_collapsible_onOpenEnd() {
	// サブシステムロール
	for (let header of document.querySelectorAll(".oaex_subsystem_role_header")) {
		if (header.classList.contains("active")) {
			for (let header_conditions of header.querySelectorAll(".oaex_subsystem_role_header_conditions")) {
				// 指定方法（指定なし/OR/AND）を表示
				header_conditions.classList.remove("oaex_subsystem_role_clsoe");
			}
		}
	}
	// 取引ロール
	for (let header of document.querySelectorAll(".oaex_biztran_role_header")) {
		if (header.classList.contains("active")) {
			for (let header_conditions of header.querySelectorAll(".oaex_biztran_role_header_conditions")) {
				// 指定方法（指定なし/OR/AND）を表示
				header_conditions.classList.remove("oaex_biztran_role_clsoe");
			}
		}
	}
}

/**
 * 折りたたみのcloseインベトです。
 */
function oaex_collapsible_onCloseEnd() {
	// サブシステムロール
	for (let header of document.querySelectorAll(".oaex_subsystem_role_header")) {
		if (!header.classList.contains("active")) {
			for (let header_conditions of header.querySelectorAll(".oaex_subsystem_role_header_conditions")) {
				// 指定方法（指定なし/OR/AND）を非表示
				header_conditions.classList.add("oaex_subsystem_role_clsoe");
			}
		}
	}
	// 取引ロール
	for (let header of document.querySelectorAll(".oaex_biztran_role_header")) {
		if (!header.classList.contains("active")) {
			for (let header_conditions of header.querySelectorAll('.oaex_biztran_role_header_conditions')) {
				// 指定方法（指定なし/OR/AND）を非表示
				header_conditions.classList.add("oaex_biztran_role_clsoe");
			}
		}
	}
}

/**
 * 有効期限ラジオボタンの変更イベントです。
 */
function oaex_validThruSel_onChange() {
	let val;
	val = oa_getRadioCheckedValue("validThruSelect");

	document.getElementById("valid_thru_status_date_div").style.display = "none";
	document.getElementById("valid_thru_conditions_div").style.display = "none";

	oa_setDisabled("valid_thru_status_date", true);
	oa_setDisabled("valid_thru_start_date_from", true);
	oa_setDisabled("valid_thru_start_date_to", true);
	oa_setDisabled("valid_thru_end_date_from", true);
	oa_setDisabled("valid_thru_end_date_to", true);

	if (val == "0") {
		// 指定なし
	} if (val == "1") {
		// 状態指定日
		document.getElementById("valid_thru_status_date_div").style.display = "block";
		oa_setDisabled("valid_thru_status_date", false);
	} if (val == "2") {
		// 条件指定
		document.getElementById("valid_thru_conditions_div").style.display = "block";
		oa_setDisabled("valid_thru_start_date_from", false);
		oa_setDisabled("valid_thru_start_date_to", false);
		oa_setDisabled("valid_thru_end_date_from", false);
		oa_setDisabled("valid_thru_end_date_to", false);
	}		
}

/**
 * サブシステムロール条件のラジオボタンの変更イベントです。
 */
function oaex_subsystemRoleSel_onChange() {
	let val = oa_getRadioCheckedValue("subSystemRoleConditionsSelect");

	if (val == "0") {
		// 指定なし　無効にする
		let objTable = document.getElementById("subsystem_role");
		for (let node of objTable.getElementsByTagName("input")) {
			oa_setDisabledForObject(node, true);
		}
	} else {
		// ＡＮＤ・ＯＲ　有効にする
		let objTable = document.getElementById("subsystem_role");
		for (let node of objTable.getElementsByTagName("input")) {
			oa_setDisabledForObject(node, false);
		}
	}
}

/**
 * サブシステムロールテーブル有効期限のラジオボタンの変更イベントです。
 * @param {int}} objRow サブシステムロールテーブルの対象行
 */
function oaex_subsystemRoleValidThruSel_onChange(objRow) {
	let val;
	if (_isThymeleaf) {
		val = oa_getRadioCheckedValue("subSystemRoleList[" + objRow + "].validThruSelect");
	} else {
		val = oa_getRadioCheckedValue("subsystemRoleValidThruSelect[" + objRow + "]");
	}

	oa_setDisabled("subsystem_role_valid_thru_status_date[" + objRow + "]", true);
	oa_setDisabled("subsystem_role_valid_thru_start_date_from[" + objRow + "]", true);
	oa_setDisabled("subsystem_role_valid_thru_start_date_to[" + objRow + "]", true);
	oa_setDisabled("subsystem_role_valid_thru_end_date_from[" + objRow + "]", true);
	oa_setDisabled("subsystem_role_valid_thru_end_date_to[" + objRow + "]", true);

	if (val == "0") {
		// 指定なし
	} if (val == "1") {
		// 状態指定日
		oa_setDisabled("subsystem_role_valid_thru_status_date[" + objRow + "]", false);
	} if (val == "2") {
		// 条件指定
		oa_setDisabled("subsystem_role_valid_thru_start_date_from[" + objRow + "]", false);
		oa_setDisabled("subsystem_role_valid_thru_start_date_to[" + objRow + "]", false);
		oa_setDisabled("subsystem_role_valid_thru_end_date_from[" + objRow + "]", false);
		oa_setDisabled("subsystem_role_valid_thru_end_date_to[" + objRow + "]", false);
		}
}

/**
 * 取引ロール条件のラジオボタンの変更イベントです。
 */
function oaex_biztranRoleSel_onChange() {
	let val = oa_getRadioCheckedValue("biztranRoleConditionsSelect");

	if (val == "0") {
		// 指定なし　無効にする
		let objTable = document.getElementById("biztran_role");
		for (let node of objTable.getElementsByTagName("input")) {
			oa_setDisabledForObject(node, true);
		}
		oa_setDisabled("subsystem_filter", true);
	} else {
		// ＡＮＤ・ＯＲ　有効にする
		let objTable = document.getElementById("biztran_role");
		for (let node of objTable.getElementsByTagName("input")) {
			oa_setDisabledForObject(node, false);
		}
		oa_setDisabled("subsystem_filter", false);
	}
	oa_initSelect();
}

/**
 * 取引ロールテーブル有効期限のラジオボタンの変更イベントです。
 * @param {int}} objRow サブシステムロールテーブルの対象行
 */
function oaex_biztranRoleValidThruSel_onChange(objRow) {
	let val;
	if (_isThymeleaf) {
		val = oa_getRadioCheckedValue("bizTranRoleList[" + objRow + "].validThruSelect");
	} else {
		val = oa_getRadioCheckedValue("biztranRoleValidThruSel[" + objRow + "]");
	}

	oa_setDisabled("biztran_role_valid_thru_status_date[" + objRow + "]", true);
	oa_setDisabled("biztran_role_valid_thru_start_date_from[" + objRow + "]", true);
	oa_setDisabled("biztran_role_valid_thru_start_date_to[" + objRow + "]", true);
	oa_setDisabled("biztran_role_valid_thru_end_date_from[" + objRow + "]", true);
	oa_setDisabled("biztran_role_valid_thru_end_date_to[" + objRow + "]", true);

	if (val == "0") {
		// 指定なし
	} if (val == "1") {
		// 状態指定日
		oa_setDisabled("biztran_role_valid_thru_status_date[" + objRow + "]", false);
	} if (val == "2") {
		// 条件指定
		oa_setDisabled("biztran_role_valid_thru_start_date_from[" + objRow + "]", false);
		oa_setDisabled("biztran_role_valid_thru_start_date_to[" + objRow + "]", false);
		oa_setDisabled("biztran_role_valid_thru_end_date_from[" + objRow + "]", false);
		oa_setDisabled("biztran_role_valid_thru_end_date_to[" + objRow + "]", false);
	}		
}

/**
 * オペレーターテーブル行のクリックイベントです。
 * 　オペレーターのサブシステムロール/取引ロールが複数行で構成されるため、
 * 　同一オペレーターに同じclass「oaex_operator_table_operator_X」を設定（htmに記述）し、
 * 　クリックした行と同じclassが設定してある行に選択色を設定する
 * 　※「oaex_operator_table_operator_X」の「X」はオペレーター毎に一意に採番
 * @param {Object} objRow オペレーターテーブルの行オブジェクト
 */
function oaex_operatorTable_onClick(objRow) {
	let objTable = document.getElementById("operator_table");
	// Table全行の選択を解除
	oa_setTableRowSelectedRemoveAll(objTable);
	for (let calssid of objRow.classList) {
		if (calssid.substr(0, "oaex_operator_table_operator_".length) == "oaex_operator_table_operator_") {
			// クリックした行の「oaex_operator_table_operator_X」と同じclassが設定してある行に選択色を設定
			objTable.querySelectorAll("."+calssid).forEach((el) => {
				el.classList.add(_TABLE_ROW_SELECTED1);
				el.classList.add(_TABLE_ROW_SELECTED2);
			});
			break;
		}
	}
}

/**
 * 検索ボタンクリックイベントです。
 */
function oaex_searchBtn_onClick() {
	// オペレーター一覧領域を表示
	oaex_viewOperatorTable();
}

/**
 * オペレーター一覧領域を表示します。
 */
function oaex_viewOperatorTable() {
	// テーブル＆ページネーションを表示
	let table = document.getElementById("operator_table");
	if (table != null) {
		table.style.visibility = "visible";
	}
	let pagination = document.getElementById("operator_table_pagination");
	if (pagination != null) {
		pagination.style.visibility = "visible";
	}
}

/**
 * 閉じるボタンクリックイベントです。
 */
function oaex_closeBtn_onClick() {
	let responseMethod = document.getElementById("response_method").value;
	if (responseMethod.length != 0)  {
		// オペレーター履歴確認に遷移
		location.href = "../"+responseMethod+"?operatorId=";
		return;
	}

	oa_transferForm("oa00000");
}
