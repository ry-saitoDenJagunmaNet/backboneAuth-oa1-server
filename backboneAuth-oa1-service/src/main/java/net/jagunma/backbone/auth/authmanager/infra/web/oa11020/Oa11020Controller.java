package net.jagunma.backbone.auth.authmanager.infra.web.oa11020;

import net.jagunma.backbone.auth.authmanager.application.commandService.EntryOperator;
import net.jagunma.backbone.auth.authmanager.application.queryService.SimpleSearchBranch;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.ed01010.vo.Ed01010Vo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11020.vo.Oa11020Vo;
import net.jagunma.backbone.auth.authmanager.model.types.OperatorCodePrefix;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * OA11020 コントローラー
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA11020
 * 機能名：オペレーター登録
 * サービスID：OA11020
 * サービス名：OA11020サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA11020", name = "オペレーター登録")
@ServiceInfo(id = "OA11020", name = "OA11020サービス")
@Controller
@RequestMapping(path = "oa11020")
@SessionAttributes(types=Oa11020Vo.class)
public class Oa11020Controller extends BaseOfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa11020Controller.class);

    private final SimpleSearchBranch simpleSearchBranch;
    private final EntryOperator entryOperator;

    // コンストラクタ
    public Oa11020Controller(
        SimpleSearchBranch simpleSearchBranch,
        EntryOperator entryOperator) {
        this.simpleSearchBranch = simpleSearchBranch;
        this.entryOperator = entryOperator;
    }

    /**
     * 画面を初期表示します。
     *
     * @param model モデル
     * @return view名
     */
    @GetMapping(path = "/get")
    public String get(Model model) {
        // ToDo: テストサインイン情報セット
        setAuthInf();

        Oa11020Vo vo = new Oa11020Vo();

        try {
            Oa11020InitPresenter presenter = new Oa11020InitPresenter();

            presenter.setJaCode(AuditInfoHolder.getAuthInf().getJaCode());
            presenter.setJaName(AuditInfoHolder.getJa().getJaAttribute().getName());
            presenter.setOperatorCodePrefix(OperatorCodePrefix.codeOf(AuditInfoHolder.getAuthInf().getJaCode()).getPrefix());
            presenter.setBranchesAtMomentForBranchItemsSource(simpleSearchBranch.getBranchesAtMoment(AuditInfoHolder.getJa().getIdentifier()));
            presenter.bindTo(vo);

            model.addAttribute("form", vo);
            return "oa11020";

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa19999";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }    }

    /**
     * 登録ボタン処理を行います。
     *
     * @param model モデル
     * @param vo ViewObject
     * @return view名
     */
    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public String entry(Model model, Oa11020Vo vo) {
        // ToDo: テストサインイン情報セット
        setAuthInf();

        try {
            // ViewObjectの検証
            Oa11020EntryValidator.with(vo).validate();

            setSessionVo(vo);

            Ed01010Vo ed01010Vo = new Ed01010Vo();
            ed01010Vo.setMode("Initial");
            ed01010Vo.setJa(vo.getJa());
            ed01010Vo.setOperator(vo.getOperatorCodePrefix() + vo.getOperatorCode6() + " " + vo.getOperatorName());

            model.addAttribute("form", ed01010Vo);
            return "ed01010";

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa11020";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }

    /**
     * 登録処理を行います。
     *
     * @param session_vo SessionViewObject
     * @param model モデル
     * @param vo ViewObject
     * @return view名
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("session_vo")Oa11020Vo session_vo, Model model, Ed01010Vo vo) {
        // ToDo: テストサインイン情報セット
        setAuthInf();

        try {
            session_vo.setPassword(vo.getNewPassword());
            session_vo.setConfirmPassword(vo.getConfirmPassword());

            Oa11020EntryConverter converter = Oa11020EntryConverter.with(session_vo);

            entryOperator.execute(converter);

            return get(model);

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            if (gre.getSimpleMessage().contains("パスワード")) {
                vo.setExceptionMessage(gre);
                model.addAttribute("form", vo);
                return "ed01010";
            } else {
                session_vo.setExceptionMessage(gre);
                model.addAttribute("form", session_vo);
                return "oa11020";
            }
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            session_vo.setExceptionMessage(re);
            model.addAttribute("form", session_vo);
            return "oa19999";
        }
    }

    /**
     * SessionにViewObjectの格納を行います。
     *
     * @param vo ViewObject
     * @return view名
     */
    @ModelAttribute("session_vo")
    public Oa11020Vo setSessionVo(Oa11020Vo vo) {
        return vo;
    }
}
