/**
 * 検索ボタンクリックイベントです。
 */
function oaex_searchBtn_onClick() {
	let currentTable = document.getElementById("current_table");
	let tempoCodeArray = ["000", "000", "000", "000", "000", "000", "000", "056"];
	let tempoNameArray = ["本所", "本所", "本所", "本所", "本所", "本所", "本所", "いせさき営農センター北部資材館"];
	let operaterCodeArray = ["OP101001", "OP101002", "OP101003", "OP101004", "OP101005", "OP101006", "OP101007", "tp052001"];
	let operaterNameArray = ["前橋　太郎", "前橋　次郎", "前橋　三郎", "前橋　四郎", "前橋　五郎", "前橋　六郎", "前橋　七郎", "購買商品スキャンチェック用"];
	let occurredDatetimeArray = ["2020/04/01 12:16:17", "2020/04/01 12:13:14", "2020/04/01 12:10:00", "2020/04/01 08:10:11", "2020/04/01 08:10:10", "2020/04/01 08:09:11", "2020/04/01 08:08:11", "2020/09/09 09:09:09"];
	let currentStatusArray = [0, 1, 1, 0, 1, 1, 0, 0];
	let paginationRow = document.getElementById("pagination_row");


	// ページネイション非表示
	paginationRow.style.visibility = "hidden";

	//テンプレート行以外削除
	let rows = currentTable.rows.length;
	for(let i = 1; i < rows; i++) {
		currentTable.deleteRow(1);
	}

	// 検索結果イメージセット
	let i = 0;
	for (let tempoCode of tempoCodeArray) {
		
		// 新規行を追加
		let newRow = currentTable.insertRow();
		// template行を適用
		newRow.innerHTML = currentTable.rows[0].cloneNode(true).innerHTML;
		// 追加行にクリックイベントを追加
		newRow.addEventListener('click', function(event) {oa_setTableRowSelected(this);} );
		newRow.addEventListener('click', function(event) {oaex_currentTableRow_onClick();} );
		
		// データイメージ配列よりセット
		newRow.cells[0].innerText = tempoCode;
		newRow.cells[1].innerText = tempoNameArray[i];
		newRow.cells[2].innerText = operaterCodeArray[i];
		newRow.cells[3].innerText = operaterNameArray[i];
		newRow.cells[4].innerText = occurredDatetimeArray[i];
		if(currentStatusArray[i] == 0){
			newRow.cells[5].childNodes[0].className = "oaex_lock_status_unlock"
			newRow.cells[6].childNodes[1].style.visibility = "hidden";
		} else {
			newRow.cells[5].childNodes[0].className = "oaex_lock_status_lock"
			newRow.cells[6].childNodes[1].style.visibility = "visible";
		}
		
		i++;
	}

	// ページネイション表示
	paginationRow.style.visibility = "visible";
	
}

/**
 * 全アンロックボタンクリックイベントです。
 */
function oaex_unlockAllBtn_onClick() {
	let currentTable = document.getElementById("current_table");

	for (let currentTableRow of currentTable.rows) {
		currentTableRow.cells[6].childNodes[1].childNodes[1].childNodes[1].checked = "";
	}
}

/**
 * 現在状態テーブル行クリックイベントです。
 */
function oaex_currentTableRow_onClick() {
	let currentTable = document.getElementById("current_table");
	let historyTable = document.getElementById("history_table");
	let occurredDatetimeArray = ["2020/04/01 12:16:17", "2020/04/01 12:13:14", "2020/04/01 12:10:00", "2020/04/01 08:10:11", "2020/04/01 08:10:10", "2020/04/01 08:09:11", "2020/04/01 08:08:11"]

	//テンプレート行以外削除
	let rows = historyTable.rows.length;
	for(let i = 1; i < rows; i++) {
		historyTable.deleteRow(1);
	}

	// 現在状態テーブルのカレント行を取得
	let currentRow = currentTable.rows[oa_getTableSelectedRowIndex(currentTable)];
 
	// 検索結果イメージセット
	let i = 0;
	for (let occurredDatetime of occurredDatetimeArray) {
		
		// 現在状態ロックの場合１行目セットをスキップ
		if (!(currentRow.cells[5].childNodes[0].className == "oaex_lock_status_lock" && i == 0)) {

			// 新規行を追加
			let newRow = historyTable.insertRow();
			// template行を適用
			newRow.innerHTML = historyTable.rows[0].cloneNode(true).innerHTML;
			// 追加行にクリックイベントを追加
			newRow.addEventListener('click', function(event) {oa_setTableRowSelected(this);} );

			// データイメージ配列よりセット
			newRow.cells[0].innerText = occurredDatetime;
			if (i % 2 == 0) {
				newRow.cells[1].childNodes[0].className = "oaex_lock_status_unlock"
				newRow.cells[2].innerText = "アンロック";
			} else {
				newRow.cells[1].childNodes[0].className = "oaex_lock_status_lock"
				newRow.cells[2].innerText = "ロック";
			}
		}

		i++;
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
	oa_transferForm("oa00000");
}
