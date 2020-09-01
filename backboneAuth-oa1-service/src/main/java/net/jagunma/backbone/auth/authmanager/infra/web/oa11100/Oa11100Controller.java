package net.jagunma.backbone.auth.authmanager.infra.web.oa11100;

import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.Oa11010Controller;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * OA11100コントローラ
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA11100
 * 機能名：オペレーター履歴確認
 * サービスID：OA11100
 * サービス名：OA11100サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA11100", name = "オペレーター履歴確認")
@ServiceInfo(id = "OA11100", name = "OA11100サービス")
@Controller
@RequestMapping(path = "oa11100")
public class Oa11100Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa11010Controller.class);

    /**
     * 画面を初期表示します。
     *
     * @param model モデル
     * @return view名
     */
    @GetMapping(path = "/get")
    private String get(@RequestParam("oi") String operatorId, Model model) {
        System.out.println("### operatorId=" + operatorId);
        return "oa11100";
    }
}
