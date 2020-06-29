/**
 * 画面Loadイベントです。
 *  Thymeleaf で起動時のみ実行
 */
function oaex_onload() {
	oaex_searchBtn_onClick();
}

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
	document.getElementById("expiration_sel_non").checked = true;	
	oaex_expiration_sel_onChange();

	//  サブシステムロール条件のラジオボタン初期化
	document.getElementById("subsystem_role_sel_and").checked = true;
	oaex_subsystem_role_sel_onChange();

	// サブシステムロール有効期限のラジオボタン初期化
	for (let i = 0; i < document.getElementById("subsystem_role").rows.length; i++) {
		document.getElementById("subsystem_role_expiration_sel_non[" + i + "]").checked = true;
		oaex_subsystem_role_expiration_sel_onChange(i);
	}

	// 取引ロール条件のラジオボタン初期化
	document.getElementById("biztran_role_sel_and").checked = true;
	oaex_biztran_role_sel_onChange();

	// 取引ロール有効期限のラジオボタン初期化
	for (let i = 0; i < document.getElementById("biztran_role").rows.length; i++) {
		document.getElementById("biztran_role_expiration_sel_non[" + i + "]").checked = true;
		oaex_biztran_role_expiration_sel_onChange(i);
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
function oaex_expiration_sel_onChange() {
	let val = oa_getRadioCheckedValue("expiration_sel");

	document.getElementById("expiration_status_date_div").style.display = "none";
	document.getElementById("expiration_conditions_div").style.display = "none";

	oa_setDisabled("expiration_status_date", true);
	oa_setDisabled("expiration_start_date_from", true);
	oa_setDisabled("expiration_start_date_to", true);
	oa_setDisabled("expiration_end_date_from", true);
	oa_setDisabled("expiration_end_date_to", true);

	if (val == "0") {
		// 指定なし
	} if (val == "1") {
		// 状態指定日
		document.getElementById("expiration_status_date_div").style.display = "block";
		oa_setDisabled("expiration_status_date", false);
	} if (val == "2") {
		// 条件指定
		document.getElementById("expiration_conditions_div").style.display = "block";
		oa_setDisabled("expiration_start_date_from", false);
		oa_setDisabled("expiration_start_date_to", false);
		oa_setDisabled("expiration_end_date_from", false);
		oa_setDisabled("expiration_end_date_to", false);
	}		
}

/**
 * サブシステムロール条件のラジオボタンの変更イベントです。
 */
function oaex_subsystem_role_sel_onChange() {
	let val = oa_getRadioCheckedValue("subsystem_role_sel");

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
function oaex_subsystem_role_expiration_sel_onChange(objRow) {
	let val = oa_getRadioCheckedValue("subsystem_role_expiration_sel[" + objRow + "]");

	oa_setDisabled("subsystem_role_expiration_status_date[" + objRow + "]", true);
	oa_setDisabled("subsystem_role_expiration_start_date_from[" + objRow + "]", true);
	oa_setDisabled("subsystem_role_expiration_start_date_to[" + objRow + "]", true);
	oa_setDisabled("subsystem_role_expiration_end_date_from[" + objRow + "]", true);
	oa_setDisabled("subsystem_role_expiration_end_date_to[" + objRow + "]", true);

	if (val == "0") {
		// 指定なし
	} if (val == "1") {
		// 状態指定日
		oa_setDisabled("subsystem_role_expiration_status_date[" + objRow + "]", false);
	} if (val == "2") {
		// 条件指定
		oa_setDisabled("subsystem_role_expiration_start_date_from[" + objRow + "]", false);
		oa_setDisabled("subsystem_role_expiration_start_date_to[" + objRow + "]", false);
		oa_setDisabled("subsystem_role_expiration_end_date_from[" + objRow + "]", false);
		oa_setDisabled("subsystem_role_expiration_end_date_to[" + objRow + "]", false);
		}		
}

/**
 * 取引ロール条件のラジオボタンの変更イベントです。
 */
function oaex_biztran_role_sel_onChange() {
	let val = oa_getRadioCheckedValue("biztran_role_sel");

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
function oaex_biztran_role_expiration_sel_onChange(objRow) {
	let val = oa_getRadioCheckedValue("biztran_role_expiration_sel[" + objRow + "]");

	oa_setDisabled("biztran_role_expiration_status_date[" + objRow + "]", true);
	oa_setDisabled("biztran_role_expiration_start_date_from[" + objRow + "]", true);
	oa_setDisabled("biztran_role_expiration_start_date_to[" + objRow + "]", true);
	oa_setDisabled("biztran_role_expiration_end_date_from[" + objRow + "]", true);
	oa_setDisabled("biztran_role_expiration_end_date_to[" + objRow + "]", true);

	if (val == "0") {
		// 指定なし
	} if (val == "1") {
		// 状態指定日
		oa_setDisabled("biztran_role_expiration_status_date[" + objRow + "]", false);
	} if (val == "2") {
		// 条件指定
		oa_setDisabled("biztran_role_expiration_start_date_from[" + objRow + "]", false);
		oa_setDisabled("biztran_role_expiration_start_date_to[" + objRow + "]", false);
		oa_setDisabled("biztran_role_expiration_end_date_from[" + objRow + "]", false);
		oa_setDisabled("biztran_role_expiration_end_date_to[" + objRow + "]", false);
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
function oaex_operator_table_onClick(objRow) {
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
	// todo:テーブル＆ページネーションを表示
	document.getElementById("operator_table").style.visibility = "visible";
	document.getElementById("operator_table_pagination").style.visibility = "visible";
}

/**
 * 閉じるボタンクリックイベントです。
 */
function oaex_closeBtn_onClick() {
	oa_transferForm("oa00000");
}
