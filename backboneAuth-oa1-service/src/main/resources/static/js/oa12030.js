/**
 * 画面Loadイベントです。
 */
window.onload = function() {
	// 初期化処理
	oaex_initialize();
}

/**
 * 初期化処理です。
 */
function oaex_initialize() {
	// ＪＡ
	document.getElementById("ja_check").checked = false;	
	oaex_ja_check_onChange();
	document.getElementById("ja").selectedIndex = 0;
	// 店舗
	document.getElementById("tempo_check").checked = false;	
	oaex_tempo_check_onChange();
	document.getElementById("tempo").selectedIndex = 0;
	// 店舗を無効
	oa_setDisabled("tempo", true);
	//oa_initSelect();
	// サブシステム
	document.getElementById("subsystem_check").checked = false;	
	oaex_subsystem_check_onChange();
	document.getElementById("subsystem").selectedIndex = 0;
	// 取引グループ
	document.getElementById("biztran_grp_check").checked = false;	
	oaex_biztran_grp_check_onChange();
	// 取引
	document.getElementById("biztran_check").checked = false;	
	oaex_biztran_check_onChange();
	// 有効期限
	document.getElementById("valid_thru_start_date").value = "";
	document.getElementById("valid_thru_end_date").value = "";
	// 抑止理由
	document.getElementById("suspend_reason").value = "";

	// todo: 取引グループ、取引のテーブルをクリア（ほんとはサブシステムが選択されていないと取引グループのテーブルは設定されない）
	oa_setTableRowSelectedRemoveAll(document.getElementById("biztran_grp_table"));
	oa_setTableRowSelectedRemoveAll(document.getElementById("biztran_table"));
	document.getElementById("biztran_grp_table").style.overflow = "hidden";
	document.getElementById("biztran_table").style.overflow = "hidden";

	// todo: 新規または詳細の画面の入力制御
	let mode = oa_getParam("mode");
	if (mode == "1") {
		//　詳細ボタンで遷移

		// サブシステム
		document.getElementById("subsystem_check").checked = true;	
		oaex_subsystem_check_onChange();
		document.getElementById("subsystem").selectedIndex = 2;
		// 取引グループ
		document.getElementById("biztran_grp_table").style.visibility = "visible";
		oa_setTableRowSelected(document.getElementById("biztran_grp_table").rows[0]);
		// 取引
		document.getElementById("biztran_check").checked = true;	
		oaex_biztran_check_onChange();
		document.getElementById("biztran_table").style.visibility = "visible";
		oa_setTableRowSelected(document.getElementById("biztran_table").rows[2]);
		// 有効期限
		document.getElementById("valid_thru_start_date").value = "2019/04/01";
		document.getElementById("valid_thru_end_date").value = "9999/12/31";
		// 抑止理由
		document.getElementById("suspend_reason").value = "合併前のデータ凍結の為のデータ投入抑止";

		// 画面項目を無効にする
		// ＪＡ
		oa_setDisabled("ja", true);
		oa_setDisabled("ja_check", true);
		// 店舗
		oa_setDisabled("tempo", true);
		oa_setDisabled("tempo_check", true);
		// サブシステム
		oa_setDisabled("subsystem", true);
		oa_setDisabled("subsystem_check", true);
		// 取引グループ
		oa_setDisabled("biztran_grp_table", true);
		oa_setDisabled("biztran_grp_check", true);
		// 取引
		oa_setDisabled("biztran_table", true);
		oa_setDisabled("biztran_check", true);
		// 抑止理由
		oa_setDisabled("suspend_reason", true);

		document.title = "一時取引抑止更新 | 基幹系認証管理システム";
		for(let headerNd of document.getElementsByTagName("header")) {
			for(let headerLabelNd of headerNd.getElementsByTagName("span")) {
				headerLabelNd.innerText = "一時取引抑止更新";
				break;
			}
			break;
		}
		for(let footerNd of document.getElementsByTagName("footer")) {
			for(let footerLabelNd of footerNd.getElementsByTagName("a")) {
				if (footerLabelNd.innerText == "登録") {
					footerLabelNd.innerText = "更新"
					break;
				}
			}
			break;
		}
	}
	// selectの初期化
	oa_initSelect();
	// input-field の初期化
	oa_initInputField()
}

/**
 * ＪＡチェックボックスの変更イベントです。
 */
function oaex_ja_check_onChange() {
	// ＪＡの入力項目領域を必須または任意表示設定
	let thisNode = document.getElementById("ja_check");
	let rowNode = thisNode.parentNode.parentNode.parentNode;
	oaex_setRequired(rowNode, thisNode.checked);
}

