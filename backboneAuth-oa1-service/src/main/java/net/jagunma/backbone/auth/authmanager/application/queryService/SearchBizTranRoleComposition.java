package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleOrganizationExport.BizTranRoleOrganizationExportRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleOrganizationExport.BizTranRoleOrganizationExportResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpsRepository;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBook;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRoleCompositionBookRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;

/**
 * 取引ロール編成エクスポートサービス
 */
@Service
public class SearchBizTranRoleComposition {

    private final BizTranRole_BizTranGrpsRepository bizTranRole_BizTranGrpsRepository;
    private final BizTranRoleCompositionBookRepositoryForStore bizTranRoleCompositionBookRepositoryForStore;

    // コンストラクタ
    SearchBizTranRoleComposition(BizTranRole_BizTranGrpsRepository bizTranRole_BizTranGrpsRepository,
        BizTranRoleCompositionBookRepositoryForStore bizTranRoleCompositionBookRepositoryForStore) {

        this.bizTranRole_BizTranGrpsRepository = bizTranRole_BizTranGrpsRepository;
        this.bizTranRoleCompositionBookRepositoryForStore = bizTranRoleCompositionBookRepositoryForStore;
    }

    /**
     * エクスポートする取引ロール編成Excelを作成します。
     *
     * @param request  取引ロール編成エクスポートサービス Request
     * @param response 取引ロール編成エクスポートサービス Response
     */
    public void execute(BizTranRoleOrganizationExportRequest request, BizTranRoleOrganizationExportResponse response)  {

        // 取引ロール_取引グループ割当検索
        BizTranRole_BizTranGrpCriteria criteria = new BizTranRole_BizTranGrpCriteria();
        criteria.getSubSystemCode().setEqualTo(request.getSubSystemCode());
        net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps bizTranRole_BizTranGrps = bizTranRole_BizTranGrpsRepository.selectBy(criteria, Orders.empty().addOrder("SubSystemCode"));

        // 取引ロール編成リスト作成
        List<BizTranRole_BizTranGrpSheet> list = newArrayList();
        for (net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp bizTranRole_BizTranGrp : bizTranRole_BizTranGrps.getValues()) {
            list.add(BizTranRole_BizTranGrpSheet.createFrom(
                SubSystem.codeOf(bizTranRole_BizTranGrp.getSubSystemCode()).getName(),
                bizTranRole_BizTranGrp.getBizTranRole().getBizTranRoleCode(),
                bizTranRole_BizTranGrp.getBizTranRole().getBizTranRoleName(),
                bizTranRole_BizTranGrp.getBizTranGrp().getBizTranGrpCode(),
                bizTranRole_BizTranGrp.getBizTranGrp().getBizTranGrpName()
            ));
        }

        // 取引ロール編成Excel作成
        BizTranRoleCompositionBook bizTranRoleCompositionBook = bizTranRoleCompositionBookRepositoryForStore
            .create(BizTranRole_BizTranGrpsSheet.createFrom(list));

        response.setExcelContainer(bizTranRoleCompositionBook.getExcelContainer());
    }
}
