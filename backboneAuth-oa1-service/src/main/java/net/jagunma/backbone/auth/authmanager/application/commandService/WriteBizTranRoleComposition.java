package net.jagunma.backbone.auth.authmanager.application.commandService;

import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExport.BizTranRoleCompositionExportWriteRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExport.BizTranRoleCompositionExportWriteResponse;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBook;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBookRepositoryForWrite;
import org.springframework.stereotype.Service;

/**
 * 取引ロール編成エクスポートExcel Weiteサービス
 */
@Service
public class WriteBizTranRoleComposition {

    private final BizTranRoleCompositionBookRepositoryForWrite bizTranRoleCompositionBookRepositoryForWrite;

    // コンストラクタ
    WriteBizTranRoleComposition(
        BizTranRoleCompositionBookRepositoryForWrite bizTranRoleCompositionBookRepositoryForWrite) {
        this.bizTranRoleCompositionBookRepositoryForWrite = bizTranRoleCompositionBookRepositoryForWrite;
    }

    /**
     * エクスポートする取引ロール編成Excelを作成します。
     *
     * @param request 取引ロール編成エクスポートExcel Weiteサービス Request Converter
     * @param request 取引ロール編成エクスポートExcel Weiteサービス Response Presenter
     */
    public void execute(BizTranRoleCompositionExportWriteRequest request, BizTranRoleCompositionExportWriteResponse response) {
        // 取引ロール編成Excel作成
        BizTranRoleCompositionBook bizTranRoleCompositionBook = bizTranRoleCompositionBookRepositoryForWrite
            .create(
            request.getBizTranRole_BizTranGrpsSheet(),
            request.getBizTranGrp_BizTransSheet());

        response.setExcelContainer(bizTranRoleCompositionBook.getExcelContainer());

    }
}
