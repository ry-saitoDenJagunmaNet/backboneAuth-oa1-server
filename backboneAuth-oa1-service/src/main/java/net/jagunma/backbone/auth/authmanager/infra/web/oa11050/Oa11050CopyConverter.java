package net.jagunma.backbone.auth.authmanager.infra.web.oa11050;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedCopyRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.bizTranRoleGrantReference.BizTranRoleGrantedCopyRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11050.vo.Oa11050Vo;

/**
 * OA11050 コピー Converter
 */
class Oa11050CopyConverter implements BizTranRoleGrantedCopyRequest {
    /**
     * OA11050 ViewObject & Various OperatorId
     */
    private final Oa11050Vo vo;
    private final Long signInOperatorId;
    private final Long selectedOperatorId;

    // コンストラクタ
    Oa11050CopyConverter(Oa11050Vo vo, Long signInOperatorId, Long selectedOperatorId) {
        this.vo = vo;
        this.signInOperatorId = signInOperatorId;
        this.selectedOperatorId = selectedOperatorId;
    }

    // ファクトリーメソッド
    public static Oa11050CopyConverter with(Oa11050Vo vo, Long signInOperatorId, Long selectedOperatorId) {
        return new Oa11050CopyConverter(vo, signInOperatorId, selectedOperatorId);
    }

    /**
     * サインインオペレーターIDのＧｅｔ
     *
     * @return サインインオペレーターID
     */
    public Long getSignInOperatorId() {
        return signInOperatorId;
    }
    /**
     * 選択オペレーターIDのＧｅｔ
     *
     * @return 選択オペレーターID
     */
    public Long getSelectedOperatorId() {
        return selectedOperatorId;
    }
    /**
     * アサインロールリストのＧｅｔ
     *
     * @return アサインロールリスト
     */
    public List<BizTranRoleGrantedCopyRequestAssignRole> getAssignRoleList() {
        if (vo.getAssignRoleTableVoList() == null) {
            return newArrayList();
        }

        List<Oa11050AssignRoleTableVo> oa11050AssignRoleTableVoList = vo.getAssignRoleTableVoList();
        List<BizTranRoleGrantedCopyRequestAssignRole> assignRoleList = newArrayList();
        for (Oa11050AssignRoleTableVo oa11050AssignRoleTableVo : oa11050AssignRoleTableVoList) {
            Oa11050CopyAssignRoleConverter oa11050CopyAssignRoleConverter = Oa11050CopyAssignRoleConverter.with(oa11050AssignRoleTableVo);
            assignRoleList.add(oa11050CopyAssignRoleConverter);
        }
        return assignRoleList;
    }
}