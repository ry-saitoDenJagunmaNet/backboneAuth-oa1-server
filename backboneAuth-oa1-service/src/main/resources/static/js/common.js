/** Thymeleaf で起動時のみ実行 **/
let _isThymeleaf = false;

/**
 * 画面ロード時のイベントです。
 */
window.addEventListener('load', function() {
    let message = document.getElementById("message").value;
	if (message != null && message.length > 0) {
		oa_showAlert(message);
	}
})

/**
 * サーバーにFORMオブジェクトを送信します。
 * @param {String} url リクエスト先URL
 * @param {Json} formObj リクエスト送信するFORMオブジェクト
 */
function oa_th_sendFormData(url, formObj) {
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url, false);
	xhr.send(new FormData(formObj));

	if(xhr.readyState === 4 && xhr.status === 200) {
		// 正常
		let result = JSON.parse(xhr.responseText);
		//if (typeof result.message !== "undefined" && result.message != null && result.message.length > 0) {
		if (result.message != null && result.message.length > 0) {
			oa_showAlert(result.message);
			return xhr;
		}
		if (result.errorMessage != null && result.errorMessage.length > 0) {
			oa_showAlert(result.errorMessage+result.stackTrace);
			return xhr;
		}
		return xhr;

	} else {
		// 異常
		//alert(xhr.responseText);
		oa_showAlert(xhr.responseText);
		return null;
	}
}


/******** イベント処理 ********/

/**
 * HTML文書の読み込みと解析が完了したイベントです。
 */
document.addEventListener('DOMContentLoaded', function() {
    // Collapsible の初期化
    oa_initCollapsible();
    // Select の初期
    oa_initSelect();
    // datepicker の初期化
    oa_initDatepicker();
    // timeicker の初期化
    oa_initTimepicker();
    // modal の初期化
    oa_initModal();
    // input-field の初期化
    oa_initInputField();
});

/**
 * Collapsible の初期化です。
 * 　折りたたみ要素
 */
function oa_initCollapsible() {
	let elems = document.querySelectorAll('.collapsible');
	let instances = M.Collapsible.init(elems, {accordion: false});
}

/**
 * Select の初期化です。
 * 　コンボボックス要素
 */
function oa_initSelect() {
    let elems = document.querySelectorAll('select');
    let instances = M.FormSelect.init(elems, {});
    
    // コンボが選択済の場合に下線がinputタグの様にする
    // CSSでinputタグに入力があるか判断し、下線を設定します。
    for (let nodes of elems) {
        if (nodes.classList.contains("validate")) {
            // materialize select の構成部品inpit[type=text]にblur,focusイベントを追加してCSSの追加/削除で制御
            for (let selectnodeparent of nodes.parentNode.childNodes) {
                if (selectnodeparent.nodeName.toLowerCase() == "input") {
                    if (selectnodeparent.classList.contains("select-dropdown")) {
                        // focus、blurイベント追加
                        selectnodeparent.addEventListener('focus', function(event) {
                            oa_materializeSelect_onFocus(this);
                        });
                        selectnodeparent.addEventListener('blur', function(event) {
                            oa_materializeSelect_onBlur(this);
                        });
                        // blur時の処理で下線を制御
                        oa_materializeSelect_onBlur(selectnodeparent);
                    }
                }
            }
        }
    }
}

/* selectタグのvalidの下線CSSクラスID */
const _SELECT_VALIDARE_VALID = "oa_validate_valid";

/**
 * materialize select の構成部品inpit[type=text]のフォーカスイベントです。
 * 　validateのクラスを削除
 * @param  {String} obj materialize select の構成部品inpit[type=text]オブジェクト
 */
function oa_materializeSelect_onFocus(obj) {
    obj.classList.remove(_SELECT_VALIDARE_VALID);
}

/**
 * materialize select の構成部品inpit[type=text]のフォーカスが外れたイベントです。
 * 　validateのクラスを追加/削除
 * @param  {String} obj materialize select の構成部品inpit[type=text]オブジェクト
 */
