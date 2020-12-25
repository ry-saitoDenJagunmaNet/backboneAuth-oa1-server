/* 未付与ロールテーブルの非表示状態CSSクラスID */
const _RIGHT_TABLE_HIDDEN = "oaex_unallocate_role_table_hidden_row";

/**
 * 画面 Loadイベントです
 */
function oaex_th_onload() {
	_isThymeleaf = true;

	// 未付与ロールテーブルを初期非表示
	oaex_initHiddenUnallocateRoleList();
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
	let allocateRoleTable = document.getElementById("allocate_role_table");
	let unallocateRoleTable = document.getElementById("unallocate_role_table");
	for(let i = 0; i < unallocateRoleTable.rows.length; i++){
		let tableRow = unallocateRoleTable.rows[i];
		// 選択行を定義したCSSクラスで判定
		if (tableRow.classList.contains(_TABLE_ROW_SELECTED1)
				&& tableRow.classList.contains(_TABLE_ROW_SELECTED2)){
			// 新規行を追加
			let newRow = allocateRoleTable.insertRow();
			// template行を適用
			newRow.innerHTML = allocateRoleTable.rows[0].cloneNode(true).innerHTML;
			// 追加行にClickイベントを追加
			newRow.addEventListener('click', function(event) {
				oa_setTableRowSelected(this);
			});
			// datepicker の初期化
			oa_initDatepicker();
		
			// コード、名称を追加行に設定
			newRow.cells[0].innerText = tableRow.cells[0].innerText;
			newRow.cells[1].innerText = tableRow.cells[1].innerText;
			// 選択行非表示
			unallocateRoleTable.rows[i].classList.add(_RIGHT_TABLE_HIDDEN)
			// 有効行を選択状態
			oaex_setSelectRow(unallocateRoleTable, i);
			return; 
		}
	}
}

/**
 * （▶）移動ボタン Clickイベントです
 * 付与ロールテーブルから削除
 */
function oaex_moveRemovalBtn_onClick() {
	let allocateRoleTable = document.getElementById("allocate_role_table");
	let unallocateRoleTable = document.getElementById("unallocate_role_table");
	for(let i = 1; i < allocateRoleTable.rows.length; i++) {
		let tableRow = allocateRoleTable.rows[i];
		// 選択行を定義したCSSクラスで判定
		for(let j = 0; j < tableRow.classList.length; j++) {
			let rowclass = tableRow.classList[j];
			// 選択行
			if (tableRow.classList.contains(_TABLE_ROW_SELECTED1)
					&& tableRow.classList.contains(_TABLE_ROW_SELECTED2)){
				for(let k = 0; k < unallocateRoleTable.rows.length; k++) {
					// 選択行と同じコードの未付与ロールを再表示
					if (unallocateRoleTable.rows[k].cells[0].innerText == tableRow.cells[0].innerText) {
						unallocateRoleTable.rows[k].classList.remove(_RIGHT_TABLE_HIDDEN);
					}
				}
				//選択行削除
				allocateRoleTable.deleteRow(i);
				// 有効行を選択状態
				if (i <= allocateRoleTable.rows.length - 1) {
					oa_setDataTableRowSelected(allocateRoleTable.rows[i]);
				} else {
					oa_setDataTableRowSelected(allocateRoleTable.rows[i-1]);
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
function oaex_initHiddenUnallocateRoleList() {
	let allocateRoleTable = document.getElementById("allocate_role_table");
	let unallocateRoleTable = document.getElementById("unallocate_role_table");
	for (let allocateRoleTableRow of allocateRoleTable.rows) {
		for (let unallocateRoleTableRow of unallocateRoleTable.rows) {
			if (unallocateRoleTableRow.cells[0].innerText == allocateRoleTableRow.cells[0].innerText ) {
				unallocateRoleTableRow.classList.add(_RIGHT_TABLE_HIDDEN)
			}
		}
	}
}
