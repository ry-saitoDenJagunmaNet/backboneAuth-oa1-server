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
	return;
}

/**
 * 閉じるボタンクリックイベントです。
 */
function oaex_closeBtn_onClick() {
	oa_transferForm("oa00000");
}