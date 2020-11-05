package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.io.IOException;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelReference.BizTranRoleCompositionExcelReadRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelReference.BizTranRoleCompositionExcelReadResponse;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBookRepositoryForRead;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import org.springframework.stereotype.Service;

/**
 * 取引ロール編成エクスポートExcel Raedサービス
 */
@Service
public class RaedBizTranRoleComposition {

    private final BizTranRoleCompositionBookRepositoryForRead bizTranRoleCompositionBookRepositoryForRead;

    // コンストラクタ
    RaedBizTranRoleComposition(
        BizTranRoleCompositionBookRepositoryForRead bizTranRoleCompositionBookRepositoryForRead) {

        this.bizTranRoleCompositionBookRepositoryForRead = bizTranRoleCompositionBookRepositoryForRead;
    }

    /**
     * インポートした取引ロール編成Excelから取引ロール編成を読み込みます
     *
     * @param request 取引ロール編成エクスポートExcel Raedサービス Request
     * @param request 取引ロール編成エクスポートExcel Raedサービス Response
     */
    public void execute(BizTranRoleCompositionExcelReadRequest request, BizTranRoleCompositionExcelReadResponse response) {

        List<BizTranRole_BizTranGrpSheet> bizTranRole_BizTranGrpSheetList = newArrayList();
        List<BizTranGrp_BizTranSheet> bizTranGrp_BizTranSheetList = newArrayList();
        bizTranRoleCompositionBookRepositoryForRead.read(request.getBizTranRoleCompositionBook(),
            bizTranRole_BizTranGrpSheetList,
            bizTranGrp_BizTranSheetList);

        response.setSubSystemCode(request.getSubSystemCode());
        response.setBizTranRole_BizTranGrpsSheet(BizTranRole_BizTranGrpsSheet.createFrom(bizTranRole_BizTranGrpSheetList));
        response.setBizTranGrp_BizTransSheet(BizTranGrp_BizTransSheet.createFrom(bizTranGrp_BizTranSheetList));
    }
}
