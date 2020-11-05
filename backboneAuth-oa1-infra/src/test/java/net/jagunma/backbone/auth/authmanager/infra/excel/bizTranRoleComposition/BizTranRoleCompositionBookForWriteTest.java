package net.jagunma.backbone.auth.authmanager.infra.excel.bizTranRoleComposition;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.common.tests.constants.TestSize;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

@SpringBootTest
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
    //取引ロール-取引グループシートデータ作成
    private List<BizTranRole_BizTranGrpSheet> createBizTranRole_BizTranGrpSheet() {
        List<BizTranRole_BizTranGrpSheet> list = newArrayList();
        list.add(BizTranRole_BizTranGrpSheet.createFrom(2,"販売・畜産",	"ANAG01",	"（畜産）取引全般",	"ANTG01","データ入力取引グループ"));
        list.add(BizTranRole_BizTranGrpSheet.createFrom(3,"販売・畜産",	"ANAG99",	"（畜産）維持管理責任者",	"ANTG10","センター維持管理グループ"));
        return list;
    }
    //取引グループ-取引シートデータ作成
    private List<BizTranGrp_BizTranSheet> createBizTranGrp_BizTranSheet() {
        List<BizTranGrp_BizTranSheet> list = newArrayList();
        list.add(BizTranGrp_BizTranSheet.createFrom(2,"販売・畜産","ANTG01","データ入力取引グループ","AN0001","畜産メインメニュー",false,
            LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        list.add(BizTranGrp_BizTranSheet.createFrom(3,"販売・畜産","ANTG10","センター維持管理グループ","AN0002","畜産業務（センター）メニュー",true,LocalDate.of(2010,6,21),LocalDate.of(9999,12,31)));
        return list;
    }

//    @Autowired
//    ResourceLoader resourceLoader;

    @Test
    @Tag(TestSize.SMALL)
    void read_test0() {

        // 実行値
        BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet = BizTranRole_BizTranGrpsSheet.createFrom(new ArrayList<BizTranRole_BizTranGrpSheet>());
        BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet = BizTranGrp_BizTransSheet.createFrom(new ArrayList<BizTranGrp_BizTranSheet>());

        // テスト対象クラス生成
        BizTranRoleCompositionBookForWrite bizTranRoleCompositionBookForWrite = new BizTranRoleCompositionBookForWrite();

        // 期待値
        // 実行
        ExcelContainer actualExcelContainer = bizTranRoleCompositionBookForWrite.create(
            bizTranRole_BizTranGrpsSheet,
            bizTranGrp_BizTransSheet);

        // 結果検証

    }
}