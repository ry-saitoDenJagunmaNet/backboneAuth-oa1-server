package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.dto.MessageDto;
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
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrp;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole_BizTranGrp.BizTranRole_BizTranGrps;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRolesRepository;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranGrp_BizTranSheet;
import net.jagunma.backbone.auth.authmanager.model.excel.bizTranRoleComposition.BizTranRole_BizTranGrpSheet;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.util.exception.GunmaRuntimeException;
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

    private final BizTranRoleCompositionRepositoryForStore bizTranRoleCompositionRepositoryForStore;
    private final BizTranRolesRepository bizTranRolesRepository;
    private final Operator_BizTranRolesRepository operator_BizTranRolesRepository;

    // コンストラクタ
    public StoreBizTranRoleComposition(BizTranRoleCompositionRepositoryForStore bizTranRoleCompositionRepositoryForStore,
        BizTranRolesRepository bizTranRolesRepository,
        Operator_BizTranRolesRepository operator_BizTranRolesRepository) {
        this.bizTranRoleCompositionRepositoryForStore = bizTranRoleCompositionRepositoryForStore;
        this.bizTranRolesRepository = bizTranRolesRepository;
        this.operator_BizTranRolesRepository = operator_BizTranRolesRepository;
    }

    /**
     * 取引ロール編成を登録します
     *
     * @param request 取引ロール編成インポートExcel 登録サービス Request
     * @param response 取引ロール編成インポートExcel 登録サービス Response
     */
    public void execute(BizTranRoleCompositionImportRequest request,
        BizTranRoleCompositionImportResponse response) {

        // パラメーターの検証
        StoreBizTranRoleCompositionValidator validator = StoreBizTranRoleCompositionValidator.with(request);
        validator.validate();
        // インポートテータのチェック
        List<MessageDto> messageDtoList = validator.checkExcelImport();
        if (messageDtoList.size() > 0) {
            response.setMessageDtoList(messageDtoList);
            throw new GunmaRuntimeException("EOA13001");
        }

        // オペレータ取引ロール割当の関連チェック
        response.setMessageDtoList(checkOperator_BizTranRoleRelation(request));

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
     * オペレータ取引ロール割当の関連チェックを行います
     *  削除対象の取引ロールを使用している オペレータ取引ロール割当 をチェックします
     *
     * @param request 取引ロール編成インポートExcel 登録サービス Request
     * @return メッセージシスト
     */
    List<MessageDto> checkOperator_BizTranRoleRelation(BizTranRoleCompositionImportRequest request) {

        List<MessageDto> messageDtoList = newArrayList();

        // 取引ロール検索（登録前）
        BizTranRoleCriteria bizTranRoleCriteria = new BizTranRoleCriteria();
        bizTranRoleCriteria.getSubSystemCodeCriteria().setEqualTo(request.getSubSystemCode());
        BizTranRoles bizTranRoles = bizTranRolesRepository.selectBy(bizTranRoleCriteria, Orders.empty());

        // 登録前取引ロールで取引ロール編成に無い取引ロールを削除対象とする
        List<Long> bizTranRoleIdList = newArrayList();
        for (BizTranRole bizTranRole : bizTranRoles.getValues()) {
            if (request.getBizTranRole_BizTranGrpsSheet().getValues().stream().filter(
                b->b.getBizTranRoleCode().equals(bizTranRole.getBizTranRoleCode())).count() == 0) {
                bizTranRoleIdList.add(bizTranRole.getBizTranRoleId());
            }
        }

        // オペレーター_取引ロール割当検索
        if (bizTranRoleIdList.size() > 0) {
            Operator_BizTranRoleCriteria operator_BizTranRoleCriteria = new Operator_BizTranRoleCriteria();
            operator_BizTranRoleCriteria.getBizTranRoleIdCriteria().getIncludes().addAll(bizTranRoleIdList);
            Operator_BizTranRoles operator_BizTranRoles = operator_BizTranRolesRepository.selectBy(operator_BizTranRoleCriteria, Orders.empty());
            for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoles.getValues()) {
                messageDtoList.add(MessageDto.createFrom("EOA13107",
                    newArrayList(operator_BizTranRole.getOperator().getOperatorCode(),operator_BizTranRole.getBizTranRole().getBizTranRoleCode())));
            }
        }

        return messageDtoList;
    }

    /**
     * 登録データ（取引、取引グループ）を作成します
     *
     * @param request                取引ロール編成インポートExcel 登録サービス Request
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
     * @param request                    取引ロール編成インポートExcel 登録サービス Request
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