function oa_materializeSelect_onBlur(obj) {
    if (obj.value.trim().length == 0 || obj.disabled) {
        obj.classList.remove(_SELECT_VALIDARE_VALID);
    } else {
        obj.classList.add(_SELECT_VALIDARE_VALID);
    }
}

/**
 * datepicker の初期化です。
 * 　日付コントロール要素
 */
function oa_initDatepicker() {
    let options = {
        onDraw:function(e){
            // 見出し月日曜日レイアウト変更
            let i18n = e.options.i18n;
            let displayDate = e.date ? e.date : new Date();
            let day = i18n.weekdaysShort[displayDate.getDay()];
            let month = i18n.monthsShort[displayDate.getMonth()];
            let date = displayDate.getDate();
            e.dateTextEl.innerHTML = `${month} ${date}日 ${day}`;
        }
        , autoClose: true               // 日付が選択されたときにピッカーを自動的に閉じる
        , format: 'yyyy/mm/dd'          // 入力フィールド値の日付出力フォーマット
        , showClearBtn: true            // クリアボタン表示
        , showMonthAfterYear: true      // 年 月 で表示
        , i18n: {                                                                                                     // 日本語化
            months: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']             // カレンダーの「月」
            , monthsShort: ['1月', '2月',' 3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']      // 見出しの「月」
            //, weekdays: ['日曜日', '月曜日', '火曜日', '水曜日', '木曜日', '金曜日', '土曜日']
            , weekdaysAbbrev: ['日', '月', '火', '水', '木', '金', '土']                                               // カレンダーの「曜日」
            , weekdaysShort: ['(日)', '(月)', '(火)', '(水)', '(木)', '(金)', '(土)']                                  // 見出しの「曜日」
            //, previousMonth: '前月'
            //, nextMonth: '翌月'
            , cancel: 'キャンセル'
            , done: 'ＯＫ'
            , clear: 'クリア'
        }
    }

    let elems = document.querySelectorAll('.datepicker');
    let instances = M.Datepicker.init(elems, options); 
}

/**
 * timepicker の初期化です。
 * 　時間コントロール要素
 */
function oa_initTimepicker() {
    let options = {
        showClearBtn: true            // クリアボタン表示
        , defaultTime: 'now'          // デフォルトの時間
        , twelveHour: false           // 24時間時計の代わりに12時間AM / PM時計を使用
        , i18n: {                                                                                                     // 日本語化
            cancel: 'キャンセル'
            , done: 'ＯＫ'
            , clear: 'クリア'
        }
    }

    let elems = document.querySelectorAll('.timepicker');
    let instances = M.Timepicker.init(elems, options); 
}

/**
 * modal の初期化です。
 * 　モーダルダイアログ表示
 */
function oa_initModal() {
    let elems = document.querySelectorAll('.modal');
    let instances = M.Modal.init(elems, {dismissible:false});
}

/**
 * input-field の初期化です。
 * 　labelタグを持たないinput-fieldのmargin-topを0にする
 * 　inputタグに初期値が設定されている場合、validを追加して下線を設定する
 * 　inputタグdatepicker に初期値が設定されている場合、datepickerの更新を反映する
 */
function oa_initInputField() {
    for(let nodes of document.getElementsByClassName("input-field")) {
        // labelタグを持たないinput-fieldのmargin-topを0にする
        if  (nodes.getElementsByTagName("label").length == 0) {
            nodes.style.marginTop = "0";
        } else {
            let innerLabel = false;
            for(let labelNd of nodes.getElementsByTagName("label")) {
                if (labelNd.innerText.length != 0) {
                    innerLabel = true;
                    break;
                }
            }
            if (!innerLabel) {nodes.style.marginTop = "0";}
        }

        if  (nodes.getElementsByTagName("input").length > 0) {
            for(let inputNd of nodes.getElementsByTagName("input")) {
                // inputタグに初期値が設定されている場合、validを追加して下線を設定する
                if (inputNd.value.length > 0 && !inputNd.classList.contains("valid")) {
                    inputNd.classList.add("valid");
                }
                // datepicker の更新を反映
                if (inputNd.value.length > 0 && inputNd.classList.contains("datepicker")) {
                    // datepicker の更新
                    oa_updateDatepicker(inputNd);
                }
            }
        }
    }

    // 再初期化（動的にinputコントロールに値を設定する場合初期化が必要※コンテンツとラベルが重なる）
    M.updateTextFields();
}




