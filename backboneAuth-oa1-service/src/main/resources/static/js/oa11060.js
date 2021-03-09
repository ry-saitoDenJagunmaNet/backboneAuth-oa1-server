/**
 * 追加ボタンクリックイベントです。
 * @param {String} pgId 画面ID
 */
function oaex_additionBtn_onClick(pgId) {
	let roleTable = document.getElementById("role_table");

	// 行を追加
	let newRow = roleTable.insertRow();	
	// template行を適用
	newRow.innerHTML = roleTable.rows[0].cloneNode(true).innerHTML;
	// 追加行にクリックイベントを追加
	newRow.addEventListener('click', function(event) {oa_setTableRowSelected(this);} );
	// datepicker の初期化
	oa_initDatepicker();

	// 変更事由をサンプルセット
	let changeCause = document.getElementById("change_cause");
	if (pgId == "oa11060"){
		changeCause.value = "JA管理者を一括割当て";
	} else {
		changeCause.value="（青果）管理者（仕切実績修正）を一括割当て";
	}

	// input-field の初期化
	oa_initInputField();	
}

/**
 * 削除ボタンクリックイベントです。
 */
function oaex_removalBtn_onClick() {
	let roleTable = document.getElementById("role_table");

	// カレント行を取得
	let currentRow = oa_getTableSelectedRowIndex(roleTable)

	//行削除
	if(currentRow > 0){
		roleTable.deleteRow(currentRow);
	}
}

/**
 * 検索ボタンクリックイベントです。
 */
function oaex_searchBtn_onClick() {
	let roleTable = document.getElementById("role_table");
	let tempoCodeArray = ["000", "000", "000", "040", "054", "056", "084", "088"];
	let tempoNameArray = ["本所", "本所", "本所", "西部営農センター（里東倉庫）", "東部営農経済センター坂上営業所", "いせさき営農センター北部資材館", "グル米四季菜館　群馬八幡駅前店", "いせさき農産物集出荷貯蔵施設"];
	let operaterCodeArray = ["OP101001", "OP101002", "ZZ000003", "ZZ000004", "ZZ000005", "tp052001", "yu069005", "yu003007"];
	let operaterNameArray = ["前橋　太郎", "前橋　次郎", "前橋　三郎", "前橋　四郎", "前橋　五郎", "購買商品スキャンチェック用", "西部営農センター(青果･花卉)", "営農経済課（野菜・花卉）"];

	//テンプレート行以外削除
	let rows = roleTable.rows.length;
	for(let i = 1; i < rows; i++) {
		roleTable.deleteRow(1);
	}

	// 検索結果イメージセット
	let i = 0;
	for (let tempoCode of tempoCodeArray) {
		
		// 新規行を追加
		let newRow = roleTable.insertRow();
		// template行を適用
		newRow.innerHTML = roleTable.rows[0].cloneNode(true).innerHTML;
		// 追加行にクリックイベントを追加
		newRow.addEventListener('click', function(event) {oa_setTableRowSelected(this);} );
		// datepicker の初期化
		oa_initDatepicker();
		
		// データイメージ配列よりセット
		newRow.cells[0].innerText = tempoCode;
		newRow.cells[1].innerText = tempoNameArray[i];
		newRow.cells[2].childNodes[1].childNodes[1].value = operaterCodeArray[i];
		newRow.cells[3].innerText = operaterNameArray[i];
		newRow.cells[5].childNodes[1].childNodes[3].value = "2020/04/01";
		newRow.cells[5].childNodes[5].childNodes[3].value = "9999/12/31";
		
		i++;
	}

	// input-field の初期化
	oa_initInputField();	
}
