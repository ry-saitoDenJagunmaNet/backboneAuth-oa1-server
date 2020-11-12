package net.jagunma.backbone.auth.authmanager.infra.excel.bizTranRoleComposition;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.excel.constant.BizTranRoleCompositionConstants;
import net.jagunma.backbone.auth.authmanager.infra.excel.util.ExcelUtil;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranRoleCompositionBookForWriteTest {

    // 取引ロール編成作成
    private byte[] createExcelInputStream() {
        List<BizTranRole_BizTranGrpSheet> bizTranRole_BizTranGrpSheetList = createBizTranRole_BizTranGrpSheet();
        List<BizTranGrp_BizTranSheet> bizTranGrp_BizTranSheetList = createBizTranGrp_BizTranSheet();

        Cell cell;
        int rowIndex;
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet1 = workbook.createSheet();
        rowIndex = 0;
        for (BizTranRole_BizTranGrpSheet item : bizTranRole_BizTranGrpSheetList) {
            Row row = sheet1.createRow(rowIndex+2);
            cell = row.createCell(0);
            cell.setCellValue(item.getSubSystemName());
            cell = row.createCell(1);
            cell.setCellValue(item.getBizTranRoleCode());
            cell = row.createCell(2);
            cell.setCellValue(item.getBizTranRoleName());
            cell = row.createCell(3);
            cell.setCellValue(item.getBizTranGrpCode());
            cell = row.createCell(4);
            cell.setCellValue(item.getBizTranGrpName());
            rowIndex++;
        }
        Sheet sheet2 = workbook.createSheet();
        rowIndex = 0;
        for (BizTranGrp_BizTranSheet item : bizTranGrp_BizTranSheetList) {
            Row row = sheet2.createRow(rowIndex+2);
            cell = row.createCell(0);
            cell.setCellValue(item.getSubSystemName());
            cell = row.createCell(1);
            cell.setCellValue(item.getBizTranGrpCode());
            cell = row.createCell(2);
            cell.setCellValue(item.getBizTranGrpName());
            cell = row.createCell(3);
            cell.setCellValue(item.getBizTranCode());
            cell = row.createCell(4);
            cell.setCellValue(item.getBizTranName());
            cell = row.createCell(5);
            cell.setCellValue(item.getIsCenterBizTran()? 1: 0);
            cell = row.createCell(6);
            cell.setCellValue(item.getExpirationStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            cell = row.createCell(7);
            cell.setCellValue(item.getExpirationEndDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            rowIndex++;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }
    // 取引ロール-取引グループシートデータ作成
    private List<BizTranRole_BizTranGrpSheet> createBizTranRole_BizTranGrpSheet() {
        List<BizTranRole_BizTranGrpSheet> list = newArrayList();
        list.add(BizTranRole_BizTranGrpSheet.createFrom(2,"販売・畜産",	"ANAG01",	"（畜産）取引全般",	"ANTG01","データ入力取引グループ"));
        list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産",	"ANAG99",	"（畜産）維持管理責任者","ANTG10","センター維持管理グループ"));
        return list;
    }
    // 取引グループ-取引シートデータ作成
    private List<BizTranGrp_BizTranSheet> createBizTranGrp_BizTranSheet() {
        List<BizTranGrp_BizTranSheet> list = newArrayList();
        list.add(BizTranGrp_BizTranSheet.createFrom(2,"販売・畜産","ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG10","センター維持管理グループ","AN0002","畜産業務（センター）メニュー",true,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        return list;
    }

    /**
     * {@link BizTranRoleCompositionBookForWrite#create(BizTranRole_BizTranGrpsSheet,BizTranGrp_BizTransSheet)}テスト
     *  ●パターン
     *    正常
     *
     *  ●検証事項
     *  ・正常終了
     */
    @Test
    @Tag(TestSize.SMALL)
    void read_test0() {

        // 実行値
        BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet = BizTranRole_BizTranGrpsSheet.createFrom(createBizTranRole_BizTranGrpSheet());
        BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet = BizTranGrp_BizTransSheet.createFrom(createBizTranGrp_BizTranSheet());

        // テスト対象クラス生成
        BizTranRoleCompositionBookForWrite bizTranRoleCompositionBookForWrite = new BizTranRoleCompositionBookForWrite();

        // 期待値
        List<BizTranRole_BizTranGrpSheet> expectedBizTranRole_BizTranGrpSheet = createBizTranRole_BizTranGrpSheet();
        List<BizTranGrp_BizTranSheet> expectedBizTranGrp_BizTranSheet = createBizTranGrp_BizTranSheet();

        // 実行
        ExcelContainer actualExcelContainer = bizTranRoleCompositionBookForWrite.create(
            bizTranRole_BizTranGrpsSheet,
            bizTranGrp_BizTransSheet);

        // 結果検証
        Workbook actualWorkbook = null;
        try {
            actualWorkbook = WorkbookFactory.create(new ByteArrayInputStream(actualExcelContainer.getExcelOut().toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 取引ロール-取引グループシート
        Sheet actualSheet1 = actualWorkbook.getSheetAt(BizTranRoleCompositionConstants.INDEX_OF_SHEET1);
        int expectedIndex = 0;
        for (int excelRow = BizTranRoleCompositionConstants.INDEX_OF_SHEET1_ROWSTART; excelRow <= actualSheet1.getLastRowNum(); excelRow++) {
            assertThat(ExcelUtil.getString(actualSheet1, excelRow, BizTranRoleCompositionConstants.INDEX_OF_SHEET1_SUBSYSTE_NAME)).
                isEqualTo(expectedBizTranRole_BizTranGrpSheet.get(expectedIndex).getSubSystemName());
            assertThat(ExcelUtil.getString(actualSheet1, excelRow, BizTranRoleCompositionConstants.INDEX_OF_SHEET1_BIZTRAN_ROLE_CODE)).
                isEqualTo(expectedBizTranRole_BizTranGrpSheet.get(expectedIndex).getBizTranRoleCode());
            assertThat(ExcelUtil.getString(actualSheet1, excelRow, BizTranRoleCompositionConstants.INDEX_OF_SHEET1_BIZTRAN_ROLE_NAME)).
                isEqualTo(expectedBizTranRole_BizTranGrpSheet.get(expectedIndex).getBizTranRoleName());
            assertThat(ExcelUtil.getString(actualSheet1, excelRow, BizTranRoleCompositionConstants.INDEX_OF_SHEET1_BIZTRAN_GRP_CODE)).
                isEqualTo(expectedBizTranRole_BizTranGrpSheet.get(expectedIndex).getBizTranGrpCode());
            assertThat(ExcelUtil.getString(actualSheet1, excelRow, BizTranRoleCompositionConstants.INDEX_OF_SHEET1_BIZTRAN_GRP_NAME)).
                isEqualTo(expectedBizTranRole_BizTranGrpSheet.get(expectedIndex).getBizTranGrpName());
            expectedIndex++;
        }
        // 取引グループ-取引シート
        Sheet actualSheet2 = actualWorkbook.getSheetAt(BizTranRoleCompositionConstants.INDEX_OF_SHEET2);
        expectedIndex = 0;
        for (int excelRow = BizTranRoleCompositionConstants.INDEX_OF_SHEET2_ROWSTART; excelRow <= actualSheet2.getLastRowNum(); excelRow++) {
            assertThat(ExcelUtil.getString(actualSheet2, excelRow, BizTranRoleCompositionConstants.INDEX_OF_SHEET2_SUBSYSTE_NAME)).
                isEqualTo(expectedBizTranGrp_BizTranSheet.get(expectedIndex).getSubSystemName());
            assertThat(ExcelUtil.getString(actualSheet2, excelRow, BizTranRoleCompositionConstants.INDEX_OF_SHEET2_BIZTRAN_GRP_CODE)).
                isEqualTo(expectedBizTranGrp_BizTranSheet.get(expectedIndex).getBizTranGrpCode());
            assertThat(ExcelUtil.getString(actualSheet2, excelRow, BizTranRoleCompositionConstants.INDEX_OF_SHEET2_BIZTRAN_GRP_NAME)).
                isEqualTo(expectedBizTranGrp_BizTranSheet.get(expectedIndex).getBizTranGrpName());
            assertThat(ExcelUtil.getString(actualSheet2, excelRow, BizTranRoleCompositionConstants.INDEX_OF_SHEET2_BIZTRAN_CODE)).
                isEqualTo(expectedBizTranGrp_BizTranSheet.get(expectedIndex).getBizTranCode());
            assertThat(ExcelUtil.getString(actualSheet2, excelRow, BizTranRoleCompositionConstants.INDEX_OF_SHEET2_BIZTRAN_NAME)).
                isEqualTo(expectedBizTranGrp_BizTranSheet.get(expectedIndex).getBizTranName());
            assertThat(ExcelUtil.getInteger(actualSheet2, excelRow, BizTranRoleCompositionConstants.INDEX_OF_SHEET2_CENTER_BIZTRAN)).
                isEqualTo(expectedBizTranGrp_BizTranSheet.get(expectedIndex).getIsCenterBizTran()? 1 : 0);
            assertThat(ExcelUtil.getString(actualSheet2, excelRow, BizTranRoleCompositionConstants.INDEX_OF_SHEET2_EXPIRATION_STARTDATE)).
                isEqualTo(expectedBizTranGrp_BizTranSheet.get(expectedIndex).getExpirationStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            expectedIndex++;
        }
    }

    /**
     * {@link BizTranRoleCompositionBookForWrite#getTemplateResource(String)}テスト
     *  ●パターン
     *    取引ロール編成Excel Template Resource読み込みエラー
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void getTemplateResource_test0() {
        // 実行値
        String templateFileName = "無いファイル";

        // テスト対象クラス生成
        BizTranRoleCompositionBookForWrite bizTranRoleCompositionBookForWrite = new BizTranRoleCompositionBookForWrite();

        // 期待値
        String expectedMessageCode = "EOA11003";
        String expectedArgs = "取引ロール編成Excel Template";

        assertThatThrownBy(() ->
            // 実行
            bizTranRoleCompositionBookForWrite.getTemplateResource(templateFileName)).isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
            // 結果検証
            assertThat(e.getMessageCode()).isEqualTo(expectedMessageCode);
            assertThat(e.getArgs()).containsSequence(expectedArgs);
        });
    }

    /**
     * {@link BizTranRoleCompositionBookForWrite#getTemplateWorkbook(File)}テスト
     *  ●パターン
     *    取引ロール編成Excel Template 読み込みエラー
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void getTemplateWorkbook_test0() {
        // 実行値
        File templateFile = new File("");

        // テスト対象クラス生成
        BizTranRoleCompositionBookForWrite bizTranRoleCompositionBookForWrite = new BizTranRoleCompositionBookForWrite();

        // 期待値
        String expectedMessageCode = "EOA11003";
        String expectedArgs = "取引ロール編成Excel Template";

        assertThatThrownBy(() ->
            // 実行
            bizTranRoleCompositionBookForWrite.getTemplateWorkbook(templateFile)).isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
            // 結果検証
            assertThat(e.getMessageCode()).isEqualTo(expectedMessageCode);
            assertThat(e.getArgs()).containsSequence(expectedArgs);
        });

    }

    /**
     * {@link BizTranRoleCompositionBookForWrite#getTemplateWorkbook(File)}テスト
     *  ●パターン
     *    取引ロール編成Excel 出力エラー
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void wrietExcelWorkbook_test0() {
        // 実行値
        Workbook workbook = null;

        // テスト対象クラス生成
        BizTranRoleCompositionBookForWrite bizTranRoleCompositionBookForWrite = new BizTranRoleCompositionBookForWrite();

        // 期待値
        String expectedMessageCode = "EOA11004";
        String expectedArgs = "取引ロール編成Excel";

        assertThatThrownBy(() ->
            // 実行
            bizTranRoleCompositionBookForWrite.wrietExcelWorkbook(workbook)).isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
            // 結果検証
            assertThat(e.getMessageCode()).isEqualTo(expectedMessageCode);
            assertThat(e.getArgs()).containsSequence(expectedArgs);
        });
    }
}