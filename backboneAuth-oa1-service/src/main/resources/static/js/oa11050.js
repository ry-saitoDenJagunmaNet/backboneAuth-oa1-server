/* 未付与ロールテーブルの非表示状態CSSクラスID */
const _RIGHT_TABLE_HIDDEN = "oaex_unassign_role_table_hidden_row";

/**
 * 画面 Loadイベントです
 */
function oaex_th_onload() {
	_isThymeleaf = true;

	// 未付与ロールテーブルを初期非表示
	oaex_initHiddenUnAssignRoleList();
}
window.onload = function() {
	// 未付与ロールテーブルを初期非表示
	oaex_initHiddenUnAssignRoleList();
}
/**
 * 未付与ロール一リストの付与したロール行を非表示にします
 * 初期表示時に付与したサブシステムロールを未付与ロール一覧から非表示
 */
function oaex_initHiddenUnAssignRoleList() {
	let assignRoleTable = document.getElementById("assign_role_table");
	let unAssignRoleTable = document.getElementById("unassign_role_table");
	for (let assignRoleTableRow of assignRoleTable.rows) {
		for (let unAssignRoleTableRow of unAssignRoleTable.rows) {
			if (unAssignRoleTableRow.cells[0].innerText == assignRoleTableRow.cells[0].innerText ) {
				unAssignRoleTableRow.classList.add(_RIGHT_TABLE_HIDDEN)
			}
		}
	}
}

/**
 * 表示有効行を選択状態にします
 * @param {TableObject} objTable 対象テーブル
 * @param {*} rowNo 元の選択行
 */
function oaex_setSelectRow(objTable, rowNo) {
	let num = -1;
	if (rowNo <= objTable.rows.length - 1) {
		// 選択行より下で表示行を検索
		for(let i = rowNo + 1; i < objTable.rows.length; i++) {
			// 非表示行は除く
			if (objTable.rows[i].classList.length == 0
				|| !objTable.rows[i].classList.contains(_RIGHT_TABLE_HIDDEN)) {
				num = i
				break;
			}
		}
		if (num == -1) {
			// 選択行より上で表示行を検索
			for(let i = rowNo - 1; i >= 0; i--) {
				//非表示行は除く
				if (objTable.rows[i].classList.length == 0
					|| !objTable.rows[i].classList.contains(_RIGHT_TABLE_HIDDEN)) {
					num = i
					break;
				}
			}
		}
	}
	if (num == -1) {
		// 表示行無し
		oa_setTableRowSelectedRemoveAll(objTable);
	} else {
		oa_setTableRowSelected(objTable.rows[num]);
	}
}

/**
 * 付与ロールテーブル行 Clickイベントです
 * （移動ボタン(▶)の有効/無効を切り替えます）
 * @param {TableRowObject} assignRoleTableRow 対象テーブル行
 */
function oaex_assignRoleTableRow_onClick(assignRoleTableRow) {
	let id = "assignRoleTableVoList" + (assignRoleTableRow.rowIndex-1) + ".isModifiable";
	let isModifiable = document.getElementById(id);
	// 右から左に移動したロール
	if (isModifiable == null) {
		document.getElementById("move_remove_btn").removeAttribute("disabled");
	 	return;
	}
	// 画面表示時からあったロール
	if (isModifiable.value == "true") {
		document.getElementById("move_remove_btn").removeAttribute("disabled");
	} else {
		// ボタン無効
		document.getElementById("move_remove_btn").setAttribute("disabled", "disabled");
	}
}

/**
 * 未付与ロールテーブル行 Clickイベントです
 * （移動ボタン(◀)の有効/無効を切り替えます）
 * @param {TableRowObject} unAssignRoleTableRow 対象テーブル行
 */
