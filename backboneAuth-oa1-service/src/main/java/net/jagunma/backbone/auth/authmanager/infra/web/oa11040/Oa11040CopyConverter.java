package net.jagunma.backbone.auth.authmanager.infra.web.oa11040;

import java.util.ArrayList;
import java.util.Arrays;
import net.jagunma.backbone.auth.authmanager.application.usecase.operatorSubSystemRoleReference.SubSystemCopyRequest;
import net.jagunma.common.ddd.model.criterias.LongCriteria;

/**
 * OA11040 コピー Converter
 */
class Oa11040CopyConverter implements SubSystemCopyRequest {

    /**
     * OA11040 Various OperatorId
     */
    private final Long targetOperatorId;
    private final Long selectedOperatorId;
    private final Long signInOperatorId;

    // コンストラクタ
    Oa11040CopyConverter(Long targetOperatorId, Long selectedOperatorId, Long signInOperatorId) {
        this.targetOperatorId = targetOperatorId;
        this.selectedOperatorId = selectedOperatorId;
        this.signInOperatorId = signInOperatorId;
    }

    // ファクトリーメソッド
    public static Oa11040CopyConverter with(Long targetOperatorId, Long selectedOperatorId, Long signInOperatorId) {
        return new Oa11040CopyConverter(targetOperatorId, selectedOperatorId, signInOperatorId);
    }

    /**
     * ターゲットオペレーターIDのＧｅｔ
     *
     * @return ターゲットオペレーターID
     */
    public Long getTargetOperatorId() {
        return targetOperatorId;
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
     * サインインオペレーターIDのＧｅｔ
     *
     * @return サインインオペレーターID
     */
    public Long getSignInOperatorId() {
        return signInOperatorId;
    }
    /**
     * オペレーターID検索条件のＧｅｔ
     *
     * @return オペレーターID検索条件
     */
    public LongCriteria getOperatorIdCriteria() {
        LongCriteria criteria = new LongCriteria();
        criteria.getIncludes().addAll(new ArrayList<Long>(Arrays.asList(targetOperatorId, selectedOperatorId, signInOperatorId)));
        return criteria;
    }
}
