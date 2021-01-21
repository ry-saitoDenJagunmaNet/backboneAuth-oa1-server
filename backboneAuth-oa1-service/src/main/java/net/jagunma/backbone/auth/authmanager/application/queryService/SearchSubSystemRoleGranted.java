package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAllRoleDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;
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
        response.setSignInOperatorId(request.getSignInOperatorId());
        response.setTargetOperatorId(request.getTargetOperatorId());
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
        return operator_SubSystemRoleRepository.selectBy(criteria, Orders.empty().addOrder("Operator_SubSystemRoleId"));
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
        for(Operator_SubSystemRole targetOperator_SubSystemRole : targetOperator_SubSystemRoles.getValues()) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(targetOperator_SubSystemRole);
            assignRoleDto.setIsModifiable(judgeIsModifiable(targetOperator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles));
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
        for (SubSystemRole subSystemRole : SubSystemRole.values()) {//ToDo:ソートオーダー回し実装（ユーティリティで実現）
            if (subSystemRole.getCode().length() == 0) { continue; }
            SubSystemRoleGrantedAllRoleDto allRoleDto = new SubSystemRoleGrantedAllRoleDto();
            allRoleDto.setSubSystemRole(subSystemRole);
            allRoleDto.setIsModifiable(judgeIsModifiable(subSystemRole.getCode(), signInOperator_SubSystemRoles));
            allRoleDtoList.add(allRoleDto);
        }
        return allRoleDtoList;
    }

    /**
     * 変更可否を判定します
     *
     * @param subSystemRoleCode サブシステムロールコード
     * @param signInOperator_SubSystemRoles サインインオペレーターのオペレーター_サブシステムロール割当群
     * @return 変更可否
     */
    boolean judgeIsModifiable(String subSystemRoleCode, Operator_SubSystemRoles signInOperator_SubSystemRoles) {
        LocalDate today = LocalDate.now();

        // サインインオペレーター の オペレーター_サブシステムロール割当群 をコードをキーにしてMap化
        Map<String, Operator_SubSystemRole> signInOperator_SubSystemRoleMap = new HashMap<>();
        for (Operator_SubSystemRole operator_SubSystemRole : signInOperator_SubSystemRoles.getValues()) {
            signInOperator_SubSystemRoleMap.put(operator_SubSystemRole.getSubSystemRoleCode(), operator_SubSystemRole);
        }

        // サインインオペレーター が JA管理者ロール を持っているか
        if (signInOperator_SubSystemRoleMap.containsKey(SubSystemRole.JA管理者.getCode())) {
            // 本日時点で有効か
            Operator_SubSystemRole signInOperator_SubSystemRole = signInOperator_SubSystemRoleMap.get(SubSystemRole.JA管理者.getCode());
            if (!(signInOperator_SubSystemRole.getValidThruStartDate().isAfter(today) ||
                signInOperator_SubSystemRole.getValidThruEndDate().isBefore(today))) {
                return true;
            }
        }

        // サインインオペレーター が 持っているロールか
        if (signInOperator_SubSystemRoleMap.containsKey(subSystemRoleCode)) {
            // 本日時点で有効か
            Operator_SubSystemRole signInOperator_SubSystemRole = signInOperator_SubSystemRoleMap.get(subSystemRoleCode);
            if (!(signInOperator_SubSystemRole.getValidThruStartDate().isAfter(today) ||
                signInOperator_SubSystemRole.getValidThruEndDate().isBefore(today))) {
                return true;
            }
        }

        return false;
    }
}
