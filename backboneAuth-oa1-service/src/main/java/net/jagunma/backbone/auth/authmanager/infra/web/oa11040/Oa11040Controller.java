package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import net.jagunma.backbone.auth.authmanager.application.commandService.GrantSubSystemRole;
import net.jagunma.backbone.auth.authmanager.application.queryService.CopySubSystemRoleGranted;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchSubSystemRoleGranted;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.Oa11010Controller;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;
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
 * OA11040 コントローラー
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA11040
 * 機能名：オペレーター更新
 * サービスID：OA11040
 * サービス名：OA11040サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA11040", name = "サブシステムロール付与")
@ServiceInfo(id = "OA11040", name = "OA11040サービス")
@Controller
@RequestMapping(path = "oa11040")
public class Oa11040Controller extends BaseOfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa11040Controller.class);

    private final GrantSubSystemRole grantSubSystemRole;
    private final SearchSubSystemRoleGranted searchSubSystemRoleGranted;
    private final CopySubSystemRoleGranted copySubSystemRoleGranted;

    // コンストラクタ
    public Oa11040Controller(
        GrantSubSystemRole grantSubSystemRole,
        SearchSubSystemRoleGranted searchSubSystemRoleGranted,
        CopySubSystemRoleGranted copySubSystemRoleGranted) {
        this.grantSubSystemRole = grantSubSystemRole;
        this.searchSubSystemRoleGranted = searchSubSystemRoleGranted;
        this.copySubSystemRoleGranted = copySubSystemRoleGranted;
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

        Oa11040Vo vo = new Oa11040Vo();
        vo.setOperatorId(operatorId);

        try {
            Oa11040InitConverter converter = Oa11040InitConverter.with(AuditInfoHolder.getOperator().getIdentifier(), vo.getOperatorId());
            Oa11040InitPresenter presenter = new Oa11040InitPresenter();

            searchSubSystemRoleGranted.execute(converter, presenter);

            presenter.bindTo(vo);

            model.addAttribute("form", vo);
            return "oa11040";

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa11040";
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
    public String copy(Model model, RedirectAttributes redirectAttribute, Oa11040Vo vo) {
        // ToDo: テストサインイン情報セット
        setAuthInf();

        try {
            // Sessionに格納
            setSessionAttribute("session_Oa11040Vo", vo);

            // 入力補助（オペレーター一覧）へ画面遷移
            redirectAttribute.addAttribute(Oa11010Controller.RedirectAttribute, "oa11040/copyResponse");
            return Oa11010Controller.AssistanceMethod;

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa11040";
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
        Oa11040Vo vo = (Oa11040Vo) getSessionAttribute("session_Oa11040Vo");

        try {
            if (selectedOperatorId != null) {

                Oa11040CopyConverter converter = Oa11040CopyConverter.with(vo, AuditInfoHolder.getAuthInf().getOperatorId(), selectedOperatorId);
//ToDo:★                Oa11040CopyConverter converter = Oa11040CopyConverter.with(vo.getAssignRoleTableVoList(), AuditInfoHolder.getAuthInf().getOperatorId(), selectedOperatorId);
                Oa11040CopyPresenter presenter = new Oa11040CopyPresenter();

                copySubSystemRoleGranted.execute(converter, presenter);

                presenter.bindTo(vo);
            }
            model.addAttribute("form", vo);
            return "oa11040";

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa11040";
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
    public String apply(Model model, Oa11040Vo vo) {
        // ToDo: テストサインイン情報セット
        setAuthInf();

        try {
            Oa11040ApplyConverter converter = Oa11040ApplyConverter.with(vo);
            grantSubSystemRole.execute(converter);

            model.addAttribute("form", vo);
            return "oa11040"; // ToDo: 遷移制御

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa11040";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }
}
