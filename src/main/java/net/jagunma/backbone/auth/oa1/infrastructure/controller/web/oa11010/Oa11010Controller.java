package net.jagunma.backbone.auth.oa1.infrastructure.controller.web.oa11010;

import net.jagunma.backbone.auth.application.queryService.OperatorReferenceService;
import net.jagunma.backbone.auth.application.queryService.TempoReferenceService;
import net.jagunma.backbone.auth.oa1.application.service.oa11010.Oa11010InitService;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * OA11010コントローラ.
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
	private Oa11010InitService oa11010InitService;
	private OperatorReferenceService operatorReferenceService;
	private TempoReferenceService tempoReferenceService;

	public Oa11010Controller(Oa11010InitService oa11010InitService,
							 OperatorReferenceService operatorReferenceService,
							 TempoReferenceService tempoReferenceService) {
		this.oa11010InitService = oa11010InitService;
		this.operatorReferenceService = operatorReferenceService;
		this.tempoReferenceService = tempoReferenceService;
	}

	@GetMapping(path = "/get")
	private String get(Model model) {
		// Form初期化
		model.addAttribute("form", oa11010InitService.initForm());
		// 店舗コンボボックスリスト作成
		model.addAttribute("tempos", tempoReferenceService.getComboBoxList());

		return "oa11010";
	}

	@PostMapping(path = "/search")
	public String search(@ModelAttribute("oa11010Model") Oa11010Model oa11010Model,
		                  Model model) {

		model.addAttribute("form", oa11010Model);
		// 店舗コンボボックスリスト作成
		model.addAttribute("tempos", tempoReferenceService.getComboBoxList());
		// オペレータ検索
		model.addAttribute("operatorList", operatorReferenceService.getOperatorList());

		return "oa11010";
	}

}
