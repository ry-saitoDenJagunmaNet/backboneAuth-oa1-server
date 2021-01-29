package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleGrantedAssignRoleDto;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyResponse;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRole;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoleRepository;
import net.jagunma.backbone.auth.authmanager.model.domain.operator_SubSystemRole.Operator_SubSystemRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;

/**
 * サブシステムロール付与コピーサービス
 */
@Service
public class CopySubSystemRoleGranted {

    private final Operator_SubSystemRoleRepository operator_SubSystemRoleRepository;

    // コンストラクタ
    public CopySubSystemRoleGranted(Operator_SubSystemRoleRepository operator_SubSystemRoleRepository) {
        this.operator_SubSystemRoleRepository = operator_SubSystemRoleRepository;
    }

    /**
     * サブシステムロール付与コピーします
     *
     * @param request  サブシステムロール付与コピーサービス Request
     * @param response サブシステムロール付与コピーサービス Response
     */
    public void execute(SubSystemRoleGrantedCopyRequest request, SubSystemRoleGrantedCopyResponse response) {

        // パラメーターの検証
        CopySubSystemRoleGrantedValidator.with(request).validate();

        // オペレーター_サブシステムロール割当群を検索します（サインインオペレーター）
        Operator_SubSystemRoles signInOperator_SubSystemRoles = searchOperator_SubSystemRoles(request.getSignInOperatorId());

        // オペレーター_サブシステムロール割当群を検索します（選択オペレーター）
        Operator_SubSystemRoles selectedOperator_SubSystemRoles = searchOperator_SubSystemRoles(request.getSelectedOperatorId());

        // アサインロールDtoリストにサインインオペレーターが操作可能な選択オペレーターのオペレーター_サブシステムロールをコピー追加します
        List<SubSystemRoleGrantedAssignRoleDto> assignRoleDtoList = copyAddAssignRoleDtoList(request.getAssignRoleList(), signInOperator_SubSystemRoles, selectedOperator_SubSystemRoles);

        // Responseへセット
        response.setAssignRoleDtoList(assignRoleDtoList);
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
        return operator_SubSystemRoleRepository.selectBy(criteria, Orders.empty());
    }

    /**
     * アサインロールDtoリストにサインインオペレーターが操作可能な選択オペレーターのオペレーター_サブシステムロールをコピー追加します
     *
     * @param currentAssignRoleList 現在のアサインロールリスト
     * @param signInOperator_SubSystemRoles サインインオペレーターのオペレーター_サブシステムロール割当群
     * @param selectedOperator_SubSystemRoles 選択オペレーターのオペレーター_サブシステムロール割当群
     * @return コピー追加後のアサインロールDtoリスト
     */
    List<SubSystemRoleGrantedAssignRoleDto> copyAddAssignRoleDtoList(List<SubSystemRoleGrantedCopyRequestAssignRole> currentAssignRoleList, Operator_SubSystemRoles signInOperator_SubSystemRoles, Operator_SubSystemRoles selectedOperator_SubSystemRoles) {

        // 現在のアサインロールリストをアサインロールDtoリスト化（返却の型にコンバート）
        List<SubSystemRoleGrantedAssignRoleDto> copyAddedAssignRoleDtoList = newArrayList();
        for (SubSystemRoleGrantedCopyRequestAssignRole currentAssignRole : currentAssignRoleList) {
            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(Operator_SubSystemRole.createFrom(
                null,
                null,
                currentAssignRole.getSubSystemRole().getCode(),
                currentAssignRole.getValidThruStartDate(),
                currentAssignRole.getValidThruEndDate(),
                null,
                null,
                currentAssignRole.getSubSystemRole()));
            assignRoleDto.setIsModifiable(currentAssignRole.getIsModifiable());
            copyAddedAssignRoleDtoList.add(assignRoleDto);
        }

        // 現在のアサインロールリストのコードをList化（追加除外判定に使用する為）
        List<String> currentAssignRoleCodeList = newArrayList();
        for (SubSystemRoleGrantedCopyRequestAssignRole currentAssignRole : currentAssignRoleList) {
            currentAssignRoleCodeList.add(currentAssignRole.getSubSystemRole().getCode());
        }

        // アサインロールDtoリストに追加
        for(Operator_SubSystemRole operator_SubSystemRole : selectedOperator_SubSystemRoles.getValues()) {
            // サインインオペレーターによる変更可否を判定
            if (!judgeIsModifiable(operator_SubSystemRole.getSubSystemRoleCode(), signInOperator_SubSystemRoles)) {
                continue;
            }
            // 現在のアサインロールリストに存在するロールは除外
            if (currentAssignRoleCodeList.contains(operator_SubSystemRole.getSubSystemRoleCode())) {
                continue;
            }

            SubSystemRoleGrantedAssignRoleDto assignRoleDto = new SubSystemRoleGrantedAssignRoleDto();
            assignRoleDto.setOperator_SubSystemRole(Operator_SubSystemRole.createFrom(
                operator_SubSystemRole.getOperator_SubSystemRoleId(),
                operator_SubSystemRole.getOperatorId(),
                operator_SubSystemRole.getSubSystemRoleCode(),
                operator_SubSystemRole.getValidThruStartDate(),
                operator_SubSystemRole.getValidThruEndDate(),
                operator_SubSystemRole.getRecordVersion(),
                operator_SubSystemRole.getOperator(),
                operator_SubSystemRole.getSubSystemRole()));
            assignRoleDto.setIsModifiable(true);

            copyAddedAssignRoleDtoList.add(assignRoleDto);
        }

        return copyAddedAssignRoleDtoList;
    }

    // ToDo:★staticメソッドで共通化した方がいいか？staticメソッドは使用しない方向か？
    //  小さい共通のユーティリティ化するのはどうか？
    //  例えば SubSystemRoleGrantedQueryUtil
    //  （現在存在するstaticメソッド：static void checkBranchBelongJa）
    //  例えば OperatorCommandUtil
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
