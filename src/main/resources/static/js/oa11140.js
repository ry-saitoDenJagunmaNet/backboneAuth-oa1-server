/**
 * 画面Loadイベント
 */
window.onload = function() {
	
	// 初期セット
	document.getElementById("mode_composition").checked = "true";
	oaex_mode_onChange();

}

/**
 * 出力モードラジオボタンクリックイベントです。
 */
function oaex_mode_onChange() {
	let modeArray = document.getElementsByName("mode");
	let modeChildSectionArray = [
		document.getElementById("mode_composition_child_section"), 
		document.getElementById("mode_granted_child_section"), 
		document.getElementById("mode_allocated_child_section")
		];
	// 全モードの子セクションを非表示
	for (let modeChildSection of modeChildSectionArray) {
		modeChildSection.style.display = "none";
	}

	let i = 0;
	let modeChild1Array = [
		document.getElementById("mode_composition_child_1"), 
		document.getElementById("mode_granted_child_1"), 
		document.getElementById("mode_allocated_child_1")
		];	
	let modeChildArray
	let isAlreadySelected = false;
	const today = new Date();
	const formatToday = today.getFullYear() + "/" + (("0" + (today.getMonth() + 1)).slice(-2)) + "/" + (("0" + today.getDate()).slice(-2));

	// 指定モードの子セクションを表示と各種デフォルトセット
	for (let mode of modeArray) {
		if (mode.checked) {
			// 子セクションを表示
			modeChildSectionArray[i].style.display = "block";

			// 指定モードの選択肢取得と状態指定日のデフォルトセット
			switch (mode.value) {
			case "composition":
				modeChildArray = document.getElementsByName("mode_composition_child");
				break;
			case "granted":
				modeChildArray = document.getElementsByName("mode_granted_child");
				if (document.getElementById("mode_granted_state_of_date").value == "") {
					document.getElementById("mode_granted_state_of_date").value = formatToday
				}
				break;
			case "allocated":
				modeChildArray = document.getElementsByName("mode_allocated_child");
				if (document.getElementById("mode_allocated_state_of_date").value == "") {
					document.getElementById("mode_allocated_state_of_date").value = formatToday
				}
				break;
			}
			// 既存選択があるか
			for (let modeChild of modeChildArray) {
				if (modeChild.checked == true) {
					isAlreadySelected = true;
					break;
				}
			}
			// 既存選択がない場合、一番目をデフォルトセット
			if (isAlreadySelected == false) {
				modeChild1Array[i].checked = "true";
			}
		
			break;
		}
		i++;
	}

	// input-field の初期化
	oa_initInputField();
}
