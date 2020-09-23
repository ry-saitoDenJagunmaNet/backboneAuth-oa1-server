/**
* サブシステムフィルタセレクトチェンジイベントです。
* 未付与ロールテーブルより下記のロールを非表示
*  ・選択されたサブシステムに該当しないロール
*  ・既に付与ロールテーブルに存在するロール
*/
function oaex_subsystemFilter_onChange() {
	let grantedRoleTable = document.getElementById("granted_role_table");
	let notGrantedRoleTable = document.getElementById("not_granted_role_table");
	let subsystemFilter = document.getElementById("subsystem_filter");

	// 全ロール表示
	for (let notGrantedRoleTableRow of notGrantedRoleTable.rows) {
		notGrantedRoleTableRow.classList.remove(_RIGHT_TABLE_HIDDEN);
	}

	// 既に付与ロールテーブルに存在するロールを取得
	let grantedRoleCodeArray = [];
	for (let grantedRoleTableRow of grantedRoleTable.rows) {
		if (grantedRoleTableRow.cells[0].innerText != "") {
			grantedRoleCodeArray.push(grantedRoleTableRow.cells[0].innerText)
		}
	}

	// フィルタ対象かつ付与ロールテーブルに存在しないロールを表示
	if (subsystemFilter.value != "") {
		for (let notGrantedRoleTableRow of notGrantedRoleTable.rows) {
			if (notGrantedRoleTableRow.cells[0].innerText.substring(0,2) == subsystemFilter.value &&
				grantedRoleCodeArray.includes(notGrantedRoleTableRow.cells[0].innerText) == false) {
					notGrantedRoleTableRow.classList.remove(_RIGHT_TABLE_HIDDEN);
			} else {
				notGrantedRoleTableRow.classList.add(_RIGHT_TABLE_HIDDEN)
			}
		}
	}
}