function oaex_unAssignRoleTableRow_onClick(unAssignRoleTableRow) {
	let id = "unAssignRoleTableVoList" + unAssignRoleTableRow.rowIndex + ".isModifiable";
	let isModifiable = document.getElementById(id);

	// 全ロール
	if (isModifiable.value == "true") {
		document.getElementById("move_add_btn").removeAttribute("disabled");
	} else {
		// ボタン無効
		document.getElementById("move_add_btn").setAttribute("disabled", "disabled");
	}
}

/**
 * コピーボタン Clickイベントです
 */
function oaex_th_copyBtn_onClick() {
	document.forms[0].action = "copy";
	document.forms[0].method = "POST";
	document.forms[0].submit();
}

/**
 * （◀）移動ボタン Clickイベントです
 * 付与ロールテーブルへ追加
 */
function oaex_moveAddBtn_onClick() {
	let assignRoleTable = document.getElementById("assign_role_table");
	let unAssignRoleTable = document.getElementById("unassign_role_table");
	for(let i = 0; i < unAssignRoleTable.rows.length; i++){
		let tableRow = unAssignRoleTable.rows[i];
		// 選択行を定義したCSSクラスで判定
		if (tableRow.classList.contains(_TABLE_ROW_SELECTED1)
				&& tableRow.classList.contains(_TABLE_ROW_SELECTED2)){
			// 新規行を追加
			let newRow = assignRoleTable.insertRow();
			// template行を適用
			newRow.innerHTML = assignRoleTable.rows[0].cloneNode(true).innerHTML;
			// 追加行にClickイベントを追加
			newRow.addEventListener('click', function(event) {
				oa_setTableRowSelected(this);
				oaex_assignRoleTableRow_onClick(this);
			});
			// コード、名称、有効期限(空)、隠しコード、隠し名称、隠し変更可否を追加行に設定
			newRow.cells[0].innerText = tableRow.cells[0].innerText;
			newRow.cells[1].innerText = tableRow.cells[1].innerText;
			newRow.cells[2].innerHTML = "<div class='input-field'>" +
										"	<input type='text' class='datepicker' name='assignRoleTableVoList[" + i + "].validThruStartDate'></input>" +
										"</div>" +
										"<span>～</span>" +
										"<div class='input-field'>" +
										"	<input type='text' class='datepicker' name='assignRoleTableVoList[" + i + "].validThruEndDate'></input>" +
										"</div>";
			newRow.cells[3].innerHTML = "<input type='hidden' name='assignRoleTableVoList[" + i + "].roleId' value='" + tableRow.cells[2].childNodes[0].value + "'></input>";
			newRow.cells[4].innerHTML = "<input type='hidden' name='assignRoleTableVoList[" + i + "].roleCode' value='" + tableRow.cells[3].childNodes[0].value + "'></input>";
			newRow.cells[5].innerHTML = "<input type='hidden' name='assignRoleTableVoList[" + i + "].roleName' value='" + tableRow.cells[4].childNodes[0].value + "'></input>";
			newRow.cells[6].innerHTML = "<input type='hidden' name='assignRoleTableVoList[" + i + "].subSystemCode' value='" + tableRow.cells[5].childNodes[0].value + "'></input>";
			newRow.cells[7].innerHTML = "<input type='hidden' name='assignRoleTableVoList[" + i + "].isModifiable' value='" + tableRow.cells[6].childNodes[0].value + "'></input>";
			// 選択行非表示
			unAssignRoleTable.rows[i].classList.add(_RIGHT_TABLE_HIDDEN)
			// 有効行を選択状態
			oaex_setSelectRow(unAssignRoleTable, i);
			// 未付与ロールテーブル行 Clickイベントです（移動ボタン(◀)の有効/無効を切り替えます）
			oaex_unAssignRoleTableRow_onClick(unAssignRoleTable.rows[i]);
			// datepicker の初期化
			oa_initDatepicker();
			// input-field の初期化
			oa_initInputField();
			// テーブル子nodeの項目名Indexを再採番し変更する
			oa_renumberItemNameIndexForTableChild(assignRoleTable);
			return;
		}
	}
}

