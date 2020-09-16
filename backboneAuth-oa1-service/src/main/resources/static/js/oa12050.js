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
	// timepickerダイアログのデフォルト時刻を設定
	document.querySelectorAll('.timepicker').forEach((el) => {
		if (el.id.substr(0, "start_time_".length) == "start_time_") {
			oa_setTimepickerDefaultTime(el.id, "8:00")
		} else if (el.id.substr(0, "end_time_".length) == "end_time_") {
			oa_setTimepickerDefaultTime(el.id, "20:00")
		}
	});
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
		oa_transferForm("oa12040");
	}
}

/**
 * 適用ボタンクリックイベントです。
 */
function oaex_applyBtn_onClick() {
	oa_showAlert("適用しました。");
}
