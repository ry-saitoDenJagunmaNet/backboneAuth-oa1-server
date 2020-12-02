/**
 * 画面Loadイベントです。
 */
window.onload = function() {
	// 初期化処理
	oaex_initialize();
}

/**
 * 初期化処理です。
 */
function oaex_initialize() {
	// 表示方法のラジオボタン初期化
	document.getElementById("disp_mode_week_subsystem").checked = true;	
	oaex_disp_mode_sel_onChange();
	
	// システム利用可能時間帯テーブル（曜日 ＼ サブシステム）セルにクリックイベント追加
	let tbNode = document.getElementById("week_subsystem_table");
	for (let i = 0; i < tbNode.rows.length; i++) {
		for (let j = 0; j <  tbNode.rows[i].cells.length; j++) {
			let colNode = tbNode.rows[i].cells[j];
			colNode.addEventListener('click', function(event) {
                oaex_weekSubSystemList_onClick(this, true);
            });
		}
	}
}

/**
 * 表示方法ラジオボタンの変更イベントです。
 */
function oaex_disp_mode_sel_onChange() {
	let val = oa_getRadioCheckedValue("disp_mode_sel");

	if (val == "0") {
		document.getElementById("week_subsystem_table_section").style.display = "block";
		document.getElementById("subsystem_week_table_section").style.display = "none";
	} else if (val == "1") {
		document.getElementById("week_subsystem_table_section").style.display = "none";
		document.getElementById("subsystem_week_table_section").style.display = "block";
	} else if (val == "2") {
		document.getElementById("week_subsystem_table_section").style.display = "block";
		document.getElementById("subsystem_week_table_section").style.display = "block";
	}
}

/**
 * 閉じるボタンクリックイベントです。
 */
function oaex_closeBtn_onClick() {
	oa_transferForm("oa00000");
}

/**
 * システム利用可能時間帯テーブル（曜日 ＼ サブシステム）セルのクリックイベントです。
 * @param {Object} obj システム利用可能時間帯テーブル（曜日 ＼ サブシステム）のセル
 * @param {Boolean} subsystemWeekSelect true:サブシステム ＼ 曜日選択をする
 */
function oaex_weekSubSystemList_onClick(obj, subsystemWeekSelect) {
	let rowIx = obj.parentNode.rowIndex;
	let celIx = obj.cellIndex;

	// 曜日列を選択した場合は処理抑止
	if (celIx == 0) {return;}

	if (rowIx == 0) {
		// 見出しの上段は結合しているので倍にする
		celIx = celIx * 2;
	}

	let tbNode = document.getElementById("week_subsystem_table");

	// 選択状態を解除する
	for (let rowNode of tbNode.rows) {
		for (let cellNode of rowNode.cells) {
			cellNode.classList.remove(_TABLE_ROW_SELECTED1);
			cellNode.classList.remove(_TABLE_ROW_SELECTED2);
		}
    }

	// 選択状態にする
	for (let rowNode of tbNode.rows) {
		if (rowNode.rowIndex > 1) {
			rowNode.cells[celIx].classList.add(_TABLE_ROW_SELECTED1);
			rowNode.cells[celIx].classList.add(_TABLE_ROW_SELECTED2);
			// 同じサブステムの列の選択状態にする
			if ((celIx % 2) == 1) {
				rowNode.cells[celIx + 1].classList.add(_TABLE_ROW_SELECTED1);
				rowNode.cells[celIx + 1].classList.add(_TABLE_ROW_SELECTED2);
			} else {
				rowNode.cells[celIx - 1].classList.add(_TABLE_ROW_SELECTED1);
				rowNode.cells[celIx - 1].classList.add(_TABLE_ROW_SELECTED2);
			}
		}
	}
	
	// サブシステム ＼ 曜日選択
	if (subsystemWeekSelect) {
		// （サブシステム ＼ 曜日）の行　＝　（曜日／サブシステム）の選択列ix　÷　２で切り上げ　＋１
		let rowix = Math.ceil(celIx/2)+1;
		let subsystemWeekRow = document.getElementById("subsystem_week_table").rows[rowix];
		oa_setTableRowSelected(subsystemWeekRow);
	}
}

/**
 * システム利用可能時間帯テーブル（サブシステム＼曜日）行のクリックイベントです。
 * @param {Object} obj システム利用可能時間帯テーブル（サブシステム＼曜日）の行
 */
function oaex_subsystemWeekList_onClick(obj) {
	oa_setTableRowSelected(obj);

	// 曜日 ＼ サブシステム選択
	// （曜日 ＼ サブシステム選択）の列　＝　（サブシステム＼曜日）の選択行ix　×　２　－　２
	let colix = obj.rowIndex * 2 - 2;
	let weekSubSystemCol = document.getElementById("subsystem_week_table").rows[1].cells[colix];
	oaex_weekSubSystemList_onClick(weekSubSystemCol, false)
}