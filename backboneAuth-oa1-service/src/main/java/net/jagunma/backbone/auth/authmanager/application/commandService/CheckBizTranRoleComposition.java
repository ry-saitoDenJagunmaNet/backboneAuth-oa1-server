package net.jagunma.backbone.auth.authmanager.application.commandService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.dto.MessageDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportCheckRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleCompositionCommand.BizTranRoleCompositionImportCheckResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.util.exception.GunmaRuntimeException;
import org.springframework.stereotype.Service;

/**
 * 取引ロール編成エクスポートExcelインポートチェックサービス
 */
@Service
public class CheckBizTranRoleComposition {

    private final BizTranRoleRepository bizTranRoleRepository;
    private final Operator_BizTranRoleRepository operator_BizTranRoleRepository;

    // コンストラクタ
    public CheckBizTranRoleComposition(BizTranRoleRepository bizTranRoleRepository,
        Operator_BizTranRoleRepository operator_BizTranRoleRepository) {
        this.bizTranRoleRepository = bizTranRoleRepository;
        this.operator_BizTranRoleRepository = operator_BizTranRoleRepository;
    }

    /**
     * 取引ロール編成をチェックします
     *
     * @param request  取引ロール編成エクスポートExcelインポートチェックサービス Request
     * @param response 取引ロール編成エクスポートExcelインポートチェックサービス Response
     */
    public void execute(BizTranRoleCompositionImportCheckRequest request,
        BizTranRoleCompositionImportCheckResponse response) {

        // パラメーターの検証
        CheckBizTranRoleCompositionValidator validator = CheckBizTranRoleCompositionValidator.with(request);
        validator.validate();
        // インポートテータのチェック
        List<MessageDto> messageDtoList = validator.checkExcelImport();
        if (messageDtoList.size() > 0) {
            response.setMessageDtoList(messageDtoList);
            throw new GunmaRuntimeException("EOA13001");
        }

        // オペレータ取引ロール割当の関連チェック
        response.setMessageDtoList(checkOperator_BizTranRoleRelation(request));
        response.setStatus("check completed");

    }

    /**
     * オペレータ取引ロール割当の関連チェックを行います
     *  削除対象の取引ロールを使用している オペレータ取引ロール割当 をチェックします
     *
     * @param request 取引ロール編成エクスポートExcelインポートチェックサービス Request
     * @return メッセージシスト
     */
    List<MessageDto> checkOperator_BizTranRoleRelation(BizTranRoleCompositionImportCheckRequest request) {

        List<MessageDto> messageDtoList = newArrayList();

        // 取引ロール検索（登録前）
        BizTranRoleCriteria bizTranRoleCriteria = new BizTranRoleCriteria();
        bizTranRoleCriteria.getSubSystemCodeCriteria().setEqualTo(request.getSubSystemCode());
        BizTranRoles bizTranRoles = bizTranRoleRepository.selectBy(bizTranRoleCriteria, Orders.empty());

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
            Operator_BizTranRoles operator_BizTranRoles = operator_BizTranRoleRepository
                .selectBy(operator_BizTranRoleCriteria, Orders.empty());
            for (Operator_BizTranRole operator_BizTranRole : operator_BizTranRoles.getValues()) {
                messageDtoList.add(MessageDto.createFrom("WOA13107",
                    newArrayList(operator_BizTranRole.getOperator().getOperatorCode(),operator_BizTranRole.getBizTranRole().getBizTranRoleCode())));
            }
        }

        return messageDtoList;
    }
}
