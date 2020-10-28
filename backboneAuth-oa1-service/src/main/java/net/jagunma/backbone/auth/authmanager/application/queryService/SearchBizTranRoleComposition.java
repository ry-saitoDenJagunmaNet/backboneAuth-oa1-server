package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionExportSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionExportSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp_BizTran.BizTranGrp_BizTransRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpsRepository;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;

/**
 * 取引ロール編成エクスポート検索サービス
 */
@Service
public class SearchBizTranRoleComposition {

    private final BizTranRole_BizTranGrpsRepository bizTranRole_BizTranGrpsRepository;
    private final BizTranGrp_BizTransRepository bizTranGrp_BizTransRepository;

    // コンストラクタ
    SearchBizTranRoleComposition(BizTranRole_BizTranGrpsRepository bizTranRole_BizTranGrpsRepository,
        BizTranGrp_BizTransRepository bizTranGrp_BizTransRepository) {

        this.bizTranRole_BizTranGrpsRepository = bizTranRole_BizTranGrpsRepository;
        this.bizTranGrp_BizTransRepository = bizTranGrp_BizTransRepository;
    }

    /**
     * エクスポートする取引ロール編成Excelを作成します
     *
     * @param request  取引ロール編成エクスポート検索サービス Request
     * @param response 取引ロール編成エクスポート検索サービス Response
     */
    public void execute(BizTranRoleCompositionExportSearchRequest request, BizTranRoleCompositionExportSearchResponse response)  {

        // 取引ロール_取引グループ割当検索
        BizTranRole_BizTranGrpCriteria bizTranRole_BizTranGrpCriteria = new BizTranRole_BizTranGrpCriteria();
        bizTranRole_BizTranGrpCriteria.getSubSystemCode().setEqualTo(request.getSubSystemCode());
        BizTranRole_BizTranGrps bizTranRole_BizTranGrps = bizTranRole_BizTranGrpsRepository.selectBy(bizTranRole_BizTranGrpCriteria, Orders.empty().addOrder("SubSystemCode"));

        // 取引ロール編成（取引ロール－取引グループ編成）リスト作成
        List<BizTranRole_BizTranGrpSheet> bizTranRole_BizTranGrpSheetList = newArrayList();
        for (BizTranRole_BizTranGrp bizTranRole_BizTranGrp : bizTranRole_BizTranGrps.getValues()) {
            bizTranRole_BizTranGrpSheetList.add(BizTranRole_BizTranGrpSheet.createFrom(
                bizTranRole_BizTranGrpSheetList.size(),
                SubSystem.codeOf(bizTranRole_BizTranGrp.getSubSystemCode()).getName(),
                bizTranRole_BizTranGrp.getBizTranRole().getBizTranRoleCode(),
                bizTranRole_BizTranGrp.getBizTranRole().getBizTranRoleName(),
                bizTranRole_BizTranGrp.getBizTranGrp().getBizTranGrpCode(),
                bizTranRole_BizTranGrp.getBizTranGrp().getBizTranGrpName()
            ));
        }
        response.setBizTranRole_BizTranGrpsSheet(BizTranRole_BizTranGrpsSheet.createFrom(bizTranRole_BizTranGrpSheetList));

        // 取引グループ_取引割当検索
        BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria = new BizTranGrp_BizTranCriteria();
        bizTranGrp_BizTranCriteria.getSubSystemCode().setEqualTo(request.getSubSystemCode());
        BizTranGrp_BizTrans bizTranGrp_BizTrans = bizTranGrp_BizTransRepository.selectBy(bizTranGrp_BizTranCriteria, Orders.empty().addOrder("SubSystemCode"));

        // 取引ロール編成（取引グループ－取引編成）リスト作成
        List<BizTranGrp_BizTranSheet> BizTranGrp_BizTranSheetList = newArrayList();
        for (BizTranGrp_BizTran bizTranGrp_BizTran : bizTranGrp_BizTrans.getValues()) {
            BizTranGrp_BizTranSheetList.add(BizTranGrp_BizTranSheet.createFrom(
                BizTranGrp_BizTranSheetList.size(),
                SubSystem.codeOf(bizTranGrp_BizTran.getSubSystemCode()).getName(),
                bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpCode(),
                bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpName(),
                bizTranGrp_BizTran.getBizTran().getBizTranCode(),
                bizTranGrp_BizTran.getBizTran().getBizTranName(),
                bizTranGrp_BizTran.getBizTran().getIsCenterBizTran(),
                bizTranGrp_BizTran.getBizTran().getExpirationStartDate(),
                bizTranGrp_BizTran.getBizTran().getExpirationEndDate()
            ));
        }
        response.setBizTranGrp_BizTransSheet(BizTranGrp_BizTransSheet.createFrom(BizTranGrp_BizTranSheetList));
    }
}
