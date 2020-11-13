package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.SimpleSearchBizTranRole;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchOperator;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBranchAtMoment;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SearchResponseVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SubSystemRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010Vo;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import net.jagunma.common.server.aop.AuditInfoHolder;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * OA11010コントローラ
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA11010
 * 機能名：オペレーター＜一覧＞
 * サービスID：OA11010
 * サービス名：OA11010サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA11010", name = "オペレーター＜一覧＞")
@ServiceInfo(id = "OA11010", name = "OA11010サービス")
@Controller
@RequestMapping(path = "oa11010")
public class Oa11010Controller extends BaseOfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa11010Controller.class);

    private final SearchOperator searchOperator;
    private final SimpleSearchBizTranRole simpleSearchBizTranRole;
    private final SearchBranchAtMoment searchBranchAtMoment;

    // コンストラクタ
    public Oa11010Controller(SearchOperator searchOperator,
        SimpleSearchBizTranRole simpleSearchBizTranRole,
        SearchBranchAtMoment searchBranchAtMoment) {

        this.searchOperator = searchOperator;
        this.simpleSearchBizTranRole = simpleSearchBizTranRole;
        this.searchBranchAtMoment = searchBranchAtMoment;
    }

    /**
     * 画面を初期表示します
     *
     * @param model モデル
     * @return view名
     */
    @GetMapping(path = "/get")
    public String get(Model model) {
        // ToDo: テストサインイン情報セット
        setAuthInf();

        Oa11010Vo vo = new Oa11010Vo();
        try {

            // InitPresenterの初期化
            Oa11010InitPresenter presenter = createInitPresenter();

            presenter.bindTo(vo);
            model.addAttribute("form", vo);
            return "oa11010";

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa11010";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            model.addAttribute("form", vo);
            vo.setExceptionMessage(re);
            return "oa19999";
        }
    }

    /**
     * オペレーター検索処理を行います
     *
     * @param vo 検索条件（form json）
     * @return オペレーター検索結果
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Oa11010SearchResponseVo> search(Oa11010Vo vo) {

        Oa11010SearchResponseVo responseVo = new Oa11010SearchResponseVo();
        try {

            Oa11010SearchConverter converter = Oa11010SearchConverter.with(vo);
            Oa11010SearchPresenter presenter = new Oa11010SearchPresenter();

            // オぺレーター検索
            searchOperator.execute(converter, presenter);

            // ページ
            presenter.setPageNo(vo.getPageNo());
            presenter.bindTo(responseVo);
            return new ResponseEntity<>(responseVo, HttpStatus.OK);

        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            responseVo.setExceptionMessage(gre);
            return new ResponseEntity<>(responseVo, HttpStatus.OK);
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            responseVo.setExceptionMessage(re);
            return new ResponseEntity<>(responseVo, HttpStatus.OK);
        }
    }

    /**
     * InitPresenterの初期化を行います
     *
     * @return InitPresenter
     */
    Oa11010InitPresenter createInitPresenter() {

        Oa11010InitPresenter presenter = new Oa11010InitPresenter();
        // ＪＡ
        presenter.setJaId(AuditInfoHolder.getJa().getIdentifier());
        presenter.setJaCode(AuditInfoHolder.getAuthInf().getJaCode());
        presenter.setJaName(AuditInfoHolder.getJa().getJaAttribute().getName());
        // 店舗リスト
        presenter.setBranchesAtMoment(
            searchBranchAtMoment.selectBy(AuditInfoHolder.getJa().getIdentifier()));
        // 有効期限選択
        presenter.setExpirationSelect(0);
        // サブシステムロール初期選択
        presenter.setSubSystemRoleConditionsSelect(1);
        // サブシステムロールリスト
        List<Oa11010SubSystemRoleVo> subSystemRoleList = newArrayList();
        // 取引ロール初期選択
        presenter.setBizTranRoleConditionsSelect(1);
        // 取引ロール群
        presenter.setBizTranRoles(simpleSearchBizTranRole.getBizTranRoles());

        return presenter;
    }
}
