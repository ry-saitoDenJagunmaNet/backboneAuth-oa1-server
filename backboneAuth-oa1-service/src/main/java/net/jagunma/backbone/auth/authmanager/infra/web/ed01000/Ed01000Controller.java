package net.jagunma.backbone.auth.authmanager.infra.web.ed01000;

import javax.servlet.http.HttpServletRequest;
import net.jagunma.backbone.auth.authmanager.application.commandService.SignIn;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01000.vo.Ed01000Vo;
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

    private SignIn signIn;

    // コンストラクタ
    public Ed01000Controller(SignIn signIn) {
        this.signIn = signIn;
    }

    /**
     * 継続サインインを行います
     *
     * @param client_id     クライアントID
     * @param redirect_uri  HttpListenerのリダイレクト用URL（ループバックアドレス＋ポート）
     * @param state         リダイレクトして返される値 ※リクエストした値と一致しない場合、認証不正と判定
     * @param code          アクセストークン取得時に使用するコード
     * @param response_type
     * @param scope
     * @param code_challenge
     * @param code_challenge_method
     * @param login_hint
     * @param access_token アクセストークン
     * @return 認証結果
     */
    @GetMapping(path = "/get")
    public String get(
        @RequestParam(name = "client_id") String client_id,
        @RequestParam(name = "redirect_uri") String redirect_uri,
        @RequestParam(name = "state") String state,
        @RequestParam(name = "code") String code,
        @RequestParam(name = "response_type", required = false) String response_type,
        @RequestParam(name = "scope", required = false) String scope,
        @RequestParam(name = "code_challenge", required = false) String code_challenge,
        @RequestParam(name = "code_challenge_method", required = false) String code_challenge_method,
        @RequestParam(name = "login_hint", required = false) String login_hint,
        @RequestParam(name = "access_token", required = false) String access_token,
        Model model) {

        LOGGER.debug("get START");

        Ed01000Vo vo = new Ed01000Vo(client_id, redirect_uri, state, code, access_token);
        try {
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
     * @param vo ViewObject
     * @return view名
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(HttpServletRequest request, Model model, Ed01000Vo vo) {

        LOGGER.debug("signIn START");

        try {
            // リクエストを作成
            Ed01000SignInConverter converter = Ed01000SignInConverter.with(
                vo.getOperatorCode(), vo.getPassword(), request.getRemoteAddr(), vo.getMode(), vo.getAccessToken());
            Ed01000SignInPresenter presenter = new Ed01000SignInPresenter();
            // サインインサービス実行
            signIn.execute(converter, presenter);

            if (presenter.isSignInResultSuccess()) {
                // ToDo: HttpListenerレスポンス に認証結果を送信する
                // ToDo: 下記は暫定で自画面に戻る記述
                model.addAttribute("form", vo);
                return "ed01000";
            }

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
