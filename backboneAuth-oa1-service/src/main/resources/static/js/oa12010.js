/** Thymeleaf で起動時のみ実行 **/

function oaex_th_onload() {
	_isThymeleaf = true;
	oaex_mode_onChange();
}

/**
 * 実行ボタン（インポート／エクスポート）クリックイベントです。
 */
function oaex_th_executeBtn_onClick() {
	if (document.getElementById("mode_export").checked) {
		document.forms[0].action = "export";
		document.forms[0].method = "POST";
		document.forms[0].submit();
	} else if (document.getElementById("mode_import").checked) {
//		document.forms[0].action = "import";
//		document.forms[0].method = "POST";
//		document.forms[0].submit();
	}
}

/**
 * インポートボタンクリックイベントです。
 */
function oaex_th_impotBtn_onClick() {
//	document.forms[0].action = "import";
//	document.forms[0].method = "POST";
//	document.forms[0].submit();
}

/**
 * 画面Loadイベント
 */
window.onload = function() {
	
	// 初期セット
	document.getElementsByName("mode")[0].checked = "true";
	oaex_mode_onChange();

}

/**
 * 出力モードラジオボタンクリックイベントです。
 */
function oaex_mode_onChange() {
	let modeArray = document.getElementsByName("mode");
	let importFileSection = document.getElementById("import_file_section");
	let executeBtn = document.getElementById("execute_btn");

	for (let mode of modeArray) {
		if (mode.checked) {
			switch (mode.value) {
			case "import":
				importFileSection.style.display = "block"
				executeBtn.innerText = "インポート"
				break;
			case "export":
				importFileSection.style.display = "none"
				executeBtn.innerText = "エクスポート"
				break;
			}
		}
	}
}

/**
 * 実行ボタン（インポート／エクスポート）クリックイベントです。
 */
function oaex_executeBtn_onClick() {
	if (_isThymeleaf) {
		oaex_th_executeBtn_onClick();
		return;
	}

	return;
}

/**
 * 閉じるボタンクリックイベントです。
 */
function oaex_closeBtn_onClick() {
	oa_transferForm("oa00000");
}
