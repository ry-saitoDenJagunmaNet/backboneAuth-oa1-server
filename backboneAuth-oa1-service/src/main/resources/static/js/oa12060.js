/** Thymeleaf で起動時のみ実行 **/

/**
 * 画面Loadイベントです。
 */
function oaex_th_onload() {
	_isThymeleaf = true;

	// 年月のフォーマット変更
	let objDatepicker = document.getElementById("year_month");
	let yeraMonth = objDatepicker.value;
	objDatepicker.M_Datepicker.options.format = "yyyy/mm";

	// 年月初期値設定（systemdate）
	let day = new Date(yeraMonth);
	day.setDate(1);
	oa_setDatepickerValue("year_month", day);

	// カレンダーチェックボックスの表示／非表示切替
	oaex_filterCalendar();

	// input-field の初期化
	oa_initInputField()
}

/**
 * 年月の変更時処理です。
 */
function oaex_th_chegeMonth() {
	let param = "?ym="+document.getElementById("year_month").value;
	param = param+"&ct1="+(document.getElementById("calendar_type_filter_check1").checked? "1" : "0");
	param = param+"&ct2="+(document.getElementById("calendar_type_filter_check2").checked? "1" : "0");
	param = param+"&ct3="+(document.getElementById("calendar_type_filter_check3").checked? "1" : "0");
	param = param+"&wh="+document.getElementById("workingday_or_holiday").value;
	// サーバにリクエスト（カレンダー検索）
	window.location.href = "search" + param;

//	document.forms[0].action = "/search";
//	document.forms[0].method = "GET";
//	document.forms[0].submit();
}

/**
 * 適用ボタンクリックイベントです。
 */
function oaex_th_applyBtn_onClick() {
	let xhr = oa_th_sendFormData("entry", document.forms[0]);
	if (xhr == null) {return;}

	oa_showAlert("適用しました。");


//	let result = JSON.parse(xhr.responseText);
//	document.getElementById("calendar_table").innerHTML = result.calendarTable;
}


/** Thymeleafとモックで共用 **/
/**
 * 画面Loadイベントです。
 */
window.onload = function() {
	// 初期化処理
	oaex_initialize();
}

/**
 * 初期化処理です。
 */
function oaex_initialize() {
	// 年月のフォーマット変更
	let objDatepicker = document.getElementById("year_month");
	objDatepicker.M_Datepicker.options.format = "yyyy/mm";
	
	// 年月初期値設定（systemdate）
	let day = new Date();
	day.setDate(1);
	oa_setDatepickerValue("year_month", day);

	// カレンダー表示
	oaex_viewCalendar();

	// input-field の初期化
	oa_initInputField()
}

/**
 * 前月ボタンクリックイベントです。
 */
function oaex_lastMonthBtn_onClick() {
	oaex_chegeMonth(-1);
}

/**
 * 翌月ボタンクリックイベントです。
 */
function oaex_nextMonthBtn_onClick() {
	oaex_chegeMonth(1);
}

/**
 * 前月ボタン、翌月ボタンによる月変更
 * @param {int} moveMonth 加減算月（翌月：1、前月：-1）
 */
function oaex_chegeMonth(moveMonth) {
	// 年月コントロールから年月値を取得
	let year_month = document.getElementById("year_month");
	if (year_month.value.length == 0) {return;}
	let pickerdate = new Date(year_month.value+"/01");
	pickerdate.setMonth(pickerdate.getMonth() + moveMonth);

	// 年月コントロールに加減算後の値を設定
	oa_setDatepickerValue("year_month", pickerdate);

	if (_isThymeleaf) {
		oaex_th_chegeMonth();
		return;
	}

	// カレンダー表示
	oaex_viewCalendar();
}

/**
 * 年月の変更イベントです。
 */
function oaex_year_month_onChange() {
	// datepicker の更新を反映
	let objDatepicker = document.getElementById("year_month");
	oa_updateDatepicker(objDatepicker);

	if (_isThymeleaf) {
		oaex_th_chegeMonth();
		return;
	}

	// カレンダー表示
	oaex_viewCalendar();
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
		oa_transferForm("oa00000");
	}
}

/**
 * 適用ボタンクリックイベントです。
 */
function oaex_applyBtn_onClick() {
	if (_isThymeleaf) {
		oaex_th_applyBtn_onClick();
		return;
	}

	oa_showAlert("適用しました。");
}

/**
 * カレンダーチェックボックスの表示／非表示を切り替えます。
 */
function oaex_filterCalendar() {
	// 経済システム稼働カレンダー表示
	if (!document.getElementById("calendar_type_filter_check1").checked) {
		for(let node of document.getElementsByClassName("oaex_calendarcell_check1")) {
			node.classList.add("oaex_calendar_type_check_hidden");
		}
	} else {
		for(let node of document.getElementsByClassName("oaex_calendarcell_check1")) {
			// 稼働・休日の選択によりチェックボックスの表示／非表示切替
			oaex_filterWorkingdayOrHoliday(node);
		}
	}
	// 信用カレンダー表示
	if (!document.getElementById("calendar_type_filter_check2").checked) {
		for(let node of document.getElementsByClassName("oaex_calendarcell_check2")) {
			node.classList.add("oaex_calendar_type_check_hidden");
		}
	} else {
		for(let node of document.getElementsByClassName("oaex_calendarcell_check2")) {
			// 稼働・休日の選択によりチェックボックスの表示／非表示切替
			oaex_filterWorkingdayOrHoliday(node);
		}
	}
	// 広域物流カレンダー表示
	if (!document.getElementById("calendar_type_filter_check3").checked) {
		for(let node of document.getElementsByClassName("oaex_calendarcell_check3")) {
			node.classList.add("oaex_calendar_type_check_hidden");
		}
	} else {
		for(let node of document.getElementsByClassName("oaex_calendarcell_check3")) {
			// 稼働・休日の選択によりチェックボックスの表示／非表示切替
			oaex_filterWorkingdayOrHoliday(node);
		}
	}
}

