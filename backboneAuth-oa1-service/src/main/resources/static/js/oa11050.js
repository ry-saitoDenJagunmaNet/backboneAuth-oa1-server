/**
* サブシステムフィルタ SelectChangeイベントです。
* 未付与ロールテーブルより下記のロールを非表示
*  ・選択されたサブシステムに該当しないロール
*  ・既に付与ロールテーブルに存在するロール
*/
function oaex_subsystemFilter_onChange() {
	let allocateRoleTable = document.getElementById("allocate_role_table");
	let unallocateRoleTable = document.getElementById("unallocate_role_table");
	let subsystemFilter = document.getElementById("subsystem_filter");

	// 全ロール表示
	for (let unallocateRoleTableRow of unallocateRoleTable.rows) {
		unallocateRoleTableRow.classList.remove(_RIGHT_TABLE_HIDDEN);
	}

	// 既に付与ロールテーブルに存在するロールを取得
	let allocateRoleCodeArray = [];
	for (let allocateRoleTableRow of allocateRoleTable.rows) {
		if (allocateRoleTableRow.cells[0].innerText != "") {
			allocateRoleCodeArray.push(allocateRoleTableRow.cells[0].innerText)
		}
	}

	// フィルタ対象かつ付与ロールテーブルに存在しないロールを表示
	if (subsystemFilter.value != "") {
		for (let unallocateRoleTableRow of unallocateRoleTable.rows) {
			if (unallocateRoleTableRow.cells[0].innerText.substring(0,2) == subsystemFilter.value &&
					allocateRoleCodeArray.includes(unallocateRoleTableRow.cells[0].innerText) == false) {
				unallocateRoleTableRow.classList.remove(_RIGHT_TABLE_HIDDEN);
			} else {
				unallocateRoleTableRow.classList.add(_RIGHT_TABLE_HIDDEN)
			}
		}
	}
}