package net.jagunma.backbone.auth.authmanager.infra.excel.bizTranRoleComposition;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBook;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBookRepositoryForWrite;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * 取引ロール編成書き出し
 */
@Component
public class BizTranRoleCompositionBookForWrite implements
    BizTranRoleCompositionBookRepositoryForWrite {

    private final String TemplateExcelfile = "Template取引ロール編成.xlsx";

    // リソースファイルを検索するクラスローダー
    @Autowired
    ResourceLoader resourceLoader;

    /**
     * 取引ロール編成Excelを作成します
     *
     * @param bizTranRole_BizTranGrpsSheet 取引ロール－取引グループ編成群
     * @param bizTranGrp_BizTransSheet     取引グループ－取引編成群
     * @return 取引ロール編成作成結果
     */
    public BizTranRoleCompositionBook create(BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet,
        BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet) {

        Resource resource = resourceLoader.getResource("classpath:" + TemplateExcelfile);
        Workbook workbook = null;

        // Templateを読む
        try {
            FileInputStream is = new FileInputStream(resource.getFile().toString());
            workbook = WorkbookFactory.create(is);
        } catch (IOException e) {
            e.printStackTrace();
            workbook = null;
            throw new GunmaRuntimeException("EOA11003", "Excel Template", e);
        }

        // １番目の(取引ロール－取引グループ編成)シートのデータを作成
        createBizTranRole_BizTranGrpsSheet(workbook, bizTranRole_BizTranGrpsSheet);
        // ２番目の(取引グループ－取引編成)シートのデータを作成
        createBizTranGrp_BizTransSheet(workbook, bizTranGrp_BizTransSheet);

        // excel workbook出力
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            workbook.write(out);

            //TODO: DEBUG
//            FileOutputStream outExcelFile = new FileOutputStream("C:\\Work\\test.xlsx");
//            workbook.write(outExcelFile);

            out.flush();
            out.close();
            workbook.close();
        } catch (IOException e) {
            throw new GunmaRuntimeException("EOA11004", "Excel Template", e);
        } finally {
            workbook = null;
        }

        return BizTranRoleCompositionBook
            .createFrom(ExcelContainer.createFrom(out));
    }

    /**
     * 番目の(取引ロール－取引グループ編成)シートを作成します
     *
     * @param workbook Excel Workbook
     * @param bizTranRole_BizTranGrpsSheet 取引ロール－取引グループ編成群
     */
    void createBizTranRole_BizTranGrpsSheet(Workbook workbook, BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet) {

        // １番目（index:0）のシートを取得
        Sheet sheet = workbook.getSheetAt(0);

        int rowIndex = 2;
        for (BizTranRole_BizTranGrpSheet bizTranRole_BizTranGrpSheet : bizTranRole_BizTranGrpsSheet.getValues()) {
            Row row = getRow(rowIndex, 2, 4, sheet);
            getCell(row, 0).setCellValue(bizTranRole_BizTranGrpSheet.getSubSystemName());
            getCell(row, 1).setCellValue(bizTranRole_BizTranGrpSheet.getBizTranRoleCode());
            getCell(row, 2).setCellValue(bizTranRole_BizTranGrpSheet.getBizTranRoleName());
            getCell(row, 3).setCellValue(bizTranRole_BizTranGrpSheet.getBizTranGrpCode());
            getCell(row, 4).setCellValue(bizTranRole_BizTranGrpSheet.getBizTranGrpName());
            rowIndex++;
        }
    }

    /**
     * ２番目の(取引グループ－取引編成)シートを作成を作成します
     *
     * @param workbook Excel Workbook
     * @param bizTranGrp_BizTransSheet     取引グループ－取引編成群
     */
    void createBizTranGrp_BizTransSheet(Workbook workbook, BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet) {

        // ２番目（index:1）のシートを取得
        Sheet sheet = workbook.getSheetAt(1);
        DataFormat format = workbook.createDataFormat();

        int rowIndex = 2;
        for (BizTranGrp_BizTranSheet bizTranGrp_BizTranSheet : bizTranGrp_BizTransSheet.getValues()) {
            Row row = getRow(rowIndex, 2, 7, sheet);
            getCell(row, 0).setCellValue(bizTranGrp_BizTranSheet.getSubSystemName());
            getCell(row, 1).setCellValue(bizTranGrp_BizTranSheet.getBizTranGrpCode());
            getCell(row, 2).setCellValue(bizTranGrp_BizTranSheet.getBizTranGrpName());
            getCell(row, 3).setCellValue(bizTranGrp_BizTranSheet.getBizTranCode());
            getCell(row, 4).setCellValue(bizTranGrp_BizTranSheet.getBizTranName());
            getCell(row, 5).setCellValue(bizTranGrp_BizTranSheet.getIsCenterBizTran()? 1 : 0);
            getCell(row, 6).setCellValue(bizTranGrp_BizTranSheet.getExpirationStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            getCell(row, 7).setCellValue(bizTranGrp_BizTranSheet.getExpirationEndDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

            rowIndex++;
        }
    }

    /**
     * 指定行から行プロパティをCopyして行を取得します
     *
     * @param rowIndex      取得する行Index
     * @param baseRowIndex  プロパティをCopyする元の行Index
     * @param lastCellIndex プロパティをCopyする列の最終Index
     * @param sheet         対象シート
     * @return
     */
    Row getRow(int rowIndex, int baseRowIndex, int lastCellIndex, Sheet sheet) {
        if (rowIndex == baseRowIndex) {
            return sheet.getRow(rowIndex);
        }

        Row row = sheet.createRow(rowIndex);
        final Row baseRow = sheet.getRow(baseRowIndex);
        row.setHeight(baseRow.getHeight());
        for (int i = 0; i <= lastCellIndex; i++) {
            Cell cell = getCell(row, i);
            Cell baseCell = baseRow.getCell(i);
            CellStyle style = baseCell.getCellStyle();

            //スタイルのコピー
            style.cloneStyleFrom(baseCell.getCellStyle());
            cell.setCellStyle(style);
        }

        return row;
    }

    /**
     * セルを取得します
     *
     * @param row      行
     * @param colIndex 列Index
     * @return セル
     */
    Cell getCell(Row row, int colIndex) {
        Cell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex);
        }
        return cell;
    }
}
