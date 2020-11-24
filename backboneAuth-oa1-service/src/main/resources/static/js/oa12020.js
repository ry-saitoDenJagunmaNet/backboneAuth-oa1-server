/** Thymeleaf で起動時のみ実行 **/
/**
 * 画面Loadイベントです。
 */
function oaex_th_onload() {
	_isThymeleaf = true;

	// ＪＡ ItemSourceの取得
	document.getElementById("jaSelect").innerHTML = oa_th_getItemsSource("getJaItemsSource", document.forms[0]);

	// サブシステム ItemSourceの取得
	document.getElementById("subSystemSelect").innerHTML = oa_th_getItemsSource("getSubSystemItemsSource", document.forms[0]);

	// Changeイベントの追加
	document.getElementById("ja").addEventListener("change", (event) => {oaex_th_ja_onChange();});
	document.getElementById("subSystem").addEventListener("change", (event) => {oaex_th_subsystem_onChange();});
	document.getElementById("bizTranGrp").addEventListener("change", (event) => {oaex_th_biztran_grp_onChange();});

	// テーブル＆ページネーションを表示
	document.getElementById("suspend_biztran_table").style.visibility = "visible";
	document.getElementById("suspend_biztran_pagination").style.visibility = "visible";


	// selectの初期化
	oa_initSelect();

	if (document.getElementById("ja").value.length > 0) {oaex_th_ja_onChange();}
	if (document.getElementById("subSystem").value.length > 0) {oaex_th_subsystem_onChange();}
}

/**
 * JAの変更イベントです。
 */
function oaex_th_ja_onChange() {
	// 店舗 ItemSourceの取得
	document.getElementById("branchSelect").innerHTML = oa_th_getItemsSource("getBranchItemsSource", document.forms[0]);

	// selectの初期化
	oa_initSelect();
}

/**
 * サブシステムの変更イベントです。
 */
function oaex_th_subsystem_onChange() {
	// 取引グループ ItemSourceの取得
	document.getElementById("bizTranGrpSelect").innerHTML = oa_th_getItemsSource("getBizTranGrpItemsSource", document.forms[0]);
	// 取引 ItemSourceの取得
	document.getElementById("bizTranSelect").innerHTML = oa_th_getItemsSource("getBizTranItemsSource", document.forms[0]);

	// Changeイベントの追加
	document.getElementById("bizTranGrp").addEventListener("change", (event) => {oaex_th_biztran_grp_onChange();});

	// selectの初期化
	oa_initSelect();
}

/**
 * 取引グループの変更イベントです。
 */
function oaex_th_biztran_grp_onChange() {
	// 取引 ItemSourceの取得
	document.getElementById("bizTranSelect").innerHTML = oa_th_getItemsSource("getBizTranItemsSource", document.forms[0]);

	// selectの初期化
	oa_initSelect();
}

/**
 * 検索ボタンクリックイベントです。
 */
function oaex_th_searchBtn_onClick(pageNo) {
	document.getElementById("pageNo").value = pageNo;
	document.forms[0].action = "search";
	document.forms[0].method = "POST";
	document.forms[0].submit();
}



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
	// ＪＡ
	document.getElementById("ja").selectedIndex = 0;
	// 店舗
	document.getElementById("tempo").selectedIndex = 0;
	oa_setDisabled("tempo", true);
	// サブシステム
	document.getElementById("subsystem").selectedIndex = 0;
	// 取引グループ
	oaex_setBiztranGrpOption();
	document.getElementById("biztran_grp").selectedIndex = 0;
	//oa_setDisabled("biztran_grp", true);
	// 取引
	oaex_setBiztranOption();
	document.getElementById("biztran").selectedIndex = 0;
	//oa_setDisabled("biztran", true);

	// 有効期限のラジオボタン初期化
	document.getElementById("expiration_sel_non").checked = true;	
	oaex_expiration_sel_onChange();
	// 状態指定日
	document.getElementById("expiration_status_date").value = "";
	// 条件指定
	document.getElementById("expiration_start_date_from").value = "";
	document.getElementById("expiration_start_date_to").value = "";
	document.getElementById("expiration_end_date_from").value = "";
	document.getElementById("expiration_end_date_to").value = "";
	
	// selectの初期化
	oa_initSelect();
}