/**
 * 稼働・休日の選択によりチェックボックスの表示／非表示を切り替えます。
 * @param {Object}} checkboxParentNode チェックボックスコントロールを配置する親nodeのlabelタグ
 */
function oaex_filterWorkingdayOrHoliday(checkboxParentNode) {
	let workingdayOrHoliday = document.getElementById("workingday_or_holiday").value;

	for (let childNode of checkboxParentNode.getElementsByTagName("input")) {
		if (workingdayOrHoliday == "1" && !childNode.checked) {
			checkboxParentNode.classList.add("oaex_calendar_type_check_hidden");
		} else if (workingdayOrHoliday == "2" && childNode.checked) {
			checkboxParentNode.classList.add("oaex_calendar_type_check_hidden");
		} else {
			checkboxParentNode.classList.remove("oaex_calendar_type_check_hidden");
		}
	}
}

/**
 * カレンダーを表示します。
 */
function oaex_viewCalendar() {
	let year_month = document.getElementById("year_month");
	if (year_month.value.length == 0) {
		document.getElementById("calendar_table").innerHTML = "";
		return;
	}

	let pickerdate = new Date(year_month.value+"/01");
	// 月初日
	let startday = new Date(pickerdate.getFullYear(), pickerdate.getMonth(), 1);
	// 月初日の曜日
	let startdayW = startday.getDay();
	// 月末日
	let lastday = (new Date(pickerdate.getFullYear(), pickerdate.getMonth() + 1, 0)).getDate();

	let day = 1;
	let weekrow = 1;

	let html = '';
	html = html + '<table id="calendar_table">';

	html = html + '	<thead>';
	html = html + '	<tr>';
	html = html + '	<th colspan="2" class="center-align red-text ">日</th>';
	html = html + '	<th colspan="2" class="center-align">月</th>';
	html = html + '	<th colspan="2" class="center-align">火</th>';
	html = html + '	<th colspan="2" class="center-align">水</th>';
	html = html + '	<th colspan="2" class="center-align">木</th>';
	html = html + '	<th colspan="2" class="center-align">金</th>';
	html = html + '	<th colspan="2" class="center-align blue-text">土</th>';
	html = html + '	</tr>';
	html = html + '	</thead>';

	html = html + '	<tbody>';
	
	while(day <= lastday) {
		html = html + '<tr>';
		for(let i = 0; i < 7; i++) {
			if (weekrow == 1) {
				// 第１週（１日まで空セル設定）
				if (startdayW <= i) {
					// カレンダーセル（一日分）のhtml作成
					html = html + oaex_getCalendarCell(day, i);
					day++;
				} else {
					html = html + '<td colspan="2"></td>';
				}
			} else {
				if (lastday >= day) {
					// カレンダーセル（一日分）のhtml作成
					html = html + oaex_getCalendarCell(day, i);
					day++;
				} else {
					html = html + '<td colspan="2"></td>';
				}
			}
		}
		html = html + '</tr>';
		weekrow++;
	}
	html = html + '	</tbody>';
	html = html + '</table>';

	document.getElementById("calendar_table").innerHTML = html.replace(/\t/g, "");
	// カレンダーチェックボックスの表示／非表示切替
	oaex_filterCalendar();
}

/**
 * カレンダーセル（一日分）のhtmlを取得（作成）します
 * @param {int} day 対象日
 * @param {int} weekday 対象の曜日
 * @returns {String} カレンダーセル（一日分）のhtml
 */
function oaex_getCalendarCell(day, weekday) {
	let classids = "";
	// 日曜日の日付の色設定
	if (weekday == 0) {classids = classids + "red-text";}
	// 土曜日の日付の色設定
	if (weekday == 6) {classids = classids + "blue-text";}

	let checked = '';
	let html = '';

	html = html + '<td class="' + classids + ' oaex_calendarcell_left">';
	html = html + day;
	html = html + '</td>';

	html = html + '<td class="oaex_calendarcell_right">';
	html = html + '<div>';
	html = html + '	<label class="oaex_calendarcell_check1">';
	// todo:モック対応　土/日以外をチェック状態
	if (weekday >= 1 && weekday <= 5) {
		checked = ' checked="checked"'
	} else {
		checked = '';
	}
	html = html + '		<input type="checkbox" id="calendar_type_check1[' + day +  ']"' + checked + '></input>';
	html = html + '		<span>経済</span>';
	html = html + '	</label>';
	html = html + '</div>';
	html = html + '<div>';
	html = html + '	<label class="oaex_calendarcell_check2">';
	// todo:モック対応　日以外をチェック状態
	if (weekday >= 1) {
		checked = ' checked="checked"'
	} else {
		checked = '';
	}
	html = html + '		<input type="checkbox" id="calendar_type_check2[' + day +  ']"' + checked + '></input>';
	html = html + '		<span>信用</span>';
	html = html + '	</label>';
	html = html + '</div>';
	html = html + '<div>';
	html = html + '	<label class="oaex_calendarcell_check3">';
	// todo:モック対応　日以外をチェック状態
	if (weekday >= 1) {
		checked = ' checked="checked"'
	} else {
		checked = '';
	}
	html = html + '		<input type="checkbox" id="calendar_type_check3[' + day +  ']"' + checked + '></input>';
	html = html + '		<span>広域物流</span>';
	html = html + '	</label>';
	html = html + '</div>';

	html = html + '</td>';

	return html;
}
