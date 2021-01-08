package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportResponse;
import net.jagunma.backbone.auth.authmanager.infra.web.oa12010.Oa12010Controller;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.BizTranRoleComposition;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.BizTranRoleCompositionRepositoryForStore;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTran.BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp.BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTran;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranGrp_BizTran.BizTranGrp_BizTrans;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 取引ロール編成エクスポート登録サービス
 */
@Service
@Transactional
public class StoreBizTranRoleComposition {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oa12010Controller.class);

    private final BizTranRoleCompositionRepositoryForStore bizTranRoleCompositionRepositoryForStore;

    // コンストラクタ
    public StoreBizTranRoleComposition(BizTranRoleCompositionRepositoryForStore bizTranRoleCompositionRepositoryForStore) {
        this.bizTranRoleCompositionRepositoryForStore = bizTranRoleCompositionRepositoryForStore;
    }

    /**
     * 取引ロール編成を登録します
     *
     * @param request  取引ロール編成エクスポート登録サービス Request
     * @param response 取引ロール編成エクスポート登録サービス Response
     */
    public void execute(BizTranRoleCompositionImportRequest request,
        BizTranRoleCompositionImportResponse response) {

        // サブシステム名からサブシステムコードを取得
        String subSystemCode = SubSystem.nameOf(request.getBizTranGrp_BizTransSheet().getValues().get(0).getSubSystemName()).getCode();

        List<BizTran> bizTranList = newArrayList();
        List<BizTranGrp> bizTranGrpList = newArrayList();
        List<BizTranGrp_BizTran> bizTranGrp_BizTranList = newArrayList();
        List<BizTranRole> bizTranRoleList = newArrayList();
        List<BizTranRole_BizTranGrp> bizTranRole_BizTranGrpList = newArrayList();

        // 登録データ作成（取引、取引グループ）
        LOGGER.debug("### StoreBizTranRoleComposition.execute cteateStoreDataForBizTranAndBizTranGrp 登録データ作成（取引、取引グループ） start");
        cteateStoreDataForBizTranAndBizTranGrp(request, subSystemCode, bizTranList, bizTranGrpList, bizTranGrp_BizTranList);
        LOGGER.debug("### StoreBizTranRoleComposition.execute cteateStoreDataForBizTranAndBizTranGrp 登録データ作成（取引、取引グループ） end");

        // 登録データ作成（取引ロール）
        LOGGER.debug("### StoreBizTranRoleComposition.execute cteateStoreDataForBizTranRole 登録データ作成（取引ロール） start");
        cteateStoreDataForBizTranRole(request, subSystemCode, bizTranRoleList, bizTranRole_BizTranGrpList);
        LOGGER.debug("### StoreBizTranRoleComposition.execute cteateStoreDataForBizTranRole 登録データ作成（取引ロール） end");

        // 取引ロール編成の登録
        bizTranRoleCompositionRepositoryForStore.store(BizTranRoleComposition.createFrom(
            BizTrans.createFrom(bizTranList),
            BizTranGrps.createFrom(bizTranGrpList),
            BizTranGrp_BizTrans.createFrom(bizTranGrp_BizTranList),
            BizTranRoles.createFrom(bizTranRoleList),
            BizTranRole_BizTranGrps.createFrom(bizTranRole_BizTranGrpList)));
    }

    /**
     * 登録データ（取引、取引グループ）を作成します
     *
     * @param request                取引ロール編成エクスポート登録サービス Request
     * @param subSystemCode          サブシステムコード
     * @param bizTranList            取引リスト
     * @param bizTranGrpList         取引グループリスト
     * @param bizTranGrp_BizTranList 取引グループ_取引割当リスト
     */
    void cteateStoreDataForBizTranAndBizTranGrp(BizTranRoleCompositionImportRequest request,
        String subSystemCode,
        List<BizTran> bizTranList,
        List<BizTranGrp> bizTranGrpList,
        List<BizTranGrp_BizTran> bizTranGrp_BizTranList) {

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
                    bizTranGrp_BizTranSheet.getValidThruStartDate(),
                    bizTranGrp_BizTranSheet.getValidThruEndDate(),
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

            // 登録対象の取引グループ_取引割当を抽出
            bizTranGrp_BizTranList.add(BizTranGrp_BizTran.createFrom(
                null,
                null,
                null,
                subSystemCode,
                null,
                BizTranGrp.createFrom(
                    null,
                    bizTranGrp_BizTranSheet.getBizTranGrpCode(),
                    null,
                    subSystemCode,
                    null,
                    null),
                BizTran.createFrom(
                    null,
                    bizTranGrp_BizTranSheet.getBizTranCode(),
                    null,
                    null,
                    null,
                    null,
                    subSystemCode,
                    null,
                    null),
                null));
        }
    }

    /**
     * 登録データ（取引ロール）を作成します
     *
     * @param request                    取引ロール編成エクスポート登録サービス Request
     * @param subSystemCode              サブシステムコード
     * @param bizTranRoleList            取引ロールリスト
     * @param bizTranRole_BizTranGrpList 取引ロール_取引グループ割当リスト
     */
    void cteateStoreDataForBizTranRole(BizTranRoleCompositionImportRequest request,
        String subSystemCode,
        List<BizTranRole> bizTranRoleList,
        List<BizTranRole_BizTranGrp> bizTranRole_BizTranGrpList) {

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

            // 登録対象の取引ロール_取引グループ割当を抽出
            bizTranRole_BizTranGrpList.add(BizTranRole_BizTranGrp.createFrom(
                null,
                null,
                null,
                subSystemCode,
                null,
                BizTranRole.createFrom(
                    null,
                    bizTranRole_BizTranGrpSheet.getBizTranRoleCode(),
                    null,
                    subSystemCode,
                    null,
                    null),
                BizTranGrp.createFrom(
                    null,
                    bizTranRole_BizTranGrpSheet.getBizTranGrpCode(),
                    null,
                    subSystemCode,
                    null,
                    null
                ),
                null));
        }
    }
}
