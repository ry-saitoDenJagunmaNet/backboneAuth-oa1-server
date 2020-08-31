/**
 * サブシステムセレクトチェンジイベントです。
 */
function oaex_subsystem_onChange() {
	let subsystem = document.getElementById("subsystem");
	let biztran_role = document.getElementById("biztran_role");

	// 格納リスト（<option>タグ）クリア
	while(biztran_role.lastChild)
	{
		biztran_role.removeChild(biztran_role.lastChild);
	}

	// 格納リスト（<option>タグ）セット
	biztran_role.options[0] = new Option("", "");
	switch (subsystem.value) {
		case "KB":
			biztran_role.options[1] = new Option("KBAG01 （購買）購買業務基本", "KBAG01");
			biztran_role.options[2] = new Option("KBAG02 （購買）本所業務", "KBAG02");
			biztran_role.options[3] = new Option("KBAG03 （購買）本所管理業務", "KBAG03");
			biztran_role.options[4] = new Option("KBAG04 （購買）バラエサ処理", "KBAG04");
			biztran_role.options[5] = new Option("KBAG05 （購買）在庫管理", "KBAG05");
			biztran_role.options[6] = new Option("KBAG06 （購買）支払業務", "KBAG06");
			biztran_role.options[7] = new Option("KBAG07 （購買）受入業務", "KBAG07");
			biztran_role.options[8] = new Option("KBAG08 （購買）受注管理", "KBAG08");
			biztran_role.options[9] = new Option("KBAG09 （購買）未収金管理", "KBAG09");
			biztran_role.options[10] = new Option("KBAG10 （購買）損害金利息管理", "KBAG10");
			biztran_role.options[11] = new Option("KBAG11 （購買）配送管理", "KBAG11");
			biztran_role.options[12] = new Option("KBAG12 （購買）LPG担当者", "KBAG12");
			biztran_role.options[13] = new Option("KBAG13 （購買）LPG入金", "KBAG13");
			biztran_role.options[14] = new Option("KBAG14 （購買）レジ担当者", "KBAG14");
			biztran_role.options[15] = new Option("KBAG15 （購買）支所マスタ担当者", "KBAG15");
			biztran_role.options[16] = new Option("KBAG16 （購買）食材担当者", "KBAG16");
			biztran_role.options[17] = new Option("KBAG17 （購買）購買業務基本２", "KBAG17");
			biztran_role.options[18] = new Option("KBAG18 （購買）窓口供給（店舗用２）", "KBAG18");
			biztran_role.options[19] = new Option("KBAG19 （購買）合併", "KBAG19");
			biztran_role.options[20] = new Option("KBAG20 （購買）購買業務基本３", "KBAG20");
			biztran_role.options[21] = new Option("KBAG50 （購買）マスタ移行", "KBAG50");
			biztran_role.options[22] = new Option("KBAG99 （購買）電算センター", "KBAG99");
			break;

		case "YS":
			biztran_role.options[1] = new Option("YSAG10 （青果）管理者", "YSAG10");
			biztran_role.options[2] = new Option("YSAG20 （青果）精算担当", "YSAG20");
			biztran_role.options[3] = new Option("YSAG30 （青果）集荷場担当", "YSAG30");
			biztran_role.options[4] = new Option("YSAG40 （青果）単価計算担当", "YSAG40");
			biztran_role.options[5] = new Option("YSAG50 （青果）入力担当", "YSAG50");
			biztran_role.options[6] = new Option("YSAG60 （青果）安定基金管理者", "YSAG60");
			biztran_role.options[7] = new Option("YSAG70 （青果）支所担当", "YSAG70");
			biztran_role.options[8] = new Option("YSAG90 （青果）電算センター維持管理担当者", "YSAG90");
			break;

		case "YF":
			biztran_role.options[1] = new Option("YFAG10 （花卉）管理者", "YFAG10");
			biztran_role.options[2] = new Option("YFAG20 （花卉）精算担当", "YFAG20");
			biztran_role.options[3] = new Option("YFAG30 （花卉）集荷場担当", "YFAG30");
			biztran_role.options[4] = new Option("YFAG40 （花卉）単価計算担当", "YFAG40");
			biztran_role.options[5] = new Option("YFAG50 （花卉）入力担当", "YFAG50");
			biztran_role.options[6] = new Option("YFAG70 （花卉）支所担当", "YFAG70");
			biztran_role.options[7] = new Option("YFAG90 （花卉）電算センター維持管理担当者", "YFAG90");
			break;
				
		case "HK":
			biztran_role.options[1] = new Option("HKAG10 （米）ＪＡ取引全般", "HKAG10");
			biztran_role.options[2] = new Option("HKAG15 （米）ＪＡ取引振込以外全般", "HKAG15");
			biztran_role.options[3] = new Option("HKAG99 （米）センター維持管理担当者", "HKAG99");
			break;
		
		case "HM":
			biztran_role.options[1] = new Option("HMAG20 （麦）ＪＡ取引全般", "HMAG20");
			biztran_role.options[2] = new Option("HMAG25 （麦）ＪＡ取引振込以外全般", "HMAG25");
			biztran_role.options[3] = new Option("HMAG99 （麦）センター維持管理担当者", "HMAG99");
			break;

		case "AN":
			biztran_role.options[1] = new Option("ANAG01 （畜産）取引全般", "ANAG01");
			biztran_role.options[2] = new Option("ANAG02 （畜産）維持管理担当者", "ANAG02");
			biztran_role.options[3] = new Option("ANAG98 （畜産）センター維持管理担当者", "ANAG98");
			biztran_role.options[4] = new Option("ANAG99 （畜産）維持管理責任者", "ANAG99");
			break;

		default:
			break;
	}

	oa_initSelect();
	
}
