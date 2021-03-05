package net.jagunma.backbone.auth.authmanager.infra.web.oa10000;

import javax.servlet.http.HttpServletRequest;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchProfile;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.base.vo.BaseOfVo;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * OA11010コントローラ
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA10000
 * 機能名：メニュー
 * サービスID：OA10000
 * サービス名：OA10000サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA10000", name = "メニュー")
@ServiceInfo(id = "OA10000", name = "OA10000サービス")
@Controller
@RequestMapping(path = "oa10000")
public class Oa10000Controller extends BaseOfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa10000Controller.class);

    // サインイン画面にリダイレクイトするreturn文字列
    private final String SIGNIN_REDIRECT = "redirect:/ed01000/get?redirect_uri=%1$s://%2$s:%3$s/oa10000/showForm";

    private final SearchProfile searchProfile;

    // コンストラクタ
    public Oa10000Controller(SearchProfile searchProfile) {
        this.searchProfile = searchProfile;
    }

    /**
     * 画面を初期表示します
     *
     * @param request HttpServletRequest
     * @param model   モデル
     * @return view名
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(HttpServletRequest request, Model model) {

        LOGGER.debug("get START");

        try {
            if (AuditInfoHolder.getAuthInf() == null) {
                // 未認証の場合サインイン画面にリダイレクイト
                return String.format(SIGNIN_REDIRECT, request.getScheme(), request.getServerName(), request.getServerPort());
            }

            // ToDo: access tokenをsessionで管理する場合（検討中）
            String accesstoken = getSessionAttribute(SESSION_KEY_ACCES_TOKEN).toString();

            return showForm(accesstoken, model);

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            BaseOfVo vo = new BaseOfVo();
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa10000";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            BaseOfVo vo = new BaseOfVo();
            model.addAttribute("form", vo);
            vo.setExceptionMessage(re);
            return "oa19999";
        }
    }

    /**
     * 画面を初期表示します
     *
     * @param model        モデル
     * @param access_token アクセストークン
     * @return view名
     */
    @RequestMapping(value = "/showForm", method = RequestMethod.GET)
    public String showForm(@RequestParam(name = "access_token") String access_token, Model model) {

        LOGGER.debug("showForm START");

        BaseOfVo vo = new BaseOfVo();
        Oa10000SearchProfilePresenter presenter = new Oa10000SearchProfilePresenter();
        try {
            // ToDo: access tokenをsessionで管理する場合（検討中）
            setSessionAttribute(SESSION_KEY_ACCES_TOKEN, access_token);

            // ToDo: オペレーター情報取得（oa2への接続方法確認）
            // アクセストークンを引数にしてoa2で取得する
            //setAuthInf();
            Oa10000SearchProfileConverter converter = Oa10000SearchProfileConverter.with(access_token);
            searchProfile.execute(converter, presenter);
            presenter.bindToAuditInfoHolder();

            model.addAttribute("form", vo);
            return "oa10000";

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa10000";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            model.addAttribute("form", vo);
            vo.setExceptionMessage(re);
            return "oa19999";
        }
    }

    /**
     * サインアウトします
     *
     * @param request HttpServletRequest
     * @param model   モデル
     * @return view名
     */
    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String signOut(HttpServletRequest request, Model model) {

        LOGGER.debug("signOut START");

        BaseOfVo vo = new BaseOfVo();
        try {
            // AuditInfoHolderのクリア
            AuditInfoHolder.clear();

            // アクセストークン削除
            removeSessionAttribute(SESSION_KEY_ACCES_TOKEN);

            // ToDo: サインアウト（oa2への接続方法確認）

            model.addAttribute("form", vo);
            return get(request, model);

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa10000";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            model.addAttribute("form", vo);
            vo.setExceptionMessage(re);
            return "oa19999";
        }
    }
}
