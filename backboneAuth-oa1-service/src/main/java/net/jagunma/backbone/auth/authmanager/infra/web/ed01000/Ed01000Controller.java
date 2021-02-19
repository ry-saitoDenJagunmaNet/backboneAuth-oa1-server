package net.jagunma.backbone.auth.authmanager.infra.web.ed01000;

import javax.servlet.http.HttpServletRequest;
import net.jagunma.backbone.auth.authmanager.application.commandService.SignIn;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01000.vo.Ed01000Vo;
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
 * 機能ID：ED01000
 * 機能名：サインイン
 * サービスID：ED01000
 * サービス名：ED01000サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "?", name = "ＸＸＸＸＸ") // Todo: 要調査後反映 システムコード・システム名称
@SubSystemInfo(id = "ED", name = "経済・共通")
@FeatureGroupInfo(id = "ED0", name = "基幹系認証")
@FeatureInfo(id = "ED01000", name = "サインイン")
@ServiceInfo(id = "ED01000", name = "ED01000サービス")
@Controller
@RequestMapping(path = "ed01000")
public class Ed01000Controller extends BaseOfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ed01000Controller.class);

    private final SignIn signIn;

    // コンストラクタ
    public Ed01000Controller(SignIn signIn) {
        this.signIn = signIn;
    }

    /**
     * サインインを行います
     *
     * @param redirect_uri HttpListenerのリダイレクト用URL（ループバックアドレス＋ポート）
     * @param model        モデル
     * @return 認証結果
     */
    @GetMapping(path = "/get")
    public String get(@RequestParam(name = "redirect_uri") String redirect_uri, Model model) {

        LOGGER.debug("get START");

        Ed01000Vo vo = new Ed01000Vo();
        try {
            vo.setMode((int) SignInCause.サインイン.getCode());
            // ToDo: oa2Serverで認証コードを取得する

            model.addAttribute("form", vo);
            return "ed01000";
        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "ed01000";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }

    /**
     * サインインを行います
     *
     * @param model モデル
     * @param vo    ViewObject
     * @return view名
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(HttpServletRequest request, Model model, Ed01000Vo vo) {

        LOGGER.debug("signIn START");

        try {
            // リクエストを作成
            Ed01000SignInConverter converter = Ed01000SignInConverter.with(
                vo.getOperatorCode(), vo.getPassword(), request.getRemoteAddr(), vo.getMode());
            Ed01000SignInPresenter presenter = new Ed01000SignInPresenter();
            // サインインサービス実行
            signIn.execute(converter, presenter);

            if (presenter.isSignInResultSuccess()) {
                // ToDo: HttpListenerレスポンス に認証結果を送信する
                // ToDo: 下記は暫定で自画面に戻る記述
                model.addAttribute("form", vo);
                return "ed01000";
            }

            // サインインに失敗
            throw new GunmaRuntimeException("EOA12006");

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "ed01000";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }
}