/******** 共通関数 ********/

/* Tableの行選択状態CSSクラスID（Materializeの背景色） */
const _TABLE_ROW_SELECTED1 = "indigo";
const _TABLE_ROW_SELECTED2 = "lighten-4";

/**
 * Tableの行選択を設定します。
 * 　選択行の背景色を変更
 * @param  {Object} objRow Tableの選択行（tr）オブジェクト
 */
function oa_setTableRowSelected(objRow) {
	let tableid = objRow.parentNode.parentNode;
	oa_setTableRowSelectedRemoveAll(tableid);
	objRow.classList.add(_TABLE_ROW_SELECTED1);
	objRow.classList.add(_TABLE_ROW_SELECTED2);
}

/**
 * Tableの全行選択を解除します。
 * 　選択行のstyleを削除
 * @param  {Object} objTable Tableオブジェクト
 */
function oa_setTableRowSelectedRemoveAll(objTable) {
    for (let row of objTable.rows) {
        row.classList.remove(_TABLE_ROW_SELECTED1);
        row.classList.remove(_TABLE_ROW_SELECTED2);
        oa_setTableRowSelectedRemove(row);
    }
}

/**
 * Tableの行選択を解除します。
 * 　選択行のstyleを削除
 * @param  {Object} objTableRow Table row オブジェクト
 */
function oa_setTableRowSelectedRemove(objTableRow) {
    objTableRow.classList.remove(_TABLE_ROW_SELECTED1);
    objTableRow.classList.remove(_TABLE_ROW_SELECTED2);
}

/**
 * Tableの選択行indexを取得します。
 * @param {Object} objTable Tableオブジェクト
 * @returns {int} Tableの選択行index
 */
function oa_getTableSelectedRowIndex(objTable) {
    for(let i = 0; i < objTable.rows.length; i++) {
        if (objTable.rows[i].classList.contains(_TABLE_ROW_SELECTED1) && 
                objTable.rows[i].classList.contains(_TABLE_ROW_SELECTED2)) {
            return i;
        }
    }
    return -1;
}

/**
 * 入力コントロールを無効にします。
 * 　disabledを設定
 * @param  {String} objId 入力コントロールID
 * @param  {Boolean} pro Disabledプロパティ値
 */
function oa_setDisabled(objId, pro) {
    let objNode = document.getElementById(objId);
    oa_setDisabledForObject(objNode, pro)
}

/**
 * 入力コントロールを無効にします。
 * 　disabledを設定
 * @param  {Object} objNode 入力コントロール
 * @param  {Boolean} pro Disabledプロパティ値
 */
function oa_setDisabledForObject(objNode, pro) {
	if (objNode.nodeName.toLowerCase() == "input") {
		objNode.disabled = pro;
		//validを制御（boder-bottomを切り替えるため）
		if (pro) {
			objNode.classList.remove("valid");
		} else {
			if (objNode.value.trim().length > 0) {
				objNode.classList.add("valid");
			}
		}
	} else if (objNode.nodeName.toLowerCase() == "select") {
		objNode.disabled = pro;
	} else if (objNode.nodeName.toLowerCase() == "table") {
		if (pro) {
			objNode.setAttribute("disabled", "disabled");
		} else {
			objNode.removeAttribute("disabled");
		}
	}
}

/**
 * table 明細の横スクロールにヘッダーを同期させます。
 */
function oa_linkageScrollX(targetTbale, headTabelId) {
    document.getElementById(headTabelId).scrollLeft = targetTbale.scrollLeft;
}

/**
 * datepicker Value値を設定します。
 * @param {Steing} datepickerId datepickerコントロールID
 * @param {Date} objDate 設定する日付
 */
