package net.jagunma.backbone.auth.authmanager.infra.web.ed01010;

import net.jagunma.backbone.auth.authmanager.application.commandService.UpdatePassword;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchOperator;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01010.vo.Ed01010Vo;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ED01010 コントローラー
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：ED01010
 * 機能名：パスワード入力
 * サービスID：ED01010
 * サービス名：ED01010サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "ED01010", name = "パスワード入力")
@ServiceInfo(id = "ED01010", name = "ED01010サービス")
@Controller
@RequestMapping(path = "ED01010")
public class Ed01010Controller extends BaseOfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ed01010Controller.class);

    private final SearchOperator searchOperator;
    private final UpdatePassword updatePassword;

    // コンストラクタ
    public Ed01010Controller(
        SearchOperator searchOperator,
        UpdatePassword updatePassword) {
        this.searchOperator = searchOperator;
        this.updatePassword = updatePassword;
    }

    /**
     * 画面を初期表示します
     *
     * @param mode モード
     * @param operatorId オペレーターID
     * @param model モデル
     * @return view名
     */
    @GetMapping(path = "/get")
    public String get(@RequestParam("mode") String mode, @RequestParam("operatorId") Long operatorId, Model model) {
        // ToDo: テストサインイン情報セット
        setAuthInf();

        Ed01010Vo vo = new Ed01010Vo();
        vo.setMode(mode);
        vo.setOperatorId(operatorId);

        try {
            Ed01010InitConverter converter = Ed01010InitConverter.with(vo);
            Ed01010InitPresenter presenter = new Ed01010InitPresenter();

            searchOperator.execute(converter, presenter);

            presenter.bindTo(vo);

            model.addAttribute("form", vo);
            return "ed01010";

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "ed01010";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }

    /**
     * 更新処理を行います
     *
     * @param model モデル
     * @param vo ViewObject
     * @return view名
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model, Ed01010Vo vo) {
        // ToDo: テストサインイン情報セット
        setAuthInf();

        try {

            if (vo.getMode().equals("Reset")) {
                Ed01010ResetConverter converter = Ed01010ResetConverter.with(vo);
                updatePassword.execute(converter);
            }
            if (vo.getMode().equals("Change")) {
                Ed01010ChangeConverter converter = Ed01010ChangeConverter.with(vo);
                updatePassword.execute(converter);
            }

            // ToDo: 遷移制御
            model.addAttribute("form", vo);
            return "ed01010";

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "ed01010";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }
}
