/**
 * 検索ボタンクリックイベントです。
 */
function oaex_searchBtn_onClick() {
	let traceTable = document.getElementById("trace_table");
	let tempoCodeArray = ["000", "000", "000", "000", "000", "000", "000", "", "", "", "000"];
	let tempoNameArray = ["本所", "本所", "本所", "本所", "本所", "本所", "本所", "", "", "", "本所"];
	let operaterCodeArray = ["OP101001", "OP101001", "OP101001", "OP101001", "OP101001", "OP101004", "OP101003", "AA987654", "BB543210", "CC123456", "OP101002"];
	let operaterNameArray = ["前橋　太郎", "前橋　太郎", "前橋　太郎", "前橋　太郎", "前橋　太郎", "前橋　四郎", "前橋　三郎", "不明", "不明", "不明", "前橋　次郎"];
	let datetimeArray = ["2020/04/01 12:16:17", "2020/04/01 12:13:14", "2020/04/01 12:10:00", "2020/04/01 08:10:11", "2020/04/01 08:10:10", "2020/04/01 08:09:11", "2020/04/01 08:08:11", "2020/04/01 08:07:13", "2020/04/01 08:07:12", "2020/04/01 08:07:11", "2020/04/01 08:00:00"];
	let operationArray = ["サインイン", "サインイン", "サインイン", "継続サインイン", "継続サインイン", "サインイン", "サインイン", "サインイン", "サインイン", "サインイン", "サインアウト"];
	let resultArray = ["成功", "拒否（有効期限範囲外）", "遮断（IPアドレス範囲外）", "成功", "失敗（パスワード誤り）", "成功", "拒否（アカウントロック中）", "失敗（存在しないオペレーター）", "失敗（存在しないオペレーター）", "失敗（存在しないオペレーター）", ""];
	let ipAddressArray = ["145.254.216.112", "145.254.216.112", "145.254.222.110", "145.254.216.112", "145.254.216.112", "145.254.216.114", "145.254.216.113", "145.254.216.222", "145.254.216.222", "145.254.216.222", "145.254.216.112"];

	let paginationRow = document.getElementById("pagination_row");


	// ページネイション非表示
	paginationRow.style.visibility = "hidden";

	//テンプレート行以外削除
	let rows = traceTable.rows.length;
	for(let i = 1; i < rows; i++) {
		traceTable.deleteRow(1);
	}

	// 検索結果イメージセット
	let i = 0;
	for (let tempoCode of tempoCodeArray) {
		
		// 新規行を追加
		let newRow = traceTable.insertRow();
		// template行を適用
		newRow.innerHTML = traceTable.rows[0].cloneNode(true).innerHTML;
		// 追加行にクリックイベントを追加
		newRow.addEventListener('click', function(event) {oa_setTableRowSelected(this);} );
		
		// データイメージ配列よりセット
		newRow.cells[0].innerText = tempoCode;
		newRow.cells[1].innerText = tempoNameArray[i];
		newRow.cells[2].innerText = operaterCodeArray[i];
		newRow.cells[3].innerText = operaterNameArray[i];
		newRow.cells[4].innerText = datetimeArray[i];
		newRow.cells[5].innerText = operationArray[i];
		newRow.cells[6].innerText = resultArray[i];
		newRow.cells[7].innerText = ipAddressArray[i];		
		i++;
	}

	// ページネイション表示
	paginationRow.style.visibility = "visible";
	
}

/**
 * 閉じるボタンクリックイベントです。
 */
function oaex_closeBtn_onClick() {
	oa_transferForm("oa00000");
}

