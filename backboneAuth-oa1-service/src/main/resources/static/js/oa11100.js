/** Thymeleaf で起動時のみ実行 **/

/**
 * 画面Loadイベントです。
 */
function oaex_th_onload() {
	_isThymeleaf = true;
}

/**
 * 登録ボタンクリックイベントです。
 */
function oaex_outputExcelBtn_onClick() {
	return;
}

/**
 * 閉じるボタンクリックイベントです。
 */
function oaex_closeBtn_onClick() {
	oa_transferForm("oa00000");
}