/* 取引グループコンボボックスのリストデータ */
let biztranGrp_data = [
	["KBTG01", "購買メニュー"], 
	["KBTG03", "共通入力支援"], 
	["KBTG06", "未払金照会メニュー"], 
	["KBTG09", "予約受注明細入力メニュー"], 
	["KBTG10", "予約受注帳票出力メニュー"], 
	["YSTG01", "荷受取引グループ"], 
	["YSTG02", "送り状取引グループ"], 
	["YSTG03", "市況取引グループ"], 
	["YSTG04", "仕切取引グループ"], 
	["YSTG05", "単価・精算取引グループ"], 
	["YFTG01", "荷受取引グループ"], 
	["YFTG02", "送り状取引グループ"], 
	["YFTG03", "市況取引グループ"], 
	["YFTG04", "仕切取引グループ"], 
	["YFTG05", "単価・精算取引グループ"], 
	["HKTG10", "米ＪＡ取引グループ"], 
	["HKTG15", "米ＪＡ取引（振込以外取引）グループ"], 
	["HKTG99", "米センター取引グループ"], 
	["HMTG20", "麦ＪＡ取引グループ"], 
	["HMTG25", "麦ＪＡ取引（振込以外取引）グループ"], 
	["HMTG99", "麦センター取引グループ"], 
	["ANTG01", "データ入力取引グループ"], 
	["ANTG02", "精算取引グループ"], 
	["ANTG03", "マスタ取引グループ"], 
	["ANTG10", "センター維持管理グループ"] 
];

