/**
 * 画面 Loadイベントです。
 */
function oaex_th_onload() {
	_isThymeleaf = true;

	let displayAtMockupRow = document.getElementById("display_at_mockup_row");
	displayAtMockupRow.style.display = "none";

	oaex_mode_onChange();
}
window.onload = function() {
	oaex_mode_onChange();
}

/**
 * →ボタン クリックイベントです。
 */
function oaex_arrowBtn_onClick() {
	if (_isThymeleaf) {
		oaex_th_arrowBtn_onClick();
		return;
	}
	oa_showAlert("登録しました。");
}
function oaex_th_arrowBtn_onClick() {
	document.forms[0].action = "save";
	document.forms[0].method = "POST";
	document.forms[0].submit();
}

/**
 * モード 変更時のイベントです。
 */
function oaex_mode_onChange() {
	let modeArray = document.getElementsByName("mode");
	let modeTitle = document.getElementById("mode_title");
	let passwordOldRow = document.getElementById("password_old_row");
	let passwordNewLabel = document.getElementById("password_new_label");
	
	for (let mode of modeArray) {
		if (mode.checked) {
			switch (mode.value) {
			case "Initial":
				modeTitle.innerText = "初期パスワードの入力"
				passwordOldRow.style.display = "none";
				passwordNewLabel.innerText = "パスワード"
				break;
			case "Reset":
				modeTitle.innerText = "パスワードのリセット"
				passwordOldRow.style.display = "none";
				passwordNewLabel.innerText = "パスワード"
				break;
			case "Change":
				modeTitle.innerText = "パスワードの変更"
				passwordOldRow.style.display = "block";
				passwordNewLabel.innerText = "新しいパスワード"
				break;
			default:
				break;
			}
			break;
		}
	}
}
