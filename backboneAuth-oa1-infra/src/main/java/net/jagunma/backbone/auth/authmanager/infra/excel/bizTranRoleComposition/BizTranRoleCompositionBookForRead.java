package net.jagunma.backbone.auth.authmanager.infra.excel.bizTranRoleComposition;

import static net.jagunma.common.util.strings2.Strings2.isEmpty;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.excel.constant.BizTranRoleCompositionConstants;
import net.jagunma.backbone.auth.authmanager.infra.excel.util.ExcelUtil;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBookRepositoryForRead;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

/**
 * 取引ロール編成取得
 */
@Component
public class BizTranRoleCompositionBookForRead implements BizTranRoleCompositionBookRepositoryForRead {

    /**
     * 取引ロール編成Excelを読み込みます
     *
     * @param excelContainer                  Excel格納
     * @param bizTranRole_BizTranGrpSheetList 取引ロール－取引グループ編成リスト
     * @param bizTranGrp_BizTranSheetList     取引グループ－取引編成リスト
     */
    public void read(ExcelContainer excelContainer,
        List<BizTranRole_BizTranGrpSheet> bizTranRole_BizTranGrpSheetList,
        List<BizTranGrp_BizTranSheet> bizTranGrp_BizTranSheetList) {

        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(excelContainer.getExcelIn());
        } catch (IOException e) {
            throw new GunmaRuntimeException("EOA11003", "取引ロール編成Excel", e);
        }

        // １番目のシートを読み込む
        readBizTranRole_BizTranGrpsSheet(workbook.getSheetAt(BizTranRoleCompositionConstants.INDEX_OF_SHEET1), bizTranRole_BizTranGrpSheetList);
        // ２番目のシートを読み込む
        readBizTranGrp_BizTransSheet(workbook.getSheetAt(BizTranRoleCompositionConstants.INDEX_OF_SHEET2), bizTranGrp_BizTranSheetList);
    }

    /**
     * 取引ロール－取引グループ編成 Excel Sheetを読み込みます
     *
     * @param sheet                           取引ロール－取引グループ編成 Excel Sheet
     * @param bizTranRole_BizTranGrpSheetList 取引ロール－取引グループ編成リスト
     */
    void readBizTranRole_BizTranGrpsSheet(Sheet sheet, List<BizTranRole_BizTranGrpSheet> bizTranRole_BizTranGrpSheetList) {

        for (int i = BizTranRoleCompositionConstants.INDEX_OF_SHEET1_ROWSTART; i <= sheet.getLastRowNum(); i++) {
            String subSystemName = ExcelUtil.getString(sheet, i, BizTranRoleCompositionConstants.INDEX_OF_SHEET1_SUBSYSTE_NAME);
            String bizTranRoleCode = ExcelUtil.getString(sheet, i, BizTranRoleCompositionConstants.INDEX_OF_SHEET1_BIZTRAN_ROLE_CODE);
            String bizTranRoleName = ExcelUtil.getString(sheet, i, BizTranRoleCompositionConstants.INDEX_OF_SHEET1_BIZTRAN_ROLE_NAME);
            String bizTranGrpCode = ExcelUtil.getString(sheet, i, BizTranRoleCompositionConstants.INDEX_OF_SHEET1_BIZTRAN_GRP_CODE);
            String bizTranGrpName = ExcelUtil.getString(sheet, i, BizTranRoleCompositionConstants.INDEX_OF_SHEET1_BIZTRAN_GRP_NAME);

            bizTranRole_BizTranGrpSheetList.add(BizTranRole_BizTranGrpSheet.createFrom(
                i+1,
                subSystemName,
                bizTranRoleCode,
                bizTranRoleName,
                bizTranGrpCode,
                bizTranGrpName));
        }
    }

    /**
     * 取引グループ－取引編成 Excel Sheetを読み込みます
     *
     * @param sheet                       取引グループ－取引編成 Excel Sheet
     * @param bizTranGrp_BizTranSheetList 取引グループ－取引編成取引グループ－取引編成リスト
     */
    void readBizTranGrp_BizTransSheet(Sheet sheet, List<BizTranGrp_BizTranSheet> bizTranGrp_BizTranSheetList) {

        for (int i = BizTranRoleCompositionConstants.INDEX_OF_SHEET1_ROWSTART; i <= sheet.getLastRowNum(); i++) {
            String subSystemName = ExcelUtil.getString(sheet, i, BizTranRoleCompositionConstants.INDEX_OF_SHEET2_SUBSYSTE_NAME);
            String bizTranGrpCode = ExcelUtil.getString(sheet, i, BizTranRoleCompositionConstants.INDEX_OF_SHEET2_BIZTRAN_GRP_CODE);
            String bizTranGrpName = ExcelUtil.getString(sheet, i, BizTranRoleCompositionConstants.INDEX_OF_SHEET2_BIZTRAN_GRP_NAME);
            String bizTranCode = ExcelUtil.getString(sheet, i, BizTranRoleCompositionConstants.INDEX_OF_SHEET2_BIZTRAN_CODE);
            String bizTranName = ExcelUtil.getString(sheet, i, BizTranRoleCompositionConstants.INDEX_OF_SHEET2_BIZTRAN_NAME);
            Integer centerBizTran = ExcelUtil.getInteger(sheet, i, BizTranRoleCompositionConstants.INDEX_OF_SHEET2_CENTER_BIZTRAN);
            String validThruStartDate = ExcelUtil.getString(sheet, i, BizTranRoleCompositionConstants.INDEX_OF_SHEET2_VALIDTHRU_STARTDATE);
            String validThruEndDate = ExcelUtil.getString(sheet, i, BizTranRoleCompositionConstants.INDEX_OF_SHEET2_VALIDTHRU_ENDDATE);

            bizTranGrp_BizTranSheetList.add(BizTranGrp_BizTranSheet.createFrom(
                i+1,
                subSystemName,
                bizTranGrpCode,
                bizTranGrpName,
                bizTranCode,
                bizTranName,
                centerBizTran == 1,
                LocalDate.parse(validThruStartDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                LocalDate.parse(validThruEndDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"))));
        }
    }
}