/* 取引コンボボックスのリストデータ */
let biztran_data = [
	["KBTG01", "KB0000", "購買メインメニュー"], 
	["KBTG01", "KB0020", "購買支所用メニュー"], 
	["KBTG01", "KB0021", "窓口入金支所用メニュー"], 
	["KBTG01", "KB0022", "日締支所用メニュー"], 
	["KBTG01", "KB0023", "マスタ支所メニュー１"], 
	["KBTG03", "KB0001", "支所検索"], 
	["KBTG03", "KB0002", "顧客検索"], 
	["KBTG03", "KB0003", "仕入先検索"], 
	["KBTG03", "KB0004", "仕入先部門検索"], 
	["KBTG03", "KB0005", "担当者検索"], 
	["KBTG06", "KB2502", "未払明細照会"], 
	["KBTG06", "KB2503", "未払照合結果照会"], 
	["KBTG09", "KB4022", "予約受注らくらく入力_登録"], 
	["KBTG09", "KB4023", "予約受注入力_登録"], 
	["KBTG09", "KB4024", "予約受注入力_修正"], 
	["KBTG09", "KB4025", "予約受注入力_削除"], 
	["KBTG10", "KB4026", "予約受注明細リスト出力"], 
	["KBTG10", "KB4027", "予約受注集計リスト出力"], 
	["KBTG10", "KB4028", "予約発注検討表出力"], 
	["KBTG10", "KB4046", "予約受注注文書一覧表出力"], 
	["YSTG01", "YS0000", "野菜メインメニュー"], 
	["YSTG01", "YS1000", "荷受業務メニュー"], 
	["YSTG01", "YS1301", "等階級制荷受入力"], 
	["YSTG01", "YS1302", "等階級制荷受参照"], 
	["YSTG01", "YS1303", "等階級制荷受修正"], 
	["YSTG02", "YS0000", "野菜メインメニュー"], 
	["YSTG02", "YS2000", "市場発送業務メニュー"], 
	["YSTG02", "YS2402", "等階級制送り状入力"], 
	["YSTG02", "YS2406", "等階級制送り状修正"], 
	["YSTG02", "YS2501", "送り状連動エラー"], 
	["YSTG03", "YS0000", "野菜メインメニュー"], 
	["YSTG03", "YS3200", "市況業務メニュー"], 
	["YSTG03", "YS3201", "市況入力"], 
	["YSTG03", "YS3202", "市況修正"], 
	["YSTG03", "YS3203", "市況参照"], 
	["YSTG04", "YS0000", "野菜メインメニュー"], 
	["YSTG04", "YS3300", "仕切業務メニュー"], 
	["YSTG04", "YS3301", "仕切入力"], 
	["YSTG04", "YS3302", "仕切修正"], 
	["YSTG04", "YS3303", "仕切参照"], 
	["YSTG05", "YS0000", "野菜メインメニュー"], 
	["YSTG05", "YS4000", "単価・精算・振込業務メニュー"], 
	["YSTG05", "YS4101", "単価計算要求"], 
	["YSTG05", "YS4102", "単価設定"], 
	["YSTG05", "YS4103", "格付制単価修正"], 
	["YFTG01", "YF0000", "花卉メインメニュー"], 
	["YFTG01", "YF1000", "荷受業務メニュー"], 
	["YFTG01", "YF1307", "等階級制荷受入力"], 
	["YFTG01", "YF1308", "等階級制荷受参照"], 
	["YFTG01", "YF1309", "等階級制荷受修正"], 
	["YFTG02", "YF0000", "花卉メインメニュー"], 
	["YFTG02", "YF2000", "市場発送業務メニュー"], 
	["YFTG02", "YF2402", "等階級制送り状入力"], 
	["YFTG02", "YF2406", "等階級制送り状修正"], 
	["YFTG02", "YF2501", "送り状連動エラー"], 
	["YFTG03", "YF0000", "花卉メインメニュー"], 
	["YFTG03", "YF3200", "市況業務メニュー"], 
	["YFTG03", "YF3201", "市況入力"], 
	["YFTG03", "YF3202", "市況修正"], 
	["YFTG03", "YF3203", "市況参照"], 
	["YFTG04", "YF0000", "花卉メインメニュー"], 
	["YFTG04", "YF3300", "仕切業務メニュー"], 
	["YFTG04", "YF3301", "仕切入力"], 
	["YFTG04", "YF3302", "仕切修正"], 
	["YFTG04", "YF3303", "仕切参照"], 
	["YFTG05", "YF0000", "花卉メインメニュー"], 
	["YFTG05", "YF4101", "単価計算要求"], 
	["YFTG05", "YF4104", "単価修正"], 
	["YFTG05", "YF4107", "個選単価参照削除"], 
	["YFTG05", "YF4201", "精算要求"], 
	["HKTG10", "HK1000", "米ＪＡ取引"], 
	["HKTG15", "HK1500", "米ＪＡ取引（振り込み以外）"], 
	["HKTG99", "HK9999", "米センター取引"], 
	["HMTG20", "HM2000", "麦ＪＡ取引"], 
	["HMTG25", "HM2500", "麦ＪＡ取引（振り込み以外）"], 
	["HMTG99", "HM9999", "麦センター取引"], 
	["ANTG01", "AN0001", "畜産メインメニュー"], 
	["ANTG01", "AN1110", "前日処理照会"], 
	["ANTG01", "AN1210", "仕切入力"], 
	["ANTG01", "AN1211", "牛乳仕切一括取込"], 
	["ANTG01", "AN1310", "仕切修正"], 
	["ANTG02", "AN0001", "畜産メインメニュー"], 
	["ANTG02", "AN1510", "精算取消"], 
	["ANTG02", "AN1610", "振込処理"], 
	["ANTG02", "AN1611", "振込要求照会"], 
	["ANTG02", "AN1650", "リスト出力要求"], 
	["ANTG03", "AN0001", "畜産メインメニュー"], 
	["ANTG03", "AN3500", "データ提供メニュー"], 
	["ANTG03", "AN3502", "実績データダウンロード"], 
	["ANTG03", "AN8000", "畜産マスタ設定処理メニュー"], 
	["ANTG03", "AN8107", "団体登録"], 
	["ANTG10", "AN0002", "畜産業務（センター）メニュー"], 
	["ANTG10", "AN3500", "データ提供メニュー"], 
	["ANTG10", "AN3501", "マスタダウンロード"], 
	["ANTG10", "AN3502", "実績データダウンロード"], 
	["ANTG10", "AN3801", "畜産月次帳票予約登録"]
];

/**
 * 取引グループコンボボックスのリストを設定します。
 */
function oaex_setBiztranGrpOption() {
	let biztranGrp = document.getElementById("biztran_grp");
	let biztranGrpVal = biztranGrp.value;
	let subsystemVal = document.getElementById("subsystem").value;

	// クリア
	while(biztranGrp.lastChild)
	{
		biztranGrp.removeChild(biztranGrp.lastChild);
	}
	
	biztranGrp.options[0] = new Option("", "");
	let j = 0;
	for (let i = 0; i < biztranGrp_data.length; i++){
		if (subsystemVal.length == 0) {
			// 全データ
			j++;
			biztranGrp.options[j] = new Option(biztranGrp_data[i][0]+" "+biztranGrp_data[i][1], biztranGrp_data[i][0]);
		} else {
			// サブシステムでフィルタ
			if (biztranGrp_data[i][0].substr(0, 2) == subsystemVal) {
				j++;
				biztranGrp.options[j] = new Option(biztranGrp_data[i][0]+" "+biztranGrp_data[i][1], biztranGrp_data[i][0]);
			}
		}
	}

	// 取引コンボボックスのリスト再作成前の値で選択
	if (biztranGrpVal.length > 0) {
		biztranGrp.value = biztranGrpVal;
	}
	
}