function oa_setDatepickerValue(datepickerId, objDate) {
	let objDatepicker = document.getElementById(datepickerId);
    let dateString = objDate.getFullYear() + "/" + ("00" + (objDate.getMonth() + 1)).slice(-2) + "/" + ("00" + objDate.getDate()).slice(-2);
    if (objDatepicker.M_Datepicker.options.format == "yyyy/mm") {
        // 年月の場合
        dateString = objDate.getFullYear() + "/" + ("00" + (objDate.getMonth() + 1)).slice(-2);
    }
    objDatepicker.value = dateString;

    // datepicker の更新
    oa_updateDatepicker(objDatepicker);
}

/**
 * datepicker の更新を反映します。
 * @param {Object} objDatepicker datepickerコントロール
 */
function oa_updateDatepicker(objDatepicker) {
    let dateVal = new Date();
    let dateStr = "";
    let dateStrAry = objDatepicker.value.split("/")
    if (dateStrAry.length == 2) {
        dateStr = dateStrAry[0]+"/"+dateStrAry[1]+"/01";
    }if (dateStrAry.length == 3) {
        dateStr = objDatepicker.value;
    }
    // 日付が有効かチェック
    if (!oa_isDate(dateStr)) {return;}
	objDatepicker.M_Datepicker.setDate(new Date(dateStr));
}

/**
 * 日付が有効かチェックします。
 * @param {String} strDate 
 * @returns {Boolean} true:有効、false:無効
 */
function oa_isDate(strDate) {
    if (!strDate.match(/^\d{4}\/\d{2}\/\d{2}$/)) {return false;}
    let y = strDate.split("/")[0];
    let m = strDate.split("/")[1] - 1;
    let d = strDate.split("/")[2];
    let date = new Date(y,m,d);
    if (date.getFullYear() != y || date.getMonth() != m || date.getDate() != d) {return false;}
    return true;
}

/**
 * datepicker デフォルトの日付を設定します。
 * @param {String} datepickerId datepickerコントロールID
 * @param {Date} defaultDate 設定するデフォルトの日付
 */
function oa_setDatepickerDefaultDate(datepickerId, defaultDate) {
    let objDatepicker = document.getElementById(datepickerId);
    objDatepicker.M_Datepicker.options.defaultDate = defaultDate;
}

/**
 * timepicker デフォルトの時間を設定します。
 * @param {String} timepickerId timepickerコントロールID
 * @param {String} defaultTime 設定するデフォルトの時間
 */
function oa_setTimepickerDefaultTime(timepickerId, defaultTime) {
    let objTimepicker = document.getElementById(timepickerId);
    objTimepicker.M_Timepicker.options.defaultTime = defaultTime;
}

/**
 * 画面遷移します。
 * @param  {String} formid 遷移FormId
 */
function oa_transferForm(formid) {
    let sn = document.getElementById("sidenav_opne").value;
	location.href = "./" + formid + ".html?sn=" + sn;
}

/**
 * パラメータを付与して画面遷移します。
 * @param  {String} formid 遷移FormId
 * @param  {String} param パラメータ　例）abcd=1&efg=xxx
 */
function oa_transferFormWithParam(formid, param) {
    let sn = document.getElementById("sidenav_opne").value;
    pm = "?sn=" + sn;
    pm = pm + "&" + param;
	location.href = "./" + formid + ".html" + pm;
}

/**
 * 改行マークをbrタグに変換します。
 * @param {String} val 
 */
function oa_n2br(val) {
	return val.replace(/\n/g, '<br />')
}

/**
 * メッセージダイアログを表示します。
 * @param {String} msg 表示するメッセージ
 */
function oa_showAlert(msg) {
	msg = oa_n2br(msg);
	document.getElementById("alertMessage").innerHTML = msg;
	document.getElementById("btnShowAlert").click();
}

/**
 * 問合せメッセージダイアログを表示します。
 * @param  {String} msg 表示するメッセージ
 * @param  {function} func OK,キャンセルボタンクリック時にcallするファンクション
 */
