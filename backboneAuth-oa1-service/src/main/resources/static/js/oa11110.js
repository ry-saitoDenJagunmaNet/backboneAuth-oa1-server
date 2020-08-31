/**
 * 画面Loadイベントです。
 */
window.onload = function() {
	// 変更日のラジオボタン初期処理
	oaex_change_date_onChange();
}

/**
 * 変更日のラジオボタンの変更イベントです。
 */
function oaex_change_date_onChange() {
	var change_date = document.getElementsByName("change_date");
	for(var i = 0; i < change_date.length; i++){
		if (change_date[i].checked) {
			if (change_date[i].value == "1") {
				oa_setDisabled("update_day", false);
				oa_setDisabled("update_day_sel", false);
				oa_initSelect();

				oa_setDisabled("update_date_from", true);
				oa_setDisabled("update_date_to", true);
			} else {
				oa_setDisabled("update_day", true);
				oa_setDisabled("update_day_sel", true);
				oa_initSelect();

				oa_setDisabled("update_date_from", false);
				oa_setDisabled("update_date_to", false);
			}
		}
	}
}

/**
 * 検索ボタンクリックイベントです。
 */
function oaex_searchBtn_onClick() {
	// todo:テーブル＆ページネーションを表示
	document.getElementById("password_history_table").style.visibility = "visible";
	document.getElementById("password_history_pagination").style.visibility = "visible";
}

/**
 * 閉じるボタンクリックイベントです。
 */
function oaex_closeBtn_onClick() {
	oa_transferForm("oa00000");
}
