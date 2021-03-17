package net.jagunma.backbone.auth.authmanager.infra.web.ed01010;

import net.jagunma.backbone.auth.authmanager.application.commandService.UpdatePassword;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchOperator;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01010.vo.Ed01010Vo;
import net.jagunma.backbone.auth.authmanager.model.types.PasswordChangeType;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
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
 * システム：? ＸＸＸＸＸ Todo: 要調査後反映 システムコード・システム名称
 * サブシステム：ED 経済・共通
 * 機能グループID：ED0
 * 機能グループ名：基幹系認証
 * 機能ID：ED01010
 * 機能名：パスワード入力
 * サービスID：ED01010
 * サービス名：ED01010サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "?", name = "ＸＸＸＸＸ") // Todo: 要調査後反映 システムコード・システム名称
@SubSystemInfo(id = "ED", name = "経済・共通")
@FeatureGroupInfo(id = "ED0", name = "基幹系認証")
@FeatureInfo(id = "ED01010", name = "パスワード入力")
@ServiceInfo(id = "ED01010", name = "ED01010サービス")
@Controller
@RequestMapping(path = "ed01010")
public class Ed01010Controller extends BaseOfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ed01010Controller.class);

    private final UpdatePassword updatePassword;
    private final SearchOperator searchOperator;

    // コンストラクタ
    public Ed01010Controller(
        UpdatePassword updatePassword,
        SearchOperator searchOperator) {
        this.updatePassword = updatePassword;
        this.searchOperator = searchOperator;
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
            Ed01010InitConverter converter = Ed01010InitConverter.with(operatorId);
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

                // サインイン直後のパスワード変更要求の場合
                if (vo.getRedirectUri().length() > 0 && vo.getAccessToken().length() > 0) {
                    return String.format("redirect:%1$s?access_token=%2$s",vo.getRedirectUri(), vo.getAccessToken());
                }
            }

            model.addAttribute("form", vo);
            return "ed01010"; // ToDo: 遷移制御

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
     * 画面を初期表示します
     *
     * @param operatorId オペレーターID
     * @param redirectUri リダイレクトURI
     * @param accessToken アクセストークン
     * @param model モデル
     * @return view名
     */
    @GetMapping(path = "/getForUpdate")
    public String getForUpdate(@RequestParam("operatorId") Long operatorId,
        @RequestParam("redirect_uri") String redirectUri,
        @RequestParam("access_token") String accessToken,
        Model model) {

        // ToDo: テストサインイン情報セット
        setAuthInf();

        Ed01010Vo vo = new Ed01010Vo();
        vo.setMode("Change");
        vo.setOperatorId(operatorId);

        try {
            Ed01010InitConverter converter = Ed01010InitConverter.with(operatorId);
            Ed01010InitPresenter presenter = new Ed01010InitPresenter();

            searchOperator.execute(converter, presenter);

            presenter.bindTo(vo);
            vo.setRedirectUri(redirectUri);
            vo.setAccessToken(accessToken);

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
