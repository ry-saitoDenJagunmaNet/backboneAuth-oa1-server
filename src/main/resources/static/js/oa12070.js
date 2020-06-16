/**
 * 画面Loadイベントです。
 */
window.onload = function() {
	// 初期化処理
	oaex_initialize();
	// nput-field の初期化
	oa_initInputField();
}

/**
 * 初期化処理です。
 */
function oaex_initialize() {
	// 出力日開始デフォルトの日付を設定
	oa_setDatepickerValue("conditions_output_date_from", new Date());
	// 出力日終了デフォルトの日付を設定
	oa_setDatepickerValue("conditions_output_date_to", new Date());
	// 出力時間開始デフォルトの時間を設定
	oa_setTimepickerDefaultTime("conditions_output_time_from", "8:00");
	// 出力時間終了デフォルトの時間を設定
	oa_setTimepickerDefaultTime("conditions_output_time_to", "20:00");
}

/**
 * 検索ボタンクリックイベントです。
 */
function oaex_searchBtn_onClick() {
	if (!oaex_isValid()) {return;}

	// todo:テーブル＆ページネーションを表示
	let dt = new Date();
	let tbNode = document.getElementById("servicecall_trace_table");
	for (let rowNode of tbNode.rows) {
		rowNode.cells[0].innerText = oaex_getDateFormat(dt);
	}
	tbNode.style.visibility = "visible";
	document.getElementById("servicecall_trace_pagination").style.visibility = "visible";
}

/**
 * 日付をフォーマット(yyyy/mm/dd)して文字列にします。
 * @param {*} objDate 
 */
function oaex_getDateFormat(objDate) {
	let yyyy = objDate.getFullYear();
	let mm = ("00"+(objDate.getMonth()+1)).slice(-2);
	let dd = ("00"+(objDate.getDate())).slice(-2);
	return yyyy + "/" + mm + "/" + dd;
}

/**
 * 入力チェックを行います。
 */
function oaex_isValid() {
	let outputDate = document.getElementById("conditions_output_date_from");
	if (outputDate.value.length == 0) {
		outputDate.classList.add("invalid")
		oa_showAlert("出力日を入力して下さい");
		return false;
	}
	if (!oa_isDate(outputDate.value)) {
		outputDate.classList.add("invalid")
		oa_showAlert("出力日終了が正しくありません");
		return false;
	}
	outputDate = document.getElementById("conditions_output_date_to");
	if (outputDate.value.length > 0 && !oa_isDate(outputDate.value)) {
		outputDate.classList.add("invalid")
		oa_showAlert("出力日終了が正しくありません");
		return false;
	}
	return true;
}

/**
 * 閉じるボタンクリックイベントです。
 */
function oaex_closeBtn_onClick() {
	oa_transferForm("oa00000");
}
