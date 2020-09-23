/**
 * ★モック状態操作用★
 */
function oaex_mockupLockStatus_onChange() {
	let mockup_lock_status = document.getElementById("mockup_lock_status");
	let lock_status_mark = document.getElementById("lock_status_mark");
	let lock_status = document.getElementById("lock_status");

	if (mockup_lock_status.checked) {
		lock_status_mark.className = "oaex_lock_status_lock"
		lock_status.value = "ロック"
	} else {
		lock_status_mark.className = "oaex_lock_status_unlock"
		lock_status.value = "アンロック"
	}
}

/**
 * 登録／更新ボタンクリックイベントです。
 */
function oaex_entryBtn_onClick() {
	oa_showAlert("登録しました。");
}

/**
 * 閉じるボタンクリックイベントです。
 */
function oaex_closeBtn_onClick() {
	oa_transferForm("oa11010");
}
