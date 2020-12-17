package net.jagunma.backbone.auth.authmanager.infra.web.formElements;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBranchAtMoment;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchJaAtMoment;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemSource;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrpRepository;
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
import net.jagunma.common.util.strings2.Strings2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * FormElements コントローラ
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：FormElements
 * 機能名：共通フォーム要素
 * サービスID：FormElements
 * サービス名：FormElementsサービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "FormElements", name = "フォーム要素")
@ServiceInfo(id = "FormElements", name = "FormElementsサービス")
@Controller
@RequestMapping(path = "FormElements")
public class FormElementsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormElementsController.class);

    private final SearchJaAtMoment searchJaAtMoment;
    private final SearchBranchAtMoment searchBranchAtMoment;
    private final BizTranGrpsRepository bizTranGrpsRepository;
    private final BizTranGrpRepository bizTranGrpRepository;
    private final BizTranGrp_BizTransRepository bizTranGrp_BizTransRepository;

    // コンストラクタ
    FormElementsController(SearchJaAtMoment searchJaAtMoment,
        SearchBranchAtMoment searchBranchAtMoment,
        BizTranGrpsRepository bizTranGrpsRepository,
        BizTranGrpRepository bizTranGrpRepository,
        BizTranGrp_BizTransRepository bizTranGrp_BizTransRepository) {

        this.searchJaAtMoment = searchJaAtMoment;
        this.searchBranchAtMoment = searchBranchAtMoment;
        this.bizTranGrpsRepository = bizTranGrpsRepository;
        this.bizTranGrpRepository = bizTranGrpRepository;
        this.bizTranGrp_BizTransRepository = bizTranGrp_BizTransRepository;
    }

    /**
     * ＪＡコンボボックスのItemsSourceを取得します
     *
     * @param viewId viewID
     * @param model  モデル
     * @return ＪＡコンボボックスのItemsSource
     */
    @RequestMapping(value = "/getJaItemsSource", method = RequestMethod.GET)
    public String getJaItemsSource(@RequestParam("viewId") String viewId, ModelMap model) {

        LOGGER.debug("######## getJaItemsSource START");
        model.addAttribute("selectAjaxItems", SelectOptionItemsSource.createFrom(searchJaAtMoment.selectBy()).getValue());
        return viewId+"::ajaxSelectJa";
    }

    /**
     * 店舗コンボボックスのItemsSourceを取得します
     *
     * @param viewId viewID
     * @param jaCode ＪＡコード
     * @param model  モデル
     * @return 店舗コンボボックスのItemsSource
     */
    @RequestMapping(value = "/getBranchItemsSource", method = RequestMethod.GET)
    public String getBranchItemsSource(@RequestParam("viewId") String viewId,
        @RequestParam("jaCode") String jaCode, ModelMap model) {

        LOGGER.debug("######## getBranchItemsSource START");
        List<SelectOptionItemSource> list = newArrayList();
        if (Strings2.isNotEmpty(jaCode)) {
            list = SelectOptionItemsSource.createFrom(searchBranchAtMoment.selectBy(jaCode)).getValue();
        }
        model.addAttribute("selectAjaxItems", list);
        return viewId+"::ajaxSelectBranch";
    }

    /**
     * サブシステムコンボボックスのItemsSourceを取得します
     *
     * @param viewId viewID
     * @param model  モデル
     * @return サブシステムコンボボックスのItemsSource
     */
    @RequestMapping(value = "/getSubSystemItemsSource", method = RequestMethod.GET)
    public String getSubSystemItemsSource(@RequestParam("viewId") String viewId, ModelMap model) {

        LOGGER.debug("######## getSubSystemItemsSource START");
        model.addAttribute("selectAjaxItems", SelectOptionItemsSource.createFrom(SubSystem.values()).getValue());
        return viewId+"::ajaxSelectSubSystem";
    }

    /**
     * 取引グループコンボボックスのItemsSourceを取得します
     *
     * @param viewId          viewID
     * @param subSystemCode  サブシステムコード
     * @param firstRowStatus 最初の空行挿入
     * @param model          モデル
     * @return 取引グループコンボボックスのItemsSource
     */
    @RequestMapping(value = "/getBizTranGrpItemsSource", method = RequestMethod.GET)
    public String getBizTranGrpItemsSource(@RequestParam("viewId") String viewId,
        @RequestParam("subSystemCode") String subSystemCode,
        @RequestParam(name="firstRowStatus", required=false) String firstRowStatus, ModelMap model) {

        LOGGER.debug("######## getBizTranGrpItemsSource START");
        List<SelectOptionItemSource> list = newArrayList();
        if (Strings2.isNotEmpty(subSystemCode)) {
            BizTranGrpCriteria criteria = new BizTranGrpCriteria();
            criteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);
            list = SelectOptionItemsSource.createFrom(bizTranGrpsRepository.selectBy(criteria, Orders.empty()),
                (!Strings2.isNull(firstRowStatus))).getValue();
        }
        if (Strings2.isNotEmpty(firstRowStatus)) {
            list.get(0).setName(firstRowStatus);
        }
        model.addAttribute("selectAjaxItems", list);
        return viewId+"::ajaxSelectBizTranGrp";
    }

    /**
     * 取引コンボボックスのItemsSourceを取得します
     *
     * @param viewId          viewID
     * @param subSystemCode  サブシステムコード
     * @param bizTranGrpCode 取引グループコード
     * @param firstRowStatus 最初の空行挿入
     * @param model         モデル
     * @return 取引コンボボックスのItemsSource
     */
    @RequestMapping(value = "/getBizTranItemsSource", method = RequestMethod.GET)
    public String getBizTranItemsSource(@RequestParam("viewId") String viewId,
        @RequestParam("subSystemCode") String subSystemCode,
        @RequestParam("bizTranGrpCode") String bizTranGrpCode,
        @RequestParam(name="firstRowStatus", required=false) String firstRowStatus, ModelMap model) {

        LOGGER.debug("######## getBizTranItemsSource START");
        List<SelectOptionItemSource> list = newArrayList();
        BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria = new BizTranGrp_BizTranCriteria();
        if (Strings2.isNotEmpty(subSystemCode)) {
            bizTranGrp_BizTranCriteria.getSubSystemCodeCriteria().setEqualTo(subSystemCode);

            if (Strings2.isNotEmpty(bizTranGrpCode)) {
                // 取引グループコードから取引グループIDを取得
                BizTranGrpCriteria bizTranGrpCriteria = new BizTranGrpCriteria();
                bizTranGrpCriteria.getBizTranGrpCodeCriteria().setEqualTo(bizTranGrpCode);
                BizTranGrp BizTranGrp = bizTranGrpRepository.findOneBy(bizTranGrpCriteria);
                bizTranGrp_BizTranCriteria.getBizTranGrpIdCriteria().setEqualTo(BizTranGrp.getBizTranGrpId());
            }

            BizTranGrp_BizTrans bizTranGrp_BizTrans = bizTranGrp_BizTransRepository.selectBy(bizTranGrp_BizTranCriteria, Orders.empty());
            List<BizTran> bizTranList = bizTranGrp_BizTrans.getValues().stream().map(
                BizTranGrp_BizTran::getBizTran).collect(Collectors.toList());
            list = SelectOptionItemsSource.createFrom(bizTranList, (!Strings2.isNull(firstRowStatus))).getValue();
        }
        if (Strings2.isNotEmpty(firstRowStatus)) {
            list.get(0).setName(firstRowStatus);
        }
        model.addAttribute("selectAjaxItems", list);
        return viewId+"::ajaxSelectBizTran";
    }
}