/**
 * 取引コンボボックスのリストを設定します。
 */
function oaex_setBiztranOption() {
	let biztran = document.getElementById("biztran");
	let biztranVal = biztran.value;
	let subsystemVal = document.getElementById("subsystem").value;
	let biztranGrpVal = document.getElementById("biztran_grp").value;

	// クリア
	while(biztran.lastChild)
	{
		biztran.removeChild(biztran.lastChild);
	}
	
	biztran.options[0] = new Option("", "");
	let j = 0;
	for (let i = 0; i < biztran_data.length; i++){
		if (biztranGrpVal.length == 0) {
			if (subsystemVal.length == 0) {
				// 全データ
				j++;
				biztran.options[j] = new Option(biztran_data[i][1]+" "+biztran_data[i][2], biztran_data[i][1]);
			} else {
				// サブシステムでフィルタ
				if (biztran_data[i][0].substr(0, 2) == subsystemVal) {
					j++;
					biztran.options[j] = new Option(biztran_data[i][1]+" "+biztran_data[i][2], biztran_data[i][1]);
				}
			}
		} else {
			// 取引グループでフィルタ
			if (biztran_data[i][0] == biztranGrpVal) {
				j++;
				biztran.options[j] = new Option(biztran_data[i][1]+" "+biztran_data[i][2], biztran_data[i][1]);
			}
		}
	}

	// 取引コンボボックスのリスト再作成前の値で選択
	if (biztranVal.length > 0) {
		biztran.value = biztranVal;
	}
}

/**
 * 有効期限ラジオボタンの変更イベントです。
 */
function oaex_expiration_sel_onChange() {
	let val = oa_getRadioCheckedValue("expiration_sel");

	oa_setDisabled("expiration_status_date", true);
	oa_setDisabled("expiration_start_date_from", true);
	oa_setDisabled("expiration_start_date_to", true);
	oa_setDisabled("expiration_end_date_from", true);
	oa_setDisabled("expiration_end_date_to", true);

	if (val == "0") {
		// 指定なし
	} if (val == "1") {
		// 状態指定日
		oa_setDisabled("expiration_status_date", false);
	} if (val == "2") {
		// 条件指定
		oa_setDisabled("expiration_start_date_from", false);
		oa_setDisabled("expiration_start_date_to", false);
		oa_setDisabled("expiration_end_date_from", false);
		oa_setDisabled("expiration_end_date_to", false);
	}		
}

/**
 * JAの変更イベントです。
 */
function oaex_ja_onChange() {
	let thisNode = document.getElementById("ja");

	if (thisNode.value.length > 0) {
		// 選択したＪＡで店舗を検索し、店舗を有効にする
		// todo: ここで、再検索
		document.getElementById("tempo").selectedIndex = 0;
		oa_setDisabled("tempo", false);
		oa_initSelect();
	} else {
		// 店舗をクリアし、店舗を無効にする
		// todo: ここで、クリア
		document.getElementById("tempo").selectedIndex = 0;
		oa_setDisabled("tempo", true);
		oa_initSelect();
	}
}

/**
 * サブシステムの変更イベントです。
 */
function oaex_subsystem_onChange() {
	// 取引グループリスト作成
	oaex_setBiztranGrpOption();
	oa_initSelect();

	// 取引リスト作成
	oaex_setBiztranOption();
	oa_initSelect();
}

/**
 * 取引グループの変更イベントです。
 */
function oaex_biztran_grp_onChange() {
	// 取引リスト作成
	oaex_setBiztranOption();
	document.getElementById("biztran").selectedIndex = 0;
	oa_initSelect();
}

/**
 * 取引の変更イベントです。
 */
function oaex_biztran_onChange() {
	document.getElementById("biztran_grp").selectedIndex = 0;
	oa_initSelect();
}

/**
 * 検索ボタンクリックイベントです。
 */
function oaex_searchBtn_onClick() {
	if (_isThymeleaf) {
		oaex_th_searchBtn_onClick(1);
		return;
	}

	// テーブル＆ページネーションを表示
	document.getElementById("suspend_biztran_table").style.visibility = "visible";
	document.getElementById("suspend_biztran_pagination").style.visibility = "visible";
}

/**
 * 閉じるボタンクリックイベントです。
 */
function oaex_closeBtn_onClick() {
	oa_transferForm("oa00000");
}