/**
 * ＪＡの変更イベントです。
 */
function oaex_ja_onChange() {
	let thisNode = document.getElementById("ja");
	if (thisNode.value.length > 0) {
		// ＪＡチェックボックスのチェックON
		document.getElementById("ja_check").checked = true;	
		oaex_ja_check_onChange();

		// 選択したＪＡで店舗を検索し、店舗を有効にする
		// todo: ここで、再検索
		document.getElementById("tempo").selectedIndex = 0;
		oa_setDisabled("tempo", false);
		oa_initSelect();
	} else {
		// ＪＡチェックボックスのチェックOFF
		document.getElementById("ja_check").checked = false;	
		oaex_ja_check_onChange();

		// 店舗をクリアし、店舗を無効にする
		// todo: ここで、クリア
		document.getElementById("tempo").selectedIndex = 0;
		oa_setDisabled("tempo", true);
		oa_initSelect();
	}

	// 店舗チェックボックスのチェックOFF
	document.getElementById("tempo_check").checked = false;	
	oaex_tempo_check_onChange();
}

/**
 * 店舗チェックボックスの変更イベントです。
 */
function oaex_tempo_check_onChange() {
	// 店舗の入力項目領域を必須または任意表示設定
	let thisNode = document.getElementById("tempo_check");
	let rowNode = thisNode.parentNode.parentNode.parentNode;
	oaex_setRequired(rowNode, thisNode.checked);

	if (thisNode.checked) {
		// JAチェックボックスのチェックON
		document.getElementById("ja_check").checked = true;	
		oaex_ja_check_onChange();
	}
}

/**
 * 店舗の変更イベントです。
 */
function oaex_tempo_onChange() {
	let thisNode = document.getElementById("tempo");
	if (thisNode.value.length > 0) {
		// 店舗チェックボックスのチェックON
		document.getElementById("tempo_check").checked = true;	
		oaex_tempo_check_onChange();
	} else {
		// 店舗チェックボックスのチェックOFF
		document.getElementById("tempo_check").checked = false;	
		oaex_tempo_check_onChange();
	}
}

/**
 * サブシステムチェックボックスの変更イベントです。
 */
function oaex_subsystem_check_onChange() {
	// サブシステムの入力項目領域を必須または任意表示設定
	let thisNode = document.getElementById("subsystem_check");
	let colNode = thisNode.parentNode.parentNode;
	oaex_setRequired(colNode, thisNode.checked);
}

/**
 * サブシステムの選択変更イベントです。
 */
function oaex_subsystem_onChange() {
	let thisNode = document.getElementById("subsystem");
	if (thisNode.value.length > 0) {
		// サブシステムチェックボックスのチェックON
		document.getElementById("subsystem_check").checked = true;
		oaex_subsystem_check_onChange();
		//　取引グループテーブル、取引テーブルを表示
		for(let nodes of document.getElementsByClassName("oaex_biztran_grp_table")) {
			nodes.style.visibility = "visible";
		}
		for(let nodes of document.getElementsByClassName("oaex_biztran_table")) {
			nodes.style.visibility = "visible";
		}
		// 取引グループチェックボックスのチェックOFF
		document.getElementById("biztran_grp_check").checked = false;	
		oaex_biztran_grp_check_onChange();
		// 取引チェックボックスのチェックOFF
		document.getElementById("biztran_check").checked = false;	
		oaex_biztran_check_onChange();
	} else {
		// サブシステムチェックボックスのチェックOFF
		document.getElementById("subsystem_check").checked = false;
		oaex_subsystem_check_onChange();
		//　取引グループテーブル、取引テーブルを非表示
		for(let nodes of document.getElementsByClassName("oaex_biztran_grp_table")) {
			nodes.style.visibility = "collapse";
		}
		for(let nodes of document.getElementsByClassName("oaex_biztran_table")) {
			nodes.style.visibility = "collapse";
		}
	}

	// 取引グループフィルタ
	oaex_biztran_grp_filter();
	// 取引フィルタ
	oaex_biztran_filter();
}

/**
 * 取引グループテーブルのフィルタを行います。
 */
