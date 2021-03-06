package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAllRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.util.SubSystemRoleGrantedQueryUtil;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedSearchRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedSearchResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeader;
import net.jagunma.backbone.auth.authmanager.model.domain.operatorHistoryPack.operatorHistoryHeader.OperatorHistoryHeaderRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;

/**
 * サブシステムロール付与検索サービス
 */
@Service
public class SearchSubSystemRoleGranted {

    private final Operator_SubSystemRoleRepository operator_SubSystemRoleRepository;
    private final OperatorHistoryHeaderRepository operatorHistoryHeaderRepository;
    private final SubSystemRoleGrantedQueryUtil subSystemRoleGrantedQueryUtil = new SubSystemRoleGrantedQueryUtil();

    // コンストラクタ
    public SearchSubSystemRoleGranted(
        Operator_SubSystemRoleRepository operator_SubSystemRoleRepository,
        OperatorHistoryHeaderRepository operatorHistoryHeaderRepository) {
        this.operator_SubSystemRoleRepository = operator_SubSystemRoleRepository;
        this.operatorHistoryHeaderRepository = operatorHistoryHeaderRepository;
    }

    /**
     * サブシステムロール付与検索します
     *
     * @param request  サブシステムロール付与検索サービス Request
     * @param response サブシステムロール付与検索サービス Response
     */
    public void execute(SubSystemRoleGrantedSearchRequest request, SubSystemRoleGrantedSearchResponse response) {

        // パラメーターの検証
        SearchSubSystemRoleGrantedValidator.with(request).validate();

        // オペレーター_サブシステムロール割当群を検索します（サインインオペレーター）
        Operator_SubSystemRoles signInOperator_SubSystemRoles = searchOperator_SubSystemRoles(request.getSignInOperatorId());

        // オペレーター_サブシステムロール割当群を検索します（ターゲットオペレーター）
        Operator_SubSystemRoles targetOperator_SubSystemRoles = searchOperator_SubSystemRoles(request.getTargetOperatorId());

        // オペレーター履歴ヘッダーを検索します
        OperatorHistoryHeader operatorHistoryHeader = searchOperatorHistoryHeader(request.getTargetOperatorId());

        // アサインロールDtoリストを作成します
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = createAssignRoleDtoList(signInOperator_SubSystemRoles, targetOperator_SubSystemRoles);

        // 全ロールDtoリストを作成します
        List<SubSystemRoleGrantedAllRoleDto> allRoleDtoList = createAllRoleDtoList(signInOperator_SubSystemRoles);

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
     * オペレーター履歴ヘッダーを検索します
     *
     * @param operatorId オペレーターID
     * @return オペレーター履歴ヘッダー
     */
    OperatorHistoryHeader searchOperatorHistoryHeader(Long operatorId) {
        return operatorHistoryHeaderRepository.latestOneByOperatorId(operatorId);
    }

    /**
     * アサインロールDtoリストを作成します
     *
     * @param signInOperator_SubSystemRoles サインインオペレーターのオペレーター_サブシステムロール割当群
     * @param targetOperator_SubSystemRoles ターゲットオペレーターのオペレーター_サブシステムロール割当群
     * @return アサインロールDtoリスト
     */
    List<SubSystemRoleGrantedAssignRoleDto> createAssignRoleDtoList(Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles targetOperator_SubSystemRoles) {
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = newArrayList();
        for(Operator_SubSystemRole operator_SubSystemRole : targetOperator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(operator_SubSystemRole);
            assignRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
            assignRoleDtoList.add(assignRoleDto);
        }
        return assignRoleDtoList;
    }

    /**
     * 全ロールDtoリストを作成します
     *
     * @param signInOperator_SubSystemRoles サインインオペレーターのオペレーター_サブシステムロール割当群
     * @return 全ロールDtoリスト
     */
    List<SubSystemRoleGrantedAllRoleDto> createAllRoleDtoList(Operator_SubSystemRoles signInOperator_SubSystemRoles) {
        List<SubSystemRoleGrantedAllRoleDto> allRoleDtoList = newArrayList();
        for (SubSystemRole subSystemRole : SubSystemRole.getValidList()) {
            SubSystemRoleGrantedAllRoleDto allRoleDto = new SubSystemRoleGrantedAllRoleDto();
            allRoleDto.setSubSystemRole(subSystemRole);
            allRoleDto.setIsModifiable(subSystemRoleGrantedQueryUtil.judgeIsModifiable(subSystemRole.getCode(), signInOperator_SubSystemRoles));
            allRoleDtoList.add(allRoleDto);
        }
        return allRoleDtoList;
    }
}
