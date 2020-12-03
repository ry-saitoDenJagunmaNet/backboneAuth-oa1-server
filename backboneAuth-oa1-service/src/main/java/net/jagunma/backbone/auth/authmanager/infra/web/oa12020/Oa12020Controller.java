package net.jagunma.backbone.auth.authmanager.infra.web.oa12020;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBranchAtMoment;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchJaAtMoment;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchSuspendBizTran;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemSource;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12020.vo.Oa12020Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpsRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTransRepository;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import net.jagunma.common.util.strings2.Strings2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
 * 機能ID：OA12020
 * 機能名：一時取引抑止<一覧>
 * サービスID：OA12020
 * サービス名：OA12020サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA12020", name = "一時取引抑止<一覧>")
@ServiceInfo(id = "OA12020", name = "OA12020サービス")
@Controller
@RequestMapping(path = "oa12020")
@SessionAttributes(types=Oa12020Vo.class)
public class Oa12020Controller extends BaseOfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa12020Controller.class);
    private final SearchSuspendBizTran searchSuspendBizTran;
    private final SearchJaAtMoment searchJaAtMoment;
    private final SearchBranchAtMoment searchBranchAtMoment;
    private final BizTranGrpsRepository bizTranGrpsRepository;
    private final BizTranGrp_BizTransRepository bizTranGrp_BizTransRepository;

    // コンストラクタ
    Oa12020Controller(SearchSuspendBizTran searchSuspendBizTran,
        SearchJaAtMoment searchJaAtMoment,
        SearchBranchAtMoment searchBranchAtMoment,
        BizTranGrpsRepository bizTranGrpsRepository,
        BizTranGrp_BizTransRepository bizTranGrp_BizTransRepository) {

        this.searchSuspendBizTran = searchSuspendBizTran;
        this.searchJaAtMoment = searchJaAtMoment;
        this.searchBranchAtMoment = searchBranchAtMoment;
        this.bizTranGrpsRepository = bizTranGrpsRepository;
        this.bizTranGrp_BizTransRepository = bizTranGrp_BizTransRepository;
    }

    /**
     * 画面を初期表示します
     *
     * @param model モデル
     * @return view名
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(Model model) {
        // ToDo: テストサインイン情報セット
        setAuthInf();
        LOGGER.debug("get START");

        Oa12020Vo vo = new Oa12020Vo();
        try {
            Oa12020Presenter presenter = createInitPresenter();

            presenter.bindTo(vo);
            model.addAttribute("form", vo);
            return "oa12020";
        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa12020";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }

    /**
     * 一時取引抑止検索処理を行います
     *
     * @param model モデル
     * @param vo    ViewObject
     * @return 一時取引抑止検索結果
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String  search(Model model, Oa12020Vo vo) {
        // ToDo: テストサインイン情報セット
        setAuthInf();
        LOGGER.debug("search START");

        return Search(vo, model);
    }

    /**
     * 一時取引抑止メンテナンスから戻った時の一時取引抑止再検索処理を行います
     *
     * @param session_vo Sessionに退避したViewObject
     * @param model モデル
     * @return 一時取引抑止検索結果
     */
    @RequestMapping(value = "/backSearch", method = RequestMethod.POST)
    public String  backSearch(@ModelAttribute("session_vo") Oa12020Vo session_vo, Model model) {
        // ToDo: テストサインイン情報セット
        setAuthInf();
        LOGGER.debug("search START");

        return Search(session_vo, model);
    }

    /**
     * 一時取引抑止検索処理を行います
     *
     * @param argVo ViewObject
     * @param model モデル
     * @return
     */
    private String Search(Oa12020Vo argVo, Model model) {

        Oa12020Vo vo = new Oa12020Vo();
        try {
            setSessionVo(argVo);

            Oa12020SearchConverter converter = Oa12020SearchConverter.with(argVo);
            // voの検索条件をpresenterに渡す（converterは、criteriaに変換しているため元のvo値が無いため）
            Oa12020Presenter presenter = Oa12020Presenter.with(argVo);

            // 一時取引抑止検索
            searchSuspendBizTran.execute(converter, presenter);

            presenter.bindTo(vo);
            model.addAttribute("form", vo);
            return "oa12020";
        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa12020";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }

    /**
     * ＪＡコンボボックスのItemsSourceを取得します
     *
     * @param model モデル
     * @param vo    View Model（form json）
     * @return ＪＡコンボボックスのItemsSource
     */
    @RequestMapping(value = "/getJaItemsSource", method = RequestMethod.POST)
    public String getJaItemsSource(ModelMap model, Oa12020Vo vo) {
        LOGGER.debug("######## getJaItemsSource START");
        model.addAttribute("selectAjaxItems", SelectOptionItemsSource.createFrom(searchJaAtMoment.selectBy()).getValue());
        return "oa12020::ajaxSelectJa";
    }

    /**
     * 店舗コンボボックスのItemsSourceを取得します
     *
     * @param model モデル
     * @param vo    View Model（form json）
     * @return 店舗コンボボックスのItemsSource
     */
    @RequestMapping(value = "/getBranchItemsSource", method = RequestMethod.POST)
    public String getBranchItemsSource(ModelMap model, Oa12020Vo vo) {
        LOGGER.debug("######## getBranchItemsSource START");
        List<SelectOptionItemSource> list = newArrayList();
        if (vo.getJaCode() != null) {
            list = SelectOptionItemsSource.createFrom(searchBranchAtMoment.selectBy(vo.getJaCode())).getValue();
        }
        model.addAttribute("selectAjaxItems", list);
        return "oa12020::ajaxSelectBranch";
    }

    /**
     * サブシステムコンボボックスのItemsSourceを取得します
     *
     * @param model モデル
     * @param vo    View Model（form json）
     * @return サブシステムコンボボックスのItemsSource
     */
    @RequestMapping(value = "/getSubSystemItemsSource", method = RequestMethod.POST)
    public String getSubSystemItemsSource(ModelMap model, Oa12020Vo vo) {
        LOGGER.debug("######## getSubSystemItemsSource START");
        model.addAttribute("selectAjaxItems", SelectOptionItemsSource.createFrom(SubSystem.values()).getValue());
        return "oa12020::ajaxSelectSubSystem";
    }

    /**
     * 取引グループコンボボックスのItemsSourceを取得します
     *
     * @param model モデル
     * @param vo    View Model（form json）
     * @return 取引グループコンボボックスのItemsSource
     */
    @RequestMapping(value = "/getBizTranGrpItemsSource", method = RequestMethod.POST)
    public String getBizTranGrpItemsSource(ModelMap model, Oa12020Vo vo) {
        LOGGER.debug("######## getBizTranGrpItemsSource START");
        List<SelectOptionItemSource> list = newArrayList();
        if (Strings2.isNotEmpty(vo.getSubSystemCode())) {
            BizTranGrpCriteria criteria = new BizTranGrpCriteria();
            criteria.getSubSystemCodeCriteria().setEqualTo(vo.getSubSystemCode());
            list = SelectOptionItemsSource.createFrom(bizTranGrpsRepository.selectBy(criteria, Orders.empty())).getValue();
        }
        model.addAttribute("selectAjaxItems", list);
        return "oa12020::ajaxSelectBizTranGrp";
    }

    /**
     * 取引コンボボックスのItemsSourceを取得します
     *
     * @param model モデル
     * @param vo    View Model（form json）
     * @return 取引コンボボックスのItemsSource
     */
    @RequestMapping(value = "/getBizTranItemsSource", method = RequestMethod.POST)
    public String getBizTranItemsSource(ModelMap model, Oa12020Vo vo) {
        LOGGER.debug("######## getBizTranItemsSource START");
        List<SelectOptionItemSource> list = newArrayList();
        if (Strings2.isNotEmpty(vo.getSubSystemCode())) {
            BizTranGrpCriteria bizTranGrpCriteria = new BizTranGrpCriteria();
            bizTranGrpCriteria.getBizTranGrpCodeCriteria().setEqualTo(vo.getBizTranGrpCode());
            BizTranGrps BizTranGrps = bizTranGrpsRepository.selectBy(bizTranGrpCriteria, Orders.empty());

            BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria = new BizTranGrp_BizTranCriteria();
            bizTranGrp_BizTranCriteria.getBizTranGrpIdCriteria().setEqualTo(BizTranGrps.getValues().get(0).getBizTranGrpId());
            bizTranGrp_BizTranCriteria.getSubSystemCodeCriteria().setEqualTo(vo.getSubSystemCode());
            BizTranGrp_BizTrans bizTranGrp_BizTrans = bizTranGrp_BizTransRepository.selectBy(bizTranGrp_BizTranCriteria, Orders.empty());
            List<BizTran> bizTranList = bizTranGrp_BizTrans.getValues().stream().map(BizTranGrp_BizTran::getBizTran).collect(Collectors.toList());
            list = SelectOptionItemsSource.createFrom(bizTranList).getValue();
        }
        model.addAttribute("selectAjaxItems", list);
        return "oa12020::ajaxSelectBizTran";
    }

    /**
     * SessionにViewObjectの格納を行います
     *
     * @param vo ViewObject
     * @return view名
     */
    @ModelAttribute("session_vo")
    public Oa12020Vo setSessionVo(Oa12020Vo vo) {
        return vo;
    }

    /**
     * Presenterの初期化を行います
     *
     * @return InitPresenter
     */
    private Oa12020Presenter createInitPresenter() {

        Oa12020Presenter presenter = new Oa12020Presenter();

        // 抑止期間条件選択
        presenter.setSuspendConditionsSelect(0);

        return presenter;
    }
}
