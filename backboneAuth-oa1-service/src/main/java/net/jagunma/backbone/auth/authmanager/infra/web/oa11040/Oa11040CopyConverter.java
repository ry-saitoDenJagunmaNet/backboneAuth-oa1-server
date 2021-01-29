package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyRequest;
import net.jagunma.backbone.auth.authmanager.application.usecase.subSystemRoleGrantReference.SubSystemRoleGrantedCopyRequestAssignRole;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040AssignRoleTableVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11040.vo.Oa11040Vo;

/**
 * OA11040 コピー Converter
 */
class Oa11040CopyConverter implements SubSystemRoleGrantedCopyRequest {
//todo:★ 小さく渡すのと 他と同じように vo で渡すのはどちらが良いのか?
    /**
     * OA11040 ViewObject & Various OperatorId
     * Oa11040AssignRoleTableVo & Various OperatorId
     */
    private final Oa11040Vo vo;
//    private final List<Oa11040AssignRoleTableVo> assignRoleTableVoList;
    private final Long signInOperatorId;
    private final Long selectedOperatorId;

    // コンストラクタ
    Oa11040CopyConverter(Oa11040Vo vo, Long signInOperatorId, Long selectedOperatorId) {
        this.vo = vo;
        this.signInOperatorId = signInOperatorId;
        this.selectedOperatorId = selectedOperatorId;
    }
//    Oa11040CopyConverter(List<Oa11040AssignRoleTableVo> assignRoleTableVoList, Long signInOperatorId, Long selectedOperatorId) {
//        this.assignRoleTableVoList = assignRoleTableVoList;
//        this.signInOperatorId = signInOperatorId;
//        this.selectedOperatorId = selectedOperatorId;
//    }

    // ファクトリーメソッド
    public static Oa11040CopyConverter with(Oa11040Vo vo, Long signInOperatorId, Long selectedOperatorId) {
        return new Oa11040CopyConverter(vo, signInOperatorId, selectedOperatorId);
    }
//    public static Oa11040CopyConverter with(List<Oa11040AssignRoleTableVo> assignRoleTableVoList, Long signInOperatorId, Long selectedOperatorId) {
//        return new Oa11040CopyConverter(assignRoleTableVoList, signInOperatorId, selectedOperatorId);
//    }

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
    public List<SubSystemRoleGrantedCopyRequestAssignRole> getAssignRoleList() {
        if (vo.getAssignRoleTableVoList() == null) {
            return newArrayList();
        }

        List<Oa11040AssignRoleTableVo> oa11040AssignRoleTableVoList = vo.getAssignRoleTableVoList();
        List<SubSystemRoleGrantedCopyRequestAssignRole> assignRoleList = newArrayList();
        for (Oa11040AssignRoleTableVo oa11040AssignRoleTableVo : oa11040AssignRoleTableVoList) {
            Oa11040CopyAssignRoleConverter oa11040CopyAssignRoleConverter = Oa11040CopyAssignRoleConverter.with(oa11040AssignRoleTableVo);
            assignRoleList.add(oa11040CopyAssignRoleConverter);
        }
        return assignRoleList;

//        if (assignRoleTableVoList == null) {
//            return newArrayList();
//        }
//
//        List<SubSystemRoleGrantedCopyRequestAssignRole> assignRoleList = newArrayList();
//        for (Oa11040AssignRoleTableVo assignRoleTableVo : assignRoleTableVoList) {
//            Oa11040CopyAssignRoleConverter copyAssignRoleConverter = Oa11040CopyAssignRoleConverter.with(assignRoleTableVo);
//            assignRoleList.add(copyAssignRoleConverter);
//        }
//        return assignRoleList;
    }
}