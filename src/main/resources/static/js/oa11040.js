/**
 * 画面Loadイベント
 */
window.onload = function() {
	// ★モック状態操作用★
	let a = oaex_mode_onChange();
}

/**
 * ★モック状態操作用★
 */
function oaex_mode_onChange() {
	let modeArray = document.getElementsByName("mode");
	let modeTitle = document.getElementById("mode_title");
	let passwordOldRow = document.getElementById("password_old_row");
	let passwordNewLabel = document.getElementById("password_new_label");
	
	for (let mode of modeArray) {
		if (mode.checked) {
			switch (mode.value) {
			case "0":
				modeTitle.innerText = "初期パスワードの入力"
				// passwordOldRow.style.visibility = "visible";
				passwordOldRow.style.display = "none";
				passwordNewLabel.innerText = "パスワード"
				break;
			case "1":
				modeTitle.innerText = "パスワードのリセット"
				// passwordOldRow.style.visibility = "hidden";
				passwordOldRow.style.display = "none";
				passwordNewLabel.innerText = "パスワード"
				break;
			case "2":
				modeTitle.innerText = "パスワードの変更"
				// passwordOldRow.style.visibility = "hidden";
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
