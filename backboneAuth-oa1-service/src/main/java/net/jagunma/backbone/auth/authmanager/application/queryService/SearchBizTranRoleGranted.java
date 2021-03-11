package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAllRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.BizTranRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.util.BizTranRoleGrantedQueryUtil;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRoleComposition.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_BizTranRole.Operator_BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;

/**
 * 取引ロール付与検索サービス
 */
@Service
public class SearchBizTranRoleGranted {

    private final Operator_BizTranRoleRepository operator_BizTranRoleRepository;
    private final Operator_SubSystemRoleRepository operator_SubSystemRoleRepository;
    private final OperatorHistoryHeaderRepository operatorHistoryHeaderRepository;
    private final BizTranRoleRepository bizTranRoleRepository;
    private final BizTranRoleGrantedQueryUtil bizTranRoleGrantedQueryUtil = new BizTranRoleGrantedQueryUtil();

    // コンストラクタ
    public SearchBizTranRoleGranted(
        Operator_BizTranRoleRepository operator_BizTranRoleRepository,
        Operator_SubSystemRoleRepository operator_SubSystemRoleRepository,
        OperatorHistoryHeaderRepository operatorHistoryHeaderRepository,
        BizTranRoleRepository bizTranRoleRepository) {
        this.operator_BizTranRoleRepository = operator_BizTranRoleRepository;
        this.operator_SubSystemRoleRepository = operator_SubSystemRoleRepository;
        this.operatorHistoryHeaderRepository = operatorHistoryHeaderRepository;
        this.bizTranRoleRepository = bizTranRoleRepository;
    }

    /**
     * 取引ロール付与検索します
     *
     * @param request  取引ロール付与検索サービス Request
     * @param response 取引ロール付与検索サービス Response
     */
    public void execute(BizTranRoleGrantedSearchRequest request, BizTranRoleGrantedSearchResponse response) {

        // パラメーターの検証
        SearchBizTranRoleGrantedValidator.with(request).validate();

        // オペレーター_サブシステムロール割当群を検索します（サインインオペレーター）
        Operator_SubSystemRoles signInOperator_SubSystemRoles = searchOperator_SubSystemRoles(request.getSignInOperatorId());

        // オペレーター_取引ロール割当群を検索します（ターゲットオペレーター）
        Operator_BizTranRoles targetOperator_BizTranRoles = searchOperator_BizTranRoles(request.getTargetOperatorId());

        // オペレーター履歴ヘッダーを検索します
        OperatorHistoryHeader operatorHistoryHeader = searchOperatorHistoryHeader(request.getTargetOperatorId());

        // 取引ロール割当群を検索します
        BizTranRoles bizTranRoles = searchBizTranRoles();

        // アサインロールDtoリストを作成します
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = createAssignRoleDtoList(signInOperator_SubSystemRoles, targetOperator_BizTranRoles);

        // 全ロールDtoリストを作成します
        List<BizTranRoleGrantedAllRoleDto> allRoleDtoList = createAllRoleDtoList(signInOperator_SubSystemRoles, bizTranRoles);

        // Responseへセット
        response.setOperatorId(request.getTargetOperatorId());
        response.setAssignRoleDtoList(assignRoleDtoList);
        response.setAllRoleDtoList(allRoleDtoList);
        response.setOperatorHistoryHeader(operatorHistoryHeader);
    }

    /**
     * オペレーター_サブシステムロール割当群を検索します
     *
     * @param operatorId オペレーターID
     * @return オペレーター_サブシステムロール割当群
     */
    Operator_SubSystemRoles searchOperator_SubSystemRoles(Long operatorId) {
        Operator_SubSystemRoleCriteria criteria = new Operator_SubSystemRoleCriteria();
        criteria.getOperatorIdCriteria().setEqualTo(operatorId);

        List<Operator_SubSystemRole> operator_SubSystemRoleList = operator_SubSystemRoleRepository.selectBy(criteria, Orders.empty()).getValues();
        operator_SubSystemRoleList = operator_SubSystemRoleList.stream().sorted(Orders.empty().addOrder("subSystemRole.displaySortOrder").toComparator()).collect(Collectors.toList());
        return Operator_SubSystemRoles.createFrom(operator_SubSystemRoleList);
    }

