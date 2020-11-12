package net.jagunma.backbone.auth.authmanager.infra.web.oa12010;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletResponse;
import net.jagunma.backbone.auth.authmanager.application.commandService.StoreBizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.application.commandService.WriteBizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.application.queryService.RaedBizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.application.queryService.SearchBizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.infra.web.base.BaseOfController;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.vo.Oa12010Vo;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
public class Oa12010Controller extends BaseOfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa12010Controller.class);

    // エクスポートExcelファイル名
    private final String EXPORT_EXCEL_FILE_NAME = "取引ロール編成";
    private final String EXPORT_EXCEL_FILE_CONNECTOR = "－";
    private final String EXPORT_EXCEL_FILE_SUBSYSTEM = "システム";
    private final String EXPORT_EXCEL_FILE_EXTENSION = ".xlsx";
    private final String CHARSET_UTF8 = "UTF-8";

    private final SearchBizTranRoleComposition searchBizTranRoleComposition;
    private final WriteBizTranRoleComposition writeBizTranRoleComposition;
    private final RaedBizTranRoleComposition raedBizTranRoleComposition;
    private final StoreBizTranRoleComposition storeBizTranRoleComposition;

    // コンストラクタ
    Oa12010Controller(SearchBizTranRoleComposition searchBizTranRoleComposition,
        WriteBizTranRoleComposition writeBizTranRoleComposition,
        RaedBizTranRoleComposition raedBizTranRoleComposition,
        StoreBizTranRoleComposition storeBizTranRoleComposition) {

        this.searchBizTranRoleComposition = searchBizTranRoleComposition;
        this.writeBizTranRoleComposition = writeBizTranRoleComposition;
        this.raedBizTranRoleComposition = raedBizTranRoleComposition;
        this.storeBizTranRoleComposition = storeBizTranRoleComposition;
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

        Oa12010Vo vo = new Oa12010Vo();
        try {
            Oa12010InitPresenter presenter = new Oa12010InitPresenter();
            presenter.setMode("import");
            presenter.setSubSystemCode("");

            presenter.bindTo(vo);
            model.addAttribute("form", vo);

            LOGGER.debug("get END");
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
     * 取引ロール編成ファイルをエクスポートします
     *
     * @param model モデル
     * @param vo ViewObject
     * @param response Httpサーブレットレスポンス
     * @return view名（制表示null）
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
    public String  exportExcel(Model model, Oa12010Vo vo, HttpServletResponse response) {
        // ToDo: テストサインイン情報セット
        setAuthInf();

        LOGGER.debug("exportExcel START");

        // エクスポートExcelの作成
        try {
            // 取引ロール編成検索
            Oa12010CompositionExportConverter searchConverter = Oa12010CompositionExportConverter.with(vo);
            Oa12010CompositionExportPresenter searchPresenter = new Oa12010CompositionExportPresenter();
            searchBizTranRoleComposition.execute(searchConverter, searchPresenter);

            // Excel Weite
            Oa12010CompositionExcelWriteConverter writeConverter = searchPresenter.converterTo();
            Oa12010CompositionExcelWritePresenter writehPresenter = new Oa12010CompositionExcelWritePresenter();
            writeBizTranRoleComposition.execute(writeConverter, writehPresenter);

            LOGGER.debug("exportExcel END");
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
        String exportExcelFilename =  EXPORT_EXCEL_FILE_NAME;
        if (Strings2.isNotEmpty(vo.getSubSystemCode())) {
            exportExcelFilename = exportExcelFilename + EXPORT_EXCEL_FILE_CONNECTOR;
            exportExcelFilename = exportExcelFilename + SubSystem.codeOf(vo.getSubSystemCode()).getName();
            exportExcelFilename = exportExcelFilename + EXPORT_EXCEL_FILE_SUBSYSTEM;
        }
        exportExcelFilename = exportExcelFilename + EXPORT_EXCEL_FILE_EXTENSION;
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder
            .encode(exportExcelFilename, StandardCharsets.UTF_8) );

        try {
            response.getOutputStream().write(vo.getExportExcelBook());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException ie) {
            // IOExceptionが発生した場合
            vo.setExceptionMessage(ie);
            model.addAttribute("form", vo);
            return "oa19999";
        }

        return null;
    }

    /**
     * 取引ロール編成ファイルをインポートします
     *
     * @param importfile インポートファイル
     * @param model モデル
     * @param vo ViewObject
     * @return view名
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public String importExcel(@RequestParam("importfile") MultipartFile importfile, Model model, Oa12010Vo vo) {
        // ToDo: テストサインイン情報セット
        setAuthInf();

        LOGGER.debug("importExcel START");

        Oa12010CompositionImportPresenter storePresenter = new Oa12010CompositionImportPresenter();

        ByteArrayInputStream is = null;
        try {
            is = new ByteArrayInputStream(importfile.getBytes());
        } catch (IOException ie) {
            // IOExceptionが発生した場合
            vo.setExceptionMessage(ie);
            model.addAttribute("form", vo);
            return "oa19999";
        }

        try {
            // Excel Read
            Oa12010CompositionExcelReadConverter readConverter = Oa12010CompositionExcelReadConverter.with(vo, is);
            Oa12010CompositionExcelReadPresenter readPresenter = new Oa12010CompositionExcelReadPresenter();
            raedBizTranRoleComposition.execute(readConverter, readPresenter);

            // 取引ロール編成登録
            Oa12010CompositionImportConverter storeConverter = readPresenter.converterTo();
            storeBizTranRoleComposition.execute(storeConverter, storePresenter);
            storePresenter.bindTo(vo);

            model.addAttribute("form", vo);

//            //TODO:
//            vo.setMessage("DEBUG 登録が完了 DEBUG");

            LOGGER.debug("importExcel END");
            return "oa12010";
        } catch (GunmaRuntimeException gre) {
            // 業務例外が発生した場合
            storePresenter.bindTo(vo);
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
}