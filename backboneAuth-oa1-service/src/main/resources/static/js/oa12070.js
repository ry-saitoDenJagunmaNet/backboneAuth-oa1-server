/**
 * 追加ボタンクリックイベントです。
 */
function oaex_additionBtn_onClick() {
	let ipAddressRangeTable = document.getElementById("ip_address_range_table");

	// 行を追加
	let newRow = ipAddressRangeTable.insertRow();	
	// template行を適用
	newRow.innerHTML = ipAddressRangeTable.rows[0].cloneNode(true).innerHTML;
	// 追加行にクリックイベントを追加
	newRow.addEventListener('click', function(event) {oa_setTableRowSelected(this);} );
	// datepicker の初期化
	oa_initDatepicker();

	// input-field の初期化
	oa_initInputField();	
}

/**
 * 削除ボタンクリックイベントです。
 */
function oaex_removalBtn_onClick() {
	let ipAddressRangeTable = document.getElementById("ip_address_range_table");

	// カレント行を取得
	let currentRow = oa_getTableSelectedRowIndex(ipAddressRangeTable)

	//行削除
	if(currentRow > 0){
		ipAddressRangeTable.deleteRow(currentRow);
	}
}

/**
 * JAの変更イベントです。
 */
function oaex_ja_onChange() {
	let ipAddressRangeTable = document.getElementById("ip_address_range_table");
	let ipAddressRangeArray = ["145.254.2", "145.254.02", "145.254.002", "145.254.216.0", "145.254.216.100", "145.254.216.101", "145.254.216.11", "145.254.216.2"];
	let ja = document.getElementById("ja");

	//テンプレート行以外削除
	let rows = ipAddressRangeTable.rows.length;
	for(let i = 1; i < rows; i++) {
		ipAddressRangeTable.deleteRow(1);
	}

	// テーブルイメージセット
	let i = 0;
	for (let ipAddressRange of ipAddressRangeArray) {

		// 新規行を追加
		let newRow = ipAddressRangeTable.insertRow();
		// template行を適用
		newRow.innerHTML = ipAddressRangeTable.rows[0].cloneNode(true).innerHTML;
		// 追加行にクリックイベントを追加
		newRow.addEventListener('click', function(event) {oa_setTableRowSelected(this);} );
		// datepicker の初期化
		oa_initDatepicker();

		// データイメージ配列よりセット
		newRow.cells[0].childNodes[1].childNodes[1].value = ipAddressRange;
		newRow.cells[1].childNodes[1].childNodes[3].value = "2020/04/01";
		newRow.cells[1].childNodes[5].childNodes[3].value = "9999/12/31";

		i++;

		if (parseInt(ja.value) % 2 != 0 && i == 5) {
			break;
		}
	}

	// input-field の初期化
	oa_initInputField();
}
