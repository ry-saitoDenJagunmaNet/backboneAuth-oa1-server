package net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010;

import net.jagunma.backbone.auth.authmanager.application.service.oa11010.Oa11010InitService;
import net.jagunma.backbone.auth.authmanager.application.service.oa11010.Oa11010SearchService;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010SearchResponseVo;
import net.jagunma.backbone.auth.authmanager.infrastructure.controller.web.oa11010.vo.Oa11010Vo;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * OA11010コントローラ
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA11010
 * 機能名：オペレーター＜一覧＞
 * サービスID：OA11010
 * サービス名：OA11010サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA11010", name = "オペレーター＜一覧＞")
@ServiceInfo(id = "OA11010", name = "OA11010サービス")
@Controller
@RequestMapping(path = "oa11010")
public class Oa11010Controller {

	private static final Logger LOGGER = LoggerFactory.getLogger(Oa11010Controller.class);
	private final Oa11010InitService oa11010InitService;
	private final Oa11010SearchService oa11010SearchService;

	public Oa11010Controller(Oa11010InitService oa11010InitService,
		Oa11010SearchService oa11010SearchService) {
		this.oa11010InitService = oa11010InitService;
		this.oa11010SearchService = oa11010SearchService;
	}

	/**
	 * 画面を初期表示します。
	 *
	 * @param model モデル
	 * @return view名
	 */
	@GetMapping(path = "/get")
	private String get(Model model) {
		//TODO: パラメータでサインインオペレーターの情報を取得する
		//AuditInfoHolder.

		Oa11010Vo response = new Oa11010Vo();

		Oa11010InitPresenter presenter = new Oa11010InitPresenter();
		oa11010InitService.init(presenter);

		presenter.bindTo(response);

		model.addAttribute("form", response);
		return "oa11010";
	}

	/**
	 * オペレーター検索処理を行います。
	 *
	 * @param oa11010Vo 検索条件（form json）
	 * @return オペレーター検索結果
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Oa11010SearchResponseVo> search(Oa11010Vo oa11010Vo) {

		System.out.println("### pageno=" + oa11010Vo.getPageNo());

		Oa11010SearchResponseVo response = new Oa11010SearchResponseVo();
		Oa11010SearchValidator.with(oa11010Vo).validate();
		Oa11010SearchConverter converter = Oa11010SearchConverter.with(oa11010Vo);
		Oa11010SearchPresenter presenter = new Oa11010SearchPresenter();

		//オぺレーター検索してオぺレーターテーブルHtmlを作成
		oa11010SearchService.search(converter, presenter);

		presenter.bindTo(response);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