function oaex_biztran_grp_filter() {
	let biztranGrpTable = document.getElementById("biztran_grp_table");
	let subsystemSel = document.getElementById("subsystem").value;

	if (subsystemSel.length > 0) {
		for (let tableRow of biztranGrpTable.rows) {
			let disprow = false;
			for(let tableCol of tableRow.getElementsByClassName("oaex_biztran_grp_code")) {
				if (tableCol.innerText.substr(0, subsystemSel.length) == subsystemSel) {
					disprow = true;
					break;
				}
			}
			if (disprow) {
				tableRow.style.display = "block";
			} else {
				oa_setTableRowSelectedRemove(tableRow);
				tableRow.style.display = "none";
			}

		}
	} else {
		for (let tableRow of biztranGrpTable.rows) {
			tableRow.style.display = "block";
		}
	}

	// 1行目（「指定なし」行）の表示/非表示
	if (document.getElementById("biztran_grp_check").checked) {
		biztranGrpTable.rows[0].style.display = "none";
	} else {
		biztranGrpTable.rows[0].style.display = "block";
	}
}

/**
 * 取引テーブルのフィルタを行います。
 */
function oaex_biztran_filter() {
	let biztranTable = document.getElementById("biztran_table");
	let subsystemSel = document.getElementById("subsystem").value;
	let biztranGrpTable = document.getElementById("biztran_grp_table");
	let biztranGrpSelIx = oa_getTableSelectedRowIndex(biztranGrpTable);

	if (biztranGrpSelIx <= 0) {
		// 取引グループ未選択（1行目（「指定なし」行）選択も含む）
		if (subsystemSel.length > 0) {
			// サブシステムでフィルタ
			for (let tableRow of biztranTable.rows) {
				let disprow = false;
				for(let tableCol of tableRow.getElementsByClassName("oaex_biztran_table_biztran_grp_code")) {
					if (tableCol.innerText.substr(0, subsystemSel.length) == subsystemSel) {
						disprow = true;
						break;
					}
				}
				if (disprow) {
					tableRow.style.display = "block";
				} else {
					oa_setTableRowSelectedRemove(tableRow);
					tableRow.style.display = "none";
				}
	
			}
		} else {
			// 全フィルタ解除
			for (let tableRow of biztranTable.rows) {
				tableRow.style.display = "block";
			}
		}
	} else {
		// 取引グループ選択
		for (let tableRow of biztranTable.rows) {
			let disprow = false;
			for(let tableCol of tableRow.getElementsByClassName("oaex_biztran_table_biztran_grp_code")) {
				if (tableCol.innerText == biztranGrpTable.rows[biztranGrpSelIx].cells[0].innerText) {
					disprow = true;
					break;
				}
			}
			if (disprow) {
				tableRow.style.display = "block";
			} else {
				oa_setTableRowSelectedRemove(tableRow);
				tableRow.style.display = "none";
			}
		}
	}
}

/**
 * 取引グループチェックボックスの変更イベントです。
 */
function oaex_biztran_grp_check_onChange() {
	// 取引グループの入力項目領域を必須または任意表示設定
	let thisNode = document.getElementById("biztran_grp_check");
	let colNode = thisNode.parentNode.parentNode;
	oaex_setRequired(colNode, thisNode.checked);

	// 取引グループテーブル
	let biztranGrpTable = document.getElementById("biztran_grp_table");

	if (thisNode.checked) {
		// 取引チェックボックスのチェックOFF（取引グループと取引は排他関係）
		document.getElementById("biztran_check").checked = false;	
		oaex_biztran_check_onChange();

		// 1行目（「指定なし」行）の非表示
		biztranGrpTable.rows[0].style.display = "none";
	} else {
		// 1行目（「指定なし」行）の表示
		biztranGrpTable.rows[0].style.display = "block";
	}
}

/**
 * 取引グループの選択変更イベントです。
 * @param {Object} thisRow 選択行
 */
