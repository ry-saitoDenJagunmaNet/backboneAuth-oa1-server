package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletResponse;
import net.jagunma.backbone.auth.authmanager.application.commandService.WriteBizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;
import net.jagunma.common.server.annotation.FeatureGroupInfo;
import net.jagunma.common.server.annotation.FeatureInfo;
import net.jagunma.common.server.annotation.ServiceInfo;
import net.jagunma.common.server.annotation.SubSystemInfo;
import net.jagunma.common.server.annotation.SystemInfo;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * OA12010コントローラ
 *
 * <pre>
 * -------------------------------------------------
 * システム：O 業務共通システム
 * サブシステム：OA 基幹系認証管理サブシステム
 * 機能グループID：OA1
 * 機能グループ名：管理WEB
 * 機能ID：OA12010
 * 機能名：取引ロール編成インポート＆エクスポート
 * サービスID：OA12060
 * サービス名：OA12060サービス
 * -------------------------------------------------
 * </pre>
 */
@SystemInfo(id = "O", name = "業務共通システム")
@SubSystemInfo(id = "OA", name = "基幹系認証管理サブシステム")
@FeatureGroupInfo(id = "OA1", name = "管理WEB")
@FeatureInfo(id = "OA12010", name = "取引ロール編成インポート＆エクスポート")
@ServiceInfo(id = "OA12010", name = "OA12010サービス")
@Controller
@RequestMapping(path = "oa12010")
public class Oa12010Controller {

    // エクスポートExcelファイル名
    private final String exportExcelFileName = "取引ロール編成.xlsx";
    private final String CHARSET_UTF8 = "UTF-8";

    private final SearchBizTranRoleComposition searchBizTranRoleComposition;
    private final WriteBizTranRoleComposition writeBizTranRoleComposition;

    // コンストラクタ
    Oa12010Controller(SearchBizTranRoleComposition searchBizTranRoleComposition,
        WriteBizTranRoleComposition writeBizTranRoleComposition) {

        this.searchBizTranRoleComposition = searchBizTranRoleComposition;
        this.writeBizTranRoleComposition = writeBizTranRoleComposition;
    }

    /**
     * 画面を初期表示します。
     *
     * @param model モデル
     * @return view名
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(Model model) {

        Oa12010Vo vo = new Oa12010Vo();
        try {
            Oa12010InitPresenter presenter = new Oa12010InitPresenter();
            presenter.setMode("import");
            presenter.setSubSystemCode("");

            presenter.bindTo(vo);
            model.addAttribute("form", vo);
            return "oa12010";
        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa12010";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }
    }

    /**
     * 取引ロール編成エクスポートします。
     *
     * @param model モデル
     * @param vo ViewObject
     * @param response Httpサーブレットレスポンス
     * @return view名（制表示null）
     */
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public String  export(Model model, Oa12010Vo vo, HttpServletResponse response) {
        System.out.println("### Oa12010Controller export START");

        // エクスポートExcelの作成
        try {
            // 取引ロール編成検索
            Oa12010CompositionExportSearchConverter searchConverter = Oa12010CompositionExportSearchConverter.with(vo);
            Oa12010CompositionExportSearchPresenter searchPresenter = new Oa12010CompositionExportSearchPresenter();
            searchBizTranRoleComposition.execute(searchConverter, searchPresenter);

            // Excel Weite
            Oa12010CompositionExportWriteConverter writeConverter = searchPresenter.ConverterTo();
            Oa12010CompositionExportWritePresenter writehPresenter = new Oa12010CompositionExportWritePresenter();
            writeBizTranRoleComposition.execute(writeConverter, writehPresenter);

            writehPresenter.bindTo(vo);
        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            vo.setExceptionMessage(gre);
            model.addAttribute("form", vo);
            return "oa12010";
        } catch (RuntimeException re) {
            // その他予期せぬ例外が発生した場合
            vo.setExceptionMessage(re);
            model.addAttribute("form", vo);
            return "oa19999";
        }

        // エクスポート
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder
            .encode(exportExcelFileName, StandardCharsets.UTF_8) );

        try {
            response.getOutputStream().write(vo.getExportExcelBook());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            e.getStackTrace();
        }

        return null;
    }
}