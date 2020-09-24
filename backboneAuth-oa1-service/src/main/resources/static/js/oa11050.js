/* 未付与ロールテーブルの非表示状態CSSクラスID */
const _RIGHT_TABLE_HIDDEN = "oaex_not_granted_role_table_hidden_row";

/**
 * 画面Loadイベントです。
 */
window.onload = function() {
	// 未付与ロールテーブルを初期非表示
	oaex_initHiddenNotGrantedRoleList();
}

/**
 * （◀）移動ボタンクリックイベントです。
 * 付与ロールテーブルへ追加
 */
function oaex_moveAdditionBtn_onClick() {
	let grantedRoleTable = document.getElementById("granted_role_table");
	let notGrantedRoleTable = document.getElementById("not_granted_role_table");
	for(let i = 0; i < notGrantedRoleTable.rows.length; i++){
		let tableRow = notGrantedRoleTable.rows[i];
		// 選択行を定義したCSSクラスで判定
		if (tableRow.classList.contains(_TABLE_ROW_SELECTED1)
				&& tableRow.classList.contains(_TABLE_ROW_SELECTED2)){
			// 新規行を追加
			let newRow = grantedRoleTable.insertRow();
			// template行を適用
			newRow.innerHTML = grantedRoleTable.rows[0].cloneNode(true).innerHTML;
			// 追加行にクリックイベントを追加
			newRow.addEventListener('click', function(event) {
				oa_setTableRowSelected(this);
			});
			// datepicker の初期化
			oa_initDatepicker();
		
			// コード、名称を追加行に設定
			newRow.cells[0].innerText = tableRow.cells[0].innerText;
			newRow.cells[1].innerText = tableRow.cells[1].innerText;
			// 選択行非表示
			notGrantedRoleTable.rows[i].classList.add(_RIGHT_TABLE_HIDDEN)
			// 有効行を選択状態
			oaex_setSelectRow(notGrantedRoleTable, i);
			return; 
		}
	}
}

/**
 * （▶）移動ボタンクリックイベントです。
 * 付与ロールテーブルから削除
 */
function oaex_moveRemovalBtn_onClick() {
	let grantedRoleTable = document.getElementById("granted_role_table");
	let notGrantedRoleTable = document.getElementById("not_granted_role_table");
	for(let i = 1; i < grantedRoleTable.rows.length; i++) {
		let tableRow = grantedRoleTable.rows[i];
		// 選択行を定義したCSSクラスで判定
		for(let j = 0; j < tableRow.classList.length; j++) {
			let rowclass = tableRow.classList[j];
			// 選択行
			if (tableRow.classList.contains(_TABLE_ROW_SELECTED1)
					&& tableRow.classList.contains(_TABLE_ROW_SELECTED2)){
				for(let k = 0; k < notGrantedRoleTable.rows.length; k++) {
					// 選択行と同じコードの未付与ロールを再表示
					if (notGrantedRoleTable.rows[k].cells[0].innerText == tableRow.cells[0].innerText) {
						notGrantedRoleTable.rows[k].classList.remove(_RIGHT_TABLE_HIDDEN);
					}
				}
				//選択行削除
				grantedRoleTable.deleteRow(i);
				// 有効行を選択状態
				if (i <= grantedRoleTable.rows.length - 1) {
					oa_setDataTableRowSelected(grantedRoleTable.rows[i]);
				} else {
					oa_setDataTableRowSelected(grantedRoleTable.rows[i-1]);
				}

				return; 
			}
		}
	}
}

/**
 * 適用ボタンクリックイベントです。
 */
function oaex_applyBtn_onClick() {
	oa_showAlert("適用しました。");
}

/**
 * 閉じるボタンクリックイベントです。
 */
function oaex_closeBtn_onClick() {
	oa_showConfirm("変更内容が適用されていません。\n画面を閉じてよろしいですか？", oaex_closeBtn_onClick_confirmReturn);
}

/**
 * 閉じるボタンで表示したダイアログを閉じるイベントです。
 * @param {Boolean} rtn true
 */
function oaex_closeBtn_onClick_confirmReturn(rtn) {
	if (rtn) {
		oa_transferForm("oa11010");
	}
}

/**
 * 表示有効行を選択状態にします。
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
 * 未付与ロール一リストの付与したロール行を非表示にします。
 * 初期表示時に付与したサブシステムロールを未付与ロール一覧から非表示
 */
function oaex_initHiddenNotGrantedRoleList() {
	let grantedRoleTable = document.getElementById("granted_role_table");
	let notGrantedRoleTable = document.getElementById("not_granted_role_table");
	for (let grantedRoleTableRow of grantedRoleTable.rows) {
		for (let notGrantedRoleTableRow of notGrantedRoleTable.rows) {
			if (notGrantedRoleTableRow.cells[0].innerText == grantedRoleTableRow.cells[0].innerText ) {
				notGrantedRoleTableRow.classList.add(_RIGHT_TABLE_HIDDEN)
			}
		}
	}
}