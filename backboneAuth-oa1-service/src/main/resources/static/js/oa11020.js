/**
 * 画面 Loadイベントです
 */
function oaex_th_onload() {
	_isThymeleaf = true;
}

/**
 * 閉じるボタン Clickイベントです
 */
function oaex_closeBtn_onClick() {
	oa_transferForm("oa00000");
}

/**
 * 登録ボタン Clickイベントです
 */
function oaex_entryBtn_onClick() {
	if (_isThymeleaf) {
		oaex_th_entryBtn_onClick();
		return;
	}
	oa_showAlert("登録しました。");
}
function oaex_th_entryBtn_onClick() {
	document.forms[0].action = "entry";
	document.forms[0].method = "POST";
	document.forms[0].submit();
}
