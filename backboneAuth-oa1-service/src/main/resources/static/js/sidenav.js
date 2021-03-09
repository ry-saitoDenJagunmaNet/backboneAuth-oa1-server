/* bodyタグのpaddingLeft 初期値の退避用変数 */
_bodyPaddingLeft = "";

/**
 * HTML文書の読み込みと解析が完了したイベントです。
 */
document.addEventListener('DOMContentLoaded', function() {
	// bodyタグのpaddingLeft値を退避（sidenav Close時に戻す）
	let element = document.getElementsByTagName("body");
	let value = document.defaultView.getComputedStyle(element[0], '');
	_bodyPaddingLeft = value["paddingLeft"];

	// URLパラメータからsidenavのopen状態を取得（1:open）
	let sn = oa_getParam("sn");
	sn = sn.replace("#!", "");

    // Sidenav の初期化
    oa_initSidenav(sn);
});

/**
 * Sidenav の初期化です。
 * スライドアウトメニュー
 */
function oa_initSidenav(sn) {
	// preventScrolling：標準ではsidenav open時 コンテンツをoverlayで覆ってスクロールバーを非表示に制御している
	let options = {
		preventScrolling: false,					/* スクロールバーを非表示に制御しない */
		onOpenStart: oaex_sidenav_onOpenStart("0"),
		onOpenEnd: oaex_sidenav_onOpenEnd,
		onCloseStart: oaex_sidenav_onCloseStart
	}

	let elems = document.querySelectorAll('.sidenav');
	if (sn == "1") {
		// open時のアニメーション時間を無効
		options["inDuration"] = 0;
		options["onOpenStart"] = oaex_sidenav_onOpenStart("1");
	} 
	let instances = M.Sidenav.init(elems, options);

	if (sn == "1") {
		M.Sidenav.getInstance(elems[0]).open();
		// sidenavのopenフラグ（1:open）
		document.getElementById("sidenav_opne").value = "1";
	} else {
		// sidenavのopenフラグ（0:close）
		document.getElementById("sidenav_opne").value = "0";
	}
}

/**
 * sidenav open開始インベトです。
 * @param {String} sn sidenavのopen状態（1:open）
 */
function oaex_sidenav_onOpenStart(sn) {
	// コンテンツをsidenav分右に移動
	let bodyTag = document.getElementsByTagName("body");
	if (sn == "1") {
		bodyTag[0].style.transition = "";
	} else {
		bodyTag[0].style.transition = "all 0.2s";
	}
}

/**
 * sidenav open完了インベトです。
 */
function oaex_sidenav_onOpenEnd() {
	// コンテンツをsidenav分右に移動
	let bodyTag = document.getElementsByTagName("body");
	let slideOut = document.getElementById("slide-out");
	bodyTag[0].style.paddingLeft = (slideOut.offsetWidth + 10) + "px";

	// コンテンツを覆う半透明のOverlayを非表示にする
	// （標準ではsidenav open時、Overlayを表示する）
	let sidenavOverlay = document.getElementsByClassName("sidenav-overlay");
	sidenavOverlay[0].style.display = "none";
	sidenavOverlay[0].style.opacity = "0";
	// sidenavのopenフラグ（1:open）
	document.getElementById("sidenav_opne").value = "1";
}

/**
 * sidenav close開始インベトです。
 */
function oaex_sidenav_onCloseStart() {
	// コンテンツを左に移動して元にも戻す
	let bodyTag = document.getElementsByTagName("body");
	bodyTag[0].style.paddingLeft = _bodyPaddingLeft;

	// sidenavのopenフラグ（0:close）
	document.getElementById("sidenav_opne").value = "0";
	
	// Sidenav の初期化
	oa_initSidenav(null);
}

/**
 * サイドナビゲーションを取得（作成）します。
 * @param  {String} selectNavId 選択した画面ID
 * @return {String}} ナビゲーションのHTML
 */
