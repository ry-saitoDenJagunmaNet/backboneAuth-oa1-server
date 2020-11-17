package net.jagunma.backbone.auth.authmanager.infra.web.oa12020;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBranchAtMoment;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchJaAtMoment;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchSuspendBizTran;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12020.vo.Oa12020Vo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.Oa12060Controller;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa12060Controller.class);
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
     * @param vo ViewObject
     * @return 一時取引抑止検索結果
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String  search(Model model, Oa12020Vo vo) {
        // ToDo: テストサインイン情報セット
        setAuthInf();
        vo.setMessage("");
        LOGGER.debug("search START");

        try {
            // ToDo: 登録画面から戻ってきたときにSessoonから画面の条件を取り出す予定
            // ToDo: 破棄はいつするか？戻るメソッドはどこに？  getメソッド？
            // SessionにViewObjectの格納
            setSessionVo(vo);

            Oa12020SearchConverter converter = Oa12020SearchConverter.with(vo);
            Oa12020Presenter presenter = Oa12020Presenter.with(vo);

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
     * ＪＡコンボボックスのtemsSourceを取得します
     *
     * @param model モデル
     * @param vo View Model（form json）
     * @return ＪＡコンボボックスのItemsSource
     */
    @RequestMapping(value = "/getJaItemsSource", method = RequestMethod.POST)
    public String getJaItemsSource(ModelMap model, Oa12020Vo vo) {
        LOGGER.debug("######## getJaItemsSource START");
        model.addAttribute("selectAjaxItems",SelectOptionItemsSource.createFrom(searchJaAtMoment.selectBy()).getValue());
        return "oa12020::ajaxSelectJa";
    }

    /**
     * 店舗コンボボックスのtemsSourceを取得します
     *
     * @param model モデル
     * @param vo View Model（form json）
     * @return 店舗コンボボックスのItemsSource
     */
    @RequestMapping(value = "/getBranchItemsSource", method = RequestMethod.POST)
    public String getBranchItemsSource(ModelMap model, Oa12020Vo vo) {
        LOGGER.debug("######## getBranchItemsSource START");
        model.addAttribute("selectAjaxItems",SelectOptionItemsSource.createFrom(searchBranchAtMoment.selectBy(vo.getJaId())).getValue());
        return "oa12020::ajaxSelectBranch";
    }

    /**
     * サブシステムコンボボックスのtemsSourceを取得します
     *
     * @param model モデル
     * @param vo View Model（form json）
     * @return サブシステムコンボボックスのItemsSource
     */
    @RequestMapping(value = "/getSubSystemItemsSource", method = RequestMethod.POST)
    public String getSubSystemItemsSource(ModelMap model, Oa12020Vo vo) {
        LOGGER.debug("######## getSubSystemItemsSource START");
        model.addAttribute("selectAjaxItems",SelectOptionItemsSource.createFrom(SubSystem.values()).getValue());
        return "oa12020::ajaxSelectSubSystem";
    }

    /**
     * 取引グループコンボボックスのtemsSourceを取得します
     *
     * @param model モデル
     * @param vo View Model（form json）
     * @return 取引グループコンボボックスのItemsSource
     */
    @RequestMapping(value = "/getBizTranGrpItemsSource", method = RequestMethod.POST)
    public String getBizTranGrpItemsSource(ModelMap model, Oa12020Vo vo) {
        LOGGER.debug("######## getBizTranGrpItemsSource START");
        BizTranGrpCriteria criteria = new BizTranGrpCriteria();
        criteria.getSubSystemCodeCriteria().setEqualTo(vo.getSubSystemCode());
        model.addAttribute("selectAjaxItems",SelectOptionItemsSource.createFrom(bizTranGrpsRepository.selectBy(criteria, Orders.empty())).getValue());
        return "oa12020::ajaxSelectBizTranGrp";
    }

    /**
     * 取引コンボボックスのtemsSourceを取得します
     *
     * @param model モデル
     * @param vo View Model（form json）
     * @return 取引コンボボックスのItemsSource
     */
    @RequestMapping(value = "/getBizTranItemsSource", method = RequestMethod.POST)
    public String getBizTranItemsSource(ModelMap model, Oa12020Vo vo) {
        LOGGER.debug("######## getBizTranItemsSource START");
        BizTranGrp_BizTranCriteria criteria = new BizTranGrp_BizTranCriteria();
        criteria.getBizTranGrpIdCriteria().setEqualTo(vo.getBizTranGrpId());
        criteria.getSubSystemCodeCriteria().setEqualTo(vo.getSubSystemCode());
        BizTranGrp_BizTrans bizTranGrp_BizTrans = bizTranGrp_BizTransRepository.selectBy(criteria, Orders.empty());
        List<BizTran> bizTranList = bizTranGrp_BizTrans.getValues().stream().map(BizTranGrp_BizTran::getBizTran).collect(Collectors.toList());
        model.addAttribute("selectAjaxItems",SelectOptionItemsSource.createFrom(bizTranList).getValue());
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

//        // ＪＡリスト
//        presenter.setJasAtMoment(searchJaAtMoment.selectBy());
        // 抑止期間条件選択
        presenter.setSuspendConditionsSelect(0);

        return presenter;
    }
}