/**
 * （▶）移動ボタン Clickイベントです
 * 付与ロールテーブルから削除
 */
function oaex_moveRemoveBtn_onClick() {
	let assignRoleTable = document.getElementById("assign_role_table");
	let unAssignRoleTable = document.getElementById("unassign_role_table");
	for(let i = 1; i < assignRoleTable.rows.length; i++) {
		let tableRow = assignRoleTable.rows[i];
		// 選択行を定義したCSSクラスで判定
		for(let j = 0; j < tableRow.classList.length; j++) {
			let rowclass = tableRow.classList[j];
			// 選択行
			if (tableRow.classList.contains(_TABLE_ROW_SELECTED1)
					&& tableRow.classList.contains(_TABLE_ROW_SELECTED2)){
				for(let k = 0; k < unAssignRoleTable.rows.length; k++) {
					// 選択行と同じコードの未付与ロールを再表示
					if (unAssignRoleTable.rows[k].cells[0].innerText == tableRow.cells[0].innerText) {
						unAssignRoleTable.rows[k].classList.remove(_RIGHT_TABLE_HIDDEN);
					}
				}
				//選択行削除
				assignRoleTable.deleteRow(i);
				// 有効行を選択状態
				if (i <= assignRoleTable.rows.length - 1) {
					oaex_setSelectRow(assignRoleTable, i-1);
				} else {
					oaex_setSelectRow(assignRoleTable, i-2);
				}
				// テーブル子nodeの項目名Indexを再採番し変更する
				oa_renumberItemNameIndexForTableChild(assignRoleTable);
				return;
			}
		}
	}
}

/**
 * 閉じるボタン Clickイベントです
 */
function oaex_closeBtn_onClick() {
	oa_transferForm("oa00000");
}

/**
 * 適用ボタン Clickイベントです
 */
function oaex_applyBtn_onClick() {
	if (_isThymeleaf) {
		oaex_th_applyBtn_onClick();
		return;
	}
	oa_showAlert("適用しました。");
}
function oaex_th_applyBtn_onClick() {
	document.forms[0].action = "apply";
	document.forms[0].method = "POST";
	document.forms[0].submit();
}
/**
* サブシステムフィルタ SelectChangeイベントです。
* 未付与ロールテーブルより下記のロールを非表示
*  ・選択されたサブシステムに該当しないロール
*  ・既に付与ロールテーブルに存在するロール
*/
function oaex_subsystemFilter_onChange() {
	let assignRoleTable = document.getElementById("assign_role_table");
	let unAssignRoleTable = document.getElementById("unassign_role_table");
	let subsystemFilter = document.getElementById("subsystem_filter");

	// 全ロール表示
	for (let unAssignRoleTableRow of unAssignRoleTable.rows) {
		unAssignRoleTableRow.classList.remove(_RIGHT_TABLE_HIDDEN);
	}

	// 既に付与ロールテーブルに存在するロールを取得
	let assignRoleCodeArray = [];
	for (let assignRoleTableRow of assignRoleTable.rows) {
		if (assignRoleTableRow.cells[0].innerText != "") {
			assignRoleCodeArray.push(assignRoleTableRow.cells[0].innerText)
		}
	}

	// フィルタ対象かつ付与ロールテーブルに存在しないロールを表示
	if (subsystemFilter.value != "") {
		for (let unAssignRoleTableRow of unAssignRoleTable.rows) {
			if (unAssignRoleTableRow.cells[5].childNodes[0].value == subsystemFilter.value &&
					assignRoleCodeArray.includes(unAssignRoleTableRow.cells[0].innerText) == false) {
				unAssignRoleTableRow.classList.remove(_RIGHT_TABLE_HIDDEN);
			} else {
				unAssignRoleTableRow.classList.add(_RIGHT_TABLE_HIDDEN)
			}
		}
	}
}