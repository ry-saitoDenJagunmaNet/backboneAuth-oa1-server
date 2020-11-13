package net.jagunma.backbone.auth.authmanager.infra.web.oa11030;

import net.jagunma.backbone.auth.authmanager.application.commandService.UpdateOperator;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBranchAtMoment;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchOperator;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11030.vo.Oa11030Vo;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * OA11030 コントローラー
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA11030
 * 機能名：オペレーター更新
 * サービスID：OA11030
 * サービス名：OA11030サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA11030", name = "オペレーター更新")
@ServiceInfo(id = "OA11030", name = "OA11030サービス")
@Controller
@RequestMapping(path = "oa11030")
public class Oa11030Controller extends BaseOfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa11030Controller.class);

    private final SearchOperator searchOperator;
    private final UpdateOperator updateOperator;
    private final SearchBranchAtMoment searchBranchAtMoment;

    // コンストラクタ
    public Oa11030Controller(
        SearchOperator searchOperator,
        UpdateOperator updateOperator,
        SearchBranchAtMoment searchBranchAtMoment) {
        this.searchOperator = searchOperator;
        this.updateOperator = updateOperator;
        this.searchBranchAtMoment = searchBranchAtMoment;
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

        Oa11030Vo vo = new Oa11030Vo();
        vo.setOperatorId(operatorId);

        try {
            Oa11030InitConverter converter = Oa11030InitConverter.with(vo);
            Oa11030InitPresenter presenter = new Oa11030InitPresenter();
            presenter.setBranchesAtMomentForBranchItemsSource(searchBranchAtMoment.selectBy(AuditInfoHolder.getJa().getIdentifier()));

            searchOperator.execute(converter, presenter);

            presenter.bindTo(vo);

            model.addAttribute("form", vo);
            return "oa11030";

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa11030";
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
    public String update(Model model, Oa11030Vo vo) {
        // ToDo: テストサインイン情報セット
        setAuthInf();

        try {

            Oa11030UpdateConverter converter = Oa11030UpdateConverter.with(vo);

            updateOperator.execute(converter);

            // ToDo: 遷移制御
            model.addAttribute("form", vo);
            return "oa11030";

        } catch (OptimisticLockingFailureException ole) {
            // 楽観的ロック
            vo.setExceptionMessage(ole);
            model.addAttribute("form", vo);
            return "oa19999";
        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa11030";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }
}