    /**
     * オペレーター_取引ロール割当群を検索します
     *
     * @param operatorId オペレーターID
     * @return オペレーター_取引ロール割当群
     */
    Operator_BizTranRoles searchOperator_BizTranRoles(Long operatorId) {
        Operator_BizTranRoleCriteria criteria = new Operator_BizTranRoleCriteria();
        criteria.getOperatorIdCriteria().setEqualTo(operatorId);

        List<Operator_BizTranRole> operator_BizTranRoleList = operator_BizTranRoleRepository.selectBy(criteria, Orders.empty()).getValues();
        operator_BizTranRoleList = operator_BizTranRoleList.stream().sorted(Orders.empty()
            .addOrder("bizTranRole.subSystem.displaySortOrder")
            .addOrder("bizTranRole.bizTranRoleCode")
            .toComparator()).collect(Collectors.toList());
        return Operator_BizTranRoles.createFrom(operator_BizTranRoleList);
    }

    /**
     * オペレーター履歴ヘッダーを検索します
     *
     * @param operatorId オペレーターID
     * @return オペレーター履歴ヘッダー
     */
    OperatorHistoryHeader searchOperatorHistoryHeader(Long operatorId) {
        return operatorHistoryHeaderRepository.latestOneByOperatorId(operatorId);
    }

    /**
     * 取引ロール割当群を検索します
     *
     * @return オペレーター_取引ロール割当群
     */
    BizTranRoles searchBizTranRoles() {
        List<BizTranRole> bizTranRoleList = bizTranRoleRepository.selectAll(Orders.empty()).getValues();
        bizTranRoleList = bizTranRoleList.stream().sorted(Orders.empty()
            .addOrder("subSystem.displaySortOrder")
            .addOrder("bizTranRoleCode")
            .toComparator()).collect(Collectors.toList());
        return BizTranRoles.createFrom(bizTranRoleList);
    }

    /**
     * アサインロールDtoリストを作成します
     *
     * @param signInOperator_SubSystemRoles サインインオペレーターのオペレーター_サブシステムロール割当群
     * @param targetOperator_BizTranRoles ターゲットオペレーターのオペレーター_取引ロール割当群
     * @return アサインロールDtoリスト
     */
    List<BizTranRoleGrantedAssignRoleDto> createAssignRoleDtoList(Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_BizTranRoles targetOperator_BizTranRoles) {
        List<BizTranRoleGrantedAssignRoleDto> assignRoleDtoList = newArrayList();
        for(Operator_BizTranRole operator_BizTranRole : targetOperator_BizTranRoles.getValues()) {
            BizTranRoleGrantedAssignRoleDto assignRoleDto = new BizTranRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_BizTranRole(operator_BizTranRole);
            assignRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(operator_BizTranRole.getBizTranRole(), signInOperator_SubSystemRoles));
            assignRoleDtoList.add(assignRoleDto);
        }
        return assignRoleDtoList;
    }

    /**
     * 全ロールDtoリストを作成します
     *
     * @param signInOperator_SubSystemRoles サインインオペレーターのオペレーター_サブシステムロール割当群
     * @param bizTranRoles オペレーター_取引ロール割当群
     * @return 全ロールDtoリスト
     */
    List<BizTranRoleGrantedAllRoleDto> createAllRoleDtoList(Operator_SubSystemRoles signInOperator_SubSystemRoles, BizTranRoles bizTranRoles) {
        List<BizTranRoleGrantedAllRoleDto> allRoleDtoList = newArrayList();
        for (BizTranRole bizTranRole : bizTranRoles.getValues()) {
            BizTranRoleGrantedAllRoleDto allRoleDto = new BizTranRoleGrantedAllRoleDto();
            allRoleDto.setBizTranRole(bizTranRole);
            allRoleDto.setIsModifiable(bizTranRoleGrantedQueryUtil.judgeIsModifiable(bizTranRole, signInOperator_SubSystemRoles));
            allRoleDtoList.add(allRoleDto);
        }
        return allRoleDtoList;
    }
}