function oaex_biztran_grp_onSelectChange(thisRow) {
	let biztranGrpTable = document.getElementById("biztran_grp_table");

	// 無効の場合、処理抑止
	if (biztranGrpTable.getAttribute("disabled") == "disabled") {return};
	// 選択行が同じ場合、処理抑止
	let oldSelIndex = oa_getTableSelectedRowIndex(biztranGrpTable);
	if (thisRow == biztranGrpTable.rows[oldSelIndex]) {return;}

	// 選択行の背景を変更
	oa_setTableRowSelected(thisRow);

	// 選択した取引グループで取引をを検索表示する
	// todo: ここで、全選択解除

	let biztranGrpTableSelIx = oa_getTableSelectedRowIndex(biztranGrpTable);
	let biztranTable = document.getElementById("biztran_table");
	if (biztranGrpTableSelIx == 0) {
		// 1行目（「指定なし」行）選択時
		// 取引チェックボックスのチェックON
		document.getElementById("biztran_check").checked = true;	
		oaex_biztran_check_onChange();

		// 取引フィルタ oa_getTableSelectedRowIndex
		oaex_biztran_filter();
		// 取引の行数が変わったので選択行をtopに移動
		biztranTable.parentNode.scrollTop = biztranTable.rows[oa_getTableSelectedRowIndex(biztranTable)].offsetTop;
	} else {
		// 取引グループチェックボックスのチェックON
		document.getElementById("biztran_grp_check").checked = true;	
		oaex_biztran_grp_check_onChange();

		// 取引の選択行を退避（モックなので行index）
		let biztranSelIx = oa_getTableSelectedRowIndex(biztranTable);
		// 取引の全選択解除
		oa_setTableRowSelectedRemoveAll(document.getElementById("biztran_table"));
		// 取引フィルタ
		oaex_biztran_filter();
		if (biztranSelIx != -1 && biztranTable.rows[biztranSelIx].style.display != "none") {
			// 取引の選択行を退避（モックなので行index）
			oa_setTableRowSelected(biztranTable.rows[biztranSelIx]);
		}
	}
}

/**
 * 取引チェックボックスの変更イベントです。
 */
function oaex_biztran_check_onChange() {
	// 取引の入力項目領域を必須または任意表示設定
	let thisNode = document.getElementById("biztran_check");
	let colNode = thisNode.parentNode.parentNode;
	oaex_setRequired(colNode, thisNode.checked);

	// 取引テーブル
	let biztranTable = document.getElementById("biztran_table");

	if (thisNode.checked) {
		// 取引グループチェックボックスのチェックOFF（取引グループと取引は排他関係）
		document.getElementById("biztran_grp_check").checked = false;	
		oaex_biztran_grp_check_onChange();
	}
}

/**
 * 取引の選択変更イベントです。
 * @param {Object} thisRow 選択行
 */
function oaex_biztran_onSelectChange(thisRow) {
	let biztranTable = document.getElementById("biztran_table");

	// 無効の場合、処理抑止
	if (biztranTable.getAttribute("disabled") == "disabled") {return};
	// 選択行が同じ場合、処理抑止
	let oldSelIndex = oa_getTableSelectedRowIndex(biztranTable);
	if (thisRow == biztranTable.rows[oldSelIndex]) {return;}

	// 選択行の背景を変更
	oa_setTableRowSelected(thisRow);

	let biztranTableSelIx = oa_getTableSelectedRowIndex(biztranTable);
	if (biztranTableSelIx > 0) {
		// 取引チェックボックスのチェックON
		document.getElementById("biztran_check").checked = true;	
		oaex_biztran_check_onChange();
	}
}

/**
 * 入力項目領域を必須または任意表示にします。
 * @param {Object}} obj　入力項を配置領域（行/カラム） 
 * @param {Boolean} required true:必須、false:任意
 */
function oaex_setRequired(obj, required) {
	if (required) {
		if (obj.classList.contains("oa_optional")) {
			// 必須に変更
			obj.classList.remove("oa_optional");
			obj.classList.add("oa_required");
		}
	} else {
		if (obj.classList.contains("oa_required")) {
			// 任意に変更
			obj.classList.remove("oa_required");
			obj.classList.add("oa_optional");
		}
	}
}

/**
 * 閉じるボタンクリックイベントです。
 */
function oaex_closeBtn_onClick() {
	oa_showConfirm("変更内容が登録されていません。\n画面を閉じてよろしいですか？", oaex_closeBtn_onClick_confirmReturn);
}

/**
 * 閉じるボタンで表示したダイアログを閉じるイベントです。
 * @param {Boolean} rtn true:ＯＫボタンクリック
 */
function oaex_closeBtn_onClick_confirmReturn(rtn) {
	if (rtn) {
		oa_transferForm("oa12020");
	}
}

/**
 * 削除ボタンクリックイベントです。
 */
function oaex_deleteBtn_onClick() {
	oa_showConfirm("データを削除すると、元に戻すことはできません。\n削除してよろしいですか？", oaex_deleteBtn_onClick_confirmReturn);
}

/**
 * 削除ボタンで表示したダイアログを閉じるイベントです。
 * @param {Boolean} rtn true:ＯＫボタンクリック
 */
function oaex_deleteBtn_onClick_confirmReturn(rtn) {
	if (rtn) {
		// 初期化処理
		oaex_initialize();
		oa_showAlert("削除しました。");
	}
}

/**
 * 登録／更新ボタンクリックイベントです。
 */
function oaex_entryBtn_onClick() {
	oa_showAlert("登録しました。");
}
