package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionExportRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionReference.BizTranRoleCompositionExportResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTranRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTransSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpsSheet;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;

/**
 * ?????????????????????????????????????????????????????????
 */
@Service
public class SearchBizTranRoleComposition {

    private final BizTranRole_BizTranGrpRepository bizTranRole_BizTranGrpRepository;
    private final BizTranGrp_BizTranRepository bizTranGrp_BizTranRepository;

    // ?????????????????????
    public SearchBizTranRoleComposition(
        BizTranRole_BizTranGrpRepository bizTranRole_BizTranGrpRepository,
        BizTranGrp_BizTranRepository bizTranGrp_BizTranRepository) {

        this.bizTranRole_BizTranGrpRepository = bizTranRole_BizTranGrpRepository;
        this.bizTranGrp_BizTranRepository = bizTranGrp_BizTranRepository;
    }

    /**
     * ?????????????????????????????????????????????Excel??????????????????
     *
     * @param request  ????????????????????????????????????????????????????????? Request
     * @param response ????????????????????????????????????????????????????????? Response
     */
    public void execute(BizTranRoleCompositionExportRequest request, BizTranRoleCompositionExportResponse response)  {

        // ???????????????????????????
        SearchBizTranRoleCompositionValidator validator = SearchBizTranRoleCompositionValidator.with(request);
        validator.validate();

        // ???????????????_??????????????????????????????
        BizTranRole_BizTranGrpCriteria bizTranRole_BizTranGrpCriteria = new BizTranRole_BizTranGrpCriteria();
        bizTranRole_BizTranGrpCriteria.getSubSystemCodeCriteria().setEqualTo(request.getSubSystemCode());
        BizTranRole_BizTranGrps bizTranRole_BizTranGrps = bizTranRole_BizTranGrpRepository
            .selectBy(bizTranRole_BizTranGrpCriteria, Orders.empty().addOrder("subSystemCode"));

        // ????????????????????????????????????????????????????????????????????????????????????
        List<BizTranRole_BizTranGrpSheet> bizTranRole_BizTranGrpSheetList = newArrayList();
        for (BizTranRole_BizTranGrp bizTranRole_BizTranGrp : bizTranRole_BizTranGrps.getValues()) {
            bizTranRole_BizTranGrpSheetList.add(BizTranRole_BizTranGrpSheet.createFrom(
                bizTranRole_BizTranGrpSheetList.size(),
                SubSystem.codeOf(bizTranRole_BizTranGrp.getSubSystemCode()).getDisplayName(),
                bizTranRole_BizTranGrp.getBizTranRole().getBizTranRoleCode(),
                bizTranRole_BizTranGrp.getBizTranRole().getBizTranRoleName(),
                bizTranRole_BizTranGrp.getBizTranGrp().getBizTranGrpCode(),
                bizTranRole_BizTranGrp.getBizTranGrp().getBizTranGrpName()
            ));
        }
        response.setBizTranRole_BizTranGrpsSheet(BizTranRole_BizTranGrpsSheet.createFrom(bizTranRole_BizTranGrpSheetList));

        // ??????????????????_??????????????????
        BizTranGrp_BizTranCriteria bizTranGrp_BizTranCriteria = new BizTranGrp_BizTranCriteria();
        bizTranGrp_BizTranCriteria.getSubSystemCodeCriteria().setEqualTo(request.getSubSystemCode());
        BizTranGrp_BizTrans bizTranGrp_BizTrans = bizTranGrp_BizTranRepository.selectBy(bizTranGrp_BizTranCriteria, Orders.empty().addOrder("subSystemCode"));

        // ???????????????????????????????????????????????????????????????????????????
        List<BizTranGrp_BizTranSheet> BizTranGrp_BizTranSheetList = newArrayList();
        for (BizTranGrp_BizTran bizTranGrp_BizTran : bizTranGrp_BizTrans.getValues()) {
            BizTranGrp_BizTranSheetList.add(BizTranGrp_BizTranSheet.createFrom(
                BizTranGrp_BizTranSheetList.size(),
                SubSystem.codeOf(bizTranGrp_BizTran.getSubSystemCode()).getDisplayName(),
                bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpCode(),
                bizTranGrp_BizTran.getBizTranGrp().getBizTranGrpName(),
                bizTranGrp_BizTran.getBizTran().getBizTranCode(),
                bizTranGrp_BizTran.getBizTran().getBizTranName(),
                bizTranGrp_BizTran.getBizTran().getIsCenterBizTran(),
                bizTranGrp_BizTran.getBizTran().getValidThruStartDate(),
                bizTranGrp_BizTran.getBizTran().getValidThruEndDate()
            ));
        }
        response.setBizTranGrp_BizTransSheet(BizTranGrp_BizTransSheet.createFrom(BizTranGrp_BizTranSheetList));
    }
}
