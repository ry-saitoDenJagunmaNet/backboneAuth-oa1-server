package net.jagunma.backbone.auth.authmanager.infra.web.oa12030;

import net.jagunma.backbone.auth.authmanager.application.commandService.DeleteSuspendBizTran;
import net.jagunma.backbone.auth.authmanager.application.commandService.EntrySuspendBizTran;
import net.jagunma.backbone.auth.authmanager.application.commandService.UpdateSuspendBizTran;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchSuspendBizTran;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12020.Oa12020Controller;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12030.vo.Oa12030Vo;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * OA12020コントローラ
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA12030
 * 機能名：一時取引抑止メンテナンス
 * サービスID：OA12030
 * サービス名：OA12030サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA12030", name = "一時取引抑止メンテナンス")
@ServiceInfo(id = "OA12030", name = "OA12030サービス")
@Controller
@RequestMapping(path = "oa12030")
@SessionAttributes(types= Oa12030Vo.class)
public class Oa12030Controller extends BaseOfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa12020Controller.class);

    private final SearchSuspendBizTran searchSuspendBizTran;
    private final EntrySuspendBizTran entrySuspendBizTran;
    private final UpdateSuspendBizTran updateSuspendBizTran;
    private final DeleteSuspendBizTran deleteSuspendBizTran;

    // コンストラクタ
    Oa12030Controller(SearchSuspendBizTran searchSuspendBizTran,
        EntrySuspendBizTran entrySuspendBizTran,
        UpdateSuspendBizTran updateSuspendBizTran,
        DeleteSuspendBizTran deleteSuspendBizTran) {

        this.searchSuspendBizTran = searchSuspendBizTran;
        this.entrySuspendBizTran = entrySuspendBizTran;
        this.updateSuspendBizTran = updateSuspendBizTran;
        this.deleteSuspendBizTran = deleteSuspendBizTran;
    }

    /**
     * 画面を初期表示します
     *
     * @param suspendBizTranId 一時取引抑止ID
     * @param model モデル
     * @return view名
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(@RequestParam(name="suspendBizTranId", required = false) Long suspendBizTranId, Model model) {
        // ToDo: テストサインイン情報セット
        setAuthInf();
        LOGGER.debug("get START");

        Oa12030Vo vo = new Oa12030Vo();

        try {
            if (suspendBizTranId != null) {
                vo.setSuspendBizTranId(suspendBizTranId);
            }
            Oa12030InitConverter converter = Oa12030InitConverter.with(vo);
            Oa12030Presenter presenter = new Oa12030Presenter();
            if (suspendBizTranId != null) {
                searchSuspendBizTran.execute(converter, presenter);
            }

            presenter.bindTo(vo);
            model.addAttribute("form", vo);
            return "oa12030";
        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa12030";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }

    /**
     * 一時取引抑止の登録を行います
     *
     * @param model モデル
     * @param vo    ViewObject
     * @return view名
     */
    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public String entry(Model model, Oa12030Vo vo) {
        // ToDo: テストサインイン情報セット
        setAuthInf();
        LOGGER.debug("entry START");

        try {
            Oa12030CommandConverter converter = Oa12030CommandConverter.with(vo);
            entrySuspendBizTran.execute(converter);

            // 一時取引抑止<一覧>に遷移
            return "redirect:/oa12020/backSearch";
        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa12030";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }

    /**
     * 一時取引抑止の更新を行います
     *
     * @param model モデル
     * @param vo    ViewObject
     * @return view名
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model, Oa12030Vo vo) {
        // ToDo: テストサインイン情報セット
        setAuthInf();
        LOGGER.debug("update START");

        try {
            Oa12030CommandConverter converter = Oa12030CommandConverter.with(vo);
            updateSuspendBizTran.execute(converter);

            // 一時取引抑止<一覧>に遷移
            return "redirect:/oa12020/backSearch";
        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa12030";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(Model model, Oa12030Vo vo) {
        // ToDo: テストサインイン情報セット
        setAuthInf();
        LOGGER.debug("delete START");

        try {
            Oa12030CommandConverter converter = Oa12030CommandConverter.with(vo);
            deleteSuspendBizTran.execute(converter);

            // 一時取引抑止<一覧>に遷移
            return "redirect:/oa12020/backSearch";
        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa12030";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }
}
