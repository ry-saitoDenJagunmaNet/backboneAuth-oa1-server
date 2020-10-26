package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.dto.MessageDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExport.BizTranRoleCompositionImportStoreRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionExport.BizTranRoleCompositionImportStoreResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.Oa12010Controller;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTran.BizTransRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp.BizTranGrpsRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranGrp_BizTran.BizTranGrp_BizTransRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRolesRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole_BizTranGrp.BizTranRole_BizTranGrpsRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 取引ロール編成エクスポートExcel 登録サービス
 */
@Service
@Transactional
public class StoreBizTranRoleComposition {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa12010Controller.class);

    private static BizTransRepositoryForStore bizTransRepositoryForStore;
    private static BizTranGrpsRepositoryForStore bizTranGrpsRepositoryForStore;
    private static BizTranRolesRepositoryForStore bizTranRolesRepositoryForStore;
    private static BizTranRole_BizTranGrpsRepositoryForStore bizTranRole_BizTranGrpsRepositoryForStore;
    private static BizTranGrp_BizTransRepositoryForStore bizTranGrp_BizTransRepositoryForStore;

    // コンストラクタ
    StoreBizTranRoleComposition(
        BizTransRepositoryForStore bizTransRepositoryForStore,
        BizTranGrpsRepositoryForStore bizTranGrpsRepositoryForStore,
        BizTranRolesRepositoryForStore bizTranRolesRepositoryForStore,
        BizTranRole_BizTranGrpsRepositoryForStore bizTranRole_BizTranGrpsRepositoryForStore,
        BizTranGrp_BizTransRepositoryForStore bizTranGrp_BizTransRepositoryForStore) {

        this.bizTransRepositoryForStore = bizTransRepositoryForStore;
        this.bizTranGrpsRepositoryForStore = bizTranGrpsRepositoryForStore;
        this.bizTranRolesRepositoryForStore = bizTranRolesRepositoryForStore;
        this.bizTranRole_BizTranGrpsRepositoryForStore = bizTranRole_BizTranGrpsRepositoryForStore;
        this.bizTranGrp_BizTransRepositoryForStore = bizTranGrp_BizTransRepositoryForStore;
    }

    /**
     * 取引ロール編成を登録します
     *
     * @param request 取引ロール編成インポートExcel 登録サービス Request
     * @param request 取引ロール編成インポートExcel 登録サービス Response
     */
    public void execute(BizTranRoleCompositionImportStoreRequest request,
        BizTranRoleCompositionImportStoreResponse response) {

        // TODO: パラメーターの検証
        StoreBizTranRoleCompositionValidator validator = StoreBizTranRoleCompositionValidator.with(request);
        validator.validate();
        // インポートテータのチェック
        List<MessageDto> messageDtoList = validator.checkExcelImport();
        if (messageDtoList.size() > 0) {
            response.setMessageDtoList(messageDtoList);
            return;
        }


        List<BizTranGrp> bizTranGrpList = newArrayList();
        List<BizTran> bizTranList = newArrayList();
        List<BizTranGrp_BizTran> bizTranGrp_BizTranList = newArrayList();
        List<BizTranRole> bizTranRoleList = newArrayList();
        List<BizTranRole_BizTranGrp> bizTranRole_BizTranGrpList = newArrayList();

        String subSystemCode = SubSystem.nameOf(request.getBizTranGrp_BizTransSheet().getValues().get(0).getSubSystemName()).getCode();

        // 取引グループ_取引割当削除
        bizTranGrp_BizTransRepositoryForStore.delete(subSystemCode);
        LOGGER.debug("### StoreBizTranRoleComposition.execute bizTranGrp_BizTrans delete 取引グループ_取引割当削除");
        // 取引ロール_取引グループ割当削除
        bizTranRole_BizTranGrpsRepositoryForStore.delete(subSystemCode);
        LOGGER.debug("### StoreBizTranRoleComposition.execute bizTranRole_BizTranGrps delete 取引ロール_取引グループ割当削除");

        // 登録データ作成（取引グループ－取引編成）
        LOGGER.debug("### StoreBizTranRoleComposition.execute cteateStoreDataForBizTranAndBizTranGrp 登録データ作成（取引グループ－取引編成） start");
        cteateStoreDataForBizTranAndBizTranGrp(request, subSystemCode, bizTranList, bizTranGrpList);
        LOGGER.debug("### StoreBizTranRoleComposition.execute cteateStoreDataForBizTranAndBizTranGrp 登録データ作成（取引グループ－取引編成） end");
        // 取引登録
        LOGGER.debug("### StoreBizTranRoleComposition.execute bizTrans store 取引登録 start count="+bizTranList.size());
        BizTrans bizTrans = bizTransRepositoryForStore.store(BizTrans.createFrom(bizTranList));
        LOGGER.debug("### StoreBizTranRoleComposition.execute bizTrans store 取引登録 end");
        // 取引グループ登録
        LOGGER.debug("### StoreBizTranRoleComposition.execute bizTranGrps store 取引グループ登録 start count="+bizTranGrpList.size());
        BizTranGrps bizTranGrps = bizTranGrpsRepositoryForStore.store(BizTranGrps.createFrom(bizTranGrpList));
        LOGGER.debug("### StoreBizTranRoleComposition.execute bizTranGrps store 取引グループ登録 end");

        // 登録データ作成（取引グループ_取引割当）
        LOGGER.debug("### StoreBizTranRoleComposition.execute cteateStoreDataForBizTranGrp_BizTran 登録データ作成（取引グループ_取引割当） start");
        cteateStoreDataForBizTranGrp_BizTran(request, bizTranGrps, bizTrans, subSystemCode, bizTranGrp_BizTranList);
        LOGGER.debug("### StoreBizTranRoleComposition.execute cteateStoreDataForBizTranGrp_BizTran 登録データ作成（取引グループ_取引割当） end");
        // 取引グループ_取引割当登録
        LOGGER.debug("### StoreBizTranRoleComposition.execute bizTranGrp_BizTrans insert 取引グループ_取引割当登録 start count="+bizTranGrp_BizTranList.size());
        bizTranGrp_BizTransRepositoryForStore.insert(BizTranGrp_BizTrans.createFrom(bizTranGrp_BizTranList));
        LOGGER.debug("### StoreBizTranRoleComposition.execute bizTranGrp_BizTrans insert 取引グループ_取引割当登録 end");

        // 登録データ作成（取引ロール）
        LOGGER.debug("### StoreBizTranRoleComposition.execute cteateStoreDataForBizTranRole 登録データ作成（取引ロール） start");
        cteateStoreDataForBizTranRole(request, subSystemCode, bizTranRoleList);
        LOGGER.debug("### StoreBizTranRoleComposition.execute cteateStoreDataForBizTranRole 登録データ作成（取引ロール） end");
        // 取引ロール登録
        LOGGER.debug("### StoreBizTranRoleComposition.execute bizTranRoles store 取引ロール登録 start count="+bizTranRoleList.size());
        BizTranRoles bizTranRoles = bizTranRolesRepositoryForStore.store(BizTranRoles.createFrom(bizTranRoleList));
        LOGGER.debug("### StoreBizTranRoleComposition.execute bizTranRoles store 取引ロール登録 end");

        // 登録データ作成（取引ロール_取引グループ割当）
        LOGGER.debug("### StoreBizTranRoleComposition.execute cteateStoreDataForBizTranRole_BizTranGrp 登録データ作成（取引ロール_取引グループ割当） start");
        cteateStoreDataForBizTranRole_BizTranGrp(request,bizTranRoles, bizTranGrps, subSystemCode, bizTranRole_BizTranGrpList);
        LOGGER.debug("### StoreBizTranRoleComposition.execute cteateStoreDataForBizTranRole_BizTranGrp 登録データ作成（取引ロール_取引グループ割当） end");
        // 取引ロール_取引グループ割当登録
        LOGGER.debug("### StoreBizTranRoleComposition.execute bizTranRole_BizTranGrps store 取引ロール_取引グループ割当登録 start count="+bizTranRole_BizTranGrpList.size());
        bizTranRole_BizTranGrpsRepositoryForStore.insert(BizTranRole_BizTranGrps.createFrom(bizTranRole_BizTranGrpList));
        LOGGER.debug("### StoreBizTranRoleComposition.execute bizTranRole_BizTranGrps store 取引ロール_取引グループ割当登録 end");
    }

    /**
     * 登録データ（取引グループ－取引編成）を作成します
     *
     * @param request        取引ロール編成インポートExcel 登録サービス Request
     * @param subSystemCode  サブシステムコード
     * @param bizTranList    取引リスト
     * @param bizTranGrpList 取引グループリスト
     */
    void cteateStoreDataForBizTranAndBizTranGrp(BizTranRoleCompositionImportStoreRequest request,
        String subSystemCode,
        List<BizTran> bizTranList,
        List<BizTranGrp> bizTranGrpList) {

        // 取引グループ－取引編成
        for (BizTranGrp_BizTranSheet bizTranGrp_BizTranSheet : request.getBizTranGrp_BizTransSheet().getValues()) {
            // 登録対象の取引を抽出
            if (bizTranList.stream().filter(
                b->b.getBizTranCode().equals(bizTranGrp_BizTranSheet.getBizTranCode())).count() == 0) {

                bizTranList.add(BizTran.createFrom(
                    null,
                    bizTranGrp_BizTranSheet.getBizTranCode(),
                    bizTranGrp_BizTranSheet.getBizTranName(),
                    bizTranGrp_BizTranSheet.getIsCenterBizTran(),
                    bizTranGrp_BizTranSheet.getExpirationStartDate(),
                    bizTranGrp_BizTranSheet.getExpirationEndDate(),
                    subSystemCode,
                    null,
                    null));
            }

            // 登録対象の取引グループを抽出
            if (bizTranGrpList.stream().filter(
                b->b.getBizTranGrpCode().equals(bizTranGrp_BizTranSheet.getBizTranGrpCode())).count() == 0) {

                bizTranGrpList.add(BizTranGrp.createFrom(
                    null,
                    bizTranGrp_BizTranSheet.getBizTranGrpCode(),
                    bizTranGrp_BizTranSheet.getBizTranGrpName(),
                    subSystemCode,
                    null,
                    null));
            }
        }
    }

    /**
     * 登録データ（取引グループ_取引割当）を作成します
     *
     * @param request                取引ロール編成インポートExcel 登録サービス Request
     * @param bizTranGrps            取引グループ群
     * @param bizTrans               取引群
     * @param subSystemCode          サブシステムコード
     * @param bizTranGrp_BizTranList 取引グループ_取引割当リスト
     */
    void cteateStoreDataForBizTranGrp_BizTran(BizTranRoleCompositionImportStoreRequest request,
        BizTranGrps bizTranGrps,
        BizTrans bizTrans,
        String subSystemCode,
        List<BizTranGrp_BizTran> bizTranGrp_BizTranList) {

        for (BizTranGrp_BizTranSheet bizTranGrp_BizTranSheet : request.getBizTranGrp_BizTransSheet().getValues()) {
            Long bizTranGrpId = bizTranGrps.getValues().stream().filter(
                b->b.getBizTranGrpCode().equals(bizTranGrp_BizTranSheet.getBizTranGrpCode())).findFirst().orElse(null).getBizTranGrpId();
            Long bizTranId = bizTrans.getValues().stream().filter(
                b->b.getBizTranCode().equals(bizTranGrp_BizTranSheet.getBizTranCode())).findFirst().orElse(null).getBizTranId();

            bizTranGrp_BizTranList.add(
                BizTranGrp_BizTran.createFrom(
                    null,
                    bizTranGrpId,
                    bizTranId,
                    subSystemCode,
                    null,
                    null,
                    null,
                    null));
        }
    }

    /**
     * 登録データ（取引ロール）を作成します
     *
     * @param request         取引ロール編成インポートExcel 登録サービス Request
     * @param subSystemCode   サブシステムコード
     * @param bizTranRoleList 取引ロールリスト
     */
    void cteateStoreDataForBizTranRole(BizTranRoleCompositionImportStoreRequest request,
        String subSystemCode,
        List<BizTranRole> bizTranRoleList) {

        // 取引ロール－取引グループ編成
        for (BizTranRole_BizTranGrpSheet bizTranRole_BizTranGrpSheet : request.getBizTranRole_BizTranGrpsSheet().getValues()) {
            // 登録対象の取引ロールを抽出
            if (bizTranRoleList.stream().filter(
                b->b.getBizTranRoleCode().equals(bizTranRole_BizTranGrpSheet.getBizTranRoleCode())).count() == 0) {

                bizTranRoleList.add(BizTranRole.createFrom(
                    null,
                    bizTranRole_BizTranGrpSheet.getBizTranRoleCode(),
                    bizTranRole_BizTranGrpSheet.getBizTranRoleName(),
                    subSystemCode,
                    null,
                    null));
            }
        }
    }

    /**
     * 登録データ（取引ロール_取引グループ割当）を作成します
     *
     * @param request                    取引ロール編成インポートExcel 登録サービス Request
     * @param bizTranRoles               取引ロール群
     * @param bizTranGrps                取引グループ群
     * @param subSystemCode              サブシステムコード
     * @param bizTranRole_BizTranGrpList 取引ロール_取引グループ割当リスト
     */
    void cteateStoreDataForBizTranRole_BizTranGrp(BizTranRoleCompositionImportStoreRequest request,
        BizTranRoles bizTranRoles,
        BizTranGrps bizTranGrps,
        String subSystemCode,
        List<BizTranRole_BizTranGrp> bizTranRole_BizTranGrpList) {

        // 登録対象の取引ロール_取引グループ割当を抽出
        for (BizTranRole_BizTranGrpSheet bizTranRole_BizTranGrpSheet : request.getBizTranRole_BizTranGrpsSheet().getValues()) {
            Long bizTranRoleId = bizTranRoles.getValues().stream().filter(
                b->b.getBizTranRoleCode().equals(bizTranRole_BizTranGrpSheet.getBizTranRoleCode())).findFirst().orElse(null).getBizTranRoleId();
            Long bizTranGrpId = bizTranGrps.getValues().stream().filter(
                b->b.getBizTranGrpCode().equals(bizTranRole_BizTranGrpSheet.getBizTranGrpCode())).findFirst().orElse(null).getBizTranGrpId();

            bizTranRole_BizTranGrpList.add(BizTranRole_BizTranGrp.createFrom(
                null,
                bizTranRoleId,
                bizTranGrpId,
                subSystemCode,
                null,
                null,
                null,
                null));
        }
    }
}
