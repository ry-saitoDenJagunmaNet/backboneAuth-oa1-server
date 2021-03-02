package net.jagunma.backbone.auth.authmanager.infra.web.oa11050;

import net.jagunma.backbone.auth.authmanager.application.commandService.GrantBizTranRole;
import net.jagunma.backbone.auth.authmanager.application.queryService.CopyBizTranRoleGranted;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBizTranRoleGranted;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.Oa11010Controller;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050Vo;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * OA11050 コントローラー
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA11050
 * 機能名：取引ロール付与
 * サービスID：OA11050
 * サービス名：OA11050サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA11050", name = "取引ロール付与")
@ServiceInfo(id = "OA11050", name = "OA11050サービス")
@Controller
@RequestMapping(path = "oa11050")
public class Oa11050Controller extends BaseOfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa11050Controller.class);

    private final GrantBizTranRole grantBizTranRole;
    private final SearchBizTranRoleGranted searchBizTranRoleGranted;
    private final CopyBizTranRoleGranted copyBizTranRoleGranted;

    // コンストラクタ
    public Oa11050Controller(
        GrantBizTranRole grantBizTranRole,
        SearchBizTranRoleGranted searchBizTranRoleGranted,
        CopyBizTranRoleGranted copyBizTranRoleGranted) {
        this.grantBizTranRole = grantBizTranRole;
        this.searchBizTranRoleGranted = searchBizTranRoleGranted;
        this.copyBizTranRoleGranted = copyBizTranRoleGranted;
    }

    /**
     * 画面を初期表示します
     *
     * @param operatorId オペレーターID
     * @param model モデル
     * @return view名
     */
    @GetMapping(path = "/get")
    public String get(@RequestParam("operatorId") Long operatorId, Model model) {
        // ToDo: テストサインイン情報セット
        setAuthInf();

        Oa11050Vo vo = new Oa11050Vo();
        vo.setOperatorId(operatorId);

        try {
            Oa11050InitConverter converter = Oa11050InitConverter.with(AuditInfoHolder.getOperator().getIdentifier(), vo.getOperatorId());
            Oa11050InitPresenter presenter = new Oa11050InitPresenter();

            searchBizTranRoleGranted.execute(converter, presenter);

            presenter.bindTo(vo);

            model.addAttribute("form", vo);
            return "oa11050";

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa11050";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }

    /**
     * コピーボタン処理を行います
     *
     * @param model モデル
     * @param redirectAttribute リダイレクトパラメータ
     * @return view名
     */
    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copy(Model model, RedirectAttributes redirectAttribute, Oa11050Vo vo) {
        // ToDo: テストサインイン情報セット
        setAuthInf();

        try {
            // Sessionに格納
            setSessionAttribute("session_Oa11050Vo", vo);

            // 入力補助（オペレーター一覧）へ画面遷移
            redirectAttribute.addAttribute(Oa11010Controller.RedirectAttribute, "oa11050/copyResponse");
            return Oa11010Controller.AssistanceMethod;

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa11050";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }

    /**
     * コピー処理を行います
     *
     * @param selectedOperatorId 選択オペレーターID
     * @param model モデル
     * @return view名
     */
    @GetMapping(path = "/copyResponse")
    public String copyResponse(@RequestParam(value = "operatorId", required = false) Long selectedOperatorId, Model model) {
        // ToDo: テストサインイン情報セット
        setAuthInf();

        // Sessionから取り出し
        Oa11050Vo vo = (Oa11050Vo) getSessionAttribute("session_Oa11050Vo");

        try {
            if (selectedOperatorId != null) {

                Oa11050CopyConverter converter = Oa11050CopyConverter.with(vo, AuditInfoHolder.getAuthInf().getOperatorId(), selectedOperatorId);
                Oa11050CopyPresenter presenter = new Oa11050CopyPresenter();

                copyBizTranRoleGranted.execute(converter, presenter);

                presenter.bindTo(vo);
            }
            model.addAttribute("form", vo);
            return "oa11050";

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa11050";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }

    /**
     * 適用処理を行います
     *
     * @param model モデル
     * @param vo ViewObject
     * @return view名
     */
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public String apply(Model model, Oa11050Vo vo) {
        // ToDo: テストサインイン情報セット
        setAuthInf();

        try {
            Oa11050ApplyConverter converter = Oa11050ApplyConverter.with(vo);
            grantBizTranRole.execute(converter);

            model.addAttribute("form", vo);
            return "oa11050"; // ToDo: 遷移制御

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa11050";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }
}
