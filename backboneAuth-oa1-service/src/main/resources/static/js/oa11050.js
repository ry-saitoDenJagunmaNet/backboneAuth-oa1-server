/**
* サブシステムフィルタ SelectChangeイベントです。
* 未付与ロールテーブルより下記のロールを非表示
*  ・選択されたサブシステムに該当しないロール
*  ・既に付与ロールテーブルに存在するロール
*/
function oaex_subsystemFilter_onChange() {
	let assignRoleTable = document.getElementById("allocate_role_table");
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
			if (unAssignRoleTableRow.cells[0].innerText.substring(0,2) == subsystemFilter.value &&
					assignRoleCodeArray.includes(unAssignRoleTableRow.cells[0].innerText) == false) {
				unAssignRoleTableRow.classList.remove(_RIGHT_TABLE_HIDDEN);
			} else {
				unAssignRoleTableRow.classList.add(_RIGHT_TABLE_HIDDEN)
			}
		}
	}
}