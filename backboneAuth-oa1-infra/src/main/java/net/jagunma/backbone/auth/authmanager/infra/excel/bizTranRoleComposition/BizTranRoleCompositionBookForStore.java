package net.jagunma.backbone.auth.authmanager.infra.excel.bizTranRoleComposition;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBook;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBookRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * 取引ロール編成作成
 */
@Component
public class BizTranRoleCompositionBookForStore implements
    BizTranRoleCompositionBookRepositoryForStore {

//    private final BizTranRole_BizTranGrpsRepository bizTranRole_BizTranGrpsRepository;

    // リソースファイルを検索するクラスローダー
    @Autowired
    ResourceLoader resourceLoader;

//    // コンストラクタ
//    BizTranRoleCompositionBookForStore(BizTranRole_BizTranGrpsRepository bizTranRole_BizTranGrpsRepository) {
//        this.bizTranRole_BizTranGrpsRepository = bizTranRole_BizTranGrpsRepository;
//    }

    /**
     * 取引ロール編成Excelを作成します。
     *
     * @param bizTranRole_BizTranGrpsSheet 取引ロール＋取引グループ群
     * @param bizTranGrp_BizTransSheet     取引グループ＋取引群
     * @return 取引ロール編成作成結果
     */
    public BizTranRoleCompositionBook create(BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet,
        BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet) {

        Resource resource = resourceLoader.getResource("classpath:TemplateBizTranRoleOrganization.xlsx");
        Workbook workbook = null;

        try {
            // Templateを読む
            FileInputStream in = new FileInputStream(resource.getFile().toString());
            workbook = WorkbookFactory.create(in);
        } catch (IOException e) {
            e.printStackTrace();
            workbook = null;
            throw new GunmaRuntimeException("EOA11003", "Excel Template", e);
        }
        // １番目の(取引ロール＋取引グループ)シートのデータを作成
        createBizTranRole_BizTranGrpsSheet(workbook, bizTranRole_BizTranGrpsSheet);
        // ２番目の(取引グループ＋取引)シートのデータを作成
        createBizTranRole_BizTranGrpsSheet(workbook, bizTranRole_BizTranGrpsSheet);
//        Sheet sheet = workbook.getSheetAt(0);
//
//        int rowIndex = 2;
//        for (BizTranRole_BizTranGrpSheet bizTranRoleOrganization : bizTranRole_BizTranGrpsSheet.getValues()) {
//            Row row = sheet.createRow(rowIndex);
//            getCell(row, 0).setCellValue(bizTranRoleOrganization.getSubSystemName());
//            getCell(row, 1).setCellValue(bizTranRoleOrganization.getBizTranRoleCode());
//            getCell(row, 2).setCellValue(bizTranRoleOrganization.getBizTranRoleName());
//            getCell(row, 3).setCellValue(bizTranRoleOrganization.getBizTranGrpCode());
//            getCell(row, 4).setCellValue(bizTranRoleOrganization.getBizTranGrpName());
//            rowIndex++;
//        }

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

        return BizTranRoleCompositionBook.createFrom(ExcelContainer.createFrom(out));
    }

    /**
     * 番目の(取引ロール＋取引グループ)シートを作成します。
     *
     * @param workbook Excel Workbook
     * @param bizTranRole_BizTranGrpsSheet 取引ロール＋取引グループ群
     */
    void createBizTranRole_BizTranGrpsSheet(Workbook workbook, BizTranRole_BizTranGrpsSheet bizTranRole_BizTranGrpsSheet) {

        // １番目のシートを取得
        Sheet sheet = workbook.getSheetAt(0);

        int rowIndex = 2;
        for (BizTranRole_BizTranGrpSheet bizTranRoleOrganization : bizTranRole_BizTranGrpsSheet.getValues()) {
            Row row = sheet.createRow(rowIndex);
            getCell(row, 0).setCellValue(bizTranRoleOrganization.getSubSystemName());
            getCell(row, 1).setCellValue(bizTranRoleOrganization.getBizTranRoleCode());
            getCell(row, 2).setCellValue(bizTranRoleOrganization.getBizTranRoleName());
            getCell(row, 3).setCellValue(bizTranRoleOrganization.getBizTranGrpCode());
            getCell(row, 4).setCellValue(bizTranRoleOrganization.getBizTranGrpName());
            rowIndex++;
        }
    }

    /**
     * ２番目の(取引グループ＋取引)シートを作成を作成します。
     *
     * @param workbook Excel Workbook
     * @param bizTranGrp_BizTransSheet     取引グループ＋取引群
     */
    void createBizTranGrp_BizTransSheet(Workbook workbook, BizTranGrp_BizTransSheet bizTranGrp_BizTransSheet) {

        // ２番目のシートを取得
        Sheet sheet = workbook.getSheetAt(0);

        int rowIndex = 2;
        for (BizTranGrp_BizTranSheet bizTranGrp_BizTranSheet : bizTranGrp_BizTransSheet.getValues()) {
            Row row = sheet.createRow(rowIndex);
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

    Row getRow(int rowIndex, int baseRowIndex, int lastCellIndex, Sheet sheet) {
        Row row = sheet.createRow(rowIndex);
        final Row baseRow = sheet.getRow(baseRowIndex);
        row.setHeight(baseRow.getHeight());
        for (int i = 0; i <= lastCellIndex; i++) {
            Cell cell = row.getCell(i);
        }

        return row;
    }

    /**
     * セルを取得します。
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