function oa_getSidenav(selectNavId) {
	let html = '';
    let imgUrl = '../static/img/';
    if (document.getElementById("imageUrl") != null && document.getElementById("imageUrl").value != "") {
    	imgUrl = document.getElementById("imageUrl").value;
    }

	// html += '<a class="sidenav-trigger oa_sidenav_humberger" href="#" data-target="slide-out"><i class="material-icons">menu</i></a>';
	html += '<a class="sidenav-trigger oa_sidenav_open" href="#" data-target="slide-out"><img src="' + imgUrl + 'sidenav_open.png" alt="sidenav open"/></a>';
	html += '<ul class="sidenav" id="slide-out">';
	html += '	<li>';
	html += '		<div class="row oa_sidenav_header">';
	html += '			<div class="col s10 oa_sidenav_header_col">基幹系認証管理システム</div>';
	html += '			<div class="col s2">';
	// html += '				<a href="#!" class="sidenav-close" data-target="mobile-demo"><i class="material-icons">close</i></a>';
	html += '				<a href="#!" class="sidenav-close oa_sidenav_close" data-target="mobile-demo"><img src="' + imgUrl + 'sidenav_close.png" alt="sidenav close"/></a>';
	html += '			</div>';
	html += '		</div>';
	html += '	</li>';
	html += '	<li class="no-padding">';
	html += '		<ul class="collapsible">';
	
	html += '			<li class="active oa_collapsible_root">';
	html += '				<a class="waves-effect collapsible-header"><img src="' + imgUrl + 'collapsible.png" alt="collapsible"/>オペレータ</a>';
	html += '				<div class="collapsible-body">';
	html += oa_getSidenavRow(selectNavId, "OA11020", imgUrl + "oa11020.png", "新規登録", "… 新規登録を行います", []);
	html += oa_getSidenavRow(selectNavId, "OA11010", imgUrl + "oa11010.png", "オペレーター＜一覧＞", "… 下記の各取引を行う際の入口です", ["・更新（利用可否設定含む）", "・サブシステムロール付与", "・取引ロール付与", "・パスワードリセット"]);
	html += oa_getSidenavRow(selectNavId, "OA11100", imgUrl + "oa11100.png", "オペレーター履歴確認", "… 変更履歴の確認用に情報をExcelファイルに出力します", []);
	html += oa_getSidenavRow(selectNavId, "OA11090", imgUrl + "oa11090.png", "アカウントロック照会", "… アカウントのロック状態を確認、またアンロックを行います", []);
	html += oa_getSidenavRow(selectNavId, "OA11110", imgUrl + "oa11110.png", "パスワード変更履歴照会", "… パスワード変更履歴を照会します", []);
	html += oa_getSidenavRow(selectNavId, "OA11120", imgUrl + "oa11120.png", "サインイン証跡照会", "… サインイン及び、サインアウトの証跡を照会します", []);
	html += oa_getSidenavRow(selectNavId, "OA11130", imgUrl + "oa11130.png", "サービス呼出証跡照会", "… サーバーサービスの呼出証跡を照会します", []);
	html += '				</div>';
	html += '			</li>';

	html += '			<li class="active oa_collapsible_root">';
	html += '				<a class="waves-effect collapsible-header"><img src="' + imgUrl + 'collapsible.png" alt="collapsible"/>ロール</a>';
	html += '				<div class="collapsible-body">';
	html += oa_getSidenavRow(selectNavId, "OA11140", imgUrl + "oa11140.png", "ロール確認", "… 各ロール設定の確認用に情報をExcelファイルに出力します", []);
	html += oa_getSidenavRow(selectNavId, "OA11060", imgUrl + "oa11060.png", "サブシステムロールオペレーター割当", "…サブシステムロールに対してオペレーターを割り当てます", []);
	html += oa_getSidenavRow(selectNavId, "OA11080", imgUrl + "oa11080.png", "取引ロールオペレーター割当", "…取引ロールに対してオペレーターを割り当てます", []);
	html += '				</div>';
	html += '			</li>';

	html += '			<li class="active oa_collapsible_root">';
	html += '				<a class="waves-effect collapsible-header"><img src="' + imgUrl + 'collapsible.png" alt="collapsible"/>パスワード変更</a>';
	html += '				<div class="collapsible-body">';
	html += oa_getSidenavRow(selectNavId, "OA11040", imgUrl + "oa11040.png", "パスワード変更", "… 現在サインインしている自分のパスワードを変更します", []);
	html += '				</div>';
	html += '			</li>';

	html += '			<li class="active oa_collapsible_root">';
	html += '				<a class="waves-effect collapsible-header"><img src="' + imgUrl + 'collapsible.png" alt="collapsible"/>電算センターオペレーター専用</a>';
	html += '				<div class="collapsible-body">';
	html += oa_getSidenavRow(selectNavId, "OA12010", imgUrl + "oa12010.png", "取引ロール編成インポート＆エクスポート", "… 取引ロールと取引グループの編成、", ["取引グループと取引の編成 を一括で登録または出力します"]);
	html += oa_getSidenavRow(selectNavId, "OA12020", imgUrl + "oa12020.png", "一時取引抑止<一覧>", "… 下記の各取引を行う際の入口です", ["・一時取引抑止登録", "・一時取引抑止更新"]);
	html += oa_getSidenavRow(selectNavId, "OA12040", imgUrl + "oa12040.png", "システム利用可能時間帯メンテナンス", "… 各サブシステムの曜日毎の利用可能時間帯を設定します", []);
	html += oa_getSidenavRow(selectNavId, "OA12060", imgUrl + "oa12060.png", "カレンダーメンテナンス", "… 各サブシステムで使用する稼働日カレンダーを設定します", []);
	html += oa_getSidenavRow(selectNavId, "OA12070", imgUrl + "oa12070.png", "ＪＡ割当IPアドレス範囲メンテナンス", "… 各ＪＡに割り当てているIPアドレスを設定します", []);
	html += '				</div>';
	html += '			</li>';

	html += '			<li class="active oa_collapsible_root">';
	html += '				<a class="waves-effect collapsible-header"><img src="' + imgUrl + 'collapsible.png" alt="collapsible"/>サインアウト</a>';
	html += '				<div class="collapsible-body">';
	html += oa_getSidenavRow(selectNavId, "ED00010", imgUrl + "ed00010.png", "サインアウト", "… サインアウトし基幹系認証管理システムを終了します", []);
	html += '				</div>';
	html += '			</li>';


	html += '		</ul>';
	html += '	</li>';
	html += '</ul>';
	html += '<input type="hidden" id="sidenav_opne" value="1">';

	document.write(html);
}

