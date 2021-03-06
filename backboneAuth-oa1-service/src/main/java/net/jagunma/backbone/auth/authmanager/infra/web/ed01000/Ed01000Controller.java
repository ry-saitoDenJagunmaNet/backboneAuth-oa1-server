package net.jagunma.backbone.auth.authmanager.infra.web.ed01000;

import java.security.SecureRandom;
import javax.servlet.http.HttpServletRequest;
import net.jagunma.backbone.auth.authmanager.application.commandService.SignIn;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01000.dto.Ed01000Dto;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01000.vo.Ed01000Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistory;
import net.jagunma.backbone.auth.authmanager.model.domain.passwordHistory.PasswordHistoryRepository;
import net.jagunma.backbone.auth.authmanager.model.types.SignInCause;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    final String SESSIONKEY_REDIRECT_URI = "session_ed01000_redirect_uri";
    final String SESSIONKEY_STATE = "session_ed01000_state";

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

        Ed01000Vo vo = new Ed01000Vo();
        try {
            // ToDo: clientId、scope他リクエストIFの値を確認要
            String clientId = "";
            String scope = "";
            String state = createRandomString(32);
            String oAuthRedirectUri = String.format("%1$s://%2$s:%3$s/ed01000/oAuthReception", request.getScheme(), request.getLocalAddr(), request.getServerPort());
            String responseType = "code";

            // リダイレクトuri（サインインの呼び出し元）をSessionに格納
            setSessionAttribute(SESSIONKEY_REDIRECT_URI, redirect_uri);
            setSessionAttribute(SESSIONKEY_STATE, state);

            // ToDo: oa2認証Apiで認証コードを取得する
            // ToDo: 暫定でoa2認証Apiからリダイレクトされた提でoAuthRedirectメソッドを呼ぶ（oa2への接続方法確認）
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

        Ed01000Vo vo = new Ed01000Vo();
        try {
            // codeが取得できない場合（予期せぬエラー\n\nCL:サーバーで予期しないエラーが発生しました。）
            if (code.length() == 0) { throw new GunmaRuntimeException("EOA10001"); }

            // リダイレクトuri（サインインの呼び出し元）、stateをSessionから取出
            String redirectUri = (String) getSessionAttribute(SESSIONKEY_REDIRECT_URI);
            String requestState = (String) getSessionAttribute(SESSIONKEY_STATE);

            // stateが一致しない場合エラー（予期せぬエラー\n\nCL:サーバーで予期しないエラーが発生しました。）
            if (!state.equals(requestState)) { throw new GunmaRuntimeException("EOA10001"); }

            vo.setRedirectUri(redirectUri);
            vo.setMode(SignInCause.サインイン.getCode());

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

        try {
            // リクエストを作成
            Ed01000SignInConverter converter = Ed01000SignInConverter.with(vo, request.getLocalAddr());
            Ed01000SignInPresenter presenter = new Ed01000SignInPresenter();

            // サインインサービス実行
            signIn.execute(converter, presenter);
            Ed01000Dto dto = new Ed01000Dto();
            presenter.bindTo(dto);

            if (dto.isSignInResultSuccess()) {
                setSessionAttribute(SESSION_KEY_ACCES_TOKEN, dto.getAccessToken());
                setAuthInf();

                // ToDo: 「パスワード変更種別」が初期 or 管理者によるリセットの場合パスワード変更を求める
                if (isPasswordChange()) {
                    Long operatorId = AuditInfoHolder.getOperator().getIdentifier();
                    return String.format("redirect:/ed01010/getForUpdate?operatorId=%1$d&redirect_uri=%2$s&access_token=%3$s",
                        operatorId, vo.getRedirectUri(), dto.getAccessToken());
                }

                // HttpListenerレスポンス に認証結果を送信する
                return "redirect:"
                    + vo.getRedirectUri()
                    + "?access_token=" + dto.getAccessToken();
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

    // ToDo: 本当はSimpleOperatorに「パスワード変更種別」が定義されている予定だが、まだ無いのでPasswordHistoryを検索して判定する
    @Autowired
    private PasswordHistoryRepository passwordHistoryRepository;

    // ToDo: 本当はSimpleOperatorに「パスワード変更種別」が定義されている予定だが、まだ無いのでPasswordHistoryを検索して判定する
    private boolean isPasswordChange() {
        PasswordHistory passwordHistory = passwordHistoryRepository.latestOneByOperatorId(AuditInfoHolder.getOperator().getIdentifier());
        return passwordHistory.getPasswordChangeType().is初期() || passwordHistory.getPasswordChangeType().is管理者によるリセット();
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
