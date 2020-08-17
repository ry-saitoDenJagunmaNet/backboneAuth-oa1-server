package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11020;

import net.jagunma.backbone.auth.authmanager.application.service.oa11020.Oa11020EntryService;
import net.jagunma.backbone.auth.authmanager.application.service.oa11020.Oa11020InitService;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11020.vo.Oa11020Vo;
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
 * OA11020 コントローラー
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA11020
 * 機能名：オペレーター登録
 * サービスID：OA11020
 * サービス名：OA11020サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA11020", name = "オペレーター登録")
@ServiceInfo(id = "OA11020", name = "OA11020サービス")
@Controller
@RequestMapping(path = "oa11020")
public class Oa11020Controller {

	private static final Logger LOGGER = LoggerFactory.getLogger(Oa11020Controller.class);
	private final Oa11020InitService oa11020InitService;
	private final Oa11020EntryService oa11020EntryService;

	/**
	 * コンストラクタ
	 */
	public Oa11020Controller(
		Oa11020InitService oa11020InitService,
		Oa11020EntryService oa11020EntryService) {
		this.oa11020InitService = oa11020InitService;
		this.oa11020EntryService = oa11020EntryService;
	}

	/**
	 * 画面を初期表示します。
	 *
	 * @param model モデル
	 * @return view名
	 */
	@GetMapping(path = "/get")
	private String get(Model model) {

		Oa11020Vo vo = new Oa11020Vo();

		try {
			// 画面初期化
			oa11020InitService.init(vo);

			model.addAttribute("form", vo);
			return "oa11020";

		} catch (
			GunmaRuntimeException gre) {
			// 業務例外が発生した場合
			vo.setExceptionMessage(gre);
			model.addAttribute("form", vo);
			return "oa11020";
		} catch (RuntimeException re) {
			// その他予期せぬ例外が発生した場合
			model.addAttribute("form", vo);
			vo.setExceptionMessage(re);
			return "oa11020";
		}
	}

	/**
	 * 登録処理を行います。
	 *
	 * @param vo 登録内容
	 * @return
	 */
	@RequestMapping(value = "/entry", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Oa11020Vo> entry(Oa11020Vo vo) {
		Oa11020Vo responseVo = new Oa11020Vo();

		try {
			// 登録
			oa11020EntryService.entry(vo);

			return new ResponseEntity<>(responseVo, HttpStatus.OK);

		} catch (GunmaRuntimeException gre) {
			// 業務例外が発生した場合
			responseVo.setExceptionMessage(gre);
			return  new ResponseEntity<>(responseVo, HttpStatus.OK);
		} catch (RuntimeException re) {
			// その他予期せぬ例外が発生した場合
			responseVo.setExceptionMessage(re);
			return  new ResponseEntity<>(responseVo, HttpStatus.OK);
		}
	}
}
