/**
 * 画面 Loadイベントです
 */
function oaex_th_onload() {
	_isThymeleaf = true;

	let displayAtMockupRow = document.getElementById("display_at_mockup_row");
	displayAtMockupRow.style.display = "none";
	oaex_accountLockStatus_onChange();
}

/**
 * 閉じるボタン Clickイベントです
 */
function oaex_closeBtn_onClick() {
	oa_transferForm("oa00000");
}

/**
 * 更新ボタン Clickイベントです
 */
function oaex_updateBtn_onClick() {
	if (_isThymeleaf) {
		oaex_th_updateBtn_onClick();
		return;
	}
	oa_showAlert("更新しました。");
}
function oaex_th_updateBtn_onClick() {
	document.forms[0].action = "update";
	document.forms[0].method = "POST";
	document.forms[0].submit();
}

/**
 * ロック状態 Changeイベントです
 */
function oaex_accountLockStatus_onChange() {
	let accountLockStatus = document.getElementById("account_lock_status");
	let accountLockStatusMark = document.getElementById("account_lock_status_mark");
	let accountLockStatusText = document.getElementById("account_lock_status_text");

	if (accountLockStatus.checked) {
		accountLockStatusMark.className = "oaex_account_lock_status_lock"
		accountLockStatusText.value = "ロック"
	} else {
		accountLockStatusMark.className = "oaex_account_lock_status_unlock"
		accountLockStatusText.value = "アンロック"
	}
}
