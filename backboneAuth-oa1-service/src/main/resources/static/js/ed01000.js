/**
 * 画面 Loadイベントです。
 */
function edex_th_onload() {
	_isThymeleaf = true;

	let displayAtMockupRow = document.getElementById("display_at_mockup_row");
	displayAtMockupRow.style.display = "none";

	// 初期化処理
	edex_initialize();
	// nput-field の初期化
	oa_initInputField();
}
window.onload = function() {
	// 初期化処理
	edex_initialize();
	// input-field の初期化
	oa_initInputField();
}

/**
 * 初期化処理です。
 */
function edex_initialize() {
	edex_mode_onChange();
}

/**
 * サインインボタンクリックイベントです。
 */
function edex_signInBtn_onClick() {
	if (_isThymeleaf) {
		edex_th_signInBtn_onClick();
		return;
	}
	location.href = "./oa00000.html";
}
function edex_th_signInBtn_onClick() {
	document.forms[0].action = "signIn";
	document.forms[0].method = "POST";
	document.forms[0].submit();
}


/**
 * ★モック状態操作用★
 */
function edex_mode_onChange() {
	let mode = oa_getRadioCheckedValue("mode");
	if (mode == "1") {
		// 初期認証
		for (let signIn of document.querySelectorAll(".edex_sign_in")) {
			signIn.style.display = "block";
		} 
		for (let reSignIn of document.querySelectorAll(".edex_re_sign_in")) {
			reSignIn.style.display = "none";
		} 
	} else if (mode == "2") {
		// 継続再認証
		for (let signIn of document.querySelectorAll(".edex_sign_in")) {
			signIn.style.display = "none";
		} 
		for (let reSignIn of document.querySelectorAll(".edex_re_sign_in")) {
			reSignIn.style.display = "block";
		} 
	}
}
