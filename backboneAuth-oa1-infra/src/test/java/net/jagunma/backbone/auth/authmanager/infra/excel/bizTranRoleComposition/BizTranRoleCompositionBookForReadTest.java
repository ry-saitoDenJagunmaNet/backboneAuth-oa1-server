package net.jagunma.backbone.auth.authmanager.infra.excel.bizTranRoleComposition;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BizTranRoleCompositionBookForReadTest {

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
    //取引ロール-取引グループシートデータ作成
    private List<BizTranRole_BizTranGrpSheet> createBizTranRole_BizTranGrpSheet() {
        List<BizTranRole_BizTranGrpSheet> list = newArrayList();
        list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産",	"ANAG01",	"（畜産）取引全般",	"ANTG01","データ入力取引グループ"));
        list.add(BizTranRole_BizTranGrpSheet.createFrom(4,"販売・畜産",	"ANAG99",	"（畜産）維持管理責任者",	"ANTG10","センター維持管理グループ"));
        return list;
    }
    //取引グループ-取引シートデータ作成
    private List<BizTranGrp_BizTranSheet> createBizTranGrp_BizTranSheet() {
        List<BizTranGrp_BizTranSheet> list = newArrayList();
        list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        list.add(BizTranGrp_BizTranSheet.createFrom(4,"販売・畜産","ANTG10","センター維持管理グループ","AN0002","畜産業務（センター）メニュー",true,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        return list;
    }

    /**
     * {@link BizTranRoleCompositionBookForRead#read(ExcelContainer,List,List)}テスト
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
        ExcelContainer excelContainer = ExcelContainer.createFrom(new ByteArrayInputStream(createExcelInputStream()));
        List<BizTranRole_BizTranGrpSheet> actualBizTranRole_BizTranGrpSheetList = newArrayList();
        List<BizTranGrp_BizTranSheet> actualBizTranGrp_BizTranSheetList = newArrayList();

        // テスト対象クラス生成
        BizTranRoleCompositionBookForRead bizTranRoleCompositionBookForRead = new BizTranRoleCompositionBookForRead();

        // 期待値
        List<BizTranRole_BizTranGrpSheet> expectedBizTranRole_BizTranGrpSheetList = createBizTranRole_BizTranGrpSheet();
        List<BizTranGrp_BizTranSheet> expectedBizTranGrp_BizTranSheetList = createBizTranGrp_BizTranSheet();

        // 実行
        bizTranRoleCompositionBookForRead.read(excelContainer,
            actualBizTranRole_BizTranGrpSheetList,
            actualBizTranGrp_BizTranSheetList);

        // 結果検証
        assertThat(actualBizTranRole_BizTranGrpSheetList).usingRecursiveComparison().isEqualTo(expectedBizTranRole_BizTranGrpSheetList);
        assertThat(actualBizTranGrp_BizTranSheetList).usingRecursiveComparison().isEqualTo(expectedBizTranGrp_BizTranSheetList);
    }

    /**
     * {@link BizTranRoleCompositionBookForRead#read(ExcelContainer,List,List)}テスト
     *  ●パターン
     *    取引ロール編成Excel読み込みエラー
     *
     *  ●検証事項
     *  ・エラー発生
     */
    @Test
    @Tag(TestSize.SMALL)
    void read_test1() {

        // 実行値
        ExcelContainer excelContainer = ExcelContainer.createFrom(new ByteArrayInputStream("abcd".getBytes()));
        List<BizTranRole_BizTranGrpSheet> actualBizTranRole_BizTranGrpSheetList = newArrayList();
        List<BizTranGrp_BizTranSheet> actualBizTranGrp_BizTranSheetList = newArrayList();

        // テスト対象クラス生成
        BizTranRoleCompositionBookForRead bizTranRoleCompositionBookForRead = new BizTranRoleCompositionBookForRead();

        // 期待値
        String expectedMessageCode = "EOA11003";
        String expectedArgs = "取引ロール編成Excel";

        assertThatThrownBy(() ->
            // 実行
            bizTranRoleCompositionBookForRead.read(excelContainer,
                actualBizTranRole_BizTranGrpSheetList,
                actualBizTranGrp_BizTranSheetList)).isInstanceOfSatisfying(GunmaRuntimeException.class, e -> {
            // 結果検証
            assertThat(e.getMessageCode()).isEqualTo(expectedMessageCode);
            assertThat(e.getArgs()).containsSequence(expectedArgs);
        });
    }
}