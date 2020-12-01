/** Thymeleaf で起動時のみ実行 **/

function oaex_th_onload() {
	_isThymeleaf = true;
	oaex_mode_onChange();
}

/**
 * チェックボタンクリックイベントです。
 */
function oaex_th_checkBtn_onClick() {
	// Preloader表示
	oa_showPreloader();
	document.forms[0].action = "checkExcel";
	document.forms[0].method = "POST";
	document.forms[0].submit();
}

/**
 * 実行ボタン（インポート／エクスポート）クリックイベントです。
 */
function oaex_th_executeBtn_onClick() {
	if (document.getElementById("mode_export").checked) {
		document.forms[0].action = "exportExcel";
		document.forms[0].method = "POST";
		document.forms[0].submit();
	} else if (document.getElementById("mode_import").checked) {
		// Preloader表示
		oa_showPreloader();
		document.forms[0].action = "importExcel";
		document.forms[0].method = "POST";
		document.forms[0].submit();
	}
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
	let importExecuteBtn = document.getElementById("importExecute_btn");
	let exportExecuteBtn = document.getElementById("exportExecute_btn");
	let checkBtn = document.getElementById("check_Btn");

	for (let mode of modeArray) {
		if (mode.checked) {
			switch (mode.value) {
			case "import":
				importFileSection.style.display = "block"
				importExecuteBtn.style.display = ""
				checkBtn.style.display = ""
				exportExecuteBtn.style.display = "none"
				break;
			case "export":
				importFileSection.style.display = "none"
				importExecuteBtn.style.display = "none"
				checkBtn.style.display = "none"
				exportExecuteBtn.style.display = ""
				break;
			}
		}
	}
}

/**
 * チェックボタンクリックイベントです。
 */
function oaex_checkBtn_onClick() {
	if (_isThymeleaf) {
		oaex_th_checkBtn_onClick();
		return;
	}

	return;
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
