package net.jagunma.backbone.auth.authmanager.infrastructure.web.oa12060;

import net.jagunma.backbone.auth.authmanager.application.service.oa12060.Oa12060EntryService;
import net.jagunma.backbone.auth.authmanager.application.service.oa12060.Oa12060InitService;
import net.jagunma.backbone.auth.authmanager.application.service.oa12060.Oa12060SearchService;
import net.jagunma.backbone.auth.authmanager.infrastructure.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infrastructure.web.oa12060.vo.Oa12060EntryResponseVo;
import net.jagunma.backbone.auth.authmanager.infrastructure.web.oa12060.vo.Oa12060SearchResponseVo;
import net.jagunma.backbone.auth.authmanager.infrastructure.web.oa12060.vo.Oa12060Vo;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * OA12060コントローラ
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA12060
 * 機能名：カレンダーメンテナンス
 * サービスID：OA12060
 * サービス名：OA12060サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA12060", name = "カレンダーメンテナンス")
@ServiceInfo(id = "OA12060", name = "OA12060サービス")
@Controller
@RequestMapping(path = "oa12060")
public class Oa12060Controller extends BaseOfController {

	private static final Logger LOGGER = LoggerFactory.getLogger(Oa12060Controller.class);
	private final Oa12060InitService oa12060InitService;
	private final Oa12060SearchService oa12060SearchService;
	private final Oa12060EntryService oa12060EntryService;

	// コンストラクタ
	public Oa12060Controller(
		Oa12060InitService oa12060InitService,
		Oa12060SearchService oa12060SearchService,
		Oa12060EntryService oa12060EntryServic) {

		this.oa12060InitService = oa12060InitService;
		this.oa12060SearchService = oa12060SearchService;
		this.oa12060EntryService = oa12060EntryServic;
	}

	/**
	 * 画面を初期表示します。
	 *
	 * @param model モデル
	 * @return view名
	 */
	@GetMapping(path = "/get")
	private String get(Model model) {

		Oa12060Vo vo = new Oa12060Vo();
		Oa12060SearchResponseVo responseVo = new Oa12060SearchResponseVo();
		try {
			// 画面を初期化
			oa12060InitService.init(vo);

			// カレンダー検索
			oa12060SearchService.search(vo, responseVo);

			model.addAttribute("form", vo);
			return "oa12060";
		} catch (
		GunmaRuntimeException gre) {
			// 業務例外が発生した場合
			vo.setExceptionMessage(gre);
			model.addAttribute("form", vo);
			return "oa12060";
		} catch (RuntimeException re) {
			// その他予期せぬ例外が発生した場合
			model.addAttribute("form", vo);
			vo.setExceptionMessage(re);
			return "oa12060";
		}
	}

	/**
	 * カレンダー検索処理を行います。
	 *
	 * @param vo 検索条件（form json）
	 * @return カレンダー検索結果
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Oa12060SearchResponseVo> search(Oa12060Vo vo) {

		Oa12060SearchResponseVo responseVo = new Oa12060SearchResponseVo();
		try {
			// カレンダー検索
			oa12060SearchService.search(vo, responseVo);

			return new ResponseEntity<>(responseVo, HttpStatus.OK);
		} catch (GunmaRuntimeException gre) {
			// 業務例外が発生した場合
			responseVo.setExceptionMessage(gre);
			return new ResponseEntity<>(responseVo, HttpStatus.OK);
		} catch (RuntimeException re) {
			// その他予期せぬ例外が発生した場合
			responseVo.setExceptionMessage(re);
			return new ResponseEntity<>(responseVo, HttpStatus.OK);
		}
	}

	/**
	 * カレンダー登録処理を行います。
	 *
	 * @param vo カレンダー稼働日登録（form json）
	 * @return カレンダー登録結果
	 */
	@RequestMapping(value = "/entry", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Oa12060EntryResponseVo> entry(Oa12060Vo vo) {
		//TODO: パラメータでサインインオペレーターの情報を取得する
		setAuthInf();

		Oa12060EntryResponseVo responseVo = new Oa12060EntryResponseVo();
		try {
			// カレンダー登録
			oa12060EntryService.entry(vo, responseVo);

			return new ResponseEntity<>(responseVo, HttpStatus.OK);
		} catch (org.seasar.doma.jdbc.OptimisticLockException ole) {
			//楽観的ロック
			responseVo.setExceptionMessage(ole);
			return new ResponseEntity<>(responseVo, HttpStatus.OK);
		} catch (GunmaRuntimeException gre) {
			// 業務例外が発生した場合
			responseVo.setExceptionMessage(gre);
			return new ResponseEntity<>(responseVo, HttpStatus.OK);
		} catch (RuntimeException re) {
			// その他予期せぬ例外が発生した場合
			responseVo.setExceptionMessage(re);
			return new ResponseEntity<>(responseVo, HttpStatus.OK);
		}
	}
}
