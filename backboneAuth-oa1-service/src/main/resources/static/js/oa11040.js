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
function oaex_moveAdditionBtn_onClick() {
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
			// コード、名称を追加行に設定
			newRow.cells[0].innerText = tableRow.cells[0].innerText;
			newRow.cells[1].innerText = tableRow.cells[1].innerText;
			newRow.cells[2].innerHTML = "<div class='input-field'>" +
										"	<input type='text' class='datepicker' name='assignRoleTableVoList[" + i + "].validThruStartDate}'></input>" +
										"</div>" +
										"<span>～</span>" +
										"<div class='input-field'>" +
										"	<input type='text' class='datepicker' name='assignRoleTableVoList[" + i + "].validThruEndDate}'></input>" +
										"</div>";
			newRow.cells[3].innerHTML = "<input type='hidden' name='assignRoleTableVoList[" + i + "].roleCode' value='" + tableRow.cells[0].innerText + "'></input>";
			newRow.cells[4].innerHTML = "<input type='hidden' name='assignRoleTableVoList[" + i + "].roleName' value='" + tableRow.cells[1].innerText + "'></input>";
			newRow.cells[5].innerHTML = "<input type='hidden' name='assignRoleTableVoList[" + i + "].isModifiable' value='" + tableRow.cells[2].innerText + "'></input>";
			// 選択行非表示
			unAssignRoleTable.rows[i].classList.add(_RIGHT_TABLE_HIDDEN)
			// 有効行を選択状態
			oaex_setSelectRow(unAssignRoleTable, i);
			// datepicker の初期化
			oa_initDatepicker();
			// input-field の初期化
			oa_initInputField();
			return;
		}
	}
}

/**
 * （▶）移動ボタン Clickイベントです
 * 付与ロールテーブルから削除
 */
function oaex_moveRemovalBtn_onClick() {
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
					oa_setDataTableRowSelected(assignRoleTable.rows[i]);
				} else {
					oa_setDataTableRowSelected(assignRoleTable.rows[i-1]);
				}

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

function oaex_assignRoleTableRow_onClick() {


}