/**
 * サイドナビゲーションの行を取得（作成）します。
 * @param  {String} selectNavId 選択した画面ID
 * @param  {String} navId 作成対象の画面ID
 * @param  {String} img イメージアイコンのURL
 * @param  {String} title ナビゲーションのタイトル
 * @param  {String} description ナビゲーションの説明
 * @param  {String[]} descriptions ナビゲーションの説明配列
 * @return {String}} ナビゲーション１行のHTML
 */
function oa_getSidenavRow(selectNavId, navId, img, title, description, descriptions) {
	let html = '';

	if (selectNavId.toLowerCase() == navId.toLowerCase()) {
		html += '<div class="oa_sidenav_row oa_sidenav_row_selected">';
	} else {
		html += '<div class="waves-effect oa_sidenav_row">';
		html += '	<a href="./' + navId.toLowerCase() + '.html?sn=1">';
	}
	html += '		<div class="oa_display_table">';
	html += '			<div class="oa_display_table_col oa_sidenav_icon_col">';
	html += '				<img class="responsive-img oa_sidenav_icon" src="'+ img +'" alt="' + title + '"/>';
	html += '			</div>';
	html += '			<div class="oa_display_table_col oa_sidenav_guide_col">';
	html += '				<div>' + title + '</div>';
	html += '				<div class="oa_sidenav_guide_description">' + description + '</div>';

	descriptions.forEach(function( value ) {
		html += '				<div class="oa_sidenav_guide_descriptions">' + value + '</div>';
	});

	html += '			</div>';
	html += '		</div>';
	if (selectNavId.toLowerCase() != navId.toLowerCase()) {
		html += '	</a>';
	}
	html += '</div>';
	
	return html
}