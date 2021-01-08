package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelCommand.BizTranRoleCompositionExcelWriteRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExcelCommand.BizTranRoleCompositionExcelWriteResponse;
import net.jagunma.backbone.auth.authmanager.model.excel.ExcelContainer;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBookRepositoryForWrite;
import org.springframework.stereotype.Service;

/**
 * 取引ロール編成エクスポートExcel Writeサービス
 */
@Service
public class WriteBizTranRoleComposition {

    private final BizTranRoleCompositionBookRepositoryForWrite bizTranRoleCompositionBookRepositoryForWrite;

    // コンストラクタ
    public WriteBizTranRoleComposition(BizTranRoleCompositionBookRepositoryForWrite bizTranRoleCompositionBookRepositoryForWrite) {
        this.bizTranRoleCompositionBookRepositoryForWrite = bizTranRoleCompositionBookRepositoryForWrite;
    }

    /**
     * エクスポートする取引ロール編成Excelを書き出します
     *
     * @param request  取引ロール編成エクスポートExcel Writeサービス Request
     * @param response 取引ロール編成エクスポートExcel Writeサービス Response
     */
    public void execute(BizTranRoleCompositionExcelWriteRequest request, BizTranRoleCompositionExcelWriteResponse response) {

        // 取引ロール編成Excel作成
        ExcelContainer excelContainer = bizTranRoleCompositionBookRepositoryForWrite.create(
            request.getBizTranRole_BizTranGrpsSheet(),
            request.getBizTranGrp_BizTransSheet());

        response.setExcelContainer(excelContainer);
    }
}