function oa_showConfirm(msg, func) {
	document.getElementById("confirmOKOnClick").onclick = function(){
		//alert("OK");
		func(true);
	};
	document.getElementById("confirmCancelOnClick").onclick = function() {
		//alert("キャンセル");
		func(false);
	};

    msg = oa_n2br(msg);
	document.getElementById("confirmMessage").innerHTML = msg;
	document.getElementById("btnShowConfirm").click();
}

/**
 * コントロールにフォーカスを設定します。
 * @param {String} elementId 対象コントロールID
 */
function oa_setFocus(elementId) {
    let objNode = document.getElementById(elementId);
	if (objNode.nodeName.toLowerCase() == "select") {
        // コンボボックス（select）の場合、materializeでカスタマイズしたinputタグにフォーカス設定
		for (let selectNodeBrother of objNode.parentNode.childNodes) {
			if (selectNodeBrother.nodeName.toLowerCase() == "input") {
				selectNodeBrother.focus();
				selectNodeBrother.select();
				break;
			}
        }
    } else if (objNode.nodeName.toLowerCase() == "input") {
        objNode.focus();
        objNode.select();
	} else {
		objNode.focus();
	}
}

/**
 * radioの選択valeu値を取得します。
 * @param {String} elementName radioのname
 * @returns {String} radioの選択valeu値
 */
function oa_getRadioCheckedValue(elementName) {
	let obhRadio = document.getElementsByName(elementName);
	for(let i = 0; i < obhRadio.length; i++){
		if (obhRadio[i].checked) {
			return obhRadio[i].value;
		}
	}
	return null;
}

/**
 * URLパラメータを取得します。
 * @param {String} key 取得するパラメータKey
 */
function oa_getParam(key) {
    let url = location.href;
    let parameters = url.split("?");
    if (parameters.length <= 1) {return "";}
    let params = parameters[1].split("&");
    let paramsArray = [];
    for (let i = 0; i < params.length; i++) {
        neet = params[i].split("=");
        paramsArray.push(neet[0]);
        paramsArray[neet[0]] = neet[1];
    }
    // let categoryKey = paramsArray["id"]
	// return categoryKey
	if (paramsArray[key]) {
		return paramsArray[key];
	}
	return "";
}

/**
 * メッセージダイアログを取得（作成）します。
 */
function oa_getMessageDialog() {
	let html = '';
    html += '<div>';
    html += '   <button class="modal-trigger" id="btnShowAlert" data-target="alert" style="display: none;">alert</button>';
    html += '   <div id="alert" class="modal">';
    html += '       <div class="modal-content" id="alertMessage"></div>';
    html += '       <div class="modal-footer">';
    html += '           <a href="#!" class="modal-close btn waves-effect waves-light btn">OK</a>';
    html += '       </div>';
    html += '   </div>';

    html += '   <button class="modal-trigger" id="btnShowConfirm" data-target="confirm" style="display: none;">alert</button>';
    html += '   <div id="confirm" class="modal">';
    html += '       <div class="modal-content" id="confirmMessage"></div>';
    html += '       <div class="modal-footer">';
    html += '           <a href="#!" class="modal-close btn waves-effect waves-light btn" id="confirmOKOnClick">OK</a>';
    html += '           <a href="#!" class="modal-close btn waves-effect waves-light btn" id="confirmCancelOnClick">キャンセル</a>';
    html += '       </div>';
    html += '   </div>';
    html += '</div>';

    document.write(html);
}

/**
 * headerを取得（作成）します。
 * @param  {String} id 画面ID
 * @param  {String} title 画面名
 */
function oa_getHeader(id, title, isThymeleaf) {
    let html = '';
    let imgUrl = '../static/img/';
    if (document.getElementById("imageUrl") != null && document.getElementById("imageUrl").value != "") {
    	imgUrl = document.getElementById("imageUrl").value;
    }

    html += '<div class="card-panel oa_header">';
    //html += '	<img class="responsive-img" src="../static/img/' + id.toLowerCase() + '.png" alt="' + title + '"/>';
    html += '	<img class="responsive-img" src="' + imgUrl + id.toLowerCase() + '.png" alt="' + title + '"/>';
    html += '	<span>' + title + '</span>';
    html += '</div>';

    document.write(html);
}