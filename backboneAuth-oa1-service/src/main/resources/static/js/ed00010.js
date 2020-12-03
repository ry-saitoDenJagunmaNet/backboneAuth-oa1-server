/**
 * 画面Loadイベントです。
 */
window.onload = function() {
	// 初期化処理
	edex_initialize();
	// nput-field の初期化
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
	location.href = "./oa00000.html";
}



/**
 * ★モック状態操作用★
 */
function edex_mode_onChange() {
	let mode = oa_getRadioCheckedValue("mode");
	if (mode == "0") {
		// 初期認証
		for (let signIn of document.querySelectorAll(".edex_sign_in")) {
			signIn.style.display = "block";
		} 
		for (let reSignIn of document.querySelectorAll(".edex_re_sign_in")) {
			reSignIn.style.display = "none";
		} 
	} else if (mode == "1") {
		// 継続再認証
		for (let signIn of document.querySelectorAll(".edex_sign_in")) {
			signIn.style.display = "none";
		} 
		for (let reSignIn of document.querySelectorAll(".edex_re_sign_in")) {
			reSignIn.style.display = "block";
		} 
	}
}
