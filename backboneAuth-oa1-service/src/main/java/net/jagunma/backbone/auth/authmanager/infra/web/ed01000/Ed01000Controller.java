package net.jagunma.backbone.auth.authmanager.infra.web.ed01000;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.jagunma.backbone.auth.authmanager.application.commandService.SignIn;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01000.dto.Ed01000Dto;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01000.vo.Ed01000Vo;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.common.common.constant.SpecialOperator;
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

    private final String SESSIONKEY_STRING_MAP = "session_ed01000";

    private final SignIn signIn;

    // コンストラクタ
    public Ed01000Controller(SignIn signIn) {
        this.signIn = signIn;
    }

    /**
     * 認証を行います
     *
     * @param redirect_uri HttpListenerのリダイレクト用URL（ループバックアドレス＋ポート）
     * @param request      HttpServletRequest
     * @param model        モデル
     * @return 認証結果
     */
    @GetMapping(path = "/get")
    public String get(@RequestParam(name = "redirect_uri") String redirect_uri, HttpServletRequest request, Model model) {

        LOGGER.debug("get START");

        // 未ログインオペレータを設定
        setAuditInfoHolder(SpecialOperator.NON_LOGIN_OPERATOR.simpleOperator());

        Ed01000Vo vo = new Ed01000Vo();
        try {
            String clientId = "";
            String scope = "";
            String state = createRandomString(32);
            Map<String, String> sessionStringMap = new HashMap<>();
            sessionStringMap.put("redirect_uri", redirect_uri);
            sessionStringMap.put("state", state);

            // リダイレクトuri（サインインの呼び出し元）をSessionに格納
            setSessionAttribute(SESSIONKEY_STRING_MAP, sessionStringMap);

            // ToDo: Oa2認証Apiで認証コードを取得する
            String oAuthRedirectUri = request.getScheme() + "://"
                + "145.254.211.73"
                + ":" + request.getServerPort()
                + "/" + "ed01000/oAuthReception";
            String responseType = "code";

            // ToDo: 暫定でOa2認証Apiからリダイレクトされた提でoAuthRedirectメソッドにリダイレクト
//            StringBuilder uri = new StringBuilder();
//            uri.append("redirect:");
//            uri.append(oAuthRedirectUri);
//            uri.append("?code=").append("code12345");
//            uri.append("&state=").append(state);
//            return uri.toString();
            return oAuthReception("", "code12345", state, model);

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
     * 認証コード取得リクエストから戻りサインインを行います
     *
     * @param error エラーメッセージ
     * @param code  認可コード
     * @param state リクエストに含めた state
     * @param model モデル
     * @return 認証結果
     */
    @GetMapping(path = "/oAuthReception")
    public String oAuthReception(@RequestParam(name = "error", required = false) String error,
        @RequestParam(name = "code") String code,
        @RequestParam(name = "state") String state,
        Model model) {

        LOGGER.debug("oAuthReception START");

        // コードが取得できない場合
        if (code.length() == 0) { throw new GunmaRuntimeException("EOA10001"); }

        // リダイレクトuri（サインインの呼び出し元）、stateをSessionから取出
        Map<String, String> sessionStringMap = (Map) getSessionAttribute(SESSIONKEY_STRING_MAP);
        String redirectUri = sessionStringMap.get("redirect_uri");
        String requestState =sessionStringMap.get("state");

        // コードが取得できない場合
        if (!state.equals(requestState)) { throw new GunmaRuntimeException("EOA10001"); }

        // 未ログインオペレータを設定
        setAuditInfoHolder(SpecialOperator.NON_LOGIN_OPERATOR.simpleOperator());

        Ed01000Vo vo = new Ed01000Vo();
        try {
            vo.setRedirectUri(redirectUri);
            vo.setMode((int) SignInCause.サインイン.getCode());

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
     * @param request HttpServletRequest
     * @param model   モデル
     * @param vo      ViewObject
     * @return view名
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(HttpServletRequest request, Model model, Ed01000Vo vo) {

        LOGGER.debug("signIn START");

        // 未ログインオペレータを設定
        setAuditInfoHolder(SpecialOperator.NON_LOGIN_OPERATOR.simpleOperator());

        try {
            // リクエストを作成
            Ed01000SignInConverter converter = Ed01000SignInConverter.with(vo, request.getRemoteAddr());
            Ed01000SignInPresenter presenter = new Ed01000SignInPresenter();

            // サインインサービス実行
            signIn.execute(converter, presenter);
            Ed01000Dto dto = new Ed01000Dto();
            presenter.bindTo(dto);

            if (dto.isSignInResultSuccess()) {
                StringBuilder uri = new StringBuilder();
                uri.append("redirect:");
                uri.append(vo.getRedirectUri());
                uri.append("?access_token=").append(dto.getAccessToken());
                // HttpListenerレスポンス に認証結果を送信する
                return uri.toString();
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

    /**
     * ランダムな文字列を作成します
     *
     * @param length 文字列の長さ
     * @return ランダムな文字列
     */
    private String createRandomString(int length) {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[length];
        random.nextBytes(bytes);

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            buf.append(String.format("%02x", bytes[i]));
        }
        return buf.toString();
    }
}
