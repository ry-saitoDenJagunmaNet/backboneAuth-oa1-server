package net.jagunma.backbone.auth.authmanager.infra.web.oa12060;

import net.jagunma.backbone.auth.authmanager.application.commandService.StoreingCalendar;
import net.jagunma.backbone.auth.authmanager.application.queryService.CalendarQueryService;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060SearchResponseVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12060.vo.Oa12060Vo;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * OA12060コントローラ
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA12060
 * 機能名：カレンダーメンテナンス
 * サービスID：OA12060
 * サービス名：OA12060サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA12060", name = "カレンダーメンテナンス")
@ServiceInfo(id = "OA12060", name = "OA12060サービス")
@Controller
@RequestMapping(path = "oa12060")
public class Oa12060Controller extends BaseOfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa12060Controller.class);
    private final CalendarQueryService calendarQueryService;
    private final StoreingCalendar storeingCalendar;

    // コンストラクタ
    public Oa12060Controller(
        CalendarQueryService calendarQueryService,
        StoreingCalendar storeingCalendar) {

        this.calendarQueryService = calendarQueryService;
        this.storeingCalendar = storeingCalendar;
    }

    /**
     * 画面を初期表示します。
     *
     * @param model モデル
     * @return view名
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    private String get(Model model) {
        Oa12060Vo vo = new Oa12060Vo();
        Oa12060InitPresenter presenter = new Oa12060InitPresenter();
        try {
            // 画面を初期化
            presenter.bindTo(vo);
            model.addAttribute("form", vo);

            // カレンダー検索
            return search(model,
                vo.getYearMonthToString(),
                vo.getCalendarTypeFilterCheck1().toString(),
                vo.getCalendarTypeFilterCheck2().toString(),
                vo.getCalendarTypeFilterCheck3().toString(),
                vo.getWorkingdayOrHolidaySelect());
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
        }
    }

    /**
     * カレンダー検索処理を行います。
     * @param model モデル
     * @param ym 年月
     * @param ct1 表示対象経済システム稼働
     * @param ct2 表示対象信用
     * @param ct3 表示対象広域物流
     * @param wh 稼働・休日選択
     * @return view名
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(
        Model model,
        String ym,
        String ct1,
        String ct2,
        String ct3,
        String wh) {

        Oa12060Vo vo = new Oa12060Vo();
        Oa12060SearchResponseVo responseVo = new Oa12060SearchResponseVo();

        try {
            // リクエストパラーメータの設定
            vo.createFrom(ym, Short.parseShort(ct1), Short.parseShort(ct2), Short.parseShort(ct3), wh);

            Oa12060SearchConverter converter = Oa12060SearchConverter.with(vo);
            Oa12060SearchPresenter presenter = new Oa12060SearchPresenter();

            // カレンダー検索
            calendarQueryService.getCalendars(converter, presenter);

            presenter.bindTo(responseVo, vo);
            model.addAttribute("form", vo);
            return "oa12060";
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
        }
    }

    /**
     * カレンダー登録処理を行います。
     *
     * @param model モデル
     * @param vo カレンダー稼働日登録（form json）
     * @return view名
     */
    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public String entry(Model model, Oa12060Vo vo) {
        //TODO: パラメータでサインインオペレーターの情報を取得する
        setAuthInf();

        System.out.println("### YearMonth="+vo.getYearMonthToString());

        try {
            Oa12060EntryConverter converter = Oa12060EntryConverter.with(vo);
            Oa12060SearchPresenter presenter = new Oa12060SearchPresenter();

            // カレンダー登録
            storeingCalendar.execute(converter);

            // カレンダー検索
            search(model,
                vo.getYearMonthToString(),
                vo.getCalendarTypeFilterCheck1().toString(),
                vo.getCalendarTypeFilterCheck2().toString(),
                vo.getCalendarTypeFilterCheck3().toString(),
                vo.getWorkingdayOrHolidaySelect());

            vo = (Oa12060Vo)model.getAttribute("form");
            vo.setMessage("適用しました。");

            model.addAttribute("form", vo);
            return "oa12060";
        } catch (OptimisticLockingFailureException ole) {
            // 楽観的ロック
            vo.setExceptionMessage(ole);
            model.addAttribute("form", vo);
            return "oa19999";
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
        }
    }
}
